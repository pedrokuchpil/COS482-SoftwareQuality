package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.Notification;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntegrateThirdPartyDelegate implements JavaDelegate {

    @Autowired
    Notification notification;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        notification.printNotification();
    }
}
