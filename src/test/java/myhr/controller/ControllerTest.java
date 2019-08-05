package myhr.controller;

import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import myhr.data.domain.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

	private final Logger log = org.apache.logging.log4j.LogManager.getLogger(ControllerTest.class);
	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void test1() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/book/hello")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("name", "PZR"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

		log.warn(result.getResponse().getContentAsString());
	}

	@Test
	public void test2() throws Exception  {
		
		Book book=new Book();
		book.setId(100);
		book.setName("红楼梦");
		book.setAuthor("罗贯中");
		
		ObjectMapper mapper=new ObjectMapper();
		
		String sbook=mapper.writeValueAsString(book);
		MvcResult result=mockMvc.
				perform(MockMvcRequestBuilders.
						post("/book/addBook")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(sbook)
						)
				 .andExpect(MockMvcResultMatchers.status().isOk())
				 .andDo(MockMvcResultHandlers.print())
				.andReturn();
		
		log.warn(result.getResponse().getContentAsString());
			
	}
}
