
CREATE DATABASE gamebar;

CREATE TABLE gamebar.`EMPLOYEES` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE gamebar.CATEGORIES (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE gamebar.PRODUCTS (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO gamebar.EMPLOYEES(id, first_name, last_name) 
VALUES (1, 'Buddy', 'Bob');

INSERT INTO gamebar.EMPLOYEES(id, first_name, last_name) 
VALUES (2, 'Brady', 'Bob');

INSERT INTO gamebar.EMPLOYEES(id, first_name, last_name) 
VALUES (3, 'Harvey', 'Bob');

ALTER TABLE gamebar.EMPLOYEES ADD COLUMN middle_name VARCHAR(50) NOT NULL;

ALTER TABLE gamebar.PRODUCTS ADD CONSTRAINT FK_PRODUCTS_CATEGORIES FOREIGN KEY (category_id)
REFERENCES gamebar.CATEGORIES (id);

ALTER TABLE gamebar.EMPLOYEES MODIFY COLUMN middle_name VARCHAR(100);

DROP DATABASE gamebar;




