package com.linguaclassica.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityAssignmentClassesModel;
import com.linguaclassica.entity.EntityAssignmentFactModel;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.model.AssignmentClassesModel;
import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.repository.AssignmentClassesRepository;
import com.linguaclassica.repository.AssignmentFactRepository;
import com.linguaclassica.repository.AssignmentRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@Service
@Transactional
public class AssignmentService {
	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private AssignmentFactRepository assignmentFactRepository;
	
	@Autowired
	private AssignmentClassesRepository assignmentClassesRepository;
	
	@Autowired
	private TimeService timeService;
	
	
	/**
	 * This method creates an assignment. If no exceptions are thrown the method is successful.
	 * @param assignmentModel
	 * @throws EntityAlreadyExistsException if the user already exists.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public void createAssignment(AssignmentModel assignmentModel) throws EntityAlreadyExistsException, ServiceException {

		try {
			assignmentRepository.createAssignment(assignmentModel);	
			System.out.println("AssignmentService: createAssignment()");
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * This method is used to find an assignment by its id.
	 * If successful, the valid AssignmentModel is returned. 
	 * @param id
	 * @return EntityAssignmentModel
	 * @throws UnknownEntityException if the exercise cannot be found.
	 * @throws ServiceException if an unspecified error occurred.
	 */
	public EntityAssignmentModel findAssignmentById(int id) throws UnknownEntityException, ServiceException {

		System.out.println("AssignmentService: findAssignmentById()");
		try {
			EntityAssignmentModel assignmentModel = assignmentRepository.findAssignmentById(id);

			return assignmentModel;
			
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	

	/**
	 * This method retrieves the list of all assignments 
	 * @return List<EntityAssignmentModel>
	 */
	public List<EntityAssignmentModel> getListOfAssignments() {
		System.out.println("AssignmentService: getListOfAssignments()");
		return assignmentRepository.getListOfAssignments();
	}
	

	/**
	 * This method retrieves the current assignments that have been assigned to a student.
	 * @param assignmentIdList
	 * @return List<EntityAssignmentModel>
	 */
	public List<EntityAssignmentModel> getListOfAssignmentsByIdList(String status,String type, List<Integer> assignmentIdList) {
		if (!assignmentIdList.isEmpty()){
			System.out.println("AssignmentService: getListOfCurrentAssignmentsByIdList()");
			return assignmentRepository.getListOfAssignmentsByIdList(status,type, assignmentIdList);
		} else {
			return new ArrayList<EntityAssignmentModel>();
		}
	}

	

	/**
	 * This method will find the list of assignments for a certain student ID
	 * @param studentId
	 * @return List<EntityAssignmentModel>
	 */
	
	public List<EntityAssignmentModel> findAssignmentsByStudentId(Integer studentId) {
		
		List<EntityAssignmentFactModel> assignFactList = 
				assignmentFactRepository.getListOfAssignmentFactByStudentId("START","", studentId, null);
		
		System.out.println("assignFactList.size() = " + assignFactList.size());
		
		List<Integer> assignIdList = new ArrayList<Integer>();
	
		for (EntityAssignmentFactModel i : assignFactList )
			assignIdList.add(i.getAssignmentId());      
		
		System.out.println("AssignmentService, line 124: assignIdList.size = " + assignIdList.size());
		
		List<EntityAssignmentModel> assignmentList = assignmentRepository.getSimpleListOfAssignmentsByIdList(assignIdList);     
		if (assignIdList.size() > 0) {
			System.out.println("AssignmentService, line 128: assignmentList.size = " + assignmentList.size());
		}
		else {
			System.out.println("AssignmentService, line 131: assignmentIdList size - 0.");
		}
		
		return assignmentList;
	}
	
	public List<EntityAssignmentModel> getListOfAssignmentsByClassIdList(List<Integer> classIdList) {
		
		List<Integer> assignmentIdList = assignmentFactRepository.getListOfAssignIdByClassId(classIdList);
		
		return assignmentRepository.getSimpleListOfAssignmentsByIdList(assignmentIdList);
	}
	
	public List<AssignmentModel> getListOfAssignmentsByStatus(String status) {
		
		return assignmentRepository.getListOfAssignmentsByStatus(status);
	}
	
	public List<EntityAssignmentModel> getListOfPracticeAssignmentsByStudentId(Integer studentId) {
		
		List<EntityAssignmentModel> practiceAssignList = 
				assignmentRepository.getListOfPracticeAssignmentsByStudentId(studentId); 
		
		return practiceAssignList;
	}
	
	public List<EntityAssignmentModel> getListOfAssignmentsByClassId(Integer classid){
		
		//List<EntityAssignmentFactModel> assignFactList = assignmentFactRepository.getListOfAssignmentFactByClassId(classid);
		List<Integer> assignIdList = assignmentClassesRepository.getListOfAssignmentIdsByClassId(classid);
		/*
		List<Integer> assignidList = new ArrayList<Integer>();
		int listlen = assignFactList.size();
		for (int i = 0; i < listlen; i++) {
			assignidList.add(assignFactList.get(i).getAssignmentId());
		}
		*/
		List<EntityAssignmentModel> assignmentList = assignmentRepository.getSimpleListOfAssignmentsByIdList(assignIdList);
				
		return assignmentList;
	}
	
	public List<Integer> getListOfStandardAssignmentIdsByClassId(Integer classid){
		
		List<EntityAssignmentFactModel> assignFactList = assignmentFactRepository.getListOfAssignmentFactByClassId(classid);
		System.out.println("AssignmentService, line 174:  classid, assignFactList.size() = " + classid + ", " + 
				assignFactList.size());
		List<Integer> assignidList = new ArrayList<Integer>();
		int listlen = assignFactList.size();
		for (int i = 0; i < listlen; i++) {
			assignidList.add(assignFactList.get(i).getAssignmentId());
		}
		
		List<Integer> assignmentList = 
				assignmentRepository.getListOfStandardAssignmentIdsByIdList(assignidList);
		System.out.println("AssignmentService, lin 182:  assignidList, assignmentList.size() = " + assignidList + ", " + 
				assignmentList.size());		
		return assignmentList;
	}
	
	/**
	 * Retrieves assignments with specified status from one specified class
	 * @param Integer selectedClassId, String status
	 * @return List<EntityAssignmentModel>, blank list if no assignments
	 */
	public List<EntityAssignmentModel> getListOfAssignmentsByClassId(Integer selectedClassId,
			String status) {
		List<Integer> assignIds = getListOfAssignmentIdsByClassId(selectedClassId);
		if(assignIds.isEmpty()){
			return new ArrayList<EntityAssignmentModel>();
		}
		
		// checks each assignment's status
		// adds assignments with 'status' to 'assignments'
		List<EntityAssignmentModel> assignments = new ArrayList<EntityAssignmentModel>();
		for(Integer assignId: assignIds){
			try{
				EntityAssignmentModel assignModel = findAssignmentById(assignId); 
				if(assignModel.getStatus().equals(status)){
					assignments.add(assignModel);
				}
			} catch (Exception e){
				System.out.println("AssignmentService getListOfAssignmentsByClassId: "
						+ e.getCause());
			}
		}
		return assignments;
	}

	/**
	 * delete the row by the list of ids, and return the left rows
	 * @param assingmentIdList
	 * @return List<EntityAssignmentModel>
	 */

	public void deleteAssignmentById(Integer assignmentId){
		try {
			assignmentRepository.deleteAssignmentById(assignmentId);
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * delete the row by the list of ids, and return the left rows
	 * @param assingmentIdList
	 * @return List<EntityAssignmentModel>
	 */

	public int deleteAssignmentByIdList(List<Integer> assignmentIdList){
		try {
			return assignmentRepository.deleteAssignmentByIdList(assignmentIdList);
		} catch (NoResultException e) {
			throw new UnknownEntityException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * This method retrieves the class id given an assignment id.
	 * If no exceptions are thrown the method is successful.
	 * @param int assignId
	 * @return Integer if successful, null if otherwise
	 */
	public Integer getClassIdByAssignId(int assignId){
		try{
			return assignmentClassesRepository.getClassIdByAssignId(assignId);
		}catch(Exception e){
			System.out.println("AssignmentService getClassIdByAssignId() - "
					+ e.getCause());
			return null;
		}
	}
	/**
	 * Retrieves a list of classes ids from a given assignment id
	 * If no exceptions are thrown the method is successful.
	 * @param int assignId
	 * @return Integer if successful, null if otherwise
	 */
	public List<Integer> getClassesIdsByAssignId(int assignId){
		try{
			return assignmentClassesRepository.getClassesIdsByAssignId(assignId);
		}catch(Exception e){
			System.out.println("AssignmentService getClassIdByAssignId() - "
					+ e.getCause());
			return null;
		}
	}
	
	/**
	 * This method retrieves the list of all assignments from one specific class.
	 * @return List<Integer> if successful, blank list if otherwise
	 */
	public List<Integer> getListOfAssignmentIdsByClassId(int classId){
		try{
			return assignmentClassesRepository.getListOfAssignmentIdsByClassId(classId);
		}catch(Exception e){
			System.out.println("AssignmentClassesService getListOfAssignmentIdsByClassId() - "
					+ e.getCause());
			return new ArrayList<Integer>();
		}
	}
	
	/**
	 * This method retrieves the list of current assignments from one specific class.
	 * @return List<Integer> if successful, blank list if otherwise
	 */
	public List<AssignmentModel> getListOfCurrentAssignmentsByClassId(int classId){
		System.out.println("AssignmentService, line 272:  getListOfCurrentAssignmentsByClassId");		
		List<AssignmentModel> assignmentModelList = new ArrayList<AssignmentModel>();
		try{
			List<Integer> assignmentsIdList = assignmentClassesRepository.getListOfAssignmentIdsByClassId(classId);
			int listlen = assignmentsIdList.size();
			System.out.println("AssignmentService, line 278:  listlen = " + listlen);
			for(int i = 0; i < listlen; i++){
				AssignmentModel assignModel = assignmentRepository.findAssignmentById(assignmentsIdList.get(i));
				if(assignModel != null){
					
					ZonedDateTime nowtz = timeService.getOtherTimeZoneNow(assignModel.getTimeZone());
					System.out.println("AssignmentService, line 299:  nowtz = " + nowtz);
					
					ZonedDateTime startzdt = timeService.getTimeByTimeZone(assignModel.getStartDate(), 
							assignModel.getTimeZone());
					ZonedDateTime endzdt = timeService.getTimeByTimeZone(assignModel.getDueDate(), 
							assignModel.getTimeZone());
					
					if(nowtz.isAfter(startzdt) && nowtz.isBefore(endzdt)){
						if(assignModel.getStatus() == null){
							assignModel.setStatus("START");
							assignmentRepository.updateAssignment((EntityAssignmentModel) assignModel);
						}
						assignmentModelList.add(assignModel);
					}
					
					if(endzdt.isBefore(nowtz) 
							&& assignModel.getStatus() != null && !assignModel.getStatus().equals("COMPLETED")){
						assignModel.setStatus("COMPLETED");
						assignmentRepository.updateAssignment((EntityAssignmentModel) assignModel);
						System.out.println("AssignmentService, line 287:  assignment updated to COMPLETE");
					}
				
				}
			}
		}catch(Exception e){
			System.out.println("AssignmentClassesService getListOfCurrentAssignmentsByClassId() - "
					+ e.getCause());
		}
		return assignmentModelList;
	}
	
	/**
	 * Get the assignments for the classes attended by the (student) user
	 * that are active for the current day.
	 * @param userId
	 * @param thisDay
	 * @return
	 */
	public List<AssignmentModel> findCurrentUserAssignments(Integer userId, Timestamp thisTime)
	{
		return assignmentRepository.findCurrentUserAssignments(userId, thisTime);
	}
	
	/**
	 * This method creates an assignmentClass. If no exceptions are thrown the method is successful.
	 * @param AssignmentClassesModel
	 * @throws EntityAlreadyExistsException if the user already exists.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public void createAssignmentClasses(AssignmentClassesModel assignmentClassesModel) throws EntityAlreadyExistsException, ServiceException {

		try {
			assignmentClassesRepository.createAssignmentClasses((EntityAssignmentClassesModel)assignmentClassesModel);	
			System.out.println("AssignmentService: createAssignment()");
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
 * This method will find a list of classIds for an assignmentClasses id
 * @param assignmentId 
 * @return List<Integer>
 */
public ArrayList<EntityAssignmentClassesModel> getListOfAssignmentClassesByAssignmentId(Integer assignmentId) {
	
	ArrayList<EntityAssignmentClassesModel> assignmentClassesList = 
			(ArrayList<EntityAssignmentClassesModel>) assignmentClassesRepository.getListOfAssignmentClassesByAssignmentId(assignmentId);
    return assignmentClassesList; 		
}

/**
 * This method updates an assignment. If no exceptions are thrown the method is successful.
 * @param assignmentModel
 * @throws EntityAlreadyExistsException if the user already exists.
 * @throws ServiceException if an unspecified error occurs.
 */
public void updateAssignment(AssignmentModel assignmentModel) throws EntityAlreadyExistsException, ServiceException {

	try {
		assignmentRepository.updateAssignment((EntityAssignmentModel)assignmentModel);	
		System.out.println("AssignmentService: updateAssignment()");
	} catch (EntityExistsException e) {
		throw new EntityAlreadyExistsException(e);
	} catch (Exception e) {
		throw new ServiceException(e);
	}
}

/**
 * This method will remove an assignmentId from AssignmentClasses 
 * @param assignmentId
 * @throws EntityAlreadyExistsException
 * @throws ServiceException
 */
public void removeAssignmentClasses(EntityAssignmentClassesModel assignmentClassesModel) 
	throws EntityAlreadyExistsException, ServiceException {			
	try {
		System.out.println("AssignmentService: RemoveAssignment");
		assignmentClassesRepository.RemoveAssigmentClasses(assignmentClassesModel);
	} catch (EntityExistsException e) {
		throw new EntityAlreadyExistsException(e);
	} catch (Exception e) {
		throw new ServiceException(e);
	}
}

	
}

