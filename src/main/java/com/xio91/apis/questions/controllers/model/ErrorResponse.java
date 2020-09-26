package com.xio91.apis.questions.controllers.model;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

	private HttpStatus status;
	private String message;
	private Instant time;
}
