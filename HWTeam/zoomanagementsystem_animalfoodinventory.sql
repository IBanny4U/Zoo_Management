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
-- Table structure for table `animalfoodinventory`
--

DROP TABLE IF EXISTS `animalfoodinventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animalfoodinventory` (
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
-- Dumping data for table `animalfoodinventory`
--

LOCK TABLES `animalfoodinventory` WRITE;
/*!40000 ALTER TABLE `animalfoodinventory` DISABLE KEYS */;
INSERT INTO `animalfoodinventory` VALUES ('FI-0000','Timothy Hay','Herbivore Diet','Ko Zett Farm','29-08-2025','Warehouse 4',7,15000.00),('FI-0001','Mixed Vegetables','Herbivore Diet','TN KOI Farm','22-08-2025','Warehouse 5',14,5000.00),('FI-0002','Mixed Fruits','Herbivore Diet','Aung Myay Farm','27-08-2025','Warehouse 5',15,8000.00),('FI-0003','Chicken','Carnivore Diet','Daing Kyaw Gyi','01-09-2025','Warehouse 6',5,8400.00),('FI-0004','Beef','Carnivore Diet','Daing Kyaw Gyi','05-09-2025','Warehouse 6',0,14000.00),('FI-0005','Dog/Cat Kibble','Omnivore Diet','Purina','03-08-2026','Warehouse 5',20,28000.00),('FI-0006','Fresh Fish','Omnivore Diet','Shwe Taung Nyo Gyi','16-08-2025','Warehouse 6',15,6000.00),('FI-0007','Primate Chow','Specialized Diets','ZuPreem','01-02-2027','Warehouse 5',7,58000.00),('FI-0008','Insects - Crickets/Mealworms','Specialized Diets','Ko Day Insect World','16-09-2025','Warehouse 6',18,18000.00),('FI-0009','Vitamins & Supplements','Specialized Diets','Zoo Med','13-03-2028','Warehouse 6',12,14000.00);
/*!40000 ALTER TABLE `animalfoodinventory` ENABLE KEYS */;
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
