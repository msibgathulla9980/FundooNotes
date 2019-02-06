package com.bridgelabz.spring.dao;

import java.util.List;

import com.bridgelabz.spring.model.UserDetails;

public interface UserDao {

	int register(UserDetails user);
    Object user=null;
   
	UserDetails login(String emailId);

	UserDetails update(int id,UserDetails user1);
   
	UserDetails getUserByID(int id);
	
	List<UserDetails> getUsersList();
	
	 List<UserDetails> retrieve();
	
   void delete(int id);
}

