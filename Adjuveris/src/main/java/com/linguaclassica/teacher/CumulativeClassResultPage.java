package com.linguaclassica.teacher;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityClassAssignmentResultsModel;
import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ResultsService;
import com.linguaclassica.service.ClassService;
import com.linguaclassica.service.AssignmentFactService;

public class CumulativeClassResultPage extends TeacherBasePage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private ClassService classService;
	
	@SpringBean
	private AssignmentService assignmentService;
	
	@SpringBean
	private AssignmentFactService assignmentFactService;
	
	@SpringBean
	private ResultsService resultsService;
	
	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());	
	Integer selectedClassId;
	Integer teacherId;
	String selectedClassName;	
	
	public CumulativeClassResultPage(final String idIn, String category, Integer selectedClassId)
	{
		super();
		this.selectedClassId = selectedClassId;
		this.teacherId = userModel.getId();
		this.selectedClassName = classService.findClassById(selectedClassId).getClassname();
		buildForm(category);
	}
	
	private void buildForm(String categoryStr) {	
		System.out.println("CumulativeClassResultPanel buildForm()"); 
		
		// retrieves all of the class' assignment results
		List<EntityClassAssignmentResultsModel> classAssignmentResults = resultsService.getListOfClassAssignmentResultsByClassId(selectedClassId);

		// creates two containers- one default and one for displaying the class results
		WebMarkupContainer noassignment = new WebMarkupContainer("noassignment");
		WebMarkupContainer aggregateAssignmentResults = new WebMarkupContainer("aggregateAssignmentResults");
		add(noassignment);		
		add(aggregateAssignmentResults);
		
		// If the teacher's class has no completed assignments
		//  then default container is displayed
		if (classAssignmentResults.isEmpty()) {
			noassignment.setVisible(true);
			aggregateAssignmentResults.setVisible(false);
		}
		else {
			noassignment.setVisible(false);
			aggregateAssignmentResults.setVisible(true);			
		}
		
		aggregateAssignmentResults.add(new Label("classname","Class selected: " + selectedClassName));
		aggregateAssignmentResults.add(new Label("classnametitle",selectedClassName));
		
		// adds each of the class' aggregate assignment results to container
		aggregateAssignmentResults.add(new ListView<EntityClassAssignmentResultsModel>("assignmentList", classAssignmentResults) {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<EntityClassAssignmentResultsModel> item){
				
				// declarations
				final EntityClassAssignmentResultsModel assignResults = item.getModelObject();
		        String assignname = "N/A";
		        String duedateform = "N/A";
				String studentcountstr = "N/A";
		        String averagestr = "N/A";
		        AssignmentModel assignModel = null;

		        // retrieves associated assignment object 
		         try{   	
		        	 assignModel = assignmentService.findAssignmentById(assignResults.getAssignmentId()); 
		        } catch(Exception e){
		             System.out.println("CumulativeClassResultsPanel populateItem: Unable to properly populate aggregate assignment results- "
		                			+ e.getCause());
		             assignModel=null;
		        }
						
		        // if assignment was able to be properly retrieved, prepare display text for panel
		        DecimalFormat df = new DecimalFormat("##.##");
		        if(assignModel!=null){
		        	averagestr = df.format(assignResults.getAverage());
		        	averagestr += "%";
		        	studentcountstr = assignResults.getStudentCount().toString();///Float.toString(assignResults.getStudentCount());
		    				
		        	// formats assignment's due date for displaying on panel
		        	java.util.Date duedate = assignModel.getDueDate();
		        	duedateform = DateFormat.getDateTimeInstance(
		        			DateFormat.SHORT, DateFormat.SHORT).format(duedate);
							
		        	assignname = assignModel.getAssignmentName();
		        }
	
		        String standardstr = "--";
		        if (assignModel.getType().equals("STANDARD")) {		        	
		        	standardstr = "Standardized";
		        }
		        System.out.println("studentcountstr, averagestr = " + studentcountstr + ", " + 
		        		averagestr);
							
				item.add(new Label("assignname",assignname));
				item.add(new Label("datelab",duedateform)); 
				item.add(new Label("studentcountlab", studentcountstr));
				item.add(new Label("averagelab",averagestr));
				item.add(new Label("standardlab",standardstr));

			}
		});

	}	
	
}
