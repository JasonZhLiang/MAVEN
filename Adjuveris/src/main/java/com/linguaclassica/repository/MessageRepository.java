 package com.linguaclassica.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityMessageModel;
import com.linguaclassica.model.MessageModel;
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

	public void updateMessage(String stat, String messagetext) {
		
		try {
			MessageModel messageModel = entityManager.find(EntityMessageModel.class,1);
			if (stat.equals("SHOW")) {
				messageModel.setStatus(Status.SHOW);
			}
			else {
				messageModel.setStatus(Status.HIDE);
			}
			messageModel.setMessage(messagetext);
			entityManager.persist(messageModel);
		}
		catch (NoResultException nor) {
			throw new NoResultException();
		}
	}
}
