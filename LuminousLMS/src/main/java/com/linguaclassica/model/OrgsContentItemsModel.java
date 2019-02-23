package com.linguaclassica.model;

import java.io.Serializable;


/**
 * Business layer interface for orgs videos model corresponding 
 * to the orgs_videos look-up table.
 * @author Tammy
 */
public interface OrgsContentItemsModel extends Serializable
{

	/**
	 * Gets the id
	 * @return
	 */
	public Integer getId();
	
	/**
	 * Sets the id
	 * @param id
	 */
	public void setId(Integer id);

	/**
	 * Gets the org id
	 * @return
	 */
	public Integer getOrgid();

	/**
	 * Sets the org id
	 * @param orgid
	 */
	public void setOrgid(Integer orgid);

	/**
	 * Gets the content item id
	 * @return contentitemid
	 */
	public Integer getContentItemId();

	/**
	 * Sets the content item id
	 * @param contentitemid
	 */
	public void setContentItemId(Integer videoid);

}
