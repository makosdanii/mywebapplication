drop table if exists users;
drop table if exists roles;
CREATE TABLE if not exists ROLES(
	id int primary key auto_increment,
	role varchar(50) not null unique);

INSERT INTO ROLES(role) VALUES ("admin");
INSERT INTO ROLES(role) VALUES ("user");

CREATE TABLE if not exists USERS(
	email varchar(100) primary key,
	firstname varchar(100) not null,
	lastname varchar(100) not null,
	address varchar(100),
	roleid int not null,
	foreign key(roleid) REFERENCES ROLES(id));

INSERT INTO USERS (email, firstname, lastname, roleid) 
VALUES ("admin@mydomain.com", "ad", "min", 1);

-- select * from users;
-- select * from roles;