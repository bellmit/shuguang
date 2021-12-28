package com.xxl.job.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 查询最大的ID
 * @author heyongjie
 * @date 2019/12/9 16:51
 */
@Mapper
public interface MaxIdDao {

    /**
     * 查询最大ID
     * @param tableName  表名
     * @return 表中最大的ID
     */
    Integer selectMaxId(@Param("tableName") String tableName);

}
