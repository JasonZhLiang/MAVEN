package com.linguaclassica.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.AssignmentModel;

/**
 * Entity implementation of the ExerciseModel interface
 * @author Gene
 *
 */
@Entity
@Table(name="assignments")
public class EntityAssignmentModel implements AssignmentModel
{
	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private String assignmentname;
	private Integer exerciseid;
	private Integer teacherid;
	private Timestamp startdate;
	private Timestamp duedate;
	private Timestamp updatedate;
	private String status;
	private String type;
	private Integer weight;	
	private String additionalresources;
	private String comment;
	private Timestamp createddate;
	private String timezone;
	
	public EntityAssignmentModel() {
	}

	public EntityAssignmentModel(Integer id, String assignmentname, Integer exerciseid, 
			Integer teacherid, Timestamp startdate, Timestamp duedate, 
			Timestamp updatedate, String status, String type, Integer weight, 
			String additionalresources, String comment, String timezone) {
		this.id = id;
		this.assignmentname = assignmentname;
		this.exerciseid = exerciseid;
		this.teacherid = teacherid;
		this.startdate = startdate;
		this.duedate = duedate;
		this.updatedate = updatedate;
		this.status = status;
		this.type = type;
		this.weight = weight;	
		this.additionalresources = additionalresources;
		this.comment = comment;
		this.timezone = timezone;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String getAssignmentName() {
		return assignmentname;
	}

	@Override
	public void setAssignmentName(String assignmentname) {
		this.assignmentname = assignmentname;
	}

	@Override
	public Integer getExerciseId() {
		return exerciseid;
	}

	@Override
	public void setExerciseId(Integer exerciseid) {
		this.exerciseid = exerciseid;
	}

	@Override
	public Integer getTeacherId() {
		return teacherid;
	}

	@Override
	public void setTeacherId(Integer teacherid) {
		this.teacherid = teacherid;
	}
		
	@Override
	public Timestamp getStartDate() {
		return startdate;
	}

	@Override
	public void setStartDate(Timestamp startdate) {
		this.startdate = startdate;
	}
	
	@Override
	public Timestamp getDueDate() {
		return duedate;
	}
	
	@Override
	public void setDueDate(Timestamp duedate) {
		this.duedate = duedate;
	}
	
	@Override
	public Timestamp getUpdateDate() {
		return updatedate;
	}

	@Override
	public void setUpdateDate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	
		@Override
	public String getType() {
		return type;
	}
	
	@Override
	public void setType(String type) {
		this.type = type;
	}
 
	@Override
	public Integer getWeight() {
		return weight;
	}

	@Override
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	@Override
	public String getAdditionalResources() {
		return additionalresources;
	}

	@Override
	public void setAdditionalResources(String additionalresources) {
		this.additionalresources = additionalresources;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public Timestamp getCreatedDate() {
		return createddate;
	}
	
	@Override
	public void setCreatedDate(Timestamp createddate) {
		this.createddate = createddate;
	}
	

	@Override
	public String getTimeZone() {
		return timezone;
	}
	
	@Override
	public void setTimeZone(String timezone) {
		this.timezone = timezone;
	}

}




