
drop database ruk_database;
create database ruk_database;

use ruk_database;
-- 1. Section DDL

-- 1. Table design

create table branches (
	id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(30) not null UNIQUE
);

create table employees(
	id int PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    salary decimal(10,2) not null,
    started_on date not null,
    branch_id int not null,
    
    constraint fk_employees_branches foreign key (branch_id)
    references branches(id)
);

create table clients(
	id int PRIMARY KEY AUTO_INCREMENT,
    full_name varchar(50) not null,
    age int not null
);

create table employees_clients (
	employee_id int,
    client_id int,
    
	-- constraint pk_employees_clients PRIMARY KEY (employee_id,client_id),
    constraint fk_employees_employees_clients foreign key (employee_id) 
    references employees(id),
    constraint fk_clients_employees_clients foreign key (client_id) 
    references clients(id)
);

create table bank_accounts(
	id int PRIMARY KEY AUTO_INCREMENT,
    account_number varchar(10) not null,
    balance decimal(10,2) not null,
    client_id int not null UNIQUE,
    
    constraint fk_bank_accounts_clients foreign key (client_id) 
    references clients(id)
);

create table cards (
	id int PRIMARY KEY AUTO_INCREMENT,
    card_number varchar(19) not null,
    card_status varchar(7) not null,
    bank_account_id int not null,
    
    constraint fk_cards_bank_accounts foreign key (bank_account_id) 
    references bank_accounts(id)
);

SELECT COLUMN_KEY, COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
AND COLUMN_NAME IN ('id', 'branch_id', 'employee_id', 'client_id', 'bank_account_id', 'card_id')
ORDER BY TABLE_NAME, COLUMN_KEY;

-- Section 2. DML

-- 02.	Insert

insert into cards (card_number,card_status,bank_account_id)
select reverse(full_name) as 'full_name', 'Active', 
id from clients where id between 191 and 200;

select * from clients where id between 191 and 200;


-- 03 Update

update employees_clients C set C.employee_id = (select `emp_lowest_client`.employee_id from 
(select EC.employee_id, MIN(client_id) as 'lowest_client_id' ,
count(client_id) as 'count_clients' 
from employees_clients EC 
group by EC.employee_id order by count_clients, lowest_client_id limit 1) `emp_lowest_client`)
where C.client_id = C.employee_id;



select * from employees_clients where client_id in (27,46);

select * from clients C where id in 
(select employee_id from employees_clients EC where C.id = EC.client_id);

select * from clients C
join employees_clients EC on EC.client_id = C.id
where EC.employee_id = C.id;

select employee_id from (select employee_id, MIN(client_id) as 'lowest_client_id' ,
count(client_id) as 'count_clients' 
from employees_clients EC 
group by employee_id order by count_clients, lowest_client_id limit 1) `emp_lowest_client`;


-- 04.	Delete
delete from employees E where E.id not in 
(select EC.employee_id from employees_clients EC);

 select EC.employee_id from employees_clients EC where EC.client_id is null;
 select * from employees where first_name like 'Milty23';
 insert into employees (first_name, last_name, salary, started_on, branch_id) 
 values ('Milty23','Dyett',213270.78,'2017-06-10',38);
 insert into employees_clients values(102, null);


-- 3.	Section 3: Querying – 50 pts
-- 05 Clients
select id, full_name from clients order by id;

-- 06.	Newbies

select id, concat(first_name,' ',last_name) as 'full_name',
concat('$',salary) as 'salary', started_on from employees 
where salary > 100000
and date(started_on) >= date('2018-01-01')
order by salary desc, id;

-- 07.	Cards against Humanity

select C.id, concat(C.card_number,' : ', CL.full_name) as 'card_token' from cards C 
join bank_accounts BA on BA.id = C.bank_account_id
join clients CL on CL.id = BA.client_id
order by C.id desc;

select * from cards order by id desc;

-- 08.	Top 5 Employees

select concat(E.first_name,' ', E.last_name)as 'name', E.started_on, 
count(EC.client_id) as 'count_of_clients' from employees E
join employees_clients EC on EC.employee_id = E.id
group by E.id 
order by `count_of_clients`desc, EC.employee_id asc limit 5;

-- 09.	Branch cards
select B.name, count(CA.id) as 'count_of_cards' from branches B
left join employees E on E.branch_id = B.id
left join employees_clients EC on EC.employee_id = E.id
left join bank_accounts BA on BA.client_id = EC.client_id
left join cards CA on CA.bank_account_id = BA.id
group by B.id order by `count_of_cards` desc, B.name;


-- 4.	Section 4: Programmability – 30 pts

-- 10. extract clients card count
drop function udf_client_cards_count;

delimiter //
create function udf_client_cards_count(name VARCHAR(30)) 
returns int deterministic
begin
	declare count int;
    set count := (select count(CA.id) as 'cards' from clients CL
				join bank_accounts BA on BA.client_id = CL.id
				join cards CA on CA.bank_account_id = BA.id 
				where CL.full_name = name);
    return count;            
end //

delimiter ;

SELECT c.full_name, udf_client_cards_count('Baxy David') as `cards` FROM clients c
WHERE c.full_name = 'Baxy David';



select count(CA.id) as 'cards' from clients CL
join bank_accounts BA on BA.client_id = CL.id
join cards CA on CA.bank_account_id = BA.id 
where CL.full_name = 'Baxy David';

-- 11.	Extract Client Info

drop procedure udp_clientinfo;

delimiter //

create procedure udp_clientinfo(full_name varchar(30))
begin
	select CL.full_name, CL.age, BA.account_number, concat('$',BA.balance) as 'balance' 
	from clients CL
	join bank_accounts BA on BA.client_id = CL.id where CL.full_name = full_name;
end //

delimiter ;

call udp_clientinfo('Hunter Wesgate');

select CL.full_name, CL.age, BA.account_number, concat('$',BA.balance) as 'balance' 
from clients CL
join bank_accounts BA on BA.client_id = CL.id where CL.full_name = 'Hunter Wesgate';