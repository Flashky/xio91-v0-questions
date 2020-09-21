package com.xio91.apis.questions.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	@GetMapping("/{questionId}")
	public ResponseEntity<Question> getQuestion(@PathVariable String questionId) {
		
		// TODO add mandatory parameters validation
		Optional<Question> question = questionsService.getQuestion(questionId);

		if(question.isPresent()) {
			return ResponseEntity.ok().body(question.get());
		}
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
		
		// TODO add mandatory parameters validation
		Question createdQuestion = questionsService.createQuestion(question);
		
		// Prepare header Location
		UriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
		uriBuilder.pathSegment(createdQuestion.getId());
		
		return ResponseEntity.created(uriBuilder.build().toUri()).body(createdQuestion);
		
	}
}
 