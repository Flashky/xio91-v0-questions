package com.xio91.apis.questions.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public Page<Question> listQuestions(int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<QuestionEntity> questionsPage = questionsRepository.findAll(pageable);
		
		//List<QuestionEntity> questions = questionsRepository.findAll();
		return mapper.map(questionsPage);
	}

	@Override
	public Optional<Question> getQuestion(String questionId) {
		
		Question question = null;
		
		Optional<QuestionEntity> questionEntity = questionsRepository.findById(questionId);
		
		if(questionEntity.isPresent()) {
			question = mapper.map(questionEntity.get());
		}
		
		return Optional.ofNullable(question);
		
	}
	
	@Override
	public Question createQuestion(Question question) {

		// TODO add business logic
		QuestionEntity questionEntity = mapper.map(question);
		QuestionEntity savedQuestion = questionsRepository.insert(questionEntity);
		
		return mapper.map(savedQuestion);
	}

}
