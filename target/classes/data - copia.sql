-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: parking
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `comercios`
--

DROP TABLE IF EXISTS `comercios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comercios` (
  `cuit` bigint NOT NULL,
  `id_comercio` bigint NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `razon_social` varchar(255) NOT NULL,
  PRIMARY KEY (`id_comercio`),
  UNIQUE KEY `UKbjjbb1v7xjpr0vuw1k5pudp1y` (`cuit`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercios`
--

LOCK TABLES `comercios` WRITE;
/*!40000 ALTER TABLE `comercios` DISABLE KEYS */;
INSERT INTO `comercios` VALUES (30712566699,1,'Urquiza 3030','Autorizado','Librería Regalos'),(20932566699,2,'Fcdo Zuviría 5050','Autorizado','Kiosco la Flaca'),(20162467609,3,'San Martin 2815','Autorizado','Pago Fácil El trucho'),(20172568906,4,'San Martin 2756','Autorizado','Kioco puchito'),(20234569870,5,'Urquiza 2112','Suspendido','Rapipago El Sope'),(25063456560,7,'San Martin 2000','Autorizado','Rapipago La esquina');
/*!40000 ALTER TABLE `comercios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estacionamiento`
--

DROP TABLE IF EXISTS `estacionamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estacionamiento` (
  `id_estacionamiento` bigint NOT NULL AUTO_INCREMENT,
  `estado` tinyint DEFAULT NULL,
  `fecha_hora_fin` datetime(6) DEFAULT NULL,
  `fecha_hora_inicio` datetime(6) DEFAULT NULL,
  `patente` varchar(255) DEFAULT NULL,
  `dni` bigint DEFAULT NULL,
  PRIMARY KEY (`id_estacionamiento`),
  KEY `FK6fx57w4ei884ribhw4uitb9j8` (`dni`),
  CONSTRAINT `FK6fx57w4ei884ribhw4uitb9j8` FOREIGN KEY (`dni`) REFERENCES `usuarios` (`dni`),
  CONSTRAINT `estacionamiento_chk_1` CHECK ((`estado` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estacionamiento`
--

LOCK TABLES `estacionamiento` WRITE;
/*!40000 ALTER TABLE `estacionamiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `estacionamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estacionamientos`
--

DROP TABLE IF EXISTS `estacionamientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estacionamientos` (
  `id_estacionamiento` bigint NOT NULL AUTO_INCREMENT,
  `patente` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_hora_inicio` datetime DEFAULT NULL,
  `fecha_hora_fin` datetime DEFAULT NULL,
  `dni` bigint DEFAULT NULL,
  PRIMARY KEY (`id_estacionamiento`),
  KEY `FKguxgum2hn96t7ql3u4bv386a2` (`dni`),
  CONSTRAINT `FKguxgum2hn96t7ql3u4bv386a2` FOREIGN KEY (`dni`) REFERENCES `usuarios` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estacionamientos`
--

LOCK TABLES `estacionamientos` WRITE;
/*!40000 ALTER TABLE `estacionamientos` DISABLE KEYS */;
INSERT INTO `estacionamientos` VALUES (1,'RYC2098','Libre','2024-06-28 22:43:23','2024-06-29 12:19:58',12487756),(13,'RYC2098','Libre','2024-06-29 12:21:35','2024-06-29 12:35:30',12487756),(14,'RYC2098','Libre','2024-06-29 12:38:09','2024-06-29 16:31:05',12487756),(15,'RYC2098','Libre','2024-06-29 16:31:39','2024-06-29 16:32:12',12487756),(16,'RYC2098','Libre','2024-06-29 16:34:12','2024-06-29 16:36:12',12487756),(17,'ZOMMQ7931','Libre','2024-06-29 16:58:38','2024-06-29 16:58:51',25074805),(18,'LDIHZ2330','Libre','2024-06-29 17:01:55','2024-06-29 17:01:58',31781508),(19,'LDIHZ2330','Libre','2024-06-29 17:07:55','2024-06-29 17:08:07',31781508),(20,'LDIHZ2330','Libre','2024-06-29 17:09:34','2024-06-29 17:25:00',31781508),(21,'LDIHZ2330','Ocupado','2024-06-29 17:25:22',NULL,31781508),(22,'CRY9635','Libre','2024-06-29 17:30:15','2024-06-29 17:30:22',43733455),(23,'CRY9635','Libre','2024-06-29 17:35:06','2024-06-29 17:35:08',43733455),(24,'CRY9635','Libre','2024-06-29 17:40:00','2024-06-29 17:40:02',43733455),(25,'CRY9635','Libre','2024-06-29 17:43:18','2024-06-29 17:43:20',43733455);
/*!40000 ALTER TABLE `estacionamientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recargas`
--

DROP TABLE IF EXISTS `recargas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recargas` (
  `importe` float NOT NULL,
  `dni` bigint DEFAULT NULL,
  `fecha_hora` datetime(6) DEFAULT NULL,
  `id_comercio` bigint DEFAULT NULL,
  `id_recarga` bigint NOT NULL AUTO_INCREMENT,
  `patente` varchar(255) NOT NULL,
  PRIMARY KEY (`id_recarga`),
  KEY `FKptf0i9h9ckpixcboj3yl3h90w` (`id_comercio`),
  KEY `FKkigf5dubd6fdmyhwoogxkbxa6` (`dni`),
  CONSTRAINT `FKkigf5dubd6fdmyhwoogxkbxa6` FOREIGN KEY (`dni`) REFERENCES `usuarios` (`dni`),
  CONSTRAINT `FKptf0i9h9ckpixcboj3yl3h90w` FOREIGN KEY (`id_comercio`) REFERENCES `comercios` (`id_comercio`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recargas`
--

LOCK TABLES `recargas` WRITE;
/*!40000 ALTER TABLE `recargas` DISABLE KEYS */;
INSERT INTO `recargas` VALUES (2021,22111333,'2024-06-26 12:13:55.849080',1,1,'ZVB7201'),(1222,22111333,'2024-06-26 15:40:48.621410',2,2,'ZVB7202'),(2000,22111333,'2024-06-29 17:45:11.116024',3,3,'ZVB7202'),(2000,22111333,'2024-06-29 17:45:50.040155',3,4,'ZVB7201'),(2000,22111333,'2024-06-29 17:50:36.587596',3,5,'10000001'),(2000,22111333,'2024-06-29 18:01:16.632082',3,6,'ZVB7202'),(5000,22111333,'2024-06-29 18:25:01.219090',3,7,'ZVB7202'),(2500,22111333,'2024-06-29 18:25:27.376666',3,8,'ZVB7202'),(0,22111333,'2024-06-29 18:27:07.808318',3,9,'ZVB7202');
/*!40000 ALTER TABLE `recargas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `fecha_nacimiento` date NOT NULL,
  `saldo_cuenta` float NOT NULL,
  `dni` bigint NOT NULL,
  `patente` varchar(9) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `domicilio` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('2003-12-13',5928.29,7098365,'CFDML1400','Jiménez','0n3hgo2tlkp1qpljwq0','Masía Rodrigo Villanueva 33, Santa Fe, Argentina','jjimnez19@example.com','José Emilio'),('2004-07-03',0,12478020,'IUA2693','Olmos','iwassb970z45uhxxdo','Polígono Alfonso Valdés, 2, Santa Fe, Argentina','molmos2@example.com','Mayte'),('2000-07-18',62627.4,12487756,'RYC2098','Vergara','y3iglnvd3','Barrio Sonia Guerra, 21, Santa Fe, Argentina','gvergara12@example.com','Guillermina'),('1990-08-09',0,18314667,'LSEJI8298','Griego','0mv0bdn9','Rambla Marcos s/n. Esc. 656, Santa Fe, Argentina','mgriego45@example.com','Micaela'),('1978-05-28',52907.7,19655972,'QVO9265','Villalobos','gs4zqn','Arrabal María del Carmen, 53 Puerta 321, Santa Fe, Argentina','vvillalobos24@example.com','Víctor'),('1999-10-18',43505.2,19960475,'OEYFC3951','Lovato','ez43rrf4p17i','Conjunto Bernardo 9, Santa Fe, Argentina','jlovato40@example.com','Josefina'),('2006-05-05',7500,22111333,'ZVB7202','Martillo','ks_dhf_khd_fw','Apartamento Manuel Armendáriz s/n. Esc. 047, Santa Fe, Argentina','jmurillo39@example.com','Pepe'),('1979-12-17',76065.2,25074805,'ZOMMQ7931','Morales','909cniht8','Salida David, 83 Esc. 378, Santa Fe, Argentina','fmorales42@example.com','Federico'),('2006-01-15',300,25555555,'ZVB7999','Pedraza','ks_dhf_khd_fw','Apartamento 047, Santa Fe, Argentina','jmurillo39@example.com','Carlos'),('2004-02-18',63731.3,26435121,'HQJFH6743','Patiño','sc19a67ep4dac','Paseo Roberto, 92, Santa Fe, Argentina','fpatio22@example.com','Francisco'),('1980-01-05',21112.4,30308560,'QHB9234','Delacrúz','4z2h1h8j9ep9jb','Colonia Benito 5 Puerta 371, Santa Fe, Argentina','pdelacrz19@example.com','Pedro'),('1990-02-05',53221,31405057,'IWH0919','Madera','saxba4z7ab','Aldea Gerardo, 8, Santa Fe, Argentina','gmadera34@example.com','Gerardo'),('1979-07-13',42461.2,31781508,'LDIHZ2330','Pedraza','g6a3bqjdnih9xylyw2o','Caserio Daniel, 11, Santa Fe, Argentina','lpedraza6@example.com','Luisa'),('1976-03-24',66053.5,32284712,'AGTNO4244','Ulloa','zr3mdj7rhxbiiiw1nq8','Huerta Ricardo, 0 Esc. 648, Santa Fe, Argentina','mulloa28@example.com','Micaela'),('1975-09-30',72466.4,41916157,'EFN3518','Paz','dze2byoo6u9md','Municipio Jaime Mesa 6, Santa Fe, Argentina','gpaz16@example.com','Graciela'),('2003-03-25',77775.4,43733455,'CRY9635','Amaya','auzee1voyl70qo','Grupo Jorge Luis 0 Esc. 755, Santa Fe, Argentina','aamaya24@example.com','Antonio'),('1996-08-13',75511.2,45960614,'DEQCY8400','Ocampo','ziqnr2a5i','Rampa Rosario, 06, Santa Fe, Argentina','bocampo29@example.com','Beatriz');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'parking'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-30 10:21:08
