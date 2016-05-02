CREATE DATABASE  IF NOT EXISTS `proyectodaw` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ProyectoDAW`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: ProyectoDAW
-- ------------------------------------------------------
-- Server version	5.5.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Alumnos`
--

DROP TABLE IF EXISTS `Alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Alumnos` (
  `matricula` varchar(9) NOT NULL,
  `password` varchar(16) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `mail` varchar(30) NOT NULL,
  PRIMARY KEY (`matricula`),
  UNIQUE KEY `matricula` (`matricula`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Alumnos`
--

LOCK TABLES `Alumnos` WRITE;
/*!40000 ALTER TABLE `Alumnos` DISABLE KEYS */;
INSERT INTO `Alumnos` VALUES ('A00819344','alumno','Jesus Andres Valdez','8116022437','jesus.c@hotmail.com'),('A01336955','Lalo_1994','Alan Gustavo Valdez Cascajares','8115551786','alan.gvaldez@gmail.com');
/*!40000 ALTER TABLE `Alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AlumnosCursos`
--

DROP TABLE IF EXISTS `AlumnosCursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AlumnosCursos` (
  `matricula` varchar(9) NOT NULL,
  `idCurso` int(11) NOT NULL,
  PRIMARY KEY (`matricula`,`idCurso`),
  CONSTRAINT `alumnoscursos_ibfk_1` FOREIGN KEY (`matricula`) REFERENCES `Alumnos` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AlumnosCursos`
--

LOCK TABLES `AlumnosCursos` WRITE;
/*!40000 ALTER TABLE `AlumnosCursos` DISABLE KEYS */;
/*!40000 ALTER TABLE `AlumnosCursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cursos`
--

DROP TABLE IF EXISTS `Cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cursos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `claveMateria` varchar(8) NOT NULL,
  `numeroGrupo` int(11) NOT NULL,
  `horarioID` int(11) NOT NULL,
  `salon` varchar(20) NOT NULL,
  `ingles` tinyint(1) DEFAULT NULL,
  `honors` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `claveMateria` (`claveMateria`),
  KEY `horarioID` (`horarioID`),
  KEY `salon` (`salon`),
  CONSTRAINT `cursos_ibfk_1` FOREIGN KEY (`claveMateria`) REFERENCES `Materias` (`clave`),
  CONSTRAINT `cursos_ibfk_2` FOREIGN KEY (`claveMateria`) REFERENCES `Materias` (`clave`),
  CONSTRAINT `cursos_ibfk_3` FOREIGN KEY (`horarioID`) REFERENCES `Horarios` (`id`),
  CONSTRAINT `cursos_ibfk_4` FOREIGN KEY (`salon`) REFERENCES `Salones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cursos`
--

LOCK TABLES `Cursos` WRITE;
/*!40000 ALTER TABLE `Cursos` DISABLE KEYS */;
INSERT INTO `Cursos` VALUES (23,'TC-1002',3,7,'A3-101',0,0),(24,'TC-1003',1,1,'A3-102',0,0);
/*!40000 ALTER TABLE `Cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Horarios`
--

DROP TABLE IF EXISTS `Horarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Horarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `horario` varchar(12) NOT NULL,
  `hora_inicio` decimal(6,2) NOT NULL,
  `hora_fin` decimal(6,2) NOT NULL,
  `duracion` decimal(6,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Horarios`
--

LOCK TABLES `Horarios` WRITE;
/*!40000 ALTER TABLE `Horarios` DISABLE KEYS */;
INSERT INTO `Horarios` VALUES (1,'7+/3 LuJu',7.50,9.00,1.50),(2,'7+/3 MaVi',7.50,9.00,1.50),(3,'7+/5 Mi',7.50,10.50,3.00),(4,'9/3 LuJu',9.00,10.50,1.50),(5,'9/3 MaVi',9.00,10.50,1.50),(6,'10+/3 LuJu',10.50,12.00,1.50),(7,'10+/3 MaVi',10.50,12.00,1.50),(8,'10+/6 mi',10.50,13.50,3.00),(9,'12/3 LuJu',12.00,13.50,1.50),(10,'12/3 MaVi',12.00,13.50,1.50),(11,'13+/3 LuJu',13.50,15.00,1.50),(12,'13+/3 MaVi',13.50,15.00,1.50),(13,'15/3 LuJu',15.00,16.50,1.50),(14,'15/3 MaVi',15.00,16.50,1.50),(15,'15/6 Mi',15.00,18.00,3.00),(16,'16+/3 LuJu',16.50,18.00,1.50),(17,'16+/3 MaVi',16.50,18.00,1.50),(18,'18/6 Lu',18.00,21.00,3.00),(19,'18/6 Ma',18.00,21.00,3.00),(20,'18/6 Mi',18.00,21.00,3.00),(21,'18/6 Ju',18.00,21.00,3.00),(22,'18/6 Vi',18.00,21.00,3.00);
/*!40000 ALTER TABLE `Horarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MaestroCurso`
--

DROP TABLE IF EXISTS `MaestroCurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MaestroCurso` (
  `nomina` varchar(9) NOT NULL,
  `idCurso` int(11) NOT NULL,
  `porcentaje` int(11) DEFAULT NULL,
  PRIMARY KEY (`nomina`,`idCurso`),
  CONSTRAINT `maestrocurso_ibfk_1` FOREIGN KEY (`nomina`) REFERENCES `maestros` (`nomina`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MaestroCurso`
--

LOCK TABLES `MaestroCurso` WRITE;
/*!40000 ALTER TABLE `MaestroCurso` DISABLE KEYS */;
INSERT INTO `MaestroCurso` VALUES ('L01234567',21,100),('L01234567',22,100),('L01234567',23,100),('L01234567',24,100);
/*!40000 ALTER TABLE `MaestroCurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Maestros`
--

DROP TABLE IF EXISTS `Maestros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Maestros` (
  `nomina` varchar(10) NOT NULL,
  `password` varchar(16) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `mail` varchar(30) NOT NULL,
  `cursosImpartidos` int(11) DEFAULT NULL,
  PRIMARY KEY (`nomina`),
  UNIQUE KEY `nomina` (`nomina`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Maestros`
--

LOCK TABLES `Maestros` WRITE;
/*!40000 ALTER TABLE `Maestros` DISABLE KEYS */;
INSERT INTO `Maestros` VALUES ('L01234567','admin','Raul Perez','8134567890','raul.perez@itesm.mx',0),('L01336955','admin','Alan Valdez','8116022437','alan@itesm.mx',0);
/*!40000 ALTER TABLE `Maestros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Materias`
--

DROP TABLE IF EXISTS `Materias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Materias` (
  `clave` varchar(8) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  PRIMARY KEY (`clave`),
  UNIQUE KEY `clave` (`clave`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materias`
--

LOCK TABLES `Materias` WRITE;
/*!40000 ALTER TABLE `Materias` DISABLE KEYS */;
INSERT INTO `Materias` VALUES ('TC-1003','Redes 1'),('TC-1002','Redes 2');
/*!40000 ALTER TABLE `Materias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Salones`
--

DROP TABLE IF EXISTS `Salones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Salones` (
  `id` varchar(20) NOT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `administracion` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Salones`
--

LOCK TABLES `Salones` WRITE;
/*!40000 ALTER TABLE `Salones` DISABLE KEYS */;
INSERT INTO `Salones` VALUES ('A3-101',35,'CS'),('A3-102',40,'Ninguna'),('A4-101',40,'Ninguna');
/*!40000 ALTER TABLE `Salones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarioslab`
--

DROP TABLE IF EXISTS `horarioslab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horarioslab` (
  `claveMateria` varchar(8) NOT NULL,
  `idHorario` int(11) NOT NULL,
  PRIMARY KEY (`claveMateria`,`idHorario`),
  KEY `idHorario` (`idHorario`),
  CONSTRAINT `horarioslab_ibfk_2` FOREIGN KEY (`idHorario`) REFERENCES `Horarios` (`id`),
  CONSTRAINT `horarioslab_ibfk_1` FOREIGN KEY (`claveMateria`) REFERENCES `Materias` (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarioslab`
--

LOCK TABLES `horarioslab` WRITE;
/*!40000 ALTER TABLE `horarioslab` DISABLE KEYS */;
/*!40000 ALTER TABLE `horarioslab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ProyectoDAW'
--

--
-- Dumping routines for database 'ProyectoDAW'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-01 23:45:06
