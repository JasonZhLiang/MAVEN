package com.linguaclassica.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.EntityExistsException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import com.linguaclassica.entity.EntityModelFactory;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.entity.EntityTermStudentsModel;
import com.linguaclassica.entity.EntityTermTaModel;
import com.linguaclassica.entity.EntityTermTeachersModel;
import com.linguaclassica.model.TermModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.repository.TermRepository;
import com.linguaclassica.repository.TermStudentsRepository;
import com.linguaclassica.repository.TermTaRepository;
import com.linguaclassica.repository.TermTeachersRepository;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;
import com.linguaclassica.service.exception.UnknownEntityException;

@RunWith(MockitoJUnitRunner.class)
public class TermServiceMockTest
{
	@Spy EntityModelFactory modelFactory = new EntityModelFactory();
	@Mock TermRepository termRepository = new TermRepository();
	@Mock TermStudentsRepository termStudentsRepository = new TermStudentsRepository();
	@Mock TermTeachersRepository termTeachersRepository = new TermTeachersRepository();
	@Mock TermTaRepository termTaRepository = new TermTaRepository();
	@InjectMocks TermService termService;

	int defInstitutionId = 1;
	int defTermId = 7;
	int defTeacherId = 28;
	int defTaId = 53;
	int defStudentId = 127;


	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}
	
	private EntityTermModel createTerm(Integer termid, String termname, Integer instid, Integer days)
	{
		LocalDate localNow = LocalDate.now();
		LocalDate localStart = localNow.minusDays(localNow.getDayOfMonth());
		localStart.plusDays(1);
		LocalDate localEnd = localNow.plusDays(days);
		
		java.sql.Date startDate = java.sql.Date.valueOf(localStart);
		java.sql.Date endDate = java.sql.Date.valueOf(localEnd);
		
		EntityTermModel etm = (EntityTermModel) modelFactory.getNewTermModel();
		etm.setId(termid);
		etm.setTermname(termname);
		etm.setStartDate(startDate);
		etm.setEndDate(endDate);
		etm.setInstid(instid);
		return etm;
	}

	private EntityTermModel createDefaultTerm()
	{
		EntityTermModel etm = createTerm(defTermId, "Session 2112", defInstitutionId, 100);
		return etm;
	}
	
	private UserModel createOneUser(String emailAddress, String firstName, String lastName)
	{
		UserModel model = modelFactory.getNewUserModel();
		model.setEmailAddress(emailAddress);
		model.setFirstName(firstName);
		model.setLastName(lastName);
		model.setPassword("ABC456def");
		model.setStudentnumber("");
		return model;
	}
	
	private EntityTermStudentsModel createTermStudent()
	{
		EntityTermStudentsModel etsm = (EntityTermStudentsModel) modelFactory.getNewTermStudentsModel();
		etsm.setStudentid(defStudentId);
		etsm.setTermid(defTermId);
		return etsm;
	}
	
	private EntityTermTeachersModel createTermTeacher()
	{
		EntityTermTeachersModel ettm = (EntityTermTeachersModel) modelFactory.getNewTermTeachersModel();
		ettm.setTeacherid(defTeacherId);
		ettm.setTermid(defTermId);
		return ettm;
	}
	
	private EntityTermTaModel createTermTA()
	{
		EntityTermTaModel ettm = (EntityTermTaModel) modelFactory.getNewTermTaModel();
		ettm.setTaid(defTaId);
		ettm.setTermid(defTermId);
		return ettm;
	}
	
	private List<EntityTermModel> createTermList()
	{
		List<EntityTermModel> list = new ArrayList<EntityTermModel>();
		EntityTermModel term1 = createDefaultTerm();
		list.add(term1);
		EntityTermModel term2 = createTerm(defTermId + 1, "Fall 201X", defInstitutionId, 110);
		list.add(term2);
		EntityTermModel term3 = createTerm(defTermId + 2, "Winter 201Y", defInstitutionId, 112);
		list.add(term3);
		return list;
	}

	@Test
	public void testCreateTerm_OK()
	{
		EntityTermModel etm = createDefaultTerm();
		
		termService.createTerm(etm);
	}

	@Test(expected=EntityAlreadyExistsException.class)
	public void testCreateTerm_Exists()
	{
		// Configure the repository
		doThrow(new EntityExistsException()).when(termRepository).createTerm((EntityTermModel) any());

		EntityTermModel etm = createDefaultTerm();
		
		termService.createTerm(etm);
	}

	@Test(expected=ServiceException.class)
	public void testCreateTerm_Fails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termRepository).createTerm((EntityTermModel) any());

		EntityTermModel etm = createDefaultTerm();
		
		termService.createTerm(etm);
	}

	@Test
	public void testUpdateTerm_OK()
	{
		TermModel model = createDefaultTerm();
		
		termService.updateTerm(model);
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testUpdateTerm_Fails()
	{
		// Configure the repository
		doThrow(new DataIntegrityViolationException("duplicate?")).when(termRepository).updateTerm((TermModel) any());

		TermModel model = createDefaultTerm();
		
		termService.updateTerm(model);
	}

	@Test
	public void testFindTermById_OK()
	{
		@SuppressWarnings("unused")
		EntityTermModel entity = termService.findTermById(defTermId);
	}

	@Test(expected=UnknownEntityException.class)
	public void testFindTermById_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termRepository).findTermById(anyInt());

		@SuppressWarnings("unused")
		EntityTermModel entity = termService.findTermById(1000007);
	}

	@Test(expected=ServiceException.class)
	public void testFindTermById_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termRepository).findTermById(anyInt());

		@SuppressWarnings("unused")
		EntityTermModel entity = termService.findTermById(100000007);
	}

	@Test
	public void testFindTermByName_OK()
	{
		@SuppressWarnings("unused")
		EntityTermModel entity = termService.findTermByName("Fall 2017");
	}

	@Test(expected=UnknownEntityException.class)
	public void testFindTermByName_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termRepository).findTermByName(anyString());

		@SuppressWarnings("unused")
		EntityTermModel entity = termService.findTermByName("September October November and December 2017");
	}

	@Test(expected=ServiceException.class)
	public void testFindTermByName_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termRepository).findTermByName(anyString());

		@SuppressWarnings("unused")
		EntityTermModel entity = termService.findTermByName("#$%^");
	}

	@Test
	public void testGetListOfTermsByInstId_OK()
	{
		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfTermsByInstId(defInstitutionId);
	}

	@Test(expected=UnknownEntityException.class)
	public void testGetListOfTermsByInstId_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termRepository).getListOfTermsByInstId(anyInt());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfTermsByInstId(1000005);
	}

	@Test(expected=ServiceException.class)
	public void testGetListOfTermsByInstId_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termRepository).getListOfTermsByInstId(anyInt());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfTermsByInstId(100000005);
	}

	@Test
	public void testCreateTermStudents_OK()
	{
		EntityTermStudentsModel entity = createTermStudent();
		
		@SuppressWarnings("unused")
		int studid = termService.createTermStudents(entity);
	}

	@Test(expected=PersistenceException.class)
	public void testCreateTermStudents_ListFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termStudentsRepository).getListOfTermStudentById((EntityTermStudentsModel) any());

		EntityTermStudentsModel entity = createTermStudent();
		
		@SuppressWarnings("unused")
		int studid = termService.createTermStudents(entity);
	}

	@Test(expected=EntityExistsException.class)
	public void testCreateTermStudents_CreateExisting()
	{
		// Configure the repository
		doThrow(new EntityExistsException()).when(termStudentsRepository).createTermStudent((EntityTermStudentsModel) any());

		EntityTermStudentsModel entity = createTermStudent();
		
		@SuppressWarnings("unused")
		int studid = termService.createTermStudents(entity);
	}

	@Test(expected=PersistenceException.class)
	public void testCreateTermStudents_CreateFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termStudentsRepository).createTermStudent((EntityTermStudentsModel) any());

		EntityTermStudentsModel entity = createTermStudent();
		
		@SuppressWarnings("unused")
		int studid = termService.createTermStudents(entity);
	}

	@Test
	public void testGetListOfStudentIdsByTermId_OK()
	{
		@SuppressWarnings("unused")
		List<Integer> list = termService.getListOfStudentIdsByTermId(defTermId);
	}

	@Test(expected=UnknownEntityException.class)
	public void testGetListOfStudentIdsByTermId_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termStudentsRepository).getListOfStudentIdsByTermId(anyInt());

		@SuppressWarnings("unused")
		List<Integer> list = termService.getListOfStudentIdsByTermId(defTermId);
	}

	@Test(expected=ServiceException.class)
	public void testGetListOfStudentIdsByTermId_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termStudentsRepository).getListOfStudentIdsByTermId(anyInt());

		@SuppressWarnings("unused")
		List<Integer> list = termService.getListOfStudentIdsByTermId(defTermId);
	}

	@Test
	public void testCreateTermTeacher_OK()
	{
		EntityTermTeachersModel entity = createTermTeacher();
		
		@SuppressWarnings("unused")
		int teachid = termService.createTermTeacher(entity);
	}

	@Test(expected=PersistenceException.class)
	public void testCreateTermTeacher_ListFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termTeachersRepository).getListOfTermTeachersById((EntityTermTeachersModel) any());

		EntityTermTeachersModel entity = createTermTeacher();
		
		@SuppressWarnings("unused")
		int teachid = termService.createTermTeacher(entity);
	}

	@Test(expected=EntityExistsException.class)
	public void testCreateTermTeacher_CreateExisting()
	{
		// Configure the repository
		doThrow(new EntityExistsException()).when(termTeachersRepository).createTermTeacher((EntityTermTeachersModel) any());

		EntityTermTeachersModel entity = createTermTeacher();
		
		@SuppressWarnings("unused")
		int teachid = termService.createTermTeacher(entity);
	}

	@Test(expected=PersistenceException.class)
	public void testCreateTermTeacher_CreateFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termTeachersRepository).createTermTeacher((EntityTermTeachersModel) any());

		EntityTermTeachersModel entity = createTermTeacher();
		
		@SuppressWarnings("unused")
		int teachid = termService.createTermTeacher(entity);
	}

	@Test
	public void testCreateTermTa_OK()
	{
		EntityTermTaModel entity = createTermTA();
		
		@SuppressWarnings("unused")
		int taid = termService.createTermTa(entity);
	}

	@Test(expected=PersistenceException.class)
	public void testCreateTermTa_ListFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termTaRepository).getListOfTermTasById((EntityTermTaModel) any());

		EntityTermTaModel entity = createTermTA();
		
		@SuppressWarnings("unused")
		int taid = termService.createTermTa(entity);
	}

	@Test(expected=EntityExistsException.class)
	public void testCreateTermTa_CreateExisting()
	{
		// Configure the repository
		doThrow(new EntityExistsException()).when(termTaRepository).createTermTa((EntityTermTaModel) any());

		EntityTermTaModel entity = createTermTA();
		
		@SuppressWarnings("unused")
		int taid = termService.createTermTa(entity);
	}

	@Test(expected=PersistenceException.class)
	public void testCreateTermTa_CreateFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termTaRepository).createTermTa((EntityTermTaModel) any());

		EntityTermTaModel entity = createTermTA();
		
		@SuppressWarnings("unused")
		int taid = termService.createTermTa(entity);
	}

	@Test
	public void testGetListOfTeacherIdsByTermId_OK()
	{
		@SuppressWarnings("unused")
		List<Integer> list = termService.getListOfTeacherIdsByTermId(9);
	}

	@Test(expected=UnknownEntityException.class)
	public void testGetListOfTeacherIdsByTermId_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termTeachersRepository).getListOfTeacherIdsByTermId(anyInt());

		@SuppressWarnings("unused")
		List<Integer> list = termService.getListOfTeacherIdsByTermId(9);
	}

	@Test(expected=ServiceException.class)
	public void testGetListOfTeacherIdsByTermId_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termTeachersRepository).getListOfTeacherIdsByTermId(anyInt());

		@SuppressWarnings("unused")
		List<Integer> list = termService.getListOfTeacherIdsByTermId(9);
	}

	@Test
	public void testGetListOfTeacherIdsByTermIds_OK()
	{
		List<EntityTermModel> termList = createTermList();
		
		// TODO TermTeachersRepository.getListOfTeacherIdsByTermId(termid) return empty list
		@SuppressWarnings("unused")
		List<Integer> idList = termService.getListOfTeacherIdsByTermIds(termList);
	}

	@Test(expected=UnknownEntityException.class)
	public void testGetListOfTeacherIdsByTermIds_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termTeachersRepository).getListOfTeacherIdsByTermId(anyInt());

		List<EntityTermModel> termList = createTermList();
		
		// TODO TermTeachersRepository.getListOfTeacherIdsByTermId(termid) return empty list
		@SuppressWarnings("unused")
		List<Integer> idList = termService.getListOfTeacherIdsByTermIds(termList);
	}

	@Test(expected=ServiceException.class)
	public void testGetListOfTeacherIdsByTermIds_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termTeachersRepository).getListOfTeacherIdsByTermId(anyInt());

		List<EntityTermModel> termList = createTermList();
		
		// TODO TermTeachersRepository.getListOfTeacherIdsByTermId(termid) return empty list
		@SuppressWarnings("unused")
		List<Integer> idList = termService.getListOfTeacherIdsByTermIds(termList);
	}

	@Test
	public void testGetListOfTaIdsByTermId_OK()
	{
		@SuppressWarnings("unused")
		List<Integer> list = termService.getListOfTaIdsByTermId(defTermId);
	}

	@Test(expected=UnknownEntityException.class)
	public void testGetListOfTaIdsByTermId_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termTaRepository).getListOfTaIdsByTermId(anyInt());

		@SuppressWarnings("unused")
		List<Integer> list = termService.getListOfTaIdsByTermId(defTermId);
	}

	@Test(expected=ServiceException.class)
	public void testGetListOfTaIdsByTermId_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termTaRepository).getListOfTaIdsByTermId(anyInt());

		@SuppressWarnings("unused")
		List<Integer> list = termService.getListOfTaIdsByTermId(defTermId);
	}

	@Test
	public void testGetListOfExistingTerms_OK()
	{
		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfExistingTerms(defInstitutionId, now);
	}

	@Test(expected=UnknownEntityException.class)
	public void testGetListOfExistingTerms_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termRepository).getListOfExistingTerms(anyInt(), anyObject());

		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfExistingTerms(defInstitutionId, now);
	}

	@Test(expected=ServiceException.class)
	public void testGetListOfExistingTerms_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termRepository).getListOfExistingTerms(anyInt(), anyObject());

		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfExistingTerms(defInstitutionId, now);
	}

	@Test
	public void testGetListOfNewTerms_OK()
	{
		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfNewTerms(defInstitutionId, now);
	}

	@Test(expected=UnknownEntityException.class)
	public void testGetListOfNewTerms_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termRepository).getListOfNewTerms(anyInt(), anyObject());

		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfNewTerms(defInstitutionId, now);
	}

	@Test(expected=ServiceException.class)
	public void testGetListOfNewTerms_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termRepository).getListOfNewTerms(anyInt(), anyObject());

		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfNewTerms(defInstitutionId, now);
	}

	@Test
	public void testGetListOfNewAndExistingTerms_OK()
	{
		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfNewAndExistingTerms(defInstitutionId, now);
	}

	@Test(expected=UnknownEntityException.class)
	public void testGetListOfNewAndExistingTerms_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termRepository).getListOfNewAndExistingTerms(anyInt(), anyObject());

		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfNewAndExistingTerms(defInstitutionId, now);
	}

	@Test(expected=ServiceException.class)
	public void testGetListOfNewAndExistingTerms_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termRepository).getListOfNewAndExistingTerms(anyInt(), anyObject());

		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfNewAndExistingTerms(defInstitutionId, now);
	}

	@Test
	public void testGetListOfPastTerms_OK()
	{
		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfPastTerms(defInstitutionId, now,
				TermRepository.BEGINNING_OF_TIME);
	}

	@Test(expected=UnknownEntityException.class)
	public void testGetListOfPastTerms_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termRepository).getListOfPastTerms(anyInt(), anyObject(), anyInt());

		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfPastTerms(defInstitutionId, now,
				TermRepository.BEGINNING_OF_TIME);
	}

	@Test(expected=ServiceException.class)
	public void testGetListOfPastTerms_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termRepository).getListOfPastTerms(anyInt(), anyObject(), anyInt());

		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getListOfPastTerms(defInstitutionId, now,
				TermRepository.BEGINNING_OF_TIME);
	}

	@Test
	public void testGetPastAndCurrentTerms_OK()
	{
		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getPastAndCurrentTerms(defInstitutionId, now);
	}

	@Test(expected=UnknownEntityException.class)
	public void testGetPastAndCurrentTerms_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termRepository).getPastAndCurrentTerms(anyInt(), any());

		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getPastAndCurrentTerms(defInstitutionId, now);
	}

	@Test(expected=ServiceException.class)
	public void testGetPastAndCurrentTerms()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termRepository).getPastAndCurrentTerms(anyInt(), any());

		java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		@SuppressWarnings("unused")
		List<EntityTermModel> list = termService.getPastAndCurrentTerms(defInstitutionId, now);
	}

	@Test
	public void testAddStudentToList_OK()
	{
		UserModel model = createOneUser("andy@100.com", "Andy", "Andrews");
		
		@SuppressWarnings("unused")
		int studid = termService.addStudentToList(model, defTermId);
	}

	@Test(expected=PersistenceException.class)
	public void testAddStudentToList_ListFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termStudentsRepository).getListOfTermStudentById((EntityTermStudentsModel) any());

		UserModel model = createOneUser("andy@100.com", "Andy", "Andrews");
		
		@SuppressWarnings("unused")
		int studid = termService.addStudentToList(model, defTermId);
	}

	@Test(expected=EntityExistsException.class)
	public void testAddStudentToList_CreateExisting()
	{
		// Configure the repository
		doThrow(new EntityExistsException()).when(termStudentsRepository).createTermStudent((EntityTermStudentsModel) any());

		UserModel model = createOneUser("andy@100.com", "Andy", "Andrews");
		
		@SuppressWarnings("unused")
		int studid = termService.addStudentToList(model, defTermId);
	}

	@Test(expected=PersistenceException.class)
	public void testAddStudentToList_CreateFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termStudentsRepository).createTermStudent((EntityTermStudentsModel) any());

		UserModel model = createOneUser("andy@100.com", "Andy", "Andrews");
		
		@SuppressWarnings("unused")
		int studid = termService.addStudentToList(model, defTermId);
	}

	@Test
	public void testAddTeacherToList_OK()
	{
		UserModel model = createOneUser("missboopstein@100.com", "Betty", "Boopstein");
		
		@SuppressWarnings("unused")
		int studid = termService.addTeacherToList(model, defTermId);
	}

	@Test(expected=PersistenceException.class)
	public void testAddTeacherToList_ListFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termTeachersRepository).getListOfTermTeachersById((EntityTermTeachersModel) any());

		UserModel model = createOneUser("missboopstein@100.com", "Betty", "Boopstein");
		
		@SuppressWarnings("unused")
		int studid = termService.addTeacherToList(model, defTermId);
	}

	@Test(expected=EntityExistsException.class)
	public void testAddTeacherToList_CreateExisting()
	{
		// Configure the repository
		doThrow(new EntityExistsException()).when(termTeachersRepository).createTermTeacher((EntityTermTeachersModel) any());

		UserModel model = createOneUser("missboopstein@100.com", "Betty", "Boopstein");
		
		@SuppressWarnings("unused")
		int studid = termService.addTeacherToList(model, defTermId);
	}

	@Test(expected=PersistenceException.class)
	public void testAddTeacherToList_CreateFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termTeachersRepository).createTermTeacher((EntityTermTeachersModel) any());

		UserModel model = createOneUser("missboopstein@100.com", "Betty", "Boopstein");
		
		@SuppressWarnings("unused")
		int studid = termService.addTeacherToList(model, defTermId);
	}

	@Test
	public void testAddTaToList_OK()
	{
		UserModel model = createOneUser("chobbes@100.com", "Calvin", "Hobbes");
		
		@SuppressWarnings("unused")
		int studid = termService.addTaToList(model, defTermId);
	}

	@Test(expected=PersistenceException.class)
	public void testAddTaToList_ListFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termTaRepository).getListOfTermTasById((EntityTermTaModel) any());

		UserModel model = createOneUser("chobbes@100.com", "Calvin", "Hobbes");
		
		@SuppressWarnings("unused")
		int studid = termService.addTaToList(model, defTermId);
	}

	@Test(expected=EntityExistsException.class)
	public void testAddTaToList_CreateExisting()
	{
		// Configure the repository
		doThrow(new EntityExistsException()).when(termTaRepository).createTermTa((EntityTermTaModel) any());

		UserModel model = createOneUser("chobbes@100.com", "Calvin", "Hobbes");
		
		@SuppressWarnings("unused")
		int studid = termService.addTaToList(model, defTermId);
	}

	@Test(expected=PersistenceException.class)
	public void testAddTaToList_CreateFails()
	{
		// Configure the repository
		doThrow(new PersistenceException()).when(termTaRepository).createTermTa((EntityTermTaModel) any());

		UserModel model = createOneUser("chobbes@100.com", "Calvin", "Hobbes");
		
		@SuppressWarnings("unused")
		int studid = termService.addTaToList(model, defTermId);
	}

	@Test
	public void testDeleteTermStudent_OK()
	{
	}

	@Test(expected=UnknownEntityException.class)
	public void testDeleteTermStudent_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termStudentsRepository).deleteTermStudent(anyInt(), anyInt());

		termService.deleteTermStudent(defStudentId, defTermId);
	}

	@Test(expected=ServiceException.class)
	public void testDeleteTermStudent_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termStudentsRepository).deleteTermStudent(anyInt(), anyInt());

		termService.deleteTermStudent(defStudentId, defTermId);
	}

	@Test
	public void testDeleteTermTeacher_OK()
	{
		termService.deleteTermTeacher(defTeacherId, defTermId);
	}

	@Test(expected=UnknownEntityException.class)
	public void testDeleteTermTeacher_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termTeachersRepository).deleteTermTeacher(anyInt(), anyInt());

		termService.deleteTermTeacher(defTeacherId, defTermId);
	}

	@Test(expected=ServiceException.class)
	public void testDeleteTermTeacher_Fails()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termTeachersRepository).deleteTermTeacher(anyInt(), anyInt());

		termService.deleteTermTeacher(defTeacherId, defTermId);
	}

	@Test
	public void testDeleteTermTa_OK()
	{
		termService.deleteTermTa(defTaId, defTermId);
	}

	@Test(expected=UnknownEntityException.class)
	public void testDeleteTermTa_None()
	{
		// Configure the repository
		doThrow(new UnknownEntityException()).when(termTaRepository).deleteTermTa(anyInt(), anyInt());

		termService.deleteTermTa(defTaId, defTermId);
	}

	@Test(expected=ServiceException.class)
	public void testDeleteTermTa()
	{
		// Configure the repository
		doThrow(new ServiceException()).when(termTaRepository).deleteTermTa(anyInt(), anyInt());

		termService.deleteTermTa(defTaId, defTermId);
	}
}
