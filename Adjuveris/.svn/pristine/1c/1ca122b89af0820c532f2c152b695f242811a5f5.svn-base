package com.linguaclassica.shared;

import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.InstUserPermissionEntity;
import com.linguaclassica.instadmin.InstAdminOverviewPage;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.DateRangeService;
import com.linguaclassica.service.InstitutionService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.student.NotificationsPage;
import com.linguaclassica.teacher.TeacherNotificationsPage;

public class SwitchPermissionPanel extends Panel
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private ModelFactory modelFactory;	
	
	@SpringBean
	private DateRangeService dateRangeService;

	@SpringBean
	private InstitutionService institutionService;

	@SpringBean
	private PermissionService permissionService;

	@SpringBean
	private UserService userService;
	
	private Integer userid;

	public SwitchPermissionPanel()
	{
		super("swappermpanel");
		
		Request request = getRequestCycle().getRequest();
		UserModel userModel = AlphPlusSession.get().getUser(request);
		add(new Label("namelab", userModel.getFirstName() + " " + userModel.getLastName()));
		userid = userModel.getId();
		
		add(new SwitchForm(AlphPlusSession.get().getCurrentIUP()));
	}

	class SwitchForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		private InstUserPermissionEntity destinationIUP;
		
		public SwitchForm(Integer iupId)
		{
			super("swappermform");
			
			// Get the choices
			List<InstUserPermissionEntity> iupChoices = userService.getUserItems(userid);
			
			// Initialize to the current choice
			destinationIUP = (InstUserPermissionEntity) modelFactory.getNewInstUserPermissionModel();
			if (iupId > 0)
			{
				Integer index = 0;
				while (index < iupChoices.size())
				{
					if (iupChoices.get(index).getId().equals(iupId))
					{
						destinationIUP = iupChoices.get(index);
						break;
					}
					index++;
				}
			}
			
			// Configure the radio group
			RadioGroup<InstUserPermissionEntity> iupGroup = new RadioGroup<InstUserPermissionEntity>(
					"ipchoice", new PropertyModel<InstUserPermissionEntity>(this, "destinationIUP"));
			iupGroup.setOutputMarkupId(false);
			iupGroup.setOutputMarkupPlaceholderTag(false);
			iupGroup.setRenderBodyOnly(true);
			iupGroup.setRequired(true);
			add(iupGroup);

			iupGroup.add(new ListView<InstUserPermissionEntity>("instperm", iupChoices)
			{
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void populateItem(ListItem<InstUserPermissionEntity> item)
				{
					item.add(new Radio<InstUserPermissionEntity>("pickip", Model.of(item.getModelObject())));
					
					Integer institutionId = item.getModelObject().getInstitutionId();
					String instname = institutionService.findInstitutionById(institutionId).getInstname();
					item.add(new Label("instLab", instname));
					
					Integer permissionId = item.getModelObject().getPermissionId();
					String permit = permissionService.getModel(permissionId).getPermissionString();
					item.add(new Label("permLab", permit));
				}
			});
		}
		
		public void onSubmit()
		{
			System.out.println("SwitchForm.onSubmit");
			System.out.format("switched to [%d] %d %d %d%n", destinationIUP.getId(), 
					destinationIUP.getInstitutionId(), destinationIUP.getUserId(), destinationIUP.getPermissionId());
			AlphPlusSession.get().setCurrentIUP(destinationIUP.getId());
			AlphPlusSession.get().setCurrentInstitution(destinationIUP.getInstitutionId());
			AlphPlusSession.get().setCurrentPermission(destinationIUP.getPermissionId());
			Permission permission = permissionService.getModel(destinationIUP.getPermissionId()).getPermission();
			if (permission == Permission.STUDENT)
			{
				
				int dateRangeNum = dateRangeService.getStudentTerms(AlphPlusSession.get().getCurrentInstitution());
				System.out.println("dateRangeNum: " + dateRangeNum);
				AlphPlusSession.get().setDateRange(dateRangeNum);
				
				setResponsePage(NotificationsPage.class);
			}
			else if(permission == Permission.INSTITUTION_ADMIN)
			{
				int instDateRangeNum = dateRangeService.getInstTerms(AlphPlusSession.get().getCurrentInstitution());
				System.out.println("instDateRangeNum: " + instDateRangeNum);
				AlphPlusSession.get().setDateRange(instDateRangeNum);
				
				setResponsePage(InstAdminOverviewPage.class);
			}
			else if(permission == Permission.TEACHER)
			{
				// TODO Time Zone setting should be in database (user preference) or from website location.
				AlphPlusSession.get().setDateRange(AlphPlusSession.BEGINNING_OF_TIME);
				AlphPlusSession.get().setTimeZone("ET");
				AlphPlusSession.get().setDateFormat("dd-MM-yy");
				AlphPlusSession.get().setTimeFormat("hh:mm a z");
				
				setResponsePage(TeacherNotificationsPage.class);
			}
			else if(permission == Permission.TA)
			{
				// Use the same pages as the teachers
				// TODO Time Zone setting should be in database (user preference) or from website location.
				AlphPlusSession.get().setDateRange(AlphPlusSession.BEGINNING_OF_TIME);
				AlphPlusSession.get().setTimeZone("ET");
				AlphPlusSession.get().setDateFormat("dd-MM-yy");
				AlphPlusSession.get().setTimeFormat("hh:mm a z");
				
				setResponsePage(TeacherNotificationsPage.class);
			}
			else if(permission == Permission.ADMINISTRATOR)
			{
				Session.get().error("Administrator pages have not yet been added.");
				System.out.println("log in as administrator");
				//setResponsePage(AdminHomePage.class);
			}
			else
			{
				System.out.println("SwitchForm.onSubmit unchecked Permission type.");
			}
		}
	}
}
