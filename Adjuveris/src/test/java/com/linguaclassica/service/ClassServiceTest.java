package com.linguaclassica.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityClassUsersModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.InstitutionModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.TermModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.repository.ClassRepository;
import com.linguaclassica.repository.RepositoryConfiguration;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.UnknownEntityException;


/**
 * Test the functions in the NotificationService layer.
 * 
 * @author Joe A. Brown
 * 
 * When Spring runs a test that is annotated with @Transactional, 
 * then by default it will roll back the transaction when the test completes.
 * Data will not remain in the alphplus tables.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RepositoryConfiguration.class})
@TransactionConfiguration
@Transactional
public class ClassServiceTest extends AbstractTransactionalJUnit4SpringContextTests
{
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private InstitutionService instService;
	
	@Autowired
	private TermService termService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private UserService userService;
	
	private String sFirstTermname = "Fall 2017";
	private String sFirstStartdate = "2017-09-01";
	private String sFirstEnddate = "2017-12-31";

	private String sExtraTermname = "Winter 2018";
	private String sExtraStartdate = "2018-01-01";
	private String sExtraEnddate = "2018-04-30";
	
	private String sFirstClassname = "Latin 101";
	private String sFirstClasscode = "2017LTN1";
	private String sSecondClassname = "Greek 202";
	private String sSecondClasscode = "2017GRK2";
	private String sAlterClassname = "Latin grade 9";
	private String sAlterClasscode = "2017LATIN1";
	
	private InstitutionModel imDefaultInstitution = null;
	//private InstitutionModel imSecondInstitution = null;
	private TermModel tmFirstTerm = null;
	private TermModel tmExtraTerm = null;

	private void createDefaultInstitution()
	{
		imDefaultInstitution = modelFactory.getNewInstitutionModel();
		imDefaultInstitution.setAdminemail("strumbella@uarcturus.edu");
		imDefaultInstitution.setAdminfirstname("Bella");
		imDefaultInstitution.setAdminlastname("Strum");
		imDefaultInstitution.setInstname("uArcturus test");
		imDefaultInstitution.setPostaddress("1000 Main Drag, Arcturus, IL");
		imDefaultInstitution.setPrimecontact("Charles Tanika");
		imDefaultInstitution.setPrimeemail("registrar@uarcturus.edu");
		imDefaultInstitution.setPrimephone("1-815-555-1000");
		instService.createInstitution(imDefaultInstitution);
	}
/*
	private void createSecondInstitution()
	{
		imSecondInstitution = modelFactory.getNewInstitutionModel();
		imSecondInstitution.setAdminemail("lukeman@ubarnyard.edu");
		imSecondInstitution.setAdminfirstname("Luke");
		imSecondInstitution.setAdminlastname("Haneman");
		imSecondInstitution.setInstname("uBarnyard test");
		imSecondInstitution.setPostaddress("20 Rural Route, Backwater, ID");
		imSecondInstitution.setPrimecontact("Buddy Farmer");
		imSecondInstitution.setPrimeemail("registrar@ubarnyard.edu");
		imSecondInstitution.setPrimephone("1-812-555-1000");
		instService.createInstitution(imSecondInstitution);
	}
*/
	private void createFirstTerm(int institutionId)
	{
		tmFirstTerm = modelFactory.getNewTermModel();
		tmFirstTerm.setTermname(sFirstTermname);
		tmFirstTerm.setInstid(institutionId);
		tmFirstTerm.setStartDate(Date.valueOf(sFirstStartdate));
		tmFirstTerm.setEndDate(Date.valueOf(sFirstEnddate));
		termService.createTerm(tmFirstTerm);
	}
	
	private void createExtraTerm(int institutionId)
	{
		tmExtraTerm = modelFactory.getNewTermModel();
		tmExtraTerm.setTermname(sExtraTermname);
		tmExtraTerm.setInstid(institutionId);
		tmExtraTerm.setStartDate(Date.valueOf(sExtraStartdate));
		tmExtraTerm.setEndDate(Date.valueOf(sExtraEnddate));
		termService.createTerm(tmExtraTerm);
	}

	private EntityClassModel createFirstClass()
	{
		EntityClassModel cm = (EntityClassModel) modelFactory.getNewClassModel();
		cm.setClassname(sFirstClassname);
		cm.setClasscode(sFirstClasscode);
		//cm.setStartDate(tmFirstTerm.getStartDate());
		//cm.setEndDate(tmFirstTerm.getEndDate());
		cm.setTermid(tmFirstTerm.getId());
		return cm;
	}

	private EntityClassModel createSecondClass()
	{
		EntityClassModel cm = (EntityClassModel) modelFactory.getNewClassModel();
		cm.setClassname(sSecondClassname);
		cm.setClasscode(sSecondClasscode);
		//cm.setStartDate(tmFirstTerm.getStartDate());
		//cm.setEndDate(tmFirstTerm.getEndDate());
		cm.setTermid(tmFirstTerm.getId());
		return cm;
	}
	
	private EntityUserModel createSingleUser()
	{
		EntityUserModel um = (EntityUserModel) modelFactory.getNewUserModel();
		um.setEmailAddress("bobfirst@uarcturus.edu");
		um.setFirstName("Robert");
		um.setLastName("Fuerst");
		um.setStudentnumber("201700001");
		um.setPassword("PlainToSee");
		return um;
	}

	/**
	 * Create a class using passed values
	 * @param cname
	 * @param ucode
	 * @param tm
	 * @return
	 */
	private EntityClassModel createAClass(String cname, String ucode, TermModel tm)
	{
		EntityClassModel cm = (EntityClassModel) modelFactory.getNewClassModel();
		cm.setClassname(cname);
		cm.setClasscode(ucode);
		//cm.setStartDate(tm.getStartDate());
		//cm.setEndDate(tm.getEndDate());
		cm.setTermid(tm.getId());
		return cm;
	}
	
	@Before
	public void setUp() throws Exception
	{
		// Create a default institution for many of the tests
		createDefaultInstitution();
		
		// Create a default term for many of the tests
		createFirstTerm(imDefaultInstitution.getId());
		
		// Create a default user for many of the tests
		//createDefaultUser();
	}
	
	@Test
	public void TestCreateClass_OK()
	{
		EntityClassModel cm = createFirstClass();
		Integer classid = classService.createClass(cm);
		
		assertTrue(classid > 0);
	}
	
	@Test(expected=EntityAlreadyExistsException.class)
	public void TestCreateClass_Duplicate()
	{
		EntityClassModel cm1 = createFirstClass();
		Integer class1id = classService.createClass(cm1);
		
		assertTrue(class1id > 0);

		EntityClassModel cm2 = createFirstClass();
		@SuppressWarnings("unused")
		Integer class2id = classService.createClass(cm2);
		
		// Should not reach here
		assertTrue(false);
	}
	
	@Test
	public void TestFindClassById_OK()
	{
		EntityClassModel cmc = createFirstClass();
		Integer classid = classService.createClass(cmc);
		
		EntityClassModel cm1 = classService.findClassById(classid);
		
		assertTrue(0 == cm1.getClassname().compareTo(cmc.getClassname()));
		//assertTrue(cm1.getTermid() == cmc.getTermid()); not sure why this fails
		assertTrue(0 == cm1.getTermid().compareTo(cmc.getTermid()));
	}
	
	@Test(expected=UnknownEntityException.class)
	public void TestFindClassById_Bad()
	{
		EntityClassModel cmc = createFirstClass();
		Integer classid = classService.createClass(cmc);
		
		@SuppressWarnings("unused")
		EntityClassModel cm1 = classService.findClassById(1000000 + classid);
		
		// should not get here
		assertTrue(false);
	}
	
	@Test
	public void TestGetListOfClassesByTermId_OK()
	{
		EntityClassModel cm1 = createFirstClass();
		@SuppressWarnings("unused")
		Integer class1id = classService.createClass(cm1);
		
		EntityClassModel cm2 = createSecondClass();
		@SuppressWarnings("unused")
		Integer class2id = classService.createClass(cm2);
		
		// Create another term
		createExtraTerm(imDefaultInstitution.getId());
		
		EntityClassModel cm3 = createAClass("Spanglish 303", "2018SP3", tmExtraTerm);
		Integer class3id = classService.createClass(cm3);
		
		EntityClassModel cm4 = createAClass("Math 404", "2017M4", tmFirstTerm);
		@SuppressWarnings("unused")
		Integer class4id = classService.createClass(cm4);
		
		List<EntityClassModel> list1 = classService.getListOfClassesByTermId(tmFirstTerm.getId());
		
		assertTrue(3 == list1.size());
		//assertTrue(list1.get(0).getId().equals(class1id));
		
		List<EntityClassModel> list2 = classService.getListOfClassesByTermId(tmExtraTerm.getId());

		assertTrue(1 == list2.size());
		assertTrue(class3id == list2.get(0).getId());
	}
	
	@Test
	public void TestGetListOfClassesByTermId_None()
	{
		List<EntityClassModel> list1 = classService.getListOfClassesByTermId(1000000 + tmFirstTerm.getId());
		
		assertTrue(0 == list1.size());
	}
	
	@Test
	public void TestGetClassesByClassIDs_Null()
	{
		List<EntityClassModel> list = classService.getClassesByClassIDs(null);
		
		assertNotNull(list);
		assertTrue(0 == list.size());
	}
	
	@Test
	public void TestGetClassesByClassIDs_None()
	{
		List<Integer> empty = new ArrayList<Integer>();
		List<EntityClassModel> list = classService.getClassesByClassIDs(empty);
			
		assertNotNull(list);
		assertTrue(0 == list.size());
	}
	
	@Test
	public void TestGetClassesByClassIDs_OK()
	{
		// Create some classes
		EntityClassModel cm1 = createFirstClass();
		Integer class1id = classService.createClass(cm1);
		
		EntityClassModel cm2 = createSecondClass();
		Integer class2id = classService.createClass(cm2);
		
		EntityClassModel cm3 = createAClass("Spanglish 303", "2018SP3", tmFirstTerm);
		Integer class3id = classService.createClass(cm3);
		
		// Check for multiple classes
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(class1id);
		
		List<EntityClassModel> single = classService.getClassesByClassIDs(ids);
		
		assertNotNull(single);
		assertTrue(1 == single.size());
		
		ids.add(class2id);
		ids.add(class3id);
		
		List<EntityClassModel> triple = classService.getClassesByClassIDs(ids);
		
		assertNotNull(triple);
		assertTrue(3 == triple.size());
	}
	
	@Test
	public void TestGetClassesByClassIDsAndUser_Empty()
	{
		EntityUserModel single = createSingleUser();
		userService.createUser(single, Permission.STUDENT, imDefaultInstitution.getId());
		
		List<Integer> empty = new ArrayList<Integer>();
		List<EntityClassModel> list = classRepository.getClassesByClassIDsAndUser(empty, single.getId(), "STUDENT");
			
		assertNotNull(list);
		assertTrue(0 == list.size());
	}
	
	@Test
	public void TestGetClassesByClassIDsAndUser_None()
	{
		// Create some classes
		EntityClassModel cm1 = createFirstClass();
		Integer class1id = classService.createClass(cm1);
		
		EntityClassModel cm2 = createSecondClass();
		Integer class2id = classService.createClass(cm2);

		EntityUserModel single = createSingleUser();
		userService.createUser(single, Permission.STUDENT, imDefaultInstitution.getId());
		
		EntityClassUsersModel tie = (EntityClassUsersModel) modelFactory.getNewClassUsersModel();
		tie.setClassId(class1id);
		tie.setUserId(single.getId());
		tie.setUserType("STUDENT");
		classService.addClassUser(tie);
		
		List<Integer> other = new ArrayList<Integer>();
		other.add(class2id);
		List<EntityClassModel> list = classRepository.getClassesByClassIDsAndUser(other, single.getId(), "STUDENT");
			
		assertNotNull(list);
		assertTrue(0 == list.size());
	}
	
	@Test
	public void TestGetClassesByClassIDsAndUser_Some()
	{
		// Create some classes
		EntityClassModel cm1 = createFirstClass();
		Integer class1id = classService.createClass(cm1);
		
		EntityClassModel cm2 = createSecondClass();
		Integer class2id = classService.createClass(cm2);

		EntityUserModel single = createSingleUser();
		userService.createUser(single, Permission.STUDENT, imDefaultInstitution.getId());
		
		EntityClassUsersModel tie1 = (EntityClassUsersModel) modelFactory.getNewClassUsersModel();
		tie1.setClassId(class1id);
		tie1.setUserId(single.getId());
		tie1.setUserType("STUDENT");
		classService.addClassUser(tie1);
		
		List<Integer> attending = new ArrayList<Integer>();
		attending.add(class1id);
		List<EntityClassModel> list = classRepository.getClassesByClassIDsAndUser(attending, single.getId(), "STUDENT");
			
		assertNotNull(list);
		assertTrue(1 == list.size());
		assertTrue(list.get(0).getId() == class1id);
		
		EntityClassUsersModel tie2 = (EntityClassUsersModel) modelFactory.getNewClassUsersModel();
		tie2.setClassId(class2id);
		tie2.setUserId(single.getId());
		tie2.setUserType("STUDENT");
		classService.addClassUser(tie2);
		
		attending.add(class2id);
		list = classRepository.getClassesByClassIDsAndUser(attending, single.getId(), "STUDENT");
			
		assertNotNull(list);
		assertTrue(2 == list.size());
	}
	
	@Test
	public void TestGetClassesByClassIDsAndUser()
	{
		
	}
	
	@Test
	public void TestGetClassIDsByTermIds_Empty()
	{
		// Create some classes
		EntityClassModel cm1 = createFirstClass();
		@SuppressWarnings("unused")
		Integer class1id = classService.createClass(cm1);
		
		EntityClassModel cm2 = createSecondClass();
		@SuppressWarnings("unused")
		Integer class2id = classService.createClass(cm2);
		
		EntityClassModel cm3 = createAClass("Spanglish 303", "2018SP3", tmFirstTerm);
		@SuppressWarnings("unused")
		Integer class3id = classService.createClass(cm3);
		
		List<Integer> ids = new ArrayList<Integer>();
		
		List<Integer> listNone = classRepository.getClassIDsByTermIds(ids);
		
		assertNotNull(listNone);
		assertTrue(0 == listNone.size());
	}
	
	@Test
	public void TestGetClassIDsByTermIds_None()
	{
		// Create some classes
		EntityClassModel cm1 = createFirstClass();
		@SuppressWarnings("unused")
		Integer class1id = classService.createClass(cm1);
		
		EntityClassModel cm2 = createSecondClass();
		@SuppressWarnings("unused")
		Integer class2id = classService.createClass(cm2);
		
		createExtraTerm(imDefaultInstitution.getId());
		
		EntityClassModel cm3 = createAClass("Spanglish 303", "2018SP3", tmExtraTerm);
		@SuppressWarnings("unused")
		Integer class3id = classService.createClass(cm3);
		
		// Add non-existent term IDs
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1000000 + tmFirstTerm.getId());
		ids.add(1000000 + tmExtraTerm.getId());
		
		List<Integer> listNone = classRepository.getClassIDsByTermIds(ids);
		
		assertNotNull(listNone);
		assertTrue(0 == listNone.size());
	}
	
	@Test
	public void TestGetClassIDsByTermIds_Some()
	{
		// Create some classes
		EntityClassModel cm1 = createFirstClass();
		Integer class1id = classService.createClass(cm1);
		
		EntityClassModel cm2 = createSecondClass();
		Integer class2id = classService.createClass(cm2);
		
		createExtraTerm(imDefaultInstitution.getId());
		
		EntityClassModel cm3 = createAClass("Spanglish 303", "2018SP3", tmExtraTerm);
		Integer class3id = classService.createClass(cm3);
		
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(tmFirstTerm.getId());
		
		List<Integer> listSome = classRepository.getClassIDsByTermIds(ids);
		
		assertNotNull(listSome);
		assertTrue(2 == listSome.size());
		assertTrue(listSome.contains(class1id));
		assertTrue(listSome.contains(class2id));

		// Try with another term
		ids.add(tmExtraTerm.getId());
		
		listSome = classRepository.getClassIDsByTermIds(ids);
		
		assertNotNull(listSome);
		assertTrue(3 == listSome.size());
		assertTrue(listSome.contains(class3id));
	}
	
	@Test
	public void TestUpdateClass_OK()
	{
		// Create some classes
		EntityClassModel cm1 = createFirstClass();
		Integer class1id = classService.createClass(cm1);
		
		// Change some fields
		EntityClassModel cm2 = classService.findClassById(class1id);
		
		assertNotNull(cm2);
		
		cm2.setClassname(sAlterClassname);
		cm2.setClasscode(sAlterClasscode);
		
		// Update the database
		classService.updateClass(cm2);
		
		EntityClassModel cm3 = classService.findClassById(class1id);
		
		assertNotNull(cm3);
		assertTrue(cm3.getClassname().equals(sAlterClassname));
		assertTrue(cm3.getClasscode().equals(sAlterClasscode));
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void TestUpdateClass_Duplicate()
	{
		// Create some classes
		EntityClassModel cm1 = createFirstClass();
		@SuppressWarnings("unused")
		Integer class1id = classService.createClass(cm1);
		
		EntityClassModel cm2 = createSecondClass();
		@SuppressWarnings("unused")
		Integer class2id = classService.createClass(cm2);
		
		// Change the class code field to one that exists
		cm1.setClasscode(sSecondClasscode);
		
		// Update the database
		classService.updateClass(cm1);
		
		// Change the class name field to one that exists
		cm1.setClassname(sSecondClassname);
		
		// Update the database should fail
		classService.updateClass(cm1);
		
		// Should not reach here
		assertTrue(false);
	}
	
	@Test
	public void TestDeleteClass_OK()
	{
		EntityClassModel cm1 = createFirstClass();
		Integer class1id = classService.createClass(cm1);
		
		EntityClassModel cm2 = createSecondClass();
		Integer class2id = classService.createClass(cm2);
		
		try
		{
			classService.deleteClass(class1id);

			List<EntityClassModel> list1 = classService.getListOfClassesByTermId(tmFirstTerm.getId());
			
			// second class should remain
			assertTrue(1 == list1.size());
			assertTrue(class2id == list1.get(0).getId());
		}
		catch (Exception e)
		{
			// should not reach here
			assertTrue(false);
		}
	}
	
	@Test
	public void TestDeleteClass_Bad()
	{
		EntityClassModel cm1 = createFirstClass();
		Integer class1id = classService.createClass(cm1);
		
		try
		{
			classService.deleteClass(1000000 + class1id);
			
			// should not reach here
			assertTrue(false);
		}
		catch (NoResultException nre)
		{
			// expected result
			assertTrue(true);
		}
		catch (Exception e)
		{
			// should not reach here
			assertTrue(false);
		}
	}
}
