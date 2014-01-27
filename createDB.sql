delimiter $$

CREATE DATABASE `mo` /*!40100 DEFAULT CHARACTER SET utf8 */$$

delimiter $$

CREATE TABLE `authors` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` char(30) NOT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `clients` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` char(30) NOT NULL,
  `surname` char(30) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `banned` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `employees` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` char(30) NOT NULL,
  `surname` char(30) NOT NULL,
  `pesel` int(11) NOT NULL,
  `login` varchar(20) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `genres` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `items` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` char(30) NOT NULL,
  `genre_id` mediumint(9) DEFAULT NULL,
  `author_id` mediumint(9) DEFAULT NULL,
  `type_id` mediumint(9) DEFAULT NULL,
  `iban` char(30) DEFAULT NULL,
  `issue_nb` char(30) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `itemtype` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` char(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `orders` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `item_id` mediumint(9) NOT NULL,
  `client_id` mediumint(9) NOT NULL,
  `lend_date` varchar(30) DEFAULT NULL,
  `return_date` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8$$




