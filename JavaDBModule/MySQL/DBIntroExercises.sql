use minions;

insert into minions.minions values(1,'Kevin', 15);
insert into minions.minions values(2,'Bob', 22);
insert into minions.minions values(3,'Steward', null);

select * from minions.minions;
select * from minions.towns;
insert into minions.towns(id,name) values (1,'Nice');

select name from minions.minions order by name ASC;

update minions.minions set age = 10 where name='Steward';

update minions.minions set age = age + 1;
update minions.minions set town_id = 1;