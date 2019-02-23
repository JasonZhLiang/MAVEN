package com.linguaclassica.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doThrow;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.linguaclassica.access.LoginHelper;
import com.linguaclassica.entity.EntityModelFactory;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.repository.UserRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceMockTest
{
	@Spy EntityModelFactory modelFactory = new EntityModelFactory();
	@Mock UserRepository userRepository = new UserRepository();
	@InjectMocks UserService userService;
	
	String defEmail = "guy.family@junit.edu";
	String defPassword = "LetMeD0Things";

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}
	
	EntityUserModel createStudentUser()
	{
		EntityUserModel entityS = (EntityUserModel) modelFactory.getNewUserModel();
		entityS.setFirstName("Brian");
		entityS.setLastName("Bryant");
		entityS.setEmailAddress("b.bryant@uSomeTest.edu");
		entityS.setStudentnumber("173216842");
		try
		{
			// Swap the clear text password with the encoded password.
			entityS.setPassword(LoginHelper.encodePassword("LetMeLearn"));
		}
		catch (NoSuchAlgorithmException e)
		{
		}
		
		entityS.setId(12);
		return entityS;
	}

	@Test
	public void testCreateUserOK()
	{
		// Create an institution administrator
		EntityUserModel entityIA = (EntityUserModel) modelFactory.getNewUserModel();
		entityIA.setFirstName("Primus");
		entityIA.setLastName("Registrarus");
		entityIA.setEmailAddress("primer@uSomeTest.edu");
		entityIA.setStudentnumber("171234567");
		try
		{
			// Swap the clear text password with the encoded password.
			entityIA.setPassword(LoginHelper.encodePassword("ImTheBossHere"));
		}
		catch (NoSuchAlgorithmException e)
		{
		}

		userService.createUser(entityIA, Permission.INSTITUTION_ADMIN, 0);
		
		// Create a teacher
		EntityUserModel entityT = (EntityUserModel) modelFactory.getNewUserModel();
		entityT.setFirstName("Miss");
		entityT.setLastName("Blabalot");
		entityT.setEmailAddress("teacher@uSomeTest.edu");
		entityT.setStudentnumber("171248160");
		try
		{
			// Swap the clear text password with the encoded password.
			entityT.setPassword(LoginHelper.encodePassword("ListenToMe"));
		}
		catch (NoSuchAlgorithmException e)
		{
		}

		userService.createUser(entityT, Permission.TEACHER, 0);
		
		// Create a teaching assistant
		EntityUserModel entityTA = (EntityUserModel) modelFactory.getNewUserModel();
		entityTA.setFirstName("Colin");
		entityTA.setLastName("Keener");
		entityTA.setEmailAddress("ckeener@uSomeTest.edu");
		entityTA.setStudentnumber("171223344");
		try
		{
			// Swap the clear text password with the encoded password.
			entityTA.setPassword(LoginHelper.encodePassword("NotAgain"));
		}
		catch (NoSuchAlgorithmException e)
		{
		}

		userService.createUser(entityTA, Permission.TA, 0);
		
		// Create a student
		EntityUserModel entityS = createStudentUser();
		userService.createUser(entityS, Permission.STUDENT, 0);
	}

	@Test(expected=EntityAlreadyExistsException.class)
	public void testCreateUserExists()
	{
		// Configure the repository
		doThrow(new EntityAlreadyExistsException()).when(userRepository).createUser((EntityUserModel) any(), (Permission) any(), anyInt());

		// Create a student
		EntityUserModel entityS = createStudentUser();
		userService.createUser(entityS, Permission.STUDENT, 0);
	}

	@Test(expected=ServiceException.class)
	public void testCreateUserFails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(userRepository).createUser((EntityUserModel) any(), (Permission) any(), anyInt());

		// Create a student
		EntityUserModel entityS = createStudentUser();
		userService.createUser(entityS, Permission.STUDENT, 0);
	}

	@Test
	public void testAuthenticateUser()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testAuthorizeUser()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testFindUserByEmailAddress_OK()
	{
		@SuppressWarnings("unused")
		UserModel model = userService.findUserByEmailAddress(defEmail);
	}

	@Test(expected=UnknownEntityException.class)
	public void testFindUserByEmailAddress_None()
	{
		// Configure the repository
		doThrow(new NoResultException()).when(userRepository).findUserByEmailAddress(anyString());

		@SuppressWarnings("unused")
		UserModel model = userService.findUserByEmailAddress(defEmail);
	}

	@Test(expected=ServiceException.class)
	public void testFindUserByEmailAddress_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(userRepository).findUserByEmailAddress(anyString());

		@SuppressWarnings("unused")
		UserModel model = userService.findUserByEmailAddress(defEmail);
	}

	@Test
	public void testFindUserById_OK()
	{
		@SuppressWarnings("unused")
		UserModel model = userService.findUserById(5);
	}

	@Test(expected=NoResultException.class)
	public void testFindUserById_None()
	{
		// Configure the repository
		doThrow(new NoResultException()).when(userRepository).findUserByEmailAddress(anyString());

		@SuppressWarnings("unused")
		UserModel model = userService.findUserById(1000005);
	}

	@Test(expected=ServiceException.class)
	public void testFindUserById_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(userRepository).findUserByEmailAddress(anyString());

		@SuppressWarnings("unused")
		UserModel model = userService.findUserById(100000005);
	}

	@Test
	public void testUpdatePassword_OK()
	{
		userService.updatePassword(5, defPassword);
	}

	@Test(expected=NoResultException.class)
	public void testUpdatePassword_None()
	{
		// Configure the repository
		doThrow(new NoResultException()).when(userRepository).updatePassword(anyInt(), anyString());

		userService.updatePassword(5, defPassword);
	}

	@Test
	public void testUpdateUser_OK()
	{
		EntityUserModel entity = createStudentUser();
		userService.updateUser(entity);
	}

	@Test(expected=NoResultException.class)
	public void testUpdateUser_None()
	{
		// Configure the repository
		doThrow(new NoResultException()).when(userRepository).updateUser((EntityUserModel) any());

		EntityUserModel entity = createStudentUser();
		userService.updateUser(entity);
	}

	@Test(expected=EntityAlreadyExistsException.class)
	public void testUpdateUser_Duplicate()
	{
		// Configure the repository
		doThrow(new EntityAlreadyExistsException()).when(userRepository).updateUser((EntityUserModel) any());

		EntityUserModel entity = createStudentUser();
		userService.updateUser(entity);
	}

	@Test
	public void testUpdateSubsStatus()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetListOfEmails()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetListOfUsersByEmail()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetListOfStudentsByIdList_OK()
	{
		List<Integer> listi = new ArrayList<Integer>();
		listi.add(5);
		listi.add(12);
		listi.add(124);
		@SuppressWarnings("unused")
		List<EntityUserModel> entities = userService.getListOfStudentsByIdList(listi);
	}

	@Test(expected=NoResultException.class)
	public void testGetListOfStudentsByIdList_None()
	{
		// Configure the repository
		doThrow(new NoResultException()).when(userRepository).getListOfStudentsByIdList(anyListOf(Integer.class));

		List<Integer> listi = new ArrayList<Integer>();
		listi.add(5);
		listi.add(12);
		listi.add(124);
		@SuppressWarnings("unused")
		List<EntityUserModel> entities = userService.getListOfStudentsByIdList(listi);
	}

	@Test(expected=ServiceException.class)
	public void testGetListOfStudentsByIdList_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(userRepository).getListOfStudentsByIdList(anyListOf(Integer.class));

		List<Integer> listi = new ArrayList<Integer>();
		listi.add(5);
		listi.add(12);
		listi.add(124);
		@SuppressWarnings("unused")
		List<EntityUserModel> entities = userService.getListOfStudentsByIdList(listi);
	}

	@Test
	public void testGetNumberOfUsersWithoutGroup()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testFindInstIdByUserId()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testCheckDuplicates_OK()
	{
		@SuppressWarnings("unused")
		List<EntityUserModel> entities = userService.checkDuplicates(defEmail);
	}
}
