package com.linguaclassica.access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class PublicBasePage extends WebPage
{
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LogManager.getLogger(PublicBasePage.class);
	
	private boolean bFrench = false;

	public PublicBasePage()
	{
		super();
		
		logger.debug(this.getClass().getSimpleName());
		buildCommon();
		buildMenu();
	}

	public PublicBasePage(PageParameters parameters)
	{
		super(parameters);
		
		logger.debug(this.getClass().getSimpleName() + "(..)");
		buildCommon();
		buildMenu();
	}

	private void buildCommon()
	{
		FeedbackPanel feedbackpanel = new FeedbackPanel("feedbackmessages");
		add(feedbackpanel);
	}
	
	private void buildMenu()
	{
		String key = new String("msg");
		PageParameters paramFR = new PageParameters();
		paramFR.add(key, "FR");
		
		bFrench = this.getSession().getLocale().getLanguage().equals("fr");
		if (bFrench)
		{
			// French menu
			add(new NavBarPanel.Builder("navBar", HomePage.class)
					.withMenuItem(MenuItemEnum.LOGIN_F, "login_ml", HomePage.class) 
					.withMenuItem(MenuItemEnum.FORGOT_PASS_F, "forgotpass_ml", ForgotPasswordPage.class)
					//.withMenuItem(MenuItemEnum.CONTACT_US_F, HomePage.class)	// TODO ContactUsPage
					.buildPublic()
					);
		}
		else
		{
			// Default to English menu
			add(new NavBarPanel.Builder("navBar", HomePage.class)
					.withMenuItem(MenuItemEnum.LOGIN, "login_ml", HomePage.class) 
					.withMenuItem(MenuItemEnum.FORGOT_PASS, "forgotpass_ml", ForgotPasswordPage.class)
					//.withMenuItem(MenuItemEnum.CONTACT_US, HomePage.class)	// TODO ContactUsPage
					.buildPublic()
			);
		}
	}
	
	public Boolean isFrench(){
		return bFrench;
	}
}
