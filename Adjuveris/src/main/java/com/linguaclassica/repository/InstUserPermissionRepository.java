package com.linguaclassica.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.InstUserPermissionEntity;
import com.linguaclassica.model.InstUserPermissionModel;

@Repository
public class InstUserPermissionRepository
{
	@PersistenceContext
    private EntityManager entityManager;
	

	/**
	 * Create one user item
	 * @param iupModel
	 */
	public void createOne(InstUserPermissionModel iupModel)
	{
		entityManager.persist(iupModel);
	}

	/**
	 * Determine whether there are multiple user permissions in the table
	 * @param userId
	 * @return
	 */
	public boolean areMultiple(Integer userId)
	{
		Query query = entityManager.createQuery("SELECT COUNT(iup) FROM InstUserPermissionEntity iup " + 
				"WHERE iup.userid = :userID");
		query.setParameter("userID", userId);
		Long items = (Long) query.getSingleResult();
		return items > 1;
	}
	
	/**
	 * Get the request item
	 * @param iupId
	 * @return
	 */
	public InstUserPermissionEntity getItem(Integer iupId)
	{
		return entityManager.find(InstUserPermissionEntity.class, iupId);
	}
	
	/**
	 * Get all items for the user.
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InstUserPermissionEntity> getUserItems(Integer userId)
	{
		List<InstUserPermissionEntity> items = new ArrayList<InstUserPermissionEntity>();
		Query query = entityManager.createQuery("SELECT iup FROM InstUserPermissionEntity iup " + 
				"WHERE iup.userid = :userID ");
		query.setParameter("userID", userId);
		items = query.getResultList();
		return items;
	}
	
	/**
	 * Get all of the user IDs that match the passed parameters
	 * @param institutionId
	 * @param permissionId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getUserIds(Integer institutionId, Integer permissionId)
	{
		List<Integer> identifiers = new ArrayList<Integer>();
		Query query = entityManager.createQuery("SELECT iup.userid FROM InstUserPermissionEntity iup " + 
				"WHERE iup.instid = :instID AND iup.permissionid = :permID");
		query.setParameter("instID", institutionId);
		query.setParameter("permID", permissionId);
		identifiers = query.getResultList();
		return identifiers;
	}
	
	/**
	 * Determine whether the entire passed combination is present
	 * @param institutionId
	 * @param userId
	 * @param permissionId
	 * @return
	 */
	public boolean isExisting(Integer institutionId, Integer userId, Integer permissionId)
	{
		Query query = entityManager.createQuery("SELECT COUNT(iup) FROM InstUserPermissionEntity iup " + 
				"WHERE iup.instid = :institutionID AND iup.userid = :userID AND iup.permissionid = :permissionID");
		query.setParameter("institutionID", institutionId);
		query.setParameter("userID", userId);
		query.setParameter("permissionID", permissionId);
		Long count = (Long) query.getSingleResult();
		return count > 0;
	}
	
	/**
	 * Delete one item that matches the passed parameters
	 * @param institutionId
	 * @param userId
	 * @param permissionId
	 */
	public void deleteOne(Integer institutionId, Integer userId, Integer permissionId)
	{
		Query query = entityManager.createQuery("SELECT iup FROM InstUserPermissionEntity iup " + 
				"WHERE iup.instid = :institutionID AND iup.userid = :userID AND iup.permissionid = :permissionID");
		query.setParameter("institutionID", institutionId);
		query.setParameter("userID", userId);
		query.setParameter("permissionID", permissionId);
		InstUserPermissionModel iupModel = (InstUserPermissionModel) query.getSingleResult();
		entityManager.remove(iupModel);
	}
}
