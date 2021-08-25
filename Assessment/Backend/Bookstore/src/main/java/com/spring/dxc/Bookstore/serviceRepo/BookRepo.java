package com.spring.dxc.Bookstore.serviceRepo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.dxc.Bookstore.model.Book;

/**
 * Interface extending the CrudRepository for CRUD operations regarding Book
 * objects
 * 
 * @author Gerald
 *
 */
public interface BookRepo extends CrudRepository<Book, Integer> {
	List<Book> findByTitle(String title);

	List<Book> findByAuthors(String authors);
}
