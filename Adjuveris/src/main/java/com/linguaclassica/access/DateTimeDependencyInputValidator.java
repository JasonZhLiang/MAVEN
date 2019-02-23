package com.linguaclassica.access;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;

public class DateTimeDependencyInputValidator extends AbstractFormValidator
{
	private static final long serialVersionUID = 2322178460056434404L;
	
    private TextField<Date> fromDatetf;
    private TextField<Date> toDatetf;

    public DateTimeDependencyInputValidator(TextField<Date> fromTF, TextField<Date> toTF)
    {
    	fromDatetf = fromTF;
    	toDatetf = toTF;
    }

	@Override
	public FormComponent<?>[] getDependentFormComponents()
	{
		return new FormComponent[] { fromDatetf , toDatetf };
	}

	@Override
	public void validate(Form<?> form)
	{
		String startDate; 
		String endDate; 
		
		Date fromDate = new Date();
		Date toDate = new Date();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		startDate = fromDatetf.getValue();
		endDate = toDatetf.getValue();
		
		try
		{
			fromDate = formatter.parse(startDate);
		}
		catch (ParseException e)
		{
			fromDatetf.error("Failed to parse a Start date. Select or enter a date and time.");
			e.printStackTrace();
		}
		
		try
		{
			toDate = formatter.parse(endDate);
		}
		catch (ParseException e)
		{
			toDatetf.error("Failed to parse an End date. Select or enter a date and time.");
			e.printStackTrace();
		}
		
		if (toDate.before(fromDate) || toDate.equals(fromDate))
		{
			toDatetf.error("Date-Times are out of order. Start date-time must be prior to End date-time");
		}
	}

}
