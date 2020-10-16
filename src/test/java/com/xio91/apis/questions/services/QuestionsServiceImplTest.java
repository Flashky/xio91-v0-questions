package com.xio91.apis.questions.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.xio91.apis.questions.controllers.model.Question;
import com.xio91.apis.questions.repositories.QuestionsMongoRepository;
import com.xio91.apis.questions.repositories.entities.QuestionEntity;
import com.xio91.apis.questions.services.exceptions.QuestionNotFoundException;
import com.xio91.apis.questions.services.mappers.QuestionModelAssemblerImpl;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class QuestionsServiceImplTest {

	private static PodamFactory podamFactory;
	private UriComponents uriComponents = UriComponentsBuilder.fromPath("/questions").build();

	@InjectMocks
	private QuestionsService questionsService = new QuestionsServiceImpl();
	
	@Mock
	private QuestionsMongoRepository questionsRepository;
	
	@Spy
    private PagedResourcesAssembler<QuestionEntity> pagedResourcesAssembler = new PagedResourcesAssembler<>(null, uriComponents);
    
	@Spy
    private QuestionModelAssemblerImpl questionModelAssembler;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    
	    podamFactory = new PodamFactoryImpl();
	    podamFactory.getStrategy().setDefaultNumberOfCollectionElements(2);
	    
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testListQuestions() {
		
		// Prepare POJOs
		Pageable pageable = getDefaultPageable();
		List<QuestionEntity> expectedQuestions = podamFactory.manufacturePojo(ArrayList.class, QuestionEntity.class);
		Page<QuestionEntity> expected = new PageImpl<>(expectedQuestions, pageable, 2L);
		
		// Mock
		Mockito.doReturn(expected).when(questionsRepository).findAll(any(), (Pageable) any());
		
		// Call to service
		PagedModel<Question> result = questionsService.listQuestions(pageable, null);
		
		// Assertions
		Mockito.verify(questionsRepository).findAll(any(), (Pageable) any());
		
		assertNotNull(result);
		
		// Assertions - Content
		assertNotNull(result.getContent());
		assertEquals(expected.getContent().size(), result.getContent().size());
		
		// Assertions - Pagination
		test(expected.getPageable(), result.getMetadata());
		
		// Assertions - Links
		assertTrue(result.getLink(IanaLinkRelations.SELF).isPresent());
		
	}

	private void test(Pageable expected, PageMetadata result) {


		assertNotNull(result);
		assertEquals(expected.getPageSize(), result.getSize());
		assertEquals(expected.getPageNumber(), result.getNumber());
		
		
	}

	@Test
	public void testGetQuestion() {
		
		
		// Prepare POJOs
		QuestionEntity expected = podamFactory.manufacturePojo(QuestionEntity.class);
		
		// Mock
		Mockito.doReturn(Optional.of(expected)).when(questionsRepository).findById(any());
		
		// Execute method
		Optional<Question> result = questionsService.getQuestion("question-id");
		
		// ASsertions
		Mockito.verify(questionsRepository).findById(any());
		
		assertNotNull(result);
		assertTrue(result.isPresent());
		
	}

	@Test
	public void testCreateQuestion() {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		QuestionEntity expected = podamFactory.manufacturePojo(QuestionEntity.class);
		
		// Mock
		Mockito.doReturn(expected).when(questionsRepository).save(any());
		
		// Execute method
		Question result = questionsService.createQuestion(question);
		
		// Assertions
		Mockito.verify(questionsRepository).save(any());
		
		assertNotNull(result);
		assertEquals(expected.getId(), result.getId()); // Result must have at least the id
		
		
	}

	@Test
	public void testUpdateQuestion() {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		QuestionEntity savedQuestion = podamFactory.manufacturePojo(QuestionEntity.class);
		
		// Mock
		Mockito.doReturn(Optional.of(savedQuestion)).when(questionsRepository).findById(any());
		
		// Execute method
		questionsService.updateQuestion(question);
		
		// Assertions
		Mockito.verify(questionsRepository).save(any());
		
	}
	
	@Test(expected = QuestionNotFoundException.class)
	public void testUpdateQuestionNotFound() {

		// Mock
		Mockito.doReturn(Optional.empty()).when(questionsRepository).findById(any());
		
		// Execute method
		questionsService.updateQuestion(new Question());
	}

	private Pageable getDefaultPageable() {
		return PageRequest.of(0, 20, Sort.by(Direction.DESC, "createdDate"));
	}
}
