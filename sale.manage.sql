/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : sale.manage

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2018-03-16 09:27:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `operationexceptionlog`
-- ----------------------------
DROP TABLE IF EXISTS `operationexceptionlog`;
CREATE TABLE `operationexceptionlog` (
  `exceptionId` int(10) NOT NULL AUTO_INCREMENT COMMENT '异常操作ID',
  `userId` int(10) NOT NULL COMMENT '异常操作用户',
  `operation` text NOT NULL COMMENT '异常操作',
  `operationDate` datetime NOT NULL,
  PRIMARY KEY (`exceptionId`),
  KEY `userId` (`userId`),
  CONSTRAINT `operationexceptionlog_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operationexceptionlog
-- ----------------------------
INSERT INTO `operationexceptionlog` VALUES ('13', '1', '修改了酱油的信息，数量由2改为10', '2018-02-02 17:36:13');
INSERT INTO `operationexceptionlog` VALUES ('14', '1', '删除了刮胡刀的信息', '2018-02-02 17:36:34');
INSERT INTO `operationexceptionlog` VALUES ('15', '1', '删除了刮胡刀的信息', '2018-02-02 17:36:39');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `productId` int(2) NOT NULL AUTO_INCREMENT,
  `productName` varchar(100) DEFAULT NULL,
  `sock` int(10) DEFAULT '0' COMMENT '库存',
  `price` decimal(10,2) DEFAULT NULL,
  `prePrice` decimal(10,2) NOT NULL COMMENT '优惠价',
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('23', '空气进化器', '15', '1000.00', '800.00');
INSERT INTO `product` VALUES ('24', '净水机', '18', '4000.00', '3200.00');
INSERT INTO `product` VALUES ('25', '酱油', '10', '20.00', '16.00');

-- ----------------------------
-- Table structure for `productrecord`
-- ----------------------------
DROP TABLE IF EXISTS `productrecord`;
CREATE TABLE `productrecord` (
  `recordId` int(10) NOT NULL AUTO_INCREMENT,
  `productId` int(10) NOT NULL,
  `recordDate` datetime NOT NULL,
  `count` int(10) NOT NULL,
  `totalPrice` decimal(10,2) DEFAULT NULL,
  `operator` varchar(100) NOT NULL,
  `customer` varchar(100) DEFAULT NULL COMMENT '入库来源或出售客户',
  `type` int(2) NOT NULL COMMENT '操作情况：1.买入；2.售出',
  PRIMARY KEY (`recordId`),
  KEY `productId` (`productId`),
  CONSTRAINT `productrecord_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productrecord
-- ----------------------------
INSERT INTO `productrecord` VALUES ('18', '23', '2018-02-02 17:34:35', '20', '0.00', '贺宁', '进货', '1');
INSERT INTO `productrecord` VALUES ('19', '24', '2018-02-02 17:35:05', '20', '0.00', '贺宁', '进货', '1');
INSERT INTO `productrecord` VALUES ('20', '25', '2018-02-02 17:35:22', '2', '0.00', '贺宁', '客户退货', '1');
INSERT INTO `productrecord` VALUES ('21', '23', '2018-02-02 17:35:39', '5', '5000.00', '贺宁', '加贝宁', '2');
INSERT INTO `productrecord` VALUES ('22', '24', '2018-02-02 17:35:47', '2', '8000.00', '贺宁', '加贝宁', '2');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(2) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'hening', '666666', '贺宁');
