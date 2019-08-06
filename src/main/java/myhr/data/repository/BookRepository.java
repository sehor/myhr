package myhr.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import myhr.data.domain.Book;

@Repository
@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Integer>,BookDataHelper {

	List<Book> findByAuthorLike(String author);
	
	List<Book> findByPriceGreaterThan(float minPrice);
	
	@Query(value="select * from Book where id=(select max(id) from book)",nativeQuery=true)
	Book getMaxIdBook();
	
	@Query("select b from Book b where b.id>:id and b.author=:author")
	List<Book> getBookByIdAndAuthor(@Param("id") Integer id,@Param("author") String author);
	
	@Query("select b from Book b where b.id<?1 and b.name like %?2%")
	List<Book> getBookByIdAndName(Integer id,String bookName);
}
