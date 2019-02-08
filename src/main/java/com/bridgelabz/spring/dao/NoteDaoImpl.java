package com.bridgelabz.spring.dao;

import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;
import com.bridgelabz.spring.model.UserDetails;

@Repository
public class NoteDaoImpl implements NoteDao {

	@Autowired
	private SessionFactory sessionFactory;

	public int createNote(Note usernote) {

		int userId = 0;
		Session session = sessionFactory.openSession();
		userId = (Integer) session.save(usernote);
		return userId;
	}

	public void deleteNote(int noteId) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("DELETE from Note u where u.noteId= :noteId");
		query.setInteger("noteId", noteId);
		query.executeUpdate();
		tr.commit();
		session.close();
	}

	public Note getNoteByID(int noteId) {
		Session session = sessionFactory.openSession();
		String hqlQuery = "from Note where noteId = :noteId";
		Query query = session.createQuery(hqlQuery);
		query.setInteger("noteId", noteId);
		Note note = (Note) query.uniqueResult();
		if(note!=null) {
			System.out.println("Note Details are :"+note.getNoteId()+note.getTitle()+note.getDescription());
		System.out.println(note);
		}
		session.close();
		return note;
	}

	public Note updateNote(int id, Note existingusernote) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.update(existingusernote);
		tr.commit();
		session.close();
		return existingusernote;
	}

	public List<Note> retrieve(int noteId,Note newNote) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Note note where note.noteId= :noteId");
		query.setInteger("noteId", noteId);
		@SuppressWarnings("unchecked")
		List<Note> note = session.createQuery(query).list();
		return note;

	}

	public int createLabel(Label label) {
		int userId = 0;
		Session session = sessionFactory.openSession();
		userId = (Integer) session.save(label);
		session.close();
		return userId;
	}

	public Label updateLabel(int id, Label updatedlabel) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.update(updatedlabel);
		tr.commit();
		session.close();
		return updatedlabel;
	}

	public void deleteLabel(int id) {

		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("DELETE from Label u where u.labelId= :labelId");
		query.setInteger("id", id);
		query.executeUpdate();
		tr.commit();
		session.close();

	}

	public Label getLabelByID(int id) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from Label where labelId= :labelId");
		query.setInteger("id", id);
		Label emp = (Label) query.uniqueResult();
		if (emp != null) {
			System.out.println("The Label details are :" + emp.getLabel_id() + emp.getLabel_name());
			tr.commit();
			session.close();
			System.out.println(emp);
		}
		return emp;
	}

	public List<Label> retrieveLabel() {
		Session session = sessionFactory.openSession();
		String hqlQuery = "from Label";
		@SuppressWarnings("unchecked")
		List<Label> listOflabels = session.createQuery(hqlQuery).list();
		session.close();
		return listOflabels;
	}

}
