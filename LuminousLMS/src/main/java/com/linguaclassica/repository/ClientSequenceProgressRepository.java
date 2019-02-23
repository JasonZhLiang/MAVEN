package com.linguaclassica.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityClientSequenceProgressModel;
import com.linguaclassica.model.ClientSequenceProgressModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class ClientSequenceProgressRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public ClientSequenceProgressModel createOne(ClientSequenceProgressModel cspModel) {
		
		try {
			entityManager.persist(cspModel);
		}
		catch (PersistenceException pe){
			pe.printStackTrace();
		}
		return cspModel;
	}

	/**
	 * Delete one item that matches the passed parameters
	 * @param clientAssignmentId
	 * @param sequenceId
	 * @param results
	 * @param completed
	 * 
	 */
	public void deleteOneByParameters(Integer clientAssignmentId, Integer sequenceId, Integer results, Boolean completed)
	{
		Query query = entityManager.createQuery("SELECT ecsp FROM EntityClientSequenceProgressModel ecsp " + 
				"WHERE ecsp.clientassignmentid = :clientAssignmentId AND ecsp.sequenceid = :sequenceId " + 
				"AND ecsp.results = :results AND ecsp.completed = :completed ");
		query.setParameter("clientassignmentid", clientAssignmentId);
		query.setParameter("sequenceid", sequenceId);
		query.setParameter("results", results);
		query.setParameter("completed", completed);
		ClientSequenceProgressModel cspModel = (ClientSequenceProgressModel) query.getSingleResult();
		entityManager.remove(cspModel);
	}
	
	/**
	 * Delete one item that matches the passed parameter
	 * @param id
	 * 
	 */
	public void deleteOneById(Integer recordId)
	{
		Query query = entityManager.createQuery("SELECT ecsp FROM EntityClientSequenceProgressModel ecsp " + 
				"WHERE ecsp.id = :recordId ");
		query.setParameter("id", recordId);
		ClientSequenceProgressModel cspModel = (ClientSequenceProgressModel) query.getSingleResult();
		entityManager.remove(cspModel);
	}
	
	/**
	 * Get list that matches the passed parameter
	 * @param clientAssignmentId
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<EntityClientSequenceProgressModel> getListByClientAssignId(Integer clientAssignmentId)
	{
		try {
			Query query = entityManager.createQuery(
				"SELECT ecsp FROM EntityClientSequenceProgressModel ecsp "
				+ "WHERE ecsp.clientAssignmentId = :clientAssignmentId ");
			
			query.setParameter("clientAssignmentId", clientAssignmentId);
			return query.getResultList();
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
		/**
		 * Get count of assignment segments that have been completed for the given client assignment Id
		 * @param clientAssignmentId
		 * @return Integer
		 */
		@SuppressWarnings("unchecked")
		public Integer getAssignmentSegmentsCompletedCount(Integer clientAssignmentId)
		{
			List<EntityClientSequenceProgressModel> ecspmList = null;
			try {
				Query query = entityManager.createQuery(
					"SELECT ecsp FROM EntityClientSequenceProgressModel ecsp "
					+ "WHERE ecsp.clientAssignmentId = :clientAssignmentId AND ecsp.completed = true");
				
				query.setParameter("clientAssignmentId", clientAssignmentId);
				ecspmList = query.getResultList();
				return ecspmList.size();
			} catch (NoResultException e) {
				throw new UnknownEntityException(e);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		}
		
	/**
	 * Get list that matches the passed parameter
	 * @param sequenceId
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<EntityClientSequenceProgressModel> getListBySeqId(Integer sequenceId)
	{
		Query query = entityManager.createQuery(
				"SELECT ecsp FROM EntityClientSequenceProgressModel ecsp "
				+ "WHERE ecsp.sequenceid = :sequenceId ");
			
		query.setParameter("sequenceid", sequenceId);
		return query.getResultList();
	}
	
	/**
	 * Get one record that matches the passed parameter
	 * @param id
	 * 
	 */
	public EntityClientSequenceProgressModel getOneById(Integer id)
	{
		return entityManager.find(EntityClientSequenceProgressModel.class, id);
	}

	/**
	 * Get the record that matches the passed parameters
	 * @param clientAssignmentId
	 * @param sequenceId
	 * 
	 */
	public EntityClientSequenceProgressModel getOneByClientAssignIdAndSeqId(Integer clientAssignmentId, Integer sequenceId)
	{
		try {
			Query query = entityManager.createQuery(
				"SELECT ecsp FROM EntityClientSequenceProgressModel ecsp "
				+ "WHERE ecsp.sequenceId = :sequenceId AND ecsp.clientAssignmentId = :clientAssignmentId ");
			
			query.setParameter("sequenceId", sequenceId);
			query.setParameter("clientAssignmentId", clientAssignmentId);

			return (EntityClientSequenceProgressModel) query.getSingleResult();
		} catch (NoResultException e) {
			//throw new UnknownEntityException(e);
			return null;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Update the results field for a record by Id
	 * @param id
	 * @param results
	 * @throws NoResultException
	 */
	public void updateResultsById(Integer id, Integer results)
	{
		EntityClientSequenceProgressModel ecsp = entityManager.find(EntityClientSequenceProgressModel.class, id);	
		ecsp.setResults(results);	
		entityManager.persist(ecsp);
	}

	/**
	 * Update the completed field for a record by Id
	 * @param id
	 * @param completed
	 * @throws NoResultException
	 */
	public void updateCompletedById(Integer id, Boolean completed)
	{
		EntityClientSequenceProgressModel ecsp = entityManager.find(EntityClientSequenceProgressModel.class, id);	
		ecsp.setCompleted(completed);	
		entityManager.persist(ecsp);
	}
	
	/**
	 * Update the results field for a record by clientAssignmentId and sequenceId
	 * @param clientAssignmentId
	 * @param sequenceId
	 * @param results
	 * @throws NoResultException
	 */
	public void updateResultsByClientAssignIdAndSeqId(Integer clientAssignmentId, Integer seqId, Integer results)
	{
		EntityClientSequenceProgressModel ecsp = getOneByClientAssignIdAndSeqId(clientAssignmentId, seqId);	
		if (ecsp != null)
		{
			ecsp.setResults(results);	
			entityManager.persist(ecsp);
		}
		//TODO: How do we notify service that no record is found?
 	}
	
	/**
	 * Update the completed field for a record by clientAssignmentId and sequenceId
	 * @param clientAssignmentId
	 * @param sequenceId
	 * @param completed
	 * @throws NoResultException
	 */
	public void updateCompletedByClientAssignIdAndSeqId(Integer clientAssignmentId, Integer seqId, Boolean completed)
	{
		EntityClientSequenceProgressModel ecsp = getOneByClientAssignIdAndSeqId(clientAssignmentId, seqId);	
		if (ecsp != null)
		{
			ecsp.setCompleted(completed);	
			entityManager.persist(ecsp);
		}
		//TODO: How do we notify service that no record is found?
 	}

	public void updateClientSequenceProgress(EntityClientSequenceProgressModel segmentProgress) {
		entityManager.merge(segmentProgress);
	}
	
	
}