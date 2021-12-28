package com.sofn.wsService.impl;

import com.sofn.model.dgap.DataChangeLogResult;
import com.sofn.model.dgap.DataExportCountResult;
import com.sofn.model.dgap.DataExportResult;
import com.sofn.service.DwDataService;
import com.sofn.service.TDgapCheckResourceApplicationService;
import com.sofn.ws.DataExport;
import com.sofn.wsService.BaseWsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/27.
 */
@WebService
public class DataExportImpl2 extends BaseWsService implements DataExport {

    private static final Logger logger = LoggerFactory.getLogger(DataExportImpl2.class.getName());

    @Autowired
    private DwDataService dwDataService;
    @Autowired
    private TDgapCheckResourceApplicationService tDgapCheckResourceApplicationService;

    @Override
    public DataExportResult getData(String token, int start, int end, String[] fields, String condition) {
        Map<String,Object> map = tDgapCheckResourceApplicationService.getTdgapResourceApplication(token);
        String resourceId = map.get("WS_ID").toString();
        return dwDataService.getData(resourceId, start, end, fields, condition);
    }

    @Override
    public DataExportCountResult getdataNumber(String token, String condition) {
        Map<String,Object> map = tDgapCheckResourceApplicationService.getTdgapResourceApplication(token);
        String resourceId = map.get("WS_ID").toString();
        return dwDataService.getdataNumber(resourceId, condition);
    }

    @Override
    public DataChangeLogResult getDataChangeLog(String token, String mark) {
        Map<String,Object> map = tDgapCheckResourceApplicationService.getTdgapResourceApplication(token);
        String resourceId = map.get("WS_ID").toString();
        return dwDataService.getDataChangeLog(resourceId, mark);
    }
}