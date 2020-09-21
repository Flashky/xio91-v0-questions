package com.xio91.apis.questions.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xio91.apis.questions.dtos.Question;
import com.xio91.apis.questions.repositories.QuestionsMongoRepository;
import com.xio91.apis.questions.repositories.entities.QuestionEntity;
import com.xio91.apis.questions.services.mappers.QuestionMapper;

@Service
public class QuestionsServiceImpl implements QuestionsService {

	@Autowired
	private QuestionsMongoRepository questionsRepository;
	
	@Autowired
	private QuestionMapper mapper;
	
	@Override
	public List<Question> listQuestions() {
		
		List<QuestionEntity> questions = questionsRepository.findAll();
		return mapper.map(questions);
	}

	@Override
	public Question createQuestion(Question question) {

		// TODO add business logic
		QuestionEntity questionEntity = mapper.map(question);
		QuestionEntity savedQuestion = questionsRepository.insert(questionEntity);
		
		return mapper.map(savedQuestion);
	}

}
