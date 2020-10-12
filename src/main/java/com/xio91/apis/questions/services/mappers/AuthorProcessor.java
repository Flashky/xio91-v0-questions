package com.xio91.apis.questions.services.mappers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.xio91.apis.questions.controllers.QuestionsRestController;
import com.xio91.apis.questions.controllers.model.Author;

@Component
public class AuthorProcessor implements RepresentationModelProcessor<Author> {

	@Override
	public Author process(Author model) {
		
		if(model != null) {
			Link authorQuestions = linkTo(methodOn(QuestionsRestController.class).listQuestions(null, model.getName())).withRel("questions");
			model.add(authorQuestions);
		}
		
		return model;
	}

}
