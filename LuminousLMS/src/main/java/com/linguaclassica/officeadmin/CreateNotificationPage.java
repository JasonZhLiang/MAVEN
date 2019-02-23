package com.linguaclassica.officeadmin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.validation.validator.StringValidator;

import com.linguaclassica.service.NotificationService;
import com.linguaclassica.service.TimeService;
import com.linguaclassica.shared.PrivateBasePage;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;
import javafx.scene.control.DatePicker;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.EntityNotificationModel;

@SuppressWarnings("restriction")
public class CreateNotificationPage extends PrivateBasePage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	TimeService timeService;
	@SpringBean
	protected NotificationService notificationService;
	
	private static EntityNotificationModel notificationModel;

	private String notificationLabel;
	
	
	private String startRange;
	private String endRange; 
	private Integer eventId = null;
	private Boolean editable = true;
	DatePicker startdatepicker;
	DatePicker enddatepicker;
		
	CheckBox allDayCheck;
	CheckBox generalInfoCheck;
		
	TextField<String> startTimetf;
	TextField<String> endTimetf;
	
	private Logger logger = LogManager.getLogger(getClass());
	
	public CreateNotificationPage(PageParameters params)
	{
		super(params);	
		
		StringValue eventIdsv = params.get("eventid");
		//case of an existing notification	
		String eventIds = eventIdsv.toString();
		if(eventIds != null && !eventIds.isEmpty())
		{								
			eventId = Integer.valueOf(eventIds);	
			StringValue eventEditablesv = params.get("editable");
			editable = eventEditablesv.toBooleanObject();
			if(editable){
				notificationLabel = getString("leditnotification");
			}
			else{
				notificationLabel = getString("lviewnotification");
			}
		}
		else
		{
			StringValue sv1 = params.get("starttime");
			if (sv1 != null && !sv1.isNull() && !sv1.isEmpty())
			{
				
				startRange = sv1.toString().substring(0, 16).replace('T', ' ');
				if (startRange == null || startRange.isEmpty()){						
					startRange = timeService.getTime();						
				}
			}
			else{
				startRange = timeService.getTime();	
			}

			StringValue sv2 = params.get("endtime");
			if (sv2 != null && !sv2.isNull() && !sv2.isEmpty())			{
				endRange = sv2.toString().substring(0, 16).replace('T', ' ');
				
				if (endRange == null || endRange.isEmpty())
				{
					endRange = timeService.getAddedTime(1);
				}
			}
			else{
				endRange = timeService.getAddedTime(1);
			}
			notificationLabel = getString("lcreatenotification");
		}
		
		logger.info("(starttime=" + startRange + "  endtime=" + endRange + ")");

		CreateNotificationForm form = new CreateNotificationForm();
		add(form);
		
	}
	
	public CreateNotificationPage()
	{
		super();
	
		startRange = timeService.getTime();	
		endRange = timeService.getAddedTime(1);
		notificationLabel = getString("lcreatenotification");
		CreateNotificationForm form = new CreateNotificationForm();
		add(form);
    }
	
	class CreateNotificationForm extends Form<Object>{
		private static final long serialVersionUID = 1L;

		// Model placeholders
		Boolean allDayEvent = false;
		Boolean generalInfoEvent = false;
		private String starttime;
		private String endtime;
		private TextField<String> subjectTF = null;
		private TextArea<String> noteTA = null;
		
		public CreateNotificationForm(){
			super("notificationform");
			
	
			add (new Label("notificationlabel",notificationLabel ));
			// Load initial personal information
			if(eventId != null && eventId >0){
				notificationModel = notificationService.findNotificationById(eventId);
				
				starttime = notificationModel.getStartDate().toString().substring(0, 16);
				endtime = notificationModel.getExpiryDate().toString().substring(0, 16);;
				starttime += " " + notificationModel.getTimeZone();
				endtime += " " + notificationModel.getTimeZone();
				allDayEvent = notificationModel.getType()== NotificationService.indexAllDay;
				generalInfoEvent = notificationModel.getType()== NotificationService.indexNonCalendar;
						
			}
			else{
				starttime = startRange + " " + timeService.getZone();
				endtime = endRange + " " +  timeService.getZone();
			
				notificationModel = new EntityNotificationModel();
				allDayEvent = starttime.compareTo(endtime)== 0;
			}
			
			subjectTF = new TextField<String>("subjecttf", new PropertyModel<String>(notificationModel,"subject"));
			subjectTF.setEnabled(editable);
			subjectTF.add(StringValidator.maximumLength(80));
			add(subjectTF);

			noteTA = new TextArea<String>("notificationtext", new PropertyModel<String>(notificationModel,"note"));
			noteTA.setEnabled(editable);
			noteTA.add(StringValidator.maximumLength(200));
			add(noteTA);
			
			startTimetf = new TextField<String>("starttimefield", new PropertyModel<String>(this,"starttime"));
			add(startTimetf.setOutputMarkupId(true).setEnabled(!allDayEvent&&editable));
			endTimetf = new TextField<String>("endtimefield", new PropertyModel<String>(this,"endtime"));
			endTimetf.add( new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget t){
					logger.debug("endtime " + endtime);

				}					
			});
			add(endTimetf.setOutputMarkupId(true).setEnabled(!allDayEvent&&editable));

			allDayCheck = new CheckBox("allday", new PropertyModel<Boolean>(this, "allDayEvent")); 
			allDayCheck.add(new AjaxEventBehavior("change") {
				private static final long serialVersionUID = 1L;
					
				@Override
				protected void onEvent(AjaxRequestTarget target) {
					//Need direct calls to input instead of model because of it's tied to onSubmit()
					allDayCheck.convertInput();
					allDayEvent = allDayCheck.getConvertedInput();
					
					endTimetf.setEnabled(!allDayEvent);
					startTimetf.setEnabled(!allDayEvent);
					//target.add(startTimetf);
				//	target.add(endTimetf);
					target.add(	target.getPage());//.get("notificationform"));
				}
			});
			add(allDayCheck.setOutputMarkupId(true).setEnabled(editable));
			generalInfoCheck = new CheckBox("generalinformation", new PropertyModel<Boolean>(this, "generalInfoEvent"));
			generalInfoCheck.add(new AjaxEventBehavior("change") {
				private static final long serialVersionUID = 1L;
					
				@Override
				protected void onEvent(AjaxRequestTarget target) {
					//Need direct calls to input instead of model because of it's tied to onSubmit()
/*					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date parsedDateStart = null;
					Date parsedDateEnd =  null;
					try {
						parsedDateStart = dateFormat.parse(starttime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						parsedDateEnd = dateFormat.parse(endtime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(parsedDateStart.getDate() == parsedDateEnd.getDate()){
						Session.get().error("Non-Calendar events must last more than  one day.\nChoose the end day different form the start day.");
						generalInfoEvent = false;
						target.add(target.getPage());
						return;
					}*/
					generalInfoCheck.convertInput();
					generalInfoEvent = generalInfoCheck.getConvertedInput();
					
					allDayCheck.setEnabled(!generalInfoEvent);
					
					endTimetf.setEnabled(generalInfoEvent == false? !allDayEvent : generalInfoEvent);
					startTimetf.setEnabled(generalInfoEvent == false? !allDayEvent : generalInfoEvent);
				//	target.add(allDayCheck);
				//	target.add(startTimetf);
				//	target.add(endTimetf);
					target.add(	target.getPage());//.get("notificationform"));
				}
				
			});
			add(generalInfoCheck.setOutputMarkupId(true).setEnabled(editable));
			
		
			add(new Button("submitbutton").setVisible(editable));
			//if the notification has expired - let Office Admin delete it
			Boolean candelete = false;
			if(editable && (eventId != null && eventId > 0) && endtime!=null && !endtime.isEmpty()){			
				candelete = timeService.compareDates(null, endtime);
			}
			add(new Button("deletebutton").setVisible(candelete).setOutputMarkupId(true).add( new AjaxFormComponentUpdatingBehavior("click") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget t){	
					if(eventId != null && eventId > 0){
						notificationService.removeNotification(eventId);
						setResponsePage(OfficeAdminNotificationsPage.class);
					}
					
				}					
			})); 
			
			setEnabled(true);	
			
		}
		
		@SuppressWarnings("deprecation")
		public void onSubmit()
		{
			logger.debug("onSubmit");

			/**  
			 * Validate selected classes 
			 */
			
 			if (starttime.equals("00:00") || endtime.equals("00:00")){
				Session.get().error(getString("msgchoosetime"));
				return;
			}
			if (notificationModel.getSubject() == null ){
				info(getString("msgsubjectempty"));
				return;
			}		
			if (notificationModel.getNote() == null ){
				info(getString("msgsnotificationempty"));
				return;
			}

			Date parsedDateStart = timeService.parseDate(starttime);
			Date parsedDateEnd = timeService.parseDate(endtime);
			
			if(allDayEvent){
				parsedDateStart.setHours(0);
				parsedDateStart.setMinutes(0);
				parsedDateEnd.setHours(11);
				parsedDateEnd.setMinutes(59);
			}

			String timezone = timeService.getZone();
			if(starttime.length()>17)
				timezone = starttime.substring(17);
			
			
			ZonedDateTime nowtz = timeService.getOtherTimeZoneNow(timezone);
				
			Timestamp starttimestamp = new Timestamp(parsedDateStart.getTime());
			Timestamp endtimestamp = new Timestamp(parsedDateEnd.getTime());
				
			ZonedDateTime startzdt = timeService.getTimeByTimeZone(starttimestamp, timezone);
			ZonedDateTime endzdt = timeService.getTimeByTimeZone(endtimestamp, timezone);
			System.out.println("startts = " + startzdt);

			if(startzdt.isAfter(endzdt))					{
				error(getString("msgendbeforestart1"));
				return;
			}
				
			if(nowtz.isAfter(startzdt)) {
				System.out.println("start time before now");
				Session.get().error(getString("msgstartinpast"));
				return;
			}
				
			if(parsedDateStart.compareTo(parsedDateEnd) > 0){
				Session.get().error(getString("msgendbeforestart2"));
				return;
			}
			Integer indexType = NotificationService.indexOffice;
			if( allDayEvent){
				indexType = NotificationService.indexAllDay;
			}
			else if(generalInfoEvent){
				indexType = NotificationService.indexNonCalendar;
			}
										
			notificationModel.setStartDate(starttimestamp);
				
			notificationModel.setExpiryDate(endtimestamp);
				
			notificationModel.setTimeZone(timezone);
			    
			notificationModel.setCreatedById(LMSSession.get().getUser(getRequest()).getId());
			notificationModel.setOrganizationId(LMSSession.get().getCurrentOrganization());
			notificationModel.setType(indexType);
			if(eventId != null && eventId > 0){
				notificationService.updateNotification(notificationModel);
			}
			else{
				notificationService.createNotification(notificationModel);
			} 
			setResponsePage(OfficeAdminOverviewPage.class);
		}
	}
	
	
	
}

