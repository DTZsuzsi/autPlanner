package com.codecool.server.service.RabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ProducerService {

    private final RabbitTemplate rabbitTemplate;
        private final String exchangeName = "example.exchange";

        public ProducerService(RabbitTemplate rabbitTemplate) {
            this.rabbitTemplate = rabbitTemplate;
        }

        public void sendMessage(String message) {
            rabbitTemplate.convertAndSend(exchangeName, "", message);
        }
    }

