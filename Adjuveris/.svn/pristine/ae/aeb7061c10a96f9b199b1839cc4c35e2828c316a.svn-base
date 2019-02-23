package com.linguaclassica.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityAssignmentFactModel;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.entity.EntityClassAssignmentResultsModel;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityStudentAssignmentResultsModel;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.AssignmentFactModel;
import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.model.ClassAssignmentResultsModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.model.StudentAnswerModel;
import com.linguaclassica.model.StudentAssignmentResultsModel;
import com.linguaclassica.model.TeacherAssignmentResultsModel;
import com.linguaclassica.model.TeacherPreResultsModel;
import com.linguaclassica.repository.AnswerRepository;
import com.linguaclassica.repository.AssignmentFactRepository;
import com.linguaclassica.repository.AssignmentRepository;
import com.linguaclassica.repository.ClassAssignmentResultsRepository;
import com.linguaclassica.repository.QuestionRepository;
import com.linguaclassica.repository.StudentAnswerRepository;
import com.linguaclassica.repository.StudentAssignmentResultsRepository;
import com.linguaclassica.repository.TeacherAssignmentResultsRepository;
import com.linguaclassica.repository.TeacherPreResultsRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Service
@Transactional
public class ResultsService {
	
	@Autowired 
	private StudentAssignmentResultsRepository studentAssignmentResultsRepository;
	
	@Autowired
	private ClassAssignmentResultsRepository classAssignmentResultsRepository;
	
	@Autowired
	private TeacherAssignmentResultsRepository teacherAssignmentResultsRepository;
	
	@Autowired
	private TeacherPreResultsRepository teacherPreResultsRepository;
	
	@Autowired
	private AssignmentService assignmentService;
	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AssignmentFactService assignmentFactService;
	
	@Autowired
	private AssignmentFactRepository assignmentFactRepository;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private StudentAnswerService studentAnswerService;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private StudentAnswerRepository studentAnswerRepository;
	
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private ClassService classService;

	
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
				System.out.println("assignment status is neither COMPLETED nor PRACTICE, results should not be +"
						+ "added to database.");
				throw new Exception();
			}
			
		}catch(Exception e){
			throw new ServiceException();
		}

		EntityStudentAssignmentResultsModel resultsModel = calculateStudentAssignmentResults(assignFactId);
		if(resultsModel==null){
			System.out.println("Student assignment results could not be calculated");
		}
		try{
			studentAssignmentResultsRepository.addStudentAssignmentResults(resultsModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return;
	}
	
	/**
	 * Retrieves a record of one student's assignment results
	 * @param int assignFactId
	 * @return EntityStudentAssignmentResultsModel, or null
	 */
	public EntityStudentAssignmentResultsModel getStudentAssignmentResultsByAssignFactId(int assignFactId) {
		System.out.println("ResultsService, line 140:  assignFactId = " + assignFactId);
		try{
			return studentAssignmentResultsRepository.getStudentAssignmentResultsByAssignFactId(assignFactId);
		} catch(Exception e){
			System.out.println("ResultsService getStudentAssignmentResultsByAssignFactId(): "
					+ e.getCause());
			return null;
		}
	}
	
	/**
	 * Retrieves all of the student's assignment results
	 * If a student's assignment results with a 'COMPLETED' status is not in the database,
	 *   then this method invokes the add method to create a new record in the table
	 * @param int studentId
	 * @return list of student's assignment results, or blank list if no results found
	 */
	public List<EntityStudentAssignmentResultsModel> getListOfAssignmentResultsByStudentId(int studentId, 
			String statusIn, List<Integer> termIdList){
		System.out.println("ResultsService: getListOfAssignmentResultsByStudentId()");
		
		String status = statusIn;
		// given a student's id, need to retrieve a list of assignment facts for assignments that are completed
		List<EntityAssignmentFactModel> assignFacts = null;
		try{
			assignFacts = assignmentFactService.getListOfAssignmentFactByStudentId(status, "", studentId, termIdList);
			/*
			for (int i = 0; i < assignFacts.size(); i++) {
				System.out.println("ResultsService, line 167:  assignFacts.get(i).getId(),"  
						+ assignFacts.get(i).getId());
			}
			*/
		} catch (Exception e){
			System.out.println(e.getCause());
			return new ArrayList<EntityStudentAssignmentResultsModel>();
		}
		
		// compiling a list of all the completed assignment fact ids for a student
		List<Integer> assignFactIds = new ArrayList<Integer>();
		for(EntityAssignmentFactModel assignFact: assignFacts){
			assignFactIds.add(assignFact.getId());
		};
		System.out.println("ResultsService, line 180:  assignFactIds.size(); = " + assignFactIds.size());
				
		// traverses through list of assignment fact ids and adds each of the student's result records to 'results'	
		List<EntityStudentAssignmentResultsModel> results = 
				new ArrayList<EntityStudentAssignmentResultsModel>();
		
		try{
			//for(Integer assignFactId: assignFactIds){
			int listlen = assignFactIds.size();
			for (int t = 0; t < listlen; t++) {
				System.out.println("assignFactIds.get(t) = " + assignFactIds.get(t));
				EntityStudentAssignmentResultsModel item = null;
				if (studentAssignmentResultsRepository.checkIfStudentAssignmentResultsByAssignFactIdExists(assignFactIds.get(t))) {
					System.out.println("ResultsService, line 193:  student results are in table already");
					item = getStudentAssignmentResultsByAssignFactId(assignFactIds.get(t));
				}
				// if assignment's results are not in the table yet, then they are added
				else {
					System.out.println("ResultsService, line 195:  student results not yet in table");
					addStudentAssignmentResults(assignFactIds.get(t));
					item = getStudentAssignmentResultsByAssignFactId(assignFactIds.get(t));
				}
				System.out.println("ResultsService, line 200:  item = " + item);
				results.add(item);
			}
			
		}catch(Exception e){
			System.out.println(e.getCause());
			return new ArrayList<EntityStudentAssignmentResultsModel>();
		}
			
		return results;
		
	}
		
		/**
		 * This method will calculate a student's result for an assignment (assumes assignment is completed)
		 * It is called by JsonServlet for practice exercises to calculate immediate results
		 * Otherwise, it is called by addStudentResults call in 'getListOfAssignmentResultsByStudentId'
		 * @param Integer assignFactId
		 * @return EntityStudentAssignmentResultsModel, or null
		 */
		public EntityStudentAssignmentResultsModel calculateStudentAssignmentResults(Integer assignFactId){
			System.out.println("ResultsService private calculateStudentAssignmentResults, assignFactId = "
					+ assignFactId);
			
			//check if results have already been calculated
			List<StudentAssignmentResultsModel> studentResultsByAssignFactList = 
						studentAssignmentResultsRepository.getListOfStudentAssignmentResultsByAssignFactId(assignFactId);
			EntityStudentAssignmentResultsModel studentResultsModel = null;
			
			//if results have not been calculated, calculate them
			if(studentResultsByAssignFactList.size() < 1){
			
				int rightAnswerCount = 0;			
				int totallistlen = 0;
				int totalrightanswercount = 0;
				int proficiency1count = 0;
				int proficiency2count = 0;
				int proficiency3count = 0;
				int proficiency4count = 0;
				int proficiency1right = 0;
				int proficiency2right = 0;
				int proficiency3right = 0;
				int proficiency4right = 0;
			
				AssignmentFactModel assignmentFactModel = 
						assignmentFactRepository.findAssignmentFactById(assignFactId);
				AssignmentModel assignmentModel = 
						assignmentRepository.findAssignmentById(assignmentFactModel.getAssignmentId());
				int exerid = assignmentModel.getExerciseId();
				List<QuestionModel> questionList = questionRepository.getListOfQuestionByExerID(exerid);
				
				int listlen = questionList.size();			
				
				//for each question in exercise:
				for (int i = 0; i < listlen; i++) {  
					QuestionModel questionModel = questionList.get(i);
					int proficiencyid = 3;
					if (questionModel.getProficiencyindex() != null) {
						proficiencyid = questionModel.getProficiencyindex();
					}
					else {
						if(questionModel.getQuestiontype().equals("Latin Parsing") || 
								questionModel.getQuestiontype().equals("Greek Parsing")){
							proficiencyid = 2;
						}
					}
					//get list of correct answers for this question (multiple for parsing, multiple select)
					List<AnswerModel> correctanswerList = 
							answerRepository.getListOfCorrectAnswersByQuestionId(questionModel.getId());
					//get list of student answers for this question (multiple for parsing, multiple select)
					List<StudentAnswerModel> studentanswerList = 
							studentAnswerRepository.getListOfStudentAnswersByQuestionId(questionModel.getId(), 
									assignFactId);
					int anslistlen = correctanswerList.size();
					int studentanswerlistlen = studentanswerList.size();
					List<String> correctansStrList = new ArrayList<String>();
					for(int m = 0; m < anslistlen; m++) {
						correctansStrList.add(correctanswerList.get(m).getAnswer());
					}
					
					//get Multiple select questions for special handling
					if(questionModel.getQuestiontype().equals("Multiple select")) {
						int correctThisquestion = 0;
						for(int k = 0; k < studentanswerlistlen; k++) {
							if(correctansStrList.contains(studentanswerList.get(k).getStudentAnswer())) {
								rightAnswerCount++;
								totallistlen++;
								correctThisquestion++;
								if(proficiencyid == 1){
									proficiency1right++;
									proficiency1count++;
								}
								else if(proficiencyid == 2){
									proficiency2right++;;
									proficiency2count++;
								}
								else if(proficiencyid == 3){
									proficiency3right++;;
									proficiency3count++;
								}
								else if(proficiencyid == 4){
									proficiency4right++;;
									proficiency4count++;
								}
							}
						} 
						int diff = studentanswerlistlen - correctThisquestion;
						if(diff > 0){
							totallistlen = totallistlen + diff;
						}
						if(proficiencyid == 1){
							int prof1diff = studentanswerlistlen - proficiency1right;
							if(prof1diff > 0){
								proficiency1count = proficiency1count + prof1diff;
							}
						}
						else if(proficiencyid == 2){
							int prof2diff = studentanswerlistlen - proficiency2right;
							if(prof2diff > 0){
								proficiency2count = proficiency2count + prof2diff;
							}
						}
						else if(proficiencyid == 3){
							int prof3diff = studentanswerlistlen - proficiency3right;
							if(prof3diff > 0){
								proficiency3count = proficiency3count + prof3diff;
							}
						}
						else if(proficiencyid == 4){
							int prof4diff = studentanswerlistlen - proficiency4right;
							if(prof4diff > 0){
								proficiency4count = proficiency4count + prof4diff;
							}
						}
						System.out.println("line 477:  rightAnswerCount, totallistlen = " + rightAnswerCount + ", " 
								+ totallistlen);
					}  //end multiple select
					
					else {  //else == not Multiple select
					
					for (int j = 0; j < anslistlen; j++) {
						
						if(proficiencyid == 1){
							proficiency1count++;
						}
						else if(proficiencyid == 2){
							proficiency2count++;
						}
						else if(proficiencyid == 3){
							proficiency3count++;
						}
						else if(proficiencyid == 4){
							proficiency4count++;
						}
						
						//in cases where the correct answer is not null
						if (correctanswerList.get(j) != null && j < studentanswerList.size()) {
							System.out.println("correctanswerList.get(j).getAnswer()" 
									+ correctanswerList.get(j).getAnswer());
							
							//check for alternative answers, e.g., 'ei,ii' or 'eis, iis'
							String[] correctansArr = correctanswerList.get(j).getAnswer().split(",");
							if(correctansArr.length > 1){
								System.out.println("correctansArr[0], correctansArr[1] = " + correctansArr[0] +
									", " + correctansArr[1]);
								
								if(correctanswerList.get(j).getAnswer().contains(studentanswerList.
										get(j).getStudentAnswer())){
									System.out.println("correctAnswerList.get(j).getAnwswer(0) "
											+ "contains studentanswer");
									totallistlen++;
									rightAnswerCount++;
									if(proficiencyid == 1){
										proficiency1right++;
									}
									else if(proficiencyid == 2){
										proficiency2right++;
									}
									else if(proficiencyid == 3){
										proficiency3right++;
									}
									else if(proficiencyid == 4){
										proficiency4right++;
									}										
								}//end of if contains						
								else {
									totallistlen++;
								}
							} //end of if arr.length > 1
							else {
								if (j < studentanswerList.size() && 
										correctanswerList.get(j).getAnswer().equals(studentanswerList.
												get(j).getStudentAnswer())) {
									totallistlen++;
									rightAnswerCount++;
									if(proficiencyid == 1){
										proficiency1right++;
									}
									else if(proficiencyid == 2){
										proficiency2right++;
									}
									else if(proficiencyid == 3){
										proficiency3right++;
									}
									else if(proficiencyid == 4){
										proficiency4right++;
									}
								}	//end of if equals					
								else {
									totallistlen++;
								}
							} //end of else (arr.length not > 1)
						} // end of if answerList.get(j) not null
					}  //end of for j
							
					}  //end of else (not multiple select)
					totalrightanswercount = totalrightanswercount + rightAnswerCount;
					
					
				} //end of for each question
				
					System.out.println("ResultsService, line 387:  totallistlen, rightAnswerCount, proficiency1count, " + 
							"proficiency1right = " + totallistlen + ", " + rightAnswerCount + ", " + proficiency1count + 
							", " + proficiency1right);
					System.out.println("ResultsService, line 390:  totallistlen, rightAnswerCount, proficiency2count, " + 
							"proficiency2right = " + totallistlen + ", " + rightAnswerCount + ", " + proficiency2count + 
							", " + proficiency2right);
					System.out.println("ResultsService, line 393  totallistlen, rightAnswerCount, proficiency3count, " + 
							"proficiency3right = " + totallistlen + ", " + rightAnswerCount + ", " + proficiency3count + 
							", " + proficiency3right);
					System.out.println("ResultsService, line 396:  totallistlen, rightAnswerCount, proficiency4count, " + 
							"proficiency4right = " + totallistlen + ", " + rightAnswerCount + ", " + proficiency4count + 
							", " + proficiency4right);
	
				// final calculations are made
				float percent = 0;
				int total = totallistlen;
				float inflectpct = 0;
				float syntaxpct = 0;
				float vocabpct = 0;
				float comprehenpct = 0;
				
	
				if (proficiency1right > 0) {
					vocabpct = (float)(proficiency1right * 100)/proficiency1count;
				}
				if (proficiency2right > 0) {
					inflectpct = (float)(proficiency2right * 100)/proficiency2count;
				}
				if (proficiency3right > 0) {
					syntaxpct = (float)(proficiency3right * 100)/proficiency3count;
				}
				if (proficiency4right > 0) {
					comprehenpct = (float)(proficiency4right * 100)/proficiency4count;
				}
				
				System.out.println("ResultsService, line 546:  totallistlen, rightAnswerCount = " 
						+ totallistlen + ", " + rightAnswerCount);
				if(totallistlen > 0) { // protect against division by zero
					percent = (rightAnswerCount * 100)/totallistlen;
				}
				
				studentResultsModel = 
						(EntityStudentAssignmentResultsModel) modelFactory.getNewStudentAssignmentResultsModel();
				studentResultsModel.setAssignmentFactId(assignFactId);
				studentResultsModel.setCorrectNumber(rightAnswerCount);
				studentResultsModel.setPercent(percent);
				studentResultsModel.setTotal(total);
				studentResultsModel.setInflectcnt(proficiency2count);
				studentResultsModel.setInflectright(proficiency2right);
				studentResultsModel.setInflectpct(inflectpct);
				studentResultsModel.setSyntaxcnt(proficiency3count);
				studentResultsModel.setSyntaxright(proficiency3right);
				studentResultsModel.setSyntaxpct(syntaxpct);
				studentResultsModel.setVocabcnt(proficiency1count);
				studentResultsModel.setVocabright(proficiency1right);
				studentResultsModel.setVocabpct(vocabpct);
				studentResultsModel.setComprehencnt(proficiency4count);
				studentResultsModel.setComprehenright(proficiency4right);
				studentResultsModel.setComprehenpct(comprehenpct);
			} //end of if calculated results do not exist
			else {
				studentResultsModel = 
						studentAssignmentResultsRepository.
							getStudentAssignmentResultsByAssignFactId(assignFactId);
			}
			return studentResultsModel;	
		}
	
	
	/*
	 * Methods below all pertain to aggregating the student results per assignment
	 */
	
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
		
		List<AnswerModel> answerList = answerRepository.getListOfAnswersByQuestionId(questionId);
		List<StudentAnswerModel> studentAnswerList = 
				studentAnswerRepository.getListOfStudentAnswersByQuestionId(questionId,assignFactId);
		int listlen = answerList.size();
		int correctanswers = 0;
		
		for (int i = 0; i < listlen; i++) {
			if (answerList.get(i) != null) {
				System.out.println("QuestionService, line 476: answerList.get(i).getAnswer(), "
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
		System.out.println("QuestionService, line 495:  listlen, correctanswers = " + listlen + ", " 
				+ correctanswers);
		if (correctanswers > 0) {
			correctpercent = (listlen/correctanswers) * 100;
		}
		System.out.println("QuestionService, line 500: correctpercent = " + correctpercent);
		return correctpercent;
		
	}
	
	/**
	 * Adds results of one class' assignment to class assignment results table and checks to ensure that the 
	 * assignment is marked as 'COMPLETED'
	 * @param Integer
	 * @return void
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void addClassAssignmentResults(Integer assignId) 
			throws EntityAlreadyExistsException, ServiceException {
		System.out.println("ResultsService addClassAssignmentResults");
		
		// throws an exception if the assignment is not marked as complete
		if(!assignmentService.findAssignmentById(assignId).getStatus().equals("COMPLETED")){
			throw new ServiceException();
		}
		
		// calculates the aggregate results for the assignment 
		ClassAssignmentResultsModel resultsModel = calculateClassAssignmentResults(assignId);
		if(resultsModel==null){
			System.out.println("Class assignment results were unable to be calculated");
		}
		
		try{
			classAssignmentResultsRepository.addClassAssignmentResults(resultsModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * Retrieves a record of one assignment's results for a class
	 * @param int
	 * @return EntityClassAssignmentResultsModel, or null
	 */
	public EntityClassAssignmentResultsModel getClassAssignmentResultsByAssignId(int assignId) {		
		return classAssignmentResultsRepository.getClassAssignmentResultsByAssignId(assignId);		
	}
	
	/**
	 * Retrieves all of the class' assignment results
	 * If a class assignment's aggregate results with a 'COMPLETED' status is not in the database,
	 *   then this method invokes the add method to create a new record in the table
	 * @param int classId
	 * @return list of class' assignment results, or blank list if no results found
	 */
	public List<EntityClassAssignmentResultsModel> getListOfClassAssignmentResultsByClassId(int classId){
		System.out.println("ResultsService: getListOfClassAssignmentResultsByClassId()");
		
		// compiling a list of all the assignment ids for a class
		List<Integer> classAssignmentIds = assignmentService.getListOfAssignmentIdsByClassId(classId);
		if(classAssignmentIds.isEmpty()){
			return new ArrayList<EntityClassAssignmentResultsModel>();
		}
		System.out.println("ResultsService, line 616: classAssignmentIds.size(): " + classAssignmentIds.size());
		
		// traverses through all assignments for the class
		// adds completed assignments to 'completedClassAssignments'
		List<Integer> completedClassAssignmentIds = new ArrayList<Integer>();
		for(int i = 0; i < classAssignmentIds.size(); i++){
			Integer item = classAssignmentIds.get(i);
			System.out.println("ResultsService, line 623: assignmentService.findAssignmentById(item).getStatus() = " +
					assignmentService.findAssignmentById(item).getStatus());
			try{
				if(assignmentService.findAssignmentById(item).getStatus().equals("COMPLETED")){
					System.out.println("ResultsService, line 627: In if");
					completedClassAssignmentIds.add(item);
				}
			}catch(Exception e){
				System.out.println("ResultsService getListOfClassAssignmentResultsByClassId: " +
						e.getCause());
			}

		}
		
		// traverses through list of completed assignment ids and adds each of the class result records to 'results'	
		List<EntityClassAssignmentResultsModel> results = new ArrayList<EntityClassAssignmentResultsModel>();
		System.out.println("ResultsService, lin 639: completedClassAssignmentIds.size() = " +
				completedClassAssignmentIds.size());
		
		try{
			//for(Integer assignId: completedClassAssignmentIds){
			for(int i = 0; i < completedClassAssignmentIds.size(); i++){
				int assignid = completedClassAssignmentIds.get(i);
				System.out.println("ResultsService, line 645:  completedClassAssignmentIds.size(), i, assignid = " + 
						completedClassAssignmentIds.size() + ", " + i + ", " + assignid);
				EntityClassAssignmentResultsModel item = 
						getClassAssignmentResultsByAssignId(assignid);
				
				// if assignment's results are not in the table yet, then they are added
				if(item==null){
					addClassAssignmentResults(assignid);
					item = getClassAssignmentResultsByAssignId(assignid);
				}
				results.add(item);
			}
			
		}catch(Exception e){
			System.out.println(e.getCause());
			return new ArrayList<EntityClassAssignmentResultsModel>();
		}
			
		return results;
		
	}
	
	
	/**
	 * This method will calculate a class' result for an assignment
	 * Assumes this assignment has a "COMPLETED" status
	 * @param Integer assignId
	 * @return EntityClassAssignmentResultsModel, or null
	 */
	private ClassAssignmentResultsModel calculateClassAssignmentResults(Integer assignId){
		System.out.println("ResultsService, line 671: calculateClassAssignmentResults");
		
		// retrieving collection of assignment facts for given assignment
		List<EntityAssignmentFactModel> assignFactList = 
				assignmentFactService.getListOfAssignmentFactByAssignmentId(assignId);
		if(assignFactList==null){
			return null;
		}
		
		// variables for calculations
		Integer studentCount = 0;
		Float rightAnswerCount = (float) 0.0;
		Float total = (float) 0.0;
		int inflecttotal = 0;
		int inflectright = 0;
		int syntaxtotal = 0;
		int syntaxright = 0;
		int vocabtotal = 0;
		int vocabright = 0;
		int comprehentotal = 0;
		int comprehenright = 0;
		
		// information from each assignment fact (assumes only one per student per assignment fact)
		//  is retrieved and used for aggregate calculations
		//  if the student's assignment results have not been calculated yet, then they are calculated here
		System.out.println("ResultsService, line 696:  assignFactList.size() = " + assignFactList.size());
		//for(EntityAssignmentFactModel assignFact: assignFactList){
		for(int j = 0; j < assignFactList.size(); j++){
			int assignmentId = assignFactList.get(j).getId();
			EntityStudentAssignmentResultsModel studentResultsModel = 
					getStudentAssignmentResultsByAssignFactId(assignmentId);
			if(studentResultsModel==null){
				System.out.println("ResultsService, line 699:  studentResultsModel is null");
				addStudentAssignmentResults(assignmentId);
				studentResultsModel = getStudentAssignmentResultsByAssignFactId(assignmentId);
			}
			else {
				System.out.println("ResultsService, line 705:  studentResultsModel is not null");
				System.out.println("ResultsService, line 707:  studentResultsModel.getCorrectNumber() = " + 
						studentResultsModel.getCorrectNumber());
			}
			
			if(studentResultsModel!=null){				
				studentCount++;
				rightAnswerCount += studentResultsModel.getCorrectNumber();
				System.out.println("ResultsService, line 719:  not null, studentCount, rightAnswerCount = "+ 
				studentCount + ", " + rightAnswerCount);
				
				total += studentResultsModel.getTotal();				
				inflecttotal = inflecttotal + studentResultsModel.getInflectcnt();
				inflectright = inflectright + studentResultsModel.getInflectright();
				syntaxtotal = syntaxtotal + studentResultsModel.getSyntaxcnt();
				syntaxright = syntaxright + studentResultsModel.getSyntaxright();
				vocabtotal = vocabtotal + studentResultsModel.getVocabcnt();
				vocabright = vocabright + studentResultsModel.getVocabright();
				System.out.println("ResultsService, line 731:  studentResultsModel.getComprehencnt() = "
						+ studentResultsModel.getComprehencnt());				
				comprehentotal = comprehentotal + studentResultsModel.getComprehencnt();
				comprehenright = comprehenright + studentResultsModel.getComprehenright();
				
			}
			
		}
		System.out.println("ResultsService, line 738:  inflecttotal, inflectright = "
				+ inflecttotal + ", " + inflectright);

		// final calculations are made
		float average = 0;
		if(rightAnswerCount > 0) {
			average = (float)((rightAnswerCount)*100)/total;
		}
		
		System.out.println("ResultsService, line 728:  inflecttotal, inflectright = " + inflecttotal + ", " + 
				inflectright);
		
		ClassAssignmentResultsModel classAssignmentResultsModel = 
				modelFactory.getNewClassAssignmentResultsModel();
		
		classAssignmentResultsModel.setAssignmentId(assignId);
		classAssignmentResultsModel.setStudentCount(studentCount);
		classAssignmentResultsModel.setTotal(total);
		classAssignmentResultsModel.setAverage(average);
		classAssignmentResultsModel.setInflecttotal(inflecttotal);
		classAssignmentResultsModel.setInflectright(inflectright);
		classAssignmentResultsModel.setSyntaxtotal(syntaxtotal);
		classAssignmentResultsModel.setSyntaxright(syntaxright);
		classAssignmentResultsModel.setVocabtotal(vocabtotal);
		classAssignmentResultsModel.setVocabright(vocabright);
		classAssignmentResultsModel.setComprehentotal(comprehentotal);
		classAssignmentResultsModel.setComprehenright(comprehenright);
		
		return classAssignmentResultsModel;	
	}

	/**
	 * Adds results of one teacher's result to teacher assignment results table
	 * @param institutionId
	 * @param teacherId
	 * @param now Current date
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	private void addTeacherAssignmentResults(Integer institutionId, Integer teacherId, Date now) 
			throws EntityAlreadyExistsException, ServiceException
	{
		System.out.println("ResultsService addTeacherAssignmentResults");
	
		// calculates the aggregate results for the assignment 
		TeacherAssignmentResultsModel resultsModel = calculateTeacherAssignmentResults(institutionId, teacherId, now);
		if(resultsModel==null){
			System.out.println("Teacher assignment results were unable to be calculated");
			return;
		}
		
		try{
			teacherAssignmentResultsRepository.addTeacherAssignmentResults(resultsModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Updates results of one teacher's result to teacher assignment results table
	 * @param institutionId
	 * @param teacherId
	 * @param now
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void updateTeacherAssignmentResults(Integer institutionId, Integer teacherId, Date now) 
			throws EntityAlreadyExistsException, ServiceException
	{
		System.out.println("ResultsService updateTeacherAssignmentResults");

		// calculates the aggregate results for the assignment 
		try{
			TeacherAssignmentResultsModel resultsModel 
				= getTeacherAssignmentResultsByTeacherId(teacherId);
			if(resultsModel==null){
				System.out.println("Teacher assignment results is null");
				addTeacherAssignmentResults(institutionId, teacherId, now);
			}
			else {
				System.out.println("ResultsService, line 796:  teacherId, resultsModel.getId() = " + 
						teacherId + ", " + resultsModel.getId());
				resultsModel = calculateTeacherAssignmentResults(institutionId, teacherId, now);
				if(resultsModel==null) {
					System.out.println("Teacher assignment results were unable to be calculated");
				}
				else {
					teacherAssignmentResultsRepository.updateTeacherAssignmentResultsByTeacherId(teacherId, 
							resultsModel);
				}
			}
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	
	}
	
	/**
	 * This method will calculate a teacher's result
	 * @param Integer assignId
	 * @return TeacherAssignmentResultsModel, or null
	 */
	private TeacherAssignmentResultsModel calculateTeacherAssignmentResults(Integer institutionId, Integer teacherId, Date now)
	{
		System.out.println("ResultsService calculateTeacherAssignmentResults");
		
		// get all classes of the teacher
		List<EntityClassModel> classlist = classService.getCurrentClassesForUser(institutionId, teacherId, "TEACHER", now);
		
		// calculate the student count for the teacher
		int classlistlen = classlist.size();
		int studentCount = 0;
		int classID = 0;
		List<Integer> studentidlist = new ArrayList<Integer>();
		List<EntityClassAssignmentResultsModel> teacherClassAssignresultsList 
			= new ArrayList<EntityClassAssignmentResultsModel>();
		for (int i = 0; i < classlistlen; i++) {
			classID = classlist.get(i).getId();
			studentidlist = classService.getListOfUserIdsInAClassByUserType(classID, "STUDENT");
			studentCount += studentidlist.size();
			
			teacherClassAssignresultsList.addAll(getListOfClassAssignmentResultsByClassId(classID));
		}
		System.out.println("ResultsService, line 846:  teacherClassAssignesultsList.size() = " +
				teacherClassAssignresultsList.size());
		
		// get the list of assignments for the teacher and calculate the average 
		float average = 0;
		Float total = (float) 0.0;
		int inflecttotal = 0;
		int inflectright = 0;
		int syntaxtotal = 0;
		int syntaxright = 0;
		int vocabtotal = 0;
		int vocabright = 0;
		int comprehentotal = 0;
		int comprehenright = 0;
		
		int assignlen = teacherClassAssignresultsList.size();
		if (assignlen > 0) {
			for (int i = 0; i < assignlen; i++) {
				total += teacherClassAssignresultsList.get(i).getAverage();
				inflecttotal = inflecttotal + teacherClassAssignresultsList.get(i).getInflecttotal();
				inflectright = inflectright + teacherClassAssignresultsList.get(i).getInflectright();
				syntaxtotal = syntaxtotal + teacherClassAssignresultsList.get(i).getSyntaxtotal();
				syntaxright = syntaxright + teacherClassAssignresultsList.get(i).getSyntaxright();
				vocabtotal = vocabtotal + teacherClassAssignresultsList.get(i).getVocabtotal();
				vocabright = vocabright + teacherClassAssignresultsList.get(i).getVocabright();
				comprehentotal = comprehentotal + teacherClassAssignresultsList.get(i).getComprehentotal();
				comprehenright = comprehenright + teacherClassAssignresultsList.get(i).getComprehenright();
			}
			
			// final calculations are made
			average = total/assignlen;			
		}

		TeacherAssignmentResultsModel teacherAssignmentResultsModel = null;
		if(teacherAssignmentResultsRepository.getTeacherAssignmentResultsByTeacherId(teacherId) != null){
			 teacherAssignmentResultsModel = 
					 teacherAssignmentResultsRepository.getTeacherAssignmentResultsByTeacherId(teacherId);
		}
		else {
			teacherAssignmentResultsModel = modelFactory.getNewTeacherResultsModel();
		}				
				
		teacherAssignmentResultsModel.setTeacherId(teacherId);
		teacherAssignmentResultsModel.setStudentCount(studentCount);
		teacherAssignmentResultsModel.setAverage(average);
		teacherAssignmentResultsModel.setInflecttotal(inflecttotal);
		teacherAssignmentResultsModel.setInflectright(inflectright);
		teacherAssignmentResultsModel.setSyntaxtotal(syntaxtotal);
		teacherAssignmentResultsModel.setSyntaxright(syntaxright);
		teacherAssignmentResultsModel.setVocabtotal(vocabtotal);
		teacherAssignmentResultsModel.setVocabright(vocabright);
		teacherAssignmentResultsModel.setComprehentotal(comprehentotal);
		teacherAssignmentResultsModel.setComprehenright(comprehenright);
		
		return teacherAssignmentResultsModel;
	}

	/**
	 * Adds results of one teacher's previous result to teacher_pre_results table
	 * @param Integer
	 * @return void
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void addTeacherPreResults(Integer institutionId, Integer teacherId, Date now, Integer nPriorTerms) 
			throws EntityAlreadyExistsException, ServiceException {
		System.out.println("ResultsService addTeacherPreResults");
		

		// calculates the aggregate results for the assignment 
		TeacherPreResultsModel resultsModel = calculateTeacherPreResults(institutionId, teacherId, now, nPriorTerms);
		if(resultsModel==null){
			System.out.println("Teacher pre assignment results were unable to be calculated");
			return;
		}
		try{
			teacherPreResultsRepository.addTeacherPreResults(resultsModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * Updates results of one teacher's previous result to teacher_pre_results table
	 * @param Integer
	 * @return void
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void updateTeacherPreResults(Integer institutionId, Integer teacherId, Date now, Integer nPriorTerms) 
			throws EntityAlreadyExistsException, ServiceException {
		System.out.println("ResultsService updateTeacherPreResults");
		

		// calculates the aggregate results for the assignment 
		try{
			TeacherPreResultsModel resultsModel 
				= getTeacherPreResultsByTeacherId(teacherId);
			if(resultsModel==null){
				System.out.println("Teacher pre assignment results is null");
				addTeacherPreResults(institutionId, teacherId, now, nPriorTerms);
			}
			else {
				resultsModel = calculateTeacherPreResults(institutionId, teacherId, now, nPriorTerms);
				if(resultsModel==null)
					System.out.println("Teacher pre assignment results were unable to be calculated");
				else
					teacherPreResultsRepository.updateTeacherPreResultsByTeacherId(teacherId, resultsModel);
			}

		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * Retrieves a record of one teacher's results
	 * @param int
	 * @return TeacherAssignmentResultsModel, or null
	 */
	public TeacherAssignmentResultsModel getTeacherAssignmentResultsByTeacherId(int teacherId) {		
		return teacherAssignmentResultsRepository.getTeacherAssignmentResultsByTeacherId(teacherId);
	}

	/**
	 * Retrieves a record of one teacher's previous results
	 * @param int
	 * @return TeacherPreResultsModel, or null
	 */
	public TeacherPreResultsModel getTeacherPreResultsByTeacherId(int teacherId) {
		return teacherPreResultsRepository.getTeacherPreResultsByTeacherId(teacherId);
	}
	
	/**
	 * This method will calculate a teacher's previous result
	 * @param Integer teacherId
	 * @return TeacherPreResultsModel, or null
	 */
	private TeacherPreResultsModel calculateTeacherPreResults(Integer institutionId, Integer teacherId, Date now, Integer nPriorTerms)
	{
		System.out.println("ResultsService private calculateTeacherPreResults");
		
		// get all classes of the teacher
		List<EntityClassModel> classlist = classService.getPriorClassesForUser(institutionId, teacherId, "TEACHER", now, nPriorTerms);
		
		// calculate the student count for the teacher
		int classlistlen = classlist.size();
		int studentCount = 0;
		int classID = 0;
		List<Integer> studentidlist = new ArrayList<Integer>();
		List<EntityClassAssignmentResultsModel> teacherAssignresultsList 
			= new ArrayList<EntityClassAssignmentResultsModel>();
		for (int i = 0; i < classlistlen; i++) {
			classID = classlist.get(i).getId();
			studentidlist = classService.getListOfUserIdsInAClassByUserType(classID, "STUDENT");
			studentCount += studentidlist.size();
			
			teacherAssignresultsList.addAll(getListOfClassAssignmentResultsByClassId(classID));
		}
		
		// get the list of assignments for the teacher and calculate the average 
		float average = 0;
		float total = 0;
		int inflecttotal = 0;
		int inflectright = 0;
		int syntaxtotal = 0;
		int syntaxright = 0;
		int vocabtotal = 0;
		int vocabright = 0;
		int comprehentotal = 0;
		int comprehenright = 0;
		
		int assignlen = teacherAssignresultsList.size();
		if(assignlen != 0) {
			for (int i = 0; i < assignlen; i++) {
				total += teacherAssignresultsList.get(i).getAverage();
				inflecttotal += teacherAssignresultsList.get(i).getInflecttotal();
				inflectright += teacherAssignresultsList.get(i).getInflectright();
				syntaxtotal += teacherAssignresultsList.get(i).getSyntaxtotal();
				syntaxright += teacherAssignresultsList.get(i).getSyntaxright();
				vocabtotal += teacherAssignresultsList.get(i).getVocabtotal();
				vocabright += teacherAssignresultsList.get(i).getVocabright();
				comprehentotal += teacherAssignresultsList.get(i).getComprehentotal();
				comprehenright += teacherAssignresultsList.get(i).getComprehenright();
			}
			
			// final calculations are made
			average = total/assignlen;
		}
		
		TeacherPreResultsModel teacherPreResultsModel = modelFactory.getNewTeacherPreResultsModel();
		teacherPreResultsModel.setTeacherId(teacherId);
		teacherPreResultsModel.setStudentCount(studentCount);
		teacherPreResultsModel.setAverage(average);
		teacherPreResultsModel.setInflecttotal(inflecttotal);
		teacherPreResultsModel.setInflectright(inflectright);
		teacherPreResultsModel.setSyntaxtotal(syntaxtotal);
		teacherPreResultsModel.setSyntaxright(syntaxright);
		teacherPreResultsModel.setVocabtotal(vocabtotal);
		teacherPreResultsModel.setVocabright(vocabright);
		teacherPreResultsModel.setComprehentotal(comprehentotal);
		teacherPreResultsModel.setComprehenright(comprehenright);
		Calendar todaysDate = Calendar.getInstance();
		Date calculatedate = new java.sql.Date(todaysDate.getTime().getTime());
		teacherPreResultsModel.setCalculatedate(calculatedate);
		
		return teacherPreResultsModel;
	}

}
