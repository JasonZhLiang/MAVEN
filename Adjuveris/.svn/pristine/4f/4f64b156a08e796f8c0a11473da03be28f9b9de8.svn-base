package com.linguaclassica.repository;

import java.sql.Date;
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

import com.linguaclassica.entity.EntityNotificationClassesModel;
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
	 * Creates a notification entry and attaches it to classes
	 * @param notificationModel
	 * @param classIDs
	 */
	public void createNotification(EntityNotificationModel notificationModel, List<Integer> classIDs)
	{
		// create the notification
		entityManager.persist(notificationModel);
		entityManager.refresh(notificationModel);
		System.out.println("NotificationRepository.createNotification " + notificationModel.getId());

		// create the class associations
		for (int index = 0; index < classIDs.size(); index++)
		{
			EntityNotificationClassesModel nce = new EntityNotificationClassesModel();
			nce.setnotificationId(notificationModel.getId());
			nce.setclassId(classIDs.get(index));
			entityManager.persist(nce);
		}
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
			
			List<EntityNotificationModel> notificationList = new ArrayList<EntityNotificationModel>();
			
			notificationList = (List<EntityNotificationModel>) query.getResultList();
			
			System.out.println("NotificationRepository.findNotificationsByCreatedById.get(0).getNote() = " + notificationList.get(0).getNote());
			
			return notificationList;
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
	public List<EntityNotificationModel> findAllNotificationsByRange(Date startDate, Date expiryDate) {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityNotificationModel ex WHERE ex.startdate >= :startDate "
					+ "AND ex.expirydate <= :expiryDate");
			query.setParameter("startDate", startDate);
			query.setParameter("expiryDate", expiryDate);
			
			List<EntityNotificationModel> notificationList = new ArrayList<EntityNotificationModel>();
	
			notificationList = (List<EntityNotificationModel>) query.getResultList();
			
			System.out.println("NotificationRepository.findAllNotificationsByRange.get(0).getNote() = " + notificationList.get(0).getNote());
			
			return notificationList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**not in use
	 * Returns a list of notifications from a list of notification ids that are to be posted within a date range
	 * @param startDate
	 * @param expiryDate
	 * @return List<EntityNotificationModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityNotificationModel> findNotificationsByRangeInIdList(Date startDate, Date expiryDate, List<Integer> notificationIdList) {	
		
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
	
	/**
	 * Returns a list of current notifications from a list of notification ids
	 * @param notificationIdList
	 * @return List<EntityNotificationModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityNotificationModel> findNotificationsByAssignmentFactIdList(String status, List<Integer> assignmentfactIdList) {	
		System.out.println("Notification repository findNotificationsByAssignmentFactIdList line 176: List of assfactIDs");
		System.out.print("List ");
		List<EntityNotificationModel> notificationList = null;
		int listlen = assignmentfactIdList.size();
		if (listlen > 0){		
			for (int afId : assignmentfactIdList )
				System.out.print(afId+", ");
			System.out.println();
			java.util.Date nowutil = new java.util.Date();
		    java.sql.Date now = new java.sql.Date(nowutil.getTime());
			String qryString;
			qryString = "SELECT ex FROM EntityNotificationModel ex ";
			if (status=="CURRENT"){
				qryString=qryString+ "WHERE ex.startdate <= :now AND ex.expirydate >= :now ";
			}else{
				qryString=qryString+ "WHERE ex.startdate > :now AND ex.expirydate < :now ";
			}
			qryString=qryString+ "AND ex.assignmentfactid IN (:assignmentfactIdList)";	
			try {
				Query query = null;
				query = entityManager.createQuery(qryString);
				query.setParameter("now", now);
				query.setParameter("assignmentfactIdList", assignmentfactIdList);
				notificationList = new ArrayList<EntityNotificationModel>();
				notificationList = (List<EntityNotificationModel>) query.getResultList();				
			} catch (NoResultException e) {
				throw new UnknownEntityException(e);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		}
		return notificationList;
	}

	 /* Returns a list of current notifications from a list of notification ids
	 * @param notificationIdList
	 * @return List<EntityNotificationModel>
	 */
	@SuppressWarnings("unchecked")
	//public List<EntityNotificationModel> findNotificationsByNotificationIdsList(String status, List<Integer> notificationIdList) {
	public List<NotificationModel> findNotificationsByNotificationIdsList(List<Integer> notificationIdList) {	
		System.out.println("Notification repository findNotificationsByNotificationIdsList line 176: List of notificationIDs");	
		System.out.println("Notification repository, line 177: notificationIdList.size() = " + notificationIdList.size());
		System.out.print("List ");
		for (int noId : notificationIdList )
			System.out.print(noId+", ");
		System.out.println();
		/*
		java.util.Date nowutil = new java.util.Date();
		Calendar calen = Calendar.getInstance();
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
	public List<Integer> findCurrentUserNotificationIDs(Integer userId, Date thisDay)
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
	 * Get the notifications for the classes attended by the (student) user
	 * that are active for the current day.
	 * @param userId
	 * @param thisDay
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<NotificationModel> findCurrentUserNotifications(Integer userId, Timestamp thisTime)
	{
		List<NotificationModel> notifications = new ArrayList<NotificationModel>();
		Query query = entityManager.createQuery("SELECT DISTINCT nm " + 
				"FROM EntityNotificationModel nm, EntityNotificationClassesModel ncm, EntityClassUsersModel cum " + 
				"WHERE nm.id = ncm.notificationid AND ncm.classid = cum.classid AND cum.userid = :userID " + 
				"AND nm.startdate <= :currentday AND nm.expirydate >= :currentday");
		query.setParameter("userID", userId);
		query.setParameter("currentday", thisTime);
		notifications = query.getResultList();
		return notifications;
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
	 * Update a notification entry and add and remove classes when required
	 * @param notificationModel
	 * @param newClassIDs
	 * @param oldClassIDs
	 */
	public void updateNotification(EntityNotificationModel notificationModel, List<Integer> newClassIDs, List<Integer> oldClassIDs)
	{
		// update the notification
		entityManager.merge(notificationModel);

		// add new the class associations
		for (int index = 0; index < newClassIDs.size(); index++)
		{
			EntityNotificationClassesModel nce = new EntityNotificationClassesModel();
			nce.setnotificationId(notificationModel.getId());
			nce.setclassId(newClassIDs.get(index));
			entityManager.persist(nce);
		}

		if (oldClassIDs.size() > 0)
		{
			// remove old the class associations
			Query query = entityManager.createQuery("DELETE FROM EntityNotificationClassesModel nce " + 
					"WHERE nce.notificationid = :note AND nce.classid IN (:list)");
			query.setParameter("note", notificationModel.getId());
			query.setParameter("list", oldClassIDs);
			query.executeUpdate();
		}
	}
	
	/**
	 * Remove a notificationClasses entry
	 * @param notificationClassesModel
	 */
	public void removeNotification(int notificationId) 
	{
		Query query = entityManager.createQuery("SELECT ex from EntityNotificationModel ex where ex.id = :id");
		query.setParameter("id", notificationId);
		EntityNotificationModel entityNotificationModel = (EntityNotificationModel) query.getSingleResult();
		entityManager.remove(entityNotificationModel);
		entityManager.flush();
		return;
	}
	
	// Intended for testing only
	public Long getNotificationCount()
	{
		Query query = entityManager.createQuery("SELECT COUNT(nm) from EntityNotificationModel nm");
		return (Long) query.getSingleResult();
	}
	
	// Intended for testing only
	@SuppressWarnings("unchecked")
	public List<EntityNotificationModel> getAllNotifications()
	{
		Query query = entityManager.createQuery("SELECT nm from EntityNotificationModel nm");
		return query.getResultList();
	}
	
}//Class close
