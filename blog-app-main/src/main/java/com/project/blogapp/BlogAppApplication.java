package com.project.blogapp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.blogapp.Model.Role;
import com.project.blogapp.configurations.Constants;
import com.project.blogapp.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(passwordEncoder.encode("12345"));
		
		try {
			Role role = new Role();
			role.setId(Constants.ADMIN_USER);
			role.setName("ADMIN_USER");
			
			Role role1 = new Role();
			role1.setId(Constants.NORMAL_USER);
			role1.setName("NORMAL_USER");
			
			List<Role> roles =List.of(role, role1);
			
			this.roleRepo.saveAll(roles);
		}catch(Exception e){
			
		}
	}
	
	

}
