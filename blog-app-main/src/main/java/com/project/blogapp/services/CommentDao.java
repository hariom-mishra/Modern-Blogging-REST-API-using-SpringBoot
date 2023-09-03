package com.project.blogapp.services;

import com.project.blogapp.payloads.CommentDto;

public interface CommentDao {
	CommentDto addComment(CommentDto commentDto, int postId);
	void deleteComment(int CommentId);
}
