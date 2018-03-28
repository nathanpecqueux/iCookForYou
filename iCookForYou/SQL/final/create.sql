-- MySQL dump 10.16  Distrib 10.1.26-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: icookforyou
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `TAllergy`
--

DROP TABLE IF EXISTS `TAllergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TAllergy` (
  `idAllergy` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idAllergy`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TDiet`
--

DROP TABLE IF EXISTS `TDiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TDiet` (
  `idDiet` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idDiet`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TFood`
--

DROP TABLE IF EXISTS `TFood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TFood` (
  `idFood` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `idUnitF` int(11) NOT NULL,
  `excluded` enum('vegan','vegetarien','sansgluten') DEFAULT NULL,
  PRIMARY KEY (`idFood`),
  KEY `fk_idUnitF_idx` (`idUnitF`),
  CONSTRAINT `fk_idUnitF` FOREIGN KEY (`idUnitF`) REFERENCES `TUnit` (`idUnit`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `THistory`
--

DROP TABLE IF EXISTS `THistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `THistory` (
  `idUserH` int(11) NOT NULL,
  `urlRecipe` varchar(150) NOT NULL,
  PRIMARY KEY (`idUserH`,`urlRecipe`),
  CONSTRAINT `fk_idUserH` FOREIGN KEY (`idUserH`) REFERENCES `TUser` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TStock`
--

DROP TABLE IF EXISTS `TStock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TStock` (
  `idUserS` int(11) NOT NULL,
  `idFood` int(11) NOT NULL,
  `quantityS` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUserS`,`idFood`),
  KEY `fk_idFood_idx` (`idFood`),
  CONSTRAINT `fk_idFoodS` FOREIGN KEY (`idFood`) REFERENCES `TFood` (`idFood`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUserS` FOREIGN KEY (`idUserS`) REFERENCES `TUser` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TUnit`
--

DROP TABLE IF EXISTS `TUnit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TUnit` (
  `idUnit` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idUnit`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TUser`
--

DROP TABLE IF EXISTS `TUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TUser` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `lastName` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `weight(kg)` decimal(5,2) DEFAULT NULL,
  `size(cm)` int(3) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `sex` enum('man','woman') DEFAULT NULL,
  `physicalActivity` enum('sedentary','active','athlete') DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TUserAllergy`
--

DROP TABLE IF EXISTS `TUserAllergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TUserAllergy` (
  `idUserUA` int(11) NOT NULL,
  `idAllergyUA` int(11) NOT NULL,
  PRIMARY KEY (`idUserUA`,`idAllergyUA`),
  KEY `fk_idAllergyUA_idx` (`idAllergyUA`),
  CONSTRAINT `fk_idAllergyUA` FOREIGN KEY (`idAllergyUA`) REFERENCES `TAllergy` (`idAllergy`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUserUA` FOREIGN KEY (`idUserUA`) REFERENCES `TUser` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TUserDiet`
--

DROP TABLE IF EXISTS `TUserDiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TUserDiet` (
  `idUserUD` int(11) NOT NULL AUTO_INCREMENT,
  `idDietUD` int(11) NOT NULL,
  PRIMARY KEY (`idUserUD`,`idDietUD`),
  KEY `fk_idDietUD_idx` (`idDietUD`),
  CONSTRAINT `fk_idDietUD` FOREIGN KEY (`idDietUD`) REFERENCES `TDiet` (`idDiet`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUserUD` FOREIGN KEY (`idUserUD`) REFERENCES `TUser` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-28 11:45:54
