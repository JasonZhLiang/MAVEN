package com.linguaclassica.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityContentItemsModel;
import com.linguaclassica.model.ContentItemsModel;

@Repository
public class ContentItemsRepository {
	
	@PersistenceContext
    private EntityManager entityManager;	

	
	/**
	 * Create a content item.
	 * @param cItem
	 * @return The id for the new record
	 */
	public int createOne(ContentItemsModel cItem)
	{		
		entityManager.persist(cItem);
		entityManager.refresh(cItem);
		return cItem.getId();
	}
	
	/**
	 * Delete a content item.
	 * @param cItem
	 */
	public void deleteOne(ContentItemsModel cItem)
	{		
		entityManager.remove(cItem);
	}
	
	/**
	 * This method is used to find a content item by ID.
	 * @param id
	 */
	public EntityContentItemsModel getOne(int id)
	{
		return entityManager.find(EntityContentItemsModel.class, id);
	}
	
	/**
	 * Update a content item.
	 * @param cItem
	 */
	public void update(ContentItemsModel cItem)
	{		
		entityManager.merge(cItem);
	}

	/**
	 * Return the list of content items for a given assignment
	 * @param asgnId
	 */
	@SuppressWarnings("unchecked")
	public List<EntityContentItemsModel> getContentItemsForAnAssignment(int asgnId)
	{
		Query query = entityManager.createQuery(
			"SELECT ci FROM EntityAssignmentSequencesModel seq, EntityContentItemsModel ci "
			+ "WHERE seq.contentid = ci.id AND seq.assignmentid = :asgn "
			+ "ORDER BY seq.sequencenum");
		
		query.setParameter("asgn", asgnId);

		return query.getResultList();
	}
}
	