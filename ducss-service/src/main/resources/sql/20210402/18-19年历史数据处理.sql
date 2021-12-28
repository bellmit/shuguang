-- 先复制几个表结构： data_analysis_area --> data_analysis_area_bak
--                 data_analysis_city --> data_analysis_city_bak
--                 data_analysis_provice --> data_analysis_provice_bak
--                 data_analysis_six_area --> data_analysis_six_area_bak
--                 straw_usage_sum --> straw_usage_sum_bak
--                 straw_utilize_sum --> straw_utilize_sum_bak


-- 处理2020年的BUG数据
update collect_flow t1 set
status = 3
from (
SELECT a.id from collect_flow a join ( SELECT * from collect_flow where "level" = '4' and status = 3) b on b.year = a.year and b.city_id = a.city_id
where a."level" = '3' and a.status != 3) t2
where t1.id = t2.id;

DELETE FROM data_analysis_area_bak where year in ('2018','2019');
INSERT INTO data_analysis_area_bak (
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
	AND A.YEAR IN('2018','2019')
	) C;

ALTER TABLE DATA_ANALYSIS_AREA_BAK ADD COLUMN "return_ratio" numeric(38,10);

UPDATE DATA_ANALYSIS_AREA_BAK A
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
--		SUM ( B.EXPORT_YIELD ) EXPORT_YIELD
	FROM
		PRO_STILL A,
		PRO_STILL_DETAIL B,
		COLLECT_FLOW E
	WHERE
		A.ID = B.PRO_STILL_ID
		AND A.AREA_ID = E.AREA_ID
		AND A.YEAR = E.YEAR
		AND ( E.STATUS = 1 OR E.STATUS = 5 )
		AND A.YEAR IN('2018','2019')
--	GROUP BY
--		A.AREA_ID,
--		A.YEAR,
--		B.STRAW_TYPE,
--		B.STRAW_NAME,
--		B.GRAIN_YIELD,
--		B.GRASS_VALLEY_RATIO,
--		B.COLLECTION_RATIO,
--		B.SEED_AREA,
--		B.RETURN_AREA
	) D
WHERE
	D.B_ID = A.B_ID AND A.YEAR IN('2018','2019');


UPDATE DATA_ANALYSIS_AREA_BAK A
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
	WHERE A.YEAR IN('2018','2019');


-- 涉及到计算 之前计算保留了两位小数 但是 现在没有保留，要重新校对这一部分的值
-- 查询 collect_flow 和 data_analysis_area 收集量的区别
--SELECT * from ( SELECT year,area_id,sum(collect_resource) as "collect_resource" FROM data_analysis_area_bak WHERE year in('2018', '2019') GROUP BY year,area_id) a left join (SELECT id,year,area_id,collect_num,create_date from collect_flow WHERE year in('2018', '2019')) b on b.area_id = a.area_id
--where a.collect_resource != b.collect_num and a.year = b.year;

-- 以 collect_flow 表为准，更新 data_analysis_area 表的数据
update data_analysis_area_bak a
set COLLECT_RESOURCE = ceil( ceil(GRAIN_YIELD * GRASS_VALLEY_RATIO * 100)/100 * COLLECTION_RATIO *100)/100
from (SELECT a.year,a.area_id from ( SELECT year,area_id,sum(collect_resource) as "collect_resource" FROM data_analysis_area_bak WHERE year in('2018', '2019') GROUP BY year,area_id) a left join (SELECT id,year,area_id,collect_num,create_date from collect_flow WHERE year in('2018', '2019')) b on b.area_id = a.area_id
where a.collect_resource != b.collect_num and a.year = b.year) b
where a.year = b.year and a.area_id = b.area_id AND A.YEAR IN('2018','2019');

-- 查询 collect_flow 和 data_analysis_area 还田量的区别
--SELECT * from ( SELECT year,area_id,sum(return_resource) as "return_resource" FROM data_analysis_area_bak WHERE year in('2018', '2019') GROUP BY year,area_id) a left join (SELECT id,year,area_id,direct_return_num,create_date from collect_flow WHERE year in('2018', '2019')) b on b.area_id = a.area_id
--where a.return_resource != b.direct_return_num and a.year = b.year;

-- 以 collect_flow 表为准，更新 data_analysis_area 表的数据
update data_analysis_area_bak a
set RETURN_RESOURCE = round((collect_resource * return_ratio)/100,10)
from (SELECT a.year,a.area_id from ( SELECT year,area_id,round(sum((collect_resource * return_ratio)/100),10) as "re_num" FROM data_analysis_area_bak WHERE year in('2018', '2019') GROUP BY year,area_id) a left join (SELECT id,year,area_id,direct_return_num,create_date from collect_flow WHERE year in('2018', '2019')) b on b.area_id = a.area_id
      where a.re_num != b.direct_return_num and a.year = b.year and a.area_id = b.area_id) b
where a.year = b.year and a.area_id = b.area_id AND A.YEAR IN('2018','2019');

update data_analysis_area_bak set RETURN_PERS = return_ratio / 100;


UPDATE DISPERSE_UTILIZE_DETAIL A
SET B_ID = B.AREA_ID || B.YEAR || A.STRAW_TYPE
FROM DISPERSE_UTILIZE B
WHERE A.UTILIZE_ID = B.ID;

UPDATE STRAW_UTILIZE_DETAIL A
SET B_ID = B.AREA_ID || B.YEAR || A.STRAW_TYPE
FROM STRAW_UTILIZE B
WHERE A.UTILIZE_ID = B.ID;

UPDATE DATA_ANALYSIS_AREA_BAK A
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
		GROUP BY
			B.B_ID
		) C
	) D
WHERE
	A.B_ID = D.B_ID AND A.YEAR IN('2018','2019');

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
    DATA_ANALYSIS_AREA_BAK F
    WHERE
	A.B_ID = F.B_ID AND F.YEAR IN('2018','2019');


UPDATE DATA_ANALYSIS_AREA_BAK A
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
		DISPERSE_UTILIZE_DETAIL
	GROUP BY
		B_ID
	) D
WHERE
	A.B_ID = D.B_ID AND A.YEAR IN('2018','2019');

UPDATE DATA_ANALYSIS_AREA_BAK
SET REUSE = FERTILISINGD + FORAGED + FUELD + BASED + MATERIALD WHERE YEAR IN('2018','2019');

UPDATE DATA_ANALYSIS_AREA_BAK A
SET LEAVE_NUMBER = REUSE + MARKET_ENT,
STRAW_UTILIZATION = REUSE + MARKET_ENT + RETURN_RESOURCE + EXPORT_YIELD - OTHER,
FERTILIZE = FERTILIZES + FERTILISINGD + RETURN_RESOURCE,
FEED = FEEDS + FORAGED,
FUELLED = FUELLEDS + FUELD,
BASE_MAT = BASE_MATS + BASED,
MATERIALIZATION = MATERIALIZATIONS + MATERIALD
WHERE A.YEAR IN('2018','2019');

UPDATE DATA_ANALYSIS_AREA_BAK A
SET TOTOL_RATE =
CASE
		WHEN COLLECT_RESOURCE = 0 THEN
		0 ELSE STRAW_UTILIZATION / COLLECT_RESOURCE
	END,
	USETOTAL = FERTILIZE + FEED + FUELLED + BASE_MAT + MATERIALIZATION,
	INDU_UTIL_INDEX =
CASE

	WHEN COLLECT_RESOURCE = 0 THEN
	0 ELSE MARKET_ENT / COLLECT_RESOURCE END WHERE A.YEAR IN('2018','2019');

UPDATE DATA_ANALYSIS_AREA_BAK A
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
		DATA_ANALYSIS_AREA_BAK
	GROUP BY
		AREA_ID,
	YEAR
	) B
WHERE
	A.AREA_ID = B.AREA_ID
	AND A.YEAR = B.YEAR AND A.YEAR IN('2018','2019');

DELETE FROM DATA_ANALYSIS_CITY_BAK WHERE YEAR IN('2018','2019');
INSERT INTO DATA_ANALYSIS_CITY_BAK (
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
) SELECT SUBSTRING
( AREA_ID, 0, 5 ) || '00' AS CITY_ID,
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
	DATA_ANALYSIS_AREA_BAK
WHERE YEAR IN('2018','2019')
GROUP BY
	SUBSTRING ( AREA_ID, 0, 5 ) || '00',
	YEAR,
	STRAW_TYPE,
	STRAW_NAME;

UPDATE data_analysis_city_bak
SET compr_util_index =
CASE

		WHEN COLLECT_RESOURCE = 0 THEN
	0 ELSE ( MARKET_ENT + REUSE + RETURN_RESOURCE ) / COLLECT_RESOURCE
	END WHERE YEAR IN('2018','2019');


UPDATE data_analysis_city_bak A
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
		data_analysis_city_bak
	GROUP BY
		city_ID,
	YEAR
	) B
WHERE
	A.city_ID = B.city_ID
	AND A.YEAR = B.YEAR AND A.YEAR IN('2018','2019');


DELETE FROM DATA_ANALYSIS_PROVICE_BAK WHERE YEAR IN('2018','2019');
INSERT INTO DATA_ANALYSIS_PROVICE_BAK (
	provice_id,
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
) SELECT SUBSTRING
( city_id, 0, 3 ) || '0000' AS provice_id,
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
	data_analysis_city_bak
WHERE YEAR IN('2018','2019')
GROUP BY
	SUBSTRING ( city_id, 0, 3 ) || '0000',
	YEAR,
	STRAW_TYPE,
	STRAW_NAME;

UPDATE data_analysis_provice_bak
SET compr_util_index =
CASE

		WHEN COLLECT_RESOURCE = 0 THEN
	0 ELSE ( MARKET_ENT + REUSE + RETURN_RESOURCE ) / COLLECT_RESOURCE
	END WHERE YEAR IN('2018','2019');

UPDATE data_analysis_provice_bak A
SET AREA_RETURN_NUMBER = B.AREA_RETURN_NUMBER,
AREA_RETURN_PERSENT = B.AREA_RETURN_PERSENT
FROM
	(
	SELECT
		provice_id,
		YEAR,
	CASE
			WHEN SUM ( COLLECT_RESOURCE ) = 0 THEN
			0 ELSE SUM ( RETURN_RESOURCE ) / SUM ( COLLECT_RESOURCE )
		END AS AREA_RETURN_PERSENT,
		SUM ( RETURN_RESOURCE ) AREA_RETURN_NUMBER
	FROM
		data_analysis_provice_bak
	GROUP BY
		provice_id,
	YEAR
	) B
WHERE
	A.provice_id = B.provice_id
	AND A.YEAR = B.YEAR AND A.YEAR IN('2018','2019');


DELETE FROM DATA_ANALYSIS_SIX_AREA_BAK WHERE YEAR IN('2018','2019');
INSERT INTO DATA_ANALYSIS_SIX_AREA_BAK (
	ID,
	YEAR,
	SIX_AREA_ID,
	STRAW_TYPE,
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
	INDU_UTIL_INDEX,
	AREA_NAME,
	STRAW_NAME,
	YIELD_ALL_NUM,
	LEAVE_NUMBER,
	USETOTAL,
	AREA_RETURN_NUMBER,
	AREA_RETURN_PERSENT,
	RETURN_PERS
) SELECT ID
,
YEAR,
PROVICE_ID,
STRAW_TYPE,
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
INDU_UTIL_INDEX,
AREA_NAME,
STRAW_NAME,
YIELD_ALL_NUM,
LEAVE_NUMBER,
USETOTAL,
AREA_RETURN_NUMBER,
AREA_RETURN_PERSENT,
RETURN_PERS
FROM
	DATA_ANALYSIS_PROVICE_BAK WHERE YEAR IN('2018','2019');

UPDATE data_analysis_area_bak a
SET area_name = b."REGION_NAME"
FROM
	sys_region b
WHERE
	area_id = b.region_code AND A.YEAR IN('2018','2019');

UPDATE data_analysis_city_bak a
SET area_name = b."REGION_NAME"
FROM
    sys_region b
WHERE
    city_id = b.region_code AND A.YEAR IN('2018','2019');

UPDATE data_analysis_provice_bak a
SET area_name = b."REGION_NAME"
FROM
	sys_region b
WHERE
	provice_id = b.region_code AND A.YEAR IN('2018','2019');


UPDATE DATA_ANALYSIS_SIX_AREA_BAK
SET AREA_NAME =
CASE
		WHEN SIX_AREA_ID = '420000' THEN
		'长江中下游区'
		WHEN SIX_AREA_ID = '430000' THEN
		'长江中下游区'
		WHEN SIX_AREA_ID = '360000' THEN
		'长江中下游区'
		WHEN SIX_AREA_ID = '330000' THEN
		'长江中下游区'
		WHEN SIX_AREA_ID = '310000' THEN
		'长江中下游区'
		WHEN SIX_AREA_ID = '320000' THEN
		'长江中下游区'
		WHEN SIX_AREA_ID = '340000' THEN
		'长江中下游区'
		WHEN SIX_AREA_ID = '210000' THEN
		'东北区'
		WHEN SIX_AREA_ID = '220000' THEN
		'东北区'
		WHEN SIX_AREA_ID = '230000' THEN
		'东北区'
		WHEN SIX_AREA_ID = '150000' THEN
		'东北区'
		WHEN SIX_AREA_ID = '610000' THEN
		'西北区'
		WHEN SIX_AREA_ID = '620000' THEN
		'西北区'
		WHEN SIX_AREA_ID = '630000' THEN
		'西北区'
		WHEN SIX_AREA_ID = '640000' THEN
		'西北区'
		WHEN SIX_AREA_ID = '650000' THEN
		'西北区'
		WHEN SIX_AREA_ID = '660000' THEN
		'西北区'
		WHEN SIX_AREA_ID = '510000' THEN
		'西南区'
		WHEN SIX_AREA_ID = '520000' THEN
		'西南区'
		WHEN SIX_AREA_ID = '530000' THEN
		'西南区'
		WHEN SIX_AREA_ID = '540000' THEN
		'西南区'
		WHEN SIX_AREA_ID = '500000' THEN
		'西南区'
		WHEN SIX_AREA_ID = '110000' THEN
		'华北区'
		WHEN SIX_AREA_ID = '120000' THEN
		'华北区'
		WHEN SIX_AREA_ID = '370000' THEN
		'华北区'
		WHEN SIX_AREA_ID = '410000' THEN
		'华北区'
		WHEN SIX_AREA_ID = '130000' THEN
		'华北区'
		WHEN SIX_AREA_ID = '140000' THEN
		'华北区'
		WHEN SIX_AREA_ID = '350000' THEN
		'华南区'
		WHEN SIX_AREA_ID = '440000' THEN
		'华南区'
		WHEN SIX_AREA_ID = '450000' THEN
		'华南区'
		WHEN SIX_AREA_ID = '460000' THEN
		'华南区'
		ELSE '未知区域'
END WHERE YEAR IN('2018','2019');

DELETE from data_analysis_area_bak where area_name is null and YEAR IN('2018','2019');
DELETE from data_analysis_city_bak where area_name is null AND YEAR IN('2018','2019');
DELETE from data_analysis_provice_bak where area_name is null AND YEAR IN('2018','2019');


-- 处理市级汇总数据
update straw_utilize_sum_bak a
set export_yield_total = b.export_yield_total
from
(SELECT year,province_id,city_id,straw_type,sum(export_yield_total) as "export_yield_total" from straw_utilize_sum_bak where year = '2018' and city_id != area_id group by year,province_id,city_id,straw_type) b
where a.year = b.year and a.province_id = b.province_id and a.area_id = b.city_id and a.straw_type = b.straw_type and a.export_yield_total != b.export_yield_total;

-- 处理省级汇总数据
update straw_utilize_sum_bak a
set export_yield_total = b.export_yield_total
from
(SELECT year,province_id,straw_type,sum(export_yield_total) as "export_yield_total" from straw_utilize_sum_bak where year = '2018' and province_id != city_id and city_id = area_id group by year,province_id,straw_type) b
where a.year = b.year and a.province_id = b.province_id and a.province_id = a.city_id and a.city_id = a.area_id and a.straw_type = b.straw_type and a.export_yield_total != b.export_yield_total;


-- 修改省、市、区、六大区 四张汇总表的数据
update data_analysis_area_bak a
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
	SELECT * from straw_utilize_sum_bak where year in ('2018','2019')
) b
where
	a.area_id = b.area_id and a.year = b.year and a.straw_type = b.straw_type;


update data_analysis_city_bak a
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
	SELECT * from straw_utilize_sum_bak where year in ('2018','2019') and city_id = area_id and province_id != city_id
) b
where
	a.year = b.year and a.city_id = b.city_id and a.straw_type = b.straw_type;


update data_analysis_provice_bak a
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
	SELECT * from straw_utilize_sum_bak where year in ('2018','2019') and province_id = city_id and city_id = area_id
) b
where
	a.year = b.year and a.provice_id = b.province_id and a.straw_type = b.straw_type;


DELETE FROM straw_usage_sum_bak where YEAR IN('2018','2019');
insert into straw_usage_sum_bak (id,
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
	straw_utilize_sum_bak.id as id,
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
	straw_utilize_sum_bak , sys_dictionary
	WHERE straw_type=dict_key AND YEAR IN('2018','2019'));

-- 处理2020年BUG数据
DELETE from data_analysis_area_bak where id in(SELECT t1.id from data_analysis_area_bak t1 join (
SELECT a.year,a.area_id,a.status,b.status as "s2" from collect_flow a join ( SELECT * from collect_flow where "level" = '4' and status = 3) b on b.year = a.year and b.city_id = a.city_id
where a."level" = '3' and a.status = 3 ) t2 on t1.year = t2.year and t1.area_id = t2.area_id);

-- 处理18-19-20年的BUG数据
update country_task a
set status = b.status
from (SELECT a.id,b.status from country_task a left join collect_flow b on b.year = a.year and b.province_id = a.province_id and b.city_id = a.city_id and b.area_id = a.area_id
WHERE a.status != b.status) b
where a.id = b.id;

-- 最后再将bak的表替换为使用表


