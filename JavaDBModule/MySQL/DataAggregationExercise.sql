
-- 1.	 Records' Count
select count(`id`) as count from gringotts.wizzard_deposits;

-- 2.	 Longest Magic Wand
select MAX(magic_wand_size) as "longest_magic_wand" from gringotts.wizzard_deposits;

-- 3. Longest Magic Wand Per Deposit Groups
select deposit_group, MAX(magic_wand_size) as "longest_magic_wand" 
from gringotts.wizzard_deposits group by deposit_group 
order by `longest_magic_wand`,deposit_group;

-- 4. Smallest Deposit Group Per Magic Wand Size*
select deposit_group
from gringotts.wizzard_deposits 
group by deposit_group order by AVG(magic_wand_size) LIMIT 1;

-- 5.	 Deposits Sum
select deposit_group, SUM(deposit_amount) as 'total_sum'
from gringotts.wizzard_deposits 
group by deposit_group order by total_sum;

-- 6. Deposits Sum for Ollivander Family
select deposit_group, SUM(deposit_amount) as 'total_sum'
from gringotts.wizzard_deposits 
where magic_wand_creator = 'Ollivander family'
group by deposit_group order by deposit_group;

-- 7.	Deposits Filter
select deposit_group, SUM(deposit_amount) as 'total_sum'
from gringotts.wizzard_deposits 
where magic_wand_creator = 'Ollivander family'
group by deposit_group having total_sum <150000 order by total_sum DESC;

-- 8. Deposit Charge
select deposit_group, magic_wand_creator, MIN(deposit_charge) as min_deposit_charge
from gringotts.wizzard_deposits
group by deposit_group, magic_wand_creator
order by magic_wand_creator, deposit_group;


-- 9. Age Groups
select (case
when age >=0 and age <= 10
then '[0-10]'
when age >=11 and age <= 20
then '[11-20]'
when age >=21 and age <= 30
then '[21-30]'
when age >=31 and age <= 40
then '[31-40]'
when age >=41 and age <= 50
then '[41-50]'
when age >=51 and age <= 60
then '[51-60]'
when age >60
then '[61+]'
end) as 'age_group',count(id) as 'wizard_count'
from gringotts.wizzard_deposits
group by age_group order by age_group;

-- 10. First Letter
select LEFT(first_name,1) as 'first_letter'
from gringotts.wizzard_deposits
where deposit_group = 'Troll Chest'
GROUP BY first_letter order by first_letter;

-- 11.	Average Interest 
select deposit_group,is_deposit_expired, avg(deposit_interest) as 'average_interest'
from gringotts.wizzard_deposits
 where deposit_start_date > '1985/01/01'
 group by deposit_group, is_deposit_expired
order by deposit_group desc,is_deposit_expired asc;

select * from gringotts.wizzard_deposits;

select 
sum(O.deposit_amount - (select deposit_amount
from gringotts.wizzard_deposits as I 
where I.id > O.id order by I.id limit 1))
as sum_difference
from gringotts.wizzard_deposits as O;

-- 12.	 Employees Minimum Salaries
select department_id, MIN(salary) as 'minimum_salary'
from soft_uni.employees
where hire_date > timestamp('2000/01/01')
group by department_id having department_id in (2,5,7) 
order by department_id;

-- 13.	Employees Average Salaries
create table if not exists soft_uni.new_employees (
    select * from soft_uni.employees where salary > 30000
);

delete from soft_uni.new_employees where manager_id = 42;
update soft_uni.new_employees set salary = salary + 5000 where department_id = 1;
select department_id, avg(salary) as 'avg_salary'
from soft_uni.new_employees
group by department_id order by department_id;

-- 14. Employees Maximum Salaries
select department_id,MAX(salary) as 'max_salary' from soft_uni.employees
group by department_id having max_salary < 30000 or max_salary > 70000
order by department_id;

-- 15. Employees Count Salaries
select count(*) as '' from soft_uni.employees where manager_id is null;


select Distinct(salary) from soft_uni.employees as Em where Em.department_id = 3
order by salary desc limit 2,1;
-- 
-- select * from soft_uni.employees ORDER BY `salary` DESC limit 2,1;
-- 16.	3rd Highest Salary*
-- a bit wrong use limit with tw args ..
select department_id, 
(select * from (select distinct(salary) from soft_uni.employees as Em where Em.department_id = T.department_id 
order by salary desc limit 3) as F order by F.`salary` ASC limit 1) as 'third_highest_salary'
from soft_uni.employees as T group by department_id order by department_id;

-- alternative better
select department_id, 
(select distinct(salary) from soft_uni.employees as Em where Em.department_id = T.department_id 
order by salary desc limit 2,1) as 'third_highest_salary'
from soft_uni.employees as T group by department_id having `third_highest_salary` is not null
order by department_id;

-- 17.	 Salary Challenge**
select first_name, last_name, department_id
from soft_uni.employees as O
where (select AVG(I.salary) as 'avg_salary' from soft_uni.employees as I 
where I.department_id = O.department_id) < O.salary
order by O.department_id,O.employee_id limit 10;

-- 18.	Departments Total Salaries
select department_id, sum(salary) as 'total_salary' 
from soft_uni.employees group by department_id order by department_id;