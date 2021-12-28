-- 创建普通索引 ： CREATE INDEX test1_id_index ON test1 (id);
-- 创建唯一索引 ： CREATE UNIQUE INDEX name ON table (column [, ...]);
-- 创建组合索引 ： CREATE INDEX test2_mm_idx ON test2 (major, minor);
create index utilize_id_idx on disperse_utilize_detail(utilize_id);
create Index still_id_idx on pro_still_detail(pro_still_id);
