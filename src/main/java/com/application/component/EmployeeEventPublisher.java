package com.application.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.application.model.Employee;

@Component
public class EmployeeEventPublisher {

    @Autowired
    private JmsTemplate jmsTemplate;

    private static final String QUEUE_NAME = "employee-events";

    public void publishCreateEvent(Employee employee) {
        jmsTemplate.convertAndSend(QUEUE_NAME, "Created employee: " + employee.getName());
    }

    public void publishUpdateEvent(Employee employee) {
        jmsTemplate.convertAndSend(QUEUE_NAME, "Updated employee: " + employee.getName());
    }

    public void publishDeleteEvent(Long employeeId) {
        jmsTemplate.convertAndSend(QUEUE_NAME, "Deleted employee with ID: " + employeeId);
    }
}

