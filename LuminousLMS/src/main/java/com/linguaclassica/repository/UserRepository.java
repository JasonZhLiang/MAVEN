package com.linguaclassica.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.entity.EntityUserPermissionModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;


@Repository
public class UserRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	private PermissionRepository permissionRepository;

	/**
	 * This method is used to find a user by their email address.
	 * 
	 * If successful, the valid UserModel is returned.
	 * 
	 * @param emailAddress
	 * @return
	 * @throws NoResultException if the user cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityUserModel findUserByEmailAddress(String emailAddress) throws NoResultException, PersistenceException {
		Query query = entityManager.createQuery("SELECT u from EntityUserModel u where u.emailAddress LIKE :emailAddress");
		query.setParameter("emailAddress", emailAddress);
		EntityUserModel userModel = (EntityUserModel) query.getSingleResult();
		
		// Got here so valid user
		return userModel;
	}
	
	/**
	 * This method is used to find a user by their ID. If successful, a valid UserModel is returned.
	 * 
	 * @param userId
	 * @return
	 * @throws NoResultException if the user cannot be found.
	 * @throws PersistenceException if an unspecified error occured.
	 */
	public EntityUserModel findUserById(Integer userId) throws NoResultException, PersistenceException {
				
		UserModel user = entityManager.find(EntityUserModel.class, userId);
		
		// Check for user not found
		if (user == null) {
			throw new NoResultException("User does not exist");
		}
		
		// Got here so must have a valid user
		return (EntityUserModel) user;
	}
	
	/**
	 * Create a user.
	 * @param userModel
	 * @return
	 */
	public Integer createUser(EntityUserModel userModel)
	{		
		entityManager.persist(userModel);
		entityManager.refresh(userModel);
		Integer userId = userModel.getId();
		return userId;
	}
	
	/**
	 * Update just the email address for the user
	 * @param userId
	 * @param password
	 * @throws NoResultException
	 * @author Jay
	 */
	public void updateEmailaddress(Integer userId, String emailAddress)
	{
		UserModel user = entityManager.find(EntityUserModel.class, userId);	
		user.setEmailAddress(emailAddress);	
		entityManager.persist(user);
	}
	
	/**
	 * Update just the password for the user
	 * @param userId
	 * @param password
	 * @throws NoResultException
	 */
	public void updatePassword(Integer userId, String password)
	{
		UserModel user = entityManager.find(EntityUserModel.class, userId);
		user.setPassword(password);
		entityManager.persist(user);
	}
	/**
	 * Update just the question for the user
	 * @param userId
	 * @param password
	 * @throws NoResultException
	 */
	public void updateQuestion(Integer userId, String question)
	{
		UserModel user = entityManager.find(EntityUserModel.class, userId);
		user.setQuestion(question);
		entityManager.persist(user);
	}
	/**
	 * Update just the answer for the user
	 * @param userId
	 * @param password
	 * @throws NoResultException
	 */
	public void updateAnswer(Integer userId, String answer)
	{
		UserModel user = entityManager.find(EntityUserModel.class, userId);
		user.setAnswer(answer);
		entityManager.persist(user);
	}
	
	public void updateUser(EntityUserModel userModel) {
		System.out.println("UserRepository, line 127: userModel.getEmailAddress() = " + userModel.getEmailAddress());
		if (userModel.getPassword() == null) {
			try {
				UserModel user = entityManager.find(EntityUserModel.class, userModel.getId());
				userModel.setPassword(user.getPassword());				
			}
			catch (NoResultException nor) {
				throw new NoResultException();
			}
		}
		
		try {
			
			UserModel user = entityManager.find(EntityUserModel.class,userModel.getId());
			user.setFirstName(userModel.getFirstName());
			user.setLastName(userModel.getLastName());
			user.setEmailAddress(userModel.getEmailAddress());
			user.setQuestion(userModel.getQuestion()); 
			user.setAnswer(userModel.getAnswer());
			user.setPassword(userModel.getPassword());
			user.setStatus(userModel.getStatus());   //Carol 2017-11-19 change the status of the user
			
			entityManager.persist(user);
			System.out.println("UserRepository.updateUser:  user.getEmailAddress() = " + 
					user.getEmailAddress());
		}
		catch (NoResultException nor) {
			throw new NoResultException();
		}
		catch (EntityAlreadyExistsException eae) {
			throw new EntityAlreadyExistsException();
		}
	}
	
	/**
	 * This method retrieves a list of email addresses
	 */
	public List<String> getListOfEmails() {
		
		TypedQuery<String> query = entityManager.createQuery("SELECT usr.emailAddress from EntityUserModel usr", 
				String.class);
		return query.getResultList();
	}

	/**
	 * Returns friendly permission name
	 * @param userId
	 * @return
	 */ /*
	public String getUserPermissionString(Integer userId)
	{
		Permission permission = getUserPermission(userId);
		return EntityPermissionModel.getPermissionString(permission);
	}*/

	/**
	 * Gets user's permission
	 * @param userId as Permission
	 * @return
	 */ /*
	public Permission getUserPermission(Integer userId)
	{
		TypedQuery<Permission> query = entityManager.createQuery(
				"SELECT p.permission " +
				"FROM EntityUserPermissionModel up, EntityPermissionModel p " +
				"WHERE p.id=up.permissionId AND up.userId = :userId", Permission.class);
		query.setParameter("userId", userId);
		Permission permission = Permission.CLIENT; // use this as default
		try
		{
			permission = query.getSingleResult();
		}
		catch (Exception e) 
		{
			// most probably NoResultException: just return default
		}
		return permission;
	}*/

	/**
	 * Gets list of users by permission
	 * @param orgid
	 * @param permission
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfUsersByPermission(int orgid, Permission permission)
	{
		Query query = entityManager.createQuery(
				"SELECT DISTINCT usr FROM EntityUserModel usr, OrgUserPermissionEntity oup "
				+ "WHERE usr.id = oup.userid AND oup.orgid = :org AND oup.permissionid = :perm "
				+ "ORDER BY usr.accountcreatedtime, usr.lastName, usr.firstName");
		
		query.setParameter("org", orgid);
		query.setParameter("perm", permissionRepository.getPermissionIdFromDB(permission));

		return query.getResultList();
	}
	
	/**
	 * Carol 2017-11-19  add the condition status
	 * Gets list of active users by permission
	 * @param orgid
	 * @param permission
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfActiveUsersByPermission(int orgid, Permission permission)
	{
		Query query = entityManager.createQuery(
				"SELECT DISTINCT usr FROM EntityUserModel usr, OrgUserPermissionEntity oup "
				+ "WHERE usr.id = oup.userid AND oup.orgid = :org AND oup.permissionid = :perm AND usr.status= 0 "
				+ "ORDER BY usr.lastName, usr.firstName");

		query.setParameter("org", orgid);
		query.setParameter("perm", permissionRepository.getPermissionIdFromDB(permission));

		return query.getResultList();
	}
		
	
	
	/**
	 * Gets a list of users' accounts for a specific year and month
	 * Used to process metrics for the system
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfUsers(Integer year, Integer month){
	
		List<EntityUserModel> usersList = new ArrayList<EntityUserModel>();
			
		Query query = entityManager.createQuery(
		   "SELECT usr FROM EntityUserModel usr WHERE MONTH(usr.accountcreatedtime) = :month AND YEAR(usr.accountcreatedtime) = :year");

		query.setParameter("month", month);
		query.setParameter("year", year);
	
		usersList = (List<EntityUserModel>) query.getResultList();
			
		return usersList;		
	}
	
	/**
	 * Carol 2017-11-19 add the condition: status=0
	 * Gets a list of active users' accounts for a specific year and month
	 * Used to process metrics for the system
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfActiveUsers(Integer year, Integer month){
	
		List<EntityUserModel> usersList = new ArrayList<EntityUserModel>();
			
		Query query = entityManager.createQuery(
   		    "SELECT usr FROM EntityUserModel usr WHERE MONTH(usr.accountcreatedtime) = :month AND YEAR(usr.accountcreatedtime) = :year "
		  + " AND usr.status= 0 ");			
		query.setParameter("month", month);
		query.setParameter("year", year);
	
		usersList = (List<EntityUserModel>) query.getResultList();
			
		return usersList;		
	}	
	
	
	
	/**
	 * Gets a list of users' accounts by Email
	 * Used to process metrics for the system
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfUsersByEmail(String emailaddress){
	
		List<EntityUserModel> usersList = new ArrayList<EntityUserModel>();
			
		Query query = entityManager.createQuery(
			"SELECT usr FROM EntityUserModel usr WHERE emailAddress = :emailaddress");
			
		query.setParameter("emailaddress", emailaddress);
	
		usersList = (List<EntityUserModel>) query.getResultList();
			
		return usersList;		
	}
	
	
	
	/**
	 * This method retrieves a list of users entities by userid
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfUsersByIdList(List<Integer> usersIdList) {
		if(usersIdList == null)
			return null;
		
		List<EntityUserModel> userList = null;
	    String qryString = "SELECT u FROM EntityUserModel u WHERE u.id IN (:usersIdList) ORDER BY u.lastName, u.firstName ASC";
	    
	    try {
			Query query = entityManager.createQuery(qryString);
			query.setParameter("usersIdList", usersIdList);
			userList = new ArrayList<EntityUserModel>();
			userList = (List<EntityUserModel>) query.getResultList();
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	    
	    return userList;
	}
	    
	
	public void setUserLoginTime(Integer userId, Timestamp userLoginTime) throws NoResultException {
		try {
			UserModel user = entityManager.find(EntityUserModel.class,userId);
//			user.setUserLoginTime(userLoginTime);
			entityManager.persist(user);
		}
		catch (NoResultException ex) {
			System.out.println("NoResultException:  user not found in database?");
		}
		return;
	}
	
	public void setUserLogoutTime(Integer userId, Timestamp userLogoutTime) throws NoResultException {
		try {
			UserModel user = entityManager.find(EntityUserModel.class,userId);
//			user.setUserLogoutTime(userLogoutTime);
			entityManager.persist(user);
		}
		catch (NoResultException ex) {
			System.out.println("NoResultException:  user not found in database?");
		}
		return;
	}
	
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfUsersLoggedIn(Integer year, Integer month, Integer day) {
	
			System.out.println("Year: " + year + "Month: " + month + "Day: " + day);
		
			Query query = entityManager.createQuery(
				"SELECT usr FROM EntityUserModel usr WHERE DAY(usr.userLoginTime) = :day AND MONTH(usr.userLoginTime) = :month AND YEAR(usr.userLoginTime) = :year");
			
			query.setParameter("day", day);
			query.setParameter("month", month);
			query.setParameter("year", year);
	
			return query.getResultList();
			
		}
	
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfUsersSessions(Integer year, Integer month, Integer day) {
	
		Query query = entityManager.createQuery(
			"SELECT usr FROM EntityUserModel usr WHERE DAY(usr.userLoginTime) = :day AND MONTH(usr.userLoginTime) = :month AND YEAR(usr.userLoginTime) = :year");
		
		query.setParameter("day", day);
		query.setParameter("month", month);
		query.setParameter("year", year);

		return query.getResultList();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> checkDuplicates(String emailaddressIn) {
		Query query = entityManager.createQuery(
				"SELECT usr FROM EntityUserModel usr WHERE usr.emailAddress = :emailaddress");
		query.setParameter("emailaddress", emailaddressIn);
		
		return query.getResultList();
	}
	
	/**
	 * Determine whether a user is using the email address
	 * @param emaddr
	 * @return
	 */
	public boolean usingEmail(String emaddr)
	{
		Query query = entityManager.createQuery("SELECT COUNT(ue) FROM EntityUserModel ue WHERE ue.emailAddress = :emailaddr");
		query.setParameter("emailaddr", emaddr);
		Long count = (Long) query.getSingleResult();
		return count > 0;
	}
	
	/**
	 * Remove the user-permission for the specified combination
	 * @param userid
	 * @param permissionid
	 */
	public void removePermission(Integer userid, Integer permissionid) throws Exception
	{
		Query query = entityManager.createQuery("SELECT upe FROM EntityUserPermissionModel upe " + 
				"WHERE upe.userId = :userid AND upe.permissionId = :permid");
		query.setParameter("userid", userid);
		query.setParameter("permid", permissionid);
		EntityUserPermissionModel upEntity = (EntityUserPermissionModel) query.getSingleResult();
		entityManager.remove(upEntity);
	}
	
	/**
	 * Return the list of clients of a consultant
	 * @param orgid
	 * @param consultantid
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfClientsOfAConsultant(int orgid, int consultantid)
	{
		Query query = entityManager.createQuery(
				"SELECT usr FROM EntityConsultantClientsModel ecc, EntityUserModel usr, OrgUserPermissionEntity oup WHERE " 
				+ "ecc.consultantid = :cons AND "
				+ "ecc.orgid = :org AND "
				
				+ "ecc.clientid = usr.id AND " 
				+ "ecc.clientid = oup.userid AND "
				+ "ecc.orgid = oup.orgid AND "

				+ "ecc.closedtime = null"
				);	
		
		query.setParameter("org", orgid);
		query.setParameter("cons", consultantid);

		return query.getResultList();
	}	
	
	
	/**
	 * Return the list of consultants of a client
	 * @param orgid
	 * @param clientid
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfConsultantOfAClient(int orgid, int clientid)
	{
		
		Query query = entityManager.createQuery(
				"SELECT usr FROM EntityConsultantClientsModel ecc, EntityUserModel usr "
				+ "WHERE ecc.consultantid = usr.id AND ecc.orgid = :org AND ecc.clientid = :client AND ecc.closedtime = null");
		
		query.setParameter("org", orgid);
		query.setParameter("client", clientid);

		return query.getResultList();
	}	
	
	/**
	 * Return the assigned consultant for a client
	 * @param orgid
	 * @param clientid
	 */
	public EntityUserModel getAssignedConsultantOfAClient(int orgid, int clientid)
	{
		
		Query query = entityManager.createQuery(
				"SELECT usr FROM EntityConsultantClientsModel ecc, EntityUserModel usr WHERE "
				+ "ecc.consultantid = usr.id AND ecc.orgid = :org AND ecc.clientid = :client AND "
				+ "ecc.closedtime = null");
		
		query.setParameter("org", orgid);
		query.setParameter("client", clientid);

		return (EntityUserModel) query.getSingleResult();
	}	
	
	
	
	/**
	 * Return the list of unassigned clients
	 * @param orgid
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfUnassignedClients(int orgid)
	{
		Query query = entityManager.createQuery(
				"SELECT DISTINCT usr FROM EntityUserModel usr, OrgUserPermissionEntity oup "
				+ "WHERE usr.id = oup.userid AND oup.orgid = :org AND oup.permissionid = :perm "
				+ "AND usr.id NOT IN "
				+ "(SELECT ecc.clientid FROM EntityConsultantClientsModel ecc WHERE ecc.orgid = :org) "
				+ "ORDER BY usr.lastName, usr.firstName");
		
		query.setParameter("org", orgid);
		query.setParameter("perm", permissionRepository.getPermissionIdFromDB(Permission.CLIENT));

		return query.getResultList();
	}
	
	
	/**
	 * carol 2017-11-19 add condition: status=0
	 * Return the list of active unassigned clients
	 * @param orgid
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfUnassignedActiveClients(int orgid)
	{
		Query query = entityManager.createQuery(
				"SELECT DISTINCT usr FROM EntityUserModel usr, OrgUserPermissionEntity oup "
				+ "WHERE usr.id = oup.userid AND usr.status = 0 AND oup.orgid = :org AND oup.permissionid = :perm "
				+ "AND usr.id NOT IN "
				+ "(SELECT ecc.clientid FROM EntityConsultantClientsModel ecc WHERE ecc.orgid = :org AND ecc.closedtime = null) "
				+ "ORDER BY usr.lastName, usr.firstName");
		
		query.setParameter("org", orgid);
		query.setParameter("perm", permissionRepository.getPermissionIdFromDB(Permission.CLIENT));

		return query.getResultList();
	}	
	

	/**
	 * Return the list of unassigned consultants
	 * @param orgid
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfUnassignedConsultants(int orgid)
	{
		Query query = entityManager.createQuery(
				"SELECT DISTINCT usr FROM EntityUserModel usr, OrgUserPermissionEntity oup "
				+ "WHERE usr.id = oup.userid AND oup.orgid = :org AND oup.permissionid = :perm "
				+ "AND usr.id NOT IN "
				+ "(SELECT ecc.consultantid FROM EntityConsultantClientsModel ecc WHERE ecc.orgid = :org) "
				+ "ORDER BY usr.lastName, usr.firstName");
		
		query.setParameter("org", orgid);
		query.setParameter("perm", permissionRepository.getPermissionIdFromDB(Permission.CONSULTANT));

		return query.getResultList();
	}
	
	/**
	 * Carol 2017-11-19  add condition: usr.status = 0
	 * Return the list of active unassigned consultants
	 * @param orgid
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfUnassignedActiveConsultants(int orgid)
	{
		Query query = entityManager.createQuery(
				"SELECT DISTINCT usr FROM EntityUserModel usr, OrgUserPermissionEntity oup "
				+ "WHERE usr.id = oup.userid usr.status = 0 AND oup.orgid = :org AND oup.permissionid = :perm "
				+ "AND usr.id NOT IN "
				+ "(SELECT ecc.consultantid FROM EntityConsultantClientsModel ecc WHERE ecc.orgid = :org) "
				+ "ORDER BY usr.lastName, usr.firstName");
		
		query.setParameter("org", orgid);
		query.setParameter("perm", permissionRepository.getPermissionIdFromDB(Permission.CONSULTANT));

		return query.getResultList();
	}	
	
}
