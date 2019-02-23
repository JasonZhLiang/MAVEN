package com.linguaclassica.shared;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class MessagePage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LogManager.getLogger(MessagePage.class);

	public MessagePage()
	{
		super();
		
		MessageForm form = new MessageForm("MessagePage no message");
		
        add(form);	  
	}
	
	public MessagePage(PageParameters parameters)
	{
		super(parameters);
		
		// The parameter should contain a message
		StringValue sv = parameters.get("msg");
		if (sv != null)
		{
			String message = sv.toString();
			if (message !=null)
			{
				logger.debug("(message length " + message.length() + ")");
				MessageForm form = new MessageForm(message);
				add(form);	  
				return;
			}
		}

		// The parameter was defective
		MessageForm form = new MessageForm("Incorrect message parameter passed.");
		add(form);	  
	}
	
	class MessageForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		public MessageForm(String message)
		{
			super("messagef");
			
			add(new Label("messagel", message));
		}
	}

}
