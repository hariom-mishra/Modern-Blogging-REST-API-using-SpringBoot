package com.project.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.blogapp.payloads.ApiResponse;
import com.project.blogapp.payloads.UserDto;
import com.project.blogapp.services.UserDao;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	//Get
	@GetMapping("/")
	public List<UserDto> getAllUsers(){
		return userDao.getAllUsers();
	}
//	
	@GetMapping("/{Id}")
	public UserDto getOneUser(@PathVariable int Id){
		return userDao.getUserById(Id);
	}
	//Post
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUser = this.userDao.createUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
		
	}
	
	//put
	@PutMapping("/{userId}")
	public void updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid ) {
		userDao.updateUser(userDto, uid);
	}
//	
	//Delete
	@DeleteMapping("/{Id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int Id) {
		userDao.deleteUser(Id);
		
		return new ResponseEntity(new ApiResponse("User deleted successfully",true), HttpStatus.OK);
	}
}
