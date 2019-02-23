package com.linguaclassica.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {

	// Static singleton to make sure properties are read in
	private static final DatabaseProperties databaseProperties = new DatabaseProperties();
		
	// Cached DB info
	private Properties properties = new Properties();
	
	public static String getUserName() {
		return databaseProperties.properties.getProperty("user");
	}
	
	public static String getPassword() {
		return databaseProperties.properties.getProperty("password");
	}
	
	public static String getUrl() {
		return databaseProperties.properties.getProperty("url");
	}
	
	// Private constructor to support singleton
	private DatabaseProperties() {
		
		System.out.println("Bootstrapping DB properties");
		
		// Input stream for properties file
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/database.properties");
		
		// Check we found the properties file
		if (inputStream == null) {
			throw new RuntimeException("Unable to locate database properties file");
		}
		
		// Load config
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			System.err.println("Error when reading database properties: " + e.toString());
			throw new RuntimeException(e);
		}
		
		try {
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
		} catch (Exception e) {
			System.err.println("Error when reading database properties: " + e.toString());
			throw new RuntimeException(e);
		} 		
		
		// Trace info
		System.out.println("Using the following database properties: " + properties.toString());
		
	}

}
