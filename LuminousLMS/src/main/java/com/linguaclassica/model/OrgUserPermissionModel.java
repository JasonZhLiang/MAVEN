package com.linguaclassica.model;

import java.io.Serializable;

/**
 * Business layer interface for OrgUserPermissionModel corresponding 
 * to the org_user_permissions table.
 * @author Joe A Brown
 */
public interface OrgUserPermissionModel extends Serializable
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
	public Integer getOrganizationId();

	/**
	 * Sets the class id
	 * @param classId
	 */
	public void setOrganizationId(Integer organizationId);

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
