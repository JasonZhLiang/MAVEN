 package com.linguaclassica.repository;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityMessageModel;
import com.linguaclassica.model.MessageModel.Status;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class MessageRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * This method is used to find a messageModel where its id is 1.
	 * 
	 * If successful, the valid EntityMessageModel is returned.
	 * 
	 * @param id
	 * @return
	 * @throws UnknownEntityException if the group cannot be found.
	 * @throws ServiceException if an unspecified error occurred.
	 */
	public EntityMessageModel findFirstMessageModel() throws UnknownEntityException, ServiceException {

		try {
			Query query = entityManager.createQuery("SELECT msg from EntityMessageModel msg where id=1");
			EntityMessageModel messageModel = (EntityMessageModel) query.getSingleResult();

			// Got here so valid user
			return messageModel;
			
		} catch (NoResultException e) {
			// This exception is thrown by getSingleResult if a match cannot be found.
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Creates a message entry
	 * @param messageModel
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void createMessage(EntityMessageModel messageModel) 
			throws EntityExistsException, PersistenceException {
		try {
			entityManager.persist(messageModel);
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
	 * Returns a message based on its unique id
	 * @param id
	 * @return EntityMessageModel
	 * @throws NoResultException
	 * @throws PersistenceException
	 */
	public EntityMessageModel findMessageById(Integer id) 
			throws NoResultException, PersistenceException {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityMessageModel ex WHERE ex.id = :id");
			query.setParameter("id", id);
			
			EntityMessageModel messageModel = (EntityMessageModel) query.getSingleResult();
			
			System.out.println("MessageRepository.findMessageById.getSubject() = " + messageModel.getSubject());
			
			return messageModel;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Update a message entry
	 * @param messageModel
	 * @throws EntityExistsException
	 * @throws PersistenceException
	 */
	public void updateMessage(EntityMessageModel messageModel) 
			throws EntityExistsException, PersistenceException {
		try {
			entityManager.merge(messageModel);
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
	 * Remove a messageClasses entry
	 * @param messageClassesModel
	 * @throws UnknownEntityException
	 * @throws ServiceException
	 */
	public void removeMessage(int messageId) 
			throws EntityExistsException, PersistenceException {
		try {
			Query query = entityManager.createQuery("SELECT ex from EntityMessageModel ex where ex.id = :id");
			
			query.setParameter("id", messageId);
	
			EntityMessageModel entityMessageModel = (EntityMessageModel) query.getSingleResult();
						
			entityManager.remove(entityMessageModel);
			return;
		}
		catch (NoResultException e) {
			// This exception is thrown by getSingleResult if a match cannot be found.
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	 /* Returns a list of messages that were sent to a particular user id
	 * @param toId
	 * @return List<EntityMessageModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityMessageModel> findMessagesByToId(Integer toId) 
	{	
		Query query = entityManager.createQuery("SELECT ex FROM EntityMessageModel ex " + 
				"WHERE ex.toid = :toid " +
				"ORDER BY ex.createdate DESC");
		query.setParameter("toid", toId);
		List<EntityMessageModel> messageList = (List<EntityMessageModel>) query.getResultList();
		return messageList;
	}
	
	 /* Returns a list of unread messages that were sent to a particular userid
	 * @param toId
	 * @return List<EntityMessageModel>
	 * @throws NoResultException
	 * @throws PersistenceException
	 */
	@SuppressWarnings("unchecked")
	public List<EntityMessageModel> findUnreadMessagesByToId(Integer toId) 
			throws NoResultException, PersistenceException {	
		
		try {
			Status status = Status.UNREAD;
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityMessageModel ex WHERE ex.toid = :toid AND ex.status = :status " +
				"ORDER BY ex.createdate DESC");
			query.setParameter("toid", toId);
			query.setParameter("status", status);
			
			List<EntityMessageModel> messageList = (List<EntityMessageModel>) query.getResultList();
			
			//System.out.println("MessageRepository.findUnreadMessagesByToId.get(0).getSubject() = " + messageList.get(0).getSubject());
			
			return messageList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	 /* Returns a list of unread messages that were sent to a particular userid in a specific organization
	 * @param toId
	 * @param orgId
	 * @return List<EntityMessageModel>
	 * @throws NoResultException
	 * @throws PersistenceException
	 */
	@SuppressWarnings("unchecked")
	public List<EntityMessageModel> findUnreadMessagesByToId(Integer toId, Integer orgId) 
			throws NoResultException, PersistenceException {	
		
		try {
			Status status = Status.UNREAD;
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityMessageModel ex WHERE " +
					"ex.toid = :toid AND ex.orgid = :orgid AND ex.status = :status " +
					"ORDER BY ex.createdate DESC");
			query.setParameter("toid", toId);
			query.setParameter("orgid", orgId);
			query.setParameter("status", status);
			
			List<EntityMessageModel> messageList = (List<EntityMessageModel>) query.getResultList();
			
			//System.out.println("MessageRepository.findUnreadMessagesByToId.get(0).getSubject() = " + messageList.get(0).getSubject());
			
			return messageList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	 /* Returns the list of messages sent to or from a particular user.
	 * @param userId
	 * @return List<EntityMessageModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityMessageModel> findMessagesByUserid(Integer userId) 
	{	
		Query query = entityManager.createQuery("SELECT ex FROM EntityMessageModel ex " + 
				"WHERE ex.toid = :userid OR ex.fromid = :userid " +
				"ORDER BY ex.createdate DESC");
		query.setParameter("userid", userId);
		List<EntityMessageModel> messageList = (List<EntityMessageModel>) query.getResultList();
		return messageList;
	}
	
	 /* Returns a list of messages that were sent to or from a particular user id
	  * grouping the messages of the other party together, then sorting by date
	  * @param userid
	  * @return List<Object[]> where:
	  * 	Object[0] = EntityMessageModel - the message
	  * 	Object[1] = EntityUserModel    - the other party to the message
	  */
	@SuppressWarnings("unchecked")
	public List<Object[]> getUsersCorrespondence(int userid, int orgid) 
	{	
		Query query = entityManager.createQuery(
				"SELECT msg,usr FROM EntityMessageModel msg, EntityUserModel usr " +
				"WHERE ((msg.toid = :uid AND msg.fromid = usr.id ) " +
				"OR (msg.fromid = :uid AND msg.toid = usr.id)) "  +
				"AND msg.orgid = :org " +
				"ORDER BY usr.lastName, usr.firstName, usr.id DESC, msg.createdate DESC");
		query.setParameter("uid", userid);
		query.setParameter("org", orgid);
		return (List<Object[]>) query.getResultList();
	}

	/* Returns the list of messages exchanged between two users.
	 * @param userid1
	 * @param userid2
	 * @return List<EntityMessageModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityMessageModel> findMessagesByUserids(Integer userId1, Integer userId2) 
	{	
		Query query = entityManager.createQuery("SELECT ex FROM EntityMessageModel ex WHERE " +
				"(ex.toid = :userid1 OR ex.fromid = :userid1) AND " +
				"(ex.toid = :userid2 OR ex.fromid = :userid2) " +
				"ORDER BY ex.createdate DESC");
		query.setParameter("userid1", userId1);
		query.setParameter("userid2", userId2);
		List<EntityMessageModel> messageList = (List<EntityMessageModel>) query.getResultList();
		return messageList;
	}






	 /* Returns the list of messages sent to or from a particular user.
	 * @param userId
	 * @return List<EntityMessageModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityMessageModel> findMessagesByUserid(Integer userId, Integer orgId) 
	{	
		Query query = entityManager.createQuery("SELECT ex FROM EntityMessageModel ex WHERE " + 
				"(ex.toid = :userid OR ex.fromid = :userid) AND " +
				"ex.orgid = :orgid " +
				"ORDER BY ex.createdate DESC");
		query.setParameter("userid", userId);
		query.setParameter("orgid", orgId);
		List<EntityMessageModel> messageList = (List<EntityMessageModel>) query.getResultList();
		return messageList;
	}
	
	 /* Returns the list of messages exchanged between two users.
	 * @param userid1
	 * @param userid2
	 * @return List<EntityMessageModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityMessageModel> findMessagesByUserids(Integer userId1, Integer userId2, Integer orgId) 
	{	
		Query query = entityManager.createQuery("SELECT ex FROM EntityMessageModel ex WHERE " +
				"(ex.toid = :userid1 OR ex.fromid = :userid1) AND " +
				"(ex.toid = :userid2 OR ex.fromid = :userid2) AND " +
				"ex.orgid = :orgid " +
				"ORDER BY ex.createdate DESC");
		query.setParameter("userid1", userId1);
		query.setParameter("userid2", userId2);
		query.setParameter("orgid", orgId);
		List<EntityMessageModel> messageList = (List<EntityMessageModel>) query.getResultList();
		return messageList;
	}

}
