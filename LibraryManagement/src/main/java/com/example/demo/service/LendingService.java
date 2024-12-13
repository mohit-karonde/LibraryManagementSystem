package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Lending;
import com.example.demo.payload.ApiResponse;

@Service
public interface LendingService {

	
	ResponseEntity<ApiResponse<String>> LendBook(Long  bookId , Long userId);
	
	ResponseEntity<ApiResponse<String>> ReturnBook(Long  bookId , Long userId);
	
//	ResponseEntity<ApiResponse<Lending>> LendingBook(Long UserId);
	
	
	ResponseEntity<ApiResponse<List<Lending>>> getByBook_Id(Long bookId);
	
	
	
}
