package com.reactive.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Spring webflux crud example",
		version = "1.0",
		description = "sample documents"
))
public class ReactiveProgrammingDemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(ReactiveProgrammingDemoApplication.class, args);
	}


}
