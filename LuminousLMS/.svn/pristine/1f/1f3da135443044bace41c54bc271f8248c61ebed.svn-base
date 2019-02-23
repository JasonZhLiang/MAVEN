package com.linguaclassica.access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutPage extends PublicBasePage 
{
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LogManager.getLogger(LogoutPage.class);

	public LogoutPage()
	{
		logger.debug(this.getClass().getSimpleName());
		
		// Do not stop on the page. Go to home page.
		setResponsePage(HomePage.class);
	}
}
