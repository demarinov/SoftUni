-- 1. Count Employees by Town

drop function ufn_count_employees_by_town;

delimiter //
create function ufn_count_employees_by_town(town_name varchar(50)) 
RETURNS DOUBLE DETERMINISTIC READS SQL DATA
BEGIN
 	DECLARE e_count DOUBLE;
    SET e_count := (select count(employee_id) from soft_uni.employees E
		join soft_uni.addresses A on A.address_id = E.address_id
        join soft_uni.towns T on T.town_id = A.town_id
        where T.name = town_name
    );
    RETURN e_count;
END
//
delimiter ;

select ufn_count_employees_by_town('Sofia') as 'count';

-- 2. Employees Promotion
drop procedure usp_raise_salaries;
delimiter //
create procedure usp_raise_salaries(department_name varchar(50))
begin
	update soft_uni.employees E 
	inner join soft_uni.departments D on E.department_id = D.department_id
    set E.salary = E.salary + (E.salary * 0.05)
    where D.name = department_name;
end

delimiter ;

call usp_raise_salaries('Finance');

select first_name, round(salary,2) as 'salary' from soft_uni.employees 
where department_id = 10 order by first_name,salary;

-- 3. Employees Promotion By ID
delimiter //
create procedure usp_raise_salaries_by_id(id int)
begin
start transaction;
	if ((select count(*) from soft_uni.employees where employee_id like id) <> 1) 
    then
    rollback;
	else 
	update soft_uni.employees E 
    set E.salary = E.salary + (E.salary * 0.05)
    where E.employee_id = id;
    end if;
end

delimiter ;

call usp_raise_salaries_by_id(178);
select salary from soft_uni.employees where employee_id = 178;

-- 4. Triggered

create table soft_uni.deleted_employees(
employee_id int not null PRIMARY KEY,
first_name varchar(50) not null,
last_name varchar(50) not null,
middle_name varchar(50) not null,
job_title varchar(50),
department_id int,
salary double
);

drop trigger soft_uni.release_employee_trigger ;
delimiter //
create trigger soft_uni.release_employee_trigger 
AFTER DELETE
on soft_uni.employees
FOR EACH ROW
BEGIN
	insert into soft_uni.deleted_employees (employee_id,first_name, last_name, 
    middle_name,job_title ,department_id, salary)
    values(OLD.employee_id,
    OLD.first_name,OLD.last_name,OLD.middle_name, 
    OLD.job_title,OLD.department_id, OLD.salary);
END

delimiter ;
alter table soft_uni.employees drop foreign key fk_employees_employees;
alter table soft_uni.employees_projects drop foreign key fk_employees_projects_employees;
DELETE FROM soft_uni.employees WHERE employee_id IN (1);
select * from soft_uni.deleted_employees;