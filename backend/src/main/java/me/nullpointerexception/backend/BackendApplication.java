package me.nullpointerexception.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableMethodSecurity
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/register").allowedOrigins("http://localhost:3000");
				registry.addMapping("/login").allowedOrigins("http://localhost:3000");
				registry.addMapping("/setPassword").allowedOrigins("http://localhost:3000");
			}
		};
	}
}
