

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
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('Juanlopez@gmail.com',13),('julip@hotmail.com',14),('fedem@ħotmail.com',78),('julianomar@gmail.com',79),('fgarc@yahoo.com',80),('Patriciom1887@gmail.com',81);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comercio`
--

LOCK TABLES `comercio` WRITE;
/*!40000 ALTER TABLE `comercio` DISABLE KEYS */;
INSERT INTO `comercio` VALUES (7,'Marraspin 1032',-34,-58,42023020),(9,'Luis Peña 1922',49,31,15392914),(10,'Lope de vega 3530',-34.74563772,-58.4644709,42048743),(11,'Miranda 5200',-34.62494,-58.5099208,42429030);
/*!40000 ALTER TABLE `comercio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES ('Mañana',_binary '\0',15,7),('Tarde',_binary '\0',16,7),('Mañana',_binary '\0',77,9),('Tarde',_binary '\0',82,9),('Mañana',_binary '\0',83,10),('Tarde',_binary '\0',84,10),('Mañana',_binary '\0',85,11),('Tarde',_binary '\0',86,11);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lote`
--

LOCK TABLES `lote` WRITE;
/*!40000 ALTER TABLE `lote` DISABLE KEYS */;
INSERT INTO `lote` VALUES (53,10,70,_binary '\0','2020-05-29',22,9),(55,15,15,_binary '\0','2020-06-04',26,10),(56,25,25,_binary '\0','2020-06-04',29,10),(57,20,20,_binary '\0','2020-06-04',22,7),(58,3,3,_binary '\0','2020-06-04',27,7),(59,40,40,_binary '\0','2020-06-04',27,7),(61,10,10,_binary '\0','2020-06-04',26,9),(62,0,50,_binary '\0','2020-06-11',27,9),(63,0,100,_binary '\0','2020-06-11',23,10),(64,0,150,_binary '\0','2020-06-12',23,7),(65,0,160,_binary '\0','2020-06-12',22,7),(66,0,180,_binary '\0','2020-06-12',22,10),(67,0,160,_binary '\0','2020-06-12',23,10),(68,150,150,_binary '','2020-06-12',22,7),(69,150,150,_binary '','2020-06-13',22,10),(70,50,50,_binary '','2020-06-13',27,10),(71,50,50,_binary '','2020-06-13',27,9),(73,50,50,_binary '','2020-06-13',23,11),(74,25,25,_binary '','2020-06-13',23,7),(75,19,19,_binary '','2020-06-13',26,7),(76,25,25,_binary '','2020-06-13',27,7),(77,35,35,_binary '','2020-06-13',28,7),(78,60,60,_binary '','2020-06-13',22,9),(79,40,40,_binary '','2020-06-13',28,9),(80,80,80,_binary '','2020-06-13',23,10),(81,83,83,_binary '','2020-06-13',28,10),(82,30,30,_binary '','2020-06-13',26,11),(83,40,40,_binary '','2020-06-13',29,11);
/*!40000 ALTER TABLE `lote` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (13,'Lopez',40201070,'1998-08-05','Juan Carlos'),(14,'Perez',39893410,'1992-03-30','Julian'),(15,'Arevalo',39934098,'1997-04-20','Cristian'),(16,'Ramirez',85403020,'2000-12-04','Pablo'),(77,'Pellerino',3934322,'1990-11-10','Luis'),(78,'Lamartine',42012321,'1996-10-04','Federico'),(79,'Maruca',41932123,'1980-06-10','Julian'),(80,'Garcia',44932155,'1998-10-07','Francisco'),(81,'Maciel',37623421,'1990-12-05','Patricio'),(82,'Darthes',343451872,'1985-06-28','Nestor'),(83,'VIllafañe',330201030,'1970-04-28','Alberto'),(84,'Guzman',36962125,'1988-02-19','Roberto'),(85,'Burlando',31205631,'1973-01-24','Oscar'),(86,'Rodriguez',35293496,'1979-07-22','Pedro');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (22,'Zapatilla','2020-05-13','NiqueShoe2020',2600),(23,'Remera','2020-05-13','Chombadidas',500),(26,'Zapatilla','2020-06-04','Yizzi Boost',7500),(27,'Remera','2020-06-04','Supreme ',1500),(28,'Pantalon','2020-06-04','Bross Jean',2540),(29,'Pantalon','2020-06-04','Gronch',840);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (7,0),(9,0),(10,0),(11,0);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,50,0,'2020-06-12',13,7,23,84,15),(2,100,1,'2020-06-11',13,10,23,NULL,83),(3,50,1,'2020-06-11',13,9,27,NULL,77),(4,150,1,'2020-06-12',13,7,23,NULL,15),(5,180,0,'2020-06-12',13,7,26,NULL,15),(6,180,0,'2020-06-12',13,10,26,NULL,83),(7,180,1,'2020-06-12',13,7,22,83,15),(8,160,1,'2020-06-12',13,10,23,NULL,84),(9,160,1,'2020-06-12',13,7,22,NULL,16),(10,150,2,'2020-06-12',13,10,22,15,83);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-13  2:43:38
