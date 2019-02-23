package com.linguaclassica.shared;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.entity.EntityNotificationModel;
import com.linguaclassica.fullcalendar.callback.ClickedEvent;
import com.linguaclassica.fullcalendar.callback.SelectedRange;
import com.linguaclassica.fullcalendar.net.EventCalendar;
import com.linguaclassica.fullcalendar.net.ButtonText;
import com.linguaclassica.fullcalendar.net.CalendarResponse;
import com.linguaclassica.fullcalendar.net.Config;
import com.linguaclassica.fullcalendar.net.EventNotFoundException;
import com.linguaclassica.fullcalendar.net.EventProvider;
import com.linguaclassica.fullcalendar.net.EventSource;
import com.linguaclassica.fullcalendar.net.FullCalendar;
import com.linguaclassica.fullcalendar.selector.EventSourceSelector;
import com.linguaclassica.officeadmin.CreateNotificationPage;
import com.linguaclassica.service.NotificationService;
import com.linguaclassica.shared.PrivateBasePage;

public class SharedOverviewPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;

	@SpringBean
	static 	NotificationService notificationService;

	private  Boolean editable = false;
	private static int startHour = 7;
	private static int startMinutes = 30;
	private static int endHour = 16;
	private static int endMinutes = 30;
	
	public SharedOverviewPage(Boolean editable)
	{
		super();
		this.setEditable(editable);
		SetCalendar();
		SetNotifications();
		
	}
	
	
	void SetCalendar(){
		/*this.getFeedbackMessages();
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);*/

		Config config = new Config();
		config.setSelectable(true);
		config.setSelectHelper(true);
		config.setEditable(false);
		final List<String> eventColourList = Arrays.asList(new String[] {
				"#63BA68", "#ff9f89", "#B1ADAC", "#E6CC7F"});
		
		for(int i = 0; i < NotificationService.indexNonCalendar; i++){
			EventSource eventSource = new EventSource();	
			eventSource.setEventsProvider(new OfficeEventsProvider(NotificationService.notificationIndexList.get(i)));
			eventSource.setTitle(NotificationService.notificationTypeList.get(i));//for display in checkbox
		//	eventSource.setEditable(true);
			eventSource.setBackgroundColor(eventColourList.get(i));
			eventSource.setBorderColor(eventColourList.get(i));
			config.add(eventSource);
		}


		config.getHeader().setLeft("prev,next today");
		config.getHeader().setCenter("title");
		config.getHeader().setRight("month,agendaWeek,agendaDay,listWeek");

		config.getButtonText().setToday("Today");//"Week");

		config.setLoading("function(bool) { if (bool) $(\"#loading\").show(); else $(\"#loading\").hide(); }");
		config.setMinTime(new LocalTime(startHour, startMinutes));
		config.setMaxTime(new LocalTime(endHour, endMinutes));
		config.setAllDaySlot(false);
		boolean bFrench = this.getSession().getLocale().getLanguage().equals("fr");
		if(bFrench){
			config.setMonthNames(new String[]{"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre",
					  "Octobre", "Novembre", "Décembre"});
			
			config.setMonthNamesShort(new String[]{"Janv.", "Fév.", "Mars",
					  "Avr.", "Mai", "Juin", "Juil.", "Août", "Sept.", "Oct.", "Nov.", "Déc."});
			config.setDayNames(new String[]{"Dimanche", "Lundi", "Mardi", "Mercredi",
					  "Jeudi", "Vendredi", "Samedi"});
			config.setDayNamesShort(new String[]{"Dim", "Lun", "Mar", "Mer",
					 "Jeu", "ven", "Sam"});
			
			ButtonText buttext = config.getButtonText();
			buttext.setToday("Aujourd'hui");
			buttext.setWeek("Semaine");
			buttext.setMonth("Mois");
			buttext.setDay("Jour");
		//	buttext.setPrev("Précédent");
		//	buttext.setNext("Suivant");
		//	buttext.setPrevYear(prevYear);
		//	buttext.setNextYear(nextYear);
		}
		
		FullCalendar calendar = new FullCalendar("calendar", config) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("static-access")
			@Override

			protected void onDateRangeSelected(SelectedRange range, CalendarResponse response) {
			
			//	info("Selected region: " + range.getStart() + " - " + range.getEnd() + " / allDay: " + range.isAllDay());
				
				//if range is more than a day, assign it alert status - don't show in Calendar, only in the list
				//selection from the month view gives all day range == office type
				// range of hours within a day - events
			//	response.getTarget().add(feedback);
				if(!editable)
					return;
				
				DateTime timeStart = range.getStart();
				DateTime timeEnd = range.getEnd();	
				Boolean today = timeStart.getDayOfYear() == timeStart.now().getDayOfYear();
				//if before today but not today
				if(timeStart.isBeforeNow()&& timeEnd.isBeforeNow() && !today){
					return;
				}
				PageParameters paramTimeRange = new PageParameters();
				if(!today && timeStart.isEqual(timeEnd)){
					timeStart = timeStart.withTime(startHour, startMinutes, 0, 0);
					timeEnd = timeStart.plusHours(1);
				}
				
				if(!today){//if clicked on a day from the month view
				
					paramTimeRange.add("starttime",timeStart );
				    paramTimeRange.add("endtime",timeEnd);
				}
				setResponsePage(CreateNotificationPage.class,paramTimeRange);//, paramEditClient);

			}
			/*
			@Override
			protected boolean onEventDropped(DroppedEvent event, CalendarResponse response) {
				info("EventCalendar drop. eventId: " + event.getEvent().getId() + " sourceId: " + event.getSource().getUuid()
					+ " dayDelta: " + event.getDaysDelta() + " minuteDelta: " + event.getMinutesDelta() + " allDay: "
					+ event.isAllDay());
				info("Original start time: " + event.getEvent().getStart() + ", original end time: "
					+ event.getEvent().getEnd());
				info("New start time: " + event.getNewStartTime() + ", new end time: " + event.getNewEndTime());

				response.getTarget().add(feedback);
				return false;
			}

			@Override

			protected boolean onEventResized(ResizedEvent event, CalendarResponse response) {
				info("EventCalendar resized. eventId: " + event.getEvent().getId() + " sourceId: " + event.getSource().getUuid()
					+ " dayDelta: " + event.getDaysDelta() + " minuteDelta: " + event.getMinutesDelta());
				response.getTarget().add(feedback);
				
				return false;
			}*/

			@Override

			protected void onEventClicked(ClickedEvent event, CalendarResponse response) {
			/*	info("EventCalendar clicked. eventId: " + event.getEvent().getId() + ", sourceId: "
					+ event.getSource().getUuid());
				response.refetchEvents();
				response.getTarget().add(feedback);*/
				PageParameters paramTimeRange = new PageParameters();
				EventCalendar eventCalendar = event.getEvent();
			    paramTimeRange.add("eventid",eventCalendar.getId());
			    paramTimeRange.add("editable",editable);
			   
				setResponsePage(CreateNotificationPage.class,paramTimeRange);//, paramEditClient);
		
			}
/*
			@Override
			protected void onViewDisplayed(View view, CalendarResponse response) {

				info("View displayed. viewType: " + view.getType().name() + ", start: " + view.getStart() + ", end: "
					+ view.getEnd());
				response.getTarget().add(feedback);
			}*/
		};
		calendar.setMarkupId("calendar");
		add(calendar);
		if(editable)
			add(new EventSourceSelector("selector", calendar));		
	}

	public Boolean getEditable() {
		return editable;
	}


	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	///////////////////////
	private static class OfficeEventsProvider implements EventProvider {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		Map<Integer, EventCalendar> events = new HashMap<Integer, EventCalendar>();
		private Integer type = 0;// Calendar event by default
	//	private final String title;
		private List<EntityNotificationModel> notificationList = null;

		public OfficeEventsProvider(Integer type) {
		//	this.title = title;
			this.type = type;
	
		}
		
		@Override
		public Collection<EventCalendar> getEvents(DateTime start, DateTime end) {
			events.clear();
					
			System.out.println("Time range " + start.getDayOfMonth() + " " + 
					start.getMonthOfYear() + " to " + end.getDayOfMonth() + " " + 
					end.getMonthOfYear());
			Timestamp currentTime = new Timestamp(start.getMillis());
			
			Timestamp endTime = new Timestamp(end.getMillis());
			if(notificationList != null && !notificationList.isEmpty())	{
				notificationList.clear();
			}
			
			notificationList = notificationService.findNotificationsByTypeRange(type, currentTime , endTime);
		
			int listLenght = 0;
			if(notificationList != null && !notificationList.isEmpty()){
				listLenght = notificationList.size();
			}
				
			for (int i = 0; i < listLenght; i++){
				
				EntityNotificationModel enm = notificationList.get(i);
				EventCalendar event = new EventCalendar();
				int id = enm.getId();//(int) (i * 35);
				event.setId("" + id);
				event.setTitle( enm.getSubject());// + " " + enm.getNote());
			//	event.setTitle(	(title + (1 + i)) + " " + enm.getNote());
				event.setStart(new DateTime(enm.getStartDate().getTime()));
				event.setEnd(new DateTime(enm.getExpiryDate().getTime()));
			
				events.put(id, event);
				
			}
			
			return events.values();
		}

		@Override
		public EventCalendar getEventForId(String id) throws EventNotFoundException {
			Integer idd = Integer.valueOf(id);
			EventCalendar event = events.get(idd);
			if (event != null) {
				return event;
			}
			throw new EventNotFoundException("EventCalendar with id: " + id + " not found");
		}
	}
	//Set future office notifications
	protected void SetNotifications(){
		List<EntityNotificationModel> notificationList = notificationService.getFutureNotifications(currentOrg.getId(),
				java.sql.Timestamp.from(Instant.now()));
		ListView<EntityNotificationModel> notificationView = new ListView<EntityNotificationModel>("noticeview", notificationList)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<EntityNotificationModel> item)
			{
				String sTime = item.getModelObject().getStartDate().toString();
				sTime = sTime.substring(0,sTime.lastIndexOf(":")).replace('-', '/');
				
				item.add(new Label("startime", sTime));
				sTime = item.getModelObject().getExpiryDate().toString();
				
				sTime = sTime.substring(0,sTime.lastIndexOf(":")).replace('-', '/');
				item.add(new Label("endingtime", sTime));

				item.add(new Label("noticemsg", item.getModelObject().getNote()));
				item.add(new AjaxEventBehavior("click")
				{
					private static final long serialVersionUID = 1L;

					@Override
				    protected void onEvent(AjaxRequestTarget target)
					{
				    	EntityNotificationModel notificationModel = item.getModelObject();
				    	
				    	PageParameters paramTimeRange = new PageParameters();
						
				    	paramTimeRange.add("editable",editable);
					    paramTimeRange.add("eventid",notificationModel.getId());
					   
						setResponsePage(CreateNotificationPage.class,paramTimeRange);//, paramEditClient);
				    }
				});
			}
			
		};
		add(notificationView);
	}
}