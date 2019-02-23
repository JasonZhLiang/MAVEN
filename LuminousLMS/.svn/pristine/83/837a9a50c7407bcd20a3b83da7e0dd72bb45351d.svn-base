package com.linguaclassica.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.linguaclassica.model.ClientsAssignmentsModel;

/**
 * Entity that implements the ConsultantClientsModel
 * @author Rob Hilchie
 */

@Entity
@Table(name="clients_assignments")
public class EntityClientsAssignmentsModel implements ClientsAssignmentsModel
{
	private static final long serialVersionUID = -1211589196566978096L;

	@Id
	@GeneratedValue()
	private Integer id;

	private Integer clientid;
	private Integer assignmentid;
	private Date duedate;
	
	@ManyToOne
	@JoinColumn(name = "clientid", referencedColumnName = "id", insertable = false, updatable = false)
	private EntityUserModel client;
	
	@ManyToOne
	@JoinColumn(name = "assignmentid", referencedColumnName = "id", insertable = false, updatable = false)
	private EntityAssignmentModel assignment;
	
	@OneToMany
	@JoinColumn(name = "clientassignmentid")
	private List<EntityClientSequenceProgressModel> assignmentSequenceProgressRecords;

	public EntityClientsAssignmentsModel()
	{
		clientid = 0;
		assignmentid = 0;
		duedate=null;
	}

	public EntityClientsAssignmentsModel(Integer clientId, Integer assignmentId, Date dueDate)
	{
		assignmentid = assignmentId;
		clientid = clientId;
		duedate = dueDate;
	}

	@Override
	public Integer getId()
	{
		return id;
	}

	@Override
	public void setClientId(Integer clientId)
	{
		clientid = clientId;
	}

	@Override
	public Integer getClientId()
	{
		return clientid;
	}

	@Override
	public Integer getAssignmentId()
	{
		return assignmentid;
	}

	@Override
	public void setAssignmentId(Integer assignmentId)
	{
		assignmentid = assignmentId;
	}
	
	@Override
	public EntityUserModel getClient() {
		return client;
	}
	
	@Override
	public void setClient(EntityUserModel client) {
		this.client = client;
	}

	@Override
	public EntityAssignmentModel getAssignment() {
		return assignment;
	}

	@Override
	public void setAssignment(EntityAssignmentModel assignment) {
		this.assignment = assignment;
	}

	@Override
	public Date getDuedate() {
		return duedate;
	}

	@Override
	public void setDuedate(Date dueDate) {
		this.duedate = dueDate;
	}

	@Override
	public List<EntityClientSequenceProgressModel> getListOfAssignmentSequenceProgress() {
		return assignmentSequenceProgressRecords;
	}

	@Override
	public void setListOfAssignmentSequenceProgress(
			List<EntityClientSequenceProgressModel> assignmentSequenceProgressList) {
		this.assignmentSequenceProgressRecords = assignmentSequenceProgressList;
	}
}
