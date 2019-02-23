package com.linguaclassica.instadmin;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.validation.ValidationError;

public class SelectValidator extends AbstractFormValidator //implements IValidator<String>, IFormValidator
{
	private static final long serialVersionUID = 1L;
	
	List<DropDownChoice<?>> components = new ArrayList<DropDownChoice<?>>();
	FormComponent<?>[] fcarray;
	
	public SelectValidator(DropDownChoice<?> choice1)
	{
		components.add(choice1);
	}
	
	public void add(DropDownChoice<?> choicen)
	{
		components.add(choicen);
	}

	@Override
	public FormComponent<?>[] getDependentFormComponents()
	{
		fcarray = new FormComponent[components.size()];
		for (int index = 0; index < components.size(); index++)
		{
			fcarray[index] = components.get(index);
		}
		return fcarray;
	}

	@Override
	public void validate(Form<?> form)
	{
		// for each drop-down except the last
		for (int index = 0; index < components.size() - 1; index++)
		{
			DropDownChoice<?> ddcScan = components.get(index);
			String sScan = ddcScan.getValue();
			
			// compare with the rest of the drop-downs
			// the last drop-down is not required. it may contain an empty string
			for (int chex = index + 1; chex < components.size(); chex++)
			{
				DropDownChoice<?> ddcCheck = components.get(chex);
				if (sScan.equals(ddcCheck.getValue()))
				{
					ValidationError valError = new ValidationError();
					valError.setMessage("Multiple controls cannot have the same selection.");
					//TODO show control names using properties value (does not work)
					//valError.setVariable("pick0", ddcScan.getDefaultLabel());
					//valError.setVariable("pick1", ddcCheck.getDefaultLabel());
					ddcCheck.error(valError);
				}
			}
		}
	}
}
