package com.linguaclassica.access;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;

public class DateStringValidator extends AbstractFormValidator
{
	private static final long serialVersionUID = 592390526813580184L;
	
	private TextField<Date> thisDatetf;
	private Date minDate;
	private Date maxDate;
	private String sFormat;

	public DateStringValidator(TextField<Date> checkableDate, Date minimumDate, Date maximumDate, String dateFormat)
	{
		thisDatetf = checkableDate;
		minDate = minimumDate;
		maxDate = maximumDate;
		sFormat = dateFormat;
	}

	@Override
	public FormComponent<?>[] getDependentFormComponents()
	{
		return new FormComponent[] { thisDatetf };
	}

	@Override
	public void validate(Form<?> form)
	{
		Date checkDate = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
		
		String checkStr = thisDatetf.getValue();
		
		try
		{
			checkDate = formatter.parse(checkStr);
		}
		catch (ParseException pe)
		{
			thisDatetf.error("Failed to parse a date. Select or enter a date.");
		}
		
		if (checkDate.before(minDate) || checkDate.after(maxDate))
		{
			String sMin = formatter.format(minDate);
			String sMax = formatter.format(maxDate);
			String sError = String.format("A date is outside of the accepted range (%s to %s).", sMin, sMax);
			thisDatetf.error(sError);
		}
	}

}
