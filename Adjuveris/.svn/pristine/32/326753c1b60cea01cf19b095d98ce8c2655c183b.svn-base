package com.linguaclassica.teacher;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class TeacherPreferences extends TeacherBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;
	
	@SpringBean
	TermService termService;
	
	@SpringBean
	UserService userService;

	private static final List<String> DateSettings = Arrays.asList("yyyy-MM-dd", "MM-dd-yy", "dd-MM-yy");
	private static final List<String> TimeSettings = Arrays.asList("HH:mm", "hh:mm a");
	
	public TeacherPreferences()
	{
		TePreferencesForm form = new TePreferencesForm();
		add(form);
	}

	class TePreferencesForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		// Active user
		UserModel userModel;
		EntityTermModel destinationTerm;
		String dateformat;
		String timeformat;
		
		TePreferencesForm()
		{
			super("teachpreff");
			
			System.out.println("TePreferencesForm");
			
			// Get passed parameters
			userModel = AlphPlusSession.get().getUser(getRequest());
			Integer institutionId = AlphPlusSession.get().getCurrentInstitution();
			dateformat = AlphPlusSession.get().getDateFormat();
			timeformat = AlphPlusSession.get().getTimeFormat();

			// Create a drop-down control to select the date format
			ChoiceRenderer<String> dateRenderer = new ChoiceRenderer<String>()
			{
				private static final long serialVersionUID = 1L;
				
				@Override
				public Object getDisplayValue(String value)
				{
					LocalDate today = LocalDate.now();
					switch (value)
					{
						case "yyyy-MM-dd":
							return value + "  (" + today.format(DateTimeFormatter.ofPattern(value)) + ")";
						case "MM-dd-yy":
							return value + "  (" + today.format(DateTimeFormatter.ofPattern(value)) + ")";
						case "dd-MM-yy":
							return value + "  (" + today.format(DateTimeFormatter.ofPattern(value)) + ")";
						default:
							throw new IllegalStateException(value + " is not mapped!");
					}
				}
			};
			DropDownChoice<String> dateselect = new DropDownChoice<String>("datepref", 
					new PropertyModel<String>(this, "dateformat"), DateSettings, dateRenderer);
			dateselect.setNullValid(false);
			dateselect.setRequired(true);
			add(dateselect);

			// Create a drop-down control to select the time format
			ChoiceRenderer<String> timeRenderer = new ChoiceRenderer<String>()
			{
				private static final long serialVersionUID = 1L;
				
				@Override
				public Object getDisplayValue(String value)
				{
					LocalTime today = LocalTime.now();
					switch (value)
					{
						case "HH:mm":
							return value + "  (" + today.format(DateTimeFormatter.ofPattern(value)) + ")";
						case "hh:mm a":
							return value + "  (" + today.format(DateTimeFormatter.ofPattern(value)) + ")";
						default:
							throw new IllegalStateException(value + " is not mapped!");
					}
				}
			};
			DropDownChoice<String> timeselect = new DropDownChoice<String>("timepref", 
					new PropertyModel<String>(this, "timeformat"), TimeSettings, timeRenderer);
			timeselect.setNullValid(false);
			timeselect.setRequired(true);
			add(timeselect);
			
			// Create a drop-down control to select a term
			List<EntityTermModel> termsList = termService.getTermsByInstTeacher(institutionId, userModel.getId());
			destinationTerm = (EntityTermModel) modelFactory.getNewTermModel();
			destinationTerm.setId(AlphPlusSession.get().getCurrentTerm());
			final DropDownChoice<EntityTermModel> termselect = new DropDownChoice<EntityTermModel>("termpref",
					new PropertyModel<EntityTermModel>(this, "destinationTerm"), termsList, new ChoiceRenderer<EntityTermModel>("termname", "id"));
			termselect.setNullValid(false);
			termselect.setRequired(true);
			add(termselect);
		}
		
		public void onSubmit()
		{
			System.out.println("TePreferencesForm.onSubmit");
			
			try
			{
				AlphPlusSession.get().setDateFormat(dateformat);
				AlphPlusSession.get().setTimeFormat(timeformat);
				AlphPlusSession.get().setCurrentTerm(destinationTerm.getId());
			}
			catch (Exception e)
			{
				Session.get().error("Something failed when trying to update the preferences.");
				return;
			}
			
			setResponsePage(TeacherMessagePage.class);
		}
	}
}
