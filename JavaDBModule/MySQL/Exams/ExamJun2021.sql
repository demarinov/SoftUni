
-- 1. Section DDL

drop database stc;
create database stc;

use stc;

create table addresses (
	id int not null Primary key auto_increment,
    name varchar(100) not null
);

create table categories (
	id int not null PRIMARY KEY auto_increment,
    name varchar(10) not null
);

create table clients (
	id int not null primary key auto_increment,
    full_name varchar(50) not null,
    phone_number varchar(20) not null
);

create table drivers (
	id int not null primary key auto_increment,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    age int not null,
    rating float default 5.5
);

create table cars (
	id int not null primary key auto_increment,
    make varchar(20) not null,
    model varchar(20),
    year int not null default 0,
    mileage int default 0,
    `condition` char not null,
    category_id int not null,
    
    constraint fk_cars_categories foreign key (category_id) 
    references categories(id)
);

create table courses (
	id int not null primary key auto_increment,
    from_address_id int not null,
    start datetime not null,
    bill decimal(10,2) default 10,
    car_id int not null,
    client_id int not null,
    
    constraint fk_courses_addresses foreign key (from_address_id) 
    references addresses(id),
    constraint fk_courses_cars foreign key (car_id) 
    references cars(id),
    constraint fk_courses_clients foreign key (client_id) 
    references clients(id)
);

create table cars_drivers (
	car_id int not null,
    driver_id int not null,
    
    primary key (car_id, driver_id),
    
constraint fk_cars_drivers_cars foreign key (car_id) 
references cars(id),
constraint fk_cars_drivers_drivers foreign key (driver_id) 
references drivers(id)
);



-- -- Section 2. DML

-- Insert
insert into clients (full_name, phone_number) 
(select concat(first_name,' ',last_name) as 'full_name', concat('(088) 9999',(D.id*2)) 
as 'phone_number' from drivers D where D.id >= 10 and D.id <= 20);

select concat(first_name,' ',last_name) as 'full_name', concat('(088) 9999',(D.id*2)) 
as 'phone_number' from drivers D;

-- 3. Update

update cars set `condition` = 'C' 
where (mileage >= 800000 or mileage is null) and year <= 2010 
and make not in ('Mercedes-Benz');

select * from cars where (mileage >= 800000 or mileage is null) and year <= 2010 
and make not in ('Mercedes-Benz');

-- 4. Delete
delete from clients 
where id not in (select client_id from courses) and char_length(full_name) > 3;

select * from clients 
where id not in (select client_id from courses) and char_length(full_name) > 3;


-- 3.	Section 3: Querying
-- 5. Cars
	select make, model, `condition` from cars order by id;


-- 6.	Drivers and Cars
select D.first_name, D.last_name, C.make, C.model, C.mileage
from drivers D
join cars_drivers CD on CD.driver_id = D.id
join cars C on C.id = CD.car_id 
where C.mileage is not null
order by C.mileage desc, D.first_name;


-- 7.	Number of courses for each car
select C.id as 'car_id', C.make, C.mileage, count(CO.id) as 'count_of_courses', 
round(avg(CO.bill),2) as 'avg_bill' 
from cars C
left join courses CO on CO.car_id = C.id
group by C.id 
having `count_of_courses` <> 2
order by `count_of_courses` desc, C.id;

select * from cars where id = 95;
select * from courses where car_id = 95;

-- 8.	Regular clients
select C.full_name, count(CO.car_id) as 'count_of_cars',
sum(CO.bill) as 'total_sum' 
from clients C 
join courses CO on CO.client_id = C.id
where substring(C.full_name,2,1) = 'a' 
group by C.id
having `count_of_cars` > 1
order by C.full_name; 


select * from clients where full_name = "Haven Seaton";
select * from courses where client_id = 88;


-- 9.	Full information of courses
select A.name, 
(case when (hour(CO.start) >= 6 and hour(CO.start) <= 20)
then 'Day'
when (hour(CO.start) <= 5 or hour(CO.start) >= 21 and hour(CO.start) <= 23)
then 'Night'
end
) as 'day_time',
CO.bill,C.full_name,CA.make,CA.model, CAT.name as 'category_name'
from clients C 
join courses CO on CO.client_id = C.id
join cars CA on CA.id = CO.car_id
join addresses A on A.id = CO.from_address_id
join categories CAT on CAT.id = CA.category_id
order by CO.id;


select hour(now());
-- 4. Section 4: Programmability

-- 10.	Find all courses by client’s phone number

drop function udf_courses_by_client;
delimiter //

create function udf_courses_by_client (phone_num VARCHAR (20))
returns int deterministic
begin
	declare count int;
    set count := (select count(CO.id) from clients C
		join courses CO on CO.client_id = C.id
		where C.phone_number = phone_num);
	return count;
end //

delimiter ;


SELECT udf_courses_by_client ('(803) 6386812') as `count`; 
SELECT udf_courses_by_client ('(831) 1391236') as `count`;
SELECT udf_courses_by_client ('(704) 2502909') as `count`;

select count(CO.id) from clients C
join courses CO on CO.client_id = C.id
where C.phone_number = '(803) 6386812';


-- 11.	Full info for address

drop procedure udp_courses_by_address;

delimiter //

create procedure udp_courses_by_address (address_name varchar(100))
begin
	select A.name, C.full_name,
(case when (CO.bill <= 20) then 'Low' 
when (CO.bill > 20 and CO.bill <= 30) then 'Medium'
else 'High'
end
) as 'level_of_bill',
CA.make, CA.`condition`, CAT.name as 'cat_name' 
from addresses A
join courses CO on CO.from_address_id = A.id
join clients C on C.id = CO.client_id
join cars CA on CA.id = CO.car_id
join categories  CAT on CAT.id = CA.category_id 
where A.name = address_name
order by CA.make, C.full_name;
end //

delimiter ;

CALL udp_courses_by_address('700 Monterey Avenue');

CALL udp_courses_by_address('66 Thompson Drive');

select A.name, C.full_name,
(case when (CO.bill <= 20) then 'Low' 
when (CO.bill > 20 and CO.bill <= 30) then 'Medium'
else 'High'
end
) as 'level_of_bill',
CA.make, CA.`condition`, CAT.name as 'cat_name' 
from addresses A
join courses CO on CO.from_address_id = A.id
join clients C on C.id = CO.client_id
join cars CA on CA.id = CO.car_id
join categories  CAT on CAT.id = CA.category_id 
where A.name = '700 Monterey Avenue'
order by CA.make, C.full_name;