package com.linguaclassica.instadmin;

import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;

import org.apache.commons.mail.EmailException;
import org.apache.wicket.Session;

import com.linguaclassica.access.LoginHelper;
import com.linguaclassica.access.SendEmail;
import com.linguaclassica.entity.EntityUserModel;

public class PasswordGenerate {
	
	EntityUserModel userModel;
			
		public String getPassword(EntityUserModel userModel) {
			System.out.println("PasswordGenerate, line 19");
			//generate random password
			String password = "";
			RandomPassword random = new RandomPassword(8);
			String passwordran = random.nextString();
			System.out.format("PasswordGenerate(user %d), passwordran = %s%n", userModel.getId(), passwordran);
			
			//send password in email to user
			SendEmail sendmail = new SendEmail();
			try {
				sendmail.send(userModel.getEmailAddress(), userModel.getFirstName(), userModel.getLastName(),
						"To log in initially to AlpheiosPlus, please use this email address and this password:  " +
								passwordran);
				System.out.println("PasswordGenerate, line 32:  password email sent, passwordran = " + passwordran);
			}
			catch(MessagingException mex) {
				mex.printStackTrace();	
				Session.get().error("Your message could not be sent.");
			}
			catch(EmailException ex) {
				ex.printStackTrace();	
				Session.get().error("Your message could not be sent.");				
			}
			//encrypt password
			LoginHelper loginHelper = new LoginHelper();			
			try {
				password = LoginHelper.encodePassword(passwordran);				
			}
			catch (NoSuchAlgorithmException e)
			{
				Session.get().error("The password for new user " + userModel.getFirstName() + " " + 
						userModel.getLastName() + " could not be encrypted.  Please contact your " +
						"system administrator.");
				e.printStackTrace();				
			}
			return password;
		}
}
