package com.example.questapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


//Disabled spring security for testing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class QuestappApplication {

	public static void main(String[] args) {

		SpringApplication.run(QuestappApplication.class, args);
	}

}
