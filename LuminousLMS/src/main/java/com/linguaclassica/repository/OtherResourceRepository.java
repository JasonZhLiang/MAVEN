package com.linguaclassica.repository;

import java.sql.Blob;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityMessageModel;
import com.linguaclassica.entity.EntityOtherResourceModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class OtherResourceRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/* Returns a list of other_resources that come from a particular orgId
	 * @param orgId
	 * @return List<EntityOtherResourceModel>
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<EntityOtherResourceModel> findOtherResourceByOrgId(Integer orgId) 
	{
		try
		{
		Query query = null;
		query = entityManager.createQuery("SELECT ex FROM EntityOtherResourceModel ex WHERE ex.orgid = :orgid ");
		query.setParameter("orgid", orgId);
		
		List<EntityOtherResourceModel> otherResList = (List<EntityOtherResourceModel>) query.getResultList();
		
		//System.out.println("otherResList.get(0).getResName() = " + otherResList.get(0).getResName());
		return otherResList;
		}
		catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	
	
	
	
	/* Returns an other_resources entity that come from a particular id
	 * @param id
	 * @return EntityOtherResourceModel
	 *
	 */
	@SuppressWarnings("unchecked")
	public EntityOtherResourceModel findOtherResourceById(Integer id) 
	{	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex FROM EntityOtherResourceModel ex WHERE ex.id = :id ");
			query.setParameter("id", id);
			
			EntityOtherResourceModel entityOtherResourceModel = (EntityOtherResourceModel) query.getSingleResult();
			
			//System.out.println("EntityOtherResourceModel.findOtherResourceById.getSubject() = " + entityOtherResourceModel.get);
			
			return entityOtherResourceModel;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Integer createOtherResource(EntityOtherResourceModel otherResourceRepository)
	{
		Integer id;
		entityManager.persist(otherResourceRepository);
		entityManager.refresh(otherResourceRepository);
		id = otherResourceRepository.getId();
		return id;
	}
}
