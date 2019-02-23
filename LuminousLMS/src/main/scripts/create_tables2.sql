/*
 * Username = eoadmin
 *
 * password = arethusa232
 */
DROP DATABASE IF EXISTS eolms;
CREATE DATABASE eolms DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
USE eolms;

-- -----------------------------------------------------
-- Table eolms.orgs
-- -----------------------------------------------------
DROP TABLE if EXISTS orgs;

CREATE TABLE orgs (
id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
orgname VARCHAR(50) NOT NULL UNIQUE KEY,
orgemail VARCHAR(50) NULL DEFAULT NULL
);


-- -----------------------------------------------------
-- Table eolms.assignments
-- -----------------------------------------------------
DROP TABLE if EXISTS assignments;

CREATE TABLE assignments (
id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
orgid INT(11) NOT NULL,
name VARCHAR(500) NULL DEFAULT NULL,
description VARCHAR(4500) NULL,
assisted TINYINT(1) NULL DEFAULT NULL,
CONSTRAINT fk_a_orgid FOREIGN KEY (orgid) REFERENCES orgs (id)
  ON DELETE NO ACTION ON UPDATE CASCADE
  );


-- -----------------------------------------------------
-- Table eolms.content_types
-- -----------------------------------------------------
DROP TABLE if EXISTS content_types;

CREATE TABLE content_types (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  type VARCHAR(20) NOT NULL
  );


-- -----------------------------------------------------
-- Table eolms.users
-- -----------------------------------------------------
DROP TABLE if EXISTS users;

CREATE TABLE users (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  password VARCHAR(50) NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  emailaddress VARCHAR(80) NOT NULL UNIQUE KEY,
  accountcreatedtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  accountclosedtime TIMESTAMP NULL,
  status INT(11) NULL DEFAULT 0,
  question VARCHAR(50) DEFAULT NULL,
  answer VARCHAR(50) DEFAULT NULL
  );


-- -----------------------------------------------------
-- Table eolms.clients_assignments
-- -----------------------------------------------------
DROP TABLE if EXISTS clients_assignments;

CREATE TABLE clients_assignments (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  clientid INT(11) NOT NULL,
  assignmentid INT(11) NOT NULL,
  duedate DATE NOT NULL DEFAULT '2017-12-30',
  INDEX fk_ca_clientid (clientid ASC),
  INDEX fk_ca_assignmentid_idx (assignmentid ASC),
CONSTRAINT fk_ca_assignmentid FOREIGN KEY (assignmentid) REFERENCES assignments (id)
  ON DELETE NO ACTION ON UPDATE CASCADE,
CONSTRAINT fk_ca_clientid FOREIGN KEY (clientid) REFERENCES users (id)
  ON DELETE NO ACTION ON UPDATE CASCADE
  );


-- -----------------------------------------------------
-- Table eolms.consultant_clients
-- -----------------------------------------------------
DROP TABLE if EXISTS consultant_clients;

CREATE TABLE consultant_clients (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  orgid INT(11) NOT NULL,
  consultantid INT(11) NOT NULL,
  clientid INT(11) NOT NULL,
  createdtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  closedtime TIMESTAMP NULL,
  INDEX fk_cc_orgid (orgid ASC),
  INDEX fk_cc_consultantid (consultantid ASC),
  INDEX fk_cc_clientid (clientid ASC),
CONSTRAINT fk_cc_clientid FOREIGN KEY (clientid) REFERENCES users (id)
  ON DELETE RESTRICT ON UPDATE RESTRICT,
CONSTRAINT fk_cc_consultantid FOREIGN KEY (consultantid) REFERENCES users (id),
CONSTRAINT fk_cc_orgid FOREIGN KEY (orgid) REFERENCES orgs (id)
);


-- -----------------------------------------------------
-- Table eolms.messages
-- -----------------------------------------------------
DROP TABLE if EXISTS messages;

CREATE TABLE messages (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  status VARCHAR(20) NOT NULL DEFAULT 'UNREAD',
  fromid INT(11) NOT NULL,
  toid INT(11) NOT NULL,
  orgid INT(11) NOT NULL,
  subject VARCHAR(50) NULL DEFAULT NULL,
  message VARCHAR(511) NOT NULL DEFAULT 'No message.',
  createdate DATETIME NOT NULL DEFAULT '2017-10-01 00:00:00',
  timezone VARCHAR(12) NOT NULL DEFAULT 'EST',
  INDEX fk_message_fromid (fromid ASC),
  INDEX fk_message_toid (toid ASC),
CONSTRAINT fk_message_fromid FOREIGN KEY (fromid) REFERENCES users (id)
  ON DELETE NO ACTION ON UPDATE RESTRICT,
CONSTRAINT fk_message_toid FOREIGN KEY (toid) REFERENCES users (id)
  ON DELETE CASCADE
 );


-- -----------------------------------------------------
-- Table eolms.notifications
-- -----------------------------------------------------
DROP TABLE if EXISTS notifications;

CREATE TABLE notifications (
  ID INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  orgid INT(11) NOT NULL,
  createdbyid INT(11) NOT NULL,
  subject VARCHAR(80) NOT NULL,
  note VARCHAR(200) NOT NULL,
  startdate DATETIME NOT NULL DEFAULT '2017-09-01 00:00:00',
  expirydate DATETIME NOT NULL DEFAULT '2017-09-01 00:00:00',
  timezone VARCHAR(12) NOT NULL DEFAULT 'EST',
  type INT(11) NOT NULL DEFAULT 0,
  INDEX fk_notifications_orgid (orgid ASC),
  INDEX fk_notifications_createdbyid (createdbyid ASC),
CONSTRAINT fk_notifications_createdbyid FOREIGN KEY (createdbyid) REFERENCES users (id)
  ON DELETE CASCADE,
CONSTRAINT fk_notifications_orgid FOREIGN KEY (orgid) REFERENCES orgs (id)
  ON DELETE CASCADE
  );


-- -----------------------------------------------------
-- Table eolms.permissions
-- -----------------------------------------------------
DROP TABLE if EXISTS permissions;

CREATE TABLE permissions (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  permission VARCHAR(50) UNIQUE KEY NOT NULL DEFAULT 'user'
  );


-- -----------------------------------------------------
-- Table eolms.org_user_permissions
-- -----------------------------------------------------
DROP TABLE if EXISTS org_user_permissions;

CREATE TABLE org_user_permissions (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  orgid INT(16) NOT NULL,
  userid INT(11) NOT NULL,
  permissionid INT(11) NOT NULL,
  INDEX fk_oup_orgid (orgid ASC),
  INDEX fk_oup_userid (userid ASC),
  INDEX fk_oup_permissionid (permissionid ASC),
CONSTRAINT fk_oup_orgid FOREIGN KEY (orgid) REFERENCES orgs (id),
CONSTRAINT fk_oup_permissionid FOREIGN KEY (permissionid) REFERENCES permissions (id),
CONSTRAINT fk_oup_userid FOREIGN KEY (userid) REFERENCES users (id));


-- -----------------------------------------------------
-- Table eolms.resources
-- -----------------------------------------------------
DROP TABLE if EXISTS resources;

CREATE TABLE resources (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20) NULL DEFAULT NULL,
  resource MEDIUMBLOB NULL DEFAULT NULL,
  ext VARCHAR(20) NULL DEFAULT NULL
  );


-- -----------------------------------------------------
-- Table eolms.orgs_resources
-- -----------------------------------------------------
DROP TABLE if EXISTS orgs_resources;

CREATE TABLE orgs_resources (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  orgid INT(11) NOT NULL,
  resourceid INT(11) NOT NULL,
  INDEX fk_orgresid (orgid ASC),
  INDEX fk_resourcesid (resourceid ASC),
CONSTRAINT fk_orgresid FOREIGN KEY (orgid) REFERENCES orgs (id)
  ON DELETE RESTRICT ON UPDATE RESTRICT,
CONSTRAINT fk_resourcesid FOREIGN KEY (resourceid) REFERENCES resources (id)
  ON DELETE RESTRICT ON UPDATE RESTRICT
);


-- -----------------------------------------------------
-- Table eolms.content_items
-- -----------------------------------------------------
DROP TABLE if EXISTS content_items;

CREATE TABLE content_items (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NULL,
  description VARCHAR(1000) NULL,
  typeid INT(11) NOT NULL,
  INDEX fk_ci_typeid_idx (typeid ASC),
CONSTRAINT fk_ci_typeid FOREIGN KEY (typeid) REFERENCES content_types (id)
  ON DELETE NO ACTION ON UPDATE CASCADE
  );


-- -----------------------------------------------------
-- Table eolms.assignment_sequences
-- -----------------------------------------------------
DROP TABLE if EXISTS assignment_sequences;

CREATE TABLE assignment_sequences (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  assignmentid INT(11) NOT NULL,
  contentid INT(11) NOT NULL,
  sequencenum INT(11) NULL DEFAULT 0,
  INDEX fk_as_assignmentid_idx (assignmentid ASC),
  INDEX fk_as_contentid_idx (contentid ASC),
CONSTRAINT fk_as_assignmentid FOREIGN KEY (assignmentid) REFERENCES assignments (id)
  ON DELETE NO ACTION ON UPDATE CASCADE,
CONSTRAINT fk_as_contentid FOREIGN KEY (contentid) REFERENCES content_items (id)
  ON DELETE NO ACTION ON UPDATE CASCADE
  );


-- -----------------------------------------------------
-- Table eolms.client_sequence_progress
-- -----------------------------------------------------
DROP TABLE if EXISTS client_sequence_progress;

CREATE TABLE client_sequence_progress (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  clientassignmentid INT(11) NOT NULL,
  sequenceid INT(11) NOT NULL,
  results INT(11) NULL DEFAULT 0,
  completed TINYINT(1) NULL DEFAULT 0,
  INDEX fk_csp_clientassignmentid_idx (clientassignmentid ASC),
  INDEX fk_csp_sequenceid_idx (sequenceid ASC),
CONSTRAINT fk_csp_clientassignmentid FOREIGN KEY (clientassignmentid) REFERENCES clients_assignments (id)
  ON DELETE NO ACTION ON UPDATE CASCADE,
CONSTRAINT fk_csp_sequenceid FOREIGN KEY (sequenceid) REFERENCES assignment_sequences (id)
  ON DELETE NO ACTION ON UPDATE CASCADE
  );


-- -----------------------------------------------------
-- Table eolms.client_sequence_usage
-- -----------------------------------------------------
DROP TABLE if EXISTS client_sequence_usage;

CREATE TABLE client_sequence_usage (
  id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  clientsequenceid INT(11) NOT NULL,
  starttime DATETIME NOT NULL DEFAULT '2017-10-01 00:00:00',
  stoptime DATETIME NOT NULL DEFAULT '2017-10-01 00:00:00',
  durationinseconds INT(11) NOT NULL DEFAULT 0,
  INDEX fk_su_clientsequenceid_idx (clientsequenceid ASC),
CONSTRAINT fk_su_clientsequenceid FOREIGN KEY (clientsequenceid) REFERENCES client_sequence_progress (id)
  ON DELETE NO ACTION ON UPDATE CASCADE
  );


-- -----------------------------------------------------
-- Table eolms.videos
-- -----------------------------------------------------
DROP TABLE if EXISTS videos;

CREATE TABLE videos (
  contentid INT(11) NOT NULL PRIMARY KEY,
  videoid VARCHAR(45) NOT NULL,
  videoproviderurl VARCHAR(450) NOT NULL,
  transcript LONGTEXT,
CONSTRAINT fk_v_contentid FOREIGN KEY (contentid) REFERENCES content_items (id)
  ON DELETE NO ACTION ON UPDATE CASCADE
  );


-- -----------------------------------------------------
-- Table eolms.exams
-- -----------------------------------------------------
DROP TABLE if EXISTS exams;

CREATE TABLE exams (
  contentid INT(11) NOT NULL PRIMARY KEY,
  examservleturl VARCHAR(450) NULL,
CONSTRAINT fk_e_contentid FOREIGN KEY (contentid) REFERENCES content_items (id)
  ON DELETE NO ACTION ON UPDATE CASCADE
  );


-- -----------------------------------------------------
-- Table eolms.org_content_items
-- -----------------------------------------------------
DROP TABLE if EXISTS org_content_items;

CREATE TABLE org_content_items (
  id INT(11) NOT NULL PRIMARY KEY,
  orgid INT(11) NOT NULL,
  contentitemid INT(11) NOT NULL,
  INDEX fk_oci_orgid_idx (orgid ASC),
  INDEX fk_oci_contentitemid_idx (contentitemid ASC),
CONSTRAINT fk_oci_orgid FOREIGN KEY (orgid) REFERENCES orgs (id)
  ON DELETE NO ACTION ON UPDATE CASCADE,
CONSTRAINT fk_oci_contentitemid FOREIGN KEY (contentitemid) REFERENCES content_items (id)
  ON DELETE NO ACTION ON UPDATE CASCADE
  );
  
  /**********************************************************************************************
* create table other_resources
* Author: Robin McGinnis
* 2017-12-02: Created table
**********************************************************************************************/
SET foreign_key_checks = 0; 
DROP TABLE if EXISTS other_resources;

CREATE TABLE IF NOT EXISTS other_resources (
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	orgid INTEGER NOT NULL,
	res_name VARCHAR(50),
	res_blob LONGBLOB NOT NULL,
    CONSTRAINT fk_other_resources_orgid FOREIGN KEY (orgid) REFERENCES orgs (id) ON DELETE CASCADE
);
SET foreign_key_checks = 1;

