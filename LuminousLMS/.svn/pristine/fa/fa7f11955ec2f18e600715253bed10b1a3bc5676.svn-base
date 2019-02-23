package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.PermissionModel;

/**
 * Entity implementation of the permission interface
 * @author Cris
 *
 */
@Entity
@Table(name="permissions")
public class EntityPermissionModel implements PermissionModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Permission permission;
	
	public EntityPermissionModel()
	{
	}

	public EntityPermissionModel(Permission permission)
	{
		this.permission = permission;
	}

	public EntityPermissionModel(Integer id, Permission permission)
	{
		this.id = id;
		this.permission = permission;
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
	public Permission getPermission() {
		return permission;
	}

	@Override
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public String getPermissionString()
	{
		String permissionString = "";
		
		switch (permission)
		{
			case CLIENT:
				permissionString = "Client";
				break;
			case CONSULTANT:
				permissionString = "Consultant";
				break;
			case OFFICE_ADMIN:
			    permissionString = "Office_Admin";
				break;
			case ADMINISTRATOR:
				permissionString = "Administrator";
				break;
			default:
				permissionString = "undefined";
				break;
		}
		return permissionString;
	}

	/**
	 * Helper that converts the permission enum into 
	 * a friendly permission name
	 * @param permission
	 * @return
	 */
	public static String getPermissionString(Permission perm)
	{
		String permissionString = "";
		
		switch (perm)
		{
			case CLIENT:
				permissionString = "Client";
				break;
			case CONSULTANT:
				permissionString = "Consultant";
				break;
			case OFFICE_ADMIN:
			    permissionString = "Office_Admin";
				break;
			case ADMINISTRATOR:
				permissionString = "Administrator";
				break;
			default:
				permissionString = "undefined";
				break;
		}
		return permissionString;
	}
	
	/**
	 * Helper that converts a friendly permission name into
	 * our own enumerator
	 * @param permission
	 * @return
	 */
	public static Permission getPermission(String permissionString)
	{
		// TODO This initialization is a bad idea!
		Permission permission = Permission.CLIENT;
		if (permissionString.equalsIgnoreCase("CLIENT"))
		{
			permission = Permission.CLIENT;
		}
		else if (permissionString.equalsIgnoreCase("CONSULTANT"))
		{
			permission = Permission.CONSULTANT;
		}
		else if (permissionString.equalsIgnoreCase("OFFICE_ADMIN"))
		{
			permission = Permission.OFFICE_ADMIN;
		}
		else if (permissionString.equalsIgnoreCase("ADMINISTRATOR"))
		{
			permission = Permission.ADMINISTRATOR;
		}
		return permission;
	}
}
