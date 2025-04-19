package com.example.utils;

import java.util.Base64;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.example.vo.Person;

/**
 * This class HibernateUtil is used to create Hibernate SessionFactory &
 * Session.
 */

public class HibernateUtil {

	private static Logger logger = LogManager.getLogger();

	/**
	 * 
	 * @return Hibernate SessionFactory
	 */
	private static SessionFactory getSessionFactory() {
		logger.info("Initialising the session factory");
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] bytes = decoder.decode("TXlzcWw=");
		String password = new String(bytes);
		logger.info("Creating the configuration");
		Configuration configuration = new Configuration();
		logger.info("Setting the properties for session factory");
		Properties props = new Properties();
		props.put(Environment.URL, "jdbc:mysql://localhost:3306/sampledb");
		props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		props.put(Environment.USER, "root");
		props.put(Environment.PASS, password);
		props.put(Environment.SHOW_SQL, true);
		props.put(Environment.FORMAT_SQL, true);
		props.put(Environment.HBM2DDL_AUTO, "update");
		configuration.setProperties(props);
		logger.info("Add the annotated class");
		configuration.addAnnotatedClass(Person.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		logger.info("Sesssion factory initialized");
		return sessionFactory;
	}

	/**
	 * 
	 * @return Hibernate Session
	 */
	public static Session getSession() {
		logger.info("Session initialized");
		return getSessionFactory().openSession();
	}

	/*
	 * Main method to test the session creation
	 */
	
	 public static void main(String[] args) { 
	//  getSessionFactory(); 
		 getSession(); 
	 }

}