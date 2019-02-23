package com.linguaclassica.teacher;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;


public class TeacherMessagePage extends TeacherBasePage
{
	private static final long serialVersionUID = 1L;
	
	public TeacherMessagePage()
	{
		super();
		
		System.out.print("TeacherMessagePage");
		
		buildMessageForm("No message passed.");
	}
	
	class MessageForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		public MessageForm(String id)
		{
			super(id);
		}
	}

	private void buildMessageForm(String message)
	{
		System.out.println("MessageForm.buildMessageForm");

		MessageForm form = new MessageForm("temessagef");
        form.add(new Label("message", message));	
		add(form);	  
	}

}
