package com.xio91.apis.questions.controllers.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiValidationError extends ApiError {

	private String object;
	private String field;
}
