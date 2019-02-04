package com.bridgelabz.spring.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody UserDetails user, HttpServletRequest request) {
		try {
			if (userService.register(user, request))
				return new ResponseEntity<String>("User Registered Successfully",HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<?> login(@RequestParam String emailId, HttpServletRequest request) {
		 
			UserDetails userDetails=userService.login(emailId, request);
			if(userDetails!=null) {
				return new ResponseEntity<UserDetails>(userDetails,HttpStatus.FOUND);	
			}
			else
			{
				return new ResponseEntity<String>("User not found by the given Email Id",HttpStatus.NOT_FOUND);	
			}
		
		
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	 public ResponseEntity<String> update(@RequestParam("id") int id,@RequestBody UserDetails user, HttpServletRequest request)
	{
		try {
	
		if(userService.update(id, user, request)!=null) {
			return new ResponseEntity<String>("User Successfully Updated", HttpStatus.OK);
		}
		else
		return new ResponseEntity<String>("User details incorrect, Please provide correct details",HttpStatus.CONFLICT);
	}catch (Exception e) {
	 
	}
		return new ResponseEntity<String>("User not found by the given Email Id",HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestParam("id") int id, HttpServletRequest request)
	{
		  try {
              if (userService.delete(id,request)!=null)
            	 return new ResponseEntity<String>("User Succesfully deleted",HttpStatus.FOUND);
              else
            	  return  new ResponseEntity<String>("User not Found by given  Id",HttpStatus.NOT_FOUND);
              
	}
		 
		  catch (Exception e) {
			  e.printStackTrace(); 
		  }
		  
		  return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
		  }
	

	
}



