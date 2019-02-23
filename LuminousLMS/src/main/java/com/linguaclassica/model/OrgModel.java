package com.linguaclassica.model;

import java.io.Serializable;

public interface OrgModel extends Serializable {
	
	/**
	 * Returns the unique Id field for this message model
	 * @return Id
	 */
	public Integer getId();

	/**
	 * Sets the orgname
	 */
	public void setOrgname(String orgname);

	
	/**
	 * Returns the orgname
	 * @return
	 */
	public String getOrgname();
	
	/**
	 * Sets the orgemail
	 * @param orgemail
	 */
	public void setOrgemail(String orgemail);

	
	/**
	 * Returns orgemail
	 * @return
	 */
	public String getOrgemail();

}
