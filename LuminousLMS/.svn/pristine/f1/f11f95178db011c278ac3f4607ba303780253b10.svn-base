

-- -----------------------------------------------------
-- Data for table `eolms`.`orgs`
-- -----------------------------------------------------
USE `eolms`;
INSERT INTO `eolms`.`orgs` (`id`, `orgname`, `orgemail`) VALUES (1, 'EmploymentON', 'manager@employmenton.on.ca');
INSERT INTO `eolms`.`orgs` (`id`, `orgname`, `orgemail`) VALUES (2, 'GruntJobsON', 'manager@gruntjobs.on.ca');



-- -----------------------------------------------------
-- Data for table `eolms`.`assignments`
-- -----------------------------------------------------
INSERT INTO `eolms`.`assignments` (`id`, `orgid`, `name`, `description`, `assisted`) VALUES (1, 1, 'Wistia Sample Videos, (EO assisted)', NULL, 1);
INSERT INTO `eolms`.`assignments` (`id`, `orgid`, `name`, `description`, `assisted`) VALUES (2, 1, 'Latin Parser, (EO assisted)', NULL, 1);
INSERT INTO `eolms`.`assignments` (`id`, `orgid`, `name`, `description`, `assisted`) VALUES (3, 1, 'Video 3, (EO other video) ', NULL, 0);
INSERT INTO `eolms`.`assignments` (`id`, `orgid`, `name`, `description`, `assisted`) VALUES (4, 2, 'Video 4, (GJ other)', NULL, 0);
INSERT INTO `eolms`.`assignments` (`id`, `orgid`, `name`, `description`, `assisted`) VALUES (5, 2, 'Video 5, (GJ other)', NULL, 0);
INSERT INTO `eolms`.`assignments` (`id`, `orgid`, `name`, `description`, `assisted`) VALUES (6, 2, 'Exam 6, (GJ other)', NULL, 0);


-- -----------------------------------------------------
-- Data for table `eolms`.`content_types`
-- -----------------------------------------------------
INSERT INTO `eolms`.`content_types` (`id`, `type`) VALUES (1, 'VIDEO');
INSERT INTO `eolms`.`content_types` (`id`, `type`) VALUES (2, 'QUIZ');
INSERT INTO `eolms`.`content_types` (`id`, `type`) VALUES (3, 'EXAM');
INSERT INTO `eolms`.`content_types` (`id`, `type`) VALUES (4, 'OTHER');

-- -----------------------------------------------------
-- Data for table `eolms`.`users`
-- -----------------------------------------------------

INSERT INTO `eolms`.`users` (`id`, `password`, `firstname`, `lastname`, `emailaddress`, `accountcreatedtime`, `status`, `question`, `answer`) VALUES (1, 'f7a9e24777ec23212c54d7a350bc5bea5477fdbb', 'Gussie', 'Fink-Nottle', 'nb@100.com', '2017-11-19 09:36:35', 0, NULL, NULL);
INSERT INTO `eolms`.`users` (`id`, `password`, `firstname`, `lastname`, `emailaddress`, `accountcreatedtime`, `status`, `question`, `answer`) VALUES (2, 'f7a9e24777ec23212c54d7a350bc5bea5477fdbb', 'Ayman', 'Admin', 'offadmin@100.com', '2017-11-19 09:36:35', 0, NULL, NULL);
INSERT INTO `eolms`.`users` (`id`, `password`, `firstname`, `lastname`, `emailaddress`, `accountcreatedtime`, `status`, `question`, `answer`) VALUES (3, 'f7a9e24777ec23212c54d7a350bc5bea5477fdbb', 'Connie', 'Sultant', 'consultant@100.com', '2017-11-19 09:36:35', 0, NULL, NULL);
INSERT INTO `eolms`.`users` (`id`, `password`, `firstname`, `lastname`, `emailaddress`, `accountcreatedtime`, `status`, `question`, `answer`) VALUES (4, '70ccd9007338d6d81dd3b6271621b9cf9a97ea00', 'super', 'administrator', 'admin@100.com', '2017-11-19 09:36:35', 0, NULL, NULL);
INSERT INTO `eolms`.`users` (`id`, `password`, `firstname`, `lastname`, `emailaddress`, `accountcreatedtime`, `status`, `question`, `answer`) VALUES (5, 'f7a9e24777ec23212c54d7a350bc5bea5477fdbb', 'Arnold', 'Punter', 'ap@100.com', '2017-11-19 09:36:35', 0, NULL, NULL);
INSERT INTO `eolms`.`users` (`id`, `password`, `firstname`, `lastname`, `emailaddress`, `accountcreatedtime`, `status`, `question`, `answer`) VALUES (6, 'f7a9e24777ec23212c54d7a350bc5bea5477fdbb', 'Bart', 'Boss', 'bb@100.com', '2017-11-19 09:36:35', 0, NULL, NULL);
INSERT INTO `eolms`.`users` (`id`, `password`, `firstname`, `lastname`, `emailaddress`, `accountcreatedtime`, `status`, `question`, `answer`) VALUES (7, 'f7a9e24777ec23212c54d7a350bc5bea5477fdbb', 'Fred', 'Foreman', 'ff@100.com', '2017-11-19 09:36:35', 0, NULL, NULL);
INSERT INTO `eolms`.`users` (`id`, `password`, `firstname`, `lastname`, `emailaddress`, `accountcreatedtime`, `status`, `question`, `answer`) VALUES (8, 'f7a9e24777ec23212c54d7a350bc5bea5477fdbb', 'Manual', 'Labourer', 'ml@100.com', '2017-11-19 09:36:35', 0, NULL, NULL);
INSERT INTO `eolms`.`users` (`id`, `password`, `firstname`, `lastname`, `emailaddress`, `accountcreatedtime`, `status`, `question`, `answer`) VALUES (9, 'f7a9e24777ec23212c54d7a350bc5bea5477fdbb', 'Handy', 'Manny', 'hm@100.com', '2017-11-19 09:36:35', 0, NULL, NULL);
INSERT INTO `eolms`.`users` (`id`, `password`, `firstname`, `lastname`, `emailaddress`, `accountcreatedtime`, `status`, `question`, `answer`) VALUES (10, 'f7a9e24777ec23212c54d7a350bc5bea5477fdbb', 'Hugh', 'New', 'hu@100.com', '2017-11-19 09:36:35', 0, NULL, NULL);



-- -----------------------------------------------------
-- Data for table `eolms`.`clients_assignments`
-- -----------------------------------------------------
INSERT INTO `eolms`.`clients_assignments` (`id`, `clientid`, `assignmentid`, `duedate`) VALUES (1, 1, 1, '2017-12-30');
INSERT INTO `eolms`.`clients_assignments` (`id`, `clientid`, `assignmentid`, `duedate`) VALUES (2, 1, 2, '2018-02-01');
INSERT INTO `eolms`.`clients_assignments` (`id`, `clientid`, `assignmentid`, `duedate`) VALUES (3, 8, 4, '2017-12-20');
INSERT INTO `eolms`.`clients_assignments` (`id`, `clientid`, `assignmentid`, `duedate`) VALUES (4, 9, 4, '2018-01-15');



-- -----------------------------------------------------
-- Data for table `eolms`.`consultant_clients`
-- -----------------------------------------------------
INSERT INTO `eolms`.`consultant_clients` (`id`, `orgid`, `consultantid`, `clientid`) VALUES (1, 1, 3, 1);
INSERT INTO `eolms`.`consultant_clients` (`id`, `orgid`, `consultantid`, `clientid`) VALUES (2, 1, 3, 5);
INSERT INTO `eolms`.`consultant_clients` (`id`, `orgid`, `consultantid`, `clientid`) VALUES (3, 1, 3, 9);
INSERT INTO `eolms`.`consultant_clients` (`id`, `orgid`, `consultantid`, `clientid`) VALUES (4, 2, 7, 8);
INSERT INTO `eolms`.`consultant_clients` (`id`, `orgid`, `consultantid`, `clientid`) VALUES (5, 2, 7, 9);



-- -----------------------------------------------------
-- Data for table `eolms`.`messages`
-- -----------------------------------------------------
INSERT INTO `eolms`.`messages` (`id`, `status`, `fromid`, `toid`, `orgid`, `subject`, `message`, `createdate`, `timezone`) VALUES (1, 'UNREAD', 1, 3, 1, 'This is a test Subject 1', 'This is a test message from the database to be shown to the Consultant from Client', '2017-10-02 09:10:00', 'EST');
INSERT INTO `eolms`.`messages` (`id`, `status`, `fromid`, `toid`, `orgid`, `subject`, `message`, `createdate`, `timezone`) VALUES (2, 'UNREAD', 3, 1, 1, 'This is a test Subject 2', 'This is a test message 2 for Client from Consultant', '2017-10-03 11:13:01', 'EST');
INSERT INTO `eolms`.`messages` (`id`, `status`, `fromid`, `toid`, `orgid`, `subject`, `message`, `createdate`, `timezone`) VALUES (3, 'UNREAD', 3, 1, 1, 'This is a test Subject 3', 'This is a test message 3 for Client from Consultant', '2017-10-04 12:15:04', 'EST');
INSERT INTO `eolms`.`messages` (`id`, `status`, `fromid`, `toid`, `orgid`, `subject`, `message`, `createdate`, `timezone`) VALUES (4, 'UNREAD', 3, 1, 1, 'This is a test Subject 4', 'This is a test message 4 for Client from Consultant', '2017-10-05 13:17:09', 'EST');
INSERT INTO `eolms`.`messages` (`id`, `status`, `fromid`, `toid`, `orgid`, `subject`, `message`, `createdate`, `timezone`) VALUES (5, 'UNREAD', 3, 1, 1, 'This is a test Subject 5', 'This is a test message 5 for Client from Consultant', '2017-10-10 14:19:16', 'EST');
INSERT INTO `eolms`.`messages` (`id`, `status`, `fromid`, `toid`, `orgid`, `subject`, `message`, `createdate`, `timezone`) VALUES (6, 'UNREAD', 1, 3, 1, 'This is a test Subject 6', 'This is a test message 2 from the database to be shown to the Consultant from Client', '2017-10-11 15:21:25', 'EST');
INSERT INTO `eolms`.`messages` (`id`, `status`, `fromid`, `toid`, `orgid`, `subject`, `message`, `createdate`, `timezone`) VALUES (7, 'UNREAD', 1, 3, 1, 'This is a test Subject 7', 'This is a test message 7 from the database to be shown to the Consultant from Client', '2017-10-12 16:23:36', 'EST');
INSERT INTO `eolms`.`messages` (`id`, `status`, `fromid`, `toid`, `orgid`, `subject`, `message`, `createdate`, `timezone`) VALUES (8, 'UNREAD', 1, 3, 1, 'This is a test Subject 8', 'This is a test message 8 from the database to be shown to the Consultant from Client', '2017-10-13 09:25:49', 'EST');
INSERT INTO `eolms`.`messages` (`id`, `status`, `fromid`, `toid`, `orgid`, `subject`, `message`, `createdate`, `timezone`) VALUES (9, 'UNREAD', 7, 8, 2, 'This is a test Subject 9s', 'This is a test message 9 sent from the consultant Hugh to client Manual', '2017-10-16 10:28:04', 'EST');
INSERT INTO `eolms`.`messages` (`id`, `status`, `fromid`, `toid`, `orgid`, `subject`, `message`, `createdate`, `timezone`) VALUES (10, 'UNREAD', 8, 7, 2, 'This is a test Subject 9r', 'This is a test message 9 reply from the client Manual to consultant Hugh', '2017-10-17 11:33:21', 'EST');
INSERT INTO `eolms`.`messages` (`id`, `status`, `fromid`, `toid`, `orgid`, `subject`, `message`, `createdate`, `timezone`) VALUES (11, 'UNREAD', 3, 5, 1, 'This is a test Subject 10s', 'This is a test message 10 sent from the consultant Connie to client Arnold', '2017-10-18 12:39:40', 'EST');
INSERT INTO `eolms`.`messages` (`id`, `status`, `fromid`, `toid`, `orgid`, `subject`, `message`, `createdate`, `timezone`) VALUES (12, 'UNREAD', 5, 3, 1, 'This is a test Subject 10r', 'This is a test message 10 reply from the client Arnold to consultant Connie', '2017-10-19 13:48:01', 'EST');



-- -----------------------------------------------------
-- Data for table `eolms`.`notifications`
-- -----------------------------------------------------
INSERT INTO `eolms`.`notifications` (`ID`, `orgid`, `createdbyid`, `subject`, `note`, `startdate`, `expirydate`, `timezone`) VALUES (1, 1, 2, 'Fall hours', 'Office hours for the Fall are from 9:00 until 16:30.', '2017-09-22 00:00:00', '2017-12-20 23:59:59', 'EST');
INSERT INTO `eolms`.`notifications` (`ID`, `orgid`, `createdbyid`, `subject`, `note`, `startdate`, `expirydate`, `timezone`) VALUES (2, 1, 2, 'Thanksgiving', 'Office is closed on Thanksgiving Day.', '2017-10-09 00:00:00', '2017-10-09 23:59:59', 'EST');
INSERT INTO `eolms`.`notifications` (`ID`, `orgid`, `createdbyid`, `subject`, `note`, `startdate`, `expirydate`, `timezone`) VALUES (3, 1, 2, 'Remembrance Day', 'Don not forget Remembrance Day on Saturday.', '2017-11-11 00:00:00', '2017-11-11 23:59:59', 'EST');
INSERT INTO `eolms`.`notifications` (`ID`, `orgid`, `createdbyid`, `subject`, `note`, `startdate`, `expirydate`, `timezone`) VALUES (4, 2, 6, 'Christmas', 'No work on Christmas Day.', '2017-12-25 00:00:00', '2017-12-25 23:59:59', 'EST');



-- -----------------------------------------------------
-- Data for table `eolms`.`permissions`
-- -----------------------------------------------------
INSERT INTO `eolms`.`permissions` (`id`, `permission`) VALUES (4, 'ADMINISTRATOR');
INSERT INTO `eolms`.`permissions` (`id`, `permission`) VALUES (1, 'CLIENT');
INSERT INTO `eolms`.`permissions` (`id`, `permission`) VALUES (2, 'CONSULTANT');
INSERT INTO `eolms`.`permissions` (`id`, `permission`) VALUES (3, 'OFFICE_ADMIN');



-- -----------------------------------------------------
-- Data for table `eolms`.`org_user_permissions`
-- -----------------------------------------------------
INSERT INTO `eolms`.`org_user_permissions` (`id`, `orgid`, `userid`, `permissionid`) VALUES (1, 1, 1, 1);
INSERT INTO `eolms`.`org_user_permissions` (`id`, `orgid`, `userid`, `permissionid`) VALUES (2, 1, 3, 2);
INSERT INTO `eolms`.`org_user_permissions` (`id`, `orgid`, `userid`, `permissionid`) VALUES (3, 1, 2, 3);
INSERT INTO `eolms`.`org_user_permissions` (`id`, `orgid`, `userid`, `permissionid`) VALUES (4, 1, 4, 4);
INSERT INTO `eolms`.`org_user_permissions` (`id`, `orgid`, `userid`, `permissionid`) VALUES (5, 1, 5, 1);
INSERT INTO `eolms`.`org_user_permissions` (`id`, `orgid`, `userid`, `permissionid`) VALUES (6, 2, 6, 3);
INSERT INTO `eolms`.`org_user_permissions` (`id`, `orgid`, `userid`, `permissionid`) VALUES (7, 1, 8, 1);
INSERT INTO `eolms`.`org_user_permissions` (`id`, `orgid`, `userid`, `permissionid`) VALUES (8, 2, 8, 1);
INSERT INTO `eolms`.`org_user_permissions` (`id`, `orgid`, `userid`, `permissionid`) VALUES (9, 2, 9, 1);
INSERT INTO `eolms`.`org_user_permissions` (`id`, `orgid`, `userid`, `permissionid`) VALUES (10, 2, 7, 2);
INSERT INTO `eolms`.`org_user_permissions` (`id`, `orgid`, `userid`, `permissionid`) VALUES (11, 1, 10, 2);
INSERT INTO `eolms`.`org_user_permissions` (`id`, `orgid`, `userid`, `permissionid`) VALUES (12, 1, 10, 3);



-- -----------------------------------------------------
-- Data for table `eolms`.`content_items`
-- -----------------------------------------------------
INSERT INTO `eolms`.`content_items` (`id`, `name`, `description`, `typeid`) VALUES (1, 'Wistia Standard embed example', 'NULL', 1);
INSERT INTO `eolms`.`content_items` (`id`, `name`, `description`, `typeid`) VALUES (2, 'Wistia Fallback embed example', 'NULL', 1);
INSERT INTO `eolms`.`content_items` (`id`, `name`, `description`, `typeid`) VALUES (3, 'Wistia Popover embed example', 'NULL', 1);
INSERT INTO `eolms`.`content_items` (`id`, `name`, `description`, `typeid`) VALUES (4, 'Latin Parser tutorial', 'NULL', 1);
INSERT INTO `eolms`.`content_items` (`id`, `name`, `description`, `typeid`) VALUES (5, 'EO - Other Video 3', 'NULL', 1);
INSERT INTO `eolms`.`content_items` (`id`, `name`, `description`, `typeid`) VALUES (6, 'GJ - Other Video 4', 'NULL', 1);
INSERT INTO `eolms`.`content_items` (`id`, `name`, `description`, `typeid`) VALUES (7, 'GJ - Other Video 5', 'NULL', 1);
INSERT INTO `eolms`.`content_items` (`id`, `name`, `description`, `typeid`) VALUES (8, 'GJ - Other Exam 6', 'NULL', 3);



-- -----------------------------------------------------
-- Data for table `eolms`.`assignment_sequences`
-- -----------------------------------------------------
INSERT INTO `eolms`.`assignment_sequences` (`id`, `assignmentid`, `contentid`, `sequencenum`) VALUES (1, 1, 1, 1);
INSERT INTO `eolms`.`assignment_sequences` (`id`, `assignmentid`, `contentid`, `sequencenum`) VALUES (2, 1, 2, 2);
INSERT INTO `eolms`.`assignment_sequences` (`id`, `assignmentid`, `contentid`, `sequencenum`) VALUES (3, 1, 3, 3);
INSERT INTO `eolms`.`assignment_sequences` (`id`, `assignmentid`, `contentid`, `sequencenum`) VALUES (4, 2, 4, 1);
INSERT INTO `eolms`.`assignment_sequences` (`id`, `assignmentid`, `contentid`, `sequencenum`) VALUES (5, 3, 5, 1);
INSERT INTO `eolms`.`assignment_sequences` (`id`, `assignmentid`, `contentid`, `sequencenum`) VALUES (6, 4, 6, 1);
INSERT INTO `eolms`.`assignment_sequences` (`id`, `assignmentid`, `contentid`, `sequencenum`) VALUES (7, 5, 7, 1);
INSERT INTO `eolms`.`assignment_sequences` (`id`, `assignmentid`, `contentid`, `sequencenum`) VALUES (8, 6, 8, 1);



-- -----------------------------------------------------
-- Data for table `eolms`.`videos`
-- -----------------------------------------------------
INSERT INTO `eolms`.`videos` (`contentid`, `videoid`, `videoproviderurl`, `transcript`) VALUES (1, 'j38ihh83m5', 'https://fast.wistia.com/embed/medias/', 'This is the transcript');
INSERT INTO `eolms`.`videos` (`contentid`, `videoid`, `videoproviderurl`, `transcript`) VALUES (2, 'avk9twrrbn', 'https://fast.wistia.com/embed/medias/', NULL);
INSERT INTO `eolms`.`videos` (`contentid`, `videoid`, `videoproviderurl`, `transcript`) VALUES (3, 'pukozq6xf0', 'https://fast.wistia.com/embed/medias/', 'This too is a transcript');
INSERT INTO `eolms`.`videos` (`contentid`, `videoid`, `videoproviderurl`, `transcript`) VALUES (4, 'eybc5xh2oe', 'https://fast.wistia.com/embed/medias/',
	'The transcript for the tutorial is found here');
INSERT INTO `eolms`.`videos` (`contentid`, `videoid`, `videoproviderurl`, `transcript`) VALUES (5, 'eybc5xh2oe', 'https://fast.wistia.com/embed/medias/', NULL);
INSERT INTO `eolms`.`videos` (`contentid`, `videoid`, `videoproviderurl`, `transcript`) VALUES (6, 'eybc5xh2oe', 'https://fast.wistia.com/embed/medias/', NULL);
INSERT INTO `eolms`.`videos` (`contentid`, `videoid`, `videoproviderurl`, `transcript`) VALUES (7, 'eybc5xh2oe', 'https://fast.wistia.com/embed/medias/', NULL);



-- -----------------------------------------------------
-- Data for table `eolms`.`org_content_items`
-- -----------------------------------------------------
INSERT INTO `eolms`.`org_content_items` (`id`, `orgid`, `contentitemid`) VALUES (1, 1, 1);
INSERT INTO `eolms`.`org_content_items` (`id`, `orgid`, `contentitemid`) VALUES (2, 1, 2);
INSERT INTO `eolms`.`org_content_items` (`id`, `orgid`, `contentitemid`) VALUES (3, 1, 3);
INSERT INTO `eolms`.`org_content_items` (`id`, `orgid`, `contentitemid`) VALUES (4, 1, 4);
INSERT INTO `eolms`.`org_content_items` (`id`, `orgid`, `contentitemid`) VALUES (5, 1, 5);
INSERT INTO `eolms`.`org_content_items` (`id`, `orgid`, `contentitemid`) VALUES (6, 1, 6);


