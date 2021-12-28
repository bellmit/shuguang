package com.sofn.wsClient.ex;

import com.sofn.model.dgap.DataImportResult;
import com.sofn.ws.DataImport;
import com.sofn.wsClient.DataImportClient;

import java.util.List;

public class DataxImportClient {

    DataImport dataImport;

    public <T> DataImportResult addData(String accessToken, T data) {
        return dataImport.addData(accessToken, BeanConverter.convertToRowData(data));
    }

    public <T> DataImportResult addLogData(String accessToken, T data) {
        return dataImport.addLogData(accessToken, BeanConverter.convertToRowData(data));
    }

    public <T> DataImportResult addBatch(String token, List<T> data) {
        return dataImport.addBatch(token, BeanConverter.convertToRowDataList(data));
    }

    public <T> DataImportResult deleteData(String accessToken, T data) {
        return dataImport.deleteData(accessToken, BeanConverter.convertToRowData(data));
    }

    public <T> DataImportResult updateData(String accessToken, T data) {
        return dataImport.updateData(accessToken, BeanConverter.convertToRowData(data));
    }

    public DataxImportClient(String wsdl) {
        dataImport = new DataImportClient().createImportClient(wsdl);
    }
}
