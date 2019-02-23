package com.linguaclassica.model;

import java.io.Serializable;


/**
 * Business layer interface for EntityClassModel entity.
 */
public interface TextPassageModel extends Serializable {

	Integer getId();

	String getTextcontent();

	void setTextcontent(String textcontent);

	Integer getExerciseid();

	void setExerciseid(Integer exerciseid);

	String getTextinfo();

	void setTextinfo(String textinfo);

}
