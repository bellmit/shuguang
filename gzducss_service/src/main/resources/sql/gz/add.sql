-- 产生量与直接还田量填报新增字段
ALTER TABLE pro_still_detail ADD COLUMN return_type varchar(12) DEFAULT NULL COMMENT '还田方式 DEEP_TURN-深翻还田, NO_TILLAGE-免耕还田, HEAP_RETTING-堆沤还田';
ALTER TABLE pro_still_detail ADD COLUMN leaving_type varchar(12) DEFAULT NULL COMMENT '离田运输方式 FARMER-农民自运, GOVERNMENT-政府统筹';
ALTER TABLE pro_still_detail ADD COLUMN transport_amount decimal(38,10) NOT NULL DEFAULT 0.0000000000 COMMENT '运输补贴';


-- 汇总新增字段
ALTER TABLE data_analysis_area ADD COLUMN return_type varchar(40) DEFAULT '' COMMENT '还田方式 DEEP_TURN-深翻还田, NO_TILLAGE-免耕还田, HEAP_RETTING-堆沤还田';
ALTER TABLE data_analysis_area ADD COLUMN leaving_type varchar(30) DEFAULT '' COMMENT '离田运输方式 FARMER-农民自运, GOVERNMENT-政府统筹';
ALTER TABLE data_analysis_area ADD COLUMN transport_amount decimal(38,10) NOT NULL DEFAULT 0.0000000000 COMMENT '运输补贴';

ALTER TABLE data_analysis_city ADD COLUMN return_type varchar(40) DEFAULT '' COMMENT '还田方式 DEEP_TURN-深翻还田, NO_TILLAGE-免耕还田, HEAP_RETTING-堆沤还田';
ALTER TABLE data_analysis_city ADD COLUMN leaving_type varchar(30) DEFAULT '' COMMENT '离田运输方式 FARMER-农民自运, GOVERNMENT-政府统筹';
ALTER TABLE data_analysis_city ADD COLUMN transport_amount decimal(38,10) NOT NULL DEFAULT 0.0000000000 COMMENT '运输补贴';

-- 字典值增加排序
ALTER TABLE sys_dict ADD COLUMN sort int(11) DEFAULT 0 COMMENT '排序值';