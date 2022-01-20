-- for judge use lowercase entity names and backsticks
CREATE DATABASE minions;

-- 1. create tables
CREATE TABLE minions.MINIONS(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT,
    PRIMARY KEY (id)
);

CREATE TABLE minions.TOWNS (
	town_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

-- 2. Alter Minions Table
ALTER TABLE minions.minions ADD COLUMN town_id INT NOT NULL;
ALTER TABLE minions.minions ADD CONSTRAINT FK_MINIONS_TOWNS 
FOREIGN KEY (town_id) REFERENCES minions.TOWNS (id);

-- 3.	Insert Records in Both Tables
INSERT INTO minions.TOWNS(id, name) VALUES(1,'Sofia');
INSERT INTO minions.TOWNS(id, name) VALUES(2,'Plovdiv');
INSERT INTO minions.TOWNS(id, name) VALUES(3,'Varna');

INSERT INTO minions.minions(id, name, age, town_id) 
VALUES(1,'Kevin',22,1);

INSERT INTO minions.minions(id, name, age, town_id) 
VALUES(2,'Bob',15,3);

INSERT INTO minions.minions(id, name, age, town_id) 
VALUES(3,'Steward',NULL,2);

-- 4.	Truncate Table Minions
TRUNCATE TABLE minions.minions;

-- 5.	Drop All Tables
DROP TABLE minions.minions;
DROP TABLE minions.towns;

-- 6.	Create Table People
CREATE TABLE minions.PEOPLE (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL COLLATE utf8_bin,
    picture MEDIUMBLOB null,
    height FLOAT(5,2),
    weight FLOAT(5,2),
    gender CHAR(1) NOT NULL,
    birthdate DATE NOT NULL,
    biography TEXT,
    PRIMARY KEY (id)
);

-- 
INSERT INTO minions.PEOPLE(id, name, picture, height, 
weight, gender, birthdate, biography) 
VALUES(1,'Bob',
x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082'
,3,100,'m','1980-12-01','Funny guy');

INSERT INTO minions.PEOPLE(id, name, picture, height, 
weight, gender, birthdate, biography) 
VALUES(2,'Bobro',
x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082'
,4,102,'m','1981-12-01','Funny guy');

INSERT INTO minions.PEOPLE(id, name, picture, height, 
weight, gender, birthdate, biography) 
VALUES(3,'Bobri',
x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082'
,3,100,'m','1980-12-01','Funny guy');

INSERT INTO minions.PEOPLE(id, name, picture, height, 
weight, gender, birthdate, biography) 
VALUES(4,'Soni',
x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082'
,5,110,'m','1985-12-01','Funny guy');

INSERT INTO minions.PEOPLE(id, name, picture, height, 
weight, gender, birthdate, biography) 
VALUES(5,'Pedro',
x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082'
,3,100,'m','1980-12-01','Funny guy and more ...');

-- 7.	Create Table Users
CREATE TABLE minions.USERS (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(26),
    profile_picture MEDIUMBLOB NULL,
    last_login_time DATETIME,
    is_deleted boolean,
    PRIMARY KEY pk_users (id)
);

INSERT INTO minions.users(id, username, password, 
profile_picture, last_login_time, is_deleted)
VALUES(1, 'bubba','xxx0728',x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082'
,'2007-12-01 00:00:00','true');

INSERT INTO minions.users(id, username, password, 
profile_picture, last_login_time, is_deleted)
VALUES(2, 'bubba2','xxx0728',x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082'
,'2007-12-01 00:00:00','true');

INSERT INTO minions.users(id, username, password, 
profile_picture, last_login_time, is_deleted)
VALUES(3, 'bubba3','xxx0728',x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082'
,'2007-12-01 00:00:01','true');

INSERT INTO minions.users(id, username, password, 
profile_picture, last_login_time, is_deleted)
VALUES(4, 'bubba4','xxx0728',x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082'
,'2007-12-01 00:00:00','false');

INSERT INTO minions.users(id, username, password, 
profile_picture, last_login_time, is_deleted)
VALUES(5, 'bubbasparkx','xxx0728',x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082'
,'2007-12-02 00:00:00','true');

-- 8.	Change Primary Key
alter table minions.users modify id int not null;
alter table minions.users drop primary key;

alter table minions.users add primary key (id, username);

-- 9.	Set Default Value of a Field
alter table minions.users modify last_login_time 
datetime default NOW();

-- Set Unique Field
alter table `minions.users` modify id int not null;
alter table `minions.users` drop primary key;
alter table `minions.users` add primary key (`id`);
alter table `minions.users` add UNIQUE(`username`);

-- 11.	Movies Database
create database movies;

create table `movies.directors` (
	`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `director_name` varchar(50) NOT NULL,
    `notes` varchar(50)
);

create table `movies.genres` (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `genre_name` varchar(50) NOT NULL,
    `notes` varchar(50)
);

create table `movies.categories`(
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `category_name` varchar(50) NOT NULL,
    `notes` varchar(50)
);

create table `movies.movies` (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`title` varchar(100) NOT NULL,
	`director_id` INT NOT NULL,
    `copyright_year` YEAR,
    `length` INT NOT NULL,
    `genre_id` INT NOT NULL,
    `category_id` INT NOT NULL,
    `rating` float not null,
    `notes` varchar(50)
);

-- alter table `movies.movies` add constraint fk_movies_director
-- foreign key (`director_id`) references directors(`id`);

-- alter table `movies.movies` add constraint fk_movies_genre
-- foreign key (`genre_id`) references genres(`id`);

-- alter table `movies.movies` add constraint fk_movies_category
-- foreign key(`category_id`) references categories(`id`);

INSERT INTO `movies.directors` values(1,'Benji','NA');
INSERT INTO `movies.directors` values(2,'Benji2','NA');
INSERT INTO `movies.directors` values(3,'Benji3','NA');
INSERT INTO `movies.directors` values(4,'Benji4','NA');
INSERT INTO `movies.directors` values(5,'Benji5','NA');

insert into `movies.genres` values(1,'action','NA');
insert into `movies.genres` values(2,'horror','NA');
insert into `movies.genres` values(3,'drama','NA');
insert into `movies.genres` values(4,'thriller','NA');
insert into `movies.genres` values(5,'comedy','NA');

insert into `movies.categories` values(1, 'Horror Films','NA');
insert into `movies.categories` values(2, 'Comedy Films','NA');
insert into `movies.categories` values(3, 'Action Films','NA');
insert into `movies.categories` values(4, 'Drama Films','NA');
insert into `movies.categories` values(5, 'Thriller Films','NA');

insert into `movies.movies` values(1, 'Never give up'
,1,2010,2,1,3,6.5,'NA');
insert into `movies.movies` values(2, 'Never give up 2'
,1,2012,2,1,3,6.3,'NA');
insert into `movies.movies` values(3, 'Never give up 3'
,1,2015,2,1,3,6.0,'NA');
insert into `movies.movies` values(4, 'Meet the neighbours'
,2,2011,2,5,2,7.0,'NA');
insert into `movies.movies` values(5, 'Lost in the dark'
,3,2010,3,2,1,6.5,'NA');


-- 12.	Car Rental Database
create database car_rental;

create table `car_rental.categories` (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `category` varchar(50) NOT NULL,
    `daily_rate` float NOT NULL,
    `weekly_rate` float NOT NULL,
    `monthly_rate` float NOT NULL,
    `weekend_rate` float NOT NULL
);

create table `car_rental.cars` (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `plate_number` INT NOT NULL,
    `make` varchar(50) NOT NULL,
    `model` varchar(50) NOT NULL,
    `car_year` YEAR,
    `category_id` INT NOT NULL,
    `doors` INT NOT NULL,
    `picture` MEDIUMBLOB,
    `car_condition` varchar(20),
    `available` varchar(3)
);

create table car_rental.employees(
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `title` varchar(50) NOT NULL,
    `notes` varchar(50) NOT NULL
);

create table `car_rental.customers`(
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `driver_license_number` varchar(50) NOT NULL,
    `full_name` varchar(50) NOT NULL,
    `address` varchar(50) NOT NULL,
    `city` varchar(50) NOT NULL,
    `zip_code` INT,
    `notes` varchar(50)
);

create table `car_rental.rental_orders` (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `employee_id` INT NOT NULL,
    `customer_id` INT NOT NULL,
    `car_id` INT NOT NULL,
    `car_condition` varchar(50),
    `tank_level` varchar(50),
    `kilometrage_start` INT,
    `kilometrage_end` INT,
    `total_kilometrage` INT,
    `start_date` DATETIME,
    `end_date` DATETIME,
    `total_days` INT,
    `rate_applied` float,
    `tax_rate` float,
    `order_status` varchar(20),
    `notes` varchar(50)
);

-- alter table `car_rental.cars` add foreign key (`category_id`) references car_rental.categories(`id`);
-- alter table `car_rental.rental_orders` add foreign key (`employee_id`) references car_rental.employees(`id`);
-- alter table `car_rental.rental_orders` add foreign key (`customer_id`) references car_rental.customers(`id`);
-- alter table `car_rental.rental_orders` add foreign key (`car_id`) references car_rental.cars(`id`);

insert into `car_rental.categories` values(1,'Big', 2.5,3.2, 10.5,2);
insert into `car_rental.categories` values(2,'Big', 2.2,3.1, 10.5,2.1);
insert into `car_rental.categories` values(3,'Small', 2.3,3.0, 10.5,2.2);

insert into `car_rental.cars` values(1, 12345, 'toyota','corrola','2002',1,5,x'89504B',
'good','yes');
insert into `car_rental.cars` values(2, 123452, 'toyota','avensis','2001',1,5,x'89504A',
'good','yes');
insert into `car_rental.cars` values(3, 123453, 'toyota','bird','2000',1,5,x'89504E',
'good','yes');

insert into `car_rental.employees` values(1,'Bonno','Stones','Sir','NA');
insert into `car_rental.employees` values(2,'Bonno2','Stones2','Sir','NA');
insert into `car_rental.employees` values(3,'Bonno3','Stones3','Sir','NA');

insert into `car_rental.customers` values(1, 5555222, 'Bonno Stones','Funny street','Smallville','3333','NA');
insert into `car_rental.customers` values(2, 5555222, 'Bonno2 Stones3','Funny street','Smallville','3333','NA');
insert into `car_rental.customers` values(3, 5555222, 'Bonno3 Stones3','Funny street','Smallville','3333','NA');

insert into `car_rental.rental_orders` values(1, 1, 1, 1, "good", 'full',10000,12000,12000,
now(), date_add(now(),interval 1 day),1, 1.5,2.0,'done','NA');
insert into `car_rental.rental_orders` values(2, 2, 2, 2, "good", 'full',11000,12000,12000,
now(), date_add(now(),interval 1 day),1, 1.5,2.0,'done','NA');
insert into `car_rental.rental_orders` values(3, 3, 3, 3, "good", 'full',10500,12000,12000,
now(), date_add(now(),interval 1 day),1, 1.5,2.0,'done','NA');


create database hotel;

-- employees (id, first_name, last_name, title, notes)
create table `hotel.employees` (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `title` varchar(50) NOT NULL,
    `notes` varchar(50)
);
insert into `hotel.employees` values(1, 'Rut','Bergano','Manager','NA');
insert into `hotel.employees` values(2, 'Ronald','Kessler','Receptionist','NA');
insert into `hotel.employees` values(3, 'Rita','Messi','Receptionist','NA');

-- customers (account_number, first_name, last_name, phone_number, emergency_name, emergency_number, notes)
create table `hotel.customers`(
	`account_number` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `phone_number` varchar(20),
    emergency_name varchar(20) NOT NULL,
    emergency_number varchar(20) NOT NULL,
    notes varchar(50)
);
insert into hotel.customers values(1, 'Cristiano','Messi','12324','Rondo','123','NA');
insert into hotel.customers values(2, 'Patrick','Kliuvert','12324','Rondo','123','NA');
insert into hotel.customers values(3, 'Rui','Costa','12324','Rondo','123','NA');

-- room_status (room_status, notes)
create table hotel.room_status (
	room_status INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    notes varchar(50)
);
insert into hotel.room_Status values(1,'busy');
insert into hotel.room_Status values(2,'free');
insert into hotel.room_Status values(3,'na');

-- room_types (room_type, notes)
create table hotel.room_types (
	room_type INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    notes varchar(50)
);
insert into hotel.room_types values(1, 'big');
insert into hotel.room_types values(2, 'medium');
insert into hotel.room_types values(3, 'small');

-- bed_types (bed_type, notes)
create table hotel.bed_types (
	bed_type INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    notes varchar(50)
);
insert into hotel.bed_types values(1, 'single');
insert into hotel.bed_types values(2, 'double');
insert into hotel.bed_types values(3, 'extra big');

-- rooms (room_number, room_type, bed_type, rate, room_status, notes)
create table hotel.rooms (
	room_number INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    room_type INT NOT NULL,
    bed_type INT NOT NULL,
    rate float NOT NULL,
    room_status INT NOT NULL,
    notes varchar(50),
    CONSTRAINT FK_RO_BED FOREIGN KEY (bed_type) references hotel.bed_types(bed_type),
    CONSTRAINT FK_RO_ST FOREIGN KEY (room_status) references hotel.room_status(room_status),
    CONSTRAINT FK_RO_TY FOREIGN KEY (room_type) references hotel.room_types(room_type)
);
insert into hotel.rooms values(1, 1, 1, 2.5, 2, 'NA');
insert into hotel.rooms values(2, 2, 1, 3.5, 2, 'NA');
insert into hotel.rooms values(3, 3, 1, 4.5, 2, 'NA');

-- payments (id, employee_id, payment_date, account_number, first_date_occupied, 
-- last_date_occupied, total_days, amount_charged, tax_rate, tax_amount, payment_total, notes)
create tAble hotel.payments (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    payment_date DATETIME NOT NULL,
    account_number INT NOT NULL,
    first_date_occupied DATETIME,
    last_date_occupied DATETIME,
    total_days INT,
    amount_charged float,
    tax_rate float,
    tax_amount float,
    payment_total float,
    notes varchar(50),
    CONSTRAINT FK_PAY_EMP FOREIGN KEY (employee_id) references hotel.employees(id),
    CONSTRAINT FK_PAY_CUST FOREIGN KEY (account_number) references hotel.customers(account_number)
);
insert into hotel.payments values(1, 1, now(),1,now(),
date_add(now(), INTERVAL 2 day), 2, 2, 10,0.2,2.2, 'NA');
insert into hotel.payments values(2, 2, now(),2,now(),
date_add(now(), INTERVAL 2 day), 2, 2, 10,0.2,2.2, 'NA');
insert into hotel.payments values(3, 1, now(),3,now(),
date_add(now(), INTERVAL 2 day), 2, 2, 10,0.2,2.2, 'NA');

-- occupancies (id, employee_id, date_occupied, account_number, room_number, 
-- rate_applied, phone_charge, notes)
create table hotel.occupancies (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    date_occupied DATETIME NOT NULL,
    account_number INT NOT NULL,
    room_number INT NOT NULL,
    rate_applied float,
    phone_charge varchar(50),
    notes varchar(50),
    CONSTRAINT FK_OCC_EMP FOREIGN KEY (employee_id) references hotel.employees(id),
    CONSTRAINT FK_OCC_CUST FOREIGN KEY (account_number) references hotel.customers(account_number),
    CONSTRAINT FK_OCC_RO FOREIGN KEY (room_number) references hotel.rooms(room_number)
);
insert into hotel.occupancies values(1, 1, now(),1,1,2,0.5,'NA');
insert into hotel.occupancies values(2, 2, now(),2,1,2,0.5,'NA');
insert into hotel.occupancies values(3, 3, now(),3,1,2,0.5,'NA');

-- 13.	Basic Insert
create database soft_uni;

-- towns (id, name)
create table `soft_uni.towns` (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(50) NOT NULL
);

-- addresses (id, address_text, town_id)
create table `soft_uni.addresses` (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `address_text` varchar(50) NOT NULL,
    `town_id` INT NOT NULL,
    CONSTRAINT FK_ADDR_TOWN FOREIGN KEY (`town_id`) references `soft_uni.towns`(`id`)
);

-- departments (id, name)
create table `soft_uni.departments` (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(50) NOT NULL
);

-- employees (id, first_name, middle_name, last_name, job_title, department_id, hire_date, salary, address_id)
create table `soft_uni.employees` (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `first_name` varchar(50) NOT NULL,
    `middle_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `job_title` varchar(50),
    `department_id` INT NOT NULL,
   `hire_date` DATETIME,
    `salary` float,
    `address_id` INT NOT NULL,
    CONSTRAINT FK_EMP_DEP FOREIGN KEY (`department_id`) references `soft_uni.departments`(`id`),
    CONSTRAINT FK_EMP_ADDR FOREIGN KEY (`address_id`) references `soft_uni.addresses`(`id`)

);


insert into `towns` values (1, 'Sofia');
insert into `towns` values (2, 'Plovdiv');
insert into `towns` values (3, 'Varna');
insert into `towns` values (4, 'Burgas');

insert into `departments` values(1, 'Engineering');
insert into `departments` values(2, 'Sales');
insert into `departments` values(3, 'Marketing');
insert into `departments` values(4, 'Software Development');
insert into `departments` values(5, 'Quality Assurance');

insert into `addresses` values(1, 'Santa street',1);


insert into `employees` values(1,'Ivan','Ivanov','Ivanov','.NET Developer',4,'2013-02-01',3500.00,1);

insert into `employees` values(3,'Maria','Petrova','Ivanova','Intern',5,'2016-08-28',525.25,1);

insert into `employees` values(4,'Georgi','Terziev','Ivanov','CEO',2,'2007-12-09',3000.00,1);

insert into `employees` values(2,'Petar','Petrov','Petrov','Senior Engineer',1,'2004-03-02',4000.00,1);

insert into `employees` values(5,'Peter','Pan','Pan','Intern',3,'2016-08-28',599.88,1);

-- mysqldump -h 127.0.0.1 -u root -p<pass> soft_uni > softuni-backup.sql
-- mysqladmin -u root -p<pass> -h 127.0.0.1 create soft_uni
-- mysql -h 127.0.0.1 -u root -p<pass> soft_uni < softuni-backup.sql

-- 14.	Basic Select All Fields
select * from `soft_uni.towns`;
select * from `soft_uni.departments`;
select * from `soft_uni.employees`;

-- 15.	Basic Select All Fields and Order Them
select * from `soft_uni.towns` order by `name` asc;
select * from `soft_uni.departments` order by `name` asc;
select * from `soft_uni.employees` order by `salary` desc;

-- 16.	Basic Select Some Fields
select `name` from `soft_uni.towns` order by `name` asc;
select `name` from `soft_uni.departments` order by `name` asc;
select `first_name`, `last_name`, `job_title`, `salary` from `soft_uni.employees` 
order by `salary` desc;

-- 17.	Increase Employees Salary
update `soft_uni`.`employees` set `salary` = `salary` + 0.10 * `salary`;
select `salary` from `soft_uni`.`employees`;

-- 18.	Delete All Records
truncate `hotel`.`occupancies`;

update hotel.payments set tax_rate = tax_rate - 0.03 * tax_rate;

