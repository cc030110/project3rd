package com.spring.project3rd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("com.spring.project3rd.domain.user")
public class Project3rdApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project3rdApplication.class, args);
	}

}
