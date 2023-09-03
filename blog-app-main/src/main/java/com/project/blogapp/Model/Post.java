package com.project.blogapp.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {
	public Post() {}
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int postId;
	@Column(name="post_content", length=10000, nullable=false)
	private String content;
	@Column(name="post_title", length=100, nullable=false)
	private String title;
	private String imageName;
	@Column(name="add_date",nullable=false)
	private Date addDate;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	public Post(int postId, String content, String title, String imageName, Date addDate) {
		super();
		this.postId = postId;
		this.content = content;
		this.title = title;
		this.imageName = imageName;
		this.addDate = addDate;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", content=" + content + ", title=" + title + ", imageName=" + imageName
				+ ", addDate=" + addDate + "]";
	}
	
	
}
