package com.linguaclassica.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.model.ClientSequenceUsageModel;
import com.linguaclassica.entity.EntityClientSequenceUsageModel;

@Repository
public class ClientSequenceUsageRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void createOne(ClientSequenceUsageModel csuModel) {
		
		try {
			entityManager.persist(csuModel);
			entityManager.flush();
		}
		catch (PersistenceException pe){
			pe.printStackTrace();
		}
		return;
	}

	/**
	 * Delete client_sequence_usage.
	 * @param csUsage
	 */
	public void deleteOne(ClientSequenceUsageModel csUsage)
	{		
		entityManager.remove(csUsage);
	}
	
	
	/**
	 * This method is used to find a clint_sequence_usage by ID.
	 * @param id
	 */
	public EntityClientSequenceUsageModel getOne(int id)
	{
		return entityManager.find(EntityClientSequenceUsageModel.class, id);
	}
	
	
	/**
	 * This method retrieves the list of clientsequenceid from the client sequence progress.
	 * If no exceptions are thrown the method is successful.
	 * @param csuId
	 * @return List<Integer>
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfClientSequenceUsage(int csuId) {
		List<Integer> clientSequList = null;
		try {
			Query query = entityManager.createQuery("SELECT e.clientsequenceid FROM EntityClientSequenceUsageModel e WHERE e.csuId = :csuId");
			
			query.setParameter("clientsequenceid", csuId);
	
			clientSequList = query.getResultList();			
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return clientSequList;
	}
	
	/**
	 * This method retrieves the list of client_sequence_usage object from the client sequence progress.
	 * If no exceptions are thrown the method is successful.
	 * @param csuId
	 * @return List<EntityClientSequenceUsageModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityClientSequenceUsageModel> getListOfClientSequenceUsageModel(int csuId) {
		List<EntityClientSequenceUsageModel> clientSequList = null;
		try {
			Query query = entityManager.createQuery("SELECT e FROM EntityClientSequenceUsageModel e WHERE e.clientsequenceid = :clientsequenceid");
			
			query.setParameter("clientsequenceid", csuId);
	
			clientSequList = query.getResultList();			
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return clientSequList;
	}

	public void updateClientSequenceUsage(EntityClientSequenceUsageModel sequenceUsage) {
		entityManager.merge(sequenceUsage);
	}

}