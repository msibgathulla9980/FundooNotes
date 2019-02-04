	package com.bridgelabz.spring.service;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.spring.model.UserDetails;

public interface UserService {
	
	boolean register(UserDetails user, HttpServletRequest request);
	UserDetails login(String emailId, HttpServletRequest request);
	UserDetails update(int id,UserDetails user1,HttpServletRequest request);	
	UserDetails delete(int id,HttpServletRequest request);
	
	}

