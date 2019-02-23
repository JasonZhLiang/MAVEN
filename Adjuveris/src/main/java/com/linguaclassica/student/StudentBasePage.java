package com.linguaclassica.student;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.HomePage;
import com.linguaclassica.access.MenuItemEnum;
import com.linguaclassica.access.NavBarPanel;
import com.linguaclassica.model.UserModel;

public class StudentBasePage extends WebPage
{
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = null;
	
	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());

	public StudentBasePage()
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

		add(new Link<Object>("permswap")
		{
			private static final long serialVersionUID = 1L;
			
			public boolean isVisible()
			{
				return AlphPlusSession.get().areMultiplePermissions();
			}
			
			@Override
			public void onClick()
			{
				System.out.println("StudentBasePage.onClick");
				setResponsePage(StudentSwitchPermissionPage.class);
			}
		});
	}

	private void buildMenu()
	{
		String key = new String("msg");
		PageParameters paramN = new PageParameters();
		paramN.add(key, "Future Notifications and Assignments page");
		PageParameters paramP = new PageParameters();
		paramP.add(key, "Future Practise Assignments page");
		PageParameters paramR = new PageParameters();
		paramR.add(key, "Future Results page");
		PageParameters paramF = new PageParameters();
		paramF.add(key, "Future Proficiencies page");
		PageParameters paramA = new PageParameters();
		paramA.add(key, "Future Account page");
		PageParameters paramE = new PageParameters();
		paramE.add("oops", "Test for wrong message key.");
		
		add(new NavBarPanel.Builder("navBar", HomePage.class)
			.withMenuItem(MenuItemEnum.SNOTIFICATIONS, NotificationsPage.class) 
			.withMenuItem(MenuItemEnum.PRACTICEX, PracticeExercisesPage.class)
			.withMenuItem(MenuItemEnum.SRESULTS, ExercisesResultsPage.class)
			.withMenuItemAsDropdown(MenuItemEnum.SPROFICIENCIES, ExercisesMetricsPage.class, "\u25BB View Table")
			.withMenuItemAsDropdown(MenuItemEnum.SPROFICIENCIES, ExercisesPolarChartPage.class, "\u25BB View Polar Chart")
			.withMenuItem(MenuItemEnum.SACCOUNT, StudentMenuErrorPage.class, paramA)
			.withMenuItem(MenuItemEnum.ERROR, StudentMenuErrorPage.class, paramE)
			.build()
		);
	}

	// Log and display the message page when exception occur on subclassed pages
	public void processException(String pageName, Exception ex)
	{
		logger = LoggerFactory.getLogger(pageName);
		logger.error(ex.toString());
		PageParameters params = new PageParameters();
		params.add("message", ex.toString());
		setResponsePage(StudentMenuErrorPage.class, params);
	}
	
	public /*abstract*/ MenuItemEnum getActiveMenu()
	{
		return null;
	}
}
