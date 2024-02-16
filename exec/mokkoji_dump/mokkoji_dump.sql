-- MySQL dump 10.13  Distrib 8.0.35, for Linux (x86_64)
--
-- Host: localhost    Database: mokkoji
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `account_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `bank` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `number` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UK_h6dr47em6vg85yuwt4e2roca4` (`user_id`),
  CONSTRAINT `FK7m8ru44m93ukyb61dfxw0apf6` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,'카카오뱅크','2222111122222'),(2,2,'SC제일','123123123'),(3,3,'KB','01298301982'),(4,4,'KB','944502-00-229145'),(5,5,'KB','111'),(6,6,'KB','123456789'),(7,7,'',''),(8,8,'',''),(9,9,'',''),(10,11,'',''),(11,12,'','');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_seq`
--

DROP TABLE IF EXISTS `account_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_seq`
--

LOCK TABLES `account_seq` WRITE;
/*!40000 ALTER TABLE `account_seq` DISABLE KEYS */;
INSERT INTO `account_seq` VALUES (1);
/*!40000 ALTER TABLE `account_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `background_template`
--

DROP TABLE IF EXISTS `background_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `background_template` (
  `background_id` int NOT NULL,
  `background_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `background_name` enum('BASIC','WEDDING','SCHOOL','LUNAR','BABY') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`background_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `background_template`
--

LOCK TABLES `background_template` WRITE;
/*!40000 ALTER TABLE `background_template` DISABLE KEYS */;
INSERT INTO `background_template` VALUES (1,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EB%B0%B0%EA%B2%BD%EC%A7%80/rolling_basic.png','BASIC'),(2,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EB%B0%B0%EA%B2%BD%EC%A7%80/rolling_wedding.png','WEDDING'),(3,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EB%B0%B0%EA%B2%BD%EC%A7%80/rolling_school.png','SCHOOL'),(4,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EB%B0%B0%EA%B2%BD%EC%A7%80/rolling_baby.png','BABY'),(5,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EB%B0%B0%EA%B2%BD%EC%A7%80/rolling_lunar.png','LUNAR');
/*!40000 ALTER TABLE `background_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `background_template_seq`
--

DROP TABLE IF EXISTS `background_template_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `background_template_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `background_template_seq`
--

LOCK TABLES `background_template_seq` WRITE;
/*!40000 ALTER TABLE `background_template_seq` DISABLE KEYS */;
INSERT INTO `background_template_seq` VALUES (1);
/*!40000 ALTER TABLE `background_template_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `participant_count` int DEFAULT '0',
  `end_time` datetime(6) DEFAULT NULL,
  `event_id` bigint NOT NULL AUTO_INCREMENT,
  `start_time` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `session_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` enum('ACTIVE','CLOSED') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `FKi8bsvlthqr8lngsyshiqsodak` (`user_id`),
  CONSTRAINT `FKi8bsvlthqr8lngsyshiqsodak` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (2,'2024-02-15 15:02:23.185563',2,'2024-02-15 14:57:23.582783',1,'ses_Z05L2FyxeX','CLOSED'),(2,'2024-02-15 15:15:58.566083',3,'2024-02-15 15:04:51.962630',1,'ses_W8sGARcKrA','CLOSED'),(2,'2024-02-15 15:24:44.498189',6,'2024-02-15 15:16:50.689014',2,'ses_IcHkzuLNry','CLOSED'),(2,'2024-02-15 15:37:08.753466',15,'2024-02-15 15:33:23.719379',1,'ses_Vw6aSRdM8v','CLOSED'),(1,'2024-02-15 15:52:45.316750',17,'2024-02-15 15:49:59.463801',1,'ses_FCeh2adjXr','CLOSED'),(1,'2024-02-15 15:54:03.035427',18,'2024-02-15 15:53:56.566677',1,'ses_FLmDBtpepA','CLOSED'),(6,'2024-02-15 16:26:02.104238',19,'2024-02-15 16:17:55.837756',4,'ses_OS7tWy41YS','CLOSED'),(1,'2024-02-15 16:50:31.449862',21,'2024-02-15 16:49:28.414079',7,'ses_VenXZIJ0Jz','CLOSED'),(1,'2024-02-15 17:01:20.109796',22,'2024-02-15 17:00:09.174433',4,'ses_SHXcm6CuKS','CLOSED'),(1,'2024-02-15 17:11:43.760990',23,'2024-02-15 17:11:31.828284',5,'ses_IeJoqjSNX7','CLOSED'),(1,'2024-02-15 17:29:51.102334',24,'2024-02-15 17:24:49.502045',4,'ses_WdZODIlq5Y','CLOSED'),(1,'2024-02-15 17:34:23.430211',25,'2024-02-15 17:33:55.251482',2,'ses_PcItmpG06V','CLOSED'),(1,'2024-02-15 17:50:51.179134',26,'2024-02-15 17:50:44.502382',2,'ses_YvYMNXbBME','CLOSED'),(1,'2024-02-15 17:50:56.504261',27,'2024-02-15 17:50:52.313874',2,'ses_EVGqrLQ6O1','CLOSED'),(1,'2024-02-15 17:51:02.756518',28,'2024-02-15 17:50:57.939903',2,'ses_Xp1fZysOJl','CLOSED'),(1,'2024-02-15 17:51:09.712214',29,'2024-02-15 17:51:03.867855',2,'ses_K1U7g8opAy','CLOSED'),(1,'2024-02-15 20:23:43.357829',30,'2024-02-15 20:23:38.698532',9,'ses_UldXyyUPK1','CLOSED'),(0,NULL,31,'2024-02-15 20:32:10.036014',2,'ses_TpALroVzEL','ACTIVE'),(0,NULL,37,'2024-02-15 20:57:01.085444',1,'ses_K0LEayAKpq','ACTIVE'),(0,NULL,39,'2024-02-15 20:58:24.847811',1,'ses_Rl4UNIV6Jr','ACTIVE'),(1,'2024-02-15 21:01:08.221226',40,'2024-02-15 21:01:04.307534',1,'ses_Pj5YTU6ODQ','CLOSED'),(2,'2024-02-15 21:08:02.198041',41,'2024-02-15 21:02:17.902380',1,'ses_KGfiWqoNWH','CLOSED'),(1,'2024-02-15 21:09:39.287219',43,'2024-02-15 21:09:05.510691',2,'ses_VLzuM24nrB','CLOSED'),(1,'2024-02-15 21:12:07.378923',44,'2024-02-15 21:12:05.627262',9,'ses_L289TRSbBl','CLOSED'),(1,'2024-02-15 21:12:17.756604',45,'2024-02-15 21:12:16.373759',9,'ses_WOweO0ogtw','CLOSED'),(6,'2024-02-15 21:31:21.076936',48,'2024-02-15 21:25:35.542838',1,'ses_GZXS4a0pQT','CLOSED'),(0,NULL,52,'2024-02-15 21:47:50.579794',1,'ses_IUXMCWixBe','ACTIVE'),(1,'2024-02-15 22:00:16.346944',58,'2024-02-15 22:00:15.078444',9,'ses_T0m6ejPLS5','CLOSED'),(0,NULL,59,'2024-02-15 22:01:53.153932',10,'ses_IUV0QQf8oz','ACTIVE'),(0,NULL,60,'2024-02-15 22:02:39.867561',11,'ses_FQ2FX2NS7O','ACTIVE'),(1,'2024-02-15 22:04:40.058385',62,'2024-02-15 22:04:34.342485',2,'ses_YRfBTklDZF','CLOSED'),(1,'2024-02-15 22:15:33.514971',65,'2024-02-15 22:15:32.124401',9,'ses_OFvueZ1TTK','CLOSED'),(1,'2024-02-15 22:49:09.455022',71,'2024-02-15 22:45:03.396858',2,'ses_Eu0cVuN0z2','CLOSED'),(1,'2024-02-15 23:03:09.023237',73,'2024-02-15 23:02:40.923159',9,'ses_FHAFkHwWXR','CLOSED'),(1,'2024-02-15 23:04:07.593277',75,'2024-02-15 23:03:40.252047',9,'ses_S8AzzY3NXw','CLOSED'),(1,'2024-02-16 00:23:47.630423',84,'2024-02-16 00:23:45.200045',10,'ses_OHo1TGlF0C','CLOSED'),(1,'2024-02-16 00:23:52.452746',85,'2024-02-16 00:23:50.651697',10,'ses_OcuI3IWlFO','CLOSED'),(1,'2024-02-16 01:05:39.513053',95,'2024-02-16 01:05:37.804303',1,'ses_XGt0UE6Mfb','CLOSED'),(0,NULL,97,'2024-02-16 01:07:51.098448',12,'ses_ZpvOzSx6fm','ACTIVE'),(4,'2024-02-16 01:32:01.522636',99,'2024-02-16 01:13:14.854990',3,'ses_JE0z5yZWtv','CLOSED'),(1,'2024-02-16 01:13:47.434006',100,'2024-02-16 01:13:43.605610',1,'ses_ESy7bBjFVU','CLOSED'),(3,'2024-02-16 01:41:31.352331',101,'2024-02-16 01:32:10.875370',3,'ses_ICcWVOcIbg','CLOSED'),(3,'2024-02-16 01:45:31.291022',102,'2024-02-16 01:41:34.704712',3,'ses_DluOLqzwwL','CLOSED'),(3,'2024-02-16 01:47:12.738686',103,'2024-02-16 01:45:32.673720',3,'ses_NuTo8XBr9v','CLOSED'),(18,'2024-02-16 01:53:01.574988',104,'2024-02-16 01:47:15.578605',3,'ses_EztSjJD3ZY','CLOSED'),(0,NULL,105,'2024-02-16 01:53:19.230245',3,'ses_Foiqy2YEdu','ACTIVE'),(2,'2024-02-16 01:55:24.525288',106,'2024-02-16 01:54:59.211895',3,'ses_UKxeBrdvcj','CLOSED'),(1,'2024-02-16 01:55:34.634751',107,'2024-02-16 01:55:27.874841',3,'ses_I0vmsqJqra','CLOSED'),(1,'2024-02-16 01:55:37.974635',108,'2024-02-16 01:55:36.237491',3,'ses_MzQ90tB6pP','CLOSED'),(1,'2024-02-16 01:55:40.674547',109,'2024-02-16 01:55:38.955700',3,'ses_I4qDdRjZYR','CLOSED'),(2,'2024-02-16 02:26:30.446480',110,'2024-02-16 02:22:17.839692',3,'ses_YuzNUEEgH6','CLOSED'),(1,'2024-02-16 02:54:15.060156',111,'2024-02-16 02:52:51.283775',3,'ses_RClqSn17Qd','CLOSED'),(0,NULL,112,'2024-02-16 02:54:30.301228',3,'ses_P63UKiq0gF','ACTIVE'),(0,NULL,113,'2024-02-16 03:12:59.577388',3,'ses_WogVJc3Z88','ACTIVE'),(0,NULL,114,'2024-02-16 03:13:02.769575',3,'ses_B1EW5AU5Q5','ACTIVE');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_seq`
--

DROP TABLE IF EXISTS `event_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_seq`
--

LOCK TABLES `event_seq` WRITE;
/*!40000 ALTER TABLE `event_seq` DISABLE KEYS */;
INSERT INTO `event_seq` VALUES (801);
/*!40000 ALTER TABLE `event_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `writer` varchar(6) COLLATE utf8mb4_unicode_ci NOT NULL,
  `message_id` bigint NOT NULL AUTO_INCREMENT,
  `rollingpaper_id` bigint DEFAULT NULL,
  `text` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `video_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `voice_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FKg8eesqg0by9msqj7wy8i9h0hp` (`rollingpaper_id`),
  CONSTRAINT `FKg8eesqg0by9msqj7wy8i9h0hp` FOREIGN KEY (`rollingpaper_id`) REFERENCES `rolling_paper` (`rollingpaper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES ('이정민',2,2,'ㅋㅋㅋㅋㅋㅋㅋㅋ생일 축하해',NULL,NULL),('이정민',3,2,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/3/rollingpaper/voi_8179113d-7814-4187-824e-6706bdffe4d4.webm'),('이정민',4,2,'히히 생선 뭐받고 싶어!!','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/3/rollingpaper/vid_8b3ba325-cf38-420e-b406-eb953f7de873.mp4',NULL),('이정민',5,2,'바보야 생일 추카해',NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/3/rollingpaper/voi_df5dbd83-86b2-45e2-bb27-527b56e736a3.webm'),('이정민',6,2,'333',NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/3/rollingpaper/voi_3d2841a2-712f-45f2-9242-46242f146645.webm'),('이정민',7,2,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/3/rollingpaper/vid_76c29654-0a3d-43c1-9884-aae78b20d7a0.mp4','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/3/rollingpaper/voi_0576f062-6459-4f1b-b35c-a8c11665d459.webm'),('이정민',8,2,'세개','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/3/rollingpaper/vid_106535d2-d05b-4194-a950-179f268f3482.mp4','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/3/rollingpaper/voi_f2706bc1-7f10-4d05-9121-0f6a70f6eed6.webm'),('한재훈',9,1017,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/1017/rollingpaper/vid_9c1075b9-c426-4cd0-89e3-79e55589e916.mp4',NULL),('김지은',10,3,NULL,NULL,NULL),('김지은',11,3,'정민아 졸업 축하해~!~!',NULL,NULL),('김지은',12,3,'정민아 졸업 축하해~!~!',NULL,NULL),('김지은',13,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/6/rollingpaper/voi_25f2179b-7abd-4356-be1e-7474c96a5853.webm'),('김지은',14,3,'정민아 졸업 축하해~!~!',NULL,NULL),('김지은',15,3,'정민아 졸업 축하해~!~!',NULL,NULL),('김지은',16,3,'정민이 ',NULL,NULL),('김지은',17,3,NULL,NULL,NULL),('김지은',18,3,NULL,NULL,NULL),('김지은',19,3,NULL,NULL,NULL),('김지은',20,3,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/6/rollingpaper/vid_9b8b21d0-e1ab-4616-8b90-800e97c29d46.mp4',NULL),('김지은',21,3,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/6/rollingpaper/vid_c90d161f-d388-4bdf-9cc3-57b3e5f49dda.mp4',NULL),('김지은',22,3,'정민이 밥오',NULL,NULL),('김지은',23,3,'정민이 밥오',NULL,NULL),('김지은',24,3,'정민아 취직하면 밥쏴',NULL,NULL),('김지은',25,3,'정민아 취직하면 밥쏴ㅎㅎㅎㅎ','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/6/rollingpaper/vid_80de768a-2020-42a4-afe4-4d8743651476.mp4',NULL),('김지은',26,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/6/rollingpaper/voi_bc86b132-2a6e-4d19-b32f-00101b77b2b9.webm'),('김지은',27,3,'정민아.....벌써 졸업이라니..',NULL,NULL),('김지은',28,3,'가지마 정민',NULL,NULL),('김지은',29,3,'ㅋㅋㅋㅋㅋㅋㅋ나도 같이 졸업한다','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/6/rollingpaper/vid_12517170-b7bd-4433-a559-492313246502.mp4',NULL),('김지은',30,3,'ㅂㅂㅂㅂㅂㅂㅂ',NULL,NULL),('김지은',31,3,'아 거의 다 끝나가네ㅠ 아쉽다',NULL,NULL),('김지은',32,3,'안뇽 재밌었어',NULL,NULL),('이정민',33,4,'억만장자 된 거 축하해~!!',NULL,NULL),('이정민',34,4,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/15/rollingpaper/vid_1e70c594-1a96-4511-a3ff-2513977d4156.mp4',NULL),('이정민',35,4,'우헤헤 나 밥 사줘',NULL,NULL),('이정민',36,4,'맛있는 거 사줘',NULL,NULL),('조용운',37,152,'안녕 2월 15일이야 자고싶어',NULL,NULL),('한재훈',38,152,'안녕 오랜만이네\n얼굴이 더 좋아졌다.\n잘지내 행복해',NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/4/19/rollingpaper/voi_24c288f5-00ff-4142-a26c-84d94fd19d2b.webm'),('한재훈',39,152,NULL,NULL,NULL),('한재훈',40,152,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/4/19/rollingpaper/voi_12a4ec46-727e-4d23-b068-356cf7a2f9c3.webm'),('test',41,202,'롤링페이퍼 테스트',NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/7/21/rollingpaper/voi_5d5dd8e2-ec65-4f8d-8ccb-061dd2816640.webm'),('한재훈',42,203,'나한테 쓰는 편지\n싸탈해라',NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/4/22/rollingpaper/voi_9b1e2801-cbe8-47fc-bbac-2964a63abd58.webm'),('이정민',43,206,'편지예시',NULL,NULL),('이정민',44,212,'생일 축하해~~~ 맛난 거 많이 먹고 오늘은 행복해라 ㅎㅎㅎㅎㅎ너가 최고야~~!!!',NULL,NULL),('이정민',45,212,'생일 축하해~~~ 맛난 거 많이 먹고 오늘은 행복해라 ㅎㅎㅎㅎㅎ너가 최고야~~!!!',NULL,NULL),('이정민',46,219,'롤링페이퍼 메시지 작성',NULL,NULL),('이정민',47,219,'메시지 작성',NULL,NULL),('이정민',48,219,'메시지메시지',NULL,NULL),('이정민',49,219,'메시지를 입력하세요',NULL,NULL),('한재훈',50,229,NULL,NULL,NULL),('한재훈',51,229,NULL,NULL,NULL),('한재훈',52,230,NULL,NULL,NULL),('한재훈',53,230,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/68/rollingpaper/vid_d4a1c85c-a0e0-4838-a7d7-a48a386ad5fa.mp4',NULL),('이정민',54,233,'안녕하세요, 롤링페이퍼님 작성되어주세요',NULL,NULL),('이정민',55,233,'롤링페이퍼님 작동해주세요',NULL,NULL),('이정민',56,233,'내일이면 끝이래요~~~신난당',NULL,NULL),('이정민',57,233,'모꼬지 화이팅!',NULL,NULL),('이정민',58,233,'편지 생성하기',NULL,NULL),('이정민',59,233,'메시지 입력하기',NULL,NULL),('한재훈',60,307,'안녕하세요. 저는 한재훈입니다.','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/93/rollingpaper/vid_ec1acae3-dd8a-4b33-832c-2da0e136979c.mp4','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/93/rollingpaper/voi_73d068d7-db26-4ff6-946c-5a8d4ad33592.webm'),('김지은',61,312,'재훈오빠 그동안 행복했고.....힘내....가서 잘 지낼거야',NULL,NULL),('박철준',62,312,'파이팅',NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/rollingpaper/voi_e4ead5e6-2de7-40e8-b0c5-1942a9b5d4e9.webm'),('이경배',63,312,'재훈아, 579일 후에나 만날 수 있겠구나...\n건강 조심하고 삽질 열심히 하렴.\n오늘부터 너 믿고 발 뻗고 편히 잘 수 있겠다.\n이병 한재훈 충! 떵! ^^7',NULL,NULL),('조용운',64,312,'이등병의 편지 들으면서 가',NULL,NULL),('이정민',65,312,'잘 가!! 행복해!!',NULL,NULL),('조용운',66,312,'재훈아 국가를 위해 큰결심을 하다니 매우 자랑스럽구나.. 말뚝까지 박고 행복하게 살아',NULL,NULL),('이경배',67,312,'브금 선물','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/rollingpaper/vid_6b87d795-35b0-41cd-abec-51d371a968cd.mp4',NULL),('김광석',68,312,'재훈아...그동안 즐거웠고 행복하게 살아...',NULL,NULL),('한재현',69,312,'재훈아, 아빠다. 우리 아들이 재입대를 선택할 줄은 꿈에도 몰랐구나. 엄마는 걱정 한가득 안고 울고 계신단다. 그치만 아빠는 듬직한 아들이 자랑스럽구나. \n휴가 나올 때마다 px 꼭 들려오길 바란다.',NULL,NULL),('한재희',70,312,'내년에 보자고',NULL,NULL),('김지예',71,312,'재훈 오빠, 저 지예에요...\n건너 건너 재입대 하신다고 들었어요 ㅠㅠㅠ\n면회 자주 갈게요. 꼭 어디로 갔는지 연락줘요!!',NULL,NULL),('김범창',72,312,'재훈오빠 나버리고 군대로 튀면은 못찾을거같아? 지옥끝까지 쫓아갈거야',NULL,NULL),('양을필',73,312,'잘가고 달팽이크림 보내줘','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/rollingpaper/vid_9e442665-c53e-4eaa-a861-21729113c0e8.mp4','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/rollingpaper/voi_509be894-da45-476b-aa8d-364ce5e13054.webm'),('이경배',74,312,'이야 우리 재훈이 마이 컸네. 선임한테 뺨 맞고 광광 울면서 전화하던 한재훈 맞냐? 가슴이 웅장해진다. 요즘 월급도 많이 올랐는데 자주 나와서 나 밥 좀 사줘라.',NULL,NULL),('조용운',75,312,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/rollingpaper/vid_636962ff-23ca-469b-ab2a-bbb9062c99f9.mp4',NULL),('한재훈',76,314,'결혼 축하해!!!',NULL,NULL),('이경배',77,314,'재수씨, 우리 철부지 잘부탁합니다.',NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/rollingpaper/voi_5c8b7d01-7531-4b92-8142-bd530665cf22.webm'),('조용운',78,314,'결혼까지 생각했어','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/rollingpaper/vid_b56c6c80-b661-42e6-a4c2-19b99220bcd9.mp4',NULL),('조용운',79,314,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/rollingpaper/vid_eae7379f-0491-47cb-9052-e58af62f998c.mp4',NULL),('조용운',80,314,'재훈아 결혼축하해~~~~~',NULL,NULL),('이경배',81,314,'결혼을 축하드립니다. 아름다운 두 사람의 앞 날에 행복만 가득하길 진심으로 축복합니다.',NULL,NULL),('한재훈',82,314,'결 혼 축 하 해',NULL,NULL),('한재훈',83,314,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/rollingpaper/vid_d3c1d5f2-b68a-4155-a263-2a48a76d75e3.mp4',NULL),('한재훈',84,314,'다음 결혼도 모꼬지에서 ㄱㄱㄱ','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/rollingpaper/vid_a2f3a196-e270-4bd7-9d89-dbbfb3ebcd2d.mp4','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/rollingpaper/voi_92ad2532-c755-4a4d-a629-45695d9e0bc8.webm'),('한재훈',85,314,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/rollingpaper/vid_c4d6797b-eea7-4ae2-a0fb-7ac83477ac31.mp4','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/rollingpaper/voi_2fd1823c-fe17-4ba7-8dcf-0af87473cab6.webm'),('한재훈',86,314,'축의금 10원냈어 축하해!',NULL,NULL),('조용운',87,314,'재훈아 결혼식에 너무 자주 부르는거 아니니?',NULL,NULL),('한재훈',88,314,'뷔페 밥 맛있냐','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/rollingpaper/vid_8eb68d57-69e5-42f5-8753-b8b02aa638fc.mp4',NULL),('한재훈',89,314,'재훈아 축하하고 내년에 만나자',NULL,NULL),('한재훈',90,314,'행복하게 살고 잘 지내',NULL,NULL),('한재훈',91,314,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/rollingpaper/vid_96eb709e-9dd7-46ce-a57b-901969dc57ff.mp4',NULL),('조용운',92,314,'옥지얌 사랑행','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/rollingpaper/vid_3d157b30-9003-4f4f-aedb-85a47be8de5c.mp4',NULL),('이경배',93,314,'재훈아, 복에 겨운 와이프 분을 만났구나. 너랑 클럽을 그렇게 다니면서 너만큼은 결혼은 못할 거라 생각했는데, 지금까지 봐온 모습 중에 오늘 제일 행복해 보이더라. 건실하게 잘 살아, 인마. 클럽도 이제 그만 다니고 ㅋㅋ 축하한다',NULL,NULL),('한재훈',94,315,'생일 축하해~!!',NULL,NULL);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_seq`
--

DROP TABLE IF EXISTS `message_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_seq`
--

LOCK TABLES `message_seq` WRITE;
/*!40000 ALTER TABLE `message_seq` DISABLE KEYS */;
INSERT INTO `message_seq` VALUES (1);
/*!40000 ALTER TABLE `message_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `photo` (
  `photo_id` bigint NOT NULL AUTO_INCREMENT,
  `result_id` bigint DEFAULT NULL,
  `photo_path` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`photo_id`),
  KEY `FKl3a4d5y45xa4k8iqtuafpbjx5` (`result_id`),
  CONSTRAINT `FKl3a4d5y45xa4k8iqtuafpbjx5` FOREIGN KEY (`result_id`) REFERENCES `result` (`result_id`)
) ENGINE=InnoDB AUTO_INCREMENT=527 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO `photo` VALUES (1,2,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/2/photos/photoList/pic_c6239aff-60df-4c62-858a-7ca26638b498.png'),(2,3,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/3/photos/photoList/pic_b32ea983-236d-46c2-9a93-d62f8903ebd7.png'),(3,4,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/4/photos/photoList/pic_044947eb-db37-4495-8519-9710847bbe6f.png'),(4,5,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/5/photos/photoList/pic_ee51d103-17f0-4947-a359-cb1f80a4398c.png'),(6,5,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/5/photos/photoList/pic_5be2b0bd-189c-4581-9311-8a5cdb9d5273.png'),(7,7,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/7/photos/photoList/pic_24fe466f-2d26-4a74-949b-c0376b1427b4.png'),(8,12,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/12/photos/photoList/pic_33c4ee65-662d-47ae-bf97-8de4192f8b6e.png'),(9,12,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/12/photos/photoList/pic_1064ca2d-49d5-4b04-bfb6-215b81d2afbe.png'),(10,6,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/6/photos/photoList/pic_47a32782-06c9-4abb-948c-96ea5d9c9ee5.jpg'),(12,15,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/15/photos/photoList/pic_5b9289a9-98a8-4c23-9e04-b81aca5f5d62.png'),(13,15,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/15/photos/photoList/pic_1ca01dbc-22d1-4baa-83bd-ca421f618720.png'),(14,15,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/15/photos/photoList/pic_5ce56762-3028-4db3-965e-d2307d63b6ee.png'),(15,15,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/15/photos/photoList/pic_31bc5398-5acf-4bd3-a43b-eb30393ce578.png'),(16,15,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/15/photos/photoList/pic_d3918c86-3df3-4762-8560-4f7ff46aaa20.png'),(17,16,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/16/photos/photoList/pic_32fc16ce-7029-4277-b158-0b91ecd333e3.png'),(18,16,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/16/photos/photoList/pic_0f37c5ae-6ebe-424a-b27c-cd6f72ce182c.png'),(19,16,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/16/photos/photoList/pic_2414a2b1-17f1-42fd-b3eb-cbf2c08feaa5.png'),(20,16,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/16/photos/photoList/pic_9ccc1145-26a6-4d2b-a6c8-4a2628d21016.png'),(33,19,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/4/19/photos/photoList/pic_6213b04b-2e78-4cf0-849e-afead16564ed.png'),(34,21,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/7/21/photos/photoList/pic_f9b57655-7043-4f84-91f3-e629ae2c1aaa.png'),(41,22,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/4/22/photos/photoList/pic_71fbf3ec-3993-4528-972e-359078074f62.png'),(214,25,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/25/photos/photoList/pic_3869993f-086b-49df-b676-3f5db72029d8.png'),(215,34,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/34/photos/photoList/pic_e56f4b01-5115-43d3-bfbb-c8a9baa49a75.png'),(216,34,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/34/photos/photoList/pic_266dcca0-a890-48de-8a75-a6fea563828e.png'),(217,34,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/34/photos/photoList/pic_8fb168e8-eaea-4419-a366-f8f184e04def.png'),(218,34,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/34/photos/photoList/pic_5d442a3a-e95a-44a3-b4fc-a222fec18631.png'),(219,34,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/34/photos/photoList/pic_3279d4aa-faa1-4816-9036-70f837611467.png'),(220,34,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/34/photos/photoList/pic_f540a254-e4d5-4899-ad4e-4d592e62e136.png'),(221,36,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/36/photos/photoList/pic_74e8b043-1596-45fa-96b5-5cae344a81e3.png'),(222,36,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/36/photos/photoList/pic_0574b59a-7410-4188-8b5e-d735f4223def.png'),(223,36,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/36/photos/photoList/pic_857f63ee-30bc-4b9e-9916-7a4fc087a773.png'),(224,36,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/36/photos/photoList/pic_1c3b5fcb-aaba-4891-9e20-ec955bb3058c.png'),(225,36,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/36/photos/photoList/pic_a6932067-4c35-4193-9779-720131cbf622.png'),(226,37,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/37/photos/photoList/pic_d3ac3159-42a7-4adf-8de5-f15d37620620.png'),(227,39,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/39/photos/photoList/pic_c901790f-1ce1-40c6-a7bd-49015b37aa7d.png'),(228,39,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/39/photos/photoList/pic_e982a072-e5be-49b4-9aad-a91d967baf01.png'),(229,38,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/38/photos/photoList/pic_f6fe2556-4503-41e2-99a1-fec12b3a693f.png'),(231,27,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/27/photos/photoList/pic_408b5932-0b64-41ca-b0d2-1b15ba105552.jpg'),(234,41,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/41/photos/photoList/pic_0c414668-bae5-4698-a61e-eece97ab1244.png'),(235,41,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/41/photos/photoList/pic_7a0f0620-8a03-4e02-b827-a3faf86a15dc.png'),(236,41,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/41/photos/photoList/pic_ed8394d5-c523-43bd-8732-799bcf96c3a8.png'),(237,41,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/41/photos/photoList/pic_91b1187d-c0ec-411f-8fad-e0635a22797e.png'),(238,41,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/41/photos/photoList/pic_fd3008fd-3623-440a-95d7-5804095b22ac.png'),(239,42,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/42/photos/photoList/pic_a9048382-5665-47ba-961d-61e6ea5b61a7.png'),(240,42,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/42/photos/photoList/pic_4db43b94-76e1-4ef8-aa2e-05df52a49f58.png'),(241,42,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/42/photos/photoList/pic_f4b24549-6685-42c7-8da2-8494fa45fd23.png'),(259,42,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/42/photos/photoList/pic_d3e1ec63-4319-4d6a-a37d-1ab678559ed5.png'),(260,42,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/42/photos/photoList/pic_a02490fb-6cd6-4f81-8698-b64c66ce27c1.png'),(261,42,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/42/photos/photoList/pic_32962568-4f6b-4312-8dab-7f63fae53c1c.png'),(262,42,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/42/photos/photoList/pic_3592b052-18d6-4a4b-9980-4d9217ac7ae0.png'),(266,43,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/43/photos/photoList/pic_0619e698-c940-410c-8b7e-fb913366bb77.jpg'),(273,42,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/42/photos/photoList/pic_0a5fb035-64ea-4731-90ba-66e3a566a385.png'),(274,42,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/42/photos/photoList/pic_c986c99c-623e-4e5e-9e71-591a45d7b43d.png'),(275,42,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/42/photos/photoList/pic_bebc1a3c-c34f-4cb7-a6f7-37c492a08dc5.png'),(276,42,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/42/photos/photoList/pic_a8d78ccc-e7fa-447d-8804-aed40b048166.png'),(277,47,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/47/photos/photoList/pic_5c91654d-e13a-4d5e-8524-2bc1eb407f64.png'),(278,47,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/47/photos/photoList/pic_3ad6822d-f712-40ed-a3ea-b95c05a9112d.png'),(279,47,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/47/photos/photoList/pic_5b331d77-52fa-4a84-a4df-a4c48ae6900c.png'),(280,48,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/48/photos/photoList/pic_dd6efea7-7060-4cfb-9e40-564dcd0f9a80.png'),(281,48,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/48/photos/photoList/pic_71be1edb-b445-4f0a-9e13-0bef0246c074.png'),(282,48,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/48/photos/photoList/pic_46f68fb8-292d-43fa-a69e-25474151c73a.png'),(283,48,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/48/photos/photoList/pic_eaae3cac-f404-489a-8948-d2e89e453699.png'),(284,48,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/48/photos/photoList/pic_cd7943ba-d681-4285-9c56-3b20ab99d2dc.png'),(285,47,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/47/photos/photoList/pic_8cfa9668-4b62-4999-83c7-f8aa0499692a.png'),(286,47,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/47/photos/photoList/pic_cba2adb2-6a8e-4b7e-83ac-dd4c2767b27a.png'),(287,47,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/47/photos/photoList/pic_a97a9f96-3d56-4f3e-ae71-0b002329e957.png'),(288,47,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/47/photos/photoList/pic_f868a303-7c9a-4edb-8c7f-7e7a5a72313c.png'),(289,47,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/47/photos/photoList/pic_d2b63a29-86ba-4702-94a8-765defe22a69.png'),(290,47,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/47/photos/photoList/pic_eb5b14b6-39ef-4a2c-bf9a-3c772426a2c4.png'),(291,47,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/47/photos/photoList/pic_67604c2b-9d5d-457a-9428-89e82897707c.png'),(292,47,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/47/photos/photoList/pic_f1b89c72-dd66-44d6-9bba-518f9848fb2c.png'),(293,47,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/47/photos/photoList/pic_dfc0cb72-128d-43d3-bda3-1c46f36ad9ee.png'),(294,49,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/49/photos/photoList/pic_6c0331bf-7e42-43a7-9f48-ea91f96cfe5d.png'),(295,49,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/49/photos/photoList/pic_394dc8fa-1dec-4f5c-aea0-278544846597.png'),(296,51,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/51/photos/photoList/pic_3c0f1971-5868-4787-bc90-a8befdfee679.png'),(297,52,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/52/photos/photoList/pic_10951897-0a48-4b56-9a0f-ac79e6464b94.png'),(298,52,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/52/photos/photoList/pic_9da97b19-6fed-42c3-8ef6-338f44c2486d.png'),(299,53,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/53/photos/photoList/pic_bb01b46f-b1a1-4a7a-a7df-1831a0ab0566.png'),(300,52,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/52/photos/photoList/pic_038ee117-7d38-4394-bf5a-5a78234047d9.png'),(301,52,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/52/photos/photoList/pic_18f2e864-8fa4-4904-9732-3d235d4ec539.png'),(302,54,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/54/photos/photoList/pic_912dd04a-0845-4bf7-ba6d-4bd320396244.png'),(303,52,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/52/photos/photoList/pic_dfc6d17e-c7b4-4c3b-a9d4-ad013959129b.png'),(304,54,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/54/photos/photoList/pic_bc59f226-b13c-4900-b64e-561b5552b08a.png'),(305,52,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/52/photos/photoList/pic_17f20e21-7f94-4428-aa6e-8b3de1707007.png'),(306,52,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/52/photos/photoList/pic_7c658b95-be0b-4464-b42c-e6756358bd71.png'),(307,55,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/55/photos/photoList/pic_4a105e59-55b5-4ac4-97cf-ee2bad3afcc2.png'),(308,55,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/55/photos/photoList/pic_29587bce-4f47-4795-b900-4632617085a5.png'),(309,56,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/56/photos/photoList/pic_18ca9f7d-23c8-47e9-b005-c2122b8627ef.png'),(310,57,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/57/photos/photoList/pic_03e05e51-d6c6-46fd-af70-4cbc74c4743f.png'),(311,57,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/57/photos/photoList/pic_0c32c92a-a6dc-4bf6-b199-70b9fdd4e9de.png'),(312,57,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/57/photos/photoList/pic_91a237ee-985e-4d06-9e67-7231a9ab45e1.png'),(313,60,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/11/60/photos/photoList/pic_8a74fee2-bcc3-44fa-aa06-74967c17a5a3.png'),(314,61,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/61/photos/photoList/pic_c4d16953-6e90-4218-8e4b-ba52cfb883aa.png'),(315,61,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/61/photos/photoList/pic_1ae1a874-902f-40d1-a777-9c8b4900863e.png'),(316,61,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/61/photos/photoList/pic_1a4d2bcb-2009-4a1d-8e25-de31fa8bbc2a.png'),(317,61,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/61/photos/photoList/pic_c7db15b6-fd2d-418a-8def-881cc18604c3.png'),(318,61,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/61/photos/photoList/pic_b664b2c2-be71-42d2-94ae-71337a453c61.png'),(319,61,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/61/photos/photoList/pic_51bbc902-de85-4b2f-bb61-92e6a881de03.png'),(320,61,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/61/photos/photoList/pic_cbbd1a6c-74b7-4cf2-a752-ca9c19fa23ee.png'),(321,61,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/61/photos/photoList/pic_d422bf8f-b582-48f4-8b21-5969caa65b89.png'),(322,61,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/61/photos/photoList/pic_269d63df-c372-49b5-b01d-bdbca1b1c980.png'),(323,68,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/68/photos/photoList/pic_83141aeb-15bf-4757-a464-a162f8d5c334.png'),(324,68,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/68/photos/photoList/pic_d48080a8-1d26-4026-b8c7-94848298a9d7.png'),(325,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_9a13f105-dbe6-46c3-9f31-4d7080e1dd50.png'),(326,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_4acb2573-e53d-41f6-8082-d5f96d7095b6.png'),(327,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_64a4962d-6d32-4f19-a3ff-0b4f96506274.png'),(328,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_1b83d1bb-6e9b-483d-863c-8a4e23992160.png'),(329,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_a5622174-bd92-4c83-be19-44c9c15e8fc9.png'),(330,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_b5172537-0979-4ea4-a899-8ee36c3c59fc.png'),(331,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_a4fe1fd9-9c93-428f-917f-24d8c7fc7256.png'),(332,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_8d977169-4e83-42b3-be78-18a4915bb58f.png'),(333,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_0371c4eb-ca7b-4c27-81d6-d985d9c3c9f0.png'),(334,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_3b3b2793-c2c6-484f-9ec3-e0ce2ee1b121.png'),(335,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_95654a4a-5946-44f8-9194-d7e511f3ae8a.png'),(336,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_ecab75d5-bf3b-40c4-9d31-4bf4dcb285a9.png'),(337,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_5e410840-5e76-49d9-be86-3a7cb405eea9.png'),(338,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_4bb0abef-a4db-4052-95d3-7c9565354cf4.png'),(339,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_2b1b0fb1-9001-4d01-affb-a0ee87c4fc3e.png'),(340,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_f4ac158d-55b7-4432-9920-0f8d0773a743.png'),(341,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_a7c8a2dc-5ecb-4c4a-a2fa-12095d16ef39.png'),(342,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_3248a46c-a8a2-4a7c-8ac1-134ca35d1e1e.png'),(343,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_51ef59c4-33b1-46c2-9345-be210942d56c.png'),(344,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_750bf2be-72b9-49bb-9873-4c6564f5b9c0.png'),(345,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_38917e6b-8e47-418c-b328-99fb2d1e1064.png'),(346,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_fcf847f0-2da7-4fae-968a-2a52cb928286.png'),(347,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_e5ce2b25-7095-4417-9af9-001084b0e1cd.png'),(348,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_67570e0f-739e-4b13-859a-c36f4d67d546.png'),(349,70,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/70/photos/photoList/pic_032caa82-5b81-4165-b5bd-3985ee466db6.png'),(355,71,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/71/photos/photoList/pic_7846ad56-77fa-405a-9409-99272a23a1e2.png'),(357,72,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/72/photos/photoList/pic_b68aaf4c-da49-4b4d-b6fe-c1d0f867f8a8.png'),(361,72,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/72/photos/photoList/pic_8b138d08-a23b-4c30-9b3e-f59afa86f5e7.png'),(362,72,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/72/photos/photoList/pic_3721d221-c3db-4f7c-b9f4-b021537e883e.png'),(363,72,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/72/photos/photoList/pic_9896a38a-e661-419a-ada9-5e7529cbefbb.png'),(364,72,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/72/photos/photoList/pic_bc1046f5-6d68-42cf-8058-e06a53f86485.png'),(365,73,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/9/73/photos/photoList/pic_18a6f711-0c37-4226-b8e5-f8d6f66d6810.png'),(366,73,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/9/73/photos/photoList/pic_574c7fde-f215-4557-9db8-700a5c215113.png'),(367,74,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/74/photos/photoList/pic_2b718b83-c21d-47a3-85fb-30e7a37e2751.png'),(368,74,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/74/photos/photoList/pic_00349182-31a3-48ad-b869-bae47d100136.png'),(369,74,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/74/photos/photoList/pic_ade6be52-7d98-4c67-a838-47fa3797706d.png'),(370,77,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/77/photos/photoList/pic_dafd93c2-9224-4adb-9bd5-6764d770eeb2.png'),(371,77,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/77/photos/photoList/pic_b819e92c-a1da-4b90-8662-da241e0ea698.png'),(372,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_1e85c8f4-d17f-4a4b-9293-34e670ecd759.png'),(373,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_b5dad049-243a-4cdf-9112-57207dd4b4d6.png'),(374,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_609cfd8b-6780-4151-af70-76e3fdb70218.png'),(375,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_e4614ed1-e015-431c-a32e-a2d299e3fd51.png'),(376,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_b62edcad-7289-474f-8401-62d16af4f051.png'),(377,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_f44e3465-30a4-4c52-9d37-49a1e052bb73.png'),(378,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_ae409ab4-56f4-4b2b-a729-91804b3ab794.png'),(379,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_1bb9249c-ff31-4aa4-8eb9-51289234e6c8.png'),(380,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_9a24fc39-fbc3-4fd6-8eeb-ef18f84ba2ff.png'),(381,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_8639349c-a1d6-41c0-9816-589cda94695a.png'),(382,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_6bc45e29-2bb1-4728-a624-659429702f10.png'),(383,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_4c1ea634-ef93-4ccd-95db-5978ccffc2cb.png'),(384,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_22332eda-c607-466e-9975-a1c0ce19b9e0.png'),(385,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_c94d03e1-fe97-4cda-aaa9-416b63aa898b.png'),(386,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_dfd6b655-a749-481d-989c-06405f73cfe2.png'),(387,80,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/80/photos/photoList/pic_6e37f29d-bf8f-4847-b7bb-c46166f3c680.png'),(388,82,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/82/photos/photoList/pic_00b534dd-f308-46ca-896d-5a2e7053d770.png'),(389,82,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/82/photos/photoList/pic_b5832856-dbd9-4788-b4db-19ee40554c3c.png'),(390,82,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/82/photos/photoList/pic_573594b8-e246-4434-bd15-7946dabf3213.png'),(391,82,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/82/photos/photoList/pic_5957ea2a-5fff-4704-af80-4cbff00817d5.png'),(392,82,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/82/photos/photoList/pic_33ec1ed4-5495-4ffe-9d90-d3c90aa9a88b.png'),(393,82,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/82/photos/photoList/pic_bc477910-dd32-41a5-889b-90859987c92d.png'),(394,82,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/82/photos/photoList/pic_ae938462-b789-4245-a8ef-c0945830a3d8.png'),(395,82,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/82/photos/photoList/pic_785a409e-2f1a-46db-be86-e64bfbaa330a.png'),(396,82,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/82/photos/photoList/pic_50bf37f7-d636-450f-ad01-9deb3622f0e2.png'),(397,82,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/82/photos/photoList/pic_51a63def-a1d1-4aa6-a669-7ed267254ce8.png'),(398,83,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/83/photos/photoList/pic_1c3bcc61-a6e2-4691-be6f-59c99d923b73.png'),(399,83,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/83/photos/photoList/pic_7c9e320d-0831-4dab-bf82-04e3a6daf819.png'),(400,83,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/83/photos/photoList/pic_98d6426e-7239-4f27-b9a1-4929416c29b9.png'),(401,83,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/83/photos/photoList/pic_01653bd9-a102-4474-9eee-f4e4a2df2ebc.png'),(402,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_2a01fbc4-b21a-4dd6-84a3-26135c27c101.png'),(403,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_9e400281-e24d-48dc-9fbc-28c18bfe39ce.png'),(404,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_b337ce4a-71cb-4e56-a46f-1916f34c1b81.png'),(405,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_fbb7e5a6-7d45-42f6-bc7e-708e433b06d6.png'),(406,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_9792da22-abba-4cd3-8b66-b777fe99aa73.png'),(407,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_af666545-b928-439b-bdeb-b8490ed80a94.png'),(408,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_ef767d6d-8cf3-4ab6-8eba-62fe1939b42f.png'),(409,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_6866b6a8-5a87-4e81-bd3b-419a5b9b8b76.png'),(410,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_0328df37-1763-469f-8f63-33e23ca39d2f.png'),(411,87,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/87/photos/photoList/pic_8b71fdb8-8028-41f4-9448-4843ea7ffbe5.png'),(412,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_106127f6-37be-477b-9f89-9cb2ba793472.png'),(413,87,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/87/photos/photoList/pic_f5548239-8ff5-4491-bd67-ba3241dd8b48.png'),(414,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_b421e248-71cb-4268-99e6-dd24bd6745c8.png'),(415,87,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/87/photos/photoList/pic_ffbc2672-db1a-44c5-a0ec-98ad864e46cf.png'),(416,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_ba47b238-cb51-404e-a6ed-fdecba097f12.png'),(417,87,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/87/photos/photoList/pic_531f68ef-b064-4280-9159-f43e849b16fa.png'),(418,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_91c50675-6eb9-4378-a1af-17cd1e255405.png'),(419,87,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/87/photos/photoList/pic_528475c5-fbb5-4c93-b9bd-70feb54bd3af.png'),(420,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_27b75b60-4cee-4886-8e65-9bbc68337f4b.png'),(421,87,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/87/photos/photoList/pic_eae53292-44c5-4e23-a17f-6ac6a009452c.png'),(422,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_a7f53acc-e8a6-4a84-99f5-c167214ca00c.png'),(423,86,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/86/photos/photoList/pic_de98ac5b-31e4-4e80-93a5-19c38ca6d403.png'),(424,90,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/90/photos/photoList/pic_9c553a82-6d2d-4fe4-88ea-0772d4e38e79.png'),(425,93,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/93/photos/photoList/pic_8ae81800-66c7-4c54-9b24-67a392b1779e.png'),(426,93,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/93/photos/photoList/pic_ba44932f-2e3b-4d40-bf50-c7817819deb5.png'),(427,93,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/93/photos/photoList/pic_fe2a3b67-b6f6-4d00-a50e-a18be7a21ec8.png'),(428,94,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/94/photos/photoList/pic_fc430fc2-fa12-4a4b-9d79-02d94ee6ee78.png'),(429,94,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/94/photos/photoList/pic_8893dd49-03b9-437a-94d9-ed49d3e5a6fe.png'),(430,96,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/96/photos/photoList/pic_08bcaad7-77c7-45a8-82c0-ad5e5f2d346c.png'),(431,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_922f64eb-1e5d-46f3-b2c8-e92988edc4e0.png'),(432,96,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/96/photos/photoList/pic_35e3e1e4-43cb-401d-9ac3-06b45db8f259.png'),(433,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_9847ef23-e94a-4999-b16f-14927ebdfbac.png'),(434,98,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/98/photos/photoList/pic_5e2c9d2c-2de4-46ab-bef4-db2ad409bf23.png'),(435,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_7b883b10-e2ae-4696-85bb-ba0d2c0ab697.png'),(436,98,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/98/photos/photoList/pic_c37eebcd-c066-485d-b544-cd3ca724c350.png'),(437,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_69d25ece-31d4-4d49-9170-29f57faf99db.png'),(438,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_32bba51e-a0cc-4213-9454-ab77680b5ea8.png'),(439,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_06688d22-690c-432f-9afc-b82670f2c37c.png'),(440,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_69731a28-f599-422f-b43c-f575fbe0fcac.png'),(441,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_2bc9932c-0e2c-44ac-81ca-568841470813.png'),(442,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_486771ca-d2f1-47b0-9d5e-ae9d5c65436f.png'),(443,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_78b9ccd8-a36a-45d2-ac4d-0dc9001f71e7.png'),(444,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_74f9e872-9001-4a0b-a075-87339bf0f686.png'),(445,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_e8bbcaa3-caa5-4b72-821a-1b6075b7170b.png'),(446,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_4704c5d1-3d51-4713-bd73-a19a7b467e6b.png'),(447,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_3f6e9bff-113c-4f9c-b4c1-6f0864b3d7dd.png'),(448,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_6071a999-0c7a-498f-95e0-407570147ebe.png'),(449,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_a4b02e27-3403-4387-954b-bf6a73b0d062.png'),(450,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_491da1ce-c6b3-4f2e-ba94-32f91cc3d5b9.png'),(451,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_3d4df97d-1c88-4b3b-a68c-e188afb8deda.png'),(452,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_18deaffd-45c5-4de1-bf45-fdcf557a6096.png'),(453,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_00d1a0e4-0020-4cbe-b5e1-8136562221ac.png'),(454,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_e8cd41ac-1517-4edb-bee0-07ff727dd352.png'),(455,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_67dcccca-da33-4009-af2e-b92fde5364d8.png'),(456,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_003eb659-948c-4714-be37-e8097a5f3c5e.png'),(457,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_a602fe85-8232-4432-b6ae-5ba8a264cd7f.png'),(458,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_8e0c7ff3-92b5-40e0-b4d9-87da411bf9ba.png'),(459,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_dcd565a1-9125-407e-9183-8e8abe7e6519.png'),(460,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_fa05f85a-5143-46a0-95db-078227b131df.png'),(461,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_34cb3eb5-53a3-42a4-bcb6-64557fbe3a2c.png'),(462,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_959662fe-dcda-43fd-8b21-e33af19e7ec2.png'),(463,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_4d530482-579b-451d-86c7-ed6739dacaaa.png'),(464,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_0cebf3a8-f9fb-427c-b9ea-49e116980d94.png'),(465,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_9aa2cc54-2dfc-47e4-90c2-ed47c9081143.png'),(466,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_333a6443-5564-4aa1-875a-549c3916be81.png'),(467,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_d02d21e2-188e-4777-8806-541f97c8791f.png'),(468,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_c2ff8114-2b9b-47b8-9c20-158970724938.png'),(469,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_810f251a-a6d4-4f9f-9061-ea980ff784e1.png'),(470,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_cf4bdefb-28e1-4ad1-9df6-eff1ed30e67b.png'),(471,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_62491d48-22dd-48eb-ad5a-044fd76e6ebc.png'),(472,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_f918b7be-c82c-4ae0-b93b-590796e33895.png'),(473,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_95687fb6-663b-4d1d-97ca-ae5fe4fff5a5.png'),(474,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_19bc002d-046e-47e6-a311-9ed83a8a549c.png'),(475,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_f7840999-0db5-4d14-859f-544eaa4ce0f7.png'),(476,101,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/photos/photoList/pic_7951e757-4b6e-4467-8903-5ada3560e9f2.png'),(477,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_ada4ce03-6f39-4411-87e3-853881595f62.png'),(478,101,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/photos/photoList/pic_2b2d160c-12e0-4375-91a3-724d5e6dafc4.png'),(479,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_0cbf66cc-1a31-41a1-96dd-796d6c99475f.png'),(480,101,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/photos/photoList/pic_a65b5916-fcd5-4960-a1d5-f3d2164aebce.png'),(481,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_b6475b1e-e501-450a-982f-1fe77b634362.png'),(482,101,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/photos/photoList/pic_b862311c-83b9-43aa-bfa6-5622093e92e2.png'),(483,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_3e28d3f6-c9a0-4376-92ca-54e771781098.png'),(484,101,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/photos/photoList/pic_f6d66f1c-2043-4021-bf89-fc0c5993a68d.png'),(485,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_f875c91c-7327-42c9-bcbd-aad5210a7c0b.png'),(486,101,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/photos/photoList/pic_6f11c356-a6b6-49e9-b660-ec756e065f93.png'),(487,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_9b40e723-4434-46ca-bbb8-bfb15a22b67d.png'),(488,101,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/photos/photoList/pic_62eb7dbb-e5f6-4da0-ad42-85f19ad5bd9b.png'),(489,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_67a27fb0-c734-4322-ba74-9a2806898bec.png'),(490,101,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/photos/photoList/pic_efdd392e-1a99-481d-875d-14a6e112da6d.png'),(491,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_684ee60c-be57-4cac-aeb5-63bdd90894f0.png'),(492,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_2e8f681f-0104-4729-8845-270dcde4bd82.png'),(493,101,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/101/photos/photoList/pic_6ea725d9-6295-4af8-aeab-6bb06227ef54.png'),(494,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_c5f32563-489c-446a-830a-0fdbba053da8.png'),(495,102,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/102/photos/photoList/pic_c46a82fc-3fc8-4720-955d-ecac425d42f9.png'),(496,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_f7f9a84b-91f1-4e29-9c57-d0fed1f2fd6d.png'),(497,102,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/102/photos/photoList/pic_df6c621a-cb42-4982-98f5-b0550bb13c8f.png'),(498,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_34ddd413-6742-458c-ba0c-c321d4d0f606.png'),(499,102,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/102/photos/photoList/pic_c56064dd-f032-4203-8487-5a98a981f002.png'),(500,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_31521d5b-71ed-411f-bab8-fa047f723bb5.png'),(501,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_c3bd41d3-c5c3-4d98-ad48-0ed2f03d3171.png'),(502,103,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/103/photos/photoList/pic_5bbdb314-797f-443a-bcfc-cf19222092f3.png'),(503,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_c36ef010-1166-4c40-9bd3-705d71b1f1a9.png'),(504,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_ed82c57e-a1bb-4a93-8e59-f0a6bcf9501a.png'),(505,104,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/104/photos/photoList/pic_6b5a2907-81b4-4dd2-9144-a241a7fc9ace.png'),(506,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_b433c31e-5df9-4064-99bd-7e0cf41af54c.png'),(507,104,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/104/photos/photoList/pic_2d39c40b-e611-45f2-a4af-39166c0469b2.png'),(508,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_07ad1c73-a548-4b78-abfa-601edf22d83e.png'),(509,104,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/104/photos/photoList/pic_78acec68-ecb1-45a5-8676-38daea61c041.png'),(510,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_d9fbf7d2-df65-4e85-97ec-fe199def9c0c.png'),(511,104,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/104/photos/photoList/pic_0668aedb-f1e5-48e6-be0d-b78f9b7125fe.png'),(512,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_fbbb921c-081d-41ab-bcff-352ba6f742cc.png'),(513,104,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/104/photos/photoList/pic_db29e9d2-e916-430d-b056-766dedbfd910.png'),(514,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_6d0941b9-3b60-4f83-9099-1af513c0ec43.png'),(515,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_2d2cfcc8-76c4-4421-8dc5-b3620e21255a.png'),(516,105,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/105/photos/photoList/pic_4f212eed-8931-4dfc-b64c-4774f56e2816.png'),(517,99,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_df88477c-402e-4000-b56a-10b8fd80d1aa.png'),(518,97,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/12/97/photos/photoList/pic_3edc6ebe-4bb8-44e4-bf9c-04c133328115.png'),(519,110,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/110/photos/photoList/pic_3c95e5b2-4dce-4249-8ed2-c1f997fee4a6.png'),(523,111,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/111/photos/photoList/pic_92189122-4632-4181-9109-d5962288f633.png'),(524,112,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/112/photos/photoList/pic_4d6934e1-4810-40c2-b3b4-67a284630a1a.png'),(525,112,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/112/photos/photoList/pic_c5f8a5ca-0909-4c16-aa32-d881a395467c.png'),(526,112,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/112/photos/photoList/pic_2279a59b-3489-48ab-ae5f-55725001c2a8.png');
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo_seq`
--

DROP TABLE IF EXISTS `photo_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `photo_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo_seq`
--

LOCK TABLES `photo_seq` WRITE;
/*!40000 ALTER TABLE `photo_seq` DISABLE KEYS */;
INSERT INTO `photo_seq` VALUES (1351);
/*!40000 ALTER TABLE `photo_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photomosaic`
--

DROP TABLE IF EXISTS `photomosaic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `photomosaic` (
  `photomosaic_id` bigint NOT NULL AUTO_INCREMENT,
  `result_id` bigint DEFAULT NULL,
  `path` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`photomosaic_id`),
  UNIQUE KEY `UK_3ji5athsqy9ieso8b3hnm56p4` (`result_id`),
  CONSTRAINT `FKq76aqq8vsn5m8ygt6980u4xcg` FOREIGN KEY (`result_id`) REFERENCES `result` (`result_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photomosaic`
--

LOCK TABLES `photomosaic` WRITE;
/*!40000 ALTER TABLE `photomosaic` DISABLE KEYS */;
/*!40000 ALTER TABLE `photomosaic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photomosaic_seq`
--

DROP TABLE IF EXISTS `photomosaic_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `photomosaic_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photomosaic_seq`
--

LOCK TABLES `photomosaic_seq` WRITE;
/*!40000 ALTER TABLE `photomosaic_seq` DISABLE KEYS */;
INSERT INTO `photomosaic_seq` VALUES (1);
/*!40000 ALTER TABLE `photomosaic_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postit_template`
--

DROP TABLE IF EXISTS `postit_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postit_template` (
  `postit_id` int NOT NULL,
  `postit_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `postit_name` enum('RAINBOW','GREEN','BLUE','PINK','YELLOW') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`postit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postit_template`
--

LOCK TABLES `postit_template` WRITE;
/*!40000 ALTER TABLE `postit_template` DISABLE KEYS */;
INSERT INTO `postit_template` VALUES (1,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EC%86%8D%EC%A7%80/rainbow.png','RAINBOW'),(2,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EC%86%8D%EC%A7%80/green.png','GREEN'),(3,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EC%86%8D%EC%A7%80/blue.png','BLUE'),(4,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EC%86%8D%EC%A7%80/pink.png','PINK'),(5,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EC%86%8D%EC%A7%80/yellow.png','YELLOW');
/*!40000 ALTER TABLE `postit_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postit_template_seq`
--

DROP TABLE IF EXISTS `postit_template_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postit_template_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postit_template_seq`
--

LOCK TABLES `postit_template_seq` WRITE;
/*!40000 ALTER TABLE `postit_template_seq` DISABLE KEYS */;
INSERT INTO `postit_template_seq` VALUES (1);
/*!40000 ALTER TABLE `postit_template_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `record` (
  `event_count` int NOT NULL DEFAULT '0',
  `total_message` int NOT NULL DEFAULT '0',
  `total_participant` int NOT NULL DEFAULT '0',
  `total_time` int NOT NULL DEFAULT '0',
  `record_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `UK_np4qx16bnj5i2i34ak9miooqm` (`user_id`),
  CONSTRAINT `FKeny3549xar8rnrcmdw3hl0la1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (10,11,19,10,1,1),(9,34,10,4,2,2),(11,34,39,41,3,3),(3,5,8,6,4,4),(1,0,1,0,5,5),(0,0,0,0,6,6),(1,1,1,1,7,7),(0,0,0,0,8,8),(7,0,7,0,9,9),(0,0,0,0,10,11),(0,0,0,0,11,12);
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record_seq`
--

DROP TABLE IF EXISTS `record_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `record_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record_seq`
--

LOCK TABLES `record_seq` WRITE;
/*!40000 ALTER TABLE `record_seq` DISABLE KEYS */;
INSERT INTO `record_seq` VALUES (1);
/*!40000 ALTER TABLE `record_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `event_id` bigint DEFAULT NULL,
  `result_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `name` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT 'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png',
  `status` enum('MEMORY','RECOLLECTION') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`result_id`),
  UNIQUE KEY `UK_c79qmsofnl7knkprooflyklyy` (`event_id`),
  KEY `FKpjjrrf0483ih2cvyfmx70a16b` (`user_id`),
  CONSTRAINT `FKniu3a6j6tu4wh9l9v4gckbloo` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`),
  CONSTRAINT `FKpjjrrf0483ih2cvyfmx70a16b` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (2,2,1,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(3,3,1,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(6,6,2,'정민이 가는 날','정민아.........같이 가','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/6/photos/photoList/pic_47a32782-06c9-4abb-948c-96ea5d9c9ee5.jpg','RECOLLECTION'),(15,15,1,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/1/15/photos/photoList/pic_5b9289a9-98a8-4c23-9e04-b81aca5f5d62.png','MEMORY'),(17,17,1,'혼자서','혼자','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','RECOLLECTION'),(18,18,1,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(19,19,4,'졸업식','싸피 졸업하고싶다.','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/4/19/photos/photoList/pic_6213b04b-2e78-4cf0-849e-afead16564ed.png','RECOLLECTION'),(21,21,7,'ㅁㄴㅇ','ㅁㄴㅇ','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/7/21/photos/photoList/pic_f9b57655-7043-4f84-91f3-e629ae2c1aaa.png','RECOLLECTION'),(22,22,4,'아 그래?','그럼 안되는데','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/4/22/photos/photoList/pic_71fbf3ec-3993-4528-972e-359078074f62.png','RECOLLECTION'),(23,23,5,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(24,24,4,'대망의 발표날','제발 버그나지 마세요','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','RECOLLECTION'),(25,25,2,'결혼식','ㅁㄴㅇㄻㄴ','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/25/photos/photoList/pic_3869993f-086b-49df-b676-3f5db72029d8.png','RECOLLECTION'),(26,26,2,'행사','예시','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','RECOLLECTION'),(27,27,2,'행사2','행사222','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/27/photos/photoList/pic_408b5932-0b64-41ca-b0d2-1b15ba105552.jpg','RECOLLECTION'),(28,28,2,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(29,29,2,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(30,30,9,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(31,31,2,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(37,37,1,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(39,39,1,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(40,40,1,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(41,41,1,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(43,43,2,'케이크','먹는날','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/43/photos/photoList/pic_0619e698-c940-410c-8b7e-fb913366bb77.jpg','RECOLLECTION'),(44,44,9,'','','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','RECOLLECTION'),(45,45,9,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(48,48,1,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(52,52,1,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(58,58,9,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(59,59,10,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(60,60,11,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(62,62,2,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(65,65,9,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(71,71,2,'테스트','되라','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/2/71/photos/photoList/pic_7846ad56-77fa-405a-9409-99272a23a1e2.png','RECOLLECTION'),(73,73,9,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(75,75,9,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(84,84,10,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(85,85,10,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(95,95,1,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(97,97,12,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(99,99,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/99/photos/photoList/pic_00d1a0e4-0020-4cbe-b5e1-8136562221ac.png','MEMORY'),(100,100,1,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(101,101,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(102,102,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(103,103,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(104,104,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(105,105,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(106,106,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(107,107,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(108,108,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(109,109,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(110,110,3,'입학식','드디어 다미가 입학을 하다니','https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/3/110/photos/photoList/pic_3c95e5b2-4dce-4249-8ed2-c1f997fee4a6.png','RECOLLECTION'),(111,111,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(112,112,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(113,113,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY'),(114,114,3,NULL,NULL,'https://mokkoji-bucket.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%EC%82%AC%EC%A7%84/%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80_%EA%B8%B0%EB%B3%B8.png','MEMORY');
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_seq`
--

DROP TABLE IF EXISTS `result_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_seq`
--

LOCK TABLES `result_seq` WRITE;
/*!40000 ALTER TABLE `result_seq` DISABLE KEYS */;
INSERT INTO `result_seq` VALUES (951);
/*!40000 ALTER TABLE `result_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolling_paper`
--

DROP TABLE IF EXISTS `rolling_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rolling_paper` (
  `background_id` int DEFAULT NULL,
  `is_edited` bit(1) NOT NULL DEFAULT b'0',
  `postit_id` int DEFAULT NULL,
  `result_id` bigint DEFAULT NULL,
  `rollingpaper_id` bigint NOT NULL,
  PRIMARY KEY (`rollingpaper_id`),
  UNIQUE KEY `UK_f9q060wo44d7ev9plmuogg9ag` (`result_id`),
  KEY `FKevalsyuad70kj65s77f64aqqn` (`background_id`),
  KEY `FKtlss8wro6ly2qhfc95vpj1yb0` (`postit_id`),
  CONSTRAINT `FKevalsyuad70kj65s77f64aqqn` FOREIGN KEY (`background_id`) REFERENCES `background_template` (`background_id`),
  CONSTRAINT `FKq5xif1n1drxhpsvk9d85rticu` FOREIGN KEY (`result_id`) REFERENCES `result` (`result_id`),
  CONSTRAINT `FKtlss8wro6ly2qhfc95vpj1yb0` FOREIGN KEY (`postit_id`) REFERENCES `postit_template` (`postit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolling_paper`
--

LOCK TABLES `rolling_paper` WRITE;
/*!40000 ALTER TABLE `rolling_paper` DISABLE KEYS */;
INSERT INTO `rolling_paper` VALUES (1,_binary '',1,2,1),(1,_binary '',1,3,2),(3,_binary '',5,6,3),(1,_binary '',1,15,4),(1,_binary '',1,17,52),(1,_binary '\0',1,18,102),(2,_binary '',1,19,152),(1,_binary '\0',1,20,153),(1,_binary '',2,21,202),(3,_binary '',5,22,203),(1,_binary '\0',1,23,204),(3,_binary '',2,24,205),(1,_binary '\0',1,25,206),(3,_binary '',4,26,207),(4,_binary '',3,27,208),(2,_binary '',4,28,209),(1,_binary '\0',1,29,210),(1,_binary '\0',1,30,211),(1,_binary '\0',1,31,212),(1,_binary '\0',1,32,213),(1,_binary '\0',1,33,214),(1,_binary '\0',1,37,215),(1,_binary '\0',1,39,216),(1,_binary '\0',1,40,217),(1,_binary '\0',1,41,218),(5,_binary '',1,43,219),(1,_binary '\0',1,44,220),(1,_binary '\0',1,45,221),(1,_binary '\0',1,48,222),(1,_binary '\0',1,52,223),(1,_binary '\0',1,58,224),(1,_binary '\0',1,59,225),(1,_binary '\0',1,60,226),(1,_binary '\0',1,62,227),(1,_binary '\0',1,65,228),(1,_binary '\0',1,67,229),(1,_binary '\0',1,68,230),(1,_binary '\0',1,69,231),(1,_binary '\0',1,70,232),(3,_binary '',3,71,233),(1,_binary '\0',1,72,234),(1,_binary '\0',1,73,235),(1,_binary '\0',1,74,236),(1,_binary '\0',1,75,237),(1,_binary '\0',1,76,238),(1,_binary '\0',1,77,239),(1,_binary '\0',1,78,240),(1,_binary '\0',1,79,241),(1,_binary '\0',1,80,242),(1,_binary '\0',1,81,243),(1,_binary '',1,34,252),(1,_binary '',1,35,253),(1,_binary '',1,36,254),(1,_binary '',1,38,255),(1,_binary '',1,42,256),(1,_binary '',1,46,257),(1,_binary '',1,47,258),(1,_binary '',1,49,259),(1,_binary '',1,50,260),(1,_binary '',1,51,261),(1,_binary '',1,53,262),(1,_binary '',1,54,263),(1,_binary '',1,55,264),(1,_binary '',1,56,265),(1,_binary '',1,57,266),(1,_binary '',1,61,267),(1,_binary '',1,63,268),(1,_binary '',1,64,269),(1,_binary '',1,66,270),(1,_binary '',1,82,271),(1,_binary '',1,83,272),(1,_binary '',1,86,273),(1,_binary '',1,87,274),(1,_binary '',1,88,275),(1,_binary '',1,89,276),(1,_binary '',1,96,277),(1,_binary '\0',1,84,302),(1,_binary '\0',1,85,303),(1,_binary '\0',1,90,304),(1,_binary '\0',1,91,305),(1,_binary '\0',1,92,306),(1,_binary '\0',1,93,307),(1,_binary '\0',1,94,308),(1,_binary '\0',1,95,309),(1,_binary '\0',1,97,310),(1,_binary '\0',1,98,311),(1,_binary '',2,99,312),(1,_binary '\0',1,100,313),(2,_binary '',4,101,314),(1,_binary '\0',1,102,315),(1,_binary '\0',1,103,316),(1,_binary '\0',1,104,317),(1,_binary '\0',1,105,318),(1,_binary '\0',1,106,319),(1,_binary '\0',1,107,320),(1,_binary '\0',1,108,321),(1,_binary '\0',1,109,322),(1,_binary '\0',1,110,323),(1,_binary '\0',1,111,324),(1,_binary '\0',1,112,325),(1,_binary '\0',1,113,352),(1,_binary '\0',1,114,353),(1,_binary '',1,4,1017),(1,_binary '',1,5,1018),(1,_binary '',1,7,1019),(1,_binary '',1,8,1020),(1,_binary '',1,9,1021),(1,_binary '',1,10,1022),(1,_binary '',1,11,1023),(1,_binary '',1,12,1024),(1,_binary '',1,13,1025),(1,_binary '',1,14,1026),(1,_binary '',1,16,1027);
/*!40000 ALTER TABLE `rolling_paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolling_paper_seq`
--

DROP TABLE IF EXISTS `rolling_paper_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rolling_paper_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolling_paper_seq`
--

LOCK TABLES `rolling_paper_seq` WRITE;
/*!40000 ALTER TABLE `rolling_paper_seq` DISABLE KEYS */;
INSERT INTO `rolling_paper_seq` VALUES (451);
/*!40000 ALTER TABLE `rolling_paper_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `name` varchar(6) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `refresh_token` text COLLATE utf8mb4_unicode_ci,
  `authority` enum('GUEST','USER','ADMIN') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `provider` enum('GOOGLE','NAVER','KAKAO') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('김지은',1,'jieun9912@naver.com','https://ssl.pstatic.net/static/pwe/address/img_profile.png','eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDkyMjc4NDh9.HvC8kHcmZmjoYuTlJQiLT-c9Ej8pLSTBX7Zo9xIL9wcYz97LZfYS6Llx7WvH2YVE4Eu-MgLKKUMwADnKIkbk_g','USER','NAVER'),('이정민',2,'leejm234@gmail.com','https://phinf.pstatic.net/contact/20230407_88/1680827190483szbPd_PNG/profileImage.png','eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDkyMjc3Nzh9.s9q5gay7fM83Lcc8_xoBTHWp5AneBXFIVsZU7ELN3KpjDYdjijTMJpEOiFhozRgauR0r72axsUI0WHse8mqM4Q','USER','NAVER'),('한재훈',3,'acia1234@nexon.com','https://ssl.pstatic.net/static/pwe/address/img_profile.png','eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDkyMjkxNTh9.K7UNcq9C3iVs92MKMriLbodihih8vUrb3hp2GZi4N_to57mbJVjLE-EIA2fTVdlWc-oGt2nRL0Z8KBaaxisofg','USER','NAVER'),('한재훈',4,'98cline@gmail.com','https://lh3.googleusercontent.com/a/ACg8ocIQdNaPPP9qgK-N3yQJomDh65WCMp9OshtB4uaXU2vf=s96-c','eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDkyMjAxOTZ9.dB1oxsIIwHPOUmJNAk3rEEbcMrGNsUvvY2EBmVppL0qfFvR3dugj-w_K_FK13j1z4IaBXbeBdnOiRL1F-B0qwQ','USER','GOOGLE'),('조용운',5,'cyy8071@naver.com','https://ssl.pstatic.net/static/pwe/address/img_profile.png','eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDkxOTYwMjJ9.MFFM-62rf3Ovsfh0P55RcBzJtS7NAR3Sccjvd9w_oK5zhmncduYVqikPQm-UEIUYfyJCy2ehY7z4ZjpWE7_7WQ','USER','NAVER'),('한재훈',6,'98cline@naver.com','http://k.kakaocdn.net/dn/mxdGr/btsCG3kcThd/0tmkR7D3vs2QSlUolfpqT1/img_640x640.jpg','eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDkxOTIxODZ9.E7WsTf1FCDsVFsPDepsxD1KyHvfTQVDTAVw32PM4VMLQ7U6BF4mb2tE919IsyAbUUgh00Cpp8c2kKndaJIcD_g','USER','KAKAO'),('test',7,'4pjttest@gmail.com','https://lh3.googleusercontent.com/a/ACg8ocIPFfjyZhZPXqLS7PRD2AL2NP73_JUcRwjb2vpRJ73u=s96-c','eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDkxOTI5MzJ9.i260T3xM6jAsCKCVbCUsR_fxgORV3fBrpDmp7maXIbPm1V5FLCpfho51OCvlBOLL3xWtjR7fqnC3gU6V6AHOxw','USER','GOOGLE'),('조용운',8,'example@email.com','http://k.kakaocdn.net/dn/1G9kp/btsAot8liOn/8CWudi3uy07rvFNUkk3ER0/img_640x640.jpg','eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDkxOTU5OTh9.28tPS9X_Ikuco-ESpf_nyx-Tz13m8THV7T2X6gYN_Imnm6E8yopXUo9fxk0qSmEEgU81-w7W19lz7hYbdSmR4w','USER','KAKAO'),('조용운',9,'cyy8071@gmail.com','https://lh3.googleusercontent.com/a/ACg8ocK8eBq1XhapAqvseSWi-GHE7OTOueWfOzqOPoReT35-=s96-c','eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDkyMjM4MDh9.6Fg2P0GYDUFiJdNOTQU4px0QDlSZhrBjkeiJeSeyTuDr0AVEqRk7nLfKcJnG18HrwdXYJsEZogCFMJzQwPSMuQ','USER','GOOGLE'),('이경배',10,'','http://k.kakaocdn.net/dn/jM8Ef/btsCzIhzQ7U/MmzTp10LVlAopTRnh0SLR1/img_640x640.jpg','eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDkyMjAyMDF9.vdaDtefLohg26bwwcJTC2l4bWzD0-mFvXr1vU2lfHJ5dfmYqgYuLeH60c3QOq8-9EZs-qalCpCK-RBIEk34Uxg','GUEST','KAKAO'),('이경배',11,'lgb8572@gmail.com','https://lh3.googleusercontent.com/a/ACg8ocI2cOGUg2pTJev06zCxORYwgzPWqvpvUB1f3S_jDRLNxVI=s96-c','eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDkyMjI5NzB9.zwTAH5FcN3nigbpxGD_uLIZMukI37kJMSif77kQmAWqY3vFD6P62E55n4eIFc-VDmzgi6On_TsIbKTN1kU7a0Q','USER','GOOGLE'),('이경배',12,'lgb8572@gmail.com','https://ssl.pstatic.net/static/pwe/address/img_profile.png','eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE3MDkyMjI4Njd9.QDSZ-JbjeSlMuc5toZAZs4pLjoyKTtmQLkYJaqm-CrdlAnsAf-QbP1BSnDkKPc77rW4S-Kj0Td4ueEEjJuZ0mA','USER','NAVER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_seq`
--

DROP TABLE IF EXISTS `user_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_seq`
--

LOCK TABLES `user_seq` WRITE;
/*!40000 ALTER TABLE `user_seq` DISABLE KEYS */;
INSERT INTO `user_seq` VALUES (401);
/*!40000 ALTER TABLE `user_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16  3:13:20
