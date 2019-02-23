package com.linguaclassica.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityPermissionModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.UserModel.SubsStatusType;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;


@Repository
public class UserRepository {

	@PersistenceContext
	EntityManager entityManager;
	
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
	 * 
	 * This method creates a user. If no exceptions are thrown the method is successful.
	 * 
	 * @param userModel
	 * @throws EntityExistsException if the user already exists.
	 * @throws PersistenceException if an unspecified error occurs.
	 */
	public Integer createNewUser(EntityUserModel userModel) throws EntityExistsException, PersistenceException {
		int userId = 0;
		try {
			entityManager.persist(userModel);
			entityManager.refresh(userModel);
			userId = userModel.getId();
		} catch (PersistenceException e) {
			// Duplicate users are captured by an index violation on emailAddress. Semantically,
			// EntityExistsException would describe this scenario but JPA providers normally only
			// throw this on a primary key violation. For a duplicate emailAddress, they throw a
			// PersistenceException with a nested cause of a Hibernate ConstraintViolationException. We
			// are going to check for this and map it to EntityExistsException.
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		return userId;
	}

	/**
	 * Create a user with a permission for an institution
	 * @param userModel
	 * @param permission
	 * @param institutionId
	 * @return
	 */
	public Integer createUser(EntityUserModel userModel, Permission permission, int institutionId)
	{		
		int userId = createNewUser(userModel);
		System.out.println("UserRepository.createUser:  userId = " + userId);

		return userId;
	}
	
	/**
	 * Update just the password for the user
	 * @param userId
	 * @param password
	 */
	public void updatePassword(Integer userId, String password)
	{
		UserModel user = entityManager.find(EntityUserModel.class,userId);
		user.setPassword(password);
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
			user.setStudentnumber(userModel.getStudentnumber());
			user.setPassword(userModel.getPassword());
			
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
	 * Gets the permission model
	 * @param permission Permission as enum
	 * @return
	 */
	public EntityPermissionModel getPermissionModel(Permission permission)
	{
		TypedQuery<Integer> query = entityManager.createQuery("SELECT p.id " +
				"from EntityPermissionModel p where p.permission = :permission", Integer.class);
		query.setParameter("permission", permission);
		Integer permissionId = query.getSingleResult();
		EntityPermissionModel model = new EntityPermissionModel(permissionId, permission);
		return model;
	}
	
	public void updateSubsStatus(Integer userId, SubsStatusType status) throws NoResultException {
		try {
			UserModel user = entityManager.find(EntityUserModel.class,userId);
			user.setStatus(status);
			entityManager.persist(user);
		}
		catch (NoResultException ex) {
			System.out.println("NoResultException:  user not found in database?");
		}
		return;
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
	 * Gets a list of users' accounts for a specific year and month
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
	 * This method retrieves a list of student entities by userid
	 * @param studentIdList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EntityUserModel> getListOfStudentsByIdList(List<Integer> studentIdList)
	{
		List<EntityUserModel> studentList = new ArrayList<EntityUserModel>();
		if (studentIdList.size() > 0)
		{
		    String qryString;
		    qryString="SELECT u FROM EntityUserModel u WHERE u.id IN (:studentIdList) ORDER BY u.lastName, u.firstName ASC";
		    
		    try {
				Query query = entityManager.createQuery(qryString);
				query.setParameter("studentIdList", studentIdList);
				studentList = new ArrayList<EntityUserModel>();
				studentList = (List<EntityUserModel>) query.getResultList();
				
			} catch (NoResultException e) {
				throw new UnknownEntityException(e);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		}
	    return studentList;
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
}
