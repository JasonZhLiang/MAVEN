package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.ClientSequenceUsageModel;
import java.sql.Timestamp;

/**
 * Entity that implements the ConsultantClientsModel
 * @author 
 */

@Entity
@Table(name="client_sequence_usage")
public class EntityClientSequenceUsageModel implements ClientSequenceUsageModel
{
	private static final long serialVersionUID = -1211589196566978096L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	private Integer clientsequenceid;
	private Timestamp starttime;
	private Timestamp stoptime;
	private Integer durationinseconds;

	public EntityClientSequenceUsageModel() {
		clientsequenceid = 0;
		starttime = null; 
		stoptime =  null; 
		durationinseconds = 0;
	}

	public EntityClientSequenceUsageModel(Integer clientSequId, Integer durationInSec, Timestamp start, Timestamp stop) {
		clientsequenceid = clientSequId;
		durationinseconds = durationInSec;
		starttime = start;
		stoptime = stop;
	}

	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public Timestamp getStopTime() {
		return stoptime;
	}
	
	@Override
	public void setStopTime(Timestamp stop) {
		stoptime = stop;
	}
	
	@Override
	public Timestamp getStartTime() {
		return starttime;
	}
	
	@Override
	public void setStartTime(Timestamp start) {
		starttime = start;
	}
	
	@Override
	public Integer getDuration() {
		return durationinseconds;
	}
	
	@Override
	public Integer getClientSequenceUsageId() {
		return clientsequenceid;
	}

	@Override
	public void setClientSequenceUsageId(Integer clientSeqUsgId) {
		clientsequenceid = clientSeqUsgId;
	}

	public void setDuration(int endTimestamp) {
		durationinseconds = endTimestamp;
	}
}
