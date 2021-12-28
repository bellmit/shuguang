ALTER TABLE "data_analysis_area" ADD COLUMN "collect_resource_v2" numeric(38,10);
ALTER TABLE "data_analysis_area" ADD COLUMN "straw_utilization_v2" numeric(38,10);
COMMENT ON COLUMN "data_analysis_area"."collect_resource_v2" IS '该字段用于计算综合利用量得，不做其他展示';
COMMENT ON COLUMN "data_analysis_area"."straw_utilization_v2" IS '该字段用于计算综合利用量得，不做其他展示';

ALTER TABLE "data_analysis_city" ADD COLUMN "collect_resource_v2" numeric(38,10);
ALTER TABLE "data_analysis_city" ADD COLUMN "straw_utilization_v2" numeric(38,10);
COMMENT ON COLUMN "data_analysis_city"."collect_resource_v2" IS '该字段用于计算综合利用量得，不做其他展示';
COMMENT ON COLUMN "data_analysis_city"."straw_utilization_v2" IS '该字段用于计算综合利用量得，不做其他展示';

ALTER TABLE "data_analysis_provice" ADD COLUMN "collect_resource_v2" numeric(38,10);
ALTER TABLE "data_analysis_provice" ADD COLUMN "straw_utilization_v2" numeric(38,10);
COMMENT ON COLUMN "data_analysis_provice"."collect_resource_v2" IS '该字段用于计算综合利用量得，不做其他展示';
COMMENT ON COLUMN "data_analysis_provice"."straw_utilization_v2" IS '该字段用于计算综合利用量得，不做其他展示';
