package com.linguaclassica.repository;

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

import com.linguaclassica.entity.EntityAssignmentFactModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class AssignmentFactRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * 
	 * This method creates an assignment fact. If no exceptions are thrown the method is successful.
	 * 
	 * @param assignmentfactModel
	 * @throws EntityExistsException if the user already exists.
	 * @throws PersistenceException if an unspecified error occurs.
	 */
	public void createAssignmentFact(EntityAssignmentFactModel assignmentfactModel) 
			throws EntityExistsException, PersistenceException {

		try {
			entityManager.persist(assignmentfactModel);
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
	 * This method is used to find an assignment fact by its assignment id.
	 * If successful, the valid AssignmentFactModel is returned.
	 * @param Integer assignid
	 * @return EntityAssignmentFactModel
	 * @throws NoResultException if the assignment cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityAssignmentFactModel findAssignmentFactByAssignID(Integer userId, Integer assignid) 
			throws NoResultException, PersistenceException {

		Query query = null;
		query = entityManager.createQuery("SELECT ef from EntityAssignmentFactModel ef where "
				+ "ef.studentid = :userId AND ef.assignmentid LIKE :assignid");
		query.setParameter("userId", userId);
		query.setParameter("assignid", assignid);
		
		EntityAssignmentFactModel assignmentfactModel = (EntityAssignmentFactModel) query.getSingleResult();
		
		System.out.println("AssignmentFactRepository.findAssignmentFactByAssignid");
		
		return assignmentfactModel;
	}
	
	/**
	 * This method is used to find an assignment fact by its id.
	 * If successful, the valid AssignmentFactModel is returned.
	 * @param int id
	 * @return EntityAssignmentFactModel
	 * @throws NoResultException if the assignment cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityAssignmentFactModel findAssignmentFactById(int id) 
			throws NoResultException, PersistenceException {		
		
		Query query = null;
		query = entityManager.createQuery("SELECT ef from EntityAssignmentFactModel ef where ef.id = :id");
		query.setParameter("id", id);
		
		EntityAssignmentFactModel assignmentfactModel = (EntityAssignmentFactModel) query.getSingleResult();
		
		System.out.println("AssignmentFactRepository.findAssignmentFactById");
		
		// Got here so valid user
		return assignmentfactModel;
	}

	/**
	 * This method retrieves the list of all assignment facts for a class.
	 * If no exceptions are thrown the method is successful.
	 * @param Integer classId
	 * @return List<EntityAssignmentFactModel>
	 * @throws ServiceException if an unspecified error occurs.
	 * @throws UnknownEntityException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentFactModel> getListOfAssignmentFactByClassId(Integer classId)
	throws UnknownEntityException, ServiceException{
    
		try {
			Query query = entityManager.createQuery(
				"SELECT ef FROM EntityAssignmentFactModel ef WHERE ef.classid = :classId");
		
			query.setParameter("classId", classId);
			
			List<EntityAssignmentFactModel> assignmentfactList = new ArrayList<EntityAssignmentFactModel>();
	
			assignmentfactList = (List<EntityAssignmentFactModel>) query.getResultList();	
			
			System.out.println("AssignmentFactRepository.getListOfAssignmentFactByClassId");
			
			return assignmentfactList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * This method retrieves the list of assignment facts of a particular status, i.e. COMPLETED, for a class.
	 * If no exceptions are thrown the method is successful.
	 * --Edited from ORDER BY a.duedate DESC, af.id DESC to ORDER BY a.duedate ASC, af.id ASC
	 *   12-16-2015: Kwirtanen
	 * @param Integer classId, String status 
	 * @throws UnknownEntityException
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentFactModel> getListOfAssignmentFactByClassId(Integer classId, String status) 
	throws UnknownEntityException, ServiceException{
		System.out.println("AssignmentFactRepository: getListOfAssignmentFactByClassId() using status");
		System.out.println("AssignmentFactRepository:  status = " + status);

		java.util.Date nowutil = new java.util.Date();
	    java.sql.Date now = new java.sql.Date(nowutil.getTime());
	    String qryString;
	    Boolean useNow = false;
	    qryString = "SELECT af FROM EntityAssignmentFactModel af ";
	   
	    qryString=qryString	+ ", EntityAssignmentModel a ";
	    qryString=qryString	+ " WHERE  af.classid = :classid ";
	    qryString=qryString	+ " AND af.assignmentid = a.id ";
	   
	  if (status.equals("WAIT")){
	    	qryString=qryString+" AND a.startdate <= :now AND a.duedate >= :now ";
	    	useNow = true;
	    }
	    else if(status.equals("COMPLETED")){
	    	qryString=qryString+" AND a.status = 'COMPLETED' AND a.duedate < :now ";
	    	useNow = true;
	   }
		   
	    else if(status.equals("PRACTICE")){
	    	qryString=qryString+" AND a.status = 'PRACTICE' ";
	    }
	   /* if(type == "ASSIGNMENT"){
	    	qryString=qryString+" AND a.weight <> 0 ";
	    }*/
	    qryString=qryString+" ORDER BY a.duedate ASC, af.id ASC";
	    		   		
	    System.out.println("AssignmentFactRepository: qryString: "+ qryString);
		try {
			Query query = entityManager.createQuery(qryString);
			query.setParameter("classid", classId);
			//query.setParameter("weight",0);
			if (useNow){query.setParameter("now",now);}
			List<EntityAssignmentFactModel> assignedfactList = new ArrayList<EntityAssignmentFactModel>();
			assignedfactList = (List<EntityAssignmentFactModel>) query.getResultList();
			
			System.out.println("AssignmentFactRepository.getListOfAssignmentFactByClassId --  Assignment List Size = " + assignedfactList.size());
			
			return assignedfactList;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}



	/**
	 * This method retrieves the list of assignment ids from a list of classids.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 * @throws UnknownEntityException
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfAssignIdByClassId(List<Integer> classIdList) 
			throws UnknownEntityException, ServiceException{
		
		List<Integer> assignmentIdList = new ArrayList<Integer>();
		
		if (classIdList.size() > 0) {
    
			try {
				Query query = entityManager.createQuery(
					"SELECT ef.assignmentid FROM EntityAssignmentFactModel ef WHERE ef.classid IN :classIdList");
			
				query.setParameter("classIdList", classIdList);
		
				assignmentIdList = (List<Integer>) query.getResultList();	
				
				System.out.println("AssignmentFactRepository.getListOfAssignIdByClassId");
				
			}catch (NoResultException e) {
				throw new UnknownEntityException(e);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		}
		
		return assignmentIdList;
		
	}

	/**
	 * This method retrieves the list of assignment facts for a list of classids.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 * @throws UnknownEntityException
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfAssignmentFactIdsByClassIdList(Integer classId)
		throws ServiceException, UnknownEntityException{
		try {
			Query query = entityManager.createQuery(
				"SELECT ef.id FROM EntityAssignmentFactModel ef WHERE ef.classid  = :classId");
		
			query.setParameter("classId", classId);
			
			List<Integer> assignmentfactidList = new ArrayList<Integer>();
	
			assignmentfactidList = (List<Integer>) query.getResultList();	
			
			System.out.println("AssignmentFactRepository.getListOfAssignmentFactByClassIdList");
			
			return assignmentfactidList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method retrieves the list of assignment facts for an assignment.
	 * If no exceptions are thrown the method is successful.
	 * @throws UnknownEntityException
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentFactModel> getListOfAssignmentFactByAssignmentId(Integer assignmentId) 
			throws UnknownEntityException, ServiceException{
		try {
			Query query = entityManager.createQuery(
				"SELECT af FROM EntityAssignmentFactModel af WHERE af.assignmentid = :assignmentId");
		
			query.setParameter("assignmentId", assignmentId);
			
			List<EntityAssignmentFactModel> assignmentFactList = new ArrayList<EntityAssignmentFactModel>();
	
			assignmentFactList = (List<EntityAssignmentFactModel>) query.getResultList();	
			
			System.out.println("AssignmentFactRepository.getListOfAssignmentFactByAssignmentId");
			
			return assignmentFactList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * This method retrieves the list of all assignments.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 * @throws UnknownEntityException
	 */
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentFactModel> getListOfAssignmentFact() 
			throws UnknownEntityException, ServiceException{
		try {
			Query query = entityManager.createQuery(
				"SELECT ef FROM EntityAssignmentFactModel ef ORDER BY ef.id ASC");
			
			List<EntityAssignmentFactModel> assignmentfactList = new ArrayList<EntityAssignmentFactModel>();
	
			assignmentfactList = (List<EntityAssignmentFactModel>) query.getResultList();
			
			System.out.println("AssignmentFactRepository.getListOfAssignmentFact");
			
			return assignmentfactList;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method retrieves the list of all assignment facts for one student for a certain status.
	 * If termIdList is not null, only assignment facts from the given terms are returned.
	 * If no exceptions are thrown the method is successful.
	 * --Edited from ORDER BY a.duedate DESC, af.id DESC to ORDER BY a.duedate ASC, af.id ASC
	 *   12-16-2015: Kwirtanen
	 * @throws ServiceException if an unspecified error occurs.
	 * @throws UnknownEntityException
	 */
	@SuppressWarnings("unchecked")
	public List<EntityAssignmentFactModel> getListOfAssignmentFactByStudentId(String statuss,String type, Integer studentid, List<Integer> termIdList) 
			throws UnknownEntityException, ServiceException{
		System.out.println("AssignmentFactRepository: getListOfAssignmentFactByStudentId()");
		System.out.println("AssignmentFactRepository:  statuss = " + statuss);
		java.util.Date nowutil = new java.util.Date();
	    java.sql.Date now = new java.sql.Date(nowutil.getTime());
	    String qryString;
	    Boolean useNow = false;

	    qryString = "SELECT af FROM EntityAssignmentFactModel af ";
	   	    qryString=qryString	+ ", EntityAssignmentModel a ";
		if (termIdList != null)
		    qryString=qryString	+ ", EntityClassModel cl ";
	    	
        qryString=qryString	+ " WHERE  af.assignmentid = a.id ";
	    if (termIdList != null)
		    qryString=qryString	+ " AND af.classid = cl.id ";
	    
	    qryString=qryString	+ " AND af.studentid = :studentid ";
	    
	    if (termIdList != null)
		    qryString=qryString	+ " AND cl.termid in (:termidlist) ";
 
	  if (statuss.equals("WAIT")){
	    	qryString=qryString+" AND a.startdate <= :now AND a.duedate >= :now ";
	    	useNow = true;
	    }
	    else if(statuss.equals("COMPLETED")){
	    	qryString=qryString+" AND a.status = 'COMPLETED' AND a.duedate < :now ";
	    	useNow = true;
	   }
		   
	    else if(statuss.equals("PRACTICE")){
	    	qryString=qryString+" AND a.status = 'PRACTICE' ";
	    }
	    if(type == "ASSIGNMENT"){
	    	qryString=qryString+" AND a.weight <> 0 ";
	    }
	    qryString=qryString+" ORDER BY a.duedate ASC, af.id ASC";
	    		   		
	    System.out.println("AssignmentFactRepository: qryString: "+ qryString);
	    System.out.println("AssignmentFactRepository: StudentID: "+ studentid);
		try {
			Query query = entityManager.createQuery(qryString);
			query.setParameter("studentid", studentid);
			//query.setParameter("weight",0);
			if (useNow){
				query.setParameter("now",now);
			}
		    if (termIdList != null)
				query.setParameter("termidlist", termIdList);

			List<EntityAssignmentFactModel> assignedfactList;
			assignedfactList = (List<EntityAssignmentFactModel>) query.getResultList();
			
			System.out.println("AssignmentFactRepository.getListOfAssignmentFactByStudentId --  "
					+ "Assignment List Size = " + assignedfactList.size());
			
			return assignedfactList;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public Integer getCountByUserAndAssignmentId(Integer userId, Integer assignId){
		int count = 0;
		System.out.println("AssignmentFactRepository:  getCountbyUserAndAssignementId");
		try {
			Query query = entityManager.createQuery("SELECT COUNT(af) FROM EntityAssignmentFactModel af "
					+ "WHERE af.studentid = :userId AND af.assignmentid = :assignId");
			query.setParameter("userId", userId);
			query.setParameter("assignId", assignId);
			count = ((Number) query.getSingleResult()).intValue();
			return count;
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
