package com.sofn.ws;

import com.sofn.model.dgap.FieldData;
import com.sofn.model.dgap.RowData;

import java.util.Arrays;
import java.util.List;

/**
 * Created by helong on 18-1-17.
 */
public class ResourceExportProfile {

    String token;
    int dataSize;
    String[] fieldNames;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(String[] fieldNames) {
        this.fieldNames = fieldNames;
    }

    @Override
    public String toString() {
        return "ResourceExportProfile{" +
                "token='" + token + '\'' +
                ", dataSize=" + dataSize +
                ", fieldNames=" + Arrays.toString(fieldNames) +
                '}';
    }}
