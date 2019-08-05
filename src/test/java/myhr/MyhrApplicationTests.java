package myhr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import myhr.controller.TestAop;
import myhr.data.domain.Author;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyhrApplicationTests {

	private final Logger logger=LogManager.getLogger();
	
	@Autowired
	Author me;
	@Autowired
	TestAop ta;
	@Test
	public void contextLoads() throws Exception {
		
		/*
		 * for (String favorite : me.getFavorites()) { log.info(favorite); }
		 */
		logger.info("i am l4j2");
		ta.testAspect();
		
	}

}
