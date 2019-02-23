package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.MessageModel;

@Entity
@Table(name="message")
public class EntityMessageModel implements MessageModel {

	private static final long serialVersionUID = 6331128951228340173L;

	@Id
	@GeneratedValue()
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private Status status;
		
	private String message;
		
	public EntityMessageModel() {
		}


	/**
	 * Normal constructor used to create a group
	 * @param status
	 */
	public EntityMessageModel(Status status, String message) {
			this.status = status;
			this.message = message;
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
		public String getMessage() {
			return message;
		}

		@Override
		public void setMessage(String message) {
			this.message = message;
		}
	}
