/*
Navicat MySQL Data Transfer

Source Server         : testforself
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : xszheng

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-10-23 16:07:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sk_product_stock
-- ----------------------------
DROP TABLE IF EXISTS `sk_product_stock`;
CREATE TABLE `sk_product_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(20) NOT NULL COMMENT '商品库存',
  `start_time` datetime NOT NULL COMMENT '秒杀开始时间',
  `end_time` datetime NOT NULL COMMENT '秒杀结束时间',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_add_time` (`add_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='秒杀商品库存表';

-- ----------------------------
-- Records of sk_product_stock
-- ----------------------------
INSERT INTO `sk_product_stock` VALUES ('1000', '1000元秒杀iPhone7S', '100', '2017-11-01 00:00:00', '2017-11-02 00:00:00', '2017-10-23 16:05:11');
INSERT INTO `sk_product_stock` VALUES ('1001', '500元秒杀ipad mini 4', '200', '2017-11-01 00:00:00', '2017-11-02 00:00:00', '2017-10-23 16:05:11');
INSERT INTO `sk_product_stock` VALUES ('1002', '300元秒杀小米4', '300', '2017-11-01 00:00:00', '2017-11-02 00:00:00', '2017-10-23 16:05:11');
INSERT INTO `sk_product_stock` VALUES ('1003', '200元秒杀红米note', '400', '2017-11-01 00:00:00', '2017-11-02 00:00:00', '2017-10-23 16:05:11');
