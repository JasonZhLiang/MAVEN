package com.linguaclassica.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityAnswerModel;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class AnswerRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * This method creates an answer. If no exceptions are thrown the method is successful.
	 * @param answerModel
	 * @throws EntityExistsException
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public int createAnswer(EntityAnswerModel answerModel) 
			throws EntityExistsException, PersistenceException {

		int answerId;
		try {
			entityManager.persist(answerModel);
			entityManager.refresh(answerModel);
			answerId = answerModel.getId();
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		return answerId;
	}
	
	/**
	 * This method is used to find an answer by its questionid.
	 * If successful, the valid AnswerModel is returned.
	 * @param id
	 * @return
	 * @throws NoResultException if the class cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityAnswerModel findCorrectAnswerByQuestionId(int questionId) 
			throws NoResultException, PersistenceException {	
		
		try {
			//System.out.println("AnswerRepository, line 66:  questionId = " + questionId);
			Query query = null;
			query = entityManager.createQuery("SELECT ans from EntityAnswerModel ans where ans.questionid = :questionid AND " +
					"ans.correct = 1");
			query.setParameter("questionid", questionId);
			
			@SuppressWarnings("unchecked")
			List<EntityAnswerModel> listAnswerModel = (List<EntityAnswerModel>) query.getResultList();
			
			EntityAnswerModel answerModel = null;
			
			if (listAnswerModel.size() > 0 ) {
				//System.out.println("AnswerRepository.findAnswerByQuestionId.getAnswer() = " + listAnswerModel.get(0));
				answerModel = listAnswerModel.get(0);
			}
			else if(listAnswerModel.size() < 1) {
				System.out.println("AnswerRepository.findAnswerByQuestionId.getAnswer() = " + "null");
				answerModel = new EntityAnswerModel();
			}
			return answerModel;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method is used to find an answer by its questionid.
	 * If successful, the valid AnswerModel is returned.
	 * @param questionid
	 * @return
	 * @throws NoResultException if the class cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityAnswerModel findAnswerByQuestionId(int questionId) 
			throws NoResultException, PersistenceException {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ans from EntityAnswerModel ans where "
					+ "ans.questionid = :questionid");
			query.setParameter("questionid", questionId);
			
			@SuppressWarnings("unchecked")
			List<EntityAnswerModel> listAnswerModel = (List<EntityAnswerModel>) query.getResultList();			
			EntityAnswerModel answerModel = null;
			
			if (listAnswerModel.size() > 0 ) {
				answerModel = listAnswerModel.get(0);
			}
			return answerModel;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}		
	
	@SuppressWarnings("unchecked")
	public List<EntityAnswerModel> findAnswersByQuestionId(int questionId) 
			throws NoResultException, PersistenceException {	
		
		try {
			
			Query query = null;
			query = entityManager.createQuery("SELECT ans from EntityAnswerModel ans where ans.questionid = :questionid " );
			query.setParameter("questionid", questionId);
			
			return (List<EntityAnswerModel>) query.getResultList();
			
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
	public List<AnswerModel> getListOfAnswersByQuestionIdList(List<Integer> questIdListIn) {
		List<AnswerModel> answerList = new ArrayList<AnswerModel>();
		
	    String qryString="SELECT ans FROM EntityAnswerModel ans WHERE ans.questionid IN (:questIdList) ORDER BY ans.questionid ASC";
	    
	    try {
			Query query = entityManager.createQuery(qryString);
			query.setParameter("questIdList", questIdListIn);
			//answerList = new ArrayList<EntityUserModel>();
			answerList = (List<AnswerModel>) query.getResultList();				
			return answerList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}	
	
	/*
	 * @param questionid
	 * @return List of answers
	 */
	@SuppressWarnings("unchecked")
	public List<AnswerModel> getListOfAnswersByQuestionId(Integer questId) {
		List<AnswerModel> answerList = new ArrayList<AnswerModel>();
		
	    String qryString="SELECT ans FROM EntityAnswerModel ans WHERE ans.questionid = (:questId) ORDER BY ans.id ASC";
	    
	    try {
			Query query = entityManager.createQuery(qryString);
			query.setParameter("questId", questId);
			answerList = (List<AnswerModel>) query.getResultList();				
			return answerList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/*
	 * @param questionid
	 * @return List of answers
	 */
	@SuppressWarnings("unchecked")
	public List<AnswerModel> getListOfCorrectAnswersByQuestionId(Integer questId) {
		List<AnswerModel> answerList = new ArrayList<AnswerModel>();
		
	    String qryString="SELECT ans FROM EntityAnswerModel ans WHERE ans.questionid = (:questId) "
	    		+ "AND ans.correct = 1 ORDER BY ans.id ASC";	    
	    try {
			Query query = entityManager.createQuery(qryString);
			query.setParameter("questId", questId);
			answerList = (List<AnswerModel>) query.getResultList();				
			return answerList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}	
	
	/*
	 * @param questionid
	 * @return List of answers
	 */
	public Integer getCountOfCorrectAnswersByQuestionId(Integer questId) {
		
	    String qryString="SELECT COUNT(ans) FROM EntityAnswerModel ans WHERE ans.questionid = (:questId) "
	    		+ "AND ans.correct = 1";
	    
	    try {
			Query query = entityManager.createQuery(qryString);
			query.setParameter("questId", questId);
			Long answerCountlng = (Long) query.getSingleResult();
			int answerCount = answerCountlng.intValue();
			return answerCount;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	

	public void removeAnswersByQuestionId(int questionId) 
			throws NoResultException, PersistenceException {	
		
		try {
			System.out.println("AnswerRepository, line 66:  questionId = " + questionId);
			Query query = null;
			query = entityManager.createQuery("SELECT ans from EntityAnswerModel ans where ans.questionid = :questionid " );
			query.setParameter("questionid", questionId);
			
			@SuppressWarnings("unchecked")
			List<EntityAnswerModel> listAnswerModel = (List<EntityAnswerModel>) query.getResultList();
			for (EntityAnswerModel ansModel : listAnswerModel){
				entityManager.remove(ansModel);
			}
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}	
	
	
	
	public void addOrUpdateAnswer(EntityAnswerModel answerModelIn) throws PersistenceException {
		
		System.out.println("AnswerRepository.addOrUpdateAnswer, answerModelIn.getId() = " +
				answerModelIn.getId());		
		try {
			System.out.println("answerModel is null");
			entityManager.merge(answerModelIn);
		}
		catch (NoResultException nor) {
			throw new NoResultException();
		}
		catch (EntityAlreadyExistsException eae) {
			throw new EntityAlreadyExistsException();
		}
		
	}
}
