package com.linguaclassica.teacher;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.HomePage;
import com.linguaclassica.access.MenuItemEnum;
import com.linguaclassica.access.NavBarPanel;
import com.linguaclassica.exercises.CreateExercisePage;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.PermissionService;

public class TeacherBasePage extends WebPage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private PermissionService permissionService;
	
	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());
	
	private static final List<Integer> TermSettings = Arrays.asList(0, 1, 2, -1);
	private boolean allowPreference = false;

	@SuppressWarnings("unused")
	private Integer termsetting;
	
	public TeacherBasePage()
	{
		super();
		
		buildCommon();
		buildMenu();
	}
	
	// Allow the setting the be set by a super-class
	protected void permitPreferenceSelection()
	{
		allowPreference = true;
	}
	
	private void buildCommon()
	{
		FeedbackPanel feedbackpanel = new FeedbackPanel("feedbackmessages");
		add(feedbackpanel);
		
		Label userlabel = new Label("ident", new PropertyModel<String>(userModel, "firstName"));
		add(userlabel);
		
		String permStr = permissionService.getModel(AlphPlusSession.get().getCurrentPermission()).getPermissionString();
		Label userPermissionL = new Label("userperm", permStr);
		add(userPermissionL);

		termsetting = AlphPlusSession.get().getDateRange();
		ChoiceRenderer<Integer> choiceRenderer = new ChoiceRenderer<Integer>()
		{
			private static final long serialVersionUID = 1L;
			
			@Override
			public Object getDisplayValue(Integer value)
			{
				switch (value)
				{
					case 0:
						return "Current Term";
					case 1:
						return "One Prior Term";
					case 2:
						return "Two Prior Terms";
					case -1:
						return "All Terms";
					default:
						throw new IllegalStateException(value + " is not mapped!");
				}
			}
			
			@Override
			public String getIdValue(Integer object, int index)
			{
				Integer idvalue = TermSettings.get(index);
				String strvalue = String.valueOf(idvalue);
				return strvalue;
			}
		};
		DropDownChoice<Integer> prefselect = new DropDownChoice<Integer>("termPref", 
				new PropertyModel<Integer>(this, "termsetting"), TermSettings, choiceRenderer)
		{
			private static final long serialVersionUID = 1L;
			
			public boolean isVisible()
			{
				return allowPreference;
			}
			
			protected boolean wantOnSelectionChangedNotifications()
			{
				return true;
			}
			
			protected void onSelectionChanged(final Integer newSelection)
			{
				System.out.format("onSelectionChanged(%d)%n", newSelection);
				AlphPlusSession.get().setDateRange(newSelection);
				setResponsePage(getPage().getClass());
			}
		};
		prefselect.setNullValid(false);
		prefselect.setRequired(true);
		add(prefselect);

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
				System.out.println("TeacherBasePage.onClick");
				setResponsePage(TeacherSwitchPermissionPage.class);
			}
		});
	}
	
	private void buildMenu()
	{
		
		add(new NavBarPanel.Builder("navBar", HomePage.class)
			.withMenuItemAsDropdown(MenuItemEnum.TENOTIFICATIONS, TeacherNotificationPage.class, "New Notification") 
			.withMenuItemAsDropdown(MenuItemEnum.TENOTIFICATIONS, TeacherNotificationsPage.class, "View Notifications")
			.withMenuItemAsDropdown(MenuItemEnum.TEXERCISES, TeacherExercisesPage.class, "View All Exercises")
			.withMenuItemAsDropdown(MenuItemEnum.TEXERCISES, CreateExercisePage.class, "Create a New Exercise")
			.withMenuItemAsDropdown(MenuItemEnum.TEXERCISES, ExerciseListPage.class, "Edit Avaliable Exercises")
			
			.withMenuItemAsDropdown(MenuItemEnum.TEASSIGNMENTS, CreateAssignmentPage.class, "Create Assignment") 
			.withMenuItemAsDropdown(MenuItemEnum.TEASSIGNMENTS, TeacherAssignmentsPage.class, "View Assignments")
			// "View Assignments Tree (temp)" menu sub-item below is for tree view investigation , and should be removed
			// once the investigation is completed.
			.withMenuItemAsDropdown(MenuItemEnum.TEASSIGNMENTS, TeacherAssignmentsPageTemp.class, "View Assignments Tree (temp)")
			.withMenuItem(MenuItemEnum.GROUPS, TeacherMessagePage.class)
			.withMenuItem(MenuItemEnum.TERESULTS, TeacherResultsPage.class)
			.withMenuItemAsDropdown(MenuItemEnum.TEPROFICIENCIES, TeacherMessagePage.class, "Class")
			.withMenuItemAsDropdown(MenuItemEnum.TEPROFICIENCIES, TeacherMessagePage.class, "Student")
			.withMenuItemAsDropdown(MenuItemEnum.TEACCOUNT, TeacherMessagePage.class, "Password")
			//.withMenuItemAsDropdown(MenuItemEnum.TEACCOUNT, TeacherPreferences.class, "Preferences")
			.build()
		);
	}
}
