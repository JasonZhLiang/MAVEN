package com.linguaclassica.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.entity.EntityAssignmentSequencesModel;
import com.linguaclassica.entity.EntityContentItemsModel;
import com.linguaclassica.entity.EntityUserModel;

public class TreeData implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static int nextNodeId = 1;

	private Integer nodeId;
	private String id;

	public enum MsgStatus {FROM, TO, NEWFROM, CONTACT};

	private String fromto;
	private String date;
	private String message;
	private MsgStatus msgStatus;
	
	private EntityUserModel user = null;
	private EntityAssignmentModel assignment = null;
	private EntityAssignmentSequencesModel assignmentSegment = null;
	private EntityContentItemsModel content = null;
	private Integer sequenceNum;
	private TreeData parent;
	private List<TreeData> branches = new ArrayList<>();

	public TreeData(String id)
	{
		setNodeId();
		this.id = id;
		user = null;
		assignment = null;
	}

	public TreeData(TreeData parent, String name)
	{
		this(name);

		this.parent = parent;
		this.parent.branches.add(this);
	}

	public TreeData(String id, EntityUserModel user)
	{
		setNodeId();
		this.id = id;
		this.user = user;
	}

	public TreeData(String id, EntityAssignmentModel assignment)
	{
		setNodeId();
		this.id = id;
		this.assignment = assignment;
	}

	public TreeData(String id, EntityAssignmentSequencesModel assignmentSegment)
	{
		setNodeId();
		this.id = id;
		this.assignmentSegment = assignmentSegment;
	}

	public TreeData(TreeData parent, String name, EntityUserModel user)
	{
		this(name, user);

		this.parent = parent;
		this.parent.branches.add(this);
	}

	public TreeData(TreeData parent, String id, EntityContentItemsModel content, int sequenceNum)
	{
		setNodeId();
		this.id = id;
		this.content = content;
		this.sequenceNum = sequenceNum;
		setParent(parent);
	}

	public TreeData(TreeData parent, String name, EntityAssignmentModel assignment)
	{
		this(name, assignment);

		this.parent = parent;
		this.parent.branches.add(this);
	}

	public TreeData(TreeData parent, String name, EntityAssignmentSequencesModel assignmentSegment)
	{
		this(name, assignmentSegment);

		this.parent = parent;
		this.parent.branches.add(this);
	}

	/* Make sure this is always called in the constructors */
	protected void setNodeId()
	{
		nodeId = nextNodeId++;
	}
	
	public int getNodeId()
	{
		return nodeId;
	}

	public TreeData getParent()
	{
		return parent;
	}


	public void setParent(TreeData parent)
	{
		this.parent = parent;
		this.parent.branches.add(this);
	}

	public EntityUserModel getUser()
	{
		return user;
	}

	public EntityAssignmentModel getAssignment()
	{
		return assignment;
	}

	public EntityAssignmentSequencesModel getAssignmentSegment()
	{
		return assignmentSegment;
	}

	public EntityContentItemsModel getContent()
	{
		return content;
	}

	public int getSequenceNum()
	{
		return sequenceNum;
	}

	public void setFromTo(String ft)
	{
		fromto = ft;
	}

	public String getFromTo()
	{
		return fromto;
	}
	
	public void setDate(String dt)
	{
		date = dt;
	}

	public String getDate()
	{
		return date;
	}
	
	public void setMessage(String msg)
	{
		message = msg;
	}

	public String getMessage()
	{
		return message;
	}
	
	public void setMsgStatus(MsgStatus msgStat)
	{
		msgStatus = msgStat;
	}
	
	public MsgStatus getMsgStatus()
	{
		return msgStatus;
	}
	
	public String getId()
	{
		return id;
	}

	public List<TreeData> getBranches()
	{
		return Collections.unmodifiableList(branches);
	}

	@Override
	public String toString()
	{
		return id;
	}
}
