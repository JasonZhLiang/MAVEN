package com.linguaclassica.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.model.ConsultantClientsModel;
import com.linguaclassica.service.exception.ServiceException;

@Repository
public class ConsultantClientsRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void createOne(ConsultantClientsModel ccModel) {
		
		try {
			entityManager.persist(ccModel);
		}
		catch (PersistenceException pe){
			pe.printStackTrace();
		}
		return;
	}
	/**
	 * Close one item that matches the passed parameters by filling the closedtime field
	 * @param organizationId
	 * @param consId
	 * @param clientId
	 */
	public void closeOne(Integer orgid, Integer consid, Integer clientid)
	{
		Query query = entityManager.createQuery("SELECT ecc FROM EntityConsultantClientsModel ecc " + 
				"WHERE ecc.orgid = :orgid AND ecc.consultantid = :consid AND ecc.clientid = :clientid AND ecc.closedtime = null");
		
			
		query.setParameter("orgid", orgid);
		query.setParameter("consid", consid);
		query.setParameter("clientid", clientid);
		ConsultantClientsModel ccModel = (ConsultantClientsModel) query.getSingleResult();
		//ClosedTime indicates the end of relationship
		LocalDateTime localtime = LocalDateTime.now(); 
		ccModel.setClosedtime(Timestamp.valueOf(localtime));
		entityManager.merge(ccModel);
	}
	/**
	 * Delete one item that matches the passed parameters
	 * @param organizationId
	 * @param consId
	 * @param clientId
	 */
	public void deleteOne(Integer orgid, Integer consid, Integer clientid)
	{
		Query query = entityManager.createQuery("SELECT ecc FROM EntityConsultantClientsModel ecc " + 
				"WHERE ecc.orgid = :orgid AND ecc.consultantid = :consid AND ecc.clientid = :clientid");
		
			
		query.setParameter("orgid", orgid);
		query.setParameter("consid", consid);
		query.setParameter("clientid", clientid);
		ConsultantClientsModel ccModel = (ConsultantClientsModel) query.getSingleResult();
		entityManager.persist(ccModel);
	}
	
	/**
	 * This method retrieves the list of client ids for a consultant.
	 * If no exceptions are thrown the method is successful.
	 * @param orgid
	 * @param consid
	 * @return List<Integer>
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfClientIdsForConsultant(int orgid, int consid) {
		List<Integer> clientidList = null;
		try {
			Query query = entityManager.createQuery("SELECT e.clientid FROM EntityConsultantClientsModel e WHERE e.orgid = :orgid " + 
						"AND e.consultantid = :consid");
			
			query.setParameter("orgid", orgid);
			query.setParameter("consid", consid);
	
			clientidList = query.getResultList();			
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return clientidList;
	}

/**
 * This method retrieves the consultant id for a client
 * or 0 if there is no consultant assigned.
 * If no exceptions are thrown the method is successful.
 * @param orgid
 * @param clientid
 * @return int
 */
public int getConsultantIdForClient(int orgid, int clientid) {
	try {
		Query query = entityManager.createQuery("SELECT e.consultantid FROM EntityConsultantClientsModel e WHERE e.orgid = :orgid " + 
					"AND e.clientid = :clientid AND e.closedtime = null");
		
		query.setParameter("orgid", orgid);
		query.setParameter("clientid", clientid);
		//query.setParameter("closedtime", null);
		return (Integer) query.getSingleResult();			
		
	}catch (NoResultException e) {
		return 0;
	} catch (Exception e) {
		throw new ServiceException(e);
	}
}
}