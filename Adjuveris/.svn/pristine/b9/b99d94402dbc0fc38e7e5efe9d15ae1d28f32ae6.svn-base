package com.linguaclassica.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityPermissionModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.entity.InstUserPermissionEntity;
import com.linguaclassica.model.InstUserPermissionModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.model.UserModel.SubsStatusType;
import com.linguaclassica.repository.InstUserPermissionRepository;
import com.linguaclassica.repository.UserRepository;
import com.linguaclassica.service.exception.CircumstanceException;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.UnknownEntityException;
import com.linguaclassica.service.exception.ServiceException;

@Service
@Transactional
public class UserService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private InstUserPermissionRepository iupRepository;
	
	
	public UserModel authenticateUser(String emailAddress, String password) 
			throws UnknownEntityException, ServiceException, CircumstanceException
	{
		try
		{
			// See if we find a matching user with the email address
			EntityUserModel userModel = userRepository.findUserByEmailAddress(emailAddress);
			
			System.out.println("got to UserService line 51, userModel.getPassword() = " + userModel.getPassword());

			// Check passwords match
			if (!userModel.passwordMatches(password)) {
				throw new UnknownEntityException("Passwords do not match");
			}
			return userModel;
		}
		catch (NoResultException e)
		{
			throw new UnknownEntityException(e);
		}
		catch (PersistenceException e)
		{
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Determine whether there are multiple permissions for the user
	 * @param userModel
	 * @return
	 */
	public boolean areMultiplePermissions(UserModel userModel)
	{
		return iupRepository.areMultiple(userModel.getId());
	}
	
	/**
	 * Add an authorization for the institution-user-permission
	 * when one does not already exist
	 * @param institutionId
	 * @param userModel
	 * @param permissionId
	 * @return
	 */
	public void addAuthorization(Integer institutionId, UserModel userModel, Integer permissionId)
	{
		if (!iupRepository.isExisting(institutionId, userModel.getId(), permissionId))
		{
			InstUserPermissionModel iup = modelFactory.getNewInstUserPermissionModel();
			iup.setInstitutionId(institutionId);
			iup.setUserId(userModel.getId());
			iup.setPermissionId(permissionId);
			iupRepository.createOne(iup);
		}
	}
	
	/**
	 * Get the institution user permission
	 * @param iupId
	 * @return
	 */
	public InstUserPermissionEntity getMultiPermissionItem(Integer iupId)
	{
		return iupRepository.getItem(iupId);
	}
	
	/**
	 * Get all items for the user.
	 * @param userId
	 * @return
	 */
	public List<InstUserPermissionEntity> getUserItems(Integer userId)
	{
		return iupRepository.getUserItems(userId);
	}

	/**
	 * This method is used to find a user by their email address.
	 * 
	 * If successful, the valid UserModel is returned. 
	 * 
	 * @param emailAddress
	 * @return
	 * @throws UnknownEntityException if the user cannot be found.
	 * @throws ServiceException if an unspecified error occurred.
	 */
	public UserModel findUserByEmailAddress(String emailAddress) throws UnknownEntityException, ServiceException {

		try {
			EntityUserModel userModel = userRepository.findUserByEmailAddress(emailAddress);
			
			// Got here so valid user
			return userModel;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method is used to find a user by their ID. If successful, a valid UserModel is returned.
	 * 
	 * @param userId
	 * @return
	 * @throws UnknownEntityException if the user cannot be found.
	 * @throws ServiceException if an unspecified error occured.
	 */
	public UserModel findUserById(Integer userId) throws UnknownEntityException, ServiceException {
		System.out.println("UserService.findUserById:  userId = " + userId);
		EntityUserModel user = userRepository.findUserById(userId);
		
		// Got here so must have a valid user
		return user;
	}
	
	/**
	 * Get the users that match the institution and Permission
	 * @param institutionId
	 * @param permission
	 * @return
	 */
	public List<EntityUserModel> getUsersByPermission(Integer institutionId, Permission permission)
	{
		Integer permissionId = getPermissionModel(permission).getId();
		List<Integer> userIdList = iupRepository.getUserIds(institutionId, permissionId);
		return getListOfStudentsByIdList(userIdList);
	}
	
	/**
	 * Creates a user with permission for an institution
	 * @param userModel
	 * @param permission
	 */
	public void createUser(UserModel userModel, Permission permission, int institutionId)
	{
		userRepository.createUser((EntityUserModel) userModel, permission, institutionId);
		
		// Attach the user to an institution and a permission
		addAuthorization(institutionId, userModel, userRepository.getPermissionModel(permission).getId());
	}
	
	/**
	 * Update just the password for the user
	 * @param userId
	 * @param password
	 */
	public void updatePassword(int userId, String password)
	{
		userRepository.updatePassword(userId, password);
	}
	
	public void updateUser(EntityUserModel userModel) {
		try {
			userRepository.updateUser(userModel);
		}
		catch (NoResultException nor) {
			throw new NoResultException();
		}
		catch (EntityAlreadyExistsException eae) {
			throw new EntityAlreadyExistsException();
		}
	}

	/**
	 * Gets the permission model
	 * @param permission Permission as enum
	 * @return
	 */
	public EntityPermissionModel getPermissionModel(Permission permission)
	{
		return userRepository.getPermissionModel(permission);
	}

	/**
	 * Update of the user's subscription status by an institutional administrator; userId must already exist
	 * @param userId
	 * @param status
	 */
	public void updateSubsStatus(Integer userId, SubsStatusType status)
	{
		userRepository.updateSubsStatus(userId, status);
	}
		
	/**
	 * This method retrieves a list of email addresses 
	 */
	public List<String> getListOfEmails() {
		
		return userRepository.getListOfEmails();
	}
	
	/**
	 * This method retrieves a list of email addresses 
	 */
	public List<EntityUserModel> getListOfUsersByEmail(String emailaddress) {
	
		return userRepository.getListOfUsersByEmail(emailaddress);
	}
	
	public List<EntityUserModel> getListOfStudentsByIdList(List<Integer> studentIdList) {
		return userRepository.getListOfStudentsByIdList(studentIdList);
	}
	
	public Long getNumberOfUsersWithoutGroup(Integer year, Integer month){
			
		Long numberOfUsersWithoutGroup = 0L;
		
		// Retrieve the list of users in the system
		
		//List<EntityUserModel> list = userRepository.getListOfUsers(year, month);
		
		return numberOfUsersWithoutGroup;
	}

	/**
	 * Determine whether the email address is used by a current user
	 * @param emailaddressIn
	 * @return
	 */
	public List<EntityUserModel> checkDuplicates(String emailaddressIn) {
		List<EntityUserModel> existingUserList = userRepository.checkDuplicates(emailaddressIn);
		return existingUserList;
	}

	/**
	 * Determine whether the user repository is using the email address
	 * @param emaddr
	 * @return
	 */
	public boolean usingEmail(String emaddr)
	{
		return userRepository.usingEmail(emaddr);
	}
}
