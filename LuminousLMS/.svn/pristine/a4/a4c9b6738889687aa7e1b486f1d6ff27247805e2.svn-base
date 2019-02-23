package com.linguaclassica.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.linguaclassica.model.AssignmentModel;

@Entity
@Table(name="assignments")
public class EntityAssignmentModel implements AssignmentModel {

	private static final long serialVersionUID = 6331128474718340173L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private Integer orgid;
	private String name;
	private String description;
	private Boolean assisted;
	
	@OneToMany(mappedBy = "assignmentid")
	private List<EntityAssignmentSequencesModel> assignmentSegments;
	
	public EntityAssignmentModel() {
	}

	/**
	 * Normal constructor used to create an assignment
	 */
	public EntityAssignmentModel(String name) {
			this.name = name;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setAssisted(Boolean assisted){
		this.assisted = assisted;
	}
	
	@Override
	public Boolean getAssisted() {
		return assisted;
	}

	@Override
	public void setOrgId(Integer orgId) {
		orgid = orgId;
		
	}

	@Override
	public Integer getOrgId() {
		return orgid;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
		
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public void setAssignmentSegments(List<EntityAssignmentSequencesModel> assignmentSegments) {
		this.assignmentSegments = assignmentSegments;
	}
	
	@Override
	public List<EntityAssignmentSequencesModel> getAssignmentSegments() {
		return assignmentSegments;
	}
}
