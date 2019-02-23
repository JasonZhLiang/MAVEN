package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.UsersGroupsModel;

/**
 * Entity implementation of the UsersGroupsModel interface
 */
@Entity
@Table(name="users_groups")
public class EntityUsersGroupsModel implements UsersGroupsModel {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private Integer groupid;
	private Integer userid;
	
	public EntityUsersGroupsModel() {
	}

	public EntityUsersGroupsModel(Integer id, Integer groupid, Integer userid) {
		this.id = id;
		this.groupid = groupid;
		this.userid = userid;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getGroupId() {
		return groupid;
	}

	@Override
	public void setGroupId(Integer groupid) {
		this.groupid = groupid;
	}

	@Override
	public Integer getUserId() {
		return userid;
	}

	@Override
	public void setUserId(Integer userid) {
		this.userid = userid;
	}

}
