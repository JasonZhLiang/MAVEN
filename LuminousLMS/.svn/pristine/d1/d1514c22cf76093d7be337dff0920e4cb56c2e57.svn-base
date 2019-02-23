package com.linguaclassica.service;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.mail.MessagingException;

import org.apache.commons.mail.EmailException;
import org.apache.wicket.Session;
import org.springframework.stereotype.Service;

import com.linguaclassica.access.LoginHelper;
import com.linguaclassica.access.SendEmail;
import com.linguaclassica.entity.EntityUserModel;

@Service
public class PasswordGenerateService {
	
	EntityUserModel userModel;
	
		public String generatePassword() {
			RandomPassword random = new RandomPassword(8);
			String passwordran = random.nextString();
			return passwordran;
		}
			
		public String replacePassword(EntityUserModel userModel) {
			System.out.println("PasswordGenerateService, line 19");
			
			//generate random password
			String password = "";
			RandomPassword random = new RandomPassword(8);
			String passwordran = random.nextString();
			System.out.println("PasswordGenerateService, line 28:  passwordran = " + passwordran);
			
			//send password in email to user
			SendEmail sendmail = new SendEmail();
			try {
				sendmail.send(userModel.getEmailAddress(), userModel.getFirstName(), userModel.getLastName(),
						"To log in initially to Adjuveris, please use this email address and this password:  " +
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
		
		public class RandomPassword {
			
			private char[] getString() {

			  final char[] symbols;

			    StringBuilder tmp = new StringBuilder();
			    for (char chnum = '0'; chnum <= '9'; ++chnum)
			      tmp.append(chnum);
			    for (char ch = 'a'; ch <= 'z'; ++ch)
			      tmp.append(ch);
			    symbols = tmp.toString().toCharArray();
			    return symbols;
			}
			private final Random random = new Random();

			private final char[] buf;

			public RandomPassword(int length) {
			    if (length < 1)
			      throw new IllegalArgumentException("length < 1: " + length);
			    buf = new char[length];
			  }

			public String nextString() {
				char[] symbols = getString();
			    for (int idx = 0; idx < buf.length; ++idx) 
			      buf[idx] = symbols[random.nextInt(symbols.length)];
			    return new String(buf);
			  }
			}
}
