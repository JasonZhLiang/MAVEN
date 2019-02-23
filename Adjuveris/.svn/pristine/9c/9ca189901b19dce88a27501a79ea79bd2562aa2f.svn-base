package com.linguaclassica.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityPermissionModel;
import com.linguaclassica.repository.PermissionRepository;

@Service
@Transactional
public class PermissionService implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionRepository permissionRepository;

	/**
	 * Get the item
	 * @param id
	 * @return
	 */
	public EntityPermissionModel getModel(Integer id)
	{
		return permissionRepository.getModel(id);
	}

}
