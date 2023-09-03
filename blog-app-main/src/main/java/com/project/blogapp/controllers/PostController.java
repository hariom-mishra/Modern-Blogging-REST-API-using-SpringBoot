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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.blogapp.payloads.ApiResponse;
import com.project.blogapp.payloads.PostDto;
import com.project.blogapp.payloads.PostResponse;
import com.project.blogapp.services.PostDao;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	PostDao postDao;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId){
		PostDto newPost = postDao.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(newPost, HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{postId}")
	PostDto getPostByPostId(@PathVariable int postId) {
		PostDto postDto = postDao.getPostById(postId);
		return postDto;
	}
	
	@GetMapping("/posts")
	ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber", defaultValue="0", required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="8", required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue="postId", required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue="asc", required=false) String sortDir

			){
		return new ResponseEntity<PostResponse>(postDao.getAllPosts(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postId}")
	ResponseEntity<ApiResponse> deletePostById(@PathVariable int postId){
		postDao.deletePost(postId);
		return new ResponseEntity<>(new ApiResponse("post deleted successfully!",true), HttpStatus.OK);
	}
	
	@PutMapping("/post/{postId}")
	ResponseEntity<PostDto> editPost(@RequestBody PostDto postDto, @PathVariable int postId){
		PostDto dto = postDao.editPost(postId, postDto);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keywords}")
	ResponseEntity<List<PostDto>> searchByPostTitle(@PathVariable String keywords){
		List<PostDto> posts = postDao.searchPost(keywords);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
}
