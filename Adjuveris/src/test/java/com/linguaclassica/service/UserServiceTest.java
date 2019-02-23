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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.access.LoginHelper;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.entity.InstUserPermissionEntity;
import com.linguaclassica.model.InstitutionModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.model.UserModel;
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
 * Data will not remain in the alphplus tables.
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
	private InstitutionService instService;
	
	// Permission IDs
	private Integer permidS = 1;
	private Integer permidT = 2;
	private Integer permidTA = 3;
	private Integer permidIA = 4;
	private Integer permidA= 5;
	
	private String sFirstGiven = "Hannah";
	private String sFirstFamily = "Lector";
	private String sFirstEmail = "hannah.lector@uarcturus.edu";
	private String sFirstUsernum = "171234001";
	private String sFirstPassword = "HereToLearnMuch";
	
	private String sForgotPassword = "ab34g6h89k";
	
	private String sSecondGiven = "Splash";
	private String sSecondFamily = "Boots";
	private String sSecondEmail = "splash.n.boots@uarcturus.edu";
	private String sSecondUsernum = "171234002";
	private String sSecondPassword = "HereToHaveFun";
	
	private String sThirdGiven = "Steward";
	private String sThirdFamily = "Goodyear";
	private String sThirdEmail = "stu.goodyear@uarcturus.edu";
	private String sThirdUsernum = "171234003";
	private String sThirdPassword = "HeresToGoodStew";

	private InstitutionModel imDefaultInstitution = null;
	private InstitutionModel imAnotherInstitution = null;
	private EntityUserModel umDefaultUser = null;
	private EntityUserModel umSecondUser = null;
	private EntityUserModel umNextUser = null;

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
	
	private void createAnotherInstitution()
	{
		imAnotherInstitution = modelFactory.getNewInstitutionModel();
		imAnotherInstitution.setAdminemail("dickjr@ubackwater.edu");
		imAnotherInstitution.setAdminfirstname("Dick");
		imAnotherInstitution.setAdminlastname("Richardson");
		imAnotherInstitution.setInstname("uBackwater");
		imAnotherInstitution.setPostaddress("100 Back Street, Dacron. OH");
		imAnotherInstitution.setPrimecontact("Lars Larson");
		imAnotherInstitution.setPrimeemail("larlar@ubackwater.edu");
		imAnotherInstitution.setPrimephone("1-816-555-2002");
		instService.createInstitution(imAnotherInstitution);
	}
	
	private void createDefaultUser()
	{
		umDefaultUser = (EntityUserModel) modelFactory.getNewUserModel();
		umDefaultUser.setFirstName(sFirstGiven);
		umDefaultUser.setLastName(sFirstFamily);
		umDefaultUser.setEmailAddress(sFirstEmail);
		umDefaultUser.setStudentnumber(sFirstUsernum);
		String encrypted = "TooLongForValidPasswordWithBad(@)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sFirstPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		umDefaultUser.setPassword(encrypted);
	}
	
	private void createSecondUser()
	{
		umSecondUser = (EntityUserModel) modelFactory.getNewUserModel();
		umSecondUser.setFirstName(sSecondGiven);
		umSecondUser.setLastName(sSecondFamily);
		umSecondUser.setEmailAddress(sSecondEmail);
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
	
	private void createThirdUser()
	{
		umNextUser = (EntityUserModel) modelFactory.getNewUserModel();
		umNextUser.setFirstName(sThirdGiven);
		umNextUser.setLastName(sThirdFamily);
		umNextUser.setEmailAddress(sThirdEmail);
		umNextUser.setStudentnumber(sThirdUsernum);
		String encrypted = "TooLongForValidPasswordWithBad(#)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sThirdPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		umNextUser.setPassword(encrypted);
	}
	
	@Before
	public void setUp() throws Exception
	{
		// Create a default institution for many of the tests
		createDefaultInstitution();
		
		// Create a default user for many of the tests
		createDefaultUser();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCreateUser_OK()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
	}

	@Test(expected=ServiceException.class)
	public void testCreateUser_Same()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// try again
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testCreateUser_BadInst()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, 0);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testCreateUser_Fails()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// try again with different user but same email
		EntityUserModel umTypoUser = (EntityUserModel) modelFactory.getNewUserModel();
		umTypoUser.setFirstName(sSecondGiven);
		umTypoUser.setLastName(sSecondFamily);
		umTypoUser.setEmailAddress(sFirstEmail);  // already in use
		umTypoUser.setStudentnumber(sSecondUsernum);
		String encrypted = "TooLongForValidPasswordWithBad(@)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sSecondPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		umTypoUser.setPassword(encrypted);
		userService.createUser(umTypoUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testAuthenticateUser_OK()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		UserModel umResult = null;
		try
		{
			umResult = userService.authenticateUser(sFirstEmail, umDefaultUser.getPassword());
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
		}
		catch (CircumstanceException e)
		{
			// Should not reach here
			assertTrue(false);
		}
		
		assertNotNull(umResult);
		assertTrue(0 == umResult.getFirstName().compareTo(sFirstGiven));
		assertTrue(0 == umResult.getLastName().compareTo(sFirstFamily));
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
		}
		catch (CircumstanceException e)
		{
			// Should not reach here
			assertTrue(false);
		}
		
		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=ServiceException.class)
	public void testAuthenticateUser_BadPassword()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
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
		}
		catch (CircumstanceException e)
		{
			// Should not reach here
			assertTrue(false);
		}
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testFindUserByEmailAddress_OK()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		UserModel umResult = userService.findUserByEmailAddress(sFirstEmail);
		
		assertNotNull(umResult);
		assertTrue(0 == umResult.getFirstName().compareTo(sFirstGiven));
		assertTrue(0 == umResult.getLastName().compareTo(sFirstFamily));
	}

	@Test(expected=ServiceException.class)
	public void testfindUserByEmailAddress_Fails()
	{
		@SuppressWarnings("unused")
		UserModel umResult = userService.findUserByEmailAddress(sSecondEmail);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testFindUserById_OK()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		UserModel umResult = userService.findUserById(umDefaultUser.getId());
		
		assertNotNull(umResult);
		assertTrue(0 == umResult.getFirstName().compareTo(sFirstGiven));
		assertTrue(0 == umResult.getLastName().compareTo(sFirstFamily));
	}

	@Test(expected=NoResultException.class)
	public void testFindUserById_Fails()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		@SuppressWarnings("unused")
		UserModel umResult = userService.findUserById(1000000 + umDefaultUser.getId());
		
		// Should not reach here
		assertTrue(false);
	}

	/**
	 * Test calls to addAuthorization (InstUserPermissionRepository.createOne)
	 */
	@Test
	public void testAddAuthorization_OK()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		List<InstUserPermissionEntity> list = userService.getUserItems(umDefaultUser.getId());
		
		assertTrue(1 == list.size());
	}

	/**
	 * Test calls to addAuthorization (InstUserPermissionRepository.createOne)
	 */
	@Test
	public void testAddAuthorization_Same()
	{
		userService.createUser(umDefaultUser, Permission.INSTITUTION_ADMIN, imDefaultInstitution.getId());
		
		// Add a second of the same permission
		userService.addAuthorization(imDefaultInstitution.getId(), umDefaultUser, permidIA);
		
		List<InstUserPermissionEntity> list = userService.getUserItems(umDefaultUser.getId());
		
		assertTrue(1 == list.size());
		
		boolean b = userService.areMultiplePermissions(umDefaultUser);
		
		assertFalse(b);
	}

	/**
	 * Test calls to addAuthorization (InstUserPermissionRepository.createOne)
	 */
	@Test
	public void testAddAuthorization_Many()
	{
		// Create a user with two roles at one institution
		userService.createUser(umDefaultUser, Permission.STUDENT, imDefaultInstitution.getId());
		
		boolean a = userService.areMultiplePermissions(umDefaultUser);
		
		assertFalse(a);
		
		userService.addAuthorization(imDefaultInstitution.getId(), umDefaultUser, permidTA);
		
		boolean b = userService.areMultiplePermissions(umDefaultUser);
		
		assertTrue(b);
		
		createAnotherInstitution();

		// Create another user with one role at one institution
		createSecondUser();
		userService.createUser(umSecondUser, Permission.STUDENT, imAnotherInstitution.getId());
		
		boolean c = userService.areMultiplePermissions(umSecondUser);
		
		assertFalse(c);
		
		// Add two roles for the first user at the second institution
		userService.addAuthorization(imAnotherInstitution.getId(), umDefaultUser, permidT);
		
		userService.addAuthorization(imAnotherInstitution.getId(), umDefaultUser, permidIA);
		
		List<InstUserPermissionEntity> items1 = userService.getUserItems(umDefaultUser.getId());
		
		assertTrue(4 == items1.size());
		
		List<InstUserPermissionEntity> items2 = userService.getUserItems(umSecondUser.getId());
		
		assertTrue(1 == items2.size());
		assertEquals(items2.get(0).getInstitutionId(), imAnotherInstitution.getId());
	}
	
	@Test
	public void testGetItem_None()
	{
		userService.createUser(umDefaultUser, Permission.STUDENT, imDefaultInstitution.getId());
		
		List<InstUserPermissionEntity> items1 = userService.getUserItems(umDefaultUser.getId());
		
		assertTrue(1 == items1.size());
		
		InstUserPermissionEntity iupOne = userService.getMultiPermissionItem(1000000 + items1.get(0).getId());
		
		assertNull(iupOne);
	}
	
	@Test
	public void testGetItem_One()
	{
		userService.createUser(umDefaultUser, Permission.STUDENT, imDefaultInstitution.getId());
		
		List<InstUserPermissionEntity> items = userService.getUserItems(umDefaultUser.getId());
		
		assertTrue(1 == items.size());
		
		InstUserPermissionEntity iupOne = userService.getMultiPermissionItem(items.get(0).getId());
		
		assertNotNull(iupOne);
		assertTrue(items.get(0).getId().equals(iupOne.getId()));
	}
	
	@Test
	public void testGetUserItems_None()
	{
		userService.createUser(umDefaultUser, Permission.STUDENT, imDefaultInstitution.getId());
		
		List<InstUserPermissionEntity> items = userService.getUserItems(1000000 + umDefaultUser.getId());
		
		assertTrue(0 == items.size());
	}
	
	@Test
	public void testGetUsersByPermission_BadInst()
	{
		List<EntityUserModel> items = userService.getUsersByPermission(1000000 + imDefaultInstitution.getId(), Permission.TEACHER);
		
		assertTrue(0 == items.size());
	}
	
	@Test
	public void testGetUsersByPermission_Admin()
	{
		List<EntityUserModel> items = userService.getUsersByPermission(imDefaultInstitution.getId(), Permission.ADMINISTRATOR);
		
		assertTrue(0 == items.size());
	}
	
	@Test
	public void testGetUsersByPermission()
	{
		// Create a user with two roles at one institution
		userService.createUser(umDefaultUser, Permission.STUDENT, imDefaultInstitution.getId());
		
		List<EntityUserModel> student1 = userService.getUsersByPermission(imDefaultInstitution.getId(), Permission.STUDENT);
		List<EntityUserModel> ta1 = userService.getUsersByPermission(imDefaultInstitution.getId(), Permission.TA);
		
		assertTrue(1 == student1.size());
		assertTrue(0 == ta1.size());
		
		userService.addAuthorization(imDefaultInstitution.getId(), umDefaultUser, permidTA);
		
		ta1 = userService.getUsersByPermission(imDefaultInstitution.getId(), Permission.TA);
		
		assertTrue(1 == ta1.size());
		
		createAnotherInstitution();

		// Create another user with one role at two institutions
		createSecondUser();
		userService.createUser(umSecondUser, Permission.STUDENT, imAnotherInstitution.getId());
		
		userService.addAuthorization(imDefaultInstitution.getId(), umSecondUser, permidS);
		
		student1 = userService.getUsersByPermission(imDefaultInstitution.getId(), Permission.STUDENT);
		List<EntityUserModel> student2 = userService.getUsersByPermission(imAnotherInstitution.getId(), Permission.STUDENT);
		
		assertTrue(2 == student1.size());
		assertTrue(1 == student2.size());
		
		// Add two roles for the next user at the different institutions
		createThirdUser();
		userService.createUser(umNextUser, Permission.STUDENT, imDefaultInstitution.getId());
		
		userService.addAuthorization(imAnotherInstitution.getId(), umNextUser, permidIA);

		student1 = userService.getUsersByPermission(imDefaultInstitution.getId(), Permission.STUDENT);
		List<EntityUserModel> instad2 = userService.getUsersByPermission(imAnotherInstitution.getId(), Permission.INSTITUTION_ADMIN);
		
		assertTrue(3 == student1.size());
		assertTrue(1 == instad2.size());
	}

	@Test
	public void testUpdatePassword_OK()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		String oldpassword = umDefaultUser.getPassword();
		
		String encrypted = "TooLongForValidPasswordWithBad(@)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sForgotPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		userService.updatePassword(umDefaultUser.getId(), encrypted);
		
		UserModel umFetch = userService.findUserById(umDefaultUser.getId());
		
		assertNotNull(umFetch);
		assertTrue(0 == umDefaultUser.getEmailAddress().compareTo(umFetch.getEmailAddress()));
		assertTrue(0 == umFetch.getPassword().compareTo(encrypted));
		assertTrue(0 != umFetch.getPassword().compareTo(oldpassword));
	}

	@Test(expected=NullPointerException.class)
	public void testUpdatePassword_Fails()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		String encrypted = "TooLongForValidPasswordWithBad(@)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sForgotPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		userService.updatePassword(1000000 + umDefaultUser.getId(), encrypted);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testGetListOfStudentsByIdList_OK()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());

		// create another user
		EntityUserModel umAnotherUser = (EntityUserModel) modelFactory.getNewUserModel();
		umAnotherUser.setFirstName(sSecondGiven);
		umAnotherUser.setLastName(sSecondFamily);
		umAnotherUser.setEmailAddress(sSecondEmail);
		umAnotherUser.setStudentnumber(sSecondUsernum);
		String encrypted = "TooLongForValidPasswordWithBad(@)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sSecondPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		umAnotherUser.setPassword(encrypted);
		userService.createUser(umAnotherUser, Permission.STUDENT, imDefaultInstitution.getId());

		// create another user
		/*
		EntityUserModel umNextUser = (EntityUserModel) modelFactory.getNewUserModel();
		umNextUser.setFirstName(sThirdGiven);
		umNextUser.setLastName(sThirdFamily);
		umNextUser.setEmailAddress(sThirdEmail);
		umNextUser.setStudentnumber(sThirdUsernum);
		encrypted = "TooLongForValidPasswordWithBad(#)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sThirdPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		umNextUser.setPassword(encrypted);
		*/
		createThirdUser();
		userService.createUser(umNextUser, Permission.TA, imDefaultInstitution.getId());
		
		// Create a list of IDs
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(umDefaultUser.getId());
		idList.add(umAnotherUser.getId());
		idList.add(umNextUser.getId());
		
		// Retrieve the entities
		List<EntityUserModel> entityList = userService.getListOfStudentsByIdList(idList);
		
		assertNotNull(entityList);
		assertTrue(entityList.size() == 3);
		
		// Check that the entities were retrieved
		boolean bDefault = false;
		boolean bAnother = false;
		boolean bNext = false;
		for (int index = 0; index < entityList.size(); index++)
		{
			EntityUserModel entity = entityList.get(index);
			if (entity.getId() == umDefaultUser.getId())
			{
				bDefault = true;
			}
			else if (entity.getId() == umAnotherUser.getId())
			{
				bAnother = true;
			}
			else if (entity.getId() == umNextUser.getId())
			{
				bNext = true;
			}
		}
		
		assertTrue(bDefault);
		assertTrue(bAnother);
		assertTrue(bNext);
	}

	@Test(expected=NullPointerException.class)
	public void testGetListOfStudentsByIdList_Null()
	{
		List<Integer> emptyList = null;
		
		@SuppressWarnings("unused")
		List<EntityUserModel> entityList = userService.getListOfStudentsByIdList(emptyList);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testGetListOfStudentsByIdList_None()
	{
		List<Integer> emptyList = new ArrayList<Integer>();
		
		List<EntityUserModel> entityList = userService.getListOfStudentsByIdList(emptyList);
		
		assertNotNull(entityList);
		assertTrue(entityList.size() == 0);
	}

	@Test
	public void testGetListOfStudentsByIdList_Fails()
	{
		userService.createUser(umDefaultUser, Permission.STUDENT, imDefaultInstitution.getId());
		
		// Add invalid users
		List<Integer> badList = new ArrayList<Integer>();
		badList.add(1000000 + umDefaultUser.getId());
		badList.add(1000001 + umDefaultUser.getId());
		
		List<EntityUserModel> entityList = userService.getListOfStudentsByIdList(badList);
		
		assertNotNull(entityList);
		assertTrue(entityList.size() == 0);
	}

	@Test
	public void testCheckDuplicates_OK()
	{
		userService.createUser(umDefaultUser, Permission.STUDENT, imDefaultInstitution.getId());
		
		List<EntityUserModel> entityList = userService.checkDuplicates(sFirstEmail);
		
		assertNotNull(entityList);
		assertTrue(umDefaultUser.getId() == entityList.get(0).getId());
	}

	@Test
	public void testCheckDuplicates_None()
	{
		userService.createUser(umDefaultUser, Permission.STUDENT, imDefaultInstitution.getId());
		
		List<EntityUserModel> entityList = userService.checkDuplicates(sSecondEmail);
		
		assertNotNull(entityList);
		assertTrue(entityList.size() == 0);
	}

	@Test
	public void testUsingEmail_True()
	{
		userService.createUser(umDefaultUser, Permission.STUDENT, imDefaultInstitution.getId());
		
		boolean bResult = userService.usingEmail(sFirstEmail);
		
		assertTrue(bResult);
	}

	@Test
	public void testUsingEmail_False()
	{
		userService.createUser(umDefaultUser, Permission.STUDENT, imDefaultInstitution.getId());
		
		boolean bResult = userService.usingEmail(sSecondEmail);
		
		assertFalse(bResult);
	}
}
