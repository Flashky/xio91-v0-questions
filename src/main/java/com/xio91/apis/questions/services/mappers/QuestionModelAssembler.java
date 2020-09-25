package com.xio91.apis.questions.services.mappers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.mapstruct.Mapper;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.xio91.apis.questions.controllers.QuestionsRestController;
import com.xio91.apis.questions.controllers.model.Author;
import com.xio91.apis.questions.controllers.model.Question;
import com.xio91.apis.questions.repositories.entities.QuestionEntity;

@Mapper(componentModel = "spring")
public abstract class QuestionModelAssembler extends RepresentationModelAssemblerSupport<QuestionEntity, Question>{
	
	public QuestionModelAssembler() {
	    super(QuestionsRestController.class, Question.class);
	  }
	
	public Question toModel(QuestionEntity entity) {
		
		Question questionModel = map(entity);
		
		// Add links
		
		Link self = linkTo(methodOn(QuestionsRestController.class).getQuestion(entity.getId())).withSelfRel();
		
		Author author = questionModel.getAuthor();
		Link authorQuestions = linkTo(methodOn(QuestionsRestController.class).listQuestions(null, author.getName())).withRel("questions");
		questionModel.getAuthor().add(authorQuestions);
		
		questionModel.add(self);
		
		return questionModel;
	}
	
	public abstract Question map(QuestionEntity entity);
	
	public abstract QuestionEntity toEntity(Question model);
 
}
