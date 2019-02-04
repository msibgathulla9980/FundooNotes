package com.bridgelabz.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bridgelabz.spring.dao.NotesDao;
import com.bridgelabz.spring.model.Notes;

@Service
public class NotesServiceimp implements NotesService {
	@Autowired
	private NotesDao notesDao;

	@SuppressWarnings("unused")
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public boolean createdNotes(Notes usernotes, HttpServletRequest request) {
		int id = notesDao.createdNotes(usernotes);
		if (id > 0) {
			//String token = generateToken.generateToken(String.valueOf(id));
			//System.out.println(token);
			return true;
		}
		return false;
	}

	@Transactional
	public Notes deleteNotes(int id,HttpServletRequest request) {

		Notes notes=notesDao.getUserByID(id);
		notesDao.deleteNotes(id);
		return notes;
	}
	@Transactional
	public Notes updateNotes(int id, Notes usernotes, HttpServletRequest request) {
		Notes usernotes1=notesDao.getUserByID(id);
		if(usernotes1!=null) {

			usernotes1.setTitle(usernotes.getTitle());
			usernotes1.setDescription(usernotes.getDescription());
			notesDao.updateNotes(id, usernotes1);
		}
		return usernotes1;

	}

	public List<Notes> retrieve(HttpServletRequest request) {
		 List<Notes> listOfNotes = notesDao.retrieve();
	        if (!listOfNotes.isEmpty()) {
	            return listOfNotes;
	        }
	        return null;
	}
	

}


