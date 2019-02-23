package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.AssignmentClassesModel;

/**
 * Entity implementation of the AssignmentClassesModel interface
 * @author Kwirtanen
 */

@Entity
@Table(name="assignment_classes")
public class EntityAssignmentClassesModel implements AssignmentClassesModel {

	private static final long serialVersionUID = -3086077120733093733L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private Integer assignmentId;
	private Integer classId;
	
	public EntityAssignmentClassesModel(){
	}
	
	public EntityAssignmentClassesModel(Integer assignmentId, Integer classId){
		this.assignmentId = assignmentId;
		this.classId = classId;
	}
	
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public Integer getAssignmentId() {
		return assignmentId;
	}

	@Override
	public void setAssignmentId(Integer assignmentId) {
		this.assignmentId = assignmentId;
		
	}

	@Override
	public Integer getClassId() {
		return classId;
	}

	@Override
	public void setClassId(Integer classId) {
		this.classId = classId;
		
	}

}
