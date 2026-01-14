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
-- Table structure for table `dayshift`
--

DROP TABLE IF EXISTS `dayshift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dayshift` (
  `Duty_id` int NOT NULL AUTO_INCREMENT,
  `Staff_Name` varchar(45) NOT NULL,
  `Department` varchar(45) NOT NULL,
  `Time` varchar(45) NOT NULL,
  PRIMARY KEY (`Duty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dayshift`
--

LOCK TABLES `dayshift` WRITE;
/*!40000 ALTER TABLE `dayshift` DISABLE KEYS */;
INSERT INTO `dayshift` VALUES (1,'IBanny','Animal Management','8 a.m - 3 p.m'),(8,'Alita','Health Department','9 a.m - 1 p.m'),(10,'Gwen','Visitor Management','9 a.m - 12 p.m'),(12,'Mint','Inventory Management','5 a.m - 6 a.m'),(13,'Minku','Animal Management','5 a.m - 12 p.m'),(14,'Rainbow','Inventory Management','6 a.m - 11 a.m'),(16,'ling','Inventory Management','5 a.m - 1 p.m'),(17,'Yue','Visitor Management','7 a.m - 3 p.m'),(18,'Ryan ','Health Department','8 a.m - 1 p.m'),(19,'Zaw Zaw','Animal Management','8 a.m - 1 p.m'),(21,'ling','Visitor Management','10 a.m - 11 a.m');
/*!40000 ALTER TABLE `dayshift` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-02 22:14:10
