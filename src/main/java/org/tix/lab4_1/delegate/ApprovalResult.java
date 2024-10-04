package org.tix.lab4_1.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.tix.lab4_1.service.ApprovingService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApprovalResult implements JavaDelegate {
    private final ApprovingService approvingService;

    public ApprovalResult(ApprovingService approvingService) {
        this.approvingService = approvingService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.valueOf((String) delegateExecution.getVariable("user_id"));
        String rowCardsId = (String) delegateExecution.getVariable("cards_id");
        List<Long> cardsId = Arrays.stream(rowCardsId.split(",")).map(Long::valueOf).collect(Collectors.toList());
        approvingService.getResult(id,cardsId);
    }
}
