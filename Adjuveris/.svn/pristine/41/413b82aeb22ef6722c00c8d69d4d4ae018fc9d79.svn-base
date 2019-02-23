package com.linguaclassica.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

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

import com.linguaclassica.access.LoginHelper;
import com.linguaclassica.entity.EntityTermModel;
import com.linguaclassica.entity.EntityTermTeachersModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.InstitutionModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.TermModel;
import com.linguaclassica.model.TermTeachersModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.repository.RepositoryConfiguration;

/**
 * Test the functions in the TermTeacherRepository layer.
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
public class TermUserTest extends AbstractTransactionalJUnit4SpringContextTests
{
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private InstitutionService instService;
	
	@Autowired
	private TermService termService;
	
	@Autowired
	private UserService userService;

	private String sFirstInstitution = "uArcturus Test";
	private String sFirstPostad = "1000 University Drive, Startown, ON";
	private String sFirstContact = "Registration Office";
	private String sFirstPhone = "1-613-555-1000";
	private String sFirstInstEmail = "registration@uarcturus.edu";
	private String sFirstAdmingiven = "Primus";
	private String sFirstAdminfamily = "Bureaucratus";
	private String sFirstAdminemail = "prime.bear@uarcturus.edu";

	private String sThirdInstitution = "uCardriver Test";
	private String sThirdPostad = "2000 Racetrack Road, Autoville, ON";
	private String sThirdContact = "Racing Office";
	private String sThirdPhone = "1-613-555-2000";
	private String sThirdInstEmail = "registration@ucardriver.edu";
	private String sThirdAdmingiven = "Backseat";
	private String sThirdAdminfamily = "Driver";
	private String sThirdAdminemail = "bdriver@ucardriver.edu";

	private String sFirstTermname = "Fall 2017";
	private String sFirstStartdate = "2017-09-01";
	private String sFirstEnddate = "2017-12-31";

	private String sFourthTermname = "Winter 2018";
	private String sFourthStartdate = "2018-01-02";
	private String sFourthEnddate = "2018-04-27";

	private String sFifthTermname = "Spring 2018";
	private String sFifthStartdate = "2018-05-01";
	private String sFifthEnddate = "2018-08-31";

	private String sEighthTermname = "Fall 2018";
	private String sEighthStartdate = "2018-09-04";
	private String sEighthEnddate = "2018-12-21";

	private String sFirstGiven = "Hannah";
	private String sFirstFamily = "Lector";
	private String sFirstEmail = "hannah.lector@uarcturus.edu";
	private String sFirstUsernum = "171234001";
	private String sFirstPassword = "HereToTeachMuch";
	
	private String sSecondGiven = "Anna";
	private String sSecondFamily = "Lestor";
	private String sSecondEmail = "anna.lestor@uarcturus.edu";
	private String sSecondUsernum = "171234002";
	private String sSecondPassword = "HereToWorkLess";
	
	private String sSixthGiven = "Anne";
	private String sSixthFamily = "Preacher";
	private String sSixthEmail = "anne.preacherr@uarcturus.edu";
	private String sSixthUsernum = "171234006";
	private String sSixthPassword = "HereToPreachMore";
	
	private String sSeventhGiven = "Harold";
	private String sSeventhFamily = "Bleeker";
	private String sSeventhEmail = "harold.bleeker@uarcturus.edu";
	private String sSeventhUsernum = "171234007";
	private String sSeventhPassword = "HereIsBleakPlace";
	
	private InstitutionModel imFirstInst = null;
	private InstitutionModel imThirdInst = null;
	private TermModel tmFirstTerm = null;
	private TermModel tmFourthTerm = null;
	private TermModel tmFifthTerm = null;
	private TermModel tmEighthTerm = null;
	private EntityUserModel umDefaultUser = null;
	private EntityUserModel umSecondUser = null;
	private EntityUserModel umSixthUser = null;
	private EntityUserModel umSeventhUser = null;

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
		imFirstInst.setPrimeemail(sFirstInstEmail);
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
		imThirdInst.setPrimeemail(sThirdInstEmail);
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
	
	private void createEighthTerm(int institutionId)
	{
		tmEighthTerm = modelFactory.getNewTermModel();
		tmEighthTerm.setTermname(sEighthTermname);
		tmEighthTerm.setInstid(institutionId);
		tmEighthTerm.setStartDate(Date.valueOf(sEighthStartdate));
		tmEighthTerm.setEndDate(Date.valueOf(sEighthEnddate));
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
	
	private void createSixthUser()
	{
		umSixthUser = (EntityUserModel) modelFactory.getNewUserModel();
		umSixthUser.setFirstName(sSixthGiven);
		umSixthUser.setLastName(sSixthFamily);
		umSixthUser.setEmailAddress(sSixthEmail);
		umSixthUser.setStudentnumber(sSixthUsernum);
		String encrypted = "TooLongForValidPasswordWithBad(@)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sSixthPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		umSixthUser.setPassword(encrypted);
	}
	
	private void createSeventhUser()
	{
		umSeventhUser = (EntityUserModel) modelFactory.getNewUserModel();
		umSeventhUser.setFirstName(sSeventhGiven);
		umSeventhUser.setLastName(sSeventhFamily);
		umSeventhUser.setEmailAddress(sSeventhEmail);
		umSeventhUser.setStudentnumber(sSeventhUsernum);
		String encrypted = "TooLongForValidPasswordWithBad(@)Chars";
		try
		{
			encrypted = LoginHelper.encodePassword(sSeventhPassword);
		}
		catch(Exception e)
		{
			assertTrue(false);		
		}
		umSeventhUser.setPassword(encrypted);
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
		
		// Create a default user for many of the tests
		createDefaultUser();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCreateTermTeacher_OK()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);

		userService.createUser(umDefaultUser, Permission.TEACHER, imFirstInst.getId());
		
		assertTrue(umDefaultUser.getId() > 0);
		
		TermTeachersModel termTeacherModel = modelFactory.getNewTermTeachersModel();
		termTeacherModel.setTermid(tmFirstTerm.getId());
		termTeacherModel.setTeacherid(umDefaultUser.getId());
		Integer ttid = termService.createTermTeacher((EntityTermTeachersModel) termTeacherModel);
		
		assertTrue(ttid > 0);
	}

	@Test
	public void testCreateTermTeacher_Same()
	{
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);

		userService.createUser(umDefaultUser, Permission.TEACHER, imFirstInst.getId());
		
		assertTrue(umDefaultUser.getId() > 0);
		
		TermTeachersModel termTeacherModel = modelFactory.getNewTermTeachersModel();
		termTeacherModel.setTermid(tmFirstTerm.getId());
		termTeacherModel.setTeacherid(umDefaultUser.getId());
		Integer tt1id = termService.createTermTeacher((EntityTermTeachersModel) termTeacherModel);
		
		assertTrue(tt1id > 0);

		// Try to create term-teacher item again
		TermTeachersModel termTeach2Model = modelFactory.getNewTermTeachersModel();
		termTeach2Model.setTermid(tmFirstTerm.getId());
		termTeach2Model.setTeacherid(umDefaultUser.getId());
		Integer tt2id = termService.createTermTeacher((EntityTermTeachersModel) termTeach2Model);
		
		assertTrue(0 == tt2id);
	}

	@Test
	public void testGetListOfTeacherIdsByTermId_OK()
	{
		// Create two terms
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);
		
		createFourthTerm(imFirstInst.getId());
		termService.createTerm(tmFourthTerm);
		
		assertTrue(tmFourthTerm.getId() > 0);

		// Create four teachers
		userService.createUser(umDefaultUser, Permission.TEACHER, imFirstInst.getId());
		
		assertTrue(umDefaultUser.getId() > 0);
		
		createSecondUser();
		userService.createUser(umSecondUser, Permission.TEACHER, imFirstInst.getId());
		
		assertTrue(umSecondUser.getId() > 0);
		
		createSixthUser();
		userService.createUser(umSixthUser, Permission.TEACHER, imFirstInst.getId());
		
		assertTrue(umSixthUser.getId() > 0);
		
		createSeventhUser();
		userService.createUser(umSeventhUser, Permission.TEACHER, imFirstInst.getId());
		
		assertTrue(umSeventhUser.getId() > 0);
		
		// Assign three teachers to the first term
		TermTeachersModel tt1Model = modelFactory.getNewTermTeachersModel();
		tt1Model.setTermid(tmFirstTerm.getId());
		tt1Model.setTeacherid(umDefaultUser.getId());
		Integer tt1id = termService.createTermTeacher((EntityTermTeachersModel) tt1Model);
		
		assertTrue(tt1id > 0);
		
		TermTeachersModel tt2Model = modelFactory.getNewTermTeachersModel();
		tt2Model.setTermid(tmFirstTerm.getId());
		tt2Model.setTeacherid(umSecondUser.getId());
		Integer tt2id = termService.createTermTeacher((EntityTermTeachersModel) tt2Model);
		
		assertTrue(tt2id > 0);
		
		TermTeachersModel tt3Model = modelFactory.getNewTermTeachersModel();
		tt3Model.setTermid(tmFirstTerm.getId());
		tt3Model.setTeacherid(umSixthUser.getId());
		Integer tt3id = termService.createTermTeacher((EntityTermTeachersModel) tt3Model);
		
		assertTrue(tt3id > 0);
		
		// Assign one teacher to the other term
		TermTeachersModel tt4Model = modelFactory.getNewTermTeachersModel();
		tt4Model.setTermid(tmFourthTerm.getId());
		tt4Model.setTeacherid(umSeventhUser.getId());
		Integer tt4id = termService.createTermTeacher((EntityTermTeachersModel) tt4Model);
		
		assertTrue(tt4id > 0);
		
		// Test the method
		List<Integer> list1 = termService.getListOfTeacherIdsByTermId(tmFirstTerm.getId());
		
		assertTrue(3 == list1.size());
		assertTrue(list1.contains(umDefaultUser.getId()));
		assertTrue(list1.contains(umSecondUser.getId()));
		assertTrue(list1.contains(umSixthUser.getId()));
		
		List<Integer> list2 = termService.getListOfTeacherIdsByTermId(tmFourthTerm.getId());
		
		assertTrue(1 == list2.size());
		assertTrue(list2.contains(umSeventhUser.getId()));
	}

	@Test
	public void testGetTermsByInstTeacher()
	{
		// Create four terms for two institutions
		createFirstTerm(imFirstInst.getId());
		termService.createTerm(tmFirstTerm);
		
		assertTrue(tmFirstTerm.getId() > 0);
		
		createFourthTerm(imFirstInst.getId());
		termService.createTerm(tmFourthTerm);
		
		assertTrue(tmFourthTerm.getId() > 0);
		
		createFifthTerm(imThirdInst.getId());
		termService.createTerm(tmFifthTerm);
		
		assertTrue(tmFifthTerm.getId() > 0);
		
		createEighthTerm(imFirstInst.getId());
		termService.createTerm(tmEighthTerm);
		
		assertTrue(tmFourthTerm.getId() > 0);

		// Create two teachers in two institutions
		userService.createUser(umDefaultUser, Permission.TEACHER, imFirstInst.getId());
		
		assertTrue(umDefaultUser.getId() > 0);
		
		createSecondUser();
		userService.createUser(umSecondUser, Permission.TEACHER, imThirdInst.getId());
		
		// Assign a teacher to three terms
		TermTeachersModel tt1Model = modelFactory.getNewTermTeachersModel();
		tt1Model.setTermid(tmFirstTerm.getId());
		tt1Model.setTeacherid(umDefaultUser.getId());
		Integer tt1id = termService.createTermTeacher((EntityTermTeachersModel) tt1Model);
		
		assertTrue(tt1id > 0);
		
		TermTeachersModel tt2Model = modelFactory.getNewTermTeachersModel();
		tt2Model.setTermid(tmFourthTerm.getId());
		tt2Model.setTeacherid(umDefaultUser.getId());
		Integer tt2id = termService.createTermTeacher((EntityTermTeachersModel) tt2Model);
		
		assertTrue(tt2id > 0);
		
		TermTeachersModel tt3Model = modelFactory.getNewTermTeachersModel();
		tt3Model.setTermid(tmEighthTerm.getId());
		tt3Model.setTeacherid(umDefaultUser.getId());
		Integer tt3id = termService.createTermTeacher((EntityTermTeachersModel) tt3Model);
		
		assertTrue(tt3id > 0);
		
		// Assign one teacher to the other term
		TermTeachersModel tt4Model = modelFactory.getNewTermTeachersModel();
		tt4Model.setTermid(tmFifthTerm.getId());
		tt4Model.setTeacherid(umSecondUser.getId());
		Integer tt4id = termService.createTermTeacher((EntityTermTeachersModel) tt4Model);
		
		assertTrue(tt4id > 0);
		
		// Test the method
		List<EntityTermModel> list1 = termService.getTermsByInstTeacher(imFirstInst.getId(), umDefaultUser.getId());
		
		assertTrue(3 == list1.size());
		assertTrue(list1.contains(tmFirstTerm));
		assertTrue(list1.contains(tmFourthTerm));
		assertTrue(list1.contains(tmEighthTerm));
		
		List<EntityTermModel> list2 = termService.getTermsByInstTeacher(imThirdInst.getId(), umSecondUser.getId());
		
		assertTrue(1 == list2.size());
		assertTrue(list2.contains(tmFifthTerm));
	}

	@Test
	public void test()
	{
		fail("Not yet implemented");
	}

	@Test
	public void test2() //getListOfTermTeachersById
	{
		fail("Not yet implemented");
	}

	@Test
	public void test3() //deleteTermTeacher
	{
		fail("Not yet implemented");
	}

	@Test
	public void test4() //checkTermTeacher
	{
		fail("Not yet implemented");
	}
}
