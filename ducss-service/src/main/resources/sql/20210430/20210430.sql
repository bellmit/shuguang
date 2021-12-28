-- 处理collect_flow 秸秆利用量数据
update collect_flow set
syn_utilize_num = round(straw_utilize_num * 100/collect_num,10)
where
abs(syn_utilize_num - round(straw_utilize_num * 100/collect_num,10)) > 0.001 and collect_num != 0 and year = '2020';

update collect_flow set straw_utilize_num = main_num + farmer_split_num + direct_return_num + export_num - buy_other_num
where main_num + farmer_split_num + direct_return_num + export_num - buy_other_num != straw_utilize_num;

-- 校对数据
update production_usage_sum a
set straw_usage = b.straw_utilize_num
from(
SELECT
	a.id,
	b.straw_utilize_num
FROM
	production_usage_sum
	A JOIN collect_flow b ON b.YEAR = A.YEAR
	AND b.area_id = A.area_id
	where a.straw_usage != b.straw_utilize_num and a.produce = b.theory_num and a."collect" = b.collect_num) b
where a.id = b.id;


