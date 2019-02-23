package com.linguaclassica.instadmin;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.UserService;
import com.linguaclassica.entity.EntityDateRangeModel;
import com.linguaclassica.service.DateRangeService;

public class InstitutionPreferences extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;
	
	@SpringBean
	UserService userService;

	@SpringBean
	DateRangeService dateRangeService;

	private static final List<String> PriorTermChoices = Arrays.asList("None", "One", "Two", "All");
	
	public InstitutionPreferences()
	{
		InstPreferencesForm form = new InstPreferencesForm();
		add(form);
	}

	class InstPreferencesForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		// Active user
		UserModel userModel;
		
		String studentrange;
		String iarange;
		
		InstPreferencesForm()
		{
			super("instpreff");
			
			System.out.println("InstPreferencesForm");
			
			// Get passed parameters
			userModel = AlphPlusSession.get().getUser(getRequest());
			Integer institutionId = AlphPlusSession.get().getCurrentInstitution();

			// Set the dropdowns according to the settings in the database
			studentrange = PriorTermChoices.get(0);
			iarange = PriorTermChoices.get(PriorTermChoices.size()-1);
			EntityDateRangeModel dateRange = dateRangeService.getDateRangeByInstId(institutionId);
			if (dateRange != null)
			{
				int studIndex, iaIndex;
				
				studIndex = dateRange.getStudentsTerms();
				if (studIndex == AlphPlusSession.BEGINNING_OF_TIME)
					studIndex = 3;
				else if (studIndex > 2)
					studIndex = 2;
				studentrange = PriorTermChoices.get(studIndex);
				
				iaIndex = dateRange.getInstAdminTerms();
				if (iaIndex == AlphPlusSession.BEGINNING_OF_TIME)
					iaIndex = 3;
				else if (iaIndex > 2)
					iaIndex = 2;
				iarange = PriorTermChoices.get(iaIndex);
			}
		
			// Create a drop-down control to select the student term range
			DropDownChoice<String> studselect = new DropDownChoice<String>("studrange", 
					new PropertyModel<String>(this, "studentrange"), PriorTermChoices);
			studselect.setNullValid(false);
			studselect.setRequired(true);
			add(studselect);

			// Create a drop-down control to select the inst admin term range
			DropDownChoice<String> iaselect = new DropDownChoice<String>("iarange", 
					new PropertyModel<String>(this, "iarange"), PriorTermChoices);
			iaselect.setNullValid(false);
			iaselect.setRequired(true);
			add(iaselect);
		}
		
		public void onSubmit()
		{
			System.out.println("InstPreferencesForm.onSubmit");
			
			try
			{
				/* Save the settings in the database */
				EntityDateRangeModel dateRange = new EntityDateRangeModel();
				
				dateRange.setInstId(AlphPlusSession.get().getCurrentInstitution());
				
				int studTerms = PriorTermChoices.indexOf(studentrange);
				if (studTerms == PriorTermChoices.size()-1)
					studTerms = AlphPlusSession.BEGINNING_OF_TIME;
				dateRange.setStudentsTerms(studTerms);

				int iaTerms = PriorTermChoices.indexOf(iarange);
				if (iaTerms == PriorTermChoices.size()-1)
					iaTerms = AlphPlusSession.BEGINNING_OF_TIME;
				dateRange.setInstAdminTerms(iaTerms);
				
				dateRangeService.setDateRange(dateRange);
			}
			catch (Exception e)
			{
				Session.get().error("Something failed when trying to update the preferences.");
				return;
			}
			
			setResponsePage(InstAdminOverviewPage.class);
		}
	}
}
