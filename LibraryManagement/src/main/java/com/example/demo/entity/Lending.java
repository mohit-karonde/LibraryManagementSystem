package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Lending")
public class Lending {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id; // Unique identifier for the loan

	    @ManyToOne // Many loans can be associated with one book
	    @JoinColumn(name = "book_id") // Foreign key
	    private Book book;

	    @ManyToOne // Many loans can be associated with one user
	    @JoinColumn(name = "user_id") // Foreign key
	    private User user;

	    private LocalDate loanDate; // Date the book was borrowed
	    private LocalDate dueDate; // Date the book is due
	    private LocalDate returnDate; // Date the book was returned
	    private Double fineAmount = 0.0; // Fine for late returns
	    
	    
		public Lending() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Lending(Long id, Book book, User user, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate,
				Double fineAmount) {
			super();
			this.id = id;
			this.book = book;
			this.user = user;
			this.loanDate = loanDate;
			this.dueDate = dueDate;
			this.returnDate = returnDate;
			this.fineAmount = fineAmount;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Book getBook() {
			return book;
		}


		public void setBook(Book book) {
			this.book = book;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		public LocalDate getLoanDate() {
			return loanDate;
		}


		public void setLoanDate(LocalDate loanDate) {
			this.loanDate = loanDate;
		}


		public LocalDate getDueDate() {
			return dueDate;
		}


		public void setDueDate(LocalDate dueDate) {
			this.dueDate = dueDate;
		}


		public LocalDate getReturnDate() {
			return returnDate;
		}


		public void setReturnDate(LocalDate returnDate) {
			this.returnDate = returnDate;
		}


		public Double getFineAmount() {
			return fineAmount;
		}


		public void setFineAmount(Double fineAmount) {
			this.fineAmount = fineAmount;
		}


		@Override
		public String toString() {
			return "Lending [id=" + id + ", book=" + book + ", user=" + user + ", loanDate=" + loanDate + ", dueDate="
					+ dueDate + ", returnDate=" + returnDate + ", fineAmount=" + fineAmount + "]";
		}
	    
	    
		
		
		
	    
	
	

}
