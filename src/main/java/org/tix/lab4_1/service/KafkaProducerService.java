package org.tix.lab4_1.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.tix.lab4_1.model.mainDB.ExpertMessage;

@Service
public class KafkaProducerService {


    private final KafkaTemplate<String, ExpertMessage> expertMessageKafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, ExpertMessage> expertMessageKafkaTemplate) {
        this.expertMessageKafkaTemplate = expertMessageKafkaTemplate;
    }

    public void sendMessage(ExpertMessage expertMessage){
        expertMessageKafkaTemplate.send("expertResponse",expertMessage);
    }

}
