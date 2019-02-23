package com.linguaclassica.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.ExerciseModel;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="exercises")
public class EntityExerciseModel implements ExerciseModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private Integer exercisecategory;
	private int variable1;
	private int variable2;
	private String var1string;
	private String var2string;
	private String identifier;
	private Integer createdbyid;
	private Timestamp createddate;
	
	public EntityExerciseModel() {
	}

	public EntityExerciseModel(Integer id, Integer exercisecategory, String identifier, int variable1, 
			int variable2, String var1string, String var2string, Integer createdbyid, 
			Timestamp createddate) {
		this.id = id;
		this.exercisecategory = exercisecategory;
		this.createdbyid = createdbyid;
		this.identifier = identifier;
		this.variable1 = variable1;
		this.variable2 = variable2;
		this.var1string = var1string;
		this.var2string = var2string;
		this.createddate = createddate;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public Integer getCreatedById() {
		return createdbyid;
	}

	@Override
	public void setCreatedById(Integer createdbyid) {
		this.createdbyid = createdbyid;
	}
	
	@Override
	public int getExercisecategory() {
		return exercisecategory;
	}

	@Override
	public void setExercisecategory(int exercisecategory) {
		this.exercisecategory = exercisecategory;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	@Override
	public int getVariable1() {
		return variable1;
	}

	@Override
	public void setVariable1(int variable1) {
		this.variable1 = variable1;
	}
	@Override
	public int getVariable2() {
		return variable2;
	}

	@Override
	public void setVariable2(int variable2) {
		this.variable2 = variable2;
	}
	
	@Override
	public String getVar1string() {
		return var1string;
	}
		
	@Override
	public void setVar1string(String var1string) {
		this.var1string = var1string;
	}
	
	@Override
	public String getVar2string() {
		return var2string;
	}
		
	@Override
	public void setVar2string(String var2string) {
		this.var2string = var2string;
	}

	@Override
	public Timestamp getCreateddate() {
		return createddate;
	}

	@Override
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	
}



