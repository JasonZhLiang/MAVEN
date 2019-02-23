package com.linguaclassica.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.NotificationModel;

/**
 * Entity implementation of the NotificationModel interface
 * @author Tammy
 */
@Entity
@Table(name="notifications")
public class EntityNotificationModel implements NotificationModel
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue()
	private Integer id;
	private Integer orgid;
	private Integer createdbyid;
	private String subject;
	private String note;
	private Timestamp startdate;
	private Timestamp expirydate;
	private String timezone;
	private Integer type;
	
	public EntityNotificationModel() {
	}

	public EntityNotificationModel(Integer id, 
			Integer organizationId, 
			Integer createdbyid, 
			String subject,
			String note, 
			Timestamp startdate, 
			Timestamp expirydate,
			String timezone,
			Integer type) {
		this.id = id;
		orgid = organizationId;
		this.createdbyid = createdbyid;
		this.subject = subject;
		this.note = note;
		this.startdate = startdate;
		this.expirydate = expirydate;
		this.timezone = timezone;
		this.type = type;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public Integer getOrganizationId()
	{
		return orgid;
	}

	@Override
	public void setOrganizationId(Integer orgId)
	{
		orgid = orgId;
	}
	
	@Override
	public Integer getCreatedById() {
		return createdbyid;
	}

	@Override
	public void setCreatedById(Integer createdbyid) {
		this.createdbyid = createdbyid;
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
	public String getNote() {
		return note;
	}

	@Override
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public Timestamp getStartDate() {
		return startdate;
	}

	@Override
	public void setStartDate(Timestamp startdate) {
		this.startdate = startdate;
	}

	@Override
	public Timestamp getExpiryDate() {
		return expirydate;
	}

	@Override
	public void setExpiryDate(Timestamp expirydate) {
		this.expirydate = expirydate;
	}
	
	@Override
	public String getTimeZone() {
		return timezone;
	}
	
	@Override
	public void setTimeZone(String timezone) {
		this.timezone = timezone;
	}
	
	@Override
	public Integer getType()
	{
		return type;
	}

	@Override
	public void setType(Integer type)
	{
		this.type = type;
	}
	
}
