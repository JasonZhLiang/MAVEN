package com.linguaclassica.model;

import java.io.Serializable;
import java.sql.Timestamp;

public interface MessageModel extends Serializable {

	/**
	 * status can either be show or hide message
	 */
	public enum Status {UNREAD, READ};
	
	/**
	 * Returns the unique Id field for this message model
	 * @return Id
	 */
	public Integer getId();

	/**
	 * Sets the status, shoe or hide message
	 * class
	 * @param status
	 */
	public Status getStatus();

	/**
	 * Returns the status of the message
	 * @return
	 */
	public void setStatus(Status status);
	
	/**
	 * Returns the ID of the user that created the message
	 * @return
	 */
	Integer getFromId();
	
	/**
	 * Sets the ID of the user that created the message
	 * @param fromId
	 */
	void setFromId(Integer fromId);

	/**
	 * Returns the ID of the recipient of the message
	 * @return
	 */
	Integer getToId();
	
	/**
	 * Sets the ID of the recipient of the message
	 * @param toId
	 */
	void setToId(Integer toId);

	/**
	 * Returns the ID of the organization of the message
	 * @return
	 */
	Integer getOrgId();
	
	/**
	 * Sets the ID of the organization of the message
	 * @param orgId
	 */
	void setOrgId(Integer orgId);

	/**
	 * Returns the message subject
	 * @return
	 */
	String getSubject();
	
	/**
	 * Sets the message subject
	 * @param note
	 */
	void setSubject(String subject);
	
	/**
	 * Sets the content text of the message
	 * @param message
	 */
	public String getMessage();

	
	/**
	 * Returns the text content of the message
	 * @return
	 */
	public void setMessage(String message);

	/**
	 * Returns the creation date of the message
	 * @return
	 */
	public Timestamp getCreateDate();
	
	/**
	 * Sets the creation date of the mssage
	 * @param createdate
	 */
	public void setCreateDate(Timestamp createdate);

	/**
	 * Returns 	timezone of the message
	 * @return
	 */
	String getTimeZone();

	/**
	 * Sets the timezone of the message
	 * @param timezone
	 */
	void setTimeZone(String timeZone);
}
