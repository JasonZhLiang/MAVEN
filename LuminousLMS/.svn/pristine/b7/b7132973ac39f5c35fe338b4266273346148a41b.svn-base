package com.linguaclassica.shared;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualInputValidator;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.service.exception.CircumstanceException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

import java.security.NoSuchAlgorithmException;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.access.LoginHelper;

public class UserAccountPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserService userService;

	@SpringBean
	private PermissionService permissionService;

	private Logger logger = LogManager.getLogger(getClass());
	
	// Active user
	private final UserModel userModel = LMSSession.get().getUser(getRequest());
	
	public UserAccountPage()
	{
		super();
		
		UserAccountForm form = new UserAccountForm(getString("lanswer"),getString("lquestion"));
		add(form);
    }
	 
	class UserAccountForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		// Model placeholders
		private String newEmail;
		private String localPassword = "";
		private String newPassword = "";
		@SuppressWarnings("unused")
		private String confirmPassword = "";
		private String newQuestion= "";
		private String newAnswer= "";
		
		public UserAccountForm(String questionlbl, String answerlbl)
		{
			super("UserAccountForm");

			// Load initial personal information
			String myaccountUsername = userModel.getFirstName() + " " + userModel.getLastName();
			add(new Label("username", myaccountUsername));

			newEmail = userModel.getEmailAddress();
			EmailTextField emailAddressField = new EmailTextField("emailaddr", new PropertyModel<String>(this, "newEmail"));
			emailAddressField.setRequired(true);
			add(emailAddressField);
			
			localPassword = userModel.getPassword();
			PasswordTextField oldPasswordField = new PasswordTextField("currentpassword", new PropertyModel<String>(this, "localPassword"));
			oldPasswordField.setRequired(true);
			oldPasswordField.add(new PasswordValidator());
			add(oldPasswordField);

			PasswordTextField newPasswordField = new PasswordTextField("newpassword", new PropertyModel<String>(this, "newPassword"));
			newPasswordField.setRequired(false);
			newPasswordField.add(new PasswordValidator());
			add(newPasswordField);

			PasswordTextField confirmPasswordField = new PasswordTextField("confirmpassword", new PropertyModel<String>(this, "confirmPassword"));
			confirmPasswordField.setRequired(false);
			confirmPasswordField.add(new PasswordValidator());
			add(confirmPasswordField);
			
			newQuestion = userModel.getQuestion();
			TextField<String> questiontf = new TextField<String>("questiontf",
					new PropertyModel<String>(userModel,"question"));
			
			newAnswer = userModel.getAnswer();
			TextField<String> answertf = new TextField<String>("answertf",
					new PropertyModel<String>(userModel,"answer"));
			
			Boolean securityQA = !currentPerm.equals("Client");
			questiontf.setVisible(securityQA);
			answertf.setVisible(securityQA);
			add(questiontf);
			add(answertf);
			
			add(new Label ("lquestion", questionlbl).setVisible(securityQA));
			add(new Label ("lanswer", answerlbl).setVisible(securityQA));
			
			add(new EqualInputValidator(newPasswordField, confirmPasswordField));
			setEnabled(true);		
		}
		
		public void onSubmit()
		{
			logger.debug("onSubmit");
			
			boolean updatedEmail = false;
			boolean updatedPassword = false;		
			boolean updatedQuestion = false;
			boolean updatedAnswer = false;

			// Validate the original email address and password
			try 
			{
				String authPassword = LoginHelper.encodePassword(localPassword);
				userService.authenticateUser(userModel.getEmailAddress(), authPassword);
			}
			catch (NoSuchAlgorithmException e)
			{
				logger.error("Password encryption failed " + e);
				error(getString("exnoalgorith"));
				return;
			}
			catch (UnknownEntityException e)
			{
				Session.get().error(getString("exunknown"));
				return;
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CircumstanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String encryptedPassword = "";
			if ((newPassword != null) && !newPassword.isEmpty())
			{
				// Validate the new password
				try
				{
					encryptedPassword = LoginHelper.encodePassword(newPassword);
				}
				catch (NoSuchAlgorithmException e)
				{
					logger.error("New password encryption error: " + e.toString());
					error(getString("exnoalgorith"));
					return;
				}
				catch (NullPointerException e) // should not happen
				{
					logger.debug("New password field empty: " + e.toString());
					error(getString("exnullp"));
					return;
				}
			}
			
			try
			{
				// Update the user's settings individually
				if (!userModel.getEmailAddress().equals(newEmail))
				{
					if (userService.usingEmail(newEmail))
					{
						error(getString("exemailexists"));
						return;
					}
					userService.updateEmailaddress(userModel.getId(), newEmail);
					updatedEmail = true;
				}
				if (!encryptedPassword.isEmpty())
				{
					userService.updatePassword(userModel.getId(), encryptedPassword);
					userModel.setPassword(encryptedPassword);
					updatedPassword = true;
					
				}
				
				if (!userModel.getQuestion().equals(newQuestion))
				{
					// Changed question 
					userService.updateQuestion(userModel.getId(), userModel.getQuestion());
					//userModel.setQuestion(newQuestion);
					updatedQuestion = true;
				}
				if (!userModel.getAnswer().equals(newAnswer))
				{
					// Changed question 
					userService.updateAnswer(userModel.getId(), userModel.getAnswer());
				//	userModel.setAnswer(newAnswer);
					updatedAnswer = true;
				}
				
				
			}
			catch (Exception e)
			{
				logger.error("Update failed for '" + LMSSession.get().getLogInfo() + newEmail + "' and " + encryptedPassword);
				error(getString("exfailedupdate"));
			}
			
			PageParameters param = new PageParameters();
			if ((updatedEmail==true) && (updatedPassword==true)) {
				param.add("msg", getString("updatedepmsg"));
			}
			else if (updatedEmail == true) {
				param.add("msg", getString("updatedemsg"));
			}
			else if (updatedPassword == true) {
				param.add("msg", getString("updatedpmsg"));
			}
				
			setResponsePage(MessagePage.class, param);

		}
	}
}
