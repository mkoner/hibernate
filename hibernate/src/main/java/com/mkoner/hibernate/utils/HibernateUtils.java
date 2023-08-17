package com.mkoner.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mkoner.hibernate.User;

public class HibernateUtils {
	private static SessionFactory factory;
	public static SessionFactory getSessionFactory() {
		if(factory == null) {
			factory = new Configuration().configure()
					.addAnnotatedClass(User.class)
					.buildSessionFactory();
		}
		return factory;
	}
}
