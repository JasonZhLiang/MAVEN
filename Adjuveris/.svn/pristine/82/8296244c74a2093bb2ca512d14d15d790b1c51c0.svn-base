package com.linguaclassica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linguaclassica.model.AssignmentModel;

@Service
public class UpdateAssignmentsService {
	
	@Autowired
	AssignmentService assignmentService;
	
	public void updateAssignment(){
		
		//update all assignments status if due date is past
        List<AssignmentModel> assignmentsList = assignmentService.getListOfAssignmentsByStatus("START");
        for(int i = 0; i < assignmentsList.size(); i++){
    		java.util.Date nowutil = new java.util.Date();
    	    java.sql.Date now = new java.sql.Date(nowutil.getTime());
        	if(assignmentsList.get(i).getDueDate().before(now)){
        		assignmentsList.get(i).setStatus("COMPLETED");
        		assignmentService.updateAssignment(assignmentsList.get(i));
        	}
        }
        return;
	}

}
