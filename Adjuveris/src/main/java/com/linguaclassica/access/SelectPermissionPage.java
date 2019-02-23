package com.linguaclassica.access;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class SelectPermissionPage extends WebPage
{
	private static final long serialVersionUID = 1L;

	public SelectPermissionPage()
	{
		super();
		
		FeedbackPanel feedback = new FeedbackPanel("feedbackmessages");
		add(feedback);
		
		SelectPermissionPanel spp = new SelectPermissionPanel();
		add(spp);
	}
}
