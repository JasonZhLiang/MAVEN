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

import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.model.ClassModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class ClassRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * This method creates a class. If no exceptions are thrown the method is successful.
	 * @param classModel
	 * @throws EntityExistsException
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public Integer createClass(EntityClassModel classModel) 
			throws EntityExistsException, PersistenceException {

		Integer classId;
		try {
			entityManager.persist(classModel);
			entityManager.refresh(classModel);
			classId = classModel.getId();
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		return classId;
	}
	
	/**
	 * This method is used to find a class by its id.
	 * If successful, the valid ClassModel is returned.
	 * @param id
	 * @return
	 * @throws NoResultException if the class cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityClassModel findClassById(int id) 
			throws NoResultException, PersistenceException {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex from EntityClassModel ex where ex.id = :id");
			query.setParameter("id", id);
			
			EntityClassModel classModel = (EntityClassModel) query.getSingleResult();
			
			System.out.println("ClassRepository.findClassById.getClassName() = " + classModel.getClassname());
			
			return classModel;
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * This method is used to find a class by its name.
	 * If successful, the valid ClassModel is returned.
	 * @param id
	 * @return EntityClassModel
	 * @throws NoResultException if the class cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityClassModel findClassByName(String className) 
			throws NoResultException, PersistenceException {		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex from EntityClassModel ex where ex.classname = :className");
			query.setParameter("className", className);
			
			EntityClassModel classModel = (EntityClassModel) query.getSingleResult();
			
			System.out.println("ClassRepository.findClassByName.getId() = " + classModel.getId());
			
			return classModel;
		
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method is used to find class within a date range.
	 * If successful, the valid ClassModel is returned.
	 * @param startDate
	 * @param endDate
	 * @return List<EntityClassModel>
	 */ /*
	@SuppressWarnings("unchecked")
	public List<EntityClassModel> findClassByRange(Date startDate, Date endDate) {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex from EntityClassModel ex where ex.startdate >= :startDate "
					+ "and ex.enddate <= :endDate");
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			
			List<EntityClassModel> classList = new ArrayList<EntityClassModel>();
	
			classList = (List<EntityClassModel>) query.getResultList();
			
			System.out.println("ClassRepository.findClassByRange.get(0).getClassname() = " + classList.get(0).getClassname());
			
			return classList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}*/

	/**
	 * This method retrieves the list of class
	 * If no exceptions are thrown the method is successful.
	 * @return List<EntityClassModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityClassModel> getListOfClasses() {
		try {
			Query query = entityManager.createQuery(
				"SELECT ex FROM EntityClassModel ex ORDER BY ex.classname ASC");
			
			List<EntityClassModel> classList = new ArrayList<EntityClassModel>();
	
			classList = (List<EntityClassModel>) query.getResultList();
			
			int classlistlen = classList.size();
			if (classlistlen > 0) {
				System.out.println("ClassRepository.getListOfClass.get(0).getClassname() = " + 
						classList.get(0).getClassname());
			}			
			
			return classList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the class entities specified by the class IDs
	 * @param classIDs
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EntityClassModel> getClassesByClassIDs(List<Integer> classIDs)
	{
		List<EntityClassModel> classeslist = new ArrayList<EntityClassModel>();
		if ((classIDs != null) && (classIDs.size() > 0))
		{
			Query query = entityManager.createQuery("SELECT ex from EntityClassModel ex where ex.id IN (:classIds)");
			query.setParameter("classIds", classIDs);
			classeslist = query.getResultList();
		}
		return classeslist;
	}
	
	/**
	 * Get the classes that are in the list and the user is in the class
	 * @param classIDs
	 * @param userId
	 * @param userType (text permission)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EntityClassModel> getClassesByClassIDsAndUser(List<Integer> classIDs, Integer userId, String userType)
	{
		List<EntityClassModel> classeslist = new ArrayList<EntityClassModel>();
		if (classIDs.size() > 0)
		{
			Query query = entityManager.createQuery("SELECT ecm from EntityClassModel ecm, EntityClassUsersModel ecum " + 
					"WHERE ecm.id IN :classIds AND ecum.classid = ecm.id AND ecum.userid = :userID AND ecum.usertype = :userType");
			query.setParameter("classIds", classIDs);
			query.setParameter("userID", userId);
			query.setParameter("userType", userType);
			classeslist = query.getResultList();
		}
		return classeslist;
	}

	/**
	 * This method retrieves the list of classes for a term.
	 * If no exceptions are thrown the method is successful.
	 * @param termid  
	 * @return List<EntityClassModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityClassModel> getListOfClassesByTermId(Integer termId) {

		try {
			List<EntityClassModel> classlist = new ArrayList<EntityClassModel>();
		
			System.out.println("got to ClassRepository line 211, termId = " + termId);
		
			Query query = null;
			query = entityManager.createQuery("SELECT ex from EntityClassModel ex where ex.termid = (:termid)");
			query.setParameter("termid", termId);	
			classlist = query.getResultList();
		
			if (classlist.size() > 0) {
				System.out.println("classlist.get(0).getClassname() = " + classlist.get(0).getClassname());
			}	
						
			return classlist;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Get the classes attached to the notification.
	 * There should always be at least one attached class.
	 * @param notificationId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EntityClassModel> getClassesByNotificationId(Integer notificationId)
	{
		List<EntityClassModel> classesList = new ArrayList<EntityClassModel>();

		Query query = entityManager.createQuery(
				"SELECT ce FROM EntityClassModel ce, EntityNotificationClassesModel nce " + 
				"WHERE ce.id = nce.classid AND nce.notificationid = :notificationId");
		query.setParameter("notificationId", notificationId);
		classesList = (ArrayList<EntityClassModel>) query.getResultList();	
		return classesList;
	}

	/**
	 * Get the ID of the classes attached to the notification.
	 * @param notificationId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getClassIDsByNotificationId(Integer notificationId)
	{
		List<Integer> classIDsList = new ArrayList<Integer>();

		Query query = entityManager.createQuery(
				"SELECT ce.id FROM EntityClassModel ce, EntityNotificationClassesModel nce " + 
				"WHERE ce.id = nce.classid AND nce.notificationid = :notificationId");
		query.setParameter("notificationId", notificationId);
		classIDsList = (ArrayList<Integer>) query.getResultList();	
		return classIDsList;
	}

	/**
	 * Get the IDs of the classes attached to any of the terms
	 * @param termIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getClassIDsByTermIds(List<Integer> termIds)
	{
		List<Integer> classIDsList = new ArrayList<Integer>();
		if (termIds.size() > 0)
		{
			Query query = entityManager.createQuery("SELECT ce.id FROM EntityClassModel ce " +
					"WHERE ce.termid IN :termIDs");
			query.setParameter("termIDs", termIds);
			classIDsList = query.getResultList();
		}
		return classIDsList;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Integer> getClassIDsByTermId(Integer termIds)
	{
		List<Integer> classIDsList = new ArrayList<Integer>();
			Query query = entityManager.createQuery("SELECT ce.id FROM EntityClassModel ce " +
					"WHERE ce.termid IN :termIDs");
			query.setParameter("termIDs", termIds);
			classIDsList = query.getResultList();
		return classIDsList;
	}
	
	
	
	

	/**
	 * Update an existing class entity
	 * SQL constraints may cause an exception
	 * @param classEntity
	 * Note: persisting the passed class entity causes a weird exception (bug 375).
	 */
	public void updateClass(EntityClassModel classEntity)
	{
		try
		{
			ClassModel classModel = entityManager.find(EntityClassModel.class, classEntity.getId());
			classModel.setClassname(classEntity.getClassname());
			classModel.setClasscode(classEntity.getClasscode());
			classModel.setTermid(classEntity.getTermid());
			entityManager.persist(classModel);
			entityManager.flush();
		}
		catch (PersistenceException pe)
		{
			if (pe.getCause() instanceof ConstraintViolationException)
			{
				throw new EntityExistsException(pe);
			}
		}
	}
	
	/**
	 * This method checks if the specified class string is present in the term indicated by termId.
	 * The method returns true if the class was found, and false if a match was not found.
	 * @param termId
	 * @param classStr
	 * @return boolean
	 */
	public boolean isClassInTerm(Integer termId, String classStr)
	{
		try {
			System.out.println("ClassService.isClassInTerm(), termId=" + termId + " , classStr=" + classStr);

			Query query = entityManager.createQuery("SELECT ex from EntityClassModel ex where ex.classname = :className and ex.termid = :termId");
			query.setParameter("className", classStr);
			query.setParameter("termId", termId);
				
			@SuppressWarnings("unused")
			EntityClassModel classModel = (EntityClassModel) query.getSingleResult();
				
			// If we get here we found a result, meaning this class is defined in this term 
			return true;
		}
		catch (NoResultException e) {
			// This exception is thrown by getSingleResult if a match cannot be found.
			return false;
		} catch (Exception e) {
			System.out.println("ClassRepository.isClassInTerm Exception event");
			throw e;
		}
	}

	/**
	 * Remove the specified class entity
	 * @param classId
	 * @throws Exception
	 */
	public void deleteClass(int classId) throws Exception
	{
		Query query = entityManager.createQuery(
				"SELECT ce FROM EntityClassModel ce WHERE ce.id = :id");
		query.setParameter("id", classId);
		EntityClassModel classEntity = (EntityClassModel) query.getSingleResult();
		entityManager.remove(classEntity);
	}
}
