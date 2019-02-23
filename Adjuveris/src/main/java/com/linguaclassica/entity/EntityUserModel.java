package com.linguaclassica.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linguaclassica.model.UserModel;

@Entity
@Table(name="users")
public class EntityUserModel implements UserModel {

	private static final long serialVersionUID = 5529447539783591321L;

	@Id
	@GeneratedValue()
	private Integer id;	

	private String firstName;

	private String lastName;

	@Column(unique=true)
	private String emailAddress;
	
	private String studentnumber;

	private String password;
	
	private Timestamp accountcreatedtime;
	
	@Enumerated(EnumType.STRING)
	private SubsStatusType status;

	public EntityUserModel() {
		id = 0;
		firstName = "";
		lastName = "";
		emailAddress = "";
		studentnumber = "";
		password = "";
		status = SubsStatusType.ACTIVE;
	}

	public EntityUserModel(Integer id, String firstName, String lastName, String emailAddress, String studentnumber, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.studentnumber = studentnumber;
		this.password = password;
		this.status = SubsStatusType.ACTIVE;
		// use setter when update to the 'expires' field is needed
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getEmailAddress() {
		return emailAddress;
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String getStudentnumber() {
		return studentnumber;
	}

	@Override
	public void setStudentnumber(String studentnumber) {
		this.studentnumber = studentnumber;
	}
	

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean passwordMatches(String password) {
		return this.password.equals(password);
	}
	
	@Override
	public void setAccountCreatedTime(Timestamp accountCreatedTime) {
		this.accountcreatedtime = accountCreatedTime;
	}

	@Override
	public Timestamp getAccountCreatedTime() {
		return accountcreatedtime;
	}
	
	@Override
	public void setStatus(SubsStatusType type){
		this.status = type;
	}
	
	@Override
	public SubsStatusType getStatus(){
		return status;
	}

}
