/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Sagar Das
 * Created: Jul 26, 2020
 */


drop database if exists db_bank;
create database db_bank;
use db_bank;

CREATE TABLE `tbl_account` (
  `accno` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(128) NOT NULL,
  `age` int(10) NOT NULL,
  `address` varchar(11) NOT NULL
);

CREATE TABLE `tbl_transaction` (
  `transactionid` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,  ---+1
  `accno1` int(11) not null,
  `date1` date NOT NULL,
  `trantype` varchar(10) NOT NULL,
  `amount` int(11) NOT NULL,
  `balance1` int (11) NOT NULL
);