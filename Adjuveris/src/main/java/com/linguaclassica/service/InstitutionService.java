package com.linguaclassica.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityInstitutionModel;
import com.linguaclassica.entity.EntityPermissionModel;
import com.linguaclassica.model.InstitutionModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.repository.InstUserPermissionRepository;
import com.linguaclassica.repository.InstitutionRepository;
import com.linguaclassica.repository.UserRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;


@Service
@Transactional
public class InstitutionService {
	
	@Autowired
	private InstitutionRepository institutionRepository;
	
	@Autowired
	private InstUserPermissionRepository iupRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public int createInstitution(InstitutionModel instModel) throws EntityAlreadyExistsException, ServiceException {
		int instId;
		try {
			System.out.println("InstitutionService.createInstitution");
			instId = institutionRepository.createInstitution((EntityInstitutionModel)instModel);
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		return instId;
	}

	public EntityInstitutionModel findInstitutionById(int instId){
		return institutionRepository.findInstitutionById(instId);
	}
	
	public EntityInstitutionModel findInstitutionByName(String instName){
		return institutionRepository.findInstitutionByName(instName);
	}
	
	/**
	 * This method updates an institution record.
	 * An exception may be thrown that the form handler must trap.
	 * @param institutionModel
	 */
	public void updateInstitution(InstitutionModel institutionModel)
	{
		institutionRepository.updateInstitution(institutionModel);
	}
	
	public List<EntityInstitutionModel> getListOfInstitutions() {
		return institutionRepository.getListOfInstitutions();
	}

	/**
	 * Delete the institution link and the permission from the user
	 * @param userId
	 * @param instId
	 */
	public void deleteInstUser(Integer userId, Integer instId) throws Exception
	{
		EntityPermissionModel permEntity = userRepository.getPermissionModel(Permission.INSTITUTION_ADMIN);
		iupRepository.deleteOne(instId, userId, permEntity.getId());
	}
}

