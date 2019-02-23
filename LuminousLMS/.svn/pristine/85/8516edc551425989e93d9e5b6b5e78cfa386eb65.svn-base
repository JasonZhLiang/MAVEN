package com.linguaclassica.shared;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.HomePage;
import com.linguaclassica.access.LMSSession;
import com.linguaclassica.access.MenuItemEnum;
import com.linguaclassica.access.NavBarPanel;
import com.linguaclassica.administrator.ViewOrganizationsPage;
import com.linguaclassica.client.AssignmentsPage;
import com.linguaclassica.consultant.ConsViewMsgsPage;
import com.linguaclassica.consultant.ConsultantClientsPage;
import com.linguaclassica.shared.TrainingMaterialsPage;
import com.linguaclassica.entity.EntityOrgModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.officeadmin.CreateNotificationPage;
import com.linguaclassica.officeadmin.OfficeAdminClientsPage;
import com.linguaclassica.officeadmin.OfficeAdminConsultantsPage;
import com.linguaclassica.officeadmin.OfficeAdminNotificationsPage;
import com.linguaclassica.officeadmin.OfficeAdminOverviewPage;
import com.linguaclassica.officeadmin.UploadFilePage;
import com.linguaclassica.officeadmin.UserCreatePage;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.OrganizationService;
import com.linguaclassica.service.PermissionService;

public class PrivateBasePage extends WebPage
{
	private static final long serialVersionUID = 1L;

	@SpringBean
	private OrganizationService organizationService;
	
	@SpringBean
	private PermissionService permissionService;
	
	@SuppressWarnings("unused")
	private String username;
	@SuppressWarnings("unused")
	protected String currentPerm = "role";
	
	boolean bFrench = false;
	/**
	 * currentOrg contains the information about current organization
	 * for all classes derived from PrivateBasePage
	 */
	protected EntityOrgModel currentOrg;
	// Active user
	private final UserModel userModel = LMSSession.get().getUser(getRequest());
	private static final Logger logger = LogManager.getLogger(PrivateBasePage.class);

	public PrivateBasePage()
	{
		super();
		
		logger.debug(this.getClass().getSimpleName());
		buildCommon();
		buildMenu();
	}
/*
	public PrivateBasePage(IModel<?> model)
	{
		super(model);
	}
*/
	public PrivateBasePage(PageParameters parameters)
	{
		super(parameters);
		
		logger.debug(this.getClass().getSimpleName() + "(..)");
		buildCommon();
		buildMenu();
	}
	
	private void buildCommon()
	{
		FeedbackPanel feedbackpanel = new FeedbackPanel("feedbackmessages");
		add(feedbackpanel);
		
		currentOrg = organizationService.findOrganizationById(LMSSession.get().getCurrentOrganization());
		Label orglabel =  new Label("orglab", new PropertyModel<String>(currentOrg, "orgname"));
		add(orglabel);
		
		Integer permissionId = LMSSession.get().getCurrentPermission();
		if (permissionId > 0)
		{
			// logged in pages
			currentPerm = permissionService.getModel(permissionId).getPermissionString();
		}
		Label permLabel = new Label("permlab", new PropertyModel<String>(this, "currentPerm"))
		{
			private static final long serialVersionUID = 1L;
			
			public boolean isVisible()
			{
				return LMSSession.get().getCurrentPermission() > 0;
			}
		};
		add(permLabel);
		
		username = userModel.getFirstName() + " " + userModel.getLastName();
		Label userlabel = new Label("ident", new PropertyModel<String>(this, "username"));
		add(userlabel);

		add(new Link<Object>("permswap")
		{
			private static final long serialVersionUID = 1L;
			
			public boolean isVisible()
			{
				return LMSSession.get().areMultiplePermissions();
			}
			
			@Override
			public void onClick()
			{
				System.out.println("PrivateBasePage.onClick");
				setResponsePage(SwitchPermissionPage.class);
			}
		});
	}

	private void buildMenu()
	{
		String key = new String("msg");
		PageParameters paramPR = new PageParameters();
		paramPR.add(key, "Future Participation Record page");
		
		bFrench = this.getSession().getLocale().getLanguage().equals("fr");
		Integer permissionId = LMSSession.get().getCurrentPermission();
		if (permissionId > 0)
		{
			Permission permission = permissionService.getModel(permissionId).getPermission();
			if (bFrench && (permission == Permission.CLIENT))
			{
				add(new NavBarPanel.Builder("navBar", HomePage.class)
						.withMenuItem(MenuItemEnum.CLOVERVIEW_F, "comoverview_ml", CommonOverviewPage.class) 
						.withMenuItem(MenuItemEnum.CLASSIGNMENTS_F, "classignments_ml", AssignmentsPage.class)
						.withMenuItem(MenuItemEnum.CLOTHERES_F, "clotherres_ml", OtherResourcePage.class)
						.withMenuItemAsDropdown(MenuItemEnum.CLMESSAGING_F, "viewmsging_ml", ViewMessagesPage.class, "\u25BB Voir")
						.withMenuItemAsDropdown(MenuItemEnum.CLMESSAGING_F, "createmsging_ml", CreateMessagePage.class, "\u25BB Créer/envoyer")
						.withMenuItem(MenuItemEnum.CLPARTICIPATION_F, "participate_ml", MessagePage.class, paramPR)
						.withMenuItem(MenuItemEnum.CLACCOUNT_F, "useracct_ml", UserAccountPage.class)
						.build()
					);
			}
			else if (permission == Permission.CLIENT)
			{
				add(new NavBarPanel.Builder("navBar", HomePage.class)
						.withMenuItem(MenuItemEnum.CLOVERVIEW, "comoverview_ml", CommonOverviewPage.class) 
						.withMenuItem(MenuItemEnum.CLASSIGNMENTS, "classignments_ml", AssignmentsPage.class)
						.withMenuItem(MenuItemEnum.CLOTHERES, "clotherres_ml", OtherResourcePage.class)
						.withMenuItemAsDropdown(MenuItemEnum.CLMESSAGING, "viewmsging_ml", ViewMessagesPage.class, "\u25BB View")
						.withMenuItemAsDropdown(MenuItemEnum.CLMESSAGING, "createmsging_ml", CreateMessagePage.class, "\u25BB Create/send")
						.withMenuItem(MenuItemEnum.CLACCOUNT, "useracct_ml", UserAccountPage.class)
						.build()
					);
			}
			else if (bFrench && (permission == Permission.CONSULTANT))
			{
				add(new NavBarPanel.Builder("navBar", HomePage.class)
						.withMenuItem(MenuItemEnum.COOVERVIEW_F, "comoverview_ml", CommonOverviewPage.class)
						.withMenuItem(MenuItemEnum.COCLIENTS_F, "conclient_ml", ConsultantClientsPage.class)
						.withMenuItem(MenuItemEnum.TRAININGMATERIALS_F, "viewvideo_ml", TrainingMaterialsPage.class)
						.withMenuItem(MenuItemEnum.CORESOURCES_F, "otherres_ml", OtherResourcePage.class)
						.withMenuItemAsDropdown(MenuItemEnum.COMESSAGING, "viewmsging_ml", ConsViewMsgsPage.class, "\u25BB Voir")
						.withMenuItemAsDropdown(MenuItemEnum.COMESSAGING, "createmsging_ml", CreateMessagePage.class, "\u25BB Créer/envoyer")
						.withMenuItem(MenuItemEnum.COACCOUNT_F, "useracct_ml", UserAccountPage.class)
						.build()
					);
			}
			else if (permission == Permission.CONSULTANT)
			{
				add(new NavBarPanel.Builder("navBar", HomePage.class)
						.withMenuItem(MenuItemEnum.COOVERVIEW, "comoverview_ml", CommonOverviewPage.class)
						.withMenuItem(MenuItemEnum.CLIENTS, "conclient_ml", ConsultantClientsPage.class)
						.withMenuItem(MenuItemEnum.TRAININGMATERIALS, "viewvideo_ml", TrainingMaterialsPage.class)
						.withMenuItem(MenuItemEnum.OTHERRESOURCES, "otherres_ml", OtherResourcePage.class)
						.withMenuItemAsDropdown(MenuItemEnum.MESSAGING, "viewmsging_ml", ConsViewMsgsPage.class, "\u25BB View")
						.withMenuItemAsDropdown(MenuItemEnum.MESSAGING, "createmsging_ml", CreateMessagePage.class, "\u25BB Create/send")
						.withMenuItem(MenuItemEnum.ACCOUNT, "useracct_ml", UserAccountPage.class)
						.build()
					);
			}
			else if (permission == Permission.OFFICE_ADMIN)
			{
				PageParameters paramOA = new PageParameters();
				paramOA.add(key, "Office Administrator Page has not been implemented.");
				
				PageParameters paramOANewNote = new PageParameters();
		//		String currentTime = java.sql.Timestamp.from(Instant.now()).toString();
		//		paramOANewNote.add("starttime",currentTime);//Calendar format
				
		//		paramOANewNote.add("endtime",currentTime);
				
				PageParameters paramOANewOA = new PageParameters();
				paramOANewOA.add("usertype", Permission.OFFICE_ADMIN);
				PageParameters paramOANewCons = new PageParameters();
				paramOANewCons.add("usertype", Permission.CONSULTANT);
				PageParameters paramOANewClient = new PageParameters();
				paramOANewClient.add("usertype", Permission.CLIENT);

				if (bFrench)
				{
					add(new NavBarPanel.Builder("navBar", HomePage.class)
							.withMenuItem(MenuItemEnum.OAOVERVIEW_F, "oaoverview_ml", OfficeAdminOverviewPage.class) 
							.withMenuItemAsDropdown(MenuItemEnum.OANOTIFICATIONS_F, "createnotify_ml", CreateNotificationPage.class, "Créer", paramOANewNote)
							.withMenuItemAsDropdown(MenuItemEnum.OANOTIFICATIONS_F, "viewnotify_ml", OfficeAdminNotificationsPage.class, "Voir")
							.withMenuItem(MenuItemEnum.OATRAININGMATL_F, "trainmat_ml", TrainingMaterialsPage.class)
							.withMenuItemAsDropdown(MenuItemEnum.OAEVALUATION_F, "createeval_ml", MessagePage.class, "Créer", paramOA)
							.withMenuItemAsDropdown(MenuItemEnum.OAEVALUATION_F, "vieweval_ml", MessagePage.class, "Voir", paramOA)
							.withMenuItemAsDropdown(MenuItemEnum.OAOTHERRES_F, "createres_ml", MessagePage.class, "Créer", paramOA)
							.withMenuItemAsDropdown(MenuItemEnum.OAOTHERRES_F, "viewreses_ml", OtherResourcePage.class, "Voir")
							.withMenuItemAsDropdown(MenuItemEnum.OACONSULTANTS_F, "createconsultu_ml", UserCreatePage.class, "Créer", paramOANewCons)
							.withMenuItemAsDropdown(MenuItemEnum.OACONSULTANTS_F, "oaconsultants_ml", OfficeAdminConsultantsPage.class, "Voir")
							.withMenuItemAsDropdown(MenuItemEnum.OACONCLIENTS_F, "createclientu_ml", UserCreatePage.class,  "Créer", paramOANewClient)
							.withMenuItemAsDropdown(MenuItemEnum.OACONCLIENTS_F, "oaclients_ml", OfficeAdminClientsPage.class, "Voir")
							.withMenuItemAsDropdown(MenuItemEnum.OAADMINISTRATOR_F, "createoffadu_ml", UserCreatePage.class,  "Créer", paramOANewOA)
							.withMenuItemAsDropdown(MenuItemEnum.OAADMINISTRATOR_F, "viewradmin_ml", MessagePage.class, "Voir", paramOA)
							.withMenuItemAsDropdown(MenuItemEnum.OAREPORTS_F, "eventreport_ml", MessagePage.class, "Par événement", paramOA)
							.withMenuItemAsDropdown(MenuItemEnum.OAREPORTS_F, "monthreport_ml", MessagePage.class, "Par mois", paramOA)
							.withMenuItem(MenuItemEnum.OAMYACCOUNT_F, "useracct_ml", UserAccountPage.class)
							.build()
						);
				}
				else
				{
					add(new NavBarPanel.Builder("navBar", HomePage.class)
							.withMenuItem(MenuItemEnum.OAOVERVIEW, "oaoverview_ml", OfficeAdminOverviewPage.class) 
							.withMenuItemAsDropdown(MenuItemEnum.OANOTIFICATIONS, "createnotify_ml", CreateNotificationPage.class, "Create", paramOANewNote)
							.withMenuItemAsDropdown(MenuItemEnum.OANOTIFICATIONS, "viewnotify_ml", OfficeAdminNotificationsPage.class, "View")
							.withMenuItem(MenuItemEnum.OATRAININGMATL, "trainmat_ml", TrainingMaterialsPage.class)
							.withMenuItemAsDropdown(MenuItemEnum.OAEVALUATION, "createeval_ml", MessagePage.class, "Create", paramOA)
							.withMenuItemAsDropdown(MenuItemEnum.OAEVALUATION, "vieweval_ml", MessagePage.class, "View", paramOA)
							
							//.withMenuItemAsDropdown(MenuItemEnum.OAOTHERRES, "createres_ml", MessagePage.class, "Create", paramOA)
							.withMenuItemAsDropdown(MenuItemEnum.OAOTHERRES, "createres_ml", UploadFilePage.class, "Create")
							
							.withMenuItemAsDropdown(MenuItemEnum.OAOTHERRES, "viewreses_ml", OtherResourcePage.class, "View")
							.withMenuItemAsDropdown(MenuItemEnum.OACONSULTANTS, "createconsultu_ml", UserCreatePage.class, "Create", paramOANewCons)
							.withMenuItemAsDropdown(MenuItemEnum.OACONSULTANTS, "oaconsultants_ml", OfficeAdminConsultantsPage.class, "View")
							.withMenuItemAsDropdown(MenuItemEnum.OACONCLIENTS, "createclientu_ml", UserCreatePage.class,  "Create", paramOANewClient)
							.withMenuItemAsDropdown(MenuItemEnum.OACONCLIENTS, "oaclients_ml", OfficeAdminClientsPage.class, "View")
							.withMenuItemAsDropdown(MenuItemEnum.OAADMINISTRATOR, "createoffadu_ml", UserCreatePage.class,  "Create", paramOANewOA)
							.withMenuItemAsDropdown(MenuItemEnum.OAADMINISTRATOR, "viewradmin_ml", MessagePage.class, "View", paramOA)
							.withMenuItemAsDropdown(MenuItemEnum.OAREPORTS, "eventreport_ml", MessagePage.class, "By Event", paramOA)
							.withMenuItemAsDropdown(MenuItemEnum.OAREPORTS, "monthreport_ml", MessagePage.class, "By Month", paramOA)
							.withMenuItem(MenuItemEnum.OAMYACCOUNT, "useracct_ml", UserAccountPage.class)
							.build()
						);
				}
			}
			else if (permission == Permission.ADMINISTRATOR)
			{
				PageParameters paramSA = new PageParameters();
				paramSA.add(key, "System Administrator Page has not been implemented.");

				add(new NavBarPanel.Builder("navBar", HomePage.class)
						.withMenuItemAsDropdown(MenuItemEnum.SAORGANIZATIONS, "createorg_ml", MessagePage.class, "Create", paramSA)
						.withMenuItemAsDropdown(MenuItemEnum.SAORGANIZATIONS, "vieworgs_ml", ViewOrganizationsPage.class, "View")
						.build()
					);
			}
		}
		else
		{
			PageParameters paramEM = new PageParameters();
			if (bFrench)
			{
				paramEM.add("message", "Revenez en arrière et sélectionnez un rôle d'organisation-utilisateur.");
				
				add(new NavBarPanel.Builder("navBar", HomePage.class).build());
//				add(new NavBarPanel.Builder("navBar", HomePage.class)
//						.withMenuItem(MenuItemEnum.EMPTYMENU_F, "empty_ml", MessagePage.class, paramEM)
//						.build()
//					);
			}
			else
			{
				paramEM.add("message", "Go back and select an organization-user-role.");
	
				add(new NavBarPanel.Builder("navBar", HomePage.class).build());
//				add(new NavBarPanel.Builder("navBar", HomePage.class)
//						.withMenuItem(MenuItemEnum.EMPTYMENU, "empty_ml", MessagePage.class, paramEM)
//						.build()
//					);
			}
		}
	}
	
	public Boolean isFrench(){
		return bFrench;
	}
}
