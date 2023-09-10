CREATE DATABASE IF NOT EXISTS `house_finder` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


CREATE TABLE IF NOT EXISTS `house_finder`.`users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `house_finder`.`users`
(`id`,
`email`,
`name`,
`password`)
VALUES
(1,
'it21928@hua.gr',
'John Capocci',
'$2a$10$eAJr.5DSEh0UlGKEvDRMp.i.hDnIzWdo/Zf1reHVbO8.c8q6Uk0R.'),
(2,
'it21970@gmail.com',
'Aggelos Panagiwti',
'$2a$10$Dt/O1bp9NH2DUBsKJBq6huj.Pd6czU0HruS0lH2r.Rz8rHIZ0QVjG'),
(3,
'test@hua.gr',
'test test',
'$2a$10$/2ycRGWkopYCnZvnCMJoj.CtKePl8EgPBMny.rZF3Xaxwz48G6Ne.');


CREATE TABLE IF NOT EXISTS `house_finder`.`roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `house_finder`.`roles`
(`id`,
`name`)
VALUES
(1,
'ROLE_ADMIN'),
(2,
'ROLE_USER');


CREATE TABLE IF NOT EXISTS `house_finder`.`users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `house_finder`.`users_roles`
(`user_id`,
`role_id`)
VALUES
(1,
1),
(2,
2),
(3,
1);


CREATE TABLE IF NOT EXISTS `house_finder`.`listings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `area` varchar(255) DEFAULT NULL,
  `area_code` int DEFAULT NULL,
  `built_date` date NOT NULL,
  `floor` int NOT NULL,
  `price` double NOT NULL,
  `square_meters` int NOT NULL,
  `street` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb5kgkbggc40jyxeq28onih2x1` (`user_id`),
  CONSTRAINT `FKb5kgkbggc40jyxeq28onih2x1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `house_finder`.`listings`
(`id`,
`area`,
`area_code`,
`built_date`,
`floor`,
`price`,
`square_meters`,
`street`,
`user_id`)
VALUES
(1,
'Kifisia',
10495,
'1990-06-13',
2,
10000,
130,
'test 1',
1),
(2,
'Chalandri',
10249,
'1981-12-14',
1,
50670,
200,
'test 2',
1),
(3,
'Tavros',
14769,
'1990-05-14',
0,
709845,
147,
'test 3',
3);


CREATE TABLE IF NOT EXISTS `house_finder`.`photo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) DEFAULT NULL,
  `listing_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhhsr26321h9872a2r5dy5wa5d` (`listing_id`),
  CONSTRAINT `FKhhsr26321h9872a2r5dy5wa5d` FOREIGN KEY (`listing_id`) REFERENCES `listings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `house_finder`.`photo`
(`id`,
`filename`,
`listing_id`)
VALUES
(1,
'1694365568601_house_1_2.jpeg',
1),
(2,
'1694365568603_house_1.jpeg',
1),
(3,
'1694365614680_house_2.jpeg',
2),
(4,
'1694365705447_house_3.jpeg',
3);