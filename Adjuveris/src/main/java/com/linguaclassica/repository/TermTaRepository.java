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

import com.linguaclassica.entity.EntityTermTaModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class TermTaRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * This method creates a term_ta. If no exceptions are thrown the method is successful.
	 * @param TermTaModel
	 * @throws EntityExistsException
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public int createTermTa(EntityTermTaModel termTaModel) 
			throws EntityExistsException, PersistenceException {

		int termTaId;
		try {
			System.out.println("TermTaRepository, line 39: termTaModel.getTaid() = " + 
					termTaModel.getTaid());
			entityManager.persist(termTaModel);
			entityManager.refresh(termTaModel);
			termTaId = termTaModel.getId();
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		return termTaId;
	}
	
	/**
	 * This method is used to get a list of ta userIds by the termid of the term
	 * If successful, a valid list of userids for tas is returned.
	 * @param termid
	 * @return
	 * @throws NoResultException if the class cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfTaIdsByTermId(int termid) 
			throws NoResultException, PersistenceException {	
		List<Integer> taIdList = null;
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex.taid from EntityTermTaModel ex where ex.termid = :termid");
			query.setParameter("termid", termid);
			
			taIdList = new ArrayList<Integer>();
			taIdList = query.getResultList();
			
			System.out.println("TermStudentsRepository.getListOfTaIdsByTermId termid = " + termid);
			
			return taIdList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method finds a term_ta by taid to enable check for existing term_tas. 
	 * @param taid
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	@SuppressWarnings("unchecked")
	public Integer getListOfTermTasById(EntityTermTaModel termTaModel) 
			throws PersistenceException {
		List<EntityTermTaModel> existingTSMList = new ArrayList<EntityTermTaModel>();
		Query query = null;
		query = entityManager.createQuery("SELECT etsm from EntityTermTaModel etsm where etsm.termid = :termid " +
				"AND etsm.taid = :taid");
		query.setParameter("termid", termTaModel.getTermid());
		query.setParameter("taid", termTaModel.getTaid());		
		existingTSMList = query.getResultList();
		int listlen = existingTSMList.size();
		System.out.println("TermTaRepository, line 101:  termTaModel.getTermid(), listlen = " + 
				termTaModel.getTermid() + ", " + listlen);
		return listlen;
	}
	
	public void deleteTermTa(int taId, int termid) {
		System.out.println("TermTaRepository(line 87).deleteTermTa(teacherId = " + taId + ")");
		
		try {
			Query query = entityManager.createQuery(
				"SELECT ts FROM EntityTermTaModel ts where ts.taid = :taid AND ts.termid = :termid");
		
			query.setParameter("taid", taId);
			query.setParameter("termid", termid);
	
			EntityTermTaModel termTaModel = (EntityTermTaModel) query.getSingleResult();
						
			entityManager.remove(termTaModel);
		}
		catch (NoResultException e) {
			// This exception is thrown by getSingleResult if a match cannot be found.
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public boolean checkTermTa(int taId, int termid) {
		System.out.println("TermTaRepository(line 129).checkTermTa(teacherId = " + taId + ")");
		
		try {
			Query query = entityManager.createQuery(
				"SELECT ts FROM EntityTermTaModel ts where ts.taid = :taid AND ts.termid = :termid");
		
			query.setParameter("taid", taId);
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

