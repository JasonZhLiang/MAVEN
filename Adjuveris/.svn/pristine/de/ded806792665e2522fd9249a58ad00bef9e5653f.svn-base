package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.InstUsersModel;

/**
 * Entity that implements the InstUsersModel
 * for the inst_users lookup table
 * @author Gene Price
 */

@Entity
@Table(name="inst_users")
public class EntityInstUsersModel implements InstUsersModel{

	private static final long serialVersionUID = -8936026231938744660L;

	@Id
	@GeneratedValue()
	private Integer id;

	private Integer instid;
	
	private Integer userid;
	
	
	public EntityInstUsersModel()
	{	
	}

	public EntityInstUsersModel(Integer instid, Integer userid)
	{
		this.instid = instid;
		this.userid = userid;
	}

	@Override
	public Integer getId() {
		return id;
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
	public Integer getUserid() {
		return userid;
	}
	
	@Override
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
}
