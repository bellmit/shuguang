-- 处理数据审核页面秸秆指标
UPDATE collect_flow
SET straw_utilize_num = main_num + farmer_split_num + direct_return_num + export_num - buy_other_num
WHERE
	ID IN (
	SELECT A
		.ID
	FROM
		"collect_flow"
		A JOIN production_usage_sum b ON b.YEAR = A.YEAR
		AND b.area_id = A.area_id
	WHERE
	A.straw_utilize_num != b.straw_usage
	);

-- 处理产生量与利用量汇总页面秸秆指标
update production_usage_sum a
set straw_usage = a.straw_usage + b.export_num
from(
SELECT a.id,b.export_num from production_usage_sum a join collect_flow b on b.year = a.year and b.area_id = a.area_id
where b.export_num > 0 and b.straw_utilize_num != a.straw_usage
) b
where a.id = b.id;

-- 处理straw_utilize_sum_total表中秸秆利用量指标,调出量丢失和部分数据没有将调入调出纳入计算
update straw_utilize_sum_total a
set export_yield_total = b.export_num
from (SELECT a.id,export_num from straw_utilize_sum_total a join collect_flow b on a.year = b.year and a.area_id = b.area_id
where a.export_yield_total != b.export_num) b
where a.id = b.id;

update straw_utilize_sum_total
set pro_straw_utilize = main_total + disperse_total + return_resource + export_yield_total - main_total_other
where id in
( SELECT
	id
FROM
	straw_utilize_sum_total
WHERE
	main_total + disperse_total + return_resource + export_yield_total - main_total_other != pro_straw_utilize);

-- 更新straw_utilize_sum秸秆利用量
update straw_utilize_sum
set pro_straw_utilize = main_total + disperse_total + pro_still_field + export_yield_total - main_total_other
where pro_straw_utilize != main_total + disperse_total + pro_still_field + export_yield_total - main_total_other;


-- 更新19年错误数据
update production_usage_sum a
set straw_usage = b.u2
from (SELECT year,area_id,sum(straw_usage) as "u2" from straw_usage_sum where year = '2019' group by year,area_id ) b
where a.straw_usage != b.u2 and a.year = b.year and a.area_id = b.area_id and a.year = '2019';

-- 更新18年错误数据
update straw_usage_sum a
set straw_usage = b.u2
from (SELECT year,area_id,sum(straw_usage) as "u2" from production_usage_sum where year = '2018' group by year,area_id) b
where a.straw_usage != b.u2 and a.year = b.year and a.area_id = b.area_id and a.year = '2018';




-- 更新city表数据
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
	SELECT * from straw_utilize_sum where city_id = area_id and province_id != city_id
) b
where
	a.year = b.year and a.city_id = b.city_id and a.straw_type = b.straw_type;



-- 处理19年广西贺州数据(数据审核数据错误)
UPDATE collect_flow A
SET theory_num = b.theory_num,
collect_num = b.collect_num,
main_num = b.main_num,
farmer_split_num = b.farmer_split_num,
direct_return_num = b.direct_return_num,
straw_utilize_num = b.straw_utilize_num,
buy_other_num = b.buy_other_num,
export_num = b.export_num,
syn_utilize_num = b.straw_utilize_num * 100 / b.collect_num
FROM
	(
	SELECT SUM
		( theory_num ) AS "theory_num",
		SUM ( collect_num ) AS "collect_num",
		SUM ( main_num ) AS "main_num",
		SUM ( farmer_split_num ) AS "farmer_split_num",
		SUM ( direct_return_num ) AS "direct_return_num",
		SUM ( straw_utilize_num ) AS "straw_utilize_num",
		SUM ( buy_other_num ) AS "buy_other_num",
		SUM ( export_num ) AS "export_num"
	FROM
		"collect_flow"
	WHERE
		YEAR = '2019'
		AND city_id = '451100'
		AND "level" = 3
	) b
WHERE
	A.YEAR = '2019'
	AND A.area_id = '451100';

-- 处理19年广西省数据(数据审核)
UPDATE collect_flow A
SET theory_num = b.theory_num,
collect_num = b.collect_num,
main_num = b.main_num,
farmer_split_num = b.farmer_split_num,
direct_return_num = b.direct_return_num,
straw_utilize_num = b.straw_utilize_num,
buy_other_num = b.buy_other_num,
export_num = b.export_num,
syn_utilize_num = b.straw_utilize_num * 100 / b.collect_num
FROM
	(
	SELECT SUM
		( theory_num ) AS "theory_num",
		SUM ( collect_num ) AS "collect_num",
		SUM ( main_num ) AS "main_num",
		SUM ( farmer_split_num ) AS "farmer_split_num",
		SUM ( direct_return_num ) AS "direct_return_num",
		SUM ( straw_utilize_num ) AS "straw_utilize_num",
		SUM ( buy_other_num ) AS "buy_other_num",
		SUM ( export_num ) AS "export_num"
	FROM
		"collect_flow"
	WHERE
		YEAR = '2019'
		AND province_id = '450000'
		AND "level" = 4
	) b
WHERE
	A.YEAR = '2019'
	AND A.area_id = '450000';

-- 处理19年河南省下的济源市  原济源市现移入驻马店市下
-- 济源市 : 419001 原上级id : 419000 修改为 411700
update collect_flow set city_id = '411700',level = '3' where area_id = '419001';
update collect_flow a
SET theory_num = a.theory_num + b.theory_num,
collect_num = a.collect_num + b.collect_num,
main_num = a.main_num + b.main_num,
farmer_split_num = a.farmer_split_num + b.farmer_split_num,
direct_return_num = a.direct_return_num + b.direct_return_num,
straw_utilize_num = a.straw_utilize_num + b.straw_utilize_num,
buy_other_num = a.buy_other_num + b.buy_other_num,
export_num = a.export_num + b.export_num,
syn_utilize_num = (a.straw_utilize_num + b.straw_utilize_num) * 100 / (a.collect_num + b.collect_num)
FROM
	(
	SELECT
	    *
	FROM
		"collect_flow"
	WHERE
	    area_id = '419001'
	) b
WHERE
	A.YEAR = b.year
	AND A.area_id = '411700';

update production_usage_sum a
SET produce = a.produce + b.produce,
collect = a.collect + b.collect,
straw_usage = a.straw_usage + b.straw_usage,
all_total = a.all_total + b.all_total,
fertilizer = a.fertilizer + b.fertilizer,
fuel = a.fuel + b.fuel,
basic = a.basic + b.basic,
raw_material = a.raw_material + b.raw_material,
feed = a.feed + b.feed,
main_total = a.main_total + b.main_total,
comprehensive_rate = (a.straw_usage + b.straw_usage) * 100 / (a.collect + b.collect)
FROM
	(
	SELECT
	    *
	FROM
		production_usage_sum
	WHERE
	    area_id = '419001'
	) b
WHERE
	A.YEAR = b.year
	AND A.area_id = '411700';

update production_usage_sum a
set comprehensive_index = ( b.main_num + b.farmer_split_num + b.direct_return_num + b.export_num - b.buy_other_num) / b.collect_num,
industrialization_index = b.main_num / b.collect_num
FROM
	(
	SELECT
	    *
	FROM
		collect_flow
	WHERE
	    area_id = '411700'
	) b
WHERE
	A.YEAR = b.year
	AND A.area_id = '411700';


update straw_utilize_sum set city_id = '411700' where area_id = '419001';

update straw_utilize_sum a
set
main_fertilising = a.main_fertilising + b.main_fertilising,
main_forage = a.main_forage + b.main_forage,
main_fuel = a.main_fuel + b.main_fuel,
main_base = a.main_base + b.main_base,
main_material = a.main_material + b.main_material,
main_total = a.main_total + b.main_total,
main_total_other = a.main_total_other + b.main_total_other,
disperse_fertilising = a.disperse_fertilising + b.disperse_fertilising,
disperse_forage = a.disperse_forage + b.disperse_forage,
disperse_fuel = a.disperse_fuel + b.disperse_fuel,
disperse_base = a.disperse_base + b.disperse_base,
disperse_material = a.disperse_material + b.disperse_material,
disperse_total = a.disperse_total + b.disperse_total,
pro_still_field = a.pro_still_field + b.pro_still_field,
pro_straw_utilize = a.pro_straw_utilize + b.pro_straw_utilize,
collect_resource = a.collect_resource + b.collect_resource,
theory_resource = a.theory_resource + b.theory_resource,
export_yield_total = a.export_yield_total + b.export_yield_total,
grain_yield = a.grain_yield + b.grain_yield
FROM
	(
	SELECT
	    *
	FROM
		straw_utilize_sum
	WHERE
	    area_id = '419001'
	) b
WHERE
	A.YEAR = b.year
	AND A.area_id = '411700' and a.straw_type = b.straw_type;


update straw_usage_sum a
set
straw_usage = b.straw_usage,
comprehensive_rate = b.comprehensive_rate,
all_total = b.all_total,
fertilizer= b.fertilizer,
fuel = b.fuel,
basic = b.basic,
raw_material = b.raw_material,
feed = b.feed,
other = b.other,
yield_export = b.yield_export,
collect_resource = b.collect_resource,
main_total = b.main_total,
return_resource = b.return_resource
from(
    SELECT
        straw_utilize_sum.id as id,
        YEAR,
        area_id,
        straw_type,
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
    from straw_utilize_sum
    where area_id = '411700'
) b
where a.area_id = '411700' and a.year = b.year and a.straw_type = b.straw_type;

-- 承德市滦平县数据丢失 需要退回重新上报
// todo


-- 吉首市县的分散利用量有误
update disperse_utilize_detail set sown_area = '0' where id = '14198558';
update disperse_utilize_detail set fuel = '0' where id = '14198782';
update disperse_utilize_detail set fuel = '0' where id = '14199258';

update disperse_utilize_detail set yield_per_mu = '1600' where id = '14194312';