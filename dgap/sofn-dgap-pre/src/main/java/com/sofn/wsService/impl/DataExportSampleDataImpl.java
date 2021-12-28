package com.sofn.wsService.impl;

import com.sofn.model.dgap.DataExportResult;
import com.sofn.model.dgap.FieldData;
import com.sofn.model.dgap.RowData;
import com.sofn.model.generator.TDgapResourceApplication;
import com.sofn.service.DataImportService;
import com.sofn.service.DwDataService;
import com.sofn.service.TDgapCheckResourceApplicationService;
import com.sofn.ws.DataExportSampleData;
import com.sofn.ws.DataImportSampleData;
import com.sofn.ws.ResourceExportProfile;
import com.sofn.wsService.BaseWsService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by helong on 16-11-22.
 */
@WebService
public class DataExportSampleDataImpl extends BaseWsService implements DataExportSampleData {

    @Autowired
    private TDgapCheckResourceApplicationService tDgapCheckResourceApplicationService;

    @Autowired
    private DwDataService dwDataService;

    @Override
    public List<ResourceExportProfile> listResourceExports() {
        List<ResourceExportProfile> ret = new ArrayList<>();
        List<TDgapResourceApplication> resApplications = tDgapCheckResourceApplicationService.queryResourceExportApplicationList();
        for(TDgapResourceApplication appli:resApplications){
            String accessToken = appli.getCallerToken();
            DataExportResult er = dwDataService.getData(accessToken, 0, 100, null, null);
            List<RowData> rows = er.getData();
            List<String> fieldNames = new ArrayList<>();
            if(rows !=null && rows.size() > 0){
                RowData row = rows.get(0);
                for(FieldData field: row.getFieldsData()){
                    fieldNames.add(field.getColumnName());
                }
            }
            ResourceExportProfile profile = new ResourceExportProfile();
            profile.setToken(accessToken);
            profile.setFieldNames(fieldNames.toArray(new String[0]));
            profile.setDataSize(-1);
            ret.add(profile);
        }
        return ret;
    }
}
