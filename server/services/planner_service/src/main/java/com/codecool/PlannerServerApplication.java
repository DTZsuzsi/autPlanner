package com.codecool;


import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class PlannerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlannerServerApplication.class, args);
    }

}

