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
-- Table structure for table `medicalinventory`
--

DROP TABLE IF EXISTS `medicalinventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicalinventory` (
  `product_id` varchar(10) NOT NULL,
  `product_name` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `manufacturer` varchar(50) DEFAULT NULL,
  `exp_date` varchar(50) DEFAULT NULL,
  `warehouse` varchar(50) DEFAULT NULL,
  `stock` int DEFAULT '0',
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalinventory`
--

LOCK TABLES `medicalinventory` WRITE;
/*!40000 ALTER TABLE `medicalinventory` DISABLE KEYS */;
INSERT INTO `medicalinventory` VALUES ('MI-0000','Amoxicillin(T)','Tablet/Pill','GSK','21-08-2025','Warehouse 1',10,3500.00),('MI-0001','Ivermectin','Tablet/Pill','MSD Animal Health','09-09-2025','Warehouse 1',18,9200.00),('MI-0002','Fipronil Spray','Tropical Medications','Bayer','26-08-2025','Warehouse 1',1,23000.00),('MI-0003','Metronidazole','Syrup/Liquid medicine','Pfizer','12-10-2026','Warehouse 2',0,6200.00),('MI-0004','Meloxicam','Injectables','Boehringer Ingelheim','15-08-2025','Warehouse 3',8,7000.00),('MI-0005','Dexamethasone','Syrup/Liquid medicine','VetOne','12-08-2027','Warehouse 2',35,5000.00),('MI-0006','Atropine Sulfate','Injectables','Phoenix Pharmaceutical','15-06-2026','Warehouse 3',3,6500.00),('MI-0007','Saline Solution','Syrup/Liquid medicine','B. Braun','14-11-2028','Warehouse 2',35,1200.00),('MI-0008','Wound Care Products','Tropical Medications','Betadine','02-02-2026','Warehouse 2',0,3200.00),('MI-0009','Multivitamins','Tablet/Pill','Nutramax Laboratories','06-11-2026','Warehouse 1',65,2100.00),('MI-0010','Eye and Ear Drops (Antibiotic)','Eye/Ear Drops','Otic Vet','26-08-2025','Warehouse 3',10,9500.00),('MI-0011','Activated Charcoal','Tropical Medications','Actidose','31-08-2025','Warehouse 2',21,12000.00),('MI-0013','Amoxicillin(I)','Injectables','GSK','27-08-2025','Warehouse 3',2,7000.00);
/*!40000 ALTER TABLE `medicalinventory` ENABLE KEYS */;
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
