package com.linguaclassica.shared;

import java.sql.Blob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.OrgsResourcesModel;
import com.linguaclassica.model.ResourceModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.officeadmin.OfficeAdminClientsPage;
import com.linguaclassica.officeadmin.OfficeAdminConsultantsPage;
import com.linguaclassica.officeadmin.OfficeAdminNotificationsPage;
import com.linguaclassica.officeadmin.UserCreatePage;
import com.linguaclassica.service.ConsultantClientsService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.shared.PrivateBasePage;

public class DialogConfirmPage extends PrivateBasePage{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;

	@SpringBean
	UserService userService;
	
	@SpringBean
	ConsultantClientsService consultantClientsService;
	
	private Logger logger = LogManager.getLogger(DialogConfirmPage.class);

	private Permission permission;
	private int userid = 0;
	private int assignedConsultantId = 0;
	private Integer status = null;
	private String confirmMessage = null;
	private String userfirstname = null;
	private String userlastname = null;
	private String useremail = null;
	
    
	public DialogConfirmPage(PageParameters params)
	{
		super(params);
	
		StringValue sv1 = params.get("usertype");
		if (sv1 != null){
			String usertype = sv1.toString();
			if (usertype !=null){
				permission = Permission.valueOf(usertype);
			}
		}

		StringValue sv2 = params.get("userid");
		if (sv2 != null){
			String userstr = sv2.toString();
			if (userstr !=null){
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

			}
		}
			
		StringValue sv4 = params.get("confirmMsg");
		if (sv4 != null){
			String confirmString = sv4.toString();
			if (confirmString !=null){
				confirmMessage = confirmString;
			}
		}
		if (permission == Permission.CLIENT){
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

		logger.debug("DialogConfirmPage()    permission= " + permission );
		logger.debug("DialogConfirmPage()    userid= " + userid );

		Form<Object> form = new UserForm();
		add(form);
	}

	class UserForm extends Form<Object> {
		private static final long serialVersionUID = 1L;

		EntityUserModel addUserModel;
		
				
		String userstring = new String("");

		public UserForm(){
			super("userform");
			
			if (userid > 0){  // Must have a valid userid since this is a CONFIRM action  
			
				addUserModel = (EntityUserModel) userService.findUserById(userid);
			}else{
				logger.error("invalid userid passed, userid=" + userid);
				
			}
					
			if (permission == Permission.CLIENT){
				userstring = "Client";
			}else if (permission == Permission.CONSULTANT){
				userstring = "Consultant";
			}
			else if (permission == Permission.OFFICE_ADMIN)
			{
				userstring = "Office Administrator";
			}
			
			if (assignedConsultantId > 0) {
				EntityUserModel eum = (EntityUserModel) userService.findUserById(assignedConsultantId);
				logger.debug("DialogConfirmPage() ... consultant = " + eum.getEmailAddress()); 
			}
			
			add(new Label("confirmmsg", confirmMessage).setEscapeModelStrings(false));
			
			add(new Button("backbutton").setOutputMarkupId(true).add( new AjaxFormComponentUpdatingBehavior("click") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget t){	
					 System.out.println("Back button clicked.");
                     if (permission == Permission.CLIENT){
                     	setResponsePage(OfficeAdminClientsPage.class);
         			}else if (permission == Permission.CONSULTANT){
         				setResponsePage(OfficeAdminConsultantsPage.class);
         			}
				}					
			})); 
		
		}
		public void onSubmit(){
					
			PageParameters params = new PageParameters();
	       
	        params.add("usertype", permission);
	        params.add("userid",userid);
	        params.add("userstatus",status);
	        if (permission == Permission.CLIENT ){
	       		
	            params.add("userfirstname", userfirstname);
	            params.add("userlastname", userlastname);
	            params.add("useremail", useremail);
	       	}
	      	setResponsePage(UserCreatePage.class, params);	
		}
	}

}