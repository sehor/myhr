package myhr.controller;

import org.springframework.stereotype.Component;

@Component
public class TestAop {

	public void testAspect() throws Exception {
		
		System.out.println("testAspect method running..");
		throw new Exception("bad thing happend!");
	}
}
