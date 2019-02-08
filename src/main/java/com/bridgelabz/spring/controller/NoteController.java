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

import com.bridgelabz.spring.dao.NoteDao;
import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;
import com.bridgelabz.spring.service.NoteService;

@RestController
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	@RequestMapping(value = "/createNote", method = RequestMethod.POST)
	public ResponseEntity<String> createNotes( @RequestHeader("token")String token,@RequestBody Note usernote, HttpServletRequest request) {
		try {
			if (noteService.createNote(token,usernote, request))
				return new ResponseEntity<String>("Notes Created Successfully",HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(HttpStatus.CONFLICT);
	}

	public NoteService getNotesService() {
		return noteService;
	}

	public void setNotesService(NoteService notesService) {
		this.noteService = notesService;
	}
	

	@RequestMapping(value = "/deleteNote", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteNote(@RequestHeader("token")String token,@RequestParam("noteId") int noteId, HttpServletRequest request)
	{
		  try {
              if (noteService.deleteNote(token,noteId,request)!=null)
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
	 public ResponseEntity<String> updateNotes(@RequestHeader("token")String token,@RequestParam("noteId") int noteId, @RequestBody Note note, HttpServletRequest request)
	{ 
		try {
	
		if(noteService.updateNote(token,noteId, note, request)!=null) {
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
	public ResponseEntity<?> retrieve(@RequestHeader("token")String token, @RequestParam("noteId") int noteId, HttpServletRequest request)
	{
		List<Note> listOfNotes=noteService.retrieveNote(token,noteId,request);
		if(!listOfNotes.isEmpty()) {
			 return new ResponseEntity<List<Note>>(listOfNotes, HttpStatus.FOUND);
		}
		else 
		{
			return new ResponseEntity<String>("The Entered Note is Incorrect. Please enter valid note present in the database",
                    HttpStatus.NOT_FOUND);
		}

		
	}
	
	@RequestMapping(value = "/createLabel", method = RequestMethod.POST)
	public ResponseEntity<String> createLabel(@RequestParam("labelId") int labelId,@RequestHeader("token")String token,@RequestBody Label label, HttpServletRequest request) {
		try {
			if (noteService.createLabel(labelId,label, request))
				return new ResponseEntity<String>("Label Created Successfully",HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = "/deleteLabel", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteLabel(@RequestParam("labelId") int labelId,@RequestHeader("token")String token, HttpServletRequest request)
	{
		  try {
              if (noteService.deleteLabel(labelId,request)!=null)
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
	 public ResponseEntity<String> updateLabel(@RequestParam("labelId") int labelId,@RequestHeader("token")String token,@RequestBody Label label, HttpServletRequest request)
	{
		try {
	
		if(noteService.updateLabel(labelId, label, request)!=null) {
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
	public ResponseEntity<?> retrieveLabel(@RequestParam("labelId") int labelId,@RequestHeader("token")String token, HttpServletRequest request)
	{
		List<Label> label=noteService.retrieveLabel(labelId,request);
		if(!label.isEmpty()) {
			 System.out.println(label);
			 return new ResponseEntity<List<Label>>(label, HttpStatus.FOUND);
			
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
		
			if (noteService.addNoteLabel(token, noteId, labelId, request)) {
            return new ResponseEntity<String>("Note Id and Label Id have been successfully mapped", HttpStatus.FOUND);
			}
		
		else {
            return new ResponseEntity<String>("User id given is not present or Note is not activated", HttpStatus.NOT_FOUND);
        }
		

	}
//
//	@RequestMapping(value = "/removenotelabel", method = RequestMethod.DELETE)
//	 public ResponseEntity<?> removeNoteLabel(@RequestHeader("token") String token,@RequestParam("noteId") int noteId,@RequestParam("labelId") int labelId,HttpServletRequest request)
//	{
//		if(notes)
//		return null;
//		
//	}
	
	}
	

