package com.linguaclassica.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Repository
public class Variable1Repository {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * This method retrieves the list of variables from variable1 for an exercise.
	 * If no exceptions are thrown the method is successful.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	@SuppressWarnings("unchecked")
	public List<String> getListOfVariable1sByExercise(Integer exerciseid) {
		System.out.println("exerciseid = " + exerciseid);
		try {
			Query query = entityManager.createQuery(
				"SELECT var1.variable FROM EntityVariable1Model var1 where var1.exerciseid = :exerid");
	
			query.setParameter("exerid", exerciseid);
			
			List<String> exercisesList = new ArrayList<String>();
	
			exercisesList = (List<String>) query.getResultList();
			
			return exercisesList;
			
		}catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
}	