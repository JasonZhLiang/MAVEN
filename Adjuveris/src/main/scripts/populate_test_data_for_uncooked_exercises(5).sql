USE alphplus;

 /**********************************************************************************************
 * Create the assignments for the latin pronoun drill exercises from teacher 1 and 2 to a students in Latin 101 and Latin 102
 **********************************************************************************************/
INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status,type,weight,additionalresources,createddate,comment) 
	VALUES('Uncooked Assignment a',1,1,'2017-01-01 00:00:00','2017-01-03 00:00:00','2017-01-01 21:23:00','COMPLETED','NORMAL',0,'','2017-04-07 21:23:00','--');
INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status,type,weight,additionalresources,createddate,comment) 
	VALUES('Uncooked Assignment b',2,1,'2017-01-01 00:00:00','2017-01-03 00:00:00','2017-01-01 21:23:00','COMPLETED','NORMAL',0,'','2017-04-07 21:23:00','--');    
INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status,type,weight,additionalresources,createddate,comment) 
	VALUES('Uncooked Assignment c',3,1,'2017-01-01 00:00:00','2017-01-03 00:00:00','2017-01-01 21:23:00','COMPLETED','NORMAL',0,'','2017-04-07 21:23:00','--'); 
INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status,type,weight,additionalresources,createddate,comment) 
	VALUES('Uncooked Assignment d',4,1,'2017-01-01 00:00:00','2017-01-03 00:00:00','2017-01-01 21:23:00','COMPLETED','NORMAL',0,'','2017-04-07 21:23:00','--');
INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status,type,weight,additionalresources,createddate,comment) 
	VALUES('Uncooked Assignment e',5,1,'2017-01-01 00:00:00','2017-01-03 00:00:00','2017-01-01 21:23:00','COMPLETED','NORMAL',0,'','2017-04-07 21:23:00','--');    
INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status,type,weight,additionalresources,createddate,comment) 
	VALUES('Uncooked Assignment f',6,1,'2017-01-01 00:00:00','2017-01-03 00:00:00','2017-01-01 21:23:00','COMPLETED','NORMAL',0,'','2015-04-07 21:23:00','--'); 

INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status,type,weight,additionalresources,createddate,comment) 
	VALUES('Uncooked Assignment a',1,1,'2016-09-09 00:00:00','2016-09-16 00:00:00','2016-09-07 21:23:00','COMPLETED','NORMAL',0,'','2015-04-07 21:23:00','--');
INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status,type,weight,additionalresources,createddate,comment) 
	VALUES('Uncooked Assignment b',2,1,'2016-09-15 00:00:00','2016-09-19 00:00:00','2016-09-07 21:23:00','COMPLETED','NORMAL',0,'','2015-04-07 21:23:00','--');    
INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status,type,weight,additionalresources,createddate,comment) 
	VALUES('Uncooked Assignment c',3,1,'2016-09-19 00:00:00','2016-09-22 00:00:00','2016-09-07 21:23:00','COMPLETED','NORMAL',0,'','2015-04-07 21:23:00','--'); 
INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status,type,weight,additionalresources,createddate,comment) 
	VALUES('Uncooked Assignment d',4,1,'2016-09-22 00:00:00','2016-09-23 00:00:00','2016-09-07 21:23:00','COMPLETED','NORMAL',0,'','2015-04-07 21:23:00','--');
INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status,type,weight,additionalresources,createddate,comment) 
	VALUES('Uncooked Assignment e',5,1,'2016-10-01 00:00:00','2016-10-07 00:00:00','2016-09-07 21:23:00','COMPLETED','NORMAL',0,'','2015-04-07 21:23:00','--');    
INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status,type,weight,additionalresources,createddate,comment) 
	VALUES('Uncooked Assignment f',6,1,'2016-10-07 00:00:00','2016-10-15 00:00:00','2016-09-07 21:23:00','COMPLETED','NORMAL',0,'','2015-04-07 21:23:00','--'); 


/**********************************************************************************************
 * This script add assignments facts
 **********************************************************************************************/
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,4);
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,5);
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,6);
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,7);
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,8);
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,9);
 
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,10);
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,11);
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,12);
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,13);
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,14);
 INSERT INTO assignments_fact (classid,studentid,groupid,assignmentid) VALUES (1,5,NULL,15);
 /**********************************************************************************************
 * This script add student anwsers
 **********************************************************************************************/
 SET foreign_key_checks = 0;
 
 delete from student_answers where assignmentfactid>=4;
 
 INSERT INTO student_answers (assignmentfactid,questionid, studentanswer) 
 SELECT 4 as assignmentfactid, id, answer FROM answers WHERE questionid > 0 AND questionid < 31;
 
 INSERT INTO student_answers (assignmentfactid,questionid, studentanswer) 
 SELECT 5 as assignmentfactid, id, answer FROM answers WHERE questionid > 30 AND questionid < 61;
 
 INSERT INTO student_answers (assignmentfactid,questionid, studentanswer) 
 SELECT 6 as assignmentfactid, id, answer FROM answers WHERE questionid > 60 AND questionid < 91;
 INSERT INTO student_answers (assignmentfactid,questionid, studentanswer) 
 
 SELECT 7 as assignmentfactid, id, answer FROM answers WHERE questionid > 90 AND questionid < 121;
 
 INSERT INTO student_answers (assignmentfactid,questionid, studentanswer) 
 SELECT 8 as assignmentfactid, id, answer FROM answers WHERE questionid > 120 AND questionid < 151;
 
 INSERT INTO student_answers (assignmentfactid,questionid, studentanswer) 
 SELECT 9 as assignmentfactid, id, answer FROM answers WHERE questionid > 150 AND questionid < 181;

 update student_answers set studentanswer="" where assignmentfactid >=4 and cast(id/3 as signed) = id/3;
 
 SET foreign_key_checks = 1;
 
 /**********************************************************************************************
 * This script add some notifications
 **********************************************************************************************/
 /*INSERT INTO notifications (assignmentfactid, createdbyid, note, startdate, expirydate)
 SELECT af.id, a.teacherid, CONCAT('LATIN 101 Student, ',a.assignmentname ,' is now available.') AS Note, a.startdate, a.duedate
 FROM assignments a INNER JOIN assignments_fact af WHERE a.id=af.assignmentid and a.id>=4;*/
 
  /**********************************************************************************************
 * Initial population of assignment_classes table
 * Author: Kwirtanen
 * Date: 2015-12-02
 **********************************************************************************************/  
  
INSERT INTO assignment_classes (assignmentId, classId) VALUES (1,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (2,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (3,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (4,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (5,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (6,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (7,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (8,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (9,1);

INSERT INTO assignment_classes (assignmentId, classId) VALUES (10,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (11,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (12,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (13,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (14,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (15,1);
INSERT INTO assignment_classes (assignmentId, classId) VALUES (16,1);



 
 
 /***********************************************************************************************
  * These scripts populate exercise, exercisecategories,assignments, assignments_fact, questions and
  * question_options tables to test the TeacherGeneratedExerciseServlet
  * author:  Gene 3 December 2015
  *********************************************************************************************/
INSERT INTO exercisecategories (exercisename,urlbase,exercisetype) values ('testBA','TeacherGeneratedExerciseServlet','PRACTICE');

INSERT INTO exercises (createdbyid,exercisecategory,var1string,var2string) values (1,LAST_INSERT_ID(),'None','None');

INSERT INTO assignments (assignmentname,exerciseid,teacherid,startdate,duedate,updatedate,status) values 
('testingServlet',LAST_INSERT_ID(),1,'2017-03-15','2017-12-31','2017-03-15','START');

INSERT INTO assignments_fact (assignmentid,classid,studentid) values (LAST_INSERT_ID(),1,5);

SET foreign_key_checks = 0;
INSERT INTO questions (exerciseid,questiontype,qnumber,question) values ((SELECT MAX(id) FROM exercises LIMIT 1),'True-false',1,'Do bears live in the woods?');
INSERT INTO answers (questionid, answer,correct) values ((SELECT MAX(id) FROM questions LIMIT 1),'true',1);
INSERT INTO questions (exerciseid,questiontype,qnumber,question) values ((SELECT MAX(id) FROM exercises LIMIT 1),'True-false',2,'Do bears read books?');
INSERT INTO answers (questionid, answer,correct) values ((SELECT MAX(id) FROM questions LIMIT 1),'false',1);
INSERT INTO questions (exerciseid,questiontype,qnumber,question) values ((SELECT MAX(id) FROM exercises LIMIT 1),'True-false',3,'Do bears like beer?');
INSERT INTO answers (questionid, answer,correct) values ((SELECT MAX(id) FROM questions LIMIT 1),'true',1);
SET foreign_key_checks = 1;

/*done    (SELECT MAX(id) FROM exercises LIMIT 1)*/

 
 