package com.linguaclassica.student;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class StudentMenuErrorPage extends StudentBasePage
{
	private static final long serialVersionUID = 1L;
	
	public StudentMenuErrorPage()
	{
		super();
		
		System.out.println("StudentMenuErrorPage");
		
		buildMenuErrorForm("No message passed.");
	}
	
	public StudentMenuErrorPage(PageParameters params)
	{
		super();
		
		System.out.println("StudentMenuErrorPage with message");
		StringValue sv = params.get("msg");
		if (sv != null)
		{
			String message = sv.toString();
			if (message !=null)
			{
				buildMenuErrorForm(message);
				return;
			}
		}
		
		buildMenuErrorForm("Incorrect message parameter passed.");
	}
	
	class MenuErrorForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		public MenuErrorForm(String id)
		{
			super(id);
		}
	}

	private void buildMenuErrorForm(String message)
	{
		System.out.println("MenuErrorForm.buildMenuErrorForm");

		MenuErrorForm form = new MenuErrorForm("smenuerrorf");
        form.add(new Label("message", message));	
		add(form);	  
	}
}
