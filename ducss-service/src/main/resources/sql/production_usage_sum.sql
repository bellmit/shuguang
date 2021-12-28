CREATE TABLE `production_usage_sum` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `year` varchar(5) DEFAULT NULL COMMENT '所属年度',
  `area_id` varchar(7) DEFAULT NULL COMMENT '所属区域',
  `produce` decimal(38,10) DEFAULT NULL COMMENT '产生量=粮食产量 吨*草谷比',
  `collect` decimal(38,10) DEFAULT NULL COMMENT '可收集量=粮食产量（吨）*草谷比*收集系数',
  `straw_usage` decimal(38,10) DEFAULT NULL COMMENT '秸秆利用量=(市场主体利用量+农户分散利用量+直接还田量+调出量-调入量)',
  `comprehensive_rate` decimal(38,10) DEFAULT NULL COMMENT '综合利用率（%）：秸秆利用量/可收集量 *100%',
  `all_total` decimal(38,10) DEFAULT NULL COMMENT '五料化合计=（分散五料+主体五料）',
  `fertilizer` decimal(38,10) DEFAULT NULL COMMENT '肥料利用：市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量',
  `fuel` decimal(38,10) DEFAULT NULL COMMENT '燃料化利用量（吨）：市场主体利用量（燃料）+农户分散利用量（燃料',
  `basic` decimal(38,10) DEFAULT NULL COMMENT '基料化利用量（吨）：市场主体利用量（基料）+农户分散利用量（基料）',
  `raw_material` decimal(38,10) DEFAULT NULL COMMENT '原料化利用量（吨）：市场主体利用量（原料）+农户分散利用量（原料）',
  `comprehensive_index` decimal(38,10) DEFAULT NULL COMMENT '综合利用能力指数：市场主体规模化利用量+农户分散利用量+直接还田量/可收集量*100',
  `industrialization_index` decimal(38,10) DEFAULT NULL COMMENT '产业化利用能力指数：市场主体规模化利用量/可收集量*100',
  `feed` decimal(38,10) DEFAULT NULL COMMENT '饲料：市场主体利用量（饲料）+农户分散利用量（饲料）',
  `main_total` decimal(38,10) DEFAULT NULL COMMENT '市场主体合计',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产生量与利用量汇总' ;


-- 11.17
-- insert into   production_usage_sum ( id,
--                 year,
--                 area_id,
--                 produce,
--                 collect,
--                 straw_usage,
--                 comprehensive_rate,
--                 all_total,
--                 fertilizer,
--                 fuel,
--                 basic,
--                 raw_material,
--                 comprehensive_index,
--                 industrialization_index,
-- 								feed,
--                 main_total)  ( SELECT
-- 		id,
-- 		YEAR,
-- 		area_id,
-- 		theory_resource AS produce,-- 产生量,
-- 		collect_resource AS collect,-- 可收集资源量
-- 		main_total + disperse_total + return_resource + export_yield_total - main_total_other AS straw_usage,-- 秸秆利用量
-- 		round( IFNULL( ( ( main_total + disperse_total + return_resource + export_yield_total - main_total_other ) / collect_resource * 100 ), 0 ), 10 ) AS comprehensive_rate,-- 综合利用率
-- 		main_total + disperse_total + return_resource AS all_total,
-- 		main_fertilising + disperse_fertilising + return_resource AS fertilizer,
-- 		main_fuel + disperse_fuel AS fuel,
-- 		main_base + disperse_base AS basic,
-- 		main_material + disperse_material AS raw_material,
-- 		round( IFNULL( ( ( main_total + disperse_total + return_resource ) / collect_resource ), 0 ), 10 ) AS comprehensive_index,-- 综合利用能力指数
-- 		round( IFNULL( ( main_total / collect_resource ), 0 ), 10 ) AS industrialization_index,-- 产业利用能力指数
-- 		main_forage + disperse_forage AS feed,
-- 		main_total AS main_total
-- 	FROM
-- 		straw_utilize_sum_total);


insert into   production_usage_sum ( id,
                year,
                area_id,
                produce,
                collect,
                straw_usage,
                comprehensive_rate,
                all_total,
                fertilizer,
                fuel,
                basic,
                raw_material,
                comprehensive_index,
                industrialization_index,
								feed,
                main_total)  ( SELECT
		id,
		YEAR,
		area_id,
		theory_resource AS produce,-- 产生量,
		collect_resource AS collect,-- 可收集资源量
		main_total + disperse_total + return_resource + export_yield_total - main_total_other AS straw_usage,-- 秸秆利用量
		round( IFNULL( (
CASE WHEN collect_resource=0 THEN 0 ELSE
 ( main_total + disperse_total + return_resource + export_yield_total - main_total_other ) / collect_resource * 100 END), 0 ), 10 ) AS comprehensive_rate,-- 综合利用率
		main_total + disperse_total + return_resource AS all_total,
		main_fertilising + disperse_fertilising + return_resource AS fertilizer,
		main_fuel + disperse_fuel AS fuel,
		main_base + disperse_base AS basic,
		main_material + disperse_material AS raw_material,
		round( IFNULL( (
CASE WHEN collect_resource=0 THEN 0 ELSE
( main_total + disperse_total + return_resource ) / collect_resource END), 0 ), 10 ) AS comprehensive_index,-- 综合利用能力指数
		round( IFNULL(
 (CASE WHEN collect_resource=0 THEN 0 ELSE main_total / collect_resource END), 0 ), 10) AS industrialization_index,-- 产业利用能力指数
		main_forage + disperse_forage AS feed,
		main_total AS main_total
	FROM
		straw_utilize_sum_total);
