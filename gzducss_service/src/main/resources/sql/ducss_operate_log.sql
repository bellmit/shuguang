/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.77-beijing
 Source Server Type    : MySQL
 Source Server Version : 50559
 Source Host           : 192.168.1.77:3306
 Source Schema         : ducss_test

 Target Server Type    : MySQL
 Target Server Version : 50559
 File Encoding         : 65001

 Date: 01/11/2020 14:04:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ducss_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `ducss_operate_log`;
CREATE TABLE `ducss_operate_log` (
  `id` varchar(32) NOT NULL,
  `operate_type` varchar(2) NOT NULL COMMENT '操作类型，1. 新增2. 编辑3. 删除4. 上报5. 撤回6. 退回7. 通过8. 年度任务下发9. 年度任务编辑10.通知下发11.公布数据12.参数新增13.参数编辑 14.参数删除15.阈值新增16.阈值编辑17.阈值删除',
  `operate_detail` varchar(150) DEFAULT NULL COMMENT '操作详情',
  `operate_user_id` varchar(32) NOT NULL COMMENT '操作人ID',
  `operate_user_name` varchar(100) DEFAULT NULL COMMENT '操作人名字',
  `operate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `level` varchar(10) DEFAULT NULL COMMENT '操作级别 部级:ministry 省级:province 市级:city  区县级:county',
  `area_id` varchar(10) DEFAULT NULL COMMENT '当前登录用户所属机构的区划id'
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
