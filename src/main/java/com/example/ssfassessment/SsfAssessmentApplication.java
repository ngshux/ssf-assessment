package com.example.ssfassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.ssfassessment.service.NewsService;

@SpringBootApplication
public class SsfAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsfAssessmentApplication.class, args);
		NewsService.getArticles();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
