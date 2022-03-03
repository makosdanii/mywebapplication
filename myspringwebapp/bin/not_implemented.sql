drop table if exists user_ref_role;
drop table if exists users;
drop table if exists roles;


CREATE TABLE if not exists ROLES(
	id int primary key auto_increment,
	rolename varchar(50) not null unique);

INSERT INTO ROLES(rolename) VALUES ("admin");
INSERT INTO ROLES(rolename) VALUES ("user");

CREATE TABLE if not exists USERS(
	id int primary key auto_increment,
	email varchar(100) not null unique,
	firstname varchar(100) not null,
	lastname varchar(100) not null,
	address varchar(100),
	password varchar(256) not null);
    
CREATE TABLE if not exists USER_REF_ROLE(
	user_id int primary key,
    role_id int not null,
    foreign key(user_id) references USERS(id),
    foreign key(role_id) references ROLES(id));

INSERT INTO USERS (email, firstname, lastname, password) 
VALUES ("admin@mydomain.com", "ad", "min","admin");
INSERT INTO USER_REF_ROLE (user_id, role_id) VALUES (1,1);
INSERT INTO USERS (email, firstname, lastname, password) 
VALUES ("user@mydomain.com", "us", "er","user");
INSERT INTO USER_REF_ROLE (user_id, role_id) VALUES (2,2);

-- select users.email, roles.rolename 
-- from users
-- INNER JOIN user_ref_role on user_ref_role.user_id = users.id
-- INNER JOIN roles on user_ref_role.role_id = roles.id 
-- where users.email like 'admin@mydomain.com';