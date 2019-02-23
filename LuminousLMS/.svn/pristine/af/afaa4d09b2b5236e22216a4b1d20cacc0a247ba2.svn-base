package com.linguaclassica.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityPermissionModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.repository.PermissionRepository;

@Service
@Transactional
public class PermissionService implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionRepository permissionRepository;

	private static Integer clientCacheId = 0;
	private static Integer consultCacheId = 0;
	private static Integer offadCacheId = 0;
	private static Integer adminCacheId = 0;

	/**
	 * Get the item
	 * @param id
	 * @return
	 */
	public EntityPermissionModel getModel(Integer id)
	{
		return permissionRepository.getModel(id);
	}
	
	/**
	 * Gets the permission model
	 * @param permission Permission as enum
	 * @return
	 */
	public EntityPermissionModel getPermissionModel(Permission permission)
	{
		return permissionRepository.getPermissionModel(permission);
	}
	
	/**
	 * Gets the ID of a permission
	 * @param permission Permission as enum
	 * @return
	 */
	public int getPermissionIdFromDB(Permission permission)
	{
		return permissionRepository.getPermissionIdFromDB(permission);
	}
	
	public Integer getCachedPermissionId(Permission permission)
	{
		
		Integer cachedId = 0;
		switch (permission)
		{
			case CLIENT:
				if (0 == clientCacheId)
				{
					clientCacheId = permissionRepository.getPermissionModel(permission).getId();
				}
				cachedId = clientCacheId;
				break;
			case CONSULTANT:
				if (0 == consultCacheId)
				{
					consultCacheId = permissionRepository.getPermissionModel(permission).getId();
				}
				cachedId = consultCacheId;
				break;
			case OFFICE_ADMIN:
				if (0 == offadCacheId)
				{
					offadCacheId = permissionRepository.getPermissionModel(permission).getId();
				}
				cachedId = offadCacheId;
				break;
			case ADMINISTRATOR:
				if (0 == adminCacheId)
				{
					adminCacheId = permissionRepository.getPermissionModel(permission).getId();
				}
				cachedId = adminCacheId;
				break;
			case NONE:
				// Use initialized value
				break;
		}
		return cachedId;
	}
	
	public Integer getPermissionId(Permission permission)
	{
		return permission.ordinal();
	}
}
