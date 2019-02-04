package com.bridgelabz.spring.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.spring.model.Notes;
import com.bridgelabz.spring.service.NotesService;

@RestController
public class NotesController {
	
	@Autowired
	private NotesService notesService;

	@RequestMapping(value = "/registerNotes", method = RequestMethod.POST)
	public ResponseEntity<String> createNotes(@RequestBody Notes usernotes, HttpServletRequest request) {
		try {
			if (notesService.createdNotes(usernotes, request))
				return new ResponseEntity<String>("Notes Created Successfully",HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/deleteNotes", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteNotes(@RequestParam("id") int id, HttpServletRequest request)
	{
		  try {
              if (notesService.deleteNotes(id,request)!=null)
            	 return new ResponseEntity<String>("Notes Succesfully deleted",HttpStatus.FOUND);
              else
            	  return  new ResponseEntity<String>("notes are not found by the given Id",HttpStatus.NOT_FOUND);
              
	}
		  catch (Exception e) {
			  e.printStackTrace(); 
		  }
		  
		  return new ResponseEntity<String>("Please provide the id correctly",HttpStatus.CONFLICT);
		  }
	
	@RequestMapping(value = "/updateNotes", method = RequestMethod.POST)
	 public ResponseEntity<String> updateNotes(@RequestParam("id") int id,@RequestBody Notes usernotes, HttpServletRequest request)
	{
		try {
	
		if(notesService.updateNotes(id, usernotes, request)!=null) {
			return new ResponseEntity<String>("Notes Successfully Updated", HttpStatus.OK);
		}
		else
		return new ResponseEntity<String>("Notes details are incorrect, Please provide the correct details",HttpStatus.CONFLICT);
	}catch (Exception e) {
	 e.printStackTrace();
	}
		return new ResponseEntity<String>("Notes are not found by the given Id",HttpStatus.NOT_FOUND);
	}
		
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public ResponseEntity<?> retrieve( HttpServletRequest request)
	{
		List<Notes> listOfNotes=notesService.retrieve(request);
		if(!listOfNotes.isEmpty()) {
			 return new ResponseEntity<List<Notes>>(listOfNotes, HttpStatus.FOUND);
		}
		else 
		{
			return new ResponseEntity<String>("The Entered Note is Incorrect. Please enter valid note present in the database",
                    HttpStatus.NOT_FOUND);
		}

		
	}
	
}
