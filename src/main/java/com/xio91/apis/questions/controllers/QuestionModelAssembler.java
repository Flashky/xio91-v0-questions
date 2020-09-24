package com.xio91.apis.questions.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.xio91.apis.questions.controllers.model.Question;
import com.xio91.apis.questions.repositories.entities.QuestionEntity;
import com.xio91.apis.questions.services.mappers.QuestionMapper;

@Component
public class QuestionModelAssembler extends RepresentationModelAssemblerSupport<QuestionEntity, Question>{

	@Autowired
	private QuestionMapper mapper;
	
	public QuestionModelAssembler() {
	    super(QuestionsRestController.class, Question.class);
	  }
	
	@Override
	public Question toModel(QuestionEntity entity) {
		return mapper.map(entity);
	}
	
	public QuestionEntity toEntity(Question model) {
		return mapper.map(model);
	}
 
}
