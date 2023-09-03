package com.project.blogapp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blogapp.Model.Comment;
import com.project.blogapp.Model.Post;
import com.project.blogapp.exceptionHnadler.ResourceNotFoundException;
import com.project.blogapp.payloads.CommentDto;
import com.project.blogapp.repositories.CommentRepo;
import com.project.blogapp.repositories.PostRepo;
import com.project.blogapp.services.CommentDao;

@Service
public class CommentServiceImpl implements CommentDao{
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	PostRepo postRepo;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CommentDto addComment(CommentDto commentDto, int postId) {
		Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		CommentDto savedComment = modelMapper.map(commentRepo.save(comment), CommentDto.class);
		return savedComment;
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
		commentRepo.delete(comment);
	}
	
}
