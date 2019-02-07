package com.bridgelabz.spring.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Notes;
import com.bridgelabz.spring.service.NotesService;

@RestController
public class NotesController {
	
	@Autowired
	private NotesService notesService;

	@RequestMapping(value = "/createNotes", method = RequestMethod.POST)
	public ResponseEntity<String> createNotes(@RequestParam("id") int user_id, @RequestHeader("token")String token,@RequestBody Notes usernotes, HttpServletRequest request) {
		try {
			if (notesService.createdNotes(user_id,usernotes, request))
				return new ResponseEntity<String>("Notes Created Successfully",HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(HttpStatus.CONFLICT);
	}

	public NotesService getNotesService() {
		return notesService;
	}

	public void setNotesService(NotesService notesService) {
		this.notesService = notesService;
	}

	@RequestMapping(value = "/deleteNotes", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteNotes(@RequestParam("id") int id,@RequestHeader("token")String token, HttpServletRequest request)
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
	 public ResponseEntity<String> updateNotes(@RequestParam("id") int id, @RequestHeader("token")String token, @RequestBody Notes usernotes, HttpServletRequest request)
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
		
	@RequestMapping(value = "/retrieveNotes", method = RequestMethod.GET)
	public ResponseEntity<?> retrieve( @RequestParam("id") int id,@RequestHeader("token")String token, HttpServletRequest request)
	{
		List<Notes> listOfNotes=notesService.retrieve(id,request);
		if(!listOfNotes.isEmpty()) {
			 return new ResponseEntity<List<Notes>>(listOfNotes, HttpStatus.FOUND);
		}
		else 
		{
			return new ResponseEntity<String>("The Entered Note is Incorrect. Please enter valid note present in the database",
                    HttpStatus.NOT_FOUND);
		}

		
	}
	
	@RequestMapping(value = "/createLabel", method = RequestMethod.POST)
	public ResponseEntity<String> createLabel(@RequestParam("user_id") int user_id,@RequestHeader("token")String token,@RequestBody Label label, HttpServletRequest request) {
		try {
			if (notesService.createLabel(user_id,label, request))
				return new ResponseEntity<String>("Label Created Successfully",HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = "/deleteLabel", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteLabel(@RequestParam("label_id") int id,@RequestHeader("token")String token, HttpServletRequest request)
	{
		  try {
              if (notesService.deleteLabel(id,request)!=null)
            	 return new ResponseEntity<String>("Label Succesfully deleted",HttpStatus.FOUND);
              else
            	  return  new ResponseEntity<String>("label was not found by the given Id",HttpStatus.NOT_FOUND);
              
	}
		  catch (Exception e) {
			  e.printStackTrace(); 
		  }
		  
		  return new ResponseEntity<String>("Please provide the id correctly",HttpStatus.CONFLICT);
		  }
	
	@RequestMapping(value = "/updateLabel", method = RequestMethod.POST)
	 public ResponseEntity<String> updateLabel(@RequestParam("label_id") int id,@RequestHeader("token")String token,@RequestBody Label label, HttpServletRequest request)
	{
		try {
	
		if(notesService.updateLabel(id, label, request)!=null) {
			return new ResponseEntity<String>("Label Successfully Updated", HttpStatus.OK);
		}
		else
		return new ResponseEntity<String>("Label details are incorrect, Please provide the correct details",HttpStatus.CONFLICT);
	}catch (Exception e) {
	 e.printStackTrace();
	}
		return new ResponseEntity<String>("Label was not found by the given Id",HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/retrieveLabel", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveLabel(@RequestParam("label_id") int id,@RequestHeader("token")String token, HttpServletRequest request)
	{
		List<Label> listOflabel=notesService.retrieveLabel(id,request);
		if(!listOflabel.isEmpty()) {
			 System.out.println(listOflabel);
			 return new ResponseEntity<List<Label>>(listOflabel, HttpStatus.FOUND);
			
		}
		else 
		{
			return new ResponseEntity<String>("The Entered label is Incorrect. Please enter valid label present in the database",
                    HttpStatus.NOT_FOUND);
		}

		
	}
	
	@RequestMapping(value = "/addnotelabel", method = RequestMethod.PUT)
    public ResponseEntity<?> addNoteLabel(@RequestHeader("token") String token,@RequestParam("noteId") int noteId,@RequestParam("labelId") int labelId,HttpServletRequest request)
	{
		
			if (notesService.addNoteLabel(token,noteId, labelId, request)) {
            return new ResponseEntity<String>("Note Id and Label Id have been successfully mapped", HttpStatus.FOUND);
			}
		
		else {
            return new ResponseEntity<String>("User id given is not present or Note is not activated", HttpStatus.NOT_FOUND);
        }
		

	}

	
	}
	

