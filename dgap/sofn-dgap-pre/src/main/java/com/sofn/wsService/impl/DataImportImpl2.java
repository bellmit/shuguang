package com.sofn.wsService.impl;

import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.RowData;
import com.sofn.model.generator.TDgapWsErrorLog;
import com.sofn.service.DataImportService;
import com.sofn.service.TDgapCheckResourceApplicationService;
import com.sofn.util.Constant;
import com.sofn.util.SessionHelper;
import com.sofn.ws.DataImport;
import com.sofn.wsService.BaseWsService;
import org.apache.zookeeper.server.quorum.FastLeaderElection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;

/**
 * Created by helong on 16-11-22.
 */
@WebService
public class DataImportImpl2 extends BaseWsService implements DataImport {

    @Value("${dataImport.rowData.length}")
    long MAXLENGTH;

    @Autowired
    private DataImportService dataImportService;
    @Autowired
    private TDgapCheckResourceApplicationService tDgapCheckResourceApplicationService;

    @Override
    public DataImportResult addData(String token, RowData data) {
        Map<String, Object> map = tDgapCheckResourceApplicationService.getTdgapResourceApplication(token);
        String resourceId = map.get("WS_ID").toString();
        return dataImportService.insertData(token,resourceId, data);
    }

    @Override
    public DataImportResult addLogData(String token, RowData data) {
        Map<String, Object> map = tDgapCheckResourceApplicationService.getTdgapResourceApplication(token);
        String resourceId = map.get("WS_ID").toString();
        return dataImportService.insertLogData(token,resourceId, data);
    }

    @Override
    public DataImportResult addBatch(String token, List<RowData> data) {
        DataImportResult result = new DataImportResult();
        try {
            if(data.size()>MAXLENGTH){
                throw new RuntimeException(Constants.ImportDataErrorType.DATALEN_ERROR.getErrorCode());
            }

            Map<String, Object> map = tDgapCheckResourceApplicationService.getTdgapResourceApplication(token);
            String resourceId = map.get("WS_ID").toString();
            dataImportService.insertBatch(token,resourceId, data);
            result.setSuccess(true);
        } catch (RuntimeException e) {
            result.setSuccess(false);
            if (Constants.ImportDataErrorType.DATA_ERROR.getErrorCode().equals(e.getLocalizedMessage())) {
                result.setErrorCode(Constants.ImportDataErrorType.DATA_ERROR.getErrorCode());
                result.setErrorDesc(Constants.ImportDataErrorType.DATA_ERROR.getErrorOutDesc());
            }
            if (Constants.ImportDataErrorType.DATALEN_ERROR.getErrorCode().equals(e.getLocalizedMessage())) {
                result.setErrorCode(Constants.ImportDataErrorType.DATALEN_ERROR.getErrorCode());
                result.setErrorDesc(Constants.ImportDataErrorType.DATALEN_ERROR.getErrorOutDesc());

            }
        }
        return result;
    }


    @Override
    public DataImportResult deleteData(String token, RowData data) {
        Map<String, Object> map = tDgapCheckResourceApplicationService.getTdgapResourceApplication(token);
        String resourceId = map.get("WS_ID").toString();
        return dataImportService.deleteData(token,resourceId, data);
    }

    @Override
    public DataImportResult updateData(String token, RowData data) {
        Map<String, Object> map = tDgapCheckResourceApplicationService.getTdgapResourceApplication(token);
        String resourceId = map.get("WS_ID").toString();
        return dataImportService.updateData(token,resourceId, data);
    }

    @Override
    public DataImportResult updateDataLog(String token, RowData data) {
        Map<String, Object> map = tDgapCheckResourceApplicationService.getTdgapResourceApplication(token);
        String resourceId = map.get("WS_ID").toString();
        return dataImportService.updateDataLog(token,resourceId, data);
    }
}
