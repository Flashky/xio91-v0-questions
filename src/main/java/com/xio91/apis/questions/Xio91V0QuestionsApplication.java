package com.xio91.apis.questions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.xio91.apis.questions.repositories.QuestionValidator;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongoAuditing // Support for @CreatedDate on Documents
public class Xio91V0QuestionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Xio91V0QuestionsApplication.class, args);
	}

	@Bean
	QuestionValidator personEventHandler() {
	    return new QuestionValidator();
	  }
}
