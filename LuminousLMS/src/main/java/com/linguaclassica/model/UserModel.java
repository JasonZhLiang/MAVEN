package com.linguaclassica.model;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * @author Paul
 *
 * Business layer interface for User model.
 */
public interface UserModel extends Serializable {

	// Global constant used to key the LC application user stored in the underlying container session
	public static final String LCUSER = "lcUser";

	/**
	 * Returns the unique ID field for this user model. IDs are only set once the user is 
	 * created.
	 * @return ID
	 */
	public Integer getId();
	
	/**
	 * Sets First Name for user
	 * @param firstName
	 */
	public void setFirstName(String firstName);
	
	/**
	 * Returns the first name for the user
	 * @return
	 */
	public String getFirstName();

	/**
	 * Sets the last name for the user
	 * @param lastName
	 */
	public void setLastName(String lastName);
	
	/**
	 * Returns the last name for the user
	 * @return
	 */
	public String getLastName();

	/**
	 * Sets the email address for the user
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress);
	
	/**
	 * Returns the email address for the user
	 * @return
	 */
	public String getEmailAddress();
	
	/**
	 * Sets the password for the user
	 * @param password
	 */
	public void setPassword(String password);
	
	/**
	 * Returns the password for the user
	 * @return
	 */
	public String getPassword();

	/**
	 * Checks whether the user's password matches the parameter password
	 * @param password
	 * @return TRUE if the passwords match
	 */
	public boolean passwordMatches(String password);
	
	/**
	 * Sets the time a user account was created
	 * @return
	 */
	
	/**
	 * Sets the Security Question for the user
	 * @param password
	 */
	public void setQuestion(String question);
	
	/**
	 * Returns the Security Question for the user
	 * @return
	 */
	public String getQuestion();
	
	/**
	 * Sets the Answer Question for the user
	 * @param password
	 */
	public void setAnswer(String answer);
	
	/**
	 * Returns the Security Answer for the user
	 * @return
	 */
	public String getAnswer();

	/**
	 * Checks whether the user's password matches the parameter password
	 * @param password
	 * @return TRUE if the passwords match
	 */
	public boolean QAMatches(String answer);
	
	/**
	 * Sets the time a user account was created
	 * @return
	 */
	
	
	public void setAccountCreatedTime(Timestamp accountCreatedTime);

	/**
	 * Returns the time a account was created in the system
	 * @return
	 */
	public Timestamp getAccountCreatedTime();
	/**
	 * Sets the time a user account was closed
	 * @return
	 */
	public void setAccountclosedtime(Timestamp accountclosedtime);
	/**
	 * Returns the time a account was closed in the system
	 * @return
	 */
	public Timestamp getAccountclosedtime();	
	
	/**
	 * Sets the status for the user
	 * @param status
	 */
	public void setStatus(Integer status);
	
	/**
	 * Returns the status for the user
	 * @return
	 */
	public Integer getStatus();
	
	

}
