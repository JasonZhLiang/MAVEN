package com.linguaclassica.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public void updateMessage(String stat, String messagetext) {
		try {
			messageRepository.updateMessage(stat, messagetext);
		}
		catch (NoResultException nor) {
			throw new NoResultException();
		}
		catch (EntityAlreadyExistsException eae) {
			throw new EntityAlreadyExistsException();
		}
	}

}
