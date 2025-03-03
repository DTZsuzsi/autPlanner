package com.codecool.server.service;

import com.codecool.server.model.UserCheckRequest;
import com.codecool.server.model.UserMessage;
import org.apache.catalina.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AuthService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public AuthService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private CompletableFuture<String> userResponseFuture = new CompletableFuture<>();

    @RabbitListener(queues = "userQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message from user-service: " + message);
        userResponseFuture.complete(message);
    }

    public CompletableFuture<String> getUserResponseFuture() {
        return userResponseFuture;
    }

    public void resetUserResponseFuture() {
        userResponseFuture = new CompletableFuture<>();
    }

    public void sendMessage(UserMessage userMessage) {
        rabbitTemplate.convertAndSend("userQueue", userMessage);

    }


    public void checkUser(UserCheckRequest userCheckRequest) {
        rabbitTemplate.convertAndSend("userQueue", userCheckRequest);
    }


}
