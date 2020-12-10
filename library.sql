-- MySQL dump 10.13  Distrib 8.0.0-dmr, for osx10.11 (x86_64)
--
-- Host: localhost    Database: boot
-- ------------------------------------------------------
-- Server version	8.0.0-dmr-log

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
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `doc_id` bigint(20) NOT NULL,
  `pid` bigint(20) NOT NULL,
  PRIMARY KEY (`doc_id`,`pid`),
  KEY `FKyevrrxb7xrf9vb9ykdt09cyb` (`pid`),
  CONSTRAINT `FKfui7i1uf4nmjugqxtsxb6igwv` FOREIGN KEY (`doc_id`) REFERENCES `document` (`doc_id`),
  CONSTRAINT `FKyevrrxb7xrf9vb9ykdt09cyb` FOREIGN KEY (`pid`) REFERENCES `person` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (3,16),(1,17),(5,18),(2,19),(4,20);
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `author` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow_transaction`
--

DROP TABLE IF EXISTS `borrow_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrow_transaction` (
  `bor_number` bigint(20) NOT NULL,
  `bor_date_time` datetime(6) DEFAULT NULL,
  `reservation_status` int(11) NOT NULL,
  `ret_date_time` datetime(6) DEFAULT NULL,
  `bid` bigint(20) DEFAULT NULL,
  `copy_num` bigint(20) DEFAULT NULL,
  `doc_id` bigint(20) DEFAULT NULL,
  `reader_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bor_number`),
  KEY `FK5dmtwoor06jtc5oxpdl8tqstk` (`bid`,`copy_num`,`doc_id`),
  KEY `FK8ud9rjwksv5vymsn5p4sngvbd` (`reader_id`),
  CONSTRAINT `FK5dmtwoor06jtc5oxpdl8tqstk` FOREIGN KEY (`bid`, `copy_num`, `doc_id`) REFERENCES `copy` (`bid`, `copy_num`, `doc_id`),
  CONSTRAINT `FK8ud9rjwksv5vymsn5p4sngvbd` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow_transaction`
--

LOCK TABLES `borrow_transaction` WRITE;
/*!40000 ALTER TABLE `borrow_transaction` DISABLE KEYS */;
INSERT INTO `borrow_transaction` VALUES (1,'2020-03-25 09:31:25.000000',1,'2020-03-27 12:02:04.000000',1,1,1,15),(2,'2020-05-05 12:01:02.000000',1,'2020-05-16 11:59:22.000000',2,1,2,10),(3,'2020-07-20 15:13:22.000000',1,'2020-08-01 15:28:31.000000',3,1,3,13),(4,'2020-11-02 14:44:58.000000',0,NULL,4,1,4,11),(5,'2020-11-25 10:28:43.000000',0,NULL,5,1,5,12),(6,'2020-03-28 12:03:05.000000',1,'2020-03-29 09:30:01.000000',1,1,9,14),(7,'2020-04-02 12:53:02.000000',1,'2020-04-04 09:14:24.000000',1,1,10,14),(8,'2020-04-25 14:20:34.000000',1,'2020-04-28 09:52:39.000000',1,1,11,14),(9,'2020-07-23 13:11:28.000000',1,'2020-07-26 09:33:23.000000',1,1,15,14),(10,'2020-05-23 12:31:02.000000',1,'2020-05-24 11:22:22.000000',1,1,1,9),(11,'2020-05-28 12:24:02.000000',1,'2020-05-29 11:19:22.000000',1,1,12,9),(12,'2020-05-31 12:21:42.000000',1,'2020-06-02 11:32:22.000000',1,1,13,9),(13,'2020-07-22 13:53:44.000000',1,'2020-07-24 09:24:34.000000',1,1,14,12),(14,'2020-05-15 12:28:02.000000',1,'2020-05-16 11:59:22.000000',2,1,1,10),(15,'2020-06-26 13:23:24.000000',1,'2020-06-27 10:24:26.000000',2,1,1,1),(16,'2020-06-15 12:43:22.000000',1,'2020-06-16 09:59:24.000000',2,1,2,2),(17,'2020-02-01 14:55:31.000000',1,'2020-05-22 09:42:11.000000',3,1,3,5),(18,'2020-06-17 13:37:12.000000',1,'2020-09-30 10:22:51.000000',3,1,13,6),(19,'2020-01-01 12:02:55.000000',1,'2020-05-01 12:44:32.000000',4,1,4,7),(20,'2020-05-02 13:22:41.000000',1,'2020-11-01 11:22:33.000000',4,1,4,8),(28,'2020-12-10 11:47:09.679000',1,'2020-12-10 12:46:32.402000',1,1,1,21),(29,'2020-12-10 11:47:09.685000',1,'2020-12-10 12:46:32.404000',1,2,1,21),(30,'2020-12-10 11:47:09.686000',1,'2020-12-10 12:46:32.405000',1,3,1,21),(31,'2020-12-10 11:47:09.688000',1,'2020-12-10 12:46:32.405000',1,4,1,21),(32,'2020-12-10 11:47:09.690000',1,'2020-12-10 12:46:32.406000',1,5,1,21),(33,'2020-12-10 11:47:09.692000',1,'2020-12-10 12:46:32.406000',1,6,1,21);
/*!40000 ALTER TABLE `borrow_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `bid` bigint(20) NOT NULL,
  `location` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'Newark','Newark Library'),(2,'Jersey City','Jersey City Library'),(3,'Paterson','Paterson Library'),(4,'Elizabeth','Elizabeth Library'),(5,'Edison','Edison Library'),(6,'Woodbridge','Woodbridge Library'),(7,'Lakewood','Lakewood Library'),(8,'Toms River','Toms River Library'),(9,'Hamilton','Hamilton Library'),(10,'Trenton','Trenton Library');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chairs`
--

DROP TABLE IF EXISTS `chairs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chairs` (
  `doc_id` bigint(20) NOT NULL,
  `pid` bigint(20) NOT NULL,
  PRIMARY KEY (`doc_id`,`pid`),
  KEY `FKgs22arvnnh1ve0ptpqh3ijvrm` (`pid`),
  CONSTRAINT `FK1iwj3vthtpvq18mdvni3ow7c` FOREIGN KEY (`doc_id`) REFERENCES `document` (`doc_id`),
  CONSTRAINT `FKgs22arvnnh1ve0ptpqh3ijvrm` FOREIGN KEY (`pid`) REFERENCES `person` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chairs`
--

LOCK TABLES `chairs` WRITE;
/*!40000 ALTER TABLE `chairs` DISABLE KEYS */;
INSERT INTO `chairs` VALUES (11,6),(15,7),(12,8),(14,9),(13,10);
/*!40000 ALTER TABLE `chairs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `copy`
--

DROP TABLE IF EXISTS `copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `copy` (
  `bid` bigint(20) NOT NULL,
  `copy_num` bigint(20) NOT NULL,
  `doc_id` bigint(20) NOT NULL,
  `copy_status` int(11) NOT NULL,
  `position` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`bid`,`copy_num`,`doc_id`),
  KEY `FK3hk2favo0r3uh33c1u6i11ly` (`doc_id`),
  CONSTRAINT `FK3hk2favo0r3uh33c1u6i11ly` FOREIGN KEY (`doc_id`) REFERENCES `document` (`doc_id`),
  CONSTRAINT `FK7hc4m5rhkj6a0vdsmwq0arpvd` FOREIGN KEY (`bid`) REFERENCES `branch` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `copy`
--

LOCK TABLES `copy` WRITE;
/*!40000 ALTER TABLE `copy` DISABLE KEYS */;
INSERT INTO `copy` VALUES (1,1,1,4,'001A03'),(1,1,9,4,'001A73'),(1,1,10,4,'001B23'),(1,1,11,4,'001A76'),(1,1,12,4,'030C02'),(1,1,13,4,'006F12'),(1,1,14,4,'031B23'),(1,1,15,4,'001A11'),(1,2,1,4,'1CBS29'),(1,3,1,4,'1CBS30'),(1,4,1,4,'1CBS31'),(1,5,1,4,'1CBS32'),(1,6,1,4,'1CBS29'),(2,1,1,4,'018B25'),(2,1,2,4,'018B01'),(2,1,3,4,'035K12'),(2,1,4,4,'020F34'),(2,1,12,4,'012D01'),(3,1,3,4,'003F15'),(3,1,13,4,'003A23'),(4,1,4,3,'032A23'),(4,1,14,4,'032A21'),(5,1,5,3,'022H01'),(5,1,15,4,'012H28'),(6,1,6,4,'005D18'),(6,1,11,4,'035D28'),(7,1,7,1,'009J32'),(7,1,12,4,'019A02'),(8,1,8,1,'010A02'),(8,1,13,4,'016N12'),(9,1,9,4,'025J24'),(9,1,14,4,'021J04'),(10,1,10,4,'020C09'),(10,1,15,4,'052C12');
/*!40000 ALTER TABLE `copy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `doc_id` bigint(20) NOT NULL,
  `c_date` datetime(6) DEFAULT NULL,
  `c_location` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `doc_type` bigint(20) DEFAULT NULL,
  `editor_id` bigint(20) DEFAULT NULL,
  `isbn` bigint(20) DEFAULT NULL,
  `p_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `volume_num` bigint(20) DEFAULT NULL,
  `publisher_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`doc_id`),
  KEY `FKougujunl9t6bmjw71wtqnairx` (`editor_id`),
  KEY `FK7bopjhbrlf99vr3ffs4gm5k87` (`publisher_id`),
  CONSTRAINT `FK7bopjhbrlf99vr3ffs4gm5k87` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`publisher_id`),
  CONSTRAINT `FKougujunl9t6bmjw71wtqnairx` FOREIGN KEY (`editor_id`) REFERENCES `person` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (1,NULL,NULL,1,1,1593952923,'1970-01-01 12:00:00.000000','Alice In Wonderland',NULL,3),(2,NULL,NULL,1,2,5493851992,'2010-05-05 12:00:00.000000','Life of Pi',NULL,2),(3,NULL,NULL,1,3,5898300192,'1952-11-09 12:00:00.000000','The Old Man and The Sea',NULL,5),(4,NULL,NULL,1,4,2493844908,'2016-01-12 12:00:00.000000','The Last Breath',NULL,4),(5,NULL,NULL,1,5,8304921283,'1988-12-25 12:00:00.000000','C Programming Book',NULL,1),(6,NULL,NULL,2,6,NULL,'2019-02-05 12:00:00.000000','ACM Computing Reviews',13,9),(7,NULL,NULL,2,7,NULL,'2015-07-02 12:00:00.000000','Algorithmica',7,7),(8,NULL,NULL,2,8,NULL,'2020-09-04 12:00:00.000000','Applied Artificial Intelligence',3,8),(9,NULL,NULL,2,9,NULL,'2018-12-29 12:00:00.000000','Journal of Cryptology',25,6),(10,NULL,NULL,2,10,NULL,'2019-01-30 12:00:00.000000','IEEE Intelligent Systems',8,11),(11,'2020-03-29 14:00:00.000000','Las Vegas, NV',3,11,NULL,'2020-11-04 12:00:00.000000','DEFCON 2020',NULL,13),(12,'2019-01-20 15:00:00.000000','Redmond, WA',3,12,NULL,'2019-08-23 12:00:00.000000','Cybersecurity & Fraud Summit 2019',NULL,15),(13,'2017-04-04 12:00:00.000000','Los Angeles, CA',3,13,NULL,'2017-07-05 12:00:00.000000','World Congress on Engineering 2017',NULL,10),(14,'2017-02-04 15:00:00.000000','San Jose, CA',3,14,NULL,'2017-10-04 12:00:00.000000','2017 Global Insider Threat Summit',NULL,14),(15,'2020-03-08 17:00:00.000000','Albany, NY',3,15,NULL,'2020-09-27 12:00:00.000000','RSA Conference 2020',NULL,12);
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest_edits`
--

DROP TABLE IF EXISTS `guest_edits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guest_edits` (
  `pid` bigint(20) NOT NULL,
  `doc_id` bigint(20) NOT NULL,
  `issue_no` int(11) NOT NULL,
  PRIMARY KEY (`doc_id`,`issue_no`,`pid`),
  KEY `FKcwtm60mdwg4eh9i3puhuy8pg` (`pid`),
  CONSTRAINT `FK7amhewwf57arrla5wsg6swdf7` FOREIGN KEY (`doc_id`, `issue_no`) REFERENCES `journal_issue` (`doc_id`, `issue_no`),
  CONSTRAINT `FKcwtm60mdwg4eh9i3puhuy8pg` FOREIGN KEY (`pid`) REFERENCES `person` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest_edits`
--

LOCK TABLES `guest_edits` WRITE;
/*!40000 ALTER TABLE `guest_edits` DISABLE KEYS */;
INSERT INTO `guest_edits` VALUES (1,8,2),(2,7,7),(3,6,10),(4,9,5),(5,10,1);
/*!40000 ALTER TABLE `guest_edits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (34);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journal_issue`
--

DROP TABLE IF EXISTS `journal_issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journal_issue` (
  `doc_id` bigint(20) NOT NULL,
  `issue_no` int(11) NOT NULL,
  `scope` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`doc_id`,`issue_no`),
  CONSTRAINT `FKku7r31h19l9uepluen130p1ep` FOREIGN KEY (`doc_id`) REFERENCES `document` (`doc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journal_issue`
--

LOCK TABLES `journal_issue` WRITE;
/*!40000 ALTER TABLE `journal_issue` DISABLE KEYS */;
INSERT INTO `journal_issue` VALUES (6,10,'Computer Science'),(7,7,'Biology'),(8,2,'Chemistry'),(9,5,'English Literature'),(10,1,'History');
/*!40000 ALTER TABLE `journal_issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `pid` bigint(20) NOT NULL,
  `p_name` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Maryjane Torres'),(2,'Carter Davis'),(3,'Anika Bowen'),(4,'Valentina Harmon'),(5,'Lena Marsh'),(6,'Sharon Shaw'),(7,'Jared Curry'),(8,'Erik Pollard'),(9,'Michael Taylor'),(10,'Kallie Wu'),(11,'Randy Cruz'),(12,'Wesley Benitez'),(13,'Troy Haney'),(14,'Javon Wong'),(15,'Tara Perkins'),(16,'Alex Liu'),(17,'Adrian Petty'),(18,'Cecelia Stuart'),(19,'Brent Douglas'),(20,'Adrian Stevenson');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publisher` (
  `publisher_id` bigint(20) NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pub_name` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'8874 Brickell Circle, Oxnard, CA 93035','Penguin Random House'),(2,'474 Woodside Ave., Griffin, GA 30223','Hachette Livre'),(3,'789 Country Club Drive, Carol Stream, IL 60188','Harper Collins'),(4,'8134 Gulf Lane, Port Charlotte, FL 33952','Macmillan Publishers'),(5,'8332 Delaware Street, Brandenton, FL 34203','Simon & Schuster'),(6,'8022 Kingston Street, Starkville, MS 39759','McGraw-Hill Education'),(7,'384 Alton Road, Bayonne, NJ 07002','Houghton Mifflin Harcourt'),(8,'710 Hickory Lane, Lilburn, GA 30047','Pearson Education'),(9,'799 East Queen Street, Lansdowne, PA 19050','Scholastic'),(10,'6 N. Washington Street, Westminster, MD 21157','Cengage Learning'),(11,'403 Rosewood Road, Miami Gardens, FL 33056','Springer Nature'),(12,'188 Fulton Street, Mc Lean, VA 22101','Wiley'),(13,'7330 Wrangler Drive, Vicksburg, MS 39180','Oxford University Press'),(14,'786 Shadow Brook Road, Salt Lake City, UT 84119','Kodansha'),(15,'865 Cherry Hill Avenue, Largo FL 33771','Shueisha'),(16,'8026 Foster Court, Butler, PA 16001','Grupo Santillana'),(17,'682 Amerige Street, Crown Point, IN 46307','Bonnier Books'),(18,'5 Mayflower Street, Johnston, RI 02919','Editis'),(19,'83 Plumb Branch Street, Anderson, SC 29621','Klett'),(20,'39 Yukon Street, Yonkers, NY 10701','Egmont Books');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader`
--

DROP TABLE IF EXISTS `reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reader` (
  `reader_id` bigint(20) NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `phone_no` bigint(20) NOT NULL,
  `reader_name` varchar(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` VALUES (1,'1006 Drummond Street, Newark, NJ 07102',2015958375,'Lindsey Archer',1),(2,'1101 South Beach Avenue, Beach Haven, NJ 08008',2019920400,'Darrel Mullins',1),(3,'391 Waterview Road, Bridgewater, NJ 08807',2014583052,'Alonzo Sellers',1),(4,'2513 Watson Street, Camden NJ 08102	',7325019327,'Cherish Holt',1),(5,'2268 Dark Hollow Road, Penns Neck, NJ 08540',2019123130,'Destiny Glenn',1),(6,'533 Prospect Street, Camden, NJ 08102',5515950385,'Bennett Hubbard',1),(7,'4943 Prospect Street, Laurel Springs, NJ 08021',5512039545,'July Garcia',1),(8,'48 East Wrangler Avenue, Wallingford, CT 06492',7173866522,'Charles Barron',1),(9,'1327 Duke Lane, Dunelien, NJ 08812',7327525105,'Alana Short',1),(10,'1212 Main Street, Belleville, NJ 07109',7326654533,'George Cain',1),(11,'1990 Pooz Street, Long Branch, NJ 07740',7322231367,'Rosa Wilkerson',1),(12,'3137 Beechwood Avenue, Westfield, NJ 07090',5512004036,'Colton Weber',1),(13,'1422 Stonepot Road, Wayne, NJ 07477',9082026100,'Francis Poole',1),(14,'1649 Deer Ridge Drive, Whippany, NJ 07981',6092179509,'Nicolas Austin',1),(15,'941 Webster Street, Lyndhurst, NJ 07071',7327108531,'Annie Shannon',1),(16,'2760 Passaic Street, Wayne, NJ 07477',2012473924,'Alison Hurley',1),(17,'4360 Main Street, Red Bank, NJ 07701',8484664677,'Piper Lane',1),(18,'535 Moonlight Drive, Egg Harbor, NJ 08232',5512002671,'Deborah Le',1),(19,'4225 Broad Street, Wayne, NJ 07477',9084305859,'Riya Wang',1),(20,'1023 Central Avenue, Jersey City, NJ 07304',2012404773,'Logan Mccoy',1),(21,'40 Newport Pkwy Apt309',15512632136,'Jiawei Wang',0);
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_transaction`
--

DROP TABLE IF EXISTS `reservation_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation_transaction` (
  `res_number` bigint(20) NOT NULL,
  `res_date_time` datetime(6) DEFAULT NULL,
  `reservation_status` int(11) NOT NULL,
  `bid` bigint(20) DEFAULT NULL,
  `copy_num` bigint(20) DEFAULT NULL,
  `doc_id` bigint(20) DEFAULT NULL,
  `reader_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`res_number`),
  KEY `FK2j8m13x3uakxqlohoorobktv5` (`bid`,`copy_num`,`doc_id`),
  KEY `FKtreidgibh2sj9ufg4l9jy1esx` (`reader_id`),
  CONSTRAINT `FK2j8m13x3uakxqlohoorobktv5` FOREIGN KEY (`bid`, `copy_num`, `doc_id`) REFERENCES `copy` (`bid`, `copy_num`, `doc_id`),
  CONSTRAINT `FKtreidgibh2sj9ufg4l9jy1esx` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_transaction`
--

LOCK TABLES `reservation_transaction` WRITE;
/*!40000 ALTER TABLE `reservation_transaction` DISABLE KEYS */;
INSERT INTO `reservation_transaction` VALUES (1,'2020-03-25 09:00:01.000000',3,1,1,1,14),(2,'2020-05-05 09:24:14.000000',3,2,1,2,9),(3,'2020-07-20 10:03:52.000000',3,3,1,3,12),(4,'2020-11-02 09:38:59.000000',3,4,1,4,7),(5,'2020-11-25 08:14:42.000000',3,5,1,5,11),(6,'2020-11-26 10:01:27.000000',0,6,1,6,8),(7,'2020-11-29 09:20:14.000000',1,7,1,7,10),(8,'2020-11-29 09:15:56.000000',1,8,1,8,13),(9,'2020-03-28 09:10:01.000000',3,1,1,9,14),(10,'2020-04-02 09:25:01.000000',3,1,1,10,14),(11,'2020-04-25 09:32:01.000000',3,1,1,11,14),(12,'2020-05-23 09:24:14.000000',3,1,1,1,9),(13,'2020-05-28 09:14:14.000000',3,1,1,12,9),(14,'2020-05-31 09:48:14.000000',3,1,1,13,9),(15,'2020-07-22 09:48:14.000000',3,1,1,14,12),(16,'2020-07-23 09:12:01.000000',3,1,1,15,14),(22,'2020-12-10 11:46:34.656000',3,1,1,1,21),(23,'2020-12-10 11:46:40.544000',3,1,2,1,21),(24,'2020-12-10 11:46:47.179000',3,1,3,1,21),(25,'2020-12-10 11:46:52.296000',3,1,4,1,21),(26,'2020-12-10 11:46:58.800000',3,1,5,1,21),(27,'2020-12-10 11:47:04.288000',3,1,6,1,21);
/*!40000 ALTER TABLE `reservation_transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-10 15:37:10
