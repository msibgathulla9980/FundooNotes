package com.bridgelabz.spring.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody UserDetails user, HttpServletRequest request, HttpServletResponse response) {
		
			UserDetails userDetails=userService.login(user, request,response);
			if(userDetails!=null) {
				return new ResponseEntity<String>("User has been activated and found successfully",HttpStatus.FOUND);	
			
		 }
		 
		 
			 return new ResponseEntity<String>("User not found by the given Email Id",HttpStatus.NOT_FOUND);	
		 
		
			
	
		
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	 public ResponseEntity<String> update(@RequestHeader("token") String token, @RequestBody UserDetails existinguser, HttpServletRequest request)
	{
//		try {
	
		if(userService.update(token, existinguser, request)!=null) {
			return new ResponseEntity<String>("User Successfully Updated", HttpStatus.OK);
		}
		else
			
		return new ResponseEntity<String>("User details incorrect, Please provide correct details",HttpStatus.CONFLICT);
//	}
//		catch (Exception e) {
//	 
//	}
//		return new ResponseEntity<String>("User not found by the given Email Id",HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestHeader("token") String token, HttpServletRequest request)
	{
		  try {
              if (userService.delete(token,request)!=null)
            	 return new ResponseEntity<String>("User Succesfully deleted",HttpStatus.FOUND);
              else
            	  return  new ResponseEntity<String>("User not Found by given  Id",HttpStatus.NOT_FOUND);
              
	}
		 
		  catch (Exception e) {
			  e.printStackTrace(); 
		  }
		  
		  return new ResponseEntity<String>("pls provide details correctly",HttpStatus.CONFLICT);
		  }
	

	
	@RequestMapping(value = "/retrieveUsers", method = RequestMethod.GET)
	public ResponseEntity<?> retrieve( @RequestHeader("token") String token,HttpServletRequest request)
	{
		List<UserDetails> users=userService.retrieve(token,request);
		if(!users.isEmpty()) {
			 return new ResponseEntity<List<UserDetails>>(users, HttpStatus.FOUND);
		}
		else 
		{
			return new ResponseEntity<String>("The Entered Note is Incorrect. Please enter valid note present in the database",
                    HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/activationstatus/{token:.+}", method = RequestMethod.GET)
    public ResponseEntity<?> activateUser(@PathVariable("token") String token, HttpServletRequest request) {

        UserDetails user = userService.activateUser(token, request);
        if (user != null) {
            return new ResponseEntity<UserDetails>(user, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
                    HttpStatus.NOT_FOUND);
        }
    }	
}



