-- 处理19年河南省商丘市的数据
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
		AND city_id = '411400'
		AND level = '3'
	) b
WHERE
	A.YEAR = '2019'
	AND A.area_id = '411400'
	and level = '4';

-- 商丘的数据错误
update straw_produce a
set theory_resource = b.th,
collect_resource = b.co,
grain_yield = b.gr,
seed_area = b.se
from (
SELECT straw_type,sum(theory_resource) as "th",sum(collect_resource) as "co",sum(grain_yield) as "gr",sum(seed_area) as "se" FROM "straw_produce" where year = '2019' and area_id in ('411402','411403','411404','411421','411422','411423','411424','411425','411426','411481') GROUP BY straw_type
) b
where a.year = '2019' and b.straw_type = a.straw_type and a.area_id = '411400';

update straw_produce a
set theory_resource = b.th,
collect_resource = b.co,
grain_yield = b.gr,
seed_area = b.se
from (
SELECT straw_type,sum(theory_resource) as "th",sum(collect_resource) as "co",sum(grain_yield) as "gr",sum(seed_area) as "se" FROM "straw_produce" where year = '2019' and area_id in ('410100','410200','410300','410400','410500','410600','410700','410800','410900','411000','411100','411200','411300','411400','411500','411600','411700','419001') GROUP BY straw_type
) b
where a.year = '2019' and b.straw_type = a.straw_type and a.area_id = '410000';