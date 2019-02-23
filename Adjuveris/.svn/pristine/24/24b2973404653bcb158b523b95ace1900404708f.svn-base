package com.linguaclassica.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityAssignmentFactModel;
import com.linguaclassica.model.AssignmentFactModel;
import com.linguaclassica.repository.AssignmentFactRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Service
@Transactional
public class AssignmentFactService {
	
	@Autowired
	private AssignmentFactRepository assignmentFactRepository;
	
/*	@Autowired
	private Variable1Repository variable1Repository;*/
	
	/**
	 * This method creates an assignment. If no exceptions are thrown the method is successful.
	 * @param assignmentfactModel
	 * @throws EntityAlreadyExistsException if the user already exists.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public void createAssignmentFact(AssignmentFactModel assignmentfactModel) throws EntityAlreadyExistsException, ServiceException {

		try {
			assignmentFactRepository.createAssignmentFact((EntityAssignmentFactModel)assignmentfactModel);	
			System.out.println("AssignmentFactService: createAssignmentFact()");
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * This method is used to find an assignment fact by its assignment id.
	 * If successful, the valid AssignmentFactModel is returned. 
	 * @param identifier
	 * @return EntityAssignmentFactModel
	 * @throws UnknownEntityException if the exercise cannot be found.
	 * @throws ServiceException if an unspecified error occurred.
	 */
	public EntityAssignmentFactModel findAssignmentFactByAssignID(Integer userId, Integer assignid) 
			throws UnknownEntityException, ServiceException {

		EntityAssignmentFactModel assignmentfactModel = null;
		try {
			assignmentfactModel = 
					assignmentFactRepository.findAssignmentFactByAssignID(userId, assignid);
			System.out.println("AssignmentFactService: findAssignmentFactByAssignID()");
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return assignmentfactModel;
	}

	/**
	 * This method is used to find an assignment fact by its id.
	 * If successful, the valid AssignmentFactModel is returned. 
	 * @param id
	 * @return EntityAssignmentFactModel
	 * @throws UnknownEntityException if the exercise cannot be found.
	 * @throws ServiceException if an unspecified error occurred.
	 */
	public EntityAssignmentFactModel findAssignmentFactById(int id) throws UnknownEntityException, ServiceException {

		try {
			EntityAssignmentFactModel assignmentfactModel = assignmentFactRepository.findAssignmentFactById(id);
			System.out.println("AssignmentFactService: findAssignmentFactById()");

			return assignmentfactModel;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	/**
	 * This method retrieves the list of all assignment facts
	 * @return List<EntityAssignmentModel>
	 */
	public List<EntityAssignmentFactModel> getListOfAssignmentFact() {
		System.out.println("AssignmentService: getListOfAssignments()");
		return assignmentFactRepository.getListOfAssignmentFact();
	}
	/**
	 * This method retrieves assignment facts with a certain status that have been assigned to a student.
	 * @param assignmentIdList
	 * @return List<EntityAssignmentModel>
	 */
	public List<EntityAssignmentFactModel> getListOfAssignmentFactByStudentId(String status,String type,Integer studentId, List<Integer> termIdList) {
		System.out.println("AssignmentFactService: getListOfAssignmentFactByStudentId(), status = " + status);
		List<EntityAssignmentFactModel> assignFactModelList = 
				assignmentFactRepository.getListOfAssignmentFactByStudentId(status, type, studentId, termIdList);
		return assignFactModelList;
	}
	
	/**
	 * This method retrieves all of the assignment facts linked to a specified assignment
	 * @param assignmentId
	 * @return List<EntityAssignmentFactModel> or null
	 */
	public List<EntityAssignmentFactModel> getListOfAssignmentFactByAssignmentId(Integer assignmentId) {
		System.out.println("AssignmentFactService: getListOfAssignmentFactByAssignmentId()");
		try{
			return assignmentFactRepository.getListOfAssignmentFactByAssignmentId(assignmentId);
		}
		catch(Exception e){
			System.out.println(e.getCause());
			return null;
		}

	}
	
	/**
	 * This method retrieves the assignment facts for a class, any status
	 * @param classId
	 * @return List<EntityAssignmentModel>
	 */
	public List<EntityAssignmentFactModel> getListOfAssignmentFactByClassId(Integer classId) {
		System.out.println("AssignmentFactService: getListOfAssignmentFactByClassId()");
		return assignmentFactRepository.getListOfAssignmentFactByClassId(classId);

	}
	/**
	 * This method retrieves the assignment facts of one status i.e. COMPLETED for one class.
	 * @param assignmentIdList
	 * @return List<EntityAssignmentModel>
	 */
	public List<EntityAssignmentFactModel> getListOfAssignmentFactByClassId(Integer classId, String status) {
		System.out.println("AssignmentFactService: getListOfAssignmentFactByClassId() - with status indicated");
		return assignmentFactRepository.getListOfAssignmentFactByClassId(classId,status);
	}
	
	/**
	 * This method retrieves the count of assignment facts by user and assignment ids.
	 * @param userid, assignmentid
	 * @return Integer
	 */
	public Integer getCountByUserAndAssignmentId(Integer userId, Integer assignId) {
		System.out.println("AssignmentFactService: getCountOfAssignmentFactsByUserAndAssignmentId");
		return assignmentFactRepository.getCountByUserAndAssignmentId(userId,assignId);
	}
}

