package com.sofn.model.dgap;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fqc on 2019/07/24.
 */
public class DataExportCountResult implements ErrorSupport, Serializable {
    private boolean success;
    private String errorCode;
    private String errorDesc;
    private long totalCount;

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

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "DataExportResult{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", errorDesc='" + errorDesc + '\'' +
                ", totalCount=" + totalCount +
                '}';
    }
}
