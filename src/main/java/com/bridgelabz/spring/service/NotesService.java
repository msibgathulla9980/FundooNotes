package com.bridgelabz.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.spring.model.Notes;

public interface NotesService {

	boolean createdNotes(int user_id,Notes usernotes, HttpServletRequest request);
	Notes deleteNotes(int id,HttpServletRequest request);
	Notes updateNotes(int id,Notes usernotes1,HttpServletRequest request);	
	List<Notes> retrieve(HttpServletRequest request);
}
