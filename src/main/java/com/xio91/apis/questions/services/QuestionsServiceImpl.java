package com.xio91.apis.questions.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.xio91.apis.questions.controllers.model.Question;
import com.xio91.apis.questions.repositories.QuestionsMongoRepository;
import com.xio91.apis.questions.repositories.entities.QuestionEntity;
import com.xio91.apis.questions.services.mappers.QuestionModelAssembler;

@Service
public class QuestionsServiceImpl implements QuestionsService {

	@Autowired
	private QuestionsMongoRepository questionsRepository;
	
    @Autowired
    private PagedResourcesAssembler<QuestionEntity> pagedResourcesAssembler;
    
    @Autowired
    private QuestionModelAssembler questionModelAssembler;
	
	@Override
	public PagedModel<Question> listQuestions(Pageable pageable) {
		
		Page<QuestionEntity> questionEntities = questionsRepository.findAll(pageable);

		// Convert to DTO model and return
		return pagedResourcesAssembler.toModel(questionEntities, questionModelAssembler);
	}

	@Override
	public Optional<Question> getQuestion(String questionId) {
		
		Optional<QuestionEntity> questionEntity = questionsRepository.findById(questionId);
		
		return questionEntity.map(entity -> questionModelAssembler.toModel(entity));
		
	}
	
	@Override
	public Question createQuestion(Question question) {

		QuestionEntity questionEntity = questionModelAssembler.toEntity(question);
		QuestionEntity savedQuestion = questionsRepository.save(questionEntity);
		
		return questionModelAssembler.toModel(savedQuestion);
	}

}
