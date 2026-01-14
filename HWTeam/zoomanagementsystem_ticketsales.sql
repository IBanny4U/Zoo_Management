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
-- Table structure for table `ticketsales`
--

DROP TABLE IF EXISTS `ticketsales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticketsales` (
  `Number` int NOT NULL AUTO_INCREMENT,
  `Male` int NOT NULL,
  `Female` int NOT NULL,
  `Adult` int NOT NULL,
  `Child` int NOT NULL,
  `Tour` varchar(45) NOT NULL,
  `Transport` varchar(45) NOT NULL,
  `Transport_Quantity` int NOT NULL,
  `TotalPrice` varchar(45) NOT NULL,
  `Sale_Date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Number`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticketsales`
--

LOCK TABLES `ticketsales` WRITE;
/*!40000 ALTER TABLE `ticketsales` DISABLE KEYS */;
INSERT INTO `ticketsales` VALUES (1,4,2,4,2,'Guided','Shuttle Bus',2,'510.0','2025-08-20 04:59:36'),(2,2,0,1,1,'None','Walking',2,'75.0','2025-08-20 05:02:00'),(3,3,0,0,3,'None','Walking',3,'75.0','2025-08-20 05:03:16'),(4,2,0,2,0,'None','Walking',2,'100.0','2025-08-20 05:04:43'),(5,0,4,4,0,'Guided','Walking',4,'300.0','2025-08-20 05:05:09'),(6,2,1,2,1,'None','Walking',3,'125.0','2025-08-20 05:12:55'),(15,6,3,2,7,'None','Bicycle',8,'355.0','2025-08-20 05:18:06'),(16,2,3,0,5,'Guided','Shuttle Bus',1,'305.0','2025-08-20 06:25:15'),(17,1,3,4,0,'None','Buggy',2,'320.0','2025-08-20 06:26:45'),(18,0,4,3,1,'Guided','Walking',4,'275.0','2025-08-20 06:30:16'),(19,0,13,0,13,'Guided','Walking',13,'425.0','2025-08-20 06:33:50'),(20,0,5,4,1,'None','Buggy',2,'345.0','2025-08-20 06:35:31'),(21,1,1,2,0,'Guided','Shuttle Bus',1,'280.0','2025-08-21 05:06:12'),(22,2,1,2,1,'None','Walking',3,'125.0','2025-08-21 05:06:49'),(23,2,3,3,2,'Guided','Shuttle Bus',2,'29000.0','2025-08-21 05:51:24'),(24,4,-1,0,4,'None','Walking',4,'4000.0','2025-08-21 17:55:32'),(25,3,3,3,3,'None','Bicycle',2,'11000.0','2025-08-21 18:40:51'),(26,3,4,4,3,'None','Walking',7,'11000.0','2025-08-21 18:55:50'),(27,1,2,2,1,'None','Bicycle',5,'10000.0','2025-08-22 01:04:49'),(28,3,5,4,4,'None','Walking',8,'12000.0','2025-08-23 01:51:44'),(29,2,2,3,1,'None','Bicycle',2,'9000.0','2025-08-23 01:52:03'),(30,2,1,0,3,'Guided','Shuttle Bus',1,'16000.0','2025-08-23 01:52:18'),(31,3,1,0,4,'None','Buggy',1,'10000.0','2025-08-23 01:52:36'),(32,6,8,0,14,'Guided','Bicycle',9,'28000.0','2025-08-23 01:52:53'),(33,1,3,3,1,'Guided','Buggy',2,'24000.0','2025-08-23 11:28:12'),(34,0,5,0,5,'None','Walking',5,'5000.0','2025-08-23 11:29:18');
/*!40000 ALTER TABLE `ticketsales` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-02 22:14:08
