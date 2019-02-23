package com.linguaclassica.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityStudentAnswerModel;
import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.model.StudentAnswerModel;
import com.linguaclassica.repository.AnswerRepository;
import com.linguaclassica.repository.QuestionRepository;
import com.linguaclassica.repository.StudentAnswerRepository;
import com.linguaclassica.service.exception.ServiceException;

@Service
@Transactional
public class StudentAnswerService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private StudentAnswerRepository studentAnswerRepository;
	
	@Autowired 
	private AnswerRepository answerRepository;
	
	@Autowired 
	private QuestionRepository questionRepository;
		
	/**
	 * This method creates an question. If no exceptions are thrown the method is successful.
	 * 
	 * @param studentanswerModel
	 * @throws EntityAlreadyExistsException if the user already exists.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	
	public void addStudentAnswerData(StudentAnswerModel studentAnswerModel) throws ServiceException {
		
		int questionId = studentAnswerModel.getQuestionid();
		Integer correctAnswerCount = answerRepository.getCountOfCorrectAnswersByQuestionId(questionId);
		QuestionModel questionModel = questionRepository.findQuestionById(questionId);
		try {
			studentAnswerRepository.addStudentAnswerData((EntityStudentAnswerModel)studentAnswerModel, 
					correctAnswerCount,questionModel);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
/**
 * This method creates an question. If no exceptions are thrown the method is successful.
 * 
 * @param studentAnswerModelList
 * @throws EntityAlreadyExistsException if the user already exists.
 * @throws ServiceException if an unspecified error occurs.
 */

	public void addMultipleStudentAnswer(List<StudentAnswerModel> studentAnswerModelList,
		Integer questionId, Integer assignmentFactId) throws ServiceException {
		
		if (studentAnswerRepository == null) {
			System.out.println("StudentAnswerRepository is null");
		}
		else {
			System.out.println("StudentAnswerRepository not null");
		}
		int questionAnswerCount = answerRepository.getCountOfCorrectAnswersByQuestionId(questionId);
		QuestionModel questionModel = questionRepository.findQuestionById(questionId);
		
		//first, clean out existing records
		List<StudentAnswerModel> preexistingStudentAnswerList = 
				studentAnswerRepository.getListOfStudentAnswersByQuestionId(questionId, assignmentFactId);
		try {
			for(int i = 0; i < preexistingStudentAnswerList.size(); i++) {
				studentAnswerRepository.removeStudentAnswer(preexistingStudentAnswerList.get(i));
			}
			//then, insert student answers from incoming list
			for(int j = 0; j < studentAnswerModelList.size(); j++) {
				System.out.println("StudentAnswerService, line 103:  "
						+ "studentAnswerModelList.size(), studentAnswerModelList.get(j).getStudentanswer() = " + 
						studentAnswerModelList.size() + ", " + studentAnswerModelList.get(j).getStudentAnswer());
				
					studentAnswerRepository.addStudentAnswerData(studentAnswerModelList.get(j), 
							questionAnswerCount, questionModel);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}	
	}
	
	public  List<StudentAnswerModel> getListOfStudentAnswerByAssignFactID(int assignfactid) {
		return studentAnswerRepository.getListOfStudentAnswerByAssignFactID(assignfactid);
	}
	
	public  List<StudentAnswerModel> getListOfStudentAnswersByAssignFactAndQuestGroupIds(int assignfactid, 
			int qgid) {
		
		List<Integer> questionIdList = questionRepository.getListOfQuestionIdsByQuestionGroupId(qgid);
		List<StudentAnswerModel> studentanswerlist = 
				studentAnswerRepository.getListOfStudentAnswersByQuestionIdList(questionIdList);	
		
		return studentanswerlist;
	}
	
	public  List<StudentAnswerModel> getListByAssignFactAndQuestionIDs(int assignfactid, int questionid) {
		return studentAnswerRepository.getListByAssignFactAndQuestionIDs(assignfactid, questionid);
	}
	
	public  List<StudentAnswerModel> getListOfStudentAnswersByQuestionId(int questionid, int assignfactid) {
		return studentAnswerRepository.getListOfStudentAnswersByQuestionId(questionid, assignfactid);
	}

	public EntityStudentAnswerModel findStudentAnswerByAssignmentFactQuestionId(Integer assignmentfactid,Integer questionid) {
		return studentAnswerRepository.findStudentAnswerByAssignmentFactQuestionId(assignmentfactid, questionid);
	}
	
	public void deleteStudentAnswer(StudentAnswerModel studentAnswerModel){
		studentAnswerRepository.deleteStudentAnswer(studentAnswerModel);
	}
	
}
