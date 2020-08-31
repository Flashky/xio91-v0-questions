package com.xio91.apis.questions.repositories;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xio91.apis.questions.repositories.entities.Question;

@RunWith(SpringRunner.class)
@SpringBootTest
class QuestionsMongoRepositoryTest {
	
	@Autowired
	QuestionsMongoRepository questionsMongoRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {

		List<Question> questions = questionsMongoRepository.findAll();
		
		System.out.println(questions);
	}

}
