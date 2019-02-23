package com.linguaclassica.model;

import java.io.Serializable;

/**
 * Business layer interface for AssignmentSequencesModel corresponding 
 * to the assignment_sequences table.
 * @author Rob Hilchie
 */
public interface AssignmentSequencesModel extends Serializable
{
	/**
	 * Gets the id
	 * @return
	 */
	public Integer getId();

	/**
	 * Gets the assignment id
	 * @return
	 */
	public Integer getAssignmentId();

	/**
	 * Sets the assignment id
	 * @param assignmentId
	 */
	public void setAssignmentId(Integer assignmentId);
	
	/**
	 * Gets the content id
	 * @return
	 */
	public Integer getContentId();

	/**
	 * Sets the content id
	 * @param contentId
	 */
	public void setContentId(Integer contentId);

	/**
	 * Get the sequence number
	 * @return
	 */
	public Integer getSequenceNum();

	/**
	 * Set the sequence number
	 * @param sequenceNum
	 */
	public void setSequenceNum(Integer sequenceNum);
}
