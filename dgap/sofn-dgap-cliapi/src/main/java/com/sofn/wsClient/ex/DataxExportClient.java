package com.sofn.wsClient.ex;

import com.sofn.model.dgap.DataChangeLogResult;
import com.sofn.model.dgap.DataExportResult;
import com.sofn.ws.DataExport;
import com.sofn.wsClient.DataExportClient;

import java.util.List;

public class DataxExportClient {

    DataExport dataExport;

    public <T> List<T> getData(String accessToken, int start, int end, String condition, Class<T> type) throws Exception {
        DataExportResult result = dataExport.getData(accessToken, start, end, null, condition);
        if(!result.isSuccess()){
            throw new Exception(result.getErrorCode());
        }
        return ExportConverter.convertFromRowData(result.getData(), type);
    }

    public  <T> List<T> getDataChangeLog(String token, String mark, Class<T> type) throws Exception {
        DataChangeLogResult result= dataExport.getDataChangeLog(token,mark);
        if(!result.isSuccess()){
            throw new Exception(result.getErrorCode());
        }
        return ExportConverter.convertFromRowData(result.getData(), type);

    }
    public DataxExportClient(String wsdl) {
        dataExport = new DataExportClient().createExportClent(wsdl);
    }
}
