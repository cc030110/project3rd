package com.spring.project3rd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Project3rdApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project3rdApplication.class, args);
	}

}
