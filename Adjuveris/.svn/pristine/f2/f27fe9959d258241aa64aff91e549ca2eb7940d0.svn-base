package com.linguaclassica.instadmin;

import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.mapper.parameter.UrlPathPageParametersEncoder;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.linguaclassica.access.AlphPlusSession;



/**
* Application object for your web application. If you want to run this application without deploying, run the Start class.
* 
* @see com.linguaclassica.Start#main(String[])
*/
public class InstAdminApplication extends WebApplication {

	@Override
	public RuntimeConfigurationType getConfigurationType() {
  	return RuntimeConfigurationType.DEPLOYMENT;
	}
	
	public InstAdminApplication()
	{
	}
	
  /**
   * @see org.apache.wicket.Application#getHomePage()
   */
  @Override
  public Class<? extends Page> getHomePage() {
      return InstAdminOverviewPage.class;
  }

	@Override
	public Session newSession(Request request, Response response) {
		  return new AlphPlusSession(request);
	  }
  
  /**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		
		// add your configuration here
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}

}
