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
public interface DataImportSampleData extends HeartService {

    List<RowData> sampleData(String token, int size);
}
