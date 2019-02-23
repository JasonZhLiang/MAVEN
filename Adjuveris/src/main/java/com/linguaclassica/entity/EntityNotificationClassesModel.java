package com.linguaclassica.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.NotificationClassesModel;

/**
 * Entity implementation of the NotificationModel interface
 * @author Tammy
 */
@Entity
@Table(name="notification_classes")
public class EntityNotificationClassesModel implements NotificationClassesModel
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue()
	private Integer id;
	private Integer notificationid;
	private Integer classid;
	
	public EntityNotificationClassesModel() {
	}

	public EntityNotificationClassesModel(Integer id, 
			Integer notificationid, 
			Integer classid) 
 {
		this.id = id;
		this.notificationid = notificationid;
		this.classid = classid;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Override
	public Integer getnotificationId() {
		return notificationid;
	}

	@Override
	public void setnotificationId(Integer notificationid) {
		this.notificationid = notificationid;
	}

	@Override
	public Integer getclassId() {
		return classid;
	}

	@Override
	public void setclassId(Integer classid) {
		this.classid = classid;
	}

}
