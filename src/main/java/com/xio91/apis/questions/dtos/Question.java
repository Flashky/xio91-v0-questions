package com.xio91.apis.questions.dtos;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Relation(collectionRelation = "questions")
public class Question extends RepresentationModel<Question> {

	private String id;
	
	private String text;
	private String author;
	private Answer answer;
	
	@JsonIgnore
	@JsonFormat(timezone = "GMT+02:00")
	private Date createdDate;
	
}
