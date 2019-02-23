package com.linguaclassica.student;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.shared.ExercisesResultsPanel;
import com.linguaclassica.model.UserModel;

public class ExercisesResultsPage extends StudentBasePage {
	private static final long serialVersionUID = 1L;

	
	public ExercisesResultsPage()
	{
		super();

		UserModel userModel = AlphPlusSession.get().getUser(getRequest());	

		Integer studentId = userModel.getId();
		Integer teacherId = 0;
		
		System.out.println("ExercisesResultsPage()  studentid, teacherId = " + studentId + ", " + teacherId);

		ExercisesResultsPanel erp = new ExercisesResultsPanel("ExercisesResultsPanel", "RESULTS", studentId, teacherId);
		add(erp);
	}
	/*
	public ExercisesResultsPage(Integer studentId, Integer teacherId)
	{
		super();
		System.out.println("ExercisesResultsPage(student, teacher)  studentid, teacherId = " + studentId + ", " + teacherId);

		ExercisesResultsPanel erp = new ExercisesResultsPanel("ExercisesResultsPanel", "RESULTS", studentId, teacherId);
		add(erp);
	}*/
}