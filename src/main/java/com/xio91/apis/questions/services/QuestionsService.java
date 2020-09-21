package com.xio91.apis.questions.services;

import java.util.List;

import com.xio91.apis.questions.dtos.Question;

public interface QuestionsService {

	List<Question> listQuestions();
	
	/**
	 * Creates a question.
	 * @param question the question to create.
	 * @return a representation of the created question.
	 */
	Question createQuestion(Question question);
}
