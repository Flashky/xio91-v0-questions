package com.xio91.apis.questions.services.mappers;

import org.mapstruct.Mapper;

import com.xio91.apis.questions.controllers.model.Answer;
import com.xio91.apis.questions.repositories.entities.AnswerEntity;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
	
	AnswerEntity map(Answer answer);

}
