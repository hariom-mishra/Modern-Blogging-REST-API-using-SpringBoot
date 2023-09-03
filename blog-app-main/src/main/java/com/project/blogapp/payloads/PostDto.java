package com.project.blogapp.payloads;

import java.util.Date;
import java.util.Set;

import com.project.blogapp.Model.Comment;

public class PostDto {
	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date addDate;
	private UserDto userDto;
	private Set<CommentDto> comments;
	private CategoryDto categoryDto;
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUser(UserDto userDto) {
		this.userDto = userDto;
	}
	public CategoryDto getCategory() {
		return categoryDto;
	}
	public void setCategory(CategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}
	public Set<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	
	
}
