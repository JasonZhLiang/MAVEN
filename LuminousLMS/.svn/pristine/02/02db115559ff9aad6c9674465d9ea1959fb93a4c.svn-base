package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.OrgsContentItemsModel;

/**
 * Entity that implements the orgsvideos model
 * for the orgs_videos lookup table
 * @author Tammy
 * Copyright (c) 2015 - 2017 Lingua Classica
 */

@Entity
@Table(name="orgs_videos")
public class EntityOrgsContentItemsModel implements OrgsContentItemsModel{

	private static final long serialVersionUID = -8936026231938744660L;

	@Id
	@GeneratedValue()
	private Integer id;

	private Integer orgid;
	private Integer videoid;
	
	
	public EntityOrgsContentItemsModel()
	{	
	}

	public EntityOrgsContentItemsModel(EntityOrgModel orgs, EntityVideoModel videos)
	{
		this.orgid = orgs.getId();
		this.videoid = videos.getContentId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public Integer getContentItemId() {
		return videoid;
	}

	public void setContentItemId(Integer videoid) {
		this.videoid = videoid;
	}
}
