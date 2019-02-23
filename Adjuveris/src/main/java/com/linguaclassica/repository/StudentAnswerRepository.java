package com.linguaclassica.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityStudentAnswerModel;
import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.model.StudentAnswerModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class StudentAnswerRepository {

	@PersistenceContext
	private EntityManager entityManager;
	

	/*
	 * This method creates a question. If no exceptions are thrown the method is successful.
	 * 
	 * @param studentAnswerModel
	 * @throws PersistenceException if an unspecified error occurs.
	 */
	public void addStudentAnswerData(StudentAnswerModel studentAnswerModel,Integer questionAnswerCount,
			QuestionModel questionModel) {
			System.out.println("StudentAnswerRepository, line 37:  studentAnswerModel.getAssignmentFactId() = " +
				studentAnswerModel.getAssignmentFactId());
			try {
				entityManager.merge(studentAnswerModel);
			} catch (PersistenceException e) {			
				throw e;
			}
	}
	
	@SuppressWarnings("unchecked")
	public List<EntityStudentAnswerModel> getStudentAnswerList(Integer assignmentfactid, 
			Integer questionid) {

		try {
			Query query = entityManager.createQuery("SELECT sa FROM EntityStudentAnswerModel sa "
					+ "WHERE sa.assignmentfactid = :assignmentfactid AND sa.questionid = :questionid");
			query.setParameter("assignmentfactid", assignmentfactid);
			query.setParameter("questionid", questionid);			
			return query.getResultList();
		} catch (PersistenceException e) {			
			throw e;
		}
	}
	
	/**
	 * Get a list of student answers with questions
	 * @param assignfactid
	 * @return list of student answers with questions
	 */
	@SuppressWarnings("unchecked")
	public List<StudentAnswerModel> getListOfStudentAnswerByAssignFactID(int assignfactid) {
		try {
			Query query = entityManager.createQuery(
				"SELECT sa FROM EntityStudentAnswerModel sa where sa.assignmentfactid = :assignmentfactid");
			
			query.setParameter("assignmentfactid",assignfactid);
			
			List<StudentAnswerModel> studentanswerList = new ArrayList<StudentAnswerModel>();
	
			studentanswerList = (List<StudentAnswerModel>) query.getResultList();
			System.out.println("StudentAnswerRepository, line 77: studentanswerList.size() = " + 
					studentanswerList.size());
			return studentanswerList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Get a list of student answers by AssignmentFactId and QuestionId 
	 * @param assignfactid, questionid
	 * @return list of student answers 
	 */
	@SuppressWarnings("unchecked")
	public List<StudentAnswerModel> getListByAssignFactAndQuestionIDs(int assignfactid, int questionid) {
		try {
			Query query = entityManager.createQuery(
				"SELECT sa FROM EntityStudentAnswerModel sa where sa.assignmentfactid = :assignmentfactid AND "
				+ "sa.questionid = :questionid");
			
			query.setParameter("assignmentfactid",assignfactid);
			query.setParameter("questionid",questionid);
			
			List<StudentAnswerModel> studentanswerList = new ArrayList<StudentAnswerModel>();
	
			studentanswerList = (List<StudentAnswerModel>) query.getResultList();
			System.out.println("StudentAnswerRepository, line 106: studentanswerList.size() = " + 
					studentanswerList.size());
			return studentanswerList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
	/**
	 * Get a question by it's id
	 * @param assignmentfactid, questionid
	 * @return EntityStudentAnswerModel
	 */
	public EntityStudentAnswerModel findStudentAnswerByAssignmentFactQuestionId(
			Integer assignmentfactid, Integer questionid) {	
		/*
		System.out.println("StudentAnswerRepository, line 110:  assignmentfactid, questionid = " +
			assignmentfactid + ", " + questionid);
			*/
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT af from EntityStudentAnswerModel af where " + 
					"af.assignmentfactid = :assignmentfactid AND af.questionid = :questionid");
			query.setParameter("assignmentfactid", assignmentfactid);
			query.setParameter("questionid", questionid);
			EntityStudentAnswerModel studentanwserModel = (EntityStudentAnswerModel) query.getSingleResult();
			
			System.out.println("StudentAnswerRepository.findStudentAnswerByQuestionId");
			
			return studentanwserModel;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}	
	
	/*
	 * @param questiongroupid
	 * @return List of answers
	 */
	@SuppressWarnings("unchecked")
	public List<StudentAnswerModel> getListOfStudentAnswersByQuestionId(Integer questId,Integer assignFactIdIn) {
		System.out.println("questId, assignFactIdIn = " + questId + ", " + assignFactIdIn);
		
		List<StudentAnswerModel> answerList = new ArrayList<StudentAnswerModel>();
		
	    String qryString="SELECT ans FROM EntityStudentAnswerModel ans WHERE ans.questionid = "
	    		+ "(:questId) AND ans.assignmentfactid = :assignFactId ORDER BY ans.id ASC";
	    
	    try {
			Query query = entityManager.createQuery(qryString);
			query.setParameter("questId", questId);
			query.setParameter("assignFactId", assignFactIdIn);
			answerList = (List<StudentAnswerModel>) query.getResultList();
			System.out.println("StudentAnswerRepository, line 161:  questId, answerList.size() = " 
					+ questId + ", " + answerList.size());
			return answerList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}	
	
	/*
	 * @param questiongroupid
	 * @return List of answers
	 */
	@SuppressWarnings("unchecked")
	public List<StudentAnswerModel> getListOfStudentAnswersByQuestionIdList(List<Integer> qidlist) {
	    String qryString;
	    qryString="SELECT sa FROM EntityStudentAnswerModel sa WHERE sa.questionid IN (:qidlist) "
	    		+ "ORDER BY sa.questionid ASC";
	    
	    try {
			Query query = entityManager.createQuery(qryString);
			query.setParameter("qidlist", qidlist);
			List<StudentAnswerModel> studentAnsList = (List<StudentAnswerModel>) query.getResultList();
			return studentAnsList;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
		
	/*
	 * @param StudentAnswerModel
	 * @remove student answer
	 */	
	public void removeStudentAnswer(StudentAnswerModel studentAnswerModel) {
		try {
			if(studentAnswerModel != null){
				entityManager.remove(studentAnswerModel);
	        }			
			return;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}		
	}
	
	public void deleteStudentAnswer(StudentAnswerModel studentAnswerModel) 
			throws NoResultException, PersistenceException {	
		
		try {
			System.out.println("StudentAnswerRepository, line 198:  studentAnswerModel.getId() = " + 
					studentAnswerModel.getId());
			Query query = null;
			query = entityManager.createQuery("SELECT sa from EntityStudentAnswerModel sa where sa.id = :id " );
			query.setParameter("id", studentAnswerModel.getId());
			
			@SuppressWarnings("unchecked")
			List<EntityStudentAnswerModel> listStudentAnswerModel = 
				(List<EntityStudentAnswerModel>) query.getResultList();
			for (EntityStudentAnswerModel ansModel : listStudentAnswerModel){
				entityManager.remove(ansModel);
			}
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
