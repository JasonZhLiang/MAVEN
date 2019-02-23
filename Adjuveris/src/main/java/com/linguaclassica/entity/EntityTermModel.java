package com.linguaclassica.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.TermModel;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="terms")
public class EntityTermModel implements TermModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private String termname;
	private int instid;
	private Date startdate;
	private Date enddate;
	private Date updatedate;
	
	public EntityTermModel() {
		this.id = 0;
		this.instid = 0;
	}

	public EntityTermModel(Integer id, String termname, Date startdate, Date enddate, Date updatedate) {
		this.id = id;
		this.termname = termname;
		this.startdate = startdate;
		this.enddate = enddate;
		this.updatedate = updatedate;
	}

	@Override
	public Integer getId() {
		return id;
	}

	// for JUnit testing
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getTermname() {
		return termname;
	}

	@Override
	public void setTermname(String termname) {
		this.termname = termname;
	}

	@Override
	public Integer getInstid() {
		return instid;
	}

	@Override
	public void setInstid(Integer instid) {
		this.instid = instid;
	}

	@Override
	public Date getStartDate() {
		return startdate;
	}

	@Override
	public void setStartDate(Date startdate) {
		this.startdate = startdate;
	}

	@Override
	public Date getEndDate() {
		return enddate;
	}

	@Override
	public void setEndDate(Date enddate) {
		this.enddate = enddate;
	}

	@Override
	public Date getUpdateDate() {
		return updatedate;
	}

	@Override
	public void setUpdateDate(Date updatedate) {
		this.updatedate = updatedate;
	}
}




