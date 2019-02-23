package com.linguaclassica.instadmin;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import com.linguaclassica.entity.EntityUserModel;

/**
 * Render the full name for the user entity
 * Author: Joe A. Brown 
 */
public class UsernameRenderer implements IChoiceRenderer<EntityUserModel>
{
	private static final long serialVersionUID = 1L;

	@Override
	public Object getDisplayValue(EntityUserModel object)
	{
		return object.getFirstName() + " " + object.getLastName();
	}

	@Override
	public String getIdValue(EntityUserModel object, int index)
	{
		return object.getId().toString();
	}

	/*
	* The type UsernameRenderer must implement the inherited abstract method 
	* IChoiceRenderer<EntityUserModel>.getObject(String, IModel<? extends List<? extends EntityUserModel>>)
	* 
	* This method had to be added when migrating to Wicket 7. No sure if it works.
	*/
	@Override
	public EntityUserModel getObject(String id, IModel choices)
	{
/*
		if (!choices.getObject().isEmpty()) 
		{
			int index = 0;
			while (index < choices.getObject().size())
			{
				if (0 == choices.getObject().get(index).getId().toString().compareTo(id))
				{
					return (EntityUserModel) choices.getObject().get(index); 
				}
			}
			
		}
*/		
		// Empty object or not found
		return null;
	} 
}
