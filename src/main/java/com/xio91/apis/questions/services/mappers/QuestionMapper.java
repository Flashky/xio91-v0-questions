package com.xio91.apis.questions.services.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.xio91.apis.questions.dtos.Question;
import com.xio91.apis.questions.repositories.entities.QuestionEntity;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

	List<Question> map(List<QuestionEntity> questions);
	
	Question map(QuestionEntity question);
	QuestionEntity map(Question question);

}