package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.linguaclassica.model.ProficiencyModel;

/**
 * Entity implementation of the proficiency interface
 * @author Tammy
 * Date: 09-02-2015
 */
@Entity
@Table(name="proficiencies")
public class EntityProficiencyModel implements ProficiencyModel 
{
	private static final long serialVersionUID = 1404092662180242948L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private Proficiency proficiency;
	
	public EntityProficiencyModel()
	{
	}
	
	public EntityProficiencyModel(Proficiency proficiency)
	{
		this.proficiency = proficiency;
	}
	
	public EntityProficiencyModel(Integer id, Proficiency proficiency)
	{
		this.id = id;
		this.proficiency = proficiency;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Proficiency getProficiency() {
		return proficiency;
	}
	
	@Override
	public void setProficiency(Proficiency proficiency) {
		this.proficiency = proficiency;
	}
	
	/**
	 * Helper that converts the proficiency enum into a friendly proficiency name
	 * @param proficiency
	 * @return String
	 */
	public static String getProficiencyString(Proficiency proficiency) 
	{
		String proficiencyString = "";
		switch (proficiency)
		{
			case VOCABULARY:
				proficiencyString = "Vocabulary";
				default:
				break;
			case INFLECTIONS:
				proficiencyString = "Inflections";
				break;
			case SYNTAX:
				proficiencyString = "Syntax";
				break;
			case COMPREHENSION:
				proficiencyString = "Comprehension";
				break;
		}
		return proficiencyString;
	}
	
	/**
	 * Helper that converts a friendly proficiency name into our own enumerator
	 * @param proficiencyString
	 * @return proficiency
	 */
	public static Proficiency getProficiency(String proficiencyString) 
	{
		Proficiency proficiency = Proficiency.VOCABULARY;
		if (proficiencyString.equalsIgnoreCase("Vocabulary")) 
		{
			proficiency = Proficiency.VOCABULARY;
		} 
		else if (proficiencyString.equalsIgnoreCase("Inflections"))
		{
			proficiency = Proficiency.INFLECTIONS;
		}
		else if (proficiencyString.equalsIgnoreCase("Syntax"))
		{
			proficiency = Proficiency.SYNTAX;
		}
		else if (proficiencyString.equalsIgnoreCase("Comprehension"))
		{
			proficiency = Proficiency.COMPREHENSION;
		}
		return proficiency;
	}
}
