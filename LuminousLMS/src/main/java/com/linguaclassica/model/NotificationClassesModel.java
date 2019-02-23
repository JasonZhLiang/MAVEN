package com.linguaclassica.model;

import java.io.Serializable;


/**
 * Business layer interface for EntityNotificationModel entity.
 */
public interface NotificationClassesModel extends Serializable
{
	/**
	 * Returns the unique ID field for this model.
	 * @return
	 */
	public Integer getId();

	/**
	 * Set the unique ID field for this model.
	 * @param id
	 */
	void setId(Integer id);	

	/**
	 * Returns the notification Id
	 * @return
	 */
	Integer getnotificationId();
	
	/**
	 * Sets the notification Id
	 * @param notificationId
	 */
	void setnotificationId(Integer notificationid);
	
	/**
	 * Returns the class Id
	 * @return
	 */
	Integer getclassId();
	
	/**
	 * Sets the class Id
	 * @param classId
	 */
	void setclassId(Integer classid);
	
}
