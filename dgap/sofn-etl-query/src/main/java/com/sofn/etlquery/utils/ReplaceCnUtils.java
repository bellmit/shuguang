package com.sofn.etlquery.utils;

import java.util.HashMap;
import java.util.Map;

public class ReplaceCnUtils {
    //表名对应中文注释
    public static Map<String, String> tableNameCn;
    //字段对应中文注释
    public static Map<String, String> tableFieldNameCn;


    static {
        tableNameCn = getTableName();
        tableFieldNameCn=getTableFieldNameCn();
    }


    public static Map getTableName(){
        Map data =new HashMap();
        data.put("SYS_ARGI_PRODUCT","农产品分类");
        data.put("SYS_ORGANIZATION","组织机构");
        data.put("SYS_TEST_STANDARD","检测标准");
        data.put("TTS_SCLTXXCJ_COMPLAINT_V2","投诉举报信息");
        return data;
    }
    public static Map getTableFieldNameCn() {
        Map data = new HashMap();
        //农产品分类
        data.put("ALIAS", "别名");
        data.put("CREATE_BY", "创建人ID");
        data.put("CREATE_TIME", "创建时间");
        data.put("DEL_FLAG", "删除标识");
        data.put("DESCRIPTION", "说明");
        data.put("ENNAME", "英文名");
        data.put("GB_CODE", "对应的GB/T 7635.1代码");
        data.put("ID", "ID");
        data.put("NAME", "名称");
        data.put("PARENT_ID", "上级id");
        data.put("PARENT_NAME", "");
        data.put("PRODUCT_CODE", "产品代码");
        data.put("RESERVED_FIELD1", "预留字段1");
        data.put("RESERVED_FIELD2", "预留字段2");
        data.put("SLAUGHTER_STATUS", "屠宰标识ID（畜牧业产品");
        data.put("TYPE_ID", "农产品分类类型");
        data.put("UPDATE_BY", "修改人ID");
        data.put("UPDATE_TIME", "修改时间");
        data.put("USE_CODE", "用途代码");

        //组织机构
        data.put("ORG_NAME","机构名称");
        data.put("ORG_TYPE","机构类型");
        data.put("ORG_LEVEL","机构等级");
        data.put("PRO","省");
        data.put("CITY","市");
        data.put("COUNTY","区/县");
        data.put("LEVEL_NAME","机构级别名");

        //检测标准表
//        data.put("CREATE_BY","创建人账户");
        data.put("REMARK","说明");
        data.put("STANDARD_CODE","标准号");
        data.put("STANDARD_NAME","标准名称");

        //投诉举报信息
        data.put("ACCEPTANCE","受理意见");
        data.put("ACC_ENTITY_IDCODE","受理主体身份码");
        data.put("ACC_TIME","受理时间");
        data.put("ACC_USER_IDCODE","受理主体用户码");
        data.put("AREA_ID","所属区域ID");
        data.put("ATTACHMENT_NAME","附件名称");
        data.put("ATTACHMENT_PATH","附件路径");
        data.put("BE_ENTITY_IDCODE","被投诉主体身份码");
        data.put("BE_USER_IDCODE","被投诉主体用户码");
        data.put("COMPLAINT_COP_NAME","投诉主体名称");
        data.put("COMPLAINT_ENT_NAME","被投诉主体名称");
        data.put("COMPLAINT_TIME","投诉时间");
        data.put("COMPLAINT_TITLE","投诉标题");
        data.put("CONTENT","投诉内容");
        data.put("ENTITY_IDCODE","来源标识");
        data.put("STATUS","投诉信息状态");
        data.put("TYPE","问题类型");
        data.put("TYPE_NAME","类型名称");
        data.put("USER_IDCODE","投诉主体用户码");
        data.put("ENTERPRISE_NAME","企业名称");

        return data;
    }

    //替换中文表名
    public static String replaceTableName(String tableName){
        for(Map.Entry<String, String> entry:tableNameCn.entrySet()){
            if(entry.getKey().equals(tableName)){
                tableName=entry.getValue();
            }
        }
        return tableName;
    }

    //替换中文字段
    public static String replaceTableField(String field){
        for(Map.Entry<String, String> entry:tableFieldNameCn.entrySet()){
            if(entry.getKey().equals(field)){
                field=entry.getValue();
            }
        }
        return field;
    }

}
