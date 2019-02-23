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

import com.linguaclassica.entity.EntityClassUsersModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class ClassUsersRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * This method retrieves the list of class ids for a user.
	 * If no exceptions are thrown the method is successful.
	 * @param userId
	 * @return List<EntityClassUsersModel>
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfClassIdsByUserId(Integer userId) {
		List<Integer> classidList = null;
		try {
			Query query = entityManager.createQuery("SELECT e.classid FROM EntityClassUsersModel e WHERE e.userid = :userId");
			
			query.setParameter("userId", userId);
			
			// classidList = new ArrayList<Integer>();
	
			classidList = query.getResultList();	
			
			//System.out.println("ClassUsersRepository: classidList.get(0) = " + classidList.get(0));			
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return classidList;
	}
	/**
	 * This method retrieves the list of class ids for a user.
	 * If no exceptions are thrown the method is successful.
	 * @param userId,userType
	 * @return List<EntityClassUsersModel>
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfClassIdsByUserId(Integer userId,String userType) {
		List<Integer> classidList = null;
		try {
			
			Query query = entityManager.createQuery(
					"SELECT cu.classid FROM EntityClassUsersModel cu " + 
					"WHERE cu.userid = :userId AND cu.usertype = :userType");
				query.setParameter("userId", userId);
				query.setParameter("userType", userType);
			
			classidList = query.getResultList();	
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return classidList;
	}
	
	/**
	 * This method retrieves the list of user ids for a user type (STUDENT, TA, TEACHER) 
	 * and for a specific classid.
	 * If no exceptions are thrown the method is successful.
	 * @param classId
	 * @param userType
	 * @return List<EntityClassUsersModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityClassUsersModel> getListOfUsersInAClassByUserType(Integer classId, String userType) {
		try {
			Query query = entityManager.createQuery(
				"SELECT cu FROM EntityClassUsersModel cu WHERE cu.classid = :classId and cu.usertype = :userType");
			System.out.println("ClassUsersRepository, line 63: userType = " + userType);
			query.setParameter("classId", classId);
			query.setParameter("userType", userType);
			
			List<EntityClassUsersModel> userIdList = new ArrayList<EntityClassUsersModel>();
	
			userIdList = (List<EntityClassUsersModel>) query.getResultList();	
			
			return userIdList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfUserIdsInAClassByUserType(Integer classId, String userType) {
		try {
			System.out.println("ClassUsersRepository, line 83: userType = " + userType);
			Query query = entityManager.createQuery(
				"SELECT cu.userid FROM EntityClassUsersModel cu WHERE cu.classid = :classId and cu.usertype = :userType");
		
			query.setParameter("classId", classId);
			query.setParameter("userType", userType);
			
			List<Integer> userIdList = new ArrayList<Integer>();
			userIdList = (List<Integer>) query.getResultList();
			
			return userIdList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Get the number of users with the specified permission within a single class.
	 * @param classId
	 * @param userType
	 * @return
	 */
	public Long getCountByUserType(Integer classId, String userType)
	{
		System.out.println("getCountByUserType(class " + classId + ", " + userType + ")");
		Long participants = 0L;
		try
		{
			Query query = entityManager.createQuery(
					"SELECT COUNT(cu) FROM EntityClassUsersModel cu " + 
					"WHERE cu.classid = :classId and cu.usertype = :userType");
			query.setParameter("classId", classId);
			query.setParameter("userType", userType);
			participants = (Long) query.getSingleResult();
		}
		catch (PersistenceException e)
		{
			throw e;
		}
		return participants;
	}
	
	/**
	 * Get the number of users, of all permissions, within a single class.
	 * @param classId
	 * @return
	 */
	public Long getCountOfUsers(Integer classId) throws Exception
	{
		Long participants = 0L;

		Query query = entityManager.createQuery(
				"SELECT COUNT(cu) FROM EntityClassUsersModel cu " + 
				"WHERE cu.classid = :classId");
		query.setParameter("classId", classId);
		participants = (Long) query.getSingleResult();
		return participants;
	}
	
	/**
	 * This method retrieves the list of user ids for ALL user types (STUDENT, TA, TEACHER) 
	 * and for a specific classid.
	 * If no exceptions are thrown the method is successful.
	 * @param classId
	 * @param userType
	 * @return List<EntityClassUsersModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityClassUsersModel> getListOfUsersForAllUserTypesByClass(Integer classId) {
		try {
			Query query = entityManager.createQuery(
				"SELECT cu FROM EntityClassUsersModel cu WHERE cu.classid = :classId");
		
			query.setParameter("classId", classId);
			
			List<EntityClassUsersModel> userIdList = new ArrayList<EntityClassUsersModel>();
	
			userIdList = (List<EntityClassUsersModel>) query.getResultList();	
			
			System.out.println("ClassRepository.getListOfUsersForAllUserTypesByClass(0).getUserId() = " 
					+ userIdList.get(0).getUserId());
			System.out.println("ClassRepository.getListOfUsersForAllUserTypesByClass(0).getUserType() = " 
					+ userIdList.get(0).getUserType());
			System.out.println("ClassRepository.getListOfUsersForAllUserTypesByClass(0).getClassId() = " 
					+ userIdList.get(0).getClassId());
			
			return userIdList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfClassIdsByStudentId(Integer studentId) {
		try {
			Query query = entityManager.createQuery(
				"SELECT cu.classid FROM EntityClassUsersModel cu WHERE cu.userid = :userId");
		
			query.setParameter("userId", studentId);
			
			List<Integer> classIdList = new ArrayList<Integer>();
	
			classIdList = (List<Integer>) query.getResultList();	
			
			System.out.println("ClassUsersRepository.getListOfClassIdsByStudentId.get(0).getUserId() = " 
					+ classIdList.get(0));
			
			return classIdList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Add a user entity to the table
	 * @param classuserEntity
	 * @return
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public int addClassUser(EntityClassUsersModel classuserEntity) 
			throws EntityExistsException, PersistenceException
	{
		int cuId = 0;
		try
		{
			entityManager.persist(classuserEntity);
			entityManager.refresh(classuserEntity);
			cuId = classuserEntity.getId();
		}
		catch (PersistenceException e)
		{
			if (e.getCause() instanceof ConstraintViolationException)
			{
				throw new EntityExistsException(e);
			}
		}
		return cuId;
	}

	/**
	 * Remove one class-user from the table
	 * @param userid
	 * @param classid
	 * @param usertype
	 */
	public void removeClassUser(Integer userid, Integer classid, String usertype)
	{
		System.out.println("ClassUsersRepository.removeClassUser(user " + userid + ", class " + classid + ")");
		try
		{
			Query query = entityManager.createQuery(
				"DELETE FROM EntityClassUsersModel cu " + 
				"WHERE cu.userid = :userId AND cu.classid = :classId AND cu.usertype = :userType");
			query.setParameter("userId", userid);
			query.setParameter("classId", classid);
			query.setParameter("userType", usertype);
			query.executeUpdate();
		}
		catch (NoResultException e)
		{
			throw new UnknownEntityException(e);
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}
	
}