package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Book;
import com.example.demo.payload.ApiResponse;

public interface BookService {
	
   ResponseEntity<ApiResponse<Book>> createBook(Book book);
	
   
   ResponseEntity<ApiResponse<List<Book>>> getAllBook();
   
   
   ResponseEntity<ApiResponse<Book>> getBookById(Long id);
   
   ResponseEntity<ApiResponse<Book>> updateByBookByID(Long id , Book book);
   
   ResponseEntity<ApiResponse<String>> deleteById(Long id);
   
   ResponseEntity<ApiResponse<List<Book>>> findByName(String name);
   
   ResponseEntity<ApiResponse<List<Book>>> getAllAvailablebookfrolending();
   
   
   
   
   
   
   
   
	
	

}
