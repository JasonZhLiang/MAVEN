package com.linguaclassica.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityMessageModel;
import com.linguaclassica.model.MessageModel;
import com.linguaclassica.repository.MessageRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Service
@Transactional
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	/**
	 * This method is used to find he messageMmodel where the id is 1.
	 * 
	 * If successful, the valid EntityMessageModel is returned. 
	 * 
	 * @param id
	 * @return
	 * @throws UnknownEntityException if the group cannot be found.
	 * @throws ServiceException if an unspecified error occurred.
	 */
	public MessageModel findFirstMessageModel() throws UnknownEntityException, ServiceException {

		return messageRepository.findFirstMessageModel();
	}
	
	/**
	 * This method will create a message
	 * @param messageModel
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void createMessage(MessageModel messageModel) 
		throws EntityAlreadyExistsException, ServiceException {			
		try {
			System.out.println("MessageService: createMessage");
			messageRepository.createMessage((EntityMessageModel) messageModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method will find a message by is unique id
	 * @param id
	 * @return EntityMessageModel
	 */
	public EntityMessageModel findMessageById(Integer id){
		return messageRepository.findMessageById(id);
	}
	
	/**
	 * This method will update a message
	 * @param messageModel
	 * @throws EntityAlreadyExistsException
	 * @throws ServiceException
	 */
	public void updateMessage(MessageModel messageModel) 
		throws EntityAlreadyExistsException, ServiceException {			
		try {
			System.out.println("MessageService: UpdateMessage");
			messageRepository.updateMessage((EntityMessageModel) messageModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeMessage(int messageId) {
		messageRepository.removeMessage(messageId);
	}

	/**
	 * This method will find a list of messages sent to a specific userid
	 * @param toId
	 * @return List<EntityMessageModel>
	 */
	public List<EntityMessageModel> findMessagesByToId(Integer toId){
		return messageRepository.findMessagesByToId(toId);
	}
	
	/**
	 * This method will find a list of unread messages sent to a specific userid
	 * @param toId
	 * @return List<EntityMessageModel>
	 */
	public List<EntityMessageModel> findUnreadMessagesByToId(Integer toId){
		return messageRepository.findUnreadMessagesByToId(toId);
	}

	/**
	 * This method returns the list of messages sent to or from a specific user.
	 * @param userId
	 * @return List<EntityMessageModel>
	 */
	public List<EntityMessageModel> findMessagesByUserid(Integer userId){
		return messageRepository.findMessagesByUserid(userId);
	}
	
	/**
	 * This method returns the list of messages exchanged between two users.
	 * @param userId1
	 * @param userId2
	 * @return List<EntityMessageModel>
	 */
	public List<EntityMessageModel> findMessagesByUserids(Integer userId1, Integer userId2) {
		return messageRepository.findMessagesByUserids(userId1, userId2);
	}

	/**
	 * This method will find a list of unread messages sent to a specific userid in a specific organization
	 * @param toId
	 * @param orgId
	 * @return List<EntityMessageModel>
	 */
	public List<EntityMessageModel> findUnreadMessagesByToId(Integer toId, Integer orgId){
		return messageRepository.findUnreadMessagesByToId(toId, orgId);
	}

	
	 /* Returns a list of messages that were sent to or from a particular user id
	  * grouping the messages of the other party together, then sorting by date
	  * @param userid
	  * @return List<Object[]> where:
	  * 	Object[0] = EntityMessageModel - the message
	  * 	Object[1] = EntityUserModel    - the other party to the message
	  */
	public List<Object[]> getUsersCorrespondence(int userid, int orgid) {
		return messageRepository.getUsersCorrespondence(userid, orgid);		
	}

	/**
	 * This method returns the list of messages sent to or from a specific user in the
	 * specified organization.
	 * @param userId
	 * @param orgId
	 * @return List<EntityMessageModel>
	 */
	public List<EntityMessageModel> findMessagesByUserid(Integer userId, Integer orgId){
		return messageRepository.findMessagesByUserid(userId, orgId);
	}
	
	/**
	 * This method returns the list of messages exchanged between two users in the
	 * specified organization..
	 * @param userId1
	 * @param userId2
	 * @param orgId
	 * @return List<EntityMessageModel>
	 */
	public List<EntityMessageModel> findMessagesByUserids(Integer userId1, Integer userId2, Integer orgId) {
		return messageRepository.findMessagesByUserids(userId1, userId2, orgId);
	}
}
