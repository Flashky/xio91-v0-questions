package com.xio91.apis.questions.controllers.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "authors")
public class Author extends RepresentationModel<Question> {

	private String name;
	
}
