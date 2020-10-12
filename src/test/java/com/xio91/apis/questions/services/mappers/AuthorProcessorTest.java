package com.xio91.apis.questions.services.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.hateoas.Link;

import com.xio91.apis.questions.controllers.model.Author;

import uk.co.jemos.podam.api.PodamFactoryImpl;

public class AuthorProcessorTest {

	private AuthorProcessor authorProcessor = new AuthorProcessor();
	
	private static PodamFactoryImpl podamFactory;

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
	public void testProcess() {
		
		// Prepare POJOs
		Author author = podamFactory.manufacturePojo(Author.class);
		
		// Execute test
		Author processedAuthor = authorProcessor.process(author);

		// Assertions
		List<Link> result = processedAuthor.getLinks("questions");
		
		assertEquals(1, result.size());
		assertEquals("/questions?author="+author.getName(), result.get(0).getHref());
		assertEquals("questions", result.get(0).getRel().toString());
	}

	@Test
	public void testProcessNull() {
		Author processedAuthor = authorProcessor.process(null);
		
		assertNull(processedAuthor);
	}
}
