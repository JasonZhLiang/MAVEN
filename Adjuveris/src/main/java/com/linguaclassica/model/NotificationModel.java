package com.linguaclassica.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Business layer interface for EntityNotificationModel entity.
 */
public interface NotificationModel extends Serializable
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
	 * Returns the userid of the person who created the notification
	 * @return
	 */
	Integer getCreatedById();
	
	/**
	 * Sets the userid of the person who created the notification
	 * @param userId
	 */
	void setCreatedById(Integer userId);
	
	/**
	 * Returns the notification string
	 * @return
	 */
	String getNote();
	
	/**
	 * Sets the notification string
	 * @param note
	 */
	void setNote(String note);
	
	/**
	 * Returns the start date for when the notification will be posted
	 * @return
	 */
	Timestamp getStartDate();
	
	/**
	 * Sets the start date for when the notification will be posted
	 * @param startDate
	 */
	void setStartDate(Timestamp startDate);
	
	/**
	 * Returns the expiry date of the notification posting
	 * @return
	 */
	Timestamp getExpiryDate();
	
	/**
	 * Sets the expiry date of the notification posting
	 * @param myDate
	 */

	void setExpiryDate(Timestamp expiryDate);
	/**
	 * Returns 	timezone of the notification posting
	 * @return
	 */

	String getTimeZone();
	 /**
		 * Sets the timezone of the notification posting
		 * @param timezone
		 */

	void setTimeZone(String timeZone);
}
