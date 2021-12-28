package com.sofn.provider.dgap;

import com.sofn.exception.dgap.DataImportApplyExeption;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.RowData;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;

import java.util.List;

public interface DataImportApplyProvider {

    @Deprecated
    DataImportResult applyDataAdd(String token,String resourceId, RowData rowData) throws DataImportApplyExeption;

    @Deprecated
    DataImportResult applyDataUpdate(String token,String resourceId, RowData rowData) throws DataImportApplyExeption;

    DataImportResult applyDataDelete(String token,String resourceId, RowData rowData) throws DataImportApplyExeption;

    DataImportResult applyDataBatchAdd(String token,String resourceId, List<RowData> data) throws DataImportApplyExeption;

    DataImportResult applyDataUpsert(String token,String resourceId, RowData rowData) throws DataImportApplyExeption;
}
