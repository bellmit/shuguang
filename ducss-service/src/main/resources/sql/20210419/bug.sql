-- 市场主体填报法人电话未展示
update straw_utilize
set mobile_phone = company_phone
where mobile_phone is null;

-- 20年贵州省利用情况汇总秸秆数据错误
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
	straw_utilize_sum , sys_dictionary
	WHERE straw_type=dict_key and year = '2020' and area_id = '520500');

-- 处理18 19年市级上报，但县级上报待审核的数据
--SELECT
--	b."manger_name",
--	a.*
--FROM
--	"collect_flow" A
--	left join sys_area b on b.id = a.area_id
--WHERE
--	A.YEAR IN ( '2018', '2019' )
--	AND A.status = '1'
--	AND city_id != area_id

update collect_flow a
set status = '5'
where id in
(SELECT id FROM "collect_flow" where year in ('2018','2019') and status = 1
and city_id in
    (SELECT area_id from collect_flow
    where city_id = area_id and city_id != province_id and year in ('2018','2019') and status = '5'));

update country_task a set
status = '5'
from(SELECT * from collect_flow where year in ('2018','2019') and status = '5') b
where a.year = b.year and a.area_id = b.area_id and a.status != b.status;

-- straw_produce 处理重复数据
DELETE from straw_produce
where id in (
SELECT a.max_id from
( SELECT year,area_id,straw_type,count(1) as "count",max(id) as "max_id"
FROM "straw_produce" where year in ('2018','2019') group by area_id,year,straw_type ) a
where a.count > 1 );


-- 丢失数据
INSERT into data_analysis_area SELECT
	a.*
FROM
	"data_analysis_area_bak1"
	A left JOIN data_analysis_area b ON A.YEAR = b.YEAR
	AND A.area_id = b.area_id
	AND A.straw_type = b.straw_type
	where b.year is null and a.year in ('2018','2019');

INSERT into data_analysis_city SELECT
	a.*
FROM
	"data_analysis_city_bak1"
	A left JOIN data_analysis_city b ON A.YEAR = b.YEAR
	AND A.city_id = b.city_id
	AND A.straw_type = b.straw_type
	where b.year is null and a.year in ('2018','2019');

-- 20年数据
INSERT into data_analysis_area SELECT b.* from collect_flow a join (SELECT
	a.*
FROM
	"data_analysis_area_bak1"
	A left JOIN data_analysis_area b ON A.YEAR = b.YEAR
	AND A.area_id = b.area_id
	AND A.straw_type = b.straw_type
	where b.year is null ) b on b.area_id = a.area_id and b.year = a.year
	where a.year = '2020' and a.status in ('1','5');

INSERT into data_analysis_city	SELECT b.* from collect_flow a join (SELECT
	a.*
FROM
	"data_analysis_city_bak1"
	A left JOIN data_analysis_city b ON A.YEAR = b.YEAR
	AND A.city_id = b.city_id
	AND A.straw_type = b.straw_type
	where b.year is null ) b on b.city_id = a.area_id and b.year = a.year
	where a.year = '2020' and a.status in ('1','5');

INSERT into straw_utilize_sum  SELECT b.* from collect_flow a join (SELECT
	a.*
FROM
	"straw_utilize_sum_bak1"
	A LEFT JOIN straw_utilize_sum b ON A.YEAR = b.YEAR
	AND A.area_id = b.area_id
	AND A.straw_type = b.straw_type
	where b.area_id is null) b on b.year = a.year and a.area_id = b.area_id
	where a.status in ('1','5');

INSERT into straw_usage_sum  SELECT b.* from collect_flow a join (SELECT
	a.*
FROM
	"straw_usage_sum_bak1"
	A LEFT JOIN straw_usage_sum b ON A.YEAR = b.YEAR
	AND A.area_id = b.area_id
	AND A.straw_type = b.straw_type
	where b.area_id is null) b on b.year = a.year and a.area_id = b.area_id
	where a.status in ('1','5');


