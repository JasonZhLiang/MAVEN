package com.linguaclassica.model;

import java.io.Serializable;

/**
 * Business layer interface for InstUserPermissionModel corresponding 
 * to the inst_user_permissions table.
 * @author Joe Brown
 */

public interface InstUserPermissionModel extends Serializable
{
	/**
	 * Gets the id
	 * @return
	 */
	public Integer getId();

	/**
	 * Gets the class id
	 * @return
	 */
	public Integer getInstitutionId();

	/**
	 * Sets the class id
	 * @param classId
	 */
	public void setInstitutionId(Integer institutionId);

	/**
	 * Gets the user id
	 * @return
	 */
	public Integer getUserId();

	/**
	 * Sets the user id
	 * @param userId
	 */
	public void setUserId(Integer userId);

	/**
	 * Set the Permission (role) id
	 * @param permid
	 */
	public void setPermissionId(Integer permissionId);
	
	/**
	 * Get the Permission (role) id
	 * @return
	 */
	public Integer getPermissionId();
}
