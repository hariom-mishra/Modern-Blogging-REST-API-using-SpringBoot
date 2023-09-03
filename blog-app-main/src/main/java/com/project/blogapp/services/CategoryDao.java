package com.project.blogapp.services;

import java.util.List;

import com.project.blogapp.payloads.CategoryDto;

public interface CategoryDao {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(int categoryId, CategoryDto categoryDto);
	void deleteCategory(int categoryId);
	CategoryDto getCategory(int categoryId);
	List<CategoryDto> getAllCategory();
}
