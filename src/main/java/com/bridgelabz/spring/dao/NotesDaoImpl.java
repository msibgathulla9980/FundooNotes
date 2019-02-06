package com.bridgelabz.spring.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
		public Notes getUserByID(int id) {
			Session session=sessionFactory.openSession();
			Transaction tr=session.beginTransaction();
			Query query=session.createQuery("from Notes where id= :id");
			query.setInteger("id",id);
			Notes emp=(Notes) query.uniqueResult();
			if(usernotes!=null) {
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
}

	


