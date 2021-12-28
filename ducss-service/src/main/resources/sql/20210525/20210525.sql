-- 洋浦加 18 19 20年度任务
INSERT INTO "country_task"("id", "year", "province_id", "city_id", "area_id", "peasant_total", "expect_num", "fact_num", "main_num", "status", "is_report", "task_level", "create_user_name", "create_user_id", "create_date") VALUES ('b50af1f9fa8e404bafe937e912d79dcb', '2018', '460000', '460500', '460501', 0, 100, 0, 0, 0, 0, 'county', 'xiongliang', '09831765be1240f28cdf2834d528fccf', '2021-04-14 10:05:17.373');
INSERT INTO "country_task"("id", "year", "province_id", "city_id", "area_id", "peasant_total", "expect_num", "fact_num", "main_num", "status", "is_report", "task_level", "create_user_name", "create_user_id", "create_date") VALUES ('573624c10a6f4ac8aeed4eceb7973b78', '2019', '460000', '460500', '460501', 0, 5, 5, 0, 5, 0, 'county', 'ducss_gdbj', 'a2f86cabf73647d1b848baeecdff5028', '2021-05-12 10:31:07.507');
INSERT INTO "country_task"("id", "year", "province_id", "city_id", "area_id", "peasant_total", "expect_num", "fact_num", "main_num", "status", "is_report", "task_level", "create_user_name", "create_user_id", "create_date") VALUES ('573624c10a6f4ac8aeed4eceb7943b98', '2020', '460000', '460500', '460501', 0, 100, 0, 0, 0, 0, 'county', 'ducss_gdbj', 'a2f86cabf73647d1b848baeecdff5028', '2021-05-12 10:31:07.507');

-- 校正定州虚拟县的区域id 139682
-- 删除重复数据
DELETE from disperse_utilize_detail where id in(  SELECT a.id FROM "disperse_utilize_detail" a left join disperse_utilize b on b.id = a.utilize_id
where b.area_id = '139682' and year = '2019' and b.create_user_id = '152');
DELETE from disperse_utilize where area_id = '139682' and year = '2019' and create_user_id = '152';
-- 修改province_id city_id
update disperse_utilize set province_id = '130000',city_id = '130682' where area_id = '139682';


DELETE from straw_utilize_detail where id in( SELECT a.id from straw_utilize_detail a left join straw_utilize b on b.id = a.utilize_id
where b.area_id = '139682' and b.year = '2019' and b.create_user_id = 'a82767175757429daab7be35d780edcc');
DELETE from straw_utilize where area_id = '139682' and year = '2019' and create_user_id = 'a82767175757429daab7be35d780edcc';
-- 修改province_id city_id
update straw_utilize set province_id = '130000',city_id = '130682' where area_id = '139682';

-- 修改任务表
update country_task set province_id = '130000',city_id = '130682' where area_id = '139682';

