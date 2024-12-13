package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.payload.ApiResponse;

@Service

public interface UserService {

	
	   ResponseEntity<ApiResponse<User>> createUser(User user);
		
	   
	   ResponseEntity<ApiResponse<List<User>>> getAllUser();
	   
	   
	   ResponseEntity<ApiResponse<User>> getUserById(Long id);
	   
	   ResponseEntity<ApiResponse<User>> updateByUserByID(Long id , User user);
	   
	   ResponseEntity<ApiResponse<String>> deleteById(Long id);
	   
	   ResponseEntity<ApiResponse<List<User>>> findByUser(String name);
	   
}
