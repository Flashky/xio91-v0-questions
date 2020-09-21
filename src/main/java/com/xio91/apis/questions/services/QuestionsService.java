package com.xio91.apis.questions.services;

import java.util.List;
import java.util.Optional;

import com.xio91.apis.questions.dtos.Question;

public interface QuestionsService {

	List<Question> listQuestions();
	
	Optional<Question> getQuestion(String questionId);
	
	/**
	 * Creates a question.
	 * @param question the question to create.
	 * @return a representation of the created question.
	 */
	Question createQuestion(Question question);
}
