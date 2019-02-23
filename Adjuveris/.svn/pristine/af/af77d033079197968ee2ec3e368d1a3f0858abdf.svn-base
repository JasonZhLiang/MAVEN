package com.linguaclassica.access;

import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linguaclassica.entity.InstUserPermissionEntity;
import com.linguaclassica.instadmin.InstAdminOverviewPage;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.model.UserModel.SubsStatusType;
import com.linguaclassica.service.DateRangeService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.service.exception.CircumstanceException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;
import com.linguaclassica.student.NotificationsPage;
import com.linguaclassica.ta.TAMenuErrorPage;
import com.linguaclassica.teacher.TeacherBasePage;
import com.linguaclassica.teacher.TeacherNotificationsPage;

import java.security.NoSuchAlgorithmException;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserService userService;

	@SpringBean
	private PermissionService permissionService;
	
	@SpringBean
	private DateRangeService dateRangeService;

	private Logger logger = LoggerFactory.getLogger("HomePage");

	public HomePage()
	{
		super();
		
		System.out.println("HomePage loaded");
		
		add(new Link<Object>("teacherlink"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(TeacherBasePage.class);				
			}
			
		});
		
		FeedbackPanel feedback = new FeedbackPanel("feedbackmessages");
		add(feedback);
		
		HomeForm form = new HomeForm("HomeForm");
		add(form);
    }
	
	class HomeForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		// Model placeholders
		private String emailAddress = "";
		private String password = "";
		
		public HomeForm(String id)
		{
			super(id);

			// Setup login box
			EmailTextField emailAddressField = new EmailTextField("emailAddress", new PropertyModel<String>(this, "emailAddress"));
			emailAddressField.setRequired(true);
			
			PasswordTextField passwordField = new PasswordTextField("password", new PropertyModel<String>(this, "password"));
			passwordField.setRequired(true);
			
			add(emailAddressField);
			add(passwordField);
			setEnabled(true);
		}
		
		public void onSubmit()
		{
			System.out.println("HomeForm.onSubmit");
			
			try
			{
				// Encode the password
				UserModel userModel = userService.authenticateUser(emailAddress, LoginHelper.encodePassword(password));
				Integer userId = userModel.getId();

				// Successful authentication, set user in session and continue to Landing Page
				AlphPlusSession.get().setUser(getRequest(), userModel);
				
				// Initiate the retrieval of browser setting, especially local time zone
				AlphPlusSession.get().getApplication().getRequestCycleSettings().setGatherExtendedBrowserInfo(true);
				
		    	SubsStatusType subsstatus = userModel.getStatus();
		    	
				if (subsstatus == SubsStatusType.ACTIVE)
				{
					System.out.println("HomeForm.onSubmit permissionID " + AlphPlusSession.get().getCurrentPermission());
					AlphPlusSession.get().setMultiplePermissions(false);
					if (userService.areMultiplePermissions(userModel))
					{
						// There are multiple institutions/permissions for the user
						System.out.println("HomeForm.onSubmit there are multiple permissions for user " + userModel.getId());
						AlphPlusSession.get().setMultiplePermissions(true);
						AlphPlusSession.get().setCurrentIUP(0);
						setResponsePage(SelectPermissionPage.class);
					}
					else
					{
						// There is one institution/permission for the user
						InstUserPermissionEntity singleIUP = userService.getUserItems(userModel.getId()).get(0);
						AlphPlusSession.get().setCurrentIUP(singleIUP.getId());
						AlphPlusSession.get().setCurrentInstitution(singleIUP.getInstitutionId());
						AlphPlusSession.get().setCurrentPermission(singleIUP.getPermissionId());
						Permission permission = permissionService.getModel(singleIUP.getPermissionId()).getPermission();
						if (permission == Permission.STUDENT)
						{
							
							int dateRangeNum = dateRangeService.getStudentTerms(singleIUP.getInstitutionId());
							System.out.println("dateRangeNum: " + dateRangeNum);
							
							AlphPlusSession.get().setDateRange(dateRangeNum);
							
							setResponsePage(NotificationsPage.class);
						}
						else if(permission == Permission.INSTITUTION_ADMIN)
						{
							
							int instDateRangeNum = dateRangeService.getInstTerms(singleIUP.getInstitutionId());
							System.out.println("instDateRangeNum: " + instDateRangeNum);
							
							
							AlphPlusSession.get().setDateRange(instDateRangeNum);
							
							setResponsePage(InstAdminOverviewPage.class);
						}
						else if(permission == Permission.TEACHER)
						{
							// TODO Time Zone setting should be in database (user preference) or from website location.
							AlphPlusSession.get().setDateRange(AlphPlusSession.BEGINNING_OF_TIME);
							AlphPlusSession.get().setTimeZone("ET");
							AlphPlusSession.get().setDateFormat("dd-MM-yy");
							AlphPlusSession.get().setTimeFormat("hh:mm a z");
							
							setResponsePage(TeacherNotificationsPage.class);
						}
						else if(permission == Permission.TA)
						{
							// TODO Time Zone setting should be in database (user preference) or from website location.
							AlphPlusSession.get().setDateRange(AlphPlusSession.BEGINNING_OF_TIME);
							AlphPlusSession.get().setTimeZone("ET");
							AlphPlusSession.get().setDateFormat("dd-MM-yy");
							AlphPlusSession.get().setTimeFormat("hh:mm a z");

							setResponsePage(TeacherNotificationsPage.class);
						}
						else if(permission == Permission.ADMINISTRATOR)
						{
							Session.get().error("Administrator pages have not yet been added.");
							System.out.println("log in as administrator");
							//setResponsePage(AdminHomePage.class);
						}
						else
						{
							System.out.println("HomeForm.onSubmit unchecked Permission type.");
						}
					}
				}				
				else if (subsstatus == SubsStatusType.SUSPENDED)
				{
					System.out.println("got to HomePage suspended");
					error("Sorry.  Your subscription has been suspended.  Please contact your institution's administrator.");
				}	
			}
			catch (UnknownEntityException e)
			{
				// Failed login attempt, not distinguishing between invalid email addresses and 
				// wrong passwords to avoid revealing whether it is a valid email address
				logger.debug("Failed login attempt for " + emailAddress + " " + e.toString());
				error("Please enter a valid userid and password combination.");					
			}
			catch (CircumstanceException ce)
			{
				// Circumstance: user is probably suspended
				logger.debug("Failed login attempt for " + emailAddress + " " + ce.toString());
				error("Sorry. Your subscription has been suspended. Please contact your program administrator.");
			}
			catch (ServiceException e)
			{
				// Something happened
				logger.error("Error when logging in for " + emailAddress + ": " + e.toString());
				error("An unexpected error occurred. If the problem continues, please contact your system administrator");
			}
			catch (NoSuchAlgorithmException e)
			{
			    logger.debug("Password encode error: " + e.toString());
				// TODO: Feedback user password encode failed. Asking to retry.
				e.printStackTrace();
			}
		}
	}
}
