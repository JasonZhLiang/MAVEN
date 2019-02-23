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

import com.linguaclassica.entity.EntityExerciseModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class ExerciseRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * This method creates an exercise. If no exceptions are thrown the method is successful.
	 * @param exerciseModel
	 * @throws EntityExistsException if the user already exists.
	 * @throws PersistenceException if an unspecified error occurs.
	 */
	public int createExercise(EntityExerciseModel exerciseModel) 
			throws EntityExistsException, PersistenceException {

		int exerciseId;
		try {
			entityManager.persist(exerciseModel);
			entityManager.refresh(exerciseModel);
			exerciseId = exerciseModel.getId();
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		return exerciseId;
	}	
	
	
	/**
	 * This method is used to find an exercise by its id.
	 * If successful, the valid ExerciseModel is returned.
	 * 
	 * @param id
	 * @return
	 * @throws NoResultException if the exercise cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityExerciseModel findExerciseById(int id) 
			throws NoResultException, PersistenceException {
		
		System.out.println("got to ExerciseRepository findExerciseById");
		if(entityManager == null) {
			System.out.println("exid entityManager null");
		}
		else {
			System.out.println("exid entityManager not null");			
		}
		
		if(entityManager == null) {
			System.out.println("entityManager null");
		}
		else {
			System.out.println("entityManager not null");			
		}		
		
		Query query = null;
		query = entityManager.createQuery("SELECT ex from EntityExerciseModel ex where ex.id = :id");
		query.setParameter("id", id);
		
		EntityExerciseModel exerciseModel = (EntityExerciseModel) query.getSingleResult();
		
		System.out.println("ExerciseRepository.findExerciseById");
		
		// Got here so valid user
		return exerciseModel;
	}
	
	/**
	 * This method is used to find an exercise by its variable1.
	 * If successful, the valid ExerciseModel is returned.
	 * 
	 * @param variable1
	 * @return
	 * @throws NoResultException if the exercise cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityExerciseModel findExerciseByVariableStrings(int catid, String var1, String var2In) 
			throws NoResultException, PersistenceException {		

		System.out.println("got to ExerciseRepository findUserByVariableStrings, catid, var1, var2In = " +
				catid + ", " + var1 + ", " + var2In);
		
		if(entityManager == null) {
			System.out.println("exid entityManager null");
		}
		else {
			System.out.println("exid entityManager not null");			
		}	
		EntityExerciseModel exerciseModel = null;
		Query query = null;
		
		if (var1 != null) {
			String var2 = ""; 
			if (var2In != null) {
				var2 = var2In;
			}
			query = entityManager.createQuery("SELECT ex from EntityExerciseModel ex " + 
				"where ex.exercisecategory = :catid and ex.var1string = :var1 and " +
				"ex.var2string = :var2");
			query.setParameter("catid",catid);
			query.setParameter("var1", var1);
			query.setParameter("var2", var2);
		}
		else {
			query = entityManager.createQuery("SELECT ex from EntityExerciseModel ex " + 
					"where ex.exercisecategory = :catid");
				query.setParameter("catid",catid);			
		}
		
		//check to see if there is a record of this sort and, if so, return it
		@SuppressWarnings("unchecked")
		List<EntityExerciseModel> exModelList = (List<EntityExerciseModel>) query.getResultList();
		if (exModelList.size() > 0) {
			exerciseModel = exModelList.get(0);
		}
		
		System.out.println("ExerciseRepository.findExerciseByVariableStrings");
		
		// Got here so valid user
		return exerciseModel;
	}

	/**
	 * This method retrieves the list of exercises CREATED by a user.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 */

	@SuppressWarnings("unchecked")
	public List<EntityExerciseModel> getListOfExercisesCreatedByUser(Integer userId) {
		
		List<EntityExerciseModel> exercisesList;
		try {			
			Query queryprim = entityManager.createQuery(
					"SELECT COUNT(ex) FROM EntityExerciseModel ex WHERE ex.createdbyid = :userId");
			queryprim.setParameter("userId", userId);
				
			long cnt = (Long) queryprim.getSingleResult();
			
			if (cnt > 0){
				Query query = entityManager.createQuery(
					"SELECT ex FROM EntityExerciseModel ex where ex.createdbyid = :userid");
				query.setParameter("userid", userId);			
				
				exercisesList = (List<EntityExerciseModel>) query.getResultList();
			}
			else
				exercisesList = new ArrayList<EntityExerciseModel>();	
			
			System.out.println("ExerciseRepository.getListOfExercisesCreatedByUser");
			
			return exercisesList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * This method retrieves the list of exercises by category.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 */

	@SuppressWarnings("unchecked")
	public List<EntityExerciseModel> getListOfExercisesByCategory (Integer categoryId) {
		
		List<EntityExerciseModel> exercisesList = null;
		try {			
			Query queryprim = entityManager.createQuery(
					"SELECT COUNT(ex) FROM EntityExerciseModel ex WHERE ex.exercisecategory = :categoryId");
			queryprim.setParameter("categoryId", categoryId);
				
			long cnt = (Long) queryprim.getSingleResult();
			
			if (cnt > 0){
				Query query = entityManager.createQuery(
					"SELECT ex FROM EntityExerciseModel ex where ex.exercisecategory = :categoryid "
					+ "ORDER BY ex.variable1 ASC");
				query.setParameter("categoryid", categoryId);			
				
				exercisesList = new ArrayList<EntityExerciseModel>();	
				exercisesList = (List<EntityExerciseModel>) query.getResultList();				
			}			
			
			System.out.println("ExerciseRepository.getListOfExercisesByCategory");
			
			return exercisesList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method retrieves the list of exercises by id list.
	 * If no exceptions are thrown the method is successful.
	 * Tammy - put null check in service file
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityExerciseModel> findExercisesByIdList(List<Integer> exerciseIdListIn) {
		
		List<EntityExerciseModel> exercisesList = null;
		try {			
			Query query = entityManager.createQuery(
				"SELECT ex FROM EntityExerciseModel ex WHERE ex.id IN (:exerciseIdList) ");
			query.setParameter("exerciseIdList", exerciseIdListIn);	
			
			//exercisesList = new ArrayList<EntityExerciseModel>();	
			exercisesList = (List<EntityExerciseModel>) query.getResultList();	
			
			System.out.println("ExerciseRepository.findExercisesByIdList");
			
			return exercisesList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	/**
	 * This method retrieves the list of exercises created by users in the userIdList.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityExerciseModel> findExercisesByUserIdList(List<Integer> userIdList) {
		
		try {
			if (!userIdList.isEmpty()) {
				Query query = entityManager.createQuery(
						"SELECT ex FROM EntityExerciseModel ex WHERE ex.createdbyid IN (:userIdList) ");
				query.setParameter("userIdList", userIdList);	
			
				return (List<EntityExerciseModel>) query.getResultList();
			}
			return new ArrayList<EntityExerciseModel>();
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method retrieves the list of variable2 strings by exercise.
	 * If no exceptions are thrown the method is successful.
	 * Tammy - put null check in service file
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<String> getListofExerciseVariable1(int exerciseid) {
		
		List<String> exercisesList = null;
		try {			
			Query query = entityManager.createQuery(
				"SELECT ex.variable1 FROM EntityExerciseModel ex WHERE ex.id = (:exerciseId)");
			query.setParameter("exerciseId", exerciseid);	
			
			exercisesList = new ArrayList<String>();	
			exercisesList = (List<String>) query.getResultList();	
			
			System.out.println("ExerciseRepository.findExercisesByIdList");
			
			return exercisesList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method retrieves the list of variable2 strings by exercise.
	 * If no exceptions are thrown the method is successful.
	 * Tammy - put null check in service file
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<String> getListofExerciseVariable2(int exerciseid) {
		
		List<String> exercisesList = null;
		try {			
			Query query = entityManager.createQuery(
				"SELECT ex.variable2 FROM EntityExerciseModel ex WHERE ex.id = (:exerciseId)");
			query.setParameter("exerciseId", exerciseid);	
			
			exercisesList = new ArrayList<String>();	
			exercisesList = (List<String>) query.getResultList();	
			
			System.out.println("ExerciseRepository.findExercisesByIdList");
			
			return exercisesList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * This method retrieves the list of exercises.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityExerciseModel> getListOfExercises() {
		try {
			Query query = entityManager.createQuery(
				"SELECT ex FROM EntityExerciseModel ex");
			
			List<EntityExerciseModel> exercisesList = new ArrayList<EntityExerciseModel>();
			exercisesList = (List<EntityExerciseModel>) query.getResultList();
			
			System.out.println("ExerciseRepository.getListOfExercises");
			
			return exercisesList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	@SuppressWarnings("unchecked")
	public List<EntityExerciseModel> getPracticeExercises() {
		List<EntityExerciseModel> practiceExerList = new ArrayList<EntityExerciseModel>();
		try {
			Query query = entityManager.createQuery(
				"SELECT ex FROM EntityExerciseModel ex");	
			practiceExerList = (List<EntityExerciseModel>)query.getResultList();		
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return practiceExerList;
	}
	
	/**
	 * This method delete a textPassage. If no exceptions are thrown the method is successful.
	 * @param passageId
	 * @throws EntityExistsException
	 * @throws PersistenceException if an unspecified error octextPassageModelcurred.
	 */
	public void deleteExercise(int exerciseid) 
			throws EntityExistsException, PersistenceException {

		try {
			Query query = entityManager.createQuery("SELECT ex from EntityExerciseModel ex where ex.id = :id");
	
			query.setParameter("id", exerciseid);
	
			EntityExerciseModel exerciseModel = (EntityExerciseModel) query.getSingleResult();
			
			entityManager.remove(exerciseModel);
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
