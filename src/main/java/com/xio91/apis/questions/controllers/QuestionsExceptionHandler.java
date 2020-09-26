package com.xio91.apis.questions.controllers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.xio91.apis.questions.controllers.exceptions.ValidationException;
import com.xio91.apis.questions.controllers.model.ErrorResponse;
import com.xio91.apis.questions.services.exceptions.QuestionNotFoundException;

@RestControllerAdvice
public class QuestionsExceptionHandler extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(ValidationException.class)
	protected ResponseEntity<ErrorResponse> handleValidationError(ValidationException ex) { 
		
		ErrorResponse response = ErrorResponse.builder()
				.message(ex.getMessage())
				.status(HttpStatus.BAD_REQUEST)
				.time(Instant.now())
				.build();
		
		return buildResponseWithBody(response);
		
	}
	
	@ExceptionHandler(QuestionNotFoundException.class)
	protected ResponseEntity <ErrorResponse> handleQuestionNotFoundError(QuestionNotFoundException ex) { 
		
		ErrorResponse response = ErrorResponse.builder()
				.message(ex.getMessage())
				.status(HttpStatus.CONFLICT)
				.time(Instant.now())
				.build();
		
		return buildResponseWithBody(response);
		
	}
	
	private ResponseEntity<ErrorResponse> buildResponseWithBody(ErrorResponse response) {
		return ResponseEntity.status(response.getStatus()).body(response);
	}
}
