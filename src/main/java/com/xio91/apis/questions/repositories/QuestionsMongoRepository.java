package com.xio91.apis.questions.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xio91.apis.questions.repositories.entities.Question;

public interface QuestionsMongoRepository extends MongoRepository<Question, String> {

}
