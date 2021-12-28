package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface TDgapDw3Provider {

    public List<TDgapDataImportTable> getValidWdTables(String parttern);

    public List<TDgapDataImportField> getFieldsByTable(String tableId);

    public PageInfo<Map<String,Object>> getDatas(String tableId, Integer start, Integer end, String condition);

    public PageInfo<Map<String,Object>> getDatas(String tableName, Integer start, Integer end, List<String> fields, String condition);

    List<TDgapDataImportField> getByFieldNames(String tableName, String[] fields);

    TDgapDataImportTable getByTableName(String tableId);

    long getAllTablesCount();

    /**
     * 分页查询表详情
     * @param params
     * @return
     */
    List<TDgapDataImportTable> getTablesforPages(Map<String, Object> params);
}
