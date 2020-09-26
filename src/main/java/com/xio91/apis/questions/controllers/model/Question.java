package com.xio91.apis.questions.controllers.model;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "questions")
public class Question extends RepresentationModel<Question> {

	private String id;
	
	private String text;
	private Author author;
	private Answer answer;
	
	@JsonFormat(timezone = "GMT+02:00")
	private Date createdDate;
	
}
