-- Part I – Queries for SoftUni Database
-- 1.Employees with Salary Above 35000

delimiter //
create procedure usp_get_employees_salary_above_35000()
begin
	select first_name, last_name from soft_uni.employees 
    where salary > 35000 order by first_name,last_name, employee_id asc;
end;

delimiter ;
call usp_get_employees_salary_above_35000();

-- 2.	Employees with Salary Above Number

delimiter //
create procedure usp_get_employees_salary_above(num double) 
begin
	select first_name, last_name from soft_uni.employees 
    where salary >= num order by first_name,last_name, employee_id asc;
end;

delimiter ;
call usp_get_employees_salary_above(48100);

-- 3.	Town Names Starting With
drop procedure usp_get_towns_starting_with;
delimiter //
create procedure usp_get_towns_starting_with(startingName varchar(50))
begin
	select name as 'town_name' from soft_uni.towns T
    where left(T.name,length(startingName)) = startingName
    order by T.name;
end;

delimiter ;
call usp_get_towns_starting_with('b');

-- 4.	Employees from Town

delimiter //
create procedure usp_get_employees_from_town(town_name varchar(50))
begin
	select E.first_name, E.last_name 
    from soft_uni.employees E
    join soft_uni.addresses A on A.address_id = E.address_id
    inner join soft_uni.towns T on T.town_id = A.town_id
    where T.name = town_name 
    order by E.first_name, E.last_name, E.employee_id;
end;

delimiter ;
call usp_get_employees_from_town('Sofia');

-- 5.	Salary Level Function

delimiter //
create function ufn_get_salary_level(salary double)
RETURNS varchar(50) DETERMINISTIC READS SQL DATA
begin
   -- •	If salary is < 30000 return “Low”
   -- •	If salary is between 30000 and 50000 (inclusive) return “Average”
   -- •	If salary is > 50000 return “High”

	declare result varchar(50);
	set result := "High";
	if (salary < 30000) 
    then
		set result := "Low";
    elseif (salary >= 30000 and salary <= 50000) 
    then
		set result:= "Average";
    end if;
    return result;
end;

delimiter ;
select round(salary,2) as 'salary', 
ufn_get_salary_level(salary) as 'salary_level' from soft_uni.employees;

-- 6.	Employees by Salary Level

drop procedure usp_get_employees_by_salary_level;
delimiter //
create procedure usp_get_employees_by_salary_level(salary_level varchar(50))
begin
	select first_name, last_name from soft_uni.employees
    where ufn_get_salary_level(salary) = salary_level
    order by first_name desc, last_name desc;
end;

delimiter ;
call usp_get_employees_by_salary_level('High');

-- 7.	Define Function

use test_db;
drop function ufn_is_word_comprised;

delimiter //
create function ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))
returns int DETERMINISTIC READS SQL DATA
begin
	DECLARE result int;
    DECLARE letter char(1);
    DECLARE count int;
    Declare letterPos int;
    set count = 1;
    set result := 0;
    while count <= char_length(word) 
    do
    set letter = substring(word,count,1);
    set letterPos := locate(letter, set_of_letters);
    if (letterPos > 0) 
    then
		set result := result + 1;
    end if;
    set count := count + 1; 
    end while;
    if (result <> 0 and result = length(word)) then 
     return 1;
     end if;
     return 0;
end //

delimiter ;

select char_length('') from dual;

select 'oistmiahf' as 'set_of_letters','Sofia' as 'word'
, ufn_is_word_comprised('oistmiahf','Sofia') as 'result' 
from dual;
select 'oistmiahf' as 'set_of_letters','halves' as 'word', 
ufn_is_word_comprised2(lower('oistmiahf'),lower('halves')) as 'result'
from dual;
select 'bobr' as 'set_of_letters','Rob' as 'word', 
ufn_is_word_comprised2(lower('bobr'),lower('Rob')) as 'result' 
from dual;
select 'pppp' as 'set_of_letters','Guy' as 'word', 
ufn_is_word_comprised2(lower('pppp'),lower('Guy')) as 'result'
from dual;

drop function ufn_is_word_comprised2;
delimiter //
create function ufn_is_word_comprised2(set_of_letters varchar(50), word varchar(50))
returns int DETERMINISTIC READS SQL DATA
begin
    return word regexp concat('^[',set_of_letters,']+$');
end //

-- PART II – Queries for Bank Database
-- 8.	Find Full Name

drop procedure usp_get_holders_full_name;
delimiter //

create procedure usp_get_holders_full_name()
begin
	select concat(first_name," ",last_name) as 'full_name'
    from soft_uni.account_holders A 
    order by `full_name` asc, A.id asc;
end;

delimiter ;

call usp_get_holders_full_name();

-- 9

delimiter //
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(balance_param DECIMAL(19, 4))
BEGIN
	SELECT h.first_name, h.last_name
    FROM account_holders h
    INNER JOIN accounts a
    ON a.account_holder_id = h.id
    GROUP BY a.account_holder_id
    HAVING SUM(a.balance) > balance_param
    ORDER BY a.account_holder_id asc;
END //

delimiter ;

-- 10.	Future Value Function
drop function ufn_calculate_future_value;
delimiter //
create function ufn_calculate_future_value(i decimal(15,4), r double, t int)
returns decimal(15,4) DETERMINISTIC READS SQL DATA
begin
 -- FV=I×(〖(1+R)〗^T)
	declare fv decimal(15,4);
    set fv := (i * POW((1 + r),t));
    return fv;
end //

delimiter ;

select format(2,4) from dual;
select ufn_calculate_future_value(1000,0.1,5) as 'output' from dual;
select sum, interest, years, ufn_calculate_future_value(sum, interest, years) as future_sum
from deposits order by sum;

-- 11.	Calculating Interest
use test_db;
drop procedure usp_calculate_future_value_for_account;
delimiter //
create procedure usp_calculate_future_value_for_account (account_id int, interest_rate double)
begin
	select A.id as 'account_id', AH.first_name, AH.last_name, A.balance as 'current_balance',
    round(ufn_calculate_future_value(A.balance,round(interest_rate,4),5),4) as 'balance_in_5_years'
    from soft_uni.account_holders AH join soft_uni.accounts A on A.account_holder_id = AH.id
    where A.id = account_id;
end //

delimiter ;

call usp_calculate_future_value_for_account(1,0.005125343);

-- 12.	Deposit Money

drop procedure usp_deposit_money;
delimiter //
create procedure usp_deposit_money(account_id int, money_amount double)
begin
	
    start transaction;
    if (money_amount < 0 or (select count(*) from soft_uni.accounts where id like account_id) <> 1) 
    then
		rollback;
    else 
    update soft_uni.account_holders AH join soft_uni.accounts A on A.account_holder_id = AH.id
	set A.balance = round(A.balance + money_amount,4)
    where A.id = account_id;
    end if;
end //

delimiter ;

select A.id as 'account_id', A.account_holder_id, A.balance from soft_uni.accounts A where A.id = 1;
call usp_deposit_money(1,10);
select A.id as 'account_id', A.account_holder_id, A.balance from soft_uni.accounts A where A.id = 1;

-- 13.	Withdraw Money

drop procedure usp_withdraw_money;
delimiter //
create procedure usp_withdraw_money(account_id int, money_amount double)
begin
	
    start transaction;
    if (money_amount < 0 
    or (select count(*) from soft_uni.accounts where id like account_id) <> 1
    or (select balance-money_amount from soft_uni.accounts where id like account_id) < 0) 
    then
		rollback;
    else 
    update soft_uni.account_holders AH join soft_uni.accounts A on A.account_holder_id = AH.id
	set A.balance = round(A.balance - money_amount,4)
    where A.id = account_id;
    end if;
end //

delimiter ;
select A.id as 'account_id', A.account_holder_id, A.balance from soft_uni.accounts A where A.id = 1;
call usp_withdraw_money(1,10);
select A.id as 'account_id', A.account_holder_id, A.balance from soft_uni.accounts A where A.id = 1;

-- 14.	Money Transfer

use test_db;
delimiter //
create procedure usp_transfer_money(from_account_id int, to_account_id int, amount double)
begin
	-- check old balance and new one
    declare oldBalance double;
    declare newBalance double;
    if (from_account_id <> to_account_id and from_account_id > 0 and to_account_id > 0 and amount > 0) 
    then
    set oldBalance := (select balance from soft_uni.accounts where id like from_account_id);
    call usp_withdraw_money(from_account_id, amount);
    set newBalance := (select balance from soft_uni.accounts where id like from_account_id);
    
    if (newBalance < oldBalance) then
    call usp_deposit_money(to_account_id, amount);
    end if;
    end if;
end //

delimiter ;

select id as 'account_id', account_holder_id, balance from soft_uni.accounts where id in (1,2);
call usp_transfer_money(1,2,10);

-- 15.	Log Accounts Trigger

create table if not exists logs (
log_id int not null PRIMARY KEY AUTO_INCREMENT, 
account_id int not null, 
old_sum decimal(15,4), 
new_sum decimal(15,4)
); 

drop trigger soft_uni.tr_accounts_logs;
delimiter //
create trigger soft_uni.tr_accounts_logs
after update
on soft_uni.accounts
for each row
begin
	insert into logs(account_id, old_sum, new_sum) values (OLD.id,OLD.balance,NEW.balance);
end //

delimiter ;

select * from logs;
call usp_withdraw_money(1,10);
call usp_deposit_money(1,10);

-- 16.	Emails Trigger

create table soft_uni.notification_emails(
id int not null PRIMARY KEY AUTO_INCREMENT, 
recipient int not null, 
subject varchar(50),  
body varchar(100)
); 

-- 	•	recipient – account_id
--	•	subject – Balance change for account: account_id
--	•	body - On {date (current date)} your balance was changed from {old} to {new}.

drop trigger soft_uni.tr_logs_notifications;

delimiter //
create trigger soft_uni.tr_logs_notifications
after insert
on soft_uni.logs
for each row
begin
	insert into notification_emails(recipient, `subject`, body) 
values(NEW.account_id, concat('Balance change for account: ',NEW.account_id),concat('On ',date_format(now(),'%b %d %Y at %r')
,' your balance was changed from ',round(New.old_sum),' to ',Round(NEW.new_sum),'.'));
end //

delimiter ;

select truncate(2.55,2), concat(date_format(now(),'%b %d %Y at %r')) from dual;
select * from soft_uni.notification_emails;
-- delete from soft_uni.notification_emails;
call usp_deposit_money(1,10);
call usp_withdraw_money(1,10);