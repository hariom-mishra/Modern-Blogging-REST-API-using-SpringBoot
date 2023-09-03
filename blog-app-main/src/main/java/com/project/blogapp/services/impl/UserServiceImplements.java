package com.project.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.blogapp.Model.Role;
import com.project.blogapp.Model.User;
import com.project.blogapp.configurations.Constants;
import com.project.blogapp.exceptionHnadler.ResourceNotFoundException;
import com.project.blogapp.payloads.UserDto;
import com.project.blogapp.repositories.RoleRepo;
import com.project.blogapp.repositories.UserRepo;
import com.project.blogapp.services.UserDao;

@Service
public class UserServiceImplements implements UserDao{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser= this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		User user= userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","id",userId));
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(int userId) {
		User user= userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user"," id ",userId));
		UserDto userDto = userToDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> list = userRepo.findAll();
		List<UserDto> listDto = list.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return listDto;
	}

	//admin
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public void deleteUser(int userId) {
		User user= userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","id",userId));
		userRepo.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		
		// roles
		Role role = this.roleRepo.findById(Constants.NORMAL_USER).get();
		
		
		
		user.getRoles().add(role);

		User newUser = this.userRepo.save(user);

		return this.modelMapper.map(newUser, UserDto.class);
	}

}
