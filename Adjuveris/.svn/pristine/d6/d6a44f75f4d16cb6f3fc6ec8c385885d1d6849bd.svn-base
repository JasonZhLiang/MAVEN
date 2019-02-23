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

import com.linguaclassica.entity.EntityAssignmentClassesModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class AssignmentClassesRepository {

	@PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * Retrieves class id from a given assignment id
	 * @param int assignId
	 * @return Integer
	 * @throws UnknownEntityException
	 * @throws ServiceException
	 */
	public Integer getClassIdByAssignId(int assignId) 
			throws UnknownEntityException, ServiceException{
		try {
			Query query = entityManager.createQuery(
				"SELECT ac.classId FROM EntityAssignmentClassesModel ac where ac.assignmentId = :assignId");
			
			query.setParameter("assignId",assignId);
	
			Integer result = (Integer) query.getSingleResult();
			
			return result;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Retrieves a list of classes ids from a given assignment id
	 * @param int assignId
	 * @return Integer
	 * @throws UnknownEntityException
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getClassesIdsByAssignId(int assignId) 
			throws UnknownEntityException, ServiceException{
		try {
			Query query = entityManager.createQuery(
				"SELECT ac.classId FROM EntityAssignmentClassesModel ac where ac.assignmentId = :assignId");
			
			query.setParameter("assignId",assignId);
	
			return (List<Integer>) query.getResultList();
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method retrieves the list of all assignments from one specific class.
	 * The assignment ids are returned.
	 * If no exceptions are thrown the method is successful.
	 * @param int classId
	 * @return List<Integer>
	 * @throws ServiceException if an unspecified error occurs.
	 * @throws UnknownEntityException if no results
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfAssignmentIdsByClassId(int classId) throws ServiceException, UnknownEntityException {
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex from EntityAssignmentClassesModel ex where ex.classId = :classId");
			query.setParameter("classId", classId);
			
			List<EntityAssignmentClassesModel> assignmentList = new ArrayList<EntityAssignmentClassesModel>();
			assignmentList = (List<EntityAssignmentClassesModel>)query.getResultList();
			
			System.out.println("AssignmentClassesRepository.getListOfAssignmentIdsByClassId");
			
			List<Integer> result = new ArrayList<Integer>();
			
			for(EntityAssignmentClassesModel queryResult: assignmentList){
				result.add(queryResult.getAssignmentId());
			}
			
			return result;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 
	 * This method creates an assignmentClass. If no exceptions are thrown the method is successful.
	 * 
	 * @param assignmentClassesModel
	 * @throws EntityExistsException if the user already exists.
	 * @throws PersistenceException if an unspecified error occurs.
	 */
	public void createAssignmentClasses(EntityAssignmentClassesModel assignmentClassModel) 
			throws EntityExistsException, PersistenceException {

		try {
			entityManager.persist(assignmentClassModel);
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
	 * This method retrieves the list of classes for an assignment.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<EntityAssignmentClassesModel> getListOfAssignmentClassesByAssignmentId(Integer assignmentId) {
		
    
		try {
			Query query = entityManager.createQuery(
				"SELECT en FROM EntityAssignmentClassesModel en WHERE en.assignmentId = :assignmentId");
		
			query.setParameter("assignmentId", assignmentId);
			
			ArrayList<EntityAssignmentClassesModel> assignmentClassesList = new ArrayList<EntityAssignmentClassesModel>();
	
			assignmentClassesList = (ArrayList<EntityAssignmentClassesModel>) query.getResultList();	
			
			System.out.println("AssignmentClassesRepository.getListOfClassIdsByAssignmentId");
			
			return assignmentClassesList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Remove an assignmentClasses entry
	 * @param assignmentClassesModel
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void RemoveAssigmentClasses(EntityAssignmentClassesModel assignmentClassesModel) 
			throws EntityExistsException, PersistenceException {
		try {
			EntityAssignmentClassesModel assigClasses = new EntityAssignmentClassesModel(); 
			assigClasses = entityManager.find(assignmentClassesModel.getClass(), assignmentClassesModel.getId());
			entityManager.remove(assigClasses);
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		return;
	}
	
	
}
