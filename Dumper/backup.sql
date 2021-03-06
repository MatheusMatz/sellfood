-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: sabores
-- ------------------------------------------------------
-- Server version	5.7.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cardapio`
--

DROP TABLE IF EXISTS `cardapio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cardapio` (
  `id` bigint(20) NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardapio`
--

LOCK TABLES `cardapio` WRITE;
/*!40000 ALTER TABLE `cardapio` DISABLE KEYS */;
INSERT INTO `cardapio` VALUES (12,'Lanches');
/*!40000 ALTER TABLE `cardapio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (13),(13),(13),(13);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientes`
--

DROP TABLE IF EXISTS `ingredientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredientes` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `valor` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_stc4s65yimop05l9lukuo2fap` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientes`
--

LOCK TABLES `ingredientes` WRITE;
/*!40000 ALTER TABLE `ingredientes` DISABLE KEYS */;
INSERT INTO `ingredientes` VALUES (1,'Alface',0.40),(2,'Bacon',2.00),(3,'Hamb├║rguer de carne',3.00),(4,'Ovo',0.80),(5,'Queijo',1.50);
/*!40000 ALTER TABLE `ingredientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientes_do_lanche`
--

DROP TABLE IF EXISTS `ingredientes_do_lanche`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredientes_do_lanche` (
  `lanche_id` bigint(20) NOT NULL,
  `ingrediente_id` bigint(20) NOT NULL,
  KEY `FK7xohnj46w2etm19gec7rjmwml` (`ingrediente_id`),
  KEY `FKspkslxo9mklwudqe2kpummdj6` (`lanche_id`),
  CONSTRAINT `FK7xohnj46w2etm19gec7rjmwml` FOREIGN KEY (`ingrediente_id`) REFERENCES `ingredientes` (`id`),
  CONSTRAINT `FKspkslxo9mklwudqe2kpummdj6` FOREIGN KEY (`lanche_id`) REFERENCES `lanches` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientes_do_lanche`
--

LOCK TABLES `ingredientes_do_lanche` WRITE;
/*!40000 ALTER TABLE `ingredientes_do_lanche` DISABLE KEYS */;
INSERT INTO `ingredientes_do_lanche` VALUES (6,3),(6,5),(6,4),(6,2),(7,3),(7,5),(7,2),(8,3),(8,5),(9,3),(9,5),(9,4);
/*!40000 ALTER TABLE `ingredientes_do_lanche` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lanches`
--

DROP TABLE IF EXISTS `lanches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lanches` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `valor` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lanches`
--

LOCK TABLES `lanches` WRITE;
/*!40000 ALTER TABLE `lanches` DISABLE KEYS */;
INSERT INTO `lanches` VALUES (6,'X-Egg Bacon',7.30),(7,'X-Bacon',6.50),(8,'X-Burger',4.50),(9,'X-Egg',5.30);
/*!40000 ALTER TABLE `lanches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lanches_do_cardapio`
--

DROP TABLE IF EXISTS `lanches_do_cardapio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lanches_do_cardapio` (
  `cardapio_id` bigint(20) NOT NULL,
  `lanche_id` bigint(20) NOT NULL,
  KEY `FKo6f3fl65ka60sa0ukusi2qpro` (`lanche_id`),
  KEY `FKkc6cvbekkelgh9b0dg7fxocc8` (`cardapio_id`),
  CONSTRAINT `FKkc6cvbekkelgh9b0dg7fxocc8` FOREIGN KEY (`cardapio_id`) REFERENCES `cardapio` (`id`),
  CONSTRAINT `FKo6f3fl65ka60sa0ukusi2qpro` FOREIGN KEY (`lanche_id`) REFERENCES `lanches` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lanches_do_cardapio`
--

LOCK TABLES `lanches_do_cardapio` WRITE;
/*!40000 ALTER TABLE `lanches_do_cardapio` DISABLE KEYS */;
INSERT INTO `lanches_do_cardapio` VALUES (12,7),(12,8),(12,9),(12,6);
/*!40000 ALTER TABLE `lanches_do_cardapio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lanches_do_pedido`
--

DROP TABLE IF EXISTS `lanches_do_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lanches_do_pedido` (
  `pedido_id` bigint(20) NOT NULL,
  `lanche_id` bigint(20) NOT NULL,
  KEY `FKluwij0r45u4m2xjkv9s0js7lh` (`lanche_id`),
  KEY `FK2nn45c360hiufetxi3ltf2cek` (`pedido_id`),
  CONSTRAINT `FK2nn45c360hiufetxi3ltf2cek` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`),
  CONSTRAINT `FKluwij0r45u4m2xjkv9s0js7lh` FOREIGN KEY (`lanche_id`) REFERENCES `lanches` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lanches_do_pedido`
--

LOCK TABLES `lanches_do_pedido` WRITE;
/*!40000 ALTER TABLE `lanches_do_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `lanches_do_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedidos` (
  `id` bigint(20) NOT NULL,
  `data` datetime(6) DEFAULT NULL,
  `valor` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promocoes`
--

DROP TABLE IF EXISTS `promocoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promocoes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r3jydoqaawexyrsbsqmade4er` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promocoes`
--

LOCK TABLES `promocoes` WRITE;
/*!40000 ALTER TABLE `promocoes` DISABLE KEYS */;
INSERT INTO `promocoes` VALUES (1,'Se o lanche tem alface e n├úo tem bacon, ganha 10% de desconto.','Light'),(2,'A cada 3 por├º├Áes de carne o cliente s├│ paga 2. Se o lanche tiver 6 por├º├Áes, ocliente pagar├í 4. Assim por diante...','Muita carne'),(3,'A cada 3 por├º├Áes de queijo o cliente s├│ paga 2. Se o lanche tiver 6 por├º├Áes, ocliente pagar├í 4. Assim por diante...','Muito queijo');
/*!40000 ALTER TABLE `promocoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promocoes_do_cardapio`
--

DROP TABLE IF EXISTS `promocoes_do_cardapio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promocoes_do_cardapio` (
  `cardapio_id` bigint(20) NOT NULL,
  `promocao_id` bigint(20) NOT NULL,
  KEY `FK1v4pc5p4n5n0ek6g9ahf0agjr` (`promocao_id`),
  KEY `FKromb0dncfl4ugocqrol2q34it` (`cardapio_id`),
  CONSTRAINT `FK1v4pc5p4n5n0ek6g9ahf0agjr` FOREIGN KEY (`promocao_id`) REFERENCES `promocoes` (`id`),
  CONSTRAINT `FKromb0dncfl4ugocqrol2q34it` FOREIGN KEY (`cardapio_id`) REFERENCES `cardapio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promocoes_do_cardapio`
--

LOCK TABLES `promocoes_do_cardapio` WRITE;
/*!40000 ALTER TABLE `promocoes_do_cardapio` DISABLE KEYS */;
INSERT INTO `promocoes_do_cardapio` VALUES (12,1),(12,2),(12,3);
/*!40000 ALTER TABLE `promocoes_do_cardapio` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-10 19:57:52
