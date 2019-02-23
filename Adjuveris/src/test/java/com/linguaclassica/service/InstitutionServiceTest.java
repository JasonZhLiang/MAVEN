package com.linguaclassica.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.repository.RepositoryConfiguration;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.access.LoginHelper;
import com.linguaclassica.entity.EntityInstitutionModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.InstitutionModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.PermissionModel.Permission;


/**
 * Test the functions in the InstitutionService layer.
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
public class InstitutionServiceTest extends AbstractTransactionalJUnit4SpringContextTests
{
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private InstitutionService instService;
	
	@Autowired
	private UserService userService;

	private String sFirstInstitution = "uArcturus Test";
	private String sFirstPostad = "1000 University Drive, Startown, ON";
	private String sFirstContact = "Registration Office";
	private String sFirstPhone = "1-613-555-1000";
	private String sFirstEmail = "registration@uarcturus.edu";
	private String sFirstAdmingiven = "Primus";
	private String sFirstAdminfamily = "Bureaucratus";
	private String sFirstAdminemail = "prime.bear@uarcturus.edu";
	
	private String sSecondAdmingiven = "Secondus";
	private String sSecondAdminfamily = "Flunkitus";
	private String sSecondAdminemail = "second.flunkie@uarcturus.edu";
	private String sSecondUsernum = "171234567";
	private String sSecondPassword = "NextInCommand";

	private String sThirdInstitution = "uCardriver Test";
	private String sThirdPostad = "2000 Racetrack Road, Autoville, ON";
	private String sThirdContact = "Racing Office";
	private String sThirdPhone = "1-613-555-2000";
	private String sThirdEmail = "registration@ucardriver.edu";
	private String sThirdAdmingiven = "Backseat";
	private String sThirdAdminfamily = "Driver";
	private String sThirdAdminemail = "bdriver@ucardriver.edu";
	
	private String sFourthAdmingiven = "Otto";
	private String sFourthAdminfamily = "Ryder";
	private String sFourthAdminemail = "otto.ryder@ucardriver.edu";
	private String sFourthUsernum = "171223344";
	private String sFourthPassword = "SnoozeControl";
	
	private String sFifthAdmingiven = "Harold";
	private String sFifthAdminfamily = "Hood";
	private String sFifthAdminemail = "hhood@ucardriver.edi";
	private String sFifthUsernum = "17466341";
	private String sFifthPassword = "FrontEnd";
	
	private String sSixthAdmingiven = "Arnold";
	private String sSixthAdminfamily = "Wagoner";
	private String sSixthAdminemail = "awagoner@ucardriver.edi";
	private String sSixthUsernum = "17924663";
	private String sSixthPassword = "PullingSomeStuff";

	private InstitutionModel imFirstInst = null;
	private UserModel umSecondUser = null;
	private InstitutionModel imThirdInst = null;
	private UserModel umFourthUser = null;
	private UserModel umFifthUser = null;
	private UserModel umSixthUser = null;

	/**
	 * Create the first institution administrator
	 */
	private void createFirstInstitution()
	{
		imFirstInst = modelFactory.getNewInstitutionModel();
		imFirstInst.setInstname(sFirstInstitution);
		imFirstInst.setPostaddress(sFirstPostad);
		imFirstInst.setPrimecontact(sFirstContact);
		imFirstInst.setPrimephone(sFirstPhone);
		imFirstInst.setPrimeemail(sFirstEmail);
		imFirstInst.setAdminfirstname(sFirstAdmingiven);
		imFirstInst.setAdminlastname(sFirstAdminfamily);
		imFirstInst.setAdminemail(sFirstAdminemail);
	}
	
	private void createSecondUser()
	{
		umSecondUser = modelFactory.getNewUserModel();
		umSecondUser.setFirstName(sSecondAdmingiven);
		umSecondUser.setLastName(sSecondAdminfamily);
		umSecondUser.setEmailAddress(sSecondAdminemail);
		umSecondUser.setStudentnumber(sSecondUsernum);
		String encrypted = "TooLongForValidPasswordWithBad(@)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sSecondPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		umSecondUser.setPassword(encrypted);
	}

	private void createThirdInstitution()
	{
		imThirdInst = modelFactory.getNewInstitutionModel();
		imThirdInst.setInstname(sThirdInstitution);
		imThirdInst.setPostaddress(sThirdPostad);
		imThirdInst.setPrimecontact(sThirdContact);
		imThirdInst.setPrimephone(sThirdPhone);
		imThirdInst.setPrimeemail(sThirdEmail);
		imThirdInst.setAdminfirstname(sThirdAdmingiven);
		imThirdInst.setAdminlastname(sThirdAdminfamily);
		imThirdInst.setAdminemail(sThirdAdminemail);
	}
	
	private void createFourthUser()
	{
		umFourthUser = modelFactory.getNewUserModel();
		umFourthUser.setFirstName(sFourthAdmingiven);
		umFourthUser.setLastName(sFourthAdminfamily);
		umFourthUser.setEmailAddress(sFourthAdminemail);
		umFourthUser.setStudentnumber(sFourthUsernum);
		String encrypted = "TooLongForValidPasswordWithBad($)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sFourthPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		umFourthUser.setPassword(encrypted);
	}
	
	private void createFifthUser()
	{
		umFifthUser = modelFactory.getNewUserModel();
		umFifthUser.setFirstName(sFifthAdmingiven);
		umFifthUser.setLastName(sFifthAdminfamily);
		umFifthUser.setEmailAddress(sFifthAdminemail);
		umFifthUser.setStudentnumber(sFifthUsernum);
		String encrypted = "TooLongForValidPasswordWithBad(%)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sFifthPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		umFifthUser.setPassword(encrypted);
	}
	
	private void createSixthUser()
	{
		umSixthUser = modelFactory.getNewUserModel();
		umSixthUser.setFirstName(sSixthAdmingiven);
		umSixthUser.setLastName(sSixthAdminfamily);
		umSixthUser.setEmailAddress(sSixthAdminemail);
		umSixthUser.setStudentnumber(sSixthUsernum);
		String encrypted = "TooLongForValidPasswordWithBad(^)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sSixthPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		umSixthUser.setPassword(encrypted);
	}

	@Before
	public void setUp() throws Exception
	{
		// Create a default institution for ALL of the tests
		createFirstInstitution();
		
		// Create a default user for many of the tests
		createSecondUser();

		// Create another institution for many of the tests
		createThirdInstitution();
		
		// Create another user for many of the tests
		createFourthUser();
		
		// Create another user for many of the tests
		createFifthUser();
		
		// Create another user for many of the tests
		createSixthUser();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCreateInitialInstitution_OK()
	{
		int resultid = instService.createInstitution(imFirstInst);
		
		assertTrue(resultid > 0);
		
		InstitutionModel imCheck = instService.findInstitutionById(resultid);
		
		assertNotNull(imCheck);
		assertEquals(imCheck.getInstname(), sFirstInstitution);
		assertEquals(imCheck.getPrimeemail(), sFirstEmail);
		
		InstitutionModel imChect = instService.findInstitutionByName(sFirstInstitution);
		
		assertNotNull(imChect);
		assertEquals(imChect.getId().intValue(), resultid);
		assertEquals(imChect.getPrimeemail(), sFirstEmail);
	}

	@Test(expected=ServiceException.class)
	public void testCreateInitialInstitution_Same()
	{
		// Save the first institution
		int resultid = instService.createInstitution(imFirstInst);
		
		assertTrue(resultid > 0);

		// Create a second institution with the same code
		InstitutionModel imSameInst = modelFactory.getNewInstitutionModel();
		imSameInst.setInstname(sFirstInstitution);
		imSameInst.setPostaddress(sFirstPostad);
		imSameInst.setPrimecontact(sFirstContact);
		imSameInst.setPrimephone(sFirstPhone);
		imSameInst.setPrimeemail(sFirstEmail);
		imSameInst.setAdminfirstname(sFirstAdmingiven);
		imSameInst.setAdminlastname(sFirstAdminfamily);
		imSameInst.setAdminemail(sFirstAdminemail);
		
		instService.createInstitution(imSameInst);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=ServiceException.class)
	public void testCreateInitialInstitution_Fails()
	{
		// Save the first institution
		int resultid = instService.createInstitution(imFirstInst);
		
		assertTrue(resultid > 0);

		// Create a second institution with invalid code
		InstitutionModel imDiffInst = modelFactory.getNewInstitutionModel();
		imDiffInst.setInstname("A Realy Long Institution Name Intending to Fail the Creation Test");
		imDiffInst.setPostaddress(sFirstPostad);
		imDiffInst.setPrimecontact("A Realy Long Primary Contact Name for the Institution Intending to Fail the Creation Test");
		imDiffInst.setPrimephone("011-1-416-555-1000 123456 7890 1234567890");
		imDiffInst.setPrimeemail("thelongwindedandhugelynamedpompusadministrator@TheUniversityOfStateCityNeighborhood@edu");
		imDiffInst.setAdminfirstname("Long Winded and Hugely Named Pompus");
		imDiffInst.setAdminlastname(sFirstAdminfamily);
		imDiffInst.setAdminemail(sFirstAdminemail);
		
		instService.createInstitution(imDiffInst);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=ServiceException.class)
	public void testCreateInstUser_Same()
	{
		// Create the institution
		int resultid = instService.createInstitution(imFirstInst);
		
		// Create an institution administrator
		userService.createUser(umSecondUser, Permission.INSTITUTION_ADMIN, resultid);
		
		assertTrue(umSecondUser.getId() > 0);
		
		// Create the same institution administrator
		userService.createUser(umSecondUser, Permission.INSTITUTION_ADMIN, resultid);
		
		// Should not get here
		assertTrue(false);
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testCreateInstUser_InstFails()
	{
		// Create the institution
		int resultid = instService.createInstitution(imFirstInst);
		
		// Create an institution administrator
		userService.createUser(umSecondUser, Permission.INSTITUTION_ADMIN, 1000000 + resultid);

		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=ServiceException.class)
	public void testFindInstitutionById_Fails()
	{
		@SuppressWarnings("unused")
		EntityInstitutionModel eim = instService.findInstitutionById(1000000);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testUpdateInstitution_OK()
	{
		String sAdminEmail = "p.bureau@uarcturus.edu";
		String sContactEmail = "contactus@uarcturus.edu";
		
		// Create the institution
		int resultid = instService.createInstitution(imFirstInst);

		// Make some changes and update
		imFirstInst.setAdminemail(sAdminEmail);
		imFirstInst.setPrimeemail(sContactEmail);
		instService.updateInstitution(imFirstInst);
		
		assertTrue(resultid == imFirstInst.getId());
		
		// Retrieve the updated institution
		InstitutionModel imCheck = instService.findInstitutionById(resultid);
		
		assertTrue(0 == sAdminEmail.compareTo(imCheck.getAdminemail()));
		assertTrue(0 == sContactEmail.compareTo(imCheck.getPrimeemail()));
	}

	@Test(expected=InvalidDataAccessApiUsageException.class)
	public void testUpdateInstitution_None()
	{
		// Update an unidentified institution
		instService.updateInstitution(imFirstInst);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testUpdateInstitution_Fails()
	{
		// Create the institution
		int resultid = instService.createInstitution(imFirstInst);

		// Make some changes using invalid data and update
		imFirstInst.setPrimecontact("A Realy Long Primary Contact Name for the Institution Intending to Fail the Creation Test");
		
		instService.updateInstitution(imFirstInst);
		
		// Should not reach here
		// Retrieve the updated institution
		InstitutionModel imCheck = instService.findInstitutionById(resultid);
		
		assertTrue(resultid == imCheck.getId());
		assertTrue(false);
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testUpdateInstitution_Duplicate()
	{
		// Create the institution
		@SuppressWarnings("unused")
		int resultid = instService.createInstitution(imFirstInst);

		// Create another institution
		@SuppressWarnings("unused")
		int otherid = instService.createInstitution(imThirdInst);
		
		// Change the name field to one that exists
		imFirstInst.setInstname(sThirdInstitution);
		
		// Update the database
		instService.updateInstitution(imFirstInst);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testGetListOfInstitutions_OK()
	{
		// Create the institution
		int resultid = instService.createInstitution(imFirstInst);

		// Create another institution
		int otherid = instService.createInstitution(imThirdInst);
		
		// Get the current institutions
		List<EntityInstitutionModel> entities = instService.getListOfInstitutions();
		
		assertTrue(entities.size() >= 2);
		
		// Confirm that the two institutions are in the list
		boolean bone = false;
		boolean bthree = false;
		for (EntityInstitutionModel imScan : entities)
		{
			if (imScan.getId() == resultid)
			{
				bone = true;
			}
			if (imScan.getId() == otherid)
			{
				bthree = true;
			}
		}
		assertTrue(bone);
		assertTrue(bthree);
	}

	@Test
	public void testGetListOfUsersByInstitution()
	{
		// Create the institution
		int resultid = instService.createInstitution(imFirstInst);
		
		// Create an institution administrator
		userService.createUser(umSecondUser, Permission.INSTITUTION_ADMIN, resultid);

		// Create another the institution
		int otherid = instService.createInstitution(imThirdInst);
		
		// Create another institution administrator
		userService.createUser(umFourthUser, Permission.INSTITUTION_ADMIN, otherid);

		// Create another institution administrator
		userService.createUser(umFifthUser, Permission.INSTITUTION_ADMIN, otherid);

		// Create a teacher
		userService.createUser(umSixthUser, Permission.TEACHER, otherid);

		List<EntityUserModel> listOne = userService.getUsersByPermission(resultid, Permission.INSTITUTION_ADMIN);
		
		assertTrue(listOne.size() == 1);
		assertTrue(listOne.get(0).getId() == umSecondUser.getId());
		
		List<EntityUserModel> listThree = userService.getUsersByPermission(otherid, Permission.INSTITUTION_ADMIN);
		
		assertTrue(listThree.size() == 2);
		List<Integer> threeids = new ArrayList<Integer>();
		threeids.add(listThree.get(0).getId());
		threeids.add(listThree.get(1).getId());
		assertTrue(threeids.contains(umFourthUser.getId()));
		assertTrue(threeids.contains(umFifthUser.getId()));
		
		List<EntityUserModel> listX = userService.getUsersByPermission(1000000 + otherid, Permission.INSTITUTION_ADMIN);
		
		assertTrue(listX.size() == 0);
		
		List<EntityUserModel> listY = userService.getUsersByPermission(resultid, Permission.TEACHER);
		
		assertTrue(listY.size() == 0);
		
		List<EntityUserModel> listZ = userService.getUsersByPermission(otherid, Permission.TEACHER);
		
		assertTrue(listZ.size() == 1);
		assertTrue(listZ.get(0).getId() == umSixthUser.getId());
	}

	@Test
	public void testDeleteInstUser_OK()
	{
		// Create the institution
		int resultid = instService.createInstitution(imFirstInst);
		
		// Create an institution administrator
		userService.createUser(umSecondUser, Permission.INSTITUTION_ADMIN, resultid);

		// Create another the institution
		int otherid = instService.createInstitution(imThirdInst);
		
		// Create another institution administrator
		userService.createUser(umFourthUser, Permission.INSTITUTION_ADMIN, otherid);

		// Create another institution administrator
		userService.createUser(umFifthUser, Permission.INSTITUTION_ADMIN, otherid);
		
		List<EntityUserModel> listOne = userService.getUsersByPermission(resultid, Permission.INSTITUTION_ADMIN);

		assertTrue(listOne.size() == 1);
		
		List<EntityUserModel> listTwo = userService.getUsersByPermission(otherid, Permission.INSTITUTION_ADMIN);

		assertTrue(listTwo.size() == 2);

		try
		{
			instService.deleteInstUser(umFourthUser.getId(), otherid);
			
			List<EntityUserModel> listThree = userService.getUsersByPermission(otherid, Permission.INSTITUTION_ADMIN);
			
			assertTrue(listThree.size() == 1);
			assertTrue(listThree.get(0).getId() == umFifthUser.getId());
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	@Test
	public void testDeleteInstUser_Fails()
	{
		boolean bBadInst = false;
		boolean bBadUser = false;
		
		// Create the institution
		int resultid = instService.createInstitution(imFirstInst);
		
		// Create an institution administrator
		userService.createUser(umSecondUser, Permission.INSTITUTION_ADMIN, resultid);
		
		try
		{
			instService.deleteInstUser(1000000 + umSecondUser.getId(), resultid);
		}
		catch (Exception e)
		{
			// Expected to fail
			assertTrue(e.getClass() == EmptyResultDataAccessException.class);
			assertTrue(e.getCause().getClass() == NoResultException.class);
			bBadUser = true;
		}
		
		try
		{
			instService.deleteInstUser(umSecondUser.getId(), 1000000 + resultid);
		}
		catch (Exception e)
		{
			// Expected to fail
			assertTrue(e.getClass() == EmptyResultDataAccessException.class);
			assertTrue(e.getCause().getClass() == NoResultException.class);
			bBadInst = true;
		}
		
		assertTrue(bBadInst && bBadUser);
	}
}
