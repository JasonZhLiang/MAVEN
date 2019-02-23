package com.linguaclassica.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.linguaclassica.access.LoginHelper;
import com.linguaclassica.entity.EntityInstitutionModel;
import com.linguaclassica.entity.EntityModelFactory;
import com.linguaclassica.entity.EntityPermissionModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.InstitutionModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.repository.InstUserPermissionRepository;
import com.linguaclassica.repository.InstitutionRepository;
import com.linguaclassica.repository.UserRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@RunWith(MockitoJUnitRunner.class)
public class InstitutionServiceMockTest
{
	@Spy EntityModelFactory modelFactory = new EntityModelFactory();
	@Mock InstitutionRepository instRepository = new InstitutionRepository();
	@InjectMocks InstitutionService instService;
	@Mock UserRepository userRepository = new UserRepository();
	@Mock InstUserPermissionRepository iupRepository = new InstUserPermissionRepository();
	

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}
	
	EntityUserModel createPrimeUser()
	{
		String email = "primer@uSomeTest.edu";
		
		// Create an institution administrator
		EntityUserModel entityIA = (EntityUserModel) modelFactory.getNewUserModel();
		entityIA.setFirstName("Primus");
		entityIA.setLastName("Registrarus");
		entityIA.setEmailAddress(email);
		entityIA.setStudentnumber("171234567");
		try
		{
			// Swap the clear text password with the encoded password.
			entityIA.setPassword(LoginHelper.encodePassword("ImTheBossHere"));
		}
		catch (NoSuchAlgorithmException e)
		{
		}
		
		entityIA.setId(8);   // fudge
		return entityIA;

	}
	
	EntityInstitutionModel createInstitution()
	{
		EntityInstitutionModel entity = (EntityInstitutionModel) modelFactory.getNewInstitutionModel();
		entity.setInstname("University of Some Test");
		entity.setPostaddress("1000 Mayne Drag, Dacron, OH");
		entity.setPrimecontact("Registrar Office");
		entity.setPrimephone("613-555-1000");
		entity.setPrimeemail("registrar@usometest.edu");
		entity.setAdminfirstname("Jane");
		entity.setAdminlastname("Hathaway");
		entity.setAdminemail("MissJane@usometest.edu");
		
		// Cannot set ID p
		return entity;
	}
	
	EntityPermissionModel fakeInstPermission()
	{
		EntityPermissionModel pe = (EntityPermissionModel) modelFactory.getNewPermissionModel();
		pe.setPermission(Permission.INSTITUTION_ADMIN);
		pe.setId(4);
		return pe;
	}

	@Test
	public void testCreateInstitutionOK()
	{
		EntityInstitutionModel entity = createInstitution();

		@SuppressWarnings("unused")
		int id = instService.createInstitution(entity);
	}

	@Test(expected=EntityAlreadyExistsException.class)
	public void testCreateInstitutionExists()
	{
		// Configure the repository
		doThrow(new EntityExistsException()).when(instRepository).createInstitution((EntityInstitutionModel) any());

		EntityInstitutionModel entity = createInstitution();

		@SuppressWarnings("unused")
		int id = instService.createInstitution(entity);
	}

	@Test(expected=ServiceException.class)
	public void testCreateInstitutionFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(instRepository).createInstitution((EntityInstitutionModel) any());

		EntityInstitutionModel entity = createInstitution();

		@SuppressWarnings("unused")
		int id = instService.createInstitution(entity);
	}

	@Test
	public void testCreateInstUserOK()
	{
		@SuppressWarnings("unused")
		EntityUserModel entityU = createPrimeUser();
		@SuppressWarnings("unused")
		EntityInstitutionModel entityI = createInstitution();
	}

	@Test
	public void testFindInstitutionByIdOK()
	{
		@SuppressWarnings("unused")
		EntityInstitutionModel institution = instService.findInstitutionById(1);
	}

	@Test(expected=UnknownEntityException.class)
	public void testFindInstitutionByIdNone()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(instRepository).findInstitutionById(anyInt());

		@SuppressWarnings("unused")
		EntityInstitutionModel institution = instService.findInstitutionById(1000001);
	}

	@Test(expected=ServiceException.class)
	public void testFindInstitutionByIdFails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(instRepository).findInstitutionById(anyInt());

		@SuppressWarnings("unused")
		EntityInstitutionModel institution = instService.findInstitutionById(1000001);
	}

	@Test
	public void testFindInstitutionByNameOK()
	{
		@SuppressWarnings("unused")
		EntityInstitutionModel institution = instService.findInstitutionByName("Some Test College");
	}

	@Test(expected=UnknownEntityException.class)
	public void testFindInstitutionByNameNone()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(instRepository).findInstitutionByName(anyString());

		@SuppressWarnings("unused")
		EntityInstitutionModel institution = instService.findInstitutionByName("Some Unknown College");
	}

	@Test(expected=ServiceException.class)
	public void testFindInstitutionByNameFails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(instRepository).findInstitutionByName(anyString());

		@SuppressWarnings("unused")
		EntityInstitutionModel institution = instService.findInstitutionByName("Some Unknown College");
	}

	@Test
	public void testUpdateInstitutionOK()
	{
		EntityInstitutionModel entity = createInstitution();
		
		instService.updateInstitution(entity);
	}

	@Test(expected=EntityExistsException.class)
	public void testUpdateInstitutionExists()
	{
		// Configure the repository
		doThrow(new EntityExistsException()).when(instRepository).updateInstitution((InstitutionModel) any());

		EntityInstitutionModel entity = createInstitution();
		
		instService.updateInstitution(entity);
	}

	@Test(expected=Exception.class)
	public void testUpdateInstitutionFails()
	{
		// Configure the repository
		doThrow(new Exception()).when(instRepository).updateInstitution((InstitutionModel) any());

		EntityInstitutionModel entity = createInstitution();
		
		instService.updateInstitution(entity);
	}

	@Test
	public void testGetListOfInstitutionsOK()
	{
		@SuppressWarnings("unused")
		List<EntityInstitutionModel> list = instService.getListOfInstitutions();
	}

	@Test(expected=UnknownEntityException.class)
	public void testGetListOfInstitutionsNone()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(instRepository).getListOfInstitutions();

		@SuppressWarnings("unused")
		List<EntityInstitutionModel> list = instService.getListOfInstitutions();
	}

	@Test(expected=ServiceException.class)
	public void testGetListOfInstitutionsFails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(instRepository).getListOfInstitutions();

		@SuppressWarnings("unused")
		List<EntityInstitutionModel> list = instService.getListOfInstitutions();
	}

	@Test
	public void testDeleteInstUserOK() throws Exception
	{
		doReturn(fakeInstPermission()).when(userRepository).getPermissionModel(any());
		
		instService.deleteInstUser(14, 1);
	}

	@Test(expected=UnknownEntityException.class)
	public void testDeleteInstUserNone() throws Exception
	{
		// Configure the repository
		doReturn(fakeInstPermission()).when(userRepository).getPermissionModel(any());
		doThrow(new UnknownEntityException()).when(iupRepository).deleteOne(anyInt(), anyInt(), anyInt());

		instService.deleteInstUser(14, 1);
	}

	@Test(expected=ServiceException.class)
	public void testDeleteInstUserFails() throws Exception
	{
		// Configure the repository
		doReturn(fakeInstPermission()).when(userRepository).getPermissionModel(any());
		doThrow(new ServiceException()).when(iupRepository).deleteOne(anyInt(), anyInt(), anyInt());

		instService.deleteInstUser(14, 1);
	}
}
