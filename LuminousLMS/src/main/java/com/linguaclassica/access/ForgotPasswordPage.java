package com.linguaclassica.access;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.entity.OrgUserPermissionEntity;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.PasswordGenerateService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;

public class ForgotPasswordPage extends PublicBasePage
{
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserService userService;
	
	@SpringBean
	PermissionService permissionService;
	
	@SpringBean 
	PasswordGenerateService passwordGenerateService;
	
	@SpringBean
	private ModelFactory modelFactory;
	
	private Logger logger = LogManager.getLogger(getClass());

	public ForgotPasswordPage()
	{
		ForgotPasswordForm form = new ForgotPasswordForm();
		add(form);
	}
	
	class ForgotPasswordForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		private UserModel userModel = modelFactory.getNewUserModel();
		private EmailTextField memEmailField;

		public ForgotPasswordForm()
		{
			super("forgotpassf");
			
			// Fill the form
			memEmailField = new EmailTextField("whatemaili", new PropertyModel<String>(userModel, "emailAddress"));
			memEmailField.setRequired(true);
			add(memEmailField);
		}

		@Override
		public void onSubmit()
		{
			logger.debug("onSubmit");
			String plainpass = "";
			try
			{
				// Create the user model
				String tryemail = userModel.getEmailAddress().trim();
				boolean bFound = userService.usingEmail(tryemail);
				if (bFound)
				{
					UserModel user2Send = userService.findUserByEmailAddress(tryemail);
					Integer userId = user2Send.getId();
					
					// Determine whether the use is an Administrator
					List<OrgUserPermissionEntity> oups = userService.getUserItems(userId);
					Integer adminPermId = permissionService.getPermissionModel(Permission.ADMINISTRATOR).getId();
					boolean bAdmin = false;
					for (OrgUserPermissionEntity oup : oups)
					{
						if (oup.getPermissionId() == adminPermId)
						{
							bAdmin = true;
						}
					}
					
					if (!bAdmin)
					{
						plainpass = passwordGenerateService.generatePassword();
						
						// Show password in the console, never the log
						System.out.format("Generated password %s for user %d%n", plainpass, userId);
                		user2Send.setPassword(plainpass);
                		String passwrdEncoded = LoginHelper.encodePassword(plainpass);
                		userService.updatePassword(userId, passwrdEncoded);
                		
                		// Compose the message
                		String sSubject = getString("sendsubjectforgot");
                		StringBuilder sbmsg = new StringBuilder();
                		String smsg1 = getString("sendtextforgot1");
                		String smsg2 = getString("sendtextforgot2");
                		sbmsg.append(smsg1).append("\n" + plainpass + "\n").append(smsg2).append("\n");
						
                		SendEmail sendmail = new SendEmail();
                    	sendmail.sendPackage(tryemail, sSubject, sbmsg.toString());
                    	/*
        				getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler("SendMailServlet"));
						*/
					}
					else
					{
						logger.debug("Attempt to reset Administrator password.");
					}
				}
				else {

				// Show the response page
				/*
				PageParameters params = new PageParameters();
                params.add("msg", getString("forgotpassmsg"));
            	setResponsePage(MessagePage.class, params);
            	*/
					info(getString("forgotpassnotreg"));
				}
			}
    		catch (NoSuchAlgorithmException nsae)
			{
			    logger.error("Password encode error encrypting '" + plainpass + "': " + nsae.toString());
			    error("An error occurred. If the problem persists please contact your system administrator.");
			}
			catch (Exception e)
			{
				logger.debug("Unexpected error when getting user: '" + userModel.getEmailAddress().trim() + "' " + e.getMessage());
				error("An error occurred. If the problem persists please contact your system administrator.");
			}
		}
	}
}
