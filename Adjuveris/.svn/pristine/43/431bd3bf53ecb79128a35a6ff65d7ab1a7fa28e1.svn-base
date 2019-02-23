package com.linguaclassica.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityExerCategoryModel;
import com.linguaclassica.entity.EntityExerciseModel;
import com.linguaclassica.model.ExerCategoryModel;
import com.linguaclassica.model.ExerciseModel;
import com.linguaclassica.model.TextPassageModel;
import com.linguaclassica.repository.ExerCategoryRepository;
import com.linguaclassica.repository.ExerciseRepository;
import com.linguaclassica.repository.InstUserPermissionRepository;
import com.linguaclassica.repository.TextPassageRepository;
import com.linguaclassica.repository.InstUsersRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;

@Service
@Transactional
public class ExerciseService {
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@Autowired
	private ExerCategoryRepository exerCategoryRepository;
	
	@Autowired
	private TextPassageRepository textPassageRepository;

	@Autowired
	private InstUserPermissionRepository iupRepository;
	
	/**
	 * This method creates an exercise. If no exceptions are thrown the method is successful.
	 * 
	 * @param exerciseModel
	 * @throws EntityAlreadyExistsException if the user already exists.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public int createExercise(ExerciseModel exerciseModel) 
			throws EntityAlreadyExistsException, ServiceException {
		
		int exerciseId;
		try {
			exerciseId = exerciseRepository.createExercise((EntityExerciseModel)exerciseModel);			
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return exerciseId;
	}
	
	public void deleteExerciseCategory(int exerCategoryId) {
		exerCategoryRepository.deleteExerciseCategory(exerCategoryId);
	}
	
	public void deleteExercise(int exerciseid) {
		exerciseRepository.deleteExercise(exerciseid);
	}
	/**
	 * This method creates an exercise category. If no exceptions are thrown the method is successful.
	 * 
	 * @param exerCategoryModel
	 * @throws EntityAlreadyExistsException if the user already exists.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public int createExerCategory(ExerCategoryModel exerCategoryModel) 
			throws EntityAlreadyExistsException, ServiceException {
		int exerCategoryId;
		try {
			exerCategoryId = exerCategoryRepository.createExerCategory((EntityExerCategoryModel)exerCategoryModel);	
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return exerCategoryId;
	}
	
	/**
	 * This method creates an text passage. If no exceptions are thrown the method is successful.
	 * 
	 * @param textPassageModel
	 * @throws EntityAlreadyExistsException if the user already exists.
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public int createTextPassage(TextPassageModel passageModel) 
			throws EntityAlreadyExistsException, ServiceException {
		int textPassageId;
		try {
			textPassageId = textPassageRepository.createTextPassage(passageModel);	
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return textPassageId;
	}
	
	/**
	 * This method updates an text passage. If no exceptions are thrown the method is successful.
	 * 
	 * @param textPassageModel
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public void updateTextPassage(TextPassageModel passageModel) 
			throws ServiceException {
		try {
			textPassageRepository.updateTextPassage(passageModel);	
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return;
	}
	
	/**
	 * This method updates an text passage. If no exceptions are thrown the method is successful.
	 * 
	 * @param textPassageModel
	 * @throws ServiceException if an unspecified error occurs.
	 */
	public void updateExerCategory(ExerCategoryModel exerCategoryModel) 
			throws ServiceException {
		try {
			exerCategoryRepository.updateExerCategory(exerCategoryModel);	
		} catch (EntityExistsException e) {
			throw new EntityAlreadyExistsException(e);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return;
	}
	
	/*
	 * This method retrieves the list of exercises created by id 
	 */
	public EntityExerciseModel findExerciseById(int id) {
		return exerciseRepository.findExerciseById(id);
	}
	
	/*
	 * This method retrieves the list of exercises created by id 
	 */
	public EntityExerciseModel findExerciseByVariableStrings(int catid, String var1, 
			String var2) {
		return exerciseRepository.findExerciseByVariableStrings(catid, var1, var2);
	}
	
	
	
	
	/**
	 * This method retrieves the list of exercises created by a user 
	 */
	public List<EntityExerciseModel> getListOfExercisesCreatedByUser(Integer userId) {
		return exerciseRepository.getListOfExercisesCreatedByUser(userId);
	}
	
	public List<String> getListofExerciseVariable1(int exerciseid) {
		return exerciseRepository.getListofExerciseVariable1(exerciseid);
	}
	
	public List<String> getListofExerciseVariable2(int exerciseid) {
		return exerciseRepository.getListofExerciseVariable2(exerciseid);
	}
	
	/**
	 * This method retrieves the list of exercises by id list 
	 * Tammy - added null check
	 */
	public List<EntityExerciseModel> findExercisesByIdList(List<Integer> exerciseIdList) {
		if (!exerciseIdList.isEmpty()){
			return exerciseRepository.findExercisesByIdList(exerciseIdList);
		} else {
			return new ArrayList<EntityExerciseModel>();
		}
	}
	
	/**
	 * This method retrieves the list of exercises 
	 */
	public List<EntityExerciseModel> getListOfExercises() {
		return exerciseRepository.getListOfExercises();
	}	
	

	public List<EntityExerciseModel> getPracticeExercises() {
		return exerciseRepository.getPracticeExercises();
	}
	
	public EntityExerCategoryModel findExerCategoryById(int id) {
		return exerCategoryRepository.findExerCategoryById(id);
	}
	
	public List<EntityExerCategoryModel> getListOfExerCategories() {
		return exerCategoryRepository.getListOfExerCategories();
	}
	
	public List<EntityExerCategoryModel> getListOfExerCategoriesByType(String type) {
		List<EntityExerCategoryModel> ExerCategoryListByType = 
				exerCategoryRepository.getListOfExerCategoriesByType(type);
		return ExerCategoryListByType;
	}
	
	public List<EntityExerCategoryModel> getListOfExerCategoriesByExerciseListAndType(List<EntityExerciseModel> exerList, String type) {
		if (!exerList.isEmpty()) {
			// Compile a list of category IDs from these exercises
			int i, n;
			List<Integer> exCatIdList = new ArrayList<Integer>();
			n = exerList.size();
			for (i=0; i<n; i++)
				exCatIdList.add(exerList.get(i).getExercisecategory());

			// Return the categories from this list of the given "type"
			return exerCategoryRepository.getListOfExerCategoriesByIdListAndType(exCatIdList, type);
		}
		else
			return new ArrayList<EntityExerCategoryModel>();
}
	
	public List<EntityExerCategoryModel> getListOfPrivateExerCategoriesCreatedByUser(Integer userId) {
		// Get the list of exercises created by this user
		List<EntityExerciseModel> exerList = exerciseRepository.getListOfExercisesCreatedByUser(userId);

		// Return the corresponding private exercise categories
		return getListOfExerCategoriesByExerciseListAndType(exerList, "PRIVATE");
	}

	/**
	 * 
	 * @param institutionId
	 * @param userId
	 * @param permissionId
	 * @return
	 */
	public List<EntityExerCategoryModel> getListOfSharedExerCategoriesForAUser(Integer institutionId, Integer userId, Integer permissionId)
	{
		// Find all users of this institution
		List<Integer> usersList = iupRepository.getUserIds(institutionId, permissionId);
		
		// Get the exercises created by these users
		List<EntityExerciseModel> exerList = exerciseRepository.findExercisesByUserIdList(usersList);

		// Return the corresponding shared exercise categories
		return getListOfExerCategoriesByExerciseListAndType(exerList, "SHARED");
	}
	
	public List<EntityExerciseModel> getListOfExercisesByCategory(Integer categoryId) {
		return exerciseRepository.getListOfExercisesByCategory(categoryId);
	}
	
	public List<EntityExerciseModel> getListOfExercisesByExerCatList(List<EntityExerCategoryModel> exerCatList) {
		List<EntityExerciseModel> practiceExerList = new ArrayList<EntityExerciseModel>();
		int listlen = exerCatList.size();
		for (int i = 0; i < listlen; i++) {
			EntityExerciseModel exerModel = 
					(EntityExerciseModel) exerciseRepository.getListOfExercisesByCategory(exerCatList.get(i).getId());
			practiceExerList.add(exerModel);
		}
		return practiceExerList;
	}	
}

