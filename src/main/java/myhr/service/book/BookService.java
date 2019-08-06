package myhr.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import myhr.data.domain.Book;
import myhr.data.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	public void addBook(Book book) {
		
		bookRepository.save(book);
	}
	
	public Page<Book> getBookByPage(Pageable pageable){
		return bookRepository.findAll(pageable);
	}
	
	public List<Book> getBookByAuthorLike(String author){
		String sLike="%"+author+"%";
		return bookRepository.findByAuthorLike(sLike);
	}
	
	public List<Book> getBookByPriceGreaterThan(float price){
		return bookRepository.findByPriceGreaterThan(price);
	}
	
	public Book getMaxIdBook() {
		return bookRepository.getMaxIdBook();
	}
	
	public List<Book> getBookByIdAndAuthor(Integer id,String author){
		return bookRepository.getBookByIdAndAuthor(id, author);
	}
	
	public List<Book> getBookByIdAndName(Integer id,String name){
		return bookRepository.getBookByIdAndName(id, name);
	}
	
	public void updateDiscription(String discription) {
		
		bookRepository.addBookDiscription(discription);
	}
	
	
	public List<Book> findAllBook(){
		
		return bookRepository.findAll();
	}
	
}
