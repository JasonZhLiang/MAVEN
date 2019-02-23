package com.linguaclassica.access;

import org.apache.wicket.markup.html.WebPage;

public class LogoutPage extends WebPage 
{
	private static final long serialVersionUID = 1L;

	public LogoutPage()
	{
		setResponsePage(HomePage.class);
	}
}
