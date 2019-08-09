package myhr.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import myhr.data.domain.Book;
import myhr.data.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {
	
	
	private  final Logger log= LoggerFactory.getLogger(BookRepositoryTest.class);

	@Autowired
	BookRepository bookRepository;

	@Test
	public void test1() {
		List<Book> books=new ArrayList<>(); 
		
		books=bookRepository.findByAuthorLike("name");
		
		books.forEach(b->log.warn(b.getName()));
	}
}
