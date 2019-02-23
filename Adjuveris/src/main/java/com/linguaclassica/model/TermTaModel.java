package com.linguaclassica.model;

import java.io.Serializable;

/**
 * Business layer interface for TermStudentsModel corresponding 
 * to the term_students look-up table.
 * @author Gene Price
 */
public interface TermTaModel extends Serializable{
	
	/**
	 * Gets the id
	 * @return
	 */
	public Integer getId();

	/**
	 * Gets the term id
	 * @return
	 */
	public Integer getTermid();

	/**
	 * Sets the  term id
	 * @param termId
	 */
	public void setTermid(Integer termid);

	/**
	 * Gets the ta id
	 * @return
	 */
	public Integer getTaid();

	/**
	 * Sets the ta id
	 * @param taid
	 */
	public void setTaid(Integer taid);
}

