package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.linguaclassica.model.UserPermissionModel;

/**
 * Entity that implements the user permission model
 * for the user_permission lookup table
 * @author Cris
 *
 */
@Entity
@Table(name="users_permissions")
public class EntityUserPermissionModel implements UserPermissionModel
{
	private static final long serialVersionUID = 8662353643350193794L;

	@Id
	@GeneratedValue()
	private Integer id;

	private Integer userId;
	
	private Integer permissionId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId", insertable=false, updatable=false)	
	private EntityUserModel user = null;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="permissionId", insertable=false, updatable=false)
	private EntityPermissionModel permission = null;

	public EntityUserPermissionModel()
	{	
	}

	public EntityUserPermissionModel(EntityUserModel user, EntityPermissionModel permission)
	{
		this.userId = user.getId();
		this.user = user;
		this.permissionId = permission.getId();
		this.permission = permission;
	}
	
	@Override
	public Integer getId()
	{
		return id;
	}

	@Override
	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public Integer getUserId()
	{
		return this.userId;
	}

	@Override
	public Integer getPermissionId()
	{
		return this.permissionId;
	}

	@Override
	public void setUserId(Integer id)
	{
		this.userId = id;
	}

	@Override
	public void setPermissionId(Integer id)
	{
		this.permissionId = id;
	}

	public EntityUserModel getUser()
	{
		return user;
	}

	public EntityPermissionModel getPermission()
	{
		return permission;
	}
}
