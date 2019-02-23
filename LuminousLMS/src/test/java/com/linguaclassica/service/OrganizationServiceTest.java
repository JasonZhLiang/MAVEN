package com.linguaclassica.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityOrgModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.repository.RepositoryConfiguration;

/**
 * Test the functions in the OrgsnizationService layer.
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
public class OrganizationServiceTest extends AbstractTransactionalJUnit4SpringContextTests
{
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private OrganizationService organizationService;
	
	// variables
	private String sFirstOrg = "FirstJobsON";
	private String sFirstEmail = "exec@firstjobson.edu";
	private String sSecondOrg = "SecondCareerON";
	private String sSecondEmail = "contactus@secondcareeron.edu";
	
	private EntityOrgModel oeFirstOrg = null;
	private EntityOrgModel oeSecondOrg = null;

	private void createFirstOrganization()
	{
		oeFirstOrg = (EntityOrgModel) modelFactory.getNewOrgModel();
		oeFirstOrg.setOrgname(sFirstOrg);
		oeFirstOrg.setOrgemail(sFirstEmail);
	}
	
	private void createSecondOrganization()
	{
		oeSecondOrg = (EntityOrgModel) modelFactory.getNewOrgModel();
		oeSecondOrg.setOrgname(sSecondOrg);
		oeSecondOrg.setOrgemail(sSecondEmail);
	}
	
	@Before
	public void setUp() throws Exception
	{
		// Create a default organization for ALL of the tests
		createFirstOrganization();
		
		// Create a default user for many of the tests
		//createSecondUser();

		// Create another organization for many of the tests
		createSecondOrganization();
		
		// Create another user for many of the tests
		//createFourthUser();
		
		// Create another user for many of the tests
		//createFifthUser();
		
		// Create another user for many of the tests
		//createSixthUser();
	}

	@After
	public void tearDown() throws Exception
	{
	}
	
	@Test
	public void testCreateOrganization_OK()
	{
		Integer resultid = organizationService.createOrganization(oeFirstOrg);
		
		assertTrue(resultid > 0);
		
		EntityOrgModel oeCheck = organizationService.findOrganizationById(resultid);
		
		assertNotNull(oeCheck);
		assertEquals(oeCheck.getOrgname(), sFirstOrg);
		assertEquals(oeCheck.getOrgemail(), sFirstEmail);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testCreateOrganization_Same()
	{
		// Create the first organization
		Integer resultid = organizationService.createOrganization(oeFirstOrg);
		
		assertTrue(resultid > 0);

		// Create a second organization with the same values
		EntityOrgModel oeSameOrg = (EntityOrgModel) modelFactory.getNewOrgModel();
		oeSameOrg.setOrgname(sFirstOrg);
		oeSameOrg.setOrgemail(sFirstEmail);
		
		organizationService.createOrganization(oeSameOrg);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testCreateOrganization_Fails()
	{
		// Create an organization with invalid code
		EntityOrgModel oeBadOrg = (EntityOrgModel) modelFactory.getNewOrgModel();
		oeBadOrg.setOrgname("A Really Long Organization Name Intending to Fail the Creation Test");
		
		organizationService.createOrganization(oeBadOrg);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testFindOrganizationById_Fails()
	{
		// Create the first organization
		Integer resultid = organizationService.createOrganization(oeFirstOrg);
		
		assertTrue(resultid > 0);

		EntityOrgModel oeNoOrg = organizationService.findOrganizationById(1000000 + resultid);
		
		// Should not return object
		assertNull(oeNoOrg);
	}

	@Test
	public void testUpdateOrganization_OK()
	{
		String sOtherName = "EntryLevelJobs";
		String sOtherEmail = "publicrelations@firstjobson.edu";
		
		// Create the first organization
		Integer resultid = organizationService.createOrganization(oeFirstOrg);
		
		// Make some changes and update
		oeFirstOrg.setOrgname(sOtherName);
		oeFirstOrg.setOrgemail(sOtherEmail);
		organizationService.updateOrganization(oeFirstOrg);
		
		assertTrue(resultid == oeFirstOrg.getId());
		assertEquals(resultid, oeFirstOrg.getId());
		
		// Retrieve the updated organization
		EntityOrgModel oeCheck = organizationService.findOrganizationById(resultid);
		
		assertNotNull(oeCheck);
		assertEquals(oeCheck.getOrgname(), sOtherName);
		assertEquals(oeCheck.getOrgemail(), sOtherEmail);
	}

	@Test(expected=InvalidDataAccessApiUsageException.class)
	public void testUpdateOrganization_None()
	{
		// Update an uncreated organization
		organizationService.updateOrganization(oeFirstOrg);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test(expected=DataIntegrityViolationException.class)
	public void testUpdateOrganization_Fails()
	{
		// Create the first organization
		@SuppressWarnings("unused")
		Integer resultid = organizationService.createOrganization(oeFirstOrg);
		
		// Make some changes using invalid data and update
		oeFirstOrg.setOrgemail("thelongwindedandhugelynamedpompusadministrator@someflybynightagency@edu");
		organizationService.updateOrganization(oeFirstOrg);
		
		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void testGetAll_Start()
	{
		List<EntityOrgModel> list = organizationService.getAll();
		
		assertNotNull(list);
		assertTrue(list.size() >= 0);
	}

	@Test
	public void testGetAll_OK()
	{
		List<EntityOrgModel> listStart = organizationService.getAll();
		
		// Create the first organization
		@SuppressWarnings("unused")
		Integer firstid = organizationService.createOrganization(oeFirstOrg);
		
		// Create the second organization
		@SuppressWarnings("unused")
		Integer secondid = organizationService.createOrganization(oeSecondOrg);
		
		List<EntityOrgModel> listAfter = organizationService.getAll();
		
		assertNotNull(listAfter);
		assertTrue(listAfter.size() >= 2);
		assertEquals(2, listAfter.size() - listStart.size());
	}
}
