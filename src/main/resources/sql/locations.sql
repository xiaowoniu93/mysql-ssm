/*
Navicat MySQL Data Transfer

Source Server         : testforself
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : xszheng

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-10-16 16:48:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for locations
-- ----------------------------
DROP TABLE IF EXISTS `locations`;
CREATE TABLE `locations` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `street_address` varchar(50) DEFAULT NULL COMMENT '街道地址',
  `postal_code` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  `province` varchar(20) DEFAULT NULL COMMENT '省',
  `country` varchar(20) DEFAULT NULL COMMENT '国家',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='地址表';

-- ----------------------------
-- Records of locations
-- ----------------------------
INSERT INTO `locations` VALUES ('9', '祥符街道', '310000', '杭州', '浙江', '中国');
INSERT INTO `locations` VALUES ('10', '三墩街道', '310000', '杭州', '浙江', '中国');
INSERT INTO `locations` VALUES ('11', '四季青街道', '310000', '杭州', '浙江', '中国');
INSERT INTO `locations` VALUES ('12', '申花街道', '310000', '杭州', '浙江', '中国');
INSERT INTO `locations` VALUES ('13', '西湖街道', '310000', '杭州', '浙江', '中国');
INSERT INTO `locations` VALUES ('14', '五常街道', '310000', '杭州', '浙江', '中国');
INSERT INTO `locations` VALUES ('15', '留下街道', '310000', '杭州', '浙江', '中国');
INSERT INTO `locations` VALUES ('16', '转塘街道', '310000', '杭州', '浙江', '中国');
INSERT INTO `locations` VALUES ('17', '北山街道', '310000', '杭州', '浙江', '中国');
INSERT INTO `locations` VALUES ('18', '白杨街道', '310000', '杭州', '浙江', '中国');
