package com.xio91.apis.questions.controllers.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class QuestionValidationTest {

	
	private static ValidatorFactory validatorFactory;
	private static Validator validator;
	private static PodamFactory podamFactory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    validatorFactory = Validation.buildDefaultValidatorFactory();
	    validator = validatorFactory.getValidator();
	    
	    podamFactory = new PodamFactoryImpl();
	    podamFactory.getStrategy().setDefaultNumberOfCollectionElements(2);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testQuestionOk() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertTrue(violations.isEmpty());
		
	}

	@Test
	public void testQuestionEmptyText() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setText(StringUtils.EMPTY);
		
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertFalse(violations.isEmpty());
		
	}
	
	@Test
	public void testQuestionNullText() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setText(null);
		
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertFalse(violations.isEmpty());
		
	}
	
	@Test
	public void testQuestionAuthorNull() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setAuthor(null);
		
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertFalse(violations.isEmpty());
		
	}
	
	@Test
	public void testQuestionAuthorEmptyName() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		question.getAuthor().setName(StringUtils.EMPTY);
		
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertFalse(violations.isEmpty());
		
	}
	
	@Test
	public void testQuestionAuthorNullName() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		question.getAuthor().setName(null);
		
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertFalse(violations.isEmpty());
		
	}
	
	@Test
	public void testQuestionAnswerNullOk() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setAnswer(null);
		
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertTrue(violations.isEmpty());
		
	}
	
	@Test
	public void testQuestionAnswerYesNull() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		question.getAnswer().setYes(null);
		
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertFalse(violations.isEmpty());
		
	}
	
	@Test
	public void testQuestionAnswerNoNull() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		question.getAnswer().setNo(null);
		
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertFalse(violations.isEmpty());
		
	}
}
