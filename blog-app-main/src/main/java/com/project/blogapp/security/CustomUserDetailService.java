package com.project.blogapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.blogapp.Model.User;
import com.project.blogapp.exceptionHnadler.ResourceNotFoundException;
import com.project.blogapp.repositories.UserRepo;

//@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email"+username,0));
		
		return user;
	}

}
