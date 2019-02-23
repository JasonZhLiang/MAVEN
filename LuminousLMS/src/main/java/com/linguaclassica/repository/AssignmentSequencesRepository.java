package com.linguaclassica.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityAssignmentSequencesModel;
import com.linguaclassica.model.AssignmentSequencesModel;

@Repository
public class AssignmentSequencesRepository {
	
	@PersistenceContext
    private EntityManager entityManager;	

	
	/**
	 * Create an assignment_sequence.
	 * @param aSeq
	 * @return The id for the new record
	 */
	public int createOne(AssignmentSequencesModel aSeq)
	{		
		entityManager.persist(aSeq);
		entityManager.refresh(aSeq);
		return aSeq.getId();
	}
	
	/**
	 * Delete an assignment_sequence.
	 * @param aSeq
	 */
	public void deleteOne(AssignmentSequencesModel aSeq)
	{		
		entityManager.remove(aSeq);
	}
	
	/**
	 * This method is used to find an assignment_sequence by ID.
	 * @param id
	 */
	public EntityAssignmentSequencesModel getOne(int id)
	{
		return entityManager.find(EntityAssignmentSequencesModel.class, id);
	}
	
	/**
	 * Update an assignment_sequence.
	 * @param aSeq
	 */
	public void update(AssignmentSequencesModel aSeq)
	{		
		entityManager.merge(aSeq);
	}

	@SuppressWarnings("unchecked")
	public List<EntityAssignmentSequencesModel> getAssignmentSequences(Integer assignmentid) {
		Query query = entityManager.createQuery("SELECT s FROM EntityAssignmentSequencesModel s WHERE s.assignmentid = :assignmentid");
		
		query.setParameter("assignmentid", assignmentid);
		
		List<EntityAssignmentSequencesModel> sequences = query.getResultList();
		return sequences;
	}
}
	