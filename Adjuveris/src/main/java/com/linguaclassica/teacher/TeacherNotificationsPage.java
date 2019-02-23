package com.linguaclassica.teacher;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.googlecode.wicket.jquery.ui.form.button.ConfirmButton;
import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.ClassChoice;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.NotificationModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentFactService;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.NotificationService;
import com.linguaclassica.service.UserService;

public class TeacherNotificationsPage extends TeacherBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private NotificationService notificationService;

	@SpringBean
	private AssignmentFactService assignmentfactService;
	
	@SpringBean
	private UserService userService;
	
	@SpringBean
	private ClassService classService;
	
	ModelFactory modelFactory;
	
	List<EntityClassModel> classSelection;
	
	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());

	public TeacherNotificationsPage()
	{
		super();
		
		permitPreferenceSelection();
		classSelection = AlphPlusSession.get().getSelectedClasses();
		
		AnnouncementForm annform = new AnnouncementForm();
		add(annform);
    }
	
	class AnnouncementForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		ClassChoice<String> checkboxes;
		
		AnnouncementForm()
		{
			super("teacherAssignments");
			
			System.out.println("Teacher AnnouncementForm");
			
			checkboxes = new ClassChoice<String>("teacherAssignments", userModel.getId(), ClassChoice.DISPLAY,0);
			add(checkboxes);

			List<Integer> someClasses = AlphPlusSession.get().getSelectedClassIDs();
			List<NotificationModel> notiflist = new ArrayList<NotificationModel>();
			if (someClasses.size() > 0)
			{
				notiflist = notificationService.findNotificationsByClassIds(someClasses);
			}
			System.out.format("%d classes, %d notifications%n", someClasses.size(), notiflist.size());
			
			add(new ListView<NotificationModel>("notlist", notiflist)
			{
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(final ListItem<NotificationModel> nitem)
				{
					long currentTime = System.currentTimeMillis();	
					long startTime = nitem.getModelObject().getStartDate().getTime();
					long endTime = nitem.getModelObject().getExpiryDate().getTime();
				
					List<EntityClassModel> classesList = classService.getClassesByNotificationId(nitem.getModelObject().getId());
					nitem.add(new MultiLineLabel("classnamelab", getClassNames(classesList, someClasses)));
					nitem.add(new Label("notiflab", nitem.getModelObject().getNote()));
					nitem.add(new Label("startdatelab", nitem.getModelObject().getStartDate()));
					nitem.add(new Label("starttimezonelab", nitem.getModelObject().getTimeZone()));
					nitem.add(new Label("enddatelab", nitem.getModelObject().getExpiryDate()));
					nitem.add(new Label("endtimezonelab", nitem.getModelObject().getTimeZone()));

					Button editButton = new Button("editnotice")
					{
						private static final long serialVersionUID = 1L;
						
						@Override
						public boolean isEnabled()
						{
							// Disable edit after the time of notification
							return super.isEnabled() && currentTime < endTime;
						}
						
						public void onSubmit()
						{
							System.out.println("TeacherNotificationsPage.edit.onSubmit.");
							PageParameters pageParameters = new PageParameters();
							pageParameters.add("NotificationId", nitem.getModelObject().getId());
							setResponsePage(TeacherNotificationPage.class, pageParameters);
						}
					};
					nitem.add(editButton);

					ConfirmButton confdeletebutton = new ConfirmButton("confdeletenotice", "Delete",
							"Please Confirm Deletion", "The selected notification will be permanently deleted")
					{
						private static final long serialVersionUID = 1L;
						
						@Override
						public boolean isEnabled()
						{
							// Disable edit after the time of notification
							return super.isEnabled() && currentTime < startTime;
						}

						@Override
						public void onError()
						{
							this.error("Validation failed!");
						}

						@Override
						public void onSubmit()
						{
							System.out.println("TeacherNotificationsPage.delete.onSubmit.");
							notificationService.removeNotification(nitem.getModelObject().getId());
							//setResponsePage(TeacherTALandingPage.class);
						}																															
					};
					nitem.add(confdeletebutton);
				}
			});
		}

		/**
		 * Generate a string of all class names
		 * @param classesList
		 * @return
		 */
		/**
		 * Generates a text list of classes, one class per row
		 * @param classesList: all classes
		 * @param selectedClasses: just the classes to display
		 * @return
		 */
		private String getClassNames(List<EntityClassModel> classesList, List<Integer> selectedClasses)
		{
			EntityClassModel classEntity;
			
			Integer others = 0;
			StringBuilder classNames = new StringBuilder();
			for (int index = 0; index < classesList.size(); index++)
			{
				classEntity = classesList.get(index);
				if (selectedClasses.contains(classEntity.getId()))
				{
					// Show this class
					if (classNames.length() > 0)
					{
						classNames.append("\n");
					}
					classNames.append(classEntity.getClassname());
				}
				else
				{
					// Count classes not selected
					others++;
				}
			}
			if (others > 0)
			{
				String sOthers = String.format("\n%d other class(es)", others);
				classNames.append(sOthers);
			}
			return classNames.toString();
		}
	}
}
