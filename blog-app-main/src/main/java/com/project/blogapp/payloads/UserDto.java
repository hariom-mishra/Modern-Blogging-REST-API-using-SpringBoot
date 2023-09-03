package com.project.blogapp.payloads;

import java.util.HashSet;
import java.util.Set;

import com.project.blogapp.Model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {
	public UserDto() {}
	
	private int id;
	
	@NotEmpty(message="name field can  not be null")
	@Size(min=3, message="name must be atleast 3 charecter")
	private String name;
	@Email(message="Please Enter a valid email")
	@NotEmpty(message="email field can not be null")
	private String email;
	@NotEmpty(message="password can not be empty")
	@Size(min=4, max=10, message="password must be atleast 4 and not exceeding 10 charecter")
	//@Pattern
	private String password;
	@NotEmpty(message="please write something in about")
	private String about;
	private Set<RoleDto> roles = new HashSet<>();
	public UserDto(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	public Set<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	
	
}
