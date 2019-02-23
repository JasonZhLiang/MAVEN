package com.linguaclassica.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.linguaclassica.access.LoginHelper;
import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.entity.EntityNotificationClassesModel;
import com.linguaclassica.entity.EntityNotificationModel;
import com.linguaclassica.entity.EntityUserModel;
import com.linguaclassica.model.InstitutionModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.NotificationModel;
import com.linguaclassica.model.TermModel;
import com.linguaclassica.model.PermissionModel.Permission;
import com.linguaclassica.service.exception.UnknownEntityException;
import com.linguaclassica.repository.RepositoryConfiguration;
import com.linguaclassica.service.exception.EntityAlreadyExistsException;
import com.linguaclassica.service.exception.ServiceException;

/**
 * Test the functions in the NotificationService layer.
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
public class NotificationServiceTest extends AbstractTransactionalJUnit4SpringContextTests
{
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private InstitutionService instService;
	
	@Autowired
	private TermService termService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	protected NotificationService notificationService;

	private String sFirstTermname = "Fall 2017";
	private String sFirstStartdate = "2017-09-01";
	private String sFirstEnddate = "2017-12-31";
	
	private String sFirstGiven = "Hannah";
	private String sFirstFamily = "Lector";
	private String sFirstEmail = "hannah.lector@uarcturus.edu";
	private String sFirstUsernum = "171234001";
	private String sFirstPassword = "HereToLearnMuch";
	
	private InstitutionModel imDefaultInstitution = null;
	private TermModel tmFirstTerm = null;
	private EntityUserModel umDefaultUser = null;
	
	private String sFirstNote = "Make a wreath.";
	private String sSecondNote = "Stuff a turkey.";
	private String sThirdNote = "Carve a pumpkin.";
	private String sFourthNote = "Eat Halloween candy.";
	private String sAlteredNote = "Make a cedar wreath.";

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
	
	private void createFirstTerm(int institutionId)
	{
		tmFirstTerm = modelFactory.getNewTermModel();
		tmFirstTerm.setTermname(sFirstTermname);
		tmFirstTerm.setInstid(institutionId);
		tmFirstTerm.setStartDate(Date.valueOf(sFirstStartdate));
		tmFirstTerm.setEndDate(Date.valueOf(sFirstEnddate));
		termService.createTerm(tmFirstTerm);
	}

	/**
	 * Create a class using passed values
	 * @param cname
	 * @param ucode
	 * @param tm
	 * @return
	 */
	private EntityClassModel createAClass(String cname, String ucode, TermModel tm)
	{
		EntityClassModel cm = (EntityClassModel) modelFactory.getNewClassModel();
		cm.setClassname(cname);
		cm.setClasscode(ucode);
		//cm.setStartDate(tm.getStartDate());
		//cm.setEndDate(tm.getEndDate());
		cm.setTermid(tm.getId());
		return cm;
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
	
	private NotificationModel createFirstNotification()
	{
		NotificationModel nm = modelFactory.getNewNotificationModel();
		nm.setNote(sFirstNote);
		LocalDateTime ldtStart = LocalDateTime.of(2017, 11, 10, 9, 0);
		Timestamp tsStart = Timestamp.valueOf(ldtStart);
		nm.setStartDate(tsStart);
		LocalDateTime ldtExpiry = LocalDateTime.of(2017, 11, 11, 9, 30);
		Timestamp tsExpiry = Timestamp.valueOf(ldtExpiry);
		nm.setExpiryDate(tsExpiry);
		nm.setTimeZone("EST");
		nm.setCreatedById(umDefaultUser.getId());
		return nm;
	}
	
	private NotificationModel createSecondNotification()
	{
		NotificationModel nm = modelFactory.getNewNotificationModel();
		nm.setNote(sSecondNote);
		LocalDateTime ldtStart = LocalDateTime.of(2017, 10, 9, 11, 15);
		Timestamp tsStart = Timestamp.valueOf(ldtStart);
		nm.setStartDate(tsStart);
		LocalDateTime ldtExpiry = LocalDateTime.of(2017, 10, 9, 12, 0);
		Timestamp tsExpiry = Timestamp.valueOf(ldtExpiry);
		nm.setExpiryDate(tsExpiry);
		nm.setTimeZone("EST");
		nm.setCreatedById(umDefaultUser.getId());
		return nm;
	}
	
	private NotificationModel createThirdNotification()
	{
		NotificationModel nm = modelFactory.getNewNotificationModel();
		nm.setNote(sThirdNote);
		LocalDateTime ldtStart = LocalDateTime.of(2017, 10, 30, 19, 0);
		Timestamp tsStart = Timestamp.valueOf(ldtStart);
		nm.setStartDate(tsStart);
		LocalDateTime ldtExpiry = LocalDateTime.of(2017, 10, 30, 19, 45);
		Timestamp tsExpiry = Timestamp.valueOf(ldtExpiry);
		nm.setExpiryDate(tsExpiry);
		nm.setTimeZone("EST");
		nm.setCreatedById(umDefaultUser.getId());
		return nm;
	}
	
	private NotificationModel createFourthNotification()
	{
		NotificationModel nm = modelFactory.getNewNotificationModel();
		nm.setNote(sFourthNote);
		LocalDateTime ldtStart = LocalDateTime.of(2017, 10, 31, 20, 30);
		Timestamp tsStart = Timestamp.valueOf(ldtStart);
		nm.setStartDate(tsStart);
		LocalDateTime ldtExpiry = LocalDateTime.of(2017, 11, 30, 21, 15);
		Timestamp tsExpiry = Timestamp.valueOf(ldtExpiry);
		nm.setExpiryDate(tsExpiry);
		nm.setTimeZone("EST");
		nm.setCreatedById(umDefaultUser.getId());
		return nm;
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
	public void TestCreateNotification_OK()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		try
		{
			NotificationModel nm = createFirstNotification();
			notificationService.createNotification(nm);
			
			assertTrue(nm.getId() > 0);
		}
		catch (EntityAlreadyExistsException eaee)
		{
			assertFalse(true);
		}
		catch (ServiceException se)
		{
			assertFalse(true);
		}
		catch (Exception e)
		{
			assertFalse(true);
		}
	}
	
	@Test
	public void TestFindNotificationById_OK()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		try
		{
			// create some notifications
			NotificationModel nm1 = createFirstNotification();
			notificationService.createNotification(nm1);
			
			assertTrue(nm1.getId() > 0);

			NotificationModel nm2 = createSecondNotification();
			notificationService.createNotification(nm2);
			
			assertTrue(nm2.getId() > 0);

			NotificationModel nm3 = createThirdNotification();
			notificationService.createNotification(nm3);
			
			assertTrue(nm3.getId() > 0);

			NotificationModel nm4 = createFourthNotification();
			notificationService.createNotification(nm4);
			
			assertTrue(nm4.getId() > 0);
			
			// verify that the notifications can be found
			EntityNotificationModel en1 = notificationService.findNotificationById(nm1.getId());
			
			assertTrue(0 == en1.getNote().compareTo(sFirstNote));
			
			EntityNotificationModel en3 = notificationService.findNotificationById(nm3.getId());
			
			assertTrue(0 == en3.getNote().compareTo(sThirdNote));
		}
		catch (EntityAlreadyExistsException eaee)
		{
			assertFalse(true);
		}
		catch (ServiceException se)
		{
			assertFalse(true);
		}
	}
	
	@Test(expected=UnknownEntityException.class)
	public void TestFindNotificationById_Bad()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// create a notification
		NotificationModel nm1 = createFirstNotification();
		notificationService.createNotification(nm1);
		
		assertTrue(nm1.getId() > 0);
		
		// verify that the notifications can be found
		@SuppressWarnings("unused")
		EntityNotificationModel en1 = notificationService.findNotificationById(1000000 + nm1.getId());
		
		// should not reach here
		assertTrue(false);
	}
	
	@Test
	public void TestFindNotificationsByClassIds_OK()
	{
		createFirstTerm(imDefaultInstitution.getId());
		EntityClassModel cm1 = createAClass("Latin 101", "2017LTN1", tmFirstTerm);
		int class1id = classService.createClass(cm1);
		EntityClassModel cm2 = createAClass("Greek 202", "2017GRK2", tmFirstTerm);
		int class2id = classService.createClass(cm2);
		EntityClassModel cm3 = createAClass("Spanglish 303", "2017SP3", tmFirstTerm);
		int class3id = classService.createClass(cm3);
		
		// see what is in the repository
		Long count = notificationService.getNotificationCount();
		List<EntityNotificationModel> all = new ArrayList<EntityNotificationModel>();
		if (count > 0)
		{
			all = notificationService.getAllNotifications();
		}

		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// create some notifications
		NotificationModel nm1 = createFirstNotification();
		notificationService.createNotification(nm1);
		
		assertTrue(nm1.getId() > 0);

		NotificationModel nm2 = createSecondNotification();
		notificationService.createNotification(nm2);
		
		assertTrue(nm2.getId() > 0);

		NotificationModel nm3 = createThirdNotification();
		notificationService.createNotification(nm3);
		
		assertTrue(nm3.getId() > 0);

		NotificationModel nm4 = createFourthNotification();
		notificationService.createNotification(nm4);
		
		assertTrue(nm4.getId() > 0);
		
		// assignment notifications to classes
		EntityNotificationClassesModel cne1 = (EntityNotificationClassesModel) modelFactory.getNewNotificationClassesModel();
		cne1.setclassId(class1id);
		cne1.setnotificationId(nm1.getId());
		notificationService.createNotificationClasses(cne1);
		
		EntityNotificationClassesModel cne2 = (EntityNotificationClassesModel) modelFactory.getNewNotificationClassesModel();
		cne2.setclassId(class1id);
		cne2.setnotificationId(nm2.getId());
		notificationService.createNotificationClasses(cne2);
		
		EntityNotificationClassesModel cne3 = (EntityNotificationClassesModel) modelFactory.getNewNotificationClassesModel();
		cne3.setclassId(class1id);
		cne3.setnotificationId(nm3.getId());
		notificationService.createNotificationClasses(cne3);
		
		EntityNotificationClassesModel cne4 = (EntityNotificationClassesModel) modelFactory.getNewNotificationClassesModel();
		cne4.setclassId(class1id);
		cne4.setnotificationId(nm4.getId());
		notificationService.createNotificationClasses(cne4);
		
		EntityNotificationClassesModel cne5 = (EntityNotificationClassesModel) modelFactory.getNewNotificationClassesModel();
		cne5.setclassId(class2id);
		cne5.setnotificationId(nm3.getId());
		notificationService.createNotificationClasses(cne5);
		
		EntityNotificationClassesModel cne6 = (EntityNotificationClassesModel) modelFactory.getNewNotificationClassesModel();
		cne6.setclassId(class2id);
		cne6.setnotificationId(nm4.getId());
		notificationService.createNotificationClasses(cne6);
		
		EntityNotificationClassesModel cne7 = (EntityNotificationClassesModel) modelFactory.getNewNotificationClassesModel();
		cne7.setclassId(class3id);
		cne7.setnotificationId(nm1.getId());
		notificationService.createNotificationClasses(cne7);
		
		// get the notifications for some classes
		List<Integer> classIds = new ArrayList<Integer>();
		classIds.add(cm2.getId());
		classIds.add(cm3.getId());

		List<NotificationModel> notices = notificationService.findNotificationsByClassIds(classIds);

		assertNotNull(notices);
		assertTrue(notices.size() == 3);

		// get the notifications for more classes
		classIds.add(cm1.getId());

		notices = notificationService.findNotificationsByClassIds(classIds);

		assertNotNull(notices);
		assertTrue(notices.size() == 4);
	}
	
	@Test
	public void TestFindNotificationsByClassIds_ClassList_OK()
	{
		createFirstTerm(imDefaultInstitution.getId());
		EntityClassModel cm1 = createAClass("Latin 101", "2017LTN1", tmFirstTerm);
		int class1id = classService.createClass(cm1);
		EntityClassModel cm2 = createAClass("Greek 202", "2017GRK2", tmFirstTerm);
		int class2id = classService.createClass(cm2);
		EntityClassModel cm3 = createAClass("Spanglish 303", "2017SP3", tmFirstTerm);
		int class3id = classService.createClass(cm3);
		
		// see what is in the repository
		Long count = notificationService.getNotificationCount();
		List<EntityNotificationModel> all = new ArrayList<EntityNotificationModel>();
		if (count > 0)
		{
			all = notificationService.getAllNotifications();
		}

		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// generate class list
		List<Integer> note1classes = new ArrayList<Integer>();
		note1classes.add(class1id);
		note1classes.add(class3id);
		
		List<Integer> note2classes = new ArrayList<Integer>();
		note2classes.add(class1id);
		
		List<Integer> note3classes = new ArrayList<Integer>();
		note3classes.add(class1id);
		note3classes.add(class2id);
		
		List<Integer> note4classes = new ArrayList<Integer>();
		note4classes.add(class1id);
		note4classes.add(class2id);
		
		// create some notifications
		EntityNotificationModel nm1 = (EntityNotificationModel) createFirstNotification();
		notificationService.createNotification(nm1, note1classes);
		
		assertTrue(nm1.getId() > 0);

		EntityNotificationModel nm2 = (EntityNotificationModel) createSecondNotification();
		notificationService.createNotification(nm2, note2classes);
		
		assertTrue(nm2.getId() > 0);

		EntityNotificationModel nm3 = (EntityNotificationModel) createThirdNotification();
		notificationService.createNotification(nm3, note3classes);
		
		assertTrue(nm3.getId() > 0);

		EntityNotificationModel nm4 = (EntityNotificationModel) createFourthNotification();
		notificationService.createNotification(nm4, note4classes);
		
		assertTrue(nm4.getId() > 0);
		
		// get the notifications for some classes
		List<Integer> classIds = new ArrayList<Integer>();
		classIds.add(cm2.getId());
		classIds.add(cm3.getId());

		List<NotificationModel> notices = notificationService.findNotificationsByClassIds(classIds);

		assertNotNull(notices);
		assertTrue(notices.size() == 3);

		// get the notifications for more classes
		classIds.add(cm1.getId());

		notices = notificationService.findNotificationsByClassIds(classIds);

		assertNotNull(notices);
		assertTrue(notices.size() == 4);
		
		// get the notifications for a single class
		notices = notificationService.findNotificationsByClassId("not used on 2017-07-18", class1id);

		assertNotNull(notices);
		assertTrue(notices.size() == 4);

		notices = notificationService.findNotificationsByClassId("not used on 2017-07-18", class2id);

		assertNotNull(notices);
		assertTrue(notices.size() == 2);

		notices = notificationService.findNotificationsByClassId("not used on 2017-07-18", class3id);

		assertNotNull(notices);
		assertTrue(notices.size() == 1);
	}
	
	@Test
	public void TestFindNotificationsByClassIds_Null()
	{
		List<Integer> classIds = null;

		List<NotificationModel> notices = notificationService.findNotificationsByClassIds(classIds);

		assertNotNull(notices);
		assertTrue(notices.size() == 0);
	}
	
	@Test(expected=InvalidDataAccessApiUsageException.class)
	public void TestFindNotificationsByClassIds_None()
	{
		List<Integer> classIds = new ArrayList<Integer>();

		List<NotificationModel> notices = notificationService.findNotificationsByClassIds(classIds);

		// should not reach here
		assertNotNull(notices);
		assertTrue(notices.size() == 0);
	}
	
	@Test
	public void TestFindNotificationsByClassIds_Empty()
	{
		createFirstTerm(imDefaultInstitution.getId());
		EntityClassModel cm = createAClass("LATIN 101", "2017LTN1", tmFirstTerm);
		classService.createClass(cm);
		
		// add invalid classes
		List<Integer> classIds = new ArrayList<Integer>();
		classIds.add(1000000 + cm.getId());
		classIds.add(1000001 + cm.getId());
		classIds.add(1000002 + cm.getId());

		List<NotificationModel> notices = notificationService.findNotificationsByClassIds(classIds);

		assertNotNull(notices);
		assertTrue(notices.size() == 0);
	}
	
	@Test
	public void TestUpdateNotification_None_None_OK()
	{
		createFirstTerm(imDefaultInstitution.getId());
		EntityClassModel cm1 = createAClass("Latin 101", "2017LTN1", tmFirstTerm);
		int class1id = classService.createClass(cm1);

		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// generate class list
		List<Integer> note1classes = new ArrayList<Integer>();
		note1classes.add(class1id);
		
		// create some notifications
		EntityNotificationModel nm1 = (EntityNotificationModel) createFirstNotification();
		notificationService.createNotification(nm1, note1classes);
		
		assertTrue(nm1.getId() > 0);
		
		Integer note1id = nm1.getId();
		
		// see what is in the repository
		Long ncount = notificationService.getNotificationCount();
		Long nccount = notificationService.getNotificationClassCount();
		
		// Alter and update the note with no class changes
		nm1.setNote(sAlteredNote);
		List<Integer> newClassIds = new ArrayList<Integer>();
		List<Integer> oldClassIds = new ArrayList<Integer>();
		notificationService.updateNotification(nm1, newClassIds, oldClassIds);
		
		EntityNotificationModel nma = notificationService.findNotificationById(note1id);
		
		// Confirm that the notification changed and the table count did not
		assertTrue(0 == nma.getNote().compareTo(sAlteredNote));
		assertTrue(notificationService.getNotificationCount() == ncount);
		assertTrue(notificationService.getNotificationClassCount() == nccount);
	}
	
	@Test
	public void TestUpdateNotification_Add_OK()
	{
		createFirstTerm(imDefaultInstitution.getId());
		EntityClassModel cm1 = createAClass("Latin 101", "2017LTN1", tmFirstTerm);
		int class1id = classService.createClass(cm1);
		EntityClassModel cm2 = createAClass("Greek 202", "2017GRK2", tmFirstTerm);
		int class2id = classService.createClass(cm2);
		EntityClassModel cm3 = createAClass("Spanglish 303", "2017SP3", tmFirstTerm);
		int class3id = classService.createClass(cm3);

		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// generate class list
		List<Integer> note1classes = new ArrayList<Integer>();
		note1classes.add(class1id);
		
		// create some notifications
		EntityNotificationModel nm1 = (EntityNotificationModel) createFirstNotification();
		notificationService.createNotification(nm1, note1classes);
		
		assertTrue(nm1.getId() > 0);
		
		Integer note1id = nm1.getId();
		
		// see what is in the repository
		Long ncount = notificationService.getNotificationCount();
		Long nccount = notificationService.getNotificationClassCount();
		
		// Alter and update the note with added classes
		nm1.setNote(sAlteredNote);
		List<Integer> newClassIds = new ArrayList<Integer>();
		newClassIds.add(class2id);
		newClassIds.add(class3id);
		List<Integer> oldClassIds = new ArrayList<Integer>();
		notificationService.updateNotification(nm1, newClassIds, oldClassIds);
		
		EntityNotificationModel nma = notificationService.findNotificationById(note1id);
		
		// Confirm that the notification changed and the table count did not
		assertTrue(0 == nma.getNote().compareTo(sAlteredNote));
		assertTrue(notificationService.getNotificationCount() == ncount);
		assertTrue(notificationService.getNotificationClassCount() == nccount + 2);
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void TestUpdateNotification_Add_Fail()
	{
		createFirstTerm(imDefaultInstitution.getId());
		EntityClassModel cm1 = createAClass("Latin 101", "2017LTN1", tmFirstTerm);
		int class1id = classService.createClass(cm1);
		EntityClassModel cm2 = createAClass("Greek 202", "2017GRK2", tmFirstTerm);
		int class2id = classService.createClass(cm2);
		EntityClassModel cm3 = createAClass("Spanglish 303", "2017SP3", tmFirstTerm);
		int class3id = classService.createClass(cm3);

		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// generate class list
		List<Integer> note1classes = new ArrayList<Integer>();
		note1classes.add(class1id);
		note1classes.add(class2id);
		
		// create some notifications
		EntityNotificationModel nm1 = (EntityNotificationModel) createFirstNotification();
		notificationService.createNotification(nm1, note1classes);
		
		assertTrue(nm1.getId() > 0);
		
		// Update the note with added classes
		List<Integer> newClassIds = new ArrayList<Integer>();
		newClassIds.add(class2id);
		newClassIds.add(class3id);
		List<Integer> oldClassIds = new ArrayList<Integer>();
		notificationService.updateNotification(nm1, newClassIds, oldClassIds);

		// Should not reach here
		assertTrue(false);
	}

	@Test
	public void TestUpdateNotification_Remove_OK()
	{
		createFirstTerm(imDefaultInstitution.getId());
		EntityClassModel cm1 = createAClass("Latin 101", "2017LTN1", tmFirstTerm);
		int class1id = classService.createClass(cm1);
		EntityClassModel cm2 = createAClass("Greek 202", "2017GRK2", tmFirstTerm);
		int class2id = classService.createClass(cm2);
		EntityClassModel cm3 = createAClass("Spanglish 303", "2017SP3", tmFirstTerm);
		int class3id = classService.createClass(cm3);

		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// generate class list
		List<Integer> note1classes = new ArrayList<Integer>();
		note1classes.add(class1id);
		note1classes.add(class2id);
		note1classes.add(class3id);
		
		// create some notifications
		EntityNotificationModel nm1 = (EntityNotificationModel) createFirstNotification();
		notificationService.createNotification(nm1, note1classes);
		
		assertTrue(nm1.getId() > 0);
		
		Integer note1id = nm1.getId();
		
		// see what is in the repository
		Long ncount = notificationService.getNotificationCount();
		Long nccount = notificationService.getNotificationClassCount();
		
		// Alter and update the note with added classes
		nm1.setNote(sAlteredNote);
		List<Integer> newClassIds = new ArrayList<Integer>();
		List<Integer> oldClassIds = new ArrayList<Integer>();
		oldClassIds.add(class1id);
		oldClassIds.add(class2id);
		notificationService.updateNotification(nm1, newClassIds, oldClassIds);
		
		EntityNotificationModel nma = notificationService.findNotificationById(note1id);
		
		// Confirm that the notification changed and the table count did not
		assertTrue(0 == nma.getNote().compareTo(sAlteredNote));
		assertTrue(notificationService.getNotificationCount() == ncount);
		assertTrue(notificationService.getNotificationClassCount() == nccount - 2);
	}

	@Test
	public void TestUpdateNotification_Remove_What()
	{
		createFirstTerm(imDefaultInstitution.getId());
		EntityClassModel cm1 = createAClass("Latin 101", "2017LTN1", tmFirstTerm);
		int class1id = classService.createClass(cm1);
		EntityClassModel cm2 = createAClass("Greek 202", "2017GRK2", tmFirstTerm);
		int class2id = classService.createClass(cm2);
		EntityClassModel cm3 = createAClass("Spanglish 303", "2017SP3", tmFirstTerm);
		int class3id = classService.createClass(cm3);

		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// generate class list
		List<Integer> note1classes = new ArrayList<Integer>();
		note1classes.add(class1id);
		note1classes.add(class2id);
		
		// create some notifications
		EntityNotificationModel nm1 = (EntityNotificationModel) createFirstNotification();
		notificationService.createNotification(nm1, note1classes);
		
		assertTrue(nm1.getId() > 0);
		
		// see what is in the repository
		Long ncount = notificationService.getNotificationCount();
		Long nccount = notificationService.getNotificationClassCount();
		
		// Update the note with removed classes that do not exist
		List<Integer> newClassIds = new ArrayList<Integer>();
		List<Integer> oldClassIds = new ArrayList<Integer>();
		oldClassIds.add(class3id);
		notificationService.updateNotification(nm1, newClassIds, oldClassIds);

		// Confirm the row counts
		assertTrue(notificationService.getNotificationCount() == ncount);
		assertTrue(notificationService.getNotificationClassCount() == nccount);
		
		// Update the note where one of two removed classes does not exist
		oldClassIds.add(class2id);
		notificationService.updateNotification(nm1, newClassIds, oldClassIds);

		// Confirm the row counts
		assertTrue(notificationService.getNotificationCount() == ncount);
		assertTrue(notificationService.getNotificationClassCount() == nccount - 1);
	}
	
	@Test(expected=UnknownEntityException.class)
	public void TestRemoveNotification_OK()
	{
		userService.createUser(umDefaultUser, Permission.TEACHER, imDefaultInstitution.getId());
		
		// create some notifications
		NotificationModel nm1 = createFirstNotification();
		notificationService.createNotification(nm1);
		
		assertTrue(nm1.getId() > 0);

		NotificationModel nm2 = createSecondNotification();
		notificationService.createNotification(nm2);
		
		assertTrue(nm2.getId() > 0);
		
		// delete one notification
		notificationService.removeNotification(nm1.getId());
		
		// verify that one can be found
		EntityNotificationModel en2 = notificationService.findNotificationById(nm2.getId());
		
		assertTrue(0 == en2.getNote().compareTo(sSecondNote));

		// the other should not be found
		@SuppressWarnings("unused")
		EntityNotificationModel en1 = notificationService.findNotificationById(nm1.getId());
		
		// should not get here
		assertTrue(false);
	}
}
