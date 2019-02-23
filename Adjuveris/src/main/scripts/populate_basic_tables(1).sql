USE alphplus;

/**********************************************************************************************
 * Generate permissions types
 **********************************************************************************************/
INSERT INTO permissions (permission) VALUES
	('STUDENT'),
	('TEACHER'),
	('TA'),
	('INSTITUTION_ADMIN'),
	('ADMINISTRATOR');

/**********************************************************************************************
 * Create an institution
 **********************************************************************************************/
INSERT INTO institutions(instname, postaddress, primecontact, primephone, primeemail, adminfirstname, adminlastname,
adminemail) VALUES ('Demo College', '1234 University Drive', 'Bob Smith', '613-555-5555', 'admin@democ.com',
'institution', 'admin', 'inst_admin@100.com');

INSERT INTO date_ranges(instid, studentsTerms, instAdminTerms) 
	VALUES ((SELECT i.id FROM institutions i WHERE i.instname='Demo College'), 0, -1);

/**********************************************************************************************
 * Populate user and permissions tables with 2 TEACHER-type users
 **********************************************************************************************/
INSERT INTO users (password, firstname, lastname, emailaddress)
	VALUES(sha1('aaaaaa'), 'Ima', 'Teacher', 'te@100.com');

INSERT INTO users_permissions (userid, permissionid)
    VALUES((SELECT u.id FROM users u where emailaddress='te@100.com'), 
           (SELECT p.id from permissions p where permission='TEACHER'));

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='Demo College'),
           (SELECT u.id FROM users u WHERE emailaddress='te@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='TEACHER'));

INSERT INTO users (password, firstname, lastname, emailaddress)
	VALUES(sha1('aaaaaa'), 'Ima', 'Teachure', 'te2@100.com');

INSERT INTO users_permissions (userid, permissionid)
    VALUES((SELECT u.id FROM users u where emailaddress='te2@100.com'), 
           (SELECT p.id from permissions p where permission='TEACHER'));        

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='Demo College'),
           (SELECT u.id FROM users u WHERE emailaddress='te2@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='TEACHER'));        

/**********************************************************************************************
 * Populate user and permissions tables with 2 TA-type users
 **********************************************************************************************/
INSERT INTO users (password, firstname, lastname, emailaddress)
	VALUES(sha1('aaaaaa'), 'Iaman', 'Assistant', 'ta@100.com');

INSERT INTO users_permissions (userid, permissionid)
    VALUES((SELECT u.id FROM users u where emailaddress='ta@100.com'), 
           (SELECT p.id from permissions p where permission='TA'));

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='Demo College'),
           (SELECT u.id FROM users u WHERE emailaddress='ta@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='TA'));

INSERT INTO users (password, firstname, lastname, emailaddress)
	VALUES(sha1('aaaaaa'), 'Imalso', 'Assistant', 'ta2@100.com');

INSERT INTO users_permissions (userid, permissionid)
    VALUES((SELECT u.id FROM users u where emailaddress='ta2@100.com'), 
           (SELECT p.id from permissions p where permission='TA'));           

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='Demo College'),
           (SELECT u.id FROM users u WHERE emailaddress='ta2@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='TA'));           

/**********************************************************************************************
 * Populate user and permissions tables with 4 STUDENT-type users
 **********************************************************************************************/
INSERT INTO users (password, firstname, lastname, emailaddress)
	VALUES(sha1('aaaaaa'), 'Gussie', 'Fink-Nottle', 'nb@100.com');

INSERT INTO users_permissions (userid, permissionid)
    VALUES((SELECT u.id FROM users u where emailaddress='nb@100.com'), 
           (SELECT p.id from permissions p where permission='STUDENT'));

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='Demo College'),
           (SELECT u.id FROM users u WHERE emailaddress='nb@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='STUDENT'));

INSERT INTO users (password, firstname, lastname, emailaddress)
	VALUES(sha1('aaaaaa'), 'Diana', 'Turn-Bottom', 'nb2@100.com');

INSERT INTO users_permissions (userid, permissionid)
    VALUES((SELECT u.id FROM users u where emailaddress='nb2@100.com'), 
           (SELECT p.id from permissions p where permission='STUDENT'));    

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='Demo College'),
           (SELECT u.id FROM users u WHERE emailaddress='nb2@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='STUDENT'));    

INSERT INTO users (password, firstname, lastname, emailaddress)
	VALUES(sha1('aaaaaa'), 'Trey', 'Landon', 'nb3@100.com');

INSERT INTO users_permissions (userid, permissionid)
    VALUES((SELECT u.id FROM users u where emailaddress='nb3@100.com'), 
           (SELECT p.id from permissions p where permission='STUDENT')); 

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='Demo College'),
           (SELECT u.id FROM users u WHERE emailaddress='nb3@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='STUDENT')); 

INSERT INTO users (password, firstname, lastname, emailaddress)
	VALUES(sha1('aaaaaa'), 'Girda', 'Thorn', 'nb4@100.com');

INSERT INTO users_permissions (userid, permissionid)
    VALUES((SELECT u.id FROM users u where emailaddress='nb4@100.com'), 
           (SELECT p.id from permissions p where permission='STUDENT'));        

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='Demo College'),
           (SELECT u.id FROM users u WHERE emailaddress='nb4@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='STUDENT'));        

/**********************************************************************************************
 * Populate user and permissions tables with ADMINISTRATOR-type user
 **********************************************************************************************/
INSERT INTO users (password, firstname, lastname, emailaddress)
    VALUES(sha1('Password1'), 'admin', 'admin', 'admin@100.com');

INSERT INTO users_permissions (userid, permissionid)
    VALUES((SELECT u.id FROM users u where emailaddress='admin@100.com'), 
           (SELECT p.id from permissions p where permission='ADMINISTRATOR'));

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='Demo College'),
           (SELECT u.id FROM users u WHERE emailaddress='admin@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='ADMINISTRATOR'));

/**********************************************************************************************
 * Populate user and permissions tables with TEACHER-type user called SYSTEM
 * This user will own the pre-cooked exercises
 **********************************************************************************************/     
INSERT INTO users (password, firstname, lastname, emailaddress)
	VALUES(sha1('aaaaaa'), 'TE', 'SYSTEM', 'tesys@100.com');

INSERT INTO users_permissions (userid, permissionid)
    VALUES((SELECT u.id FROM users u where emailaddress='tesys@100.com'), 
           (SELECT p.id from permissions p where permission='TEACHER'));

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='Demo College'),
           (SELECT u.id FROM users u WHERE emailaddress='tesys@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='TEACHER'));

/**********************************************************************************************
 * Populate user and permissions tables with INSTITUTION_ADMIN-type user
 **********************************************************************************************/
INSERT INTO users (password, firstname, lastname, emailaddress)
    VALUES(sha1('aaaaaa'), 'institution', 'admin', 'inst_admin@100.com');

INSERT INTO users_permissions (userid, permissionid)
    VALUES((SELECT u.id from users u where emailaddress='inst_admin@100.com'), 
           (SELECT p.id from permissions p where permission='INSTITUTION_ADMIN'));

INSERT INTO inst_user_permissions (instid, userid, permissionid)
    VALUES((SELECT i.id FROM institutions i WHERE i.instname='Demo College'),
           (SELECT u.id FROM users u WHERE emailaddress='inst_admin@100.com'), 
           (SELECT p.id FROM permissions p WHERE permission='INSTITUTION_ADMIN'));

/**********************************************************************************************
 * Populate message table with an initial message
 **********************************************************************************************/			
INSERT INTO message (status, message) VALUES ('HIDE','No message.');

/**********************************************************************************************
 * Generate proficiencies types
 * Author: Tammy
 * Date: 2015-02-09
 **********************************************************************************************/
INSERT INTO proficiencies (proficiency) VALUES
	('VOCABULARY'),
	('INFLECTIONS'),
	('SYNTAX'),
    ('COMPREHENSION');

/**********************************************************************************************
 * Initial population of exercisecategories table
 * Author: Gene
 * Date: 2015-07-09
 **********************************************************************************************/
  INSERT INTO exercisecategories (exercisename,urlbase,exercisetype) VALUES ('Pronoun Drill','PronounDrill/PronounDrill.html','PRACTICE'); 
  INSERT INTO exercisecategories (exercisename,urlbase,exercisetype) VALUES ('Noun Drill','NounDrill/LatinNounDrill.html','PRACTICE');  
