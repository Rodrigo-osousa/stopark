package com.stopark;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition
@EnableRetry
public class StoparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoparkApplication.class, args);
	}

}
