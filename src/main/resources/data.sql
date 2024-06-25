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
  `id_comercio` bigint NOT NULL AUTO_INCREMENT,
  `cuit` bigint DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `razon_social` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_comercio`),
  UNIQUE KEY `UKbjjbb1v7xjpr0vuw1k5pudp1y` (`cuit`),
  CONSTRAINT `comercios_chk_1` CHECK ((`estado` between 0 and 1))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercios`
--

LOCK TABLES `comercios` WRITE;
/*!40000 ALTER TABLE `comercios` DISABLE KEYS */;
INSERT INTO `comercios` VALUES (1,20214587849,'San Martin 2020','0','El kiosco'),(2,20162587416,'Facundo ZUviría 5451','0','Telecentro La llamada'),(3,21124569870,'Vitori 3550','0','Kiosco la cuadra');
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
  `patente_vehiculo` varchar(255) DEFAULT NULL,
  `dni` bigint DEFAULT NULL,
  PRIMARY KEY (`id_estacionamiento`),
  KEY `FK6fx57w4ei884ribhw4uitb9j8` (`dni`),
  CONSTRAINT `FK6fx57w4ei884ribhw4uitb9j8` FOREIGN KEY (`dni`) REFERENCES `usuarios` (`dni`),
  CONSTRAINT `estacionamiento_chk_1` CHECK ((`estado` between 0 and 1))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estacionamiento`
--

LOCK TABLES `estacionamiento` WRITE;
/*!40000 ALTER TABLE `estacionamiento` DISABLE KEYS */;
INSERT INTO `estacionamiento` VALUES (1,0,NULL,'2024-06-20 06:00:00.000000','HBQVT3310',11985383),(2,0,NULL,'2024-06-20 06:05:00.000000','VIQYH1637',7027890);
/*!40000 ALTER TABLE `estacionamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recargas`
--

DROP TABLE IF EXISTS `recargas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recargas` (
  `id_recarga` bigint NOT NULL AUTO_INCREMENT,
  `fecha_hora` datetime(6) DEFAULT NULL,
  `importe` float NOT NULL,
  `patente` varchar(255) DEFAULT NULL,
  `dni` bigint DEFAULT NULL,
  `id_comercio` bigint DEFAULT NULL,
  PRIMARY KEY (`id_recarga`),
  KEY `FKptf0i9h9ckpixcboj3yl3h90w` (`id_comercio`),
  KEY `FKkigf5dubd6fdmyhwoogxkbxa6` (`dni`),
  CONSTRAINT `FKkigf5dubd6fdmyhwoogxkbxa6` FOREIGN KEY (`dni`) REFERENCES `usuarios` (`dni`),
  CONSTRAINT `FKptf0i9h9ckpixcboj3yl3h90w` FOREIGN KEY (`id_comercio`) REFERENCES `comercios` (`id_comercio`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recargas`
--

LOCK TABLES `recargas` WRITE;
/*!40000 ALTER TABLE `recargas` DISABLE KEYS */;
INSERT INTO `recargas` VALUES (1,'2024-06-15 21:00:00.000000',100,'VIQYH1637',7027890,1),(2,'2024-06-15 21:15:00.000000',500,'MRY0519',12800554,1),(3,'2024-06-18 20:20:00.000000',150,'MRY0519',12800554,1),(4,'2024-06-16 12:00:00.000000',100,'VIQYH1637',7027890,1);
/*!40000 ALTER TABLE `recargas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `dni` bigint NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `domicilio` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `patente` varchar(9) NOT NULL,
  `saldo_cuenta` float NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (7027890,'Águilar','0lerjf62','Puente Andrés Hinojosa, 86, Santa Fe, Argentina','guilar25@example.com','2004-07-05','Óscar','VIQYH1637',24608.6),(11142575,'Zamora','14sd8j6','Escalinata Martín, 77 Puerta 485, Santa Fe, Argentina','pzamora24@example.com','2001-04-06','Pablo','OFUBP5597',21021.4),(11985383,'Melgar','0obagp7y5tnsmqyc','Ramal José Eduardo Sosa s/n. Esc. 188, Santa Fe, Argentina','nmelgar41@example.com','2001-08-25','Natalia','HBQVT3310',62625.6),(12604692,'Rivero','djn31jrdxw8b67','Terrenos Hernán, 3 Puerta 839, Santa Fe, Argentina','srivero17@example.com','1987-11-14','Sara','XEJ8353',23015.8),(12800554,'Ruiz','hqefeq','Lado Javier Pagan 38 Esc. 566, Santa Fe, Argentina','nruiz5@example.com','1987-10-20','Nicolás','MRY0519',34653.7),(14528602,'Cisneros','ojxph02vvey1','Poblado José Emilio s/n. Puerta 995, Santa Fe, Argentina','rcisneros3@example.com','1995-08-20','Rosalia','HJNMA9650',58744.5),(15038112,'Rosario','nopv4gqdm0aq1uaccg9','Partida Rosa Solano s/n. Esc. 994, Santa Fe, Argentina','srosario31@example.com','1989-07-11','Sara','HTNYA9053',50408),(15954107,'Balderas','gtvmu6cwei','Prolongación Rubén s/n., Santa Fe, Argentina','lbalderas42@example.com','1977-10-02','Luis Miguel','HGI7674',65176.6),(18550054,'Zúñiga','z43fqtffhlz','Barrio Marco Antonio Casillas s/n., Santa Fe, Argentina','mziga50@example.com','2004-08-06','Mariano','NPIYH2228',35970.4),(22111333,'Martillo','ks_dhf_khd_fw','Apartamento Manuel Armendáriz s/n. Esc. 047, Santa Fe, Argentina','jmurillo39@example.com','2006-05-05','Pedro','ZVB7202',1000.1),(22956125,'Menéndez','nktdgkao21zt819k','Arroyo Mariano Romo 9 Puerta 980, Santa Fe, Argentina','bmenndez32@example.com','1982-06-10','Blanca','XHA4769',24021.7),(23148861,'Guillen','et246utrlw8zvpz34br','Terrenos Mariana s/n. Puerta 509, Santa Fe, Argentina','eguillen50@example.com','1994-06-30','Esperanza','KIOUV4214',42470.9),(23996285,'Zambrano','7g7j0i1g9ytvqa7houm','Prolongación Claudia 6, Santa Fe, Argentina','gzambrano45@example.com','2002-05-11','Guillermo','PNNJS2335',84425.6),(25813173,'Esquivel','ppivbx8v72tvxdosi3','Rincón Joaquín Córdova s/n. Esc. 958, Santa Fe, Argentina','mesquivel11@example.com','1981-12-30','María Elena','UMR3618',17032.1),(28934583,'Torres','8m2ezy','Carretera Cristobal Niño 7 Puerta 558, Santa Fe, Argentina','dtorres9@example.com','1996-04-29','Dorotea','CTS7831',97841.3),(31348084,'Calderón','rq6crbwh9w9s7id1','Cuesta Jesús, 27 Puerta 631, Santa Fe, Argentina','mcaldern7@example.com','1983-05-05','Mariano','LLP5816',95144.2),(36465008,'Jaime','2f0i9u00dn9','Bloque Natalia 42, Santa Fe, Argentina','ljaime11@example.com','1978-12-19','Leonor','NVI7864',51567.5),(40141918,'Murillo','ksdhfkhqwerfqd_fw','Apartamento Manuel Armendáriz s/n. Esc. 047, Santa Fe, Argentina','jmurillo39@example.com','2006-05-05','Jerónimo','ZVB7202',85857.1),(41787668,'Manzanares','hhb030','Vía Pública Irene 4, Santa Fe, Argentina','amanzanares50@example.com','1986-09-25','Ana','GQY7004',89098.3),(42520799,'Murillo','lecuv0du8yt','Calle Adán Téllez, 16 Esc. 301, Santa Fe, Argentina','gmurillo43@example.com','1990-09-03','Gilberto','HOTGO8096',26141.2),(46236100,'Carbajal','rcacysxviiwga9','Aldea Clara s/n. Esc. 292, Santa Fe, Argentina','acarbajal19@example.com','1983-01-28','Alicia','DGOPE5221',99664.2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-24 22:09:55
