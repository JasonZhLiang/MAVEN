package com.linguaclassica.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityPermissionModel;

@Repository
public class PermissionRepository
{

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Get the item
	 * @param id
	 * @return
	 */
	public EntityPermissionModel getModel(Integer id)
	{
		Query query = entityManager.createQuery("SELECT pe FROM EntityPermissionModel pe WHERE pe.id = :ID");
		query.setParameter("ID", id);
		return (EntityPermissionModel) query.getSingleResult();
	}
}
