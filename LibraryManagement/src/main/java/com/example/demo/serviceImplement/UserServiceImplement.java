package com.example.demo.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.payload.ApiResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;


@Service
public class UserServiceImplement implements UserService {
	
	@Autowired
	UserRepository repository;

	@Override
	public ResponseEntity<ApiResponse<User>> createUser(User user) {
		User newuser = new User();
		
		newuser.setFirstName(user.getFirstName());
		newuser.setEmail(user.getEmail());
		newuser.setLastName(user.getLastName());
		newuser.setPhoneNumber(user.getPhoneNumber());
		
		User savedUser = repository.save(newuser);
				
		
		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Ok","success","new employee is Created",savedUser));
	}

	@Override
	public ResponseEntity<ApiResponse<List<User>>> getAllUser() {
		// TODO Auto-generated method stub
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Ok","success","get all employee",repository.findAll()));
	
	}

	@Override
	public ResponseEntity<ApiResponse<User>> getUserById(Long id) {
		// TODO Auto-generated method stub
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Ok","success","new employee is Created",repository.findById(id).get()));
		
	}

	@Override
	public ResponseEntity<ApiResponse<User>> updateByUserByID(Long id, User user) {
		User existinguser = repository.findById(id).get();
		
		existinguser.setEmail(user.getEmail());
		existinguser.setFirstName(user.getFirstName());
		existinguser.setLastName(user.getLastName());
		existinguser.setPhoneNumber(user.getPhoneNumber());
		
		User UpdatedUser = repository.save(existinguser);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Ok","success","new employee is Created",UpdatedUser ));
		
		
		
	}

	@Override
	public ResponseEntity<ApiResponse<String>> deleteById(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Ok","success","Employee deleted succesfull",null ));
		
	}

	@Override
	public ResponseEntity<ApiResponse<List<User>>> findByUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
