/*********************************************************************************************
 This script will run all five sql scripts to create the alphplus database and its tables and to 
 populate tables with test data.
 *********************************************************************************************/
DROP DATABASE IF EXISTS testalphplus;
CREATE DATABASE testalphplus DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
USE testalphplus;

SOURCE ./create_tables(0).sql
SOURCE ./populate_basic_tables(1).sql
SOURCE ./create_assign_pronoundrill_exercises(2).sql
SOURCE ./populate_test_data_table(3).sql
SOURCE ./populate_test_data_for_uncooked_exercises(4).sql


/*Done */

