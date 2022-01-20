
-- 1. Managers
select E.employee_id, concat(first_name,' ',last_name) as 'full_name', D.department_id, 
D.name as department_name from soft_uni.employees E join soft_uni.departments D 
on D.manager_id = E.employee_id
order by E.employee_id limit 5;


-- 2. Towns Adresses
select T.town_id, T.name, A.address_text
from soft_uni.towns T join soft_uni.addresses A on A.town_id = T.town_id
where T.name in ('San Francisco','Sofia','Carnation') order by A.town_id,A.address_id;

-- 3. Employees Without Managers
select E.employee_id, E.first_name, E.last_name, D.department_id, E.salary
from soft_uni.employees E join soft_uni.departments D on E.department_id = D.department_id
where E.manager_id is null; 

-- 4. Higher Salary
select count(*) as 'count' from soft_uni.employees E where
E.salary > (select avg(salary) from soft_uni.employees);