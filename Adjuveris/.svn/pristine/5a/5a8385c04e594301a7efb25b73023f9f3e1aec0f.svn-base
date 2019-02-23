package com.linguaclassica.service;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityPanelsModel;
import com.linguaclassica.model.PanelsModel;
import com.linguaclassica.repository.PanelsRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Service
@Transactional
public class PanelsService {
	
	@Autowired
	private PanelsRepository panelsRepository;
	
/*	@Autowired
	private Variable1Repository variable1Repository;*/
	
	/**
	 * This method creates an assignment. If no exceptions are thrown the method is successful.
	 * @param assignmentModel
	 * @throws EntityAlreadyExistsException if the user already exists.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public void updatePanel(PanelsModel panelsModel) throws EntityAlreadyExistsException, ServiceException {

		try {
			panelsRepository.updatePanel((EntityPanelsModel)panelsModel);	
			System.out.println("PanelsService: updatePanel()");
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	/**
	 * This method is used to find the active panel.
	 * If successful, the valid PanelsModel is returned. 
	 * @return EntityPanelsModel
	 * @throws UnknownEntityException if the panel cannot be found.
	 * @throws ServiceException if an unspecified error occurred.
	 */
	public EntityPanelsModel findPanel() throws UnknownEntityException, ServiceException {

		try {
			EntityPanelsModel panlModel = panelsRepository.findPanel();
			System.out.println("PanelsService: findPanel()");

			return panlModel;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}

