package com.linguaclassica.ta;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.HomePage;
import com.linguaclassica.access.MenuItemEnum;
import com.linguaclassica.access.NavBarPanel;
import com.linguaclassica.model.UserModel;

public class TABasePage extends WebPage
{
	private static final long serialVersionUID = 1L;
	
	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());
	
	public TABasePage()
	{
		super();
		
		buildCommon();
		buildMenu();
	}
	
	private void buildCommon()
	{
		FeedbackPanel feedbackpanel = new FeedbackPanel("feedbackmessages");
		add(feedbackpanel);
		
		Label userlabel = new Label("ident", new PropertyModel<String>(userModel, "firstName"));
		add(userlabel);
	}

	private void buildMenu()
	{
		add(new NavBarPanel.Builder("navBar", HomePage.class)
			.withMenuItem(MenuItemEnum.TANOTIFICATIONS, TAMenuErrorPage.class) 
			.withMenuItem(MenuItemEnum.TAASSIGNMENTS, TAMenuErrorPage.class)
			.withMenuItem(MenuItemEnum.TARESULTS, TAMenuErrorPage.class)
			.withMenuItem(MenuItemEnum.TAPROFICIENCIES, TAMenuErrorPage.class)
			.withMenuItem(MenuItemEnum.TAACCOUNT, TAMenuErrorPage.class)
			.withMenuItem(MenuItemEnum.ERROR, TAMenuErrorPage.class)
			.build()
		);
	}
}
