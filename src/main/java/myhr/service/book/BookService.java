package myhr.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import myhr.data.domain.Book;
import myhr.data.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	@CachePut(value="bookCache",key="#result.id")
	public Book addBook(Book book) {
		
		return bookRepository.save(book);
	}
	
	public Page<Book> getBookByPage(Pageable pageable){
		return bookRepository.findAll(pageable);
	}
	
	@Cacheable("bookCache")
	public List<Book> getBookByAuthorLike(String author){
		return bookRepository.findByAuthorLike(author);
	}
	
	@Cacheable("bookCache")
	public Book findBookById(Integer id) {
		return bookRepository.findBookById(id);
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

	//save books list
	public int saveBooks(List<Book> books){
		books.forEach(book -> bookRepository.save(book));
		return books.size();
	}
	
	
	//delete a book by id
	@CacheEvict("bookCache")
	public void deleteById(Integer id) {
		bookRepository.deleteById(id);
	}
	
}
