package com.bridgelabz.spring.service;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRendererTokenTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.spring.dao.UserDao;
import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.utility.EmailUtil;
import com.bridgelabz.spring.utility.TokenGenerator;



@SuppressWarnings("unused")
@Service
public class UserServiceImlp implements UserService  {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private TokenGenerator generateToken;
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmailUtil email;
	

	@Transactional
	public boolean register(UserDetails user, HttpServletRequest request) {
		int id = userDao.register(user);
		if (id > 0) {
			String token = generateToken.generateToken(String.valueOf(id));
			System.out.println(token);
			email.sendEmail("tharungandhi636@gmail.com", "First Mail", "Hello");
			return true;
		}
		return false;
	}
	@Transactional
	public UserDetails login(String emailId, HttpServletRequest request) {
		return userDao.login(emailId);
	}

	@Transactional
	public UserDetails update(int id,UserDetails user,HttpServletRequest request) {
		UserDetails user1=userDao.getUserByID(id);
		if(user1!=null) {
			user1.setName(user.getName());
			user1.setEmailId(user.getEmailId());
			user1.setPassword(user.getPassword());
			user1.setMobileNumber(user.getMobileNumber());
			userDao.update(id, user1);
		}
		return user1;
	}

	@Transactional
	public UserDetails delete(int id,HttpServletRequest request) {
		UserDetails user2=userDao.getUserByID(id);
		userDao.delete(id);
		return user2;
	}
	public TokenGenerator tokenGenerator() {
		// TODO Auto-generated method stub
		return null;
	}
}


