package com.sofn.wsService.impl;

import com.sofn.model.dgap.RowData;
import com.sofn.service.DataImportService;
import com.sofn.ws.DataImportSampleData;
import com.sofn.wsService.BaseWsService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by helong on 16-11-22.
 */
@WebService
public class DataImportSampleDataImpl extends BaseWsService implements DataImportSampleData {
    @Autowired
    private DataImportService dataImportService;

    @Override
    public List<RowData> sampleData(String token, int size) {
        return dataImportService.sampleData(token,size);
    }

}
