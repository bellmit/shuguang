package com.sofn.provider.dgap;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface TDgapPreProvider {

    public List<Map<String,Object>> getDatas(String tableName, String mark);

    List<String> getAllTableNames(String pattern);

    /**
     * 取得该资源所有pre库数据
     * @param tableName
     */
    public List<Map<String, Object>>  getAllDatas(String tableName);
}
