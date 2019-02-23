package com.linguaclassica.model;

import java.io.Serializable;

public interface ExamModel extends Serializable {
	
	
	/**
	 * returns the unique ID for an exam
	 * @return ContentID
	 */
	public Integer getContentId();
	
	/**
	 * returns the exam servlet url
	 * @return ExamServletURL
	 */
	public String getExamServletURL();
	
	/**
	 * sets the exam servlet url
	 * @param servletURL
	 */
	public void setExamServletURL(String servletURL);

}
