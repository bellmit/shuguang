package com.sofn.ducss.mapper;

import com.sofn.ducss.model.StoredProcedure;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoredProcedureMapper {

    StoredProcedure queryList();

    void insert(@Param("storedProcedure") StoredProcedure storedProcedure);

    /***
     * 批量新增 create by xl 2021/3/30 11:49
     * @param list
     */
    void insertBatch(@Param("list") List<StoredProcedure> list);

    void deleteById(@Param("id") String id);
}