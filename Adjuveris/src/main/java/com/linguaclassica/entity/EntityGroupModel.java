package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.GroupModel;

/**
 * Entity implementation of the GroupModel interface
 */
@Entity
@Table(name="groups")
public class EntityGroupModel implements GroupModel {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private String groupname;
	private Integer createdbyid;
	
	public EntityGroupModel() {
	}

	public EntityGroupModel(Integer id, String groupname, Integer createdbyid) {
		this.id = id;
		this.groupname = groupname;
		this.createdbyid = createdbyid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupname;
	}

	public void setGroupName(String groupname) {
		this.groupname = groupname;
	}

	public Integer getCreatedById() {
		return createdbyid;
	}

	public void setCreatedById(Integer createdbyid) {
		this.createdbyid = createdbyid;
	}

}
