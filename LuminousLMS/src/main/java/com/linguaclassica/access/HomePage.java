package com.linguaclassica.access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.linguaclassica.entity.EntityPermissionModel;
import com.linguaclassica.entity.OrgUserPermissionEntity;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.officeadmin.OfficeAdminOverviewPage;
import com.linguaclassica.service.ConsultantClientsService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.service.exception.CircumstanceException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;
import com.linguaclassica.shared.CommonOverviewPage;
import com.linguaclassica.shared.MessagePage;
import com.linguaclassica.shared.SwitchPermissionPage;

import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class HomePage extends PublicBasePage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserService userService;

	@SpringBean
	private PermissionService permissionService;
	
	@SpringBean
	private ConsultantClientsService consultantClientsService;
	
	private Logger logger = LogManager.getLogger(HomePage.class);
	
	
	public HomePage()
	{
		super();

		Locale here = LMSSession.get().getLocale();
		String isLanguage = String.format("Locale language '%s' '%s'", here.getLanguage(), here.getDisplayLanguage());
		logger.info(isLanguage);

		add(new LocaleLink("anglo", "en"));
		
		add(new LocaleLink("franco", "fr"));
		
		HomeForm form = new HomeForm("HomeForm", getString("lanswer"),getString("lquestion"));
		add(form);
    }
	
	class HomeForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		Label questiontlbl;
		Label lblanswer,lblquestion;
		TextField<String> answertf;
		PasswordTextField passwordField;
		// Model placeholders
		private String emailAddress; 
		private String password;
		private String question; 
		private String answer;
		
		public HomeForm(String id, String questionlbl, String answerlbl)
		{
			super(id);

			// Setup login box
			EmailTextField emailAddressField = new EmailTextField("emailAddress", new PropertyModel<String>(this, "emailAddress"));
			emailAddressField.setRequired(true);
			
			passwordField = new PasswordTextField("password", new PropertyModel<String>(this, "password"));
			passwordField.setRequired(true);
			
			questiontlbl = new Label("questiontlbl",
					new PropertyModel<String>(this,"question"));
			answertf = new TextField<String>("answertf",
					new PropertyModel<String>(this,"answer"));
			
			lblquestion= new Label ("lquestion", questionlbl);
			lblanswer = new Label ("lanswer", answerlbl);
			
			add(emailAddressField);
			add(passwordField);
			
			add(lblanswer.setVisible(false));
			add(lblquestion.setVisible(false));
			add(questiontlbl.setVisible(false));
			add(answertf.setVisible(false));
			
			setEnabled(true);		
		}
		
		public void onSubmit()
		{
			logger.debug("onSubmit");
			
			try
			{
				// Encode the password
				UserModel userModel = userService.authenticateUser(emailAddress, LoginHelper.encodePassword(password));
			
				if(userModel.getQuestion() != null && !userModel.getQuestion().isEmpty()){
					if(!answertf.isVisible()){
						this.question = userModel.getQuestion();
						
						answertf.setVisible(true);
						questiontlbl.setVisible(true);
						lblanswer.setVisible(true);
						lblquestion.setVisible(true);
						
						//in order not to clear entered password force regular text box behaviour
						passwordField.setResetPassword(false);
						return;
					}
					//the case when answers do not match
					if(userModel.getAnswer() != null && !userModel.getAnswer().isEmpty()){
						if(this.answer == null || this.answer .isEmpty() || !this.answer.equals(userModel.getAnswer())){
				    		logger.debug("The provided answer to the secury question does not match");
	                    	Session.get().error("The provided answer to the secury question does not match");
	                    	return;
						}
					}// the case where there is no answer to the question -tricky
					else if(this.answer != null && !this.answer .isEmpty()){
						logger.debug("The provided answer to the secury question does not match");
	                    Session.get().error("The provided answer to the secury question does not match");
	                    return;
					}
				}
						
						// Successful authentication, set user in session and continue to Landing Page
				LMSSession.get().setUser(getRequest(), userModel);
				
				// Initiate the retrieval of browser setting, especially local time zone
				LMSSession.get().getApplication().getRequestCycleSettings().setGatherExtendedBrowserInfo(true);
				
		    	LMSSession.get().setMultiplePermissions(false);
		    	if (userService.areMultiplePermissions(userModel))
		    	{
					// There are multiple organizations/permissions for the user
		    		logger.debug("There are multiple permissions for user.");
					LMSSession.get().setMultiplePermissions(true);
					// Initialize the currentOUP to the first permission showed up in permission table.
					OrgUserPermissionEntity firstOUP = userService.getUserItems(userModel.getId()).get(0);
					LMSSession.get().setCurrentOUP(firstOUP.getId());
					setResponsePage(SwitchPermissionPage.class);
		    	}
		    	else
		    	{
		    		// There is one organization/permission for the user
		    		OrgUserPermissionEntity singleOUP = userService.getUserItems(userModel.getId()).get(0);
		    		LMSSession.get().setCurrentOUP(singleOUP.getId());
		    		LMSSession.get().setCurrentOrganization(singleOUP.getOrganizationId());
		    		LMSSession.get().setCurrentPermission(singleOUP.getPermissionId(), 
		    				permissionService.getModel(singleOUP.getPermissionId()).getPermissionString());
		    		Permission permission = permissionService.getModel(singleOUP.getPermissionId()).getPermission();
					if (permission == Permission.CLIENT)
					{
						logger.info("Login permission " + EntityPermissionModel.getPermissionString(permission));
						setResponsePage(CommonOverviewPage.class);
					}
					else if(permission == Permission.CONSULTANT)
					{
						logger.info("Login permission " + EntityPermissionModel.getPermissionString(permission));
						setResponsePage(CommonOverviewPage.class);
					}
					else if(permission == Permission.OFFICE_ADMIN)
					{
						logger.info("Login permission " + EntityPermissionModel.getPermissionString(permission));
						setResponsePage(OfficeAdminOverviewPage.class);
					}
					else if(permission == Permission.ADMINISTRATOR)
					{
						// TODO Add response to an administrator page
						logger.info("Login permission " + EntityPermissionModel.getPermissionString(permission));
						logger.error("Attempted Log in as Administrator");
						PageParameters paramAL = new PageParameters();
						paramAL.add("msg", "Future Administrator Landing page");
						setResponsePage(MessagePage.class, paramAL);
					}
					else
					{
						
						logger.error("Unchecked Permission type " + LMSSession.get().getCurrentPermission().toString());
					}
		    	}
			}
			catch (UnknownEntityException e)
			{
				// Failed login attempt, not distinguishing between invalid email addresses and 
				// wrong passwords to avoid revealing whether it is a valid email address
				logger.warn("Failed login attempt for " + emailAddress + ": " + e.toString());
				error(getString("ueex"));
			}
			catch (ServiceException e)
			{
				// Something happened
				logger.error("Error when logging in for " + emailAddress + ": " + e.toString());
				error(getString("servex"));
			}
			catch (NoSuchAlgorithmException e)
			{
			    logger.error("Password encoding error: " + e.toString());
			    error(getString("nosalgex"));
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CircumstanceException e) {
				// TODO Auto-generated catch block
				 logger.error("User status: " + e.toString());
				 error("User's Status is not Active. Contact the Administrator to change it.");//getString("nosalgex"));
			}
		}
	}

	// https://cwiki.apache.org/confluence/display/WICKET/Manually+switching+locale
	public class LocaleLink extends Link<Object>
	{
	    private static final long serialVersionUID = 1L;
	 
	    private final Locale locale;
	 
	    /**
	     * Constructor for a new LocaleLink.
	     *
	     * @param wicketId Id of wicket component
	     * @param localeString Locale to switch to
	     */
	    public LocaleLink(final String wicketId, final String localeString)
	    {
	        super(wicketId);
	        this.locale = new Locale(localeString);
	    }
	 
	    /**
	     * Constructor with locale.
	     *
	     * @param wicketId Id of wicket component
	     * @param locale Locale to switch to
	     */
	    public LocaleLink(final String wicketId, final Locale locale)
	    {
	        super(wicketId);
	        this.locale = locale;
	    }
	 
	    @Override
	    public void onClick()
	    {
	    	// Refresh the page with the new language set
	    	this.getSession().setLocale(this.locale);
	        setResponsePage(HomePage.class);
	    }
	 
	    @Override
	    public boolean isVisible()
	    {
	        return !this.getSession().getLocale().equals(this.locale);
	    }
	}
}
