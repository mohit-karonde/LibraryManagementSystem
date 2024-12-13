package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Lending;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.LendingService;

@RestController
@RequestMapping("/api")
public class lendingController {

	
	@Autowired
	LendingService lendingservice;
	
	@PostMapping("/Lend/{bookid}/{userid}")
	ResponseEntity<ApiResponse<String>> LendBook(@PathVariable Long bookid, @PathVariable Long userid){
		
		return lendingservice.LendBook(bookid, userid);
		
		
		
	}
	
	@PutMapping("/Lend/{bookid}/{userid}")
	ResponseEntity<ApiResponse<String>> ReturnBook(@PathVariable Long bookid, @PathVariable Long userid) {
		
		return lendingservice.ReturnBook(bookid, userid);
		
	}
		
	@GetMapping("/lendBookdetail/{bookId}")
	public ResponseEntity<ApiResponse<List<Lending>>> getByBook_Id(@PathVariable Long bookId){
		return lendingservice.getByBook_Id(bookId);
		
	}
	
	
	

}
