package com.vallete.portfolio.backendjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendJavaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BackendJavaApplication.class, args);
	}

}
