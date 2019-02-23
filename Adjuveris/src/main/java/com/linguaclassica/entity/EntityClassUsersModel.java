package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.ClassUsersModel;

/**
 * Entity that implements the class user model
 * for the class_users lookup table
 * @author Tammy
 * Copyright (c) 2015 - 2017 Lingua Classica
 */

@Entity
@Table(name="class_users")
public class EntityClassUsersModel implements ClassUsersModel{

	private static final long serialVersionUID = -8936026231938744660L;

	@Id
	@GeneratedValue()
	private Integer id;

	private Integer classid;
	private Integer userid;
	private String usertype;
	
	
	public EntityClassUsersModel()
	{	
	}

	public EntityClassUsersModel(EntityClassModel classes, EntityUserModel user, String usertype)
	{
		this.classid = classes.getId();
		this.userid = user.getId();
		this.usertype = usertype;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClassId() {
		return classid;
	}

	public void setClassId(Integer classid) {
		this.classid = classid;
	}

	public Integer getUserId() {
		return userid;
	}

	public void setUserId(Integer userid) {
		this.userid = userid;
	}

	public String getUserType() {
		return usertype;
	}

	public void setUserType(String usertype) {
		this.usertype = usertype;
	}
}
