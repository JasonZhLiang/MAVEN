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

import com.linguaclassica.entity.EntityTermTeachersModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class TermTeachersRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * This method creates a term_teacher. If no exceptions are thrown the method is successful.
	 * @param TermTeacherModel
	 * @throws EntityExistsException
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public int createTermTeacher(EntityTermTeachersModel termTeachersModel) 
			throws EntityExistsException, PersistenceException {

		int termTeachersId;
		System.out.println("TermTeachersRepository, lin 37: termTeachersModel.getTeacherid() = " + 
				termTeachersModel.getTeacherid());
		try {
			entityManager.persist(termTeachersModel);
			entityManager.refresh(termTeachersModel);
			termTeachersId = termTeachersModel.getId();
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		return termTeachersId;
	}
	
	/**
	 * This method is used to get a list of teacher userIds by the termid of the term
	 * If successful, a valid list of userids for teachers is returned.
	 * @param termid
	 * @return
	 * @throws NoResultException if the class cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfTeacherIdsByTermId(int termid) 
			throws NoResultException, PersistenceException {	
		List<Integer> teacherIdList = null;
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex.teacherid from EntityTermTeachersModel ex where ex.termid = :termid");
			query.setParameter("termid", termid);
			
			teacherIdList = new ArrayList<Integer>();
			teacherIdList = query.getResultList();
			
			System.out.println("TermTeachersRepository.getListOfTeachersIdsByTermId termid = " + termid);
			
			return teacherIdList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method finds a term_teacher by teacherid to enable check for existing term_teachers. 
	 * @param teacherid
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	@SuppressWarnings("unchecked")
	public Integer getListOfTermTeachersById(EntityTermTeachersModel termTeachersModel) 
			throws PersistenceException {
		List<EntityTermTeachersModel> existingTSMList = new ArrayList<EntityTermTeachersModel>();
		Query query = null;
		query = entityManager.createQuery("SELECT etsm from EntityTermTeachersModel etsm where etsm.termid = :termid " +
				"AND etsm.teacherid = :teacherid");
		query.setParameter("termid", termTeachersModel.getTermid());
		query.setParameter("teacherid", termTeachersModel.getTeacherid());		
		existingTSMList = query.getResultList();
		int listlen = existingTSMList.size();
		System.out.println("TermTeachersRepository, line100:  termTeachersModel.getTermid(), listlen = " + 
				termTeachersModel.getTermid() + ", " + listlen);
		return listlen;
	}
	
	/**
	 * This method is used to remove a teacher userId from the term_teachers list.
	 * @param teacherid
	 * @return
	 * @throws NoResultException if the class cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public void deleteTermTeacher(int teacherId, int termid) {
		System.out.println("TermTeachersRepository(line 87).deleteTermTeacher(teacherId = " + teacherId + ")");
		
		try {
			Query query = entityManager.createQuery(
				"SELECT ts FROM EntityTermTeachersModel ts where ts.teacherid = :teacherid AND ts.termid = :termid");
		
			query.setParameter("teacherid", teacherId);
			query.setParameter("termid", termid);
	
			EntityTermTeachersModel termTeachersModel = (EntityTermTeachersModel) query.getSingleResult();
						
			entityManager.remove(termTeachersModel);
		}
		catch (NoResultException e) {
			// This exception is thrown by getSingleResult if a match cannot be found.
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public boolean checkTermTeacher(int teacherId, int termid) {
		System.out.println("TermTeachersRepository(line 136).checkTermTeacher(teacherId = " + teacherId + ")");
		
		try {
			Query query = entityManager.createQuery(
				"SELECT ts FROM EntityTermTeachersModel ts where ts.teacherid = :teacherid AND ts.termid = :termid");
		
			query.setParameter("teacherid", teacherId);
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

