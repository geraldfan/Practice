package com.spring.dxc.Bookstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dxc.Bookstore.exception.ResourceNotFoundException;
import com.spring.dxc.Bookstore.model.Book;
import com.spring.dxc.Bookstore.serviceRepo.BookRepo;

/**
 * Controller class for Book entities
 * 
 * @author Gerald
 *
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	public BookRepo br;

	/**
	 * inserts a Book object into the book table
	 * 
	 * @param b a Book object
	 * @return b
	 */
	@PostMapping("/insert")
	public Book insert(@RequestBody Book b) {
		return br.save(b);
	}

	/**
	 * Finds all books in book table
	 * 
	 * @return List of all books
	 */
	@GetMapping("/fetch")
	public List<Book> findAllBooks() {
		return (List<Book>) br.findAll();
	}

	/**
	 * Finds all books by title (Exact match, case-insensitive)
	 * 
	 * @param title String containing the title of books
	 * @return List of all books matching the title
	 */
	@GetMapping("/search/title/{title}")
	public List<Book> findBooksByTitle(@PathVariable String title) {
		return (List<Book>) br.findByTitle(title);
	}

	/**
	 * Finds all books by author
	 * 
	 * @param author String containing the author of books
	 * @return List of all books matching the author
	 */
	@GetMapping("/search/author/{author}")
	public List<Book> findBooksByAuthor(@PathVariable String author) {
		return (List<Book>) br.findByAuthors(author);
	}

	/**
	 * Finds a book by isbn
	 * 
	 * @param isbn integer containing the unique isbn of a Book
	 * @return ResponseEntity A HttpResposne containing the book or a
	 *         ResourceNotFoundException if no book is found
	 */
	@GetMapping("/find/{isbn}")
	public ResponseEntity<Book> getBookByIsbn(@PathVariable int isbn) {
		Book book = br.findById(isbn)
				.orElseThrow(() -> new ResourceNotFoundException("Book does not exist with isbn :" + isbn));
		return ResponseEntity.ok(book);
	}

	/**
	 * Deletes a book from the book table
	 * 
	 * @param isbn integer containing the unique isbn of a Book
	 * @return ResponseEntity A HttpResponse containing a "deleted" string or a
	 *         ResourceNotFoundException if no book is found
	 */
	@DeleteMapping("/delete/{isbn}")
	public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable int isbn) {
		Book book = br.findById(isbn)
				.orElseThrow(() -> new ResourceNotFoundException("Book does not exist with isbn :" + isbn));
		br.delete(book);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Updates the record of a Book in the book table
	 * @param isbn integer containing the unique isbn of a Book
	 * @param b Updated Book object
	 * @return ResponseEntity A HttpResposne containing the updated book or a
	 *         ResourceNotFoundException if no book is found
	 */
	@PutMapping("/update/{isbn}")
	public ResponseEntity<Book> updateBook(@PathVariable int isbn, @RequestBody Book b) {
		Book book = br.findById(isbn)
				.orElseThrow(() -> new ResourceNotFoundException("Book does not exist with isbn :" + isbn));

		book.setTitle(b.getTitle());
		book.setAuthors(b.getAuthors());
		book.setYear(b.getYear());
		book.setPrice(b.getPrice());

		Book updatedBook = br.save(book);
		return ResponseEntity.ok(updatedBook);
	}
}
