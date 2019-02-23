package com.linguaclassica.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
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

import com.linguaclassica.access.LoginHelper;
import com.linguaclassica.entity.EntityOrgModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.ConsultantClientsModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.repository.RepositoryConfiguration;
import com.linguaclassica.service.exception.CircumstanceException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

/**
 * Test the functions in the UserService layer.
 * 
 * @author Joe A. Brown
 * 
 * When Spring runs a test that is annotated with @Transactional, 
 * then by default it will roll back the transaction when the test completes.
 * Data will not remain in the eolms tables.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RepositoryConfiguration.class})
@TransactionConfiguration
@Transactional
public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests
{
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private ConsultantClientsService concliService;
	
	private Integer ARCHIVED = 1;
	
	private String sDefaultOrgname = "FirstJobsON";
	private String sDefaultOrgmail = "exec@firstjobson.edu";
	
	private String sFirstGiven = "Hannah";
	private String sFirstFamily = "Lector";
	private String sFirstEmail = "hannah.lector@uarcturus.edu";
	private String sFirstPassword = "HereToLearnMuch";
	
	private String sSecondGiven = "Splash";
	private String sSecondFamily = "Boots";
	private String sSecondEmail = "splash.n.boots@uarcturus.edu";
	private String sSecondPassword = "HereToHaveFun";
	
	private String sThirdGiven = "Annie";
	private String sThirdFamily = "Hull";
	private String sThirdEmail = "anniewho@uarcturus.edu";
	
	private String sFourthGiven = "Benny";
	private String sFourthFamily = "Hill";
	private String sFourthEmail = "bennyhill@freemil.ca";
	
	private String sFifthGiven = "Grant";
	private String sFifthFamily = "Carey";
	private String sFifthEmail = "gcarey@freemil.ca";
	
	private String sSixthGiven = "Felix";
	private String sSixthFamily = "Fernandez";
	private String sSixthEmail = "felixfern@freemil.ca";
	
	private String sSeventhGiven = "Harvey";
	private String sSeventhFamily = "Wall";
	private String sSeventhEmail = "wallbanger@freemil.ca";
	
	private EntityOrgModel oeDefaultOrg = null;
	private EntityUserModel ueFirstUser = null;
	
	private void createDefaultOrganization()
	{
		oeDefaultOrg = (EntityOrgModel) modelFactory.getNewOrgModel();
		oeDefaultOrg.setOrgname(sDefaultOrgname);
		oeDefaultOrg.setOrgemail(sDefaultOrgmail);
		organizationService.createOrganization(oeDefaultOrg);
	}
	
	private void createFirstUser()
	{
		ueFirstUser = (EntityUserModel) modelFactory.getNewUserModel();
		ueFirstUser.setFirstName(sFirstGiven);
		ueFirstUser.setLastName(sFirstFamily);
		ueFirstUser.setEmailAddress(sFirstEmail);
		String encrypted = "TooLongForValidPasswordWithBad(@)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sFirstPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		ueFirstUser.setPassword(encrypted);
	}
	
	private UserModel createSingleUser(String sFirst, String sLast, String sEmail)
	{
		UserModel singleUM = modelFactory.getNewUserModel();
		singleUM.setFirstName(sFirst);
		singleUM.setLastName(sLast);
		singleUM.setEmailAddress(sEmail);
		String encrypted = "TooLongForValidPasswordWithBad(@)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword("CommonPassword");
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		singleUM.setPassword(encrypted);
		singleUM.setStatus(0);
		return singleUM;
	}
	
	@Before
	public void setUp() throws Exception
	{
		// Create a default organization for many of the tests
		createDefaultOrganization();
		
		// Create a default user for many of the tests
		createFirstUser();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCreateUser_OK()
	{
		userService.createUser(ueFirstUser, Permission.CLIENT, oeDefaultOrg.getId());
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testCreateUser_BadOrg()
	{
		userService.createUser(ueFirstUser, Permission.OFFICE_ADMIN, 1000000 + oeDefaultOrg.getId());
		
		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testCreateUser_Fails()
	{
		userService.createUser(ueFirstUser, Permission.CLIENT, oeDefaultOrg.getId());

		// try again with different user but same email
		EntityUserModel ueTypoUser = (EntityUserModel) modelFactory.getNewUserModel();
		ueTypoUser.setFirstName(sSecondGiven);
		ueTypoUser.setLastName(sSecondFamily);
		ueTypoUser.setEmailAddress(sFirstEmail);  // already in use
		String encrypted = "TooLongForValidPasswordWithBad(@)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sSecondPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		ueTypoUser.setPassword(encrypted);
		userService.createUser(ueTypoUser, Permission.CLIENT, oeDefaultOrg.getId());
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testAuthenticateUser_OK()
	{
		userService.createUser(ueFirstUser, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel umResult = null;
		try
		{
			umResult = userService.authenticateUser(sFirstEmail, ueFirstUser.getPassword());
		}
		catch (UnknownEntityException e)
		{
			// Should not reach here
			assertTrue(false);
		}
		catch (ServiceException e)
		{
			// Should not reach here
			assertTrue(false);
		} catch (CircumstanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(umResult);
		assertTrue(0 == umResult.getFirstName().compareTo(sFirstGiven));
		assertTrue(0 == umResult.getLastName().compareTo(sFirstFamily));
		assertEquals(0, umResult.getFirstName().compareTo(sFirstGiven));
		assertEquals(0, umResult.getLastName().compareTo(sFirstFamily));
	}

	@Test(expected=ServiceException.class)
	public void testAuthenticateUser_BadEmail()
	{
		try
		{
			@SuppressWarnings("unused")
			UserModel umResult = userService.authenticateUser(sSecondEmail, sSecondPassword);
		}
		catch (UnknownEntityException e)
		{
			// May reach here
			throw(e);
		}
		catch (ServiceException e)
		{
			// Should reach here
			throw(e);
		} catch (CircumstanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=ServiceException.class)
	public void testAuthenticateUser_BadPassword()
	{
		userService.createUser(ueFirstUser, Permission.CLIENT, oeDefaultOrg.getId());
		
		try
		{
			@SuppressWarnings("unused")
			UserModel umResult = userService.authenticateUser(sFirstEmail, sFirstPassword);
		}
		catch (UnknownEntityException e)
		{
			// May reach here
			throw(e);
		}
		catch (ServiceException e)
		{
			// Should reach here
			throw(e);
		} catch (CircumstanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testGetListOfUsersByPermission_None()
	{
		List<EntityUserModel> clients = userService.getListOfUsersByPermission(oeDefaultOrg.getId(), Permission.CLIENT);
		
		assertEquals(clients.size(), 0);

		List<EntityUserModel> consultants = userService.getListOfUsersByPermission(oeDefaultOrg.getId(), Permission.CONSULTANT);
		
		assertEquals(consultants.size(), 0);

		List<EntityUserModel> offadmins = userService.getListOfUsersByPermission(oeDefaultOrg.getId(), Permission.OFFICE_ADMIN);
		
		assertEquals(offadmins.size(), 0);

		List<EntityUserModel> administrators = userService.getListOfUsersByPermission(oeDefaultOrg.getId(), Permission.ADMINISTRATOR);
		
		assertEquals(administrators.size(), 0);
	}

	@Test
	public void testGetListOfUsersByPermission_Some()
	{
		// Client several users, of different permissions
		userService.createUser(ueFirstUser, Permission.OFFICE_ADMIN, oeDefaultOrg.getId());
		
		UserModel consultantOne = createSingleUser(sSecondGiven, sSecondFamily, sSecondEmail);
		userService.createUser(consultantOne, Permission.CONSULTANT, oeDefaultOrg.getId());
		
		UserModel consultantTwo = createSingleUser(sThirdGiven, sThirdFamily, sThirdEmail);
		userService.createUser(consultantTwo, Permission.CONSULTANT, oeDefaultOrg.getId());
		
		UserModel clientOne = createSingleUser(sFourthGiven, sFourthFamily, sFourthEmail);
		userService.createUser(clientOne, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel clientTwo = createSingleUser(sFifthGiven, sFifthFamily, sFifthEmail);
		userService.createUser(clientTwo, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel clientThree = createSingleUser(sSixthGiven, sSixthFamily, sSixthEmail);
		userService.createUser(clientThree, Permission.CLIENT, oeDefaultOrg.getId());

		// Read the user from the database
		List<EntityUserModel> offadmins = userService.getListOfUsersByPermission(oeDefaultOrg.getId(), Permission.OFFICE_ADMIN);
		
		assertEquals(offadmins.size(), 1);
		assertEquals(ueFirstUser.getId(), offadmins.get(0).getId());

		List<EntityUserModel> consultants = userService.getListOfUsersByPermission(oeDefaultOrg.getId(), Permission.CONSULTANT);
		
		assertEquals(consultants.size(), 2);

		List<EntityUserModel> clients = userService.getListOfUsersByPermission(oeDefaultOrg.getId(), Permission.CLIENT);
		
		assertEquals(clients.size(), 3);
	}

	@Test
	public void testGetListOfActiveUsersByPermission_None()
	{
		// Client several users, of different permissions, all inactive
		ueFirstUser.setStatus(ARCHIVED);
		userService.createUser(ueFirstUser, Permission.OFFICE_ADMIN, oeDefaultOrg.getId());
		
		UserModel consultantOne = createSingleUser(sSecondGiven, sSecondFamily, sSecondEmail);
		consultantOne.setStatus(ARCHIVED);
		userService.createUser(consultantOne, Permission.CONSULTANT, oeDefaultOrg.getId());
		
		UserModel consultantTwo = createSingleUser(sThirdGiven, sThirdFamily, sThirdEmail);
		consultantTwo.setStatus(ARCHIVED);
		userService.createUser(consultantTwo, Permission.CONSULTANT, oeDefaultOrg.getId());
		
		UserModel clientOne = createSingleUser(sFourthGiven, sFourthFamily, sFourthEmail);
		clientOne.setStatus(ARCHIVED);
		userService.createUser(clientOne, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel clientTwo = createSingleUser(sFifthGiven, sFifthFamily, sFifthEmail);
		clientTwo.setStatus(ARCHIVED);
		userService.createUser(clientTwo, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel clientThree = createSingleUser(sSixthGiven, sSixthFamily, sSixthEmail);
		clientThree.setStatus(ARCHIVED);
		userService.createUser(clientThree, Permission.CLIENT, oeDefaultOrg.getId());

		// Read the users from the database
		List<EntityUserModel> clients = userService.getListOfActiveUsersByPermission(oeDefaultOrg.getId(), Permission.CLIENT);
		
		assertEquals(clients.size(), 0);

		List<EntityUserModel> consultants = userService.getListOfActiveUsersByPermission(oeDefaultOrg.getId(), Permission.CONSULTANT);
		
		assertEquals(consultants.size(), 0);

		List<EntityUserModel> offadmins = userService.getListOfActiveUsersByPermission(oeDefaultOrg.getId(), Permission.OFFICE_ADMIN);
		
		assertEquals(offadmins.size(), 0);

		List<EntityUserModel> administrators = userService.getListOfActiveUsersByPermission(oeDefaultOrg.getId(), Permission.ADMINISTRATOR);
		
		assertEquals(administrators.size(), 0);
	}

	@Test
	public void testGetListOfActiveUsersByPermission_Some()
	{
		// Client several users, of different permissions, all inactive
		ueFirstUser.setStatus(ARCHIVED);
		userService.createUser(ueFirstUser, Permission.OFFICE_ADMIN, oeDefaultOrg.getId());
		
		UserModel offadminTwo = createSingleUser(sSeventhGiven, sSeventhFamily, sSeventhEmail);
		userService.createUser(offadminTwo, Permission.OFFICE_ADMIN, oeDefaultOrg.getId());
		
		UserModel consultantOne = createSingleUser(sSecondGiven, sSecondFamily, sSecondEmail);
		consultantOne.setStatus(ARCHIVED);
		userService.createUser(consultantOne, Permission.CONSULTANT, oeDefaultOrg.getId());
		
		UserModel consultantTwo = createSingleUser(sThirdGiven, sThirdFamily, sThirdEmail);
		userService.createUser(consultantTwo, Permission.CONSULTANT, oeDefaultOrg.getId());
		
		UserModel clientOne = createSingleUser(sFourthGiven, sFourthFamily, sFourthEmail);
		userService.createUser(clientOne, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel clientTwo = createSingleUser(sFifthGiven, sFifthFamily, sFifthEmail);
		clientTwo.setStatus(ARCHIVED);
		userService.createUser(clientTwo, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel clientThree = createSingleUser(sSixthGiven, sSixthFamily, sSixthEmail);
		userService.createUser(clientThree, Permission.CLIENT, oeDefaultOrg.getId());

		// Read the users from the database
		List<EntityUserModel> offadmins = userService.getListOfActiveUsersByPermission(oeDefaultOrg.getId(), Permission.OFFICE_ADMIN);
		
		assertEquals(offadmins.size(), 1);
		assertEquals(offadminTwo.getId(), offadmins.get(0).getId());

		List<EntityUserModel> consultants = userService.getListOfActiveUsersByPermission(oeDefaultOrg.getId(), Permission.CONSULTANT);
		
		assertEquals(consultants.size(), 1);
		assertEquals(consultantTwo.getId(), consultants.get(0).getId());

		List<EntityUserModel> clients = userService.getListOfActiveUsersByPermission(oeDefaultOrg.getId(), Permission.CLIENT);
		
		assertEquals(clients.size(), 2);
	}

	@Test
	public void testGetAssignments_None()
	{
		// Client several clients and consultants
		userService.createUser(ueFirstUser, Permission.CONSULTANT, oeDefaultOrg.getId());
		
		UserModel consultantOne = createSingleUser(sSecondGiven, sSecondFamily, sSecondEmail);
		userService.createUser(consultantOne, Permission.CONSULTANT, oeDefaultOrg.getId());
		
		UserModel consultantTwo = createSingleUser(sThirdGiven, sThirdFamily, sThirdEmail);
		userService.createUser(consultantTwo, Permission.CONSULTANT, oeDefaultOrg.getId());
		
		UserModel clientOne = createSingleUser(sFourthGiven, sFourthFamily, sFourthEmail);
		userService.createUser(clientOne, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel clientTwo = createSingleUser(sFifthGiven, sFifthFamily, sFifthEmail);
		userService.createUser(clientTwo, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel clientThree = createSingleUser(sSixthGiven, sSixthFamily, sSixthEmail);
		userService.createUser(clientThree, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel clientFour = createSingleUser(sSeventhGiven, sSeventhFamily, sSeventhEmail);
		userService.createUser(clientFour, Permission.CLIENT, oeDefaultOrg.getId());
		
		// Create some consultant-client associations
		ConsultantClientsModel ccmOne = modelFactory.getNewConsultantClientsModel();
		ccmOne.setOrganizationId(oeDefaultOrg.getId());
		ccmOne.setConsultantId(consultantOne.getId());
		ccmOne.setClientId(clientTwo.getId());
		concliService.createOne(ccmOne);
		
		ConsultantClientsModel ccmTwo = modelFactory.getNewConsultantClientsModel();
		ccmTwo.setOrganizationId(oeDefaultOrg.getId());
		ccmTwo.setConsultantId(consultantOne.getId());
		ccmTwo.setClientId(clientThree.getId());
		concliService.createOne(ccmTwo);

		ConsultantClientsModel ccmThree = modelFactory.getNewConsultantClientsModel();
		ccmThree.setOrganizationId(oeDefaultOrg.getId());
		ccmThree.setConsultantId(consultantTwo.getId());
		ccmThree.setClientId(clientFour.getId());
		concliService.createOne(ccmThree);
		
		// Extract data from the database
		List<EntityUserModel> unconsultants = userService.getListOfUnassignedConsultants(oeDefaultOrg.getId());
		
		assertEquals(unconsultants.size(), 1);
		assertEquals(ueFirstUser.getId(), unconsultants.get(0).getId());

		List<EntityUserModel> unclients = userService.getListOfUnassignedClients(oeDefaultOrg.getId());
		
		assertEquals(unclients.size(), 1);
		assertEquals(clientOne.getId(), unclients.get(0).getId());
		
		// Extract data from the database
		List<EntityUserModel> consultants = userService.getListOfConsultantOfAClient(oeDefaultOrg.getId(), clientOne.getId());
		
		assertEquals(consultants.size(), 0);

		List<EntityUserModel> clients = userService.getListOfClientsOfAConsultant(oeDefaultOrg.getId(), ueFirstUser.getId());
		
		assertEquals(clients.size(), 0);
	}

	@Test
	public void testGetAssignments_Some()
	{
		// Client several clients and consultants
		userService.createUser(ueFirstUser, Permission.CONSULTANT, oeDefaultOrg.getId());
		
		UserModel consultantOne = createSingleUser(sSecondGiven, sSecondFamily, sSecondEmail);
		userService.createUser(consultantOne, Permission.CONSULTANT, oeDefaultOrg.getId());
		
		UserModel consultantTwo = createSingleUser(sThirdGiven, sThirdFamily, sThirdEmail);
		userService.createUser(consultantTwo, Permission.CONSULTANT, oeDefaultOrg.getId());
		
		UserModel clientOne = createSingleUser(sFourthGiven, sFourthFamily, sFourthEmail);
		userService.createUser(clientOne, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel clientTwo = createSingleUser(sFifthGiven, sFifthFamily, sFifthEmail);
		userService.createUser(clientTwo, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel clientThree = createSingleUser(sSixthGiven, sSixthFamily, sSixthEmail);
		userService.createUser(clientThree, Permission.CLIENT, oeDefaultOrg.getId());
		
		UserModel clientFour = createSingleUser(sSeventhGiven, sSeventhFamily, sSeventhEmail);
		userService.createUser(clientFour, Permission.CLIENT, oeDefaultOrg.getId());
		
		// Create some consultant-client associations
		ConsultantClientsModel ccmOne = modelFactory.getNewConsultantClientsModel();
		ccmOne.setOrganizationId(oeDefaultOrg.getId());
		ccmOne.setConsultantId(consultantOne.getId());
		ccmOne.setClientId(clientTwo.getId());
		concliService.createOne(ccmOne);
		
		ConsultantClientsModel ccmTwo = modelFactory.getNewConsultantClientsModel();
		ccmTwo.setOrganizationId(oeDefaultOrg.getId());
		ccmTwo.setConsultantId(consultantOne.getId());
		ccmTwo.setClientId(clientThree.getId());
		concliService.createOne(ccmTwo);

		ConsultantClientsModel ccmThree = modelFactory.getNewConsultantClientsModel();
		ccmThree.setOrganizationId(oeDefaultOrg.getId());
		ccmThree.setConsultantId(consultantTwo.getId());
		ccmThree.setClientId(clientFour.getId());
		concliService.createOne(ccmThree);
		
		// Extract data from the database
		List<EntityUserModel> consultants = userService.getListOfConsultantOfAClient(oeDefaultOrg.getId(), clientTwo.getId());
		
		assertEquals(consultants.size(), 1);
		assertEquals(consultantOne.getId(), consultants.get(0).getId());

		List<EntityUserModel> assignedOne = userService.getListOfClientsOfAConsultant(oeDefaultOrg.getId(), consultantOne.getId());
		
		assertEquals(assignedOne.size(), 2);

		List<EntityUserModel> assignedTwo = userService.getListOfClientsOfAConsultant(oeDefaultOrg.getId(), consultantTwo.getId());
		
		assertEquals(assignedTwo.size(), 1);
		assertEquals(clientFour.getId(), assignedTwo.get(0).getId());
	}
}
