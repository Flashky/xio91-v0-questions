package com.xio91.apis.questions.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Question {

	private String id;
	
	private String text;
	private String author;
	private Answer answer;
	
	@JsonIgnore
	@JsonFormat(timezone = "GMT+02:00")
	private Date createdDate;
	
}
