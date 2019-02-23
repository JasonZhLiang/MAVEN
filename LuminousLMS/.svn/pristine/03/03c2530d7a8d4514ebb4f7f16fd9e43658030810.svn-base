package com.linguaclassica.service;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.model.ClientsAssignmentsModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.repository.AssignmentSequencesRepository;
import com.linguaclassica.repository.ClientAssignmentsRepository;
import com.linguaclassica.repository.ClientSequenceProgressRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;

@Service
@Transactional	
public class ClientAssignmentsService {
	
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private ClientAssignmentsRepository clientAssignmentsRepository;
	
	@Autowired
	private AssignmentSequencesRepository assignmentSequencesRepository;

	@Autowired
	private ClientSequenceProgressRepository clientSequenceProgressRepository;
	
	private Logger logger = LogManager.getLogger(ClientAssignmentsService.class);

	public void addAssignment(Integer organizationId, Integer clientId, Integer assignmentId) 
			throws EntityAlreadyExistsException, ServiceException {			
			try {
				logger.debug("addAssignment() : org=" + organizationId + " , client=" + clientId  + " , assign=" + assignmentId);
				
				//TODO:  temporarily setting assgnment due date 3 weeks from now, just to populate something.
				//       Long term plan should be to have the consultant enter a due date when selecting the assignment
				//       and create a new addAssignment service method like above that accepts duedate as well.
				int millisecsInDay = 1000 * 24 * 60 * 60;
				int millisecsInWeek = millisecsInDay * 7;
				
				ClientsAssignmentsModel caModel = modelFactory.getNewClientsAssignmentsModel();
				caModel.setClientId(clientId);
				caModel.setAssignmentId(assignmentId);
				caModel.setDuedate(new Date(System.currentTimeMillis() + (3*millisecsInWeek)));  //TODO: should eventually be a passed in parameter
				clientAssignmentsRepository.createOne((ClientsAssignmentsModel) caModel);
			} catch (EntityExistsException e) {
				throw new EntityAlreadyExistsException(e);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		}
	
	public void removeAssignment(Integer organizationId, Integer clientId, Integer assignmentId) 
			throws EntityAlreadyExistsException, ServiceException {			
			try {
				logger.debug("removeAssignment() : org=" + organizationId + " , client=" + clientId  + " , assign=" + assignmentId);
				clientAssignmentsRepository.deleteOne(organizationId, clientId, assignmentId);
			} catch (EntityExistsException e) {
				throw new EntityAlreadyExistsException(e);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
		}

	public List<Integer> getListOfClientAssignmentIdsForClient(int clientid) {
		return clientAssignmentsRepository.getListOfClientAssignmentIdsForClient(clientid);
	}

	public List<Integer> getListOfAssignmentIdsForClientOrg(Integer clientid, Integer org) {
		return clientAssignmentsRepository.getListOfAssignmentIdsForClientOrg(clientid, org);
	}

	public Integer getClientAssignmentId(Integer clientId, Integer assignmentId) {
		return clientAssignmentsRepository.getClientAssignmentId(clientId, assignmentId);
	}
	
	public String getClientAssignmentStatus(Integer clientId, Integer assignmentId)
	{
		Integer clientAssignmentId = clientAssignmentsRepository.getClientAssignmentId(clientId, assignmentId);
		Integer clientAssignmentSegmentTotal = assignmentSequencesRepository.getAssignmentSequences(assignmentId).size();
		Integer clientAssignmentSegmentsCompleted = clientSequenceProgressRepository.getAssignmentSegmentsCompletedCount(clientAssignmentId);
				
    	String status = null;
    	
    	if (clientAssignmentSegmentTotal > 0)
    	{
    		status = String.format("%3d%% completed", 100 * clientAssignmentSegmentsCompleted / clientAssignmentSegmentTotal);
    	}
    	else
    	{
    		status = "not completed";
    	}

		return status;
	}

	public boolean isClientAssignmentComplete(Integer clientId, Integer assignmentId)
	{
		Integer clientAssignmentId = clientAssignmentsRepository.getClientAssignmentId(clientId, assignmentId);
		Integer clientAssignmentSegmentTotal = assignmentSequencesRepository.getAssignmentSequences(assignmentId).size();
		Integer clientAssignmentSegmentsCompleted = clientSequenceProgressRepository.getAssignmentSegmentsCompletedCount(clientAssignmentId);
		return (clientAssignmentSegmentTotal == clientAssignmentSegmentsCompleted);
	}

	public Date getClientAssignmentDuedate(Integer clientId, Integer assignmentId)
	{
		return clientAssignmentsRepository.getClientAssignmentDuedate(clientId, assignmentId);
	}
}
