USE alphplus;

/**********************************************************************************************
 * This script will create a 'Latin 101' class and a 'Latin 102' class
 **********************************************************************************************/ 
set foreign_key_checks = 0;

 INSERT INTO classes (classname, termid) VALUES ('Latin 101',1);
 INSERT INTO classes (classname, termid) VALUES ('Latin 102',1); 
 INSERT INTO classes (classname, termid) VALUES ('Latin 103',1);
 
 
/**********************************************************************************************
 * This script will add teachers, tas and students to the 'Latin 101' class and 'Latin 102' class
 **********************************************************************************************/
 INSERT INTO class_users (classid, userid, usertype) VALUES (1, 1, 'TEACHER');
 INSERT INTO class_users (classid, userid, usertype) VALUES (1, 3, 'TA');
 INSERT INTO class_users (classid, userid, usertype) VALUES (1, 5, 'STUDENT');
 INSERT INTO class_users (classid, userid, usertype) VALUES (1, 6, 'STUDENT');
 INSERT INTO class_users (classid, userid, usertype) VALUES (2, 2, 'TEACHER');
 INSERT INTO class_users (classid, userid, usertype) VALUES (2, 4, 'TA');
 INSERT INTO class_users (classid, userid, usertype) VALUES (2, 7, 'STUDENT');
 INSERT INTO class_users (classid, userid, usertype) VALUES (2, 8, 'STUDENT');
 INSERT INTO class_users (classid, userid, usertype) VALUES (3, 1, 'TEACHER');
 INSERT INTO class_users (classid, userid, usertype) VALUES (3, 3, 'TA');
 INSERT INTO class_users (classid, userid, usertype) VALUES (3, 5, 'STUDENT');
 INSERT INTO class_users (classid, userid, usertype) VALUES (3, 6, 'STUDENT');
 
  
 set foreign_key_checks = 1;
    
/**********************************************************************************************
 * This script will create groups
 **********************************************************************************************/
 INSERT INTO groups (groupname, createdbyid) VALUES ('ROSE GROUP', 10);
 INSERT INTO groups (groupname, createdbyid) VALUES ('TIGER GROUP', 10);
  
/**********************************************************************************************
 * This script add userids to groups
 **********************************************************************************************/ 
 INSERT INTO users_groups (groupid, userid) VALUES (1, 5);	
 INSERT INTO users_groups (groupid, userid) VALUES (1, 6);
 INSERT INTO users_groups (groupid, userid) VALUES (2, 7);	
 INSERT INTO users_groups (groupid, userid) VALUES (2, 8);
  
 
 /**********************************************************************************************
 * Create the assignments for the latin pronoun drill exercises from teacher 1 and 2 to a students in Latin 101 and Latin 102
 **********************************************************************************************/
INSERT INTO assignments (assignmentname,exerciseid,teacherid,status,startdate,duedate,updatedate,type,weight,additionalresources,createddate,comment) 
	VALUES('Latin Pronoun Drill 01',1,1,'COMPLETED','2017-01-01 00:00:00','2017-01-03 00:00:00','2017-01-01 21:23:00','STANDARD',0,'','2017-04-07 21:23:00','--');
INSERT INTO assignments (assignmentname,exerciseid,teacherid,status,startdate,duedate,updatedate,type,weight,additionalresources,createddate,comment) 
	VALUES('Latin Pronoun Drill 02',2,1,'COMPLETED','2017-02-01 00:00:00','2017-02-03 00:00:00','2017-01-01 21:23:00','STANDARD',0,'','2017-04-07 21:23:00','--');    
INSERT INTO assignments (assignmentname,exerciseid,teacherid,status,startdate,duedate,updatedate,type,weight,additionalresources,createddate,comment) 
	VALUES('Latin Pronoun Drill 03',3,1,'COMPLETED','2017-02-14 00:00:00','2017-02-15 00:00:00','2017-01-01 21:23:00','STANDARD',0,'','2017-04-07 21:23:00','--'); 


/**********************************************************************************************
 * This script add assignments facts
 **********************************************************************************************/
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,1);
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,2);
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,3);

 /**********************************************************************************************
 * This script add student answers
 **********************************************************************************************/
 INSERT INTO student_answers (assignmentfactid, questionid, studentanswer) 
 SELECT 1 as assignmentid, questionid, answer FROM answers WHERE questionid<31; 
 
 /*
  * introduce some errors
  */
 UPDATE student_answers SET studentanswer = 'hoc' WHERE questionid=4;
 UPDATE student_answers SET studentanswer = 'haec' WHERE questionid=7;
 UPDATE student_answers SET studentanswer = 'hoc' WHERE questionid=12;
 UPDATE student_answers SET studentanswer = 'hac' WHERE questionid=26;
 
 INSERT INTO student_answers (assignmentfactid,questionid, studentanswer) 
 SELECT 2 as assignmentid, questionid, answer FROM answers WHERE questionid>30 AND questionid<61;
 
 /*
  * make answer to questionid 46 a single answer rather than eidem, iidem
  */
 UPDATE student_answers SET studentanswer = 'eidem' WHERE questionid=46;
 UPDATE student_answers SET studentanswer = 'eicdem' WHERE questionid=34;
 UPDATE student_answers SET studentanswer = 'eicdem' WHERE questionid=39;
 UPDATE student_answers SET studentanswer = 'eicdem' WHERE questionid=44;
 
 INSERT INTO student_answers (assignmentfactid,questionid, studentanswer) 
 SELECT 3 as assignmentid, questionid, answer FROM answers WHERE questionid>60 AND questionid<91;

 /**********************************************************************************************
 * This script add some notifications
 **********************************************************************************************/
 INSERT INTO notifications (createdbyid, note, startdate, expirydate)
 SELECT  1 AS createdbyid, CONCAT('LATIN 101 Student, ',a.assignmentname ,' is now available.') AS Note, a.startdate, a.duedate
 FROM assignments a WHERE a.id=1;
 
 /********************************************************************************************
  * Adds an institution for test purposes
  *******************************************************************************************/
 INSERT INTO institutions (instname,postaddress,primecontact,primephone,primeemail,adminfirstname,adminlastname,
 adminemail) values ('TestUniversity',
 '9876 Aardvark Court, Iqaluit, Nunavut, A1M 1A1','Bigg Wigg','1-765-000-000','wigg@100.com','Egosum','Instadmin','inst_admin@100.com');

 INSERT INTO date_ranges(instid, studentsTerms, instAdminTerms) 
	VALUES ((SELECT i.id FROM institutions i WHERE i.instname='TestUniversity'), 0, -1);
 
 /********************************************************************************************
  * creates records in inst_users that associate each user with the test institution
  *********************************************************************************************/
 INSERT INTO inst_users (instid, userid) VALUES (1,1);
 INSERT INTO inst_users (instid, userid) VALUES (1,2);
 INSERT INTO inst_users (instid, userid) VALUES (1,3);
 INSERT INTO inst_users (instid, userid) VALUES (1,4);
 INSERT INTO inst_users (instid, userid) VALUES (1,5);
 INSERT INTO inst_users (instid, userid) VALUES (1,6);
 INSERT INTO inst_users (instid, userid) VALUES (1,7);
 INSERT INTO inst_users (instid, userid) VALUES (1,8);
 INSERT INTO inst_users (instid, userid) VALUES (1,9);
 INSERT INTO inst_users (instid, userid) VALUES (1,10);
 INSERT INTO inst_users (instid, userid) VALUES (1,11);
 
 /***********************************************************************************************
  * creates a record in the terms table for testing the InstAdminTermsStudentListPanel
  *********************************************************************************************/
 INSERT INTO terms (termname, instid, startdate, enddate, updatedate) VALUES ('Spring 2017',1,'2017-01-01',
 '2017-05-20','2017-01-01');
 INSERT INTO terms (termname, instid, startdate, enddate, updatedate) VALUES ('Fall 2015',1,'2015-09-01',
 '2015-12-20','2015-10-01');
 INSERT INTO terms (termname, instid, startdate, enddate, updatedate) VALUES ('Fall 2017',1,'2017-05-20',
 '2017-12-31','2017-01-03');
 
 /**********************************************************************************************
  * creates a student list for Spring 2016 term
  * ********************************************************************************************/
 
 INSERT INTO terms_students (termid,studentid) VALUES (1,5);
 INSERT INTO terms_students (termid,studentid) VALUES (1,6);
 INSERT INTO terms_students (termid,studentid) VALUES (1,7);
 INSERT INTO terms_students (termid,studentid) VALUES (1,8);
 INSERT INTO terms_students (termid,studentid) VALUES (2,5);
 INSERT INTO terms_students (termid,studentid) VALUES (2,6);
 INSERT INTO terms_students (termid,studentid) VALUES (2,7);
 INSERT INTO terms_students (termid,studentid) VALUES (2,8);
 INSERT INTO terms_students (termid,studentid) VALUES (3,5);
 INSERT INTO terms_students (termid,studentid) VALUES (3,6);
 INSERT INTO terms_students (termid,studentid) VALUES (3,7);
 INSERT INTO terms_students (termid,studentid) VALUES (3,8);
 
 /**********************************************************************************************
  * creates a teacher list for Spring 2016 term
  * ********************************************************************************************/
 
 INSERT INTO terms_teachers (termid,teacherid) VALUES (1,1);
 INSERT INTO terms_teachers (termid,teacherid) VALUES (1,2);
 INSERT INTO terms_teachers (termid,teacherid) VALUES (2,1);
 INSERT INTO terms_teachers (termid,teacherid) VALUES (2,2);
 INSERT INTO terms_teachers (termid,teacherid) VALUES (3,1);
 INSERT INTO terms_teachers (termid,teacherid) VALUES (3,2);
 
 /**********************************************************************************************
  * creates a ta list for Spring 2016 term
  * ********************************************************************************************/
 
 INSERT INTO terms_tas (termid,taid) VALUES (1,3);
 INSERT INTO terms_tas (termid,taid) VALUES (1,4);
 INSERT INTO terms_tas (termid,taid) VALUES (2,3);
 INSERT INTO terms_tas (termid,taid) VALUES (2,4);
 INSERT INTO terms_tas (termid,taid) VALUES (3,3);
 INSERT INTO terms_tas (termid,taid) VALUES (3,4);
 
 /***********************************************************************************************
  * create records in the terms table for another institution
  *********************************************************************************************/
 INSERT INTO terms (termname, instid, startdate, enddate, updatedate) 
 	VALUES ('2017 into 2018', (SELECT i.id FROM institutions i WHERE i.instname='TestUniversity'), '2017-09-01', '2018-01-31','2017-09-11');
 INSERT INTO terms (termname, instid, startdate, enddate, updatedate) 
 	VALUES ('2018 WinSpr', (SELECT i.id FROM institutions i WHERE i.instname='TestUniversity'), '2018-02-01', '2018-06-30','2017-09-11');
 INSERT INTO terms (termname, instid, startdate, enddate, updatedate) 
 	VALUES ('2018 Summer', (SELECT i.id FROM institutions i WHERE i.instname='TestUniversity'), '2018-07-01', '2018-08-31','2017-09-11');

/**********************************************************************************************
 * create records in the classes table for another institution
 **********************************************************************************************/ 
 INSERT INTO classes (classname, classcode, termid) VALUES ('Math grade 9A', '2017F-09M', (SELECT t.id FROM terms t WHERE t.termname='2017 into 2018'));
 INSERT INTO classes (classname, classcode, termid) VALUES ('History grade 9', '2017F-09H', (SELECT t.id FROM terms t WHERE t.termname='2017 into 2018')); 
 INSERT INTO classes (classname, classcode, termid) VALUES ('Functions grade 12', '2017F-12M', (SELECT t.id FROM terms t WHERE t.termname='2017 into 2018'));
 INSERT INTO classes (classname, classcode, termid) VALUES ('Math grade 9B', '2018W-9M', (SELECT t.id FROM terms t WHERE t.termname='2018 WinSpr'));
 INSERT INTO classes (classname, classcode, termid) VALUES ('Geog grade 9', '2018W-9G', (SELECT t.id FROM terms t WHERE t.termname='2018 WinSpr')); 
 INSERT INTO classes (classname, classcode, termid) VALUES ('Algebra grade 12', '2018W-12M', (SELECT t.id FROM terms t WHERE t.termname='2018 WinSpr'));
 INSERT INTO classes (classname, classcode, termid) VALUES ('Math grade 9C', '2018S-9M', (SELECT t.id FROM terms t WHERE t.termname='2018 Summer'));
 INSERT INTO classes (classname, classcode, termid) VALUES ('Geog grade 9C', '2018S-9G', (SELECT t.id FROM terms t WHERE t.termname='2018 Summer')); 
 INSERT INTO classes (classname, classcode, termid) VALUES ('English grade 12C', '2018S-12E', (SELECT t.id FROM terms t WHERE t.termname='2018 Summer'));
 
/**********************************************************************************************
 * create records in the user and permissions tables with a TA/Student
 **********************************************************************************************/
INSERT INTO users (password, firstname, lastname, emailaddress, studentnumber)
	VALUES(sha1('aaaaaa'), 'Buddy', 'Bigguy', 'bb@100.com', '2017244489');

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='TestUniversity'),
           (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='TA'));

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='TestUniversity'),
           (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='STUDENT'));
 
/**********************************************************************************************
 * create records in the user and permissions tables for 
 * a teacher at one institution and a institution administrator at another
 **********************************************************************************************/
INSERT INTO users (password, firstname, lastname, emailaddress, studentnumber)
	VALUES(sha1('aaaaaa'), 'Tammy', 'Teachwright', 'tt@100.com', '832349744');

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='TestUniversity'),
           (SELECT u.id FROM users u WHERE emailaddress='tt@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='INSTITUTION_ADMIN'));

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='Demo College'),
           (SELECT u.id FROM users u WHERE emailaddress='tt@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='TEACHER'));
 
 /**********************************************************************************************
  * creates a teacher list for Spring 2016 term
  * ********************************************************************************************/
  INSERT INTO terms_teachers (termid, teacherid) VALUES (1, (SELECT u.id FROM users u WHERE emailaddress='tt@100.com'));

 /**********************************************************************************************
  * creates a ta list for another institution
  * ********************************************************************************************/
 INSERT INTO terms_tas (termid, taid) VALUES ((SELECT t.id FROM terms t WHERE t.termname='2017 into 2018'), (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'));
 INSERT INTO terms_tas (termid, taid) VALUES ((SELECT t.id FROM terms t WHERE t.termname='2018 WinSpr'), (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'));
 INSERT INTO terms_tas (termid, taid) VALUES ((SELECT t.id FROM terms t WHERE t.termname='2018 Summer'), (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'));

 /**********************************************************************************************
  * creates a student list for for another institution
  * ********************************************************************************************/
 INSERT INTO terms_students (termid, studentid) VALUES ((SELECT t.id FROM terms t WHERE t.termname='2017 into 2018'), (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'));
 INSERT INTO terms_students (termid, studentid) VALUES ((SELECT t.id FROM terms t WHERE t.termname='2018 WinSpr'), (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'));
 INSERT INTO terms_students (termid, studentid) VALUES ((SELECT t.id FROM terms t WHERE t.termname='2018 Summer'), (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'));
 
/**********************************************************************************************
 * create records in the class users table for another institution
 **********************************************************************************************/
 INSERT INTO class_users (classid, userid, usertype) VALUES ((SELECT c.id FROM classes c WHERE classname='Math grade 9A'), (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'), 'TA');
 INSERT INTO class_users (classid, userid, usertype) VALUES ((SELECT c.id FROM classes c WHERE classname='Functions grade 12'), (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'), 'STUDENT');
 INSERT INTO class_users (classid, userid, usertype) VALUES ((SELECT c.id FROM classes c WHERE classname='Math grade 9B'), (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'), 'TA');
 INSERT INTO class_users (classid, userid, usertype) VALUES ((SELECT c.id FROM classes c WHERE classname='Algebra grade 12'), (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'), 'STUDENT');
 INSERT INTO class_users (classid, userid, usertype) VALUES ((SELECT c.id FROM classes c WHERE classname='Math grade 9C'), (SELECT u.id FROM users u WHERE emailaddress='bb@100.com'), 'TA');
