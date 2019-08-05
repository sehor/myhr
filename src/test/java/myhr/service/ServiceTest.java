package myhr.service;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
	
	@Autowired
	HelloService hService;
	@Test
	public void test() {
		
		String hello=hService.hello("PZR!");
		
		org.junit.Assert.assertThat(hello, Matchers.is("Hello,PZR!"));
	}

}
