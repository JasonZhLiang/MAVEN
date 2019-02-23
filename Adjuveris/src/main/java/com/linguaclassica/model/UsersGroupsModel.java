package com.linguaclassica.model;

import java.io.Serializable;

/**
 * Business layer interface for EntityUsersGroupsModel entity.
 */
public interface UsersGroupsModel extends Serializable{
	
	/**
	 * Get the unique id of the user-group pair
	 * @return Integer id
	 */
	public Integer getId();
	
	/**
	 * Set the unique id of the user-group pair
	 * @param id
	 */
	void setId(Integer id);
	
	/**
	 * Get the groupid
	 * @return Integer groupid
	 */
	Integer getGroupId();
	
	/**
	 * Set the groupid
	 * @param groupId
	 */
	void setGroupId(Integer groupId);
	
	/**
	 * Get the userid that is part of the group
	 * @return
	 */
	Integer getUserId();
	
	/**
	 * Set the userid to add it to the group
	 * @param userId
	 * @return
	 */
	void setUserId(Integer userId);
	
	

}
