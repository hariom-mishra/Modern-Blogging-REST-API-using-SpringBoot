package com.project.blogapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blogapp.Model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	//geting user by name
	Optional<User> findByEmail(String email);
}
