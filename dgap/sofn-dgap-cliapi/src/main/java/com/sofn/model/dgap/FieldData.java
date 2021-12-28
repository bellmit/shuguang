package com.sofn.model.dgap;

/**
 * Created by helong on 16-11-22.
 */
public class FieldData {
    private String columnName;
    private String value;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FieldData{" +
                "columnName='" + columnName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
