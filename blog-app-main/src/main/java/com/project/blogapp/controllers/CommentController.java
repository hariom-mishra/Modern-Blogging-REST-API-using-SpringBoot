package com.project.blogapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.blogapp.payloads.ApiResponse;
import com.project.blogapp.payloads.CommentDto;
import com.project.blogapp.services.CommentDao;
 
@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	CommentDao commentDao;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createPost(@RequestBody CommentDto commentDto, @PathVariable int postId){
		CommentDto dto =commentDao.addComment(commentDto, postId);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId){
		commentDao.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully", true), HttpStatus.OK);

	}
}
