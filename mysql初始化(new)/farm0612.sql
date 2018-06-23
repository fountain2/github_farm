/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.5.27 : Database - farmer
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`farmer` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `farmer`;

/*Table structure for table `crop` */

DROP TABLE IF EXISTS `crop`;

CREATE TABLE `crop` (
  `crop_id` int(10) NOT NULL AUTO_INCREMENT,
  `crop_name` varchar(20) NOT NULL,
  `crop_price` int(10) NOT NULL,
  `crop_introduction` text NOT NULL,
  PRIMARY KEY (`crop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `crop` */

insert  into `crop`(`crop_id`,`crop_name`,`crop_price`,`crop_introduction`) values (1,'向日葵',10,'会生产额外阳光'),(2,'豌豆射手',20,'最基础的射手植物'),(5,'土豆地雷',50,'一踩就爆，但需要时间准备'),(7,'食人花',70,'一口一敌人，吃东西时很弱'),(10,'阳光蘑菇',100,'黑夜里也能生产阳光'),(18,'窝瓜',180,'压碎靠近的第一个敌人'),(24,'高坚果',240,'很高很坚硬，可以吃很久'),(27,'仙人掌',270,'发出一针戳爆敌人的气球'),(30,'杨桃',300,'魂斗罗你玩过吧，跟S弹很像'),(31,'南瓜',310,'堪称其它植物的盔甲'),(39,'金盏花',390,'在另一个游戏里会掉钱'),(43,'忧郁蘑菇',430,'向四周快速喷射气体');

/*Table structure for table `field` */

DROP TABLE IF EXISTS `field`;

CREATE TABLE `field` (
  `field_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `seed_id` int(10) NOT NULL,
  `field_status` int(10) NOT NULL,
  `filed_harvest_number` int(10) NOT NULL,
  `field_planting_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`field_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `field` */

insert  into `field`(`field_id`,`user_id`,`seed_id`,`field_status`,`filed_harvest_number`,`field_planting_time`) values (1,3,43,1,10,'2018-06-23 23:43:06'),(2,3,2,1,10,'2018-06-23 23:43:10'),(3,3,2,1,10,'2018-06-23 23:43:24'),(4,3,7,1,10,'2018-06-23 23:43:26'),(5,4,1,1,10,'2018-06-23 23:43:28'),(6,4,2,0,10,'2018-06-23 23:43:31'),(7,4,5,1,10,'2018-06-23 23:43:33'),(8,4,10,1,10,'2018-06-23 23:43:35'),(9,4,18,1,10,'2018-06-22 16:27:01'),(10,4,24,1,10,'2018-06-22 16:27:03'),(13,3,2,1,10,'2018-06-23 23:06:16'),(14,3,2,1,10,'2018-06-23 23:06:16'),(15,3,31,1,14,'2018-06-23 23:15:53'),(16,3,31,1,14,'2018-06-23 23:15:53'),(17,3,39,1,14,'2018-06-23 23:20:22'),(18,3,39,1,14,'2018-06-23 23:20:25'),(19,3,31,1,14,'2018-06-23 23:20:27'),(20,3,1,0,10,'2018-06-24 02:56:30'),(21,3,1,0,0,'2018-06-23 22:48:05'),(22,3,1,0,0,'2018-06-23 22:48:05'),(23,3,1,0,0,'2018-06-23 22:48:05'),(24,5,18,1,12,'2018-06-24 02:07:20'),(25,5,18,1,12,'2018-06-24 02:07:20'),(26,5,18,1,12,'2018-06-24 02:07:20'),(27,5,18,1,12,'2018-06-24 02:07:20'),(28,5,18,1,12,'2018-06-24 02:07:20'),(29,5,18,1,12,'2018-06-24 02:07:20'),(30,5,18,1,12,'2018-06-24 02:07:20'),(31,5,18,1,12,'2018-06-24 02:07:20'),(32,5,18,1,12,'2018-06-24 02:07:20'),(33,5,18,1,8,'2018-06-24 02:07:20'),(34,5,18,1,12,'2018-06-24 02:07:20'),(35,5,18,1,10,'2018-06-24 02:07:20'),(36,5,43,1,14,'2018-06-24 01:08:53'),(37,5,43,1,14,'2018-06-24 01:08:53'),(38,5,43,1,14,'2018-06-24 01:08:53');

/*Table structure for table `repository` */

DROP TABLE IF EXISTS `repository`;

CREATE TABLE `repository` (
  `res_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `res_goods_id` int(10) NOT NULL,
  `res_type` int(10) NOT NULL,
  `res_number` int(10) NOT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `repository` */

insert  into `repository`(`res_id`,`user_id`,`res_goods_id`,`res_type`,`res_number`) values (1,1,2,1,200),(3,3,1,1,563),(4,3,10,1,146),(5,3,18,1,272),(6,4,10,1,35),(7,3,2,1,34),(8,3,5,1,34),(9,3,24,1,18),(11,3,2,2,13),(12,3,18,2,1),(13,3,30,2,1),(18,3,1,2,33),(20,5,1,1,40),(21,5,43,2,9),(22,5,18,2,12),(23,5,24,2,11),(24,5,30,2,9),(25,5,2,2,7),(26,5,31,2,11),(27,5,1,2,99);

/*Table structure for table `seed` */

DROP TABLE IF EXISTS `seed`;

CREATE TABLE `seed` (
  `seed_id` int(10) NOT NULL AUTO_INCREMENT,
  `seed_name` varchar(20) NOT NULL,
  `seed_price` int(10) NOT NULL,
  `seed_introduction` text NOT NULL,
  `seed_harvest_number` int(10) NOT NULL,
  `seed_harvest_time` int(10) NOT NULL,
  `crop_id` int(10) NOT NULL,
  PRIMARY KEY (`seed_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `seed` */

insert  into `seed`(`seed_id`,`seed_name`,`seed_price`,`seed_introduction`,`seed_harvest_number`,`seed_harvest_time`,`crop_id`) values (1,'向日葵种子',0,'向日葵的种子',10,1,1),(2,'豌豆射手种子',100,'豌豆射手的种子',10,10,2),(5,'土豆地雷种子',250,'土豆地雷的种子',10,15,5),(7,'食人花种子',350,'食人花的种子',10,21,7),(10,'阳光蘑菇孢子',500,'阳光蘑菇的孢子',10,30,10),(18,'窝瓜种子',900,'窝瓜的种子',12,36,18),(24,'高坚果种子',1200,'高坚果的种子',12,48,24),(27,'仙人掌种子',1350,'仙人掌的种子',12,54,27),(30,'杨桃种子',1500,'杨桃的种子',12,60,30),(31,'南瓜种子',1550,'南瓜的种子',14,62,31),(39,'金盏花种子',1950,'金盏花的种子',14,78,39),(43,'忧郁蘑菇孢子',2150,'忧郁蘑菇的孢子',14,86,43);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_paswword` varchar(40) NOT NULL,
  `user_lv` int(10) NOT NULL,
  `user_money` int(20) NOT NULL,
  `user_type` int(10) NOT NULL,
  `user_accesstime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`user_paswword`,`user_lv`,`user_money`,`user_type`,`user_accesstime`) values (3,'小明','123123',6,164142,2,'2018-06-11 13:40:38'),(4,'李狗蛋','123123',2,2222,2,'2018-06-21 16:33:45'),(5,'赵铁柱','123123',6,2750,2,'2018-06-24 03:02:49');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
