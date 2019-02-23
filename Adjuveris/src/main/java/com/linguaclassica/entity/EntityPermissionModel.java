package com.linguaclassica.entity;

//import javax.persistence.CascadeType;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.linguaclassica.model.PermissionModel;
//import com.linguaclassica.model.GroupMembership.GroupRole;

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
			case TEACHER:
				permissionString = "teacher";
				break;
			case TA:
				permissionString = "ta";
				break;
			case STUDENT:
				default:
				permissionString = "student";
				break;
			case INSTITUTION_ADMIN:
			    permissionString = "institution_admin";
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
	public static String getPermissionString(Permission permission)
	{
		String permissionString = "";
		
		switch (permission)
		{
			case TEACHER:
				permissionString = "teacher";
				break;
			case TA:
				permissionString = "ta";
				break;
			case STUDENT:
				default:
				permissionString = "student";
				break;
			case INSTITUTION_ADMIN:
			    permissionString = "institution_admin";
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
		Permission permission = Permission.STUDENT;
		if (permissionString.equalsIgnoreCase("STUDENT"))
		{
			permission = Permission.STUDENT;
		}
		else if (permissionString.equalsIgnoreCase("TEACHER"))
		{
			permission = Permission.TEACHER;
		} 
		else if (permissionString.equalsIgnoreCase("TA"))
		{
			permission = Permission.TA;
		}
		else if (permissionString.equalsIgnoreCase("INSTITUTION_ADMIN"))
		{
			permission = Permission.INSTITUTION_ADMIN;
		}
		else if (permissionString.equalsIgnoreCase("ADMINISTRATOR"))
		{
			permission = Permission.ADMINISTRATOR;
		}
		return permission;
	}
}
