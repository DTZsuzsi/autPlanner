package com.codecool.server.service;

import com.codecool.server.model.UserMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public AuthService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(UserMessage userMessage) {
        rabbitTemplate.convertAndSend("userQueue", userMessage);

    }

    @RabbitListener(queues = "userQueue")
    public void receiveMessage(UserMessage userMessage) {
        System.out.println("Received message: " + userMessage);

    }
}
