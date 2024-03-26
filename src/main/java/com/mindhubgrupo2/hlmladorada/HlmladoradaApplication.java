package com.mindhubgrupo2.hlmladorada;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HlmladoradaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HlmladoradaApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData() {
		return args -> {

		};
	}

}
