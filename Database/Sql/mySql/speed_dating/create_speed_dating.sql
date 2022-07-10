/********************************************************
* This script creates the database named speed_dating 
*********************************************************/

CREATE DATABASE speed_dating;
USE speed_dating;

-- create the tables for the database
CREATE TABLE men_participants (
  men_participant_id	INT			PRIMARY KEY		AUTO_INCREMENT,
  first_name			VARCHAR(50)	NOT NULL
);

CREATE TABLE women_participants (
  women_participant_id      INT				PRIMARY KEY   AUTO_INCREMENT,
  first_name				VARCHAR(50)		NOT NULL
);

-- Insert data into the tables
INSERT INTO men_participants (first_name) VALUES
('Chad'),
('Fred'),
('Steve'),
('Bill'),
('Henry'),
('Mike'),
('Bob'),
('Tom'),
('Tim'),
('Greg'),
('Joe');

INSERT INTO women_participants (first_name) VALUES
('Kelly'),
('Stephanie'),
('Cassandra'),
('Kim'),
('Brooke'),
('Jessica'),
('Diane'),
('Mona'),
('Gail'),
('Sue'),
('Pam');
