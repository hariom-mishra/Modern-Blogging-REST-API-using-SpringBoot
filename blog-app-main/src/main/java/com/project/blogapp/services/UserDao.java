package com.project.blogapp.services;

import java.util.List;

import com.project.blogapp.Model.User;
import com.project.blogapp.payloads.UserDto;

public interface UserDao {
	UserDto registerNewUser(UserDto userDto);
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto, int userId);
	UserDto getUserById(int userId);
	List<UserDto> getAllUsers();
	void deleteUser(int userId);
}
