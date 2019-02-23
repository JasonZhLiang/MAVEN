package com.linguaclassica.service;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.linguaclassica.repository.RepositoryConfiguration;
import com.linguaclassica.entity.EntityAssignmentModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.service.AssignmentService;
import com.linguaclassica.service.exception.*;

/**
 * Tests the methods in the AssignmentService class.
 * 
 * @author Kristina Wirtanen
 * @version 2.0 
 * @since 2016-02-20
 * 
 * 
 * When Spring runs a test that is annotated with @Transactional, 
 * then by default it will rollback the transaction when the test completes.
 * Data will not remain in the alphplus tables.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RepositoryConfiguration.class})
@TransactionConfiguration
@Transactional
public class AssignmentServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	ModelFactory modelFactory;
	
	@Autowired
	AssignmentService assignmentService;
	
	@PersistenceContext
	EntityManager entityManager;
	
	// Tracks the ID of the last assignment persisted
	private int lastAssignId = 0;
	
	// Variables for creating a standard assignment
	private String assignName = "Sample assignment name";
	private int exerciseId = 1;
	private int teacherId = 1;
	private String status = "COMPLETED";
	private String createdDate = "2016-01-01 00:00 ET";
	private String startDate = "2016-01-10 00:00 ET";
	private String dueDate = "2016-04-30 00:00 ET";
	private String updateDate = "2016-02-01 00:00 ET";
	private String assignType = "NORMAL";
	private int weight = 0;

	private String updatedAssignmentName = "New name";
	
	private static int INVALID_ID = 2000;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * The following tests exercise the AssignmentService.createAssignment method.
	 */
	
	/**
	* This method ensures that the createAssignment method works properly for its
	* intended success path.
	* @param none
	* @return void
	*/
	@Test
	public void createAssignmentTest() {		
		EntityAssignmentModel builtAssignment = buildStandardAssignment();
		assignmentService.createAssignment(builtAssignment);
		lastAssignId = builtAssignment.getId();
		assertTrue("The last assignment Id is " + lastAssignId,lastAssignId>0);
	}
	
	/**
	* This method ensures that an assignment with a name that is longer than 24 characters
	* cannot be persisted.
	* @param none
	* @return void
	*/
	@Test(expected=ServiceException.class)
	public void assignmentNameLengthFailsTest() {
		EntityAssignmentModel assignmentEntity = buildStandardAssignment();
		assignmentEntity.setAssignmentName("This name is longer than the varchar(24)");
		assignmentService.createAssignment(assignmentEntity);
	}
	
	/**
	* This method ensures that an assignment with an exercise ID that does not exist 
	* in the database cannot be persisted.
	* @param none
	* @return void
	*/
	@Test(expected=ServiceException.class)
	public void exerciseIdConstraintFailsTest() {
		EntityAssignmentModel assignmentEntity = buildStandardAssignment();
		assignmentEntity.setExerciseId(INVALID_ID);
		assignmentService.createAssignment(assignmentEntity);
	}
	
	/**
	* This method ensures that an assignment with a teacher ID that does not exist 
	* in the database cannot be persisted.
	* @param none
	* @return void
	*/
	@Test(expected=ServiceException.class)
	public void teacherIdConstraintFailsTest() {
		EntityAssignmentModel assignmentEntity = buildStandardAssignment();
		assignmentEntity.setTeacherId(INVALID_ID);
		assignmentService.createAssignment(assignmentEntity);
	}
	
	/**
	 * The following tests exercise the AssignmentService.findAssignmentById method.
	 */
	
	/**
	* This method ensures that the findAssignmentById method works properly for its
	* intended success path.
	* @param none
	* @return void
	*/
	@Test
	public void findAssignmentByIdTest() {		
		EntityAssignmentModel builtAssignment = buildStandardAssignment();
		assignmentService.createAssignment(builtAssignment);
		lastAssignId = builtAssignment.getId();
		EntityAssignmentModel result = assignmentService.findAssignmentById(lastAssignId);
		assertEquals(builtAssignment,result);
	}
	
	
	/**
	* This method ensures that when an invalid assignment ID is passed to findAssignmentById
	* that an exception is thrown. 
	* .
	* @param none
	* @return void
	*/
	@SuppressWarnings("unused")
	@Test(expected=UnknownEntityException.class)
	public void findAssignmentByIdFailsTest() {
		EntityAssignmentModel result = assignmentService.findAssignmentById(INVALID_ID);
	}
	
	/**
	 * The following tests exercise the AssignmentService.getListOfAssignments method.
	 */
	
	/**
	* This method ensures that a list of assignments can be returned successfully.  
	* .
	* @param none
	* @return void
	*/
	@Test
	public void getListOfAssignmentsTest() {
		List<EntityAssignmentModel> results = assignmentService.getListOfAssignments();
		assertFalse(results.isEmpty());
	}
	
	/**
	 * The following tests exercise the AssignmentService.updateAssignment method.
	 */
	
	/**
	* This method ensures that an assignment can be updated successfully.  
	* .
	* @param none
	* @return void
	*/
	@Test
	public void updateAssignmentTest() {
		// Builds standard assignment and stores its ID
		EntityAssignmentModel builtAssignment = buildStandardAssignment();
		assignmentService.createAssignment(builtAssignment);
		lastAssignId = builtAssignment.getId();
		
		// Retrieves just persisted standard assignment entity from database
		EntityAssignmentModel retrievedAssignment = assignmentService.findAssignmentById(lastAssignId);
		String previousName = retrievedAssignment.getAssignmentName();
		
		// Changes the assignment's name and updates the record in the database table
		retrievedAssignment.setAssignmentName(updatedAssignmentName);
		assignmentService.updateAssignment(retrievedAssignment);
		assertFalse(updatedAssignmentName.equals(previousName));
	}

	/**
	 * The following tests exercise the AssignmentService.deleteAssignmentByIds method.
	 */
	
	/**
	* This method ensures that a list of assignments can be deleted successfully.  
	* .
	* @param none
	* @return void
	*/
	@Test
	public void deleteAssignmentByIdsTest() {
		EntityAssignmentModel builtAssignment = buildStandardAssignment();
		assignmentService.createAssignment(builtAssignment);
		lastAssignId = builtAssignment.getId();
		ArrayList<Integer> deleteAssignIds= new ArrayList<Integer>(
				Arrays.asList(8,9));
		assertTrue(assignmentService.deleteAssignmentByIdList(deleteAssignIds) == 2);
	}
	
	/**
	* This method ensures that an invalid assignment ID cannot be deleted.  
	* .
	* @param none
	* @return void
	*/
	@Test
	public void deleteAssignmentByIdsFailsTest() {
		ArrayList<Integer> deleteAssignIds= new ArrayList<Integer>(
				Arrays.asList(INVALID_ID));
		assertTrue(assignmentService.deleteAssignmentByIdList(deleteAssignIds) == 0);
	}
	
	
	/**
	 * Creates a standard assignment entity with fields set
	 * @param none
	 * @return EntityAssignmentModel
	 */
	private EntityAssignmentModel buildStandardAssignment()
	{
		EntityAssignmentModel assignmentEntity = (EntityAssignmentModel)modelFactory.getNewAssignmentModel();
		assignmentEntity.setAssignmentName(assignName);
		assignmentEntity.setExerciseId(exerciseId);
		assignmentEntity.setTeacherId(teacherId);
		assignmentEntity.setStatus(status);
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date parsedDate = dateFormat.parse(createdDate); 
			Timestamp timestamp = new Timestamp(parsedDate.getTime());
			assignmentEntity.setCreatedDate(timestamp);
		
			parsedDate = dateFormat.parse(startDate); 
			timestamp = new Timestamp(parsedDate.getTime()); 
			assignmentEntity.setStartDate(timestamp);
		
			parsedDate = dateFormat.parse(dueDate); 
			timestamp = new Timestamp(parsedDate.getTime()); 
			assignmentEntity.setDueDate(timestamp);
		
			parsedDate = dateFormat.parse(updateDate); 
			timestamp = new Timestamp(parsedDate.getTime()); 
			assignmentEntity.setUpdateDate(timestamp);
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String timezone = startDate.substring(17);
		assignmentEntity.setTimeZone(timezone);

		assignmentEntity.setType(assignType);
		assignmentEntity.setWeight(weight);
		return assignmentEntity;
	}

}
