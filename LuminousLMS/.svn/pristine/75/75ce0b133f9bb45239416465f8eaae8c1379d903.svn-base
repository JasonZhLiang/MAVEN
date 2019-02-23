package com.linguaclassica.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityPermissionModel;
import com.linguaclassica.model.PermissionModel.Permission;

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
		/*
		Query query = entityManager.createQuery("SELECT pe FROM EntityPermissionModel pe WHERE pe.id = :ID");
		query.setParameter("ID", id);
		return (EntityPermissionModel) query.getSingleResult();
		*/
		return entityManager.find(EntityPermissionModel.class, id);
	}

	/**
	 * Gets the permission model
	 * @param permission Permission as enum
	 * @return
	 */
	public EntityPermissionModel getPermissionModel(Permission permission)
	{
		TypedQuery<Integer> query = entityManager.createQuery("SELECT p.id " +
				"from EntityPermissionModel p where p.permission = :permission", Integer.class);
		query.setParameter("permission", permission);
		Integer permissionId = query.getSingleResult();
		EntityPermissionModel entity = new EntityPermissionModel(permissionId, permission);
		return entity;
	}
	
	/**
	 * Gets the ID of a permission
	 * @param permission Permission as enum
	 * @return
	 */
	public int getPermissionIdFromDB(Permission permission)
	{
		TypedQuery<Integer> query = entityManager.createQuery("SELECT p.id " +
				"from EntityPermissionModel p where p.permission = :permission", Integer.class);
		query.setParameter("permission", permission);
		return query.getSingleResult();
	}
}
