/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.77
 Source Server Type    : MySQL
 Source Server Version : 50559
 Source Host           : 192.168.1.77:3306
 Source Schema         : ducss_test

 Target Server Type    : MySQL
 Target Server Version : 50559
 File Encoding         : 65001

 Date: 30/10/2020 13:49:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for collect_flow
-- ----------------------------
DROP TABLE IF EXISTS `collect_flow`;
CREATE TABLE `collect_flow`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `year` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属年度',
  `province_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省id',
  `city_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市id',
  `area_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属行政区划,区县id',
  `level` tinyint(4) NULL DEFAULT NULL COMMENT '审核等级县级3 ，市级4 ，部级 6 ，省级 5 ',
  `theory_num` decimal(38, 10) NULL DEFAULT 0.0000000000 COMMENT '理论资源量',
  `collect_num` decimal(38, 10) NULL DEFAULT 0.0000000000 COMMENT '可收集资源量',
  `main_num` decimal(38, 10) NULL DEFAULT 0.0000000000 COMMENT '市场主体规模化利用量',
  `farmer_split_num` decimal(38, 10) NULL DEFAULT 0.0000000000 COMMENT '农户分散利用量',
  `direct_return_num` decimal(38, 10) NULL DEFAULT 0.0000000000 COMMENT '直接还田量',
  `straw_utilize_num` decimal(30, 10) NULL DEFAULT 0.0000000000 COMMENT '秸秆利用量',
  `syn_utilize_num` decimal(38, 10) NULL DEFAULT NULL COMMENT '综合利用率',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '0保存1已上报2已读3已退回4已撤回5已通过',
  `isreport` tinyint(4) NULL DEFAULT 0 COMMENT '是否生成报告',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者id',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `buy_other_num` decimal(38, 10) NULL DEFAULT 0.0000000000 COMMENT '外县购入量',
  `export_num` decimal(38, 10) NULL DEFAULT 0.0000000000 COMMENT '调出量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique`(`year`, `area_id`) USING BTREE,
  INDEX `cf_year`(`year`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for collect_flow_log
-- ----------------------------
DROP TABLE IF EXISTS `collect_flow_log`;
CREATE TABLE `collect_flow_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `year` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属年度',
  `area_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属行政区划',
  `area_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属行政区划名称',
  `operation` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  `minhour` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作内容描述',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for country_task
-- ----------------------------
DROP TABLE IF EXISTS `country_task`;
CREATE TABLE `country_task`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `year` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属年度',
  `province_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属行政区划',
  `peasant_total` int(11) NOT NULL DEFAULT 0 COMMENT '总农户数',
  `expect_num` int(11) NOT NULL DEFAULT 0 COMMENT '预计填报数',
  `fact_num` int(11) NULL DEFAULT 0 COMMENT '实际填报数',
  `main_num` int(11) NULL DEFAULT 0 COMMENT '主体个数',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '0保存1已上报2已读3退回4撤回5已通过',
  `is_report` tinyint(4) NULL DEFAULT 0 COMMENT '是否生成报表',
  `task_level` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `year_area_status`(`year`, `area_id`, `status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for disperse_utilize
-- ----------------------------
DROP TABLE IF EXISTS `disperse_utilize`;
CREATE TABLE `disperse_utilize`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fill_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '填报编号',
  `year` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `report_area` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '填报单位',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `farmer_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '农户序号',
  `farmer_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户主姓名',
  `farmer_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户主电话',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `province_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `year_areaid_key`(`year`, `area_id`) USING BTREE COMMENT '联合索引',
  INDEX `index_year`(`year`) USING BTREE,
  INDEX `index_province_id`(`province_id`) USING BTREE,
  INDEX `index_city`(`city_id`) USING BTREE,
  INDEX `index_create_time`(`create_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '农户分散利用量填报表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for disperse_utilize_detail
-- ----------------------------
DROP TABLE IF EXISTS `disperse_utilize_detail`;
CREATE TABLE `disperse_utilize_detail`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `utilize_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '农户分散利用量填报表ID',
  `straw_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '秸秆类型',
  `straw_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fertilising` decimal(38, 10) NOT NULL COMMENT '肥料化',
  `forage` decimal(38, 10) NOT NULL COMMENT '饲料化',
  `fuel` decimal(38, 10) NOT NULL COMMENT '燃料化',
  `base` decimal(38, 10) NOT NULL COMMENT '基料化',
  `material` decimal(38, 10) NOT NULL COMMENT '原料化',
  `sown_area` decimal(38, 10) NOT NULL COMMENT '播种面积',
  `yield_per_mu` decimal(38, 10) NOT NULL COMMENT '亩产',
  `reuse` decimal(38, 10) NOT NULL COMMENT '收集利用量',
  `application` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用途',
  `fertilize` decimal(38, 10) NULL DEFAULT NULL COMMENT '肥料化利用量',
  `feed` decimal(38, 10) NULL DEFAULT NULL COMMENT '饲料化利用量',
  `fuelled` decimal(38, 10) NULL DEFAULT NULL COMMENT '燃料化利用量',
  `base_mat` decimal(38, 10) NULL DEFAULT NULL COMMENT '基料化利用量',
  `materialization` decimal(38, 10) NULL DEFAULT NULL COMMENT '原料化利用量',
  `straw_utilization` decimal(38, 10) NULL DEFAULT NULL COMMENT '秸秆利用量',
  `totol_rate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '综合利用率',
  `compr_util_index` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '综合利用能力指数',
  `indu_util_index` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产业化利用能力指数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `INDEX_DETAIL_ID`(`id`) USING BTREE,
  INDEX `straw_type_key`(`straw_type`) USING BTREE,
  INDEX `utilizeid_key`(`utilize_id`) USING BTREE,
  INDEX `straw_utilize`(`utilize_id`, `straw_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for disperse_utilize_detail_result
-- ----------------------------
DROP TABLE IF EXISTS `disperse_utilize_detail_result`;
CREATE TABLE `disperse_utilize_detail_result`  (
  `province_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '农户分散利用量填报表ID',
  `YEAR` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `straw_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '秸秆类型',
  `straw_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fertilising` decimal(38, 10) NOT NULL COMMENT '肥料化',
  `forage` decimal(38, 10) NOT NULL COMMENT '饲料化',
  `fuel` decimal(38, 10) NOT NULL COMMENT '燃料化',
  `base` decimal(38, 10) NOT NULL COMMENT '基料化',
  `material` decimal(38, 10) NOT NULL COMMENT '原料化',
  `sown_area` decimal(38, 10) NOT NULL COMMENT '播种面积',
  `reuse` decimal(38, 10) NOT NULL COMMENT '收集利用量',
  `fertilize` decimal(38, 10) NULL DEFAULT NULL COMMENT '肥料化利用量',
  `feed` decimal(38, 10) NULL DEFAULT NULL COMMENT '饲料化利用量',
  `fuelled` decimal(38, 10) NULL DEFAULT NULL COMMENT '燃料化利用量',
  `base_mat` decimal(38, 10) NULL DEFAULT NULL COMMENT '基料化利用量',
  `materialization` decimal(38, 10) NULL DEFAULT NULL COMMENT '原料化利用量',
  `straw_utilization` decimal(38, 10) NULL DEFAULT NULL COMMENT '秸秆利用量',
  `totol_rate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '综合利用率',
  `compr_util_index` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '综合利用能力指数',
  `indu_util_index` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产业化利用能力指数',
  `yield_per_mu` decimal(38, 10) NOT NULL COMMENT '亩产',
  INDEX `straw_type_key`(`straw_type`) USING BTREE,
  INDEX `utilizeid_key`(`area_id`) USING BTREE,
  INDEX `straw_utilize`(`area_id`, `straw_type`) USING BTREE,
  INDEX `index_detail_result_year`(`YEAR`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'text',
  `send_object` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送对象',
  `issued_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下发人',
  `message_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型（通知，上报消息，审核消息）消息类型',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息状态（已读，未读）',
  `send_status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下发状态',
  `send_time` datetime NULL DEFAULT NULL COMMENT '下发日期',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户信息',
  `area_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域id',
  `audit_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `user_level` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送用户等级\r\n',
  `user_areaId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送用户范围',
  `audit_status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作状态',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间\r\n',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pro_still
-- ----------------------------
DROP TABLE IF EXISTS `pro_still`;
CREATE TABLE `pro_still`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fill_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '填报编号',
  `year` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属年度',
  `province_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属行政区划',
  `report_area` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '填报单位',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `year_area2_key`(`year`, `area_id`) USING BTREE,
  INDEX `index_province_id`(`province_id`) USING BTREE,
  INDEX `index_city`(`city_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pro_still_detail
-- ----------------------------
DROP TABLE IF EXISTS `pro_still_detail`;
CREATE TABLE `pro_still_detail`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pro_still_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '还田量主键ID',
  `straw_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '秸秆类型',
  `straw_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grain_yield` decimal(38, 10) NOT NULL COMMENT '粮食产量',
  `grass_valley_ratio` decimal(38, 10) NOT NULL COMMENT '草谷比',
  `collection_ratio` decimal(38, 10) NOT NULL COMMENT '收集系数',
  `return_ratio` decimal(38, 10) NOT NULL COMMENT '还田比例',
  `return_area` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '还田面积',
  `seed_area` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '播种面积',
  `export_yield` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '调出量',
  `theory_resource` decimal(38, 10) NULL DEFAULT NULL COMMENT '产生量=粮食产量*草谷比',
  `collect_resource` decimal(38, 10) NULL DEFAULT NULL COMMENT '可收集量=产生量*收集系数',
  `return_resource` decimal(38, 10) NULL DEFAULT NULL COMMENT '直接还田量=可收集量*还田比例',
  `fertilize` decimal(38, 10) NULL DEFAULT NULL COMMENT '肥料化利用量=市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量',
  `feed` decimal(38, 10) NULL DEFAULT NULL COMMENT '饲料化利用量',
  `fuelled` decimal(38, 10) NULL DEFAULT NULL COMMENT '燃料化利用量',
  `base_mat` decimal(38, 10) NULL DEFAULT NULL COMMENT '基料化利用量',
  `materialization` decimal(38, 10) NULL DEFAULT NULL COMMENT '原料化利用量',
  `straw_utilization` decimal(38, 10) NULL DEFAULT NULL COMMENT '秸秆利用量=市场主体利用量+农户分散利用量+直接还田量 - 调入量 + 调出量',
  `totol_rate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '综合利用率=秸秆利用量/可收集量 *100%',
  `compr_util_index` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '综合利用能力指数=市场主体规模化利用量+农户分散利用量+直接还田量/可收集量*100%',
  `indu_util_index` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产业化利用能力指数=市场主体规模化利用量/可收集量*100%',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `straw_type2_key`(`straw_type`) USING BTREE,
  INDEX `prostillid_key`(`pro_still_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for standard_set
-- ----------------------------
DROP TABLE IF EXISTS `standard_set`;
CREATE TABLE `standard_set`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标准表id',
  `year` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属年度',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `operation_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for standard_value
-- ----------------------------
DROP TABLE IF EXISTS `standard_value`;
CREATE TABLE `standard_value`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作物类型标准值id',
  `straw_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作物类型',
  `grass_valley` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '草谷比值',
  `collect_coefficient` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可收集系数',
  `ss_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标准值区域表主键id',
  `year` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属年度',
  `area` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属区域',
  `area_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属区域id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for straw_produce
-- ----------------------------
DROP TABLE IF EXISTS `straw_produce`;
CREATE TABLE `straw_produce`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `year` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `area_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `straw_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `theory_resource` decimal(38, 10) NOT NULL COMMENT '理论资源量',
  `collect_resource` decimal(38, 10) NOT NULL COMMENT '可收集资源量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '秸秆产生量汇总' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for straw_utilize
-- ----------------------------
DROP TABLE IF EXISTS `straw_utilize`;
CREATE TABLE `straw_utilize`  (
  `id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fill_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '填报编号',
  `year` varchar(4) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `province_id` varchar(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `city_id` varchar(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `area_id` varchar(12) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `report_area` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '填报单位',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `main_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市场主体序号',
  `main_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市场主体名称',
  `corporation_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人名称',
  `company_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位电话',
  `mobile_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `create_user_name` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `area_id_and_year`(`year`, `area_id`) USING BTREE,
  INDEX `year`(`year`) USING BTREE,
  INDEX `index_province_id`(`province_id`) USING BTREE,
  INDEX `index_city_id`(`city_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for straw_utilize_detail
-- ----------------------------
DROP TABLE IF EXISTS `straw_utilize_detail`;
CREATE TABLE `straw_utilize_detail`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `utilize_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `straw_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '秸秆类型',
  `straw_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fertilising` decimal(38, 10) NOT NULL COMMENT '肥料化',
  `forage` decimal(38, 10) NOT NULL COMMENT '饲料化',
  `fuel` decimal(38, 10) NOT NULL COMMENT '饲料化',
  `base` decimal(38, 10) NOT NULL COMMENT '基料化',
  `material` decimal(38, 10) NOT NULL COMMENT '原料化',
  `other` decimal(38, 10) NOT NULL COMMENT '外县购入',
  `market_ent` decimal(38, 10) NULL DEFAULT NULL COMMENT '市场主体利用量=五料化之和',
  `fertilize` decimal(38, 10) NULL DEFAULT NULL COMMENT '肥料化利用量',
  `feed` decimal(38, 10) NULL DEFAULT NULL COMMENT '饲料化利用量',
  `fuelled` decimal(38, 10) NULL DEFAULT NULL COMMENT '燃料化利用量',
  `base_mat` decimal(38, 10) NULL DEFAULT NULL COMMENT '基料化利用量',
  `materialization` decimal(38, 10) NULL DEFAULT NULL COMMENT '原料化利用量',
  `straw_utilization` decimal(38, 10) NULL DEFAULT NULL COMMENT '秸秆利用量',
  `totol_rate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '综合利用率',
  `compr_util_index` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '综合利用能力指数',
  `indu_util_index` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产业化利用能力指数',
  `year` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属年度，冗余字段，用于方便查看',
  `area_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属区划省市区名称，方便查询',
  `area_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属区划ID，冗余字段',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `utilize_id`(`utilize_id`) USING BTREE,
  INDEX `sud_fertilising`(`fertilising`) USING BTREE,
  INDEX `sud_forage`(`forage`) USING BTREE,
  INDEX `sud_fuel`(`fuel`) USING BTREE,
  INDEX `sud_base`(`base`) USING BTREE,
  INDEX `sud_material`(`material`) USING BTREE,
  INDEX `sud_other`(`other`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for straw_utilize_detail_2018
-- ----------------------------
DROP TABLE IF EXISTS `straw_utilize_detail_2018`;
CREATE TABLE `straw_utilize_detail_2018`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `utilize_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `straw_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '秸秆类型',
  `straw_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fertilising` decimal(38, 10) NOT NULL COMMENT '肥料化',
  `forage` decimal(38, 10) NOT NULL COMMENT '饲料化',
  `fuel` decimal(38, 10) NOT NULL COMMENT '饲料化',
  `base` decimal(38, 10) NOT NULL COMMENT '基料化',
  `material` decimal(38, 10) NOT NULL COMMENT '原料化',
  `other` decimal(38, 10) NOT NULL COMMENT '外县购入',
  `market_ent` decimal(38, 10) NULL DEFAULT NULL COMMENT '市场主体利用量=五料化之和',
  `fertilize` decimal(38, 10) NULL DEFAULT NULL COMMENT '肥料化利用量',
  `feed` decimal(38, 10) NULL DEFAULT NULL COMMENT '饲料化利用量',
  `fuelled` decimal(38, 10) NULL DEFAULT NULL COMMENT '燃料化利用量',
  `base_mat` decimal(38, 10) NULL DEFAULT NULL COMMENT '基料化利用量',
  `materialization` decimal(38, 10) NULL DEFAULT NULL COMMENT '原料化利用量',
  `straw_utilization` decimal(38, 10) NULL DEFAULT NULL COMMENT '秸秆利用量',
  `totol_rate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '综合利用率',
  `compr_util_index` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '综合利用能力指数',
  `indu_util_index` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产业化利用能力指数',
  `year` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属年度，冗余字段，用于方便查看',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `utilize_id`(`utilize_id`) USING BTREE,
  INDEX `sud_fertilising`(`fertilising`) USING BTREE,
  INDEX `sud_forage`(`forage`) USING BTREE,
  INDEX `sud_fuel`(`fuel`) USING BTREE,
  INDEX `sud_base`(`base`) USING BTREE,
  INDEX `sud_material`(`material`) USING BTREE,
  INDEX `sud_other`(`other`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for straw_utilize_sum
-- ----------------------------
DROP TABLE IF EXISTS `straw_utilize_sum`;
CREATE TABLE `straw_utilize_sum`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `year` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `province_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `straw_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '秸秆类型',
  `main_fertilising` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '市场主体肥料化',
  `main_forage` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '市场主体饲料化',
  `main_fuel` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '市场主体燃料化',
  `main_base` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '市场主体基料化',
  `main_material` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '市场主体原料化',
  `main_total` decimal(38, 10) NOT NULL COMMENT ' 主体合计',
  `main_total_other` decimal(38, 10) NOT NULL COMMENT '其他县购入',
  `disperse_fertilising` decimal(38, 10) NOT NULL COMMENT '分散利用肥料化',
  `disperse_forage` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '分散利用饲料化',
  `disperse_fuel` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '分散利用燃料化',
  `disperse_base` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '分散利用基料化',
  `disperse_material` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '分散利用原料化',
  `disperse_total` decimal(38, 10) NOT NULL COMMENT '分散利用合计',
  `pro_still_field` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '直接还田量',
  `pro_straw_utilize` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '秸秆利用量',
  `collect_resource` decimal(38, 10) NULL DEFAULT 0.0000000000 COMMENT '可收集资源量',
  `theory_resource` decimal(38, 10) NULL DEFAULT 0.0000000000 COMMENT '理论资源量',
  `export_yield_total` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '总调出量',
  `grain_yield` decimal(38, 10) NULL DEFAULT NULL COMMENT '粮食产量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '秸秆产生量汇总' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for straw_utilize_sum_total
-- ----------------------------
DROP TABLE IF EXISTS `straw_utilize_sum_total`;
CREATE TABLE `straw_utilize_sum_total`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `year` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `province_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `main_fertilising` decimal(38, 10) NULL DEFAULT NULL,
  `main_forage` decimal(38, 10) NULL DEFAULT NULL,
  `main_fuel` decimal(38, 10) NULL DEFAULT NULL,
  `main_base` decimal(38, 10) NULL DEFAULT NULL,
  `main_material` decimal(38, 10) NULL DEFAULT NULL,
  `main_total` decimal(38, 10) NULL DEFAULT NULL,
  `main_total_other` decimal(38, 10) NULL DEFAULT NULL,
  `disperse_fertilising` decimal(38, 10) NULL DEFAULT NULL,
  `disperse_forage` decimal(38, 10) NULL DEFAULT NULL,
  `disperse_fuel` decimal(38, 10) NULL DEFAULT NULL,
  `disperse_base` decimal(38, 10) NULL DEFAULT NULL,
  `disperse_material` decimal(38, 10) NULL DEFAULT NULL,
  `disperse_total` decimal(38, 10) NULL DEFAULT NULL,
  `pro_straw_utilize` decimal(38, 10) NULL DEFAULT NULL,
  `return_ratio` decimal(38, 10) NULL DEFAULT NULL,
  `comprehensive` decimal(38, 10) NULL DEFAULT NULL,
  `comprehensive_index` decimal(38, 10) NULL DEFAULT NULL,
  `industrialization_index` decimal(38, 10) NULL DEFAULT NULL,
  `collect_resource` decimal(38, 10) NULL DEFAULT 0.0000000000 COMMENT '可收集资源量',
  `yield_all_num` decimal(38, 10) NULL DEFAULT NULL,
  `theory_resource` decimal(38, 10) NULL DEFAULT 0.0000000000 COMMENT '理论资源量',
  `export_yield_total` decimal(38, 10) NOT NULL DEFAULT 0.0000000000 COMMENT '总调出量',
  `fertilising` decimal(38, 10) NULL DEFAULT NULL,
  `forage` decimal(38, 10) NULL DEFAULT NULL,
  `fuel` decimal(38, 10) NULL DEFAULT NULL,
  `base` decimal(38, 10) NULL DEFAULT NULL COMMENT '直接还田量',
  `material` decimal(38, 10) NULL DEFAULT NULL,
  `grass_valley_ratio` decimal(38, 10) NULL DEFAULT NULL,
  `return_resource` decimal(38, 10) NULL DEFAULT NULL,
  `collection_ratio` decimal(38, 10) NULL DEFAULT NULL,
  `level` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '汇总级别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_value` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_no` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_region
-- ----------------------------
DROP TABLE IF EXISTS `sys_region`;
CREATE TABLE `sys_region`  (
  `id` int(11) NOT NULL,
  `region_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REGION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PARENT_NAMES` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for threshold_value_manager
-- ----------------------------
DROP TABLE IF EXISTS `threshold_value_manager`;
CREATE TABLE `threshold_value_manager`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `year` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '年度',
  `table_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表类型：\n1.数据审核\r\n2. 产生情况汇总\r\n3. 利用情况汇总\r\n4.还田离田情况',
  `target_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1-1.产生量（万吨）\r\n1-2.可收集量（万吨）\r\n1-3.综合利用量（万吨）\r\n1-4.综合利用率（%）\r\n1-5. 直接还田量（万吨）\r\n1-6. 离田利用量（万吨）\r\n1-7. 抽样分散户数（个\r\n1-8.市场主体规模化数量（个）\r\n1-9.产生量（万吨）\r\n2-1.产生量（万吨）\r\n2-2.产生量占比（%）\r\n2-3.可收集量（万吨）\r\n2-3.可收集量比例（%）\r\n2-5.粮食产量（万吨）\r\n2-6.播种面积（千公顷）\r\n3-1.秸秆利用量（万吨）\r\n3-2 综合利用率（%）\r\n3-3.合计\r\n3-4.肥料化\r\n3-5 饲料化\r\n3-6. 燃料化\r\n3-7.基料化\r\n3-8.原料化\r\n3-9.综合利用能力指数\r\n3-10.产业化利用能力指数\r\n\r\n4-1.直接还田量（万吨）\r\n4-2.直接还田率（%）\r\n4-3.合计\r\n4-4.农户分散利用量\r\n4-5.市场主体规模化利用量',
  `value1` decimal(10, 2) NULL DEFAULT NULL COMMENT '阈值1',
  `operate1` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阈值1的操作',
  `value2` decimal(10, 2) NULL DEFAULT NULL COMMENT '阈值2',
  `operate2` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阈值2的操作',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tvm_table_type`(`table_type`) USING BTREE,
  INDEX `tvm_target_type`(`target_type`) USING BTREE,
  INDEX `tvm_year`(`year`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for threshold_year_manager
-- ----------------------------
DROP TABLE IF EXISTS `threshold_year_manager`;
CREATE TABLE `threshold_year_manager`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `year` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '年度',
  `operation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作人',
  `operation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `is_add` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否新增数据1 新增',
  `odd_year` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '如果不是新增数据，该字段必填',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tym_year`(`year`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
