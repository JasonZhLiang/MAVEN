package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.TermStudentsModel;

/**
 * Entity that implements the term_students join table
 * @author Gene Price
 */

@Entity
@Table(name="terms_students")
public class EntityTermStudentsModel implements TermStudentsModel{

	private static final long serialVersionUID = -8936026231938744660L;

	@Id
	@GeneratedValue()
	private Integer id;

	private Integer termid;
	
	private Integer studentid;
	
	
	public EntityTermStudentsModel()
	{	
	}

	public EntityTermStudentsModel(Integer id, Integer termid, Integer studentid)
	{
		this.id = id;
		this.termid = termid;
		this.studentid = studentid;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public Integer getTermid() {
		return termid;
	}
	
	@Override
	public void setTermid(Integer termid) {
		this.termid = termid;
	}
	
	@Override
	public Integer getStudentid() {
		return studentid;
	}
	
	@Override
	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}
}

