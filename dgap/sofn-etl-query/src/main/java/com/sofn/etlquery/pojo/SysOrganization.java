package com.sofn.etlquery.pojo;

import com.sofn.etlquery.utils.CharUtils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SysOrganization implements Serializable {

    private String ID;
    private String ORG_NAME;
    private String ORG_TYPE;
    private String ORG_LEVEL;
    private String PRO;
    private String CITY;
    private String COUNTY;
    private String TYPE_ID;
    private String LEVEL_ID;
    private String REGION_ID;
    private String ORG_ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getORG_NAME() {
        return ORG_NAME;
    }

    public void setORG_NAME(String ORG_NAME) {
        this.ORG_NAME = ORG_NAME;
    }

    public String getORG_TYPE() {
        return ORG_TYPE;
    }

    public void setORG_TYPE(String ORG_TYPE) {
        this.ORG_TYPE = ORG_TYPE;
    }

    public String getORG_LEVEL() {
        return ORG_LEVEL;
    }

    public void setORG_LEVEL(String ORG_LEVEL) {
        this.ORG_LEVEL = ORG_LEVEL;
    }

    public String getPRO() {
        return PRO;
    }

    public void setPRO(String PRO) {
        this.PRO = PRO;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getCOUNTY() {
        return COUNTY;
    }

    public void setCOUNTY(String COUNTY) {
        this.COUNTY = COUNTY;
    }

    public String getTYPE_ID() {
        return TYPE_ID;
    }

    public void setTYPE_ID(String TYPE_ID) {
        this.TYPE_ID = TYPE_ID;
    }

    public String getLEVEL_ID() {
        return LEVEL_ID;
    }

    public void setLEVEL_ID(String LEVEL_ID) {
        this.LEVEL_ID = LEVEL_ID;
    }

    public String getREGION_ID() {
        return REGION_ID;
    }

    public void setREGION_ID(String REGION_ID) {
        this.REGION_ID = REGION_ID;
    }

    public String getORG_ID() {
        return ORG_ID;
    }

    public void setORG_ID(String ORG_ID) {
        this.ORG_ID = ORG_ID;
    }

    @Override
    public String toString() {
        return "SysOrganization{" +
                "ID='" + ID + '\'' +
                ", ORG_NAME='" + ORG_NAME + '\'' +
                ", ORG_TYPE='" + ORG_TYPE + '\'' +
                ", ORG_LEVEL='" + ORG_LEVEL + '\'' +
                ", PRO='" + PRO + '\'' +
                ", CITY='" + CITY + '\'' +
                ", COUNTY='" + COUNTY + '\'' +
                ", TYPE_ID='" + TYPE_ID + '\'' +
                ", LEVEL_ID='" + LEVEL_ID + '\'' +
                ", REGION_ID='" + REGION_ID + '\'' +
                ", ORG_ID='" + ORG_ID + '\'' +
                '}';
    }

//    public SysOrganization getResult(ResultSet rs) throws SQLException {
//        SysOrganization sysOrganization = new SysOrganization();
//        sysOrganization.setID(CharUtils.toNullCase(rs.getString(1)));
//        sysOrganization.setORG_NAME(CharUtils.toNullCase(rs.getString(2)));
//        sysOrganization.setORG_TYPE(CharUtils.toNullCase(rs.getString(3)));
//        sysOrganization.setORG_LEVEL(CharUtils.toNullCase(rs.getString(4)));
//        sysOrganization.setPRO(CharUtils.toNullCase(rs.getString(5)));
//        sysOrganization.setCITY(CharUtils.toNullCase(rs.getString(6)));
//        sysOrganization.setCOUNTY(CharUtils.toNullCase(rs.getString(7)));
//        sysOrganization.setTYPE_ID(CharUtils.toNullCase(rs.getString(8)));
//        sysOrganization.setLEVEL_ID(CharUtils.toNullCase(rs.getString(9)));
//        sysOrganization.setREGION_ID(CharUtils.toNullCase(rs.getString(10)));
//        sysOrganization.setORG_ID(CharUtils.toNullCase(rs.getString(11)));
//
//        return sysOrganization;
//    }
}
