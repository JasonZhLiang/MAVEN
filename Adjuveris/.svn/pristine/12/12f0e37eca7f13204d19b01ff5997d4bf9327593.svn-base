package com.linguaclassica.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Business layer interface for entity.
 */
public interface PanelsModel extends Serializable {
	/**
	 * Permissions can be simple user or administrator, or some institution administration assistant
	 * Not to be confused with group roles in GroupMembership
	 */
	public enum PanelType 
	{
		NOTIFICATIONS,ASSIGNMENTS,EXERCISES,RESULTS,ASSESSMENTS,GROUPS,EXERCISESAVAILABLE;
		/**
		 * Helper function to return list of possible values of this Enum as strings
		 * @return
		 */
		public List<String> getListOfPanels() 
		{
			ArrayList<String> listOfPanels = new ArrayList<String>();
			
			for (PanelType panl : PanelType.values())
			{
				listOfPanels.add(panl.toString());
			}
			return listOfPanels;
		}
	};

	Integer getId();

	PanelType getPanel();

	void setPanel(PanelType panel);
}
