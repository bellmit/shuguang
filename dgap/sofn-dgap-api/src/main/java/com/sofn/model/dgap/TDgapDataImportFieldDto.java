package com.sofn.model.dgap;

import com.sofn.model.generator.TDgapDataImportField;

/**
 * Created by Administrator on 2016/12/7.
 */
public class TDgapDataImportFieldDto extends TDgapDataImportField {
    //字段类型说明
    private  String typeName;
    //字段状态说明
    private String statusText;

    public TDgapDataImportFieldDto(String typeName, String statusText) {
        this.typeName = typeName;
        this.statusText = statusText;
    }

    public TDgapDataImportFieldDto() {
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
