package com.spring.dxc.Bookstore.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * Book entity class
 * @author Gerald
 *
 */
@Entity
@Table(name = "book")
public class Book {
	@Id
	private int isbn;

	@Column(name = "title", length = 100, nullable = false)
	private String title;
	
	@ElementCollection 
    @CollectionTable(name = "authors", joinColumns = @JoinColumn(name = "isbn"))
    @Column(name = "authors")
	private List<String> authors;
	private int year;
	private int price;

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
