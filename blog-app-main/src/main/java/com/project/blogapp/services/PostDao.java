package com.project.blogapp.services;

import java.util.List;

import com.project.blogapp.Model.Category;
import com.project.blogapp.Model.User;
import com.project.blogapp.payloads.PostDto;
import com.project.blogapp.payloads.PostResponse;

public interface PostDao {
	public PostDto createPost(PostDto postDto,int userId, int CategoryId);
	public PostDto editPost(int postId, PostDto postDto);
	public PostDto getPostById(int postId);
	public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir);
	public void deletePost(int postId);
	public List<PostDto> getPostByCategory(Category category);
	public List<PostDto> getPostByUser(User user);
	public List<PostDto> searchPost(String keyword);
}
