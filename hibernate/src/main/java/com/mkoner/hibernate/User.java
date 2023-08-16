package com.mkoner.hibernate;

import java.util.Date;

import jakarta.persistence.Embedded;

public class User {
	private int userId;
	private String username;
	private Date createdDate;
	@Embedded
	private Address address;
	
	public User() {
		super();
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User(int userId, String username, Date createdDate) {
		super();
		this.userId = userId;
		this.username = username;
		this.createdDate = createdDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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
