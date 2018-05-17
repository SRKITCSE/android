create database if not exists `osc`;

USE `osc`;

DROP TABLE IF EXISTS `login`;

create table companyinfo(
companyname varchar(100)primary key,
password varchar(30),
address varchar(100),
email varchar(30),
landline varchar(15),
phone varchar(100)
);


create table customer(
userid varchar(50)primary key,
password varchar(20),
address varchar(100),
email varchar(50),
phone1 varchar(50),
phone2 varchar(50),
phone3 varchar(50),
phone4 varchar(50)
);


DROP TABLE IF EXISTS `drivers`;
CREATE TABLE drivers(
image varchar(255) NOT NULL,	
name	varchar(255) NOT NULL,	
gender	varchar(20),
userid	varchar(100) PRIMARY KEY,
password varchar(100) NOT NULL,
address	varchar(200)  NOT NULL,
email	varchar(50) NOT NULL,
phone	varchar(50) NOT NULL,
cabno varchar(50) NOT NULL,
availability int
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE profile(
usreid	varchar(100) PRIMARY KEY,
password varchar(20) NOT NULL,
desig varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


create table cabs(
carno varchar(20)primary key,
status int
);

/*create table caballotment
(   carno   varchar(20),
    driveruserid  varchar(50),
    sourceLatitude  varchar(20),
    sourceLongitude  varchar(20),
    destinationLatitude  varchar(20),
    destinationLongitude  varchar(20),
    customerid varchar(50),
    dateOfAllotment varchar(20)
);
*/

create table caballotment2
(   
    carno   varchar(20),
    driveruserid  varchar(50),
    source varchar(50),
    destination varchar(50),
    sourceLatitude  varchar(20),
    sourceLongitude  varchar(20),
    destinationLatitude  varchar(20),
    destinationLongitude  varchar(20),
    customerid varchar(50),
    dateOfAllotment varchar(20),
    status varchar(25)
);

insert into caballotment2 values('','','vijayawada','hyderabad','16.5027866','80.6396287','16.5209286','80.6829163','123','1426516669751','NotAlloted');
