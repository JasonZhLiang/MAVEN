package com.linguaclassica.shared;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import com.linguaclassica.model.ExerCategoryModel;
import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.model.StudentAnswerModel;
import com.linguaclassica.model.StudentAssignmentResultsModel;
import com.linguaclassica.model.ExerciseModel;
import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.access.AlphPlusSession;
import com.linguaclassica.access.User;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.AssignmentFactModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.AssignmentFactService;
import com.linguaclassica.service.QuestionService;
import com.linguaclassica.service.ResultsService;
import com.linguaclassica.service.StudentAnswerService;
import com.linguaclassica.service.UserService;


/**
 * The page to which users are directed after successfully logging in.
 * From this page, users may proceed direct to the Parser or to the
 * root log-in page
 * @author Eugene Price
 * @Copyright("2012 - 2017 Lingua Classica")
 */
public class DetailedResultsPanel extends Panel {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private UserService userService;

	@SpringBean
	private ExerciseService exerciseService;
	@SpringBean
	private AssignmentService assignmentService;
	@SpringBean
	private AssignmentFactService assignmentfactService;
	@SpringBean
	private QuestionService questionService;
	@SpringBean
	private ResultsService resultsService;	
	@SpringBean
	private StudentAnswerService studentanswerService;
	
	@SpringBean
	private ModelFactory modelFactory;	
	
	// Active user
	private final UserModel userModel = AlphPlusSession.get().getUser(getRequest());	

	// Backing model storage
	User user;
	
	java.sql.Date expires;
	
	FeedbackPanel feedback;
	
	int listlen = 0;
	
	int correctcount = 0; 
	
	int assignfactid = 0;

	public DetailedResultsPanel(final String idIn, String category, PageParameters params) {
		super(idIn);
		
		System.out.println("DetailedResultsPage(..)");
		System.out.println("user[" + userModel.getId() + "] " + userModel.getEmailAddress());
		StringValue assignfactidStr = params.get("assignfactid");
		System.out.println("DetailedResultsPanel, line 91:  assignfactidStr = " + assignfactidStr);
		assignfactid = Integer.parseInt(assignfactidStr.toString());
		
		StudentAssignmentResultsModel resultsModel = 
				resultsService.getStudentAssignmentResultsByAssignFactId(assignfactid);
		//listlen = resultsModel.getTotal();
		correctcount = resultsModel.getCorrectNumber();
		
		// The feedback panel will display errors
		//feedback = new FeedbackPanel("feedbackmessages");
		//add(feedback);
		
		// Build the form
		buildForm();
	}


	class LandingForm extends Form<Object> {
		private static final long serialVersionUID = 1L;

		public LandingForm(String id) {
			super(id);
		}
	}

	private void buildForm()
	{
		Form<?> form = new LandingForm("LandingForm");

		// Get the student's data
		AssignmentFactModel assignfact = assignmentfactService.findAssignmentFactById(assignfactid);
		AssignmentModel assignEntity = assignmentService.findAssignmentById(assignfact.getAssignmentId());
		Integer studentid = assignfact.getStudentId();
		UserModel studentUser = userService.findUserById(studentid);
		String frstname = studentUser.getFirstName();
		String lstname = studentUser.getLastName();
		Integer exerid = assignEntity.getExerciseId();
		ExerciseModel exerciseModel = exerciseService.findExerciseById(exerid);
		
		int answerlistlen = questionService.getListOfAnswersByExerciseId(exerid).size();

		ArrayList<QuestionInst> resultsList = new ArrayList<QuestionInst>();
		
		resultsList = GetResultsList(assignfactid, exerid);
		
		form.add(new ListView<QuestionInst>("questionlist", resultsList) {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<QuestionInst> item)
			{
				final QuestionInst questInst = item.getModelObject();
				item.add(new Label("qnumber",questInst.getQuestionnumber()));
				item.add(new Label("questiontype",questInst.getQuestiontype()));
				item.add(new Label("question",questInst.getQuestion()));
				item.add(new Label("correctanswer",questInst.getCorrectAnswer()));
				item.add(new Label("studentanswer",questInst.getStudentAnswer()));
				item.add(new Label("proficiency",questInst.getProficiency())); // add proficiency
			}
		}); 
		
		// get the percentage correct
		float percent = (float)(correctcount * 100) / answerlistlen;

		DecimalFormat df = new DecimalFormat("##.##");
		String percentstr = df.format(percent);
		
		String studentcodestr = "No student code";
		if(studentUser.getStudentnumber() != null){
			studentcodestr = studentUser.getStudentnumber().toString();
		}
		
		form.add(new Label("namelab", frstname));
		form.add(new Label("lstnamelab", lstname));
		form.add(new Label("studidlab", studentcodestr));
		
		ExerCategoryModel exerCategoryModel = exerciseService.findExerCategoryById(exerciseModel.getExercisecategory());  //.getId());
		String exercisename = exerCategoryModel.getExerciseName();
		
		String exeridstr = exercisename ;
		if(!exerciseModel.getVar1string().equals("None")){
			exeridstr = exeridstr + " " + exerciseModel.getVar1string();
		}
		if(!exerciseModel.getVar2string().equals("None")){
			exeridstr = exeridstr + " " + exerciseModel.getVar2string();
		}
		
		form.add(new Label("exeridlab", exeridstr));
		
		form.add(new Label("correctlab", Integer.toString((correctcount))));
		
		form.add(new Label("listlenlab", Integer.toString(answerlistlen)));
		
		form.add(new Label("percentlab", percentstr)); 	

		add(form);
	} 
	
	public ArrayList<QuestionInst> GetResultsList(Integer assignfactid, Integer exerid){
		List<QuestionModel> templatelist = questionService.getListOfQuestionByExerID(exerid);
		
		ArrayList<QuestionInst> outerlist = new ArrayList<QuestionInst>();
		listlen = templatelist.size();
		int qnumber = 0;
		for (int i = 0; i < listlen; i++) {
			Integer questionid = templatelist.get(i).getId();
			int currentqnumber = templatelist.get(i).getQnumber();
			List<AnswerModel> answerList = questionService.getListOfAnswersByQuestionId(questionid);
			List<AnswerModel> correctAnswerList = new ArrayList<AnswerModel>();
			int anslen = answerList.size();
			for(int k = 0; k < anslen; k++){
				if(answerList.get(k).getCorrect() == true){
					correctAnswerList.add(answerList.get(k));
				}
			}
			List<StudentAnswerModel> studentAnswerList = 
					studentanswerService.getListOfStudentAnswersByQuestionId(questionid, assignfactid);
			int correctlen = correctAnswerList.size();
			int studentlen = studentAnswerList.size();
			int index = correctlen;
			List<String> correctAnswerStrList = new ArrayList<String>();
			for(int q = 0; q < correctlen; q++) {
				correctAnswerStrList.add(correctAnswerList.get(q).getAnswer());
			}
			
			if(studentlen > correctlen) {
				index = studentlen;
			}
			System.out.println("questionid, correctlen, studentlen, index = " + questionid + ", " 
					+ correctlen + ", " + studentlen + ", " + index);
			for(int j = 0; j < index; j++){
				QuestionInst questionInst = new QuestionInst();
				if(templatelist.get(i).getQuestiontype().equals("Multiple select")){
					//if(studentlen >= correctlen){
						questionInst.setQuestionnumber(templatelist.get(i).getQnumber().toString());
						questionInst.setQuestiontype(templatelist.get(i).getQuestiontype());
						questionInst.setQuestion(templatelist.get(i).getQuestion());
						if(studentlen > 0){
							if(correctAnswerStrList.contains(studentAnswerList.get(j).getStudentAnswer())){
								questionInst.setCorrectAnswer(studentAnswerList.get(j).getStudentAnswer());
								questionInst.setStudentAnswer("ok");
							}
							else{
								questionInst.setCorrectAnswer(correctAnswerStrList.toString());
								questionInst.setStudentAnswer("X " + studentAnswerList.get(j).getStudentAnswer());
							}
						}
						else {
							questionInst.setCorrectAnswer(correctAnswerStrList.toString());
							questionInst.setStudentAnswer("X No answer");							
						}
				}
				else { //question types other than multiple select	
				
				
					if(j == 0){ 
						questionInst.setQuestionnumber(templatelist.get(i).getQnumber().toString());
						questionInst.setQuestiontype(templatelist.get(i).getQuestiontype());
						questionInst.setQuestion(templatelist.get(i).getQuestion());
					}
					else { 
						questionInst.setQuestionnumber(" ");
						questionInst.setQuestiontype(" ");
						questionInst.setQuestion(" ");
					}
					if(j < correctlen && correctAnswerList.get(j).getAnswer() != null){ 
						questionInst.setCorrectAnswer(correctAnswerList.get(j).getAnswer());
					}
					
					//if(j < studentlen && studentAnswerList.get(j).getStudentAnswer() != null){
					if(j < correctlen && j < studentlen && studentAnswerList.get(j).getStudentAnswer() != null){
						//the following necessary to catch alternative answers
						String[] correctansArr = correctAnswerList.get(j).getAnswer().split(",");
						if(correctansArr.length > 1){
							System.out.println("correctansArr[0], correctansArr[1] = " + correctansArr[0] +
								", " + correctansArr[1]);
							if(correctAnswerList.get(j).getAnswer().contains(studentAnswerList.
									get(j).getStudentAnswer())){
								System.out.println("correctAnswerList.get(j).getAnwswer(0) "
										+ "contains studentanswer");
								questionInst.setStudentAnswer("ok");																
							}						
							else if(!studentAnswerList.get(j).getStudentAnswer().equals("")){
								questionInst.setCorrectAnswer(correctAnswerList.get(j).getAnswer());
								questionInst.setStudentAnswer("X " + studentAnswerList.get(j).getStudentAnswer());
							}
						}
						else {					
							if(j < correctlen && studentAnswerList.get(j).getStudentAnswer().equals(correctAnswerList.get(j).getAnswer())){
								questionInst.setStudentAnswer("ok");
							}
							else if (!studentAnswerList.get(j).getStudentAnswer().equals("")){
								questionInst.setCorrectAnswer(correctAnswerList.get(j).getAnswer());
								questionInst.setStudentAnswer("X " + studentAnswerList.get(j).getStudentAnswer());
							}
						}
					}
					//else if(j < studentlen) {
					else if(j < correctlen && j < studentlen) {
						System.out.println("DetailedResults, line 348:  studentAnswerList.get(j).getStudentAnswer() = " + 
								studentAnswerList.get(j).getStudentAnswer());
						questionInst.setCorrectAnswer(correctAnswerList.get(j).getAnswer());
						questionInst.setStudentAnswer("X No answer");						
					}
				
				} //end of else (not multiple select
				if(currentqnumber > 0 && currentqnumber == qnumber) {
					questionInst.setQuestionnumber(" ");
					questionInst.setQuestiontype(" ");
					if(templatelist.get(i).getQuestiontype().equals("Multiple select")){
						questionInst.setQuestion(" ");					
					}
				}
				else {
					qnumber = currentqnumber;
				}
		
				//get the proficiency string for the question
				//default is 4 - comprehension
				int proficiencyindex = 0;
				int proficiencyid = 4;
				//proficiency for parsing questions is 3 (syntax) unless teacher has selected another
				if(templatelist.get(i).getQuestiontype().equals("Latin Parsing")  || 
						templatelist.get(i).getQuestiontype().equals("Greek Parsing")){
					proficiencyindex = 2;
					proficiencyid = 3;
				}
				else if(templatelist.get(i).getProficiencyindex() != null){
					proficiencyindex =  templatelist.get(i).getProficiencyindex();
				}
				System.out.println("qnumbe, proficiencyindex = " + templatelist.get(i).getQnumber() + 
						", " + proficiencyindex);
				if(proficiencyindex == 0){
					proficiencyid=1;
				}
				else if(proficiencyindex == 1){
					proficiencyid=2;
				}
				else if(proficiencyindex == 2){
					proficiencyid=3;
				}
				else if(proficiencyindex == 3){
					proficiencyid=4;
				}
				String proficiency = 
						questionService.getProficiencyString(proficiencyid);
				if(questionInst.getCorrectAnswer() != null && !questionInst.getCorrectAnswer().equals("")){
					questionInst.setProficiency(proficiency);
				}
				
				outerlist.add(questionInst);
			}  // end of for j
		}  // end of for i
		return outerlist;
	}// end of getResultsList
	
	public class QuestionInst implements Serializable {
		private static final long serialVersionUID = 1L;
		
		String questionnumber;
		String questiontype;
		String question;
		String correctanswer;
		String studentanswer;
		String result;
		String proficiency; 
		
		public QuestionInst() {
			
		}
		
		public QuestionInst(
				String questionnumber,
				String questiontype, 
				String question, 
				String correctanswer, 
				String studentanswer, 
				String result, 
				String proficiency) {
			
			this.questionnumber = questionnumber;
			this.question = question;
			this.questiontype = questiontype;
			this.correctanswer = correctanswer;
			this.studentanswer = studentanswer;
			this.result = result;
			this.proficiency = proficiency; 
		}

		public String getQuestionnumber() {
			return questionnumber;
		}
		
		public void setQuestionnumber(String questionnumber) {
			this.questionnumber = questionnumber;
		}
		
		public String getQuestiontype() {
			return questiontype;
		}
		
		public void setQuestiontype(String questiontype) {
			this.questiontype = questiontype;
		}

		public String getQuestion() {
			return question;
		}
		
		public void setQuestion(String question) {
			this.question = question;
		}

		public String getCorrectAnswer() {
			return correctanswer;
		}
		
		public void setCorrectAnswer(String correctanswer) {
			this.correctanswer = correctanswer;
		}

		public String getStudentAnswer() {
			return studentanswer;
		}
		
		public void setStudentAnswer(String studentanswer) {
			this.studentanswer = studentanswer;
		}
		public String getResult() {
			return result;
		}
		
		public void setResult(String result) {
			this.result = result;
		}		
		public String getProficiency() { 
			return proficiency;
		}

		public void setProficiency(String proficiency) { 
			this.proficiency = proficiency;
		}
	}
}

