package com.codecool.server.configuration;

import ch.qos.logback.classic.pattern.MessageConverter;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue authStringQueue() {
        return new Queue("authStringQueue", true);
    }

    @Bean
    public Queue userQueue() {
        return new Queue("userStringQueue", true);
    }

    @Bean
    public Queue authRequestQueue() {
        return new Queue("authRequestQueue", true);
    }

    @Bean
    public Queue userRequestQueue() {
        return new Queue("userRequestQueue", true);
    }

    @Bean
    public Queue authUserQueue(){return new Queue("authUserQueue", true);}

    @Bean
    public Queue userUserQueue(){return new Queue("userUserQueue", true);}

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);  // 🔥 Ensure JSON conversion
        return template;
    }

}
