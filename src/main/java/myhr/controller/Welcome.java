package myhr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import myhr.data.domain.Author;

@RestController		
public class Welcome {
	
	@Autowired
	Author me;

	@RequestMapping("/")
	public Author welcome() {
		/*
		 * ModelAndView mView=new ModelAndView(); mView.addObject("author", me);
		 * mView.setViewName("author");
		 */
		return me;
	}
}
