package com.sofn.etlquery.pojo;

import com.sofn.etlquery.utils.CharUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SysTestStandard {

    private String ID;
    private String STANDARD_CODE;
    private String STANDARD_NAME;
    private String REMARK;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSTANDARD_CODE() {
        return STANDARD_CODE;
    }

    public void setSTANDARD_CODE(String STANDARD_CODE) {
        this.STANDARD_CODE = STANDARD_CODE;
    }

    public String getSTANDARD_NAME() {
        return STANDARD_NAME;
    }

    public void setSTANDARD_NAME(String STANDARD_NAME) {
        this.STANDARD_NAME = STANDARD_NAME;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    @Override
    public String toString() {
        return "SysTestStandard{" +
                "ID='" + ID + '\'' +
                ", STANDARD_CODE='" + STANDARD_CODE + '\'' +
                ", STANDARD_NAME='" + STANDARD_NAME + '\'' +
                ", REMARK='" + REMARK + '\'' +
                '}';
    }
//
//    public SysTestStandard getResult(ResultSet rs) throws SQLException {
//        SysTestStandard sysTestStandard = new SysTestStandard();
//        sysTestStandard.setID(CharUtils.toNullCase(rs.getString(1)));
//        sysTestStandard.setSTANDARD_CODE(CharUtils.toNullCase(rs.getString(2)));
//        sysTestStandard.setSTANDARD_NAME(CharUtils.toNullCase(rs.getString(3)));
//        sysTestStandard.setREMARK(CharUtils.toNullCase(rs.getString(4)));
//
//        return sysTestStandard;
//    }
}
