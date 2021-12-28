package com.sofn.provider.dgap;

import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.RowData;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;

import java.util.List;

/**
 * Created by weiq on 2016/12/6.
 */
public interface DataImportProvider {
    DataImportResult addData(String token, String resourceId, RowData rowData);
    DataImportResult addDataLog(String token, String resourceId, RowData rowData);
    DataImportResult deleteData(String token,String resourceId, RowData rowData);
    DataImportResult updateData(String token,String resourceId, RowData rowData);
    TDgapDataImportTable getTableNameByResourceId(String resourceId);
    List<TDgapDataImportField> getFieldNameByResourceId(String tableId);
    DataImportResult addBatch(String token,String resourceId, List<RowData> rowData);
    List<RowData> sampleData(String token, int num);
    Integer getMark(String resourceId);
}
