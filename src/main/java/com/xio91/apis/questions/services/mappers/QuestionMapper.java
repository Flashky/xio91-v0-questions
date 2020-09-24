package com.xio91.apis.questions.services.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.xio91.apis.questions.controllers.model.Question;
import com.xio91.apis.questions.repositories.entities.QuestionEntity;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

	List<Question> map(List<QuestionEntity> questions);
	
	/*
	default Page<Question> map(Page<QuestionEntity> questions) {
		return questions.map(entity -> this.map(entity));
	}*/
	
	Question map(QuestionEntity question);
	QuestionEntity map(Question question);

}