package com.xio91.apis.questions.services.exceptions;

public class QuestionNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7201596029815578339L;

	public QuestionNotFoundException(String message) {
		super(message);
	}

}
