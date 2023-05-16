-- MySQL dump 10.13  Distrib 8.0.33, for Linux (x86_64)
--
-- Host: localhost    Database: libapp
-- ------------------------------------------------------
-- Server version	8.0.33-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `libapp`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `libapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `libapp`;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(80) NOT NULL,
  `publication_year` int DEFAULT NULL,
  `author` varchar(80) NOT NULL,
  `isbn` varchar(30) DEFAULT NULL,
  `genre` varchar(20) DEFAULT NULL,
  `times_borrowed` int NOT NULL DEFAULT '0',
  `actually_borrowed` tinyint(1) DEFAULT '0',
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'The Upside of Unrequited',2004,'Becky Albertalli','978-6-7205-7458-8','ROMANCE',0,0,0),(2,'Sister Carrie',2011,'Theodore Dreiser','997-6-7715-4178-1','DRAMA',0,0,0),(3,'The City of Brass (The Daevabad Trilogy, Book 1)',2000,'S. A. Chakraborty','878-4-8745-9741-4','MYSTERY',1,1,0),(4,'Spin the Dawn',2014,'Becky Albertalli','914-6-1135-847-3','FANTASY',0,0,0),(5,'The Upside of Unrequited',2004,'Becky Albertalli','978-6-7205-7458-8','ROMANCE',0,0,0),(6,'Romancing Mister Bridgerton: The 2nd Epilogue',2018,'Julia Quinn','914-8-7105-1478-9','ROMANCE',0,0,0),(7,'Harry Potter and the Sorcerer\'s Stone ',1997,'J.K Rowling','148-2-4105-7458-8','MYSTERY',0,0,0),(8,'Harry Potter and the Chamber of Secrets',2001,'J.K Rowling','321-2-4215-745-3','MYSTERY',0,0,0),(9,'Harry Potter and the Sorcerer\'s Stone ',2004,'J.K Rowling','123-4-4235-2358-1','MYSTERY',0,0,0),(10,'Harry Potter and the Prisoner of Azkaban ',2007,'J.K Rowling','230-2-4214-4321-8','MYSTERY',0,0,0),(11,'Harry Potter and the Goblet of Fire',2010,'J.K Rowling','214-2-3345-4521-2','MYSTERY',0,0,0),(12,'Harry Potter and the Order of the Phoenix',2011,'J.K Rowling','178-3-4741-7998-4','MYSTERY',0,0,0),(13,'Harry Potter and the Half-Blood Prince',2014,'J.K Rowling','214-3-8475-1238-8','MYSTERY',0,0,0),(14,'Harry Potter and the Deathly Hallows ',2016,'J.K Rowling','311-2-4105-7347-3','MYSTERY',0,0,0),(15,'The Hunger Games',2005,'Suzanne Collins','112-6-2225-4538-6','DYSTOPIAN',0,0,0),(16,'The Last Olympian',2007,'Rick Riordan','218-4-7223-7221-8','DYSTOPIAN',0,0,0),(17,'Follow You Home',2011,'Mark Edwards','921-4-1234-8563-2','HORROR',0,0,0),(18,'The Girl in The Tower',2020,'Katherine Arden','112-6-4405-2528-8','FICTION',0,0,0),(19,'Hunted',2018,'Meagan Spooner','214-6-7505-8758-8','ROMANCE',0,0,0),(20,'The Wonderful Wizard of Oz',2014,'L Frank Baum','221-6-7331-3218-8','FICTION',0,0,0),(21,'Never Never',2019,'Colleen Hoover','123-4-5651-8748-8','FICTION',0,0,0),(22,'No Country for Old Men',2015,'Cormac McCarthy','221-6-4405-2384-1','WESTERN',0,0,0),(23,'Lonesome Dove',2004,'Larry McMurthy','312-3-4215-2218-4','WESTERN',0,0,0),(24,'It',1986,'Stepheng King','2134-1-2145-7548-6','HORROR',0,0,0),(25,'A Brief History of Time',1988,'Stephen Hawking','984-1-3345-3154-7','SCIENCE',0,0,0),(26,'The Big Plan',1997,'Stephen Hawking','921-4-5525-1128-1','SCIENCE',0,0,0),(27,'Lost Roses',2014,'Martha Hall Kelly','114-2-4256-3144-1','FICTION',0,0,0),(28,'Last Breath',2000,'Robert Bryndza','222-3-4425-2128-4','THRILLER',1,1,1),(29,'Connections in Death',2008,'J. D. Robb','3311-2-3135-3318-6','THRILLER',0,0,0),(30,'Gut',2016,'Giulia Enders','9511-1-3145-1124-4','SCIENCE',0,0,0),(31,'Hidden Figures',2011,'Margot Lee Shetterly','1142-1-4321-3451-3','SCIENCE',0,0,0),(32,'Tricky Twenty-Two',2006,'Janet Evanovich','223-2-2415-1348-1','FICTION',0,0,0);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrows`
--

DROP TABLE IF EXISTS `borrows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrows` (
  `borrow_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `book_id` int NOT NULL,
  `borrowing_day` date NOT NULL,
  `expected_return` date NOT NULL,
  `return_date` date DEFAULT NULL,
  PRIMARY KEY (`borrow_id`),
  KEY `book_id` (`book_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `borrows_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `borrows_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `libuser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrows`
--

LOCK TABLES `borrows` WRITE;
/*!40000 ALTER TABLE `borrows` DISABLE KEYS */;
INSERT INTO `borrows` VALUES (1,12,28,'2023-05-11','2023-05-31',NULL),(2,10,3,'2023-05-11','2023-05-31',NULL);
/*!40000 ALTER TABLE `borrows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libadmin`
--

DROP TABLE IF EXISTS `libadmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libadmin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libadmin`
--

LOCK TABLES `libadmin` WRITE;
/*!40000 ALTER TABLE `libadmin` DISABLE KEYS */;
INSERT INTO `libadmin` VALUES (1,'administrator','admin@admin.com','$2a$04$7twssMrly4FMzpvYlg8SK.gkrPo.vAhL0FKxdOi/SI1e8OJmcH0xe');
/*!40000 ALTER TABLE `libadmin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libuser`
--

DROP TABLE IF EXISTS `libuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libuser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `address` varchar(70) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `email` varchar(70) NOT NULL,
  `password` varchar(200) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libuser`
--

LOCK TABLES `libuser` WRITE;
/*!40000 ALTER TABLE `libuser` DISABLE KEYS */;
INSERT INTO `libuser` VALUES (1,'Lynsey','K Lopez','1994-04-29','female','Virginia, 2447 Murry Street','757-498-7769','lopez23@gmail.com','712a8adeee560dd204ce4cf667fac619',1),(2,'Linda','J Boden','1997-01-09','female','Illinois, 4323 Jadewood Drive','857-435-9069','linda8714@gmail.com','872a8dasdas560dd244ce4cf61c619',1),(3,'Lester','T Heller','2000-09-01','male','West Virginia, 3614 Augusta Park','978-218-4714','lesterxhellerx@gmail.com','f5fd41448f25b88g4cevdf5e574',1),(4,'Joshua','J Jordan','1974-11-20','male','New York, 1400 Cantebury Drive','214-368-1147','jordan142@gmail.com','241vf111dasdasterty22147529t874rf17',1),(5,'Norma','R Calloway','2001-11-04','female','Oklahoma, Luke Lane 1911','421-118-8879','norma9785@gmail.com','785fds21548eqwnbm12587hyt8528yhrt8225r',1),(6,'Christopher','J Shaw','1988-12-31','male','North Carolina, 4127 Green Road','421-543-8419','jshawchristoperx12@gmail.com','1146fdfsfsd338985das96325gfd',1),(7,'Rhonda','C Jones','1975-07-19','female','Birmingham, 1 Flower Street','874-214-1478','joneswhoisronda14@gmail.com','4174fds24sad558741fgsdgdf12fsd5555fds',1),(8,'Kristi','J Jackson','2002-08-11','female','Florida, 4014 Boundary Street','71-498-8869','kristi2002@gmail.com','441fds121748748545fds12154dsfsdvdb0021784',1),(9,'Michael','N Gray','1986-03-24','male','Florida, 8741 Main Street','123-945-3047','michael8gray@gmail.com','4fsd45ewqeddsgv2222b5df899y5uty52ngh892g9b8fg',1),(10,'Anna','Filippio','2003-11-04','female','Atlanta, 8147 Atlanta Drive','917-048-7069','filipanna147523@gmail.com','7197btg8r79f8e4we6561r48234r4564t6gt4897',1),(11,'Joseph','J Miller','2004-07-17','male','Louisiana, 1147 Bassel Street','874-445-951','josi414mill@gmail.com','14fsd403r242350237812370gr0ger0ev79',1),(12,'Daniel','Nagy','1998-01-21','male','Bratislava, 017 28 , Trencianska 26','+421905990012','nagydani120@gmail.com','$2a$04$YD5t7kOtLtd7xpZtjspa.OAgOCjqY6glN3rpUIVj.IaTDbhMA8SbS',1);
/*!40000 ALTER TABLE `libuser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-13  0:35:52
