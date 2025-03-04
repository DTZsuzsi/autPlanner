package com.codecool.server;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class UserServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServerApplication.class, args);
	}

}
