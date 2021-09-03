/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  gunnnu
 * Created: Sep 2, 2021
 */

drop database if exists db_studendatas;
create database db_studentdatas;
use db_studentdatas;

create table tbl_studens(
   id INT primary key auto_increment,
    sfname varchar(255),
    slname varchar(255),
    scalss varchar(255),
    sroll varchar(255),
    saddress varchar(255)
);

