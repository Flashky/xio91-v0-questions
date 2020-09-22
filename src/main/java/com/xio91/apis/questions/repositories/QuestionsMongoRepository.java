package com.xio91.apis.questions.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.xio91.apis.questions.repositories.entities.QuestionEntity;

@RepositoryRestResource(collectionResourceRel = "questions", path = "questions")
public interface QuestionsMongoRepository extends MongoRepository<QuestionEntity, String> {
	
	@Override
	default Page<QuestionEntity> findAll(Pageable pageable) {
	    return findAllByOrderByCreatedDateDesc(pageable);
	}

	
	@RestResource(exported = false)
	Page<QuestionEntity> findAllByOrderByCreatedDateDesc(Pageable pageable);
	
	@RestResource(path = "author", rel = "author")
	List<QuestionEntity> findByAuthor(String author);
}
