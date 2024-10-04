package org.tix.lab4_1.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.tix.lab4_1.DTO.ExpertMessageDTO;
import org.tix.lab4_1.service.ApprovingService;
import org.tix.lab4_1.util.UserRepository;

@Component
public class ShowCardsById implements JavaDelegate {
    private final UserRepository userRepository;
    private final ApprovingService approvingService;
    public ShowCardsById(UserRepository userRepository, ApprovingService approvingService) {
        this.userRepository = userRepository;
        this.approvingService = approvingService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.valueOf((String) delegateExecution.getVariable("user_id"));
        ExpertMessageDTO expertMessageDTO =  approvingService.getInfo(id);
        delegateExecution.setVariable("cards_for_approval",expertMessageDTO.toString());

    }
}
