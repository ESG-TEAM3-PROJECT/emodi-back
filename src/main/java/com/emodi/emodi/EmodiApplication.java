package com.emodi.emodi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
public class EmodiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmodiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
