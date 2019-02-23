package com.linguaclassica.shared;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class PasswordValidator implements IValidator<String>
{
	private static final long serialVersionUID = 1L;
	
    // The pattern matches any alphanumeric pattern between 6 to 20 times
	// change the regex pattern if the input requirement rule changes.
	private static Pattern VALID_PASSWORD_INPUT = Pattern.compile("[A-Za-z0-9]{6,20}");

	/**
	 * Check whether the parameter represents a valid password
	 * 
	 * @param String s
	 * @return true when valid
	 */
	public static boolean checkPasswordInput(String s)
	{
		if (s == null)
		{
			return false;
		}
		else
		{
			Matcher m = VALID_PASSWORD_INPUT.matcher(s);
			return m.matches();
		}
	}

	@Override
	public void validate(IValidatable<String> validatable)
	{
		// get input from attached component
		final String field = validatable.getValue();

		// validate name
		if (!checkPasswordInput(field))
		{
			// Generate an error message from key "<control_id>.PasswordValidator"
			ValidationError error = new ValidationError();
			error.addKey(getClass().getSimpleName());
			validatable.error(error);
		}
	}

}
