package com.linguaclassica.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class AssignmentRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * 
	 * This method creates an assignment. If no exceptions are thrown the method is successful.
	 * 
	 * @param assignmentModel
	 * @throws EntityExistsException if the user already exists.
	 * @throws PersistenceException if an unspecified error occurs.
	 */
	public void createAssignment(AssignmentModel assignmentModel) 
			throws EntityExistsException, PersistenceException {

		try {
			entityManager.persist(assignmentModel);
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		return;
	}
	
	/**
	 * This method is used to find an assignment by its id.
	 * 
	 * If successful, the valid AssignmentModel is returned.
	 * 
	 * @param id
	 * @return
	 * @throws NoResultException if the assignment cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityAssignmentModel findAssignmentById(int id) 
			throws NoResultException, PersistenceException {		
		
		Query query = null;
		query = entityManager.createQuery("SELECT asn from EntityAssignmentModel asn where asn.id = :id");
		query.setParameter("id", id);
		
		EntityAssignmentModel assignmentModel = (EntityAssignmentModel) query.getSingleResult();
		
		// Got here so valid user
		return assignmentModel;
	}

	/**
	 * Get the assignments for the classes attended by the (student) user
	 * that are active for the current day.
	 * @param userId
	 * @param thisDay
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AssignmentModel> findCurrentUserAssignments(Integer userId, Timestamp thisTime)
	{
		List<AssignmentModel> assignments = new ArrayList<AssignmentModel>();
		Query query = entityManager.createQuery("SELECT DISTINCT am " + 
				"FROM EntityAssignmentModel am, EntityAssignmentClassesModel acm, EntityClassUsersModel cum " + 
				"WHERE am.id = acm.assignmentId AND acm.classId = cum.classid AND cum.userid = :userID " + 
				"AND am.startdate <= :currentday AND am.duedate >= :currentday");
		query.setParameter("userID", userId);
		query.setParameter("currentday", thisTime);
		assignments = query.getResultList();
		return assignments;
	}

	/**
	 * This method retrieves the list of all assignments.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentModel> getListOfAssignments() {
		try {
			Query query = entityManager.createQuery(
				"SELECT ex FROM EntityAssignmentModel ex ORDER BY ex.assignmentname ASC");
			
			List<EntityAssignmentModel> assignmentsList = new ArrayList<EntityAssignmentModel>();
	
			assignmentsList = (List<EntityAssignmentModel>) query.getResultList();
			
			System.out.println("AssignmentRepository.getListOfAssignments");
			
			return assignmentsList;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method retrieves the current assignments that have been assigned to a student.
	 * The current date must be between the start date and the due date
	 * The statusid = 2 (active) and weight >= 1
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentModel> getListOfAssignmentsByIdList(String status,String type, List<Integer> assignmentIdList) {
		java.util.Date nowutil = new java.util.Date();
	    java.sql.Date now = new java.sql.Date(nowutil.getTime());
	    String qryString;
	    qryString="SELECT a FROM EntityAssignmentModel a WHERE a.id IN (:assignmentIdList) ";
	    if (status == "CURRENT"){
	    	qryString=qryString+" AND a.startdate <= :now AND a.duedate >= :now ";
	    }
	    else if(status =="COMPLETED"){
	    	qryString=qryString+" AND a.duedate < :now ";
	   }
	   if(type =="PRACTICE"){
	    	qryString=qryString+" AND a.weight = 0 ";
	    }
	    else if(type =="ASSIGNMENT"){
	    	qryString=qryString+" AND a.weight <> 0 ";
	    }
	    qryString=qryString+" ORDER BY a.startdate ASC";
	    		   		
		System.out.println(qryString);
		    
		try {
			Query query = entityManager.createQuery(qryString);
			query.setParameter("assignmentIdList", assignmentIdList);
			query.setParameter("now",now);
			
			List<EntityAssignmentModel> assignedList = new ArrayList<EntityAssignmentModel>();
			assignedList = (List<EntityAssignmentModel>) query.getResultList();
			
			System.out.println("AssignmentRepository.getListOfCurrentAssignmentsByIdList");
			
			return assignedList;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
		
	/**
	 * This method retrieves the current assignments that have been assigned to a student.
	 * The current date must be between the start date and the due date
	 * The statusid = 2 (active) and weight >= 1
	 * type is NOT a parameter here
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentModel> getSimpleListOfAssignmentsByIdList(List<Integer> assignmentIdList) {
		
		System.out.println("AssignmentRepository.getSimpleListOfCurrentAssignmentsByIdList" + 
				"assignmentIdList.size() = " + assignmentIdList.size());
		List<EntityAssignmentModel> assignmentList = null;
		int listlen = assignmentIdList.size();
		if (listlen > 0){		
		
			String qryString;
		    qryString="SELECT a FROM EntityAssignmentModel a WHERE a.id IN (:assignmentIdList) ";
		    qryString=qryString+" ORDER BY a.startdate ASC";
		    		   		
			System.out.println(qryString);
			    
			try {
				Query query = entityManager.createQuery(qryString);
				query.setParameter("assignmentIdList", assignmentIdList);
				//query.setParameter("now",now);
				
				assignmentList = new ArrayList<EntityAssignmentModel>();
				assignmentList = (List<EntityAssignmentModel>) query.getResultList();
				
				System.out.println("AssignmentRepository, line 196: assignedList.size() = " + 
						assignmentList.size());
				
			} catch (NoResultException e) {
				throw new UnknownEntityException(e);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		}		
		return assignmentList;
	}
	
		
	/**
	 * This method retrieves the standardized assignments that have been assigned to a student.
	 * The statusid = 2 (active) and weight >= 1
	 * type is NOT a parameter here
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfStandardAssignmentIdsByIdList(List<Integer> assignmentIdList) {
		
		System.out.println("AssignmentRepository.getListOfStandardAssignmentsByIdList, " + 
				"assignmentIdList.size() = " + assignmentIdList.size());
		List<Integer> standardassignmentIdList = new ArrayList<Integer>();
		int listlen = assignmentIdList.size();
		if (listlen > 0){		
		    String qryString;
		    qryString="SELECT a.id FROM EntityAssignmentModel a WHERE a.id IN (:assignmentIdList)";
		    qryString=qryString+" AND a.type = 'STANDARD' ORDER BY a.startdate ASC";
		    		   		
			System.out.println(qryString);
			    
			try {
				Query query = entityManager.createQuery(qryString);
				query.setParameter("assignmentIdList", assignmentIdList);
				
				//assignmentList = new ArrayList<EntityAssignmentModel>();
				standardassignmentIdList = (List<Integer>) query.getResultList();
				
				System.out.println("AssignmentRepository, line 228: standardassignmentIdList.size() = " + 
						standardassignmentIdList.size());
				
			} catch (NoResultException e) {
				throw new UnknownEntityException(e);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		}		
		return standardassignmentIdList;
	}
	
	@SuppressWarnings("unchecked")
	public List<AssignmentModel> getListOfAssignmentsByStatus(String status) {
		
		String qryString;
	    qryString="SELECT a FROM EntityAssignmentModel a WHERE a.status = :status";   
		try {
			Query query = entityManager.createQuery(qryString);
			query.setParameter("status", status);
			
			List<AssignmentModel> assignList = new ArrayList<AssignmentModel>();
			assignList = (List<AssignmentModel>) query.getResultList();
			return assignList;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentModel> getListOfPracticeAssignmentsByStudentId(Integer studentId) {
		
		String qryString;
	    qryString="SELECT a FROM EntityAssignmentModel a WHERE a.teacherid = :studentId" +
	    		"AND a.status = 'PRACTICE'";   
		try {
			Query query = entityManager.createQuery(qryString);
			query.setParameter("studentId", studentId);
			
			List<EntityAssignmentModel> practiceAssignList = new ArrayList<EntityAssignmentModel>();
			practiceAssignList = (List<EntityAssignmentModel>) query.getResultList();
			
			System.out.println("AssignmentRepository, line 222: assignedList.size() = " + 
					practiceAssignList.size());
			
			return practiceAssignList;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * delete the row by the list of ids, and return the left rows
	 * @param assingmentIdList
	 * @return List<EntityAssignmentModel>
	 */
	public int deleteAssignmentByIdList(List<Integer> assignmentIdList){
		try {
			String queryStr = "DELETE FROM EntityAssignmentModel a WHERE ";
			for ( int i = 0; i < assignmentIdList.size(); i++ ){
				if ( i == 0 ) {
				  queryStr += "a.id = " + assignmentIdList.get(i).intValue();
				} else {
			      queryStr += "or a.id = " + assignmentIdList.get(i).intValue();
				}
			}
			System.out.println(queryStr);
			Query query = entityManager.createQuery(queryStr);
			return query.executeUpdate();
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public void deleteAssignmentById(int assignmentId) 
			throws EntityExistsException, PersistenceException {

		try {
			Query query = entityManager.createQuery(
				"SELECT t FROM EntityAssignmentModel t where t.id = :tid");
		
			query.setParameter("tid", assignmentId);
	
			EntityAssignmentModel assignmentModel = (EntityAssignmentModel) query.getSingleResult();
						
			entityManager.remove(assignmentModel);
			return;
		}
		catch (NoResultException e) {
			// This exception is thrown by getSingleResult if a match cannot be found.
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Returns a list of current assignments from a list of assignment ids
	 * @param assingmentIdList
	 * @return List<EntityAssignmentModel>
	 */
	/*
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentModel> findAssignmentsByAssignmentFactIdList(List<Integer> assignmentfactIdList) {	
		System.out.println("Assignment repository findAssignmentsByAssignmentFactIdList line 160: List of assfactIDs");
		System.out.print("List ");
		for (int afId : assignmentfactIdList )
			System.out.print(afId+", ");
		System.out.println();
		java.util.Date nowutil = new java.util.Date();
	    java.sql.Date now = new java.sql.Date(nowutil.getTime());
		String qryString;
		qryString = "SELECT ex FROM EntityAssignmentModel ex ";
		qryString=qryString+ "WHERE ex.startdate <= :now AND ex.duedate >= :now ";
		qryString=qryString+ "AND ex.assignmentfactid IN (:assignmentfactIdList)";	
		try {
			Query query = null;
			query = entityManager.createQuery(qryString);
			query.setParameter("now", now);
			query.setParameter("assignmentfactIdList", assignmentfactIdList);
			List<EntityAssignmentModel> assignmentList = new ArrayList<EntityAssignmentModel>();
			assignmentList = (List<EntityAssignmentModel>) query.getResultList();
			
			//System.out.println("NotificationRepository.findNotificationsByRangeInIdList.get(0).getNote() = " + notificationList.get(0).getNote());
			
			return assignmentList;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	*/

	/**
	 * 
	 * This method updates an assignment. If no exceptions are thrown the method is successful.
	 * 
	 * @param assignmentModel
	 * @throws EntityExistsException if the user already exists.
	 * @throws PersistenceException if an unspecified error occurs.
	 */
	public void updateAssignment(EntityAssignmentModel assignmentModel) 
			throws EntityExistsException, PersistenceException {

		try {
			entityManager.merge(assignmentModel);
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		return;
	}

}
