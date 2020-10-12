package com.xio91.apis.questions.services.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import com.xio91.apis.questions.controllers.model.Answer;
import com.xio91.apis.questions.controllers.model.Author;
import com.xio91.apis.questions.controllers.model.Question;
import com.xio91.apis.questions.repositories.entities.AnswerEntity;
import com.xio91.apis.questions.repositories.entities.AuthorEntity;
import com.xio91.apis.questions.repositories.entities.QuestionEntity;

import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class QuestionModelAssemblerTest {

	private static PodamFactoryImpl podamFactory;
	
	@InjectMocks
	private QuestionModelAssembler questionModelAssembler = new QuestionModelAssemblerImpl();
	
	@Spy
	private AuthorProcessor authorProcessor;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// Podam (POJOs) configuration
	    podamFactory = new PodamFactoryImpl();
	    podamFactory.getStrategy().setDefaultNumberOfCollectionElements(2);
	    
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToModel() {
		
		// Prepare POJOs
		QuestionEntity expected = podamFactory.manufacturePojo(QuestionEntity.class);
		
		// Execute method
		Question result = questionModelAssembler.toModel(expected);
		
		// Assertions - Model
		test(expected, result);
		
		// Assertions - Links
		List<Link> links = result.getLinks(IanaLinkRelations.SELF);
		
		assertEquals(1, links.size());
		assertEquals("/questions/"+result.getId(), links.get(0).getHref());
		assertEquals(IanaLinkRelations.SELF, links.get(0).getRel());
	}
	
	@Test
	public void testToModelNull() {
		
		// Execute method
		Question result = questionModelAssembler.toModel(null);
		
		// Assertions - Model
		assertNull(result);

	}
	
	@Test
	public void testToEntity() {
		
		// Prepare POJOs
		Question expected = podamFactory.manufacturePojo(Question.class);
		
		// Execute method
		QuestionEntity result = questionModelAssembler.toEntity(expected);
		
		// Assertions
		test(expected, result);
		
	}
	
	
	@Test
	public void testToEntityNull() {
		
		// Execute method
		QuestionEntity result = questionModelAssembler.toEntity(null);
		
		// Assertions
		assertNull(result);
		
	}


	private void test(QuestionEntity expected, Question result) {

		assertNotNull(result);
		
		assertEquals(expected.getId(), result.getId());
		assertEquals(expected.getText(), result.getText());
		assertEquals(expected.getCreatedDate(), result.getCreatedDate());
		
		test(expected.getAuthor(), result.getAuthor());
		test(expected.getAnswer(), result.getAnswer());
	}

	private void test(AnswerEntity expected, Answer result) {

		assertNotNull(result);
		
		assertEquals(expected.getNo(), result.getNo());
		assertEquals(expected.getYes(), result.getYes());
		
	}

	private void test(AuthorEntity expected, Author result) {

		assertNotNull(result);
		
		assertEquals(expected.getName(), result.getName());
		assertFalse(result.getLinks().isEmpty());
		
		
	}

	private void test(Question expected, QuestionEntity result) {
		
		assertNotNull(result);
		
		assertEquals(expected.getId(), result.getId());
		assertEquals(expected.getText(), result.getText());
		assertEquals(expected.getCreatedDate(), result.getCreatedDate());
		
		test(expected.getAuthor(), result.getAuthor());
		test(expected.getAnswer(), result.getAnswer());
		
	}

	private void test(Author expected, AuthorEntity result) {

		assertNotNull(result);
		assertEquals(expected.getName(), result.getName());
		
	}

	private void test(Answer expected, AnswerEntity result) {
		
		assertNotNull(result);
		assertEquals(expected.getNo(), result.getNo());
		assertEquals(expected.getYes(), result.getYes());
	}
}
