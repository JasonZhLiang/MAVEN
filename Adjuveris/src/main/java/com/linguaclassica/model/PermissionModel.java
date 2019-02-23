package com.linguaclassica.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Business layer interface for permissions model.
 * @author Cris
 *
 */
public interface PermissionModel extends Serializable
{
	/**
	 * Permissions can be simple user or administrator, or some institution administration assistant
	 * Not to be confused with group roles in GroupMembership
	 */
	public enum Permission 
	{
		STUDENT,TEACHER,TA,INSTITUTION_ADMIN,ADMINISTRATOR;
		/**
		 * Helper function to return list of possible values of this Enum as strings
		 * @return
		 */
		public List<String> getListOfPermissions() 
		{
			ArrayList<String> listOfPermissions = new ArrayList<String>();
			
			for (Permission perm : Permission.values())
			{
				listOfPermissions.add(perm.toString());
			}
			return listOfPermissions;
		}
	};

	/**
	 * Returns the unique ID field for this permission model. IDs are only set once the permission is 
	 * created.
	 * @return id
	 */
	public Integer getId();
	
	/**
	 * Sets id
	 * @param id
	 */
	public void setId(Integer id);
	
	/**
	 * Sets permission
	 * @param permission
	 */
	public void setPermission(Permission permission);
	
	/**
	 * Returns the permission
	 * @return Permission
	 */
	public Permission getPermission();

}
