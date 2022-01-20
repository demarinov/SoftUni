
-- 1.	Find Names of All Employees by First Name
select first_name, last_name from soft_uni.employees 
where lower(substring(first_name,1,2)) = 'sa' order by employee_id;

-- 2.	Find Names of All Employees by Last Name
select first_name, last_name from soft_uni.employees
where lower(last_name) like '%ei%' order by employee_id;

-- 2. alternative solution
select `first_name`, `last_name` from `employees`
where locate('ei',`last_name`) > 0;

-- 3.	Find First Names of All Employees
select `first_name` from `employees` where `department_id` in (2,10) and 
EXTRACT(year from `hire_date`) between 1995 and 2005 order by `employee_id`;

SELECT
	`first_name`
FROM
	`employees`
WHERE
    `department_id` IN(3, 10)
	AND YEAR(`hire_date`) BETWEEN 1995 AND 2005
ORDER BY `employee_id`;

-- 4.	Find All Employees Except Engineers
select first_name, last_name, job_title from soft_uni.employees 
where job_title not like '%engineer%' order by employee_id;

-- 5.	Find Towns with Name Length
select name from soft_uni.towns where char_length(name) in (5,6) order by name;

-- 6.	 Find Towns Starting With
select town_id, name from soft_uni.towns where LOWER(LEFT(name,1)) in ('m','k','b','e') 
order by name;
-- 6. alternative
select town_id, name from soft_uni.towns where LEFT(name,1) in ('m','k','b','e') 
order by name;

-- 7.	 Find Towns Not Starting With
select town_id, name from soft_uni.towns where LOWER(LEFT(name,1)) not in ('r','b','d') 
order by name;

-- 7. alternative
select town_id, name from soft_uni.towns where LEFT(name,1) not in ('r','b','d') 
order by name;

-- 8.	Create View Employees Hired After 2000 Year
create view soft_uni.v_employees_hired_after_2000 
as
select first_name, last_name from soft_uni.employees
where EXTRACT(year from hire_date) > 2000;

select * from soft_uni.v_employees_hired_after_2000;

-- 9.	Length of Last Name
select first_name, last_name from soft_uni.employees 
where char_length(last_name) = 5;

-- 10.	Countries Holding 'A' 3 or More Times
select country_name, iso_code from geography.countries 
where ROUND((char_length(country_name) - char_length(replace(lower(country_name),'a',''))
/ char_length('a'))) >= 3 order by iso_code;

-- 11.	 Mix of Peak and River Names
select peak_name, river_name, lower(concat(substring(peak_name,1,char_length(peak_name)-1)
,river_name)) as 'mix' 
from geography.peaks, geography.rivers 
where RIGHT(peak_name,1) = LEFT(river_name,1) order by mix;

-- 12.	Games from 2011 and 2012 Year
select name, date_format(start,'%Y-%m-%d') as 'start' from diablo.games 
where extract(year from start) in (2011,2012)
order by start,name limit 50;

-- 13.	 User Email Providers
select user_name, substring(email,(position('@' in email)+1)) as 'Email Provider' 
from diablo.users order by `Email Provider`, `user_name`;

-- 14.	 Get Users with IP Address Like Pattern
select user_name, ip_address from diablo.users 
where ip_address like '___.1%.%.___'
order by user_name;

-- 15.	 Show All Games with Duration and Part of the Day
select name as game, 
(case 
when EXTRACT(hour from start) >=0 AND  EXTRACT(hour from start) < 12
then 'Morning'
when EXTRACT(hour from start) >=12 AND  EXTRACT(hour from start) < 18
then 'Afternoon'
when EXTRACT(hour from start) >=18 AND  EXTRACT(hour from start) < 24
then 'Evening'
end) as 'Part of the Day',
(case 
	when duration <=3
    then 'Extra Short'
    when duration > 3 and duration <=6
    then 'Short'
    when duration > 6 and duration <= 10
    then 'Long'
    else
		'Extra Long'
end        
) as Duration
from diablo.games;

-- 16.	 Orders Table
select product_name, order_date, date_add(order_date, interval 3 day) as pay_due, 
 date_add(order_date, interval 1 month) as deliver_due
from orders.orders;