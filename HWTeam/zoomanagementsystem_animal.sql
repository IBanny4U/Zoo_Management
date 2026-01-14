-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: zoomanagementsystem
-- ------------------------------------------------------
-- Server version	9.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal` (
  `idanimal` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `age` int NOT NULL,
  `gender` varchar(45) NOT NULL,
  `Species` varchar(45) NOT NULL,
  `enclosure` varchar(45) NOT NULL,
  `healthstats` varchar(45) NOT NULL,
  PRIMARY KEY (`idanimal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` VALUES ('HW2581003906','Asian Elephant','Elephas maximus',10,'Male','Herbivores','Center','Recovering'),('HW25810184613','Bengal Tiger','Panthera tigris tigris',1,'Male','Carnivore','Center','Normal'),('HW2582223023','African Lion','Panthera leo',4,'Male','Carnivore','East','Normal'),('HW25822231040','Western Hoolock Gibbon','Hoolock Hoolock',1,'Male','Herbivores','Northeast','Normal'),('HW25822231125','White Tiger','Panthera tigris tigris',3,'Female','Carnivore','Southeast','Normal'),('HW25822231246','Malayan Tapir','Tapirus indicus',3,'Male','Carnivore','South','Normal'),('HW25822231423','Red muntjac','Muntiacus muntjak',1,'Female','Herbivores','East','Sick'),('HW25822231747','Red-crested turaco','Tauraco erythrolophus',1,'Female','Herbivores','Southwest','Normal'),('HW25822231838','Hornbill','Bucerotidae',2,'Male','Herbivores','Northeast','Injure'),('HW25822232055','Tuatara','Sphenodon',1,'Male','Carnivore','North','Pregnant'),('HW25822232247','Saltwater Crocodile','Crocodylus Porosus',1,'Female','Herbivores','Northwest','Pregnant'),('HW25822232352','Ladder Snake','Rhinechis scalaris',1,'Female','Carnivore','Northwest','Pregnant'),('HW25822232451','Common Box Turtle','Terrapene carolina',1,'Female','Herbivores','Southwest','Recovering'),('HW25822232611','Dhole','Cuon alpinus',2,'Female','Omnivores','Southwest','Breeding'),('HW25822232723','Wombat ','Vombatus ursinus',2,'Male','Herbivores','North','Recovering'),('HW25822233330','Koala','Phascolarctos cinereus',2,'Female','Herbivores','South','Pregnant'),('HW2582223615','Asiatic Black Bear','Ursus Thibetanus',4,'Male','Omnivores','East','Normal'),('HW2582223727','Star Tortoise','Geochelone platynota',4,'Female','Herbivores','South','Normal'),('HW2582223858','Binturong','Arctictis binturong',3,'Female','Herbivores','South','Normal'),('HW2582223953','White Rhinoceros','Ceratotherium simum',8,'Male','Herbivores','Northeast','Normal');
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-02 22:14:06
