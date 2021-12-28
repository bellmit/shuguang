-- 20年 湖北省荆门市掇刀区重复数据
delete from straw_produce
where id in (select id from straw_produce where year = '2020' and area_id = '420804' limit 14);
delete from straw_produce
where id in (select id from straw_produce where year = '2020' and area_id = '420881' limit 14);

-- 20年 湖北省荆门市汇总数据
update straw_produce a
set theory_resource = b.th,
collect_resource = b.co,
grain_yield = b.gr,
seed_area = b.se
from (
SELECT straw_type,sum(theory_resource) as "th",sum(collect_resource) as "co",sum(grain_yield) as "gr",sum(seed_area) as "se" FROM "straw_produce" where year = '2020' and area_id in ('420802','420804','420821','420822','420881') GROUP BY straw_type
) b
where a.year = '2020' and b.straw_type = a.straw_type and a.area_id = '420800';

-- 重复数据
-- DELETE from production_usage_sum where id = 'cc3a7ed8795e448c84da1018b96803db';


