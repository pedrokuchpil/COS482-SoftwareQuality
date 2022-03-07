package com.mycompany.myapp.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class OrderAccepted implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("######################################################################################");
        System.out.println("######################################################################################");
        System.out.println("######################################################################################");
        System.out.println("Pedido aceito");
        System.out.println("######################################################################################");
        System.out.println("######################################################################################");
        System.out.println("######################################################################################");
    }
}
