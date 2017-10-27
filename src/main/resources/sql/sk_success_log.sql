/*
Navicat MySQL Data Transfer

Source Server         : testforself
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : xszheng

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-10-27 18:52:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sk_success_log
-- ----------------------------
DROP TABLE IF EXISTS `sk_success_log`;
CREATE TABLE `sk_success_log` (
  `pro_stock_id` bigint(20) NOT NULL COMMENT '秒杀的商品主键',
  `user_phone` varchar(11) NOT NULL COMMENT '秒杀用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态表示：-1：无效 0：成功 1：已付款 2：已发货',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`pro_stock_id`,`user_phone`),
  KEY `idx_add_time` (`add_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- ----------------------------
-- Records of sk_success_log
-- ----------------------------
