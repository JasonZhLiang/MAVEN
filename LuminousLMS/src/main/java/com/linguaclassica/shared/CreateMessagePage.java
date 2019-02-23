package com.linguaclassica.shared;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.StringValidator;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.EntityMessageModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.MessageModel.Status;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.MessageService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;


public class CreateMessagePage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;

	@SpringBean
	MessageService messageService;

	@SpringBean
	UserService userService;

	@SpringBean
	PermissionService permissionService;
	
	private Logger logger = LogManager.getLogger(CreateMessagePage.class);
	
	public CreateMessagePage()
	{
		super();
		
		Form<Object> form = new CreateMessageForm();
		add(form);
	}
	
	class AreaStringValidator extends StringValidator
	{
		private static final long serialVersionUID = 1L;
		
		public AreaStringValidator(Integer minimum, Integer maximum)
		{
			setRange(minimum, maximum);
		}

		@Override
		protected Integer getValue(IValidatable<String> validatable)
		{
			return validatable.getValue().replace("\r\n", "\n").length();
		}
	}
	
	class CreateMessageForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		TextField<String> subjecttf;
		TextArea<String> messageta;
		
		EntityMessageModel entityMessageModel;
		EntityUserModel destinationUserModel;
		
		
		UserModel userModel = LMSSession.get().getUser(getRequest());
		

		public CreateMessageForm()
		{
			super("createmessageform");
			
			entityMessageModel = (EntityMessageModel) modelFactory.getNewMessageModel();
			destinationUserModel = (EntityUserModel) modelFactory.getNewUserModel();
			
			entityMessageModel.setFromId(userModel.getId());
			
			subjecttf = new TextField<String>("subjecttf",
					new PropertyModel<String>(entityMessageModel,"subject"));
			subjecttf.setRequired(true);
			subjecttf.add(StringValidator.maximumLength(50));
			
			messageta = new TextArea<String>("messageta",
					new PropertyModel<String>(entityMessageModel,"message"));
			messageta.setRequired(true);
			messageta.add(new AreaStringValidator(null, 511));
			
			List<EntityUserModel> receiverEntityList = null;

			Integer upermid = LMSSession.get().getCurrentPermission();
			Permission upermission = permissionService.getModel(upermid).getPermission();
			String labelid = null;
			if (upermission == Permission.CLIENT)
			{
				labelid = "createmessageconl";
				receiverEntityList = userService.getListOfConsultantOfAClient(currentOrg.getId(), userModel.getId());
			}
			else if (upermission == Permission.CONSULTANT)
			{
				labelid = "createmessagecll";
				receiverEntityList = userService.getListOfClientsOfAConsultant(currentOrg.getId(), userModel.getId());
			}
			add(new Label("clientsddl", new ResourceModel(labelid)));

			add(new Label("subjectcharlimitl", new ResourceModel("charlimitl")));
			add(new Label("messagecharlimitl", new ResourceModel("charlimitl")));
			
			DropDownChoice<EntityUserModel> receiversDropDown = new DropDownChoice<EntityUserModel>("clientsdd", 
					new PropertyModel<EntityUserModel>(this, "destinationUserModel"), receiverEntityList,
					new UsernameRenderer());
			
			if (upermission == Permission.CLIENT)
			{
				receiversDropDown.setNullValid(false);
			}
			else if (upermission == Permission.CONSULTANT)
			{
				receiversDropDown.setNullValid(true);
			}
			receiversDropDown.setRequired(true);
			
			add(subjecttf);
			add(messageta);
			add(receiversDropDown);
		}
		
		
		public void onSubmit()
		{
			try
			{
				Date date = new Date();
				entityMessageModel.setCreateDate(new Timestamp(date.getTime()));
				entityMessageModel.setToId(destinationUserModel.getId());
				entityMessageModel.setOrgId(currentOrg.getId());
				entityMessageModel.setMessage(entityMessageModel.getMessage().replace("\r\n", "\n"));
				entityMessageModel.setStatus(Status.UNREAD);
				entityMessageModel.setTimeZone("EST");
				messageService.createMessage(entityMessageModel);
				
				PageParameters paramMESS = new PageParameters();
				paramMESS.add("msg", getString("messagesent"));
				setResponsePage(MessagePage.class, paramMESS);
			}
			catch(Exception e)
			{
				logger.error("Failed to create a message from " + userModel.getId() + " to " + destinationUserModel.getId() + ".");
				logger.error(e.toString());
				error(getString("exceptionerr"));	
			}
		}
	}
	

}
