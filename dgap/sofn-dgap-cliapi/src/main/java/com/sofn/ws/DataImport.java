package com.sofn.ws;

import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.RowData;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by helong on 16-11-22.
 */
@WebService(name="dataimport")
public interface DataImport extends HeartService {

    DataImportResult addData(@WebParam(name="accessToken")String accessToken,
                             @WebParam(name="data")RowData data);

    DataImportResult addBatch(@WebParam(name="accessToken")String token, @WebParam(name="data")List<RowData> data);

    DataImportResult deleteData(@WebParam(name="accessToken")String accessToken,
                                @WebParam(name="data")RowData data);

    DataImportResult updateData(@WebParam(name="accessToken")String accessToken,
                                @WebParam(name="data")RowData data);

    DataImportResult addLogData(@WebParam(name="accessToken")String token,
                                @WebParam(name="data")RowData data);

    DataImportResult updateDataLog(@WebParam(name="accessToken")String token,
                                   @WebParam(name="data")RowData data);
}
