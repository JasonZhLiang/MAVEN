package com.linguaclassica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;

public class SQLConnectionFactory {

	public SQLConnectionFactory() {
		
	}
	
	/**
	 * Method to get a connection from the DriverManager
	 * 
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getConnection() throws SQLException {
		try {			
			return DriverManager.getConnection (DatabaseProperties.getUrl(),
												DatabaseProperties.getUserName(),
												DatabaseProperties.getPassword());
		}
		catch (SQLException e)
		{
			System.err.println ("Cannot connect to database server " + e.getMessage());
			throw e;
		} 
	}

	/**
	 * This method is used to map an error code from an SQL Exception to a named exception from
	 * this package
	 * 
	 * @param SQLException e
	 * @return A named runtime exception
	 * @throws  
	 */
	public void mapSqlErrorToModelException(SQLException e) throws EntityAlreadyExistsException, 
																   ServiceException {
		
		String sqlState = e.getSQLState();
		
		/*
		 * The SQL states we are interested in are:
		 * 
		 * 23000 - Integrity constraint violation
		 * 
		 */
		
		if (sqlState.equals("23000")) {throw new EntityAlreadyExistsException();}
		
		// Did not identify a specific error
		System.err.println("Unhandled SQL State: " + sqlState + ", error code " + e.getErrorCode());
		e.printStackTrace();
		throw new ServiceException(sqlState);
	}
	
}
