package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.TermTaModel;

/**
 * Entity that implements the EntityTermTaModel
 * for the term_ta lookup table
 * @author Gene Price
 */

@Entity
@Table(name="terms_tas")
public class EntityTermTaModel implements TermTaModel{

	private static final long serialVersionUID = -8936026231938744660L;

	@Id
	@GeneratedValue()
	private Integer id;

	private Integer termid;
	
	private Integer taid;
	
	
	public EntityTermTaModel()
	{	
	}

	public EntityTermTaModel(Integer termid, Integer taid)
	{
		this.termid = termid;
		this.taid = taid;
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
	public Integer getTaid() {
		return taid;
	}

	@Override
	public void setTaid(Integer taid) {
		this.taid = taid;
		
	}
}


