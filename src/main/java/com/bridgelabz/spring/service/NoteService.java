package com.bridgelabz.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

public interface NoteService {

	boolean createNote(String token,Note usernote, HttpServletRequest request);
	Note deleteNote(String token,int noteId,HttpServletRequest request);
	Note updateNote(String token,int noteId,Note note,HttpServletRequest request);	
	
	List<Note> retrieveNote(String token,int noteId,HttpServletRequest request);
	
	boolean createLabel(int labelId, Label userlabel, HttpServletRequest request);
	Label deleteLabel(int labelId,HttpServletRequest request);
	Label updateLabel(int labelId,Label userlabel,HttpServletRequest request);	
	List<Label> retrieveLabel(int labelId,HttpServletRequest request);
	 boolean addNoteLabel(String token,int noteId, int labelId,HttpServletRequest request);
}
