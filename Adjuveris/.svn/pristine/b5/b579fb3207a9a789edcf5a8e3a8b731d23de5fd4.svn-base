package com.linguaclassica.repository;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.linguaclassica.model.QuestionOptionModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class QuestionOptionRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 
	 * This method creates a questionoption. If no exceptions are thrown the method is successful.
	 * 
	 * @param questionModel
	 * @throws PersistenceException if an unspecified error occurs.
	 */

	public void createQuestionOption(Integer questid, QuestionOptionModel questionOptionModel)
			throws EntityExistsException, PersistenceException  {

		try {
				entityManager.persist(questionOptionModel);
			} 
		catch (PersistenceException e) {
				
				if (e.getCause() instanceof ConstraintViolationException) {
					throw new EntityExistsException(e);
				}
				
				// Didn't match, throw the original exception
				throw e;
			}
		return;			
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getListOfQuestionOptions(Integer questionid) {
		
		List<String> questoptionList = null;
		try {			
			Query queryprim = entityManager.createQuery(
					"SELECT COUNT(q.questionoption) FROM EntityQuestionOptionModel q WHERE q.questionid = :questionid");
			queryprim.setParameter("questionid", questionid);
			
			long cnt = (Long) queryprim.getSingleResult();
		
			if (cnt > 0){
			
				Query query = entityManager.createQuery(
					"SELECT q.questionoption FROM EntityQuestionOptionModel q where q.questionid = :questionid");				
				query.setParameter("questionid",questionid);
		
				questoptionList = query.getResultList();
			}
			
			return questoptionList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
		
