package com.bridgelabz.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bridgelabz.spring.model.UserDetails;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public int register(UserDetails user) {
		int userId = 0;
		Session session = sessionFactory.getCurrentSession();
		userId = (Integer) session.save(user);
		return userId;
	}

	// public UserDetails login(UserDetails user) {
	// Session session = sessionFactory.getCurrentSession();
	// String hqlQuery = "from UserDetails where userName = :userName and password
	// =:password";
	// Query query = session.createQuery(hqlQuery);
	// query.setParameter("userName", user.getEmailId());
	// query.setParameter("password", user.getPassword());
	// UserDetails existingUser = (UserDetails) query.uniqueResult();
	// return existingUser;
	// }

	public UserDetails login(String emailId) {
		// Opening the connection using the JDBC Connection.
		Session session = sessionFactory.openSession();
		// Beginning a unit of work and returning the associated Transaction object.
			Transaction tr=session.beginTransaction();
		Query query = session.createQuery("from UserDetails where emailId= :emailId");
		query.setString("emailId", emailId);
		UserDetails emp = (UserDetails) query.uniqueResult();
		if (emp != null) {
			System.out.println("User details are=" + emp.getName() + "," + emp.getEmailId() + "," + emp.getMobileNumber());
			tr.commit();
			session.close();
			return emp;
		} else {
			return null;
		}

	}
	
	public UserDetails update(int id,UserDetails user) {
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.update(user);
		tr.commit();
		session.close();
		return user;
	}
	
	public UserDetails getUserByID(int id) {
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		Query query=session.createQuery("from UserDetails where id= :id");
		query.setInteger("id",id);
		UserDetails emp=(UserDetails) query.uniqueResult();
		if(user!=null) {
			System.out.println("User Details are :"+emp.getId()+emp.getName()+emp.getEmailId());
		tr.commit();
		System.out.println(emp);
		}
		session.close();
		return emp;
		
	}

	public void delete(int id) {
		Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Query query =session.createQuery("DELETE from UserDetails u where u.id= :id");
        query.setInteger("id", id);
        query.executeUpdate();
        tr.commit();
        session.close();
	}
	
	public List<UserDetails> getUsersList() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<UserDetails> retrieve(){
		Session session = sessionFactory.openSession();
		String hqlQuery = "from UserDetails	";
		@SuppressWarnings("unchecked")
		List<UserDetails> listOfUsers = session.createQuery(hqlQuery).list();
		return listOfUsers;


}
}