package com.linguaclassica.entity;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.SessionModel;

@Entity
@Table(name="session")
public class EntitySessionModel implements SessionModel {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue()	
	private Integer id;
	
	private String sessionName;
	private String createtime;
	private String lasttime;
	private int userId;
	private ArrayList<String> classList;
	
	EntitySessionModel() {
		
	}
	
	EntitySessionModel(Integer id, String sessionName, String createtime, String lasttime, int duration, 
			int userId,ArrayList<String> classList) {
		
		this.id = id;
		this.sessionName = sessionName;
		this.createtime = createtime;
		this.lasttime = lasttime;
		this.userId = userId;
		this.classList = classList;
	}

	@Override
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer Id) {
		this.id=Id;
	}

	@Override
	public String getSessionName() {
		return sessionName;
	}

	@Override
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
		
	}

	@Override
	public String getCreatetime() {
		return createtime;
	}

	@Override
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
		
	}

	@Override
	public String getLasttime() {
		return lasttime;
	}

	@Override
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
		
	}

	@Override
	public int getUserId() {
		return userId;
	}

	@Override
	public void setUserId(int userId) {
		this.userId = userId;		
	}

	@Override
	public ArrayList<String> getClassList() {
		return classList;
	}

	@Override
	public void setClassList(ArrayList<String> classList) {
		this.classList = classList;		
	}

}
