package com.sofn.model.generator;

/**
 * Created by Administrator on 2018/2/6 0006.
 */
public class TDgapDataImportModel {
    private  String tableId;
    private String tableName;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDgapDataImportModel that = (TDgapDataImportModel) o;

        if (tableId != null ? !tableId.equals(that.tableId) : that.tableId != null) return false;
        return tableName != null ? tableName.equals(that.tableName) : that.tableName == null;

    }

    @Override
    public int hashCode() {
        int result = tableId != null ? tableId.hashCode() : 0;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TDgapDataImportModel{" +
                "tableId='" + tableId + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
