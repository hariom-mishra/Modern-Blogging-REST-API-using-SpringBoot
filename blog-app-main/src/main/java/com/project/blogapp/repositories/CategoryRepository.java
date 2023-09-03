package com.project.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blogapp.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
