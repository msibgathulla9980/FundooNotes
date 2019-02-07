package com.bridgelabz.spring.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Notes;

@Repository
public class NotesDaoImpl implements NotesDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public int createdNotes(Notes usernotes) {
		
			int userId = 0;
			Session session = sessionFactory.getCurrentSession();
			userId = (Integer) session.save(usernotes);
			return userId;
		}
	
	public void deleteNotes(int id) {
		Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Query query =session.createQuery("DELETE from Notes u where u.id= :id");
        query.setInteger("id", id);
        query.executeUpdate();
        tr.commit();
        session.close();
	}
		public Notes getNotesByID(int id) {
			Session session=sessionFactory.openSession();
			Transaction tr=session.beginTransaction();
			Query query=session.createQuery("from Notes where id= :id");
			query.setInteger("id",id);
			Notes emp=(Notes) query.uniqueResult();
			if(emp!=null) {
				System.out.println("The Note's details are :"+emp.getId()+emp.getTitle()+emp.getDescription()+emp.getCreatedTime()+emp.getUpdatedTime());
			tr.commit();
			session.close();
			System.out.println(emp);
			}
			return emp;

	}
		public Notes updateNotes(int id,Notes usernotes) {
			Session session=sessionFactory.openSession();
			Transaction tr=session.beginTransaction();
			session.update(usernotes);
			tr.commit();
			session.close();
			return usernotes;
		}

		public List<Notes> retrieve(){
			Session session = sessionFactory.openSession();
			String hqlQuery = "from Notes";
			@SuppressWarnings("unchecked")
			List<Notes> listOfNotes = session.createQuery(hqlQuery).list();
			return listOfNotes;


		}

		public int createLabel(Label label) {
			int userId = 0;
			Session session = sessionFactory.openSession();
			userId = (Integer) session.save(label);
			session.close();
			return userId;
		}

		public Label updateLabel(int id, Label label1) {
			Session session=sessionFactory.openSession();
			Transaction tr=session.beginTransaction();
			session.update(label1);
			tr.commit();
			session.close();
			return label1;
		}

		public void deleteLabel(int id) {
			
			Session session = sessionFactory.openSession();
	        Transaction tr = session.beginTransaction();
	        Query query =session.createQuery("DELETE from Label u where u.id= :id");
	        query.setInteger("id", id);
	        query.executeUpdate();
	        tr.commit();
	        session.close();
			
		}

		public Label getLabelByID(int id) {
			Session session=sessionFactory.openSession();
			Transaction tr=session.beginTransaction();
			Query query=session.createQuery("from Label where id= :id");
			query.setInteger("id",id);
			Label emp=(Label) query.uniqueResult();
			if(emp!=null) {
				System.out.println("The Label details are :"+emp.getLabel_id()+emp.getLabel_name());
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
			return listOflabels;
		}	
		
}

	


