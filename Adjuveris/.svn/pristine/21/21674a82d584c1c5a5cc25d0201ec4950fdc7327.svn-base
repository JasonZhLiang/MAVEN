package com.linguaclassica.shared;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityAssignmentFactModel;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityStudentAssignmentResultsModel;
import com.linguaclassica.model.AssignmentFactModel;
import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ResultsService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.AssignmentFactService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.service.UpdateAssignmentsService;

public class ExercisesResultsPanel extends Panel {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private ClassService classService;

	@SpringBean
	private UserService userService;
	
	@SpringBean
	private AssignmentService assignmentService;
	
	@SpringBean
	private AssignmentFactService assignmentFactService;
	
	@SpringBean
	private ResultsService resultsService;
	
	@SpringBean
	private UpdateAssignmentsService updateAssignmentsService;
	
	@SpringBean
	private TermService termService;
	
	
	// Active user
	private UserModel userModel = null; 	
	Integer studentId ;
	Integer teacherId;
	String idIn;
	List<EntityClassModel> classList = null;
	List<EntityAssignmentFactModel> assignFactList = null;

	public ExercisesResultsPanel(String idIn, String category, Integer studentId, Integer teacherId)
	{
		super(idIn);
		this.studentId = studentId;
		this.teacherId = teacherId;
		this.idIn = idIn;
		userModel = userService.findUserById(studentId);
		updateAssignmentsService.updateAssignment();
		buildPanel();
	}
	
	private void buildPanel()
	{
		System.out.println("ExercisesResultsPanel buildForm()");

		System.out.println("AlphPlusSession.get().getDateRange() in "
				+ "ExercisesResultsPanel: " + AlphPlusSession.get().getDateRange());
		
		// Fill header with student data
		add(new Label("studentnamelab", userModel.getFirstName() + " " + userModel.getLastName()));
		String studentcodestr = "No student code";
		if((userModel.getStudentnumber() != null) && (userModel.getStudentnumber().length() > 0))
		{
			studentcodestr = userModel.getStudentnumber().toString();
		}
		add(new Label("studidlab", studentcodestr));
		
		// retrieves all classes that student is enrolled in
		Integer institutionId = AlphPlusSession.get().getCurrentInstitution();
		Date now = new Date(Calendar.getInstance().getTime().getTime());
		try{
			if (teacherId == 0) {
				classList = classService.getRecentClassesForUser(institutionId, studentId, "STUDENT", AlphPlusSession.get().getDateRange());
			}
			else {
				System.out.println("Have to get list of classes by studentId AND teacherId, "
						+ "studentid, teacherId = " + studentId + ", " + teacherId);
				Integer termsetting = AlphPlusSession.get().getDateRange();
				classList = classService.getListOfClassesByUserIdAndTeacherId(institutionId, studentId, teacherId, "STUDENT", now, termsetting);
			}
		}
		catch(Exception e){
			classList = null;
			System.out.println(e.getCause());
		}
		
		// gets list of completed assignment facts ids for student
		try{
			assignFactList = assignmentFactService.getListOfAssignmentFactByStudentId("COMPLETED","",studentId, null);
			for(int i = 0; i < assignFactList.size(); i++){
				resultsService.addStudentAssignmentResults(assignFactList.get(i).getId());
			}
		} catch(Exception e){
			assignFactList = null;	
			System.out.println(e.getCause());
		}
		
		// The container will be displayed when there are no classes or no marked assignments
		add(new WebMarkupContainer("noresults")
		{
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isVisible()
			{
				return (classList.size() < 1) || (assignFactList.size() < 1);
			}
		});		

		// The container will be displayed when assignments have been marked
		WebMarkupContainer completedAssignments = new WebMarkupContainer("completedAssignments");
		add(completedAssignments);
		
		System.out.println("ExerciseResultsPanel, line 120:  classlist.size();, assignFactList.size() = " +
				classList.size() + ", " + assignFactList.size());
		if (classList.size() < 1 || assignFactList.size() < 1) {
			completedAssignments.setVisible(false);
		}
		else {
			completedAssignments.setVisible(true);			
		}
	
		// adds each class along with its respective completed assignments to container
		completedAssignments.add(new ListView<EntityClassModel>("classList", classList) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<EntityClassModel> item)
			{
				EntityClassModel currentClass = item.getModelObject();
				item.add(new Label("classnameLab",currentClass.getClassname()));
				
				// gets all of the completed assignments facts for this class
				List<EntityAssignmentFactModel> classAssignFactModelList = null;
				List<Integer> classAssignFactIdList = new ArrayList<Integer>();
				try{
					classAssignFactModelList = assignmentFactService.getListOfAssignmentFactByClassId(item.getModelObject().getId(), "COMPLETED");
					
					// adds the assignment fact ids to 'classAssignFactIdList'
					for(EntityAssignmentFactModel assignFactModel: classAssignFactModelList){
						classAssignFactIdList.add(assignFactModel.getId());
					}
				} catch(Exception e){
					System.out.println("ExercisesResultsPanel populateItem- Unable to retrieve assignment facts for class: "
							+ currentClass.getClassname() + " " + e.getCause());
					classAssignFactIdList = new ArrayList<Integer>();
				}
				
				// retrieves a list of all of the student's assignment results
				//  the method invoked automatically adds results that have not been calculated yet to 'studentAssignResults'
				//  returns blank list if no results found
				List<EntityStudentAssignmentResultsModel> studentAssignResults = 
						resultsService.getListOfAssignmentResultsByStudentId(studentId,"COMPLETED", null);
				
				System.out.println("ExercisesResultsPanel studentAssignResults for " + currentClass.getClassname());
				System.out.println("ExercisesResultsPanel studentAssignResults size: " + studentAssignResults.size());
				
				// compiles a list for this particular class of all of the completed assignments results for this 
				//student
				List<EntityStudentAssignmentResultsModel> selectedStudentResultsList = 
						new ArrayList<EntityStudentAssignmentResultsModel>();
				
				// traverses through list of student assignment results
				// adds the results that are associated with this specific class to 'selectedStudentResultsList'
				for(EntityStudentAssignmentResultsModel studentResultsModel: studentAssignResults){
					System.out.println("ExerciseResultsPanel, line 167:  studentResultsModel.getAssignmentFactId() = " + 
							studentResultsModel.getAssignmentFactId());
					if(classAssignFactIdList.contains(studentResultsModel.getAssignmentFactId())){
						selectedStudentResultsList.add(studentResultsModel);
					}
				}
				
				System.out.println("ExercisesResultsPanel selectedStudentResultsList size: " + selectedStudentResultsList.size());
				
				item.add(new ListView<EntityStudentAssignmentResultsModel>("assignmentList", selectedStudentResultsList) {

					private static final long serialVersionUID = 1L;

					@Override
					protected void populateItem(ListItem<EntityStudentAssignmentResultsModel> subitem)
					{
		                final EntityStudentAssignmentResultsModel assignResults = subitem.getModelObject();
		                String ratiostr = "N/A";
		                String percentstr = "N/A";
		                String duedateform = "N/A";
		                String assignname = "N/A";
		                AssignmentModel assignModel = null;

		                // retrieves associated assignment object 
		                System.out.println("ExerciseResultsPanel, line 195:  assignResults.getAssignmentFactId() = " + 
		                		assignResults.getAssignmentFactId());
		                try{
		                	AssignmentFactModel assignFact = assignmentFactService.findAssignmentFactById(assignResults.getAssignmentFactId());
		                	assignModel = assignmentService.findAssignmentById(assignFact.getAssignmentId()); 
		                } catch(Exception e){
		                	System.out.println("ExercisesResultsPanel populateItem: Unable to properly populate student assignment results- "
		                			+ e.getCause());
		                	assignModel=null;
		                }
						
		                // if assignment was able to be properly retrieved, prepare display text for panel
		                String standardstr = "--";
		                if(assignModel!=null){
		                	DecimalFormat df = new DecimalFormat("##.##");
		    				percentstr = df.format(assignResults.getPercent());
		    				percentstr += "%";
		    				String countstr = Float.toString(assignResults.getCorrectNumber()); // get the number of correct answers
		    				String totalstr = Float.toString(assignResults.getTotal());
		    				ratiostr = countstr + "/" + totalstr;
		    				
							// formats assignment's due date for displaying on panel
							java.util.Date duedate = assignModel.getDueDate();
							duedateform = DateFormat.getDateTimeInstance(
						            DateFormat.SHORT, DateFormat.SHORT).format(duedate);
							
							assignname = assignModel.getAssignmentName();
							if (assignModel.getType().equals("STANDARD")) {
								standardstr = "Standardized";
							}
		                }
		                System.out.println("ratiostr, percentstr = " + ratiostr + ", " + percentstr);
							
						subitem.add(new Label("assignname",assignname));
						subitem.add(new Label("datelab",duedateform)); 
						subitem.add(new Label("ratiolab", ratiostr));
						subitem.add(new Label("percentlab",percentstr));
						subitem.add(new Label("standardlab",standardstr));

						// adds 'detailed results' button to panel per assignment
						if (assignModel!=null){
							subitem.add(new Link<Object>("chooseAssign") {
								private static final long serialVersionUID = 1L;
								@Override
								public void onClick() {
									//System.out.println("assignfact.getId() = " + assignResults.getId());
									//String assignfactid = Integer.toString(assignResults.getId());assignResults.getAssignmentFactId()
									System.out.println("assignResults.getAssignmentFactId() = " + assignResults.getAssignmentFactId());
									String assignfactid = Integer.toString(assignResults.getAssignmentFactId());
									PageParameters params = new PageParameters();
									params.add("assignfactid", assignfactid);
									//setResponsePage(DetailedResultsPage.class,params);
									ExercisesResultsPanel.this.replaceWith(new DetailedResultsPanel(idIn,"RESULTS", params));
								}	
							});
						} else { // do not add a detail button
							subitem.add(new Label("chooseAssign",""));

						}
					}
				});
			}
		});

	}	
	
}
