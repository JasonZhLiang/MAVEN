package com.linguaclassica.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.model.OrgsResourcesModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class OrgsResourcesRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void createOrgsResourcesRecord(OrgsResourcesModel orgsResourcesModel) {
		
		try {
			entityManager.persist(orgsResourcesModel);
		}
		catch (PersistenceException pe){
			pe.printStackTrace();
		}
		return;
	}

	/**
	 * This method retrieves the list of class ids for a user.
	 * If no exceptions are thrown the method is successful.
	 * @param userId
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfResourceidsByOrgid(Integer orgid) {
		List<Integer> resourceidList = null;
		try {
			Query query = entityManager.createQuery("SELECT e.resourceid FROM EntityOrgsResourcesModel e WHERE e.orgid = :orgid");
			
			query.setParameter("orgid", orgid);
	
			resourceidList = query.getResultList();			
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return resourceidList;
	}
}