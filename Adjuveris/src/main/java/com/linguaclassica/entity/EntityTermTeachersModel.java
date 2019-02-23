package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.TermTeachersModel;

/**
 * Entity that implements the term_teachers lookup table
 * @author Gene Price
 */

@Entity
@Table(name="terms_teachers")
public class EntityTermTeachersModel implements TermTeachersModel{

	private static final long serialVersionUID = -8936026231938744660L;

	@Id
	@GeneratedValue()
	private Integer id;

	private Integer termid;
	
	private Integer teacherid;
	
	
	public EntityTermTeachersModel()
	{	
	}

	public EntityTermTeachersModel(Integer termid, Integer teacherid)
	{
		this.termid = termid;
		this.teacherid = teacherid;
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
	public Integer getTeacherid() {
		return teacherid;
	}
	
	@Override
	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}
}


