package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.InstUserPermissionModel;

/**
 * Entity that implements the InstUserPermissionModel
 * @author Joe Brown
 */

@Entity
@Table(name="inst_user_permissions")
public class InstUserPermissionEntity implements InstUserPermissionModel
{
	private static final long serialVersionUID = -6791409754609260506L;

	@Id
	@GeneratedValue()
	private Integer id;

	private Integer instid;
	private Integer userid;
	private Integer permissionid;
	
	public InstUserPermissionEntity()
	{
		instid = 0;
		userid = 0;
		permissionid = 0;
	}
	
	public InstUserPermissionEntity(Integer institutionId, Integer userId, Integer permissionId)
	{
		instid = institutionId;
		userid = userId;
		permissionid = permissionId;
	}

	@Override
	public Integer getId()
	{
		return id;
	}

	@Override
	public Integer getInstitutionId()
	{
		return instid;
	}

	@Override
	public void setInstitutionId(Integer institutionId)
	{
		instid = institutionId;		
	}

	@Override
	public Integer getUserId()
	{
		return userid;
	}

	@Override
	public void setUserId(Integer userId)
	{
		userid = userId;
	}

	@Override
	public void setPermissionId(Integer permissionId)
	{
		permissionid = permissionId;
	}

	@Override
	public Integer getPermissionId()
	{
		return permissionid;
	}

}
