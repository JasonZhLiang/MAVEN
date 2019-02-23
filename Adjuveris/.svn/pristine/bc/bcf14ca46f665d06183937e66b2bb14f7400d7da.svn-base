package com.linguaclassica.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityClassUsersModel;
import com.linguaclassica.model.ClassModel;
import com.linguaclassica.repository.ClassRepository;
import com.linguaclassica.repository.ClassUsersRepository;
import com.linguaclassica.repository.TermRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;


@Service
@Transactional
public class ClassService {
	
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private ClassUsersRepository classUsersRepository;
	
	@Autowired
	private TermRepository termRepository;
	
	@Autowired
	private TermService termService;
	
	public Integer createClass(ClassModel classModel) throws EntityAlreadyExistsException, ServiceException
	{
		Integer classId;
		try {
			System.out.println("ClassService: createClass");
			classId = classRepository.createClass((EntityClassModel) classModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		return classId;
	}
	
	public EntityClassModel findClassById(int classId){
		return classRepository.findClassById(classId);
	}
	
	public EntityClassModel findClassByName(String className){
		return classRepository.findClassByName(className);
	}
	/*
	public List<EntityClassModel> findClassByRange(Date startDate, Date endDate) {
		return classRepository.findClassByRange(startDate, endDate);
	}
	*/
	public List<EntityClassModel> getListOfClasses() {
		return classRepository.getListOfClasses();
	}
	
	/**
	 * Get the classes in current terms that have the user registered
	 * @param institutionId
	 * @param userId
	 * @param userType (text permission)
	 * @param now
	 * @return
	 */
	public List<EntityClassModel> getCurrentClassesForUser(Integer institutionId, Integer userId, String userType, Date now)
	{
		List<Integer> termIDs = termRepository.getCurrentTermIDs(institutionId, now);
		List<Integer> classIDs = classRepository.getClassIDsByTermIds(termIDs);
		return classRepository.getClassesByClassIDsAndUser(classIDs, userId, userType);
	}
	
	public List<EntityClassModel> getClassesForTerm(Integer termId, Integer userId, String userType, Date now)
	{
		List<Integer> classIDs = classRepository.getClassIDsByTermId(termId);
		return classRepository.getClassesByClassIDsAndUser(classIDs, userId, userType);
	}
	
	/**
	 * Get the classes in recent terms that have the user registered
	 * @param institutionId
	 * @param userId
	 * @param userType (text permission)
	 * @param priorTerms
	 * @return
	 */
	public List<EntityClassModel> getRecentClassesForUser(Integer institutionId, Integer userId, String userType, int priorTerms)
	{
		List<Integer> termIDs = termService.getListOfRecentTermIDs(institutionId, priorTerms);
		List<Integer> classIDs = classRepository.getClassIDsByTermIds(termIDs);
		return classRepository.getClassesByClassIDsAndUser(classIDs, userId, userType);
	}
	
	/**
	 * Get the classes in prior terms that have the user registered
	 * @param institutionId
	 * @param userId
	 * @param userType (text permission)
	 * @param now
	 * @param nPriorTerms should never be 0
	 * @return
	 */
	public List<EntityClassModel> getPriorClassesForUser(Integer institutionId, Integer userId, String userType, Date now, Integer nPriorTerms)
	{
		List<Integer> termIDs = termRepository.getPriorTermIDs(institutionId, now, nPriorTerms);
		List<Integer> classIDs = classRepository.getClassIDsByTermIds(termIDs);
		return classRepository.getClassesByClassIDsAndUser(classIDs, userId, userType);
	}
	
	public List<EntityClassModel> getListOfClassesByUserIdAndTeacherId(Integer institutionId, Integer studentId, Integer teacherId, String userType, Date now, Integer nPriorTerms)
	{
		// TODO This needs a good JUnit test
		// Get the classes in date range
		List<Integer> termIDs = termRepository.getCurrentTermIDs(institutionId, now);
		termIDs.addAll(termRepository.getPriorTermIDs(institutionId, now, nPriorTerms));
		List<Integer> classIDs = classRepository.getClassIDsByTermIds(termIDs);
		
		// Filter to get those including the teacher
		List<Integer> allTeacherClassIDs = classUsersRepository.getListOfClassIdsByUserId(teacherId);
		List<Integer> teacherClassIDs = new ArrayList<Integer>();
		for (Integer index = 0; index < classIDs.size(); index++)
		{
			if (allTeacherClassIDs.contains(classIDs.get(index)))
			{
				teacherClassIDs.add(classIDs.get(index));
			}
		}

		// Filter to get those including the student
		return classRepository.getClassesByClassIDsAndUser(teacherClassIDs, studentId, userType);
	}
	
	public List<EntityClassModel> getListOfClassesByTermId(Integer termid) {
		return classRepository.getListOfClassesByTermId(termid);
	}

	public List<EntityClassUsersModel> getListOfUsersForAllUserTypesByClass(Integer classId) {
		return classUsersRepository.getListOfUsersForAllUserTypesByClass(classId);
	}

	/**
	 * Get the class entities specified by the class IDs
	 * @param classIDs
	 * @return
	 * @throws Exception
	 */
	public List<EntityClassModel> getClassesByClassIDs(List<Integer> classIDs)
	{
		return classRepository.getClassesByClassIDs(classIDs);
	}
	
	public List<Integer> getListOfClassIdsByUserId(Integer studentId) {
		return classUsersRepository.getListOfClassIdsByUserId(studentId);
	}
	
	public List<EntityClassUsersModel> getListOfUsersInAClassByUserType(Integer classId, String userType) {

		List<EntityClassUsersModel> classusersbytypeList = 
				classUsersRepository.getListOfUsersInAClassByUserType(classId, userType);				
		return classusersbytypeList;
	}
	
	public List<Integer> getListOfUserIdsInAClassByUserType(Integer classId, String userType) {
		List<Integer> userIdByTypeList = 
				classUsersRepository.getListOfUserIdsInAClassByUserType(classId, userType);
		return userIdByTypeList;
	}
	
	public Long getCountByUserType(Integer classId, String userType)
	{
		Long count = classUsersRepository.getCountByUserType(classId, userType);
		return count;
	}
	
	public Long getCountOfUsers(Integer classId) throws Exception
	{
		Long count = classUsersRepository.getCountOfUsers(classId);
		return count;
	}
	
	/**
	 * Get the classes attached to the notification.
	 * @param notificationId
	 * @return
	 */
	public List<EntityClassModel> getClassesByNotificationId(Integer notificationId)
	{
		return classRepository.getClassesByNotificationId(notificationId);
	}
	
	/**
	 * Get the ID of the classes attached to the notification.
	 * @param notificationId
	 * @return
	 */
	public List<Integer> getClassIDsByNotificationId(Integer notificationId)
	{
		return classRepository.getClassIDsByNotificationId(notificationId);
	}
	
	/**
	 * Update an existing class entity
	 * SQL constraints may cause an exception
	 * @param classEntity
	 */
	public void updateClass(EntityClassModel classEntity)
	{
		classRepository.updateClass(classEntity);
	}
	
	public int addClassUser(EntityClassUsersModel cue)
	{
		return classUsersRepository.addClassUser(cue);
	}
	
	public void removeClassUser(Integer userid, Integer classid, String usertype)
	{
		classUsersRepository.removeClassUser(userid, classid, usertype);
	}
	
	public boolean isClassInTerm(Integer termId, String classStr)
	{
		return classRepository.isClassInTerm(termId, classStr);
	}
	
	public void deleteClass(int classId) throws Exception
	{
		classRepository.deleteClass(classId);
	}
}
