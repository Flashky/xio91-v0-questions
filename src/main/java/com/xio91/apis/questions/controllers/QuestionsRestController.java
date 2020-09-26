package com.xio91.apis.questions.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xio91.apis.questions.controllers.model.Question;
import com.xio91.apis.questions.services.QuestionsService;

@RestController
@RequestMapping("/xio91/v0/questions")
public class QuestionsRestController {
	
	@Autowired
	private QuestionsService questionsService;
    
	@GetMapping
	public ResponseEntity<PagedModel<Question>> listQuestions(@SortDefault(sort = "createdDate") 
																Pageable pageable,
																@RequestParam(required = false) String author) {
		
		PagedModel<Question> questions = questionsService.listQuestions(pageable, author);

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
	public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
		
		// TODO add mandatory parameters validation
		Question createdQuestion = questionsService.createQuestion(question);
		
		// Prepare header Location
		URI location = getLocationUri(createdQuestion);
		
		return ResponseEntity.created(location).body(createdQuestion);
		
	}

	@PutMapping
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
		
		// TODO add mandatory parameters validation
		questionsService.updateQuestion(question);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Generates a location URI from a representation model link with "self" rel, if present.
	 * @param representationModel the model to obtain the location URI from.
	 * @return The model location URI. May be <code>null</null> if no self attribute is defined.
	 */
	private URI getLocationUri(RepresentationModel<?> representationModel) {
		
		URI location = null;
		
		Optional<Link> selfLink = representationModel.getLink(IanaLinkRelations.SELF);
		
		if(selfLink.isPresent()) {
			location = selfLink.get().toUri();
		}
		
		return location;
	}
}
 