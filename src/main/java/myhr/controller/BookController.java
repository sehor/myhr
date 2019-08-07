package myhr.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import myhr.data.domain.Book;
import myhr.service.book.BookService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService bookService;
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
	
	
	@GetMapping("/hello")
	public String hello(String name) {
		
		return "Hello,"+name;
	}
	
	@PostMapping("/addBook")
	public String addBook(@RequestBody Book book) {
		
		return book.toString();
	}
	
	@GetMapping("/findAll")
	public List<Book> findAll() {
		PageRequest pageable=PageRequest.of(2, 3);
		Page<Book> page=bookService.getBookByPage(pageable);
		log.warn("总页数："+page.getTotalPages());
		log.warn("总记录数：:"+page.getTotalElements());
		log.warn("查询结果："+page.getContent());
		log.warn("当前页数："+page.getNumber()+1);
		log.warn("当前记录数："+page.getNumberOfElements());
		log.warn("每页记录数："+page.getSize());
		
		return bookService.findAllBook();
	}
	
	
	@GetMapping("/search")
	public void search() {
	List<Book> b1=bookService.getBookByAuthorLike("李");
	List<Book> b2=bookService.getBookByIdAndName(8, "西");
	List<Book> b3=bookService.getBookByPriceGreaterThan(30F);
	Book b4=bookService.getMaxIdBook();
	
    for(Book b:b1) {
    	 System.out.print("b1--> name: "+b.getName()+"  author:"+b.getAuthor()+"  price:"+b.getPrice()+"  discription:"+b.getDiscription());
    	 System.out.println();
    }
    
    b2.forEach(b->{
    	System.out.print("b2--> name: "+b.getName()+"  author:"+b.getAuthor()+"  price:"+b.getPrice()+"  discription:"+b.getDiscription());
    	System.out.println();
    	});
    
    b3.forEach(b->{
    	System.out.print("b3--> name: "+b.getName()+"  author:"+b.getAuthor()+"  price:"+b.getPrice()+"  discription:"+b.getDiscription());
    	System.out.println();
    	});
	
    System.out.println("b4--> name: "+b4.getName()+"  author:"+b4.getAuthor()+"  price:"+b4.getPrice()+"  discription:"+b4.getDiscription());
	
	}	
	
	@GetMapping("/save")
	public void save() {
		Book book=new Book();
		book.setAuthor("鲁迅");
		book.setName("呐喊");
		book.setPrice(23F);
		
		
		Book book1=new Book();
		book1.setAuthor("罗贯中");
		book1.setName("三国演义");
		book1.setPrice(36F);
		
		
		Book book2=new Book();
		book2.setAuthor("施耐庵");
		book2.setName("西游记");
		book2.setPrice(100.56F);
		
		
		Book book3=new Book();
		book3.setAuthor("吴承恩");
		book3.setName("红楼梦");
		book3.setPrice(20F);
		
		
		Book book4=new Book();
		book4.setAuthor("李白");
		book4.setName("唐诗三百首");
		book4.setPrice(65F);
		
		
		Book book5=new Book();
		book5.setAuthor("金庸");
		book5.setName("小李飞刀");
		book5.setPrice(20.33F);
		
		Book book6=new Book();
		book6.setAuthor("李商隐");
		book6.setName("黄鹤楼");
		book6.setPrice(20.33F);
		
		bookService.addBook(book);
		bookService.addBook(book1);
		bookService.addBook(book2);
		bookService.addBook(book3);
		bookService.addBook(book4);
		bookService.addBook(book5);
		bookService.addBook(book6);
			
	}
	
	@GetMapping("/updateDiscription/{discription}")
	public void updateDiscription(@PathVariable String discription) {

		bookService.updateDiscription(discription);
	}

	@PostMapping("/saveBooks")
	public String saveBooks(HttpServletRequest hsr){

		Map<String, String[]> map=hsr.getParameterMap();
		for(Map.Entry<String,String[]> entry:map.entrySet()){

			log.info(entry.getKey()+":");
			for(String s:entry.getValue()){
				log.info(s);
			}
		}

         //log.warn(hsr.getParameterMap());



		//return  "saved "+bookService.saveBooks(books)+"books";
		return  "save book done";
	}

	
}

