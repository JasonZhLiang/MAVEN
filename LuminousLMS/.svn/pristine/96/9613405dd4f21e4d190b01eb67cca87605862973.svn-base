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
import com.linguaclassica.entity.OrgUserPermissionEntity;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.OrgUserPermissionModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.repository.OrgUserPermissionRepository;
import com.linguaclassica.repository.PermissionRepository;
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
	private OrgUserPermissionRepository oupRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	public UserModel authenticateUser(String emailAddress, String password) 
			throws UnknownEntityException, ServiceException, CircumstanceException
	{
		try
		{
			// See if we find a matching user with the email address
			EntityUserModel userModel = userRepository.findUserByEmailAddress(emailAddress);
			
			System.out.println("got to UserService line 51, userModel.getPassword() = " + userModel.getPassword());

			// Check status
			if (userModel.getStatus()> 0) {
					throw new CircumstanceException("User is not active");
			}
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
	 * areMultiplePermissions
	 * @param userModel
	 * @return
	 */
	public boolean areMultiplePermissions(UserModel userModel)
	{
		return oupRepository.areMultiple(userModel.getId());
	}
	
	/**
	 * Add an authorization for the organization-user-permission
	 * when one does not already exist
	 * @param organizationId
	 * @param userModel
	 * @param permissionId
	 */
	public void addAuthorization(Integer organizationId, UserModel userModel, Integer permissionId)
	{
		if (!oupRepository.isExisting(organizationId, userModel.getId(), permissionId))
		{
			OrgUserPermissionModel oup = modelFactory.getNewOrgUserPermissionModel();
			oup.setOrganizationId(organizationId);
			oup.setUserId(userModel.getId());
			oup.setPermissionId(permissionId);
			oupRepository.createOne(oup);
		}
	}
	
	/**
	 * userModel
	 * @param userId
	 * @return
	 */
	public List<OrgUserPermissionEntity> getUserItems(Integer userId)
	{
		return oupRepository.getUserItems(userId);
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
	 * @throws ServiceException if an unspecified error occurred.
	 */
	public UserModel findUserById(Integer userId) throws UnknownEntityException, ServiceException {
		System.out.println("UserService.findUserById:  userId = " + userId);
		EntityUserModel user = userRepository.findUserById(userId);
		
		// Got here so must have a valid user
		return user;
	}
	
	/**
	 * Creates a user with permission for an organization
	 * @param userModel
	 * @param permission
	 */
	public void createUser(UserModel userModel, Permission permission, int organizationId)
	{
		userRepository.createUser((EntityUserModel) userModel);
		
		// Attach the user to an organization and a permission
		addAuthorization(organizationId, userModel, permissionRepository.getPermissionModel(permission).getId());
	}
	
	public void updatePassword(int userId, String password) {
		try {
			userRepository.updatePassword(userId, password);
		}
		catch (NoResultException nor) {
			throw new NoResultException();
		}
	}
	public void updateQuestion(Integer userId, String question)
	{
		try {
			userRepository.updateQuestion(userId, question);
		}
		catch (NoResultException nor) {
			throw new NoResultException();
		}
	}
	
	public void updateAnswer(Integer userId, String answer)
	{
		try {
			userRepository.updateAnswer(userId, answer);
		}
		catch (NoResultException nor) {
			throw new NoResultException();
		}
	}
	
	/**
	 * Update just the email address for the user
	 * @param userId
	 * @param emailAddress
	 */
	public void updateEmailaddress(Integer userId, String emailAddress)
	{
		userRepository.updateEmailaddress(userId, emailAddress);
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
	 * Returns friendly permission name
	 * @param userId
	 * @return
	 */ /*
	public String getUserPermissionString(Integer userId)
	{
		return userRepository.getUserPermissionString(userId);
	} */

	/**
	 * Gets user's permission
	 * @param userId as Permission
	 * @return
	 */ /*
	public Permission getUserPermission(Integer userId)
	{
		return userRepository.getUserPermission(userId);
	} */
	
		
	/**
	 * This method retrieves a list of email addresses 
	 */
	public List<String> getListOfEmails() {
		
		return userRepository.getListOfEmails();
	}
	
	/**
	 * This method retrieves a list of email adddresses 
	 */
	public List<EntityUserModel> getListOfUsersByEmail(String emailaddress) {
	
		return userRepository.getListOfUsersByEmail(emailaddress);
	}
	
	public List<EntityUserModel> getListOfUserssByIdList(List<Integer> studentIdList) {
		return userRepository.getListOfUsersByIdList(studentIdList);
	}
	
	/**
	 * Gets a list of users' accounts by permission
	 * Used to process metrics for the system
	 */
	public List<EntityUserModel> getListOfUsersByPermission(int orgid, Permission permission){
		return userRepository.getListOfUsersByPermission(orgid, permission);	
				
	}
	
	/**
	 * Get the users with a status of active
	 * @param organizationId
	 * @param permission
	 * @return
	 */
	public List<EntityUserModel> getListOfActiveUsersByPermission(int organizationId, Permission permission)
	{
		return userRepository.getListOfActiveUsersByPermission(organizationId, permission);	
	}
	
	/**
	 * Gets the permission model
	 * @param permission Permission as enum
	 * @return
	 */
	public EntityPermissionModel getPermissionModel(Permission permission)
	{
		return permissionRepository.getPermissionModel(permission);
	}

	public Long getNumberOfUsersWithoutGroup(Integer year, Integer month){
			
		Long numberOfUsersWithoutGroup = 0L;
		
		// Retrieve the list of users in the system
		
		//List<EntityUserModel> list = userRepository.getListOfUsers(year, month);
		
		return numberOfUsersWithoutGroup;
	}
	
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
	
	/**
	 * Return the list of clients of a consultant
	 * @param orgid
	 * @param consultantid
	 */
	public List<EntityUserModel> getListOfClientsOfAConsultant(int orgid, int consultantid)
	{
		return userRepository.getListOfClientsOfAConsultant(orgid, consultantid);
	}
	
	
	/**
	 * Return the list of consultants of a client
	 * @param orgid
	 * @param clientid
	 */
	public List<EntityUserModel> getListOfConsultantOfAClient(int orgid, int clientid)
	{
		return userRepository.getListOfConsultantOfAClient(orgid, clientid);
	}
	
	/**
	 * Return the currently assigned consultant for a client
	 * @param orgid
	 * @param clientid
	 */
	public EntityUserModel getAssignedConsultantOfAClient(int orgid, int clientid)
	{
		return userRepository.getAssignedConsultantOfAClient(orgid, clientid);
	}
	
	/**
	 * Return the list of unassigned clients
	 * @param orgid
	 */
	public List<EntityUserModel> getListOfUnassignedClients(int orgid)
	{
		return userRepository.getListOfUnassignedClients(orgid);
	}
	
	/**
	 * Return the list of unassigned clients
	 * @param orgid
	 */
	public List<EntityUserModel> getListOfUnassignedActiveClients(int orgid)
	{
		return userRepository.getListOfUnassignedActiveClients(orgid);
	}


	/**
	 * Return the list of unassigned consultants
	 * @param orgid
	 */
	public List<EntityUserModel> getListOfUnassignedConsultants(int orgid)
	{
		return userRepository.getListOfUnassignedConsultants(orgid);
	}
}
