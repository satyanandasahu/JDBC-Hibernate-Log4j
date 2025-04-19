package com.example.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.vo.Person;

/**
 * 
 * 
 * @version 1.0
 * @author Satyananda Sahu (Satya)
 * 
 *         This class shows CURD operation using JDBC call.
 * 
 */

public class MainJdbc {

	private static Logger logger = LogManager.getLogger();

	/**
	 * 
	 * @return SQL Connection Object
	 */
	private static Connection getconnection() {
		logger.info("Initializing the connection");
		Connection connection = null;
		try {

			Base64.Decoder decoder = Base64.getDecoder();
			byte[] bytes = decoder.decode("TXlzcWw=");
			String password = new String(bytes);
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "root", password);
			logger.info("Connection established.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.debug("ClassNotFoundException: ", e);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("SQLException: ", e);
		}
		return connection;
	}

	
	/**
	 *  Method used to insert  record on database table 
	 *  
	 *  
	 * @param keyValuePair is a Map<String, String> Key is the table column name and
	 *                     Value is the Value for that column
	 * @return Integer: Insertion status 0 failed, 1 success
	 */
	public int save(Map<String, String> keyValuePair) {
		logger.info("Save record method call");
		int updateValue = 0;
		try {
			Connection connection = getconnection();
			String sql = "INSERT INTO Person (Id, FirstName, LastName, MiddleName, Age, gender) VALUES (?, ?, ?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "17447869089");
			preparedStatement.setString(2, "ABCD");
			preparedStatement.setString(3, "WXYZ");
			preparedStatement.setString(4, "PQRS");
			preparedStatement.setInt(5, 30);
			preparedStatement.setString(6, "Female");
			updateValue = preparedStatement.executeUpdate();
			if (updateValue == 1)
				logger.info("Record successfully inserted");
			else
				logger.info("Insert Failed");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("SQLException: ",e);
		}
		logger.info("Exit");
		return updateValue;
	}

	/**
	 * Method used to update record on database table 
	 * 
	 * @param keyValuePair is a Map<String, String> Key is the table column name and
	 *                     Value is the Value for that column
	 * @return Integer: Insertion status 0 failed, 1 success
	 */
	public int update(Map<String, String> keyValuePair) {
		logger.info("Update record method call");
		int updateValue = 0;
		Connection connection = getconnection();
		String sql = "UPDATE Person SET age = ? WHERE id = ?";
		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "32");
			preparedStatement.setString(2, "17447869089");
			updateValue = preparedStatement.executeUpdate();
			if (updateValue == 1)
				logger.info("Record updated.");
			else
				logger.info("Record update failed");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("SQLException: ",e);
		}
		logger.info("Update record method call");
		return updateValue;
	}

	
	/**
	 * 
	 * @param id Id of the record to be deleted
	 * @return Integer: Insertion status 0 failed, 1 success
	 */
	public int delete(String id) {
		logger.info("Update record method call");
		int updateValue = 0;
		try {
			Connection connection = getconnection();
			String sql = "DELETE FROM Person WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			updateValue = preparedStatement.executeUpdate();
			if (updateValue == 1)
				logger.info("Record deleted.");
			else
				logger.info("Record delete failed");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("SQLException: ",e);
		}
		logger.info("Update record method call");
		return updateValue;
	}

	/**
	 * 
	 * @param id Person Id
	 * @return Person Object fetched from db by id
	 */
	public Person getPersonById(String id) {
		logger.info("Inside the medtod");
		Person person = null;
		try {
			Connection connection = getconnection();
			String sql = "Select * from Person where Id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				person = new Person();
				person.setId(resultSet.getString("Id"));
				person.setFirstName(resultSet.getString("FirstName"));
				person.setLastName(resultSet.getString("LastName"));
				person.setMiddleName(resultSet.getString("MiddleName"));
				person.setAge(resultSet.getInt("Age"));
				person.setGender(resultSet.getString("Gender"));
				logger.info("Person: " + person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("SQLException: ",e);
		}
		logger.info("Exit from PersonByID");
		return person;
	}

	
	/**
	 * Java Main method to test the application
	 * 
	 */
	public static void main(String[] args) {
		logger.info("Main");
		MainJdbc jdbc = new MainJdbc();
		jdbc.getPersonById("17447869089");
		logger.info("Exit from Main");
	}

}
