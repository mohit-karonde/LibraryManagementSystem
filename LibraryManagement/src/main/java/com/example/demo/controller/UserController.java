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

import com.example.demo.entity.User;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService service ;
	
	@PostMapping("/User")
	public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
		
		return service.createUser(user);
		
	}
	
	@GetMapping("/User")
	public ResponseEntity<ApiResponse<List<User>>> getAllUser() {
		return service.getAllUser();
	}

	@GetMapping("/User/{id}")
	public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
		return service.getUserById(id);
	}

	@PutMapping("/User/{id}")
	public ResponseEntity<ApiResponse<User>> updateByUserByID(@PathVariable Long id,@RequestBody  User user) {
		return service.getUserById(id);
		
	}

	@DeleteMapping("/User/{id}")
	public ResponseEntity<ApiResponse<String>> deleteById(@PathVariable Long id) {
	   return service.deleteById(id);
		
	}

}
