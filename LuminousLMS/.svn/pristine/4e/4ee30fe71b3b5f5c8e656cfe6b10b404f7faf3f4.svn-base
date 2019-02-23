package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.OrgUserPermissionModel;

/**
 * Entity that implements the OrgUserPermissionModel
 * @author Joe Brown
 */

@Entity
@Table(name="org_user_permissions")
public class OrgUserPermissionEntity implements OrgUserPermissionModel
{
	private static final long serialVersionUID = -1464539196566973931L;

	@Id
	@GeneratedValue()
	private Integer id;

	private Integer orgid;
	private Integer userid;
	private Integer permissionid;

	public OrgUserPermissionEntity()
	{
		orgid = 0;
		userid = 0;
		permissionid = 0;
	}

	public OrgUserPermissionEntity(Integer organizationId, Integer userId, Integer permissionId)
	{
		orgid = organizationId;
		userid = userId;
		permissionid = permissionId;
	}

	@Override
	public Integer getId()
	{
		return id;
	}

	@Override
	public Integer getOrganizationId()
	{
		return orgid;
	}

	@Override
	public void setOrganizationId(Integer organizationId)
	{
		orgid = organizationId;
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
