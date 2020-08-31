package com.xio91.apis.questions.repositories.entities;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Document(collection = "questions")
@Data
public class Question {

	@Id
	private String id;
	private String text;
	private String author;
	private Answer answer;
	
	@CreatedDate
	@JsonFormat(timezone = "GMT+02:00")
	private Date createdDate;
	
	
	
}
