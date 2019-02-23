package com.linguaclassica.repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import com.linguaclassica.entity.EntityQuestionGroupModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class QuestionGroupRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 
	 * This method creates a record in question_groups table. If no exceptions are thrown the method is successful.
	 * 
	 * @param questionGroupModel
	 * @throws PersistenceException if an unspecified error occurs.
	 */
	public Integer createQuestionGroup(EntityQuestionGroupModel questionGroupModel) 
			throws EntityExistsException, PersistenceException {
		
		int qgid = 0;

		try {
				entityManager.persist(questionGroupModel);
				entityManager.refresh(questionGroupModel);		
				qgid = questionGroupModel.getId();
			} 
		catch (PersistenceException e) {
				
				if (e.getCause() instanceof ConstraintViolationException) {
					throw new EntityExistsException(e);
				}
				
				// Didn't match, throw the original exception
				throw e;
			}
		return qgid;	
	}
	public void deleteQuestionGroup(Integer questiongroupid) {
		
		try {
			Query query = entityManager.createQuery(
				"SELECT q FROM EntityQuestionGroupModel q where q.id = :qid");
		
			query.setParameter("qid", questiongroupid);
	
			EntityQuestionGroupModel questiongroupModel = (EntityQuestionGroupModel) query.getSingleResult();
						
			entityManager.remove(questiongroupModel);
			return;
		}
		catch (NoResultException e) {
			// This exception is thrown by getSingleResult if a match cannot be found.
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}


