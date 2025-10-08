package com.example.API.MODELO.MIERCOLES;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ApiModeloMiercolesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiModeloMiercolesApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Aplica a todos los endpoints
						.allowedOrigins("*") // Permite todos los orígenes
						.allowedMethods("*") // Permite todos los métodos HTTP
						.allowedHeaders("*"); // Permite todos los headers
			}
		};
	}

}
