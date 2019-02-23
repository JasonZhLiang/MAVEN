package com.linguaclassica.shared;

import java.util.List;

import org.apache.wicket.PageReference;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.InstUserPermissionEntity;
import com.linguaclassica.instadmin.InstAdminOverviewPage;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.DateRangeService;
import com.linguaclassica.service.InstitutionService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.student.NotificationsPage;
import com.linguaclassica.student.StudentBasePage;
import com.linguaclassica.teacher.TeacherNotificationsPage;

public class SwitchPermissionPage extends WebPage
{
	private static final long serialVersionUID = 3901114353603567481L;
	
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

	public SwitchPermissionPage(final PageReference modalWindowPage, final ModalWindow window)
	{
		Request request = getRequestCycle().getRequest();
		UserModel userModel = AlphPlusSession.get().getUser(request);
		add(new Label("namelab", userModel.getFirstName() + " " + userModel.getLastName()));
		userid = userModel.getId();
		
		FeedbackPanel feedbackpanel = new FeedbackPanel("feedbackmessages");
		add(feedbackpanel);
		
		SwitchPermissionForm spform = new SwitchPermissionForm(modalWindowPage, window);
		add(spform);
	}
	
	class SwitchPermissionForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		private InstUserPermissionEntity destinationIUP;

		public SwitchPermissionForm(final PageReference modalWindowPage, final ModalWindow window)
		{
			super("swappermform");
			
			List<InstUserPermissionEntity> iupChoices = userService.getUserItems(userid);
			
			destinationIUP = (InstUserPermissionEntity) modelFactory.getNewInstUserPermissionModel();
			Integer iupId = AlphPlusSession.get().getCurrentIUP();
			if (iupId > 0)
			{
				destinationIUP = (InstUserPermissionEntity) userService.getMultiPermissionItem(iupId);
			}
			
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

			add(new AjaxLink<Void>("GoButton")
	        {
				private static final long serialVersionUID = 1L;

				@Override
	            public void onClick(AjaxRequestTarget target)
	            {
					System.out.println("SwitchPermissionForm.GoButton.onSubmit");
	                if (modalWindowPage != null)
	                {
	                    //((ModalWindowPage)modalWindowPage.getPage()).setResult("SwitchPermissionPage - close link OK");
	                }
	                window.close(target);
	            }
	        });

	        add(new AjaxLink<Void>("CancelButton")
	        {
				private static final long serialVersionUID = 1L;

				@Override
	            public void onClick(AjaxRequestTarget target)
	            {
					System.out.println("SwitchPermissionForm.CancelButton.onSubmit");
	                if (modalWindowPage != null)
	                {
	                 //   ((ModalWindowPage)modalWindowPage.getPage()).setResult("Modal window 1 - close link Cancel");
	                }
	                window.close(target);
	            }
	        });
	        
	        add(new AjaxSubmitLink("pswap")
	        {
				private static final long serialVersionUID = 1L;
	        	
				@Override
				public void onSubmit(AjaxRequestTarget target, Form<?> form)
				{
					System.out.println("SwitchPermissionForm.onSubmit t f");
					System.out.format("switched to [%d] %d %d %d%n", destinationIUP.getId(), 
							destinationIUP.getInstitutionId(), destinationIUP.getUserId(), destinationIUP.getPermissionId());
					AlphPlusSession.get().setCurrentIUP(destinationIUP.getId());
					AlphPlusSession.get().setCurrentInstitution(destinationIUP.getInstitutionId());
					AlphPlusSession.get().setCurrentPermission(destinationIUP.getPermissionId());

					window.close(target);
				}
	        });
		}
	}
	
	public void switchUser()
	{
		Permission permission = permissionService.getModel(AlphPlusSession.get().getCurrentPermission()).getPermission();
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
			// TODO Use the same pages as the teachers
			AlphPlusSession.get().setDateRange(AlphPlusSession.BEGINNING_OF_TIME);
			AlphPlusSession.get().setTimeZone("ET");
			AlphPlusSession.get().setDateFormat("dd-MM-yy");
			AlphPlusSession.get().setTimeFormat("hh:mm a z");
			
			setResponsePage(TeacherNotificationsPage.class);
		}
		else if(permission == Permission.ADMINISTRATOR)
		{
			Session.get().error("Administrator pages have not yet been added.");
			System.out.println("Should not be able to switch to administrator");
		}
		else
		{
			System.out.println("SwitchForm.onSubmit unchecked Permission type.");
		}
	}
}
