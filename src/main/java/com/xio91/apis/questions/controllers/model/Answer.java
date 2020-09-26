package com.xio91.apis.questions.controllers.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Answer {

	@Min(value= 0, message = "Must be greater than zero")
	@NotNull(message = "Must be greater than zero")
	private Integer yes;
	
	@Min(value= 0, message = "Must be greater than zero")
	@NotNull(message = "Must be greater than zero")
	private Integer no;
	
}
