package com.application.component;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventSubscriber {

    @JmsListener(destination = "employee-events")
    public void onMessage(String message) {
        // Save the audit log (this could be a separate table in the database)
        System.out.println("Received event: " + message);
    }
}
