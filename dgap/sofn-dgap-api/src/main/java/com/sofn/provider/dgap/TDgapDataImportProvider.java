package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.exception.dgap.DataImportApplyExeption;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface TDgapDataImportProvider {
    /**
     * 新建表
     * @param id
     * @throws SQLException
     */
    void createTable(String id) throws SQLException;

    /**
     * 新建仓库表
     * @param id
     * @throws SQLException
     */
    void createWsTable(String id) throws SQLException;

    /**
     * 删除表
     * @param id
     */
    void  deleteTable(String id) throws SQLException;

    /**
     * 检查数据是否通过审核的状态
     * @param enName
     * @param dataId
     * @return
     * @throws SQLException
     */
    String checkStatus(String enName,String dataId) throws SQLException;

    /**
     * 审核数据
     * @param tableName
     * @param status
     * @param dataId
     * @throws SQLException
     */
     void updateDataStatus(String tableName,String status,String dataId) throws SQLException, DataImportApplyExeption;

    /**
     * 分页查询数据
     * @param start
     * @param length
     * @param status
     * @param tableName
     * @return
     */
    PageInfo<Map<String, Object>> dataList(Integer start, Integer length, String status, String tableName);
    /**
     * 分页查询数据
     * @param start
     * @param length
     * @param status
     * @param tableName
     * @return
     */
    PageInfo<Map<String, Object>> dataList(Integer start, Integer length, String status, String tableName, List<String> leftKey,List<String> rightValue);


    Map<String,Object> getDataById(String tableName, String id);
}
