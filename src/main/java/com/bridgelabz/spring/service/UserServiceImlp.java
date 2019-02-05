package com.bridgelabz.spring.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRendererTokenTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bridgelabz.spring.dao.UserDao;
import com.bridgelabz.spring.model.Notes;
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
	
			System.out.println(token);
			String link=" Please click the link below to verify. \n\nhttp://localhost:8080/FundooNotes/activationstatus/"+token+"\n\nRegards,\nMohammed Sibgathulla.";
			user.setPassword(bcryptEncoder.encode(user.getPassword()));//encrypting the password here and storing it as a hash key 
			email.sendEmail("msibgathulla@gmail.com", "Registration Mail", link);
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
	
	@Transactional
	public List<UserDetails> retrieve(HttpServletRequest request) {
		 List<UserDetails> listOfUsers = userDao.retrieve();
	        if (!listOfUsers.isEmpty()) {
	            return listOfUsers;
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
        }
        return user;
    }
	
	public TokenGenerator tokenGenerator() {
		// TODO Auto-generated method stub
		return null;
	}
}


