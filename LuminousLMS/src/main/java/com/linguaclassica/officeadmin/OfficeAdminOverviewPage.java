package com.linguaclassica.officeadmin;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.linguaclassica.service.NotificationService;
import com.linguaclassica.shared.SharedOverviewPage;

public class OfficeAdminOverviewPage extends SharedOverviewPage
{
	private static final long serialVersionUID = 1L;

	@SpringBean
	static 	NotificationService notificationService;

	public OfficeAdminOverviewPage()
	{
		super(true);
		add(new Label("officeadminoverviewhdr", new ResourceModel("officeadminoverviewhdr")));
   }
	
	void ReloadPage(){
		setResponsePage(OfficeAdminOverviewPage.class);
	}

}