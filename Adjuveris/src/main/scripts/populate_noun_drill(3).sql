/*This creates Noun Drill exercises*/
USE alphplus;

INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,0,0,'First','porta','2015-04-15 21:23:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,0,1,'First','amica','2015-04-15 21:23:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,1,0,'Second','servus','2015-04-15 21:23:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,1,1,'Second','donum','2016-11-11 11:11:11');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,1,2,'Second','puer','2016-11-11 11:11:11');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,1,3,'Second','ager','2016-11-11 11:11:11');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,2,0,'Third','lex','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,2,1,'Third','rex','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,2,2,'Third','corpus','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,2,3,'Third','mos','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,2,4,'Third','genus','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,3,0,'Third i-stem','civis','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,3,1,'Third i-stem','urbs','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,3,2,'Third i-stem','mare','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,4,0,'Fourth','fructus','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,4,1,'Fourth','cornu','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,5,0,'Fifth','dies','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,5,1,'Fifth','res','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,6,0,'Irregular','senex','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,6,1,'Irregular','caro','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,6,2,'Irregular','os','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,7,0,'Greek first','Archias','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,7,1,'Greek first','epitome','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,7,2,'Greek first','cometes','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,8,0,'Greek second','barbitos','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,8,1,'Greek second','Androgeos','2016-11-23 17:00:00');
INSERT INTO exercises (createdbyid,exercisecategory,variable1,variable2,var1string,var2string,createddate) 
	VALUES (10,2,8,2,'Greek second','Ilion','2016-11-23 17:00:00');



/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'First amica' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (10,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (10,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (10,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (10,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (10,'Fill in the blank',5,'Singular Ablative',2,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (10,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (10,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (10,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (10,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (10,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'First amica' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (11,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (11,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (11,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (11,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (11,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (11,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (11,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (11,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (11,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (11,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Second servus' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (12,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (12,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (12,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (12,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (12,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (12,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (12,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (12,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (12,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (12,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Second donum' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (13,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (13,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (13,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (13,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (13,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (13,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (13,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (13,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (13,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (13,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Second puer' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (14,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (14,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (14,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (14,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (14,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (14,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (14,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (14,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (14,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (14,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Second ager' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (15,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (15,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (15,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (15,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (15,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (15,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (15,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (15,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (15,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (15,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Third lex' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (16,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (16,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (16,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (16,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (16,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (16,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (16,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (16,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (16,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (16,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Third rex' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (17,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (17,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (17,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (17,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (17,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (17,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (17,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (17,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (17,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (17,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Third corpus' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (18,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (18,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (18,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (18,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (18,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (18,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (18,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (18,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (18,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (18,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Third mos' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (19,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (19,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (19,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (19,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (19,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (19,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (19,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (19,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (19,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (19,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Third genus' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (20,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (20,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (20,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (20,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (20,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (20,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (20,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (20,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (20,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (20,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Third i-stem civis' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (21,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (21,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (21,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (21,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (21,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (21,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (21,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (21,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (21,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (21,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Third i-stem urbs' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (22,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (22,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (22,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (22,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (22,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (22,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (22,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (22,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (22,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (22,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Third i-stem mare' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (23,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (23,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (23,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (23,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (23,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (23,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (23,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (23,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (23,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (23,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Fourth fructus' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (24,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (24,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (24,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (24,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (24,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (24,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (24,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (24,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (24,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (24,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Fourth cornu' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (25,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (25,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (25,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (25,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (25,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (25,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (25,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (25,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (25,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (25,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Fifth dies' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (26,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (26,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (26,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (26,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (26,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (26,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (26,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (26,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (26,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (26,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Fifth res' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (27,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (27,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (27,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (27,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (27,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (27,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (27,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (27,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (27,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (27,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'irregular senex' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (28,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (28,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (28,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (28,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (28,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (28,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (28,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (28,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (28,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (28,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Irregular caro' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (29,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (29,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (29,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (29,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (29,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (29,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (29,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (29,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (29,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (29,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Irregular os' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (30,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (30,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (30,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (30,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (30,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (30,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (30,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (30,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (30,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (30,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Greek first Archias' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (31,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (31,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (31,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (31,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (31,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (31,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (31,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (31,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (31,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (31,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Greek first epitome' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (32,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (32,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (32,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (32,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (32,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (32,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (32,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (32,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (32,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (32,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Greek first cometes' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (33,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (33,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (33,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (33,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (33,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (33,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (33,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (33,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (33,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (33,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Greek second barbiton' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (34,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (34,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (34,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (34,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (34,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (34,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (34,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (34,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (34,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (34,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Greek second Androgeos' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (35,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (35,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (35,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (35,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (35,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (35,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (35,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (35,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (35,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (35,'Fill in the blank',10,'Plural Ablative',2,1,true);


/**********************************************************************************************
 * This script will create a 'template' for NounDrill for 'Greek second Ilion' 
 **********************************************************************************************/
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (36,'Fill in the blank',1,'Singular Nominative',1,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (36,'Fill in the blank',2,'Singular Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (36,'Fill in the blank',3,'Singular Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (36,'Fill in the blank',4,'Singular Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (36,'Fill in the blank',5,'Singular Ablative',3,1,true);


INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (36,'Fill in the blank',6,'Plural Nominative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (36,'Fill in the blank',7,'Plural Genitive',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (36,'Fill in the blank',8,'Plural Accusative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (36,'Fill in the blank',9,'Plural Dative',2,1,true);
INSERT INTO questions (exerciseid,questiontype,qnumber,question,proficiencyindex,weight,swassessed) VALUES (36,'Fill in the blank',10,'Plural Ablative',2,1,true);


/********************************
* Populate answers
*********************************/
INSERT INTO answers (questionid,answer,correct) VALUES(271,'porta',1);
INSERT INTO answers (questionid,answer,correct) VALUES(272,'portae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(273,'portam',1);
INSERT INTO answers (questionid,answer,correct) VALUES(274,'portae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(275,'porta',1);
INSERT INTO answers (questionid,answer,correct) VALUES(276,'portae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(277,'portarum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(278,'portas',1);
INSERT INTO answers (questionid,answer,correct) VALUES(279,'portis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(280,'portis',1);


INSERT INTO answers (questionid,answer,correct) VALUES(281,'amica',1);
INSERT INTO answers (questionid,answer,correct) VALUES(282,'amicae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(283,'amicam',1);
INSERT INTO answers (questionid,answer,correct) VALUES(284,'amicae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(285,'amica',1);
INSERT INTO answers (questionid,answer,correct) VALUES(286,'amicae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(287,'amicarum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(288,'amicas',1);
INSERT INTO answers (questionid,answer,correct) VALUES(289,'amicis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(290,'amicis',1);


INSERT INTO answers (questionid,answer,correct) VALUES(291,'servus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(292,'servi',1);
INSERT INTO answers (questionid,answer,correct) VALUES(293,'servum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(294,'servo',1);
INSERT INTO answers (questionid,answer,correct) VALUES(295,'servo',1);
INSERT INTO answers (questionid,answer,correct) VALUES(296,'servi',1);
INSERT INTO answers (questionid,answer,correct) VALUES(297,'servorum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(298,'servos',1);
INSERT INTO answers (questionid,answer,correct) VALUES(299,'servis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(300,'servis',1);


INSERT INTO answers (questionid,answer,correct) VALUES(301,'donum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(302,'doni',1);
INSERT INTO answers (questionid,answer,correct) VALUES(303,'donum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(304,'dono',1);
INSERT INTO answers (questionid,answer,correct) VALUES(305,'dono',1);
INSERT INTO answers (questionid,answer,correct) VALUES(306,'dona',1);
INSERT INTO answers (questionid,answer,correct) VALUES(307,'donorum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(308,'dona',1);
INSERT INTO answers (questionid,answer,correct) VALUES(309,'donis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(310,'donis',1);


INSERT INTO answers (questionid,answer,correct) VALUES(311,'puer',1);
INSERT INTO answers (questionid,answer,correct) VALUES(312,'pueri',1);
INSERT INTO answers (questionid,answer,correct) VALUES(313,'puerum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(314,'puero',1);
INSERT INTO answers (questionid,answer,correct) VALUES(315,'puero',1);
INSERT INTO answers (questionid,answer,correct) VALUES(316,'pueri',1);
INSERT INTO answers (questionid,answer,correct) VALUES(317,'puerorum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(318,'pueros',1);
INSERT INTO answers (questionid,answer,correct) VALUES(319,'pueris',1);
INSERT INTO answers (questionid,answer,correct) VALUES(320,'pueris',1);


INSERT INTO answers (questionid,answer,correct) VALUES(321,'ager',1);
INSERT INTO answers (questionid,answer,correct) VALUES(322,'agri',1);
INSERT INTO answers (questionid,answer,correct) VALUES(323,'agrum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(324,'agro',1);
INSERT INTO answers (questionid,answer,correct) VALUES(325,'agro',1);
INSERT INTO answers (questionid,answer,correct) VALUES(326,'agri',1);
INSERT INTO answers (questionid,answer,correct) VALUES(327,'agrorum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(328,'agros',1);
INSERT INTO answers (questionid,answer,correct) VALUES(329,'agris',1);
INSERT INTO answers (questionid,answer,correct) VALUES(330,'agris',1);


INSERT INTO answers (questionid,answer,correct) VALUES(331,'lex',1);
INSERT INTO answers (questionid,answer,correct) VALUES(332,'legis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(333,'legem',1);
INSERT INTO answers (questionid,answer,correct) VALUES(334,'legi',1);
INSERT INTO answers (questionid,answer,correct) VALUES(335,'lege',1);
INSERT INTO answers (questionid,answer,correct) VALUES(336,'leges',1);
INSERT INTO answers (questionid,answer,correct) VALUES(337,'legium',1);
INSERT INTO answers (questionid,answer,correct) VALUES(338,'leges',1);
INSERT INTO answers (questionid,answer,correct) VALUES(339,'legibus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(340,'legibus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(341,'rex',1);
INSERT INTO answers (questionid,answer,correct) VALUES(342,'regis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(343,'regem',1);
INSERT INTO answers (questionid,answer,correct) VALUES(344,'regi',1);
INSERT INTO answers (questionid,answer,correct) VALUES(345,'rege',1);
INSERT INTO answers (questionid,answer,correct) VALUES(346,'reges',1);
INSERT INTO answers (questionid,answer,correct) VALUES(347,'regium',1);
INSERT INTO answers (questionid,answer,correct) VALUES(348,'reges',1);
INSERT INTO answers (questionid,answer,correct) VALUES(349,'regibus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(350,'regibus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(351,'corpus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(352,'corporis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(353,'corpus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(354,'corpori',1);
INSERT INTO answers (questionid,answer,correct) VALUES(355,'corpore',1);
INSERT INTO answers (questionid,answer,correct) VALUES(356,'corpora',1);
INSERT INTO answers (questionid,answer,correct) VALUES(357,'corporum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(358,'corpora',1);
INSERT INTO answers (questionid,answer,correct) VALUES(359,'corporibus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(360,'corporibus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(361,'mos',1);
INSERT INTO answers (questionid,answer,correct) VALUES(362,'moris',1);
INSERT INTO answers (questionid,answer,correct) VALUES(363,'morem',1);
INSERT INTO answers (questionid,answer,correct) VALUES(364,'mori',1);
INSERT INTO answers (questionid,answer,correct) VALUES(365,'more',1);
INSERT INTO answers (questionid,answer,correct) VALUES(366,'mores',1);
INSERT INTO answers (questionid,answer,correct) VALUES(367,'morum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(368,'mores',1);
INSERT INTO answers (questionid,answer,correct) VALUES(369,'moribus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(370,'moribus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(371,'genus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(372,'generis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(373,'genus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(374,'generi',1);
INSERT INTO answers (questionid,answer,correct) VALUES(375,'genere',1);
INSERT INTO answers (questionid,answer,correct) VALUES(376,'genera',1);
INSERT INTO answers (questionid,answer,correct) VALUES(377,'generum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(378,'genera',1);
INSERT INTO answers (questionid,answer,correct) VALUES(379,'generibus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(380,'generibus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(381,'civis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(382,'civis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(383,'civem',1);
INSERT INTO answers (questionid,answer,correct) VALUES(384,'civi',1);
INSERT INTO answers (questionid,answer,correct) VALUES(385,'cive',1);
INSERT INTO answers (questionid,answer,correct) VALUES(386,'cives',1);
INSERT INTO answers (questionid,answer,correct) VALUES(387,'civium',1);
INSERT INTO answers (questionid,answer,correct) VALUES(388,'cives',1);
INSERT INTO answers (questionid,answer,correct) VALUES(389,'civibus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(390,'civibus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(391,'urbs',1);
INSERT INTO answers (questionid,answer,correct) VALUES(392,'urbis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(393,'urbem',1);
INSERT INTO answers (questionid,answer,correct) VALUES(394,'urbi',1);
INSERT INTO answers (questionid,answer,correct) VALUES(395,'urbe',1);
INSERT INTO answers (questionid,answer,correct) VALUES(396,'urbes',1);
INSERT INTO answers (questionid,answer,correct) VALUES(397,'urbium',1);
INSERT INTO answers (questionid,answer,correct) VALUES(398,'urbes',1);
INSERT INTO answers (questionid,answer,correct) VALUES(399,'urbibus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(400,'urbibus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(401,'mare',1);
INSERT INTO answers (questionid,answer,correct) VALUES(402,'maris',1);
INSERT INTO answers (questionid,answer,correct) VALUES(403,'mare',1);
INSERT INTO answers (questionid,answer,correct) VALUES(404,'mari',1);
INSERT INTO answers (questionid,answer,correct) VALUES(405,'mare',1);
INSERT INTO answers (questionid,answer,correct) VALUES(406,'maria',1);
INSERT INTO answers (questionid,answer,correct) VALUES(407,'marium',1);
INSERT INTO answers (questionid,answer,correct) VALUES(408,'maria',1);
INSERT INTO answers (questionid,answer,correct) VALUES(409,'maribus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(410,'maribus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(411,'fructus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(412,'fructus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(413,'fructum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(414,'fructui',1);
INSERT INTO answers (questionid,answer,correct) VALUES(415,'fructu',1);
INSERT INTO answers (questionid,answer,correct) VALUES(416,'fructus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(417,'fructuum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(418,'fructus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(419,'fructibus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(420,'fructibus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(421,'cornu',1);
INSERT INTO answers (questionid,answer,correct) VALUES(422,'cornus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(423,'cornu',1);
INSERT INTO answers (questionid,answer,correct) VALUES(424,'cornu',1);
INSERT INTO answers (questionid,answer,correct) VALUES(425,'cornu',1);
INSERT INTO answers (questionid,answer,correct) VALUES(426,'cornua',1);
INSERT INTO answers (questionid,answer,correct) VALUES(427,'cornuum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(428,'cornua',1);
INSERT INTO answers (questionid,answer,correct) VALUES(429,'cornibus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(430,'cornibus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(431,'dies',1);
INSERT INTO answers (questionid,answer,correct) VALUES(432,'diei',1);
INSERT INTO answers (questionid,answer,correct) VALUES(433,'diem',1);
INSERT INTO answers (questionid,answer,correct) VALUES(434,'diei',1);
INSERT INTO answers (questionid,answer,correct) VALUES(435,'die',1);
INSERT INTO answers (questionid,answer,correct) VALUES(436,'dies',1);
INSERT INTO answers (questionid,answer,correct) VALUES(437,'dierum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(438,'dies',1);
INSERT INTO answers (questionid,answer,correct) VALUES(439,'diebus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(440,'diebus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(441,'res',1);
INSERT INTO answers (questionid,answer,correct) VALUES(442,'rei',1);
INSERT INTO answers (questionid,answer,correct) VALUES(443,'rem',1);
INSERT INTO answers (questionid,answer,correct) VALUES(444,'rei',1);
INSERT INTO answers (questionid,answer,correct) VALUES(445,'re',1);
INSERT INTO answers (questionid,answer,correct) VALUES(446,'res',1);
INSERT INTO answers (questionid,answer,correct) VALUES(447,'rerum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(448,'res',1);
INSERT INTO answers (questionid,answer,correct) VALUES(449,'rebus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(450,'rebus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(451,'senex',1);
INSERT INTO answers (questionid,answer,correct) VALUES(452,'senis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(453,'senem',1);
INSERT INTO answers (questionid,answer,correct) VALUES(454,'seni',1);
INSERT INTO answers (questionid,answer,correct) VALUES(455,'sene',1);
INSERT INTO answers (questionid,answer,correct) VALUES(456,'senes',1);
INSERT INTO answers (questionid,answer,correct) VALUES(457,'senum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(458,'senes',1);
INSERT INTO answers (questionid,answer,correct) VALUES(459,'senibus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(460,'senibus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(461,'caro',1);
INSERT INTO answers (questionid,answer,correct) VALUES(462,'carnis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(463,'carnem',1);
INSERT INTO answers (questionid,answer,correct) VALUES(464,'carni',1);
INSERT INTO answers (questionid,answer,correct) VALUES(465,'carne',1);
INSERT INTO answers (questionid,answer,correct) VALUES(466,'carnes',1);
INSERT INTO answers (questionid,answer,correct) VALUES(467,'carnium',1);
INSERT INTO answers (questionid,answer,correct) VALUES(468,'carnes',1);
INSERT INTO answers (questionid,answer,correct) VALUES(469,'carnibus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(470,'carnibus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(471,'os',1);
INSERT INTO answers (questionid,answer,correct) VALUES(472,'ossis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(473,'os',1);
INSERT INTO answers (questionid,answer,correct) VALUES(474,'ossi',1);
INSERT INTO answers (questionid,answer,correct) VALUES(475,'osse',1);
INSERT INTO answers (questionid,answer,correct) VALUES(476,'ossa',1);
INSERT INTO answers (questionid,answer,correct) VALUES(477,'ossium',1);
INSERT INTO answers (questionid,answer,correct) VALUES(478,'ossa',1);
INSERT INTO answers (questionid,answer,correct) VALUES(479,'ossibus',1);
INSERT INTO answers (questionid,answer,correct) VALUES(480,'ossibus',1);


INSERT INTO answers (questionid,answer,correct) VALUES(481,'Archias',1);
INSERT INTO answers (questionid,answer,correct) VALUES(482,'Archiae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(483,'Archiam',1);
INSERT INTO answers (questionid,answer,correct) VALUES(484,'Archiae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(485,'Archia',1);
INSERT INTO answers (questionid,answer,correct) VALUES(486,'--',1);
INSERT INTO answers (questionid,answer,correct) VALUES(487,'--',1);
INSERT INTO answers (questionid,answer,correct) VALUES(488,'--',1);
INSERT INTO answers (questionid,answer,correct) VALUES(489,'--',1);
INSERT INTO answers (questionid,answer,correct) VALUES(490,'--',1);


INSERT INTO answers (questionid,answer,correct) VALUES(491,'epitome',1);
INSERT INTO answers (questionid,answer,correct) VALUES(492,'epitomes',1);
INSERT INTO answers (questionid,answer,correct) VALUES(493,'epitomen',1);
INSERT INTO answers (questionid,answer,correct) VALUES(494,'epitomae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(495,'epitome',1);
INSERT INTO answers (questionid,answer,correct) VALUES(496,'epitomae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(497,'epitomarum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(498,'epitomas',1);
INSERT INTO answers (questionid,answer,correct) VALUES(499,'epitomis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(500,'epitomis',1);


INSERT INTO answers (questionid,answer,correct) VALUES(501,'cometes',1);
INSERT INTO answers (questionid,answer,correct) VALUES(502,'cometae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(503,'cometen',1);
INSERT INTO answers (questionid,answer,correct) VALUES(504,'cometae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(505,'comete',1);
INSERT INTO answers (questionid,answer,correct) VALUES(506,'cometae',1);
INSERT INTO answers (questionid,answer,correct) VALUES(507,'cometarum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(508,'cometas',1);
INSERT INTO answers (questionid,answer,correct) VALUES(509,'cometis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(510,'cometis',1);


INSERT INTO answers (questionid,answer,correct) VALUES(511,'barbitos',1);
INSERT INTO answers (questionid,answer,correct) VALUES(512,'barbiti',1);
INSERT INTO answers (questionid,answer,correct) VALUES(513,'barbiton',1);
INSERT INTO answers (questionid,answer,correct) VALUES(514,'barbito',1);
INSERT INTO answers (questionid,answer,correct) VALUES(515,'barbito',1);
INSERT INTO answers (questionid,answer,correct) VALUES(516,'barbiti',1);
INSERT INTO answers (questionid,answer,correct) VALUES(517,'barbitorum',1);
INSERT INTO answers (questionid,answer,correct) VALUES(518,'barbitos',1);
INSERT INTO answers (questionid,answer,correct) VALUES(519,'barbitis',1);
INSERT INTO answers (questionid,answer,correct) VALUES(520,'barbitis',1);


INSERT INTO answers (questionid,answer,correct) VALUES(521,'Androgeos',1);
INSERT INTO answers (questionid,answer,correct) VALUES(522,'Androgeo',1);
INSERT INTO answers (questionid,answer,correct) VALUES(523,'Androgeon',1);
INSERT INTO answers (questionid,answer,correct) VALUES(524,'Androgeo',1);
INSERT INTO answers (questionid,answer,correct) VALUES(525,'Androgeo',1);
INSERT INTO answers (questionid,answer,correct) VALUES(526,'--',1);
INSERT INTO answers (questionid,answer,correct) VALUES(527,'--',1);
INSERT INTO answers (questionid,answer,correct) VALUES(528,'--',1);
INSERT INTO answers (questionid,answer,correct) VALUES(529,'--',1);
INSERT INTO answers (questionid,answer,correct) VALUES(530,'--',1);


INSERT INTO answers (questionid,answer,correct) VALUES(531,'Ilion',1);
INSERT INTO answers (questionid,answer,correct) VALUES(532,'Ilii',1);
INSERT INTO answers (questionid,answer,correct) VALUES(533,'Ilion',1);
INSERT INTO answers (questionid,answer,correct) VALUES(534,'Ilio',1);
INSERT INTO answers (questionid,answer,correct) VALUES(535,'Ilio',1);
INSERT INTO answers (questionid,answer,correct) VALUES(536,'--',1);
INSERT INTO answers (questionid,answer,correct) VALUES(537,'--',1);
INSERT INTO answers (questionid,answer,correct) VALUES(538,'--',1);
INSERT INTO answers (questionid,answer,correct) VALUES(539,'--',1);
INSERT INTO answers (questionid,answer,correct) VALUES(540,'--',1);