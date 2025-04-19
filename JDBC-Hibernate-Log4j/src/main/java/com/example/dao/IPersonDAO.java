package com.example.dao;

import java.util.List;

import com.example.vo.Person;

/**
 * This is PersonDAO class. Performs all the CURD operation demonstrating the
 * use of Hibernate 6.0
 */

public interface IPersonDAO {

	/**
	 * Insert record to Person
	 * 
	 * @param person
	 * @return
	 */
	public void save(Person person);

	/**
	 * 
	 * @return
	 */
	public List<Person> retriveAll();

	/**
	 * 
	 * @param id id of the record need to be fetched
	 * @return Person fetched from db by id
	 */
	public Person getPersonById(String id);

	/**
	 * 
	 * @param person populate the variable of person whose record you need to fetch
	 *               from db
	 * @return Single record or multiple record based of search parameters
	 */
	public List<Person> getPersonByParam(Person person);

	/**
	 * 
	 * @param person Populate the values you need to update. Id is required to
	 *               update
	 * @return updated person object for verification
	 */
	public Person update(Person person);

}
