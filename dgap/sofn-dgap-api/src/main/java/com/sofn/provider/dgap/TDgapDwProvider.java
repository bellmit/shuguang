package com.sofn.provider.dgap;

import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface TDgapDwProvider {

    public List<TDgapDataImportTable> getValidWdTables(String parttern);

    public List<TDgapDataImportField> getFieldsByTable(String tableId);

    public List<Map<String,Object>> getDatas(String tableId, Integer start, Integer end,String condition);

    public List<Map<String,Object>> getDatas(String tableName,Integer start,Integer end,List<String> fields,String condition);
    //查询仓库表是否包含dataid
    boolean isNullByDataId(String id,String tableName);

    Map<String,Map<String,Object>> getExistedData(String tableName);

    /**
     * 获取总数量
     * @param tableName
     * @param condition
     * @return
     */
    public Long getdataNumber(String tableName,String condition);
}
