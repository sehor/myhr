package myhr.data.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

public class BookRepositoryImpl implements BookDataHelper {

	@PersistenceContext
	 EntityManager em;
	@Override
	@Transactional
	public void addBookDiscription(String discription) {
		// TODO Auto-generated method stub
		
		String updateDiscription=" update Book book set book.discription="+"'"+discription+"'";
		em.createQuery(updateDiscription).executeUpdate();
	}

}
