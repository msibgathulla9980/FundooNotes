package com.bridgelabz.spring.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bridgelabz.spring.dao.NotesDao;
import com.bridgelabz.spring.dao.UserDao;
import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Notes;
import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.utility.TokenGenerator;

@Service
public class NotesServiceimp implements NotesService {
	@Autowired
	private NotesDao notesDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private TokenGenerator generateToken;

	@Transactional
	public boolean createdNotes(int user_id,Notes usernotes, HttpServletRequest request) {
		UserDetails user=userDao.getUserByID(user_id);
		System.out.println(user);
		if(user!=null) {
			usernotes.setUser_id(user);

			int id = notesDao.createdNotes(usernotes);
			if (id > 0) {
				//String token = generateToken.generateToken(String.valueOf(id));
				//System.out.println(token);
				return true;
			}
		}
		return false;
	}

	@Transactional
	public Notes deleteNotes(int id,HttpServletRequest request) {

		Notes notes=notesDao.getNotesByID(id);
		notesDao.deleteNotes(id);
		return notes;
	}
	@Transactional
	public Notes updateNotes(int id, Notes usernotes, HttpServletRequest request) {
		Notes usernotes1=notesDao.getNotesByID(id);
		if(usernotes1!=null) {

			usernotes1.setTitle(usernotes.getTitle());
			usernotes1.setDescription(usernotes.getDescription());
			notesDao.updateNotes(id, usernotes1);
		}
		return usernotes1;

	}
	@Transactional
	public List<Notes> retrieve(int id,HttpServletRequest request) {
		UserDetails user=userDao.getUserByID(id);
		if(user!=null) {
			List<Notes> listOfNotes = notesDao.retrieve();
			if (!listOfNotes.isEmpty()) {

				return listOfNotes;
			}
		}
		return null;
	}
	@Transactional
	public boolean createLabel(int user_id, Label userlabel, HttpServletRequest request) {
		UserDetails user=userDao.getUserByID(user_id);
		System.out.println(user);
		if(user!=null) {
			userlabel.setUserId(user);

			int id = notesDao.createLabel(userlabel);
			if (id > 0) {
				//String token = generateToken.generateToken(String.valueOf(id));
				//System.out.println(token);
				return true;
			}
		}
		return false;
	}
	@Transactional
	public Label deleteLabel(int id, HttpServletRequest request) {
		Label label=notesDao.getLabelByID(id);
		notesDao.deleteLabel(id);
		return label;
	}

	@Transactional
	public Label updateLabel(int id, Label userlabel, HttpServletRequest request) {
		Label userlabel1=notesDao.getLabelByID(id);
		if(userlabel1!=null) {


			userlabel1.setLabel_name(userlabel.getLabel_name());
			notesDao.updateLabel(id, userlabel1);
		}
		return userlabel1;
	}

	@Transactional
	public List<Label> retrieveLabel(int id,HttpServletRequest request) {
		UserDetails user=userDao.getUserByID(id);
		System.out.println(user);
		if(user!=null) {
			List<Label> listOflabel = notesDao.retrieveLabel();
			if (!listOflabel.isEmpty()) {
				System.out.println(listOflabel);
				return listOflabel;
			}
		}
		return null;
	}

	@Transactional
	public boolean addNoteLabel(String token, int noteId, int labelId, HttpServletRequest request) {

		int id=generateToken.verifyToken(token);
		UserDetails user=userDao.getUserByID(id);
		if(user!=null) {
			Notes notes=notesDao.getNotesByID(id);
			Label label=notesDao.getLabelByID(id);
			notes.setListOfLabels(notes.getListOfLabels());
			List<Label> listOfLabel = notes.getListOfLabels();
			listOfLabel.add(label);
			  if (!listOfLabel.isEmpty()) {
	                notes.setListOfLabels(listOfLabel);
	                notesDao.updateNotes(1, notes);
	                return true;
	            }
	        }
	        return false;
		}
	}




