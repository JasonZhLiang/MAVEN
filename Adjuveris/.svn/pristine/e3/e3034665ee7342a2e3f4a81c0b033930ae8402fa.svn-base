package com.linguaclassica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityDateRangeModel;
import com.linguaclassica.model.DateRangeModel;
import com.linguaclassica.repository.DateRangeRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;

@Service
@Transactional
public class DateRangeService {
	
	@Autowired
	private DateRangeRepository dateRangeRepository;
	
	public int createDateRange(EntityDateRangeModel dateRangeModel) 
	{
		return dateRangeRepository.createDateRange(dateRangeModel);
	}

	public void updateDateRange(EntityDateRangeModel dateRangeEntity)
	{
		dateRangeRepository.updateDateRange(dateRangeEntity);
	}
	
	/* Creates the Date Range record if it doesn't exist for the given institution ID,
	 * otherwise updates it                                                            */
	public int setDateRange(EntityDateRangeModel dateRangeEntity) 
	{
		EntityDateRangeModel oldDateRange = dateRangeRepository.getDateRangeByInstId(
				dateRangeEntity.getInstId());

		if (oldDateRange == null)
			return createDateRange(dateRangeEntity);

		int id = oldDateRange.getId();
		dateRangeEntity.setId(id);
		dateRangeRepository.updateDateRange(dateRangeEntity);
		return id;
	}
	
	public int getStudentTerms(Integer instid) throws EntityAlreadyExistsException, ServiceException
	{
		return dateRangeRepository.getStudentTerms(instid);
	}
	
	public int getInstTerms(Integer instid) throws EntityAlreadyExistsException, ServiceException
	{
		return dateRangeRepository.getInstTerms(instid);
	}

	public EntityDateRangeModel getDateRangeByInstId(int instid)
	{
		return dateRangeRepository.getDateRangeByInstId(instid);
	}
}
