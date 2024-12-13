package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String bookName;
	
	private String description;
	
	private Boolean isAvailable;
	
	private String  author;
	
	private Long stock;
	
	 @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<Lending> lendings;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Long id, String bookName, String description, Boolean isAvailable, String author , Long stock) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.description = description;
		this.isAvailable = isAvailable;
		this.author = author;
		this.stock = stock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", description=" + description + ", isAvailable=" 
	            + isAvailable + ", author=" + author + ", stock=" + stock + "]";
	}

	
	
	
	
	
	
	
}
