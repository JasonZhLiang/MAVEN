package com.linguaclassica.shared;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.OrgUserPermissionEntity;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.officeadmin.OfficeAdminOverviewPage;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.OrganizationService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;

public class SwitchPermissionPage extends PrivateBasePage
{
	private static final long serialVersionUID = -7800146887443913736L;
	
	@SpringBean
	private ModelFactory modelFactory;	

	@SpringBean
	private OrganizationService organizationService;

	@SpringBean
	private PermissionService permissionService;

	@SpringBean
	private UserService userService;
	
	private Logger logger = LogManager.getLogger(SwitchPermissionPage.class);
	private Integer userid;

	public SwitchPermissionPage()
	{
		super();
		
		Request request = getRequestCycle().getRequest();
		UserModel userModel = LMSSession.get().getUser(request);
		add(new Label("namelab", userModel.getFirstName() + " " + userModel.getLastName()));
		userid = userModel.getId();
		
		add(new SwitchPermissionForm(LMSSession.get().getCurrentOUP()));
	}

	class SwitchPermissionForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		private OrgUserPermissionEntity destinationOUP;

		public SwitchPermissionForm(Integer oupId)
		{
			super("swappermform");
			
			// Get the choices
			List<OrgUserPermissionEntity> oupChoices = userService.getUserItems(userid);
			
			// Initialize the empty choice or to the current choice
			destinationOUP = (OrgUserPermissionEntity) modelFactory.getNewOrgUserPermissionModel();
			if (oupId > 0)
			{
				Integer index = 0;
				while (index < oupChoices.size())
				{
					if (oupChoices.get(index).getId().equals(oupId))
					{
						destinationOUP = oupChoices.get(index);
						break;
					}
					index++;
				}
			}
			
			// Configure and display the organization-user-permission radio group
			RadioGroup<OrgUserPermissionEntity> oupGroup = new RadioGroup<OrgUserPermissionEntity>(
					"opchoice", new PropertyModel<OrgUserPermissionEntity>(this, "destinationOUP"));
			oupGroup.setOutputMarkupId(false);
			oupGroup.setOutputMarkupPlaceholderTag(false);
			oupGroup.setRenderBodyOnly(true);
			oupGroup.setRequired(true);
			add(oupGroup);

			oupGroup.add(new ListView<OrgUserPermissionEntity>("orgperm", oupChoices)
			{
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void populateItem(ListItem<OrgUserPermissionEntity> item)
				{
					item.add(new Radio<OrgUserPermissionEntity>("pickop", Model.of(item.getModelObject())));
					
					Integer organizationId = item.getModelObject().getOrganizationId();
					String organname = organizationService.findOrganizationById(organizationId).getOrgname();
					item.add(new Label("orgLab", organname));
					
					Integer permissionId = item.getModelObject().getPermissionId();
					String permit = permissionService.getModel(permissionId).getPermissionString();
					item.add(new Label("permLab", permit));
				}
			});
		}
		
		public void onSubmit()
		{
			logger.debug("onSubmit");
			String debugstr = String.format("switched to [%d] %d %d %d", destinationOUP.getId(), 
					destinationOUP.getOrganizationId(), destinationOUP.getUserId(), destinationOUP.getPermissionId());
			logger.info(debugstr);
			
			LMSSession.get().setCurrentOUP(destinationOUP.getId());
			LMSSession.get().setCurrentOrganization(destinationOUP.getOrganizationId());
			LMSSession.get().setCurrentPermission(destinationOUP.getPermissionId(), 
					permissionService.getModel(destinationOUP.getPermissionId()).getPermissionString());
			Permission permission = permissionService.getModel(destinationOUP.getPermissionId()).getPermission();
			if (permission == Permission.CLIENT)
			{
				setResponsePage(CommonOverviewPage.class);
			}
			else if(permission == Permission.CONSULTANT)
			{
				// Actually for Consultants
				setResponsePage(CommonOverviewPage.class);
			}
			else if(permission == Permission.OFFICE_ADMIN)
			{
				setResponsePage(OfficeAdminOverviewPage.class);
			}
			else if(permission == Permission.ADMINISTRATOR)
			{
				Session.get().error("Administrator pages have not yet been added.");
				//System.out.println("log in as administrator");
				logger.info("log in as administrator");
				//setResponsePage(AdminHomePage.class);
			}
			else
			{
				//System.out.println("HomeForm.onSubmit unchecked Permission type.");
				logger.warn("HomeForm.onSubmit unchecked Permission type.");
			}
		}
	}
}
