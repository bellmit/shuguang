package com.sofn.etlquery.pojo;

import com.sofn.etlquery.utils.CharUtils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SysArgiProduct implements Serializable {
    private String ID;
    private String PARENT_ID;
    private String PRODUCT_CODE;
    private String USE_CODE;
    private String NAME;
    private String ALIAS;
    private String ENNAME;
    private String GB_CODE;
    private String DESCRIPTION;
    private String TYPE_ID;
    private String PARENT_NAME;
    private String SLAUGHTER_STATUS;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPARENT_ID() {
        return PARENT_ID;
    }

    public void setPARENT_ID(String PARENT_ID) {
        this.PARENT_ID = PARENT_ID;
    }

    public String getPRODUCT_CODE() {
        return PRODUCT_CODE;
    }

    public void setPRODUCT_CODE(String PRODUCT_CODE) {
        this.PRODUCT_CODE = PRODUCT_CODE;
    }

    public String getUSE_CODE() {
        return USE_CODE;
    }

    public void setUSE_CODE(String USE_CODE) {
        this.USE_CODE = USE_CODE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getALIAS() {
        return ALIAS;
    }

    public void setALIAS(String ALIAS) {
        this.ALIAS = ALIAS;
    }

    public String getENNAME() {
        return ENNAME;
    }

    public void setENNAME(String ENNAME) {
        this.ENNAME = ENNAME;
    }

    public String getGB_CODE() {
        return GB_CODE;
    }

    public void setGB_CODE(String GB_CODE) {
        this.GB_CODE = GB_CODE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getTYPE_ID() {
        return TYPE_ID;
    }

    public void setTYPE_ID(String TYPE_ID) {
        this.TYPE_ID = TYPE_ID;
    }

    public String getPARENT_NAME() {
        return PARENT_NAME;
    }

    public void setPARENT_NAME(String PARENT_NAME) {
        this.PARENT_NAME = PARENT_NAME;
    }

    public String getSLAUGHTER_STATUS() {
        return SLAUGHTER_STATUS;
    }

    public void setSLAUGHTER_STATUS(String SLAUGHTER_STATUS) {
        this.SLAUGHTER_STATUS = SLAUGHTER_STATUS;
    }

    @Override
    public String toString() {
        return "SysArgiProduct{" +
                "ID='" + ID + '\'' +
                ", PARENT_ID='" + PARENT_ID + '\'' +
                ", PRODUCT_CODE='" + PRODUCT_CODE + '\'' +
                ", USE_CODE='" + USE_CODE + '\'' +
                ", NAME='" + NAME + '\'' +
                ", ALIAS='" + ALIAS + '\'' +
                ", ENNAME='" + ENNAME + '\'' +
                ", GB_CODE='" + GB_CODE + '\'' +
                ", DESCRIPTION='" + DESCRIPTION + '\'' +
                ", TYPE_ID='" + TYPE_ID + '\'' +
                ", PARENT_NAME='" + PARENT_NAME + '\'' +
                ", SLAUGHTER_STATUS='" + SLAUGHTER_STATUS + '\'' +
                '}';
    }

//    public SysArgiProduct getResult(ResultSet rs) throws SQLException {
//        SysArgiProduct sysArgiProduct = new SysArgiProduct();
//        sysArgiProduct.setID(CharUtils.toNullCase(rs.getString(1)));
//        sysArgiProduct.setPARENT_ID(CharUtils.toNullCase(rs.getString(2)));
//        sysArgiProduct.setPRODUCT_CODE(CharUtils.toNullCase(rs.getString(3)));
//        sysArgiProduct.setUSE_CODE(CharUtils.toNullCase(rs.getString(4)));
//        sysArgiProduct.setNAME(CharUtils.toNullCase(rs.getString(5)));
//        sysArgiProduct.setALIAS(CharUtils.toNullCase(rs.getString(6)));
//        sysArgiProduct.setENNAME(CharUtils.toNullCase(rs.getString(7)));
//        sysArgiProduct.setGB_CODE(CharUtils.toNullCase(rs.getString(8)));
//        sysArgiProduct.setDESCRIPTION(CharUtils.toNullCase(rs.getString(9)));
//        sysArgiProduct.setTYPE_ID(CharUtils.toNullCase(rs.getString(10)));
//        sysArgiProduct.setPARENT_NAME(CharUtils.toNullCase(rs.getString(11)));
//        sysArgiProduct.setSLAUGHTER_STATUS(CharUtils.toNullCase(rs.getString(12)));
//        return sysArgiProduct;
//    }

}
