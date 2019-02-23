package com.linguaclassica.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityOrgModel;

@Repository
public class OrganizationRepository
{
	@PersistenceContext
    private EntityManager entityManager;

	/**
	 * This method creates an organization. Constraint may cause an exceptions to be thrown.
	 * @param orgEntity
	 */
	public Integer createOrganization(EntityOrgModel orgEntity)
	{
		Integer orgId;
		
		entityManager.persist(orgEntity);
		entityManager.refresh(orgEntity);
		orgId = orgEntity.getId();
		return orgId;
	}
	
	/**
	 * Get the data item.
	 * @param organizationId
	 * @return
	 */
	public EntityOrgModel findOrganizationById(Integer organizationId)
	{	
		
		return entityManager.find(EntityOrgModel.class, organizationId);
	}
	
	/**
	 * Get all or the organizations
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EntityOrgModel> getAll()
	{
		Query query = entityManager.createQuery("SELECT org FROM EntityOrgModel org");
		return query.getResultList();
	}
	
	/**
	 * This method updates an organization record.
	 * An exception may be thrown that a calling method must trap.
	 * @param orgEntity
	 */
	public void updateOrganization(EntityOrgModel orgEntity)
	{
		EntityOrgModel eoLocal = entityManager.find(EntityOrgModel.class, orgEntity.getId());
		eoLocal.setOrgname(orgEntity.getOrgname());
		eoLocal.setOrgemail(orgEntity.getOrgemail());
		entityManager.persist(orgEntity);
		entityManager.flush();
	}
}
