package com.linguaclassica.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityNotificationClassesModel;
import com.linguaclassica.entity.EntityNotificationModel;
import com.linguaclassica.model.NotificationModel;
import com.linguaclassica.repository.NotificationClassesRepository;
import com.linguaclassica.repository.NotificationRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;

@Service
@Transactional
public class NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private NotificationClassesRepository notificationClassesRepository;

	public final static List<String> notificationTypeList = Arrays.asList(new String[] {
			"Office", "All Day", "Non-Calendar"}); 
	public final static int indexOffice = 0;
	public final static int indexAllDay = 1;
	public final static int indexNonCalendar = 2;
	public final static List<Integer> notificationIndexList = Arrays.asList(new Integer[] {
			indexOffice, indexAllDay, indexNonCalendar}); 
	
	/**
	 * This method will create a notification
	 * @param notificationModel
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void createNotification(NotificationModel notificationModel) 
		throws EntityAlreadyExistsException, ServiceException {			
		try {
			System.out.println("NotificationService: createNotification");
			notificationRepository.createNotification((EntityNotificationModel) notificationModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method will find a notification by is unique id
	 * @param id
	 * @return EntityNotificationModel
	 */
	public EntityNotificationModel findNotificationById(Integer id){
		return notificationRepository.findNotificationById(id);
	}
	
	/**
	 * This method will find a list of notifications created by a specific userid
	 * @param createdById
	 * @return List<EntityNotificationModel>
	 */
	public List<EntityNotificationModel> findNotificationsByCreatedById(Integer createdById){
		return notificationRepository.findNotificationsByCreatedById(createdById);
	}
	
	/**
	 * Returns a list of notifications of particular subject type and time range
	 * @param startDate
	 * @param expiryDate
	 * @return List<EntityNotificationModel>
	 */
	public List<EntityNotificationModel> findNotificationsByTypeRange(int type, Timestamp startDate, Timestamp expiryDate){
		return notificationRepository.findNotificationsByTypeRange(type,startDate,expiryDate);
	}
	
	/**
	 * Returns a list of ALL notifications 
	 * @return List<EntityNotificationModel>
	 */
	
	public List<EntityNotificationModel> getAllNotifications() {	
		
		return notificationRepository.getAllNotifications();
	}
	/**
	 * This method will find a list of notifications within a date range
	 * @param startDate
	 * @param expiryDate
	 * @return List<EntityNotificationModel>
	 */
	public List<EntityNotificationModel> findAllNotificationsByRange(Timestamp startDate, Timestamp expiryDate) {
		return notificationRepository.findAllNotificationsByRange(startDate, expiryDate);
	}
	
	/**
	 * Get the notifications that expire past the current date
	 * @param orgId
	 * @param currentDate
	 * @return
	 */
	public List<EntityNotificationModel> getFutureNotifications(Integer orgId, Timestamp thisTime)
	{
		return notificationRepository.getFutureNotifications(orgId, thisTime);
	}
	
	/**
	 * This method will find a list of notifications within a date range for a list if notification ids
	 * @param startDate
	 * @param expiryDate
	 * @param notificationIdList
	 * @return List<EntityNotificationModel>
	 */
	public List<EntityNotificationModel> findNotificationsByRangeInIdList(Timestamp startDate, Timestamp expiryDate, List<Integer> notificationIdList) {	
		
		if (!notificationIdList.isEmpty()){
			return notificationRepository.findNotificationsByRangeInIdList(startDate, expiryDate, notificationIdList);
		} else {
			return new ArrayList<EntityNotificationModel>();
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
		return notificationRepository.findCurrentUserNotificationIDs(userId, thisDay);
	}

	/**
	 * This method will update a notification
	 * @param notificationModel
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void updateNotification(NotificationModel notificationModel) 
		throws EntityAlreadyExistsException, ServiceException {			
		try {
			System.out.println("NotificationService: UpdateNotification");
			notificationRepository.updateNotification((EntityNotificationModel) notificationModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * This method will remove a notificationId from NotificationClasses 
	 * @param notificationId
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void removeNotificationClasses(EntityNotificationClassesModel notificationClassesModel) 
		throws EntityAlreadyExistsException, ServiceException {			
		try {
			System.out.println("NotificationService: RemoveNotification");
			notificationClassesRepository.RemoveNotificationClasses(notificationClassesModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeNotification(int notificationId) {
		notificationRepository.removeNotification(notificationId);
		
	}

}
