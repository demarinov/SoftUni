-- 1. Employee Address
select E.employee_id, E.job_title, A.address_id, A.address_text
from soft_uni.employees E join soft_uni.addresses A on E.address_id = A.address_id
order by A.address_id LIMIT 5;

-- 2. Addresses with Towns
select E.first_name, E.last_name, T.name, A.address_text from soft_uni.employees E
join soft_uni.addresses A on E.address_id = A.address_id
JOIN soft_uni.towns T on A.town_id = T.town_id
order by E.first_name,E.last_name limit 5; 

-- 3. Sales Employee
-- Sort the result by employee_id in descending order. 
-- Select only employees from the “Sales” department.
select E.employee_id, E.first_name, E.last_name, D.name as 'department_name'
from soft_uni.employees E 
join soft_uni.departments D on D.department_id = E.department_id
where D.name = 'Sales' order by E.employee_id DESC;

-- 4. Employee Departments
-- Filter only employees with salary higher than 15000. 
-- Return the first 5 rows sorted by department_id in descending order
select E.employee_id, E.first_name, round(E.salary,2), D.name as 'department_name'
from soft_uni.employees E 
join soft_uni.departments D on D.department_id = E.department_id 
where E.salary > 15000 order by D.department_id DESC limit 5;

-- 5. Employees Without Project
-- Filter only employees without a project. 
-- Return the first 3 rows sorted by employee_id in descending order
select E.employee_id, E.first_name from soft_uni.employees E 
left join soft_uni.employees_projects EP on EP.employee_id = E.employee_id
where EP.project_id is null 
order by E.employee_id DESC limit 4;


-- 6. Employees Hired After
-- Filter only employees hired after 1/1/1999 and from either the 
-- "Sales" or the "Finance" departments. Sort the
-- result by hire_date (ascending)
select E.first_name, E.last_name, E.hire_date, D.name as 'dept_name' 
from soft_uni.employees E join soft_uni.departments D on D.department_id = E.department_id
where E.hire_date > '1999/01/01' and D.name in ('Sales','Finance')
order by E.hire_date asc;

-- 7. Employees with Project
 select E.employee_id, E.first_name, P.name as 'project_name'
 from soft_uni.employees E
 join soft_uni.employees_projects EP on EP.employee_id = E.employee_id
 join soft_uni.projects P on P.project_id = EP.project_id
 where DATE(P.start_date) > date ('2002-08-13') and P.end_date is null
 order by E.first_name asc,`project_name` asc limit 5;
 
 
 -- 8. Employee 24
 -- Filter all the projects of employees with id 24. 
 -- If the project has started after 2005 inclusively the return value
 -- should be NULL. Sort the result by project_name alphabetically.
 select E.employee_id, E.first_name,
 case when EXTRACT(year from P.start_date) < '2005'
 then P.name
 else null
 end as 'project_name'
 from soft_uni.employees E
 join soft_uni.employees_projects EP on EP.employee_id = E.employee_id
 join soft_uni.projects P on P.project_id = EP.project_id
 where E.employee_id = 24 order by P.name;
 
 -- 9. Employee Manager
 -- Filter all employees with a manager who has id equal to 3 or 7. 
-- Return all rows sorted by employee first_name in ascending order
 select E.employee_id, E.first_name, E.manager_id, M.first_name as 'manager_name'
 from soft_uni.employees E join soft_uni.employees M on E.manager_id = M.employee_id
 where M.employee_id in (3,7) order by E.first_name;
 
 -- 10. Employee Summary
 -- Show the first 5 employees (only for employees who have a manager) 
 -- with their managers and the departments
-- they are in (show the departments of the employees). Order by employee_id
select E.employee_id, concat(E.first_name,' ',E.last_name) as 'employee_name', 
concat(M.first_name,' ',M.last_name) as 'manager_name', 
D.name as 'department_name' from soft_uni.employees E join soft_uni.departments D
on E.department_id = D.department_id
join soft_uni.employees M on M.employee_id = E.manager_id order by E.employee_id limit 5;

-- 11. Min Average Salary
select truncate(min(`avg_salary`),4) as 'min_average_salary'
from (select avg(salary) as 'avg_salary' from soft_uni.employees group by department_id) M;

-- 12. Highest Peaks in Bulgaria

select MC.country_code, M.mountain_range, P.peak_name, P.elevation
from geography.mountains M join geography.peaks P on P.mountain_id = M.id
join geography.mountains_countries MC on MC.mountain_id = M.id
join geography.countries C on C.country_code = MC.country_code
where C.country_code = 'BG' AND P.elevation > 2835 order by P.elevation desc; 

-- 13. Count Mountain Ranges
 select C.country_code, count(M.mountain_range) as 'mountain_range'
 from geography.mountains M join geography.mountains_countries MC
 on MC.mountain_id = M.id 
 join geography.countries C on C.country_code = MC.country_code
 where C.country_code in ('BG','US','RU') group by C.country_code 
 order by `mountain_range` desc;
 
 -- 14. Countries with Rivers
 select C.country_name, R.river_name from geography.countries C 
 left join geography.countries_rivers CR on CR.country_code = C.country_code
 left join geography.rivers R on R.id = CR.river_id
 where C.continent_code = 'AF'
 order by C.country_name limit 5;
 
 -- 15. *Continents and Currencies
 select CC1.continent_code, CC1.currency_code, CC1.currency_usage 
 from (select CO.continent_code, CO.currency_code, count(*) as 'currency_usage'
 from geography.countries CO group by CO.continent_code, CO.currency_code) CC1
 left join 
 (select C.currency_code,C.continent_code,count(*) as 'currency_usage'
 from geography.countries C group by C.continent_code, C.currency_code) CC2
 on CC1.continent_code = CC2.continent_code and CC1.`currency_usage` < CC2.`currency_usage`
 where CC2.currency_usage is null
 having CC1.`currency_usage` > 1
 order by CC1.continent_code, CC1.currency_code;

 
 -- 16. Countries without any Mountains
 select count(*) as 'country_count' from geography.countries C
 left join geography.mountains_countries MC on MC.country_code = C.country_code
 where MC.mountain_id is null;
 
 -- 17. Highest Peak and Longest River by Country
 
 select C.country_name, 
 MAX(P.elevation) as 'highest_peak_elevation',
 MAX(R.length) as 'longest_river_length'
from geography.mountains M join geography.peaks P on P.mountain_id = M.id
join geography.mountains_countries MC on MC.mountain_id = M.id
join geography.countries C on C.country_code = MC.country_code 
left join geography.countries_rivers CR on CR.country_code = C.country_code
left join geography.rivers R on R.id = CR.river_id
group by C.country_code order by `highest_peak_elevation` desc, `longest_river_length` desc
,C.country_name limit 5;
