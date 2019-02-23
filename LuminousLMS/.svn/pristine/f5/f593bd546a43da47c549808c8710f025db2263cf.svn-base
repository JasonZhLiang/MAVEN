package com.linguaclassica.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityAssignmentModel;

@Repository
public class AssignmentRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	

	/**
	 * Creates a new assignment
	 * @param EntityAssignmentModel
	 */
	public void createAssignment(EntityAssignmentModel assignment) {
		entityManager.persist(assignment);
	}
	
	public void updateAssignment(EntityAssignmentModel assignment) {
		entityManager.persist(assignment);
	}
	
	public void removeAssignment(EntityAssignmentModel assignment) {
		entityManager.remove(assignment);
	}
	
	public EntityAssignmentModel getAssignment(Integer assignmentId) {
		EntityAssignmentModel assignment = entityManager.find(EntityAssignmentModel.class, assignmentId);
		return assignment;
	}
	
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentModel> getAssignmentsForOrgId(Integer orgid) {
		List<EntityAssignmentModel> assignments;
		
		Query query = entityManager.createQuery("SELECT a FROM EntityAssignmentModel a WHERE a.orgid = :orgid");
		query.setParameter("orgid", orgid);
		
		assignments = query.getResultList();
		
		return assignments;
	}
	
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentModel> getAssistedAssignmentsForOrgId(Integer orgid) {
		List<EntityAssignmentModel> assignments;
		
		Query query = entityManager.createQuery("SELECT a FROM EntityAssignmentModel a WHERE a.orgid = :orgid AND a.assisted = 1");
		
		query.setParameter("orgid", orgid);
		assignments = query.getResultList();
		
		return assignments;
	}
	
/*	*//**
	 * Return the list of assignments for a single client
	 * @param orgid
	 * @param clientid
	 *//*
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentModel> getClientAssignmentsList(int orgid, int clientid)
	{
		Query query = entityManager.createQuery(
			"SELECT asgn FROM EntityClientsAssignmentsModel eca, EntityAssignmentModel asgn "
			+ "WHERE eca.assignmentid = asgn.id AND eca.clientid = :cli");
		
	//	query.setParameter("org", orgid);
		query.setParameter("cli", clientid);

		return query.getResultList();
	}*/

/*	*//**
	 * Return the complete list of assignments for an organization
	 * @param orgid
	 *//*
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentModel> getOrgAssignmentsList(int orgid)
	{
		Query query = entityManager.createQuery(
			"SELECT asgn FROM EntityOrgsAssignmentsModel oa, EntityAssignmentModel asgn "
			+ "WHERE oa.assignmentid = asgn.id AND oa.orgid = :org");
		
		query.setParameter("org", orgid);

		return query.getResultList();
	}*/

/*	*//**
	 * Return the list of assignments for an organization with the given assisted flag
	 * @param orgid
	 * @param assisted
	 */
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentModel> getOrgAssignmentsList(int orgid, boolean assisted)
	{
		Query query = entityManager.createQuery(
			"SELECT asgn FROM EntityAssignmentModel asgn "
			+ "WHERE asgn.orgid = :org AND asgn.assisted = :astd");
		
		query.setParameter("org", orgid);
		query.setParameter("astd", assisted);

		return query.getResultList();
	}

	/**
	 * This method retrieves a list of assignments by assignmentid list
	 * @param assignmentIdList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentModel> getListOfAssignmentsByIdList(List<Integer> assignmentIdList)
	{
		List<EntityAssignmentModel> assignmentEntityList = new ArrayList<EntityAssignmentModel>();
		if (assignmentIdList.size() > 0)
		{
		    String qryString;
		    qryString="SELECT a FROM EntityAssignmentModel a WHERE a.id IN (:assignmentIdList) ORDER BY a.name";
			Query query = entityManager.createQuery(qryString);
			query.setParameter("assignmentIdList", assignmentIdList);
			assignmentEntityList = (List<EntityAssignmentModel>) query.getResultList();
		}
	    return assignmentEntityList;
	}
	
}
	