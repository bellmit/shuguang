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
	SELECT * from straw_utilize_sum where year = '2020' and area_id = '330205'
) b
where
	a.area_id = b.area_id and a.year = b.year and a.straw_type = b.straw_type and a.area_id = '2020';


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
	SELECT * from straw_utilize_sum where year = '2020'
) b
where
	a.year = b.year and a.city_id = b.area_id and a.straw_type = b.straw_type and a.year = '2020';


update data_analysis_provice a
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
	SELECT * from straw_utilize_sum where year = '2020'
) b
where
	a.year = b.year and a.provice_id = b.area_id and a.straw_type = b.straw_type and a.year = '2020';