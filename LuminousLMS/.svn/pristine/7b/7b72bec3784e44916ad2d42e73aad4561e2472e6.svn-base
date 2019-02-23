package com.linguaclassica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.model.ConsultantClientsModel;
import com.linguaclassica.repository.ConsultantClientsRepository;

@Service
@Transactional	
public class ConsultantClientsService {
	
	@Autowired
	private ConsultantClientsRepository consultantClientsRepository;
	
	public void createOne(ConsultantClientsModel ccModel) {
		
		consultantClientsRepository.createOne(ccModel);
	}

	/**
	 * Delete one item that matches the passed parameters
	 * @param organizationId
	 * @param consId
	 * @param clientId
	 */
	public void deleteOne(Integer organizationId, Integer consId, Integer clientId)
	{
		consultantClientsRepository.deleteOne(organizationId,consId,clientId);
	}
	/**
	 * Close one item that matches the passed parameters by fillind the closedtime field
	 * @param organizationId
	 * @param consId
	 * @param clientId
	 */
	public void closeOne(Integer organizationId, Integer consId, Integer clientId){
		
		consultantClientsRepository.closeOne(organizationId,consId,clientId);
	}
	
	public List<Integer> getListOfClientIdsForConsultant(int orgid, int consid) {
		return consultantClientsRepository.getListOfClientIdsForConsultant(orgid, consid);
	}
	
	
	public int getConsultantIdForClient(int orgid, int clientid) {
		return consultantClientsRepository.getConsultantIdForClient(orgid, clientid);
	}

	
	
}
