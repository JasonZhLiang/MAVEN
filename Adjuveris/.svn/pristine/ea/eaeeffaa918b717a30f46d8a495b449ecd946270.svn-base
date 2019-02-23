package com.linguaclassica.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.model.TermModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class TermRepository {
	
	@PersistenceContext
    private EntityManager entityManager;

	public static final int BEGINNING_OF_TIME = -1;
	
	/**
	 * This method creates a term. If no exceptions are thrown the method is successful.
	 * @param termModel
	 * @throws EntityExistsException
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public int createTerm(EntityTermModel termModel) 
			throws EntityExistsException, PersistenceException {

		int termId;
		try {
			entityManager.persist(termModel);
			entityManager.refresh(termModel);
			termId = termModel.getId();
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			
			// Didn't match, throw the original exception
			throw e;
		}
		return termId;
	}
	
	/**
	 * This method updates a term. If no exceptions are thrown the method is successful.
	 * @param termModel
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public void updateTerm(TermModel termModelIn)
	{
		System.out.format("TermRepository.updateTerm(%d '%s')%n", termModelIn.getId(), termModelIn.getTermname());

		TermModel termModel = entityManager.find(EntityTermModel.class,termModelIn.getId());
		termModel.setTermname(termModelIn.getTermname());
		termModel.setStartDate(termModelIn.getStartDate());
		termModel.setEndDate(termModelIn.getEndDate());
		entityManager.persist(termModel);
		entityManager.flush();
	 }
	
	/**
	 * This method is used to find a term by its id.
	 * If successful, the valid TermModel is returned.
	 * @param id
	 * @return
	 * @throws NoResultException if the class cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityTermModel findTermById(int id) 
			throws NoResultException, PersistenceException {	
		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex from EntityTermModel ex where ex.id = :id");
			query.setParameter("id", id);
			
			EntityTermModel termModel = (EntityTermModel) query.getSingleResult();
			
			System.out.println("TermRepository.findTermById.getClassName() = " + termModel.getTermname());
			
			return termModel;
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * This method is used to find a term by its name.
	 * If successful, the valid TermModel is returned.
	 * @param id
	 * @return EntityTermModel
	 * @throws NoResultException if the class cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public EntityTermModel findTermByName(String termName) 
			throws NoResultException, PersistenceException {		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT ex from EntityTermModel ex where ex.termname = :termName");
			query.setParameter("termName", termName);
			
			EntityTermModel termModel = (EntityTermModel) query.getSingleResult();
			
			System.out.println("TermRepository.findTermByName.getId() = " + termModel.getId());
			
			return termModel;
		
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * This method retrieves the list of terms for an institution.
	 * If no exceptions are thrown the method is successful.
	 * @param institutionid  
	 * @return List<EntityClassModel>
	 */
	@SuppressWarnings("unchecked")
	public List<EntityTermModel> getListOfTermsByInstId(Integer instId) {

		try {
			System.out.println("got to TermRepository line 121, instId = " + instId);
		
			Query query = null;
			query = entityManager.createQuery("SELECT ex from EntityTermModel ex where ex.instid = :instId " +
					"ORDER BY ex.startdate DESC");
			query.setParameter("instId", instId);
			List<EntityTermModel> termslist = query.getResultList();
		
			System.out.println("TermRepository, line 129:  termslist.size() = " + termslist.size());
						
			return termslist;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Get the terms where the teacher is assigned to the term of the specified institution
	 * @param instId
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EntityTermModel> getTermsByInstTeacher(Integer instId, Integer userId)
	{
		List<EntityTermModel> entities = new ArrayList<EntityTermModel>();
		try
		{
			Query query = entityManager.createQuery("SELECT etm from EntityTermModel etm, EntityTermTeachersModel ettm " + 
					"WHERE etm.instid = :instId AND ettm.termid = etm.id AND ettm.teacherid = :teacherId " +
					"ORDER BY etm.startdate DESC");
			query.setParameter("instId", instId);
			query.setParameter("teacherId", userId);
			entities = query.getResultList();
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
		return entities;
	}
	
	/**
	 * This method retrieves the list of current terms for an institution sorted in
	 * descending order of start date. If no exceptions are thrown the method is successful.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityTermModel> getListOfExistingTerms(int instId, Date now)
	{
		try
		{
			String qstring = "SELECT ex from EntityTermModel ex WHERE ex.instid = :instId " +
					"AND ex.startdate <= :edgeDay AND ex.enddate >= :edgeDay ORDER BY ex.startdate DESC";
			
			Query query = entityManager.createQuery(qstring);
			query.setParameter("instId", instId);
			query.setParameter("edgeDay", now, TemporalType.DATE);

			List<EntityTermModel> termlist = query.getResultList();
			return termlist;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Get the current terms for the institution
	 * @param instId
	 * @param now
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getCurrentTermIDs(Integer instId, Date now)
	{
		List<Integer> termids = new ArrayList<Integer>();
		String qstring = "SELECT etm.id from EntityTermModel etm WHERE etm.instid = :instId " +
				"AND etm.startdate <= :edgeDay AND :edgeDay <= etm.enddate ORDER BY etm.startdate DESC";
		
		Query query = entityManager.createQuery(qstring);
		query.setParameter("instId", instId);
		query.setParameter("edgeDay", now, TemporalType.DATE);

		termids = query.getResultList();
		return termids;
	}
	
	/**
	 * Get the expired terms for the institution
	 * @param instId
	 * @param now
	 * @param nPriorTerms (no consensus about 0 - usually ignores the query limit
	 * forcing an empty result JoeAB 2017-08-014)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getPriorTermIDs(Integer instId, Date now, Integer nPriorTerms)
	{
		List<Integer> termids = new ArrayList<Integer>();
		if (0 != nPriorTerms)
		{
			String qstring = "SELECT etm.id from EntityTermModel etm WHERE etm.instid = :instId " +
					"AND etm.enddate < :edgeDay ORDER BY etm.enddate DESC";
			
			Query query = entityManager.createQuery(qstring);
			query.setParameter("instId", instId);
			query.setParameter("edgeDay", now, TemporalType.DATE);
			if (nPriorTerms != BEGINNING_OF_TIME)
			{
				query.setMaxResults(nPriorTerms);
			}
	
			termids = query.getResultList();
		}
		return termids;
	}
	
	/**
	 * This method retrieves the list of new terms for an institution sorted in
	 * descending order of start date. If no exceptions are thrown the method is successful.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityTermModel> getListOfNewTerms(int instId, Date now)
	{
		try
		{
			String qstring = "SELECT ex from EntityTermModel ex WHERE ex.instid = :instId " +
					"AND ex.startdate > :edgeDay ORDER BY ex.startdate DESC";
			
			Query query = entityManager.createQuery(qstring);
			query.setParameter("instId", instId);
			query.setParameter("edgeDay", now, TemporalType.DATE);

			List<EntityTermModel> termlist = query.getResultList();
			return termlist;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method retrieves the list of new and current terms for an institution sorted in
	 * descending order of start date. If no exceptions are thrown the method is successful.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityTermModel> getListOfNewAndExistingTerms(int instId, Date now)
	{
		try
		{
			String qstring = "SELECT ex from EntityTermModel ex WHERE ex.instid = :instId " +
					"AND ex.enddate >= :edgeDay ORDER BY ex.startdate DESC";
			
			Query query = entityManager.createQuery(qstring);
			query.setParameter("instId", instId);
			query.setParameter("edgeDay", now, TemporalType.DATE);

			List<EntityTermModel> termlist = query.getResultList();
			return termlist;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method retrieves the list of nTerms most recent past terms for an institution,
	 * sorted in descending order of start date. If nTerms = BEGINNING_OF_TIME then all term IDs
	 * from the start are returned. If no exceptions are thrown the method is successful.
	 */
	@SuppressWarnings("unchecked")
	public List<EntityTermModel> getListOfPastTerms(int instId, Date now, int nTerms)
	{
		if (nTerms == 0)
			return new ArrayList<EntityTermModel>();
		
		try
		{
			String qstring = "SELECT ex from EntityTermModel ex where ex.instid = :instId " +
					"AND ex.enddate < :edgeDay ORDER BY ex.startdate DESC";
			
			Query query = entityManager.createQuery(qstring);
			query.setParameter("instId", instId);
			query.setParameter("edgeDay", now, TemporalType.DATE);
			if (nTerms != BEGINNING_OF_TIME)
				query.setMaxResults(nTerms);

			List<EntityTermModel> termlist = query.getResultList();
			return termlist;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Determine whether the term name is in use for the institution 
	 * @param instId
	 * @param termName
	 * @return
	 */
	public boolean isInstTermInUse(Integer instId, String termName)
	{
		Query query = entityManager.createQuery("Select COUNT(t) FROM EntityTermModel t " + 
				"WHERE t.instid = :InstId AND t.termname = :TermName");
		query.setParameter("InstId", instId);
		query.setParameter("TermName", termName);
		Long items = (Long) query.getSingleResult();
		boolean bResult = items > 0;
		return bResult;
	}
	
	/**
	 * This method retrieves a list of consecutive term IDs of an institution starting with 
	 * startTermID and ending with endTermID, sorted in descending order of start date.
	 * If no exceptions are thrown the method is successful.
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListOfConsecutiveTermIDs(int instId, int startTermID, int endTermID) {

		try {
			TermModel startTerm = findTermById(startTermID);
			TermModel endTerm   = findTermById(endTermID);
			
			String qstring = "SELECT ex.id from EntityTermModel ex where ex.instid = :instId " +
					"AND ex.startdate BETWEEN :start AND :end ORDER BY ex.startdate DESC";
			
			Query query = entityManager.createQuery(qstring);
			query.setParameter("instId", instId);
			query.setParameter("start", startTerm.getStartDate(), TemporalType.DATE);
			query.setParameter("end", endTerm.getStartDate(), TemporalType.DATE);

			List<Integer> termIdlist = query.getResultList();
			return termIdlist;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Get all matching terms prior to the specified date and overlapping the specified date
	 * @param instId
	 * @param today
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EntityTermModel> getPastAndCurrentTerms(Integer instId, java.sql.Date today)
	{
		System.out.format("TermRepository.getPastAndCurrentTerms(%d, %s)%n", instId, today.toString());
		try
		{
			Query query = entityManager.createQuery("SELECT etm from EntityTermModel etm " + 
					"WHERE etm.instid = :instId AND :edgeDay >= etm.startdate " +
					"ORDER BY etm.startdate");
			query.setParameter("instId", instId);
			query.setParameter("edgeDay", today, TemporalType.DATE);
			List<EntityTermModel> termslist = new ArrayList<EntityTermModel>();		
			termslist = query.getResultList();
			System.out.format("getPastAndCurrentTerms %d items%n", termslist.size());
			return termslist;
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}
	
	public void deleteTermById(int id) {
		System.out.println("TermTaRepository(line 223).deleteTermById(id = " + id + ")");
		
		try {
			Query query = entityManager.createQuery(
					"SELECT ts FROM EntityTermModel ts where ts.id = :id ");
			
			query.setParameter("id", id);
			
			EntityTermModel termTaModel = (EntityTermModel) query.getSingleResult();
			
			entityManager.remove(termTaModel);
		}
		catch (NoResultException e) {
			// This exception is thrown by getSingleResult if a match cannot be found.
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
