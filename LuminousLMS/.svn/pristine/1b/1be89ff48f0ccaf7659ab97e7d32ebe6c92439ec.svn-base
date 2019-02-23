package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.ContentItemsModel;

@Entity
@Table(name="content_items")
public class EntityContentItemsModel implements ContentItemsModel {

	private static final long serialVersionUID = 6337718473244340173L;

	@Id
	@GeneratedValue()
	private Integer id;
		
	private String name;
	private String description;
	
	@Enumerated(EnumType.ORDINAL)
	private ContentTypes typeid;
	
	public EntityContentItemsModel() {
	}

	/**
	 * Normal constructor used to create a content item
	 */
	public EntityContentItemsModel(String name, String description, ContentTypes typeid) {
			this.name = name;
			this.description = description;
			this.typeid = typeid;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public void setTypeId(ContentTypes typeid){
		this.typeid = typeid;
	}
	
	@Override
	public ContentTypes getTypeId() {
		return typeid;
	}
}
