package com.linguaclassica.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityExerCategoryModel;
import com.linguaclassica.entity.EntityExerciseModel;
import com.linguaclassica.entity.EntityTextPassageModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.repository.RepositoryConfiguration;
import com.linguaclassica.repository.TextPassageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RepositoryConfiguration.class})
@TransactionConfiguration
@Transactional


public class ExerciseServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	ExerciseService exerciseService;
	
	@Autowired
	TextPassageRepository textPassageRepository;
	
	@Autowired
	ModelFactory modelFactory;
	
	@PersistenceContext
	EntityManager entityManager;
	
	private int lastExerciseId = 0;
	private String createdDate = "2016-11-01 00:00 ET";
	private String identifier = "multi selection";
	
	private Integer exercisecategory = 1;
	private int variable1 = 1;
	private int variable2 = 1;
	private String var1string = "good";
	private String var2string = "bad";
	private Integer createdbyid = 1;
	private Timestamp createddate;
	
	private int lastExerCategoryId = 0;
	private String Urlbase = "none";
	private String comment = "none";
	private String exerciseName = "Uint Test";
	
	private String textcontent = "adding test contents";
	private String updatetextcontent = "The updated new test contents";
	private int exerciseId = 1;
	private int lastTextPassageId = 0;
		
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	* This method ensures that the createExercise method works properly for its
	* intended success path.
	* @param exerciseModel 
	* @param none
	* @return void
	*/	
	
	
    @Test()
    public void createExerciseTests() {
		List<EntityExerciseModel> exerciseList = exerciseService.getListOfExercises();
		List<Integer> exerciseIdList = new ArrayList<Integer>();
		int listlen = exerciseList.size();
		System.out.println(listlen);
		for (int i = 0; i < listlen; i++) {
			int idin = exerciseList.get(i).getId();
			exerciseIdList.add(idin);
		}
		Collections.sort(exerciseIdList);
		int previousListId = exerciseIdList.get(listlen - 1);
		System.out.println("Previous exercise Id is " + previousListId);
    	
    	EntityExerciseModel builtExercise = buildStandardExercise(); 	
	    exerciseService.createExercise(builtExercise);
	    lastExerciseId=builtExercise.getId();
	    assertTrue("The last Exercise Id is " + lastExerciseId,lastExerciseId>previousListId);
	    System.out.println("The last Exercise Id is " + lastExerciseId);
	    System.out.println(lastExerciseId);
	    System.out.println("The is ExerciseService unit test1");
    	 
    }

    @Test()
    public void createExerCategory() {
		List<EntityExerCategoryModel> exerCategoryList = exerciseService.getListOfExerCategories();
		List<Integer> exerCategoryIdList = new ArrayList<Integer>();
		int listlen = exerCategoryList.size();
		System.out.println(listlen);
		for (int i = 0; i < listlen; i++) {
			int idin = exerCategoryList.get(i).getId();
			exerCategoryIdList.add(idin);
		}
		Collections.sort(exerCategoryIdList);
		int previousListId = exerCategoryIdList.get(listlen - 1);
		System.out.println(previousListId);
    	
    	EntityExerCategoryModel builtExerCategory = buildStandardExerCateg(); 	
	    exerciseService.createExerCategory(builtExerCategory);
	    lastExerCategoryId=builtExerCategory.getId();
	    assertTrue("The last Exercise Id is " + lastExerCategoryId,lastExerCategoryId>0);
	    System.out.println("The last Exercise Category Id is " + lastExerCategoryId);
	    System.out.println(lastExerCategoryId);
	    System.out.println("The is ExerciseService unit test2");
    	
    }

    @Test()
   
    public void createTextPassage() {
	
    	EntityTextPassageModel builtTextPassage = buildStandardTextPassage(); 	
	    exerciseService.createTextPassage(builtTextPassage);
	    lastTextPassageId=builtTextPassage.getId();
	    assertTrue("The last TextPassag Id is " + lastTextPassageId,lastTextPassageId>0);
	    System.out.println("The last TextPassag Id is " + lastTextPassageId);
	    System.out.println(lastTextPassageId);
	    System.out.println("The is ExerciseService unit test3");
    	
    } 
    
    @Test()
    
    public void updateTextPassage() {
	
    	EntityTextPassageModel builtTextPassage = buildStandardTextPassage(); 	
	    exerciseService.createTextPassage(builtTextPassage);
	    lastTextPassageId=builtTextPassage.getId();
	    System.out.println("The last TextPassag Id is " + lastTextPassageId);
	    
	    int id = lastTextPassageId;
	    
		EntityTextPassageModel retrievedTextPassage = textPassageRepository.findPassageById(id);
		String previousTextContent = retrievedTextPassage.getTextcontent();
		System.out.println("This is previousTextContent -- " + previousTextContent);
		
		retrievedTextPassage.setTextcontent(updatetextcontent);
		exerciseService.updateTextPassage(retrievedTextPassage);
		
		EntityTextPassageModel retrievedupdateTextPassage = textPassageRepository.findPassageById(id);
		String afterUpdatedTextContent = retrievedupdateTextPassage.getTextcontent();
		System.out.println("This is afterUpdatedTextContent -- " + afterUpdatedTextContent);
		
		assertFalse(updatetextcontent.equals(previousTextContent));
	    
	    System.out.println("This is ExerciseService unit test4");
    	
    } 
    
    @Test()
    public void getListOfExercises() {
		List<EntityExerciseModel> exerciseListResult = exerciseService.getListOfExercises();
		assertFalse(exerciseListResult.isEmpty());
		int listlen = exerciseListResult.size();
		System.out.println(listlen);
		System.out.println("exercise list content is " + exerciseListResult);	
		System.out.println("This is ExerciseService unit test5");	 
		
    }
    
 /*   @Test()
    public void getListOfExercisesByCategory() {
		List<EntityExerCategoryModel> exerciseListResult1 = exerciseService.getListOfExerCategories();
		int listlen = exerciseListResult1.size();
		int CategoryId = exerciseListResult1.get(id);
		System.out.println(listlen);
		System.out.println("exercise list content is " + exerciseListResult1);	
		System.out.println("This is ExerciseService unit test6");	 
		
    }
    */
    
	private EntityExerciseModel buildStandardExercise()
	{
		EntityExerciseModel exerciseEntity = (EntityExerciseModel)modelFactory.getNewExerciseModel();
		exerciseEntity.setExercisecategory(exercisecategory);
		exerciseEntity.setIdentifier(identifier);
		exerciseEntity.setVariable1(variable1);
		exerciseEntity.setCreateddate(createddate);
		exerciseEntity.setCreatedById(createdbyid);
		exerciseEntity.setVariable2(variable2);
		exerciseEntity.setVar1string(var1string);
		exerciseEntity.setVar2string(var2string);
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date parsedDate = dateFormat.parse(createdDate); 
			Timestamp timestamp = new Timestamp(parsedDate.getTime());
			exerciseEntity.setCreateddate(timestamp);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return exerciseEntity;
		
	}
	
	private EntityExerCategoryModel buildStandardExerCateg()
	{
		EntityExerCategoryModel exerCategoryEntity = (EntityExerCategoryModel)modelFactory.getNewExerCategoryModel();
		exerCategoryEntity.setUrlbase(Urlbase);
		exerCategoryEntity.setComment(comment);
		exerCategoryEntity.setExerciseName(exerciseName);
		
		return exerCategoryEntity;
		
	}	
	
	
	private EntityTextPassageModel buildStandardTextPassage()
	{
		EntityTextPassageModel testPassageEntity = (EntityTextPassageModel)modelFactory.getNewTextPassageModel();
		testPassageEntity.setTextcontent(textcontent);
		testPassageEntity.setExerciseid(exerciseId);

		return testPassageEntity;
		
	}	
}
