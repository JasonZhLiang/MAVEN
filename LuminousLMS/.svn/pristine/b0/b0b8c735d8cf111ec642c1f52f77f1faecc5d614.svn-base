package com.linguaclassica.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	private String password;
	
	private String question;
	private String answer;
	
	private Timestamp accountcreatedtime;
	private Timestamp accountclosedtime;
	private Integer status;

	public EntityUserModel(Integer id, String firstName, String lastName, String emailAddress, String password, Integer status) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.status = status;
		
	}
	

	public EntityUserModel()
	{
		id = 0;
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
	public Integer getStatus() {
		return status;
	}

	@Override
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public Timestamp getAccountclosedtime() {
		return accountclosedtime;
	}

	@Override
	public void setAccountclosedtime(Timestamp accountclosedtime) {
		this.accountclosedtime = accountclosedtime;
	}


	@Override
	public void setQuestion(String question) {
		this.question = question;
	}


	@Override
	public String getQuestion() {
		// TODO Auto-generated method stub
		return question;
	}


	@Override
	public void setAnswer(String answer) {
		this.answer = answer;
	}


	@Override
	public String getAnswer() {
		return answer;
	}


	@Override
	public boolean QAMatches(String answer) {
		if(answer == null ||this.answer == null ||
				answer.isEmpty() || this.answer.isEmpty()){
			return false;
		}
			
		return (this.answer.equals(answer));
	}

}
