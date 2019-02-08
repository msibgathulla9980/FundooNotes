	package com.bridgelabz.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.spring.model.UserDetails;

public interface UserService {
	
	boolean register(UserDetails user, HttpServletRequest request);
	
	UserDetails login(UserDetails user, HttpServletRequest request, HttpServletResponse response);
	
	UserDetails update(String token,UserDetails existinguser,HttpServletRequest request);	
	
	UserDetails delete(String token,HttpServletRequest request);
	
	List<UserDetails> retrieve(String token,HttpServletRequest request);
	
	 UserDetails activateUser(String token, HttpServletRequest request);
	
	}
