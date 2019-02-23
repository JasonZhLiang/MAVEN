package com.linguaclassica.model;

import java.io.Serializable;
import java.util.List;

import com.linguaclassica.entity.EntityClientSequenceUsageModel;

/**
 * Business layer interface for ClientSequenceProgressModel corresponding 
 * to the client_sequence_progress table.
 * @author Liying Liu
 */
public interface ClientSequenceProgressModel extends Serializable {
	/**
	 * Gets the id
	 * @return
	 */
	public Integer getId();

	/**
	 * Gets the client assignment id
	 * @return
	 */
	public Integer getClientAssignmentId();

	/**
	 * Sets the client assignment id
	 * @param clientAssignmentId
	 */
	public void setClientAssignmentId(Integer clientAssignmentId);
	
	/**
	 * Get the sequence id
	 * @return
	 */
	public Integer getSequenceId();

	/**
	 * Set the sequence id
	 * @param sequenceId
	 */
	public void setSequenceId(Integer sequenceId);
	
	/**
	 * Get the results
	 * @return
	 */
	public Integer getResults();


	/**
	 * Set the results
	 * @param results
	 */
	public void setResults(Integer results);

	/**
	 * Gets the completed status
	 * @return
	 */
	public Boolean getCompleted();

	/**
	 * Sets the completed status
	 * @param completed
	 */
	public void setCompleted(Boolean completed);

	/**
	 * Gets the usage records for a client's assignment
	 * @return assignmentSequenceUsageRecords
	 */
	public List<EntityClientSequenceUsageModel> getClientSequenceUsageRecords();

	/**
	 * Adds a new usage record for a client's assignment
	 * @param usageRecord
	 */
	public void addClientSequenceUsageRecord(EntityClientSequenceUsageModel usageRecord);
}
