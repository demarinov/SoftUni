-- Section 1: Data Definition Language (DDL) – 40 pts

create database fsd;
-- Table Design

create table fsd.skills_data(
	id int(11) not null primary key auto_increment,
    dribbling int(11) default 0,
    pace int(11) default 0,
    passing int(11) default 0,
    shooting int(11) default 0,
    speed int(11) default 0,
    strength int(11) default 0
);

create table fsd.countries(
	id int(11) not null primary key auto_increment,
    name varchar(45) not null
);

create table fsd.towns (
	id int(11) not null primary key auto_increment,
    name varchar(45) not null,
    country_id int(11) not null,
    
    constraint fk_towns_countries
    foreign key (country_id) references countries(id)
);

create table fsd.stadiums (
	id int(11) not null primary key auto_increment,
    name varchar(45) not null,
    capacity int(11) not null,
    town_id int(11) not null,
    constraint fk_stadiums_towns 
    foreign key (town_id) references towns(id)
);

create table fsd.teams(
	id int(11) not null primary key auto_increment,
    name varchar(45) not null,
    established date not null,
    fan_base bigint(20) not null default 0,
    stadium_id int(11) not null,
    
    constraint fk_teams_stadiums 
    foreign key (stadium_id) 
    references stadiums(id)
);

create table fsd.players (
	id int(11) not null PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(10) not null,
    last_name varchar(20) not null,
    age int(11) not null DEFAULT 0,
    position char(1) not null,
    salary decimal(10,2) not null default 0,
    hire_date datetime,
    skills_data_id int(11) not null,
    team_id int(11),
    
    -- relationships 
    constraint fk_players_teams
    foreign key (team_id) references teams(id),
    constraint fk_players_skills 
    foreign key (skills_data_id) 
    references skills_data(id)
);

create table fsd.coaches(
	id int(11) not null PRIMARY KEY auto_increment,
    first_name varchar(10) not null,
    last_name varchar(20) not null,
    salary decimal(10,2)not null default 0,
    coach_level int(11) not null default 0
);


create table fsd.players_coaches(
	player_id int(11),
    coach_id int(11),
    
    PRIMARY KEY(player_id, coach_id),
    constraint fk_player_coaches_players
    foreign key (player_id) references players(id),
    
	constraint fk_player_coaches_coaches
	foreign key (coach_id) references coaches(id)
);


-- Section 2: Data Manipulation Language (DML) – 30 pts
-- 2.	Insert

insert into coaches(`first_name`,`last_name`,`salary`,`coach_level`) 
(select pl.first_name, pl.last_name, 
pl.salary, char_length(`first_name`) as 'coach_level' 
from fsd.players pl where pl.age >= 45);

select count(coach_id) from fsd.players_coaches pc where pc.player_id is not null and
pc.coach_id = 5 limit 1;

-- 3.	Update
update fsd.coaches c set coach_level = coach_level + 1 
where (select count(coach_id) from fsd.players_coaches pc where pc.player_id is not null 
and pc.coach_id = c.id limit 1) <> 0
and LEFT(c.first_name,1) = 'A';

SELECT c.id, c.first_name,c.coach_level FROM coaches as c

where id BETWEEN 1 AND 5
ORDER BY c.id;

-- 4.	Delete
delete from fsd.coaches c where concat(c.first_name,' ',c.last_name) in 
(select concat(first_name,' ',last_name) from fsd.players pl where pl.age >= 45); 

-- right solution
delete from fsd.players pl where pl.age >= 45; 

SELECT COUNT(*) FROM players;

-- Section 3: Querying – 50 pts
-- 5.	Players 

select first_name, age, salary from players order by salary desc;

-- 6.	Young offense players without contract

select pl.id,concat(first_name, ' ',last_name)  as 'full_name',
age, position, hire_date from players pl
join skills_data sd on pl.skills_data_id = sd.id
where age < 23 and position = 'A' and hire_date is null and sd.strength > 50
order by salary asc;

-- 7.	Detail info for all teams

select name as 'team_name', established, fan_base,
count(pl.id) as players_count from teams t 
left join players pl on pl.team_id = t.id
group by t.id order by `players_count`desc, fan_base desc;

-- 8.	The fastest player by towns
select MAX(speed) as 'max_speed', tt.name as 'town_name' from players pl
join skills_data sd on pl.skills_data_id = sd.id
right join teams t on pl.team_id = t.id
join stadiums st on t.stadium_id = st.id
join towns tt on tt.id = st.town_id
where t.name not in ('Devify')
group by tt.id
order by `max_speed` desc, tt.name asc;

-- alternative different...
SELECT MAX(sd.speed) max_speed, tw.name
#p.*, sd.*
FROM teams t
LEFT JOIN players p
ON p.team_id = t.id
LEFT JOIN stadiums s
ON s.id = t.stadium_id
LEFT JOIN towns tw
ON s.town_id = tw.id
LEFT JOIN skills_data sd
ON p.skills_data_id = sd.id
WHERE t.name != 'Devify'
GROUP BY tw.id
ORDER BY max_speed DESC, tw.name;


-- 9.	Total salaries and players by country
select cc.name,count(pl.id) as 'total_count_of_players', sum(pl.salary) as 'total_sum_of_salaries'
from fsd.players pl
join fsd.teams t on pl.team_id = t.id
join fsd.stadiums st on t.stadium_id = st.id
join fsd.towns tt on tt.id = st.town_id
right join countries cc on cc.id = tt.country_id
group by cc.id order by `total_count_of_players` desc, cc.name asc;

-- Section 4: Programmability – 30 pts
-- 10.	Find all players that play on stadium
delimiter //
create function udf_stadium_players_count(stadium_name VARCHAR(30)) 
returns int DETERMINISTIC
begin
	declare count_players int;
    set count_players := (select count(pl.id) 
    from fsd.players pl
    join fsd.teams t on pl.team_id = t.id
    join fsd.stadiums st on t.stadium_id = st.id
    where st.name = stadium_name
    );
    return count_players;
end
//
delimiter ;

SELECT udf_stadium_players_count ('Jaxworks') as `count`; 
SELECT udf_stadium_players_count ('Linklinks') as `count`; 
drop function udf_stadium_players_count;

set global log_bin_trust_function_creators = 0;
show variables;

-- 11.	Find good playmaker by teams
delimiter //
create procedure udp_find_playmaker(min_dribble_points int, team_name varchar(45))
begin
	select concat(pl.first_name,' ',pl.last_name) as 'full_name', 
    pl.age, pl.salary, sd.dribbling, sd.speed, t.name 
from fsd.players pl
join fsd.skills_data sd on sd.id = pl.skills_data_id
join fsd.teams t on t.id = pl.team_id 
where sd.dribbling > min_dribble_points and t.name = team_name
and sd.speed > (select AVG(sd.speed) from fsd.skills_data sd)
order by sd.speed desc limit 1;
end
//
delimiter ;

CALL udp_find_playmaker (20, 'Skyble');

-- select pl.first_name, pl.age, pl.salary, sd.dribbling, sd.speed, t.name 
-- from fsd.players pl
-- join fsd.skills_data sd on sd.id = pl.skills_data_id
-- join fsd.teams t on t.id = pl.team_id 
-- where sd.dribbling > 20 and t.name = 'Skyble'
-- and sd.speed > (select AVG(sd.speed) from fsd.skills_data sd)
-- order by sd.speed desc limit 1;