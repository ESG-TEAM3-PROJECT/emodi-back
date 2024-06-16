package com.emodi.emodi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmodiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmodiApplication.class, args);
	}

}
