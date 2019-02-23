package com.linguaclassica.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.ConsultantClientsModel;

/**
 * Entity that implements the ConsultantClientsModel
 * @author Rob Hilchie
 */

@Entity
@Table(name="consultant_clients")
public class EntityConsultantClientsModel implements ConsultantClientsModel
{
	private static final long serialVersionUID = -1424589196566973426L;

	@Id
	@GeneratedValue()
	private Integer id;

	private Integer orgid;
	private Integer consultantid;
	private Integer clientid;
	private Timestamp createdtime;
	private Timestamp closedtime;

	public EntityConsultantClientsModel()
	{
		orgid = 0;
		consultantid = 0;
		clientid = 0;
	}

	public EntityConsultantClientsModel(Integer organizationId, Integer consultantId, Integer clientId)
	{
		orgid = organizationId;
		consultantid = consultantId;
		clientid = clientId;
	}

	@Override
	public Integer getId()
	{
		return id;
	}

	@Override
	public Integer getOrganizationId()
	{
		return orgid;
	}

	@Override
	public void setOrganizationId(Integer organizationId)
	{
		orgid = organizationId;
	}

	@Override
	public Integer getConsultantId()
	{
		return consultantid;
	}

	@Override
	public void setConsultantId(Integer consultantId)
	{
		consultantid = consultantId;
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

	public Timestamp getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Timestamp createdtime) {
		this.createdtime = createdtime;
	}
	@Override
	public Timestamp getClosedtime() {
		return closedtime;
	}
	@Override
	public void setClosedtime(Timestamp closedtime) {
		this.closedtime = closedtime;
	}
}
