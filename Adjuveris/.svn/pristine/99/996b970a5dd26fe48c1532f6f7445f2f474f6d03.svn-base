package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.ClassModel;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="classes")
public class EntityClassModel implements ClassModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private String classname;
	private String classcode;
	private int termid;
	
	public EntityClassModel()
	{
		id = 0;
		termid = 0;
	}

	public EntityClassModel(Integer id, String classname, String classcode, Integer termid)
	{
		this.id = id;
		this.classname = classname;
		this.classcode = classcode;
		this.termid = termid;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getClassname() {
		return classname;
	}

	@Override
	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Override
	public String getClasscode() {
		return classcode;
	}

	@Override
	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}

	@Override
	public Integer getTermid() {
		return termid;
	}

	@Override
	public void setTermid(Integer termid) {
		this.termid = termid;
	}
}



