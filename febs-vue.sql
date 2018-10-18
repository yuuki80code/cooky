/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.7.23-log : Database - cooky
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cooky` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cooky`;

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `DEPT_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级部门ID',
  `DEPT_NAME` varchar(100) NOT NULL COMMENT '部门名称',
  `ORDER_NUM` bigint(20) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`DEPT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`DEPT_ID`,`PARENT_ID`,`DEPT_NAME`,`ORDER_NUM`,`CREATE_TIME`) values 
(1,0,'开发部',1,'2018-08-13 21:00:18'),
(2,1,'开发一部',1,'2018-08-13 21:00:48'),
(3,0,'人事部',1,'2018-08-14 12:51:32'),
(4,3,'HR部',1,'2018-08-14 12:51:50');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `MENU_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `MENU_NAME` varchar(50) NOT NULL COMMENT '菜单/按钮名称',
  `URL` varchar(100) DEFAULT NULL COMMENT '菜单URL',
  `PERMS` text COMMENT '权限标识',
  `ICON` varchar(50) DEFAULT NULL COMMENT '图标',
  `TYPE` char(2) NOT NULL COMMENT '类型 0菜单 1按钮',
  `ORDER_NUM` bigint(20) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`MENU_ID`,`PARENT_ID`,`MENU_NAME`,`URL`,`PERMS`,`ICON`,`TYPE`,`ORDER_NUM`,`CREATE_TIME`,`MODIFY_TIME`) values 
(1,0,'系统管理','null','sys','zmdi zmdi-settings','0',1,'2018-08-09 21:01:09','2018-10-11 18:02:25'),
(2,1,'部门管理','dept','dept',NULL,'0',4,'2018-08-09 21:02:34','2018-10-11 18:02:38'),
(4,2,'新增部门','','dept_add','','1',NULL,'2018-10-08 16:03:31','2018-10-10 14:36:38'),
(5,1,'用户管理','sys_menu','user','','0',NULL,'2018-10-11 10:32:53','2018-10-12 09:41:40'),
(6,2,'编辑部门','','dept_edit','','1',NULL,'2018-10-11 18:06:33','2018-10-11 18:06:47'),
(7,2,'删除部门','','dept_delete','','1',NULL,'2018-10-11 18:07:14','2018-10-11 18:07:14'),
(8,5,'新增用户','','user_add','','1',NULL,'2018-10-11 18:07:49','2018-10-11 18:07:49'),
(9,5,'编辑用户','','user_edit','','1',NULL,'2018-10-11 18:08:04','2018-10-11 18:08:04'),
(10,5,'删除用户','','user_delete','','1',NULL,'2018-10-11 18:08:25','2018-10-11 18:08:25'),
(11,1,'角色管理','','role','','0',NULL,'2018-10-11 18:09:47','2018-10-11 18:09:47'),
(12,11,'新增角色','','role_add','','1',NULL,'2018-10-11 18:10:11','2018-10-11 18:10:11'),
(13,11,'编辑角色','','role_edit','','1',NULL,'2018-10-11 18:10:30','2018-10-11 18:10:30'),
(14,11,'删除角色','','role_delete','','1',NULL,'2018-10-11 18:10:47','2018-10-11 18:10:47'),
(15,1,'菜单管理','','menu','','0',NULL,'2018-10-11 18:11:21','2018-10-11 18:11:21'),
(16,15,'新增菜单','','menu_add','','1',NULL,'2018-10-11 18:11:46','2018-10-11 18:11:46'),
(17,15,'编辑菜单','','menu_edit','','1',NULL,'2018-10-11 18:12:01','2018-10-11 18:12:01'),
(18,15,'删除菜单','','menu_delete','','1',NULL,'2018-10-11 18:12:17','2018-10-11 18:12:17');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `ROLE_NAME` varchar(100) NOT NULL COMMENT '角色名称',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`ROLE_ID`,`ROLE_NAME`,`REMARK`,`CREATE_TIME`,`MODIFY_TIME`) values 
(1,'超级管理员','最高权限','2018-08-09 20:56:18','2018-10-11 18:12:32'),
(2,'普通用户','普通权限','2018-08-21 22:14:40','2018-10-18 22:33:47');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
  `MENU_ID` bigint(20) NOT NULL COMMENT '菜单/按钮ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`ROLE_ID`,`MENU_ID`) values 
(1,1),
(1,2),
(1,4),
(1,6),
(1,7),
(1,5),
(1,8),
(1,9),
(1,10),
(1,11),
(1,12),
(1,13),
(1,14),
(1,15),
(1,16),
(1,17),
(1,18),
(2,1),
(2,2),
(2,4),
(2,5),
(2,9);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USERNAME` varchar(50) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(128) NOT NULL COMMENT '密码',
  `DEPT_ID` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `EMAIL` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `STATUS` char(1) NOT NULL COMMENT '状态 0锁定 1有效',
  `CRATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL COMMENT '最近访问时间',
  `SSEX` char(1) DEFAULT NULL COMMENT '性别 0男 1女',
  `THEME` varchar(10) DEFAULT NULL COMMENT '主题',
  `AVATAR` varchar(100) DEFAULT NULL COMMENT '头像',
  `DESCRIPTION` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`USER_ID`,`USERNAME`,`PASSWORD`,`DEPT_ID`,`EMAIL`,`MOBILE`,`STATUS`,`CRATE_TIME`,`MODIFY_TIME`,`LAST_LOGIN_TIME`,`SSEX`,`THEME`,`AVATAR`,`DESCRIPTION`) values 
(2,'yuuki','a758268ef5963c80eed5ce4a831b5277',1,'123@123.123',NULL,'1','2018-10-09 14:14:29','2018-10-10 15:43:17',NULL,'1',NULL,NULL,NULL),
(3,'test','8996f646207298744881ea69cbff770c',4,'123@123.com',NULL,'1','2018-10-12 12:41:24','2018-10-12 12:41:24',NULL,'0',NULL,NULL,NULL);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`USER_ID`,`ROLE_ID`) values 
(1,1),
(5,2),
(6,2),
(1,2),
(7,1),
(8,2),
(8,1),
(7,1),
(7,2),
(9,1),
(9,1),
(13,2),
(2,1),
(3,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
