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

import com.linguaclassica.entity.EntityStudentAssignmentResultsModel;
import com.linguaclassica.model.StudentAssignmentResultsModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class StudentAssignmentResultsRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Adds results of one student's assignment to table
	 * @param EntityStudentAssignmentResultsModel resultsModel
	 * @return void
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void addStudentAssignmentResults(EntityStudentAssignmentResultsModel resultsModel) 
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
	
	@SuppressWarnings("unchecked")
	public Boolean checkIfStudentAssignmentResultsByAssignFactIdExists(int assignFactId) 
		throws ServiceException {
		boolean result = false;		
		try {
			Query query = entityManager.createQuery(
				"SELECT sa FROM EntityStudentAssignmentResultsModel sa where sa.assignmentFactId = :assignmentFactId");
			
			query.setParameter("assignmentFactId",assignFactId);
			List<EntityStudentAssignmentResultsModel> resultList = (List<EntityStudentAssignmentResultsModel>) query.getResultList();
			if (resultList.size() > 0) {
				result = true;
			}
		} catch(Exception e) {
			throw new ServiceException(e);			
		}		
		return result;
	}
	/**
	 * Retrieves a record of one student's assignment results
	 * @param int assignFactId
	 * @return EntityStudentAssignmentResultsModel
	 * @throws UnknownEntityException
	 * @throws ServiceException
	 */
	public EntityStudentAssignmentResultsModel getStudentAssignmentResultsByAssignFactId(int assignFactId) 
			throws UnknownEntityException, ServiceException{
		EntityStudentAssignmentResultsModel result = null;
		try {
			Query query = entityManager.createQuery(
				"SELECT sa FROM EntityStudentAssignmentResultsModel sa where sa.assignmentFactId = :assignmentFactId");
			
			query.setParameter("assignmentFactId",assignFactId);
			result = (EntityStudentAssignmentResultsModel) query.getSingleResult();			
			return result;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Retrieves a record of one student's assignment results
	 * @param int assignFactId
	 * @return EntityStudentAssignmentResultsModel
	 * @throws UnknownEntityException
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public List<StudentAssignmentResultsModel> getListOfStudentAssignmentResultsByAssignFactId(int assignFactId) 
			throws UnknownEntityException, ServiceException{
		List<StudentAssignmentResultsModel> resultList = null;
		try {
			Query query = entityManager.createQuery(
				"SELECT sa FROM EntityStudentAssignmentResultsModel sa where sa.assignmentFactId = :assignmentFactId");
			
			query.setParameter("assignmentFactId",assignFactId);
			resultList = query.getResultList();			
			return resultList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
