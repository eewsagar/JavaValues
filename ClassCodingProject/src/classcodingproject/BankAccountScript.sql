/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Sagar Das
 * Created: Jul 26, 2020
 */


drop database if exists db_BankAccount;
create database db_BankAccount;
use db_BankAccount;

CREATE TABLE `tbl_account` (
  `Accontno` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `Accname` varchar(128) NOT NULL,
  `age` int(10) NOT NULL,
  `address` varchar(11) NOT NULL
  
  
);

CREATE TABLE `tbl_transaction` (
  
  `transactionid` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `Accontno` int(11) NOT NULL,
  `date` date NOT NULL,
  `transaction type` int(10) NOT NULL,
  `amount` varchar(11) NOT NULL,
  `balance` varchar(11) NOT NULL
);