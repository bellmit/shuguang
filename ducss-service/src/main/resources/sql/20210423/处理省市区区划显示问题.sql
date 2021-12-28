-- 处理 data_analysis_area 中的area_name区域不完整问题
update data_analysis_area a
set area_name = b.name
from (SELECT
	a.id,
	a.area_id,
	a.year,
	a.straw_type,
	a.area_name,
	b.parent_name,
	b.region_name,
	case
	when b.version_year = '2021' then b.parent_name || '/'|| b.region_name
	else b.parent_name end as "name"
FROM
	"data_analysis_area" a
left join (SELECT t1.region_code,t1.region_name,t1.version_year,replace(replace(t1.parent_names,'/行政区划',''),'中国','') as "parent_name" from sys_region_0423 t1 join (SELECT region_code,max(version_code) as "version_code" from sys_region_0423 where del_flag = 'N' group by region_code) t2 on t1.version_code = t2.version_code and t1.region_code = t2.region_code where t1.del_flag = 'N') b on b.region_code = a.area_id) b
where a.year = b.year and a.area_id = b.area_id and a.straw_type = b.straw_type;

-- 处理 data_analysis_city 中的area_name区域不完整问题
update data_analysis_city a
set area_name = b.name
from (SELECT
	a.id,
	a.city_id,
	a.year,
	a.straw_type,
	a.area_name,
	b.parent_name,
	b.region_name,
	case
	when b.version_year = '2021' then b.parent_name || '/'|| b.region_name
	else b.parent_name end as "name"
FROM
	"data_analysis_city" a
left join (SELECT t1.region_code,t1.region_name,t1.version_year,replace(replace(t1.parent_names,'/行政区划',''),'中国','') as "parent_name" from sys_region_0423 t1 join (SELECT region_code,max(version_code) as "version_code" from sys_region_0423 where del_flag = 'N' group by region_code) t2 on t1.version_code = t2.version_code and t1.region_code = t2.region_code where t1.del_flag = 'N') b on b.region_code = a.city_id) b
where a.year = b.year and a.city_id = b.city_id and a.straw_type = b.straw_type;