/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : ducss_test2

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 18/11/2020 16:47:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for straw_usage_sum
-- ----------------------------
DROP TABLE IF EXISTS `straw_usage_sum`;
CREATE TABLE `straw_usage_sum`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `year` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属年度',
  `area_id` varchar(7) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属区域',
  `straw_type` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '秸秆类型',
  `straw_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '秸秆名称',
  `straw_usage` decimal(38, 10) NULL DEFAULT NULL COMMENT '秸秆利用量=(市场主体利用量+农户分散利用量+直接还田量+调出量-调入量)',
  `comprehensive_rate` decimal(38, 10) NULL DEFAULT NULL COMMENT '综合利用率（%）：秸秆利用量/可收集量 *100%',
  `all_total` decimal(38, 10) NULL DEFAULT NULL COMMENT '五料化合计=（分散五料+主体五料）',
  `fertilizer` decimal(38, 10) NULL DEFAULT NULL COMMENT '肥料化利用量：市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量',
  `fuel` decimal(38, 10) NULL DEFAULT NULL COMMENT '燃料化利用量（吨）：市场主体利用量（燃料）+农户分散利用量（燃料',
  `basic` decimal(38, 10) NULL DEFAULT NULL COMMENT '基料化利用量（吨）：市场主体利用量（基料）+农户分散利用量（基料）',
  `raw_material` decimal(38, 10) NULL DEFAULT NULL COMMENT '原料化利用量（吨）：市场主体利用量（原料）+农户分散利用量（原料）',
  `feed` decimal(38, 10) NULL DEFAULT NULL COMMENT '饲料：市场主体利用量（饲料）+农户分散利用量（饲料）',
  `other` decimal(38, 10) NULL DEFAULT NULL COMMENT '市场化主体调入量(吨)=外县购入：市场主体规模化秸秆利用量填报表得到',
  `yield_export` decimal(38, 10) NULL DEFAULT NULL COMMENT '市场化主体调出量(吨)：市场主体规模化秸秆利用量填报表得到',
  `comprehensive_index` decimal(38, 10) NULL DEFAULT NULL COMMENT '综合利用能力指数：市场主体规模化利用量+农户分散利用量+直接还田量/可收集量*100',
  `industrialization_index` decimal(38, 10) NULL DEFAULT NULL COMMENT '产业化利用能力指数：市场主体规模化利用量/可收集量*100',
  `collect_resource` decimal(38, 10) NULL DEFAULT NULL COMMENT '可收集资源量',
  `main_total` decimal(38, 10) NULL DEFAULT NULL COMMENT '市场主体利用总量',
  `return_resource` decimal(38, 10) NULL DEFAULT NULL COMMENT '直接还田量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '秸秆利用汇总-分散+市场' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;




	-- 11.19
-- 	insert into straw_usage_sum (id,
--                 year,
--                 area_id,
--                 straw_type,
--                 straw_name,
--                 straw_usage,
--                 comprehensive_rate,
--                 all_total,
--                 fertilizer,
--                 fuel,
--                 basic,
--                 raw_material,
--                 feed,
--                 other,
--                 yield_export,
--                 comprehensive_index,
--                 industrialization_index,
--                 collect_resource,
--                 main_total,
--                 return_resource)
--
-- (
-- SELECT
-- 	straw_utilize_sum.id as id,
-- 	YEAR,
-- 	area_id,
-- 	straw_type,
-- 	dict_value as straw_name,
-- 	main_total + disperse_total + pro_still_field + export_yield_total - main_total_other AS straw_usage,-- 秸秆利用量=(市场主体利用量+农户分散利用量+直接还田量+调出量-调入量)
--  round(IFNULL(((main_total + disperse_total +pro_still_field+ export_yield_total - main_total_other ) / collect_resource *100),0),10) AS comprehensive_rate, -- 秸秆利用量/可收集量
--
-- 	main_total + disperse_total + pro_still_field AS all_total,-- (农户合计+市场合计+直接还田)
-- 	main_fertilising + disperse_fertilising + pro_still_field AS fertilizer,-- 市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量
-- 	main_fuel + disperse_fuel AS fuel,-- 燃料
-- 	main_base + disperse_base AS basic,-- 基料
-- 	main_material + disperse_material AS raw_material,-- 原料
-- 	main_forage + disperse_forage AS feed,-- 饲料
-- 	main_total_other AS other, -- 调入
-- 	export_yield_total AS yield_export , -- 调出
-- 	round(IFNULL((( main_total + disperse_total + pro_still_field ) / collect_resource),0),10) AS comprehensive_index, -- 综合利用能力指数
-- round( IFNULL((main_total / collect_resource) ,0),10) AS industrialization_index,-- 产业利用能力指数
--
-- 	collect_resource AS collect_resource,
-- 	main_total AS main_total,
-- 	pro_still_field AS return_resource
-- FROM
-- 	straw_utilize_sum , sys_dictionary
-- 	WHERE straw_type=dict_key);


insert into straw_usage_sum (id,
                year,
                area_id,
                straw_type,
                straw_name,
                straw_usage,
                comprehensive_rate,
                all_total,
                fertilizer,
                fuel,
                basic,
                raw_material,
                feed,
                other,
                yield_export,
                comprehensive_index,
                industrialization_index,
                collect_resource,
                main_total,
                return_resource)

(
SELECT
	straw_utilize_sum.id as id,
	YEAR,
	area_id,
	straw_type,
	dict_value as straw_name,
	main_total + disperse_total + pro_still_field + export_yield_total - main_total_other AS straw_usage,-- 秸秆利用量=(市场主体利用量+农户分散利用量+直接还田量+调出量-调入量)
 round(IFNULL((
CASE WHEN collect_resource=0 THEN 0 ELSE
(main_total + disperse_total +pro_still_field+ export_yield_total - main_total_other ) / collect_resource *100 END),0),10) AS comprehensive_rate, -- 秸秆利用量/可收集量

	main_total + disperse_total + pro_still_field AS all_total,-- (农户合计+市场合计+直接还田)
	main_fertilising + disperse_fertilising + pro_still_field AS fertilizer,-- 市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量
	main_fuel + disperse_fuel AS fuel,-- 燃料
	main_base + disperse_base AS basic,-- 基料
	main_material + disperse_material AS raw_material,-- 原料
	main_forage + disperse_forage AS feed,-- 饲料
	main_total_other AS other, -- 调入
	export_yield_total AS yield_export , -- 调出
	round(IFNULL((
CASE WHEN collect_resource=0 THEN 0 ELSE
( main_total + disperse_total + pro_still_field ) / collect_resource  END),0),10) AS comprehensive_index, -- 综合利用能力指数
round( IFNULL((
CASE WHEN collect_resource=0 THEN 0 ELSE
main_total / collect_resource END) ,0),10) AS industrialization_index,-- 产业利用能力指数

	collect_resource AS collect_resource,
	main_total AS main_total,
	pro_still_field AS return_resource
FROM
	straw_utilize_sum , sys_dictionary
	WHERE straw_type=dict_key);










