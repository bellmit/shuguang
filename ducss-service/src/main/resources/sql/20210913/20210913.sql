ALTER TABLE "collect_flow_log" ADD COLUMN "files" varchar(255);
COMMENT ON COLUMN "collect_flow_log"."files" IS '附件ids';