package com.xio91.apis.questions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xio91.apis.questions.dtos.Question;
import com.xio91.apis.questions.services.QuestionsService;

@RestController
@RequestMapping("/xio91/v0/questions")
public class QuestionsRestController {
	
	@Autowired
	private QuestionsService questionsService;
	
	@GetMapping
	public List<Question> listQuestions() {
		
		return questionsService.listQuestions();
		
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void createQuestion(@RequestBody Question question) {
		
		// TODO add mandatory parameters validation
		questionsService.createQuestion(question);
		
	}
}
 