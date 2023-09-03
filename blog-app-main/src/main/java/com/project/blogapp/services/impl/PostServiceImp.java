package com.project.blogapp.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.blogapp.Model.Category;
import com.project.blogapp.Model.Post;
import com.project.blogapp.Model.User;
import com.project.blogapp.exceptionHnadler.ResourceNotFoundException;
import com.project.blogapp.payloads.PostDto;
import com.project.blogapp.payloads.PostResponse;
import com.project.blogapp.repositories.CategoryRepository;
import com.project.blogapp.repositories.PostRepo;
import com.project.blogapp.repositories.UserRepo;
import com.project.blogapp.services.PostDao;

@Service
public class PostServiceImp implements PostDao{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

		
		Post post =this.modelMapper.map(postDto, Post.class);
		post.setAddDate(new Date());
		post.setImageName("default.png");
		post.setUser(user);
		post.setCategory(category);
		PostDto dto = this.modelMapper.map(this.postRepo.save(post), PostDto.class);
		return dto;
	}

	@Override
	public PostDto editPost(int postId, PostDto postDto) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		PostDto dto = this.modelMapper.map(postRepo.save(post),PostDto.class);
		return dto;
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePosts = postRepo.findAll(p);
		
		List<PostDto> posts =pagePosts.getContent().stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(posts);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotleElement(pagePosts.getTotalElements());
		postResponse.setTotlePages(pagePosts.getTotalPages());
		postResponse.setLastpage(pagePosts.isLast());
		return postResponse;
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
		postRepo.delete(post);	
	}

	@Override
	public List<PostDto> getPostByCategory(Category category) {
//		List<PostDto> lst=postRepo.findByCategory(category).stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return null;
	}

	@Override
	public List<PostDto> getPostByUser(User user) {
//		List<PostDto> lst=postRepo.findByUser(user).stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return null;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<PostDto> posts = postRepo.searchByTitle("%"+keyword+"%").stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList()); 
		return posts;
	}
	
	

}
