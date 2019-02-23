package com.linguaclassica.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityAnswerModel;
import com.linguaclassica.entity.EntityQuestionGroupModel;
import com.linguaclassica.entity.EntityQuestionModel;
import com.linguaclassica.entity.EntityTextPassageModel;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.ProficiencyModel.Proficiency;
import com.linguaclassica.model.QuestionGroupModel;
import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.model.StudentAnswerModel;
import com.linguaclassica.model.TextPassageModel;
import com.linguaclassica.model.UnknownEntityException;
import com.linguaclassica.repository.AnswerRepository;
import com.linguaclassica.repository.AssignmentFactRepository;
import com.linguaclassica.repository.AssignmentRepository;
import com.linguaclassica.repository.QuestionGroupRepository;
import com.linguaclassica.repository.QuestionRepository;
import com.linguaclassica.repository.StudentAnswerRepository;
import com.linguaclassica.repository.TextPassageRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;

@Service
@Transactional
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private StudentAnswerRepository studentAnswerRepository;
	
	@Autowired
	private TextPassageRepository textPassageRepository;
	
	@Autowired
	private QuestionGroupRepository questionGroupRepository;
	
	@Autowired
	private AssignmentFactRepository assignmentFactRepository;
	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
//	@Autowired
//	private QuestionQuestionGroupRepository questionQuestionGroupRepository;	

	@Autowired
	ModelFactory modelFactory;
	
	/**
	 * This method creates a question.
	 *  If no exceptions are thrown the method is successful.
	 * 
	 * @param questionModel
	 * @throws EntityAlreadyExistsException if the user already exists.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public void createQuestion(QuestionModel questionModel) 
			throws ServiceException, PersistenceException {
		
		//if (questionRepository == null) {
			//System.out.println("questionRepository is null");
		//}

		try {
			questionRepository.createQuestion((EntityQuestionModel)questionModel);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}	

	
	/**
	 * This method creates a question.
	 *  If no exceptions are thrown the method is successful.
	 * 
	 * @param answerModel
	 * @throws EntityAlreadyExistsException if the user already exists.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public void createAnswer(AnswerModel answerModel) 
			throws ServiceException, PersistenceException {
		
		if (answerRepository == null) {
			System.out.println("answerRepository is null");
		}

		try {
			answerRepository.createAnswer((EntityAnswerModel) answerModel);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method creates a question where no question number is included in the question model.
	 *  If no exceptions are thrown the method is successful.
	 * 
	 * @param questionModel
	 * @throws EntityAlreadyExistsException if the user already exists.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public void addOrUpdateAnswer(AnswerModel answerModel) throws ServiceException {
		System.out.println("QuestionService, line 91");
		if (answerRepository == null) {
			System.out.println("answerRepository is null");
		}
	  	//System.out.println("QuestionService, line 36:  questionModel.getTemplate() = " + questionModel.getTemplate());

		try {
			//questionRepository.addQuestionData((EntityQuestionModel)questionModel);
			answerRepository.addOrUpdateAnswer((EntityAnswerModel)answerModel);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	
	/**
	 * This method creates a question where no question number is included in the question model.
	 *  If no exceptions are thrown the method is successful.
	 * 
	 * @param questionModel
	 * @throws EntityAlreadyExistsException if the user already exists.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public void addQuestionData(QuestionModel questionModel) throws ServiceException {
		//System.out.println("QuestionService, line 133");
		if (questionRepository == null) {
			System.out.println("questionRepository is null");
		}
	  	//System.out.println("QuestionService, line 36:  questionModel.getTemplate() = " + questionModel.getTemplate());

		try {
			//questionRepository.addQuestionData((EntityQuestionModel)questionModel);
			questionRepository.addOrUpdateQuestion((EntityQuestionModel)questionModel);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	/*
	public void addQuestionToQuestionGroup(QuestionQuestionGroupModel questionQuestionGroupModel) {
		questionQuestionGroupRepository.addQuestionToQuestionGroup(questionQuestionGroupModel);
	}
	
	
	
	public List<EntityQuestionQuestionGroupModel>  findQuestionGroupByQuestionId(int questionid) {
		return questionQuestionGroupRepository.findQuestionGroupByQuestionId( questionid);
	}
	public int findQuestionGroupId(int questionid) {
		return questionQuestionGroupRepository.findQuestionGroupId( questionid);
	}
	
	public int getMaxQuestionByQuestionGroupId(int questiongroupI) {
		return questionQuestionGroupRepository.getMaxQuestionByQuestionGroupId( questiongroupI);
	}
	
	public List<QuestionModel>  findQuestionsByQuestionGroupId(int questiongroupid)
	
	public Integer findLastQuestionQuestionId() {
		return questionQuestionGroupRepository.findLastQuestionQuestionId();
	}
	
	
	public void removeQuestionsByQuestionQuestionGroupId(Integer questionquestiongroupId)  {
		
		if(questionquestiongroupId == null || questionquestiongroupId <= 0)// if no group identifier found  - do not proceed.
			return;
		// delete both references - questionquestion_group will remove corresponding records automatically as foreign keys
		List<EntityQuestionQuestionGroupModel> listEntityQuestionQuestionGroupMode = questionQuestionGroupRepository.findQuestionByQuestionQuesiontGroupId(questionquestiongroupId);
		int questiongroupId = 0;

		for (EntityQuestionQuestionGroupModel qqgModel : listEntityQuestionQuestionGroupMode){
			questiongroupId = qqgModel.getQuestionGroupId();
			deleteQuestion(qqgModel.getQuestionid());
		}
		if(questiongroupId <= 0)
			questionGroupRepository.deleteQuestionGroup(questiongroupId);	
	}*/
	
	public void removeQuestionsByQuestionGroupId(Integer questiongroupid)  {
		
		if(questiongroupid == null || questiongroupid <= 0)// if no group identifier found  - do not proceed.
			return;
		
		questionGroupRepository.deleteQuestionGroup(questiongroupid);	
		/*List<EntityQuestionQuestionGroupModel> listEntityQuestionQuestionGroupMode = findQuestionByQuestionGroupId(questiongroupId);
		for (EntityQuestionQuestionGroupModel qqgModel : listEntityQuestionQuestionGroupMode){
			deleteQuestion(qqgModel.getQuestionid());
		}*/
		
	}
	public Integer getQuestionGroupListSize() {
		return questionRepository.getQuestionGroupListSize();
	}
	
	
	public Integer findLastQuestionId() throws ServiceException, UnknownEntityException {
		return questionRepository.findLastQuestionId();
	}
	
	public List<EntityQuestionModel> getListOfQuestionsById(int questionid) {
		return questionRepository.getListOfQuestionsById(questionid);
	}
	
	public List<QuestionModel> getListOfQuestionsByQuestionGroupId(Integer questGroupId) {
		return questionRepository.getListOfQuestionsByQuestionGroupId(questGroupId);
	}
	
	public List<AnswerModel> getListOfAnswersByQuestionGroupId(Integer questGroupId) {
		List<QuestionModel> questionList = questionRepository.getListOfQuestionsByQuestionGroupId(questGroupId);
		List<Integer> questionIdList = new ArrayList<Integer>();
		int listlen = questionList.size();
		for (int i = 0; i < listlen; i++) {
			questionIdList.add(questionList.get(i).getId());
		}
		return answerRepository.getListOfAnswersByQuestionIdList(questionIdList);
	}
	
	public List<AnswerModel> getListOfAnswersByExerciseId(Integer exerciseId) {
		List<QuestionModel> questionList = questionRepository.getListOfQuestionByExerID(exerciseId);
		List<Integer> questionIdList = new ArrayList<Integer>();
		int listlen = questionList.size();
		for (int i = 0; i < listlen; i++) {
			questionIdList.add(questionList.get(i).getId());
		}
		return answerRepository.getListOfAnswersByQuestionIdList(questionIdList);
	}
	
	public List<AnswerModel> getListOfAnswersByQuestionId(int questionId) {
		return answerRepository.getListOfAnswersByQuestionId(questionId);
	}	
	
	public  List<QuestionModel> getListOfQuestionByExerID(int exerid) {
		return questionRepository.getListOfQuestionByExerID(exerid);
	}
	
	public List<Integer> getListOfQuestionIdsByExerID(int exerid){		
		return questionRepository.getListOfQuestionIdsByExerID(exerid);
	}
	
	public  int getSizeOfQuestionListByExerID(List<QuestionModel> questionList) {
		
		int exercisecount = questionList.size();
		Integer previousid = 0;
		Integer groupid = 0;
		int groups = 0;
		for(QuestionModel qm: questionList){
			groupid = qm.getQuestionGroupid();
			if(groupid != null && groupid > 0){
				exercisecount--;
				if( previousid != groupid){// count only unique group ids
					previousid = groupid;
					groups++;
				}
			}
		}
		exercisecount += groups;
		return exercisecount;
	}
	
	/**
	 * Get comma delimited string of proficiencies evaluated by the assignment
	 * @param exerciseid
	 * @param variable1
	 * @param variable2
	 * @return
	 */
	public String getProficienciesByExerciseId(Integer exerciseid) {
		return questionRepository.getProficienciesByExerciseId(exerciseid);
	}

	/**
	 * Build a string of proficiencies from a list of proficiency ids
	 * @param proficiencyIdList
	 * @return
	 */
	public String buildProficiencyStringFromIdList(ArrayList<Integer> proficiencyIdList){
		return questionRepository.buildProficiencyStringFromIdList(proficiencyIdList); 
	}
	
	/**
	 * Returns friendly proficiency name for a question
	 * @param proficiencyId
	 * @return
	 */
	public String getProficiencyString(Integer proficiencyId){
		return questionRepository.getProficiencyString(proficiencyId);
	}
	
	/**
	 * Get a proficiency based on id
	 * @param proficiencyid
	 * @return Proficiency
	 */
	public Proficiency getProficiencyById(Integer proficiencyId) {
		return questionRepository.getProficiencyById(proficiencyId);
	}
	
	/**
	 * Get an id based on proficiency
	 * @param proficiency
	 * @return Proficiency id
	 */
	public Integer getProficiencyIdByProficiency(Proficiency proficiency) {
		return questionRepository.getProficiencyIndexByProficiency(proficiency);
	}
	
	/**
	 * Finds a Question By its Id 
	 * @param questionId
	 * @return EntityQuestionModel
	 */
	public EntityQuestionModel findQuestionById(Integer questionId) {
		return questionRepository.findQuestionById(questionId);
	}
	
	public List<QuestionModel>  findQuestionsByQuestionGroupId(int questiongroupid) {
		return questionRepository.getListOfQuestionsByQuestionGroupId( questiongroupid);
	}
	
	/**
	 * Compares student's answer with correct answer
	 * Assumes student gives only one answer
	 * Returns true if student gave a correct answer
	 * Otherwise, returns false
	 * @param questionId, studentAnswer
	 * @return boolean
	 * @throws ServiceException if an unspecified error occurs
	 * @throws UnknownEntityException if the entity cannot be found.
	 */
	public boolean markAnswer(Integer questionId, String studentAnswer)
			throws ServiceException, UnknownEntityException{
		
		String correctAnswer = ((EntityAnswerModel) answerRepository.findCorrectAnswerByQuestionId(questionId)).getAnswer();
		System.out.println("QuestionService, line 183: The correct answer is: " + correctAnswer);
		System.out.println("QuestionService, line 184: The student's answer is: " + studentAnswer);
		String[] ansArray = correctAnswer.split(",");
		List<String> ansList = Arrays.asList(ansArray);
		return ansList.contains(studentAnswer);
		
	}
	
	/**
	 * Compares student's answer with correct answer
	 * Student may give more than one answer per question where appropriate
	 * Returns percentage correct per question as number of correct answers
	 * divided by total number of answers expected for the question
	 * @param questionId, studentAnswer
	 * @return long
	 * @throws ServiceException if an unspecified error occurs
	 * @throws UnknownEntityException if the entity cannot be found.
	 */
	public long markAnswer2(Integer questionId, Integer assignFactId)
			throws ServiceException, UnknownEntityException{
		
		//String questiontype = questionModel.getQuestiontype();
		List<AnswerModel> answerList = answerRepository.getListOfAnswersByQuestionId(questionId);
		List<StudentAnswerModel> studentAnswerList = 
				studentAnswerRepository.getListOfStudentAnswersByQuestionId(questionId,assignFactId);
		int listlen = answerList.size();
		int correctanswers = 0;
		
		for (int i = 0; i < listlen; i++) {
			if (answerList.get(i) != null) {
				System.out.println("QuestionService, line 386: answerList.get(i).getAnswer(), "
						+ "studentAnswerList.get(i).getStudentAnswer() = " 
						+ answerList.get(i).getAnswer() + ", " + studentAnswerList.get(i).getStudentAnswer());
				if (answerList.get(i).getAnswer().equals(studentAnswerList.get(i).getStudentAnswer())) {
					correctanswers++;
				}
			}
			else {
				if(studentAnswerList.get(i) != null) {
					System.out.println("Student answer is wrong, should be null.");
				}
				else {
					correctanswers++;
				}
			}
		}
		
		
		long correctpercent = (long) 0.00;
		System.out.println("QuestionService, line 402:  listlen, correctanswers = " + listlen + ", " 
				+ correctanswers);
		if (correctanswers > 0) {
			correctpercent = (listlen/correctanswers) * 100;
		}
		System.out.println("QuestionService, line 389: correctpercent = " + correctpercent);
		return correctpercent;
		
	}
	
	public EntityAnswerModel findCorrectAnswerByQuestionId(Integer questionId) {
		return answerRepository.findCorrectAnswerByQuestionId(questionId);
	}
	
	public EntityAnswerModel findAnswerByQuestionId(Integer questionId) {
		return answerRepository.findAnswerByQuestionId(questionId);
	}
	
	public List<EntityAnswerModel> findAnswersByQuestionId(Integer questionId) {
		return answerRepository.findAnswersByQuestionId(questionId);
	}
	
	public void removeAnswersByQuestionId(Integer questionId) {
		answerRepository.removeAnswersByQuestionId(questionId);
	}
	public EntityTextPassageModel findPassageById(Integer passageId) {
		//System.out.println("QuestionService, line 246:  passageId = " + passageId);
		return textPassageRepository.findPassageById(passageId);
	}
	
	public void deleteQuestion(Integer questionIdIn) {
		
		questionRepository.deleteQuestion(questionIdIn);
		//answer will be deleted by database cascade on delete function
		return;
	}
	
public Integer createQuestionGroup(QuestionGroupModel questionGroupModel) {
	return questionGroupRepository.createQuestionGroup((EntityQuestionGroupModel) questionGroupModel);
}
	/**
	 * Finds a Question By passage Id 
	 * @param passageId
	 * @return List EntityQuestionModel
	 */
	public List<QuestionModel> findQuestionByPassageId(Integer passageId) {
		return questionRepository.findQuestionByPassageId(passageId);
	}
	
	/**
	 * Get the max  Question By passage Id 
	 * @param passageId
	 * @return Integer
	 */
	public Integer getMaxQuestionByPassageId(Integer passageId) {
		return questionRepository.getMaxQuestionByPassageId(passageId);
	}

	public void deleteTextPassage(int passageId) {
		System.out.println("QuestionService, deleteTextPassage");
		textPassageRepository.deleteTextPassage(passageId);
		return;
	}
	
		
	public void RenumberPassage(int exerciseId, String addsub){
		
		List<QuestionModel> questionModelList = getListOfQuestionByExerID(exerciseId);
		
		int strWorld = 0;
		
		int passID = 0; 
		int previousID = 0;
		renumberQuestions(exerciseId,0, addsub);
		for( QuestionModel qm :questionModelList){
			
			strWorld = qm.getStrword();
							
			if(strWorld > 0){
				
				passID = qm.getPassageid();
				if(passID != previousID){
					RenumberPassage(strWorld, passID, addsub);
					previousID = passID;
				}			}
			
		}
	}
	
	public void RenumberPassage(int strword, int passageid,	String addsub) {		
		System.out.println("QuestionService, line 288:  RenumberPassage, passageid = " + passageid);		
		
		TextPassageModel textPassageModel = textPassageRepository.findPassageById(passageid);		
		String passagecontent = textPassageModel.getTextcontent();
		int passagelen = passagecontent.length();
		//EntityQuestionModel questionModel = questionRepository.findQuestionById(questionId);
		int questModelStrword = strword;     //questionModel.getStrword();
		List<QuestionModel> questionModelList = questionRepository.findQuestionByPassageId(passageid);
		int listlen = questionModelList.size();
		System.out.println("QuestionService, line 292:  listlen = " + listlen);
		
		int start = 0;
		int parenopen = 0;
		int parenclos = 0;
		String oldNum; 
		String newNum;
		
		//sort qnumbers for passage 
		List<Integer> qnumberList = new ArrayList<Integer>();
		for (int i = 0; i < listlen; i++) {
			qnumberList.add(questionModelList.get(i).getQnumber());
		}
		Collections.sort(qnumberList);
		
		//sort questions by word start index
		List<Integer> strWordList = new ArrayList<Integer>();
		for (int j = 0; j < listlen; j++) {
			int strwrd = questionModelList.get(j).getStrword();
			if(strwrd > 0){// to account for the grop questions
				strWordList.add(strwrd);
			}
		}
		Collections.sort(strWordList);
	
		listlen = strWordList.size();
		
		//set qnumbers in order of word start indices
		for (int k = 0; k < listlen; k++) {
			System.out.println("k = " + k);
			EntityQuestionModel questModel = (EntityQuestionModel) 
					questionRepository.findQuestionByStrWord(passageid, strWordList.get(k));
			int qnmbr = qnumberList.get(k);
			questModel.setQnumber(qnmbr);

			parenopen = passagecontent.indexOf("{",start);
			parenclos = passagecontent.indexOf("}",start);
			if (parenclos > 1) {
				String startstr = passagecontent.substring(0,parenopen + 1);
				//System.out.println("QuestionService, line 338:  startstr = " + startstr);
				String endstr = passagecontent.substring(parenclos,passagelen);
				//System.out.println("QuestionService, line 340:  endstr = " + endstr);
				passagecontent = startstr + qnmbr + endstr;
				passagelen = passagecontent.length();
				int qlen = String.valueOf(qnmbr).length();
				//System.out.println("qlen = " + qlen);
				start = parenopen + qlen + 2;
				
				//add length of question number to start word and end word for this question
				//and those after it in passage if (addsub.equals("add")) {
				//System.out.println("questModel.getWord(), questModel.getStrword(), questModelStrword = " + 
						//questModel.getWord() + ", " + questModel.getStrword() + ", " + questModelStrword);
				if (questModel.getStrword() >= questModelStrword) {  
					//System.out.println("QuestionService, line 338:  questModel.getId() >= questionId, "
							//+ "questModel.getStrword = " + questModel.getStrword());
					int qnlength = String.valueOf(qnmbr).length() + 2;
					int strwrd = questModel.getStrword();
					int endwrd = questModel.getEndword();
					if (addsub.equals("add")) {
						questModel.setStrword(strwrd + qnlength);
						questModel.setEndword(endwrd + qnlength);
						//System.out.println("add: questModel.getStrword() = " + questModel.getStrword());
					}
					
					else if (addsub.equals("subtract")) {
						questModel.setStrword(strwrd - qnlength);
						questModel.setEndword(endwrd - qnlength);
						//System.out.println("subtract:  questModel.getStrword() = " + questModel.getStrword());	
						oldNum = "{" + Integer.toString(qnmbr + 1) + "}";
						newNum = "{" + Integer.toString(qnmbr) + "}";
						passagecontent.replaceFirst(oldNum, newNum);
										
					}
					
					questionRepository.addOrUpdateQuestion(questModel);
				}
				
			}
		}
			
			textPassageModel.setTextcontent(passagecontent);
			textPassageRepository.updateTextPassage(textPassageModel);
	}
	
	public void renumberQuestions(Integer exerciseId, int strword, String addsub) {		
		
		int currentqnumber = 1;
		int prevListMatch = 0;// to account for List Match groupings
		int groupListNumber = 0;
		
		List<QuestionModel> questionslist = new ArrayList<QuestionModel>();
		questionslist = questionRepository.getListOfQuestionByExerID(exerciseId); ///[list ordered ASC by q.qnumber]
		int qlistlen = questionslist.size();

		
		for(int i = 0; i < qlistlen; i++ ){
			EntityQuestionModel question = (EntityQuestionModel) questionslist.get(i);
			//	if (currentpassageid == null || currentpassageid == 0) {
				if(question.getQuestiontype().equals("List matching")){//list match case ;					
					if (prevListMatch != question.getQuestionGroupid()){// a new group
						prevListMatch = question.getQuestionGroupid();
						groupListNumber = currentqnumber;
						currentqnumber++;
					}
					question.setQnumber(groupListNumber);//same group
					
				}
				else{
					question.setQnumber(currentqnumber);
					currentqnumber++;
				}
				questionRepository.addOrUpdateQuestion(question);				
				/*		}
			else {   //question.getPassageid not null and not equal to 0		
					//current passage set to this question's passageid								
			//	TextPassageModel textPassageModel = textPassageRepository.findPassageById(currentpassageid);		
			//	String passagecontent = textPassageModel.getTextcontent();	
				List<EntityQuestionModel> passagequestionslist = 
						new ArrayList<EntityQuestionModel>();
				passagequestionslist = 
						questionRepository.getListOfQuestionsByPassageIdStrword(currentpassageid);
							
				int passlistlen = passagequestionslist.size();
								
						//list ASC by strword					
				for (int j = 0; j < passlistlen; j++) {
					EntityQuestionModel questModel = (EntityQuestionModel) passagequestionslist.get(j);
					if (questModel.getStrword() != null && questModel.getStrword() > 0) {
						
						System.out.println("currentqnumber, question.getQuestion() = " + 
								currentqnumber + ", " + question.getQuestion());                                 
					                        //assign question the current qnumber
						if (questModel.getStrword() > strword)
						{
							int strwrd = questModel.getStrword(); 
							int endwrd = questModel.getEndword();
							int qnmbr = questModel.getQnumber();
							int qnlength = String.valueOf(qnmbr).length() + 2;
							if (addsub.equals("add")) {
								questModel.setStrword(strwrd + qnlength);
								questModel.setEndword(endwrd + qnlength);
								//System.out.println("add: questModel.getStrword() = " + questModel.getStrword());
							}
							
							else if (addsub.equals("subtract")) {
								questModel.setStrword(strwrd - qnlength);
								questModel.setEndword(endwrd - qnlength);																	
							}
						}
						
						if(questModel.getQuestiontype().equals("List matching")){//list match case ;					
							if (prevListMatch != questModel.getQuestionGroupid()){// a new group
								prevListMatch = questModel.getQuestionGroupid();
								groupListNumber = currentqnumber;
								currentqnumber++;
							}
							questModel.setQnumber(groupListNumber);//same group
						}
						else{
							questModel.setQnumber(currentqnumber);
							currentqnumber++;
						}
						questionRepository.addOrUpdateQuestion(questModel);
						i++;//increment the count since a model is processed  																		
					}						
				} 		
				currentpassageid = 0;
			i--;//after passage - reset back one count as the increment will be also done in the for loop
			}*/
		
		}  
		
	}

	public void removeAllQuestionGroups(int exerciseid) {
		List<Integer> questionGrouIDs = questionRepository.getAllQuestionGroupsId(exerciseid);
		if(questionGrouIDs != null && questionGrouIDs.size() > 0){
			
			for(Integer groupid: questionGrouIDs){
				removeQuestionsByQuestionGroupId(groupid);
			}
		}
	}
	

	public int getPassageQuestionListSize(Integer passageid) {
		// TODO Auto-generated method stub
		return questionRepository.getPassageQuestionListSize(passageid);
	}				
}

