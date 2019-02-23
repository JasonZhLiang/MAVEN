package com.linguaclassica.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Business layer interface for EntityExerciseModel entity.
 */
public interface ExerciseModel extends Serializable {
	
	/**
	 * Returns the unique ID field for this permission model. 
	 * @return id
	 */
	public Integer getId();

	/**
	 * Returns the createdbyid
	 * @return createdbyid
	 */
	Integer getCreatedById();

	/**
	 * Sets the createdbyid
	 * @param createdbyid
	 */
	void setCreatedById(Integer createdbyid);

	/**
	 * Returns the exercisecategory
	 * @return exercisecategory
	 */
	int getExercisecategory();

	/**
	 * Sets the exercisecategory
	 * @param exercisecategory
	 */
	void setExercisecategory(int exercisecategory);
	
	/**
	 * Returns the identifier
	 * @return identifier
	 */
	String getIdentifier();

	/**
	 * Sets the identifier 
	 * @param identifier
	 */
	void setIdentifier(String identifier);	
	
	/**
	 * returns variable1
	 * @return variable1
	 */
	int getVariable1();
	
	/**
	 * Sets variable1
	 * @param variable1
	 */
	void setVariable1(int variable1);

	/**
	 * returns variable2
	 * @return variable2
	 */
	int getVariable2();	

	/**
	 * Sets variable2
	 * @param variable2
	 */
	void setVariable2(int variable2);

	/**
	 * returns var1string
	 * return var1string
	 */
	String getVar1string();
	
	/**
	 * sets var1string
	 * @param var1string
	 */	
	void setVar1string(String var1string);

	/**
	 * returns var2string
	 * @param var2string
	 */
	String getVar2string();

	/**
	 * sets var2string
	 * @param var2string
	 */	
	void setVar2string(String var2string);

	/**
	 * Returns created date
	 * @return createddate
	 */
	Timestamp getCreateddate();

	/**
	 * Sets created date
	 * @param createddate
	 */
	void setCreateddate(Timestamp createddate);

}

