/*
Navicat MySQL Data Transfer

Source Server         : MyTestDB
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : mytest

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-10-17 13:25:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `sex` tinyint(10) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1000', '1', '测试数据02', '123456', '13547128666');
INSERT INTO `t_user` VALUES ('1001', '0', 'Jacky', '666666', '13547426666');
INSERT INTO `t_user` VALUES ('1002', '1', '测试数据01', '666666', '13547426666');
INSERT INTO `t_user` VALUES ('1003', '2', '测试类中添加的', '88888888', '13547426666');
INSERT INTO `t_user` VALUES ('1004', '1', '漩涡鸣人', '88888888', '13547426666');
