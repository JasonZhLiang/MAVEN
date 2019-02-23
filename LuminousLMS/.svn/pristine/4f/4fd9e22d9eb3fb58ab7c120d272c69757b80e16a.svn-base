package com.linguaclassica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityClientSequenceUsageModel;
import com.linguaclassica.repository.ClientSequenceUsageRepository;

@Service
@Transactional
public class ClientSequenceUsageService {
	
	@Autowired
	ClientSequenceUsageRepository clientSequenceUsageRepository;
	
	public void addClientSequenceUsageForSequenceProgress(EntityClientSequenceUsageModel sequenceUsage) {
		clientSequenceUsageRepository.createOne(sequenceUsage);
	}
	
	public void updateClientSequenceUsage(EntityClientSequenceUsageModel sequenceUsage) {
		clientSequenceUsageRepository.updateClientSequenceUsage(sequenceUsage);
	}

}
