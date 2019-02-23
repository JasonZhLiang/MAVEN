package com.linguaclassica.repository;

import java.sql.Timestamp;
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

import com.linguaclassica.entity.EntityNotificationModel;
import com.linguaclassica.model.NotificationModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class NotificationRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * Creates a notification entry
	 * @param notificationModel
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void createNotification(EntityNotificationModel notificationModel) 
			throws EntityExistsException, PersistenceException {
		try {
			entityManager.persist(notificationModel);
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
	 * Returns a notification based on its unique id
	 * @param id
	 * @return EntityNotificationModel
	 * @throws NoResultException
	 * @throws PersistenceException
	 */
	public EntityNotificationModel findNotificationById(Integer id) 
			throws NoResultException, PersistenceException {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityNotificationModel ex WHERE ex.id = :id");
			query.setParameter("id", id);
			
			EntityNotificationModel notificationModel = (EntityNotificationModel) query.getSingleResult();
			
			System.out.println("NotificationRepository.findNotificationById.getNote() = " + notificationModel.getNote());
			
			return notificationModel;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	/**
	 * Returns a list of notifications of particular subject type and time range
	 * @param createdById
	 * @return List<EntityNotificationModel>
	 * @throws NoResultException
	 * @throws PersistenceException
	 */
	@SuppressWarnings("unchecked")
	public List<EntityNotificationModel> findNotificationsByTypeRange(Integer type, Timestamp startDate, Timestamp expiryDate) 
			throws NoResultException, PersistenceException {	
		if(type == null || type < 0){
			type = 0;
		}
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityNotificationModel ex WHERE ex.type = :type "
					+ "AND ex.startdate >= :startDate AND ex.expirydate <= :expiryDate");
			query.setParameter("type", type);
			query.setParameter("startDate", startDate);
			query.setParameter("expiryDate", expiryDate);
			return (List<EntityNotificationModel>) query.getResultList();
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**not in use
	 * Returns a list of notifications that were created by a particular userid
	 * @param createdById
	 * @return List<EntityNotificationModel>
	 * @throws NoResultException
	 * @throws PersistenceException
	 */
	@SuppressWarnings("unchecked")
	public List<EntityNotificationModel> findNotificationsByCreatedById(Integer createdById) 
			throws NoResultException, PersistenceException {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityNotificationModel ex WHERE ex.createdbyid = :createdById");
			query.setParameter("createdById", createdById);
			
			List<EntityNotificationModel> notificationList = (List<EntityNotificationModel>) query.getResultList();
			
			System.out.println("NotificationRepository.findNotificationsByCreatedById.get(0).getNote() = " + notificationList.get(0).getNote());
			
			return notificationList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Returns a list of ALL notifications 
	 * @return List<EntityNotificationModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityNotificationModel> getAllNotifications() {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityNotificationModel ex ORDER BY ex.startdate");
			
			return (List<EntityNotificationModel>) query.getResultList();
	
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	/**not in use
	 * Returns a list of ALL notifications that are to be posted within a date range
	 * @param startDate
	 * @param expiryDate
	 * @return List<EntityNotificationModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityNotificationModel> findAllNotificationsByRange(Timestamp startDate, Timestamp expiryDate) {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityNotificationModel ex WHERE ex.startdate >= :startDate "
					+ "AND ex.expirydate <= :expiryDate");
			query.setParameter("startDate", startDate);
			query.setParameter("expiryDate", expiryDate);
			
			List<EntityNotificationModel> notificationList = (List<EntityNotificationModel>) query.getResultList();
			
		//	System.out.println("NotificationRepository.findAllNotificationsByRange.get(0).getNote() = " + notificationList.get(0).getNote());
			
			return notificationList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Get the notifications that expire past the current date
	 * @param orgId
	 * @param currentDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EntityNotificationModel> getFutureNotifications(Integer orgId, Timestamp thisTime)
	{
		Query query = entityManager.createQuery("SELECT enm FROM EntityNotificationModel enm " + 
				"WHERE enm.orgid = :orgID AND enm.expirydate >= :expiryDate");
		query.setParameter("orgID", orgId);
		query.setParameter("expiryDate", thisTime);
		return query.getResultList();
	}

	/**not in use
	 * Returns a list of notifications from a list of notification ids that are to be posted within a date range
	 * @param startDate
	 * @param expiryDate
	 * @return List<EntityNotificationModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityNotificationModel> findNotificationsByRangeInIdList(Timestamp startDate, Timestamp expiryDate, List<Integer> notificationIdList) {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityNotificationModel ex "
					+ "WHERE ex.startdate >= :startDate AND ex.expirydate <= :expiryDate "
					+ "AND ex.id IN (:notificationIdList)");
			query.setParameter("startDate", startDate);
			query.setParameter("expiryDate", expiryDate);
			query.setParameter("notificationIdList", notificationIdList);
			
			List<EntityNotificationModel> notificationList = new ArrayList<EntityNotificationModel>();
	
			notificationList = (List<EntityNotificationModel>) query.getResultList();
			
			System.out.println("NotificationRepository.findNotificationsByRangeInIdList.get(0).getNote() = " + notificationList.get(0).getNote());
			
			return notificationList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	

	 /* Returns a list of current notifications from a list of notification ids
	 * @param notificationIdList
	 * @return List<EntityNotificationModel>
	 */
	@SuppressWarnings("unchecked")
	public List<NotificationModel> findNotificationsByNotificationIdsList(List<Integer> notificationIdList) {	
		System.out.println("Notification repository findNotificationsByNotificationIdsList line 176: List of notificationIDs");	
		System.out.println("Notification repository, line 177: notificationIdList.size() = " + notificationIdList.size());
		System.out.print("List ");
		for (int noId : notificationIdList )
			System.out.print(noId+", ");
		System.out.println();
		/*
		java.util.Date nowutil =Timestamp java.util.Date();
	Timestampendar calen = Calendar.getInstance();
		calen.setTime(nowutil);
		calen.add(Calendar.MONTH, -6);
		java.util.Date thenprim = calen.getTime();
		java.sql.Date then = new java.sql.Date(thenprim.getTime());
		System.out.println("NotificationRepository, line 230:  then = " + then.toString());
	    java.sql.Date now = new java.sql.Date(nowutil.getTime());
	    */
		String qryString;
		qryString = "SELECT ex FROM EntityNotificationModel ex ";
		/*
		if (status.equals("CURRENT")){
			//shows notifications where startdate is past and expirydate has not yet come
			qryString=qryString+ "WHERE ex.startdate <= :now AND ex.expirydate >= :now AND ex.expirydate <> :then ";
		} else if (status.equals("ALL")) {
		
			//shows all notifications where startdate was less than six months prior
			qryString=qryString+ "WHERE ex.startdate > :then AND ex.expirydate <> :now ";			
		}else{
		
			qryString=qryString+ "WHERE ex.startdate > :now AND ex.expirydate < :now AND ex.expirydate <> :then ";
		}
		*/
		qryString=qryString+ "WHERE ex.id IN (:notificationIdList)";	
		try {
			Query query = null;
			query = entityManager.createQuery(qryString);
			//query.setParameter("now", now);
			//query.setParameter("then", then);
			query.setParameter("notificationIdList", notificationIdList);
			List<NotificationModel> notificationList = new ArrayList<NotificationModel>();
			notificationList = (List<NotificationModel>) query.getResultList();
			
			//System.out.println("NotificationRepository.findNotificationsByRangeInIdList.get(0).getNote() = " + notificationList.get(0).getNote());
			
			return notificationList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the notification for the classes attended by the (student) user
	 * that are active for the current day.
	 * @param userId
	 * @param thisDay
	 * @return
	 */
	public List<Integer> findCurrentUserNotificationIDs(Integer userId, Timestamp thisDay)
	{
		List<Integer> notificationIDs = new ArrayList<Integer>();
		Query query = entityManager.createQuery("SELECT DISTINCT ID FROM NotificationModel nm " + 
				"INNER JOIN NotificationClassesModel ncm ON nm.ID = ncm.notificationId " + 
				"INNER JOIN ClassUsersModel cum ON ncm.classId = cum.classid " + 
				"AND cum.userid = :userID " + 
				"AND nm.startdate <= :currentday AND nm.expirydate >= :currentday");
		query.setParameter("userID", userId);
		query.setParameter("currentday", thisDay);
		query.getResultList();
		return notificationIDs;
	}
	
	/**
	 * Update a notification entry
	 * @param notificationModel
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void updateNotification(EntityNotificationModel notificationModel) 
			throws EntityExistsException, PersistenceException {
		try {
			entityManager.merge(notificationModel);
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
	 * Remove a notificationClasses entry
	 * @param notificationClassesModel
	 * @throws UnknownEntityException
	 * @throws ServiceException
	 */
	public void removeNotification(int notificationId) 
			throws EntityExistsException, PersistenceException {
		try {
			Query query = entityManager.createQuery("SELECT ex from EntityNotificationModel ex where ex.id = :id");
			
			query.setParameter("id", notificationId);
	
			EntityNotificationModel entityNotificationModel = (EntityNotificationModel) query.getSingleResult();
						
			entityManager.remove(entityNotificationModel);
			return;
		}
		catch (NoResultException e) {
			// This exception is thrown by getSingleResult if a match cannot be found.
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
}//Class close
