package com.linguaclassica.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.linguaclassica.entity.EntityOrgModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.repository.RepositoryConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RepositoryConfiguration.class})
@TransactionConfiguration
@Transactional
public class MessageServiceTest extends AbstractTransactionalJUnit4SpringContextTests
{
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private OrganizationService organizationService;

	// variables
	private String sFirstOrg = "FirstJobsON";
	private String sFirstEmail = "exec@firstjobson.edu";

	private EntityOrgModel oeFirstOrg = null;
	private Integer organizationId;

	private void createFirstOrganization()
	{
		oeFirstOrg = (EntityOrgModel) modelFactory.getNewOrgModel();
		oeFirstOrg.setOrgname(sFirstOrg);
		oeFirstOrg.setOrgemail(sFirstEmail);
		organizationId = organizationService.createOrganization(oeFirstOrg);	}
	
	@Before
	public void setUp() throws Exception
	{
		// Create a default organization for ALL of the tests
		createFirstOrganization();
		
		// Create a default user for many of the tests
		//createSecondUser();

		// Create another organization for many of the tests
		//createSecondOrganization();
		
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
	public void testCreateMessage_OK()
	{
		assertTrue(false);
	}
}
