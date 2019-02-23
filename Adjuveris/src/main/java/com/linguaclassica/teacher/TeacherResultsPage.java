package com.linguaclassica.teacher;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.ClassChoice;
import com.linguaclassica.shared.TermSelectPanel;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityClassUsersModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.TeacherAssignmentResultsModel;
import com.linguaclassica.model.TeacherPreResultsModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentFactService;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.NotificationService;
import com.linguaclassica.service.ResultsService;
import com.linguaclassica.service.UpdateAssignmentsService;
import com.linguaclassica.service.UserService;

public class TeacherResultsPage extends TeacherBasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private NotificationService notificationService;

	@SpringBean
	private AssignmentFactService assignmentfactService;
	
	@SpringBean
	private AssignmentService assignmentService;
	
	@SpringBean
	private ResultsService resultsService;	
	
	@SpringBean
	private UserService userService;
	
	@SpringBean
	private ClassService classService;
	
	@SpringBean
	private UpdateAssignmentsService updateAssignmentsService;
	
	ModelFactory modelFactory;
	
	List<EntityClassModel> classSelection;
	
	List<String> classnameList;
	
	List<String> classesList;
	
	ArrayList<String> classesSelected;
	
	ArrayList<String> selClassesSelected;
	
	List<Integer> classIdList;
	
	List<EntityClassModel> classList;	
	
	String classListStr;
	
	int teacherid;

	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());

	public TeacherResultsPage()
	{
		super();

		classSelection = AlphPlusSession.get().getSelectedClasses();
		teacherid = userModel.getId();
		updateAssignmentsService.updateAssignment();
		buildForm();
	}

	private void buildForm() {
		System.out.println("TeacherResultsPage: beginning of buildform" );		
		
		System.out.println("AlphPlusSession.get().getUserPageTermId(): " + AlphPlusSession.get().getUserPageTermId());
		
		

		add(new TermSelectPanel("termpanel", "Assignment Results"));
		
		ClassChoice<String> checkboxes;
		
		checkboxes = new ClassChoice<String>("teacherResults", userModel.getId(), ClassChoice.DISPLAY,AlphPlusSession.get().getUserPageTermId());
		add(checkboxes);
		
		// Creates string of selected classes
		// String to be displayed at the top of the page
		if(classSelection!=null && classSelection.size()!=0){
			classListStr = classSelection.get(0).getClassname();
			System.out.println("0)  class = " + classSelection.get(0).getClassname() + " term = " + classSelection.get(0).getTermid());
			for(int i = 1; i < classSelection.size(); i++){
				classListStr+=", " + classSelection.get(i).getClassname();
				System.out.println(i + ")  class = " + classSelection.get(i).getClassname() + " term = " + classSelection.get(i).getTermid());
			}
		}else{
			classListStr = "No classes selected";
		}
		
		// Adds the names of the selected classes to the page
		String labelstr = "Classes selected:  " + classListStr; 
		add(new Label("classesSelected",labelstr));  

		System.out.println("classesSelected ......... = " + classListStr);
		
		String averagestr = "N/A";
		DecimalFormat df = new DecimalFormat("##.##");
		TeacherAssignmentResultsModel teacherResult = 
				resultsService.getTeacherAssignmentResultsByTeacherId(teacherid);
		if (teacherResult != null) {
			averagestr = df.format(teacherResult.getAverage());
			averagestr += "%";
		}
		averagestr = "Overall average for current term: " + averagestr;
		add(new Label("overallAverage",averagestr));
		
		String preaveragestr = "N/A";
		TeacherPreResultsModel teacherPreResult = 
				resultsService.getTeacherPreResultsByTeacherId(teacherid);
		if (teacherPreResult != null) {
			preaveragestr = df.format(teacherPreResult.getAverage());
			preaveragestr += "%";
		}
		preaveragestr = "Overall average for previous terms: " + preaveragestr;
		add(new Label("previousAverage",preaveragestr));
		
		System.out.println("TeacherResultsPage: getlistof notifications");
		
		add(new ListView<EntityClassModel>("classnameList", classSelection) {
			private static final long serialVersionUID = 1L;

			protected void populateItem(final ListItem<EntityClassModel> item) {				
				
				item.add(new Label("classnamelab",item.getModelObject().getClassname()));
				item.add(new Link<Void> ("classresultsbutton") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						System.out.println("Class Result button clicked.");
						System.out.println("Cumulative Class Result for "+ item.getModelObject().getClassname());
						setResponsePage(new CumulativeClassResultPage(TeacherResultsPage.this.getId(),"ASSESSMENTS", item.getModelObject().getId()));
					}
				});
				
		System.out.println("TeacherResultsPage: generating student list for class: "+ item.getModelObject().getClassname());
		List<EntityClassUsersModel> studentClassUsersList = new ArrayList<EntityClassUsersModel>();	
		studentClassUsersList.addAll(classService.getListOfUsersInAClassByUserType(item.getModelObject().getId(),"STUDENT"));

		List<EntityUserModel> studentList = new ArrayList<EntityUserModel>();
		for( EntityClassUsersModel i : studentClassUsersList ) {
			studentList.add((EntityUserModel) userService.findUserById(i.getUserId()));
		}

		item.add(new ListView<EntityUserModel>("userlist",studentList) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<EntityUserModel> subitem) {
				subitem.add(new Label("firstnamelab",subitem.getModelObject().getFirstName()));
				subitem.add(new Label("lastnamelab",subitem.getModelObject().getLastName()));
				subitem.add(new Link<Void> ("resultsbutton") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						int studentid = subitem.getModelObject().getId();
						System.out.println("TeacherResultPage, line 176:  studentid = " + studentid);
						System.out.println("TeacherResultsPage: Clicked the results button for student: "
								+ subitem.getModelObject().getFirstName() + " " 
								+ subitem.getModelObject().getLastName());

						setResponsePage(new ExercisesResultsPage(studentid,teacherid));
					}
				});
			}
		});
			}
		});

	}	
	
}
