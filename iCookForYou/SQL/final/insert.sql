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
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `TAllergy`
--

LOCK TABLES `TAllergy` WRITE;
/*!40000 ALTER TABLE `TAllergy` DISABLE KEYS */;
INSERT INTO `TAllergy` VALUES (1,'Céréales contenant du gluten'),(2,'Fruits à coque'),(3,'Crustacés'),(4,'Céleri '),(5,'Œufs'),(6,'Moutarde'),(7,'Poissons'),(8,'Soja'),(9,'Lait'),(10,'Graines de sésame'),(11,'Lupin'),(12,'Arachides'),(13,'Mollusques ');
/*!40000 ALTER TABLE `TAllergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TDiet`
--

LOCK TABLES `TDiet` WRITE;
/*!40000 ALTER TABLE `TDiet` DISABLE KEYS */;
INSERT INTO `TDiet` VALUES (1,'Végan'),(2,'Végétarien'),(3,'Sans gluten');
/*!40000 ALTER TABLE `TDiet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TFood`
--

LOCK TABLES `TFood` WRITE;
/*!40000 ALTER TABLE `TFood` DISABLE KEYS */;
INSERT INTO `TFood` VALUES (1,'sucre',2,NULL),(2,'céréale',2,'sansgluten'),(3,'biscotte',3,'sansgluten'),(4,'miel',2,'vegan'),(5,'chocolat',2,NULL),(6,'huile',1,NULL),(7,'vinaigre',1,NULL),(8,'mayonnaise',2,'vegan'),(9,'moutarde',2,'vegan'),(10,'farine',2,'sansgluten'),(11,'pommes de terre',2,NULL),(12,'pâtes',2,'sansgluten'),(13,'riz',2,NULL),(14,'soupe',1,NULL),(15,'lentilles',2,NULL),(16,'poisson',3,'vegetarien'),(17,'lait',1,'vegan'),(18,'semoule',2,'sansgluten'),(19,'beurre',2,'vegan'),(20,'œuf',3,'vegan'),(21,'fromage',2,'vegan'),(22,'jambon',3,'vegetarien'),(23,'rillettes',2,'vegetarien'),(24,'saucisse',3,'vegetarien'),(25,'steack haché',3,'vegetarien'),(26,'glace',2,'vegan'),(27,'pain',3,'sansgluten'),(28,'eau',1,NULL),(29,'pastis',1,NULL),(30,'bière',1,NULL),(31,'pomme',3,NULL),(32,'banane',3,NULL),(33,'orange',3,NULL),(34,'clémentine',3,NULL),(35,'pêche',3,NULL),(36,'poire',3,NULL),(37,'raisin',3,NULL),(38,'pamplemousse',3,NULL),(39,'kiwi',3,NULL),(40,'fraise',3,NULL),(41,'tomate',3,NULL),(42,'carotte',3,NULL),(43,'melon',3,NULL),(44,'endive',3,NULL),(45,'salade',3,NULL),(46,'courgette',3,NULL),(47,'oignon',3,NULL),(48,'concombre',3,NULL),(49,'poireau',3,NULL),(50,'choux-fleur',3,NULL),(51,'sel',4,NULL),(52,'poivre',4,NULL),(53,'parmesan',2,NULL),(54,'oignon',3,NULL),(55,'crème fraîche',2,NULL);
/*!40000 ALTER TABLE `TFood` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `THistory`
--

LOCK TABLES `THistory` WRITE;
/*!40000 ALTER TABLE `THistory` DISABLE KEYS */;
INSERT INTO `THistory` VALUES (1,'http://www.marmiton.org/recettes/recette_delice-a-la-banane_336131.aspx'),(1,'http://www.marmiton.org/recettes/recette_pates-au-fromage-jambon-poivron_334436.aspx'),(1,'http://www.marmiton.org/recettes/recette_pates-aux-3-fromages_21235.aspx'),(1,'http://www.marmiton.org/recettes/recette_roules-fromage-pesto_344580.aspx'),(1,'http://www.marmiton.org/recettes/recette_tarte-au-fromage-a-la-suisse_11045.aspx'),(9,'http://www.marmiton.org/recettes/recette_pates-etoiles-au-fromage-fondu_19560.aspx');
/*!40000 ALTER TABLE `THistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TStock`
--

LOCK TABLES `TStock` WRITE;
/*!40000 ALTER TABLE `TStock` DISABLE KEYS */;
INSERT INTO `TStock` VALUES (1,12,1000),(1,22,2),(1,47,2),(1,51,NULL),(1,52,NULL),(1,53,120),(1,54,2),(2,6,1),(2,24,4),(2,25,2),(3,12,100),(3,21,50),(7,12,100),(7,21,50),(7,22,3),(7,32,6);
/*!40000 ALTER TABLE `TStock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TUnit`
--

LOCK TABLES `TUnit` WRITE;
/*!40000 ALTER TABLE `TUnit` DISABLE KEYS */;
INSERT INTO `TUnit` VALUES (1,'litre(s)'),(2,'gramme(s)'),(3,'unité(s)'),(4,' ');
/*!40000 ALTER TABLE `TUnit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TUser`
--

LOCK TABLES `TUser` WRITE;
/*!40000 ALTER TABLE `TUser` DISABLE KEYS */;
INSERT INTO `TUser` VALUES (1,'Pecqueux','nathan','nathan@gmail.com','nathan',65.00,178,22,'man','active'),(2,'Lebegue','Clement','clement@hotmail.fr','clement',80.00,190,25,'woman','athlete'),(3,'Bomy','François','fr@gmail.com','franfran',120.00,180,23,'man','sedentary'),(6,'rr','rr','ddgg@gmail.com','tt',NULL,NULL,NULL,NULL,NULL),(7,'demo','test','demo@gmail.com','demo',NULL,NULL,NULL,NULL,NULL),(9,'alan','alan','alan@alan.fr','alan',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `TUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TUserAllergy`
--

LOCK TABLES `TUserAllergy` WRITE;
/*!40000 ALTER TABLE `TUserAllergy` DISABLE KEYS */;
INSERT INTO `TUserAllergy` VALUES (2,1),(1,2),(9,2),(1,5),(7,6),(9,6),(3,7),(1,8),(3,11);
/*!40000 ALTER TABLE `TUserAllergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TUserDiet`
--

LOCK TABLES `TUserDiet` WRITE;
/*!40000 ALTER TABLE `TUserDiet` DISABLE KEYS */;
INSERT INTO `TUserDiet` VALUES (1,2),(2,2),(7,2),(9,2);
/*!40000 ALTER TABLE `TUserDiet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-28 11:46:09
