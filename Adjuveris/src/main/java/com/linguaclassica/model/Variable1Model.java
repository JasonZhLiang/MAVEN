package com.linguaclassica.model;

import java.io.Serializable;

/**
 * Business layer interface for EntityVariable1Model entity.
 * @author Gene
 *
 */
public interface Variable1Model extends Serializable {
	
	/**
	 * Returns the unique ID field for this variable1 model. 
	 * @return id
	 */
	public Integer getId();

	/**
	 * Returns the exerciseid
	 * @return
	 */
	Integer getExerciseid();

	/**
	 * Sets the exerciseid
	 * class
	 * @param exerciseid
	 */
	void setExerciseid(Integer exerciseid);

	/**
	 * Returns the variable
	 * @return
	 */
	String getVariable();

	/**
	 * Sets the variable
	 * class
	 * @param variable
	 */
	void setVariable(String variable);

}


