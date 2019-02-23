package com.linguaclassica.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;

import com.linguaclassica.entity.EntityPanelsModel;
import com.linguaclassica.model.PanelsModel;

@Repository
public class PanelsRepository {
	
	@PersistenceContext
    private EntityManager entityManager;


	
	/**
	 * This method is used to update the active panel. 
	 * 
	 * @return
	 * @throws NoResultException if the user cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */
	public void updatePanel(EntityPanelsModel panelsModel) {
		try {
			PanelsModel panlModel = entityManager.find(EntityPanelsModel.class,1);
			
			panlModel.setPanel(panelsModel.getPanel());
			entityManager.persist(panlModel);
		}
		catch (NoResultException nor) {
			throw new NoResultException();
		}
		
	}
	
	/**
	 * This method is used to retrieve the active panel. If successful, a valid PanelsModel is returned.
	 * 
	 * @return
	 * @throws NoResultException if the user cannot be found.
	 * @throws PersistenceException if an unspecified error occurred.
	 */	
	public EntityPanelsModel findPanel() throws NoResultException, PersistenceException {
				
		EntityPanelsModel panlModel = entityManager.find(EntityPanelsModel.class,1);
		
		// Check for panlModel not found
		if (panlModel == null) {
			throw new NoResultException("Panel does not exist");
		}
		System.out.println("PanelsRepository, line 41: panlModel.getPanel().toString() = " + 
				panlModel.getPanel().toString());
			
		return panlModel;
	}



}
