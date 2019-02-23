package com.linguaclassica.model;

import java.io.Serializable;

public interface ContentItemsModel extends Serializable {
	
	/* Types of content which comprise assignments */
	public enum ContentTypes
	{
		NONE, // should be 0 
		// Make sure the following are in the same order defined by create_tables.sql
		VIDEO, QUIZ, EXAM, OTHER;
	}
	
	/**
	 * Returns the unique Id field for this message model
	 * @return Id
	 */
	public Integer getId();

	/**
	 * Sets the name
	 */
	public void setName(String name);

		/**
	 * Returns the name
	 * @return
	 */
	public String getName();

	/**
	 * Sets the description
	 */
	public void setDescription(String description);

		/**
	 * Returns description
	 * @return
	 */
	public String getDescription();

	/**
	 * Sets the type id
	 */
	public void setTypeId(ContentTypes typeid);

	/**
	 * Returns the type id
	 * @return
	 */
	public ContentTypes getTypeId();
}

