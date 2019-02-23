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

import com.linguaclassica.entity.EntityTermStudentsModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class TermStudentsRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * This method creates a term_student. If no exceptions are thrown the method is successful.
	 * @param TermStudentModel
	 * @throws EntityExistsException
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public int createTermStudent(EntityTermStudentsModel termStudentsModel) 
			throws EntityExistsException, PersistenceException {

		int termStudentsId;
		System.out.println("TermStudentsRepository, line 36:  termStudentsModel.getTermid(), " +
				"termStudentsModel.getStudentid() = " + termStudentsModel.getTermid() + ", " + 
				termStudentsModel.getStudentid());
		try {
			entityManager.persist(termStudentsModel);
			entityManager.refresh(termStudentsModel);
			termStudentsId = termStudentsModel.getId();
		} catch (PersistenceException e) {			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		System.out.println("TermStudentsRepository, line 49:  termStudentsId = " + termStudentsId);
		return termStudentsId;
	}
	
	/**
	 * This method finds a term_student by studentid to enable check for existing term_students. 
	 * @param studentid
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	@SuppressWarnings("unchecked")
	public Integer getListOfTermStudentById(EntityTermStudentsModel termStudentsModel) 
			throws PersistenceException {
		List<EntityTermStudentsModel> existingTSMList = new ArrayList<EntityTermStudentsModel>();
		Query query = null;
		query = entityManager.createQuery("SELECT etsm from EntityTermStudentsModel etsm where etsm.termid = :termid " +
				"AND etsm.studentid = :studentid");
		query.setParameter("termid", termStudentsModel.getTermid());
		query.setParameter("studentid", termStudentsModel.getStudentid());		
		existingTSMList = query.getResultList();
		int listlen = existingTSMList.size();
		System.out.println("TermStudentsRepository, line 68:  termStudentsModel.getTermid(), listlen = " + 
				termStudentsModel.getTermid() + ", " + listlen);
		return listlen;
	}
	
	/**
	 * This method is used to get a list of student userIds by the termid of the term
	 * If successful, a valid list of userids for students is returned.
	 * @param termid
	 * @return
	 * @throws NoResultException if the class cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfStudentIdsByTermId(int termid) 
			throws NoResultException, PersistenceException {	
		List<Integer> studentIdList = null;
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex.studentid from EntityTermStudentsModel ex where ex.termid = :termid");
			query.setParameter("termid", termid);
			
			studentIdList = new ArrayList<Integer>();
			studentIdList = query.getResultList();
			
			System.out.println("TermStudentsRepository.getListOfStudentsIdsByTermId termid = " + termid);
			
			return studentIdList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public void deleteTermStudent(int studentId, int termid) {
		System.out.println("TermStudentsRepository(line 87).deleteStudent(studentId = " + studentId + ")");
		
		try {
			Query query = entityManager.createQuery(
				"SELECT ts FROM EntityTermStudentsModel ts where ts.studentid = :studentid AND ts.termid = :termid");
		
			query.setParameter("studentid", studentId);
			query.setParameter("termid", termid);
	
			EntityTermStudentsModel termStudentsModel = (EntityTermStudentsModel) query.getSingleResult();
						
			entityManager.remove(termStudentsModel);
		}
		catch (NoResultException e) {
			// This exception is thrown by getSingleResult if a match cannot be found.
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public boolean checkTermStudent(int studentId, int termid) {
		System.out.println("TermStudentsRepository(line 130).checkTermStudent(studentId = " + studentId + ")");
		
		try {
			Query query = entityManager.createQuery(
				"SELECT ts FROM EntityTermStudentsModel ts where ts.studentid = :studentid AND ts.termid = :termid");
		
			query.setParameter("studentid", studentId);
			query.setParameter("termid", termid);
	
			query.getSingleResult();
		}
		catch (NoResultException e) {
			// This exception is thrown by getSingleResult if a match cannot be found.
			return false;
		}
		
		return true;
	}
	
	
}

