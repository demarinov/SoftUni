
-- Problem 1: Select Employee Information
select id, first_name, last_name, job_title from hospital.employees order by id;
-- Problem 2: Select Employees with Filter
select `id`, concat(`first_name`, ' ' ,`last_name`) as 'full_name', `job_title`, `salary` from `employees`
where `salary` > 1000.00;

-- Problem 3: Update Employees Salary
update hospital.employees set salary = salary + (0.1 * salary) where job_title = 'Therapist';
select salary from hospital.employees order by salary ASC; 

-- Problem 3: Update Employees Salary new
update hospital.employees set salary = salary + 100 where job_title = 'Manager';
select salary from hospital.employees;

-- Problem 4: Top Paid Employee
create view top_paid_emp_view 
as
select * from hospital.employees where salary order by salary DESC limit 1;

select * from top_paid_emp_view;

-- Problem 5: Select Employees by Multiple Filters
select * from hospital.employees where department_id = 4 and salary >= 1600 order by id;

-- Problem 5: Select Employees by Multiple Filters new
select * from hospital.employees where department_id = 4 and salary >= 1000 order by id;

-- Problem 6: Delete from Table
delete from hospital.employees where department_id in (1,2);
select * from hospital.employees order by id;
