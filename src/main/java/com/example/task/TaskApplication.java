package com.example.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EntityScan("com.example")
@ComponentScan("com.example")
@EnableJpaRepositories("com.example")
public class TaskApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}
}
