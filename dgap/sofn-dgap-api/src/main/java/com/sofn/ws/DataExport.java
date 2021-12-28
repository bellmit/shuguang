package com.sofn.ws;

import com.sofn.model.dgap.DataChangeLogResult;
import com.sofn.model.dgap.DataExportCountResult;
import com.sofn.model.dgap.DataExportResult;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by helong on 16-11-22.
 */
@WebService(name="dataexport")
public interface DataExport extends HeartService {

    DataExportResult getData(@WebParam(name="accessToken")String accessToken,
                             @WebParam(name="start")int start, @WebParam(name="end")int end,
                             @WebParam(name="fields")String[] fields,
                             @WebParam(name="condition")String condition);

    DataExportCountResult getdataNumber(@WebParam(name="accessToken")String accessToken, @WebParam(name="condition")String condition);

    DataChangeLogResult getDataChangeLog(@WebParam(name="accessToken")String token,
                                         @WebParam(name="mark")String mark);
}
