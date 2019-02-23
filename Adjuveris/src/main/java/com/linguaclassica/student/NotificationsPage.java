package com.linguaclassica.student;

import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityAssignmentClassesModel;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityNotificationClassesModel;
import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.model.DateRangeModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.NotificationModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.DateRangeService;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.NotificationService;
import com.linguaclassica.service.PermissionService;
import com.linguaclassica.service.UserService;

public class NotificationsPage extends StudentBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private NotificationService notificationService;

	@SpringBean
	private AssignmentService assignmentService;

	@SpringBean
	private ClassService classService;

	@SpringBean
	private ExerciseService exerciseService;

	@SpringBean
	private PermissionService permissionService;
	
	@SpringBean
	private UserService userService;

	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());
	


	public NotificationsPage()
	{
		super();
		
		System.out.println("AlphPlusSession.get().getDateRange(): " + AlphPlusSession.get().getDateRange());

		Integer institutionId = AlphPlusSession.get().getCurrentInstitution();
		Date now = new Date(Calendar.getInstance().getTime().getTime());
		Integer permissionId = AlphPlusSession.get().getCurrentPermission();
		String permit = permissionService.getModel(permissionId).getPermissionString();
		List<EntityClassModel> classList = classService.getCurrentClassesForUser(institutionId, userModel.getId(), permit, now);
		
		AssignmentForm assform = new AssignmentForm(classList);
		add(assform);
		
		AnnouncementForm annform = new AnnouncementForm();
		add(annform);
	}
	
	private String getAssignClassNames(List<EntityAssignmentClassesModel> classes)
	{
		StringBuilder allclasses = new StringBuilder();
		for (int index = 0; index < classes.size(); index++)
		{
			EntityClassModel oneclass = classService.findClassById(classes.get(index).getClassId());
			if (allclasses.length() > 0)
			{
				allclasses.append("\n");
			}
			allclasses.append(oneclass.getClassname());
		}
		return allclasses.toString();
	}

	class AssignmentForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		AssignmentForm(List<EntityClassModel> classList)
		{
			super("studentAssignments");
			
			System.out.println("AssignmentForm");

			Instant inow = Instant.now();
			java.sql.Timestamp currentTime = java.sql.Timestamp.from(inow);
			List<AssignmentModel> assignlist = assignmentService.findCurrentUserAssignments(userModel.getId(), currentTime);

			ListView<AssignmentModel> listview = new ListView<AssignmentModel>("assignList", assignlist)
			{
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void populateItem(final ListItem<AssignmentModel> subitem)
				{
					// Get all classes, probably just one
					Integer assignId = subitem.getModelObject().getId();
					List<EntityAssignmentClassesModel> classNotes;
					classNotes = assignmentService.getListOfAssignmentClassesByAssignmentId(assignId);
					String classNames = getAssignClassNames(classNotes);
					subitem.add(new MultiLineLabel("assignclassnameLab", classNames));
					subitem.add(new Label("assignLab", subitem.getModelObject().getAssignmentName()));
					subitem.add(new Label("assstartLab", subitem.getModelObject().getStartDate()));
					subitem.add(new Label("assdueLab", subitem.getModelObject().getDueDate()));
					
					// Add the button to access the exercise
					Button exerciseButton = new Button("gotoExercise")
					{
						private static final long serialVersionUID = 1L;
						
						public void onSubmit()
						{
							String key = new String("msg");
							PageParameters param = new PageParameters();
							String message = String.format("Clicked button for user %d to start exercise %d." , userModel.getId(), assignId);
							param.add(key, message);
							setResponsePage(new StudentMenuErrorPage(param));
						}
					};
					subitem.add(exerciseButton);
				}
			};
			add(listview);
		}
	}

	/**
	 * Get the names of the classes
	 * @param userIdList
	 * @return
	 */
	private String getNotifClassNames(List<EntityNotificationClassesModel> classes)
	{
		StringBuilder allclasses = new StringBuilder();
		for (int index = 0; index < classes.size(); index++)
		{
			EntityClassModel oneclass = classService.findClassById(classes.get(index).getclassId());
			if (allclasses.length() > 0)
			{
				allclasses.append("\n");
			}
			allclasses.append(oneclass.getClassname());
		}
		return allclasses.toString();
	}
	
	class AnnouncementForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		AnnouncementForm()
		{
			super("studentAnnouncements");
			
			System.out.println("AnnouncementForm");

			Instant inow = Instant.now();
			java.sql.Timestamp currentTime = java.sql.Timestamp.from(inow);
			List<NotificationModel> notiflist = notificationService.findCurrentUserNotifications(userModel.getId(), currentTime);

			ListView<NotificationModel> listview = new ListView<NotificationModel>("notifList", notiflist)
			{
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void populateItem(final ListItem<NotificationModel> subitem)
				{
					// Get all classes
					Integer notifId = subitem.getModelObject().getId();
					List<EntityNotificationClassesModel> classNotes;
					classNotes = notificationService.getListOfNotificationClassesByNotificationId(notifId);
					String classNames = getNotifClassNames(classNotes);
					subitem.add(new MultiLineLabel("notifclassnameLab", classNames));
					subitem.add(new Label("notifLab", subitem.getModelObject().getNote()));
					subitem.add(new Label("annstartLab", subitem.getModelObject().getStartDate()));
					subitem.add(new Label("anndueLab", subitem.getModelObject().getExpiryDate()));
				}
			};

			add(listview);
		}
	}
}
