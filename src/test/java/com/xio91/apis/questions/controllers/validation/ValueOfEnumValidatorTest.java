package com.xio91.apis.questions.controllers.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xio91.apis.questions.controllers.model.Question;
import com.xio91.apis.questions.controllers.model.QuestionStatus;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class ValueOfEnumValidatorTest {

	private static Validator validator;
	private static PodamFactory podamFactory;
	
    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
        // Podam (POJOs) configuration
        podamFactory = new PodamFactoryImpl();
        podamFactory.getStrategy().setDefaultNumberOfCollectionElements(2);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsValidNullEnum() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void testIsValidEnum() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setStatus(QuestionStatus.PENDING.toString());
		
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void testIsValidEnumIgnoreCase() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setStatus(QuestionStatus.PENDING.toString().toLowerCase());
		
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void testIsInvalidEnum() {
		
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setStatus("invalid-status");
		
		Set<ConstraintViolation<Question>> violations = validator.validate(question);
		
		assertFalse(violations.isEmpty());
	}
	
}
