package com.linguaclassica.officeadmin;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.validation.validator.StringValidator;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.access.LoginHelper;
import com.linguaclassica.access.NameValidator;
import com.linguaclassica.access.SendEmail;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.entity.OrgUserPermissionEntity;

import com.linguaclassica.model.ConsultantClientsModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.ConsultantClientsService;
import com.linguaclassica.service.OrganizationService;
import com.linguaclassica.service.PasswordGenerateService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.shared.DialogConfirmPage;
import com.linguaclassica.shared.PrivateBasePage;

public class UserCreatePage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;

	@SpringBean
	private OrganizationService organizationService; 

	@SpringBean
	private PermissionService permissionService;

	@SpringBean
	UserService userService;
	
	@SpringBean
	ConsultantClientsService consultantClientsService;
	
	@SpringBean
	PasswordGenerateService passwordGenerateService;

	private Logger logger = LogManager.getLogger(UserCreatePage.class);

	private Permission permission;
	private int userid;
	Integer status = null;
	Boolean statusConfirmed = false;
    private Integer orgId;
	EntityUserModel addUserModel;
	
    private RadioGroup<EntityUserModel> radioGroup = null;
    ListView<EntityUserModel> list = null;
    
	private String userfirstname = null;
	private String userlastname = null;
	private String useremail = null;
	
	static  ArrayList<String> listHeaders = null;
    //////////////////////////Dialog messages - English and French
	

	public UserCreatePage(PageParameters params)
	{
		super(params);
		
		orgId = LMSSession.get().getCurrentOrganization();

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
		StringValue sv3 = params.get("userstatus");
		if (sv3 != null)
		{
			String usrStatus = sv3.toString();
			if (usrStatus !=null)
			{
				status = Integer.parseInt(usrStatus);
				statusConfirmed = (status != null);
			}
		}
		if (permission == Permission.CLIENT && statusConfirmed){
			StringValue sv5 = params.get("userfirstname");
			if (sv5 != null){
				String confirmString = sv5.toString();
				if (confirmString !=null){
					userfirstname = confirmString;
				}
			}
			StringValue sv6 = params.get("userlastname");
			if (sv6 != null){
				String confirmString = sv6.toString();
				if (confirmString !=null){
					userlastname = confirmString;
				}
			}
			StringValue sv7 = params.get("useremail");
			if (sv7 != null){
				String confirmString = sv7.toString();
				if (confirmString !=null){
					useremail = confirmString;
				}
			}
		}
		logger.debug("UserCreatePage()    permission= " + permission );
		logger.debug("UserCreatePage()    userid= " + userid );

		getProperties();//must be called before the form is created in order to retrieve the lables from the property files
		Form<Object> form = new UserForm(getString("lanswer"),getString("lquestion"));
		add(form);
	}

	class UserForm extends Form<Object> 
	{
		private static final long serialVersionUID = 1L;
		
		TextField<String> firstnametf;
		TextField<String> lastnametf;
		EmailTextField emailaddresstf;
		TextField<String> questiontf;
		TextField<String> answertf;
		DropDownChoice<String> userStatusChoice;
		String password = "Password will be generated and send automatically";
		final List<String> userStatusList = 
			    Collections.unmodifiableList(Arrays.asList("Active", "Temporary Inactive", "Archived"));
		String userStatus = userStatusList.get(0);
		
		String userstring = new String("");
		String statustring = new String("");
		List<EntityUserModel> UserEntityList = new ArrayList<EntityUserModel>();

		public UserForm(String questionlbl, String answerlbl)
		{
			super("userform");
		
			if (userid > 0)  // This is an EDIT action  (ie, edit button clicked in a user list.
			{
				addUserModel = (EntityUserModel) userService.findUserById(userid);
				add (new Label("actionlabel", "Edit"));
				password = addUserModel.getPassword();
			}
			else  // This is a NEW user action (ie, from the user new sub-menu, for example Teacher -> New Teacher)
			{
				addUserModel = (EntityUserModel) modelFactory.getNewUserModel();
				add (new Label("actionlabel", "Create new"));
				if (permission == Permission.CLIENT && statusConfirmed){
					addUserModel.setFirstName(userfirstname);
					addUserModel.setLastName(userlastname);
					addUserModel.setEmailAddress(useremail);
				}
				
			}
			
			firstnametf = new TextField<String>("firstnametf",
					new PropertyModel<String>(addUserModel,"firstName"));
			lastnametf = new TextField<String>("lastnametf",
					new PropertyModel<String>(addUserModel,"lastName"));
			emailaddresstf = new EmailTextField("emailaddresstf",
					new PropertyModel<String>(addUserModel,"emailAddress"));

			questiontf = new TextField<String>("questiontf",
					new PropertyModel<String>(addUserModel,"question"));
			answertf = new TextField<String>("answertf",
					new PropertyModel<String>(addUserModel,"answer"));
			///no security questions for Clients
			questiontf.setVisible(permission != Permission.CLIENT );
			answertf.setVisible(permission != Permission.CLIENT );
			add(new Label ("lquestion", questionlbl).setVisible(permission != Permission.CLIENT ));
			add(new Label ("lanswer", answerlbl).setVisible(permission != Permission.CLIENT ));
						
			if(status == null){// if status was not sent as a parameter - retrieve from the model
				status = addUserModel.getStatus();
				if(status == null){
					status = 0;// default
					addUserModel.setStatus(0);
				}
			}
			else{
				addUserModel.setStatus(status);
			}
			userStatus = userStatusList.get(status);

			// Note:  setting emailaddress as the only required field strictly enforced within the form.
			//        This allows to add an existing user to the selected term by specifying their email
			//        address only, and then we will display the name and code details and confirm the 
			//        add action.  For the case of a brand new user (ie, user with existing email not 
			//        found), we will check for and enforce name to be entered as well.
			emailaddresstf.setRequired(true);
	
			firstnametf.add(new NameValidator());
			lastnametf.add(new NameValidator());
			questiontf.add(StringValidator.lengthBetween(1, 50));
			answertf.add(StringValidator.lengthBetween(1, 50));
			
			add(firstnametf);
			add(lastnametf);
			add(emailaddresstf);
			add(questiontf);
			add(answertf);
			
			
			userStatusChoice = new DropDownChoice<String>("userStatusChoice", 
					new PropertyModel<String>(this,"userStatus"),userStatusList);
			userStatusChoice.add(new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;
	
					@Override
					protected void onUpdate(AjaxRequestTarget target){ 
						statusConfirmed = false;
						addUserModel.setStatus(userStatusList.indexOf(userStatus));
					}			
			});
			
			userStatusChoice.setOutputMarkupId(true);
			add(userStatusChoice);

			if (permission == Permission.CLIENT)
			{
				userstring = "Client";
			}
			else if (permission == Permission.CONSULTANT)
			{
				userstring = "Consultant";
			}
			else if (permission == Permission.OFFICE_ADMIN)
			{
				userstring = "Office Administrator";
			}
			
			add (new Label("userlabel", userstring));
			add (new Label("statuslabel", statustring));
			ShowUnassignedAvialableList(permission);
		}

		public void onSubmit()
		{
			logger.debug("UserCreatePage()  onSubmit userid= " + addUserModel.getId() + ", " + userid);

			if(!statusChangeCheck(permission)){
				return;
			}
			
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
						Session.get().error(getString("msgDuplicateEntry"));
						
						logger.error("Error:  Save failed.  Duplicate entry, e.getMessage = " + e.getMessage());
					}
					else
					{
						Session.get().error(String.format(getString("msgSaveFailedEmail"), emailaddresstf.getModelObject()));
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
                    	Session.get().error(String.format(getString("msgfirstnamerequired"),permission.toString()));          
                    	return;
                    }

                    if (lastnametf.getModelObject() == null || lastnametf.getModelObject().isEmpty()) {
                    	Session.get().error(String.format(getString("msglastnamerequired"),permission.toString())); 
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

					userService.createUser(addUserModel, permission, orgId);
					userid = addUserModel.getId();// assign the new id

					// TODO: remove line below before deployment 
					System.out.format("Created user %d %s with plain password %s%n", addUserModel.getId(), addUserModel.getEmailAddress(), passwd);

					// TODO: uncomment sendemail code below before deployment or related QA testing
					SendEmail sendEmail = new SendEmail();
					sendEmail.sendPassword(addUserModel.getEmailAddress(), passwd); 
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
					List<OrgUserPermissionEntity> oupList = userService.getUserItems(addUserModel.getId());

			    	// Build the message for the dialog
					// Show the existing user data
			    	StringBuilder sbMessage = new StringBuilder();
                    String se = String.format("There is an existing user for email address '%s' with the following details:<br><br>", addUserModel.getEmailAddress());
                    String sf = String.format("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;First name:&nbsp;&nbsp;&nbsp;%s<br>", addUserModel.getFirstName());
                    String sn = String.format("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Last name:&nbsp;&nbsp;&nbsp;&nbsp;%s<br>", addUserModel.getLastName());
                    sbMessage.append(se).append(sf).append(sn).append("<br>and with the following roles:<br><br>");
			    	
			    	boolean sameOrgDiffRole = false;  	// true means trying to add existing user with different role
			    										// false means trying to add existing user with same role - WHICH IS NOT ALLOWED
			    	
			    	// Add the institution-permission roles
			    	for (Integer rolecount = 0; rolecount < oupList.size(); rolecount++)
			    	{
			    		String permit = permissionService.getModel(oupList.get(rolecount).getPermissionId()).getPermissionString().toUpperCase();
			    		String instname = organizationService.findOrganizationById(oupList.get(rolecount).getOrganizationId()).getOrgname();

			    		if (oupList.get(rolecount).getOrganizationId().equals(orgId))
				    	{
			    			if (permit.equals(permission.toString()))
			    			{
			    				// This user already has this role within this organization.  Display a warning and return.
			                    
			    				String msg = String.format(getString("msguseremailexists"), addUserModel.getEmailAddress(),permit, instname);
			    				logger.warn(msg);
			    				Session.get().error(msg);
			    				return;
			    			}

			    			// only include this role/organization info for the current organization (This is for privacy type concerns
			    			// whereby we should not show information about this user's belonging to another organization).
			    			String sip = String.format("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;%s at %s<br>", permit, instname);
				    		sbMessage.append(sip);
				    		
				    		sameOrgDiffRole = true;
			    		}
			    	}
			    	
			    	// Add the question
			    	String sp = String.format("<br>Please click the Confirm button below to add the %s permission to the user <br>with the details as shown.<br><br>", permission.toString().toUpperCase());
			    	sbMessage.append("\n").append(sp);

			    	// If adding a new role to an existing user in the same organization then display the confirm dialog below.
			    	// If adding a new role to an existing user but in a different organization for the first time, do not 
			    	// display the dialog (ie, in the different organization, there are no other roles currently assigned 
			    	// to this user so no need to display this dialog.  However, the first name and last name if entered will
			    	// not be used - the names from the existing user in the database will remain, this is because a user is
			    	// not created but only the new role is added to the existing user.).
			    	if (sameOrgDiffRole)
			    	{
			    		// Ask the institution administrator whether to add the role for the found user
                        logger.debug("Must confirm adding user in SAME ORG but DIFF ROLE");

                        PageParameters params = new PageParameters();
                        params.add("usertype", permission);
                        params.add("userid", addUserModel.getId());
                        params.add("confirmMsg", sbMessage.toString());

                        if (permission.equals(Permission.CLIENT))
                        {
                                EntityUserModel eum = getSelectedConsultant();
                                if (eum != null) {
                                	params.add("consultId", eum.getId());
                                }
                        }

                        setResponsePage(UserCreateConfirmPage.class, params);
                        return;
			    	}
			    	
			    	// Add authorization if it does not exist
			    	userService.addAuthorization(orgId, addUserModel, userPermId);
				}
			}
			AssignClienttoConsultant(permission, addUserModel.getStatus());
			setResponsePage(new OfficeAdminConsultantsPage());
		}
		////////					
		private Boolean statusChangeCheck(Permission PermissionType){
		    
		    if (addUserModel.getStatus() > 0){//if status change to non-active
				if(permission == Permission.CONSULTANT && UserEntityList != null && UserEntityList.size() > 0 && !UserEntityList.get(0).getFirstName().equals("No")){					

					AskForInput(String.format(getString("msgonsultanHasClients"),addUserModel.getFirstName(),addUserModel.getLastName()));	
					return false;
				}
				else if(permission == Permission.CLIENT  && !statusConfirmed){
					
					AskForInput(String.format(getString("msgClientUnassigned"),addUserModel.getFirstName(),addUserModel.getLastName()));	
					return false;
				}
			}
		    else if(addUserModel.getStatus() == 0 && permission == Permission.CLIENT && !statusConfirmed && radioGroup.getConvertedInput()== null){
				AskForInput(String.format(getString("msgUnassignedActiveClient"),addUserModel.getFirstName(),addUserModel.getLastName()));	
				return false;
		    }
		   
		    return true;
	    }
		////////
		private void ShowUnassignedAvialableList(Permission PermissionType){
			
			Boolean show = true;
			String avialableuserlabel = "";
						
			
			if (PermissionType == Permission.CLIENT){
				UserEntityList = userService.getListOfUsersByPermission(orgId, Permission.CONSULTANT);
				if(UserEntityList != null && !UserEntityList.isEmpty()){
					avialableuserlabel = listHeaders.get(0);//getString("lclientlist");
				}
			}
			else if (PermissionType == Permission.CONSULTANT){
				UserEntityList = userService.getListOfClientsOfAConsultant(orgId, userid);
				if(UserEntityList != null && !UserEntityList.isEmpty()){
					avialableuserlabel = listHeaders.get(1);//getString("lconsultantlist");
				}
			}
			
			if(UserEntityList == null || UserEntityList.isEmpty()){
				UserEntityList.add(new EntityUserModel(0, "No", "Entries", "","", 0));
				show = false;
			}
			
	     
			int consultantID = consultantClientsService.getConsultantIdForClient(orgId, userid);
						
			add (new Label("avialableuserlabel", avialableuserlabel).setVisibilityAllowed(PermissionType == Permission.CLIENT ||PermissionType == Permission.CONSULTANT));
			radioGroup = new RadioGroup<EntityUserModel>("radiogroup", new Model<EntityUserModel>());
			
			list = new ListView<EntityUserModel>("userlistview",
					UserEntityList){
				private static final long serialVersionUID = 1L;
		
					@SuppressWarnings({ "rawtypes" })
				protected void populateItem(ListItem<EntityUserModel> item){
				
						EntityUserModel user = (EntityUserModel) item.getModelObject();
					
						item.add(new Label("username", user.getFirstName() + " " + user.getLastName()).setVisible(PermissionType == Permission.CLIENT && user.getStatus()==0));
						
						@SuppressWarnings("unchecked")
						Radio radioButton = new Radio("radioselect",new Model<EntityUserModel>(user), radioGroup);
						radioButton.setMarkupId("radioselect" + item.getIndex());
						item.add(radioButton.setVisible(PermissionType == Permission.CLIENT && user.getStatus()==0));
						radioButton.setOutputMarkupId(true);
						if(consultantID == user.getId()){
							radioGroup.setConvertedInput(user);
							radioGroup.updateModel();
						}
						Link<Object> link = new Link<Object>("clientname"){
							private static final long serialVersionUID = 1L;
							@Override
							public void onClick() {
								PageParameters paramEditClient = new PageParameters();
								paramEditClient.add("usertype", Permission.CLIENT);
								paramEditClient.add("userid",user.getId());
								setResponsePage(UserCreatePage.class, paramEditClient);
							}			
						};
						link.add(new Label("clientlabel",user.getFirstName()+ " " + user.getLastName()));
						item.add(link.setVisible(PermissionType == Permission.CONSULTANT));
						
					}
			};
			
			list.setOutputMarkupId(true);
			list.setVisible(show);
			radioGroup.add(list);
			radioGroup.setOutputMarkupId(true);
			radioGroup.add( new AjaxFormChoiceComponentUpdatingBehavior() { 
				private static final long	serialVersionUID	= 1L;
	
				@Override
				protected void onUpdate(AjaxRequestTarget target) {		
					if(addUserModel.getStatus() > 0){//if the user is not active	
						Session.get().error("Error: User " + addUserModel.getFirstName() + " " + addUserModel.getLastName() + " is not active. " + "Only active users can be assigned. Please try again.");
						radioGroup.setConvertedInput(null);
						radioGroup.updateModel();
						//target.add(radioGroup);
						target.add(target.getPage());
					}
				}					
			});	
			
			add(radioGroup);
			
		}
	}
	
	private void AssignClienttoConsultant(Permission PermissionType, Integer status){
	
		if (permission != Permission.CLIENT)
			return;
		
		EntityUserModel user = null;	
			//get selected ConsutantModel from radio group
		user = (EntityUserModel)radioGroup.getConvertedInput();
		if(user != null && userid > 0){
			int consultantID = consultantClientsService.getConsultantIdForClient(orgId, userid);
			//is status has changed from active and the Consultant exists- remove Consultant Client connections
			if(status > 0 && consultantID > 0){
				consultantClientsService.closeOne(orgId, consultantID, userid);
				return;
			}
			if(consultantID == user.getId() ){
				return;//same person as before
			}
			else if(consultantID > 0){
				consultantClientsService.closeOne(orgId, consultantID, userid);
			}
			ConsultantClientsModel ccModel =  modelFactory.getNewConsultantClientsModel();
			ccModel.setClientId(userid);
			ccModel.setConsultantId(user.getId());
			ccModel.setOrganizationId(orgId);
		
			consultantClientsService.createOne(ccModel);
		}

		return;

	}

    private EntityUserModel getSelectedConsultant()
    {
            EntityUserModel user = null;
                    //get selected ConsutantModel from radio group
            user = (EntityUserModel)radioGroup.getConvertedInput();

            return user;
    }
    
	public void AskForInput(String Message){
		// Add the question
    	Message += (getString("msgConfirm"));

    	// If adding a new role to an existing user in the same organization then display the confirm dialog below.
    	// If adding a new role to an existing user but in a different organization for the first time, do not 
    	// display the dialog (ie, in the different organization, there are no other roles currently assigned 
    	// to this user so no need to display this dialog.  However, the first name and last name if entered will
    	// not be used - the names from the existing user in the database will remain, this is because a user is
    	// not created but only the new role is added to the existing user.).
    	
    	    PageParameters params = new PageParameters();
            params.add("usertype", permission);
            params.add("userid", addUserModel.getId());
            params.add("userstatus", addUserModel.getStatus());
            params.add("confirmMsg", Message);
            if (permission == Permission.CLIENT){
	            params.add("userfirstname", addUserModel.getFirstName());
	            params.add("userlastname", addUserModel.getLastName());
	            params.add("useremail", addUserModel.getEmailAddress());
            }
           

            setResponsePage(DialogConfirmPage.class, params);
            return;
    	
		
	}
    
    void getProperties(){
    	listHeaders = new ArrayList<String>();
    	listHeaders.add(getString("lconsultantlist"));	
    	listHeaders.add(getString("lclientlist"));
    	
    }
}