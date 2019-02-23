package com.linguaclassica.entity;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.OtherResourceModel;

@Entity
@Table(name="other_resources")
public class EntityOtherResourceModel implements OtherResourceModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue()
	private Integer id;
	private Integer orgid;
	private String res_name;
	private Blob res_blob;

	public EntityOtherResourceModel() {
		
	}
	
public EntityOtherResourceModel(Integer id, Integer orgid, String res_name, Blob res_blob) {
		this.id = id;
		this.orgid = orgid;
		this.res_name = res_name;
		this.res_blob = res_blob;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public void setOrgId(Integer orgid) {
		this.orgid = orgid;	
	}

	@Override
	public Integer getOrgId() {
		return orgid;
	}

	@Override
	public void setResName(String res_name) {
		this.res_name = res_name;
	}

	@Override
	public String getResName() {
		return res_name;
	}

	@Override
	public void setResBlob(Blob res_blob) {
		this.res_blob = res_blob;
	}

	@Override
	public Blob getResBlob() {
		return res_blob;
	}

}
