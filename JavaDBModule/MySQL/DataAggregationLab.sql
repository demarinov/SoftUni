
-- 1.	 Departments Info
select department_id,count(*) as 'Number of employees' from restaurant.employees 
group by department_id order by department_id, `Number of employees`;

-- 2.	Average Salary
select department_id, round(AVG(salary),2) as 'Average Salary' from restaurant.employees
group by department_id order by department_id;

-- 3.	 Min Salary
select department_id, round(min(salary),2) as 'Min Salary' from restaurant.employees
group by department_id having `Min Salary` >800;

-- 4.	 Appetizers Count
select count(id) from restaurant.products 
where price > 8 group by category_id having category_id = 2;

-- 5.	 Menu Prices
select category_id,round(AVG(price),2) as 'Average Price', 
round(MIN(price),2) as 'Chepeast Product',round(MAX(price),2) as 'Most Expensive Product'
from restaurant.products group by category_id;