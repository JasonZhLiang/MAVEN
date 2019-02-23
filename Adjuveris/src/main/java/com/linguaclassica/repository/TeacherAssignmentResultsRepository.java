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

import com.linguaclassica.entity.EntityTeacherAssignmentResultsModel;
import com.linguaclassica.model.TeacherAssignmentResultsModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class TeacherAssignmentResultsRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Adds results of one teacher's assignment to table
	 * @param TeacherAssignmentResultsModel resultsModel
	 * @return void
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void addTeacherAssignmentResults(TeacherAssignmentResultsModel resultsModel) 
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
	 * Retrieves a record of one teacher's assignment results
	 * @param int teacherId
	 * @return TeacherAssignmentResultsModel
	 * @throws UnknownEntityException
	 * @throws ServiceException
	 */
	public TeacherAssignmentResultsModel getTeacherAssignmentResultsByTeacherId(int teacherId) 
			throws UnknownEntityException, ServiceException{
		
		TeacherAssignmentResultsModel result = null;
		try {
			Query query = entityManager.createQuery(
				"SELECT ta FROM EntityTeacherAssignmentResultsModel ta where ta.teacherId = :teacherId");
			
			query.setParameter("teacherId",teacherId);
	
			@SuppressWarnings("unchecked")
			List<TeacherAssignmentResultsModel>resultList = 
				(List<TeacherAssignmentResultsModel>) query.getResultList();
			if (resultList.size() > 0) {
				result = resultList.get(0);
			}
			return result;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Updates a record of one teacher's assignment results
	 * @param int teacherId
	 * @param TeacherAssignmentResultsModel resultsModel
	 * @return int
	 * @throws UnknownEntityException
	 * @throws ServiceException
	 */
	public void updateTeacherAssignmentResultsByTeacherId(int teacherId, TeacherAssignmentResultsModel resultsModel) 
			throws UnknownEntityException, ServiceException{
		System.out.println("TARRepository, line 98: resultsModel.getId() = " + resultsModel.getId());
		try {
		       if(entityManager.find(EntityTeacherAssignmentResultsModel.class, resultsModel.getId()) == null){
		           throw new IllegalArgumentException("Unknown resultsModel");
		       }

		       entityManager.merge(resultsModel);
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
