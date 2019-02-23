package com.linguaclassica.ta;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class TAMenuErrorPage extends TABasePage
{
	private static final long serialVersionUID = 1L;

	public TAMenuErrorPage()
	{
		super();
		
		System.out.println("TAMenuErrorPage");
		
		buildMenuErrorForm("No message passed.");
	}

	public TAMenuErrorPage(PageParameters params)
	{
		super();
		
		System.out.println("TAMenuErrorPage with message");
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
		
		buildMenuErrorForm("No message passed.");
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

		MenuErrorForm form = new MenuErrorForm("tamenuerrorf");
        form.add(new Label("message", message));	
		add(form);	  
	}
}
