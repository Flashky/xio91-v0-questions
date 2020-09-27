package com.xio91.apis.questions.controllers.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.xio91.apis.questions.controllers.model.ApiError;
import com.xio91.apis.questions.controllers.model.ApiValidationError;
import com.xio91.apis.questions.controllers.model.ErrorResponse;
import com.xio91.apis.questions.controllers.model.ErrorResponse.ErrorResponseBuilder;
import com.xio91.apis.questions.services.exceptions.QuestionNotFoundException;

@RestControllerAdvice
public class QuestionsExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(QuestionNotFoundException.class)
	protected ResponseEntity<Object> handleQuestionNotFoundError(QuestionNotFoundException ex) { 
		
		ErrorResponse response = ErrorResponse.builder()
				.message(ex.getMessage())
				.status(HttpStatus.CONFLICT)
				.time(Instant.now())
				.build();
		
		return buildResponseWithBody(response);
		
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		
		List<ApiError> validationErrors = new ArrayList<>();
		
		ErrorResponseBuilder builder = ErrorResponse.builder()
				.message("Validation error")
				.status(HttpStatus.BAD_REQUEST)
				.time(Instant.now())
				.errors(validationErrors);

		
		 ex.getBindingResult().getAllErrors().forEach((error) -> {
			 
		        String fieldName = ((FieldError) error).getField();
		        
			 	ApiValidationError validationError = new ApiValidationError();
			 	validationError.setObject(error.getObjectName());
			 	validationError.setField(fieldName);
			 	validationError.setMessage(error.getDefaultMessage());
		        
			 	validationErrors.add(validationError);
		    });
		 
		return buildResponseWithBody(builder.build());
	
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorResponseBuilder builder = ErrorResponse.builder()
				.message("Malformed or missing request body")
				.status(HttpStatus.BAD_REQUEST)
				.time(Instant.now());
		
		
		return buildResponseWithBody(builder.build());
	}
	
	private ResponseEntity<Object> buildResponseWithBody(ErrorResponse response) {
		return ResponseEntity.status(response.getStatus()).body(response);
	}
}
