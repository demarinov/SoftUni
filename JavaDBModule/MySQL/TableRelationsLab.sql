-- 1. Mountains and Peaks
create table if not exists camp.mountains(

	id int not null primary key auto_increment,
    name varchar(50) not null
);


create table if not exists camp.peaks (
	id int not null primary key auto_increment,
    name varchar(50) not null,
    mountain_id int not null,
    CONSTRAINT fk_peaks_mountains FOREIGN KEY (mountain_id) 
    references camp.mountains(id)
);

-- -- 2. Trip Organization
select V.driver_id, V.vehicle_type, concat(C.first_name,' ',C.last_name) as driver_name from
camp.vehicles V join camp.campers C on V.driver_id = C.id;

-- 3.SoftUni Hiking
select R.starting_point, R.end_point, R.leader_id, concat(C.first_name,' ',last_name) 
as leader_name
from camp.routes R join camp.campers C on R.leader_id = C.id;

-- 4.Delete Mountains
drop table camp.peaks;
drop table camp.mountains;


create table if not exists camp.mountains(

	id int not null primary key auto_increment,
    name varchar(50) not null
);

create table if not exists camp.peaks (
	id int not null primary key auto_increment,
    name varchar(50) not null,
    mountain_id int not null,
    CONSTRAINT fk_peaks_mountains FOREIGN KEY (mountain_id) 
    references camp.mountains(id) ON DELETE CASCADE
);

insert into camp.mountains values(1, 'Rila');
insert into camp.peaks values(1,'Musala',1);

delete from camp.mountains;


alter table camp.clients drop foreign key fk_clients_projects;
alter table camp.projects drop foreign key fk_projects_clients;
alter table camp.projects drop foreign key fk_projects_employees;

-- 5. Project Management DB*
create table if not exists camp.clients (
	id int not null PRIMARY KEY AUTO_INCREMENT,
    client_name varchar(100) not null,
    project_id int(11) null
    
);



create table if not exists camp.projects(
	id int not null PRIMARY KEY AUTO_INCREMENT,
    client_id int(11) null,
    project_lead_id int(11) null
    
);



alter table camp.clients add CONSTRAINT fk_clients_projects foreign key (project_id)
    references camp.projects(id);

alter table camp.projects add CONSTRAINT fk_projects_clients foreign key (client_id) 
    references camp.clients(id);

create table if not exists camp.employees (
	id int not null primary key auto_increment,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    project_id int(11) null,
    CONSTRAINT fk_employees_projects_projects foreign key (project_id) 
    references camp.projects (id)
);

alter table camp.projects add CONSTRAINT fk_projects_employees foreign key (project_lead_id) 
    references camp.employees (id);

-- create table if not exists camp.clients_projects (
-- 	client_id int,
--     project_id int,
--     PRIMARY KEY (client_id, project_id),
--     CONSTRAINT fk_clients_projects_clients
--     FOREIGN KEY (client_id)
--     references camp.clients (id),
--     CONSTRAINT fk_clients_projects_projects
--     FOREIGN KEY (project_id)
--     references camp.projects (id)
-- );



-- create table if not exists camp.employees_projects (
-- 	project_id int,
--     project_lead_id int,
--     
--     PRIMARY KEY (project_id,  project_lead_id),
--     CONSTRAINT fk_employees_projects_employees
--     FOREIGN KEY (project_lead_id)
--     references camp.employees (id),
--     CONSTRAINT fk_employees_projects_projects
--     FOREIGN KEY (project_id)
--     references camp.projects (id)
-- );