
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `project6` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `project6`;

CREATE TABLE `users`(
    `matricola` int(11),
    `password` varchar(255),
    `name` varchar(255),
    `lastname` varchar(255),
    `class` varchar(10),
     PRIMARY KEY(`matricola`)
);

CREATE TABLE `Grades`(
  `id` int NOT NULL AUTO_INCREMENT,
  `nomeStudente` varchar(255),
  `matricolaStudente`int(11),
  `nomeProfessore` varchar(255),
  `matricolaProfessore` int(11),
  `materia` varchar(255),
  `voto` int(11),
  `tipo` varchar(255),
  `data` date,
   PRIMARY KEY(`id`)
);

CREATE TABLE `Professor` (
  `matricola` int(11),
  `password` varchar(255),
  `name` varchar(255),
  `lastname` varchar(255),
   PRIMARY KEY(`matricola`)

);

CREATE TABLE `Assenza`(
  `id` int NOT NULL AUTO_INCREMENT,
  `matricolaStudente` int(11),
  `data` date,
  `tipo` varchar(255),
  `checkbit` int,
   PRIMARY KEY(`id`)
);



CREATE TABLE `Homework`(
  `id` int NOT NULL AUTO_INCREMENT,
  `matricolaProfessore` int(11),
  `class` varchar(10),
  `materia` varchar(255),
  `descrizione` varchar(255),
  `data` date,
  PRIMARY KEY(`id`)
);

INSERT INTO `users` (`matricola`,`password`,`name`,`lastname`,`class`) VALUES
(1234,"password","Alberto","Amicomio","3B"),
(1235,"password","Sara","Costruttore","3B"),
(1236,"password","Vanessa","Opportuna","3B"),
(1237,"password","Michela","Articuno","3B"),
(1238,"password","Giuseppe","Barbiere","3B"),
(1239,"password","Daniele","Zapdos","3B"),
(1240,"password","Lucia","Portamivia","3B"),
(1241,"password","Paoletto","Fisico","3B"),
(1242,"password","Paoletto","Capellone","3B"),
(1243,"password","Salvatore","Ruspberry","3B"),
(1244,"password","Silvano","Medio","3B"),
(1245,"password","Luca","Zammariello","3B"),
(1246,"password","Alberto","Micio","4B"),
(1247,"password","Riccardo","Carpinella","4B"),
(1248,"password","Andrea","De Paolis","4B"),
(1249,"password","Alberto","Menichelli","4B"),
(1250,"password","Giapo","Colagrossi","4B"),
(1251,"password","Fabrizio","Cola","4B"),
(1252,"password","Stefan","Huma","4B"),
(1253,"password","Federico","Pescatore","4B"),
(1254,"password","Riccardo","Alberti","4B"),
(1255,"password","Silvestro","Rosso","4B");


INSERT INTO `Professor` (`matricola`,`password`,`name`,`lastname`) VALUES
(9999,"pollo","Albertone","Macchina"),
(9998,"pollo","Lucone","Albergo"),
(9997,"pollo","Poseidone","Nuotatore");


INSERT INTO `Grades` (`nomeStudente`,`matricolaStudente`,`matricolaProfessore`,`nomeProfessore`,`materia`,`voto`,`tipo`,`data`) VALUES
("Amicomio", 1234, 9999, "Albertone","Geografia","8","scritto","2020-1-9"),
("Amicomio", 1234, 9999, "Albertone","Geografia","7","orale","2020-1-3"),
("Amicomio", 1234, 9999, "Albertone","Storia","5","orale","2020-1-5"),
("Fisico", 1241, 9999, "Albertone","Storia","5","orale","2020-1-5"),
("Colagrossi",1250,9997,"Poseidone","Matematica","10","scritto","2020-1-9");

INSERT INTO `Assenza` (`matricolaStudente`,`data`,`tipo`,`checkbit`) VALUES
(1234,"2020-1-11","assenza",1),
(1237,"2020-1-10","assenza",1),
(1235,"2020-1-10","assenza",1),
(1238,"2020-1-10","assenza",1),
(1234,"2020-1-12","assenza",1),
(1239,"2020-1-10","assenza",1),
(1234,"2020-1-10","assenza",1),
(1234,"2020-1-14","assenza",1);
