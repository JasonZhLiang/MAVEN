package com.linguaclassica.instadmin;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.springframework.dao.DataIntegrityViolationException;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.DateDependencyInputValidator;
import com.linguaclassica.access.DateStringValidator;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.TermModel;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.UserService;

public class TermPage extends InstAdminBasePage
{
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	ModelFactory modelFactory;
	
	@SpringBean
	TermService termService;
	
	@SpringBean
	UserService userService;
	
	public TermPage()
	{
		EntityTermModel newterm = (EntityTermModel) modelFactory.getNewTermModel();
		
		add(new Label("termaction", "Create Term"));
		
		Form<Object> form = new CreateEditTermForm(newterm);
		add(form);
	}
	
	public TermPage(EntityTermModel existingterm)
	{
		add(new Label("termaction", "Edit Term"));
		
		Form<Object> form = new CreateEditTermForm(existingterm);
		add(form);
	}
	
	class CreateEditTermForm extends Form<Object>
	{
		private static final long serialVersionUID = 1L;
		
		TextField<String> termnametf;
		TextField<Date> startdatetf;
		TextField<Date> enddatetf;
		
		TermModel termEntity;
		String sStartdate;
		String sEnddate;
		
		CreateEditTermForm(EntityTermModel oneTerm)
		{
			super("manualcreateform");

			termEntity = oneTerm;
			if (termEntity.getInstid() > 0)
			{
				// translate existing data
				sStartdate = oneTerm.getStartDate().toString();
				sEnddate = oneTerm.getEndDate().toString();
			}
			
			termnametf = new TextField<String>("termnametf", new PropertyModel<String>(oneTerm, "termname"));
			startdatetf = new TextField<Date>("starttimefield", new PropertyModel<Date>(this, "sStartdate"));
			enddatetf = new TextField<Date>("endtimefield", new PropertyModel<Date>(this, "sEnddate"));
			termnametf.setRequired(true);
			termnametf.add(StringValidator.maximumLength(16));
			startdatetf.setRequired(true);
			enddatetf.setRequired(true);
			
			add(termnametf);
			add(startdatetf);
			add(enddatetf);
			
			// TODO time zone may be uncertain
			LocalDate localToday = LocalDate.now();
			Date today = Date.from(localToday.atStartOfDay(ZoneId.systemDefault()).toInstant());
			LocalDate localTomorrow = localToday.plusDays(1);
			Date tomorrow = Date.from(localTomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
			LocalDate localLimit = localToday.plusYears(5);
			Date limitdate = Date.from(localLimit.atStartOfDay(ZoneId.systemDefault()).toInstant());
			add(new DateStringValidator(startdatetf, today, limitdate, "yyyy-MM-dd"));
			add(new DateStringValidator(enddatetf, tomorrow, limitdate, "yyyy-MM-dd"));
			add(new DateDependencyInputValidator(startdatetf, enddatetf));
		}

		public void onSubmit()
		{
			java.sql.Date start = java.sql.Date.valueOf(sStartdate);
			java.sql.Date end = java.sql.Date.valueOf(sEnddate);			
			
			termEntity.setStartDate(start);  		
			termEntity.setEndDate(end);
			
			try
			{
				if (termEntity.getId() > 0)
				{
					// existing term
					try
					{
						termService.updateTerm(termEntity);
					}
					catch (DataIntegrityViolationException dive)
					{
						error("Update failed. The term name may already be used.");
						return;
					}
					catch (Exception e)
					{
						error("Something failed when trying to update the term.");
						return;
					}
				}
				else
				{
					// new term
					Integer institutionId = AlphPlusSession.get().getCurrentInstitution();
					termEntity.setInstid(institutionId);
					
					int success = termService.createTerm(termEntity);
					System.out.format("TermPage() create %d success = %d%n", termEntity.getId(), success);
				}

				// Save Institution ID in the session object for use by other forms
				AlphPlusSession.get().setCurrentTerm(termEntity.getId());
				setResponsePage(TermsListPage.class);
			}
			catch (Exception ex)
			{
				error("Failed to create or update a term.");
			}
		}
	}
}
