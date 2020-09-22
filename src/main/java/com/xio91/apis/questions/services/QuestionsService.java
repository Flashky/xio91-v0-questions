package com.xio91.apis.questions.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.xio91.apis.questions.dtos.Question;

public interface QuestionsService {

	Page<Question> listQuestions(int page, int pageSize);
	
	Optional<Question> getQuestion(String questionId);
	
	/**
	 * Creates a question.
	 * @param question the question to create.
	 * @return a representation of the created question.
	 */
	Question createQuestion(Question question);
}
