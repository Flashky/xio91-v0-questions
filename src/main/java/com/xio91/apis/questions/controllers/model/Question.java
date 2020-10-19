package com.xio91.apis.questions.controllers.model;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.xio91.apis.questions.controllers.validation.ValueOfEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "questions")
@JsonInclude(Include.NON_NULL)
public class Question extends RepresentationModel<Question> {

	private String id;
	
	@NotBlank(message = "Text is mandatory")
	private String text;
	
	@Valid 
	@NotNull(message = "Author is mandatory")
	private Author author;
	
	@Valid
	private Answer answer;

	@ValueOfEnum(QuestionStatus.class)
	private String status;
	
	@JsonFormat(timezone = "GMT+02:00")
	private Date createdDate;
	
}
