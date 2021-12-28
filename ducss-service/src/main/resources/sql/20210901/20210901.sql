update production_usage_sum a set collect_resource_v2 = b.col,straw_utilization_v2 = b.st
from ( SELECT year,area_id,sum(case when collect_resource > 0 and straw_utilization > 0 then collect_resource else 0 end)as "col",sum(case when collect_resource > 0 and straw_utilization > 0 then straw_utilization else 0 end) as "st" from data_analysis_area group by year,area_id ) b
where a.year = b.year and a.area_id = b.area_id;

update production_usage_sum a set collect_resource_v2 = b.col,straw_utilization_v2 = b.st
from ( SELECT year,city_id,sum(case when collect_resource_v2 > 0 and straw_utilization_v2 > 0 then collect_resource_v2 else 0 end)as "col",sum(case when collect_resource_v2 > 0 and straw_utilization_v2 > 0 then straw_utilization_v2 else 0 end) as "st" from data_analysis_city group by year,city_id ) b
where a.year = b.year and a.area_id = b.city_id;

update production_usage_sum a set collect_resource_v2 = b.col,straw_utilization_v2 = b.st
from ( SELECT year,provice_id,sum(case when collect_resource_v2 > 0 and straw_utilization_v2 > 0 then collect_resource_v2 else 0 end)as "col",sum(case when collect_resource_v2 > 0 and straw_utilization_v2 > 0 then straw_utilization_v2 else 0 end) as "st" from data_analysis_provice group by year,provice_id ) b
where a.year = b.year and a.area_id = b.provice_id;

update collect_flow a set collect_resource_v2 = b.col,straw_utilization_v2 = b.st
from ( SELECT year,area_id,sum(case when collect_resource > 0 and straw_utilization > 0 then collect_resource else 0 end)as "col",sum(case when collect_resource > 0 and straw_utilization > 0 then straw_utilization else 0 end) as "st" from data_analysis_area group by year,area_id ) b
where a.year = b.year and a.area_id = b.area_id;

update collect_flow a set collect_resource_v2 = b.col,straw_utilization_v2 = b.st
from ( SELECT year,city_id,sum(case when collect_resource_v2 > 0 and straw_utilization_v2 > 0 then collect_resource_v2 else 0 end)as "col",sum(case when collect_resource_v2 > 0 and straw_utilization_v2 > 0 then straw_utilization_v2 else 0 end) as "st" from data_analysis_city group by year,city_id ) b
where a.year = b.year and a.area_id = b.city_id;

update collect_flow a set collect_resource_v2 = b.col,straw_utilization_v2 = b.st
from ( SELECT year,provice_id,sum(case when collect_resource_v2 > 0 and straw_utilization_v2 > 0 then collect_resource_v2 else 0 end)as "col",sum(case when collect_resource_v2 > 0 and straw_utilization_v2 > 0 then straw_utilization_v2 else 0 end) as "st" from data_analysis_provice group by year,provice_id ) b
where a.year = b.year and a.area_id = b.provice_id;

update straw_utilize_sum a set collect_resource_v2 = b.collect_resource_v2,straw_utilization_v2 = b.straw_utilization_v2
from ( SELECT year,area_id,straw_type, collect_resource_v2,straw_utilization_v2 from data_analysis_area) b
where a.year = b.year and a.area_id = b.area_id and a.straw_type = b.straw_type;

update straw_utilize_sum a set collect_resource_v2 = b.collect_resource_v2,straw_utilization_v2 = b.straw_utilization_v2
from ( SELECT year,city_id,straw_type, collect_resource_v2,straw_utilization_v2 from data_analysis_city) b
where a.year = b.year and a.area_id = b.city_id and a.straw_type = b.straw_type;

update straw_utilize_sum a set collect_resource_v2 = b.collect_resource_v2,straw_utilization_v2 = b.straw_utilization_v2
from ( SELECT year,provice_id,straw_type, collect_resource_v2,straw_utilization_v2 from data_analysis_provice) b
where a.year = b.year and a.area_id = b.provice_id and a.straw_type = b.straw_type;

update straw_usage_sum a set collect_resource_v2 = b.collect_resource_v2,straw_utilization_v2 = b.straw_utilization_v2
from ( SELECT year,area_id,straw_type, collect_resource_v2,straw_utilization_v2 from data_analysis_area) b
where a.year = b.year and a.area_id = b.area_id and a.straw_type = b.straw_type;

update straw_usage_sum a set collect_resource_v2 = b.collect_resource_v2,straw_utilization_v2 = b.straw_utilization_v2
from ( SELECT year,city_id,straw_type, collect_resource_v2,straw_utilization_v2 from data_analysis_city) b
where a.year = b.year and a.area_id = b.city_id and a.straw_type = b.straw_type;

update straw_usage_sum a set collect_resource_v2 = b.collect_resource_v2,straw_utilization_v2 = b.straw_utilization_v2
from ( SELECT year,provice_id,straw_type, collect_resource_v2,straw_utilization_v2 from data_analysis_provice) b
where a.year = b.year and a.area_id = b.provice_id and a.straw_type = b.straw_type;