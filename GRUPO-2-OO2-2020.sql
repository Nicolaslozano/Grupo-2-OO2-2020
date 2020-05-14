CREATE DATABASE  IF NOT EXISTS `GRUPO-2-BDD-OO2-2020` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `GRUPO-2-BDD-OO2-2020`;
-- MySQL dump 10.13  Distrib 8.0.20, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: GRUPO-2-BDD-OO2-2020
-- ------------------------------------------------------
-- Server version	8.0.20-0ubuntu0.20.04.1

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
-- Table structure for table `carrito`
--

DROP TABLE IF EXISTS `carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrito` (
  `id_carrito` bigint NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id_carrito`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrito`
--

LOCK TABLES `carrito` WRITE;
/*!40000 ALTER TABLE `carrito` DISABLE KEYS */;
/*!40000 ALTER TABLE `carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `email` varchar(45) NOT NULL,
  `id_persona` bigint NOT NULL,
  PRIMARY KEY (`id_persona`),
  CONSTRAINT `FKlbs69o9qkvv7lgn06idak3crb` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('Juanlopez@gmail.com',13),('julip@hotmail.com',14);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comercio`
--

DROP TABLE IF EXISTS `comercio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comercio` (
  `id_local` bigint NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `latitud` double DEFAULT NULL,
  `longitud` double DEFAULT NULL,
  `telefono` bigint DEFAULT NULL,
  PRIMARY KEY (`id_local`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercio`
--

LOCK TABLES `comercio` WRITE;
/*!40000 ALTER TABLE `comercio` DISABLE KEYS */;
INSERT INTO `comercio` VALUES (7,'Marraspin 1032',-34,-58,42023020),(8,'San Martin 304',-40,-30,42421060);
/*!40000 ALTER TABLE `comercio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `franja_horaria` varchar(45) DEFAULT NULL,
  `tipo_empleado` bit(1) DEFAULT NULL,
  `id_persona` bigint NOT NULL,
  `id_local` bigint DEFAULT NULL,
  PRIMARY KEY (`id_persona`),
  KEY `FKdivg1llrjsh1c8fnnigd7f057` (`id_local`),
  CONSTRAINT `FK3yo5m2sf91t2spkatlwxagm5x` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`),
  CONSTRAINT `FKdivg1llrjsh1c8fnnigd7f057` FOREIGN KEY (`id_local`) REFERENCES `comercio` (`id_local`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES ('Mañana',_binary '\0',15,7),('Tarde',_binary '\0',16,7),('Mañana',_binary '\0',17,8),('Mañana',_binary '\0',18,8),('Tarde',_binary '\0',19,8);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `id_factura` bigint NOT NULL AUTO_INCREMENT,
  `factura` varchar(255) DEFAULT NULL,
  `id_local` bigint DEFAULT NULL,
  PRIMARY KEY (`id_factura`),
  KEY `FKrxjcwe8xaqq2nk82gsd4dki5a` (`id_local`),
  CONSTRAINT `FKrxjcwe8xaqq2nk82gsd4dki5a` FOREIGN KEY (`id_local`) REFERENCES `comercio` (`id_local`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lote`
--

DROP TABLE IF EXISTS `lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lote` (
  `id_lote` bigint NOT NULL AUTO_INCREMENT,
  `cantidad_actual` int DEFAULT NULL,
  `cantidad_inicial` int DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `id_producto` bigint DEFAULT NULL,
  `id_stock` bigint DEFAULT NULL,
  PRIMARY KEY (`id_lote`),
  KEY `FKcn55butngt8wrmglewkbyewaa` (`id_producto`),
  KEY `FKikycxdbp6webwyc477kt16m2u` (`id_stock`),
  CONSTRAINT `FKcn55butngt8wrmglewkbyewaa` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `FKikycxdbp6webwyc477kt16m2u` FOREIGN KEY (`id_stock`) REFERENCES `stock` (`id_stock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lote`
--

LOCK TABLES `lote` WRITE;
/*!40000 ALTER TABLE `lote` DISABLE KEYS */;
/*!40000 ALTER TABLE `lote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `id_pedido` bigint NOT NULL AUTO_INCREMENT,
  `aceptado` bit(1) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `id_carrito` bigint DEFAULT NULL,
  `id_cliente` bigint DEFAULT NULL,
  `id_local` bigint DEFAULT NULL,
  `id_producto` bigint DEFAULT NULL,
  `id_vendedor_auxiliar` bigint DEFAULT NULL,
  `id_vendedor_original` bigint DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `FK1tfkqk6fiyfch0qwplj7uegcc` (`id_carrito`),
  KEY `FK9y4jnyp1hxqa386cnly0ay9uw` (`id_cliente`),
  KEY `FKs68m6e8nfgbp3mx497i2x3nw` (`id_local`),
  KEY `FKc1emammk1tjnowrcgjp9ygpjj` (`id_producto`),
  KEY `FKj6jxosixucokfm3961o5yqpwp` (`id_vendedor_auxiliar`),
  KEY `FKrm2oxndvbro5qvoulw6hhgmxi` (`id_vendedor_original`),
  CONSTRAINT `FK1tfkqk6fiyfch0qwplj7uegcc` FOREIGN KEY (`id_carrito`) REFERENCES `carrito` (`id_carrito`),
  CONSTRAINT `FK9y4jnyp1hxqa386cnly0ay9uw` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_persona`),
  CONSTRAINT `FKc1emammk1tjnowrcgjp9ygpjj` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `FKj6jxosixucokfm3961o5yqpwp` FOREIGN KEY (`id_vendedor_auxiliar`) REFERENCES `empleado` (`id_persona`),
  CONSTRAINT `FKrm2oxndvbro5qvoulw6hhgmxi` FOREIGN KEY (`id_vendedor_original`) REFERENCES `empleado` (`id_persona`),
  CONSTRAINT `FKs68m6e8nfgbp3mx497i2x3nw` FOREIGN KEY (`id_local`) REFERENCES `comercio` (`id_local`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id_persona` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(45) NOT NULL,
  `dni` int NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id_persona`),
  UNIQUE KEY `UK_hlwyecu2r9wagqayhej1kt5wy` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (13,'Lopez',40201070,'1998-08-05','Juan Carlos'),(14,'Perez',39893410,'1992-03-30','Julian'),(15,'Arevalo',39934098,'1997-04-20','Cristian'),(16,'Ramirez',85403020,'2000-12-04','Pablo'),(17,'Millan',30109420,'1989-07-22','Geronimo'),(18,'Centarti',41394326,'1998-05-04','Milagros'),(19,'Monaco',42050320,'1999-10-09','Lucia');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_alta` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (22,'Zapatilla','2020-05-13','NiqueShoe2020',2600),(23,'Remera','2020-05-13','Chombadidas',500),(24,'Pantalones','2020-05-13','JeanVanDamme',700);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud_stock`
--

DROP TABLE IF EXISTS `solicitud_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud_stock` (
  `id_solicitud` bigint NOT NULL AUTO_INCREMENT,
  `aceptado` bit(1) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `id_colaborador` bigint DEFAULT NULL,
  `id_producto` bigint DEFAULT NULL,
  `id_vendedor` bigint DEFAULT NULL,
  `id_local` bigint DEFAULT NULL,
  PRIMARY KEY (`id_solicitud`),
  KEY `FKca6m9kfujewqih5y178ega7im` (`id_colaborador`),
  KEY `FKdetvoltsnhq2kmtgru679jg9r` (`id_producto`),
  KEY `FKtejspbxrnmgw8buc1erna2xia` (`id_vendedor`),
  KEY `FKgw6qr8qge3mra4r1753wewl8y` (`id_local`),
  CONSTRAINT `FKca6m9kfujewqih5y178ega7im` FOREIGN KEY (`id_colaborador`) REFERENCES `empleado` (`id_persona`),
  CONSTRAINT `FKdetvoltsnhq2kmtgru679jg9r` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  CONSTRAINT `FKgw6qr8qge3mra4r1753wewl8y` FOREIGN KEY (`id_local`) REFERENCES `comercio` (`id_local`),
  CONSTRAINT `FKtejspbxrnmgw8buc1erna2xia` FOREIGN KEY (`id_vendedor`) REFERENCES `empleado` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_stock`
--

LOCK TABLES `solicitud_stock` WRITE;
/*!40000 ALTER TABLE `solicitud_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitud_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `id_stock` bigint NOT NULL,
  `cantidad` int DEFAULT NULL,
  PRIMARY KEY (`id_stock`),
  CONSTRAINT `FKn9jgkp58eimcuomncghc4hfoh` FOREIGN KEY (`id_stock`) REFERENCES `comercio` (`id_local`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (7,0),(8,0);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-13 23:57:10
