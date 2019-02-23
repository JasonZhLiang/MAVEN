package com.linguaclassica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.repository.AssignmentRepository;


@Service
@Transactional
public class AssignmentService {
	
	@Autowired
	private AssignmentRepository assignmentRepository;

	//private Logger logger = LogManager.getLogger(AssignmentService.class);

	/**
	 * Return the complete list of assignments for an organization
	 * @param orgid
	 */
	public List<EntityAssignmentModel> getOrgAssignmentsList(int orgid)
	{
		return assignmentRepository.getAssignmentsForOrgId(orgid);
	}
	
	/*	*//**
	 * Return the list of assignments for an organization with the given assisted flag
	 * @param orgid
	 * @param assisted
	 */
	public List<EntityAssignmentModel> getOrgAssignmentsList(int orgid, boolean assisted)
	{
		return assignmentRepository.getOrgAssignmentsList(orgid, assisted);
	}

	/**
	 * Return the list of assignments for an organization with the given assisted flag
	 * @param orgid
	 * @param assisted
	 */
	public List<EntityAssignmentModel> getAssistedAssignmentsList(int orgid, boolean assisted)
	{
		return assignmentRepository.getAssistedAssignmentsForOrgId(orgid);
	}
	
	public List<EntityAssignmentModel> getAssignmentsList(List<Integer> assignmentIds) {
		return assignmentRepository.getListOfAssignmentsByIdList(assignmentIds);
	}
	
/*	*//**
	 * Return the list of assignments for a single client
	 * @param orgid
	 * @param clientid
	 *//*
	public List<EntityAssignmentModel> getClientAssignmentsList(int orgid, int clientid)
	{
		return assignmentRepository.getClientAssignmentsList(orgid, clientid);
	}

	*//**
	 * Return the list of assignments of the specified type for a single client
	 * @param orgid
	 * @param clientid
	 * @param typeid
	 *//*
	public List<EntityAssignmentModel> getClientAssignmentsList(int orgid, int clientid, int typeid)
	{
		return assignmentRepository.getClientAssignmentsList(orgid, clientid);
	}*/
}