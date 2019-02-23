package com.linguaclassica.instadmin;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.validation.validator.StringValidator;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.LoginHelper;
import com.linguaclassica.access.NameValidator;
import com.linguaclassica.access.SendEmail;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.entity.InstUserPermissionEntity;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.InstitutionService;
import com.linguaclassica.service.PasswordGenerateService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class UserCreatePage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;

	@SpringBean
	private InstitutionService institutionService;

	@SpringBean
	private PermissionService permissionService;
	
	@SpringBean
	TermService termService;

	@SpringBean
	UserService userService;
	
	@SpringBean
	PasswordGenerateService passwordGenerateService;

	private AlphPlusSession session;
	private Permission permission;
	private int userid;
    private Integer institutionId;

	public UserCreatePage(PageParameters params)
	{
		super(params);
		
		institutionId = AlphPlusSession.get().getCurrentInstitution();
		session = AlphPlusSession.get();

		StringValue sv1 = params.get("usertype");
		if (sv1 != null)
		{
			String usertype = sv1.toString();
			if (usertype !=null)
			{
				permission = Permission.valueOf(usertype);
			}
		}

		StringValue sv2 = params.get("userid");
		if (sv2 != null)
		{
			String userstr = sv2.toString();
			if (userstr !=null)
			{
				userid = Integer.parseInt(userstr);
			}
		}
		
		System.out.println("UserCreatePage()    permission= " + permission );
		System.out.println("UserCreatePage()    userid= " + userid );

		Form<Object> form = new UserForm();
		add(form);
	}

	class UserForm extends Form<Object> 
	{
		private static final long serialVersionUID = 1L;

		EntityUserModel addUserModel;
		EntityTermModel destinationTerm;
		
		TextField<String> firstnametf;
		TextField<String> lastnametf;
		EmailTextField emailaddresstf;
		TextField<String> usercodetf;
		
		String userstring = new String("");
		String codestring = new String("");

		public UserForm()
		{
			super("userform");
			
			java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
						
			List<EntityTermModel> termsList = termService.getListOfNewAndExistingTerms(institutionId, now);
			
			if (userid > 0)  // This is an EDIT action  (ie, edit button clicked in a user list.
			{
				addUserModel = (EntityUserModel) userService.findUserById(userid);
				add (new Label("actionlabel", "Edit"));
			}
			else  // This is a NEW user action (ie, from the user new sub-menu, for example Teacher -> New Teacher)
			{
				addUserModel = (EntityUserModel) modelFactory.getNewUserModel();
				add (new Label("actionlabel", "Create new"));
			}
			
			// Create a drop-down control for the term to add students to
			destinationTerm = (EntityTermModel) modelFactory.getNewTermModel();
			if (termsList.size() > 0)
			{
				// Initialize to the newest item unless term retrieved from alpheios-session
				if (session.getUserPageTermId() == -1)
					destinationTerm = termsList.get(termsList.size()-1);
				else
					destinationTerm = termService.findTermById(session.getUserPageTermId());
			}
			
			DropDownChoice<EntityTermModel> termselect = new DropDownChoice<EntityTermModel>("selectterm",
					new PropertyModel<EntityTermModel>(this, "destinationTerm"), termsList, new ChoiceRenderer<EntityTermModel>("termname", "id"));
			termselect.setNullValid(false);
			termselect.setRequired(true);
			if (userid > 0)
			{
				termselect.setEnabled(false);
			}
			
			add(termselect);
			
			firstnametf = new TextField<String>("firstnametf",
					new PropertyModel<String>(addUserModel,"firstName"));
			lastnametf = new TextField<String>("lastnametf",
					new PropertyModel<String>(addUserModel,"lastName"));
			emailaddresstf = new EmailTextField("emailaddresstf",
					new PropertyModel<String>(addUserModel,"emailAddress"));
			usercodetf = new TextField<String>("usercodetf",
					new PropertyModel<String>(addUserModel,"studentnumber"));
			
			// Note:  setting emailaddress as the only required field strictly enforced within the form.
			//        This allows to add an existing user to the selected term by specifying their email
			//        address only, and then we will display the name and code details and confirm the 
			//        add action.  For the case of a brand new user (ie, user with existing email not 
			//        found), we will check for and enforce name to be entered as well.
			emailaddresstf.setRequired(true);
			
			firstnametf.add(new NameValidator());
			lastnametf.add(new NameValidator());
			usercodetf.add(StringValidator.maximumLength(30));

			add(firstnametf);
			add(lastnametf);
			add(emailaddresstf);
			add(usercodetf);
			
			if (permission == Permission.STUDENT)
			{
				userstring = "Student";
				codestring = "Student";
			}
			else if (permission == Permission.TEACHER)
			{
				userstring = "Teacher";
				codestring = "Employee";
			}
			else if (permission == Permission.TA)
			{
				userstring = "TA";
				codestring = "Empl/Student";
			}
			
			add (new Label("userlabel", userstring));
			add (new Label("codelabel", codestring));
		}

		public void onSubmit()
		{
			System.out.println("UserCreatePage()  onSubmit userid= " + addUserModel.getId() + ", " + userid);

			if (userid > 0)   // valid userid passed as result of edit button click
			{
				// ===================================================================================================
				// ===  edit existing user
				// ===================================================================================================

				try {
					userService.updateUser(addUserModel);
				} 
				catch (Exception e) {
					if (e.getMessage().contains("Duplicate entry"))
					{
						Session.get().error("Error:  Save failed.  Duplicate entry");
						
						System.out.println("Error:  Save failed.  Duplicate entry");
						System.out.println("e.getMessage = " + e.getMessage());
					}
					else
					{
						Session.get().error("Error:  Save failed, for email '" + emailaddresstf.getModelObject() + "'.  Please try again.");
						e.printStackTrace();
					}
				}
			}
			else
			{
				// ===================================================================================================
				// ===  add new user user to term.  Could be brand new or an existing user not in the selected term
				// ===================================================================================================

				List<EntityUserModel> userEmailList = userService.checkDuplicates(emailaddresstf.getModelObject());
				
				if (userEmailList.size() < 1) {
					// THIS IS A BRAND NEW USER
					
                    // no entry in user service, so adding new user record
                    // Must ensure firstname and lastname are entered for new user

                    if (firstnametf.getModelObject() == null || firstnametf.getModelObject().isEmpty()) {
                    	Session.get().error("'First name' is required for new " + permission.toString() + ".");
                    	return;
                    }

                    if (lastnametf.getModelObject() == null || lastnametf.getModelObject().isEmpty()) {
                    	Session.get().error("'Last name' is required for new " + permission.toString() + ".");
                    	return;
                    }
					
					String passwd = passwordGenerateService.generatePassword();

					String encrypdpasswd = "PasswordNotEncrypted#$%^Invalid";
					try {
						encrypdpasswd = LoginHelper.encodePassword(passwd);
					}
					catch(NoSuchAlgorithmException nsae) {
						nsae.printStackTrace();					
					}
					catch(NullPointerException npe) {
						npe.printStackTrace();
					}
					addUserModel.setPassword(encrypdpasswd);

					userService.createUser(addUserModel, permission, institutionId);
					
					System.out.format("Created user %d %s with plain password %s%n", addUserModel.getId(), addUserModel.getEmailAddress(), passwd);

					// TODO: uncomment sendemail code below before deployment or related QA testing
					//SendEmail sendEmail = new SendEmail();
					//sendEmail.sendNewUserNotice(newUserModel, passwd); 
				}
				else
				{
					// USER EXISTS
					//   - confirm client wants to add this existing user as previously defined
					//     (ie, ignore name and user code as entered.  If client wants to change 
					//      any details, they should perform an edit operation on the user afterwards.)

					// At this point, the user already exists and may have a different name/code than what was entered
					// into the text fields.  We will ask the client to confirm if they want to add the pre-existing user
					// to the selected term.  This means that the info entered into the text fields is ignored and the
					// user is added to the term as previously defined.  If the admin intends to change the name/code info,
					// the admin can edit the user after it is added as described above.
					
					addUserModel = userEmailList.get(0);

					// Get the current institution-roles for the user
					Integer userPermId = userService.getPermissionModel(permission).getId();
					List<InstUserPermissionEntity> iupList = userService.getUserItems(addUserModel.getId());

			    	// Build the message for the dialog
					// Show the existing user data
			    	StringBuilder sbMessage = new StringBuilder();
			    	String se = String.format("There is an existing user for email address '%s' with the following details:\n", addUserModel.getEmailAddress());
			    	String sf = String.format("    First name:   %s\n", addUserModel.getFirstName());
			    	String sn = String.format("    Last name:    %s\n", addUserModel.getLastName());
			    	String sc = String.format("    Number:       %s\n\n", addUserModel.getStudentnumber() == null ? "--" : addUserModel.getStudentnumber());
			    	sbMessage.append(se).append(sf).append(sn).append(sc).append("and with the following roles:\n");
			    	
			    	// Add the institution-permission roles
			    	for (Integer rolecount = 0; rolecount < iupList.size(); rolecount++)
			    	{
			    		String permit = permissionService.getModel(iupList.get(rolecount).getPermissionId()).getPermissionString().toUpperCase();
			    		String instname = institutionService.findInstitutionById(iupList.get(rolecount).getInstitutionId()).getInstname();
			    		String sip = String.format("    %s at %s\n", permit, instname);
			    		sbMessage.append(sip);
			    	}
			    	
			    	// Add the question
			    	String sp = String.format("Do you want to add the %s permission to the user with the above details?", permission.toString().toUpperCase());
			    	sbMessage.append("\n").append(sp);

					// Ask the institution administrator whether to add the role for the found user
					final JDialog dialog = new JDialog();
			    	dialog.setAlwaysOnTop(true);    
			    	
			    	Object[] options = {"Cancel", "Add"};
			    	int option = JOptionPane.showOptionDialog(dialog, sbMessage.toString(),
			    			"Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			    			null, options, options[0]);

			    	if (option == 0)
			    	{
			    		// No, cancel the user creation.
			    		return;
			    	}
			    	
			    	// Add authorization if it does not exist
			    	userService.addAuthorization(institutionId, addUserModel, userPermId);
				}

				// Add user to the selected term
				if (permission == Permission.STUDENT)
				{
					termService.addStudentToList(addUserModel, destinationTerm.getId());
				}
				else if (permission == Permission.TEACHER)
				{
					termService.addTeacherToList(addUserModel, destinationTerm.getId());
				}
				else if (permission == Permission.TA)
				{
					termService.addTaToList(addUserModel, destinationTerm.getId());
				}
			}
			
			session.setUserPageTermId(destinationTerm.getId());
			setResponsePage(new UserEditPage(permission));
		}
	}
}