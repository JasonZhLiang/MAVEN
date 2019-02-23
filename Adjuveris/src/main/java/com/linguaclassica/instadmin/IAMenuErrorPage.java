package com.linguaclassica.instadmin;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class IAMenuErrorPage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;

	public IAMenuErrorPage()
	{
		super();
		
		System.out.print("IAMenuErrorPage");
		
		buildMetricsErrorForm("No message passed.");
	}
	
	public IAMenuErrorPage(PageParameters parameters)
	{
		super();
		
		StringValue result = parameters.get("message");
		
		System.out.print("IAMenuErrorPage: " + result.toString());
		
		buildMetricsErrorForm(result.toString());	
	}
	
	class MenuErrorForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		public MenuErrorForm(String id)
		{
			super(id);
		}
	}

	private void buildMetricsErrorForm(String message)
	{
		System.out.println("MenuErrorForm.buildMetricsErrorForm");

		MenuErrorForm form = new MenuErrorForm("iamenuerrorf");
        form.add(new Label("message", message));	
		add(form);	  
	}
/*
	@Override
	public MenuItemEnum getActiveMenu()
	{
		// Auto-generated method stub
		return MenuItemEnum.NONE;
	}
*/
}
