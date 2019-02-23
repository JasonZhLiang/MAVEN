package com.linguaclassica.model;

import java.io.Serializable;

/**
 * Business layer interface for TermStudentsModel corresponding 
 * to the term_students look-up table.
 * @author Gene Price
 */
public interface TermStudentsModel extends Serializable{
	
	/**
	 * Gets the id
	 * @return id
	 */
	public Integer getId();

	/**
	 * Gets the term id
	 * @return termid
	 */
	public Integer getTermid();

	/**
	 * Sets the  term id
	 * @param termId
	 */
	public void setTermid(Integer termid);

	/**
	 * Gets the user id
	 * @return
	 */
	public Integer getStudentid();

	/**
	 * Sets the user id
	 * @param userId
	 */
	public void setStudentid(Integer studentid);
}

