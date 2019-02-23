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

import com.linguaclassica.entity.EntityNotificationClassesModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class NotificationClassesRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * Creates a notificationClasses entry
	 * @param notificationClassesModel
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void createNotificationClasses(EntityNotificationClassesModel notificationClassesModel) 
			throws EntityExistsException, PersistenceException {
		try {
			entityManager.persist(notificationClassesModel);
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
	 * This method retrieves the list of completed notifications for a class.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfNotificationIdsByClassId(Integer classId) {
		
    
		try {
			Query query = entityManager.createQuery(
				"SELECT notificationid FROM EntityNotificationClassesModel en WHERE en.classid = :classId");
		
			query.setParameter("classId", classId);
			
			List<Integer> notificationIdsList = new ArrayList<Integer>();
	
			notificationIdsList = (List<Integer>) query.getResultList();	
			
			System.out.println("NotificationClassesRepository.getListOfNotificationIdsByClassId");
			
			return notificationIdsList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * This method retrieves the list of classes for a notification.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<EntityNotificationClassesModel> getListOfNotificationClassesByNotificationId(Integer notificationId) {
		
    
		try {
			Query query = entityManager.createQuery(
				"SELECT en FROM EntityNotificationClassesModel en WHERE en.notificationid = :notificationId");
		
			query.setParameter("notificationId", notificationId);
			
			ArrayList<EntityNotificationClassesModel> notificationClassesList = new ArrayList<EntityNotificationClassesModel>();
	
			notificationClassesList = (ArrayList<EntityNotificationClassesModel>) query.getResultList();	
			
			System.out.println("NotificationClassesRepository.getListOfClassIdsByNoficationId");
			
			return notificationClassesList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
	
	/**
	 * Remove a notificationClasses entry
	 * @param notificationClassesModel
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void RemoveNotificationClasses(EntityNotificationClassesModel notificationClassesModel) 
			throws EntityExistsException, PersistenceException {
		try {
			EntityNotificationClassesModel notClasses = new EntityNotificationClassesModel(); 
			notClasses = entityManager.find(notificationClassesModel.getClass(), notificationClassesModel.getId());
			entityManager.remove(notClasses);
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		return;
	}

	
}//Class close
