-- 2020-4-9
-- 定州 ： 130682
-- 定州县 ：139682
-- 定州虚拟县上报数据审核通过

-- 1.迁移产生量与直接还田量填报
UPDATE pro_still SET area_id= '139682'  WHERE  ID IN (SELECT ID
FROM pro_still WHERE area_id = '130682' );
-- 2.农户分散利用量填报
UPDATE disperse_utilize SET area_id = '139682' WHERE ID IN (SELECT "id" FROM disperse_utilize WHERE area_id = '130682' );
UPDATE country_task SET area_id = '139682' WHERE id in (SELECT "id" FROM country_task WHERE area_id = '130682' );
-- 3.迁移市场主体利用量填报数据
UPDATE straw_utilize SET area_id = '139682' WHERE ID IN (SELECT "id" FROM straw_utilize  WHERE area_id = '130682');


-- 把之前在保定市下的定州市数据转换到 city汇总表中
INSERT INTO data_analysis_city (
	ID,
	YEAR,
	city_id,
	straw_type,
	grain_yield,
	grass_valley_ratio,
	collection_ratio,
	seed_area,
	return_area,
	export_yield,
	theory_resource,
	collect_resource,
	market_ent,
	fertilizes,
	feeds,
	fuelleds,
	base_mats,
	materializations,
	reuse,
	fertilisingd,
	foraged,
	fueld,
	based,
	materiald,
	return_resource,
	other,
	fertilize,
	feed,
	fuelled,
	base_mat,
	materialization,
	straw_utilization,
	totol_rate,
	compr_util_index,
	indu_util_index,
	area_name,
	straw_name,
	yield_all_num,
	leave_number,
	usetotal,
	area_return_number,
	area_return_persent,
	return_pers
) SELECT ID
,
YEAR,
area_id,
straw_type,
grain_yield,
grass_valley_ratio,
collection_ratio,
seed_area,
return_area,
export_yield,
theory_resource,
collect_resource,
market_ent,
fertilizes,
feeds,
fuelleds,
base_mats,
materializations,
reuse,
fertilisingd,
foraged,
fueld,
based,
materiald,
return_resource,
other,
fertilize,
feed,
fuelled,
base_mat,
materialization,
straw_utilization,
totol_rate,
compr_util_index,
indu_util_index,
area_name,
straw_name,
yield_all_num,
leave_number,
usetotal,
area_return_number,
area_return_persent,
return_pers
FROM
	"data_analysis_area"
WHERE
	area_id = '130682';

-- city汇总表中没有定州市2020年汇总数据
INSERT INTO DATA_ANALYSIS_CITY (
	CITY_ID,
	YEAR,
	STRAW_TYPE,
	STRAW_NAME,
	GRAIN_YIELD,---粮食产量（吨）
	SEED_AREA,---播种面积（亩）
	RETURN_AREA,---还田面积（亩）
	THEORY_RESOURCE,----产生量（吨）
	COLLECT_RESOURCE,----可收集量（吨）
	FERTILIZE,----分散+主体肥料化利用量（吨）
	FEED,----分散+主体饲料化利用量（吨）
	FUELLED,----分散+主体燃料化利用量（吨）
	BASE_MAT,----分散+主体基料化利用量（吨）
	MATERIALIZATION,----分散+主体原料化利用量（吨）
	OTHER,----市场主体规模化调入量（吨）
	EXPORT_YIELD,----区域调出量（吨）
	FERTILIZES,----市场主体肥料化利用（吨）
	FEEDS,----市场主体饲料化利用（吨）
	FUELLEDS,----市场主体燃料化利用（吨）
	BASE_MATS,----市场主体基料化利用（吨）
	MATERIALIZATIONS,----市场主体原料化利用（吨）
	MARKET_ENT,----市场主体利用量合计（吨）
	FERTILISINGD,--分散肥料化
	FORAGED,--分散饲料化
	FUELD,--分散燃料化
	BASED,--分散基料化
	MATERIALD,--分散原料化
	REUSE,--分散利用量
	RETURN_RESOURCE,----直接还田量（吨）
	RETURN_PERS,--单物种的直接还田率
	usetotal,--分散合计+主体合计
	straw_utilization,--秸秆利用量
	leave_number,--离田利用量
	totol_rate,--综合利用率
	indu_util_index--产业化利用能力指数
) SELECT
'130682',
YEAR,
STRAW_TYPE,
STRAW_NAME,
SUM ( GRAIN_YIELD ) AS GRAIN_YIELD,---粮食产量（吨）
SUM ( SEED_AREA ) AS SEED_AREA,---播种面积（亩）
	SUM ( RETURN_AREA ) AS RETURN_AREA,---还田面积（亩）
	SUM ( THEORY_RESOURCE ) AS THEORY_RESOURCE,----产生量（吨）
	SUM ( COLLECT_RESOURCE ) AS COLLECT_RESOURCE,----可收集量（吨）
	SUM ( FERTILIZE ) AS FERTILIZE,----分散+主体肥料化利用量（吨）
	SUM ( FEED ) AS FEED,----分散+主体饲料化利用量（吨）
	SUM ( FUELLED ) AS FUELLED,----分散+主体燃料化利用量（吨）
	SUM ( BASE_MAT ) AS BASE_MAT,----分散+主体基料化利用量（吨）
	SUM ( MATERIALIZATION ) AS MATERIALIZATION,----分散+主体原料化利用量（吨）
	SUM ( OTHER ) AS OTHER,----市场主体规模化调入量（吨）
	SUM ( EXPORT_YIELD ) AS EXPORT_YIELD,----区域调出量（吨）
	SUM ( FERTILIZES ) AS FERTILIZES,----市场主体肥料化利用（吨）
	SUM ( FEEDS ) AS FEEDS,----市场主体饲料化利用（吨）
	SUM ( FUELLEDS ) AS FUELLEDS,----市场主体燃料化利用（吨）
	SUM ( BASE_MATS ) AS BASE_MATS,----市场主体基料化利用（吨）
	SUM ( MATERIALIZATIONS ) AS MATERIALIZATIONS,----市场主体原料化利用（吨）
	SUM ( MARKET_ENT ) AS MARKET_ENT,----市场主体利用量合计（吨）
	SUM ( FERTILISINGD ) AS FERTILISINGD,--分散肥料化
	SUM ( FORAGED ) AS FORAGED,--分散饲料化
	SUM ( FUELD ) AS FUELD,--分散燃料化
	SUM ( BASED ) AS BASED,--分散基料化
	SUM ( MATERIALD ) AS MATERIALD,--分散原料化
	SUM ( REUSE ) AS REUSE,--分散利用量
	SUM ( RETURN_RESOURCE ) AS RETURN_RESOURCE,----直接还田量（吨）
CASE

		WHEN SUM ( SEED_AREA ) = 0 THEN
		0 ELSE SUM ( RETURN_AREA ) / SUM ( SEED_AREA )
	END AS RETURN_PERS, ----单作物直接还田率（%）
	SUM ( usetotal ) AS usetotal,----分散合计+主体合计（吨）
	sum(straw_utilization) as straw_utilization,--秸秆利用量
	SUM(leave_number) AS leave_number,--离田利用量
	case when sum(COLLECT_RESOURCE) =0 then 0 else sum(straw_utilization)/sum(COLLECT_RESOURCE) end as totol_rate,--综合利用率
	case when sum(COLLECT_RESOURCE) =0 then 0 else SUM ( MARKET_ENT )/sum(COLLECT_RESOURCE) end as indu_util_index--产业化利用能力指数
FROM
	DATA_ANALYSIS_AREA
WHERE YEAR IN('2020') and area_id = '139682'
GROUP BY
	SUBSTRING ( AREA_ID, 0, 5 ) || '00',
	YEAR,
	STRAW_TYPE,
	STRAW_NAME;

-- 处理2020年定州市汇总数据
UPDATE data_analysis_city
SET compr_util_index =
CASE

		WHEN COLLECT_RESOURCE = 0 THEN
	0 ELSE ( MARKET_ENT + REUSE + RETURN_RESOURCE ) / COLLECT_RESOURCE
	END WHERE YEAR = '2020' and city_id = '130682';


UPDATE data_analysis_city A
SET AREA_RETURN_NUMBER = B.AREA_RETURN_NUMBER,
AREA_RETURN_PERSENT = B.AREA_RETURN_PERSENT
FROM
	(
	SELECT
		city_ID,
		YEAR,
	CASE
			WHEN SUM ( COLLECT_RESOURCE ) = 0 THEN
			0 ELSE SUM ( RETURN_RESOURCE ) / SUM ( COLLECT_RESOURCE )
		END AS AREA_RETURN_PERSENT,
		SUM ( RETURN_RESOURCE ) AREA_RETURN_NUMBER
	FROM
		data_analysis_city
	GROUP BY
		city_ID,
	YEAR
	) B
WHERE
	A.city_ID = B.city_ID
	AND A.YEAR = B.YEAR AND A.YEAR = '2020' and a.city_id = '130682';

-- 更新city汇总表中 区域名称
update data_analysis_city set area_name = '/河北省/定州市' where city_id = '130682';

-- 更新 data_analysis_area 表中定州市数据
update data_analysis_area set area_id = '139682' where area_id = '130682';

-- 更新area汇总表中 区域名称
update data_analysis_area set area_name = '/河北省/定州市/定州虚拟县' where area_id = '139682';


-- 更新city_id
UPDATE straw_utilize_sum
set city_id = '130682' where area_id = '130682';

UPDATE straw_utilize_sum
set city_id = '130682' where area_id = '139682';


-- 更新straw_utilize_sum表 将定州市18年的数据复制一份到定州市虚拟县中
INSERT INTO straw_utilize_sum (
id,
	YEAR,
	province_id,
	city_id,
	area_id,
	straw_type,
	main_fertilising,
	main_forage,
	main_fuel,
	main_base,
	main_material,
	main_total,
	main_total_other,
	disperse_fertilising,
	disperse_forage,
	disperse_fuel,
	disperse_base,
	disperse_material,
	disperse_total,
	pro_still_field,
	pro_straw_utilize,
	collect_resource,
	theory_resource,
	export_yield_total,
	grain_yield
) SELECT year||'139682'||straw_type as "id",YEAR
,
province_id,
city_id,
'139682',
straw_type,
main_fertilising,
main_forage,
main_fuel,
main_base,
main_material,
main_total,
main_total_other,
disperse_fertilising,
disperse_forage,
disperse_fuel,
disperse_base,
disperse_material,
disperse_total,
pro_still_field,
pro_straw_utilize,
collect_resource,
theory_resource,
export_yield_total,
grain_yield
FROM
	straw_utilize_sum
WHERE
	area_id = '130682'
	AND YEAR = '2018';

-- 更新straw_usage_sum表 将定州市18年的数据复制一份到定州市虚拟县中
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
	WHERE straw_type=dict_key and year = '2018' and area_id = '139682');


-- 更新 collect_flow 表的city_id
update collect_flow set city_id = '130682' where area_id = '139682';
update collect_flow set city_id = '130682', level = '4' where area_id = '130682';

-- 插入18年定州数据
INSERT INTO collect_flow (
	ID,
	YEAR,
	province_id,
	city_id,
	area_id,
	LEVEL,
	theory_num,
	collect_num,
	main_num,
	farmer_split_num,
	direct_return_num,
	straw_utilize_num,
	syn_utilize_num,
	status,
	isreport,
	create_user_id,
	create_user,
	create_date,
	buy_other_num,
	export_num
) SELECT year||province_id||city_id||'139682' as "id"
,
YEAR,
province_id,
city_id,
'139682',
'3',
theory_num,
collect_num,
main_num,
farmer_split_num,
direct_return_num,
straw_utilize_num,
syn_utilize_num,
status,
isreport,
create_user_id,
create_user,
create_date,
buy_other_num,
export_num
FROM
	collect_flow
WHERE
	YEAR = '2018'
	AND area_id = '130682';

-- 产生量利用量插入18年定州市虚拟县数据
INSERT INTO production_usage_sum (
	ID,
	YEAR,
	area_id,
	produce,
	COLLECT,
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
	main_total
) SELECT (id::int4 + 111) as "id"
,
YEAR,
'139682',
produce,
COLLECT,
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
main_total
FROM
	production_usage_sum
WHERE
	YEAR = '2018'
	AND area_id = '130682';

-- 产生情况汇总 插入18年定州市虚拟县数据
INSERT INTO straw_produce ( ID, YEAR, area_id, straw_type, theory_resource, collect_resource, grain_yield, seed_area ) SELECT (id::int4 + 1111) as "id"
,
YEAR,
'139682',
straw_type,
theory_resource,
collect_resource,
grain_yield,
seed_area
FROM
	straw_produce
WHERE
	YEAR = '2018'
	AND area_id = '130682';

-- 还田离田情况汇总 插入18年定州市虚拟县数据
INSERT INTO return_leave_sum ( ID, YEAR, area_id, straw_type, pro_still_field, return_ratio, all_total, disperse_total, main_total, collect_resource ) SELECT (id::int4 + 1111) as "id"
,
YEAR,
'139682',
straw_type,
pro_still_field,
return_ratio,
all_total,
disperse_total,
main_total,
collect_resource
FROM
	return_leave_sum
WHERE
	YEAR = '2018'
	AND area_id = '130682';


-- 市场主体更改定州市数据
update straw_utilize_sum_total set city_id = '130682' where area_id = '139682';
update straw_utilize_sum_total set city_id = '130682' where area_id = '130682';
INSERT INTO straw_utilize_sum_total (
	ID,
	YEAR,
	province_id,
	city_id,
	area_id,
	main_fertilising,
	main_forage,
	main_fuel,
	main_base,
	main_material,
	main_total,
	main_total_other,
	disperse_fertilising,
	disperse_forage,
	disperse_fuel,
	disperse_base,
	disperse_material,
	disperse_total,
	pro_straw_utilize,
	return_ratio,
	comprehensive_index,
	industrialization_index,
	collect_resource,
	yield_all_num,
	theory_resource,
	export_yield_total,
	fertilising,
	forage,
	fuel,
	base,
	material,
	grass_valley_ratio,
	return_resource,
	collection_ratio,
	LEVEL
	) SELECT
	year||province_id||city_id||area_id as "id",
	YEAR,
	province_id,
	city_id,
	'139682',
	main_fertilising,
	main_forage,
	main_fuel,
	main_base,
	main_material,
	main_total,
	main_total_other,
	disperse_fertilising,
	disperse_forage,
	disperse_fuel,
	disperse_base,
	disperse_material,
	disperse_total,
	pro_straw_utilize,
	return_ratio,
	comprehensive_index,
	industrialization_index,
	collect_resource,
	yield_all_num,
	theory_resource,
	export_yield_total,
	fertilising,
	forage,
	fuel,
	base,
	material,
	grass_valley_ratio,
	return_resource,
	collection_ratio,
LEVEL
FROM
	straw_utilize_sum_total
WHERE
	YEAR = '2018'
	AND area_id = '130682';


-- 上报管理
delete from country_task where id in('db9eef8e99f3429abc6f8b51839aef0a','6bfae7983de14e5ab1dcd7b710bb68ae','53c5854d1ae8489a8bbb5ec1bc42593d');
update country_task set status = '5' where year in ('2018','2019') and area_id = '139682';
update country_task set status = '5' where year = '2020' and area_id = '139682';








