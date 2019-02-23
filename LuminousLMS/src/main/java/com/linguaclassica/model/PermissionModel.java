package com.linguaclassica.model;

import java.io.Serializable;

/**
 * Business layer interface for permissions model.
 * @author Cris
 *
 */
public interface PermissionModel extends Serializable
{
	/**
	 * Permissions are roles that users can perform
	 */
	public enum Permission 
	{
		NONE,	// should be 0
		// Next four permission set in create_tables.sql and confirmed in PermissionServiceTest
		CLIENT, CONSULTANT, OFFICE_ADMIN, ADMINISTRATOR;

		/**
		 * Helper function to return list of possible values of this Enum as strings
		 * @return
		 */ /*
		public List<String> getListOfPermissions() 
		{
			ArrayList<String> listOfPermissions = new ArrayList<String>();
			
			for (Permission perm : Permission.values())
			{
				listOfPermissions.add(perm.toString());
			}
			return listOfPermissions;
		} */
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
