package com.linguaclassica.repository;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import com.linguaclassica.entity.EntityQuestionQuestionGroupModel;
import com.linguaclassica.model.QuestionQuestionGroupModel;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class QuestionQuestionGroupRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 
	 * This method creates a record in question_groups table. If no exceptions are thrown the method is successful.
	 * 
	 * @param questionQuestionGroupModel
	 * @throws PersistenceException if an unspecified error occurs.
	 */
	public void addQuestionToQuestionGroup(QuestionQuestionGroupModel questionQuestionGroupModel) 
			throws EntityExistsException, PersistenceException {
		
		try {
				entityManager.persist(questionQuestionGroupModel);
			} 
		catch (PersistenceException e) {
				
				if (e.getCause() instanceof ConstraintViolationException) {
					throw new EntityExistsException(e);
				}
				
				// Didn't match, throw the original exception
				throw e;
			}
		return;	
	}
	
	/**
	 * Using a single question id get a list of questions with the same groupguestionID
	 * @param pasageid
	 * @return EntityQuestionModel
	 */
	public List<EntityQuestionQuestionGroupModel> findQuestionGroupByQuestionId(int questionid) {
		try {
		
				return findQuestionByQuestionGroupId(findQuestionGroupId(questionid));
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public List<EntityQuestionQuestionGroupModel> findQuestionByQuestionQuesiontGroupId(int id) {
		try {
				Query query = entityManager.createQuery(
					"SELECT q FROM EntityQuestionQuestionGroupModel q where q.id = :id");
			
				query.setParameter("id", id);
			
				EntityQuestionQuestionGroupModel eqqgGM = (EntityQuestionQuestionGroupModel)query.getSingleResult();
				
				return findQuestionByQuestionGroupId(eqqgGM.getQuestionGroupId());
				
				
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@SuppressWarnings("unchecked")
	
	public List<EntityQuestionQuestionGroupModel> findQuestionByQuestionGroupId(int questiongroupid) {
		try {
		
				Query query = entityManager.createQuery("SELECT q FROM EntityQuestionQuestionGroupModel q where q.questiongroupId = :questiongroupid");  //questiongroupid, linenumber, questionid");
				query.setParameter("questiongroupid", questiongroupid);
				return (List<EntityQuestionQuestionGroupModel>) query.getResultList();
				
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public Integer findQuestionGroupId(int questionid) {
		try {
					
				Query query = entityManager.createQuery("SELECT (q.questiongroupId) from EntityQuestionQuestionGroupModel q where q.questionid = :questionid");
				query.setParameter("questionid", questionid);
				
				return (Integer) query.getSingleResult();
				
		} catch (NoResultException e) {
			//throw new UnknownEntityException(e);
			return 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public Integer getMaxQuestionByQuestionGroupId(int questiongroupId) {		
		try {
			
			Query query = entityManager.createQuery("SELECT max(q.questiongroupId) from EntityQuestionQuestionGroupModel q where q.questiongroupId = :questiongroupId");
			query.setParameter("questiongroupId", questiongroupId);
			
			return(Integer) query.getSingleResult();
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public Integer findLastQuestionQuestionId() {
		int questid = 0;
		try {
			// get the id for the last question created
			Query query = entityManager.createQuery(
				"SELECT q FROM EntityQuestionQuestionGroupModel q order by q.questiongroupId desc" );			
			@SuppressWarnings("unchecked")
			List<EntityQuestionQuestionGroupModel> questlist = query.setMaxResults(1).getResultList();
			questid = questlist.get(0).getId();
			return questid;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public void removeQuestionsByQuestionGroupId(int questiongroupId) 
			throws NoResultException, PersistenceException {	
		
		try {
			
			Query query = entityManager.createQuery("SELECT q from EntityQuestionQuestionGroupModel q where questiongroupId = :questiongroupId " );
			query.setParameter("questiongroupId", questiongroupId);
			
			@SuppressWarnings("unchecked")
			List<EntityQuestionQuestionGroupModel> listEntityQuestionQuestionGroupModel = (List<EntityQuestionQuestionGroupModel>) query.getResultList();
			for (EntityQuestionQuestionGroupModel qqgModel : listEntityQuestionQuestionGroupModel){
				entityManager.remove(qqgModel);
			}
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}	
	
	public void removeAQuestionGroup(int questionid) 
			throws NoResultException, PersistenceException {	
		
		try {
			
			Query query = entityManager.createQuery("SELECT q from EntityQuestionQuestionGroupModel q where q.questionid = :questionid");
			query.setParameter("questionid", questionid);
	
			EntityQuestionQuestionGroupModel qqgModel =  (EntityQuestionQuestionGroupModel)query.getSingleResult();
			entityManager.remove(qqgModel);
				
		}catch (NoResultException e) {
			//no problem if no entity found - just checking//throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public EntityQuestionQuestionGroupModel findQuestionById(int questionid) {		
		try {
			Query query = null;
			query = entityManager.createQuery("SELECT q from EntityQuestionQuestionGroupModel q where q.questionid = :questionid");
			query.setParameter("questionid", questionid);
			
			return (EntityQuestionQuestionGroupModel) query.getSingleResult();
		
	
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
}


