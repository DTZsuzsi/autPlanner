package com.codecool.childservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class ChildServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildServerApplication.class, args);
    }

}

