package com.linguaclassica.student;

import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class StudentApplication extends WebApplication
{
	@Override
	public RuntimeConfigurationType getConfigurationType()
	{
		return RuntimeConfigurationType.DEPLOYMENT;
	}

	public StudentApplication()
	{
	}

	@Override
	public Class<? extends Page> getHomePage()
	{
		return NotificationsPage.class;
	}

	@Override
	public void init()
	{
		super.init();
		
		// add your configuration here
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}
}
