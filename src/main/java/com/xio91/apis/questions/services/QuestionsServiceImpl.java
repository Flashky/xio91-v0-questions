package com.xio91.apis.questions.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.xio91.apis.questions.controllers.model.Question;
import com.xio91.apis.questions.repositories.QuestionsMongoRepository;
import com.xio91.apis.questions.repositories.entities.AuthorEntity;
import com.xio91.apis.questions.repositories.entities.QuestionEntity;
import com.xio91.apis.questions.services.exceptions.QuestionNotFoundException;
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
	public PagedModel<Question> listQuestions(Pageable pageable, String author) {
		
		
		ExampleMatcher caseInsensitiveMatcher = ExampleMatcher.matchingAll()
																.withIgnoreCase()
																.withStringMatcher(StringMatcher.EXACT);
		
		QuestionEntity entity = new QuestionEntity();
		entity.setAuthor(new AuthorEntity());
		entity.getAuthor().setName(author);

		Example<QuestionEntity> exampleEntity = Example.of(entity, caseInsensitiveMatcher);
		
		Page<QuestionEntity> questionEntities = questionsRepository.findAll(exampleEntity, pageable);

		
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

	@Override
	public void updateQuestion(Question question) {

		Optional<QuestionEntity> savedEntity = questionsRepository.findById(question.getId());
		
		if(!savedEntity.isPresent()) {
			throw new QuestionNotFoundException("Provide an existing question id");
		}
		
		
		// Prepare entity to save. createdDate field must not be modified.
		QuestionEntity questionEntity = questionModelAssembler.toEntity(question);
		questionEntity.setCreatedDate(savedEntity.get().getCreatedDate());
		
		questionsRepository.save(questionEntity);
		
	}

}
