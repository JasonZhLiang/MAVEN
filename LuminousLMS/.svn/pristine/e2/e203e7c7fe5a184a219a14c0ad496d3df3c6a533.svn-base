package com.linguaclassica.access;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.text.ParseException;

import org.apache.wicket.markup.html.form.Form; 
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;

public class DateDependencyInputValidator extends AbstractFormValidator {
	private static final long serialVersionUID = 2322178460056434404L;
	
    private TextField<Date> fromDatetf;
    private TextField<Date> toDatetf;

    public DateDependencyInputValidator(TextField<Date> pFromDate, TextField<Date> pToDate) {
    	this.fromDatetf = pFromDate;
    	this.toDatetf = pToDate;
    }
    
	
	@Override
	public FormComponent<?>[] getDependentFormComponents() {
        return new FormComponent[] { fromDatetf , toDatetf };
    }
	
	@Override
    public void validate(Form<?> form) { 
        String startDate; 
        String endDate; 
        
        Date fromDate = new Date();
        Date toDate = new Date();
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        startDate = fromDatetf.getValue();
        endDate = toDatetf.getValue();
        
        System.out.println("startDate = " + startDate);
        System.out.println("endDate = " + endDate);
        
        try {
        	fromDate = formatter.parse(startDate);
        } catch (ParseException e) {
        	 e.printStackTrace();
        }
        
        try {
        	toDate = formatter.parse(endDate);
        } catch (ParseException e) {
        	 e.printStackTrace();
        }
        
        if (toDate.before(fromDate) || toDate.equals(fromDate)) {
            error(toDatetf, "Date range is invalid.");
        }
    }

}
