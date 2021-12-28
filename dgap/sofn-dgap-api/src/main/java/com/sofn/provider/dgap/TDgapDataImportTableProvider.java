package com.sofn.provider.dgap;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapDataImportTable;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface TDgapDataImportTableProvider extends BaseProvider<TDgapDataImportTable> {
    /**
     *  根据资源ID得到表名
     * @param id
     * @return
     */
    String selectENName(String id);

    /**
     * 获取所有表名
     * @return
     */
    List<String> getAlltableNames(String loginedUserId);

    /**
     * 查询表中是否有数据
     * @param enName
     * @return
     * @throws SQLException
     */
    Integer selectData(String enName) throws SQLException;

    /**
     *查询表是否存在的状态
     * @param id
     * @return
     */
    String checkStatus(String id);

    /**
     * 根据资源ID获得改ID对应的表名信息
     * @param id
     * @return
     */
    TDgapDataImportTable getDataById(String id);

    /**
     * 更新表是否存在的状态
     * @param tDgapDataImportTable
     */
   void updateStatus(TDgapDataImportTable tDgapDataImportTable);

    List<String> getAllTables(String id);

    TDgapDataImportTable getImportDataById(String id);

}
