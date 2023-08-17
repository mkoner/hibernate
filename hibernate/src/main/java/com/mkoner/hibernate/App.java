package com.mkoner.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mkoner.hibernate.utils.HibernateUtils;

/**
 * Hello world!
 *
 */
public class App 
{ 
    public static void main( String[] args )
    {
    	App app = new App();
    	
    	Integer user1 = app.addUser("Mamadou", "5th cross", "Bouake");
    	Integer user2 = app.addUser("Ali", "Sable", "Yopougon");
    	Integer user3 = app.addUser("Anna", "Chu Angre", "Abidjan");
    	
    	app.listUsers();
    	
    	app.updateUser(user3, "Fatima");
    	
    	app.deleteUser(user2);
    	
    	app.listUsers();
    }
    
    
    /* Method to CREATE an employee in the database */
    public Integer addUser(String username, String street, String city){
       Session session = HibernateUtils.getSessionFactory().openSession();
       Transaction tx = null;
       Integer userId = null;
       
       try {
          tx = session.beginTransaction();
          User user = new User(username, new Date());
          Address address = new Address(street, city);
          user.setAddress(address);
          userId = (Integer) session.save(user); 
          tx.commit();
       } catch (HibernateException e) {
          if (tx!=null) tx.rollback();
          e.printStackTrace(); 
       } finally {
          session.close(); 
       }
       return userId;
    }
    
    /* Method to  READ all the employees */
    public void listUsers( ){
    	Session session = HibernateUtils.getSessionFactory().openSession();
       Transaction tx = null;
       
       try {
          tx = session.beginTransaction();
          List<User> users = session.createQuery("FROM User").list(); 
          for(User user : users)
        	  System.out.println(user);
          tx.commit();
       } catch (HibernateException e) {
          if (tx!=null) tx.rollback();
          e.printStackTrace(); 
       } finally {
          session.close(); 
       }
    }
    
    /* Method to UPDATE salary for an employee */
    public void updateUser(Integer userId, String username ){
    	Session session = HibernateUtils.getSessionFactory().openSession();
       Transaction tx = null;
       
       try {
          tx = session.beginTransaction();
          User employee = (User)session.get(User.class, userId); 
          employee.setUsername(username);
 		 session.merge(employee); 
          tx.commit();
       } catch (HibernateException e) {
          if (tx!=null) tx.rollback();
          e.printStackTrace(); 
       } finally {
          session.close(); 
       }
    }
    
    /* Method to DELETE an employee from the records */
    public void deleteUser(Integer userId){
    	Session session = HibernateUtils.getSessionFactory().openSession();
       Transaction tx = null;
       
       try {
          tx = session.beginTransaction();
          User employee = (User)session.get(User.class, userId); 
          session.remove(employee); 
          tx.commit();
       } catch (HibernateException e) {
          if (tx!=null) tx.rollback();
          e.printStackTrace(); 
       } finally {
          session.close(); 
       }
    }
}
