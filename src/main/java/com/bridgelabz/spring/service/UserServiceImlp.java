package com.bridgelabz.spring.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRendererTokenTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bridgelabz.spring.dao.UserDao;
import com.bridgelabz.spring.model.Note;
import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.utility.EmailUtil;
import com.bridgelabz.spring.utility.JwtGenerator;
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
	
	@Autowired
    private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private JwtGenerator jwtgenerator;
	
	@Transactional
	public boolean register(UserDetails user, HttpServletRequest request) {
		int id = userDao.register(user);
		if (id > 0) {
			String token = generateToken.generateToken(String.valueOf(id));
			String link=" Please click the link below to verify. \n\nhttp://localhost:8080/FundooNotes/activationstatus/"+token+"\n\nRegards,\nMohammed Sibgathulla.";
			user.setPassword(bcryptEncoder.encode(user.getPassword()));//encrypting the password here and storing it as a hash key 
			email.sendEmail("msibgathulla@gmail.com", "Registration Mail", link);
			return true;
		}
		return false;
	}
	@Transactional
	public UserDetails login(UserDetails user, HttpServletRequest request, HttpServletResponse response) {
		UserDetails existinguser=userDao.login(user.getEmailId());//pass=user entered password, actual password present in the db.
	if(bcryptEncoder.matches(user.getPassword(), existinguser.getPassword()) && existinguser.isActivationStatus()==true){
//		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		String token=generateToken.generateToken(String.valueOf(existinguser.getId()));
	      response.setHeader("Token",token);
		 return existinguser;
	}
	
		return null;
	}

	@Transactional
	public UserDetails update(String token,UserDetails user,HttpServletRequest request) {
		int id=generateToken.verifyToken(token);
		UserDetails existinguser=userDao.getUserByID(id);
		System.out.println(existinguser);
		if(existinguser!=null) {
			existinguser.setName(user.getName());
			existinguser.setEmailId(user.getEmailId());
			existinguser.setPassword(user.getPassword());
			existinguser.setMobileNumber(user.getMobileNumber());
			userDao.update(id, existinguser);
		}
		return existinguser;
	}

	@Transactional
	public UserDetails delete(String token,HttpServletRequest request) {
		int id=generateToken.verifyToken(token);
		UserDetails existinguser=userDao.getUserByID(id);
		userDao.delete(id);
		return existinguser;
	}
	
	@Transactional
	public List<UserDetails> retrieve(String token,HttpServletRequest request) {
		int id=generateToken.verifyToken(token);
		UserDetails user=userDao.getUserByID(id);
		if(user!=null) {
			int verifyId=generateToken.verifyToken(token);
		 List<UserDetails> users = userDao.retrieve(verifyId);
	        if (!users.isEmpty()) {
	            return users;
	        }
		}
	        return null;
	}
	
	@Transactional
	public UserDetails activateUser(String token, HttpServletRequest request) {
		
        int id=generateToken.verifyToken(token);
        UserDetails user=userDao.getUserByID(id);
        if(user!=null)
        {
            user.setActivationStatus(true);
            userDao.update(id, user);
            return user;
        }
		return null;
       
    }
	

}


