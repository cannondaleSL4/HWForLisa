package com.lisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FarmacyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmacyApplication.class, args);
	}

	@Bean
	public CustomSuccessHandler getCustomSuccessHandler(){
		return new CustomSuccessHandler();
	}
}
