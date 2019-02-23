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

import com.linguaclassica.entity.EntityTeacherPreResultsModel;
import com.linguaclassica.model.TeacherPreResultsModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class TeacherPreResultsRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Adds results of one teacher's previous terms assignments result to table
	 * @param TeacherPreResultsModel resultsModel
	 * @return void
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void addTeacherPreResults(TeacherPreResultsModel resultsModel) 
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
	 * Retrieves a record of one teacher's previous terms assignments result
	 * @param int teacherId
	 * @return TeacherPreResultsModel
	 * @throws UnknownEntityException
	 * @throws ServiceException
	 */
	public TeacherPreResultsModel getTeacherPreResultsByTeacherId(int teacherId) 
			throws UnknownEntityException, ServiceException{
		
		TeacherPreResultsModel result = null;
		try {
			Query query = entityManager.createQuery(
				"SELECT ta FROM EntityTeacherPreResultsModel ta where ta.teacherId = :teacherId");
			
			query.setParameter("teacherId",teacherId);
	
			@SuppressWarnings("unchecked")
			List<TeacherPreResultsModel>resultList = 
				(List<TeacherPreResultsModel>) query.getResultList();
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
	 * Updates a record of one teacher's previous terms assignments result
	 * @param int teacherId
	 * @param TeacherPreResultsModel
	 * @return int
	 * @throws UnknownEntityException
	 * @throws ServiceException
	 */
	public void updateTeacherPreResultsByTeacherId(int teacherId, TeacherPreResultsModel resultsModel) 
			throws UnknownEntityException, ServiceException{

		try {
			TeacherPreResultsModel prevResultsModel = entityManager.find(EntityTeacherPreResultsModel.class, teacherId);
			((EntityManager) prevResultsModel).merge(resultsModel);
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
