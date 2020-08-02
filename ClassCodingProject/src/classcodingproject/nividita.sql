/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Sagar Das
 * Created: Aug 2, 2020
 */


drop database if exists db_bank;
create database db_bank;
use db_bank;

CREATE TABLE `tbl_account` (

  `accountnumber` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(128) NOT NULL,
  `age` int(10) NOT NULL,
  `total_Balance` int(10) NOT NULL,
  `address` varchar(11) NOT NULL

);




CREATE TABLE `tbl_transaction` (

-- accountnumber 
-- transaction Date 
-- ammount
-- transaction Type
-- balance

  `transaction Date` date NOT NULL,
  `transaction Type` varchar(10) NOT NULL,
  `amount` int(11) NOT NULL,
  `accountnumber` int(11) NOT NULL,
  `balance` int (11) NOT NULL
);
