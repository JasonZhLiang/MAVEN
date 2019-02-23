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

import com.linguaclassica.entity.EntityGroupModel;
import com.linguaclassica.entity.EntityUsersGroupsModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class GroupRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * Create a group
	 * @param groupModel
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void createGroup(EntityGroupModel groupModel) 
			throws EntityExistsException, PersistenceException {
		try {
			System.out.println("GroupRepository.createGroup()");
			entityManager.persist(groupModel);
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
	 * Find a group by its unique id
	 * @param id
	 * @return EntityGroupModel
	 * @throws NoResultException
	 * @throws PersistenceException
	 */
	public EntityGroupModel findGroupById(Integer id) 
			throws NoResultException, PersistenceException {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityGroupModel ex WHERE ex.id = :id");
			query.setParameter("id", id);
			
			EntityGroupModel groupModel = (EntityGroupModel) query.getSingleResult();
			
			System.out.println("NotificationRepository.findGroupById.getGroupName() = " + groupModel.getGroupName());
			
			return groupModel;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Finds a list of groups created with a particular user id
	 * @param createdById
	 * @return List<EntityGroupModel>
	 * @throws NoResultException
	 * @throws PersistenceException
	 */
	@SuppressWarnings("unchecked")
	public List<EntityGroupModel> findGroupsByCreatedById(Integer createdById) 
			throws NoResultException, PersistenceException {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityGroupModel ex WHERE ex.createdbyid = :createdById");
			query.setParameter("createdById", createdById);
			
			List<EntityGroupModel> groupList = new ArrayList<EntityGroupModel>();
			
			groupList = (List<EntityGroupModel>) query.getResultList();
			
			System.out.println("GroupRepository.findGroupsByCreatedById.get(0).getGroupName() = " + groupList.get(0).getGroupName());
			
			return groupList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Adds a user to a group
	 * @param usersGroupsModel
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void addUserToGroup(EntityUsersGroupsModel usersGroupsModel) 
			throws EntityExistsException, PersistenceException {
		try {
			System.out.println("GroupRepository.addUserToGroup()");
			entityManager.persist(usersGroupsModel);
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
	 * Finds the groups to which a user has been assigned
	 * @param userId
	 * @return List<EntityUsersGroupsModel>
	 * @throws NoResultException
	 * @throws PersistenceException
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUsersGroupsModel> findGroupsByUserId(Integer userId) 
			throws NoResultException, PersistenceException {	
		
		try {
			//System.out.println("userId: " + userId);

			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityUsersGroupsModel ex WHERE ex.userid = :userId");
			query.setParameter("userId", userId);
			
			List<EntityUsersGroupsModel> userGroupList = new ArrayList<EntityUsersGroupsModel>();
			userGroupList = (List<EntityUsersGroupsModel>) query.getResultList();
			//System.out.println("GroupRepository.findGroupsByUserId");
			
			return userGroupList;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Finds the users that have been assigned to a group
	 * @param groupId
	 * @return List<EntityUsersGroupsModel>
	 * @throws NoResultException
	 * @throws PersistenceException
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUsersGroupsModel> findUsersByGroupId(Integer groupId) 
			throws NoResultException, PersistenceException {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityUsersGroupsModel ex WHERE ex.groupid = :groupId");
			query.setParameter("groupId", groupId);
			
			List<EntityUsersGroupsModel> userGroupList = new ArrayList<EntityUsersGroupsModel>();
			
			userGroupList = (List<EntityUsersGroupsModel>) query.getResultList();
			
			//System.out.println("GroupRepository.findUsersByGroupId.get(0).getUserId() = " + userGroupList.get(0).getUserId());
			
			return userGroupList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
