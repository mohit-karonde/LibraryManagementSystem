package com.example.demo.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Book;
import com.example.demo.payload.ApiResponse;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;



@Service
public class BookServiceImplement implements BookService {
	
	
	@Autowired
	BookRepository repository;

	@Override
	public ResponseEntity<ApiResponse<Book>> createBook(@RequestBody  Book book) {
		
	
		
		Book newBook = new Book();
		
		newBook.setBookName(book.getBookName());
		newBook.setAuthor(book.getAuthor());
		newBook.setDescription(book.getDescription());
		newBook.setIsAvailable(book.getIsAvailable());
		newBook.setAuthor(book.getBookName());
		newBook.setStock(book.getStock());
		Book savedBook = repository.save(newBook);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("ok","success","Employee Created", savedBook));
	}
	

	@Override
	public ResponseEntity<ApiResponse<List<Book>>> getAllBook() {
		// TODO Auto-generated method stub
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("ok","Succes","List of Employee is fetched", repository.findAll()));
		
	}

	@Override
	public ResponseEntity<ApiResponse<Book>> getBookById(Long id) {
		// TODO Auto-generated method stub
		return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("ok","success","Book with "+id+" found",
				repository.findById(id).get()));
	}

	@Override
	public ResponseEntity<ApiResponse<Book>> updateByBookByID(Long id, Book book) {
	    Book existingBook = repository.findById(id).get();
	     
	    existingBook.setBookName(book.getBookName());
	    existingBook.setAuthor(book.getAuthor());
	    existingBook.setIsAvailable(book.getIsAvailable());
	    existingBook.setDescription(book.getDescription());
	    
	    
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("ok","success","Book with id "+id+" is updated",repository.save(existingBook)));
	}

	@Override
	public ResponseEntity<ApiResponse<String>> deleteById(Long id) {
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("deleted","success","book with "+id+" is deleted succefully",null));
	}

	@Override
	public ResponseEntity<ApiResponse<List<Book>>> findByName(String name) {
		// TODO Auto-generated method stub
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("ok","success",
				"book with name "+name+"name",repository.findByBookName(name)));
	}

	@Override
	public ResponseEntity<ApiResponse<List<Book>>> getAllAvailablebookfrolending() {
		// TODO Auto-generated method stub
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("ok","success","all the available are follow",repository.findByIsAvailable(true)));
	}

}
