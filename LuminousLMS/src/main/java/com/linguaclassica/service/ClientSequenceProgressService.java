package com.linguaclassica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityClientSequenceProgressModel;
import com.linguaclassica.entity.EntityModelFactory;
import com.linguaclassica.model.ClientSequenceProgressModel;
import com.linguaclassica.repository.ClientSequenceProgressRepository;

@Service
@Transactional
public class ClientSequenceProgressService {

	@Autowired
	ClientSequenceProgressRepository clientSequenceProgressRepository;
	
	public EntityClientSequenceProgressModel getClientSequenceProgressForClientAssignmentSequence(Integer sequenceid, Integer clientAssignment) {
		return clientSequenceProgressRepository.getOneByClientAssignIdAndSeqId(clientAssignment, sequenceid);
	}
	
	public ClientSequenceProgressModel addClientSequenceProgressForClientAssignmentSequence(Integer sequenceid, Integer clientAssignment, boolean completed, Integer result) {
		EntityModelFactory modelFactory = new EntityModelFactory();
		EntityClientSequenceProgressModel cspModel = modelFactory.getNewClientSequenceProgressModel();
		cspModel.setClientAssignmentId(clientAssignment);
		cspModel.setSequenceId(sequenceid);
		cspModel.setResults(result);
		cspModel.setCompleted(completed);
		return clientSequenceProgressRepository.createOne(cspModel);
	}
	
	public void updateClientSequenceProgress(EntityClientSequenceProgressModel segmentProgress) {
		clientSequenceProgressRepository.updateClientSequenceProgress(segmentProgress);
	}
}
