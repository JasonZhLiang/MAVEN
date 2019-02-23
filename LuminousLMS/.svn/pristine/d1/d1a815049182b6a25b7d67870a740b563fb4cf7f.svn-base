package com.linguaclassica.officeadmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.linguaclassica.access.LMSSession;
import com.linguaclassica.access.NameValidator;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.ConsultantClientsModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.ConsultantClientsService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.shared.PrivateBasePage;

public class UserCreateConfirmPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;

	@SpringBean
	UserService userService;
	
	@SpringBean
	ConsultantClientsService consultantClientsService;
	
	private Logger logger = LogManager.getLogger(UserCreateConfirmPage.class);

	private Permission permission;
	private int userid = 0;
	private int assignedConsultantId = 0;
	private String confirmMessage = null;
 
    private RadioGroup<EntityUserModel> radioGroup = null;
    ListView<EntityUserModel> list = null;
    
	public UserCreateConfirmPage(PageParameters params)
	{
		super(params);
		
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
		
		StringValue sv3 = params.get("consultId");
		if (sv3 != null)
		{
			String consstr = sv3.toString();
			if (consstr !=null)
			{
				assignedConsultantId = Integer.parseInt(consstr);
			}
		}
		
		StringValue sv4 = params.get("confirmMsg");
		if (sv4 != null)
		{
			String confirmString = sv4.toString();
			if (confirmString !=null)
			{
				confirmMessage = confirmString;
			}
		}
		
		logger.debug("UserCreateConfirmPage()    permission= " + permission );
		logger.debug("UserCreateConfirmPage()    userid= " + userid );

		Form<Object> form = new UserForm();
		add(form);
	}

	class UserForm extends Form<Object> 
	{
		private static final long serialVersionUID = 1L;

		EntityUserModel addUserModel;
		
		TextField<String> firstnametf;
		TextField<String> lastnametf;
		EmailTextField emailaddresstf;
		DropDownChoice<String> userStatusChoice;
		
		final List<String> userStatusList = 
			    Collections.unmodifiableList(Arrays.asList("Active", "Temporary Inactive", "Archived"));
		
		String userStatus = userStatusList.get(0);
		
		String userstring = new String("");

		public UserForm()
		{
			super("userform");
			
			if (userid > 0)  // Must have a valid userid since this is a CONFIRM action  
			{
				addUserModel = (EntityUserModel) userService.findUserById(userid);
				add (new Label("actionlabel", "Create new"));
			}
			else 
			{
				logger.error("invalid userid passed, userid=" + userid);
			}
			
			firstnametf = new TextField<String>("firstnametf",
					new PropertyModel<String>(addUserModel,"firstName"));
			lastnametf = new TextField<String>("lastnametf",
					new PropertyModel<String>(addUserModel,"lastName"));
			emailaddresstf = new EmailTextField("emailaddresstf",
					new PropertyModel<String>(addUserModel,"emailAddress"));
			
			emailaddresstf.setRequired(true);
			
			firstnametf.add(new NameValidator());
			lastnametf.add(new NameValidator());

			add(firstnametf);
			add(lastnametf);
			add(emailaddresstf);
			
			Integer status = addUserModel.getStatus();
			if(status == null){
				status = 0;// default
				addUserModel.setStatus(0);
			}
			userStatus = userStatusList.get(status);

			userStatusChoice = new DropDownChoice<String>("userStatusChoice", 
					new PropertyModel<String>(this,"userStatus"),userStatusList);
			userStatusChoice.add(new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;
	
					@Override
					protected void onUpdate(AjaxRequestTarget target){ 
					
					}			
			});
			
			userStatusChoice.setOutputMarkupId(true);
			add(userStatusChoice);

			firstnametf.setEnabled(false);
			lastnametf.setEnabled(false);
			emailaddresstf.setEnabled(false);
			userStatusChoice.setEnabled(false);

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
			ShowUnassignedAvialableList(permission);
			
			if (assignedConsultantId > 0) {
				EntityUserModel eum = (EntityUserModel) userService.findUserById(assignedConsultantId);
				logger.debug("UserCreateConfirmPage() ... consultant = " + eum.getEmailAddress()); 
			}
			
			add(new Label("confirmmsg", confirmMessage).setEscapeModelStrings(false));
            add(new Link<Object>("backbutton") {
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick() {
                        System.out.println("Back button clicked.");
                        PageParameters params = new PageParameters();
                        params.add("usertype", permission);

                        setResponsePage(UserCreatePage.class, params);
                }
        });

		}

		public void onSubmit()
		{
			logger.debug("UserCreateConfirmPage()  onSubmit userid= " + addUserModel.getId() + ", " + userid);
			
			if (userid > 0)   // userid must be >0, otherwise error
			{
				Integer userPermId = userService.getPermissionModel(permission).getId();
				
		    	userService.addAuthorization(currentOrg.getId(), addUserModel, userPermId);
				AssignClienttoConsultant(permission);
				setResponsePage(new OfficeAdminConsultantsPage());
			}
			else
			{
				logger.error("confirm onSubmit, userid must be >0, userid=" + userid);
			}
		}
		
		private void ShowUnassignedAvialableList(Permission PermissionType){
			
			Boolean show = true;
			String avialableuserlabel = "";
						
			List<EntityUserModel> UserEntityList = new ArrayList<EntityUserModel>();
			if (PermissionType == Permission.CLIENT){
				UserEntityList = userService.getListOfUsersByPermission(currentOrg.getId(), Permission.CONSULTANT);
				avialableuserlabel = "List of Available Consultants";
			}
			
			if(UserEntityList == null || UserEntityList.isEmpty()){
				UserEntityList.add(new EntityUserModel(0, "No", "Entries", "","", 0));
				show = false;
			}
			
	     
			//int consultantID = consultantClientsService.getConsultantIdForClient(currentOrg.getId(), userid);
			int consultantID = assignedConsultantId;
						
			add (new Label("avialableuserlabel", avialableuserlabel).setVisibilityAllowed(PermissionType == Permission.CLIENT ||PermissionType == Permission.CONSULTANT));
			radioGroup = new RadioGroup<EntityUserModel>("radiogroup", new Model<EntityUserModel>());
			
			list = new ListView<EntityUserModel>("userlistview",
					UserEntityList){
				private static final long serialVersionUID = 1L;
		
					@SuppressWarnings({ "rawtypes" })
				protected void populateItem(ListItem<EntityUserModel> item){
				
						EntityUserModel user = (EntityUserModel) item.getModelObject();
		
						item.add(new Label("username", user.getFirstName() + " " + user.getLastName()));
					
						@SuppressWarnings("unchecked")
						Radio radioButton = new Radio("radioselect",new Model<EntityUserModel>(user), radioGroup);
						radioButton.setMarkupId("radioselect" + item.getIndex());
						item.add(radioButton.setVisible(PermissionType == Permission.CLIENT));
						radioButton.setOutputMarkupId(true);
						if(consultantID == user.getId()){
							radioGroup.setConvertedInput(user);
							radioGroup.updateModel();
						}
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
					
				}					
			});	
			
			add(radioGroup);
			radioGroup.setEnabled(false);
		}
	}
	
	private void AssignClienttoConsultant(Permission PermissionType){
	
		if (permission != Permission.CLIENT)
			return;
		
		EntityUserModel user = null;	
			//get selected ConsutantModel from radio group
		user = (EntityUserModel)radioGroup.getConvertedInput();
		if(user != null && userid > 0){
			int consultantID = consultantClientsService.getConsultantIdForClient(currentOrg.getId(), userid);
			if(consultantID == user.getId()){
				return;//same person as before
			}
			else if(consultantID > 0){
				consultantClientsService.deleteOne(currentOrg.getId(), consultantID, userid);
			}
			ConsultantClientsModel ccModel =  modelFactory.getNewConsultantClientsModel();
			ccModel.setClientId(userid);
			ccModel.setConsultantId(user.getId());
			ccModel.setOrganizationId(currentOrg.getId());
		
			consultantClientsService.createOne(ccModel);
		}

		return;
	}
}