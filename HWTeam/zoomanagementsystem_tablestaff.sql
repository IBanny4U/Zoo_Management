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
-- Table structure for table `tablestaff`
--

DROP TABLE IF EXISTS `tablestaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tablestaff` (
  `NO` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `EmployedDate` varchar(45) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `Department` varchar(45) NOT NULL,
  `Role` varchar(45) NOT NULL,
  `Salary` varchar(45) NOT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `Acc_Password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`NO`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tablestaff`
--

LOCK TABLES `tablestaff` WRITE;
/*!40000 ALTER TABLE `tablestaff` DISABLE KEYS */;
INSERT INTO `tablestaff` VALUES (1,'IBanny','12/6/2025','Active','Animal Management','Manager','25000Ks','IBanny','HW_ZMS.ibanny'),(2,'Gwen','12/1/2025','Active','Visitor Management','Counter','180000Ks','Gwen','HW_ZMS.gwen'),(3,'Minku','12/2/2025','Active','Animal Management','Counter','100000Ks','Minku','HW_ZMS.minku'),(4,'Mint','12/1/2025','Active','Inventory Management','Manager','250000Ks','Mint','HW_ZMS.mint'),(10,'Ryan ','12/12/2024','Active','Health Department','Nurse','350000Ks','Ryan','HW_ZMS.ryan'),(11,'Candy','12/2/2025','Active','Inventory Management','Driver','250000Ks','Candy','HW_ZMS.candy'),(12,'Rainbow','12/6/2025','Active','Inventory Management','Driver','150000Ks','Rainbow','HW_ZMS.rainbow'),(38,'Yue','6/3/2025','Resigned','Visitor Management','Ticket Seller','250000Ks','Yue','HW_ZMS.yue'),(39,'Camila','12/12/2024','Active','Visitor Management','Dancer','230000Ks','Camila','HW_ZMS.camila'),(40,'ling','12/2/2023','Active','Visitor Management','Ticket Seller','200000Ks','Ling','HW_ZMS.ling'),(41,'Zaw Zaw','16/12/2023','Resigned','Animal Management','Veterinarian','500000Ks','ZawZaw','HW_ZMS.zawzaw'),(48,'Tom','1/2/2021','Active','Animal Management','Cleaner','180000Ks','Tom','HW_ZMS.tom'),(49,'Takhin Latt','12/12/2024','Resigned','Inventory Management','Manager','250000Ks','Takhin Latt','HW_ZMS.latt'),(50,'Zett','14/6/2022','Active','Visitor Management','Dancer','180000Ks','Zett','HW_ZMS.zett'),(51,'Day','1/6/2022','Active','Visitor Management','Singer','210000Ks','Day','HW_ZMS.day'),(52,'Leon','5/6/2024','Resigned','Visitor Management','Singer','120000Ks','Leon','HW_ZMS.leon');
/*!40000 ALTER TABLE `tablestaff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-02 22:14:07
