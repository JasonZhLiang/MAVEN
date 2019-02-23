package com.linguaclassica.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.MessageModel;

@Entity
@Table(name="messages")
public class EntityMessageModel implements MessageModel {

	private static final long serialVersionUID = 6331128951228340173L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Integer fromid;
	private Integer toid;
	private Integer orgid;
	
	private String subject;
	private String message;
		
	private Timestamp createdate;
	private String timezone;

	public EntityMessageModel() {
	}


	/**
	 * Normal constructor used to create a group
	 * @param status
	 */
	public EntityMessageModel(Status status, Integer fromid, Integer toid, Integer orgid, String subject, String message,
			Timestamp createdate, String timezone) {
			this.status = status;
			this.fromid = fromid;
			this.toid = toid;
			this.orgid = orgid;
			this.subject = subject;
			this.message = message;
			this.createdate = createdate;
			this.timezone = timezone;
		}

		@Override
		public Integer getId() {
		return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}	 

		@Override
		public Status getStatus() {
			return status;
		}

		@Override
		public void setStatus(Status status) {
			this.status = status;
		}

		@Override
		public Integer getFromId()
		{
			return fromid;
		}
		
		@Override
		public void setFromId(Integer fromId)
		{
			this.fromid = fromId;
		}
		
		@Override
		public Integer getToId()
		{
			return toid;
		}
		
		@Override
		public void setToId(Integer toId)
		{
			this.toid = toId;
		}
		
		@Override
		public Integer getOrgId()
		{
			return orgid;
		}
		
		@Override
		public void setOrgId(Integer orgId)
		{
			this.orgid = orgId;
		}
		
		@Override
		public String getSubject() {
			return subject;
		}

		@Override
		public void setSubject(String subject) {
			this.subject = subject;
		}

		@Override
		public String getMessage() {
			return message;
		}

		@Override
		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public Timestamp getCreateDate() {
			return createdate;
		}

		@Override
		public void setCreateDate(Timestamp createdate) {
			this.createdate = createdate;
		}

		@Override
		public String getTimeZone() {
			return timezone;
		}
		
		@Override
		public void setTimeZone(String timezone) {
			this.timezone = timezone;
		}
	}
