package com.project.blogapp.payloads;

import java.util.List;

public class PostResponse {
	public PostResponse() {}
	
	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private int totlePages;
	private long totleElement;
	public long getTotleElement() {
		return totleElement;
	}
	public void setTotleElement(long totleElement) {
		this.totleElement = totleElement;
	}

	private boolean lastpage;
	public List<PostDto> getContent() {
		return content;
	}
	public void setContent(List<PostDto> content) {
		this.content = content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotlePages() {
		return totlePages;
	}
	public void setTotlePages(int totlePages) {
		this.totlePages = totlePages;
	}
	public boolean isLastpage() {
		return lastpage;
	}
	public void setLastpage(boolean lastpage) {
		this.lastpage = lastpage;
	}
	
	
}
