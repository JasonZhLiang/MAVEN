package com.linguaclassica.officeadmin;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.shared.SharedSelectionRangePage;


public class OfficeAdminClientsPage extends SharedSelectionRangePage
{
	private static final long serialVersionUID = 1L;
	
		
	@SpringBean
	UserService userService;

	@SpringBean
	AssignmentService assignmentService;

		
	private Logger logger = LogManager.getLogger(OfficeAdminClientsPage.class);
	
	
	public static List<EntityUserModel> clientIdList = null; 
	public static ListView<EntityUserModel> clientList = null;
	
	

	public OfficeAdminClientsPage()
	{
		super("div_uclients", null);
		
		logger.debug("OfficeAdminClientsPage");
		
	}
	
	
	protected void SetContainer(){
		
		
		WebMarkupContainer wmc = new WebMarkupContainer(containerName);

		wmc.add(PopulateList());
		
		add(wmc.setVisible(true).setOutputMarkupId(true));
	}
	

	private ListView<EntityUserModel> PopulateList(){
		
		if(clientIdList != null && !clientIdList.isEmpty()){
			clientIdList.clear();
		}
		
		clientIdList = userService.getListOfUsersByPermission(currentOrg.getId(), Permission.CLIENT);
		setTimeRange(clientIdList.get(0).getAccountCreatedTime(),clientIdList.get(clientIdList.size()-1).getAccountCreatedTime() );
		
		clientList = new ListView<EntityUserModel>("clientlist",clientIdList)
		{
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(ListItem<EntityUserModel> item)
			{
				EntityUserModel client = (EntityUserModel) item.getModelObject();
				Boolean show = getSelection(client.getStatus());	
				
				Link<Object> link = new Link<Object>("clientname"){
					private static final long serialVersionUID = 1L;
					@Override
					public void onClick() {
						PageParameters paramEditClient = new PageParameters();
						paramEditClient.add("usertype", Permission.CLIENT);
						paramEditClient.add("userid",client.getId());
						setResponsePage(UserCreatePage.class, paramEditClient);
					}			
				};
				link.add(new Label("clientlabel",client.getFirstName()+ " " + client.getLastName() + " (" + checkselectionList.get(client.getStatus())+ ")"));
				if(show){				
					show &= compareSelection(client.getAccountCreatedTime());
				}
			
				item.add(link.setVisible(show));
			}				
		};
		clientList.setOutputMarkupId(true);

		return clientList;
		
	}
}

