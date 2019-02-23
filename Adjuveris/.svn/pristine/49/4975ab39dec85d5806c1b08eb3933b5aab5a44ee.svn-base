package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.AssignmentFactModel;

/**
 * Entity implementation of the AssignmentsFactModel interface
 */
@Entity
@Table(name="assignments_fact")
public class EntityAssignmentFactModel implements AssignmentFactModel{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue()
	private Integer id;
	
	private Integer classid;
	private Integer studentid;
	private Integer groupid;
	private Integer assignmentid;
	private String status;
	
	public EntityAssignmentFactModel() {
	}
	
	public EntityAssignmentFactModel(Integer id, 
			Integer classid, 
			Integer studentid, 
			Integer groupid, 
			Integer assignmentid, 
			String status) {
		this.id = id;
		this.classid = classid;
		this.studentid = studentid;
		this.groupid = groupid;
		this.assignmentid = assignmentid;
		this.status = status;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;		
	}

	@Override
	public Integer getClassId() {
		return classid;
	}

	@Override
	public void setClassId(Integer classid) {
		this.classid = classid;
	}

	@Override
	public Integer getStudentId() {
		return studentid;
	}

	@Override
	public void setStudentId(Integer studentid) {
		this.studentid = studentid;
	}

	@Override
	public Integer getGroupId() {
		return groupid;
	}

	@Override
	public void setGroupId(Integer groupid) {
		this.groupid = groupid;		
	}

	@Override
	public Integer getAssignmentId() {
		return assignmentid;
	}

	@Override
	public void setAssignmentId(Integer assignmentid) {
		this.assignmentid = assignmentid;
	}

	
	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

}
