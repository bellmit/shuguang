package com.sofn.provider.dgap;

import com.alibaba.dubbo.container.Main;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.FastDFSUtil;

import javax.validation.constraints.Null;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by helong on 18-7-14.
 */
@DubboService(interfaceClass = TDgapDataToolProvider.class)
public class DataToolProviderImpl implements TDgapDataToolProvider{
    private static String driver = "oracle.jdbc.driver.OracleDriver"; //驱动


    public boolean copyTTSEntities(String sourceDataSource, String destDataSource){
        //TTS_SCLTXXCJ_REGISTER_V2
        int isExistence=0;//是否存在表
        isExistence = isExistTable(destDataSource, isExistence);
        if(isExistence==0){//若不存在则创建表
            createTabel(destDataSource);
        }
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        getSourceData(sourceDataSource, list);//取得源数据库的数据

        //开始向目标数据库导入更新数据
        Connection destConn=getConnection(destDataSource);
        PreparedStatement  pst = null;
        String sql="insert into TTS_SCLTXXCJ_REGISTER_V2( APPROVE_STATUS,ID,BUSINESS_OPERATION_END,BUSINESS_OPERATION_START,UPDATE_TIME,CREATE_TIME,APPROVE_TIME," +
                "REGISTER_TIME,LATITUDE,LONGITUDE,DEL_FLAG,UNIT,UNIT_NAME,UNIT_S,UNIT_X,UNIT_NAME_S,UNIT_NAME_X,CONTACT_EMAIL," +
                "CREATE_BY,UPDATE_BY,FAX_NUMBER,LEGAL_PHONE,CONTACT_PHONE,IDCODE,CREDIT_CODE,LEGAL_IDNUMBER,IS_LONG," +
                "ORGANIZATION_CERTIFICATEIMG," +
                "BUSINESS_LICENCEIMG," +
                "POSITIVE_IDCARDEIMG," +
                "HAND_IDCARDIMG," +
                "NEGATIVE_IDCARDIMG," +
                "DOCUMENT_IMAGES," +
                "LEGAL_IMAGES," +
                "ENTITY_INDUSTRY_NAME,ENTITY_PROPERTY_NAME,ENTITY_TYPE_NAME,ENTITY_SCALE_NAME,ORGANIZATION_CODE," +
                "INFO_UNIQUE,BUSINESS_OPERATION,ENTERPRISE_INDUSTRY,APPROVE_NAME,APPROVE_USER_IDCODE," +
                "APPROVE_OPINION,CONTACT_NAME,LEGAL_NAME,ENTERPRISE_NAME,REAL_NAME,ACCOUNT,ANNUAL_OUTPUT,AREA,ANNUAL_OUTPUT_S,ANNUAL_OUTPUT_X," +
                "ADDRESS,CARD_TYPE,ENTITY_IDCODE,USER_IDCODE,ENTITY_PROPERTY,ENTITY_TYPE," +
                "ENTITY_SCALE,ENTITY_INDUSTRY,BUSLICENCEIMG_BLOB,CERTIFICATEIMG_BLOB,POS_IDIMG_BLOB,NEG_IDIMG_BLOB,HAND_IDIMG_BLOB) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

         try {
             pst = destConn.prepareStatement(sql);
             for(int i=0;i<list.size();i++) {
                 Map<String,Object> sourceData=list.get(i);
                 pst.setString(1, (String) sourceData.get("APPROVE_STATUS"));
                 pst.setString(2, (String) sourceData.get("ID"));
                 Object end= sourceData.get("BUSINESS_OPERATION_END");
                 pst.setDate(3, getSqlDate((java.util.Date) end));
                 Object start=  sourceData.get("BUSINESS_OPERATION_START");
                 pst.setDate(4,  getSqlDate((java.util.Date) start));
                 Object Update= sourceData.get("UPDATE_TIME");
                 pst.setDate(5,   getSqlDate((java.util.Date) Update));
                 Object creat=  sourceData.get("CREATE_TIME");
                 pst.setDate(6, getSqlDate((java.util.Date) creat));
                 Object approve=  sourceData.get("APPROVE_TIME");
                 pst.setDate(7, getSqlDate((java.util.Date) approve));
                 Object rigister=  sourceData.get("REGISTER_TIME");
                 pst.setDate(8, getSqlDate((java.util.Date) rigister));
                pst.setFloat(9,  sourceData.get("LATITUDE")!=null?Float.parseFloat(sourceData.get("LATITUDE").toString()):null);
                pst.setFloat(10, sourceData.get("LONGITUDE")!=null?Float.parseFloat(sourceData.get("LONGITUDE").toString()):null);
                pst.setString(11, (String) sourceData.get("DEL_FLAG"));
                pst.setString(12, (String) sourceData.get("UNIT"));
                pst.setString(13, (String) sourceData.get("UNIT_NAME"));
                pst.setString(14, (String) sourceData.get("UNIT_S"));
                pst.setString(15, (String) sourceData.get("UNIT_X"));
                pst.setString(16, (String) sourceData.get("UNIT_NAME_S"));
                pst.setString(17, (String) sourceData.get("UNIT_NAME_X"));
                pst.setString(18, (String) sourceData.get("CONTACT_EMAIL"));
                pst.setString(19, (String) sourceData.get("CREATE_BY"));
                pst.setString(20, (String) sourceData.get("UPDATE_BY"));
                pst.setString(21, (String) sourceData.get("FAX_NUMBER"));
                pst.setString(22, (String) sourceData.get("LEGAL_PHONE"));
                pst.setString(23, (String) sourceData.get("CONTACT_PHONE"));
                pst.setString(24, (String) sourceData.get("IDCODE"));
                pst.setString(25, (String) sourceData.get("CREDIT_CODE"));
                pst.setString(26, (String) sourceData.get("LEGAL_IDNUMBER"));
                pst.setString(27, (String) sourceData.get("IS_LONG"));
                pst.setString(28, (String) sourceData.get("ORGANIZATION_CERTIFICATEIMG"));
                pst.setString(29, (String) sourceData.get("BUSINESS_LICENCEIMG"));
                pst.setString(30, (String) sourceData.get("POSITIVE_IDCARDEIMG"));
                pst.setString(31, (String) sourceData.get("HAND_IDCARDIMG"));
                pst.setString(32, (String) sourceData.get("NEGATIVE_IDCARDIMG"));
                pst.setString(33, (String) sourceData.get("DOCUMENT_IMAGES"));
                pst.setString(34, (String) sourceData.get("LEGAL_IMAGES"));
                pst.setString(35, (String) sourceData.get("ENTITY_INDUSTRY_NAME"));
                pst.setString(36, (String) sourceData.get("ENTITY_PROPERTY_NAME"));
                pst.setString(37, (String) sourceData.get("ENTITY_TYPE_NAME"));
                pst.setString(38, (String) sourceData.get("ENTITY_SCALE_NAME"));
                pst.setString(39, (String) sourceData.get("ORGANIZATION_CODE"));
                pst.setString(40, (String) sourceData.get("INFO_UNIQUE"));
                pst.setString(41, (String) sourceData.get("BUSINESS_OPERATION"));
                pst.setString(42, (String) sourceData.get("ENTERPRISE_INDUSTRY"));
                pst.setString(43, (String) sourceData.get("APPROVE_NAME"));
                pst.setString(44, (String) sourceData.get("APPROVE_USER_IDCODE"));
                pst.setString(45, (String) sourceData.get("APPROVE_OPINION"));
                pst.setString(46, (String) sourceData.get("CONTACT_NAME"));
                pst.setString(47, (String) sourceData.get("LEGAL_NAME"));
                pst.setString(48, (String) sourceData.get("ENTERPRISE_NAME"));
                pst.setString(49, (String) sourceData.get("REAL_NAME"));
                pst.setString(50, (String) sourceData.get("ACCOUNT"));
                pst.setString(51, (String) sourceData.get("ANNUAL_OUTPUT"));
                pst.setString(52, (String) sourceData.get("AREA"));
                pst.setString(53, (String) sourceData.get("ANNUAL_OUTPUT_S"));
                pst.setString(54, (String) sourceData.get("ANNUAL_OUTPUT_X"));
                pst.setString(55, (String) sourceData.get("ADDRESS"));
                pst.setString(56, (String) sourceData.get("CARD_TYPE"));
                pst.setString(57, (String) sourceData.get("ENTITY_IDCODE"));
                pst.setString(58, (String) sourceData.get("USER_IDCODE"));
                pst.setString(59, (String) sourceData.get("ENTITY_PROPERTY"));
                pst.setString(60, (String) sourceData.get("ENTITY_TYPE"));
                pst.setString(61, (String) sourceData.get("ENTITY_SCALE"));
                pst.setString(62, (String) sourceData.get("ENTITY_INDUSTRY"));
                pst.setBytes(63,sourceData.get("BUSINESS_LICENCEIMG")!=null?getBytes(sourceData.get("BUSINESS_LICENCEIMG").toString()):null);
                pst.setBytes(64,sourceData.get("ORGANIZATION_CERTIFICATEIMG")!=null?getBytes(sourceData.get("ORGANIZATION_CERTIFICATEIMG").toString()):null);
                pst.setBytes(65,sourceData.get("POSITIVE_IDCARDEIMG")!=null?getBytes(sourceData.get("POSITIVE_IDCARDEIMG").toString()):null);
                pst.setBytes(66,sourceData.get("NEGATIVE_IDCARDIMG")!=null?getBytes(sourceData.get("NEGATIVE_IDCARDIMG").toString()):null);
                pst.setBytes(67,sourceData.get("HAND_IDCARDIMG")!=null?getBytes(sourceData.get("HAND_IDCARDIMG").toString()):null);
                pst.addBatch();
             }
             pst.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(null!=pst){
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally{
                    pst=null;
                }
                if(null!=destConn){
                    try {
                        destConn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally{
                        destConn=null;
                    }
                }
            }
        }

    return true;
    }

    /**
     * 取得源数据库的数据
     * @param sourceDataSource
     * @param list
     */
    private void getSourceData(String sourceDataSource, List<Map<String, Object>> list) {
        Connection sourceConn=getConnection(sourceDataSource);
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm= sourceConn.createStatement();
            String sql="select APPROVE_STATUS,ID,BUSINESS_OPERATION_END,BUSINESS_OPERATION_START,UPDATE_TIME,CREATE_TIME,APPROVE_TIME," +
                    "REGISTER_TIME,LATITUDE,LONGITUDE,DEL_FLAG,UNIT,UNIT_NAME,UNIT_S,UNIT_X,UNIT_NAME_S,UNIT_NAME_X,CONTACT_EMAIL," +
                    "CREATE_BY,UPDATE_BY,FAX_NUMBER,LEGAL_PHONE,CONTACT_PHONE,IDCODE,CREDIT_CODE,LEGAL_IDNUMBER,IS_LONG," +
                    "ORGANIZATION_CERTIFICATEIMG," +
                    "BUSINESS_LICENCEIMG," +
                    "POSITIVE_IDCARDEIMG," +
                    "HAND_IDCARDIMG," +
                    "NEGATIVE_IDCARDIMG," +
                    "DOCUMENT_IMAGES," +
                    "LEGAL_IMAGES," +
                    "ENTITY_INDUSTRY_NAME,ENTITY_PROPERTY_NAME,ENTITY_TYPE_NAME,ENTITY_SCALE_NAME,ORGANIZATION_CODE," +
                    "INFO_UNIQUE,BUSINESS_OPERATION,ENTERPRISE_INDUSTRY,APPROVE_NAME,APPROVE_USER_IDCODE," +
                    "APPROVE_OPINION,CONTACT_NAME,LEGAL_NAME,ENTERPRISE_NAME,REAL_NAME,ACCOUNT,ANNUAL_OUTPUT,AREA,ANNUAL_OUTPUT_S,ANNUAL_OUTPUT_X," +
                    "ADDRESS,CARD_TYPE,ENTITY_IDCODE,USER_IDCODE,ENTITY_PROPERTY,ENTITY_TYPE," +
                    "ENTITY_SCALE,ENTITY_INDUSTRY" +
                    " from gjzspt.TTS_SCLTXXCJ_REGISTER_V2";
            rs=stm.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            Map<String,Object> rowData = new HashMap<String,Object>();
            while(rs.next()){
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(sourceConn, stm, rs);
        }
    }

    //获取下载文件的数组
    private byte[] getBytes(String urlStr) {
        byte[] getData=null;
        if(urlStr!=null&&urlStr!=""){
            try {
                InputStream inputStream= FastDFSUtil.dowloadFileToInputStream(urlStr);
                getData = readInputStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return getData;
        }else{
            return  null;
        }
    }
   //获取数据库格式 日期
    private Date getSqlDate(java.util.Date end) {
        if(end!=null){
            java.util.Date endDate= end;
            return new Date(endDate.getTime());
        }else{
            return  null;
        }
    }


    //查询目标库  表是否存在
    private int isExistTable(String destDataSource, int isExistence) {
        Connection destConn=getConnection(destDataSource);
        Statement destStm = null;
        ResultSet destRs = null;
        try {
            destStm= destConn.createStatement();
            String sql="select count(*) from user_tables where table_name = 'TTS_SCLTXXCJ_REGISTER_V2'";
            destRs=destStm.executeQuery(sql);
            destRs.next();
            isExistence=destRs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(destConn, destStm, destRs);
        }
        return isExistence;
    }

    //在目标库建立 TTS_SCLTXXCJ_REGISTER_V2  '注册主体信息表'
    private void createTabel(String destDataSource) {
        Connection destConn=getConnection(destDataSource);
        Statement deststm = null;
        try {
            deststm= destConn.createStatement();
            String sql="CREATE TABLE DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2" +
                    "(ID CHAR(64 BYTE) NOT NULL ENABLE," +
                    "ACCOUNT VARCHAR2(60 BYTE) NOT NULL ENABLE," +
                    "REAL_NAME VARCHAR2(300 BYTE) NOT NULL ENABLE," +
                    "IDCODE VARCHAR2(18 BYTE) NOT NULL ENABLE," +
                    "ENTITY_SCALE VARCHAR2(700 BYTE) NOT NULL ENABLE," +
                    "ENTITY_TYPE VARCHAR2(700 BYTE) NOT NULL ENABLE," +
                    "ENTITY_PROPERTY VARCHAR2(700 BYTE) NOT NULL ENABLE," +
                    "ENTITY_INDUSTRY VARCHAR2(700 BYTE) NOT NULL ENABLE," +
                    "ENTERPRISE_NAME VARCHAR2(300 BYTE)," +
                    "CARD_TYPE VARCHAR2(64 BYTE)," +
                    "CREDIT_CODE VARCHAR2(20 BYTE)," +
                    "BUSINESS_OPERATION VARCHAR2(30 BYTE)," +
                    "BUSINESS_OPERATION_START DATE," +
                    "BUSINESS_OPERATION_END DATE," +
                    "ENTERPRISE_INDUSTRY VARCHAR2(30 BYTE)," +
                    "ANNUAL_OUTPUT VARCHAR2(60 BYTE) DEFAULT NULL," +
                    "UNIT VARCHAR2(100 BYTE)," +
                    "ADDRESS VARCHAR2(600 BYTE) DEFAULT NULL NOT NULL ENABLE," +
                    "AREA VARCHAR2(60 BYTE) DEFAULT NULL NOT NULL ENABLE," +
                    "LONGITUDE NUMBER(20,14) NOT NULL ENABLE," +
                    "LATITUDE NUMBER(20,14) NOT NULL ENABLE," +
                    "LEGAL_NAME VARCHAR2(300 BYTE) NOT NULL ENABLE," +
                    "LEGAL_IDNUMBER VARCHAR2(20 BYTE) NOT NULL ENABLE," +
                    "LEGAL_PHONE VARCHAR2(15 BYTE) NOT NULL ENABLE," +
                    "FAX_NUMBER VARCHAR2(15 BYTE)," +
                    "CONTACT_NAME VARCHAR2(300 BYTE) NOT NULL ENABLE," +
                    "CONTACT_PHONE VARCHAR2(15 BYTE) NOT NULL ENABLE," +
                    "CONTACT_EMAIL VARCHAR2(120 BYTE) NOT NULL ENABLE," +
                    "REGISTER_TIME DATE," +
                    "APPROVE_STATUS CHAR(1 BYTE) NOT NULL ENABLE," +
                    "APPROVE_OPINION VARCHAR2(300 BYTE)," +
                    "APPROVE_USER_IDCODE VARCHAR2(300 BYTE)," +
                    "APPROVE_NAME VARCHAR2(300 BYTE)," +
                    "APPROVE_TIME DATE," +
                    "USER_IDCODE VARCHAR2(64 BYTE)," +
                    "ENTITY_IDCODE VARCHAR2(64 BYTE) NOT NULL ENABLE," +
                    "DEL_FLAG VARCHAR2(1 BYTE) NOT NULL ENABLE," +
                    "CREATE_BY VARCHAR2(128 BYTE)," +
                    "CREATE_TIME DATE," +
                    "UPDATE_BY VARCHAR2(128 BYTE)," +
                    "UPDATE_TIME DATE," +
                    "INFO_UNIQUE VARCHAR2(200 BYTE) NOT NULL ENABLE," +
                    "ENTITY_SCALE_NAME VARCHAR2(200 BYTE) NOT NULL ENABLE," +
                    "ENTITY_TYPE_NAME VARCHAR2(200 BYTE) NOT NULL ENABLE," +
                    "ENTITY_PROPERTY_NAME VARCHAR2(200 BYTE) NOT NULL ENABLE," +
                    "ENTITY_INDUSTRY_NAME VARCHAR2(200 BYTE) NOT NULL ENABLE," +
                    "UNIT_NAME VARCHAR2(100 BYTE)," +
                    "ORGANIZATION_CODE VARCHAR2(200 BYTE)," +
                    "IS_LONG VARCHAR2(20 BYTE)," +
                    "ANNUAL_OUTPUT_X VARCHAR2(60 BYTE)," +
                    "ANNUAL_OUTPUT_S VARCHAR2(60 BYTE)," +
                    "UNIT_X VARCHAR2(100 BYTE)," +
                    "UNIT_S VARCHAR2(100 BYTE)," +
                    "UNIT_NAME_X VARCHAR2(100 BYTE)," +
                    "UNIT_NAME_S VARCHAR2(100 BYTE)," +
                    "RESERVED_FIELD1 VARCHAR2(200 BYTE)," +
                    "BUSINESS_LICENCEIMG VARCHAR2(200 BYTE)," +
                    "ORGANIZATION_CERTIFICATEIMG VARCHAR2(200 BYTE)," +
                    "POSITIVE_IDCARDEIMG VARCHAR2(200 BYTE) NOT NULL ENABLE," +
                    "NEGATIVE_IDCARDIMG VARCHAR2(200 BYTE) NOT NULL ENABLE," +
                    "HAND_IDCARDIMG VARCHAR2(200 BYTE) NOT NULL ENABLE," +
                    "DOCUMENT_IMAGES VARCHAR2(600 BYTE)," +
                    "LEGAL_IMAGES VARCHAR2(600 BYTE) NOT NULL ENABLE," +
                    "BUSLICENCEIMG_BLOB BLOB," +
                    "CERTIFICATEIMG_BLOB BLOB," +
                    "POS_IDIMG_BLOB BLOB," +
                    "NEG_IDIMG_BLOB BLOB," +
//                    "HAND_IDIMG_BLOB BLOB," +   (唯一性约束 数据插入时会报错，暂时注掉)
//                    " CONSTRAINT INFO_UNIQUE_REGISTER UNIQUE (INFO_UNIQUE))";
                    "HAND_IDIMG_BLOB BLOB)";
        deststm.execute(sql);

        deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ID IS '主键UUID'");
        deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ACCOUNT IS '账号'");
        deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.REAL_NAME IS '真实姓名'");
        deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.IDCODE IS '个人身份证号'");
        deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ENTITY_SCALE IS '主体组织形式'");
        deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ENTITY_TYPE IS '主体类型'");
        deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ENTITY_PROPERTY IS '主体属性'");
        deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ENTITY_INDUSTRY IS '主体所属行业'");
        deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ENTERPRISE_NAME IS '企业名称'");
        deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.CARD_TYPE IS '企业证件类型'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.CREDIT_CODE IS '企业证件号码'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.BUSINESS_OPERATION IS '企业营业期限'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.BUSINESS_OPERATION_START IS '企业营业期限起日'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.BUSINESS_OPERATION_END IS '企业营业期限止日'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ENTERPRISE_INDUSTRY IS '企业所属行业'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ANNUAL_OUTPUT IS '企业种植业年产量'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.UNIT IS '企业种植业年产量计量单位'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ADDRESS IS '企业地址'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.DOCUMENT_IMAGES IS '证件照片'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.AREA IS '所属区域'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.LONGITUDE IS '经度'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.LATITUDE IS '纬度'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.LEGAL_NAME IS '法人姓名'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.LEGAL_IDNUMBER IS '法人身份证号码'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.LEGAL_PHONE IS '法人电话'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.LEGAL_IMAGES IS '法人相关照片'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.FAX_NUMBER IS '传真'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.CONTACT_NAME IS '联系人姓名'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.CONTACT_PHONE IS '联系人电话'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.CONTACT_EMAIL IS '联系人邮箱'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.REGISTER_TIME IS '注册时间'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.APPROVE_STATUS IS '(审批状态 0:注册待审核;1:审核通过;2:注册审核不通过;3变更待审核;4:注销待审核;5:撤销待审核;6:撤销通过;7:已注销)'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.APPROVE_OPINION IS '审批意见'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.APPROVE_USER_IDCODE IS '审批人主体用户码'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.APPROVE_NAME IS '审批人姓名'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.APPROVE_TIME IS '审批时间'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.USER_IDCODE IS '主体用户码'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ENTITY_IDCODE IS '主体身份码'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.DEL_FLAG IS '删除标识'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.CREATE_BY IS '创建人'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.CREATE_TIME IS '创建时间'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.UPDATE_BY IS '修改人'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.UPDATE_TIME IS '修改时间'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.INFO_UNIQUE IS '该字段为冗余字段，只用于防止注册信息出现重复的情况。'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ENTITY_SCALE_NAME IS '主体组织形式字典名称'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ENTITY_TYPE_NAME IS '主体类型字典名称'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ENTITY_PROPERTY_NAME IS '主体属性字典名称'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ENTITY_INDUSTRY_NAME IS '主体行业字典名称'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.UNIT_NAME IS '企业种植业年产量计量单位名称'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.BUSINESS_LICENCEIMG IS '营业执照'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ORGANIZATION_CERTIFICATEIMG IS '组织机构代码证照'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.POSITIVE_IDCARDEIMG IS '身份证正面照'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.NEGATIVE_IDCARDIMG IS '身份证反面照'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.HAND_IDCARDIMG IS '手持身份证照'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ORGANIZATION_CODE IS '组织机构代码'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.IS_LONG IS '是否是长期（true：是；null：否）'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ANNUAL_OUTPUT_X IS '畜牧业年产量'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.ANNUAL_OUTPUT_S IS '水产品年产量'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.UNIT_X IS '畜牧业年产量计量单位'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.UNIT_S IS '水产品年产量计量单位'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.UNIT_NAME_X IS '企业畜牧业年产量计量单位名称'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.UNIT_NAME_S IS '企业水产品年产量计量单位名称'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.BUSLICENCEIMG_BLOB IS '营业执照blob类型'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.CERTIFICATEIMG_BLOB IS '组织机构代码证照blob类型'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.POS_IDIMG_BLOB IS '身份证正面照blob类型'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.NEG_IDIMG_BLOB IS '身份证反面照blob类型'");
         deststm.execute("COMMENT ON COLUMN DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2.HAND_IDIMG_BLOB IS '手持身份证照blob类型'");
         deststm.execute("COMMENT ON TABLE DGAP_DW.TTS_SCLTXXCJ_REGISTER_V2  IS '注册主体信息表'");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(destConn, deststm, null);
        }
    }


    //获取连接
    private static Connection getConnection(String sourceDataSource) {
        // jdbc:oracle:thin:gjzspt/12345678@192.168.21.249:1521:gjzs
        int begin=sourceDataSource.lastIndexOf("/");
        int end=sourceDataSource.lastIndexOf("@");
        String url=sourceDataSource.substring(0,17)+sourceDataSource.substring(end);
        String name=sourceDataSource.substring(17,begin);
        String passWord=sourceDataSource.substring(begin+1,end);
        Connection conn = null;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,name,passWord);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //释放资源
    private static void release(Connection con, Statement statement,ResultSet rs){
        if(null!=rs){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                rs=null;
            }
            if(null!=statement){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally{
                    statement=null;
                }
            }
            if(null!=con){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally{
                    con=null;
                }
            }
        }
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

//测试用
//    public static void main(String[] args) {
//        DataToolProviderImpl datatool=new DataToolProviderImpl();
//        datatool.copyTTSEntities("jdbc:oracle:thin:gjzspt/12345678@192.168.21.249:1521:gjzs","jdbc:oracle:thin:dgap_dw/12345678@192.168.21.249:1521:gjzs");
//    }

}
