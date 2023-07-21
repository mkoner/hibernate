package com.mkoner.hibernate;

import java.util.Date;

public class User {
	private Long userId;
	private String username;
	private Date createdDate;
	
	public User() {
		super();
	}
	
	public User(Long userId, String username, Date createdDate) {
		super();
		this.userId = userId;
		this.username = username;
		this.createdDate = createdDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "User {userId:" + userId + ", username:" + username + ", createdDate:" + createdDate + "}";
	}
	
	
	
}
