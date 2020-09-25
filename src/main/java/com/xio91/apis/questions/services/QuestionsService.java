package com.xio91.apis.questions.services;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.xio91.apis.questions.controllers.model.Question;

public interface QuestionsService {

	PagedModel<Question> listQuestions(Pageable pageable, String author);
	
	Optional<Question> getQuestion(String questionId);
	
	/**
	 * Creates a question.
	 * @param question the question to create.
	 * @return a representation of the created question.
	 */
	Question createQuestion(Question question);
}
