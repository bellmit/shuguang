package com.sofn.model.dgap;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weiq on 2016/12/7.
 */
public class DataExportResult implements ErrorSupport,Serializable {
    private boolean success;
    private String errorCode;
    private String errorDesc;
    List<RowData> data;

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorDesc() {
        return errorDesc;
    }

    @Override
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public List<RowData> getData() {
        return data;
    }

    public void setData(List<RowData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataExportResult{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", errorDesc='" + errorDesc + '\'' +
                ", data=" + data +
                '}';
    }
}
