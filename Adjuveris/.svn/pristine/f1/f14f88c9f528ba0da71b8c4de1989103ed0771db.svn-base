package com.linguaclassica.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.entity.InstUserPermissionEntity;
import com.linguaclassica.model.InstUserPermissionModel;
import com.linguaclassica.model.InstitutionModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.InstitutionService;
import com.linguaclassica.service.UserService;

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
public class InstUserPermissionTest extends AbstractTransactionalJUnit4SpringContextTests
{
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InstUserPermissionRepository iupRepository;
	
	@Autowired
	private InstitutionService instService;
	
	private String sFirstGiven = "Hannah";
	private String sFirstFamily = "Lector";
	private String sFirstEmail = "hannah.lector@uarcturus.edu";
	private String sFirstUsernum = "171234001";
	private String sFirstPassword = "HereToLearnMuch";
	
	private String sSecondGiven = "Splash";
	private String sSecondFamily = "Boots";
	private String sSecondEmail = "splash.n.boots@uarcturus.edu";
	private String sSecondUsernum = "171234002";
	private String sSecondPassword = "HereToHaveFun";
	
	private InstitutionModel imPrimaryInstitution = null;
	private InstitutionModel imSecondaryInstitution = null;
	private EntityUserModel umFirstUser = null;
	private EntityUserModel umSecondUser = null;

	private void createPrimaryInstitution()
	{
		imPrimaryInstitution = modelFactory.getNewInstitutionModel();
		imPrimaryInstitution.setAdminemail("frosty@uarcturus.edu");
		imPrimaryInstitution.setAdminfirstname("F. Ross");
		imPrimaryInstitution.setAdminlastname("Tyson");
		imPrimaryInstitution.setInstname("uArcturus test");
		imPrimaryInstitution.setPostaddress("1000 Main Drag, Arcturus, IL");
		imPrimaryInstitution.setPrimecontact("Charles Tanika");
		imPrimaryInstitution.setPrimeemail("registrar@uarcturus.edu");
		imPrimaryInstitution.setPrimephone("1-815-555-1000");
		instService.createInstitution(imPrimaryInstitution);
	}

	private void createSecondaryInstitution()
	{
		imSecondaryInstitution = modelFactory.getNewInstitutionModel();
		imSecondaryInstitution.setAdminemail("donna@belladonnac.edu");
		imSecondaryInstitution.setAdminfirstname("Donna");
		imSecondaryInstitution.setAdminlastname("Douglas");
		imSecondaryInstitution.setInstname("BellaDonnaC test");
		imSecondaryInstitution.setPostaddress("2000 Wildflower Way, Bella Donna, WA");
		imSecondaryInstitution.setPrimecontact("Jack Owen");
		imSecondaryInstitution.setPrimeemail("registrar@belladonnac.edu");
		imSecondaryInstitution.setPrimephone("1-825-555-2000");
		instService.createInstitution(imSecondaryInstitution);
	}
	
	private void createFirstUser()
	{
		umFirstUser = (EntityUserModel) modelFactory.getNewUserModel();
		umFirstUser.setFirstName(sFirstGiven);
		umFirstUser.setLastName(sFirstFamily);
		umFirstUser.setEmailAddress(sFirstEmail);
		umFirstUser.setStudentnumber(sFirstUsernum);
		umFirstUser.setPassword(sFirstPassword);
	}
	
	private void createSecondUser()
	{
		umSecondUser = (EntityUserModel) modelFactory.getNewUserModel();
		umSecondUser.setFirstName(sSecondGiven);
		umSecondUser.setLastName(sSecondFamily);
		umSecondUser.setEmailAddress(sSecondEmail);
		umSecondUser.setStudentnumber(sSecondUsernum);
		umSecondUser.setPassword(sSecondPassword);
	}

	@Before
	public void setUp() throws Exception
	{
		// Create a default institution for many of the tests
		createPrimaryInstitution();
		
		// Create a default user for many of the tests
		createFirstUser();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCreateOne_OK()
	{
		userService.createUser(umFirstUser, Permission.TEACHER, imPrimaryInstitution.getId());
		
		boolean bstud = iupRepository.isExisting(imPrimaryInstitution.getId(), umFirstUser.getId(), 2);
		
		assertTrue(bstud);
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testCreateOne_Same()
	{
		userService.createUser(umFirstUser, Permission.TEACHER, imPrimaryInstitution.getId());

		InstUserPermissionModel iup2 = modelFactory.getNewInstUserPermissionModel();
		iup2.setInstitutionId(imPrimaryInstitution.getId());
		iup2.setUserId(umFirstUser.getId());
		iup2.setPermissionId(userRepository.getPermissionModel(Permission.TEACHER).getId());
		iupRepository.createOne(iup2);
		
		// should not get here
		assertTrue(false);
	}

	@Test
	public void testCreateOne_Many()
	{
		// Create a user with two roles at one institution
		userService.createUser(umFirstUser, Permission.STUDENT, imPrimaryInstitution.getId());
		
		boolean a = iupRepository.areMultiple(umFirstUser.getId());
		
		assertFalse(a);

		InstUserPermissionModel iup2 = modelFactory.getNewInstUserPermissionModel();
		iup2.setInstitutionId(imPrimaryInstitution.getId());
		iup2.setUserId(umFirstUser.getId());
		iup2.setPermissionId(userRepository.getPermissionModel(Permission.TA).getId());
		iupRepository.createOne(iup2);
		
		assertTrue(iup2.getId() > 0);
		
		boolean b = iupRepository.areMultiple(umFirstUser.getId());
		
		assertTrue(b);
		
		createSecondaryInstitution();

		// Create another user with one role at one institution
		createSecondUser();
		userService.createUser(umSecondUser, Permission.STUDENT, imSecondaryInstitution.getId());
		
		boolean c = iupRepository.areMultiple(umSecondUser.getId());
		
		assertFalse(c);

		// Add two roles for the first user at the second institution
		InstUserPermissionModel iup4 = modelFactory.getNewInstUserPermissionModel();
		iup4.setInstitutionId(imSecondaryInstitution.getId());
		iup4.setUserId(umFirstUser.getId());
		iup4.setPermissionId(userRepository.getPermissionModel(Permission.TEACHER).getId());
		iupRepository.createOne(iup4);
		
		assertTrue(iup4.getId() > 0);

		InstUserPermissionModel iup5 = modelFactory.getNewInstUserPermissionModel();
		iup5.setInstitutionId(imSecondaryInstitution.getId());
		iup5.setUserId(umFirstUser.getId());
		iup5.setPermissionId(userRepository.getPermissionModel(Permission.INSTITUTION_ADMIN).getId());
		iupRepository.createOne(iup5);
		
		assertTrue(iup5.getId() > 0);
		
		List<InstUserPermissionEntity> items1 = userService.getUserItems(umFirstUser.getId());
		
		assertTrue(4 == items1.size());
		
		List<InstUserPermissionEntity> items2 = userService.getUserItems(umSecondUser.getId());
		
		assertTrue(1 == items2.size());
		assertTrue(items2.get(0).getInstitutionId() == imSecondaryInstitution.getId());
	}

	@Test
	public void testAreMultiple_None()
	{
		userService.createUser(umFirstUser, Permission.STUDENT, imPrimaryInstitution.getId());
		
		boolean b = iupRepository.areMultiple(1000000 + umFirstUser.getId());
		
		assertFalse(b);
	}

	@Test
	public void testGetItem_None()
	{
		userService.createUser(umFirstUser, Permission.STUDENT, imPrimaryInstitution.getId());

		List<InstUserPermissionEntity> iups = userService.getUserItems(umFirstUser.getId());
		
		assertTrue(iups.size() > 0);
		
		InstUserPermissionEntity item = userService.getMultiPermissionItem(1000000 + iups.get(0).getId());
		
		assertNull(item);
	}

	@Test
	public void testGetItem_OK()
	{
		userService.createUser(umFirstUser, Permission.STUDENT, imPrimaryInstitution.getId());

		List<InstUserPermissionEntity> iups = userService.getUserItems(umFirstUser.getId());
		
		assertTrue(iups.size() > 0);
		
		InstUserPermissionEntity item = userService.getMultiPermissionItem(iups.get(0).getId());
		
		assertTrue(item.getInstitutionId() == imPrimaryInstitution.getId());
		assertTrue(item.getUserId() == umFirstUser.getId());
	}

	@Test
	public void testGetUserItems_None()
	{
		userService.createUser(umFirstUser, Permission.STUDENT, imPrimaryInstitution.getId());
		
		List<InstUserPermissionEntity> items = userService.getUserItems(1000000 + umFirstUser.getId());
		
		assertTrue(0 == items.size());
	}

	@Test
	public void TestIsExisting_None()
	{
		userService.createUser(umFirstUser, Permission.STUDENT, imPrimaryInstitution.getId());
		
		boolean binst = iupRepository.isExisting(1000000 + imPrimaryInstitution.getId(), umFirstUser.getId(), 1);
		
		assertFalse(binst);
		
		boolean buser = iupRepository.isExisting(imPrimaryInstitution.getId(), 1000000 + umFirstUser.getId(), 1);
		
		assertFalse(buser);
		
		boolean bperm = iupRepository.isExisting(imPrimaryInstitution.getId(), umFirstUser.getId(), 10 + 1);
		
		assertFalse(bperm);
	}

	@Test
	public void TestIsExisting_OK()
	{
		userService.createUser(umFirstUser, Permission.STUDENT, imPrimaryInstitution.getId());
		
		// Create another user with one role at one institution
		createSecondUser();
		userService.createUser(umSecondUser, Permission.TEACHER, imPrimaryInstitution.getId());
		
		boolean bstud = iupRepository.isExisting(imPrimaryInstitution.getId(), umFirstUser.getId(), 1);
		
		assertTrue(bstud);
		
		boolean bbteach = iupRepository.isExisting(imPrimaryInstitution.getId(), umSecondUser.getId(), 2);
		
		assertTrue(bbteach);
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void testDeleteOne_BadInst()
	{
		// Create a user
		userService.createUser(umFirstUser, Permission.TEACHER, imPrimaryInstitution.getId());
		
		// Try to delete an item that does not match
		iupRepository.deleteOne(1000000 + imPrimaryInstitution.getId(), umFirstUser.getId(), 
				userRepository.getPermissionModel(Permission.TEACHER).getId());
		
		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void testDeleteOne_BadUser()
	{
		// Create a user
		userService.createUser(umFirstUser, Permission.TEACHER, imPrimaryInstitution.getId());
		
		// Try to delete an item that does not match
		iupRepository.deleteOne(imPrimaryInstitution.getId(), 1000000 + umFirstUser.getId(), 
				userRepository.getPermissionModel(Permission.TEACHER).getId());
		
		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void testDeleteOne_BadPermission()
	{
		// Create a user
		userService.createUser(umFirstUser, Permission.TEACHER, imPrimaryInstitution.getId());
		
		// Try to delete an item that does not match
		iupRepository.deleteOne(imPrimaryInstitution.getId(), umFirstUser.getId(), 
				userRepository.getPermissionModel(Permission.ADMINISTRATOR).getId());
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testDeleteOne_OK()
	{
		// Create a user with two roles at one institution
		userService.createUser(umFirstUser, Permission.STUDENT, imPrimaryInstitution.getId());

		InstUserPermissionModel iup2 = modelFactory.getNewInstUserPermissionModel();
		iup2.setInstitutionId(imPrimaryInstitution.getId());
		iup2.setUserId(umFirstUser.getId());
		iup2.setPermissionId(userRepository.getPermissionModel(Permission.TA).getId());
		iupRepository.createOne(iup2);
		
		assertTrue(iup2.getId() > 0);
		
		createSecondaryInstitution();

		// Create another user with one role at one institution
		createSecondUser();
		userService.createUser(umSecondUser, Permission.TEACHER, imSecondaryInstitution.getId());

		InstUserPermissionModel iup5 = modelFactory.getNewInstUserPermissionModel();
		iup5.setInstitutionId(imSecondaryInstitution.getId());
		iup5.setUserId(umFirstUser.getId());
		iup5.setPermissionId(userRepository.getPermissionModel(Permission.INSTITUTION_ADMIN).getId());
		iupRepository.createOne(iup5);
		
		assertTrue(iup5.getId() > 0);
		
		// Delete all of the items
		iupRepository.deleteOne(imPrimaryInstitution.getId(), umFirstUser.getId(), 
				userRepository.getPermissionModel(Permission.STUDENT).getId());
		iupRepository.deleteOne(imPrimaryInstitution.getId(), umFirstUser.getId(), 
				userRepository.getPermissionModel(Permission.TA).getId());
		iupRepository.deleteOne(imSecondaryInstitution.getId(), umSecondUser.getId(), 
				userRepository.getPermissionModel(Permission.TEACHER).getId());
		iupRepository.deleteOne(imSecondaryInstitution.getId(), umFirstUser.getId(), 
				userRepository.getPermissionModel(Permission.INSTITUTION_ADMIN).getId());
		
		// There should be no items remaining
		List<InstUserPermissionEntity> items1 = userService.getUserItems(umFirstUser.getId());
		
		assertTrue(0 == items1.size());
		
		List<InstUserPermissionEntity> items2 = userService.getUserItems(umSecondUser.getId());
		
		assertTrue(0 == items2.size());
	}
}
