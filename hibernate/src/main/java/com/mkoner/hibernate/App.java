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
    	
    	Integer user1 = app.addUser("Mamadou", "5th cross", "Bouake", "Dell", "Latitude");
    	Integer user2 = app.addUser("Ali", "Sable", "Yopougon", "HP", "HP15");
    	Integer user3 = app.addUser("Anna", "Chu Angre", "Abidjan", "Lenovo", "Thinkbook");
    	
    	app.listUsers();
    	
    	//app.updateUser(3, "Fatima");
    	
    	//app.deleteUser(2);
    	
    	app.listUsers();
    }
    
    
    /* Method to CREATE an employee in the database */
    public Integer addUser(String username, String street, String city, String lbrand, String lmodel){
       Session session = HibernateUtils.getSessionFactory().openSession();
       Transaction tx = null;
       Integer userId = null;
       
       try {
          tx = session.beginTransaction();
          User user = new User(username, new Date());
          Address address = new Address(street, city);
          Laptop laptop = new Laptop(lbrand, lmodel);
          user.setAddress(address);
          user.setLaptop(laptop);
          laptop.setUser(user);
          session.save(user); 
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
          {
        	  System.out.println(user);
        	  System.out.println(user.getLaptop());
          }
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
