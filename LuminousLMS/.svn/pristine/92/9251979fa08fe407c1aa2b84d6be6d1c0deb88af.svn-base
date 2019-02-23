package com.linguaclassica.shared;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.entity.EntityMessageModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.MessageModel;
import com.linguaclassica.model.MessageModel.Status;
import com.linguaclassica.service.MessageService;

public class MessagingPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private MessageService messageService;

	public MessagingPage(EntityMessageModel messageModel, EntityUserModel user) {
		super();
		MessageModel outputMessage = messageService.findMessageById(messageModel.getId());
		outputMessage.setStatus(Status.READ);
		messageService.updateMessage(outputMessage);
		System.out.println("outputMessage.getMessage(): " + outputMessage.getMessage());
		
		MessagingForm form = new MessagingForm(messageModel, user);
		
        add(form);	  
	}

	public MessagingPage() {
		super();
		
		//This block of code is not being used
		/*MessageModel testMessage = messageService.findMessageById(1);
		
		System.out.println("testMessage.getMessage(): " + testMessage.getMessage());
		
		MessagingForm form = new MessagingForm(testMessage.getMessage());
		
        add(form);	  */
	}

	class MessagingForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		public MessagingForm(EntityMessageModel messageModel, EntityUserModel user)
		{
			super("messagingf");
			
			add(new Label("fromfld", user.getFirstName() + " " + user.getLastName()));
			add(new Label("subjectfld", messageModel.getSubject()));
			add(new Label("creationDatefld", messageModel.getCreateDate()));
			add(new MultiLineLabel("messagefld", messageModel.getMessage()));
			
		}
	}
}
