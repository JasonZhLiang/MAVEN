package com.linguaclassica.administrator;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.entity.EntityOrgModel;
import com.linguaclassica.service.OrganizationService;
import com.linguaclassica.shared.PrivateBasePage;

public class ViewOrganizationsPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	OrganizationService orgService;
	
	private Logger logger = LogManager.getLogger(getClass());

	public ViewOrganizationsPage()
	{
		super();
		
		OrganizationsForm oform = new OrganizationsForm();
		add(oform);
	}
	
	class OrganizationsForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		private List<EntityOrgModel> orgsList = new ArrayList<EntityOrgModel>();

		public OrganizationsForm()
		{
			super("organsf");
			try
			{
				orgsList = orgService.getAll();
			}
			catch (Exception e)
			{
				logger.error("Unexpected error when getting organizations. " + e.getMessage());
				error("An error occurred accessing the database.");
			}

			add(new Label("numorgans", orgsList.size()));
			
			ListView<EntityOrgModel> orgsView = new ListView<EntityOrgModel>("organv", orgsList)
			{
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<EntityOrgModel> item)
				{
					item.add(new Label("organame", item.getModelObject().getOrgname()));
					item.add(new Label("organemail", item.getModelObject().getOrgemail()));
				}
			};
			add(orgsView);
		}
		
	}
}
