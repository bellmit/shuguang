package com.sofn.etlquery.pojo;

import com.sofn.etlquery.utils.CharUtils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TtsScltxxcjComplaintV2 implements Serializable {

    private String ID;
    private String ENTITY_IDCODE;
    private String USER_IDCODE;
    private String ENTERPRISE_NAME;
    private String BE_USER_IDCODE;
    private String ACC_ENTITY_IDCODE;
    private String ACC_USER_IDCODE;
    private String COMPLAINT_TIME;
    private String COMPLAINT_TITLE;
    private String CONTENT;
    private String TYPE;
    private String STATUS;
    private String ACCEPTANCE;
    private String AREA_ID;
    private String ACC_TIME;
    private String ATTACHMENT_NAME;
    private String COMPLAINT_ENT_NAME;
    private String ATTACHMENT_PATH;
    private String COMPLAINT_COP_NAME;
    private String TYPE_NAME;


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getENTITY_IDCODE() {
        return ENTITY_IDCODE;
    }

    public void setENTITY_IDCODE(String ENTITY_IDCODE) {
        this.ENTITY_IDCODE = ENTITY_IDCODE;
    }

    public String getUSER_IDCODE() {
        return USER_IDCODE;
    }

    public void setUSER_IDCODE(String USER_IDCODE) {
        this.USER_IDCODE = USER_IDCODE;
    }

    public String getENTERPRISE_NAME() {
        return ENTERPRISE_NAME;
    }

    public void setENTERPRISE_NAME(String ENTERPRISE_NAME) {
        this.ENTERPRISE_NAME = ENTERPRISE_NAME;
    }

    public String getBE_USER_IDCODE() {
        return BE_USER_IDCODE;
    }

    public void setBE_USER_IDCODE(String BE_USER_IDCODE) {
        this.BE_USER_IDCODE = BE_USER_IDCODE;
    }

    public String getACC_ENTITY_IDCODE() {
        return ACC_ENTITY_IDCODE;
    }

    public void setACC_ENTITY_IDCODE(String ACC_ENTITY_IDCODE) {
        this.ACC_ENTITY_IDCODE = ACC_ENTITY_IDCODE;
    }

    public String getACC_USER_IDCODE() {
        return ACC_USER_IDCODE;
    }

    public void setACC_USER_IDCODE(String ACC_USER_IDCODE) {
        this.ACC_USER_IDCODE = ACC_USER_IDCODE;
    }

    public String getCOMPLAINT_TIME() {
        return COMPLAINT_TIME;
    }

    public void setCOMPLAINT_TIME(String COMPLAINT_TIME) {
        this.COMPLAINT_TIME = COMPLAINT_TIME;
    }

    public String getCOMPLAINT_TITLE() {
        return COMPLAINT_TITLE;
    }

    public void setCOMPLAINT_TITLE(String COMPLAINT_TITLE) {
        this.COMPLAINT_TITLE = COMPLAINT_TITLE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getACCEPTANCE() {
        return ACCEPTANCE;
    }

    public void setACCEPTANCE(String ACCEPTANCE) {
        this.ACCEPTANCE = ACCEPTANCE;
    }

    public String getAREA_ID() {
        return AREA_ID;
    }

    public void setAREA_ID(String AREA_ID) {
        this.AREA_ID = AREA_ID;
    }

    public String getACC_TIME() {
        return ACC_TIME;
    }

    public void setACC_TIME(String ACC_TIME) {
        this.ACC_TIME = ACC_TIME;
    }

    public String getATTACHMENT_NAME() {
        return ATTACHMENT_NAME;
    }

    public void setATTACHMENT_NAME(String ATTACHMENT_NAME) {
        this.ATTACHMENT_NAME = ATTACHMENT_NAME;
    }

    public String getCOMPLAINT_ENT_NAME() {
        return COMPLAINT_ENT_NAME;
    }

    public void setCOMPLAINT_ENT_NAME(String COMPLAINT_ENT_NAME) {
        this.COMPLAINT_ENT_NAME = COMPLAINT_ENT_NAME;
    }

    public String getATTACHMENT_PATH() {
        return ATTACHMENT_PATH;
    }

    public void setATTACHMENT_PATH(String ATTACHMENT_PATH) {
        this.ATTACHMENT_PATH = ATTACHMENT_PATH;
    }

    public String getCOMPLAINT_COP_NAME() {
        return COMPLAINT_COP_NAME;
    }

    public void setCOMPLAINT_COP_NAME(String COMPLAINT_COP_NAME) {
        this.COMPLAINT_COP_NAME = COMPLAINT_COP_NAME;
    }

    public String getTYPE_NAME() {
        return TYPE_NAME;
    }

    public void setTYPE_NAME(String TYPE_NAME) {
        this.TYPE_NAME = TYPE_NAME;
    }


    @Override
    public String toString() {
        return "TtsScltxxcjComplaintV2{" +
                "ID='" + ID + '\'' +
                ", ENTITY_IDCODE='" + ENTITY_IDCODE + '\'' +
                ", USER_IDCODE='" + USER_IDCODE + '\'' +
                ", ENTERPRISE_NAME='" + ENTERPRISE_NAME + '\'' +
                ", BE_USER_IDCODE='" + BE_USER_IDCODE + '\'' +
                ", ACC_ENTITY_IDCODE='" + ACC_ENTITY_IDCODE + '\'' +
                ", ACC_USER_IDCODE='" + ACC_USER_IDCODE + '\'' +
                ", COMPLAINT_TIME='" + COMPLAINT_TIME + '\'' +
                ", COMPLAINT_TITLE='" + COMPLAINT_TITLE + '\'' +
                ", CONTENT='" + CONTENT + '\'' +
                ", TYPE='" + TYPE + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", ACCEPTANCE='" + ACCEPTANCE + '\'' +
                ", AREA_ID='" + AREA_ID + '\'' +
                ", ACC_TIME=" + ACC_TIME +
                ", ATTACHMENT_NAME='" + ATTACHMENT_NAME + '\'' +
                ", COMPLAINT_ENT_NAME='" + COMPLAINT_ENT_NAME + '\'' +
                ", ATTACHMENT_PATH='" + ATTACHMENT_PATH + '\'' +
                ", COMPLAINT_COP_NAME='" + COMPLAINT_COP_NAME + '\'' +
                ", TYPE_NAME='" + TYPE_NAME + '\'' +
                '}';
    }

//    public TtsScltxxcjComplaintV2 getResult(ResultSet rs) throws SQLException {
//
//        TtsScltxxcjComplaintV2 complaintV2 = new TtsScltxxcjComplaintV2();
//        complaintV2.setID(CharUtils.toNullCase(rs.getString(1)));
//        complaintV2.setENTITY_IDCODE(CharUtils.toNullCase(rs.getString(2)));
//        complaintV2.setUSER_IDCODE(CharUtils.toNullCase(rs.getString(3)));
//        complaintV2.setENTERPRISE_NAME(CharUtils.toNullCase(rs.getString(4)));
//        complaintV2.setBE_USER_IDCODE(CharUtils.toNullCase(rs.getString(5)));
//        complaintV2.setACC_ENTITY_IDCODE(CharUtils.toNullCase(rs.getString(6)));
//        complaintV2.setACC_USER_IDCODE(CharUtils.toNullCase(rs.getString(7)));
//        complaintV2.setCOMPLAINT_TIME(CharUtils.toNullCase(rs.getString(8)));
//        complaintV2.setCOMPLAINT_TITLE(CharUtils.toNullCase(rs.getString(9)));
//        complaintV2.setCONTENT(CharUtils.toNullCase(rs.getString(10)));
//        complaintV2.setTYPE(CharUtils.toNullCase(rs.getString(11)));
//        complaintV2.setSTATUS(CharUtils.toNullCase(rs.getString(12)));
//        complaintV2.setACCEPTANCE(CharUtils.toNullCase(rs.getString(13)));
//        complaintV2.setAREA_ID(CharUtils.toNullCase(rs.getString(14)));
//        complaintV2.setACC_TIME(CharUtils.toNullCase(rs.getString(15)));
//        complaintV2.setATTACHMENT_NAME(CharUtils.toNullCase(rs.getString(16)));
//        complaintV2.setCOMPLAINT_ENT_NAME(CharUtils.toNullCase(rs.getString(17)));
//        complaintV2.setATTACHMENT_PATH(CharUtils.toNullCase(rs.getString(18)));
//        complaintV2.setCOMPLAINT_COP_NAME(CharUtils.toNullCase(rs.getString(19)));
//        complaintV2.setTYPE_NAME(CharUtils.toNullCase(rs.getString(20)));
//
//        return complaintV2;
//    }
}
