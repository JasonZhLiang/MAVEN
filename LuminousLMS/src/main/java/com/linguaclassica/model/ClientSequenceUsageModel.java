package com.linguaclassica.model;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Business layer interface for ClientSequenceUsageModel corresponding 
 * to the client_sequence_usage table.
 * @author
 */
public interface ClientSequenceUsageModel extends Serializable
{
	/**
	 * Gets the id
	 * @return
	 */
	public Integer getId();
	
	/**
	 * Set the start time
	 */
	public void setStartTime(Timestamp start);
	
	/**
	 * Gets the start time
	 */
	public Timestamp getStartTime();
	
	/**
	 * Set the stop time
	 */
	public void setStopTime(Timestamp stop);
	
	/**
	 * Gets the stop time
	 * @return
	 */
	public Timestamp getStopTime();
	
	/**
	 * Get the duration of the sequence
	 * @return
	 */
	public Integer getDuration();
	
	/**
	 * Gets the client sequence id
	 * @return
	 */
	public Integer getClientSequenceUsageId();

	/**
	 * Sets the sequence id
	 * @param clientsequence id
	 */
	public void setClientSequenceUsageId(Integer clientSequsagId);
}
