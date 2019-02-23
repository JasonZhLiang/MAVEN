package com.linguaclassica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityResourceModel;
import com.linguaclassica.model.OrgsResourcesModel;
import com.linguaclassica.model.ResourceModel;
import com.linguaclassica.repository.OrgsResourcesRepository;
import com.linguaclassica.repository.ResourceRepository;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;


@Service
@Transactional
public class ResourceService {
	
	@Autowired
	private OrgsResourcesRepository orgsResourcesRepository;
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	public List<EntityResourceModel> getListOfResourceidsByOrgid(int orgid) {
		List<Integer> resourceidsList = orgsResourcesRepository.getListOfResourceidsByOrgid(orgid);
		List<EntityResourceModel> resourcesList = resourceRepository.getListOfResourcesByIdList(resourceidsList);
		return resourcesList;
	}
	
	public void createResource(ResourceModel resourceModel){
		try {
			resourceRepository.createResource(resourceModel);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * This method is used to find a resource by ID. If successful, a valid ResourceModel is returned.
	 * 
	 * @param Id
	 * @return
	 * @throws UnknownEntityException if the user cannot be found.
	 * @throws ServiceException if an unspecified error occurred.
	 */
	public EntityResourceModel findResourceById(Integer Id) throws UnknownEntityException, ServiceException {
		System.out.println("ResourceService.findResourceById:  Id = " + Id);
		EntityResourceModel resource = resourceRepository.findResourceById(Id);
		
		// Got here so there is a resource
		return resource;
	}
	
	public void createOrgsResourcesRecord(OrgsResourcesModel orgsResourcesModel){
		try {
			orgsResourcesRepository.createOrgsResourcesRecord(orgsResourcesModel);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
}