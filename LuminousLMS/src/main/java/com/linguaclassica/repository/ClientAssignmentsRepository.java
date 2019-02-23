package com.linguaclassica.repository;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.model.ClientsAssignmentsModel;
import com.linguaclassica.service.exception.ServiceException;

@Repository
public class ClientAssignmentsRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void createOne(ClientsAssignmentsModel caModel) {
		
		try {
			entityManager.persist(caModel);
		}
		catch (PersistenceException pe){
			pe.printStackTrace();
		}
		return;
	}

	/**
	 * Delete one item that matches the passed parameters
	 * @param organizationId
	 * @param consId
	 * @param clientId
	 */
	public void deleteOne(Integer organizationId, Integer clientId, Integer assignmentId)
	{
		Query query = entityManager.createQuery("SELECT eca FROM EntityClientsAssignmentsModel eca " + 
				"WHERE eca.clientid = :clientid AND eca.assignmentid = :assignid");
//				"WHERE eca.orgid = :orgID AND eca.clientid = :clientid AND eca.assignmentid = :assignid");
//		query.setParameter("orgID", organizationId);
		query.setParameter("clientid", clientId);
		query.setParameter("assignid", assignmentId);
		ClientsAssignmentsModel caModel = (ClientsAssignmentsModel) query.getSingleResult();
		entityManager.remove(caModel);
	}
	
	/**
	 * This method retrieves the list of assignment ids for a client.
	 * If no exceptions are thrown the method is successful.
	 * @param client
	 * @return List<Integer>
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfClientAssignmentIdsForClient(int clientid) {
		List<Integer> assignmentidList = null;
		try {
			Query query = entityManager.createQuery("SELECT e.assignmentid FROM EntityClientsAssignmentsModel e WHERE e.clientid = :clientid");
			
			query.setParameter("clientid", clientid);
	
			assignmentidList = query.getResultList();			
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return assignmentidList;
	}

	/**
	 * This method retrieves the list of assignment ids for a client in a specified organization.
	 * If no exceptions are thrown the method is successful.
	 * @param client
	 * @param org
	 * @return List<Integer>
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfAssignmentIdsForClientOrg(Integer clientid, Integer org) {
		List<Integer> assignmentidList = null;
		try {
			// example:
			// SELECT assignments.id FROM clients_assignments, assignments WHERE clients_assignments.clientid=1 AND 
			//                                                                   assignments.orgid=1 AND
			//                                                                   clients_assignments.assignmentid=assignments.id;
			
			Query query = entityManager.createQuery("SELECT eam.id FROM EntityClientsAssignmentsModel ecam, EntityAssignmentModel eam WHERE ecam.clientid = :clientid AND eam.orgid = :org AND ecam.assignmentid = eam.id");
			
			query.setParameter("clientid", clientid);
			query.setParameter("org", org);
	
			assignmentidList = query.getResultList();			
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return assignmentidList;
	}

	/**
	 * This method retrieves the client assignment Id for the indicated client and assignment
	 * If no exceptions are thrown the method is successful.
	 * @param clientId
	 * @param assignmentId
	 * @return Integer
	 */
	@SuppressWarnings("unchecked")
	public Integer getClientAssignmentId(Integer clientId, Integer assignmentId) {

		List<Integer> clientAssignmentId = null;
		
		try {
			Query query = entityManager.createQuery("SELECT e.id FROM EntityClientsAssignmentsModel e WHERE e.clientid = :clientid AND e.assignmentid = :assignid");
			
			query.setParameter("clientid", clientId);
			query.setParameter("assignid", assignmentId);
	
			clientAssignmentId = query.getResultList();			
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		// currently expecting only one result in the list.  Should this be enforced higher up???
		return clientAssignmentId.get(0);
	}
	
	public Date getClientAssignmentDuedate(Integer clientId, Integer assignmentId) {
		List<Date> clientAssignmentDuedate = null;
		
		try {
			Query query = entityManager.createQuery("SELECT e.duedate FROM EntityClientsAssignmentsModel e WHERE e.clientid = :clientid AND e.assignmentid = :assignid");
			
			query.setParameter("clientid", clientId);
			query.setParameter("assignid", assignmentId);
	
			clientAssignmentDuedate = query.getResultList();			
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		// currently expecting only one result in the list.  Should this be enforced higher up???
		return clientAssignmentDuedate.get(0);
	}
	
}