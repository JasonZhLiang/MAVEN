package com.linguaclassica.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Business layer interface for proficiency model.
 * @author Tammy
 * Date: 09-02-2015
 */
public interface ProficiencyModel extends Serializable
{
	/**
	 * The proficiencies that will be assessed are vocabulary, inflections, syntax and comprehension
	 */
	public enum Proficiency {
		VOCABULARY, INFLECTIONS, SYNTAX, COMPREHENSION;
		
		/**
		 * Helper function to return list of possible values of this Enum as strings
		 * @return
		 */
		public List<String> getListOfProficiencies(){
			ArrayList<String> listOfProficiencies = new ArrayList<String>();
			
			for (Proficiency prof : Proficiency.values()){
				listOfProficiencies.add(prof.toString());
			}
			return listOfProficiencies;
		}
	}

	/** 
	 * Returns the unique ID field for this proficiency model.
	 * @return
	 */

	public Integer getId();

	/**
	 * Sets id
	 * @param id
	 */
	public void setId(Integer id);

	/**
	 * Returns the proficiency
	 * @return
	 */
	public Proficiency getProficiency();
	
	/**
	 * Sets the proficiency
	 * @param proficiency
	 */
	public void setProficiency(Proficiency proficiency);

}
