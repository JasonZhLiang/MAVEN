package com.linguaclassica.shared;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import com.linguaclassica.entity.EntityUserModel;

public class UsernameRenderer implements IChoiceRenderer<EntityUserModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LogManager.getLogger(UsernameRenderer.class);

	@Override
	public Object getDisplayValue(EntityUserModel object) {
		return object.getFirstName() + " " + object.getLastName();
	}

	@Override
	public String getIdValue(EntityUserModel object, int index) {
		return object.getId().toString();
	}

	/*
	* The type UsernameRenderer must implement the inherited abstract method 
	* IChoiceRenderer<EntityUserModel>.getObject(String, IModel<? extends List<? extends EntityUserModel>>)
	* 
	* This method had to be added when migrating to Wicket 7. No sure if it works.
	*/
	@Override
	public EntityUserModel getObject(String id, IModel<? extends List<? extends EntityUserModel>> choices)
	{
		try {
			Integer localid = Integer.parseInt(id);
			for(int index = 0; index < choices.getObject().size(); index++)
			{
				if(choices.getObject().get(index).getId() == localid)
				{
					return choices.getObject().get(index);
				}
			}
			logger.debug("getObject did not find a matching id");
			return null;
			//return id != null ? choices.getObject().get(Integer.parseInt(id)) : null;
		}
		catch (Exception e) {
			return null;
		}
	} 
}
