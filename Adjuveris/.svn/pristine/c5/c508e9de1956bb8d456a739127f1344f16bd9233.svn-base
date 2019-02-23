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

import com.linguaclassica.entity.EntityTextPassageModel;
import com.linguaclassica.model.TextPassageModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class TextPassageRepository {
	
	@PersistenceContext
    private EntityManager entityManager;

	/**
	 * This method creates a textPassage. If no exceptions are thrown the method is successful.
	 * @param textPassageModel
	 * @throws EntityExistsException
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public int createTextPassage(TextPassageModel textPassageModel) 
			throws EntityExistsException, PersistenceException {

		int textPassageId;
		System.out.println("textPassageModel.getTextcontent().length = " + 
				textPassageModel.getTextcontent().length());
		try {
			entityManager.persist(textPassageModel);
			entityManager.refresh(textPassageModel);
			textPassageId = textPassageModel.getId();
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		return textPassageId;
	}
	/**
	 * This method updates a textPassage. If no exceptions are thrown the method is successful.
	 * @param textPassageModel
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public void updateTextPassage(TextPassageModel textPassageModelIn) 
			throws PersistenceException {

		try {				
			TextPassageModel textPassageModel = entityManager.find(EntityTextPassageModel.class,textPassageModelIn.getId());
			textPassageModel.setTextcontent(textPassageModelIn.getTextcontent());
			
			entityManager.persist(textPassageModel);
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
	public EntityTextPassageModel findPassageById(Integer passageId) 
			throws NoResultException, PersistenceException {
		//System.out.println("TextPassageRepository, line 88:  passageId = " + passageId);
		EntityTextPassageModel passageModel = null;
		try {
			//logger.info("TextPassageRepository, line 84:  passageId = " + passageId);
			List<EntityTextPassageModel> passageList = new ArrayList<EntityTextPassageModel>();
			Query query = null;
			query = entityManager.createQuery("SELECT pas from EntityTextPassageModel pas where pas.id = :passageid");
			query.setParameter("passageid", passageId);
			
			passageList = (List<EntityTextPassageModel>) query.getResultList();
			//logger.info("TextPassageRepository, line 95:  passageList.size() = " + passageList.size());
			if (passageList.size() > 0) {
				passageModel = passageList.get(0);
			}
			
			return passageModel;
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	/**
	 * This method delete a textPassage. If no exceptions are thrown the method is successful.
	 * @param passageId
	 * @throws EntityExistsException
	 * @throws PersistenceException if an unspecified error octextPassageModelcurred.
	 */
	public void deleteTextPassage(int passageId) 
			throws EntityExistsException, PersistenceException {

		try {
			Query query = entityManager.createQuery(
				"SELECT t FROM EntityTextPassageModel t where t.id = :tid");
		
			query.setParameter("tid", passageId);
	
			EntityTextPassageModel textPassageModel = (EntityTextPassageModel) query.getSingleResult();
						
			entityManager.remove(textPassageModel);
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
