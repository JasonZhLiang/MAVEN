package com.linguaclassica.teacher;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.shared.ExercisesResultsPanel;
import com.linguaclassica.model.UserModel;

public class ExercisesResultsPage extends TeacherBasePage {
	private static final long serialVersionUID = 1L;

	/*
	public ExercisesResultsPage()
	{
		super();

		UserModel userModel = AlphPlusSession.get().getUser(getRequest());	

		Integer studentId = userModel.getId();
		Integer teacherId = 0;
		
		ExercisesResultsPanel erp = new ExercisesResultsPanel("ExercisesResultsPanel", "RESULTS", studentId, teacherId);
		add(erp);
	}
	*/
	public ExercisesResultsPage(Integer studentId, Integer teacherId)
	{
		super();
		System.out.format("ExercisesResultsPage(studentid %d, teacherId %d)%n", studentId, teacherId);

		ExercisesResultsPanel erp = new ExercisesResultsPanel("ExercisesResultsPanel", "RESULTS", studentId, teacherId);
		add(erp);
	}
}
