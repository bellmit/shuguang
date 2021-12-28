-- 处理 海南省乐东市级丢失数据
-- 乐东 : 469027  ；乐东虚拟县 : 	469927

-- 1.迁移产生量与直接还田量填报
UPDATE pro_still SET area_id= '469927',city_id = '469027'  WHERE  ID IN (SELECT ID
FROM pro_still WHERE area_id = '469927' );
-- 2.农户分散利用量填报
UPDATE disperse_utilize SET area_id = '469927',city_id = '469027'  WHERE ID IN (SELECT "id" FROM disperse_utilize WHERE area_id = '469927' );
-- 3.迁移市场主体利用量填报数据
UPDATE straw_utilize SET area_id = '469927',city_id = '469027' WHERE ID IN (SELECT "id" FROM straw_utilize  WHERE area_id = '469927');

update collect_flow set city_id = '469027',level = '3' where area_id = '469927';
update collect_flow set province_id = '460000',city_id = '469027',level = '4' where area_id = '469027';

update straw_utilize_sum_total set province_id = '460000',city_id = '469027' where area_id = '469927';
update straw_utilize_sum_total set province_id = '460000',city_id = '469027' where area_id = '469027';

update straw_utilize_sum set city_id = '469027' where area_id = '469927';
update straw_utilize_sum set city_id = '469027' where area_id = '469027';

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
'469027',
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
WHERE YEAR IN('2018','2019') and area_id = '469927'
GROUP BY
	SUBSTRING ( AREA_ID, 0, 5 ) || '00',
	YEAR,
	STRAW_TYPE,
	STRAW_NAME;

UPDATE data_analysis_city
SET compr_util_index =
CASE

		WHEN COLLECT_RESOURCE = 0 THEN
	0 ELSE ( MARKET_ENT + REUSE + RETURN_RESOURCE ) / COLLECT_RESOURCE
	END WHERE YEAR IN('2018','2019') and city_id = '469027';

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
	WHERE
	    year in ('2018','2019') and city_id = '469027'
	GROUP BY
		city_ID,
	YEAR
	) B
WHERE
	A.city_ID = B.city_ID
	AND A.YEAR = B.YEAR AND A.YEAR IN('2018','2019');


update data_analysis_city a
set grain_yield = b.grain_yield, -- 粮食产量
export_yield = b.export_yield_total, -- 调出量
theory_resource = b.theory_resource, -- 产生量
collect_resource = b.collect_resource,-- 可收集量
market_ent = b.main_total, -- 市场主体利用量=市场主体5料化之和
fertilizes = b.main_fertilising,--市场主体肥料化
feeds = b.main_forage, -- 市场主体饲料化
fuelleds = b.main_fuel, -- 市场主体燃料化
base_mats = b.main_base, -- 市场主体基料化
materializations = b.main_material, --市场主体原料化
reuse = b.disperse_total, -- 农户分散利用量合计
fertilisingd = b.disperse_fertilising, -- 分散肥料化
foraged = b.disperse_forage, -- 分散饲料化
fueld = b.disperse_fuel, -- 分散燃料化
based = b.disperse_base, --分散基料化
materiald = b.disperse_material, --分散原料化
return_resource = b.pro_still_field, --直接还田量
other = b.main_total_other, --市场主体调入量
fertilize = b.main_fertilising + b.disperse_fertilising + b.pro_still_field, -- 肥料化利用量(分散+市场主体)
feed = b.main_forage + b.disperse_forage, --饲料化利用量(分散+市场主体)
fuelled = b.main_fuel + b.disperse_fuel, -- 燃料化利用量(分散+市场主体)
base_mat = b.main_base + b.disperse_base, -- 基料化利用量(分散+市场主体)
materialization = b.main_material + b.disperse_material, -- 原料化利用量(分散+市场主体)
straw_utilization = b.main_total + b.disperse_total + b.pro_still_field + b.export_yield_total - b.main_total_other, --秸秆利用量(分散合计+市场主体合计+直接还田量+区域调出量-市场主体规模化调入量)
totol_rate = round(COALESCE(( CASE WHEN b.collect_resource=0 THEN 0 ELSE (b.main_total + b.disperse_total + b.pro_still_field+ b.export_yield_total - b.main_total_other ) / b.collect_resource *100 END),0),10), --综合利用率
compr_util_index = round(COALESCE((case when b.collect_resource = 0 then 0 else ( b.main_total + b.disperse_total + b.pro_still_field ) / b.collect_resource end),0),10), -- 综合利用能力指数
indu_util_index = round( COALESCE((CASE WHEN b.collect_resource=0 THEN 0 ELSE b.main_total / b.collect_resource END) ,0),10) -- 产业化利用能力指数

--grass_valley_ratio = , -- 草谷比
--collection_ratio = , -- 可收集系数
--seed_area = , -- 播种面积
--return_area = , -- 还田面积
--return_pers = , -- 单作物的直接还田率
from (
	SELECT * from straw_utilize_sum where year in ('2018','2019') and city_id = area_id and province_id != city_id and area_id = '469027'
) b
where
	a.year = b.year and a.city_id = b.city_id and a.straw_type = b.straw_type;

update data_analysis_city set area_name = '/海南省/乐东黎族自治县' where city_id = '469027';