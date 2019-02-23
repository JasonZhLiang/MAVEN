package com.linguaclassica.entity;


import java.sql.Blob;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.ResourceModel;

/**
 * Entity implementation of the ResourceModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="resources")
public class EntityResourceModel implements ResourceModel {
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private String name;
	private Blob resource;
	private String ext;
	
	public EntityResourceModel() {
	}

	public EntityResourceModel(Integer id, String name, Blob resource, String ext) {
		this.id = id;
		this.name = name;
		this.resource = resource;
		this.ext = ext;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Blob getResource() {
		return resource;
	}

	@Override
	public void setResource(Blob resource) {
		this.resource = resource;
	}

	@Override
	public String getExt() {
		return ext;
	}

	@Override
	public void setExt(String ext) {
		this.ext = ext;
	}


}
