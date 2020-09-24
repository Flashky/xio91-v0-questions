package com.xio91.apis.questions.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.xio91.apis.questions.controllers.model.ErrorResponse;

@RestControllerAdvice
public class QuestionsExceptionHandler extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(ValidationException.class)
	protected ResponseEntity<ErrorResponse> handleValidationError(ValidationException ex) { 
		
		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		
		return ResponseEntity.badRequest().body(response);
		
	}
}
