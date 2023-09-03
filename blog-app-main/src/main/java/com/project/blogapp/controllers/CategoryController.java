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
import com.project.blogapp.payloads.CategoryDto;
import com.project.blogapp.services.CategoryDao;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryDao categoryDao;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto dto =  categoryDao.createCategory(categoryDto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public List<CategoryDto> getAllCategories(){
		return categoryDao.getAllCategory();
	}
	
	@GetMapping("/{categoryId}")
	public CategoryDto getCategory(@PathVariable int categoryId) {
		return categoryDao.getCategory(categoryId);
	}
	
	@PutMapping("/{categoryId}")
	public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable int categoryId) {
		return categoryDao.updateCategory(categoryId, categoryDto);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId){
		categoryDao.deleteCategory(categoryId);
		
		return new ResponseEntity(new ApiResponse("category deleted successfully", true), HttpStatus.OK);
		
	}
}
