package com.linguaclassica.student;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.entity.EntityAssignmentFactModel;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.entity.EntityStudentAssignmentResultsModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.AssignmentFactService;
import com.linguaclassica.service.ResultsService;
import com.linguaclassica.service.StudentAnswerService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.service.TermService;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.QuestionService;

public class ExercisesMetricsPage extends StudentBasePage
{
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserService userService;
	@SpringBean
	private TermService termService;
	@SpringBean
	private ExerciseService exerciseService;
	@SpringBean
	private QuestionService questionService;
	@SpringBean
	private StudentAnswerService studentanswerService;
	@SpringBean
	private AssignmentService assignmentService;
	@SpringBean
	ResultsService resultsService;
	@SpringBean
	private AssignmentFactService assignmentfactService;
		
	List<EntityStudentAssignmentResultsModel> assignResultsList = 
			new ArrayList<EntityStudentAssignmentResultsModel>();
	
	String maxDate ="";
	String minDate= "";
	double[] proficiencyPct = new double[4];
	
	int assignfactid;
	int assignid;
	EntityAssignmentModel assignmentModel;
	 
	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());
	Integer studentId ;
	Integer teacherId;	

	public ExercisesMetricsPage()
	{
		super();
		this.studentId = userModel.getId();
		this.teacherId = 0;
	    setOutputMarkupId(true);		
	
		buildForm();
	}

	public ExercisesMetricsPage(final String idIn, String category, final Integer studentId, Integer teacherId)
	{
		super();
		this.studentId = studentId;
		this.teacherId = teacherId;
	    setOutputMarkupId(true);		
	
		buildForm();
	}

	private void buildForm()
	{
		// Get assignment results only from unrestricted terms
		Integer instId = AlphPlusSession.get().getCurrentInstitution();
		List<Integer> termIdList = termService.getListOfRecentTermIDs(instId, AlphPlusSession.get().getDateRange());

		assignResultsList = resultsService.getListOfAssignmentResultsByStudentId(studentId, "COMPLETED", termIdList);
		
		add(new ListView<EntityStudentAssignmentResultsModel>("proficiencyList", assignResultsList) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<EntityStudentAssignmentResultsModel> item)
			{
                final EntityStudentAssignmentResultsModel assignResult = item.getModelObject();
                assignfactid = assignResult.getAssignmentFactId();
                EntityAssignmentFactModel assignmentFactModel = 
                		assignmentfactService.findAssignmentFactById(assignfactid);
                assignid = assignmentFactModel.getAssignmentId();
                assignmentModel = assignmentService.findAssignmentById(assignid);
                
                Float percent = assignResult.getPercent();
                Float vocabpct = assignResult.getVocabpct();
                Float syntaxpct = assignResult.getSyntaxpct();
                Float inflectpct = assignResult.getInflectpct();
                Float comprehenpct = assignResult.getComprehenpct();
        		DecimalFormat df = new DecimalFormat("##.##");
        		String percentstr = df.format(percent);
        		String vocabpctstr = df.format(vocabpct)+"%";
        		if(assignResult.getVocabcnt() < 1) {
        			vocabpctstr = " ";
        		}
        		String syntaxpctstr = df.format(syntaxpct)+"%";
        		if(assignResult.getSyntaxcnt() < 1) {
        			syntaxpctstr = " ";
        		}
        		String inflectpctstr = df.format(inflectpct)+"%";
        		if(assignResult.getInflectcnt() < 1) {
        			inflectpctstr = " ";
        		}
        		String comprehenpctstr = df.format(comprehenpct)+"%";
        		if(assignResult.getComprehencnt() < 1) {
        			comprehenpctstr = " ";
        		}
        		
				item.add(new Label("idlab",assignid));
				item.add(new Label("duedate", assignmentModel.getDueDate()));
				item.add(new Label("ratiolab", assignResult.getCorrectNumber() + "/" + assignResult.getTotal()));
				item.add(new Label("percentlab",percentstr+"%"));
				item.add(new Label("proficiency1",vocabpctstr));
				item.add(new Label("proficiency2",syntaxpctstr));
				item.add(new Label("proficiency3",inflectpctstr));
				item.add(new Label("proficiency4",comprehenpctstr));
			}
		}); 
		
		add(new Label("proficiencylabel","Proficiencies mapping for:  "+userModel.getFirstName() + 
				" " + userModel.getLastName()));
	}	
}
