package com.linguaclassica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityOrgModel;
import com.linguaclassica.repository.OrganizationRepository;

@Service
@Transactional
public class OrganizationService
{
	@Autowired
	private OrganizationRepository organizationRepository;

	/**
	 * This method creates an organization. Constraint may cause an exceptions to be thrown.
	 * @param orgEntity
	 */
	public Integer createOrganization(EntityOrgModel orgEntity)
	{
		return organizationRepository.createOrganization(orgEntity);
	}
	
	/**
	 * Get the data item.
	 * @param organizationId
	 * @return
	 */
	public EntityOrgModel findOrganizationById(Integer organizationId)
	{
		return organizationRepository.findOrganizationById(organizationId);
	}
	
	/**
	 * Get all or the organizations
	 * @return
	 */
	public List<EntityOrgModel> getAll()
	{
		return organizationRepository.getAll();
	}
	
	/**
	 * This method updates an organization record.
	 * An exception may be thrown that a calling method must trap.
	 * @param orgEntity
	 */
	public void updateOrganization(EntityOrgModel orgEntity)
	{
		organizationRepository.updateOrganization(orgEntity);
	}
}
