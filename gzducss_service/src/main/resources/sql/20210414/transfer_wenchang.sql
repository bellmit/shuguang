-- 2020-4-9
-- 469095 ： /海南省/文昌市/文昌市虚拟县
-- 469005 ： /海南省/文昌市
-- 文昌市虚拟县上报数据审核通过

-- 1.迁移产生量与直接还田量填报
UPDATE pro_still SET area_id= '469095',city_id = '469005'  WHERE  ID IN (SELECT ID
FROM pro_still WHERE "year" = '2018' AND area_id = '469005' );
-- 2.农户分散利用量填报
UPDATE disperse_utilize SET area_id = '469095',city_id = '469005'  WHERE ID IN (SELECT "id" FROM disperse_utilize WHERE area_id = '469005' );
UPDATE country_task SET area_id = '469095',city_id = '469005' WHERE id in(SELECT "id" FROM country_task WHERE area_id = '469005' );
-- 3.迁移市场主体利用量填报数据
UPDATE straw_utilize SET area_id = '469095',city_id = '469005' WHERE ID IN (SELECT "id" FROM straw_utilize  WHERE area_id = '469005');


-- 插入18 19年文昌虚拟县的数据 （之前处理数据的时候处理掉了）
INSERT INTO data_analysis_area (
	B_ID,
	AREA_ID,
	YEAR,
	STRAW_TYPE,
	STRAW_NAME,
	GRAIN_YIELD,
	GRASS_VALLEY_RATIO,
	COLLECTION_RATIO,
	SEED_AREA,
	RETURN_AREA,
	EXPORT_YIELD,
	THEORY_RESOURCE,
	COLLECT_RESOURCE,
	MARKET_ENT,
	FERTILIZES,
	FEEDS,
	FUELLEDS,
	BASE_MATS,
	MATERIALIZATIONS,
	REUSE,
	FERTILISINGD,
	FORAGED,
	FUELD,
	BASED,
	MATERIALD,
	RETURN_RESOURCE,
	OTHER,
	FERTILIZE,
	FEED,
	FUELLED,
	BASE_MAT,
	MATERIALIZATION,
	STRAW_UTILIZATION,
	TOTOL_RATE,
	COMPR_UTIL_INDEX,
	INDU_UTIL_INDEX
) SELECT DISTINCT C
.AREA_ID || C.YEAR || C.STRAW_TYPE,
C.AREA_ID,
C.YEAR,
C.STRAW_TYPE,
C.STRAW_NAME,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0,
0
FROM
	(
	SELECT A
		.AREA_ID,
		A.YEAR,
		B.STRAW_TYPE,
		B.STRAW_NAME
	FROM
		PRO_STILL A,
		PRO_STILL_DETAIL B,
		COLLECT_FLOW C
	WHERE
		A.ID = B.PRO_STILL_ID
		AND A.AREA_ID = C.AREA_ID
		AND A.YEAR = C.YEAR
	AND ( C.STATUS = 1 OR C.STATUS = 5 )
	AND A.YEAR IN('2018','2019') AND a.area_id = '469095'
	) C;

UPDATE DATA_ANALYSIS_AREA A
SET GRAIN_YIELD = D.GRAIN_YIELD,
GRASS_VALLEY_RATIO = D.GRASS_VALLEY_RATIO,
COLLECTION_RATIO = D.COLLECTION_RATIO,
SEED_AREA = D.SEED_AREA,
RETURN_AREA = D.RETURN_AREA,
EXPORT_YIELD = D.EXPORT_YIELD,
RETURN_RATIO = D.RETURN_RATIO
FROM
	(
	SELECT A
		.AREA_ID || A.YEAR || B.STRAW_TYPE AS B_ID,
		A.AREA_ID,
		A.YEAR,
		B.STRAW_TYPE,
		B.STRAW_NAME,
		B.GRAIN_YIELD,
		B.GRASS_VALLEY_RATIO,
		B.COLLECTION_RATIO,
		B.SEED_AREA,
		B.RETURN_AREA,
		B.EXPORT_YIELD,
		B.RETURN_RATIO
	FROM
		PRO_STILL A,
		PRO_STILL_DETAIL B,
		COLLECT_FLOW E
	WHERE
		A.ID = B.PRO_STILL_ID
		AND A.AREA_ID = E.AREA_ID
		AND A.YEAR = E.YEAR
		AND ( E.STATUS = 1 OR E.STATUS = 5 )
		AND A.YEAR IN('2018','2019') AND a.area_id = '469095'
	) D
WHERE
	D.B_ID = A.B_ID AND A.YEAR IN('2018','2019') AND a.area_id = '469095';

UPDATE DATA_ANALYSIS_AREA A
SET THEORY_RESOURCE = A.GRAIN_YIELD * A.GRASS_VALLEY_RATIO,
COLLECT_RESOURCE = A.GRAIN_YIELD * A.GRASS_VALLEY_RATIO * A.COLLECTION_RATIO,
RETURN_RESOURCE = (
	CASE

			WHEN A.SEED_AREA = 0 THEN
			0 ELSE ( A.RETURN_AREA / A.SEED_AREA ) * A.GRAIN_YIELD * A.GRASS_VALLEY_RATIO * A.COLLECTION_RATIO
		END
		),
		RETURN_PERS =
	CASE

		WHEN A.SEED_AREA = 0 THEN
	0 ELSE A.RETURN_AREA / A.SEED_AREA END
	WHERE A.YEAR IN('2018','2019') AND a.area_id = '469095';

update data_analysis_area a
set COLLECT_RESOURCE = ceil( ceil(GRAIN_YIELD * GRASS_VALLEY_RATIO * 100)/100 * COLLECTION_RATIO *100)/100
from (SELECT a.year,a.area_id from ( SELECT year,area_id,sum(collect_resource) as "collect_resource" FROM data_analysis_area WHERE year in('2018', '2019') AND area_id = '469095' GROUP BY year,area_id) a left join (SELECT id,year,area_id,collect_num,create_date from collect_flow WHERE year in('2018', '2019') and area_id = '469095') b on b.area_id = a.area_id
where a.collect_resource != b.collect_num and a.year = b.year) b
where a.year = b.year and a.area_id = b.area_id AND A.YEAR IN('2018','2019') and A.area_id = '469095';

update data_analysis_area a
set RETURN_RESOURCE = round((collect_resource * return_ratio)/100,10)
from (SELECT a.year,a.area_id from ( SELECT year,area_id,round(sum((collect_resource * return_ratio)/100),10) as "re_num" FROM data_analysis_area WHERE year in('2018', '2019') and area_id = '469095' GROUP BY year,area_id) a left join (SELECT id,year,area_id,direct_return_num,create_date from collect_flow WHERE year in('2018', '2019') and area_id = '469095') b on b.area_id = a.area_id
      where a.re_num != b.direct_return_num and a.year = b.year and a.area_id = b.area_id) b
where a.year = b.year and a.area_id = b.area_id AND A.YEAR IN('2018','2019') and A.area_id = '469095';

update data_analysis_area set RETURN_PERS = return_ratio / 100 where YEAR IN('2018','2019') and area_id = '469095';

UPDATE DATA_ANALYSIS_AREA A
SET FERTILIZES = D.FERTILISING,
FEEDS = D.FORAGE,
FUELLEDS = D.FUEL,
BASE_MATS = D.BASE,
MATERIALIZATIONS = D.MATERIAL,
MARKET_ENT = D.MARKET_ENT,
OTHER = D.OTHER
FROM
	(
	SELECT C
		.B_ID,
		C.FERTILISING FERTILISING,
		C.FORAGE FORAGE,
		C.FUEL FUEL,
		C.BASE BASE,
		C.MATERIAL MATERIAL,
		C.OTHER,
		C.FERTILISING + C.FORAGE + C.FUEL + C.BASE + C.MATERIAL AS MARKET_ENT
	FROM
		(
		SELECT
			B.B_ID,
			SUM ( B.FERTILISING ) FERTILISING,
			SUM ( B.FORAGE ) FORAGE,
			SUM ( B.FUEL ) FUEL,
			SUM ( B.BASE ) BASE,
			SUM ( B.MATERIAL ) MATERIAL,
			SUM ( B.OTHER ) OTHER
		FROM
			STRAW_UTILIZE_DETAIL B
		WHERE
		    B.AREA_ID = '469095'
		GROUP BY
			B.B_ID
		) C
	) D
WHERE
	A.B_ID = D.B_ID AND A.YEAR IN('2018','2019') and A.area_id = '469095';


UPDATE DISPERSE_UTILIZE_DETAIL A
SET GRASS_VALLEY_RATIO = F.GRASS_VALLEY_RATIO,
COLLECTION_RATIO = F.COLLECTION_RATIO,
PERS_TOTAL = FERTILISING + FORAGE + FUEL + BASE + MATERIAL,
DISPERSE_FERTILISING = (
	CASE
    WHEN A.APPLICATION = '0' THEN A.FERTILISING * A.SOWN_AREA * A.YIELD_PER_MU * F.GRASS_VALLEY_RATIO * F.COLLECTION_RATIO * 0.00001 + A.REUSE
    ELSE A.FERTILISING * A.SOWN_AREA * A.YIELD_PER_MU * F.GRASS_VALLEY_RATIO * F.COLLECTION_RATIO * 0.00001
	END ),
DISPERSE_FORAGE = (
    CASE
    WHEN A.APPLICATION = '1' THEN A.FORAGE * A.SOWN_AREA * A.YIELD_PER_MU * F.GRASS_VALLEY_RATIO * F.COLLECTION_RATIO * 0.00001 + A.REUSE
    ELSE A.FORAGE * A.SOWN_AREA * A.YIELD_PER_MU * F.GRASS_VALLEY_RATIO * F.COLLECTION_RATIO * 0.00001
    END ),
DISPERSE_FUEL = (
    CASE
    WHEN A.APPLICATION = '2' THEN A.FUEL * A.SOWN_AREA * A.YIELD_PER_MU * F.GRASS_VALLEY_RATIO * F.COLLECTION_RATIO * 0.00001 + A.REUSE
    ELSE A.FUEL * A.SOWN_AREA * A.YIELD_PER_MU * F.GRASS_VALLEY_RATIO * F.COLLECTION_RATIO * 0.00001
    END ),
DISPERSE_BASE = (
    CASE
    WHEN A.APPLICATION = '3' THEN A.BASE * A.SOWN_AREA * A.YIELD_PER_MU * F.GRASS_VALLEY_RATIO * F.COLLECTION_RATIO * 0.00001 + A.REUSE
    ELSE A.BASE * A.SOWN_AREA * A.YIELD_PER_MU * F.GRASS_VALLEY_RATIO * F.COLLECTION_RATIO * 0.00001
    END ),
DISPERSE_MATERIAL = (
    CASE
    WHEN A.APPLICATION = '4' THEN A.MATERIAL * A.SOWN_AREA * A.YIELD_PER_MU * F.GRASS_VALLEY_RATIO * F.COLLECTION_RATIO * 0.00001 + A.REUSE
    ELSE A.MATERIAL * A.SOWN_AREA * A.YIELD_PER_MU * F.GRASS_VALLEY_RATIO * F.COLLECTION_RATIO * 0.00001
    END ),
CREATE_NUMBER = A.SOWN_AREA * A.YIELD_PER_MU * F.GRASS_VALLEY_RATIO * F.COLLECTION_RATIO * 0.001
FROM
    DATA_ANALYSIS_AREA F
    WHERE
	A.B_ID = F.B_ID AND F.YEAR IN('2018','2019') AND F.AREA_ID = '469095';


UPDATE DATA_ANALYSIS_AREA A
SET FERTILISINGD = CASE WHEN D.CREATE_NUMBER=0 THEN 0 ELSE  D.DISPERSE_FERTILISING / D.CREATE_NUMBER * A.COLLECT_RESOURCE END,
FORAGED = CASE WHEN D.CREATE_NUMBER=0 THEN 0 ELSE D.DISPERSE_FORAGE / D.CREATE_NUMBER * A.COLLECT_RESOURCE END,
FUELD = CASE WHEN D.CREATE_NUMBER=0 THEN 0 ELSE D.DISPERSE_FUEL / D.CREATE_NUMBER * A.COLLECT_RESOURCE END,
BASED = CASE WHEN D.CREATE_NUMBER=0 THEN 0 ELSE D.DISPERSE_BASE / D.CREATE_NUMBER * A.COLLECT_RESOURCE END,
MATERIALD = CASE WHEN D.CREATE_NUMBER=0 THEN 0 ELSE  D.DISPERSE_MATERIAL / D.CREATE_NUMBER * A.COLLECT_RESOURCE END
FROM
	(
	SELECT
		B_ID,
		SUM ( DISPERSE_FERTILISING ) DISPERSE_FERTILISING,
		SUM ( DISPERSE_FORAGE ) DISPERSE_FORAGE,
		SUM ( DISPERSE_FUEL ) DISPERSE_FUEL,
		SUM ( DISPERSE_BASE ) DISPERSE_BASE,
		SUM ( DISPERSE_MATERIAL ) DISPERSE_MATERIAL,
		SUM ( CREATE_NUMBER ) CREATE_NUMBER
	FROM
		DISPERSE_UTILIZE_DETAIL t1
    JOIN DISPERSE_UTILIZE t2 ON t1.utilize_id = t2.id
    where t2.year IN('2018','2019') and t2.area_id = '469095'
	GROUP BY
		B_ID
	) D
WHERE
	A.B_ID = D.B_ID AND A.YEAR IN('2018','2019') AND A.AREA_ID = '469095';

UPDATE DATA_ANALYSIS_AREA
SET REUSE = FERTILISINGD + FORAGED + FUELD + BASED + MATERIALD WHERE YEAR IN('2018','2019') AND AREA_ID = '469095';

UPDATE DATA_ANALYSIS_AREA A
SET LEAVE_NUMBER = REUSE + MARKET_ENT,
STRAW_UTILIZATION = REUSE + MARKET_ENT + RETURN_RESOURCE + EXPORT_YIELD - OTHER,
FERTILIZE = FERTILIZES + FERTILISINGD + RETURN_RESOURCE,
FEED = FEEDS + FORAGED,
FUELLED = FUELLEDS + FUELD,
BASE_MAT = BASE_MATS + BASED,
MATERIALIZATION = MATERIALIZATIONS + MATERIALD
WHERE A.YEAR IN('2018','2019') AND A.AREA_ID = '469095';

UPDATE DATA_ANALYSIS_AREA A
SET TOTOL_RATE =
CASE
		WHEN COLLECT_RESOURCE = 0 THEN
		0 ELSE STRAW_UTILIZATION / COLLECT_RESOURCE
	END,
	USETOTAL = FERTILIZE + FEED + FUELLED + BASE_MAT + MATERIALIZATION,
	INDU_UTIL_INDEX =
CASE

	WHEN COLLECT_RESOURCE = 0 THEN
	0 ELSE MARKET_ENT / COLLECT_RESOURCE END
WHERE A.YEAR IN('2018','2019') AND A.AREA_ID = '469095';

UPDATE DATA_ANALYSIS_AREA A
SET AREA_RETURN_NUMBER = B.AREA_RETURN_NUMBER,
AREA_RETURN_PERSENT = B.AREA_RETURN_PERSENT
FROM
	(
	SELECT
		AREA_ID,
		YEAR,
	CASE

			WHEN SUM ( COLLECT_RESOURCE ) = 0 THEN
			0 ELSE SUM ( RETURN_RESOURCE ) / SUM ( COLLECT_RESOURCE )
		END AS AREA_RETURN_PERSENT,
		SUM ( RETURN_RESOURCE ) AREA_RETURN_NUMBER
	FROM
		DATA_ANALYSIS_AREA
	where
	    year in ('2018','2019') and AREA_ID = '469095'
	GROUP BY
		AREA_ID,
	YEAR
	) B
WHERE
	A.AREA_ID = B.AREA_ID
	AND A.YEAR = B.YEAR AND A.YEAR IN('2018','2019');

-- 插入18 19年 文昌市的汇总数据
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
'469005',
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
WHERE YEAR IN('2018','2019') and area_id = '469095'
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
	END WHERE YEAR IN('2018','2019') and city_id = '469005';

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
	    year in ('2018','2019') and city_id = '469005'
	GROUP BY
		city_ID,
	YEAR
	) B
WHERE
	A.city_ID = B.city_ID
	AND A.YEAR = B.YEAR AND A.YEAR IN('2018','2019');



-- 20年 文昌市的数据迁移到 city汇总表中
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
	area_id = '469005' and year = '2020';

-- 更新area表中的 area_id为文昌县的id
update data_analysis_area set area_id = '469095' where area_id = '469005';

-- 更新area汇总表中 区域名称
update data_analysis_area set area_name = '/海南省/文昌市/文昌市虚拟县' where area_id = '469095';

-- 更新city汇总表中 区域名称
update data_analysis_city set area_name = '/海南省/文昌市' where city_id = '469005';

-- 更新city_id
UPDATE straw_utilize_sum set province_id = '460000',city_id = '469005' where area_id = '469005';

UPDATE straw_utilize_sum set city_id = '469005' where area_id = '469095';


-- 更新straw_utilize_sum表 将文昌市20年的数据复制一份到文昌市虚拟县中
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
) SELECT year||'469095'||straw_type as "id",YEAR
,
province_id,
city_id,
'469095',
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
	area_id = '469005'
	AND YEAR = '2020';

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
	WHERE straw_type=dict_key and year = '2020' and area_id = '469095');

-- 校对数据
update data_analysis_area a
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
	SELECT * from straw_utilize_sum where year in ('2018','2019') and area_id = '469095'
) b
where
	a.area_id = b.area_id and a.year = b.year and a.straw_type = b.straw_type and a.area_id = '469095';

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
	SELECT * from straw_utilize_sum where year in ('2018','2019') and city_id = area_id and province_id != city_id and area_id = '469005'
) b
where
	a.year = b.year and a.city_id = b.city_id and a.straw_type = b.straw_type;


-- 更新 collect_flow 表的city_id
update collect_flow set city_id = '469005' where area_id = '469095';
update collect_flow set city_id = '469005', level = '4' where area_id = '469005';
update collect_flow set status = '5' where year = '2020' and area_id = '469005';

-- 插入2020年 文昌县的数据
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
) SELECT year||province_id||city_id||'469095' as "id"
,
YEAR,
province_id,
city_id,
'469095',
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
	YEAR = '2020'
	AND area_id = '469005';

-- 产生量利用量插入20年文昌市虚拟县数据
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
) SELECT year||'469095' as "id"
,
YEAR,
'469095',
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
	YEAR = '2020'
	AND area_id = '469005';

-- 产生情况汇总 插入20年文昌市虚拟县数据
INSERT INTO straw_produce ( ID, YEAR, area_id, straw_type, theory_resource, collect_resource, grain_yield, seed_area )
SELECT year||'469095'||straw_type as "id"
,
YEAR,
'469095',
straw_type,
theory_resource,
collect_resource,
grain_yield,
seed_area
FROM
	straw_produce
WHERE
	YEAR = '2020'
	AND area_id = '469005';

-- 还田离田情况汇总 插入20年文昌市虚拟县数据
INSERT INTO return_leave_sum ( ID, YEAR, area_id, straw_type, pro_still_field, return_ratio, all_total, disperse_total, main_total, collect_resource )
SELECT year||'469095'||straw_type as "id"
,
YEAR,
'469095',
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
	YEAR = '2020'
	AND area_id = '469005';


-- 市场主体更改文昌市数据
update straw_utilize_sum_total set province_id = '460000',city_id = '469005' where area_id = '469005';
update straw_utilize_sum_total set city_id = '469005' where area_id = '469095';
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
	'469095',
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
	YEAR = '2020'
	AND area_id = '469005';


-- 上报管理
update collect_flow_log set area_id = '469095' where year = '2020' and area_id = '469005';
update collect_flow_log set operation = '5' where id = 'd9cd4cea5e3e4a24ac1c5f40f9aa8b1d';


delete from country_task where id in('3048264a36dc4e699ac67d5f4817babe','2ad5e3833cda4ad3a50a7d7201da846b','91921e59cfc94b038a170ced95501ff8');
update country_task set status = '5' where year in ('2018','2019') and area_id = '469095';
update country_task set status = '5' where id = '2be6e24f19c74359bbc30233c05143f6';





