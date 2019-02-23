/**********************************************************************************************
 * Updated the database
 * Author: Tammy
 * 2015-04-07: Implemented proposed changes
 * 2015-04-13: Implemented Star Schema changes
 **********************************************************************************************/

/**********************************************************************************************
 * Updated the database structure
 * Author: Hien Tran 
 * 2016-10-05: Implemented primary key changes
 **********************************************************************************************/

/**********************************************************************************************
 * Username = alph
 * password = arethusa232
 * NOTE!!!!!  THE DROP DATABASE/CREATE DATABASE/USE DATABASE LINE IS COMMENTED OUT.
 * USE AdjuverisCreateDatabase.sql to run this script and all others.
 ***********************************************************************************************/
USE alphplus;


/**********************************************************************************************
 * create table if exists users
 * 2015-04-13: No changes needed
 * Hien Tran: Changed field name --> studentname to studentnumber - 2016/10/05
 **********************************************************************************************/

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	password VARCHAR(50) NOT NULL,
	firstname VARCHAR(50) NOT NULL,
	lastname VARCHAR(50) NOT NULL,
	emailaddress VARCHAR(80) NOT NULL UNIQUE KEY,
	studentnumber VARCHAR(30),
	accountcreatedtime TIMESTAMP NOT NULL,
	status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'
);


/**********************************************************************************************
 * Create table permissions
 * 2015-04-13: Removed foreign key checks
 **********************************************************************************************/
DROP TABLE if EXISTS permissions;

CREATE TABLE permissions (
  id INTEGER(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  permission varchar(50) NOT NULL UNIQUE KEY DEFAULT 'user'
);


/**********************************************************************************************
 * Create table users_permissions
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS users_permissions;

CREATE TABLE users_permissions (
  id INTEGER(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userid INTEGER(11) UNIQUE KEY NOT NULL,
  permissionid int(11) NOT NULL,
  CONSTRAINT fk_userid FOREIGN KEY (userid) REFERENCES users (id),
  CONSTRAINT fk_permissionid FOREIGN KEY (permissionid) REFERENCES permissions (id)
);
SET foreign_key_checks = 1;

/**********************************************************************************************
 * create table session
 * 2015-04-13: Implemented Star Schema changes
 * Hien Tran: Modified field name --> sessionid to sessionname - 2016/10/05
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE IF EXISTS session;

CREATE TABLE session (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	sessionName VARCHAR(100) NOT NULL DEFAULT 'yyyyyy',
	createtime DATETIME NOT NULL DEFAULT '2012-01-01 00:00:00',
	lasttime DATETIME NOT NULL DEFAULT '2012-01-02 00:00:00',
	userId INTEGER NOT NULL,
	classList VARCHAR(255),
    CONSTRAINT fk_session_userId FOREIGN KEY (userId) REFERENCES users (id)
);
SET foreign_key_checks = 1;

/**********************************************************************************************
 * create table subscriptions
 * 2015-04-13: Moved constraint from alter table to create table
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS subscriptions;

CREATE TABLE subscriptions (
	id INTEGER(11) PRIMARY KEY AUTO_INCREMENT,
	purchasedate TIMESTAMP DEFAULT NOW(),
	purchaseunit VARCHAR(24),
	subsmonths INTEGER(5),
	cost TINYINT(4),
	userId INTEGER(11),
	payMode VARCHAR(14) NOT NULL DEFAULT 'CREDIT',
	CONSTRAINT fk_subscriptions_subsuserId FOREIGN KEY (userId) REFERENCES users (id)
);  

SET foreign_key_checks = 1;

/**********************************************************************************************
 * create table message
 **********************************************************************************************/
DROP TABLE if EXISTS message;

CREATE TABLE message (
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	status VARCHAR(20) NOT NULL DEFAULT 'HIDE',
	message VARCHAR(200) NOT NULL DEFAULT 'No message.'
);


/**********************************************************************************************
 * Create table proficiencies
 * Author: Tammy
 * 2015-02-09: Created table
 * 2015-04-13: Removed foreign key checks
 **********************************************************************************************/
DROP TABLE if EXISTS proficiencies;

CREATE TABLE proficiencies (
	id INTEGER(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	proficiency varchar(50) NOT NULL UNIQUE KEY DEFAULT 'VOCABULARY'
);


/**********************************************************************************************
 * Create table classes
 * Author: Jaddy
 * 2015-03-10: Created table
 * 2015-04-13: changed classname field and removed foreign key checks
 **********************************************************************************************/
set foreign_key_checks = 0;

DROP TABLE if EXISTS classes;

/* this table used up to revision 90 but code for startdate and enddate removed. JoeAB
CREATE TABLE classes (
	ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	classname VARCHAR(20) NOT NULL,
	classcode VARCHAR(20),
	startdate DATETIME NOT NULL DEFAULT '2012-01-01 00:00:00',
	enddate DATETIME NOT NULL DEFAULT '2012-01-01 00:00:00',
	termid INT(11) NOT NULL DEFAULT 0,	
	CONSTRAINT fk_classes_term
		FOREIGN KEY (termid) REFERENCES terms (id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT,
	CONSTRAINT uc_classnames UNIQUE (termid, classname)
);

// Run these commands to remove the startdate and enddate columns without losing data
ALTER TABLE classes DROP COLUMN startdate;
ALTER TABLE classes DROP COLUMN enddate;
*/
CREATE TABLE classes (
	ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	classname VARCHAR(20) NOT NULL,
	classcode VARCHAR(20),
	termid INT(11) NOT NULL DEFAULT 0,	
	CONSTRAINT fk_classes_term
		FOREIGN KEY (termid) REFERENCES terms (id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT,
	CONSTRAINT uc_classnames UNIQUE (termid, classname)
);

set foreign_key_checks = 1;


/**********************************************************************************************
 * Create table class_users
 * Author: Jaddy
 * 2015-03-10: Created table
 * 2015-04-13: Removed foreign key checks
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS class_users;

CREATE TABLE class_users (
	ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	classid INTEGER(20) NOT NULL,
	userid INTEGER(20) NOT NULL,
	usertype VARCHAR(20) NOT NULL DEFAULT 'STUDENT',
	FOREIGN KEY (classid) REFERENCES classes (id),
	FOREIGN KEY (userid) REFERENCES users (id)
);
SET foreign_key_checks = 1;

/**********************************************************************************************
 * Create table groups
 * Author: Tammy
 * 2015-04-07: Created table
 * 2015-04-13: Moved constraint from alter table to create table
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS groups;

CREATE TABLE groups (
  id INTEGER(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  groupname VARCHAR(50) NOT NULL,
  createdbyid INTEGER NOT NULL,
  CONSTRAINT fk_groups_createdbyid FOREIGN KEY (createdbyid) REFERENCES users (id)
);
SET foreign_key_checks = 1;

/**********************************************************************************************
 * Create table users_groups
 * Users-Groups lookup table
 * Author: Tammy
 * 2015-04-07: Created table
 * 2015-04-13: No changes
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS users_groups;

CREATE TABLE users_groups (
  id INTEGER(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  groupid INTEGER NOT NULL,
  userid INTEGER NOT NULL,
  FOREIGN KEY (groupid) REFERENCES groups (id),
  FOREIGN KEY (userid) REFERENCES users (id)
);
SET foreign_key_checks = 1;


/**********************************************************************************************
 * Create table exercisecategories
 * Author: Gene
 * 2015-11-09: Created table
 **********************************************************************************************/
DROP TABLE if EXISTS exercisecategories;

CREATE TABLE exercisecategories(
id INTEGER(16) PRIMARY key AUTO_INCREMENT,
exercisename VARCHAR(63),
urlbase VARCHAR(131) NOT NULL DEFAULT 'TeacherGeneratedExerciseServlet',
exercisetype VARCHAR(12) NOT NULL DEFAULT 'PRIVATE',
comment VARCHAR(131)
);


/**********************************************************************************************
 * create table exercises
 * 2015-04-07: Deleted Variable1 and Variable2, template
 * 2015-04-13: Implemented Star Schema changes
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS exercises;

CREATE TABLE exercises (
	id INTEGER(16) PRIMARY KEY AUTO_INCREMENT,
	createdbyid INTEGER(11),
	exercisecategory INTEGER(15),
	identifier varchar(75),	 
	variable1 INTEGER(3) NOT NULL DEFAULT 0,
	variable2 INTEGER(3) NOT NULL DEFAULT 0,
	var1string VARCHAR(31),
	var2string VARCHAR(31),
	/*passageid INTEGER,*/
	createddate TIMESTAMP NOT NULL,
	CONSTRAINT fk_exercises_createdbyid FOREIGN KEY (createdbyid) REFERENCES users (id),
    /*CONSTRAINT fk_exercises_passageid FOREIGN KEY (passageid) REFERENCES passages (id),*/
    CONSTRAINT fk_exercises_categories FOREIGN KEY (exercisecategory) REFERENCES exercisecategories (id)
);
SET foreign_key_checks = 1;

/**********************************************************************************************
 * create table question_types to store question types: 
   TRUE_FALSE,SINGLE_SELECTION,MULTI_SELECTION,FILL_IN_BLANK,LIST_MATCHING
* Author:  Martha Gongora, 18 FEB 2016

 **********************************************************************************************/
/*
SET foreign_key_checks = 0;
DROP TABLE if EXISTS question_types;

CREATE TABLE question_types (
  id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  questiontype VARCHAR(24) NOT NULL
 );
 
SET foreign_key_checks = 1;
*/
/**********************************************************************************************
 * Create question_groups table to storage groups of questions 
 * Author:  Gene, 7 Mar 2016

 **********************************************************************************************/
DROP TABLE if EXISTS question_groups;

CREATE TABLE question_groups (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  exerciseid INTEGER,
  CONSTRAINT fk_question_groups_exerciseid FOREIGN KEY (exerciseid) REFERENCES exercises (id)ON DELETE CASCADE
  );

/**********************************************************************************************
 * create table questions
 * 2016-02-18 : Multiple changes to separate Question and Answers by Martha Gongora
 * 2015-02-11: Changed proficiency from varchar to integer
 * 2015-03-24: Added questiontypeid column and fk_questiontypeid
 * 2015-04-07: Multiple changes
 * 2015-04-14: Implemented Star Schema changes
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS questions;

CREATE TABLE questions (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  exerciseid INTEGER NOT NULL,
  questiontype VARCHAR(24) NOT NULL DEFAULT 'True-false',
  qnumber INTEGER NOT NULL,
  question VARCHAR(100) NOT NULL,
  proficiencyindex INTEGER, 
  weight SMALLINT,   -- changed from varchar to smallint
  swassessed BOOLEAN,   -- added
  passageid INTEGER,
  word text(50),
  linenumber INTEGER,
  wordnumber INTEGER,
  strword INTEGER NOT NULL DEFAULT 0, 
  endword INTEGER NOT NULL DEFAULT 0,
  questiongroupid INTEGER,
  CONSTRAINT fk_questions_exerciseid FOREIGN KEY (exerciseid) REFERENCES exercises (id)ON DELETE CASCADE,
 -- CONSTRAINT fk_questions_proficiencyid FOREIGN KEY (proficiencyid) REFERENCES proficiencies (id),
  CONSTRAINT fk_questions_passageid FOREIGN KEY (passageid) REFERENCES passages (id) ON DELETE CASCADE,
  CONSTRAINT fk_questions_questiongroupid FOREIGN KEY (questiongroupid) REFERENCES question_groups (id) ON DELETE CASCADE
 );

SET foreign_key_checks = 1;


/**********************************************************************************************
 * Create question_questiongroups table to storage groups of questions 
 * Author:  Martha Gongora, 18 FEB 2016
 * Hien Tran: Change primary key - 2016/10/05
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS question_questiongroups;

CREATE TABLE question_questiongroups (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  questiongroupid INT,
  questionid INT,  
  CONSTRAINT fk_questionquestiongoups_questiongroupid FOREIGN KEY (questiongroupid) REFERENCES question_groups (id) ON DELETE CASCADE,
  CONSTRAINT fk_questionquestiongoups_questionid FOREIGN KEY (questionid) REFERENCES questions (id) ON DELETE CASCADE
  );
  
SET foreign_key_checks = 1;

/**********************************************************************************************
 * Create answers table to storage possible answers to the questions 
 * Author:  Martha Gongora, 18 FEB 2016

 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS answers;

CREATE TABLE answers (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  questionid INT NOT NULL,
  answer VARCHAR(100) NOT NULL,
  answerint SMALLINT,
  correct BOOLEAN,
  CONSTRAINT fk_answers_questionid FOREIGN KEY (questionid) REFERENCES questions (id) ON DELETE CASCADE
);


SET foreign_key_checks = 1;

/*
    question_types :
	(TRUE_FALSE='True Or False'),
	(SINGLE_SELECTION='Single-Select/Multiple-Choice'),
	(MULTI_SELECTION='Multi-Select/Multiple-Choice'),
    (FULL_IN_BLANK='Fill in the Blank'),
    (LISTMATCHING='List Matching'),
    (QA_TEXT_AREA='Q&A Text Area Based'),
    (SCRAMBLED_LETTER='Scrambled Letters'),
    (CROSSWORD_PUZZLE='Crossword Puzzle');
 */


/**********************************************************************************************
 * create table assignments
 * 2015-04-07: Multiple changes
 * 2015-04-14: Implemented Star Schema changes
 * 2017-01-03: assignmentname increased from 24
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS assignments;

CREATE TABLE assignments (
	id INTEGER(16) PRIMARY KEY AUTO_INCREMENT,
	assignmentname VARCHAR(65),		-- added, modified
	exerciseid INTEGER, 	
	teacherid INTEGER,				-- teacher userid
	status VARCHAR(12),
	startdate TIMESTAMP NOT NULL DEFAULT '2015-01-01 00:00:00', 	-- added
	duedate TIMESTAMP NOT NULL DEFAULT '2015-06-30 00:00:00',	-- modified the default date
	updatedate TIMESTAMP NOT NULL DEFAULT '2015-01-01 00:00:00', 	-- added
	type VARCHAR(13) NOT NULL DEFAULT 'NORMAL',	-- added
	weight SMALLINT,						-- added
	additionalresources VARCHAR(100),				-- added
	comment VARCHAR(100) NULL DEFAULT NULL,
	createddate TIMESTAMP NOT NULL DEFAULT '2015-01-01 00:00:00', 	-- added
	timezone VARCHAR(12) NOT NULL DEFAULT 'EST',
	CONSTRAINT fk_assignments_exerciseid FOREIGN KEY (exerciseid) REFERENCES exercises (id) ON DELETE CASCADE,
	CONSTRAINT fk_assignments_teacherid FOREIGN KEY (teacherid) REFERENCES users (id)
);
SET foreign_key_checks = 1;




/**********************************************************************************************
 * Create table assignments_fact
 * Author: Tammy
 * 2015-04-14: Created table
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS assignments_fact;

CREATE TABLE assignments_fact (
  id INTEGER(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  classid INTEGER,
  studentid INTEGER,
  groupid INTEGER,
  assignmentid INTEGER,
  status VARCHAR(12),
  FOREIGN KEY (classid) REFERENCES classes (id) ON DELETE CASCADE,
  FOREIGN KEY (studentid) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (groupid) REFERENCES groups (id) ON DELETE CASCADE,
  FOREIGN KEY (assignmentid) REFERENCES assignments (id) ON DELETE CASCADE
 );
 SET foreign_key_checks = 1;
 
 /**********************************************************************************************
 * Create table Notifications
 * Author: Jaddy
 * 2015-03-23: Created table
 * 2015-04-13: Implemented Star Schema changes - removed groupid
 **********************************************************************************************/
SET foreign_key_checks = 0; 
DROP TABLE if EXISTS notifications;

CREATE TABLE notifications(
	ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	createdbyid INTEGER(20) NOT NULL,
	note VARCHAR(200) NOT NULL ,
	startdate DATETIME NOT NULL DEFAULT '2015-01-01 00:00:00',
	expirydate DATETIME NOT NULL DEFAULT '2015-01-01 00:00:00',
	timezone VARCHAR(12) NOT NULL DEFAULT 'EST',
    CONSTRAINT fk_notifications_createdbyid FOREIGN KEY (createdbyid) REFERENCES users (id) ON DELETE CASCADE
);
SET foreign_key_checks = 1;

/**********************************************************************************************
 * Create table calculated_results
 * Author: Tammy
 * 2015-04-07: Created table
 * 2015-04-13: Implemented Star Schema changes - removed assignmentid, studentid
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS calculated_results;

CREATE TABLE calculated_results (
	id INTEGER(16) PRIMARY KEY AUTO_INCREMENT,
	assignmentfactid INTEGER NOT NULL,
	calculatedscore INTEGER,  -- or float?
	proficiencyscores VARCHAR(50), -- delimited string
	calculateddate TIMESTAMP,
    CONSTRAINT fk_calculated_results_assignmentfactid FOREIGN KEY (assignmentfactid) REFERENCES assignments_fact (id) ON DELETE CASCADE
);
SET foreign_key_checks = 1;

/**********************************************************************************************
 * Create table student_answers
 * Author: Tammy
 * 2015-04-07: Created table
 * 2015-04-13: Implemented Star Schema changes - removed assignmentid, studentid, exerciseid
 **********************************************************************************************/
SET foreign_key_checks = 0;
DROP TABLE if EXISTS student_answers;

CREATE TABLE student_answers (
	id INTEGER(16) PRIMARY KEY AUTO_INCREMENT,
	assignmentfactid INTEGER NOT NULL,	
	questionid INTEGER,
	studentanswer VARCHAR(100),
	calculatedweight SMALLINT,
	score float,
	CONSTRAINT fk_student_answers_questionid FOREIGN KEY (questionid) REFERENCES questions (id) ON DELETE CASCADE,
    CONSTRAINT fk_student_answers_assignmentfactid FOREIGN KEY (assignmentfactid) REFERENCES assignments_fact (id) ON DELETE CASCADE

	);
SET foreign_key_checks = 1;	



/**********************************************************************************************
 * Create table panels
 * Author: Gene
 **********************************************************************************************/
DROP TABLE if EXISTS panels;

CREATE TABLE panels(
id INTEGER(16) PRIMARY key AUTO_INCREMENT,
panel VARCHAR(16) NOT NULL DEFAULT 'NOTIFICATIONS'
);

/**********************************************************************************************
 * Create assignment classes table which links assignment ids to class ids
 * Author: Kwirtanen
 **********************************************************************************************/
DROP TABLE if EXISTS assignment_classes;

CREATE TABLE assignment_classes (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  assignmentId INTEGER(16) NOT NULL,
  classId INTEGER(16) NOT NULL,
  CONSTRAINT fk_assignmentclasses_classId_idx
    FOREIGN KEY (classId) REFERENCES classes (ID) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

/**********************************************************************************************
 * Create Notification classes table which links notification ids to class ids
 * Author: Martha Gongora
 **********************************************************************************************/
DROP TABLE if EXISTS notification_classes;

/* original table to revision 538
CREATE TABLE notification_classes (
  Id INT(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  notificationId INT(16) NOT NULL,
  classId INT(16) NOT NULL,
  CONSTRAINT fk_notificationclasses_classId_idx
    FOREIGN KEY (classId) REFERENCES classes (ID) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

// make notificationId a foreign key
ALTER TABLE `alphplus`.`notification_classes` ADD CONSTRAINT `fk_notificationclasses_notificationId`
FOREIGN KEY (`notificationId`) REFERENCES `alphplus`.`notifications` (`ID`)
ON DELETE RESTRICT ON UPDATE NO ACTION;

// make termname unique within an institution
ALTER TABLE `alphplus`.`notification_classes` 
ADD CONSTRAINT uc_notificationclasses UNIQUE (notificationId, classId);
*/
CREATE TABLE notification_classes (
  Id INT(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  notificationId INT(16) NOT NULL,
  classId INT(16) NOT NULL,
  CONSTRAINT fk_notificationclasses_classId_idx
    FOREIGN KEY (classId) REFERENCES classes (ID) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT fk_notificationclasses_notificationId_idx
    FOREIGN KEY (notificationId) REFERENCES notifications (ID) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT uc_terms UNIQUE (notificationId, classId)
	);

    
 /**********************************************************************************************
 * Create student assignment results table which stores each student's individual
 * assignment results.
 * Author: Kwirtanen
 **********************************************************************************************/
DROP TABLE if EXISTS student_assignment_results;
    
CREATE TABLE student_assignment_results (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  assignmentFactId INTEGER(16) UNIQUE KEY NOT NULL,
  correctNumber FLOAT NOT NULL,
  total FLOAT NOT NULL,
  percent FLOAT NOT NULL,
  inflectcnt SMALLINT,
  inflectright SMALLINT,
  inflectpct FLOAT NOT NULL DEFAULT 0,
  syntaxcnt SMALLINT,
  syntaxright SMALLINT,
  syntaxpct FLOAT NOT NULL DEFAULT 0,
  vocabcnt SMALLINT,
  vocabright SMALLINT,
  vocabpct FLOAT NOT NULL DEFAULT 0,  
  comprehencnt SMALLINT,
  comprehenright SMALLINT,
  comprehenpct FLOAT NOT NULL DEFAULT 0,
  CONSTRAINT fk_studentresults_assignmentFactId_idx
    FOREIGN KEY (assignmentFactId) REFERENCES assignments_fact (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

/**********************************************************************************************
 * Create class assignment results table which stores the aggregate class results per assignment.
 * Author: Kwirtanen
 **********************************************************************************************/
DROP TABLE if EXISTS class_assignment_results;
    
CREATE TABLE class_assignment_results (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  assignmentId INTEGER(16) NOT NULL,
  studentCount INTEGER(16) NOT NULL,
  total FLOAT NOT NULL,
  average FLOAT NOT NULL,
  inflecttotal INTEGER NOT NULL DEFAULT 0,
  inflectright INTEGER NOT NULL DEFAULT 0,
  syntaxtotal INTEGER NOT NULL DEFAULT 0,
  syntaxright INTEGER NOT NULL DEFAULT 0,
  vocabtotal INTEGER NOT NULL DEFAULT 0,
  vocabright INTEGER NOT NULL DEFAULT 0,
  comprehentotal INTEGER NOT NULL DEFAULT 0,
  comprehenright INTEGER NOT NULL DEFAULT 0,
  CONSTRAINT fk_classresults_assignId_idx
    FOREIGN KEY (assignmentId) REFERENCES assignments (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

/**********************************************************************************************
 * Create teacher assignment results table which stores the aggregate class results per teacher.
 * Author: George
 **********************************************************************************************/
DROP TABLE if EXISTS teacher_assignment_results;
    
CREATE TABLE teacher_assignment_results (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  teacherId INTEGER(16) NOT NULL,
  studentCount INTEGER(16) NOT NULL,
  average FLOAT NOT NULL,
  inflecttotal INTEGER NOT NULL DEFAULT 0,
  inflectright INTEGER NOT NULL DEFAULT 0,
  syntaxtotal INTEGER NOT NULL DEFAULT 0,
  syntaxright INTEGER NOT NULL DEFAULT 0,
  vocabtotal INTEGER NOT NULL DEFAULT 0,
  vocabright INTEGER NOT NULL DEFAULT 0,
  comprehentotal INTEGER NOT NULL DEFAULT 0,
  comprehenright INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_teacherresults_teacherId_idx
    FOREIGN KEY (teacherId) REFERENCES users (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

/**********************************************************************************************
 * Create teacher previous assignment results table which stores the aggregate class results 
 * per teacher for previous terms.
 * Author: George
 **********************************************************************************************/
DROP TABLE if EXISTS teacher_pre_results;

CREATE TABLE teacher_pre_results (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  teacherId INTEGER(16) NOT NULL,
  studentCount INTEGER(16) NOT NULL,
  average FLOAT NOT NULL,
  inflecttotal INTEGER NOT NULL DEFAULT 0,
  inflectright INTEGER NOT NULL DEFAULT 0,
  syntaxtotal INTEGER NOT NULL DEFAULT 0,
  syntaxright INTEGER NOT NULL DEFAULT 0,
  vocabtotal INTEGER NOT NULL DEFAULT 0,
  vocabright INTEGER NOT NULL DEFAULT 0,
  comprehentotal INTEGER NOT NULL DEFAULT 0,
  comprehenright INTEGER NOT NULL DEFAULT 0,
  calculatedate DATETIME NOT NULL DEFAULT '2012-01-01 00:00:00',
    CONSTRAINT fk_teacherpre_teacherId_idx
    FOREIGN KEY (teacherId) REFERENCES users (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

/**************************************************************************************************
 * Create 'institutions' table to store data on institutions
 * Author:  Gene Price, 02 JAN 2016
 **************************************************************************************************/
SET foreign_key_checks = 0;	
DROP TABLE if EXISTS institutions;
    
CREATE TABLE institutions (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  instname VARCHAR(16) NOT NULL,
  postaddress VARCHAR(255),
  primecontact VARCHAR(63),
  primephone VARCHAR(32),
  primeemail VARCHAR(63),
  adminfirstname VARCHAR(16),
  adminlastname VARCHAR(16),
  adminemail VARCHAR(64)
  );
    
ALTER TABLE institutions ADD UNIQUE (instname);
  
  SET foreign_key_checks = 1;
  
  
/**************************************************************************************************
 * Create 'terms' table to store data on academic terms
 * Author:  Gene Price, 02 JAN 2016
 **************************************************************************************************/
DROP TABLE if EXISTS terms;
/* original table to revision 522
CREATE TABLE terms (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  termname VARCHAR(16) NOT NULL,
  instid INTEGER(16),
  startdate DATE NOT NULL DEFAULT '2016-01-01', 
  enddate DATE NOT NULL DEFAULT '2016-01-01',
  updatedate TIMESTAMP NOT NULL DEFAULT '2016-01-01'
  );

// make instid a foreign key
ALTER TABLE `alphplus`.`terms` ADD INDEX `fk_terms_instid_idx` (`instid` ASC);
ALTER TABLE `alphplus`.`terms` ADD CONSTRAINT `fk_terms_instid`
FOREIGN KEY (`instid`) REFERENCES `alphplus`.`institutions` (`Id`)
ON DELETE RESTRICT ON UPDATE NO ACTION;

// make termname unique within an institution
ALTER TABLE `alphplus`.`terms` 
ADD CONSTRAINT uc_terms UNIQUE (instid, termname);
*/
CREATE TABLE terms (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  termname VARCHAR(16) NOT NULL,
  instid INTEGER(16),
  startdate DATE NOT NULL DEFAULT '2016-01-01', 
  enddate DATE NOT NULL DEFAULT '2016-01-01',
  updatedate TIMESTAMP NOT NULL DEFAULT '2016-01-01',
  CONSTRAINT fk_terms_instid 
    FOREIGN KEY (instid) REFERENCES institutions (Id) 
    ON DELETE RESTRICT 
    ON UPDATE RESTRICT,
  CONSTRAINT uc_terms UNIQUE (instid, termname)
  );

  
  /**************************************************************************************************
 * Create 'terms_students' table as a join table to create student lists for a term
 * Author:  Gene Price, 02 JAN 2016
 **************************************************************************************************/
DROP TABLE if EXISTS terms_students;
    
CREATE TABLE terms_students (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  termid INTEGER(16) NOT NULL,
  studentid INTEGER(16) NOT NULL,
  CONSTRAINT fk_termidstudent_idx
    FOREIGN KEY (termid) REFERENCES terms (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT fk_termsstudents_idx
    FOREIGN KEY (studentid) REFERENCES users (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
    
    ALTER TABLE terms_students ADD UNIQUE (studentid,termid);
  
  /**************************************************************************************************
 * Create 'terms_students' table as a join table to create student lists for a term
 * Author:  Gene Price, 02 JAN 2016
 **************************************************************************************************/
DROP TABLE if EXISTS terms_teachers;
    
CREATE TABLE terms_teachers (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  termid INTEGER(16) NOT NULL,
  teacherid INTEGER(16) NOT NULL,
  CONSTRAINT fk_termidteacher_idx
    FOREIGN KEY (termid) REFERENCES terms (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT fk_termsteachers_idx
    FOREIGN KEY (teacherid) REFERENCES users (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
    
    ALTER TABLE terms_teachers ADD UNIQUE (teacherid,termid);
  
  /**************************************************************************************************
 * Create 'terms_students' table as a join table to create student lists for a term
 * Author:  Gene Price, 02 JAN 2016
 **************************************************************************************************/
DROP TABLE if EXISTS terms_tas;
    
CREATE TABLE terms_tas (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  termid INTEGER(16) NOT NULL,
  taid INTEGER(16) NOT NULL,
  CONSTRAINT fk_taidteacher_idx
    FOREIGN KEY (termid) REFERENCES terms (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT fk_termstas_idx
    FOREIGN KEY (taid) REFERENCES users (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
    
    ALTER TABLE terms_tas ADD UNIQUE (taid,termid);
    
  /**************************************************************************************************
 * Create 'inst_users' table as a join table to relate users with inst_admin permissions to institutions
 * Author:  Gene Price, 02 JAN 2016
 **************************************************************************************************/
DROP TABLE if EXISTS inst_users;
    
CREATE TABLE inst_users (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  instid INTEGER(16) NOT NULL,
  userid INTEGER(16) NOT NULL,
  CONSTRAINT fk_instid_idx
    FOREIGN KEY (instid) REFERENCES institutions (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT fk_instusers_idx 
    FOREIGN KEY (userid) REFERENCES users (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
    );
    
/**************************************************************************************************
 * Create 'inst_user_permissions' table as a join table to relate institutions, users and permissions
 * Author:  Joe Brown, 16 AUG 2017
 **************************************************************************************************/
DROP TABLE if EXISTS inst_user_permissions;
    
CREATE TABLE inst_user_permissions (
  Id INTEGER(16) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  instid INTEGER(16) NOT NULL,
  userid INTEGER(16) NOT NULL,
  permissionid INTEGER(11) NOT NULL,
  CONSTRAINT fk_inst_iup
    FOREIGN KEY (instid) REFERENCES institutions (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT fk_user_iup 
    FOREIGN KEY (userid) REFERENCES users (id) 
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT fk_permission_iup 
	FOREIGN KEY (permissionid) REFERENCES permissions (id),
  CONSTRAINT uc_iupositions UNIQUE (instid, userid, permissionid)
  );
    
 /**************************************************************************************************
 * Create 'inst_users' table as a join table to relate users with inst_admin permissions to institutions
 * Author:  Gene Price, 1 MAR 2016
 **************************************************************************************************/
DROP TABLE if EXISTS passages;
    
CREATE TABLE passages (
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  textcontent VARCHAR(10000),
  textinfo VARCHAR(63),
  exerciseid INTEGER,
  CONSTRAINT fk_passage_exeridx
    FOREIGN KEY (exerciseid) REFERENCES exercises (id)  ON DELETE CASCADE
    );  

/**********************************************************************************************
 * Create table date_ranges
 * 2017-05-24: Created table
 **********************************************************************************************/

SET foreign_key_checks = 0;

DROP TABLE if EXISTS date_range;
DROP TABLE if EXISTS date_ranges;

CREATE TABLE date_ranges (
	ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	instid INTEGER(16) NOT NULL,
	studentsTerms INTEGER(1) NOT NULL DEFAULT 0,
	instAdminTerms INTEGER(2) NOT NULL DEFAULT -1,
	UNIQUE KEY unq_instid_idx (instid),
	CONSTRAINT fk_daterange_idx
		FOREIGN KEY (instid) REFERENCES institutions (id) 
);
SET foreign_key_checks = 1;  
  
    
  
    
    
    
    