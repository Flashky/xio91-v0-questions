package com.xio91.apis.questions.repositories.entities;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Document(collection = "questions")
@Data
public class QuestionEntity {

	@Id
	private String id;

	private String text;
	
	private String author;
	private AnswerEntity answer;
	
	@CreatedDate
	@JsonFormat(timezone = "GMT+02:00")
	@JsonProperty(access = Access.READ_ONLY)
	private Date createdDate;
	
	@LastModifiedDate
	@JsonFormat(timezone = "GMT+02:00")
	private Date lastModifiedDate;
	
}
