package com.linguaclassica.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class OrgsContentItemsRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	

/*	*//**
	 * This method retrieves the list of class ids for a user.
	 * If no exceptions are thrown the method is successful.
	 * @param userId
	 *//*
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfVideoidsByOrgid(Integer orgid) {
		List<Integer> videoidList = null;
		try {
			Query query = entityManager.createQuery("SELECT e.videoid FROM EntityOrgsVideosModel e WHERE e.orgid = :orgid");
			
			query.setParameter("orgid", orgid);
			
			videoidList = query.getResultList();	
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return videoidList;
	}*/
}