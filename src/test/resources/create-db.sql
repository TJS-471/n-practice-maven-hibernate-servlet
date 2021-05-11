

CREATE SCHEMA IF NOT EXISTS `testdb-unit`;

use `testdb-unit`;

SET FOREIGN_KEY_CHECKS = 0;



CREATE TABLE IF NOT EXISTS `roles_tb` (
`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(250),
PRIMARY KEY (`id`)
);



CREATE TABLE IF NOT EXISTS `users_tb` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`login` VARCHAR(250),
`email` VARCHAR(255),
`password` VARCHAR(255),
`first_name` VARCHAR(255),
`last_name` VARCHAR(255),
`birth_date` DATE,
`role_id` BIGINT,
PRIMARY KEY (`id`),
CONSTRAINT `role_id` FOREIGN KEY(`role_id`) REFERENCES `roles_tb`(`id`)  ON DELETE NO ACTION ON UPDATE CASCADE
);

