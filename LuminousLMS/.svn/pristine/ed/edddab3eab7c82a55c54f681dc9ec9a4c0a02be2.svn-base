package com.linguaclassica.access;

import javax.mail.*;

import org.apache.commons.mail.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SendEmail
{
	private Logger logger = LogManager.getLogger(getClass());
	
	private String sHost;
    private int iPort;
    private String sFrom;
    private String sPassword;
    private String sSubject;
    private String sMessagetext;
    String port = "25";
    private String sSendTo;

	public SendEmail()
	{
		logger.debug("()");
	}
	
	public void sendPassword(String to, String passwrd)
	{
		
		sSendTo = to;
		
		sHost = "smtpout.secureserver.net"; //"mail.latinparsercloud.com";
		iPort = 465;  //587;
		sFrom = "info@linguaclassica.com";  //"mail@latinparsercloud.com";
		sPassword = "tuscul312";
		sSubject = "Resetting your password";
		sMessagetext = "Use the following password to gain access to your account:  " + passwrd + 
					".  We suggest that you create a password of your choosing in 'Edit account.'";

		try
		{
			System.out.println("Got to start of SendEmail.sendPassword:  sHost, sFrom, iPort = " + sHost + ", " + sFrom + ", " +
					iPort);
			Email email = new SimpleEmail();
			email.setHostName(sHost);
			email.setSmtpPort(iPort);
			email.setAuthenticator(new DefaultAuthenticator(sFrom, sPassword));
			email.setSSLOnConnect(true);
			email.setFrom(sFrom);
			email.setSubject(sSubject);
			email.setMsg(sMessagetext);
			email.addTo(sSendTo);
			email.send();
			System.out.println("Got to end of SendEmail.sendPassword: sSendTo = " + sSendTo);
		}
		catch (EmailException eex)
		{
			eex.printStackTrace();
		}
	}
	
	public void sendPackage(String toEmailAddress, String subject, String content)
	{
		sSendTo = toEmailAddress;
		
		sHost = "smtpout.secureserver.net"; //"mail.latinparsercloud.com";
		iPort = 465;  //587;
		sFrom = "info@linguaclassica.com";  //"mail@latinparsercloud.com";
		sPassword = "tuscul312";

		try
		{
			logger.info("sendPackage:  sHost, sFrom, iPort = " + sHost + ", " + sFrom + ", " +
					iPort);
			Email email = new SimpleEmail();
			email.setHostName(sHost);
			email.setSmtpPort(iPort);
			email.setAuthenticator(new DefaultAuthenticator(sFrom, sPassword));
			email.setSSLOnConnect(true);
			email.setFrom(sFrom);
			email.setSubject(subject);
			email.setMsg(content);
			email.addTo(sSendTo);
			email.send();
			System.out.println("end of sendPackage: sSendTo = " + sSendTo);
		}
		catch (EmailException eex)
		{
			logger.error("sendPackage failed: " + eex);
		}
	}


	public void sendNotice(String to)
	{

		sSendTo = to;
		
		sHost = "smtpout.secureserver.net"; //"mail.latinparsercloud.com";
		iPort = 465;  //587;
		sFrom = "info@linguaclassica.com";  //"mail@latinparsercloud.com";
		sPassword = "tuscul312";
		sSubject = "Your password was used";
		sMessagetext = "This is a notice that someone attempted to change a password ... :-(";

		try
		{
			Email email = new SimpleEmail();
			email.setHostName(sHost);
			email.setSmtpPort(iPort);
			email.setAuthenticator(new DefaultAuthenticator(sFrom, sPassword));
			email.setSSLOnConnect(true);
			email.setFrom(sFrom);
			email.setSubject(sSubject);
			email.setMsg(sMessagetext);
			email.addTo(sSendTo);
			email.send();
		}
		catch (EmailException eex)
		{
			eex.printStackTrace();
		}
	}
		
	

	public void send(String from, String fname, String lname, String msgform) throws MessagingException, EmailException {
		System.out.println("Got to start of SendEmail.send");
		String sentfrom = from;
		String msg = msgform;
		
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtpout.secureserver.net");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("info@linguaclassica.com", "tuscul312"));
			email.setSSLOnConnect(true);
			email.setFrom("info@linguaclassica.com");
			email.setSubject("ContactMail (" + sentfrom + ")");
			email.setMsg("From:  " + fname + " " + lname + ":  " + msg);   //"This is a test mail ... :-)");
			email.addTo("eugene.price@sympatico.ca");   
			email.send();
			System.out.println("Got to end of SendEmail.send, mail sent");
		} 
		catch (EmailException mex) {
			mex.printStackTrace();
		}
    }


	public void sendPurchaseInfo(String msg) throws MessagingException, EmailException {
		System.out.println("Got to start of SendEmail.sendPurchaseInfo");
		
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtpout.secureserver.net");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("info@linguaclassica.com", "tuscul312"));
			email.setSSLOnConnect(true);
			email.setFrom("info@linguaclassica.com");
			email.setSubject("Lingua Classica Purchase)");
			email.setMsg("Following is purchase info:  " + msg);  
			email.addTo("eugene.price@sympatico.ca");   
			email.send();
			System.out.println("Go to end of SendEmail.sendPurchaseInfo, mail sent");
		} 
		catch (EmailException mex) {
			mex.printStackTrace();
		}
		return;
	}
}