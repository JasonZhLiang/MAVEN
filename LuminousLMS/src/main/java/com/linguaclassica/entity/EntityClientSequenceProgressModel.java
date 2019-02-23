package com.linguaclassica.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.linguaclassica.model.ClientSequenceProgressModel;

/**
 * Entity that implements the ClientSequenceProgressModel
 * @author Liying Liu
 */

@Entity
@Table(name="client_sequence_progress")
public class EntityClientSequenceProgressModel implements ClientSequenceProgressModel
{

	private static final long serialVersionUID = -1739953961380857271L;

	@Id
	@GeneratedValue()
	private Integer id;

	private Integer clientAssignmentId;
	private Integer sequenceId;
	private Integer results;
	private Boolean completed;

	@OneToMany
	@JoinColumn(name = "clientassignmentid")
	private List<EntityClientSequenceUsageModel> assignmentSequenceUsageRecords;

	public EntityClientSequenceProgressModel()
	{
		clientAssignmentId = 0;
		sequenceId = 0;
		results = 0;
		completed = false;
	}

	public EntityClientSequenceProgressModel(Integer clientAssignId, Integer seqId, Integer res, Boolean comp)
	{
		clientAssignmentId = clientAssignId;
		sequenceId = seqId;
		results = res;
		completed = comp;
	}

	@Override
	public Integer getId()
	{
		return id;
	}

	@Override
	public Integer getClientAssignmentId() {
		return clientAssignmentId;
	}

	@Override
	public void setClientAssignmentId(Integer clientAssignId) {
		clientAssignmentId = clientAssignId;
	}

	@Override
	public Integer getSequenceId() {
		return sequenceId;
	}

	@Override
	public void setSequenceId(Integer seqId) {
		sequenceId = seqId;
	}

	@Override
	public Integer getResults() {
		return results;
	}

	@Override
	public void setResults(Integer results) {
		this.results = results;
	}

	@Override
	public Boolean getCompleted() {
		return completed;
	}

	@Override
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
	@Override
	public List<EntityClientSequenceUsageModel> getClientSequenceUsageRecords() {
		return assignmentSequenceUsageRecords;
	}
	
	@Override
	public void addClientSequenceUsageRecord(EntityClientSequenceUsageModel usageRecord) {
		this.assignmentSequenceUsageRecords.add(usageRecord);
	}

}
