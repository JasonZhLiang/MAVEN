package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import com.linguaclassica.model.DateRangeModel;

@Entity
@Table(name="date_ranges")
public class EntityDateRangeModel implements DateRangeModel {
	
	private static final long serialVersionUID = -8936026231938744660L;

	@Id
	@GeneratedValue()
	private Integer id;
	private Integer instid;
	private Integer studentsTerms;
	private Integer instadminTerms;
	
	
	public EntityDateRangeModel(DateRangeModel rangeModel)
	{
		//this.id = id;
		this.instid= rangeModel.getInstId();
		this.studentsTerms = rangeModel.getStudentsTerms();
		this.instadminTerms = rangeModel.getInstAdminTerms();
		
	}
	
	public EntityDateRangeModel()
	{
		
	}

	
	public Integer getId(){
		return id;
	};

	public void setId(Integer ID){
		this.id=ID;
	};
	
	public 	Integer getInstId(){
		return instid;
	};

	public void setInstId(Integer instid){
		this.instid=instid;
	};

	public Integer getStudentsTerms(){
		return studentsTerms;
	};

	public void setStudentsTerms(Integer studentsTerms){
		this.studentsTerms = studentsTerms;
	};

	public Integer getInstAdminTerms(){
		return instadminTerms; 
	};

	public void setInstAdminTerms(Integer instadminTerms){
		this.instadminTerms = instadminTerms;
	};
	
};

