package com.linguaclassica.shared;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.EntityMessageModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.MessageModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.MessageService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;

public class ViewMessagesPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;

	@SpringBean
	UserService userService;
	
	@SpringBean
	MessageService messageService;
	
	@SpringBean
	PermissionService permissionService;
	
	@SpringBean
	ModelFactory modelFactory;

	private Logger logger = LogManager.getLogger(getClass());
	
	private final UserModel userModel = LMSSession.get().getUser(getRequest());
	private List<EntityMessageModel> messageList = new ArrayList<EntityMessageModel>();
	
	EntityUserModel destinationUserModel;

	// ViewMessagesPage constructor invoked with no argument retrieves all messages where the
	// currently logged in user is either the sender or receiver of the message
	public ViewMessagesPage()
	{
		this(null);
	}
	
	// ViewMessagesPage constructor invoked with an argument (userid) retrieves only messages
	// exchanged between the currently logged in user and the user specified in the argument.  
	public ViewMessagesPage(Integer otherId)
	{
		super();
		
		if (otherId == null)
		{
			destinationUserModel = (EntityUserModel) modelFactory.getNewUserModel();
		}
		else
		{
			destinationUserModel = (EntityUserModel) userService.findUserById(otherId);			
		}
		
		Integer userId = userModel.getId();
		try
		{
			if (otherId == null)
			{	
				// retrieve all messages from all users exchanged with the logged in user 
				messageList = messageService.findMessagesByUserid(userId, currentOrg.getId());
			}
			else
			{
				// retrieve only messages exchanged between the logged in user and the user having otherId
				messageList = messageService.findMessagesByUserids(userId, otherId, currentOrg.getId());
			}
		}
		catch (Exception e)
		{
			logger.error("Unexpected error when getting messages for user: '" + userModel.getEmailAddress().trim() + "' " + e.getMessage());
			error("An error occurred. If the problem persists please contact your system administrator.");
		}
				
		ListView<EntityMessageModel> messageView = new ListView<EntityMessageModel>("messageview", messageList)
		{
			private static final long serialVersionUID = 1L;
			
			public boolean isVisible()
			{
				return messageList.size() > 0;
			}

			@Override
			protected void populateItem(ListItem<EntityMessageModel> item)
			{
				EntityMessageModel messages = item.getModelObject();

				Integer fromId = messages.getFromId();
				EntityUserModel fromUser = (EntityUserModel) userService.findUserById(fromId);
				String fromFirstName = fromUser.getFirstName();
				String fromLastName = fromUser.getLastName();

				Integer toId = messages.getToId();
				EntityUserModel toUser = (EntityUserModel) userService.findUserById(toId);
				String toFirstName = toUser.getFirstName();
				String toLastName = toUser.getLastName();

				String creationDate = messages.getCreateDate().toString();
				String  messageOutput = messages.getMessage();

				item.add(new Label("subject", item.getModelObject().getSubject()));
				item.add(new Label("from", fromFirstName + " " + fromLastName));
				item.add(new Label("to", toFirstName + " " + toLastName));
				item.add(new Label("createdate", creationDate));
				item.add(new MultiLineLabel("message", messageOutput));
				item.add(new Label("status", userId.equals(toId) ? messages.getStatus() : ""));
				item.add(new AjaxEventBehavior("click")
				{
					private static final long serialVersionUID = 1L;

					@Override
				    protected void onEvent(AjaxRequestTarget target)
					{
				    	EntityMessageModel messageModel = item.getModelObject();
				    	logger.info("messaging onEvent " + messageModel.getId());
				    	setResponsePage(new MessagingPage(messageModel, fromUser));
				    }
				});
				
				if (userId.equals(fromId)) 
				{
					item.add(new AttributeAppender("class", Model.of("frombackground")));
				}
				else {
					if (messages.getStatus() == MessageModel.Status.UNREAD)
					{
						item.add(new AttributeAppender("class", Model.of("tobackgroundunread")));
					}
					else
					{
						item.add(new AttributeAppender("class", Model.of("tobackground")));
					}
				}
				
			}
		};
		add(messageView);
		
		List<EntityUserModel> receiverEntityList = null;

		Integer upermid = LMSSession.get().getCurrentPermission();
		Permission upermission = permissionService.getModel(upermid).getPermission();
		String labelid = null;
		
		if (upermission == Permission.CLIENT)
		{
			labelid = "vmselectuserconl";
			receiverEntityList = userService.getListOfConsultantOfAClient(currentOrg.getId(), userModel.getId());
		}
		else if (upermission == Permission.CONSULTANT)
		{
			labelid = "vmselectusercll";
			receiverEntityList = userService.getListOfClientsOfAConsultant(currentOrg.getId(), userModel.getId());
		}
		add(new Label("vmselectuserl", new ResourceModel(labelid)));
		
		DropDownChoice<EntityUserModel> receiversDropDown = new DropDownChoice<EntityUserModel>("vmselectuser", 
				new PropertyModel<EntityUserModel>(this, "destinationUserModel"), receiverEntityList,
				new UsernameRenderer()) 
		{
			private static final long serialVersionUID = 1L;

            public boolean isVisible()
            {
                    return true;
            }

            protected boolean wantOnSelectionChangedNotifications()
            {
                    return true;
            }

            protected void onSelectionChanged(final EntityUserModel newSelection)
            {
            	if (newSelection == null)
            	{
            		logger.debug("onSelectionChanged(null)");
            		setResponsePage(new ViewMessagesPage());
            	}
            	else
            	{
            		logger.debug("onSelectionChanged(" + newSelection.getFirstName() + " " + newSelection.getLastName() + ")");
            		setResponsePage(new ViewMessagesPage(destinationUserModel.getId()));
            	}
            }
        };
		
		if (upermission == Permission.CLIENT)
		{
			receiversDropDown.setNullValid(false);
		}
		else if (upermission == Permission.CONSULTANT)
		{
			receiversDropDown.setNullValid(true);
		}
		receiversDropDown.setRequired(true);
		
		add(receiversDropDown);
	}
}
