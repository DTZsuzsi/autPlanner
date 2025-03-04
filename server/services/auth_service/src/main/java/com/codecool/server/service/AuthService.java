package com.codecool.server.service;

import com.codecool.server.model.UserCheckRequest;
import com.codecool.server.model.UserMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AuthService {

    private final RabbitTemplate rabbitTemplate;
    private CompletableFuture<String> userResponseFuture = new CompletableFuture<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public AuthService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "userQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message from user-service: " + message);
        userResponseFuture.complete(message);
    }

    @RabbitListener(queues = "authQueue")
    public void receiveAuthMessage(UserMessage userMessage) {
        System.out.println("Received message in userQueue: " + userMessage);
    }


    public CompletableFuture<String> getUserResponseFuture() {
        return userResponseFuture;
    }

    public void resetUserResponseFuture() {
        userResponseFuture = new CompletableFuture<>();
    }

    public void sendMessage(UserMessage userMessage) {
        try {
            String json = objectMapper.writeValueAsString(userMessage);
            System.out.println("Sending JSON: " + json);  // 🔍 Debug: Print actual JSON
        } catch (Exception e) {
            e.printStackTrace();
        }

        rabbitTemplate.convertAndSend("authQueue", userMessage);
        System.out.println("sent message: " + userMessage);
    }

    public void checkUser(UserCheckRequest userCheckRequest) {
        rabbitTemplate.convertAndSend("userQueue", userCheckRequest);
    }

}
