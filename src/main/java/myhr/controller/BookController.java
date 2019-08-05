package myhr.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
	private final Log log=LogFactory.getLog(BookController.class);
	//@RequestMapping(value = "/",method =RequestMethod.POST)
	//@CrossOrigin(value="http://localhost:8082",maxAge = 180,allowedHeaders = "*")
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String addBook(String name) {
		//log.info("方法开始执行>>>");
		return "receive:"+name;
	}
	
	//@DeleteMapping("/{id}")
	//@CrossOrigin(value="http://localhost:8081",maxAge = 180,allowedHeaders = "*")
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public String deleteBookById(@PathVariable Long id) {
		
		return "delete book of which  id:"+String.valueOf(id);
	}
}

