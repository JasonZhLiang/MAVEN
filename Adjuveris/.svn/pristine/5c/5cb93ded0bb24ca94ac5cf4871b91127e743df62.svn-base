package com.linguaclassica.repository;

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

import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityDateRangeModel;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.model.ClassModel;
import com.linguaclassica.model.DateRangeModel;
import com.linguaclassica.model.TermModel;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;


@Repository
public class DateRangeRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public int createDateRange(EntityDateRangeModel dateRangeModel) 
			throws EntityExistsException, PersistenceException {
		int id;
		try {
			entityManager.persist(dateRangeModel);
			entityManager.refresh(dateRangeModel);
			id = dateRangeModel.getId();
		} catch (PersistenceException e) {
			
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new EntityExistsException(e);
			}
			// Didn't match, throw the original exception
			throw e;
		}
		return id;
	}
	
	/**
	 * Update an existing DateRange entity
	 * @param classEntity
	 */
	public void updateDateRange(EntityDateRangeModel dateRangeEntity) {
		System.out.println("EntityDateRangeModel dateRangeModelEntity(" + dateRangeEntity.getId() + ")");
		
		try
		{
			DateRangeModel replacement = entityManager.find(EntityDateRangeModel.class,dateRangeEntity.getId());
			replacement.setInstId(dateRangeEntity.getInstId());
			replacement.setStudentsTerms(dateRangeEntity.getStudentsTerms());
			replacement.setInstAdminTerms(dateRangeEntity.getInstAdminTerms());
						
			entityManager.merge(replacement);
			System.out.println("ClassRepository.updateClass OK");
		}
		catch (NoResultException nor)
		{
			throw new NoResultException();
		}
		catch (EntityAlreadyExistsException eae)
		{
			throw new EntityAlreadyExistsException();
		}
	}
	
	public Integer getStudentTerms(Integer instid) 
			throws EntityExistsException, PersistenceException {
		Query query = entityManager.createQuery(
				"SELECT edr.studentsTerms FROM EntityDateRangeModel edr where edr.instid = :instid");
		query.setParameter("instid", instid);
		
		return (Integer)query.getSingleResult();
	}
	
	public Integer getInstTerms(Integer instid) 
			throws EntityExistsException, PersistenceException {
		Query query = entityManager.createQuery(
				"SELECT edr.instadminTerms FROM EntityDateRangeModel edr where edr.instid = :instid");
		query.setParameter("instid", instid);
		
		return (Integer)query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public EntityDateRangeModel getDateRangeByInstId(int instid){
		try {
			Query query = entityManager.createQuery(
				"SELECT edr FROM EntityDateRangeModel edr where edr.instid = :instid");
			query.setParameter("instid", (Integer) instid);
			
			List<EntityDateRangeModel> results = (List<EntityDateRangeModel>) query.getResultList();

			if (results.size() == 0)
				return null;

			return results.get(0);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
	