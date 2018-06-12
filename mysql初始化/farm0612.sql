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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `crop` */

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `field` */

insert  into `field`(`field_id`,`user_id`,`seed_id`,`field_status`,`filed_harvest_number`,`field_planting_time`) values (1,1,1,1,1,'2018-06-11 18:33:17');

/*Table structure for table `repository` */

DROP TABLE IF EXISTS `repository`;

CREATE TABLE `repository` (
  `res_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `res_goods_id` int(10) NOT NULL,
  `res_type` int(10) NOT NULL,
  `res_number` int(10) NOT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `repository` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `seed` */

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`user_paswword`,`user_lv`,`user_money`,`user_type`,`user_accesstime`) values (3,'gg','312',2,111,2,'2018-06-11 13:40:38');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
