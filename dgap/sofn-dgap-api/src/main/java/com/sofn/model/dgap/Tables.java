package com.sofn.model.dgap;

/**
 * Created by Thai on 2016/9/29.
 */
public class Tables {
    //表名称
    public String tableName;
    //表类型
    public String tableType;
    //注释
    public String remarks;
    //类型
    public String typeSchem;
    //类型名称
    public String typeName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTypeSchem() {
        return typeSchem;
    }

    public void setTypeSchem(String typeSchem) {
        this.typeSchem = typeSchem;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRefGenaration() {
        return refGenaration;
    }

    public void setRefGenaration(String refGenaration) {
        this.refGenaration = refGenaration;
    }

    //引用
    public String refGenaration;

    public Tables(String tableName, String tableType, String remarks, String typeSchem, String typeName, String refGenaration) {
        this.tableName = tableName;
        this.tableType = tableType;
        this.remarks = remarks;
        this.typeSchem = typeSchem;
        this.typeName = typeName;
        this.refGenaration = refGenaration;
    }

    @Override
    public String toString() {
        return "Tables{" +
                "tableName='" + tableName + '\'' +
                ", tableType='" + tableType + '\'' +
                ", remarks='" + remarks + '\'' +
                ", typeSchem='" + typeSchem + '\'' +
                ", typeName='" + typeName + '\'' +
                ", refGenaration='" + refGenaration + '\'' +
                '}';
    }
    public Tables(){

    }
}
