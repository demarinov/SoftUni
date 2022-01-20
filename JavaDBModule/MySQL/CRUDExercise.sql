
--  the SQL commands on new lines (spaces for entities-start, end) as well as entities for judge
-- 1. Find All Information About Departments
select * from soft_uni.departments order by department_id;

-- 2. Find all Department Names
select name from soft_uni.departments order by department_id;

-- 3. Find salary of Each Employee
select first_name, last_name, salary from soft_uni.employees order by employee_id;
select first_name, last_name, salary from soft_uni.employees;

-- 4. Find Full Name of Each Employee
select first_name, middle_name, last_name from soft_uni.employees order by employee_id;
select first_name, middle_name, last_name from soft_uni.employees;

-- 5. Find Email Address of Each Employee
select concat(first_name,'.',last_name,'@softuni.bg') as 'full_email_address' 
from soft_uni.employees;

-- 6. Find All Different Employee’s Salaries
select DISTINCT Salary from soft_uni.employees order by employee_id; 

-- 7. Find all Information About Employees
select * from soft_uni.employees where job_title = 'Sales Representative' order by employee_id;

-- 8. Find Names of All Employees by salary in Range
select first_name, last_name, job_title from soft_uni.employees where salary 
between 20000 AND 30000 order by employee_id; 

-- 9. Find Names of All Employees
select concat_ws(' ',first_name,middle_name,last_name) as 'Full Name' from soft_uni.employees
where salary in (25000, 14000, 12500,23600);

-- 10. Find All Employees Without Manager
select first_name, last_name from soft_uni.employees where manager_id IS null;

-- 11. Find All Employees with salary More Than 50000
select  first_name, last_name, salary from soft_uni.employees where
salary > 50000 order by salary DESC;

-- 12. Find 5 Best Paid Employees
select  first_name, last_name from soft_uni.employees order by salary DESC LIMIT 5;

-- 13. Find All Employees Except Marketing
select  first_name, last_name from soft_uni.employees where
 not (department_id = 4);
 
 -- 14. Sort Employees Table
 select * from soft_uni.employees order by salary desc, firsT_name asc, 
 last_name desc, middle_name asc;
 
 -- 15. Create View Employees with Salaries
 create view v_employees_salaries 
 as
 select first_name, last_name, salary from soft_uni.employees;
 
 select * from v_employees_salaries;
 
 -- 16. Create View Employees with Job Titles
 create view v_employees_job_titles
 as
 select concat_ws(' ',first_name,middle_name, last_name) as 'full_name', job_title 
 from soft_uni.employees;
 
 select * from v_employees_job_titles;
 
 -- 17. Distinct Job Titles
 select distinct job_title from soft_uni.employees order by job_title;
 
 -- 18. Find First 10 Started Projects
 select * from soft_uni.projects order by start_date, name limit 10;
 
 -- 19. Last 7 Hired Employees
 select first_name, last_name, hire_date from soft_uni.employees order by hire_date desc
 limit 7;
 
 -- Write a SQL query to increase salaries of all employees that 
 -- are in the Engineering, Tool Design, Marketing or
-- Information Services department by 12%
-- 20. Increase Salaries
update soft_uni.employees set salary = salary + (0.12 * salary) 
where department_id in 
(11, 4, 2, 1);

select salary from soft_uni.employees;

-- 21. All Mountain Peaks
select peak_name from geography.peaks order by peak_name asc;

-- 22. Biggest Countries by Population
select country_name, population from geography.countries 
where continent_code in ('EU') 
order by population desc, country_name asc limit 30;

-- 23. Countries and Currency (Euro / Not Euro)
select country_name, country_code, 
(case when currency_code = 'EUR' then 'Euro' ELSE 'Not Euro' END) as currency
from countries order by country_name;

-- 24. All Diablo Characters
select name from diablo.characters order by name;
 