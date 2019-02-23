package com.linguaclassica.officeadmin;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.entity.EntityNotificationModel;
import com.linguaclassica.service.NotificationService;
import com.linguaclassica.shared.SharedSelectionRangePage;


public class OfficeAdminNotificationsPage extends SharedSelectionRangePage
{
	private static final long serialVersionUID = 1L;
		
	@SpringBean
	NotificationService notificationService;

	
	private Logger logger = LogManager.getLogger(OfficeAdminNotificationsPage.class);
	
	public static List<EntityNotificationModel> notificationsList = null; 
	public static ListView<EntityNotificationModel> notifList = null;


	public OfficeAdminNotificationsPage()
	{
		super("notificationsf",NotificationService.notificationTypeList);
		
		logger.debug("OfficeAdminNotificationsPage");
		
	}
	
	protected void SetContainer()
	{
		NotificationForm nForm = new NotificationForm(notificationsList);
		add(nForm.setOutputMarkupId(true));
	}
	
	
	private ListView<EntityNotificationModel> PopulateList(){
		
		
		if(notificationsList != null && !notificationsList.isEmpty()){
			notificationsList.clear();
		}
		
			
		notificationsList = notificationService.getAllNotifications();
		if(notificationsList== null)
			notificationsList = new ArrayList<EntityNotificationModel>();
		
		if(!notificationsList.isEmpty()){
			setTimeRange(notificationsList.get(0).getStartDate(),notificationsList.get(notificationsList.size()-1).getStartDate() );
		}
		
		notifList = new ListView<EntityNotificationModel>("notifList",notificationsList)
		{
			private static final long serialVersionUID = 1L;
			public boolean isVisible()
			{
				return !notificationsList.isEmpty();
			}
			@Override
			protected void populateItem(ListItem<EntityNotificationModel> item)
			{
				EntityNotificationModel notifModel = (EntityNotificationModel) item.getModelObject();
				Boolean show = getSelection(notifModel.getType());					
				if(show){				
					show &= compareSelection(notifModel.getStartDate());
				}
				item.add(new Label("creationDatefld", notifModel.getStartDate()).setVisible(show));
				item.add(new Label("expirationDatefld", notifModel.getExpiryDate()).setVisible(show));
				item.add(new Label("subjectfld", notifModel.getSubject()).setVisible(show));
				item.add(new Label("notificationfld", notifModel.getNote()).setVisible(show));
				item.add(new AjaxEventBehavior("click")
				{
					private static final long serialVersionUID = 1L;

					@Override
				    protected void onEvent(AjaxRequestTarget target)
					{
				    	EntityNotificationModel notificationModel = item.getModelObject();
				    	
				    	PageParameters paramTimeRange = new PageParameters();
				    	
				    	paramTimeRange.add("editable",true);
				    	
					    paramTimeRange.add("eventid",notificationModel.getId());
					   
						setResponsePage(CreateNotificationPage.class,paramTimeRange);//, paramEditClient);
				    }
				});
					
			}				
		};
		notifList.setOutputMarkupId(true);
		
		add(notifList);
		return notifList;
		
	}
	
		
	class NotificationForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;

		public NotificationForm(List<EntityNotificationModel> notificationsList)
		{
			super("notificationsf");
			
			add(PopulateList());
		}
	}
}

