package com.linguaclassica.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * NOTE!! NOTE!!
 * We may be able to delete this interface.
 * I have created an AssignmentStatus enumeration in AssignmentModel that I think 
 * does everything this does AND corresponds with the database data type (VARCHAR).
 * 
 * 
 * 
 * 
 * 
 * DELETE THIS INTERFACE?????
 */
//to find if we can set fix list values here not from database,Jaddy
public interface AssignmentStatusModel extends Serializable {
	
	/**
	 * Returns the unique ID field for this model.
	 * @return id
	 */
	public enum AssignmentStatus {
		WAIT, START, COMPLETED, PASSDUE, PRACTICE;
		
		/**
		 * Helper function to return list of possible values of this Enum as strings
		 * @return
		 */
		public List<String> getListOfAssignmentStatus(){
			ArrayList<String> ListOfAssignmentStatus = new ArrayList<String>();
			
			for (AssignmentStatus assign : AssignmentStatus.values()){
				ListOfAssignmentStatus.add(assign.toString());
			}
			return ListOfAssignmentStatus;
		}
	}
}
