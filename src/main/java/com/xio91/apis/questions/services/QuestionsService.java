package com.xio91.apis.questions.services;

import java.util.List;

import com.xio91.apis.questions.dtos.Question;

public interface QuestionsService {

	List<Question> listQuestions();
	void createQuestion(Question question);
}
