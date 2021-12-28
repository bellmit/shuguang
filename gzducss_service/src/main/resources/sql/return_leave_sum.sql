CREATE TABLE `return_leave_sum` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `year` varchar(5) DEFAULT NULL COMMENT '年份',
  `area_id` varchar(12) DEFAULT NULL COMMENT '区域id',
  `straw_type` varchar(16) DEFAULT NULL COMMENT '秸秆类型',
  `pro_still_field` decimal(38,10) DEFAULT NULL COMMENT '直接还田量',
  `return_ratio` decimal(38,10) DEFAULT NULL COMMENT '还田比例or直接还田率',
  `all_total` decimal(38,10) DEFAULT NULL COMMENT '主体合计+分散合计',
  `disperse_total` decimal(38,10) DEFAULT NULL COMMENT '农户分散利用量合计',
  `main_total` decimal(38,10) DEFAULT NULL COMMENT '主体合计',
  `collect_resource` decimal(38,10) DEFAULT NULL COMMENT '可收集资源量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='还田离田情况汇总';

-- 11.17



-- 	INSERT INTO return_leave_sum ( id,
--                 year,
--                 area_id,
--                 straw_type,
--                 pro_still_field,
--                 return_ratio,
--                 all_total,
--                 disperse_total,
--                 main_total,
--                 collect_resource)
-- 	(SELECT
-- 		id,
-- 		YEAR,
-- 		area_id,
-- 		straw_type,
-- 		pro_still_field AS pro_still_field,
-- 		round( IFNULL( ( collect_resource / pro_still_field ), 0 ), 10 ) AS return_ratio,-- 还田比率
-- 		main_total + disperse_total AS all_total,-- 还田汇总：这里不用加直接还田量。
-- 		disperse_total AS disperse_total,
-- 		main_total AS main_total,
-- 		collect_resource AS collect_resource
-- 	FROM
-- 		straw_utilize_sum
-- 	);

INSERT INTO return_leave_sum ( id,
                year,
                area_id,
                straw_type,
                pro_still_field,
                return_ratio,
                all_total,
                disperse_total,
                main_total,
                collect_resource)
	(SELECT
		id,
		YEAR,
		area_id,
		straw_type,
		pro_still_field AS pro_still_field,
		round( IFNULL( (
case when pro_still_field =0 then 0 ELSE
 collect_resource / pro_still_field  END), 0 ), 10 ) AS return_ratio,-- 还田比率
		main_total + disperse_total AS all_total,-- 还田汇总：这里不用加直接还田量。
		disperse_total AS disperse_total,
		main_total AS main_total,
		collect_resource AS collect_resource
	FROM
		straw_utilize_sum
	);



