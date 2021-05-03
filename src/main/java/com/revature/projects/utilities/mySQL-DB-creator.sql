create database p0;
use p0;

create table users (
id int not null auto_increment primary key,
firstName varchar(20) not null,
lastName varchar(20) not null,
SSN varchar(10) not null,
address varchar(50) not null,
email varchar(20) not null,
phone varchar(10) not null,
gender varchar(6) not null);


