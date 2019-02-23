package com.linguaclassica.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;
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

import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.model.InstitutionModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.TermModel;
import com.linguaclassica.repository.RepositoryConfiguration;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;


/**
 * Test the functions in the TermService layer.
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
public class TermServiceTest extends AbstractTransactionalJUnit4SpringContextTests
{
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private InstitutionService instService;
	
	@Autowired
	private TermService termService;
	
	public static final int BEGINNING_OF_TIME = -1;

	private String sFirstInstitution = "uArcturus Test";
	private String sFirstPostad = "1000 University Drive, Startown, ON";
	private String sFirstContact = "Registration Office";
	private String sFirstPhone = "1-613-555-1000";
	private String sFirstEmail = "registration@uarcturus.edu";
	private String sFirstAdmingiven = "Primus";
	private String sFirstAdminfamily = "Bureaucratus";
	private String sFirstAdminemail = "prime.bear@uarcturus.edu";

	private String sThirdInstitution = "uCardriver Test";
	private String sThirdPostad = "2000 Racetrack Road, Autoville, ON";
	private String sThirdContact = "Racing Office";
	private String sThirdPhone = "1-613-555-2000";
	private String sThirdEmail = "registration@ucardriver.edu";
	private String sThirdAdmingiven = "Backseat";
	private String sThirdAdminfamily = "Driver";
	private String sThirdAdminemail = "bdriver@ucardriver.edu";

	private String sFirstTermname = "Fall 2017";
	private String sFirstStartdate = "2017-09-01";
	private String sFirstEnddate = "2017-12-31";

	private String sUpdateTermname = "Fall 2017";
	private String sUpdateStartdate = "2017-09-04";
	private String sUpdateEnddate = "2017-12-24";

	private String sFourthTermname = "Autumn 2017";
	private String sFourthStartdate = "2017-09-04";
	private String sFourthEnddate = "2018-01-26";

	private String sFifthTermname = "Winter 2018";
	private String sFifthStartdate = "2018-01-01";
	private String sFifthEnddate = "2018-04-30";
	
	private String asTrimesters[] = 
	{
			"Fall T14", "Winter T15", "Spring T15", "Fall T15", "Winter T16", "Spring T16", 
			"Fall T16", "Winter T17", "Spring T17", "Fall T17", "Winter T18", "Spring T18", 
			"Fall T18", "Winter T19", "Spring T19"
	};
	private String asTristarts[] = 
	{
			"2014-09-01", "2015-01-01", "2015-05-01", "2015-09-01", "2016-01-01", "2016-05-01", 
			"2016-09-01", "2017-01-01", "2017-05-01", "2017-09-01", "2018-01-01", "2018-05-01", 
			"2018-09-01", "2019-01-01", "2019-05-01"
	};
	private String asTriends[] = 
	{
			"2014-12-31", "2015-04-30", "2015-08-31", "2015-12-31", "2016-04-30", "2016-08-31", 
			"2016-12-31", "2017-04-30", "2017-08-31", "2017-12-31", "2018-04-30", "2018-08-31", 
			"2018-12-31", "2019-04-30", "2019-08-31"
	};
	
	private InstitutionModel imFirstInst = null;
	private InstitutionModel imThirdInst = null;
	private TermModel tmFirstTerm = null;
	private TermModel tmFourthTerm = null;
	private TermModel tmFifthTerm = null;

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
	
	private void createFirstTerm(int institutionId)
	{
		tmFirstTerm = modelFactory.getNewTermModel();
		tmFirstTerm.setTermname(sFirstTermname);
		tmFirstTerm.setInstid(institutionId);
		tmFirstTerm.setStartDate(Date.valueOf(sFirstStartdate));
		tmFirstTerm.setEndDate(Date.valueOf(sFirstEnddate));
	}
	
	private void createFourthTerm(int institutionId)
	{
		// Create another term with different settings
		tmFourthTerm = modelFactory.getNewTermModel();
		tmFourthTerm.setTermname(sFourthTermname);
		tmFourthTerm.setInstid(institutionId);
		tmFourthTerm.setStartDate(Date.valueOf(sFourthStartdate));
		tmFourthTerm.setEndDate(Date.valueOf(sFourthEnddate));
	}
	
	private void createFifthTerm(int institutionId)
	{
		tmFifthTerm = modelFactory.getNewTermModel();
		tmFifthTerm.setTermname(sFifthTermname);
		tmFifthTerm.setInstid(institutionId);
		tmFifthTerm.setStartDate(Date.valueOf(sFifthStartdate));
		tmFifthTerm.setEndDate(Date.valueOf(sFifthEnddate));
	}
	
	private void createTrimesterTerms(int institutionId)
	{
		for (Integer index = 0; index < asTrimesters.length; index++)
		{
			TermModel tm = modelFactory.getNewTermModel();
			tm.setTermname(asTrimesters[index]);
			tm.setInstid(institutionId);
			tm.setStartDate(Date.valueOf(asTristarts[index]));
			tm.setEndDate(Date.valueOf(asTriends[index]));
			termService.createTerm(tm);
		}
	}
	
	// Determine whether most settings are the same
	private boolean isSameTerm(EntityTermModel one, EntityTermModel two)
	{
		boolean bSame = one.getId() == two.getId();
		//boolean bInstId = one.getInstid() == two.getInstid(); THIS LINE FAILS
		int iOne = one.getInstid();
		int iTwo = two.getInstid();
		boolean bInstId = iOne == iTwo;
		bSame &= bInstId;
		boolean bTermname = one.getTermname() == two.getTermname();
		bSame &= bTermname;
		boolean bStart = one.getStartDate() == two.getStartDate();
		bSame &= bStart;
		boolean bEnd = one.getEndDate() == two.getEndDate();
		bSame &= bEnd;
		return bSame;
	}
	
	@Before
	public void setUp() throws Exception
	{
		// Create a default institution for ALL of the tests
		createFirstInstitution();
		instService.createInstitution(imFirstInst);
		
		// Create another institution for some of the tests
		createThirdInstitution();
		instService.createInstitution(imThirdInst);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCreateTerm_OK()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);
	}

	@Test(expected=EntityAlreadyExistsException.class)
	public void testCreateTerm_Same()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);

		// Create a second institution with the same settings
		TermModel tmAgainTerm = modelFactory.getNewTermModel();
		tmAgainTerm.setTermname(sFirstTermname);
		tmAgainTerm.setInstid(imFirstInst.getId());
		tmAgainTerm.setStartDate(Date.valueOf(sFirstStartdate));
		tmAgainTerm.setEndDate(Date.valueOf(sFirstEnddate));
		
		termService.createTerm(tmAgainTerm);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testCreateTerm_Similar()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);

		// Create a second term with different settings
		TermModel tmSimilarTerm = modelFactory.getNewTermModel();
		tmSimilarTerm.setTermname(sFirstTermname);
		tmSimilarTerm.setInstid(imThirdInst.getId());
		tmSimilarTerm.setStartDate(Date.valueOf(sFirstStartdate));
		tmSimilarTerm.setEndDate(Date.valueOf(sFirstEnddate));
		
		termService.createTerm(tmSimilarTerm);
		
		assertTrue(tmSimilarTerm.getId() > 0);
	}

	@Test
	public void testUpdateTerm_OK()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);

		// Get previous values
		int prevTermid = tmFirstTerm.getId();
		
		tmFirstTerm.setTermname(sUpdateTermname);
		tmFirstTerm.setStartDate(Date.valueOf(sUpdateStartdate));
		tmFirstTerm.setEndDate(Date.valueOf(sUpdateEnddate));
		
		termService.updateTerm(tmFirstTerm);
		
		assertTrue(prevTermid == tmFirstTerm.getId());
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testUpdateTerm_Duplicate()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);

		// Create another term with different settings
		createFourthTerm(imFirstInst.getId());
		termService.createTerm(tmFourthTerm);
		
		assertTrue(tmFourthTerm.getId() > 0);
		
		// Change name to that of another term
		tmFirstTerm.setTermname(sFourthTermname);
		
		termService.updateTerm(tmFirstTerm);

		// TODO Should not get here
		@SuppressWarnings("unused")
		List<EntityTermModel> altered = termService.getListOfTermsByInstId(imFirstInst.getId());
		assertTrue(false);
	}

	@Test
	public void testFindTermById_OK()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);

		// Create another term with different settings
		createFourthTerm(imFirstInst.getId());
		termService.createTerm(tmFourthTerm);
		
		assertTrue(tmFourthTerm.getId() > 0);

		// Create another term with different settings for another institution
		createFifthTerm(imThirdInst.getId());
		termService.createTerm(tmFifthTerm);
		
		assertTrue(tmFifthTerm.getId() > 0);

		EntityTermModel teOne = termService.findTermById(tmFirstTerm.getId());
		
		assertNotNull(teOne);
		assertTrue(isSameTerm((EntityTermModel) tmFirstTerm, teOne));
		
		EntityTermModel teTwo = termService.findTermById(tmFourthTerm.getId());
		
		assertNotNull(teTwo);
		assertTrue(isSameTerm((EntityTermModel) tmFourthTerm, teTwo));

		EntityTermModel teTre = termService.findTermById(tmFifthTerm.getId());
		
		assertNotNull(teTre);
		assertTrue(isSameTerm((EntityTermModel) tmFifthTerm, teTre));
	}

	@Test(expected=ServiceException.class)
	public void testFindTermById_Fails()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);

		EntityTermModel teOne = termService.findTermById(tmFirstTerm.getId());
		
		assertNotNull(teOne);
		assertTrue(isSameTerm((EntityTermModel) tmFirstTerm, teOne));
		
		// Expect this one to fail
		@SuppressWarnings("unused")
		EntityTermModel teTwo = termService.findTermById(1000000 + tmFirstTerm.getId());
		
		// Should not reach here
		assertTrue(false);
		
	}

	@Test
	public void testGetListOfTermsByInstId_OK()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);

		// Create another term with different settings
		createFourthTerm(imFirstInst.getId());
		termService.createTerm(tmFourthTerm);
		
		assertTrue(tmFourthTerm.getId() > 0);

		// Create another term with different settings for another institution
		createFifthTerm(imThirdInst.getId());
		termService.createTerm(tmFifthTerm);
		
		assertTrue(tmFifthTerm.getId() > 0);

		// Tests without Date limits
		List<EntityTermModel> listOne = termService.getListOfTermsByInstId(imFirstInst.getId());
		
		assertNotNull(listOne);
		assertTrue(listOne.size() == 2);
		
		List<EntityTermModel> listThree = termService.getListOfTermsByInstId(imThirdInst.getId());
		
		assertNotNull(listThree);
		assertTrue(listThree.size() == 1);
		
		// Tests with Date limits - all dates
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listOtherOne = termService.getListOfTermsByInstId(imFirstInst.getId(), current, BEGINNING_OF_TIME);
		
		assertNotNull(listOtherOne);
		assertTrue(listOtherOne.size() == 2);
		
		List<EntityTermModel> listOtherThree = termService.getListOfTermsByInstId(imThirdInst.getId(), current, BEGINNING_OF_TIME);
		
		assertNotNull(listOtherThree);
		assertTrue(listOtherThree.size() == 1);
	}

	@Test
	public void testGetListOfTermsByInstId_None()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);
		
		// Tests without Date limits
		List<EntityTermModel> listNone = termService.getListOfTermsByInstId(1000000 + imFirstInst.getId());
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
		
		// Tests with Date limits - all dates
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listOtherNone = termService.getListOfTermsByInstId(1000000 + imFirstInst.getId(), current, BEGINNING_OF_TIME);
		
		assertNotNull(listOtherNone);
		assertTrue(listOtherNone.size() == 0);
	}
	
	@Test
	public void testGetListOfExistingTerms_None()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listNone = termService.getListOfExistingTerms(imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
		
		listNone = termService.getListOfExistingTerms(1000000 + imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
	}
	
	@Test
	public void testGetListOfExistingTerms_One()
	{
		createTrimesterTerms(imFirstInst.getId());
		
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listOne = termService.getListOfExistingTerms(imFirstInst.getId(), current);
		
		assertNotNull(listOne);
		assertTrue(listOne.size() == 1);
		
		List<EntityTermModel> listNone = termService.getListOfExistingTerms(1000000 + imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
	}
	
	@Test
	public void testGetCurrentTermIDs_None()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<Integer> listNone = termService.getCurrentTermIDs(imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
		
		listNone = termService.getCurrentTermIDs(1000000 + imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
	}
	
	@Test
	public void testGetCurrentTermIDs_One()
	{
		createTrimesterTerms(imFirstInst.getId());
		
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<Integer> listOne = termService.getCurrentTermIDs(imFirstInst.getId(), current);
		
		assertNotNull(listOne);
		assertTrue(listOne.size() == 1);
		
		List<Integer> listNone = termService.getCurrentTermIDs(1000000 + imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
	}
	
	@Test
	public void testGetPriorTermIDs_None()
	{
		createTrimesterTerms(imFirstInst.getId());
		
		// Expect no dates in range
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 8, 14);
		java.sql.Date past = new java.sql.Date(cal.getTimeInMillis());
		
		List<Integer> listNone = termService.getPriorTermIDs(imFirstInst.getId(), past, BEGINNING_OF_TIME);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
		
		listNone = termService.getPriorTermIDs(1000000 + imFirstInst.getId(), past, BEGINNING_OF_TIME);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
		
		// Dates are in range
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		listNone = termService.getPriorTermIDs(1000000 + imFirstInst.getId(), current, BEGINNING_OF_TIME);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
		
		// The method should never be called with 0 prior terms
		listNone = termService.getPriorTermIDs(imFirstInst.getId(), current, 0);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
	}
	
	@Test
	public void testGetPriorTermIDs_Some()
	{
		createTrimesterTerms(imFirstInst.getId());
		
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<Integer> listSome = termService.getPriorTermIDs(imFirstInst.getId(), current, 1);
		
		assertNotNull(listSome);
		assertTrue(listSome.size() == 1);
		
		listSome = termService.getPriorTermIDs(imFirstInst.getId(), current, 2);
		
		assertNotNull(listSome);
		assertTrue(listSome.size() == 2);
		
		listSome = termService.getPriorTermIDs(imFirstInst.getId(), current, BEGINNING_OF_TIME);
		
		assertNotNull(listSome);
		assertTrue(listSome.size() > 2);
	}
	
	@Test
	public void testGetListOfNewTerms_None()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listNone = termService.getListOfNewTerms(imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
		
		listNone = termService.getListOfNewTerms(1000000 + imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
	}
	
	@Test
	public void testGetListOfNewTerms_Some()
	{
		createTrimesterTerms(imFirstInst.getId());
		
		Calendar cal = Calendar.getInstance();
		cal.set(2016, 6, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listSome = termService.getListOfNewTerms(imFirstInst.getId(), current);
		
		assertNotNull(listSome);
		assertTrue(listSome.size() == 9);

		cal.set(2017, 10, 20);
		current = new java.sql.Date(cal.getTimeInMillis());
		
		listSome = termService.getListOfNewTerms(imFirstInst.getId(), current);
		
		assertNotNull(listSome);
		assertTrue(listSome.size() == 5);

		cal.set(2018, 2, 20);
		current = new java.sql.Date(cal.getTimeInMillis());
		
		listSome = termService.getListOfNewTerms(imFirstInst.getId(), current);
		
		assertNotNull(listSome);
		assertTrue(listSome.size() == 4);
	}

	@Test
	public void testGetListOfNewAndExistingTerms_None()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listNone = termService.getListOfNewAndExistingTerms(imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
		
		listNone = termService.getListOfNewAndExistingTerms(1000000 + imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
	}

	@Test
	public void testGetListOfNewAndExistingTerms_Some()
	{
		createTrimesterTerms(imFirstInst.getId());
		
		Calendar cal = Calendar.getInstance();
		cal.set(2016, 6, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listSome = termService.getListOfNewAndExistingTerms(imFirstInst.getId(), current);
		
		assertNotNull(listSome);
		assertTrue(listSome.size() == 10);

		cal.set(2017, 10, 20);
		current = new java.sql.Date(cal.getTimeInMillis());
		
		listSome = termService.getListOfNewAndExistingTerms(imFirstInst.getId(), current);
		
		assertNotNull(listSome);
		assertTrue(listSome.size() == 6);

		cal.set(2018, 2, 20);
		current = new java.sql.Date(cal.getTimeInMillis());
		
		listSome = termService.getListOfNewAndExistingTerms(imFirstInst.getId(), current);
		
		assertNotNull(listSome);
		assertTrue(listSome.size() == 5);
	}

	@Test
	public void testGetListOfPastTerms_None()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, BEGINNING_OF_TIME);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
		
		listNone = termService.getListOfPastTerms(1000000 + imFirstInst.getId(), current, BEGINNING_OF_TIME);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
	}

	@Test
	public void testGetListOfPastTerms_Some()
	{
		createTrimesterTerms(imFirstInst.getId());
		
		Calendar cal = Calendar.getInstance();
		cal.set(2016, 6, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, BEGINNING_OF_TIME);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 5);
		
		listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, 2);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 2);
		
		listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, 1);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 1);
		
		listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, 0);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);

		cal.set(2017, 10, 20);
		current = new java.sql.Date(cal.getTimeInMillis());
		
		listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, BEGINNING_OF_TIME);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 9);
		
		listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, 2);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 2);
		
		listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, 1);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 1);
		
		listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, 0);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);

		cal.set(2018, 2, 20);
		current = new java.sql.Date(cal.getTimeInMillis());
		
		listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, BEGINNING_OF_TIME);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 10);
		
		listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, 2);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 2);
		
		listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, 1);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 1);
		
		listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, 0);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);

		cal.set(2015, 2, 20);
		current = new java.sql.Date(cal.getTimeInMillis());
		
		listNone = termService.getListOfPastTerms(imFirstInst.getId(), current, 2);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 1);   // only 1 prior to 2015
	}
	
	@Test
	public void testGetPastAndCurrentTerms_None()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listNone = termService.getPastAndCurrentTerms(imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
		
		listNone = termService.getPastAndCurrentTerms(1000000 + imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
	}
	
	@Test
	public void testGetPastAndCurrentTerms_Some()
	{
		createTrimesterTerms(imFirstInst.getId());
		
		Calendar cal = Calendar.getInstance();
		cal.set(2016, 6, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listNone = termService.getPastAndCurrentTerms(imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 6);

		cal.set(2017, 10, 20);
		current = new java.sql.Date(cal.getTimeInMillis());
		
		listNone = termService.getPastAndCurrentTerms(imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 10);

		cal.set(2018, 2, 20);
		current = new java.sql.Date(cal.getTimeInMillis());
		
		listNone = termService.getPastAndCurrentTerms(imFirstInst.getId(), current);
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 11);
	}
	
	@Test
	public void testDeleteTerm_OK()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);
		
		termService.deleteTermById(tmFirstTerm.getId());
		
		// Tests without Date limits
		List<EntityTermModel> listNone = termService.getListOfTermsByInstId(imFirstInst.getId());
		
		assertNotNull(listNone);
		assertTrue(listNone.size() == 0);
		
		// Tests with Date limits - all dates
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 20);
		java.sql.Date current = new java.sql.Date(cal.getTimeInMillis());
		
		List<EntityTermModel> listOtherNone = termService.getListOfTermsByInstId(imFirstInst.getId(), current, BEGINNING_OF_TIME);
		
		assertNotNull(listOtherNone);
		assertTrue(listOtherNone.size() == 0);
	}

	@Test(expected=ServiceException.class)
	public void testDeleteTerm_Fails()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);
		
		termService.deleteTermById(1000000 + tmFirstTerm.getId());

		// Should not reach here
		assertTrue(false);
	}
}
