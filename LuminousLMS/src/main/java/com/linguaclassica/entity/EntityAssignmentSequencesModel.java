package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.linguaclassica.model.AssignmentSequencesModel;

/**
 * Entity that implements the AssignmentSequencesModel
 * @author Rob Hilchie
 */

@Entity
@Table(name="assignment_sequences")
public class EntityAssignmentSequencesModel implements AssignmentSequencesModel
{
	private static final long serialVersionUID = -1213999496566926896L;

	@Id
	@GeneratedValue()
	private Integer Id;

	private Integer assignmentid;
	private Integer contentid;
	private Integer sequencenum;
	
	@ManyToOne
	@JoinColumn(name = "assignmentid", referencedColumnName = "id", insertable = false, updatable = false)
	private EntityAssignmentModel assignment;
	
	@ManyToOne
	@JoinColumn(name = "contentid", referencedColumnName = "id", insertable = false, updatable = false)
	private EntityContentItemsModel contentItem;

	public EntityAssignmentSequencesModel()
	{
		assignmentid = 0;
		contentid = 0;
		sequencenum = 0;
	}

	public EntityAssignmentSequencesModel(Integer assignmentId, Integer contentId, Integer sequenceNum)
	{
		assignmentid = assignmentId;
		contentid = contentId;
		sequencenum = sequenceNum;
	}

	@Override
	public Integer getId()
	{
		return Id;
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
	public Integer getContentId()
	{
		return contentid;
	}

	@Override
	public void setContentId(Integer contentId)
	{
		contentid = contentId;
	}

	@Override
	public Integer getSequenceNum()
	{
		return sequencenum;
	}
	
	@Override
	public void setSequenceNum(Integer sequenceNum)
	{
		sequencenum = sequenceNum;
	}
}
