package com.linguaclassica.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.OrgUserPermissionEntity;
import com.linguaclassica.model.OrgUserPermissionModel;

@Repository
public class OrgUserPermissionRepository
{
	@PersistenceContext
    private EntityManager entityManager;

	/**
	 * Create one user item
	 * @param iupModel
	 */
	public void createOne(OrgUserPermissionModel oupModel)
	{
		entityManager.persist(oupModel);
	}

	/**
	 * Determine whether there are multiple user permissions in the table
	 * @param userId
	 * @return
	 */
	public boolean areMultiple(Integer userId)
	{
		Query query = entityManager.createQuery("SELECT COUNT(oup) FROM OrgUserPermissionEntity oup " + 
				"WHERE oup.userid = :userID");
		query.setParameter("userID", userId);
		Long items = (Long) query.getSingleResult();
		return items > 1;
	}
	
	/**
	 * Get the request item
	 * @param oupId
	 * @return
	 */
	public OrgUserPermissionEntity getItem(Integer oupId)
	{
		return entityManager.find(OrgUserPermissionEntity.class, oupId);
	}
	
	/**
	 * Get all items for the user.
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgUserPermissionEntity> getUserItems(Integer userId)
	{
		List<OrgUserPermissionEntity> items = new ArrayList<OrgUserPermissionEntity>();
		Query query = entityManager.createQuery("SELECT oup FROM OrgUserPermissionEntity oup " + 
				"WHERE oup.userid = :userID ");
		query.setParameter("userID", userId);
		items = query.getResultList();
		return items;
	}
	
	/**
	 * Get all of the user IDs that match the passed parameters
	 * @param organizationId
	 * @param permissionId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getUserIds(Integer organizationId, Integer permissionId)
	{
		List<Integer> identifiers = new ArrayList<Integer>();
		Query query = entityManager.createQuery("SELECT oup.userid FROM OrgUserPermissionEntity oup " + 
				"WHERE oup.orgid = :orgID AND oup.permissionid = :permID");
		query.setParameter("orgID", organizationId);
		query.setParameter("permID", permissionId);
		identifiers = query.getResultList();
		return identifiers;
	}
	
	/**
	 * Determine whether the entire passed combination is present
	 * @param organizationId
	 * @param userId
	 * @param permissionId
	 * @return
	 */
	public boolean isExisting(Integer organizationId, Integer userId, Integer permissionId)
	{
		Query query = entityManager.createQuery("SELECT COUNT(oup) FROM OrgUserPermissionEntity oup " + 
				"WHERE oup.orgid = :orgID AND oup.userid = :userID AND oup.permissionid = :permissionID");
		query.setParameter("orgID", organizationId);
		query.setParameter("userID", userId);
		query.setParameter("permissionID", permissionId);
		Long count = (Long) query.getSingleResult();
		return count > 0;
	}
	
	/**
	 * Delete one item that matches the passed parameters
	 * @param organizationId
	 * @param userId
	 * @param permissionId
	 */
	public void deleteOne(Integer organizationId, Integer userId, Integer permissionId)
	{
		Query query = entityManager.createQuery("SELECT oup FROM OrgUserPermissionEntity oup " + 
				"WHERE oup.orgid = :orgID AND oup.userid = :userID AND oup.permissionid = :permissionID");
		query.setParameter("orgID", organizationId);
		query.setParameter("userID", userId);
		query.setParameter("permissionID", permissionId);
		OrgUserPermissionModel oupModel = (OrgUserPermissionModel) query.getSingleResult();
		entityManager.remove(oupModel);
	}
}
