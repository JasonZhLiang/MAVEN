package com.linguaclassica.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.entity.EntityClientSequenceProgressModel;
import com.linguaclassica.entity.EntityUserModel;

/**
 * Business layer interface for ConsultantClientsModel corresponding 
 * to the consultant_clients table.
 * @author Rob Hilchie
 */
public interface ClientsAssignmentsModel extends Serializable
{
	/**
	 * Gets the id
	 * @return
	 */
	public Integer getId();

	/**
	 * Set the client id
	 * @param clientId
	 */
	public void setClientId(Integer clientId);
	
	/**
	 * Get the client id
	 * @return
	 */
	public Integer getClientId();

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
	 * Gets the assignment due date
	 * @return
	 */
	public Date getDuedate();

	/**
	 * Sets the assignment due date
	 * @param dueDate
	 */
	public void setDuedate(Date dueDate);

	/**
	 * Gets the associated client for this assignment
	 * @return
	 */
	public EntityUserModel getClient();
	
	/**
	 * Sets the associated client for this assignment
	 * @param client
	 */
	public void setClient(EntityUserModel client);
	
	/**
	 * Gets the client's assignment
	 * @return assignment
	 */
	public EntityAssignmentModel getAssignment();
	
	/**
	 * Sets the client's assignment
	 * @param assignment
	 */
	public void setAssignment(EntityAssignmentModel assignment);
	
	/**
	 * Gets the progress records for each segment of this assignment
	 * @return assignmentSegmentProgressList
	 */
	public List<EntityClientSequenceProgressModel> getListOfAssignmentSequenceProgress();
	
	/**
	 * Sets the progress records for each segment of this assignment
	 * NOTE: THIS IS TEMPORARY/DEPRECATED
	 * @param assignmentSequenceProgressList
	 */
	public void setListOfAssignmentSequenceProgress(List<EntityClientSequenceProgressModel> assignmentSequenceProgressList);
}
