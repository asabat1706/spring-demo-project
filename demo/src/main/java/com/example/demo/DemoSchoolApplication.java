package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.demo.repository")
@EntityScan("com.example.demo.model")
@EnableJpaAuditing(auditorAwareRef = "auditImpl")
public class DemoSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSchoolApplication.class, args);
	}

}
