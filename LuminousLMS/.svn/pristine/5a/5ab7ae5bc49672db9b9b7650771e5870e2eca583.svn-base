package com.linguaclassica.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityResourceModel;
import com.linguaclassica.model.ResourceModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class ResourceRepository {
	
	@PersistenceContext
    private EntityManager entityManager;	

	
	public void createResource(ResourceModel resourceModel) throws PersistenceException {
		
		try {
			entityManager.persist(resourceModel);
		} 
		catch (PersistenceException e) {			
			throw e;
		}
	}
	

	
	/**
	 * This method is used to find a resource by ID. If successful, a valid ResourceModel is returned.
	 * 
	 * @param Id
	 * @return
	 * @throws NoResultException if the user cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityResourceModel findResourceById(Integer id) throws NoResultException, PersistenceException {
				
		EntityResourceModel resource = entityManager.find(EntityResourceModel.class, id);
		
		// Check for resource not found
		if (resource == null) {
			throw new NoResultException("Resource does not exist");
		}
		
		// Got here so must have a valid user
		return (EntityResourceModel) resource;
	}
	
	/**
	 * This method retrieves a list of videos by videoid
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityResourceModel> getListOfResourcesByIdList(List<Integer> resourceidList) {
		List<EntityResourceModel> resourceList = null;
		if(resourceidList.size() > 0) {
		    String qryString;
		    qryString="SELECT r FROM EntityResourceModel r WHERE r.id IN (:resourceidList) ORDER BY r.name";
		    
		    try {
				Query query = entityManager.createQuery(qryString);
				query.setParameter("resourceidList", resourceidList);
				resourceList = new ArrayList<EntityResourceModel>();
				resourceList = (List<EntityResourceModel>) query.getResultList();
				
			} catch (NoResultException e) {
				throw new UnknownEntityException(e);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		}
	    return resourceList;
	}
}
	