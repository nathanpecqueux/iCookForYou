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
-- Table structure for table `TFood`
--

DROP TABLE IF EXISTS `TFood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TFood` (
  `idFood` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `idUnitF` int(11) NOT NULL,
  PRIMARY KEY (`idFood`),
  KEY `fk_idUnitF_idx` (`idUnitF`),
  CONSTRAINT `fk_idUnitF` FOREIGN KEY (`idUnitF`) REFERENCES `TUnit` (`idUnit`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TFood`
--

LOCK TABLES `TFood` WRITE;
/*!40000 ALTER TABLE `TFood` DISABLE KEYS */;
INSERT INTO `TFood` VALUES (1,'sucre',2),(2,'céréale',2),(3,'biscotte',3),(4,'miel',2),(5,'chocolat',2),(6,'huile',1),(7,'vinaigre',1),(8,'mayonnaise',2),(9,'moutarde',2),(10,'farine',2),(11,'pommes de terre',2),(12,'pâtes',2),(13,'riz',2),(14,'soupe',1),(15,'lentilles',2),(16,'poisson',3),(17,'lait',1),(18,'semoule',2),(19,'beurre',2),(20,'œuf',3),(21,'fromage',2),(22,'jambon',3),(23,'rillettes',2),(24,'saucisse',3),(25,'steack haché',3),(26,'glace',2),(27,'pain',3),(28,'eau',1),(29,'pastis',1),(30,'bière',1),(31,'pomme',3),(32,'banane',3),(33,'orange',3),(34,'clémentine',3),(35,'pêche',3),(36,'poire',3),(37,'raisin',3),(38,'pamplemousse',3),(39,'kiwi',3),(40,'fraise',3),(41,'tomate',3),(42,'carotte',3),(43,'melon',3),(44,'endive',3),(45,'salade',3),(46,'courgette',3),(47,'oignon',3),(48,'concombre',3),(49,'poireau',3),(50,'choux-fleur',3);
/*!40000 ALTER TABLE `TFood` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-07 11:01:14
