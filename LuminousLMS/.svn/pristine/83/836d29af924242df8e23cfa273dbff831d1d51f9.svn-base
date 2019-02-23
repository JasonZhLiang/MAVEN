package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.ExamModel;

@Entity
@Table(name="exams")
public class EntityExamModel implements ExamModel {

	private static final long serialVersionUID = -3383924518659728591L;

	@Id
	private Integer contentId;
	
	private String examServletUrl;
	
	@Override
	public Integer getContentId() {
		
		return contentId;
	}

	@Override
	public String getExamServletURL() {
		return examServletUrl;
	}

	@Override
	public void setExamServletURL(String servletURL) {
		examServletUrl = servletURL;
	}

}
