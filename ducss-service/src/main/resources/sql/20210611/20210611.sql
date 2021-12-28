-- 处理18 19年年度任务的状态
update country_task set status = '6' where year in ('2018','2019') and task_level = 'ministry';

-- 处理19年定州重复数据
delete from pro_still_detail where pro_still_id = '8854';
delete from pro_still where id = '8854';

-- 18年秸秆利用量错误
DELETE FROM straw_usage_sum where YEAR IN('2018');
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
 round(COALESCE((
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
	round(COALESCE((
CASE WHEN collect_resource=0 THEN 0 ELSE
( main_total + disperse_total + pro_still_field ) / collect_resource  END),0),10) AS comprehensive_index, -- 综合利用能力指数
round( COALESCE((
CASE WHEN collect_resource=0 THEN 0 ELSE
main_total / collect_resource END) ,0),10) AS industrialization_index,-- 产业利用能力指数

	collect_resource AS collect_resource,
	main_total AS main_total,
	pro_still_field AS return_resource
FROM
	straw_utilize_sum, sys_dictionary
	WHERE straw_type=dict_key AND YEAR IN('2018'));

-- 修改河北省保定市名称
update data_analysis_city set area_name = '/河北省/保定市' where city_id = '130600';

-- 18 19年河北省定州市data表数据重复
delete from data_analysis_area where area_id = '130682';

-- 填报数据市级id错误
update disperse_utilize a set city_id = b.parent_id
from (
SELECT a.id,a.area_id,a.city_id,b.parent_id from disperse_utilize a left join sys_area b on a.area_id = cast(b.id as VARCHAR) where a.city_id != cast(b.parent_id as VARCHAR) ) b
where a.id = b.id and a.area_id = b.area_id;

update straw_utilize a set city_id = b.parent_id
from (
SELECT a.id,a.area_id,a.city_id,b.parent_id from straw_utilize a left join sys_area b on a.area_id = cast(b.id as VARCHAR) where a.city_id != cast(b.parent_id as VARCHAR)
 ) b
where a.id = b.id and a.area_id = b.area_id;

update pro_still a set city_id = b.parent_id
from (
SELECT a.id,a.area_id,a.city_id,b.parent_id from pro_still a left join sys_area b on a.area_id = cast(b.id as VARCHAR) where a.city_id != cast(b.parent_id as VARCHAR)
 ) b
where a.id = b.id and a.area_id = b.area_id;