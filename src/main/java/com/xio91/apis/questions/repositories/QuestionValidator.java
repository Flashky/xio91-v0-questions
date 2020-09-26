package com.xio91.apis.questions.repositories;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.xio91.apis.questions.controllers.exceptions.ValidationException;
import com.xio91.apis.questions.repositories.entities.QuestionEntity;

@RepositoryEventHandler(QuestionEntity.class)
public class QuestionValidator {

  @HandleBeforeCreate(QuestionEntity.class) 
  public void handleQuestionCreate(QuestionEntity p) {
    if(p.getText() == null) {
    	throw new ValidationException("text is mandatory");
    }
  }
}
