/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2016/11/21 17:30:34                          */
/*==============================================================*/


alter table T_DGAP_ALERT_LOG
   drop constraint FK_T_DGAP_A_REFERENCE_T_DGAP_A;

alter table T_DGAP_ALERT_LOG
   drop constraint FK_T_DGAP_A_REFERENCE_T_DGAP_R;

alter table T_DGAP_ALERT_RECEIPT
   drop constraint FK_T_DGAP_A_REFERENCE_T_DGAP_A;

alter table T_DGAP_DATA_IMPORT_FIELD
   drop constraint FK_T_DGAP_D_REFERENCE_T_DGAP_D;

alter table T_DGAP_DATA_IMPORT_TABLE
   drop constraint FK_T_DGAP_D_REFERENCE_T_DGAP_R;

alter table T_DGAP_RESOURCE
   drop constraint FK_T_DGAP_R_REFERENCE_T_DGAP_R;

alter table T_DGAP_RESOURCE_APPLICATION
   drop constraint FK_T_DGAP_R_REFERENCE_T_DGAP_R;

alter table T_DGAP_ROLE_RESOURCE
   drop constraint FK_T_DGAP_R_REFERENCE_T_DGAP_R;

alter table T_DGAP_WS_DAILY_STAT
   drop constraint FK_T_DGAP_W_REFERENCE_T_DGAP_R;

alter table T_DGAP_WS_DAILY_STAT
   drop constraint FK_T_DGAP_W_REFERENCE_T_DGAP_R;

alter table T_DGAP_WS_ERROR_LOG
   drop constraint FK_T_DGAP_W_REFERENCE_T_DGAP_R;

alter table T_DGAP_WS_ERROR_LOG
   drop constraint FK_T_DGAP_W_REFERENCE_T_DGAP_R;

alter table T_DGAP_WS_LOG
   drop constraint FK_T_DGAP_W_REFERENCE_T_DGAP_R;

alter table T_DGAP_WS_LOG
   drop constraint FK_T_DGAP_W_REFERENCE_T_DGAP_R;

alter table T_DGAP_ALERT_CONFIG
   drop primary key cascade;

drop table T_DGAP_ALERT_CONFIG cascade constraints;

alter table T_DGAP_ALERT_LOG
   drop primary key cascade;

drop table T_DGAP_ALERT_LOG cascade constraints;

alter table T_DGAP_ALERT_RECEIPT
   drop primary key cascade;

drop table T_DGAP_ALERT_RECEIPT cascade constraints;

alter table T_DGAP_DATA_IMPORT_FIELD
   drop primary key cascade;

drop table T_DGAP_DATA_IMPORT_FIELD cascade constraints;

alter table T_DGAP_DATA_IMPORT_TABLE
   drop primary key cascade;

drop table T_DGAP_DATA_IMPORT_TABLE cascade constraints;

alter table T_DGAP_RESOURCE
   drop primary key cascade;

drop table T_DGAP_RESOURCE cascade constraints;

alter table T_DGAP_RESOURCE_APPLICATION
   drop unique (CALLER_TOKEN) cascade;

alter table T_DGAP_RESOURCE_APPLICATION
   drop primary key cascade;

drop table T_DGAP_RESOURCE_APPLICATION cascade constraints;

alter table T_DGAP_RESOURCE_DIRECTORY
   drop unique (NAME) cascade;

alter table T_DGAP_RESOURCE_DIRECTORY
   drop primary key cascade;

drop table T_DGAP_RESOURCE_DIRECTORY cascade constraints;

alter table T_DGAP_ROLE_RESOURCE
   drop primary key cascade;

drop table T_DGAP_ROLE_RESOURCE cascade constraints;

alter table T_DGAP_WS_DAILY_STAT
   drop unique (CALLER_TOKEN) cascade;

alter table T_DGAP_WS_DAILY_STAT
   drop primary key cascade;

drop table T_DGAP_WS_DAILY_STAT cascade constraints;

alter table T_DGAP_WS_ERROR_LOG
   drop unique (CALLER_TOKEN) cascade;

alter table T_DGAP_WS_ERROR_LOG
   drop primary key cascade;

drop table T_DGAP_WS_ERROR_LOG cascade constraints;

alter table T_DGAP_WS_LOG
   drop unique (CALLER_TOKEN) cascade;

alter table T_DGAP_WS_LOG
   drop primary key cascade;

drop table T_DGAP_WS_LOG cascade constraints;

drop table T_GDAP_TB_RESOUCE_CONFIG cascade constraints;

/*==============================================================*/
/* Table: T_DGAP_ALERT_CONFIG                                   */
/*==============================================================*/
create table T_DGAP_ALERT_CONFIG 
(
   ID                   VARCHAR2(64 char)         not null comment '非空，无默认值',
   ALERT_TYPE           VARCHAR2(10 char)         not null comment '非空，无默认值',
   TARGET_ID            VARCHAR2(20 char)         not null comment '非空，无默认值',
   DESCRIPTION          VARCHAR2(100 char)        not null comment '非空，无默认值',
   THRESHOLD            NUMBER               not null comment '非空，无默认值',
   NEED_SEND_ALERT      VARCHAR2(1 char)          default 'Y' not null comment '非空，默认值为Y （可填的Y或者N）',
   CREATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_DATE          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   UPDATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   UPDATE_TIME          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   DEL_FLAG             VARCHAR2(10 char)         default 'N' not null comment '非空，默认值为N
            N: 未删除
            Y: 已删除
            ',
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

comment on table T_DGAP_ALERT_CONFIG is
'配置需要监控的预警项，包括预警目标、类型、状态等内容';

comment on column T_DGAP_ALERT_CONFIG.ID is
'非空，无默认值';

comment on column T_DGAP_ALERT_CONFIG.ALERT_TYPE is
'非空，无默认值';

comment on column T_DGAP_ALERT_CONFIG.TARGET_ID is
'非空，无默认值';

comment on column T_DGAP_ALERT_CONFIG.DESCRIPTION is
'非空，无默认值';

comment on column T_DGAP_ALERT_CONFIG.THRESHOLD is
'非空，无默认值';

comment on column T_DGAP_ALERT_CONFIG.NEED_SEND_ALERT is
'非空，默认值为Y （可填的Y或者N）';

comment on column T_DGAP_ALERT_CONFIG.CREATE_BY is
'非空，无默认值';

comment on column T_DGAP_ALERT_CONFIG.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_DGAP_ALERT_CONFIG.UPDATE_BY is
'非空，无默认值';

comment on column T_DGAP_ALERT_CONFIG.UPDATE_TIME is
'非空，默认值当前时间';

comment on column T_DGAP_ALERT_CONFIG.DEL_FLAG is
'非空，默认值为N
N: 未删除
Y: 已删除
';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD1 is
'预留字段1';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD2 is
'预留字段2';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD3 is
'预留字段3';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD4 is
'预留字段4';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD5 is
'预留字段5';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD6 is
'预留字段6';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD7 is
'预留字段7';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD8 is
'预留字段8';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD9 is
'预留字段9';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD10 is
'预留字段10';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD11 is
'预留字段11';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD12 is
'预留字段12';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD13 is
'预留字段13';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD14 is
'预留字段14';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD15 is
'预留字段15';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD16 is
'预留字段16';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD17 is
'预留字段17';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD18 is
'预留字段18';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD19 is
'预留字段19';

comment on column T_DGAP_ALERT_CONFIG.RESERVED_FIELD20 is
'预留字段20';

alter table T_DGAP_ALERT_CONFIG
   add constraint PK_T_DGAP_ALERT_CONFIG primary key (ID);

/*==============================================================*/
/* Table: T_DGAP_ALERT_LOG                                      */
/*==============================================================*/
create table T_DGAP_ALERT_LOG 
(
   ID                   VARCHAR2(64 char)         not null comment '非空，无默认值',
   ALERT_CONFIG_ID      VARCHAR2(64 char)         not null comment '非空，无默认值
            预警配置表外键
            ',
   TARGET_ID            VARCHAR2(64 char)         not null comment '非空，无默认值
            资源表外键
            ',
   DESCRIPTION          VARCHAR2(100 char)        not null comment '非空，无默认值',
   "ALERT _DATE"        DATE                 not null comment '非空，无默认值',
   SOLVE_DATE           DATE                 comment '可为空',
   SOLVE_USER_ID        VARCHAR2(64 char)         comment '可为空',
   SOLVE_STATUS         VARCHAR2(10 char)         comment '可为空',
   CREATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_DATE          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   UPDATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   UPDATE_TIME          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   DEL_FLAG             VARCHAR2(10 char)         default 'N' not null comment '非空，默认值为N
            N: 未删除
            Y: 已删除
            ',
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

comment on table T_DGAP_ALERT_LOG is
'记录预警日志';

comment on column T_DGAP_ALERT_LOG.ID is
'非空，无默认值';

comment on column T_DGAP_ALERT_LOG.ALERT_CONFIG_ID is
'非空，无默认值
预警配置表外键
';

comment on column T_DGAP_ALERT_LOG.TARGET_ID is
'非空，无默认值
资源表外键
';

comment on column T_DGAP_ALERT_LOG.DESCRIPTION is
'非空，无默认值';

comment on column T_DGAP_ALERT_LOG."ALERT _DATE" is
'非空，无默认值';

comment on column T_DGAP_ALERT_LOG.SOLVE_DATE is
'可为空';

comment on column T_DGAP_ALERT_LOG.SOLVE_USER_ID is
'可为空';

comment on column T_DGAP_ALERT_LOG.SOLVE_STATUS is
'可为空';

comment on column T_DGAP_ALERT_LOG.CREATE_BY is
'非空，无默认值';

comment on column T_DGAP_ALERT_LOG.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_DGAP_ALERT_LOG.UPDATE_BY is
'非空，无默认值';

comment on column T_DGAP_ALERT_LOG.UPDATE_TIME is
'非空，默认值当前时间';

comment on column T_DGAP_ALERT_LOG.DEL_FLAG is
'非空，默认值为N
N: 未删除
Y: 已删除
';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD1 is
'预留字段1';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD2 is
'预留字段2';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD3 is
'预留字段3';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD4 is
'预留字段4';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD5 is
'预留字段5';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD6 is
'预留字段6';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD7 is
'预留字段7';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD8 is
'预留字段8';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD9 is
'预留字段9';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD10 is
'预留字段10';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD11 is
'预留字段11';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD12 is
'预留字段12';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD13 is
'预留字段13';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD14 is
'预留字段14';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD15 is
'预留字段15';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD16 is
'预留字段16';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD17 is
'预留字段17';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD18 is
'预留字段18';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD19 is
'预留字段19';

comment on column T_DGAP_ALERT_LOG.RESERVED_FIELD20 is
'预留字段20';

alter table T_DGAP_ALERT_LOG
   add constraint PK_T_DGAP_ALERT_LOG primary key (ID);

/*==============================================================*/
/* Table: T_DGAP_ALERT_RECEIPT                                  */
/*==============================================================*/
create table T_DGAP_ALERT_RECEIPT 
(
   ID                   VARCHAR2(64 char)         not null comment '非空无默认值',
   ALERT_ID             VARCHAR2(64 char)         not null comment '非空，无默认值
            预警配置表外键
            ',
   RECEIPT_BY           VARCHAR2(64 char)         not null comment '非空无默认值',
   CREATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_DATE          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   UPDATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   UPDATE_TIME          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   DEL_FLAG             VARCHAR2(10 char)         default 'N' not null comment '非空，默认值为N
            N: 未删除
            Y: 已删除
            ',
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

comment on table T_DGAP_ALERT_RECEIPT is
'预警配置全部接收者的表';

comment on column T_DGAP_ALERT_RECEIPT.ID is
'非空无默认值';

comment on column T_DGAP_ALERT_RECEIPT.ALERT_ID is
'非空，无默认值
预警配置表外键
';

comment on column T_DGAP_ALERT_RECEIPT.RECEIPT_BY is
'非空无默认值';

comment on column T_DGAP_ALERT_RECEIPT.CREATE_BY is
'非空，无默认值';

comment on column T_DGAP_ALERT_RECEIPT.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_DGAP_ALERT_RECEIPT.UPDATE_BY is
'非空，无默认值';

comment on column T_DGAP_ALERT_RECEIPT.UPDATE_TIME is
'非空，默认值当前时间';

comment on column T_DGAP_ALERT_RECEIPT.DEL_FLAG is
'非空，默认值为N
N: 未删除
Y: 已删除
';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD1 is
'预留字段1';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD2 is
'预留字段2';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD3 is
'预留字段3';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD4 is
'预留字段4';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD5 is
'预留字段5';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD6 is
'预留字段6';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD7 is
'预留字段7';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD8 is
'预留字段8';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD9 is
'预留字段9';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD10 is
'预留字段10';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD11 is
'预留字段11';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD12 is
'预留字段12';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD13 is
'预留字段13';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD14 is
'预留字段14';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD15 is
'预留字段15';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD16 is
'预留字段16';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD17 is
'预留字段17';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD18 is
'预留字段18';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD19 is
'预留字段19';

comment on column T_DGAP_ALERT_RECEIPT.RESERVED_FIELD20 is
'预留字段20';

alter table T_DGAP_ALERT_RECEIPT
   add constraint PK_T_DGAP_ALERT_RECEIPT primary key (ID);

/*==============================================================*/
/* Table: T_DGAP_DATA_IMPORT_FIELD                              */
/*==============================================================*/
create table T_DGAP_DATA_IMPORT_FIELD 
(
   ID                   VARCHAR(64 char)          not null,
   CHINESE_NAME         VARCHAR(20 char)          comment '中文字段名',
   ENGLISH_NAME         VARCHAR(20 char)          comment '英文字段名',
   TYPE                 VARCHAR(64 char)          comment '字段类型',
   LEN                  VARCHAR(64 char)          comment '字段长度',
   DATA_IMPORT_TABLE_ID VARCHAR(64 char)          comment '对应表ID',
   CREATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_DATE          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   UPDATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   UPDATE_TIME          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   DEL_FLAG             VARCHAR2(10 char)         default 'N' not null comment '非空，默认值为N
            N: 未删除
            Y: 已删除
            ',
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

comment on table T_DGAP_DATA_IMPORT_FIELD is
'用于存放表中的所有字段的中文名和英文名';

comment on column T_DGAP_DATA_IMPORT_FIELD.ID is
'ID';

comment on column T_DGAP_DATA_IMPORT_FIELD.CHINESE_NAME is
'中文字段名';

comment on column T_DGAP_DATA_IMPORT_FIELD.ENGLISH_NAME is
'英文字段名';

comment on column T_DGAP_DATA_IMPORT_FIELD.TYPE is
'字段类型';

comment on column T_DGAP_DATA_IMPORT_FIELD.LEN is
'字段长度';

comment on column T_DGAP_DATA_IMPORT_FIELD.DATA_IMPORT_TABLE_ID is
'对应表ID';

comment on column T_DGAP_DATA_IMPORT_FIELD.CREATE_BY is
'非空，无默认值';

comment on column T_DGAP_DATA_IMPORT_FIELD.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_DGAP_DATA_IMPORT_FIELD.UPDATE_BY is
'非空，无默认值';

comment on column T_DGAP_DATA_IMPORT_FIELD.UPDATE_TIME is
'非空，默认值当前时间';

comment on column T_DGAP_DATA_IMPORT_FIELD.DEL_FLAG is
'非空，默认值为N
N: 未删除
Y: 已删除
';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD1 is
'预留字段1';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD2 is
'预留字段2';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD3 is
'预留字段3';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD4 is
'预留字段4';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD5 is
'预留字段5';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD6 is
'预留字段6';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD7 is
'预留字段7';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD8 is
'预留字段8';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD9 is
'预留字段9';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD10 is
'预留字段10';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD11 is
'预留字段11';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD12 is
'预留字段12';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD13 is
'预留字段13';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD14 is
'预留字段14';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD15 is
'预留字段15';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD16 is
'预留字段16';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD17 is
'预留字段17';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD18 is
'预留字段18';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD19 is
'预留字段19';

comment on column T_DGAP_DATA_IMPORT_FIELD.RESERVED_FIELD20 is
'预留字段20';

alter table T_DGAP_DATA_IMPORT_FIELD
   add constraint PK_T_DGAP_DATA_IMPORT_FIELD primary key (ID);

/*==============================================================*/
/* Table: T_DGAP_DATA_IMPORT_TABLE                              */
/*==============================================================*/
create table T_DGAP_DATA_IMPORT_TABLE 
(
   ID                   VARCHAR(64 char)          not null,
   CHINESE_NAME         VARCHAR(20 char)          comment '中文表名',
   ENGLISH_NAME         VARCHAR(20 char)          comment '英文表名',
   RESOURCE_ID          VARCHAR(64 char)          comment '资源ID',
   STATUS               VARCHAR(10 char)          comment '''NE'':表不存在，''AE'':表存在',
   CREATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_DATE          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   UPDATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   UPDATE_TIME          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   DEL_FLAG             VARCHAR2(10 char)         default 'N' not null comment '非空，默认值为N
            N: 未删除
            Y: 已删除
            ',
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

comment on table T_DGAP_DATA_IMPORT_TABLE is
'用与存放库中的所有表的中文表名和英文表名';

comment on column T_DGAP_DATA_IMPORT_TABLE.ID is
'ID';

comment on column T_DGAP_DATA_IMPORT_TABLE.CHINESE_NAME is
'中文表名';

comment on column T_DGAP_DATA_IMPORT_TABLE.ENGLISH_NAME is
'英文表名';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESOURCE_ID is
'资源ID';

comment on column T_DGAP_DATA_IMPORT_TABLE.STATUS is
'''NE'':表不存在，''AE'':表存在';

comment on column T_DGAP_DATA_IMPORT_TABLE.CREATE_BY is
'非空，无默认值';

comment on column T_DGAP_DATA_IMPORT_TABLE.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_DGAP_DATA_IMPORT_TABLE.UPDATE_BY is
'非空，无默认值';

comment on column T_DGAP_DATA_IMPORT_TABLE.UPDATE_TIME is
'非空，默认值当前时间';

comment on column T_DGAP_DATA_IMPORT_TABLE.DEL_FLAG is
'非空，默认值为N
N: 未删除
Y: 已删除
';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD1 is
'预留字段1';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD2 is
'预留字段2';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD3 is
'预留字段3';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD4 is
'预留字段4';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD5 is
'预留字段5';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD6 is
'预留字段6';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD7 is
'预留字段7';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD8 is
'预留字段8';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD9 is
'预留字段9';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD10 is
'预留字段10';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD11 is
'预留字段11';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD12 is
'预留字段12';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD13 is
'预留字段13';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD14 is
'预留字段14';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD15 is
'预留字段15';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD16 is
'预留字段16';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD17 is
'预留字段17';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD18 is
'预留字段18';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD19 is
'预留字段19';

comment on column T_DGAP_DATA_IMPORT_TABLE.RESERVED_FIELD20 is
'预留字段20';

alter table T_DGAP_DATA_IMPORT_TABLE
   add constraint PK_T_DGAP_DATA_IMPORT_TABLE primary key (ID);

/*==============================================================*/
/* Table: T_DGAP_RESOURCE                                       */
/*==============================================================*/
create table T_DGAP_RESOURCE 
(
   ID                   VARCHAR2(64 char)         not null comment '非空，无默认值',
   NAME                 VARCHAR2(30 char)         not null comment '非空，无默认值，唯一约束',
   TYPE                 VARCHAR2(10 char)         not null comment '非空，无默认值 WS: webservcie',
   DIRECTORY_ID         VARCHAR2(50 char)         not null comment '非空，无默认值
            资源目录表外键
            ',
   DESCRIPTION          VARCHAR2(300 char)        not null comment '非空，无默认值',
   WSDL_URL             VARCHAR2(100 char)        not null comment '非空，无默认值
            说明webservice如何使用
            ',
   FLAG                 VARCHAR2(1 char)          default 'Y' not null comment '非空，默认为Y
            N: 不启用 
            Y：启用
            ',
   CREATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_DATE          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   UPDATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   UPDATE_TIME          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   DEL_FLAG             VARCHAR2(10 char)         default 'N' not null comment '非空，默认值为N
            N: 未删除
            Y: 已删除
            ',
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

comment on table T_DGAP_RESOURCE is
'记录资源的本身属性及相关描述信息，包括资源名称、资源类型、所属资源目录等信息';

comment on column T_DGAP_RESOURCE.ID is
'非空，无默认值';

comment on column T_DGAP_RESOURCE.NAME is
'非空，无默认值，唯一约束';

comment on column T_DGAP_RESOURCE.TYPE is
'非空，无默认值 WS: webservcie';

comment on column T_DGAP_RESOURCE.DIRECTORY_ID is
'非空，无默认值
资源目录表外键
';

comment on column T_DGAP_RESOURCE.DESCRIPTION is
'非空，无默认值';

comment on column T_DGAP_RESOURCE.WSDL_URL is
'非空，无默认值
说明webservice如何使用
';

comment on column T_DGAP_RESOURCE.FLAG is
'非空，默认为Y
N: 不启用 
Y：启用
';

comment on column T_DGAP_RESOURCE.CREATE_BY is
'非空，无默认值';

comment on column T_DGAP_RESOURCE.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_DGAP_RESOURCE.UPDATE_BY is
'非空，无默认值';

comment on column T_DGAP_RESOURCE.UPDATE_TIME is
'非空，默认值当前时间';

comment on column T_DGAP_RESOURCE.DEL_FLAG is
'非空，默认值为N
N: 未删除
Y: 已删除
';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD1 is
'预留字段1';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD2 is
'预留字段2';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD3 is
'预留字段3';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD4 is
'预留字段4';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD5 is
'预留字段5';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD6 is
'预留字段6';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD7 is
'预留字段7';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD8 is
'预留字段8';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD9 is
'预留字段9';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD10 is
'预留字段10';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD11 is
'预留字段11';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD12 is
'预留字段12';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD13 is
'预留字段13';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD14 is
'预留字段14';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD15 is
'预留字段15';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD16 is
'预留字段16';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD17 is
'预留字段17';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD18 is
'预留字段18';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD19 is
'预留字段19';

comment on column T_DGAP_RESOURCE.RESERVED_FIELD20 is
'预留字段20';

alter table T_DGAP_RESOURCE
   add constraint PK_T_DGAP_RESOURCE primary key (ID);

/*==============================================================*/
/* Table: T_DGAP_RESOURCE_APPLICATION                           */
/*==============================================================*/
create table T_DGAP_RESOURCE_APPLICATION 
(
   ID                   VARCHAR2(64 char)         not null comment '非空，无默认值',
   RESOURCE_ID          VARCHAR2(64 char)         not null comment '非空，无默认值
            资源表外键
            ',
   USER_ID              VARCHAR2(64 char)         not null comment '非空，无默认值',
   STATUS               VARCHAR2(10 char)         default 'NEW' not null comment '非空，默认值NEW
            NEW: 待审批
            APPR：审批通过
            DENY：审批打回
            ',
   EFFECTIVE_DATE       DATE                 not null comment '非空，无默认值',
   EXPIRE_DATE          DATE                 comment '允许为空
            NULL:为空表示不失效
            ',
   REASON               VARCHAR2(200 char)        not null comment '非空，无默认值',
   AUDIT_USER_ID        VARCHAR2(64 char)         comment '允许为空',
   PASS_OR_NOT          VARCHAR2(10 char)         comment '允许为空
            为空表示未审批
            N: 未审批通过
            Y: 审批通过
            ',
   APPROVAL             VARCHAR2(50 char)         comment '允许为空
            审批通过的可以不用填写审批意见
            审批不通过的需要填写审批意见
            ',
   CONTACT_NUMBER       VARCHAR2(20 char)         comment '可为空',
   CALLER_TOKEN         VARCHAR2(20 char)         not null comment '非空，无默认值
            唯一性约束
            ',
   CREATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_DATE          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   UPDATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   UPDATE_TIME          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   DEL_FLAG             VARCHAR2(10 char)         default 'N' not null comment '非空，默认值为N
            N: 未删除
            Y: 已删除
            ',
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

comment on table T_DGAP_RESOURCE_APPLICATION is
'用户需要申请的资源将记录在这个表中';

comment on column T_DGAP_RESOURCE_APPLICATION.ID is
'非空，无默认值';

comment on column T_DGAP_RESOURCE_APPLICATION.RESOURCE_ID is
'非空，无默认值
资源表外键
';

comment on column T_DGAP_RESOURCE_APPLICATION.USER_ID is
'非空，无默认值';

comment on column T_DGAP_RESOURCE_APPLICATION.STATUS is
'非空，默认值NEW
NEW: 待审批
APPR：审批通过
DENY：审批打回
';

comment on column T_DGAP_RESOURCE_APPLICATION.EFFECTIVE_DATE is
'非空，无默认值';

comment on column T_DGAP_RESOURCE_APPLICATION.EXPIRE_DATE is
'允许为空
NULL:为空表示不失效
';

comment on column T_DGAP_RESOURCE_APPLICATION.REASON is
'非空，无默认值';

comment on column T_DGAP_RESOURCE_APPLICATION.AUDIT_USER_ID is
'允许为空';

comment on column T_DGAP_RESOURCE_APPLICATION.PASS_OR_NOT is
'允许为空
为空表示未审批
N: 未审批通过
Y: 审批通过
';

comment on column T_DGAP_RESOURCE_APPLICATION.APPROVAL is
'允许为空
审批通过的可以不用填写审批意见
审批不通过的需要填写审批意见
';

comment on column T_DGAP_RESOURCE_APPLICATION.CONTACT_NUMBER is
'可为空';

comment on column T_DGAP_RESOURCE_APPLICATION.CALLER_TOKEN is
'非空，无默认值
唯一性约束
';

comment on column T_DGAP_RESOURCE_APPLICATION.CREATE_BY is
'非空，无默认值';

comment on column T_DGAP_RESOURCE_APPLICATION.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_DGAP_RESOURCE_APPLICATION.UPDATE_BY is
'非空，无默认值';

comment on column T_DGAP_RESOURCE_APPLICATION.UPDATE_TIME is
'非空，默认值当前时间';

comment on column T_DGAP_RESOURCE_APPLICATION.DEL_FLAG is
'非空，默认值为N
N: 未删除
Y: 已删除
';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD1 is
'预留字段1';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD2 is
'预留字段2';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD3 is
'预留字段3';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD4 is
'预留字段4';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD5 is
'预留字段5';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD6 is
'预留字段6';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD7 is
'预留字段7';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD8 is
'预留字段8';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD9 is
'预留字段9';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD10 is
'预留字段10';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD11 is
'预留字段11';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD12 is
'预留字段12';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD13 is
'预留字段13';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD14 is
'预留字段14';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD15 is
'预留字段15';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD16 is
'预留字段16';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD17 is
'预留字段17';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD18 is
'预留字段18';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD19 is
'预留字段19';

comment on column T_DGAP_RESOURCE_APPLICATION.RESERVED_FIELD20 is
'预留字段20';

alter table T_DGAP_RESOURCE_APPLICATION
   add constraint PK_T_DGAP_RESOURCE_APPLICATION primary key (ID);

alter table T_DGAP_RESOURCE_APPLICATION
   add constraint AK_CALLER_TOKEN_T_DGAP_R unique (CALLER_TOKEN);

/*==============================================================*/
/* Table: T_DGAP_RESOURCE_DIRECTORY                             */
/*==============================================================*/
create table T_DGAP_RESOURCE_DIRECTORY 
(
   ID                   VARCHAR2(64 char)         not null comment '非空，无默认值',
   NAME                 VARCHAR2(32 char)         not null comment '非空，无默认值，唯一约束',
   DESCRIPTION          VARCHAR2(200 char)        comment '允许为空',
   CREATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_DATE          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   UPDATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   UPDATE_TIME          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   DEL_FLAG             VARCHAR2(10 char)         default 'N' not null comment '非空，默认值为N
            N: 未删除
            Y: 已删除
            ',
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

comment on table T_DGAP_RESOURCE_DIRECTORY is
'为资源的主题归类目录，不同的资源放在相关的目录下';

comment on column T_DGAP_RESOURCE_DIRECTORY.ID is
'非空，无默认值';

comment on column T_DGAP_RESOURCE_DIRECTORY.NAME is
'非空，无默认值，唯一约束';

comment on column T_DGAP_RESOURCE_DIRECTORY.DESCRIPTION is
'允许为空';

comment on column T_DGAP_RESOURCE_DIRECTORY.CREATE_BY is
'非空，无默认值';

comment on column T_DGAP_RESOURCE_DIRECTORY.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_DGAP_RESOURCE_DIRECTORY.UPDATE_BY is
'非空，无默认值';

comment on column T_DGAP_RESOURCE_DIRECTORY.UPDATE_TIME is
'非空，默认值当前时间';

comment on column T_DGAP_RESOURCE_DIRECTORY.DEL_FLAG is
'非空，默认值为N
N: 未删除
Y: 已删除
';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD1 is
'预留字段1';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD2 is
'预留字段2';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD3 is
'预留字段3';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD4 is
'预留字段4';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD5 is
'预留字段5';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD6 is
'预留字段6';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD7 is
'预留字段7';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD8 is
'预留字段8';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD9 is
'预留字段9';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD10 is
'预留字段10';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD11 is
'预留字段11';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD12 is
'预留字段12';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD13 is
'预留字段13';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD14 is
'预留字段14';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD15 is
'预留字段15';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD16 is
'预留字段16';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD17 is
'预留字段17';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD18 is
'预留字段18';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD19 is
'预留字段19';

comment on column T_DGAP_RESOURCE_DIRECTORY.RESERVED_FIELD20 is
'预留字段20';

alter table T_DGAP_RESOURCE_DIRECTORY
   add constraint PK_T_DGAP_RESOURCE_DIRECTORY primary key (ID);

alter table T_DGAP_RESOURCE_DIRECTORY
   add constraint AK_NAME_T_DGAP_R unique (NAME);

/*==============================================================*/
/* Table: T_DGAP_ROLE_RESOURCE                                  */
/*==============================================================*/
create table T_DGAP_ROLE_RESOURCE 
(
   ID                   VARCHAR2(64 char)         not null comment '非空，无默认值',
   ROLE_ID              VARCHAR2(64 char)         not null comment '非空，无默认值',
   RESOURCE_ID          VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_DATE          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   UPDATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   UPDATE_TIME          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   DEL_FLAG             VARCHAR2(10 char)         default 'N' not null comment '非空，默认值为N
            N: 未删除
            Y: 已删除
            ',
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

comment on table T_DGAP_ROLE_RESOURCE is
'角色与资源关联关系';

comment on column T_DGAP_ROLE_RESOURCE.ID is
'非空，无默认值';

comment on column T_DGAP_ROLE_RESOURCE.ROLE_ID is
'非空，无默认值';

comment on column T_DGAP_ROLE_RESOURCE.RESOURCE_ID is
'非空，无默认值';

comment on column T_DGAP_ROLE_RESOURCE.CREATE_BY is
'非空，无默认值';

comment on column T_DGAP_ROLE_RESOURCE.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_DGAP_ROLE_RESOURCE.UPDATE_BY is
'非空，无默认值';

comment on column T_DGAP_ROLE_RESOURCE.UPDATE_TIME is
'非空，默认值当前时间';

comment on column T_DGAP_ROLE_RESOURCE.DEL_FLAG is
'非空，默认值为N
N: 未删除
Y: 已删除
';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD1 is
'预留字段1';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD2 is
'预留字段2';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD3 is
'预留字段3';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD4 is
'预留字段4';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD5 is
'预留字段5';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD6 is
'预留字段6';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD7 is
'预留字段7';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD8 is
'预留字段8';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD9 is
'预留字段9';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD10 is
'预留字段10';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD11 is
'预留字段11';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD12 is
'预留字段12';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD13 is
'预留字段13';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD14 is
'预留字段14';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD15 is
'预留字段15';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD16 is
'预留字段16';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD17 is
'预留字段17';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD18 is
'预留字段18';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD19 is
'预留字段19';

comment on column T_DGAP_ROLE_RESOURCE.RESERVED_FIELD20 is
'预留字段20';

alter table T_DGAP_ROLE_RESOURCE
   add constraint PK_T_DGAP_ROLE_RESOURCE primary key (ID);

/*==============================================================*/
/* Table: T_DGAP_WS_DAILY_STAT                                  */
/*==============================================================*/
create table T_DGAP_WS_DAILY_STAT 
(
   ID                   VARCHAR2(64 char)         not null comment '非空，无默认值',
   STAT_DATE            DATE                 not null comment '非空，无默认值',
   RESOURCE_DIR         VARCHAR2(64 char)         not null comment '非空，无默认值
            资源目录表外键
            ',
   WEB_SERVICE_ID       VARCHAR2(64 char)         not null comment '非空，无默认值
            资源表外键
            ',
   WEB_SERVICE_NAME     VARCHAR2(50 char)         not null comment '非空，无默认值',
   BUSSNESS_NAME        VARCHAR2(50 char)         not null comment '非空，无默认值',
   CALLER_TOKEN         VARCHAR2(20 char)         not null comment '非空，无默认值
            唯一约束
            ',
   SUCCESS_TIMES        INT                  not null comment '非空，无默认值',
   FAIL_TIMES           INT                  not null comment '非空，无默认值',
   AVG_TIMECOST         INT                  not null comment '非空，无默认值',
   CREATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_DATE          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   UPDATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   UPDATE_TIME          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   DEL_FLAG             VARCHAR2(10 char)         default 'N' not null comment '非空，默认值为N
            N: 未删除
            Y: 已删除
            ',
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

comment on table T_DGAP_WS_DAILY_STAT is
'统计每天的服务执行情况，包括成功、失败、平均耗时等信息';

comment on column T_DGAP_WS_DAILY_STAT.ID is
'非空，无默认值';

comment on column T_DGAP_WS_DAILY_STAT.STAT_DATE is
'非空，无默认值';

comment on column T_DGAP_WS_DAILY_STAT.RESOURCE_DIR is
'非空，无默认值
资源目录表外键
';

comment on column T_DGAP_WS_DAILY_STAT.WEB_SERVICE_ID is
'非空，无默认值
资源表外键
';

comment on column T_DGAP_WS_DAILY_STAT.WEB_SERVICE_NAME is
'非空，无默认值';

comment on column T_DGAP_WS_DAILY_STAT.BUSSNESS_NAME is
'非空，无默认值';

comment on column T_DGAP_WS_DAILY_STAT.CALLER_TOKEN is
'非空，无默认值
唯一约束
';

comment on column T_DGAP_WS_DAILY_STAT.SUCCESS_TIMES is
'非空，无默认值';

comment on column T_DGAP_WS_DAILY_STAT.FAIL_TIMES is
'非空，无默认值';

comment on column T_DGAP_WS_DAILY_STAT.AVG_TIMECOST is
'非空，无默认值';

comment on column T_DGAP_WS_DAILY_STAT.CREATE_BY is
'非空，无默认值';

comment on column T_DGAP_WS_DAILY_STAT.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_DGAP_WS_DAILY_STAT.UPDATE_BY is
'非空，无默认值';

comment on column T_DGAP_WS_DAILY_STAT.UPDATE_TIME is
'非空，默认值当前时间';

comment on column T_DGAP_WS_DAILY_STAT.DEL_FLAG is
'非空，默认值为N
N: 未删除
Y: 已删除
';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD1 is
'预留字段1';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD2 is
'预留字段2';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD3 is
'预留字段3';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD4 is
'预留字段4';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD5 is
'预留字段5';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD6 is
'预留字段6';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD7 is
'预留字段7';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD8 is
'预留字段8';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD9 is
'预留字段9';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD10 is
'预留字段10';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD11 is
'预留字段11';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD12 is
'预留字段12';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD13 is
'预留字段13';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD14 is
'预留字段14';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD15 is
'预留字段15';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD16 is
'预留字段16';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD17 is
'预留字段17';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD18 is
'预留字段18';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD19 is
'预留字段19';

comment on column T_DGAP_WS_DAILY_STAT.RESERVED_FIELD20 is
'预留字段20';

alter table T_DGAP_WS_DAILY_STAT
   add constraint PK_T_DGAP_WS_DAILY_STAT primary key (ID);

alter table T_DGAP_WS_DAILY_STAT
   add constraint AK_CALLER_TOKEN_T_DGAP_W unique (CALLER_TOKEN);

/*==============================================================*/
/* Table: T_DGAP_WS_ERROR_LOG                                   */
/*==============================================================*/
create table T_DGAP_WS_ERROR_LOG 
(
   ID                   VARCHAR2(64 char)         not null comment '非空，无默认值',
   RESOURCE_DIRECTORY_ID VARCHAR2(64 char)         not null comment '非空，无默认值
            资源目录表外键
            ',
   WEB_SERVICE_ID       VARCHAR2(64 char)         not null comment '非空，无默认值
            资源表外键
            ',
   WEB_SERVICE_NAME     VARCHAR2(50 char)         not null comment '非空，无默认值',
   BUSSNESS_NAME        VARCHAR2(50 char)         not null comment '非空，无默认值',
   CALLER_TOKEN         VARCHAR2(20 char)         not null comment '非空，无默认值
            唯一约束
            ',
   ERROR_TYPE           VARCHAR2(10 char)         not null comment '非空，无默认值',
   ERROR_DESC           VARCHAR2(100 char)        not null comment '非空，无默认值',
   ERROR_DATE           DATE                 not null comment '非空，默认值为当前时间',
   CREATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_DATE          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   UPDATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   UPDATE_TIME          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   DEL_FLAG             VARCHAR2(20 char)         default 'N' not null comment '非空，默认值为N
            N: 未删除
            Y: 已删除
            ',
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

comment on table T_DGAP_WS_ERROR_LOG is
'记录web服务出错信息日志';

comment on column T_DGAP_WS_ERROR_LOG.ID is
'非空，无默认值';

comment on column T_DGAP_WS_ERROR_LOG.RESOURCE_DIRECTORY_ID is
'非空，无默认值
资源目录表外键
';

comment on column T_DGAP_WS_ERROR_LOG.WEB_SERVICE_ID is
'非空，无默认值
资源表外键
';

comment on column T_DGAP_WS_ERROR_LOG.WEB_SERVICE_NAME is
'非空，无默认值';

comment on column T_DGAP_WS_ERROR_LOG.BUSSNESS_NAME is
'非空，无默认值';

comment on column T_DGAP_WS_ERROR_LOG.CALLER_TOKEN is
'非空，无默认值
唯一约束
';

comment on column T_DGAP_WS_ERROR_LOG.ERROR_TYPE is
'非空，无默认值';

comment on column T_DGAP_WS_ERROR_LOG.ERROR_DESC is
'非空，无默认值';

comment on column T_DGAP_WS_ERROR_LOG.ERROR_DATE is
'非空，默认值为当前时间';

comment on column T_DGAP_WS_ERROR_LOG.CREATE_BY is
'非空，无默认值';

comment on column T_DGAP_WS_ERROR_LOG.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_DGAP_WS_ERROR_LOG.UPDATE_BY is
'非空，无默认值';

comment on column T_DGAP_WS_ERROR_LOG.UPDATE_TIME is
'非空，默认值当前时间';

comment on column T_DGAP_WS_ERROR_LOG.DEL_FLAG is
'非空，默认值为N
N: 未删除
Y: 已删除
';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD1 is
'预留字段1';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD2 is
'预留字段2';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD3 is
'预留字段3';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD4 is
'预留字段4';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD5 is
'预留字段5';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD6 is
'预留字段6';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD7 is
'预留字段7';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD8 is
'预留字段8';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD9 is
'预留字段9';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD10 is
'预留字段10';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD11 is
'预留字段11';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD12 is
'预留字段12';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD13 is
'预留字段13';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD14 is
'预留字段14';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD15 is
'预留字段15';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD16 is
'预留字段16';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD17 is
'预留字段17';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD18 is
'预留字段18';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD19 is
'预留字段19';

comment on column T_DGAP_WS_ERROR_LOG.RESERVED_FIELD20 is
'预留字段20';

alter table T_DGAP_WS_ERROR_LOG
   add constraint PK_T_DGAP_WS_ERROR_LOG primary key (ID);

alter table T_DGAP_WS_ERROR_LOG
   add constraint AK_CALLER_TOKEN_T_DGAP_W unique (CALLER_TOKEN);

/*==============================================================*/
/* Table: T_DGAP_WS_LOG                                         */
/*==============================================================*/
create table T_DGAP_WS_LOG 
(
   ID                   VARCHAR2(64 char)         not null comment '非空，无默认值',
   RESOURCE_DIR         VARCHAR2(64 char)         not null comment '非空，无默认值
            资源目录表外键
            ',
   WEB_SERVICE_ID       VARCHAR2(64 char)         not null comment '非空，无默认值
            资源表外键
            ',
   WEB_SERVICE_NAME     VARCHAR2(50 char)         not null comment '非空，无默认值',
   METHOD_NAME          VARCHAR2(50 char)         not null comment '非空，无默认值',
   CALLER_TOKEN         VARCHAR2(20 char)         not null comment '非空，无默认值唯一性约束',
   ERROR_TYPE           VARCHAR2(2 char)          comment '允许为空',
   ERROR_DESC           VARCHAR2(100 char)        comment '允许为空',
   INVOKE_START_DATE    DATE                 not null comment '非空，无默认值',
   INVOKE_END_DATE      DATE                 comment '允许为空',
   ERROR_DATE           DATE                 comment '允许为空',
   CREATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   CREATE_DATE          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   UPDATE_BY            VARCHAR2(64 char)         not null comment '非空，无默认值',
   UPDATE_TIME          DATE                 default SYSDATE not null comment '非空，默认值当前时间',
   DEL_FLAG             VARCHAR2(10 char)         default 'N' not null comment '非空，默认值为N
            N: 未删除
            Y: 已删除
            ',
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

comment on table T_DGAP_WS_LOG is
'日志类，记录web服务的调用情况';

comment on column T_DGAP_WS_LOG.ID is
'非空，无默认值';

comment on column T_DGAP_WS_LOG.RESOURCE_DIR is
'非空，无默认值
资源目录表外键
';

comment on column T_DGAP_WS_LOG.WEB_SERVICE_ID is
'非空，无默认值
资源表外键
';

comment on column T_DGAP_WS_LOG.WEB_SERVICE_NAME is
'非空，无默认值';

comment on column T_DGAP_WS_LOG.METHOD_NAME is
'非空，无默认值';

comment on column T_DGAP_WS_LOG.CALLER_TOKEN is
'非空，无默认值唯一性约束';

comment on column T_DGAP_WS_LOG.ERROR_TYPE is
'允许为空';

comment on column T_DGAP_WS_LOG.ERROR_DESC is
'允许为空';

comment on column T_DGAP_WS_LOG.INVOKE_START_DATE is
'非空，无默认值';

comment on column T_DGAP_WS_LOG.INVOKE_END_DATE is
'允许为空';

comment on column T_DGAP_WS_LOG.ERROR_DATE is
'允许为空';

comment on column T_DGAP_WS_LOG.CREATE_BY is
'非空，无默认值';

comment on column T_DGAP_WS_LOG.CREATE_DATE is
'非空，默认值当前时间';

comment on column T_DGAP_WS_LOG.UPDATE_BY is
'非空，无默认值';

comment on column T_DGAP_WS_LOG.UPDATE_TIME is
'非空，默认值当前时间';

comment on column T_DGAP_WS_LOG.DEL_FLAG is
'非空，默认值为N
N: 未删除
Y: 已删除
';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD1 is
'预留字段1';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD2 is
'预留字段2';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD3 is
'预留字段3';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD4 is
'预留字段4';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD5 is
'预留字段5';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD6 is
'预留字段6';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD7 is
'预留字段7';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD8 is
'预留字段8';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD9 is
'预留字段9';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD10 is
'预留字段10';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD11 is
'预留字段11';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD12 is
'预留字段12';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD13 is
'预留字段13';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD14 is
'预留字段14';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD15 is
'预留字段15';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD16 is
'预留字段16';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD17 is
'预留字段17';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD18 is
'预留字段18';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD19 is
'预留字段19';

comment on column T_DGAP_WS_LOG.RESERVED_FIELD20 is
'预留字段20';

alter table T_DGAP_WS_LOG
   add constraint PK_T_DGAP_WS_LOG primary key (ID);

alter table T_DGAP_WS_LOG
   add constraint AK_CALLER_TOKEN
           _A_T_DGAP_W unique (CALLER_TOKEN);

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
   IS_CONDITION         CHAR                 comment 'y:可作为查询条件
            n:不作为查询条件'
);

comment on table T_GDAP_TB_RESOUCE_CONFIG is
'表类型资源表明字段配置表';

comment on column T_GDAP_TB_RESOUCE_CONFIG.ID is
'主键';

comment on column T_GDAP_TB_RESOUCE_CONFIG.RESOURCE_ID is
'资源ID';

comment on column T_GDAP_TB_RESOUCE_CONFIG.TABLE_NAME is
'表名';

comment on column T_GDAP_TB_RESOUCE_CONFIG.TABLE_CN_NAME is
'表中文名';

comment on column T_GDAP_TB_RESOUCE_CONFIG.FIELD_NAME is
'字段名';

comment on column T_GDAP_TB_RESOUCE_CONFIG.FIELD_CN_NAME is
'字段中文名';

comment on column T_GDAP_TB_RESOUCE_CONFIG.LENGTH is
'字段长度';

comment on column T_GDAP_TB_RESOUCE_CONFIG.TYPE is
'字段类型';

comment on column T_GDAP_TB_RESOUCE_CONFIG.IS_CONDITION is
'y:可作为查询条件
n:不作为查询条件';

alter table T_DGAP_ALERT_LOG
   add constraint FK_T_DGAP_A_REFERENCE_T_DGAP_A foreign key (ALERT_CONFIG_ID)
      references T_DGAP_ALERT_CONFIG (ID);

alter table T_DGAP_ALERT_LOG
   add constraint FK_T_DGAP_A_REFERENCE_T_DGAP_R foreign key (TARGET_ID)
      references T_DGAP_RESOURCE (ID);

alter table T_DGAP_ALERT_RECEIPT
   add constraint FK_T_DGAP_A_REFERENCE_T_DGAP_A foreign key (ALERT_ID)
      references T_DGAP_ALERT_CONFIG (ID);

alter table T_DGAP_DATA_IMPORT_FIELD
   add constraint FK_T_DGAP_D_REFERENCE_T_DGAP_D foreign key (DATA_IMPORT_TABLE_ID)
      references T_DGAP_DATA_IMPORT_TABLE (ID);

alter table T_DGAP_DATA_IMPORT_TABLE
   add constraint FK_T_DGAP_D_REFERENCE_T_DGAP_R foreign key (RESOURCE_ID)
      references T_DGAP_RESOURCE (ID);

alter table T_DGAP_RESOURCE
   add constraint FK_T_DGAP_R_REFERENCE_T_DGAP_R foreign key (DIRECTORY_ID)
      references T_DGAP_RESOURCE_DIRECTORY (ID);

alter table T_DGAP_RESOURCE_APPLICATION
   add constraint FK_T_DGAP_R_REFERENCE_T_DGAP_R foreign key (RESOURCE_ID)
      references T_DGAP_RESOURCE (ID);

alter table T_DGAP_ROLE_RESOURCE
   add constraint FK_T_DGAP_R_REFERENCE_T_DGAP_R foreign key (RESOURCE_ID)
      references T_DGAP_RESOURCE (ID);

alter table T_DGAP_WS_DAILY_STAT
   add constraint FK_T_DGAP_W_REFERENCE_T_DGAP_R foreign key (RESOURCE_DIR)
      references T_DGAP_RESOURCE_DIRECTORY (ID);

alter table T_DGAP_WS_DAILY_STAT
   add constraint FK_T_DGAP_W_REFERENCE_T_DGAP_R foreign key (WEB_SERVICE_ID)
      references T_DGAP_RESOURCE (ID);

alter table T_DGAP_WS_ERROR_LOG
   add constraint FK_T_DGAP_W_REFERENCE_T_DGAP_R foreign key (RESOURCE_DIRECTORY_ID)
      references T_DGAP_RESOURCE_DIRECTORY (ID);

alter table T_DGAP_WS_ERROR_LOG
   add constraint FK_T_DGAP_W_REFERENCE_T_DGAP_R foreign key (WEB_SERVICE_ID)
      references T_DGAP_RESOURCE (ID);

alter table T_DGAP_WS_LOG
   add constraint FK_T_DGAP_W_REFERENCE_T_DGAP_R foreign key (RESOURCE_DIR)
      references T_DGAP_RESOURCE_DIRECTORY (ID);

alter table T_DGAP_WS_LOG
   add constraint FK_T_DGAP_W_REFERENCE_T_DGAP_R foreign key (WEB_SERVICE_ID)
      references T_DGAP_RESOURCE (ID);

