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

import com.linguaclassica.entity.EntityClassAssignmentResultsModel;
import com.linguaclassica.model.ClassAssignmentResultsModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class ClassAssignmentResultsRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Adds results of one class' assignment to table
	 * @param EntityClassAssignmentResultsModel resultsModel
	 * @return void
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void addClassAssignmentResults(ClassAssignmentResultsModel resultsModel) 
			throws EntityExistsException, PersistenceException {
		try {
			entityManager.persist(resultsModel);
		} catch (PersistenceException e) {
		
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
		}
		
		// Didn't match, throw the original exception
		throw e;
	}
	return;
}
	
	/**
	 * Retrieves a record of one class' assignment results
	 * @param int assignId
	 * @return EntityClassAssignmentResultsModel
	 * @throws UnknownEntityException
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public EntityClassAssignmentResultsModel getClassAssignmentResultsByAssignId(int assignId) 
			throws UnknownEntityException, ServiceException{
		
		List<ClassAssignmentResultsModel> resultsModelList = new ArrayList<ClassAssignmentResultsModel>();
		EntityClassAssignmentResultsModel result = null;
		try {
			Query query = entityManager.createQuery(
				"SELECT ca FROM EntityClassAssignmentResultsModel ca where ca.assignmentId = :assignId");
			
			query.setParameter("assignId",assignId);
	
			resultsModelList = (List<ClassAssignmentResultsModel>) query.getResultList();
			
			if(resultsModelList.size() > 0){
				result = (EntityClassAssignmentResultsModel) resultsModelList.get(0);
			}
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}		
		return result;
	}
}
