package com.project.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blogapp.Model.Category;
import com.project.blogapp.exceptionHnadler.ResourceNotFoundException;
import com.project.blogapp.payloads.CategoryDto;
import com.project.blogapp.repositories.CategoryRepository;
import com.project.blogapp.services.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryDao{
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.dtoToCategory(categoryDto);
		CategoryDto dto = categoryToDto(this.categoryRepo.save(category));
		return dto;
	}

	@Override
	public CategoryDto updateCategory(int categoryId, CategoryDto categoryDto) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		CategoryDto dto = this.categoryToDto(categoryRepo.save(category));
		return dto;
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(int categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		CategoryDto dto = this.categoryToDto(category);
		return dto;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<CategoryDto> lst = categoryRepo.findAll().stream().map(user->categoryToDto(user)).collect(Collectors.toList());
		return lst;
	}

	private Category dtoToCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		
		return category;
	}
	
	private CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}
}
