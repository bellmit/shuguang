package com.sofn.etlquery.dao;


import java.util.List;


public interface Query2HiveDao<T> {
    //获取数据库中的表
    List getAllTables() throws Exception;

    //封装用于获取统计数据的接口
    List getCountData(String key, String tableName, String sort) throws Exception;

    //获取详细数据用于表格展示
    List<T> getDetailData(String tableName,int start,int length) throws Exception;

    Long getCountSize(String tableName) throws Exception;
}
