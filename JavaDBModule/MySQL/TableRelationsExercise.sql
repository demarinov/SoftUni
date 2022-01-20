
create database relations;

-- 1. One-To-One Relationship
create table if not exists relations.passports(
	id int not null PRIMARY KEY AUTO_INCREMENT,
    passport_number varchar(20) not null
);

create table if not exists relations.persons (
	person_id int not null,
    first_name varchar(50) not null,
    salary float,
    passport_id int not null
);

insert into relations.persons values
(1, 'Roberto',43300.00,102),
(2, 'Tom',56100.00,103),
(3, 'Yana',60200.00,101);

insert into relations.passports values
(101,'N34FG21B'),
(102,'K65LO4R7'),
(103,'ZE657QP2');

alter table relations.persons modify person_id int not null primary key auto_increment;
alter table relations.persons add constraint fk_persons_passports 
foreign key (passport_id) references relations.passports(id);

alter table relations.passports add UNIQUE KEY(passport_number);
alter table relations.persons add UNIQUE KEY(passport_id);

alter table relations.persons modify column salary decimal(15,2);

use relations;
select * from persons;

SELECT lower(column_name)
FROM INFORMATION_SCHEMA.columns
WHERE TABLE_SCHEMA = database()
  and lower(table_name) = 'persons'
  and column_name = 'passport_id'
  and column_key = 'UNI'
order by lower(column_name);

-- 2. One-To-Many Relationship

create table if not exists relations.manufacturers(
	manufacturer_id int not null,
    name varchar(50) not null,
    established_on date
);

create table if not exists relations.models (
	model_id int not null,
    name varchar(50) not null,
    manufacturer_id int not null
);

insert into relations.manufacturers values (1 ,'BMW', '1916-03-01'),
(2, 'Tesla', '2003-01-01'),
(3, 'Lada', '1966-05-01');

insert into relations.models values (101, 'X1', 1),
(102 ,'i6', 1),
(103, 'Model S', 2),
(104, 'Model X' ,2),
(105, 'Model 3', 2),
(106, 'Nova', 3);

alter table relations.manufacturers 
modify manufacturer_id int not null PRIMARY KEY AUTO_INCREMENT;

alter table relations.models 
modify model_id int not null PRIMARY KEY AUTO_INCREMENT;

alter table relations.models add constraint fk_models_manufacturers 
foreign key (manufacturer_id) references relations.manufacturers(manufacturer_id);


-- 3. Many-To-Many Relationship

create table if not exists relations.students (
	student_id int not null PRIMARY KEY AUTO_INCREMENT,
    student_name varchar(50) not null
);

create table if not exists relations.exams (
	exam_id int not null PRIMARY KEY AUTO_INCREMENT,
    exam_name varchar(50) not null
);

create table if not exists relations.students_exams(
    student_id int,
	exam_id int,
    PRIMARY KEY(student_id, exam_id),
    CONSTRAINT fk_student_exam_student 
    foreign key (student_id) references relations.students(student_id),
    CONSTRAINT fk_student_exam_exam
    foreign key (exam_id) references relations.exams(exam_id)
);

insert into relations.students 
values (1, 'Mila'),
(2, 'Toni'),
(3, 'Ron');

insert into relations.exams 
values (101, 'Spring MVC'),
(102, 'Neo4j'),
(103, 'Oracle 11g');

insert into relations.students_exams 
values (1, 101),
(1, 102),
(2, 101),
(3, 103),
(2, 102),
(2, 103);

-- 4. Self-referencing

create table if not exists relations.teachers(
	teacher_id int not null,
    name varchar(50) not null,
    manager_id int null,
    PRIMARY KEY(teacher_id)
);

insert into relations.teachers 
values  (101, 'John',null),
(102, 'Maya', 106),
(103, 'Silvia', 106),
(104, 'Ted', 105),
(105, 'Mark', 101),
(106, 'Greta', 101);

alter table relations.teachers add CONSTRAINT fk_teachers_managers 
    FOREIGN KEY (manager_id) references relations.teachers(teacher_id);


-- 5. Online Store Database
create database if not exists online_store;

create table online_store.item_types (
	item_type_id int(11) not null PRIMARY KEY,
    name varchar(50) not null
);

create table online_store.items (
	item_id int(11) not null PRIMARY KEY,
    name varchar(50) not null,
    item_type_id int(11) not null,
    CONSTRAINT foreign key(item_type_id) references online_store.item_types(item_type_id)
);

create table online_store.cities (
	city_id int(11) not null PRIMARY KEY,
    name varchar(50) not null
);

create table online_store.customers (
	customer_id int not null PRIMARY KEY,
    name varchar(50) not null,
    birthday date,
    city_id int(11),
    CONSTRAINT fk_customers_city FOREIGN KEY(city_id)
    references online_store.cities(city_id)
);

create table online_store.orders (
	order_id int(11) not null PRIMARY KEY,
    customer_id int(11) not null,
    CONSTRAINT fk_orders_customers FOREIGN KEY(customer_id)
    references online_store.customers(customer_id)
);

create table online_store.order_items (
	order_id int(11),
	item_id int(11),
    PRIMARY KEY(order_id, item_id),
    CONSTRAINT fk_order_items_order FOREIGN KEY (order_id)
    references online_store.orders(order_id),
    CONSTRAINT fk_order_items_items FOREIGN key (item_id)
    references online_store.items(item_id)
);

-- 6. University Database

create database if not exists university;

create table university.subjects (
	subject_id int(11) not null PRIMARY KEY,
    subject_name varchar(50) not null
);

create table university.majors (
	major_id int(11) not null PRIMARY KEY,
    name varchar(50) not null
);

create table university.students (
	student_id int(11) not null PRIMARY KEY,
    student_number varchar(12) not null UNIQUE,
    student_name varchar(50) not null,
    major_id int(11),
    CONSTRAINT fk_students_majors foreign key (major_id) 
    references university.majors(major_id)
);

create table university.payments (
	payment_id int(11) not null PRIMARY KEY,
    payment_date date,
    payment_amount decimal(8,2),
    student_id int(11),
    CONSTRAINT fk_payments_students FOREIGN KEY(student_id) 
    references university.students(student_id)
);

create table university.agenda (
	student_id int(11),
    subject_id int(11),
    PRIMARY KEY(student_id, subject_id),
    CONSTRAINT fk_students_agenda_students foreign key (student_id) 
    references university.students(student_id),
    CONSTRAINT fk_subjects_agenda_subjects FOREIGN KEY(subject_id)
    references university.subjects(subject_id)
);

-- 7. SoftUni Design - create E/R diagram

-- 8. Geography Design


-- 9. Peaks in Rila
select M.mountain_range, P.peak_name, P.elevation as peak_elevation 
from geography.mountains M
join geography.peaks P on M.id = P.mountain_id
where M.mountain_range = 'Rila'
 order by peak_elevation desc;