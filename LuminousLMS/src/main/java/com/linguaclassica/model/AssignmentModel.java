package com.linguaclassica.model;

import java.io.Serializable;
import java.util.List;
import com.linguaclassica.entity.EntityAssignmentSequencesModel;

public interface AssignmentModel extends Serializable {
	
	/**
	 * Returns the unique Id field for this assignment
	 * @return id
	 */
	public Integer getId();
	
	/**
	 * Sets the orgid of this model
	 * @param orgid
	 */
	public void setOrgId(Integer orgId);

	/**
	 * Returns the orgid field for this model
	 * @return orgid
	 */
	public Integer getOrgId();
	
	/**
	 * Sets the name of the assignment
	 * @param name
	 */
	public void setName(String name);

	
	/**
	 * Returns the name
	 * @return name
	 */
	public String getName();
	
	/**
	 * Sets the description of the assignment
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * Returns the description of the assignment
	 * @return description
	 */
	public String getDescription();

	/**
	 * Sets the assisted flag
	 * @param assisted
	 */
	public void setAssisted(Boolean assisted);

	/**
	 * Returns the assisted flag
	 * @return assisted
	 */
	public Boolean getAssisted();

	/**
	 * Sets the assignment segments belonging to this assignment
	 * @param assignmentSegments
	 */
	public void setAssignmentSegments(List<EntityAssignmentSequencesModel> assignmentSegments);

	/**
	 * Gets the assignment segments for this assignment
	 * @return assignmentSegments
	 */
	public List<EntityAssignmentSequencesModel> getAssignmentSegments();
}

