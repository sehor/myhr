package myhr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloTestController {
	
	@RequestMapping(value="/admin/hello",method=RequestMethod.GET)
	public String helloAdmin() {
		return "Hello,Admin";
	}
	
	
	@RequestMapping(value="/user/hello",method=RequestMethod.GET)
	public String helloUser() {
		return "Hello,User";
	}
	

	@RequestMapping(value="/dba/hello",method=RequestMethod.GET)
	public String helloDBAdministrater() {
		return "Hello,Database administrator!";
	}
	
}
