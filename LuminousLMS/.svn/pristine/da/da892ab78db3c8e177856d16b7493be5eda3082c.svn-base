package com.linguaclassica.service;

import java.sql.Blob;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityMessageModel;
import com.linguaclassica.entity.EntityOtherResourceModel;
import com.linguaclassica.repository.MessageRepository;
import com.linguaclassica.repository.OtherResourceRepository;

@Service
@Transactional
public class OtherResourceService {
	
	@Autowired
	private OtherResourceRepository otherResourceRepository;
	
	
	/**
	 * This method will find a list of other_resources found by a specific orgid
	 * @param orgId
	 * @return List<EntityOtherResourceModel>
	 */
	public List<EntityOtherResourceModel> findOtherResourceByOrgId(Integer orgId) {
		return otherResourceRepository.findOtherResourceByOrgId(orgId); 
	}
	
	
	public EntityOtherResourceModel findOtherResourceById(Integer id) {
		return otherResourceRepository.findOtherResourceById(id);
	}
	
	public Integer createOtherResource(EntityOtherResourceModel entityOtherResourceModel)
	{
		return otherResourceRepository.createOtherResource(entityOtherResourceModel);
	}

}
