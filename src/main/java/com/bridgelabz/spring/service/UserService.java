	package com.bridgelabz.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.spring.model.UserDetails;

public interface UserService {
	
	boolean register(UserDetails user, HttpServletRequest request);
	
	UserDetails login(String emailId,String password, HttpServletRequest request, HttpServletResponse response);
	
	UserDetails update(int id,UserDetails user1,HttpServletRequest request);	
	
	UserDetails delete(int id,HttpServletRequest request);
	
	List<UserDetails> retrieve(int id,HttpServletRequest request);
	
	 UserDetails activateUser(String token, HttpServletRequest request);
	
	}
