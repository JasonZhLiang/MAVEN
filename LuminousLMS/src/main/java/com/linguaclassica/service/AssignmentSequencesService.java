package com.linguaclassica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityAssignmentSequencesModel;
import com.linguaclassica.model.AssignmentSequencesModel;
import com.linguaclassica.repository.AssignmentSequencesRepository;

@Service
@Transactional
public class AssignmentSequencesService {
	
	@Autowired
	private AssignmentSequencesRepository AssignmentSequencesRepository;

	/**
	 * Create an assignment_sequence.
	 * @param aSeq
	 * @return The id for the new record
	 */
	public int createOne(AssignmentSequencesModel aSeq)
	{
		return AssignmentSequencesRepository.createOne(aSeq);
	}
	
	/**
	 * Delete an assignment_sequence.
	 * @param aSeq
	 */
	public void deleteOne(AssignmentSequencesModel aSeq)
	{
		AssignmentSequencesRepository.deleteOne(aSeq);
	}
	
	/**
	 * This method is used to find an assignment_sequence by ID.
	 * @param id
	 */
	public EntityAssignmentSequencesModel getOne(int id)
	{
		return AssignmentSequencesRepository.getOne(id);
	}
	
	/**
	 * Update an assignment_sequence.
	 * @param aSeq
	 */
	public void update(AssignmentSequencesModel aSeq)
	{
		AssignmentSequencesRepository.update(aSeq);
	}

	public List<EntityAssignmentSequencesModel> getAssignmentSequences(Integer assignmentid) {
		List<EntityAssignmentSequencesModel> sequences = AssignmentSequencesRepository.getAssignmentSequences(assignmentid);
		return sequences;
	}
}