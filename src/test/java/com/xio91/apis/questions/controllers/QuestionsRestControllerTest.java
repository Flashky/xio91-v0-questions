package com.xio91.apis.questions.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.xio91.apis.questions.controllers.exceptions.QuestionsExceptionHandler;
import com.xio91.apis.questions.controllers.model.Question;
import com.xio91.apis.questions.services.QuestionsService;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@WebMvcTest(QuestionsRestController.class)
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public class QuestionsRestControllerTest {

	private static MockMvc mockMvc;
	private static PodamFactory podamFactory;
	private static ObjectWriter objectWriter;
	
	@InjectMocks
	private static QuestionsRestController questionsController = new QuestionsRestController();	
	
	@Mock
	private QuestionsService questionsService;



	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    
		// MockMvc configuration
		mockMvc = MockMvcBuilders.standaloneSetup(questionsController)
	            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
	            .setControllerAdvice(new QuestionsExceptionHandler())
	            .build();
		
		// Podam (POJOs) configuration
	    podamFactory = new PodamFactoryImpl();
	    podamFactory.getStrategy().setDefaultNumberOfCollectionElements(2);
	    
	    // Jackson configuration
	    ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = mapper.writer().withDefaultPrettyPrinter();
	}
	
	@Before
	public void setUp() throws Exception {
		

	}

	@Test
	public void testListQuestions200() throws Exception {

		// Prepare POJOs
		List<Question> questions = podamFactory.manufacturePojo(ArrayList.class, Question.class);
		PagedModel<Question> expectedResult = PagedModel.of(questions, (PageMetadata) null);
		
		// Mock
		Mockito.doReturn(expectedResult).when(questionsService).listQuestions(any(), any());
		
		// Perform request
		MockHttpServletRequestBuilder request = get("/questions")
												.accept(MediaType.APPLICATION_JSON)
												.contentType(MediaType.APPLICATION_JSON);
				
		mockMvc.perform(request).andExpect(status().isOk());

	}

	@Test
	public void testListQuestions204() throws Exception {

		// Prepare POJOs
		PagedModel<Question> expectedResult = PagedModel.empty();
		
		// Mock
		Mockito.doReturn(expectedResult).when(questionsService).listQuestions(any(), any());
		
		// Perform request
		MockHttpServletRequestBuilder request = get("/questions")
												.accept(MediaType.APPLICATION_JSON)
												.contentType(MediaType.APPLICATION_JSON);
				
		mockMvc.perform(request).andExpect(status().isNoContent());

	}

	
	@Test
	public void testGetQuestion200() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		Optional<Question> expectedResult = Optional.of(question);
		
		// Mock
		Mockito.doReturn(expectedResult).when(questionsService).getQuestion(any());
		
		// Perform request
		MockHttpServletRequestBuilder request = get("/questions/1")
												.accept(MediaType.APPLICATION_JSON)
												.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isOk());
	}
	
	@Test
	public void testGetQuestion204() throws Exception {
		
		// Prepare POJOs
		Optional<Question> expectedResult = Optional.empty();
		
		// Mock
		Mockito.doReturn(expectedResult).when(questionsService).getQuestion(any());
		
		// Perform request
		MockHttpServletRequestBuilder request = get("/questions/1")
												.accept(MediaType.APPLICATION_JSON)
												.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isNoContent());
	}

	@Test
	public void testCreateQuestion201() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		 
		// Mock
		Mockito.doReturn(question).when(questionsService).createQuestion(question);
		
		// Perform request
		executePost(question).andExpect(status().isCreated())
								.andExpect(header().exists(HttpHeaders.LOCATION));
	}

	@Test
	public void testCreateQuestion400NullText() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setText(null);

		// Perform request
		executePost(question).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCreateQuestion400EmptyText() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setText(StringUtils.EMPTY);

		// Perform request
		executePost(question).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCreateQuestion400NullAuthor() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setAuthor(null);

		// Perform request
		executePost(question).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCreateQuestion400NullAuthorName() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		question.getAuthor().setName(null);
		
		// Perform request
		executePost(question).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCreateQuestion400EmptyAuthorName() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		question.getAuthor().setName(StringUtils.EMPTY);

		// Perform request
		executePost(question).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCreateQuestion400InvalidStatus() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setStatus("invalid-status");

		// Perform request
		executePost(question).andExpect(status().isBadRequest());
	}
	
	/**
	 * Auxiliar method to perform a post to the questions endpoint with an input body.
	 * <p>
	 * This method seeks to avoid duplication of boilerplate code on each test to the same post endpoint.
	 * </p>
	 * @param question
	 * @return
	 * @throws Exception
	 */
	private ResultActions executePost(Question question) throws Exception {
		
		String questionBody = objectWriter.writeValueAsString(question);
		
		return mockMvc.perform(post("/questions")
				.contentType(MediaType.APPLICATION_JSON)
				.content(questionBody));
	}
	
	@Test
	public void testUpdateQuestion200() throws Exception {
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
				 
		// Mock
		// No need to mock anything in this case.
				
		// Perform request
		executePut(question).andExpect(status().isOk());
	}

	private ResultActions executePut(Question question) throws Exception {
		
		String questionBody = objectWriter.writeValueAsString(question);
		
		return mockMvc.perform(put("/questions/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(questionBody));
	}

	@Test
	public void testUpdateQuestion400NullText() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setText(null);

		// Perform request
		executePut(question).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testUpdateQuestion400EmptyText() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setText(StringUtils.EMPTY);

		// Perform request
		executePut(question).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testUpdateQuestion400NullAuthor() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		question.setAuthor(null);

		// Perform request
		executePut(question).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testUpdateQuestion400NullAuthorName() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		question.getAuthor().setName(null);
		
		// Perform request
		executePut(question).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testUpdateQuestion400EmptyAuthorName() throws Exception {
		
		// Prepare POJOs
		Question question = podamFactory.manufacturePojo(Question.class);
		question.getAuthor().setName(StringUtils.EMPTY);

		// Perform request
		executePut(question).andExpect(status().isBadRequest());
	}
}
