package com.linguaclassica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.model.SessionModel;
import com.linguaclassica.repository.SessionRepository;

@Service
@Transactional
public class SessionService {
	
	@Autowired 
	private SessionRepository sessionRepository;
	
	/**
	 * This method creates or updates a session record. If no exceptions are thrown the method is successful.
	 * 
	 * @param sessionModel
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public void addSessiondata(SessionModel sessionModel) throws EntityAlreadyExistsException, ServiceException {

		sessionRepository.addSessiondata(sessionModel);	
	
	}
}