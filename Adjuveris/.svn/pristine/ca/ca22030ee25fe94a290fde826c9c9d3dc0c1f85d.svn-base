package com.linguaclassica.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.linguaclassica.model.SessionModel;
import com.linguaclassica.service.exception.ServiceException;

@Repository
public class SessionRepository {
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	//private Logger logger = LoggerFactory.getLogger("SessionRepository");
	
	/**
	 * This method adds worddata in database. If no exceptions are thrown the method is successful.
	 * 
	 * @param wordataModel
	 * @handles NoResultException if the worddata already exists.
	 */
	public void addSessiondata(SessionModel sessionModel) throws NoResultException, ServiceException {
		
		String sessId = sessionModel.getSessionName();
		Query query = entityManager.createQuery(
				"SELECT session FROM EntitySessionModel session where session.sessionName = :sessionName");	
		query.setParameter("sessionName", sessId);
		@SuppressWarnings("unchecked")
		List<SessionModel> results = query.getResultList();
        if (results.isEmpty()) {
        	System.out.println("record empty");
			entityManager.persist(sessionModel);        	
        }
        else {
        	System.out.println("record not empty");
        	int listlen = results.size();
    		//following while loop need to eliminate multiple records with the same sessionName
    		int i = 1;
        	while (listlen > 1) {
        		entityManager.remove(results.get(i));
        		listlen = listlen - 1;
        		i++;
        	}
        	SessionModel existingSessionModel = results.get(0);//(SessionModel) query.getSingleResult();
        	existingSessionModel.setLasttime(sessionModel.getLasttime());
        	entityManager.persist(existingSessionModel);
        }
		}
	}

