package com.bridgelabz.spring.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bridgelabz.spring.dao.NoteDao;
import com.bridgelabz.spring.dao.UserDao;
import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;
import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.utility.TokenGenerator;

@Service
public class NoteServiceimp implements NoteService {
	@Autowired
	private NoteDao noteDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private TokenGenerator generateToken;

	@Transactional
	public boolean createNote(String token,Note usernote, HttpServletRequest request) {
		int noteId=generateToken.verifyToken(token);
		UserDetails user=userDao.getUserByID(noteId);
		System.out.println(user);
		if(user!=null) {
			usernote.setUser_id(user);

			int id = noteDao.createNote(usernote);
			if (id > 0) {
				//String token = generateToken.generateToken(String.valueOf(id));
				//System.out.println(token);
				return true;
			}
		}
		return false;
	}

	@Transactional
	public Note deleteNote(String token,int noteId,HttpServletRequest request) {
	
		int userId=generateToken.verifyToken(token);
		UserDetails user=userDao.getUserByID(userId);
	
		if(user!=null) {
			Note newId=noteDao.getNoteByID(noteId);
			noteDao.deleteNote(noteId);
		return newId;
		}
		
		return null;
		
	}
	
	@Transactional
	public Note updateNote(String token,int noteId,Note note,HttpServletRequest request) {
		int userId=generateToken.verifyToken(token);
		UserDetails user=userDao.getUserByID(userId);
		
		if(user!=null) {
			Note newNote=noteDao.getNoteByID(noteId);
			newNote.setTitle(note.getTitle());
			newNote.setDescription(note.getDescription());
			noteDao.updateNote(noteId, newNote);
			return note;
		}
		 
      return null;
	}
	@Transactional
	public List<Note> retrieveNote(String token,int noteId,HttpServletRequest request) {
		int userId=generateToken.verifyToken(token);
		UserDetails user=userDao.getUserByID(noteId);
		if(user!=null) {
			Note newNote=noteDao.getNoteByID(noteId);
			List<Note> notes = noteDao.retrieve(noteId,newNote);
			if (!notes.isEmpty()) {

				return notes;
			}
		}
		return null;
	}
	@Transactional
	public boolean createLabel(int labelId, Label userlabel, HttpServletRequest request) {
		UserDetails user=userDao.getUserByID(labelId);
		System.out.println(user);
		if(user!=null) {
			userlabel.setUserId(user);

			int id = noteDao.createLabel(userlabel);
			if (id > 0) {
				//String token = generateToken.generateToken(String.valueOf(id));
				//System.out.println(token);
				return true;
			}
		}
		return false;
	}
	@Transactional
	public Label deleteLabel(int labelId, HttpServletRequest request) {
		Label label=noteDao.getLabelByID(labelId);
		noteDao.deleteLabel(labelId);
		return label;
	}

	@Transactional
	public Label updateLabel(int labelId, Label userlabel, HttpServletRequest request) {
		Label existinguserlabel=noteDao.getLabelByID(labelId);
		if(existinguserlabel!=null) {
			existinguserlabel.setLabel_name(userlabel.getLabel_name());
			noteDao.updateLabel(labelId, existinguserlabel);
		}
		return existinguserlabel;
	}

	@Transactional
	public List<Label> retrieveLabel(int labelId,HttpServletRequest request) {
		UserDetails user=userDao.getUserByID(labelId);
		System.out.println(user);
		if(user!=null) {
			List<Label> label = noteDao.retrieveLabel();
			if (!label.isEmpty()) {
				System.out.println(label);
				return label;
			}
		}
		return null;
	}

	@Transactional
	public boolean addNoteLabel(String token, int noteId, int labelId, HttpServletRequest request) {

		int id=generateToken.verifyToken(token);
		UserDetails user=userDao.getUserByID(id);
		if(user!=null) {
			Note note=noteDao.getNoteByID(noteId);
			Label label=noteDao.getLabelByID(labelId);
			List<Label> listOfLabel = note.getListOfLabels();
			listOfLabel.add(label);
			  if (!listOfLabel.isEmpty()) {
	                note.setListOfLabels(listOfLabel);
	                noteDao.updateNote(1, note);
	                return true;
	            }
	        }
	        return false;
		}

	



	}




