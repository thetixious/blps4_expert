package org.tix.lab4_1.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.tix.lab4_1.DTO.CreditCardDTO;
import org.tix.lab4_1.DTO.ExpertMessageDTO;
import org.tix.lab4_1.model.bankDB.Manager;
import org.tix.lab4_1.model.mainDB.ExpertMessage;
import org.tix.lab4_1.repo.bankDB.ManagerRepository;
import org.tix.lab4_1.repo.mainDB.ExpertMessageRepository;
import org.tix.lab4_1.util.CreditCardMapper;
import org.tix.lab4_1.util.ExpertMessageMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApprovingService {
    private final PlatformTransactionManager transactionManager;
    private final ManagerRepository managerRepository;
    private final ExpertMessageRepository expertMessageRepository;
    private final KafkaProducerService kafkaProducerService;
    private List<CreditCardDTO> response = new ArrayList<>();
    private final CreditCardMapper creditCardMapper;
    private final ExpertMessageMapper expertMessageMapper;

    public ApprovingService(PlatformTransactionManager transactionManager, ManagerRepository managerRepository, ExpertMessageRepository expertMessageRepository, KafkaProducerService kafkaProducerService, CreditCardMapper creditCardMapper, ExpertMessageMapper expertMessageMapper) {
        this.transactionManager = transactionManager;
        this.managerRepository = managerRepository;
        this.expertMessageRepository = expertMessageRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.creditCardMapper = creditCardMapper;
        this.expertMessageMapper = expertMessageMapper;
    }

    public ResponseEntity<?> getResult(Long id, List<Long> cardsId) {
        ExpertMessage expertMessage = expertMessageRepository.findByUserId(id).orElseThrow();
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        try {
            transactionTemplate.execute(status -> {

                expertMessage.getPreferredCards().removeIf(card -> !cardsId.contains(card.getId()));

                Manager manager = managerRepository.findFirstByStatusFalse().orElseThrow();
                String data = expertMessage.getCandidateName() +
                        expertMessage.getCandidateSurname() +
                        expertMessage.getCandidatePassport();
                manager.setData(data);
                manager.setStatus(true);
                response = expertMessage.getPreferredCards().stream().map(creditCardMapper::toDTO).collect(Collectors.toList());
                expertMessageRepository.save(expertMessage);
                managerRepository.save(manager);
                kafkaProducerService.sendMessage(expertMessage);
                return response;
            });

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    public void saveExpertMessage(ExpertMessage expertMessage) {
        expertMessageRepository.save(expertMessage);
    }

    @Transactional
    public ExpertMessageDTO getInfo(Long id) {
        ExpertMessage message = expertMessageRepository.findByUserId(id).orElseThrow();
        return expertMessageMapper.toDTO(message);
    }

}
