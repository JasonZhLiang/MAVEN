package com.linguaclassica.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Paul
 *
 * Business layer interface for User model.
 */
public interface UserModel extends Serializable {
	
	/**
	 * Subscription status can be ACTIVE, SUSPENDED or PENDING
	 */
	public enum SubsStatusType {
		ACTIVE, SUSPENDED, PENDING;
		
		/**
		 * Helper function to return list of possible values of this Enum as strings
		 * @return
		 */
		public List<String> getListOfValues() {
			ArrayList<String> listOfValues = new ArrayList<String>();
			
			for (SubsStatusType type : SubsStatusType.values()) {
				listOfValues.add(type.toString());
			}
			
			return listOfValues;
		}
	};

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
	 * Sets the studen number for the user
	 * @param studentnumber
	 */
	public void setStudentnumber(String studentnumber);
	
	/**
	 * Returns the studentnumber for the user
	 * @return
	 */
	public String getStudentnumber();
	
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
	
	public void setAccountCreatedTime(Timestamp accountCreatedTime);

	/**
	 * Returns the time a account was created in the system
	 * @return
	 */
	public Timestamp getAccountCreatedTime();
	
	/**
	 * Returns the SubsStatusType of subscription
	 * @return
	 */
	public SubsStatusType getStatus();
	
	
	/**
	 * Sets the Status for subscription either ACTIVE, SUSPENDED or PENDING
	 * @param type
	 */
	public void setStatus(SubsStatusType type);

}
