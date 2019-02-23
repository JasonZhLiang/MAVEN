package com.linguaclassica.service;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.Instant;

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

import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.repository.RepositoryConfiguration;

/**
 * Test the functions in the PermissionService layer.
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
public class PermissionServiceTest extends AbstractTransactionalJUnit4SpringContextTests
{
	
	@Autowired
	private PermissionService permService;
	
	@Before
	public void setUp() throws Exception
	{
		
	}

	@After
	public void tearDown() throws Exception
	{
	}
	
	// Must use jpaVendorAdapter.setShowSql(false); in RepositoryConfiguration
	@Test
	public void testAccessDuration()
	{
		Integer limit = 1000;
		
		// Test the method
		Integer clientSumM = 0;
		Integer consultSumM = 0;
		Integer offadSumM = 0;
		Integer adminSumM = 0;

		Instant beforeM = Instant.now();
		for (Integer count = 0; count < limit; count++)
		{
			clientSumM += permService.getPermissionIdFromDB(Permission.CLIENT);
			consultSumM += permService.getPermissionIdFromDB(Permission.CONSULTANT);
			offadSumM += permService.getPermissionIdFromDB(Permission.OFFICE_ADMIN);
			adminSumM += permService.getPermissionIdFromDB(Permission.ADMINISTRATOR);
		}
		Instant afterM = Instant.now();
		
		assertTrue(clientSumM == (limit * 1));
		assertTrue(consultSumM == (limit * 2));
		assertTrue(offadSumM == (limit * 3));
		assertTrue(adminSumM == (limit * 4));
		
		// Test the query model method
		Integer clientSumQ = 0;
		Integer consultSumQ = 0;
		Integer offadSumQ = 0;
		Integer adminSumQ = 0;

		Instant beforeQ = Instant.now();
		for (Integer count = 0; count < limit; count++)
		{
			clientSumQ += permService.getPermissionModel(Permission.CLIENT).getId();
			consultSumQ += permService.getPermissionModel(Permission.CONSULTANT).getId();
			offadSumQ += permService.getPermissionModel(Permission.OFFICE_ADMIN).getId();
			adminSumQ += permService.getPermissionModel(Permission.ADMINISTRATOR).getId();
		}
		Instant afterQ = Instant.now();
		
		assertTrue(clientSumQ == (limit * 1));
		assertTrue(consultSumQ == (limit * 2));
		assertTrue(offadSumQ == (limit * 3));
		assertTrue(adminSumQ == (limit * 4));
		
		// Test the cached method
		Integer clientSumC = 0;
		Integer consultSumC = 0;
		Integer offadSumC = 0;
		Integer adminSumC = 0;

		Instant beforeC = Instant.now();
		for (Integer count = 0; count < limit; count++)
		{
			clientSumC += permService.getCachedPermissionId(Permission.CLIENT);
			consultSumC += permService.getCachedPermissionId(Permission.CONSULTANT);
			offadSumC += permService.getCachedPermissionId(Permission.OFFICE_ADMIN);
			adminSumC += permService.getCachedPermissionId(Permission.ADMINISTRATOR);
		}
		Instant afterC = Instant.now();
		
		assertTrue(clientSumC == (limit * 1));
		assertTrue(consultSumC == (limit * 2));
		assertTrue(offadSumC == (limit * 3));
		assertTrue(adminSumC == (limit * 4));

		Instant beforeD = Instant.now();
		for (Integer count = 0; count < limit; count++)
		{
			clientSumC += permService.getPermissionId(Permission.CLIENT);
			consultSumC += permService.getPermissionId(Permission.CONSULTANT);
			offadSumC += permService.getPermissionId(Permission.OFFICE_ADMIN);
			adminSumC += permService.getPermissionId(Permission.ADMINISTRATOR);
		}
		Instant afterD = Instant.now();
		
		// Show the results
		long msM = Duration.between(beforeM, afterM).toMillis();
		System.out.println("Using getPermissionIdFromDB(Permission.XXXX)");
		System.out.println(limit + " method iterations took " + msM + " ms");
		long msQ = Duration.between(beforeQ, afterQ).toMillis();
		System.out.println("Using getPermissionModel(Permission.XXXX).getId()");
		System.out.println(limit + " query iterations took " + msQ + " ms");
		long msC = Duration.between(beforeC, afterC).toMillis();
		System.out.println("Using getCachedPermissionId(Permission.XXXX)");
		System.out.println(limit + " cached iterations took " + msC + " ms");
		long msD = Duration.between(beforeD, afterD).toMillis();
		System.out.println("Using getPermissionId(Permission.XXXX)");
		System.out.println(limit + " ordinal iterations took " + msD + " ms");
	}
	
	@Test
	public void testOrdinalValues()
	{
		boolean bSameClient = permService.getPermissionIdFromDB(Permission.CLIENT) == Permission.CLIENT.ordinal();
		boolean bSameConslt = permService.getPermissionIdFromDB(Permission.CONSULTANT) == Permission.CONSULTANT.ordinal();
		boolean bSameOffAdm = permService.getPermissionIdFromDB(Permission.OFFICE_ADMIN) == Permission.OFFICE_ADMIN.ordinal();
		boolean bSameAdmini = permService.getPermissionIdFromDB(Permission.ADMINISTRATOR) == Permission.ADMINISTRATOR.ordinal();
		
		System.out.println("Client same " + bSameClient);
		System.out.println("Consultant same " + bSameConslt);
		System.out.println("Office Admin same " + bSameOffAdm);
		System.out.println("Administrator same " + bSameAdmini);
	}
}
