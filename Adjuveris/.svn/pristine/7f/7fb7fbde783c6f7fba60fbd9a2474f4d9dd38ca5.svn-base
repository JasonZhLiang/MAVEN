package com.linguaclassica.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.linguaclassica.entity.EntityAnswerModel;
import com.linguaclassica.entity.EntityAssignmentFactModel;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.entity.EntityStudentAssignmentResultsModel;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.AssignmentFactModel;
import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.model.StudentAnswerModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentFactService;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.QuestionService;
import com.linguaclassica.service.ResultsService;
import com.linguaclassica.service.StudentAnswerService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;

/**
 * Servlet implementation class TeacherGeneratedExerciseServlet
 */
@WebServlet("/TeacherGeneratedExerciseServlet")
public class TeacherGeneratedExerciseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Model Factory to use in the servlet. Configured in init() method as we need to use
	// the Spring ApplicationContext manually as this servlet is not part of Spring MVC
	@SpringBean
	private ModelFactory modelFactory;	
	
	// Adding support to handle users
	@SpringBean
	private UserService userService;	
	
	// Adding support to handle exercises
	@SpringBean
	private ExerciseService exerciseService;	
	
	// Adding support to handle users
	@SpringBean
	private QuestionService questionService;	
	
	// Adding support to handle users
	@SpringBean
	private ResultsService resultsService;		
	
	// Adding support to handle assignments
	@SpringBean
	private AssignmentService assignmentService;		
	
	// Adding support to handle assignments
	@SpringBean
	private AssignmentFactService assignmentFactService;		
	
	// Adding support to handle student answers
	@SpringBean
	private StudentAnswerService studentAnswerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherGeneratedExerciseServlet(String exerciseid) { 
        super();
    }
    
    public TeacherGeneratedExerciseServlet() {
        super();
    }
    
    /**
     * Override the default servlet init method to initialise the servlet with dependencies
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
    	super.init();
    	
    	ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	modelFactory = (ModelFactory) context.getBean("modelFactory");
    	
    	if (modelFactory == null) {
    		throw new ServletException("Unable to locate modelFactory bean");
    	}
    	
    	userService = (UserService) context.getBean("userService");
    	
    	if (userService == null) {
    		throw new ServletException("Unable to locate userService bean");
    	}
    	
    	exerciseService = (ExerciseService) context.getBean("exerciseService");
    	
    	if (exerciseService == null) {
    		throw new ServletException("Unable to locate exerciseService bean");
    	}
    	
    	questionService = (QuestionService) context.getBean("questionService");
    	
    	if (questionService == null) {
    		throw new ServletException("Unable to locate questionService bean");
    	}
    	
    	assignmentService = (AssignmentService) context.getBean("assignmentService");
    	
    	if (assignmentService == null) {
    		throw new ServletException("Unable to locate assignmentService bean");
    	}
    	
    	assignmentFactService = (AssignmentFactService) context.getBean("assignmentFactService");
    	
    	if (assignmentFactService == null) {
    		throw new ServletException("Unable to locate assignmentFactService bean");
    	}
    	
    	studentAnswerService = (StudentAnswerService) context.getBean("studentAnswerService");
    	
    	if (studentAnswerService == null) {
    		throw new ServletException("Unable to locate studentAnswerService bean");
    	}
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String actionTarget = "JsonServlet";
        
        String assignmentIdstr = request.getParameter("assignmentid");
        int assignmentid = Integer.parseInt(assignmentIdstr);
        
        System.out.println("TeacherGeneratedServlet:  line 126:  assignmentid = " + assignmentid);
        AssignmentModel assignmentModel = null;       
        String assignmentStatus = "";
        if(assignmentid == 0) {
        	assignmentStatus = "PRIVATE";
        	actionTarget = "ShowAlertServlet";
        }
        else{
	        assignmentModel = assignmentService.findAssignmentById(assignmentid);
	        assignmentStatus = assignmentModel.getStatus();
        }
        System.out.println("TeacherGeneratedServlet, line 128:  assignmentStatus = " + 
        		assignmentStatus);
        
		String exerciseidstr = request.getParameter("exerciseid");	
		Integer exerciseid = Integer.parseInt(exerciseidstr);
		
		HttpSession session = request.getSession(true);
		UserModel userModel = (UserModel) session.getAttribute(UserModel.LCUSER);
		Integer userId;
		
		//line below is null after a timeout
		if(userModel == null)
		{			
			userId = 0;			
		}
		else {		
			userId = userModel.getId();
		} 
		
		/////\\//generate a list of student answers as string arrays with questionid
		HashMap<Integer, String> hmap = new HashMap<Integer, String>();
		
		int answerlistlen = 0;
		String questionidStr = "";
		
		if(assignmentFactService.getCountByUserAndAssignmentId(userId, assignmentid) > 0){ 
			AssignmentFactModel assignmentFactModel = 
					assignmentFactService.findAssignmentFactByAssignID(userId, assignmentid);
			
			List<Integer> questionIdList = questionService.getListOfQuestionIdsByExerID(exerciseid);
			
			//generate a comma-separated list of questionids to be passed to javascript by hidden field
			questionidStr = questionIdList.get(0).toString();
			for(int i = 1; i < questionIdList.size(); i++){
					questionidStr = questionidStr + "," + questionIdList.get(i).toString();
			}
			
			if(assignmentFactModel != null) {
				List<StudentAnswerModel> studentAnswerList = 
						studentAnswerService.getListOfStudentAnswerByAssignFactID(assignmentFactModel.getId());
				int listlen = studentAnswerList.size();
				
				for(int i = 0; i < listlen; i++){
					int questionid = studentAnswerList.get(i).getQuestionid();
					String answerStr = generateAnswerString(studentAnswerList, questionid);
					if(answerStr != null){ 
						hmap.put(questionid, answerStr);
					}
					else {
						hmap.put(questionid, "");
					}
				}
				answerlistlen = questionService.getListOfAnswersByExerciseId(exerciseid).size();
			}
		}
		//////\\
		
        //System.out.println("ExerciseProofOfConceptServlet line 126:  exerciseid,userId = " + exerciseid + ", " + userId); 
        String printString = "";
        PrintWriter out = response.getWriter();
        String docType ="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n"; 
        
		int questiongroupId = 0; 
		int passageIndex = 0;
        
		if (exerciseid != null) {
			List<QuestionModel> questionList = questionService.getListOfQuestionByExerID(exerciseid);
			int listlen = questionList.size();
			System.out.println("ExerciseProofOfConceptServlet, line 130: listlen = " + listlen);
			
			printString =   
            "<HTML>\n" +
            "<HEAD><TITLE>AlpheiosPlus Exercise</TITLE>" +
            "<script type='text/javascript' src='jQuery.js'></script>" +
            "<script type='text/javascript' src='jquery.grkchaineddropdown.js'></script>" +
            "<script type='text/javascript' src='jquery.latchaineddropdown.js'></script>" +
            "<script type='text/javascript' src='jquery.prompt.js'></script>" +
            "<script type='text/javascript' src='jquery-impromptu.js'></script>" +
            "<script type='text/javascript' src='jquery-ui.js'></script>" +
            "<script type='text/javascript' src='jquery.form.js'></script>" +
            "<script type='text/javascript' src='ProcessQuestions.js'></script>" +
            "<script type='text/javascript' src='LatinParsing.js'></script>" +
            "<script type='text/javascript' src='GreekParsing.js'></script>" +
            "<script type='text/javascript' src='passages2.js'></script>" + 
            "<script type='text/javascript' src='validateExercise.js'></script>" +              
            "<link rel='stylesheet' type='text/css' href='style.css' />" +
            "</HEAD>\n" +
			"<body>" +
			"<div id='wrapper'>" +
			"<div id='page'>" +
            "<h2>Teacher Generated Exercise Servlet</h2>" +
			"<p>This page is intended to demonstrate that we can generate an html page that shows " +
            "questions in various formats for an list of questions of variable length.  That is, " +
			"it will generate 5 questions if the question list has five questions in it and 17 questions " +
            "if the list has 17 question in it, some in true-false format and others in multiple choice " +
			"format.  </p>" +
            "<form  name='checkform' method='post' action='"+actionTarget+"'>"; 
			
			for (int i = 0; i < listlen; i++) {
				QuestionModel questionModel = questionList.get(i);
				String question = questionModel.getQuestion();
				String questiontype = questionModel.getQuestiontype();
				//System.out.println("ProofOfConceptServlet, line 194: passageIndex = " + passageIndex);
				
				//System.out.println("ProofOfConceptServlet, line 196:  question, id = " + question);				
				
				//create 'end of passage questions' message
				/*
				System.out.println("passageIndex, questionModel.getPassageid() = " + passageIndex + 
						", " + questionModel.getPassageid());
				*/
				if (questionModel.getPassageid() !=null && passageIndex != 0 && questionModel.getPassageid() != passageIndex) {
					String addString = "<div>End of questions referring to text passage above.</div>";
					printString = printString + addString;
					passageIndex = 0;
				}
				
				//create 'begin passage questions' message
				if (questionModel.getPassageid() != null && questionModel.getPassageid() != passageIndex) {
							String addString = 
								"<div class='passagediv' id='passage"+questionModel.getId()+"'>" +
								"<h3>The following questions refer to the text passage below.</h3>" +
								"<textarea class='maintxt' id='passage"+questionModel.getId()+" rows='13' cols='80' readonly>" +
								questionService.findPassageById(questionModel.getPassageid()).getTextcontent() +
								"</textarea>" +
								"</div>";
							passageIndex = questionModel.getPassageid();
							printString = printString + addString;
				}
				
				if (questiontype.equals("True-false")) {
					/*String ans = "";
					if(hmap.get(questionModel.getId()) != null){
					//	ans = hmap.get(questionModel.getId());
					}*/
					//System.out.println("Got here");
					String addString = 
				            "<div class='questiondiv' id='truefalse--"+questionModel.getId()+"'><br/>" +
							"<h4>" + questionModel.getQnumber() + 
							".  True/false question:</h4>" +
							"Question: " + question + ":  " +
							"<span id='truefalse'>" +
										"true  <input name='answer-" + questionModel.getId() + "' type='radio' value='true'/>  " +
										"false  <input name='answer-" + questionModel.getId() + "' type='radio' value='false'/><br/> " +
										//"<input type='text' class='hidden' id='qid-"+questionModel.getQnumber()+"' " +
										//"value='"+questionModel.getId()+"'/>" +
										"<input type='text' class='hidden' id='prev-"+questionModel.getId()+"' " +
										"value='"+hmap.get(questionModel.getId())+"'/>" +
										"<input type='text' class='hidden' id='qtype-"+
										questionModel.getId()+"' value='truefalse'/>" +
								"</span>" +
							"</div><br/>";
					printString = printString + addString;
				}
				else if (questiontype.equals("Multiple choice")) {
					List<EntityAnswerModel> answerModelList = new ArrayList<EntityAnswerModel>();
					int questionId = questionList.get(i).getId();
					answerModelList = questionService.findAnswersByQuestionId(questionId);
					List<String> answerList = new ArrayList<String>();
					int anslistlen = answerModelList.size();
					System.out.println("anslistlen = " + anslistlen);
					for (int j = 0; j < anslistlen; j++) {
						String answeritem = answerModelList.get(j).getAnswer();
						answerList.add(answeritem);
					}
					String addString = 
				            "<div class='multiquestiondiv' id='multichoice--"+questionModel.getId()+"' ><br/>" +
							"<h4>" + questionModel.getQnumber() + 
							". Multiple choice question (only one answer is correct):</h4>" +
							"Question: " + question + "  <br/>";
					
					String answerString = "";
					String addanswer = "";
					
					for (int k = 0; k < anslistlen; k++) {
						addanswer = answerList.get(k) +
						"<input type='radio' class='checkboxes' name='answer-"+ 
								questionModel.getId() + "' value='"+answerList.get(k)+"'/>" +
						"<input type='text' class='hidden' id='prev-"+questionModel.getId()+"' " +
						"value='"+hmap.get(questionModel.getId())+"'/>" +
						"<input type='text' class='hidden' id='qtype-"+
									questionModel.getId()+"' name='questiontype' value='multichoice'/><br/>";
						answerString = answerString + addanswer;
					}
					addString = addString + answerString;
					addString = addString + "</div><br/>";
					printString = printString + addString;
					
				}
				else if (questiontype.equals("Multiple select")) {
					List<EntityAnswerModel> answerModelList = new ArrayList<EntityAnswerModel>();
					int questionId = questionList.get(i).getId();
					answerModelList = questionService.findAnswersByQuestionId(questionId);
					List<String> answerList = new ArrayList<String>();
					int anslistlen = answerModelList.size();
					System.out.println("anslistlen = " + anslistlen);
					for (int j = 0; j < anslistlen; j++) {
						String answeritem = answerModelList.get(j).getAnswer();
						answerList.add(answeritem);
					}
					String addString = 
				            "<div class='multiquestiondiv' id='multiselect--"+questionModel.getId()+"' ><br/>" +
		            		"<h4>" + questionModel.getQnumber() + 
							".  Multiple select question (check all answers that are correct:</h4>" +
							"Question: " + question + "  <br/>";
					
					String answerString = "";
					String addanswer = "";
					
					for (int k = 0; k < anslistlen; k++) {
								addanswer = answerList.get(k) +
								"<input type='checkbox' class='checkboxes' "
								+ "name='answer-"+questionModel.getId()+k+"' "
										+ "value='"+answerList.get(k)+"'/><br/>";
								answerString = answerString + addanswer;
								System.out.println("answer-"+questionModel.getId()+k);
					}
					String hiddenitems = 
							//"<input type='text' class='hidden' id='qid-"+questionModel.getQnumber()+"' " +
						//"value='"+questionModel.getId()+"'/>" +
						"<input type='text' class='hidden' id='prev-"+questionModel.getId()+"' " +
						"value='"+hmap.get(questionModel.getId())+"'/>" +
						"<input type='text' class='hidden' id='choices-"+questionModel.getId()+"' " +
						"value='"+anslistlen+"'/>" +
						"<input type='text' class='hidden' id='qtype-"+
									questionModel.getId()+"' name='questiontype' value='multiselect'/>";
					addString = addString + answerString + hiddenitems;
					addString = addString + "</div><br/>";
					printString = printString + addString;
					
				}

				else if (questiontype.equals("Fill in the blank")) {
					List<EntityAnswerModel> answerModelList = new ArrayList<EntityAnswerModel>();
					int questionId = questionList.get(i).getId();
					answerModelList = questionService.findAnswersByQuestionId(questionId);
					List<String> answerList = new ArrayList<String>();
					int anslistlen = answerModelList.size();
					System.out.println("anslistlen = " + anslistlen);
					for (int j = 0; j < anslistlen; j++) {
						String answeritem = answerModelList.get(j).getAnswer();
						answerList.add(answeritem);
					}
					String addString = 
				            "<div class='questiondiv' id='fillblank--"+questionModel.getId()+"' ><br/>" +
		            		"<h4>" + questionModel.getQnumber() + 
							".  Fill in the blank question:</h4>" +
							"Question: " + question + "  <br/>";
					
					addString = addString + "Your answer:  <input type='text' name='answer-"+
							questionModel.getId()+"' size='80'/><br/> " +
		            		//"<input type='text' class='hidden' id='qid-"+questionModel.getQnumber()+"' " +
							//"value='"+questionModel.getId()+"'/>" +
							"<input type='text' class='hidden' id='prev-"+questionModel.getId()+"' " +
							"value='"+hmap.get(questionModel.getId())+"'/>" +
							"<input type='text' class='hidden' id='qtype-"+
										questionModel.getId()+"' name='questiontype' value='fillblank'/>";
					addString = addString + "</div><br/>";					
					printString = printString + addString;					
				}

				else if (questiontype.equals("List matching")) {
					if (questionModel.getQuestionGroupid() != questiongroupId) {
					questiongroupId = questionModel.getQuestionGroupid();
					
					questiongroupId = questionModel.getQuestionGroupid();
					int qgId = questionModel.getQuestionGroupid();
					List<QuestionModel> listquestionList = questionService.getListOfQuestionsByQuestionGroupId(qgId);
					List<AnswerModel> answerList = questionService.getListOfAnswersByQuestionGroupId(qgId);
					Collections.shuffle(answerList);
					int lmlistlen = listquestionList.size();
					//int questnumber = questionModel.getQnumber();
					System.out.println("TGES, line 452:  lmlistlen = " + lmlistlen);
					
					List<String> studentAnswerList = new ArrayList<String>();
					for(int h = 0; h < lmlistlen; h++){
						System.out.println("TGES, line 463:  hmap.get(listquestionList.get(h).getId() = " +
								hmap.get(listquestionList.get(h).getId()));
						studentAnswerList.add(hmap.get(listquestionList.get(h).getId()));
					}
					String addString =  "<div class='questiondiv' id='listmatch--"+questionModel.getId()+"' ><br/>" +
		            		"<h4>" + questionModel.getQnumber() + 
							".  List-matching question.  Enter in the text box in the left-hand " +
		            		"column the number of the matching word in the right-hand column:</h4> <br/>";
		            		//"<input type='text' class='hidden' name='questiontype' value='listmatch'/><br/>";


					int prevansint = 0;
					HashMap<Integer, String> hmapans = new HashMap<Integer, String>();
					for(int m = 0; m < lmlistlen; m++) {
						String studentanswer = "";
						String answer = "";
						if(studentAnswerList.get(m) != null){
							studentanswer = studentAnswerList.get(m);
						}
						else {
							hmapans.put(m, "");
						}
						for(int n = 0; n < lmlistlen; n++){	
							if(studentanswer.equals(answerList.get(n).getAnswer())){
								answer = answerList.get(n).getAnswer();
								int ans = n + 1;
								String ansstr = String.valueOf(ans);
								System.out.println("ansstr = " + ansstr);
								hmapans.put(m,(ansstr));
							}							
						}
							System.out.println("TGES, line 484:  m, answer, studentanswer,hmapans.get(m) = " +
									m + ", " + answer + ", " + studentanswer + ", " +
									hmapans.get(m));
					}
					
					for (int k = 0; k < lmlistlen; k++) {
						int questionid = listquestionList.get(k).getId();
						
						addString = addString + 
								listquestionList.get(k).getQuestion() + "<input type='text' class='listcheck'" +
								"size='2' name='answer-" +questionid+"' autocomplete='off' "
										+ "value='"+hmapans.get(k)+"'/>"
										+ "&nbsp;&nbsp;&nbsp;<span class='lmrightcolumn'>" +
								(k+1) + ".  <input type='text' class-'hidden'  value='"+ 
								answerList.get(k).getAnswer() +"' name='answer-" +
								questionid+"-x'/>" +
								"<input type='text' class='hidden' id='qtype-"+questionid+
								"' name='qtype-"+listquestionList.get(k).getId()+"' value='listmatch'/>" +
								"<input type='text' class='hidden' id='prev-"+questionid+"' " +
								"value='"+prevansint+"'/>" +
								"<input type='text' class='hidden' id='listlen-"+questionid+"' " +
								"value='"+lmlistlen+"'/>" +
								"</span><br/>";								
					}
						
					addString = addString + "</div><br/>";
					printString = printString + addString;
					}
				}

				else if (questiontype.equals("Latin Parsing")) {
					List<EntityAnswerModel> answerModelList = new ArrayList<EntityAnswerModel>();
					int questionId = questionList.get(i).getId();
					answerModelList = questionService.findAnswersByQuestionId(questionId);
					List<String> answerList = new ArrayList<String>();
					int anslistlen = answerModelList.size();
					System.out.println("anslistlen = " + anslistlen);
					for (int j = 0; j < anslistlen; j++) {
						String answeritem = answerModelList.get(j).getAnswer();
						answerList.add(answeritem);
					}
					String addString = 
				            "<div class='questiondiv' id='latinparsing--"+questionModel.getId()+"' ><br/>" +
							"<h4>" + questionModel.getQnumber() + 
							".  Latin parsing question:</h4>" +
							//"<input type='text' class='hidden' name='questiontype' value='latinparsing'/>" +
							//"<input type='text' class='hidden' name='questnum' value='"+questionModel.getId()+"'/>" +
							"Word: " + question + ", line number:  " + 
								questionModel.getLinenumber() + " word number: " +
								questionModel.getWordnumber() + "<br/>" +
							"<fieldset id='posStuff'>" +
							"Part of Speech: <select id='latpos"+questionModel.getId()+
								"' class='latpos' data-alt='latpos"+questionModel.getId()+
								"' name='latpos"+questionModel.getId()+
								"' onChange='chainSel("+questionModel.getId()+")'>" +
							"<option value='1' selected>Part of Speech</option>" +
							"<option value='Noun'>Noun</option>" +
							"<option value='Pronoun'>Pronoun</option>" +
							"<option value='Adjective'>Adjective</option>" +
							"<option value='Verb'>Verb</option>" +
							"<option value='Infinitive'>Infinitive</option>" +
							"<option value='Participle/gerund'>Participle/gerund</option>" +
							"<option value='Adverb'>Adverb</option>" +
							"<option value='Preposition'>Preposition</option>" +
							"<option value='Conjunction'>Conjunction</option>" +
							"<option value='Interjection'>Interjection</option>" +
							"</select>" +
							"<select id='latvar1"+questionModel.getId()+"' data-alt='latvar1"+
								questionModel.getId()+"' name='latvar1"+questionModel.getId()+"'>" +
							"<option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>" +
							"</select>" +
							"<select id='latvar2"+questionModel.getId()+"' data-alt='latvar2"+
								questionModel.getId()+"' name='latvar2"+questionModel.getId()+"'>" +
							"<option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>" +
							"</select>" +
							"<select id='latvar3"+questionModel.getId()+"' data-alt='latvar3"+
								questionModel.getId()+"' name='latvar3"+questionModel.getId()+"'>" +
							"<option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>" +
							"</select>" + 
							"<select id='latvar4"+questionModel.getId()+"' data-alt='latvar4"+
								questionModel.getId()+"' name='latvar4"+questionModel.getId()+"'>" +
							"<option>--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>" +
							"</select>" +
							"<input type='text' id='latvar5"+questionModel.getId()+
								"' data-alt='latvar5"+questionModel.getId()+"' name='latvar5"+
								questionModel.getId()+"' size='35'>" +
							"</fieldset>";
					String hiddenitems = 
							//"<input type='text' class='hidden' id='qid-"+questionModel.getQnumber()+"' " +
							//"value='"+questionModel.getId()+"'/>" +
							"<input type='text' class='hidden' id='prev-"+questionModel.getId()+"' " +
							"value='"+hmap.get(questionModel.getId())+"'/>" +
							//"<input type='text' class='hidden' id='choices-"+questionModel.getQnumber()+"' " +
							//"value='"+anslistlen+"'/>" +
							"<input type='text' class='hidden' id='qtype-"+
										questionModel.getId()+"' name='questiontype' value='latinParsing'/>";
				addString = addString + hiddenitems;
					addString = addString + "</div><br/>";					
					printString = printString + addString;	
				}


				else if (questiontype.equals("Greek Parsing")) {
					List<EntityAnswerModel> answerModelList = new ArrayList<EntityAnswerModel>();
					int questionId = questionList.get(i).getId();
					answerModelList = questionService.findAnswersByQuestionId(questionId);
					List<String> answerList = new ArrayList<String>();
					int anslistlen = answerModelList.size();
					System.out.println("anslistlen = " + anslistlen);
					for (int j = 0; j < anslistlen; j++) {
						String answeritem = answerModelList.get(j).getAnswer();
						answerList.add(answeritem);
					}
					String addString = 
			            "<div class='questiondiv' id='greekparsing--"+questionModel.getId()+"' ><br/>" +
						"<h4>" + questionModel.getQnumber() + 
						".  Greek parsing question:</h4>" +
						"<input type='text' class='hidden' name='questiontype' value='greekparsing'/>" +
						"<input type='text' class='hidden' name='questiontype' value='latinparsing'/>" +
						"<input type='text' class='hidden' name='questnum' value='"+questionModel.getId()+"'/>" +
						"Word: " + question + ", line number:  " + 
							questionModel.getLinenumber() + " word number: " +
							questionModel.getWordnumber() + "<br/>" +
						"<fieldset id='grkposStuff'>" +
						"Part of Speech: <select id='grkpos"+questionModel.getId()+
						"' class='grkpos' data-alt='grkpos"+questionModel.getId()+
						"' name='grkpos"+questionModel.getId()+
						"' onChange='grkChainSel("+questionModel.getId()+")'>" +
						"<option value='1' selected>Part of Speech</option>" +
						"<option value='Noun'>Noun</option>" +
						"<option value='Pronoun'>Pronoun</option>" +
						"<option value='Adjective'>Adjective</option>" +
						"<option value='Article'>Article</option>" +
						"<option value='Verb'>Verb</option>" +
						"<option value='Infinitive'>Infinitive</option>" +
						"<option value='Participle'>Participle</option>" +
						"<option value='Verbal Adjective'>Verbal Adjective</option>" +
						"<option value='Adverb'>Adverb</option>" +
						"<option value='Preposition'>Preposition</option>" +
						"<option value='Conjunction'>Conjunction</option>" +
						"<option value='Interjection'>Interjection</option>" +
						"<option value='Particle'>Particle</option>" +
						"</select>" +
						"<select id='grkvar1"+questionModel.getId()+"' data-alt='grkvar1"+
							questionModel.getId()+"' name='grkvar1"+questionModel.getId()+"'>" +
						"<option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>" +
						"</select>" +
						"<select id='grkvar2"+questionModel.getId()+"' data-alt='grkvar2"+
							questionModel.getId()+"' name='grkvar2"+questionModel.getId()+"'>" +
						"<option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>" +
						"</select>" +
						"<select id='grkvar3"+questionModel.getId()+"' data-alt='grkvar3"+
							questionModel.getId()+"' name='grkvar3"+questionModel.getId()+"'>" +
						"<option>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>" +
						"</select>" + 
						"<select id='grkvar4"+questionModel.getId()+"' data-alt='grkvar4"+
							questionModel.getId()+"' name='grkvar4"+questionModel.getId()+"'>" +
						"<option>--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>" +
						"</select>" +
						"<select id='grkvar5"+questionModel.getId()+"' data-alt='grkvar5"+
							questionModel.getId()+"' name='grkvar5"+questionModel.getId()+"'>" +
						"<option>--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>" +
						"</select>" +
						"<input type='text' id='grkvar6"+questionModel.getId()+"' data-alt='grkvar6"+
							questionModel.getId()+"' name='grkvar6"+questionModel.getId()+"' size='35'>" +
						"</fieldset>";
				String hiddenitems = 
						//"<input type='text' class='hidden' id='qid-"+questionModel.getQnumber()+"' " +
						//"value='"+questionModel.getId()+"'/>" +
						"<input type='text' class='hidden' id='prev-"+questionModel.getId()+"' " +
						"value='"+hmap.get(questionModel.getId())+"'/>" +
						"<input type='text' class='hidden' id='choices-"+questionModel.getId()+"' " +
						"value='"+anslistlen+"'/>" +
						"<input type='text' class='hidden' id='qtype-"+questionModel.getId()+
						"' value='greekParsing' />";
				addString = addString + hiddenitems;
					addString = addString + "</div><br/>";					
					printString = printString + addString;					
				}
			}
			  String classidsrt = request.getParameter("classid");	
			  if(classidsrt == null){
				  classidsrt = "no class assigned";// can be null for not a student - for display purposes
			  }
            
			printString = printString 
					+ "<br/><br/>"
					+ "<input type='submit' value='Submit answers' />"
					+ "<br/><br/>"
					+ "<input type='text' name='exerciseid' value='" + exerciseidstr + "'/>"
					+ "<input type='text' name='do_this' value='checkAns'/>"
					+ "<input type='text' name='assignmentstatus' value='"+assignmentStatus+"'/>"
					+ "<input type='text' name='assignmentid' value='"+assignmentid+"'/>"
					+ "<input type='text' name='classid' value='"+ classidsrt +"'/>"
					+ "number of questions:<input type='text' id='numbq' name='numbq' size='2' "
					+ "value='"+answerlistlen+"'/>"         //listlen+"'/>"
					+ "<input type='text' id='questionidlist' value='"+questionidStr+"'/>"
					+ "</form></div></div></body></html>";
			}
			out.println(docType + printString); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Adds results of one student's assignment to student assignment results table
	 * and checks to ensure that the assignment is marked as 'COMPLETED'
	 * @param Integer assignFactId
	 * @return void
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void addStudentAssignmentResults(Integer assignFactId) 
			throws EntityAlreadyExistsException, ServiceException {
		System.out.println("ResultsService addStudentAssignmentResults");
		
		// throws an exception if the assignment is not marked as complete
		try{
			EntityAssignmentFactModel assignFactModel = assignmentFactService.findAssignmentFactById(assignFactId);
	
			EntityAssignmentModel assignModel = assignmentService.findAssignmentById(assignFactModel.getAssignmentId());
			if(!assignModel.getStatus().equals("COMPLETED") && !assignModel.getStatus().equals("PRACTICE")){
				throw new Exception();
			}
			System.out.println("ResultsService, line 115");
			
		}catch(Exception e){
			throw new ServiceException();
		}
	
		EntityStudentAssignmentResultsModel resultsModel = resultsService.calculateStudentAssignmentResults(assignFactId);
		System.out.println("ResultsService, line 121:  calculated resultsModel");
		if(resultsModel != null){
			try{
				//studentAssignmentResultsRepository.addStudentAssignmentResults(resultsModel);
				int assignmentFactId = resultsModel.getAssignmentFactId();
				resultsService.addStudentAssignmentResults(assignmentFactId);
			} catch (EntityExistsException e) {
				throw new EntityAlreadyExistsException(e);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		}
		else {
			System.out.println("Student assignment results were unable to be calculated");
		}
		
	}
	
	public String generateAnswerString(List<StudentAnswerModel> studentAnswerList, 
			Integer questionid){
		List<String> answerStrList = new ArrayList<String>();
		int listlen = studentAnswerList.size();
		for(int i = 0; i < listlen; i++){
			int listid = studentAnswerList.get(i).getQuestionid();
			if(listid == questionid) {
				answerStrList.add(studentAnswerList.get(i).getStudentAnswer());
			}
		}
		int listlenbis = answerStrList.size();
		String answerstring = null;
		if(listlenbis > 0){
			answerstring = answerStrList.get(0);
		}
		if(listlenbis > 1){
			for(int j = 1; j < listlenbis; j++){
				answerstring = answerstring +","+answerStrList.get(j);
			}
		}		
		return answerstring;
	}
}
