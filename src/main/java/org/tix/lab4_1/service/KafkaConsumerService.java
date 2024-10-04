package org.tix.lab4_1.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.tix.lab4_1.model.mainDB.ExpertMessage;

@Service
public class KafkaConsumerService {
    private final ApprovingService approvingService;


    public KafkaConsumerService(ApprovingService approvingService) {
        this.approvingService = approvingService;
    }

    @KafkaListener(topics = "expertAudition",groupId = "my-group", containerFactory = "userKafkaListenerContainerFactory")
    public void receiveMessage(ExpertMessage expertMessage){
        approvingService.saveExpertMessage(expertMessage);
        System.out.println(expertMessage);
    }


}
