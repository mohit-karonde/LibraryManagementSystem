package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("api")
public class Controller {
	
	@Autowired
	BookService service;
	
	@PostMapping("/Book")
	public ResponseEntity<ApiResponse<Book>> createBook(@RequestBody  Book book) {
		
		return service.createBook(book);
	    	
	}
	
	@GetMapping("/Book")
	public ResponseEntity<ApiResponse<List<Book>>> getAllBook() {
		
		return service.getAllBook();
	}
	
	

	@GetMapping("/Book/{id}")
	public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long id) {
		
		return service.getBookById(id);
	}
	
	@PutMapping("/Book/{id}")
	public ResponseEntity<ApiResponse<Book>> updateByBookByID(@PathVariable  Long id,@RequestBody Book book) {
		
		return service.updateByBookByID(id, book);
	}
	
	@DeleteMapping("/Book/{id}")
	public ResponseEntity<ApiResponse<String>> deleteById(@PathVariable Long id){
		
		return service.deleteById(id);
	}

	@GetMapping("/Book/{name}")
	public ResponseEntity<ApiResponse<List<Book>>> findByName(@PathVariable String name){
		
		return service.findByName(name);
		
	}
	
	@GetMapping("/BookAvailable")
	ResponseEntity<ApiResponse<List<Book>>> getAllAvailablebookfrolending(){
		return service.getAllAvailablebookfrolending();
	}
}
