-- ----------------------------------------------------------------------- 
-- t_mdm_basic_code 
-- ----------------------------------------------------------------------- 

CREATE TABLE t_mdm_basic_code
(
    id VARCHAR2(64 char) NOT NULL,
    name VARCHAR2(64 char),
    mark VARCHAR2(256 char),
    abbreviation VARCHAR2(64 char),
    status CHAR(1) DEFAULT '0',
    codes_id VARCHAR2(64 char),
    parent_id VARCHAR2(64 char),
    parent_ids VARCHAR2(64 char),
    create_by VARCHAR2(64 char),
    createDate DATE,
    update_by VARCHAR2(64 char),
    updateDate DATE,
    del_flag CHAR(1) DEFAULT '0',
    remarks VARCHAR2(255 char),
    PRIMARY KEY (id)
);
comment on table t_mdm_basic_code is
'主数据代码表';
comment on column t_mdm_basic_code.id is
'主键id';
comment on column t_mdm_basic_code.name is
'代码名称';
comment on column t_mdm_basic_code.mark is
'代码标识';
comment on column t_mdm_basic_code.abbreviation is
'简称';
comment on column t_mdm_basic_code.status is
'状态（0，草稿，1，发布,2，作废）';
comment on column t_mdm_basic_code.codes_id is
'所属代码集id';
comment on column t_mdm_basic_code.parent_id is
'父代码集id';
comment on column t_mdm_basic_code.parent_ids is
'所有父代码集id拼接';
comment on column t_mdm_basic_code.create_by is
'创建人id';
comment on column t_mdm_basic_code.createDate is
'创建时间';
comment on column t_mdm_basic_code.update_by is
'更新人id';
comment on column t_mdm_basic_code.updateDate is
'非空，默认值当前时间';
comment on column t_mdm_basic_code.del_flag is
'删除标志';
comment on column t_mdm_basic_code.remarks is
'备注';

-- ----------------------------------------------------------------------- 
-- t_mdm_basic_codes 
-- ----------------------------------------------------------------------- 

CREATE TABLE t_mdm_basic_codes
(
    id VARCHAR2(64 char) NOT NULL,
    name VARCHAR2(64 char),
    format VARCHAR2(256 char),
    status CHAR(1) DEFAULT '0',
    normal VARCHAR2(256 char),
    mark VARCHAR2(256 char),
    description VARCHAR2(256 char),
    batch VARCHAR2(256 char),
    principle VARCHAR2(256 char),
    parent_id VARCHAR2(64 char),
    parent_ids VARCHAR2(64 char),
    create_by VARCHAR2(64 char),
    createDate DATE,
    update_by VARCHAR2(64 char),
    updateDate DATE,
    del_flag CHAR(1) DEFAULT '0',
    remarks VARCHAR2(255 char),
    PRIMARY KEY (id)
);
comment on table t_mdm_basic_codes is
'主数据代码集表';
comment on column t_mdm_basic_codes.id is
'主键id';
comment on column t_mdm_basic_codes.name is
'代码集名称';
comment on column t_mdm_basic_codes.format is
'表示格式';
comment on column t_mdm_basic_codes.status is
'状态（0，草稿，1，发布,2，作废）';
comment on column t_mdm_basic_codes.normal is
'引用标准';
comment on column t_mdm_basic_codes.mark is
'代码集标识';
comment on column t_mdm_basic_codes.description is
'代码集描述';
comment on column t_mdm_basic_codes.batch is
'同步批号';
comment on column t_mdm_basic_codes.principle is
'分类原则';
comment on column t_mdm_basic_codes.parent_id is
'父代码集id';
comment on column t_mdm_basic_codes.parent_ids is
'所有父代码集id拼接';
comment on column t_mdm_basic_codes.create_by is
'创建人id';
comment on column t_mdm_basic_codes.createDate is
'创建时间';
comment on column t_mdm_basic_codes.update_by is
'更新人id';
comment on column t_mdm_basic_codes.updateDate is
'更新时间';
comment on column t_mdm_basic_codes.del_flag is
'删除标志';
comment on column t_mdm_basic_codes.remarks is
'备注';

