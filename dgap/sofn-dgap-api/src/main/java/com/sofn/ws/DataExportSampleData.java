package com.sofn.ws;

import com.sofn.model.dgap.RowData;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by helong on 16-11-22.
 */
@WebService(name="dataexportSamples")
public interface DataExportSampleData extends HeartService {

    List<ResourceExportProfile> listResourceExports();
}
