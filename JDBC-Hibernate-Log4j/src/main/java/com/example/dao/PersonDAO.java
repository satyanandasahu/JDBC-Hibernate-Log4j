package com.example.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.example.utils.HibernateUtil;
import com.example.vo.Person;

public class PersonDAO implements IPersonDAO {

	private static Logger logger = LogManager.getLogger();

	private Session getSession() {
		logger.info("Getting Session");
		Session session = HibernateUtil.getSession();
		logger.info("Got Session Object");
		return session;
	}

	public void save(Person person) {
		logger.info("Entered save");
		Session session = getSession();
		session.beginTransaction();
		session.persist(person);
		session.getTransaction().commit();
		logger.info("Entered save");
	}

	public List<Person> retriveAll() {
		logger.info("Entered retrieveAll");
		List<Person> list = getSession().createQuery("from Person", Person.class).list();
		logger.info("Exit retrieveAll");
		return list;
	}

	public Person getPersonById(String id) {
		logger.info("Entered getPersonById");
		Person person = null;
		person = getSession().get(Person.class, id);
		logger.info("Person " + person);
		logger.info("Exited getPersonById");
		return person;
	}

	public List<Person> getPersonByParam(Person person) {
		logger.info("Entered getPersonByParam");
		List<Person> personObject = null;
		personObject = getSession().createQuery("from Person where gender = :gen and age=:age", Person.class)
				.setParameter("gen", person.getGender()).setParameter("age", person.getAge()).list();
		logger.info("Exit getPersonByParam: " + personObject);
		return personObject;
	}

	public Person update(Person person) {
		logger.info("Entered update");
		Session session = getSession();
		Person personObject = null;
		Person returnedObject = null;
		session.beginTransaction();
		personObject = session.get(Person.class, person.getId());
		logger.debug("Fetched Person from database/Object before update " + personObject);
		personObject
				.setFirstName(person.getFirstName() != null && !person.getFirstName().isEmpty() ? person.getFirstName()
						: personObject.getFirstName());
		personObject.setLastName(person.getLastName() != null && !person.getLastName().isEmpty() ? person.getLastName()
				: personObject.getLastName());
		personObject.setMiddleName(
				person.getMiddleName() != null && !person.getMiddleName().isEmpty() ? person.getMiddleName()
						: personObject.getMiddleName());
		personObject.setAge(person.getAge() != 0 ? person.getAge() : personObject.getAge());
		personObject.setGender(person.getGender() != null && !person.getGender().isEmpty() ? person.getGender()
				: personObject.getGender());
		logger.info("Updating Person " + personObject);
		returnedObject = session.merge(personObject);
		session.getTransaction().commit();
		if (returnedObject.getId().equals(personObject.getId()))
			logger.info("Record updated: " + returnedObject);
		else
			logger.info("Record update failed");
		logger.info("Exit update");
		return returnedObject;
	}

	public void delete(Person person) {
		logger.info("Entered delete");
		Session session = getSession();
		session.beginTransaction();
		session.remove(person);
		session.getTransaction().commit();
		logger.info("deleted person id: " + person.getId());
	}

}
