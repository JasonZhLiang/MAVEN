package com.linguaclassica.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityAssignmentFactModel;
import com.linguaclassica.entity.EntityNotificationClassesModel;
import com.linguaclassica.entity.EntityNotificationModel;
import com.linguaclassica.model.NotificationModel;
import com.linguaclassica.repository.AssignmentFactRepository;
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
	private AssignmentFactRepository assignmentFactRepository;
	
	@Autowired
	private NotificationClassesRepository notificationClassesRepository;
	/*
	@Autowired
	TimeService timeService;
	*/

	
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
	
	public void createNotification(EntityNotificationModel notificationModel, List<Integer> classIDs)
	{
		notificationRepository.createNotification(notificationModel, classIDs);
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
	 * This method will find a list of notifications within a date range
	 * @param startDate
	 * @param expiryDate
	 * @return List<EntityNotificationModel>
	 */
	public List<EntityNotificationModel> findAllNotificationsByRange(Date startDate, Date expiryDate) {
		return notificationRepository.findAllNotificationsByRange(startDate, expiryDate);
	}
	

	/**
	 * This method will find the list of notifications for a certain student ID
	 * @param studentId
	 * @return List<EntityNotificationModel>
	 */
	
	public List<EntityNotificationModel> findNotificationsByStudentId(Integer studentId) {
		
		List<EntityAssignmentFactModel> assignFactList = 
				assignmentFactRepository.getListOfAssignmentFactByStudentId("START","", studentId, null);
		
		System.out.println("assignFactList.size() = " + assignFactList.size());
		
		List<Integer> assignFactIdList = new ArrayList<Integer>();
	
		for (EntityAssignmentFactModel i : assignFactList )
			assignFactIdList.add(i.getId());
		
		
		List<EntityNotificationModel> notificationList = notificationRepository.findNotificationsByAssignmentFactIdList("CURRENT", assignFactIdList);
		return notificationList;
	}
	

	/**
	 * This method will find the list of notifications for a certain class
	 * @param classId
	 * @return List<EntityNotificationModel>
	 */
	/*
	public List<EntityNotificationModel> findNotificationsByClassId(Integer classId) {
		return notificationRepository.findNotificationsByClassId(classId);
	}
	*/
	/**
	 * This method will find a list of notifications within a date range for a list if notification ids
	 * @param startDate
	 * @param expiryDate
	 * @param notificationIdList
	 * @return List<EntityNotificationModel>
	 */
	public List<EntityNotificationModel> findNotificationsByRangeInIdList(Date startDate, Date expiryDate, List<Integer> notificationIdList) {	
		
		if (!notificationIdList.isEmpty()){
			return notificationRepository.findNotificationsByRangeInIdList(startDate, expiryDate, notificationIdList);
		} else {
			return new ArrayList<EntityNotificationModel>();
		}
			
	}
	
	/**
	 * This method will find a list of notifications for a class id
	 * @param classIdList
	 * @return List<EntityNotificationModel>
	 */
	public List<NotificationModel> findNotificationsByClassId(String status, Integer classId) {
		
		ArrayList<Integer> notificationIdslist = 
				(ArrayList<Integer>) notificationClassesRepository.getListOfNotificationIdsByClassId(classId);
		System.out.println("notificationIdslist.size() = " + notificationIdslist.size());
		
		if (!notificationIdslist.isEmpty()){
			List<NotificationModel> notifList = 
					notificationRepository.findNotificationsByNotificationIdsList(notificationIdslist);
			List<NotificationModel> retNotifList = new ArrayList<NotificationModel>();
			for(int i = 0; i < notifList.size(); i++){
				/*NotificationModel notifModel = notifList.get(i);
				String timezone = notifModel.getTimeZone();
				
				ZonedDateTime nowtz = timeService.getOtherTimeZoneNow(timezone);
				
				ZonedDateTime startzdt = timeService.getTimeByTimeZone(notifModel.getStartDate(), 
						notifModel.getTimeZone());
				ZonedDateTime endzdt = timeService.getTimeByTimeZone(notifModel.getExpiryDate(), 
						notifModel.getTimeZone());
				
				if(status.equals("CURRENT")) {
					if(startzdt.isBefore(nowtz) && endzdt.isAfter(nowtz)){
						retNotifList.add(notifModel);
					}
				}
				else {
				*/
					retNotifList = notifList;
				//}
				
			}
			return retNotifList;
			//return notificationRepository.findNotificationsByNotificationIdsList(status, notificationIdslist);
		} else {
			return new ArrayList<NotificationModel>();
		}
		
	}
	
	/**
	 * get the notifications attached to the list of classes
	 * @param classIds
	 * @return
	 */
	public List<NotificationModel> findNotificationsByClassIds(List<Integer> classIds)
	{
		return notificationClassesRepository.findNotificationsByClassIds(classIds);
	}
	
	/**
	 * This method will find a list of notifications in an Id list
	 * @param notificationIdList
	 * @return List<EntityNotificationModel>
	 */
	public List<EntityNotificationModel> findNotificationsByAssignmentFactIdList(String status,List<Integer> assignmentfactIdList) {
		System.out.println("findNotificationsByAssignmentFactIdList");
		if (!assignmentfactIdList.isEmpty()){
			return notificationRepository.findNotificationsByAssignmentFactIdList(status,assignmentfactIdList);
		} else {
			return new ArrayList<EntityNotificationModel>();
		}
		
	}

	/**
	 * This method will find a list of classIds for a notificationClasses id
	 * @param notificationId 
	 * @return List<Integer>
	 */
	public ArrayList<EntityNotificationClassesModel> getListOfNotificationClassesByNotificationId(Integer notificationId) {
		
		ArrayList<EntityNotificationClassesModel> notificationClassesList = 
				(ArrayList<EntityNotificationClassesModel>) notificationClassesRepository.getListOfNotificationClassesByNotificationId(notificationId);
	    return notificationClassesList; 		
	}



	/**
	 * This method will create a notification
	 * @param notificationModel
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void createNotificationClasses(EntityNotificationClassesModel notificationClassesModel) 
		throws EntityAlreadyExistsException, ServiceException {			
		try {
			System.out.println("NotificationService: createNotificationClasses");
			notificationClassesRepository.createNotificationClasses(notificationClassesModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
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
		return notificationRepository.findCurrentUserNotificationIDs(userId, thisDay);
	}

	/**
	 * Get the notification for the classes attended by the (student) user
	 * that are active for the current day.
	 * @param userId
	 * @param thisDay
	 * @return
	 */
	public List<NotificationModel> findCurrentUserNotifications(Integer userId, Timestamp thisTime)
	{
		return notificationRepository.findCurrentUserNotifications(userId, thisTime);
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
	 * Update a notification entry and add and remove classes when required
	 * @param notificationModel
	 * @param newClassIDs
	 * @param oldClassIDs
	 */
	public void updateNotification(EntityNotificationModel notificationModel, List<Integer> newClassIDs, List<Integer> oldClassIDs)
	{
		notificationRepository.updateNotification(notificationModel, newClassIDs, oldClassIDs);
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
	
	// Intended for testing only
	public Long getNotificationCount()
	{
		return notificationRepository.getNotificationCount();
	}
	
	// Intended for testing only
	public List<EntityNotificationModel> getAllNotifications()
	{
		return notificationRepository.getAllNotifications();
	}
	
	// Intended for testing only
	public Long getNotificationClassCount()
	{
		return notificationClassesRepository.getNotificationClassCount();
	}

}
