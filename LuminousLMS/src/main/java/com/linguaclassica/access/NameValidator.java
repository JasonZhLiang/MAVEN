package com.linguaclassica.access;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class NameValidator implements IValidator<String>
{
	private static final long serialVersionUID = 1L;
	
	public NameValidator()
	{
		super();
	}

	@Override
	public void validate(IValidatable<String> validatable)
	{
		// get input from attached component
		final String field = validatable.getValue();

		// validate name
		if (!CommonUtilities.checkValidUserNameInput(field))
		{
			// Generate an error message from key "<control_id>.NameValidator"
			ValidationError error = new ValidationError();
			error.addKey(getClass().getSimpleName());
			validatable.error(error);
		}
	}
}
