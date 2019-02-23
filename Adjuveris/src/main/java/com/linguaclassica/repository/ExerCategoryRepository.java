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

import com.linguaclassica.entity.EntityExerCategoryModel;
import com.linguaclassica.model.ExerCategoryModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class ExerCategoryRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * This method creates an exercise. If no exceptions are thrown the method is successful.
	 * @param exerCategoryModel
	 * @throws EntityExistsException if the user already exists.
	 * @throws PersistenceException if an unspecified error occurs.
	 */
	public int createExerCategory(EntityExerCategoryModel exerCategoryModel) 
			throws EntityExistsException, PersistenceException {
		int exerCategoryId;
		try {
			entityManager.persist(exerCategoryModel);
			entityManager.refresh(exerCategoryModel);
			exerCategoryId = exerCategoryModel.getId();
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}

		return exerCategoryId;
	}
	
	/**
	 * This method is used to find an exercise category by its id.
	 * If successful, the valid ExerCategoryModel is returned.
	 * 
	 * @param id
	 * @return
	 * @throws NoResultException if the exercise cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityExerCategoryModel findExerCategoryById(int id) 
			throws NoResultException, PersistenceException {		
		
		Query query = null;
		query = entityManager.createQuery("SELECT exc from EntityExerCategoryModel exc where exc.id = :id");
		query.setParameter("id", id);
		
		EntityExerCategoryModel exerCategoryModel = (EntityExerCategoryModel) query.getSingleResult();
		
		System.out.println("ExerciseRepository.findExerciseById");
		
		// Got here so valid user
		return exerCategoryModel;
	}

	/**
	 * This method retrieves the list of exercise categories.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityExerCategoryModel> getListOfExerCategories() {
		try {
			Query query = entityManager.createQuery(
				"SELECT exc FROM EntityExerCategoryModel exc ORDER BY exc.id ASC");
			
			List<EntityExerCategoryModel> exerCategoriesList = new ArrayList<EntityExerCategoryModel>();
			exerCategoriesList = (List<EntityExerCategoryModel>) query.getResultList();
			
			System.out.println("ExerCategoriesRepository.getListOfExerCategories");
			
			return exerCategoriesList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<EntityExerCategoryModel> getListOfExerCategoriesByType(String type) {
		//public List<EntityExerCategoryModel> getListOfExerCategoriesByType(String type) {
		try {
			Query query = entityManager.createQuery(
				"SELECT exc FROM EntityExerCategoryModel exc WHERE exc.exercisetype = :exertype ORDER BY exc.id ASC");

			query.setParameter("exertype",type);
			//query.setParameter("exertype",type);
			List<EntityExerCategoryModel> practiceExerCatList;
			practiceExerCatList = (List<EntityExerCategoryModel>) query.getResultList();
			
			System.out.println("ExerCategoriesRepository.getListOfExerCategoriesByType(String type)");
			
			return practiceExerCatList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method retrieves the list of exercise categories
	 * from the given list of id's and of the given type.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityExerCategoryModel> getListOfExerCategoriesByIdListAndType(List<Integer> exCatIdList, String type) {
		try {
			if (!exCatIdList.isEmpty()){
				Query query = entityManager.createQuery(
						"SELECT exc FROM EntityExerCategoryModel exc WHERE exc.id IN (:exCatIdList) and "
								+ "exc.exercisetype = :exertype ORDER BY exc.id ASC");
				query.setParameter("exCatIdList", exCatIdList);
				query.setParameter("exertype", type);

				return (List<EntityExerCategoryModel>) query.getResultList();
			}
			return new ArrayList<EntityExerCategoryModel>();
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
	public void updateExerCategory(ExerCategoryModel exerCategoryModel) 
			throws EntityExistsException, PersistenceException {

		try {
			entityManager.merge(exerCategoryModel);
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
	 * This method delete a textPassage. If no exceptions are thrown the method is successful.
	 * @param passageId
	 * @throws EntityExistsException
	 * @throws PersistenceException if an unspecified error octextPassageModelcurred.
	 */
	public void deleteExerciseCategory(int exerCategoryId) 
			throws EntityExistsException, PersistenceException {

		try {
			Query query = entityManager.createQuery("SELECT exc from EntityExerCategoryModel exc where exc.id = :id");
	
			query.setParameter("id", exerCategoryId);
	
			EntityExerCategoryModel exerCategoryModel = (EntityExerCategoryModel) query.getSingleResult();
						
			entityManager.remove(exerCategoryModel);
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

