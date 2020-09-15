package com.example.infrastructureForProjects;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InfrastructureForProjectsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfrastructureForProjectsApplication.class, args);
	}

	@Bean
	LayoutDialect thymeleafDialect() {
		return new LayoutDialect();
	}

}
