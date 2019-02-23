package com.linguaclassica.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.linguaclassica.model.AssignmentFactModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.model.StudentAnswerModel;
import com.linguaclassica.model.SessionModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.AssignmentFactService;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.ExerciseService;
import com.linguaclassica.service.QuestionService;
import com.linguaclassica.service.ResultsService;
import com.linguaclassica.service.StudentAnswerService;
import com.linguaclassica.service.SessionService;


/**
 * Servlet implementation class JsonServlet
 */
@WebServlet("/JsonServlet")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	// Model Factory to use in the servlet. Configured in init() method as we need to use
	// the Spring ApplicationContext manually as this servlet is not part of Spring MVC
	private ModelFactory modelFactory;
	
	// Adding support to handle sessions
	private SessionService sessionService;
	
	@SpringBean
	private ExerciseService exerciseService;
	
	@SpringBean
	private AssignmentService assignmentService;
	
	@SpringBean
	private AssignmentFactService assignmentFactService;
	
	@SpringBean
	private QuestionService questionService;
	
	@SpringBean
	private ResultsService resultsService;

	@SpringBean
	private StudentAnswerService studentAnswerService;	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JsonServlet(String do_this) {
        super();
    }
    
    public JsonServlet(String do_this,Integer textId, String word, Integer line, Integer lnchar, Integer wrdcnt, Integer idx, 
    		Integer idend, String root, String pos, String var1, String var2, String var3, String var4, String var5, String dicmng,
    		String trans,String comment) {
        super();
    }    
    
    public JsonServlet(String do_this,String filename,String param1,String param2,String param3) {
        super();
    }
    public JsonServlet(String do_this,String filename,String line,String wrdcnt) {
        super();
    }
    public JsonServlet(String do_this,String filename,String prp) {
        super();
    }
    public JsonServlet(String do_this,String filename) {
        super();
    }
    
    public JsonServlet() {
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

    	sessionService = (SessionService) context.getBean("sessionService");
    	
    	exerciseService = (ExerciseService) context.getBean("exerciseService");
    	
    	assignmentService = (AssignmentService) context.getBean("assignmentService");
    	
    	assignmentFactService = (AssignmentFactService) context.getBean("assignmentFactService");
    	
    	questionService = (QuestionService) context.getBean("questionService");
    	
    	studentAnswerService = (StudentAnswerService) context.getBean("studentAnswerService");
    	
    	resultsService = (ResultsService) context.getBean("resultsService");
    	
    	if (studentAnswerService == null) {
    		throw new ServletException("Unable to locate studentAnswerService bean");
    	}
    	
    	if (sessionService == null) {
    		throw new ServletException("Unable to locate sessionService bean");
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("In JsonServlet");
		request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
		
		String sessionName, createdate, lastdate;
		long createraw, lastraw;
		
		HttpSession session = request.getSession(true);
		sessionName = session.getId();
		
		HttpSession httpSession = request.getSession();
		UserModel userModel = (UserModel) httpSession.getAttribute(UserModel.LCUSER);
		String do_this = null;
		
		System.out.println("JsonServlet, line 169:  URL = " + request.getRequestURL().append('?').append(request.getQueryString()));
		
		String assignstatusstr = request.getParameter("assignmentstatus");
		String exername = request.getParameter("exercisename");	
			
		//String assignmentType = request.getParameter("assignmentType");
		
		//String exeridstr = request.getParameter("exerciseid");
		System.out.println("JsonServlet, line 174: assignstatusstr = " + 	assignstatusstr);
		System.out.println("JsonServlet, line 175: exername = " + 	exername);	
		String assignmentIdstr = request.getParameter("assignmentid");
		System.out.println("JsonServlet, line 179:  assignmentIdstr = " + assignmentIdstr);
		int assignmentId = Integer.parseInt(assignmentIdstr);
		int assignmentFactId = 0;
		Integer exerid = Integer.parseInt(request.getParameter("exerciseid"));
		System.out.println("JsonServlet line 175:  exerid = " + exerid); //was 183
		
		int userId = 0;
		
		AssignmentFactModel assignmentFactModel = null;
//		AssignmentModel assignmentModel = null;
		
		if(userModel == null){
			JSONObject flag = new JSONObject();
			flag.put("flagMsg", "error");
			response.setContentType("application/x-json");
			response.getWriter().print(flag);
		}
		else {
			userId = userModel.getId();
			
			do_this = request.getParameter("do_this");
			
			createraw = session.getCreationTime();
			lastraw = session.getLastAccessedTime(); // current time
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
			createdate = sdf.format(createraw);
			lastdate = sdf.format(lastraw);
			
			SessionModel sessionModel = modelFactory.getNewSessionModel();
			sessionModel.setSessionName(sessionName);
			sessionModel.setCreatetime(createdate);
			sessionModel.setLasttime(lastdate);
			sessionModel.setUserId(userId); 
			sessionService.addSessiondata(sessionModel);
			
			System.out.println("JsonServlet, line 224:  assignstatusstr = " + assignstatusstr);
			if (assignstatusstr.equals("PRACTICE")) {	
				assignmentFactModel = modelFactory.getNewAssignmentFactModel();
				
				assignmentFactModel.setAssignmentId(assignmentId);
				assignmentFactModel.setStudentId(userId);
				assignmentFactService.createAssignmentFact(assignmentFactModel);					
				assignmentFactId = assignmentFactModel.getId();
				System.out.println("JsonServlet, line 261:  assignmentFactId, assignmentId = " + 
						assignmentFactId + ", " + assignmentId);
			}
			else if (assignstatusstr.equals("START")) {	
				assignmentId = Integer.parseInt(request.getParameter("assignmentid"));
				int afcount = 
						assignmentFactService.getCountByUserAndAssignmentId(userId, assignmentId);
				if(afcount > 0){
					System.out.println("JsonServlet, line 264:  assignmentFactModel not null");
					assignmentFactModel = assignmentFactService.findAssignmentFactByAssignID(userId, assignmentId);
					assignmentFactId = assignmentFactModel.getId();
				}
				else {
					assignmentFactModel = modelFactory.getNewAssignmentFactModel();
					//assignmentService.getClassIdByAssignId(assignmentId);
					int classid = Integer.parseInt(request.getParameter("classid"));	
					assignmentFactModel.setClassId(classid);
					
					assignmentFactModel.setAssignmentId(assignmentId);
					assignmentFactModel.setStudentId(userId);
					assignmentFactService.createAssignmentFact(assignmentFactModel);					
					assignmentFactId = assignmentFactModel.getId();
				}
		//		assignmentModel = assignmentService.findAssignmentById(assignmentId);
				System.out.println("JsonServlet, line 271:  assignmentFactId, assignmentId = " + 
						assignmentFactId + ", " + assignmentId);					
			}
			
			//used for practice exercises
			if(do_this == null || do_this.equals("")) {	
				System.out.println("JsonServlet, line 253");			
				
				String var1 = null;
				String var2 = null;
				var1 = request.getParameter("variable1");
				var2 = request.getParameter("variable2");
				System.out.println("JsonServlet 225: exername, var1, var2 = " + exername + ", " + var1 + 
						", " + var2);
				
				userId = userModel.getId();
				System.out.println("JsonServlet, line 233:  userId, assignstatusstr = " + userId + 
							", " + assignstatusstr);				
				
				List<QuestionModel> templateModel = questionService.getListOfQuestionByExerID(exerid);
				String answ = "answer";
	        
				Map<String,String[]> paramap = request.getParameterMap();
				for (Map.Entry<String,String[]> entry : paramap.entrySet()) {
					String key = entry.getKey();
					String[] value = entry.getValue();
					int vallen = value.length;
					for (int k=0; k < vallen; k++) {
						System.out.println("key, value[k] = " + key + ", " + value[k]);	 //just for visual inspection
					}
				}

				if (studentAnswerService == null ) {
					System.out.println("studentanswerService is null");
				}
				else {
					System.out.println("studentanswerService not null. ");
				}

				// populate student answer
				for (int answerNum = 1; answerNum <= (paramap.size()); answerNum++) {
					System.out.println("JsonServlet, line 284:  answerNum = " + answerNum);
					String[] answerStr = paramap.get(answ + answerNum);
					if (null != answerStr)
					{
						StudentAnswerModel sam = modelFactory.getNewStudentAnswerModel();
						sam.setAssignmentFactId(assignmentFactId);
						sam.setQuestionid(templateModel.get(answerNum - 1).getId());
						sam.setStudentAnswer(answerStr[0]);
						studentAnswerService.addStudentAnswerData(sam);
					}
				}

				if (assignstatusstr.equals("PRACTICE")) {
					System.out.println("JsonServlet, line 336:  assignmentFactId = " + assignmentFactId);
					if (resultsService == null) {
						System.out.println("resultsService = null");
					}
					resultsService.calculateStudentAssignmentResults(assignmentFactId);	
					resultsService.addStudentAssignmentResults(assignmentFactId);
				}
				
		        System.out.println("Being redirected to the studentexercises (Student Practice Exercises page)");
			}
			
			//used for class assignments
			else if (do_this.equals("checkAns")){
				
				List<QuestionModel> questionList = questionService.getListOfQuestionByExerID(exerid);
				int listlen = questionList.size();
				for (int j = 0; j < listlen; j++) {	
					
					if(questionList.get(j).getQuestiontype().equals("Latin Parsing")) {
						List<String> answerListLP = new ArrayList<String>();
						String latpos = request.getParameter("latpos"+questionList.get(j).getId());
						String latvar1 = request.getParameter("latvar1"+questionList.get(j).getId());
						String latvar2 = request.getParameter("latvar2"+questionList.get(j).getId());
						String latvar3 = request.getParameter("latvar3"+questionList.get(j).getId());
						String latvar4 = request.getParameter("latvar4"+questionList.get(j).getId());
						String latvar5 = request.getParameter("latvar5"+questionList.get(j).getId());
						
						answerListLP.add(latpos);
						if(latvar1 != null && !latvar1.equals("")){
							answerListLP.add(latvar1);
						}
						if(latvar2 != null && !latvar2.equals("")){
							answerListLP.add(latvar2);
						}
						if(latvar3 != null && !latvar3.equals("")){
							answerListLP.add(latvar3);
						}
						if(latvar4 != null && !latvar4.equals("")){
							answerListLP.add(latvar4);
						}
						if(latvar5 != null && !latvar5.equals("")){
							answerListLP.add(latvar5);
						}						
						insertAnswer(assignmentFactId, questionList.get(j).getId(), answerListLP);
						
					}
					
					else if(questionList.get(j).getQuestiontype().equals("Greek Parsing")) {
						System.out.println("Greek parsing question, questionList.get(j).getId() = " +
								questionList.get(j).getId());
						List<String> answerListGP = new ArrayList<String>();
						String grkpos = request.getParameter("grkpos"+questionList.get(j).getId());
						String grkvar1 = request.getParameter("grkvar1"+questionList.get(j).getId());
						String grkvar2 = request.getParameter("grkvar2"+questionList.get(j).getId());
						String grkvar3 = request.getParameter("grkvar3"+questionList.get(j).getId());
						String grkvar4 = request.getParameter("grkvar4"+questionList.get(j).getId());
						String grkvar5 = request.getParameter("grkvar5"+questionList.get(j).getId());
						String grkvar6 = request.getParameter("grkvar6"+questionList.get(j).getId());
						answerListGP.add(grkpos);
						if(grkvar1 != null && !grkvar1.equals("")){
							answerListGP.add(grkvar1);
						}
						if(grkvar2 != null && !grkvar2.equals("")){
							answerListGP.add(grkvar2);
						}
						if(grkvar3 != null && !grkvar3.equals("")){
							answerListGP.add(grkvar3);
						}
						if(grkvar4 != null && !grkvar4.equals("")){
							answerListGP.add(grkvar4);
						}
						if(grkvar5 != null && !grkvar5.equals("")){
							answerListGP.add(grkvar5);
						}
						if(grkvar6 != null && !grkvar6.equals("")){
							answerListGP.add(grkvar6);
						}						
						insertAnswer(assignmentFactId, questionList.get(j).getId(), answerListGP);
					}
					else if (questionList.get(j).getQuestiontype().equals("Multiple select")) {
						List<String> ansList = new ArrayList<String>();
						for (int n = 0; n < 20; n++) {
							String ansname = "answer-"+questionList.get(j).getId()+n;
							String studentAnswer = request.getParameter(ansname);
							System.out.println("JsonServlet, line 445:  ansname, studentAnswer = " +
									ansname + ", " + studentAnswer);
							if(studentAnswer != null) {
								ansList.add(studentAnswer);								
							}
						}
						
						//System.out.println("JsonServlet, line 459:  ansList.size(), ansList.get(1) = " + 
						//		ansList.size() + ", " + ansList.get(1)); 
						
						insertAnswer(assignmentFactId, questionList.get(j).getId(), ansList);
					}
					else if (questionList.get(j).getQuestiontype().equals("List matching")) {
						int questiongroupid = questionList.get(j).getQuestionGroupid();
						List<QuestionModel> questionsList = questionService.findQuestionsByQuestionGroupId(questiongroupid);
						int qslistlen = questionsList.size();							
						List<Integer> questionsIdList = new ArrayList<Integer>();
						for (int r = 0; r < qslistlen; r++) {
							questionsIdList.add(questionsList.get(r).getId());
						}
						Collections.sort(questionsIdList);
						int lastQuestId = questionsIdList.get(qslistlen - 1);
						int firstQuestId = questionsIdList.get(0);
						String studentAnswer = null;
						String answerIn = request.getParameter("answer-"+questionList.get(j).getId());
						System.out.println("JsonServlet, line 469:  answerIn = " + answerIn);
						if (answerIn != null && !answerIn.equals("") ) {
							int ansint = Integer.parseInt(answerIn);
							int index = firstQuestId + ansint - 1;
							if (index > lastQuestId) {
								index = firstQuestId + (index - lastQuestId) - 1;
							}
							
							String ansname = "answer-"+(index)+"-x";
							studentAnswer = request.getParameter(ansname);
						}
						else {
							studentAnswer = null;
						}
						System.out.println("JsonServlet, line 483:  studentAnswer = " + studentAnswer);
						List<String> ansList = new ArrayList<String>();
						ansList.add(studentAnswer);
						insertAnswer(assignmentFactId, questionList.get(j).getId(), ansList);
					}
					else {
						System.out.println("Other question type, questionList.get(j).getId() = " +
								questionList.get(j).getId());
						List<String> studentAnswerList = new ArrayList<String>();
						String studentAnswer = request.getParameter("answer-"+questionList.get(j).getId());
						studentAnswerList.add(studentAnswer);
						insertAnswer(assignmentFactId, questionList.get(j).getId(), studentAnswerList);
						System.out.println("JsonServlet, line 510:  studentAnswer = " + studentAnswer);
					}
				}
				List<StudentAnswerModel> checkansList = 
						studentAnswerService.getListOfStudentAnswerByAssignFactID(assignmentFactId);
				int anslistlen = checkansList.size();
				for(int k = 0; k < anslistlen; k++) {
					System.out.println("JsonServlet, line 513:  "
							+ "checkansList.get(k).getQuestionid()"
							+ "checkansList.get(k).getStudentAnswer() = "
							+ checkansList.get(k).getQuestionid() + ", " 
							+ checkansList.get(k).getStudentAnswer());	
				}
				
	//			Timestamp now = new Timestamp(System.currentTimeMillis());

				if (assignstatusstr.equals("PRACTICE")) {
					System.out.println("JsonServlet, line 472:  assignmentFactId, assignmentId = " + 
							assignmentFactId + ", " + assignmentId);
					System.out.println("JsonServlet, line 474:  assignmentFactId = " + assignmentFactId);
					if (resultsService == null) {
						System.out.println("resultsService = null");
					}
					resultsService.calculateStudentAssignmentResults(assignmentFactId);	
					resultsService.addStudentAssignmentResults(assignmentFactId);
				}				
			}
		}
		
		response.sendRedirect("studentexercises");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void insertAnswer(int assignmentfactid, int questionid, List<String> studentanswerList) {
		
		StudentAnswerModel updateAnswerModel = null;
		List<StudentAnswerModel> studAnsModelList = 
			studentAnswerService.getListByAssignFactAndQuestionIDs(assignmentfactid, questionid);

		System.out.println("JsonServlet, line 554:  studAnsModelList.size(), "
				+ "studentanswerList.size() = " + studAnsModelList.size() + ", " +
				studentanswerList.size());
		
		//existing records at least 1 and equal updates
		if(studAnsModelList.size() > 0 && studAnsModelList.size() == studentanswerList.size()){
			System.out.println("JsonServlet, line 563:  lists > 0 and equal");
			for(int i = 0; i < studAnsModelList.size(); i++){
				updateAnswerModel = studAnsModelList.get(i);
				updateAnswerModel.setStudentAnswer(studentanswerList.get(i));
				studentAnswerService.addStudentAnswerData(updateAnswerModel);
			}
		}
		//no existing records, create a new StudentAnswerModels for each update
		else if(studAnsModelList.size() < 1){
			for(int i = 0; i < studentanswerList.size(); i++){
				updateAnswerModel = modelFactory.getNewStudentAnswerModel();
				updateAnswerModel.setAssignmentFactId(assignmentfactid);
				updateAnswerModel.setQuestionid(questionid);
				updateAnswerModel.setStudentAnswer(studentanswerList.get(i));
				studentAnswerService.addStudentAnswerData(updateAnswerModel);				
			}
		}
		//at least one existing records and larger list of updates
		else if(studAnsModelList.size() > 0 && studAnsModelList.size() < studentanswerList.size()){
			int diff = studentanswerList.size() - studAnsModelList.size();
			//replace existing records with updates
			for(int i = 0; i < studentanswerList.size() - diff; i++){
				updateAnswerModel = studAnsModelList.get(i);
				updateAnswerModel.setStudentAnswer(studentanswerList.get(i));
				studentAnswerService.addStudentAnswerData(updateAnswerModel);				
			}
			//create new records for the excess of updates
			for(int j = studentanswerList.size() - diff; j < studentanswerList.size(); j++){
				updateAnswerModel = modelFactory.getNewStudentAnswerModel();
				updateAnswerModel.setAssignmentFactId(assignmentfactid);
				updateAnswerModel.setQuestionid(questionid);
				updateAnswerModel.setStudentAnswer(studentanswerList.get(j));
				studentAnswerService.addStudentAnswerData(updateAnswerModel);
			}
		}
		//at least two existing record and more existing records than updates 
		else if(studAnsModelList.size() > 1 && studAnsModelList.size() > studentanswerList.size()){
			int diff = studAnsModelList.size() - studentanswerList.size();
			//replace existing records with updates
			for(int i = 0; i < studAnsModelList.size() - diff; i++){
				updateAnswerModel = studAnsModelList.get(i);
				updateAnswerModel.setStudentAnswer(studentanswerList.get(i));
				studentAnswerService.addStudentAnswerData(updateAnswerModel);				
			}
			//delete the excess of records
			for(int j = studAnsModelList.size() - diff; j < studAnsModelList.size(); j++){
				studentAnswerService.deleteStudentAnswer(studAnsModelList.get(j));
			}
		}
		return;
	}	
}
