package com.xio91.apis.questions.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.xio91.apis.questions.controllers.model.Question;
import com.xio91.apis.questions.services.QuestionsService;

@RestController
@RequestMapping("/questions")
public class QuestionsRestController {
	
	@Autowired
	private QuestionsService questionsService;
    
	@GetMapping
	public ResponseEntity<PagedModel<Question>> listQuestions(@SortDefault(sort = "createdDate", direction = Direction.DESC) 
																Pageable pageable,
																@RequestParam(required = false, value = "author.name") String authorName) {
		
		PagedModel<Question> questions = questionsService.listQuestions(pageable, authorName);

		if(!questions.getContent().isEmpty()) {
			return ResponseEntity.ok().body(questions); 
		} 
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{questionId}")
	public ResponseEntity<Question> getQuestion(@PathVariable String questionId) {
		
		Optional<Question> question = questionsService.getQuestion(questionId);

		if(question.isPresent()) {
			return ResponseEntity.ok().body(question.get());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Question> createQuestion(@RequestBody @Valid Question question) {
		
		Question createdQuestion = questionsService.createQuestion(question);
		
		// Prepare header Location
		URI location = getLocationUri(createdQuestion);
		
		return ResponseEntity.created(location).body(createdQuestion);
		
	}

	@PutMapping("/{questionId}")
	public ResponseEntity<Question> updateQuestion(@PathVariable String questionId, 
												 	@Valid @RequestBody Question question) {
		
		question.setId(questionId);
		questionsService.updateQuestion(question);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Generates a location URI from the current request path + /{id}
	 * @param question the question to obtain the location URI from.
	 * @return The question location URI.
	 */
	private URI getLocationUri(Question question) {
		
		return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(question.getId())
                .toUri();

	}
}
 