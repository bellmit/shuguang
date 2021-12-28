drop table T_GDAP_TB_RESOUCE_CONFIG cascade constraints;

/*==============================================================*/
/* Table: T_GDAP_TB_RESOUCE_CONFIG                              */
/*==============================================================*/
create table T_GDAP_TB_RESOUCE_CONFIG 
(
   ID                   VARCHAR2(64 char),
   RESOURCE_ID          VARCHAR2(64 char),
   TABLE_NAME           VARCHAR2(64 char),
   TABLE_CN_NAME        VARCHAR2(64 char),
   FIELD_NAME           VARCHAR2(64 char),
   FIELD_CN_NAME        VARCHAR2(64 char),
   LENGTH               NUMBER,
   TYPE                 VARCHAR2(20 char),
   IS_CONDITION         CHAR,
   DEL_FLAG             VARCHAR2(10 char),
   CREATE_BY            VARCHAR2(64 char)         not null,
   CREATE_DATE          TIMESTAMP            default SYSDATE not null,
   UPDATE_BY            VARCHAR2(64 char)         not null,
   UPDATE_TIME          TIMESTAMP            default SYSDATE not null,
   RESERVED_FIELD1      VARCHAR2(200 char),
   RESERVED_FIELD2      VARCHAR2(200 char),
   RESERVED_FIELD3      VARCHAR2(200 char),
   RESERVED_FIELD4      VARCHAR2(200 char),
   RESERVED_FIELD5      VARCHAR2(200 char),
   RESERVED_FIELD6      VARCHAR2(200 char),
   RESERVED_FIELD7      VARCHAR2(200 char),
   RESERVED_FIELD8      VARCHAR2(200 char),
   RESERVED_FIELD9      VARCHAR2(200 char),
   RESERVED_FIELD10     VARCHAR2(200 char),
   RESERVED_FIELD11     VARCHAR2(200 char),
   RESERVED_FIELD12     VARCHAR2(200 char),
   RESERVED_FIELD13     VARCHAR2(200 char),
   RESERVED_FIELD14     VARCHAR2(200 char),
   RESERVED_FIELD15     VARCHAR2(200 char),
   RESERVED_FIELD16     VARCHAR2(200 char),
   RESERVED_FIELD17     VARCHAR2(200 char),
   RESERVED_FIELD18     VARCHAR2(200 char),
   RESERVED_FIELD19     VARCHAR2(200 char),
   RESERVED_FIELD20     VARCHAR2(200 char)
);

comment on table T_GDAP_TB_RESOUCE_CONFIG is
'表类型资源表明字段配置表';

comment on column T_GDAP_TB_RESOUCE_CONFIG.IS_CONDITION is
'y:可作为查询条件
n:不作为查询条件';

comment on column T_GDAP_TB_RESOUCE_CONFIG.CREATE_BY is
'非空，无默认值';

comment on column T_GDAP_TB_RESOUCE_CONFIG.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_GDAP_TB_RESOUCE_CONFIG.UPDATE_BY is
'非空，无默认值';

comment on column T_GDAP_TB_RESOUCE_CONFIG.UPDATE_TIME is
'非空，默认值当前时间';
