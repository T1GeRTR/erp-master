DROP DATABASE IF EXISTS erp;

CREATE DATABASE `erp`;

USE `erp`;

CREATE TABLE `department` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(50)NOT NULL,
`deleted` boolean NOT NULL DEFAULT FALSE,
PRIMARY KEY(id),
KEY name(name),
KEY deleted(deleted)
)ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `position` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(50)NOT NULL,
`departmentId` int(11)NOT NULL,
`deleted` boolean NOT NULL DEFAULT FALSE,
PRIMARY KEY(id),
KEY name(name),
FOREIGN KEY(departmentId)REFERENCES department(id) ON DELETE CASCADE,
KEY deleted(deleted)
)ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `user` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
`firstName` varchar(50)NOT NULL,
`lastName` varchar(50)NOT NULL,
`email` varchar(50)NOT NULL,
`password` varchar(100)NOT NULL,
`deleted` boolean NOT NULL DEFAULT FALSE,
`saved` boolean NOT NULL DEFAULT FALSE,
`role` varchar(50)NOT NULL,
PRIMARY KEY(id),
KEY firstName(firstName),
KEY lastName(lastName),
KEY email(email),
KEY password(password),
KEY deleted(deleted),
KEY saved(saved)
)ENGINE = INNODB DEFAULT CHARSET = utf8
;

CREATE TABLE `session` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
`sessionId` varchar(50)NOT NULL,
`userId` int(11)NOT NULL,
PRIMARY KEY(id),
UNIQUE KEY sessionId(sessionId),
FOREIGN KEY(userId)REFERENCES `user`(id) ON DELETE CASCADE
)ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `project` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(50)NOT NULL,
`gipId` int(11)NOT NULL,
`userId` int(11)NOT NULL,
PRIMARY KEY(id),
UNIQUE KEY name(name),
FOREIGN KEY(gipId)REFERENCES `user`(id) ON DELETE CASCADE,
FOREIGN KEY(userId)REFERENCES `user`(id) ON DELETE CASCADE
)ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `task` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(50)NOT NULL,
`gipId` int(11)NOT NULL,
`userId` int(11)NOT NULL,
`projectId` int(11)NOT NULL,
PRIMARY KEY(id),
UNIQUE KEY name(name),
FOREIGN KEY(gipId)REFERENCES `user`(id) ON DELETE CASCADE,
FOREIGN KEY(userId)REFERENCES `user`(id) ON DELETE CASCADE,
FOREIGN KEY(projectId)REFERENCES `project`(id) ON DELETE CASCADE
)ENGINE = INNODB DEFAULT CHARSET = utf8
;

CREATE TABLE `user_hours` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
`userId` int(11)NOT NULL,
`date` date NOT NULL,
`hours` float(11)NOT NULL,
`taskId` int(11)NOT NULL,
`taskTitle` varchar(200)NOT NULL,
`projectId` int(11)NOT NULL,
`projectTitle` varchar(200)NOT NULL,
`deleted` boolean NOT NULL DEFAULT FALSE,
PRIMARY KEY(id),
FOREIGN KEY(userId)REFERENCES `user`(id) ON DELETE CASCADE,
KEY `date`(date),
KEY `hours`(hours),
KEY `taskId`(taskId),
KEY `taskTitle`(taskTitle),
KEY `projectId`(taskId),
KEY `projectTitle`(taskTitle),
KEY deleted(deleted)
)ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `hours` (
	`id` int(11) NOT NULL,
`userId` int(11)NOT NULL,
`date` date NOT NULL,
`hours` varchar(50) NOT NULL,
`saved` boolean NOT NULL DEFAULT FALSE,
`deleted` boolean NOT NULL DEFAULT FALSE,
`type` int(11) NOT NULL DEFAULT 1,
PRIMARY KEY(id),
FOREIGN KEY(userId)REFERENCES `user`(id) ON DELETE CASCADE,
KEY `date`(date),
KEY `hours`(hours),
KEY saved(saved),
KEY deleted(deleted),
KEY type (type)
)ENGINE = INNODB DEFAULT CHARSET = utf8;

INSERT INTO user (id, firstname, lastname, email, password, deleted, role) VALUES (1, 'firstname', 'lastname', 'email', 'password', 1, 'ROLE_ADMIN');
INSERT INTO hours (id, userId, date, hours, saved) VALUES (900000000, 1, '2000-01-01', '8.0', 1);
