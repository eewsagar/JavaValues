drop database if exists db_examples;
create database db_examples;
use db_examples;

CREATE TABLE tbl_contact(

	id varchar(255),
	fullName varchar(255),
	
	isActive varchar(1)
);