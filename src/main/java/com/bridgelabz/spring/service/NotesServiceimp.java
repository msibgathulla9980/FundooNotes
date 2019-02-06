package com.bridgelabz.spring.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bridgelabz.spring.dao.NotesDao;
import com.bridgelabz.spring.dao.UserDao;
import com.bridgelabz.spring.model.Notes;
import com.bridgelabz.spring.model.UserDetails;

@Service
public class NotesServiceimp implements NotesService {
	@Autowired
	private NotesDao notesDao;
	
	@Autowired
	private UserDao userDao;
	
	@SuppressWarnings("unused")
	@Autowired
	private SessionFactory sessionFactory;

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


