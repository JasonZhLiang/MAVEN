package com.linguaclassica.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityGroupModel;
import com.linguaclassica.entity.EntityUsersGroupsModel;
import com.linguaclassica.repository.GroupRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;

@Service
@Transactional
public class GroupService {
	
	@Autowired
	private GroupRepository groupRepository;
	
	/**
	 * This method creates a group
	 * @param groupModel
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void createGroup(EntityGroupModel groupModel) 
		throws EntityAlreadyExistsException, ServiceException {			
		try {
			System.out.println("GroupService: createGroup");
			groupRepository.createGroup(groupModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method finds a group by its unique id
	 * @param id
	 * @return EntityGroupModel
	 */
	public EntityGroupModel findGroupById(Integer id) {
		return groupRepository.findGroupById(id);
	}
	
	/**
	 * This method finds a list of groups created with a specific user id
	 * @param createdById
	 * @return List<EntityGroupModel>
	 */
	public List<EntityGroupModel> findGroupsByCreatedById(Integer createdById) {
		return groupRepository.findGroupsByCreatedById(createdById);
	}
	
	/**
	 * This method adds a user to a group
	 * @param usersGroupsModel
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void addUserToGroup(EntityUsersGroupsModel usersGroupsModel) 
		throws EntityAlreadyExistsException, ServiceException {			
		try {
			System.out.println("GroupService: addUserToGroup");
			groupRepository.addUserToGroup(usersGroupsModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method finds a list of group ids a user is a member of
	 * @param userId
	 * @return List<EntityUsersGroupsModel>
	 */
	public List<EntityUsersGroupsModel> findGroupsByUserId(Integer userId) {
		//System.out.println("GroupService: findGroupsByUserId");
		return groupRepository.findGroupsByUserId(userId);
	}
	
	/**
	 * This method finds a list of user ids that are part of a group
	 * @param groupId
	 * @return List<EntityUsersGroupsModel>
	 */
	public List<EntityUsersGroupsModel> findUsersByGroupId(Integer groupId) {
		return groupRepository.findUsersByGroupId(groupId);
	}

}
