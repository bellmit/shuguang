package com.sofn.model.dgap;

/**
 * Created by Thai on 2016/9/27.
 */
public class Column {
    //字段名称
    public String columnName ;
    //字段Type
    public String columnType ;
    //字段类型
    public String columnTypeName;
    //字段长度
    public int columnSize;
    //Decimal Digits
    public String columnDecimalDigits;
    //是否允许为空
    public String columnNullable;



    //是否自增
    public String autoIncrement;
    //GENERATEDCOLUMN
    public String generatedColumn;
    //字段注释
    public String columnRemarks;

    public String getcolumnName() {
        return columnName;
    }

    public void setcolumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public String getColumnDecimalDigits() {
        return columnDecimalDigits;
    }

    public void setColumnDecimalDigits(String columnDecimalDigits) {
        this.columnDecimalDigits = columnDecimalDigits;
    }

    public String getColumnNullable() {
        return columnNullable;
    }

    public void setColumnNullable(String columnNullable) {
        this.columnNullable = columnNullable;
    }
    public String getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(String autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getGeneratedColumn() {
        return generatedColumn;
    }

    public void setGeneratedColumn(String generatedColumn) {
        this.generatedColumn = generatedColumn;
    }
    public String getColumnRemarks() {
        return columnRemarks;
    }

    public void setColumnRemarks(String columnRemarks) {
        this.columnRemarks = columnRemarks;
    }

    public Column(String columnName, String columnType, String columnTypeName, int columnSize, String columnDecimalDigits, String columnNullable, String autoIncrement, String generatedColumn, String columnRemarks) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.columnTypeName = columnTypeName;
        this.columnSize = columnSize;
        this.columnDecimalDigits = columnDecimalDigits;
        this.columnNullable = columnNullable;
        this.autoIncrement = autoIncrement;
        this.generatedColumn = generatedColumn;
        this.columnRemarks = columnRemarks;
    }

    public Column(){}

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnTypeName='" + columnTypeName + '\'' +
                ", columnSize=" + columnSize +
                ", columnDecimalDigits='" + columnDecimalDigits + '\'' +
                ", columnNullable='" + columnNullable + '\'' +
                ", autoIncrement='" + autoIncrement + '\'' +
                ", generatedColumn='" + generatedColumn + '\'' +
                ", columnRemarks='" + columnRemarks + '\'' +
                '}';
    }
}
