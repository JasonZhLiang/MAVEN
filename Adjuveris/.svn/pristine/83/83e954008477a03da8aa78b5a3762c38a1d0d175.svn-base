package com.linguaclassica.access;

import java.io.Serializable;

import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.UserService;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

/**
 * A data object for holding information on users employed both by other
 * classes and by the ParserSession session object.
 * @author Eugene Price
 * @Copyright("2012 Lingua Classica")
 */
public class User implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private UserService userService;
	
	Integer userId = 0;
	String password = "";
	String newPassword = "";
	String fname = "";
	String lname = "";
	String email = "";
	String studentcode = "";
	Integer groupId = 0;
	java.sql.Date expires;
	
	public User(UserService userService) {
		this.userService = userService;
	}
	
	public User(Integer userIdIn, UserService userService) {
		System.out.println("GroupUser line 20:  userIdIn = " + userIdIn);
		this.userService = userService;
		
		UserModel userModel;
		try {
			userModel = userService.findUserById(userIdIn);
		} catch (UnknownEntityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		userId = userIdIn;
		fname = userModel.getFirstName();
		lname = userModel.getLastName();
		email = userModel.getEmailAddress();
		studentcode = userModel.getStudentnumber();
		password = userModel.getPassword();
	}
	
	public Integer getUserId() {
		return userId ;
		}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password ;
		}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getFname() {
		return fname ;
		}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname ;
		}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getEmail() {
		return email ;
		}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getStudentcode() {
		return studentcode ;
		}

	public void setStudentcode(String studentcode) {
		this.studentcode = studentcode;
	}
	
	public Integer getGroupId() {
		return groupId ;
		}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	public java.sql.Date getExpires() {
		return expires ;
		}

	public void setExpires(java.sql.Date expires) {
		this.expires = expires;
	}
}
