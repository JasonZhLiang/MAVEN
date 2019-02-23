package com.linguaclassica.teacher;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.validation.validator.StringValidator;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.ClassChoice;
import com.linguaclassica.entity.EntityNotificationModel;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.NotificationService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.TimeService;

public class TeacherNotificationPage extends TeacherBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;
	
	@SpringBean
	private NotificationService notificationService;
	
	@SpringBean
	ClassService classService;
	
	@SpringBean
	TermService termService;
	
	@SpringBean
	TimeService timeService;

	public TeacherNotificationPage()
	{
		super();
		
		permitPreferenceSelection();
		EntityNotificationModel newnotice = (EntityNotificationModel) modelFactory.getNewNotificationModel();
		
		add(new Label("noteaction", "Create Notification"));
		
		Form<Object> form = new NotificationForm(newnotice);
		add(form);
	}

	public TeacherNotificationPage(PageParameters params)
	{
		super();
		
		// create empty entity in case of bad parameters
		permitPreferenceSelection();
		EntityNotificationModel existingNotice = (EntityNotificationModel) modelFactory.getNewNotificationModel();

		StringValue sv = params.get("NotificationId");
		if (sv != null)
		{
			String noticeId = sv.toString();
			if (noticeId !=null)
			{
				Integer notificationId = Integer.parseInt(noticeId);
				existingNotice = notificationService.findNotificationById(notificationId);
				
				// get the existing class IDs attached to the notification and update the session
				List<Integer> classIds = classService.getClassIDsByNotificationId(notificationId);
				AlphPlusSession.get().setSelectedClassIDs(classIds);
			}
		}
		
		add(new Label("noteaction", "Edit Notification"));

		Form<Object> form = new NotificationForm(existingNotice);
		add(form);
	}

	class NotificationForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		// Active user
		UserModel userModel;
		EntityNotificationModel notificationModel;
		ClassChoice<String> classChoices;
		List<Integer> initialClassIds;
		
		String starttime = "2017-01-01 09:00 ET";
		String endtime = "2017-01-01 15:00 ET";
		
		NotificationForm(EntityNotificationModel onenotice)
		{
			super("tenoticef");
			
			System.out.println("Teacher NotificationForm");
			
			// Get passed parameters
			userModel = AlphPlusSession.get().getUser(getRequest());
			notificationModel = onenotice;
			initialClassIds = AlphPlusSession.get().getSelectedClassIDs();
			
			// Create the controls
			TextArea<String> notificationtext = new TextArea<String>("notificationtext", new PropertyModel<String>(notificationModel, "note"));
			notificationtext.add(StringValidator.lengthBetween(3, 200));
			notificationtext.setRequired(true);
			add(notificationtext);
			
			int mode = ClassChoice.CREATE;
			if (notificationModel.getId() > 0)
			{
				mode = ClassChoice.EDIT;
			}
			classChoices = new ClassChoice<String>("tenoticef", userModel.getId(), mode, 0);
			classChoices.setRequired(true);
			add(classChoices);
			
			if (notificationModel.getId() > 0)
			{
				// Initialize the date parameters
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				starttime = dateFormat.format(notificationModel.getStartDate());
				endtime = dateFormat.format(notificationModel.getExpiryDate());
				
				// Add the time zone to these values
				starttime = starttime.concat(" ").concat(notificationModel.getTimeZone());
				endtime = endtime.concat(" ").concat(notificationModel.getTimeZone());
			}
			
			TextField<Date> starttimetf = new TextField<Date>("starttimefield", new PropertyModel<Date>(this, "starttime"));
			add(starttimetf);
			
			TextField<Date> endtimetf = new TextField<Date>("endtimefield", new PropertyModel<Date>(this, "endtime"));
			add(endtimetf);
			
			if (initialClassIds.size() > 0)
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				EntityTermModel termEntity = termService.findTermById(classService.findClassById(initialClassIds.get(0)).getTermid());
				String earliestDate = dateFormat.format(termEntity.getStartDate().getTime());
				String latestDate = dateFormat.format(termEntity.getEndDate().getTime());

				IFormValidator validator = new AbstractFormValidator()
				{
					// reference: https://stackoverflow.com/questions/7650104/wicket-date-range-from-to-validation
					private static final long serialVersionUID = 1L;

					public FormComponent<?>[] getDependentFormComponents()
					{
						return new FormComponent[] { starttimetf, endtimetf };
				    }

					public void validate(Form<?> form)
					{
						String startDate = starttimetf.getValue();
						String endDate = endtimetf.getValue();

						if (startDate.compareTo(earliestDate) < 0)
						{
							starttimetf.error("The start date is earlier than permitted.");
						}
						else if (startDate.compareTo(endDate) > 0)
						{
							starttimetf.error("The start date must be before the end date.");
						}
						if (endDate.substring(0, 10).compareTo(latestDate) > 0)
						{
							endtimetf.error("The end date is later than permitted.");
						}
					}
				};
				add(validator);
			}
		}
		
		public void onSubmit()
		{
			System.out.println("NotificationForm.onSubmit");
				
			try
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
				Date parsedDateStart = dateFormat.parse(starttime); 
				Date parsedDateEnd = dateFormat.parse(endtime); 
				
				String timezone = starttime.substring(17);
				
				ZonedDateTime nowtz = timeService.getOtherTimeZoneNow(timezone);
				
				Timestamp starttimestamp = new Timestamp(parsedDateStart.getTime());
				Timestamp endtimestamp = new Timestamp(parsedDateEnd.getTime());
				
				ZonedDateTime startzdt = timeService.getTimeByTimeZone(starttimestamp, timezone);
				ZonedDateTime endzdt = timeService.getTimeByTimeZone(endtimestamp, timezone);
				System.out.println("startts = " + startzdt);
				
				if(nowtz.isAfter(startzdt)) {
					System.out.println("start time before now");
					Session.get().error("The Start date can not be set in the past.");
					return;
				}

				// Inputs have been validated
				notificationModel.setStartDate(starttimestamp);
				notificationModel.setExpiryDate(endtimestamp);
				notificationModel.setTimeZone(timezone);
				List<Integer> someClasses = AlphPlusSession.get().getSelectedClassIDs();
				if (notificationModel.getId() > 0)
				{
					// Update notification
					// add current selections not among the initial selections 
					List<Integer> newClassIds = new ArrayList<Integer>();
					for (int idix = 0; idix < someClasses.size(); idix++)
					{
						if (!initialClassIds.contains(someClasses.get(idix)))
						{
							newClassIds.add(someClasses.get(idix));
						}
					}
					
					// remove initial selections not among the current selections
					List<Integer> oldClassIds = new ArrayList<Integer>();
					for (int idix = 0; idix < initialClassIds.size(); idix++)
					{
						if (!someClasses.contains(initialClassIds.get(idix)))
						{
							oldClassIds.add(initialClassIds.get(idix));
						}
					}
					notificationService.updateNotification(notificationModel, newClassIds, oldClassIds);
				}
				else
				{
					// Create notification
					notificationModel.setCreatedById(userModel.getId());
					
					notificationService.createNotification(notificationModel, someClasses);
				}
			}
			catch (ParseException e)
			{
				Session.get().error("Both dates must be selected.");
				return;
			}
			catch (StringIndexOutOfBoundsException sioobe)
			{
				Session.get().error("Error: start or end date probably missing the time zone");
				return;
			}
			catch (Exception e)
			{
				Session.get().error("Something failed when trying to create or update the notification.");
				return;
			}

			setResponsePage(TeacherNotificationsPage.class);
		}
	}
}
