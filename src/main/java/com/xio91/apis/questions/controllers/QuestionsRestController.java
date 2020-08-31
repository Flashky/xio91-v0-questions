package com.xio91.apis.questions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xio91.apis.questions.repositories.QuestionsMongoRepository;
import com.xio91.apis.questions.repositories.entities.Question;

@RestController
@RequestMapping("/xio91/v0/questions")
public class QuestionsRestController {
	
	@Autowired
	private QuestionsMongoRepository questionsMongoRepository;
	
	@GetMapping
	public List<Question> listQuestions() {
		return questionsMongoRepository.findAll();
		
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createQuestion(Question question) {
		questionsMongoRepository.insert(question);
	}
}
 