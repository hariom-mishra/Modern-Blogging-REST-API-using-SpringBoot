package com.project.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blogapp.Model.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{

}
