package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.Variable1Model;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="variable1")
public class EntityVariable1Model implements Variable1Model
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private Integer exerciseid;
	private String variable;
	
	public EntityVariable1Model() {
	}

	public EntityVariable1Model(Integer id, Integer exerciseid, String variable) {
		this.id = id;
		this.exerciseid =exerciseid;
		this.variable = variable;		
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getExerciseid() {
		return exerciseid;
	}

	@Override
	public void setExerciseid(Integer exerciseid) {
		this.exerciseid = exerciseid;
	}
	
	@Override
	public String getVariable() {
		return variable;
	}
	
	@Override
	public void setVariable(String variable) {
		this.variable = variable;
	}
}



