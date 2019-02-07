package com.bridgelabz.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Notes;

public interface NotesService {

	boolean createdNotes(int user_id,Notes usernotes, HttpServletRequest request);
	Notes deleteNotes(int id,HttpServletRequest request);
	Notes updateNotes(int id,Notes usernotes1,HttpServletRequest request);	
	List<Notes> retrieve(int id,HttpServletRequest request);
	
	boolean createLabel(int user_id, Label userlabel, HttpServletRequest request);
	Label deleteLabel(int id,HttpServletRequest request);
	Label updateLabel(int id,Label userlabel1,HttpServletRequest request);	
	List<Label> retrieveLabel(int id,HttpServletRequest request);
	 boolean addNoteLabel(String token,int noteId, int labelId,HttpServletRequest request);
}
