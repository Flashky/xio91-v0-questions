package com.xio91.apis.questions.services.mappers;

import org.mapstruct.Mapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.xio91.apis.questions.controllers.QuestionsRestController;
import com.xio91.apis.questions.controllers.model.Question;
import com.xio91.apis.questions.repositories.entities.QuestionEntity;

@Mapper(componentModel = "spring")
public abstract class QuestionModelAssembler extends RepresentationModelAssemblerSupport<QuestionEntity, Question>{

	public QuestionModelAssembler() {
	    super(QuestionsRestController.class, Question.class);
	  }
	
	public abstract Question toModel(QuestionEntity entity);
	
	public abstract QuestionEntity toEntity(Question model);
 
}
