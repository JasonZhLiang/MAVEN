package com.linguaclassica.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Gene
 *
 * Business layer interface for Group model.
 */
public interface SessionModel extends Serializable {
	/**
	 * Returns the unique ID field for this session model. IDs are only set
	 * once the session record is created.
	 * 
	 * @return id
	 */
	public Integer getId();
	
	/**
	 * Returns the unique session ID field for this group model.
	 * @return sessionName
	 */
	public String getSessionName();
	
	/**
	 * Sets sessionName
	 * @param sessionName
	 */
	public void setSessionName(String sessionName);
	
	/**
	 * Returns the time when the session was created
	 * @return
	 */
	public String getCreatetime();

	/**
	 * Sets the time when the session was created
	 * @param createtime
	 */
	public void setCreatetime(String createtime);
	
	/**
	 * Returns the time when the session, through JsonServlet, was last accessed
	 * @return
	 */
	public String getLasttime();

	/**
	 * Sets the time when the session was last accessed through JsonServlet
	 * @param lasttime
	 */
	public void setLasttime(String lasttime);	
	
	/**
	 * Returns the userId for the session
	 * @return
	 */
	public int getUserId();

	/**
	 * Sets the duration from creation of the session until the last accessed time
	 * @param userId
	 */
	public void setUserId(int userId);

	ArrayList<String> getClassList();

	void setClassList(ArrayList<String> classList);
}


