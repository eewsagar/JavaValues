/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Sagar Das
 * Created: Jul 26, 2020
 */


drop database if exists db_Students;
create database db_Students;
use db_Students;

CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `enrolled` timestamp NOT NULL,
  `progress` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);